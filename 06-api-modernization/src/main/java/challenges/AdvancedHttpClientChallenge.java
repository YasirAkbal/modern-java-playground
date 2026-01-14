package challenges;

import data.SampleDataGenerator;
import model.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 02: Advanced HTTP Client - Enterprise Patterns
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are building the Enterprise Integration Layer for EduMaster that requires
 * advanced HTTP capabilities: authentication, streaming large responses, concurrent
 * requests, file downloads, and custom request interceptors. These patterns are
 * essential for production-grade microservices communication.
 *
 * FOCUS:
 * - Streaming responses with BodyHandlers.ofLines()
 * - File downloads with BodyHandlers.ofFile()
 * - Parallel/concurrent requests with CompletableFuture.allOf()
 * - HTTP Basic Authentication
 * - Request timeouts and retry logic
 * - Custom request/response interceptors
 * - Connection pooling and HTTP/2
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class AdvancedHttpClientChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Instructor> instructors;
    private final HttpClient client;
    private final HttpClient http2Client;

    public AdvancedHttpClientChallenge() {
        this.students = SampleDataGenerator.generateStudents(50);
        this.instructors = SampleDataGenerator.generateInstructors(10);
        this.courses = SampleDataGenerator.generateCourses(20, instructors);

        // Standard HTTP client
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // HTTP/2 enabled client with connection pool
        this.http2Client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: Streaming and Large Response Handling
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Stream Response Lines
     *
     * Don't load the entire response body into memory. Stream it line by line.
     * This is crucial for processing large responses (logs, CSV exports, etc.).
     * Use BodyHandlers.ofLines() which returns a Stream<String>.
     *
     * EXAMPLE:
     * streamTextData("https://www.w3.org/TR/PNG/iso_8859-1.txt");
     * // Processes each line without loading entire file into memory
     *
     * @param url URL to fetch (text content)
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public void streamTextData(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(URI.create(url))
                                        .GET()
                                        .build();

        HttpResponse<Stream<String>> response = client.send(request, BodyHandlers.ofLines());

        response.body()
            .forEach(System.out::println);
    }

    /**
     * TASK 1.2: Count Lines in Streamed Response
     *
     * Fetch a text resource and count the number of lines WITHOUT loading
     * the entire response into memory.
     *
     * @param url URL to text resource
     * @return Number of lines in the response
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public long countLinesInResponse(String url) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder(new URI(url))
                                .GET()
                                .build();

        long numberOfLines = client.send(request, BodyHandlers.ofLines())
                                .body()
                                .count();
        return numberOfLines;
    }

    /**
     * TASK 1.3: Filter Streamed Data
     *
     * Stream a response and filter lines containing a specific keyword.
     * Collect matching lines into a list.
     *
     * @param url URL to fetch
     * @param keyword Keyword to search for
     * @return List of lines containing the keyword
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public List<String> filterStreamedLines(String url, String keyword) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder(new URI(url))
                                .GET()
                                .build();

        List<String> filteredLines = client.send(request, BodyHandlers.ofLines())
                                    .body()
                                    .filter(line -> line.contains(keyword))
                                    .collect(Collectors.toList());

        return filteredLines;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: File Downloads
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Download File to Disk
     *
     * Download a file directly to disk without loading it into memory.
     * Use BodyHandlers.ofFile(Path) to stream directly to filesystem.
     *
     * EXAMPLE:
     * downloadFile("https://jsonplaceholder.typicode.com/posts",
     *              Paths.get("downloads/posts.json"));
     * // Downloads directly to file
     *
     * @param url URL to download
     * @param destination Path where file should be saved
     * @return Path to the downloaded file
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public Path downloadFile(String url, Path destination) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder(new URI(url))
                                .GET()
                                .build();
        
        HttpResponse<Path> response = client.send(request, BodyHandlers.ofFile(destination));
        return response.body();
    }

    /**
     * TASK 2.2: Download with Progress Tracking
     *
     * Download a file and track download progress by monitoring response headers.
     * Return the file size from Content-Length header.
     *
     * @param url URL to download
     * @param destination Path to save file
     * @return File size in bytes (from Content-Length header)
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public long downloadWithProgress(String url, Path destination) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder(new URI(url))
                                .GET()
                                .build();
        
        HttpResponse<Void> response = client.send(request, BodyHandlers.discarding());
        long contentSize = Long.parseLong(response.headers().firstValue("Content-Length").orElse("0"));

        return contentSize;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: Parallel and Concurrent Requests
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Fetch Multiple URLs Concurrently
     *
     * Trigger multiple async HTTP requests in parallel and wait for all to complete.
     * This is essential for aggregating data from multiple microservices.
     *
     * EXAMPLE:
     * List<String> urls = List.of(
     *     "https://jsonplaceholder.typicode.com/posts/1",
     *     "https://jsonplaceholder.typicode.com/posts/2",
     *     "https://jsonplaceholder.typicode.com/posts/3"
     * );
     * List<String> results = fetchAllUrls(urls);
     * // Returns list of response bodies
     *
     * @param urls List of URLs to fetch concurrently
     * @return List of response bodies in the same order as input URLs
     */
    public List<String> fetchAllUrls(List<String> urls) {
        List<CompletableFuture<String>> completableFutureList = urls.stream()
            .map(url -> {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

                return client.sendAsync(request, BodyHandlers.ofString())
                    .thenApply(HttpResponse::body);
            })
            .toList();

        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]))
            .join(); 

        return completableFutureList.stream()
            .map(CompletableFuture::join)
            .toList();
    }

    /**
     * TASK 3.2: Parallel Course Data Fetch
     *
     * Simulate fetching course details from multiple microservices concurrently.
     * For each course, make an async request to jsonplaceholder.typicode.com/posts/{id}
     * where id is derived from course position in list.
     *
     * @return List of fetched responses (one per course)
     */
    public List<String> fetchCourseDetailsParallel() {
        List<CompletableFuture<String>> futures = new ArrayList<>();

        IntStream.range(0, Math.min(10, courses.size()))
            .forEach(i -> {
                int courseId = i + 1; 
                String url = "https://jsonplaceholder.typicode.com/posts/" + courseId;

                HttpRequest request = HttpRequest.newBuilder()
                                            .uri(URI.create(url))
                                            .GET()
                                            .build();

                CompletableFuture<String> future = client.sendAsync(request, BodyHandlers.ofString())
                                                    .thenApply(HttpResponse::body);
                futures.add(future);
            });

        return futures.stream()
                .map(CompletableFuture::join)
                .toList();
    }

    /**
     * TASK 3.3: Race Multiple Requests
     *
     * Send the same request to multiple mirror servers and return the fastest response.
     * Use CompletableFuture.anyOf() to get the first completed response.
     *
     * @param urls List of mirror URLs
     * @return Response from the fastest server
     */
    public String fetchFromFastestServer(List<String> urls) {
        var completableFutureList = urls.stream().map(url -> {
            HttpRequest request = HttpRequest.newBuilder()
                                        .uri(URI.create(url))
                                        .GET()
                                        .build();
            
            return client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body);
        })
        .toList();

        var firstResponse = CompletableFuture.anyOf(completableFutureList.toArray(new CompletableFuture[0]))
            .join();

        return (String) firstResponse;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: Authentication and Security
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: HTTP Basic Authentication
     *
     * Make a request with HTTP Basic Authentication.
     * Encode username:password in Base64 and add Authorization header.
     *
     * EXAMPLE:
     * String response = fetchWithBasicAuth(
     *     "https://httpbin.org/basic-auth/user/passwd",
     *     "user",
     *     "passwd"
     * );
     *
     * @param url URL requiring authentication
     * @param username Username
     * @param password Password
     * @return Response body
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public String fetchWithBasicAuth(String url, String username, String password)
            throws IOException, InterruptedException {
        String auth = username + ":" + password; 
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .header("Authorization", "Basic " + encodedAuth)
                                .GET()
                                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }

    /**
     * TASK 4.2: Bearer Token Authentication
     *
     * Make a request with Bearer token authentication (OAuth 2.0 / JWT).
     *
     * @param url URL to access
     * @param token Bearer token
     * @return Response body
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public String fetchWithBearerToken(String url, String token)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .header("Authorization", "Bearer " + token)
                                .GET()
                                .build();
                                
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return response.body();
    }

    /**
     * TASK 4.3: API Key Authentication
     *
     * Make a request with API key in custom header (e.g., X-API-Key).
     *
     * @param url URL to access
     * @param apiKey API key value
     * @return Response body
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public String fetchWithApiKey(String url, String apiKey)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .header("X-API-Key", apiKey)
                                .GET()
                                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return response.body();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Timeouts and Retry Logic
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 5.1: Request with Custom Timeout
     *
     * Make a request that fails if it takes longer than the specified timeout.
     * The timeout is set per-request (not client-wide).
     *
     * @param url URL to fetch
     * @param timeoutSeconds Timeout in seconds
     * @return Response body
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public String fetchWithTimeout(String url, int timeoutSeconds)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .timeout(Duration.ofSeconds(timeoutSeconds))
                                .GET()
                                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        
        return response.body();
    }

    /**
     * TASK 5.2: Retry on Failure
     *
     * Implement a simple retry mechanism. Try the request up to maxRetries times.
     * Wait 1 second between retries.
     *
     * @param url URL to fetch
     * @param maxRetries Maximum number of retry attempts
     * @return Response body
     * @throws IOException if all retries fail
     * @throws InterruptedException if request is interrupted
     * @throws ExecutionException 
     */
    public String fetchWithRetry(String url, int maxRetries)
            throws IOException, InterruptedException, ExecutionException {
        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .GET()
                                .build();
        
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, BodyHandlers.ofString())
                        .thenComposeAsync(r -> retry(request, r, 1, maxRetries, false));
        
        return response.get().body();
    }

    private CompletableFuture<HttpResponse<String>> retry(HttpRequest request, HttpResponse<String> response, int count, int maxRetries, boolean exponentialBackoff) {
        if (response.statusCode() == 200 || count >= maxRetries) {
            return CompletableFuture.completedFuture(response);
        } else {
            long delaySeconds = exponentialBackoff ? (long) Math.pow(2, count) : 1;
            Executor afterDelay = CompletableFuture.delayedExecutor(delaySeconds, TimeUnit.SECONDS);
            return CompletableFuture.supplyAsync(() -> null, afterDelay)
                .thenComposeAsync(v -> client.sendAsync(request, BodyHandlers.ofString()))
                .thenComposeAsync(r -> retry(request, r, count + 1, maxRetries, exponentialBackoff));
        }
    }

    /**
     * TASK 5.3: Exponential Backoff Retry
     *
     * Implement retry with exponential backoff: 1s, 2s, 4s, 8s, etc.
     *
     * @param url URL to fetch
     * @param maxRetries Maximum retry attempts
     * @return Response body
     * @throws IOException if all retries fail
     * @throws InterruptedException if interrupted
     */
    public String fetchWithExponentialBackoff(String url, int maxRetries)
            throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .GET()
                                .build();

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, BodyHandlers.ofString())
                        .thenComposeAsync(r -> retry(request, r, 1, maxRetries, true));

        try {
            return response.get().body();
        } catch (ExecutionException e) {
            throw new IOException("All retries failed", e);
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 6: HTTP/2 and Performance Optimization
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 6.1: HTTP/2 Multiplexing
     *
     * Use HTTP/2 client to send multiple requests over the same connection.
     * HTTP/2 allows multiplexing multiple requests without head-of-line blocking.
     *
     * @param urls List of URLs to fetch using HTTP/2
     * @return List of response bodies
     */
    public List<String> fetchWithHttp2(List<String> urls) {
        List<CompletableFuture<String>> completableFutureList = urls.stream()
            .map(url -> {
                HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

                return http2Client.sendAsync(request, BodyHandlers.ofString())
                    .thenApply(HttpResponse::body);
            })
            .toList();

        CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0]))
            .join(); 

        return completableFutureList.stream()
            .map(CompletableFuture::join)
            .toList();
    }

    /**
     * TASK 6.2: Compare HTTP/1.1 vs HTTP/2 Performance
     *
     * Fetch 10 URLs using HTTP/1.1 and measure time, then do the same with HTTP/2.
     * Return the time difference in milliseconds.
     *
     * @return Array: [http1Time, http2Time, timeSaved]
     */
    public long[] compareHttpVersions() {
        List<String> urls = IntStream.range(0, 100)
                                .boxed()
                                .map(i -> "https://jsonplaceholder.typicode.com/posts/" + (i+1))
                                .toList();

        LocalTime http1Start = LocalTime.now();
        fetchAllUrls(urls);
        LocalTime http1End = LocalTime.now();

        LocalTime http2Start = LocalTime.now();
        fetchWithHttp2(urls);
        LocalTime http2End = LocalTime.now();
        
        return new long[] {
            Duration.between(http1Start, http1End).toMillis(),
            Duration.between(http2Start, http2End).toMillis(),
            Duration.between(http1Start, http1End).toMillis() - Duration.between(http2Start, http2End).toMillis()
        };
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 7: Request/Response Interceptors Pattern
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 7.1: Logging Interceptor
     *
     * Create a method that wraps HTTP requests with logging.
     * Log the request URL and response status code.
     *
     * @param url URL to fetch
     * @return Response body
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public String fetchWithLogging(String url) throws IOException, InterruptedException {
        System.out.println("[REQUEST] GET " + url);

        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .GET()
                                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        System.out.println("[RESPONSE] Status: " + response.statusCode());

        return response.body();
    }

    /**
     * TASK 7.2: Custom User-Agent Interceptor
     *
     * Create a reusable method that adds custom headers to every request.
     * Add User-Agent, Accept, and X-Client-Version headers.
     *
     * @param url URL to fetch
     * @return Response body
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public String fetchWithCustomHeaders(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .header("User-Agent", "EduMaster-Client/1.0")
                                .header("Accept", "application/json")
                                .header("X-Client-Version", "1.0.0")
                                .GET()
                                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        return response.body();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // TEST RUNNER
    // ═══════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");
        System.out.println("        Advanced HTTP Client Challenge - Enterprise Patterns");
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");

        AdvancedHttpClientChallenge challenge = new AdvancedHttpClientChallenge();

        try {
            // Test 1.1: Stream Text Data
            System.out.println("\n[TEST 1.1] Streaming Response Lines:");
            challenge.streamTextData("https://www.w3.org/TR/PNG/iso_8859-1.txt");

            // Test 1.2: Count Lines
            System.out.println("\n[TEST 1.2] Count Lines in Response:");
            long lineCount = challenge.countLinesInResponse("https://www.w3.org/TR/PNG/iso_8859-1.txt");
            System.out.println("Total lines: " + lineCount);

            // Test 2.1: Download File
            System.out.println("\n[TEST 2.1] Download File:");
            Path downloaded = challenge.downloadFile(
                    "https://jsonplaceholder.typicode.com/posts/1",
                    Paths.get("test-download.json")
            );
            System.out.println("File downloaded to: " + downloaded);

            // Test 3.1: Parallel Requests
            System.out.println("\n[TEST 3.1] Fetch Multiple URLs Concurrently:");
            List<String> urls = List.of(
                    "https://jsonplaceholder.typicode.com/posts/1",
                    "https://jsonplaceholder.typicode.com/posts/2",
                    "https://jsonplaceholder.typicode.com/posts/3"
            );
            List<String> results = challenge.fetchAllUrls(urls);
            System.out.println("Fetched " + results.size() + " responses concurrently");

            // Test 3.2: Parallel Course Data
            System.out.println("\n[TEST 3.2] Fetch Course Details Parallel:");
            List<String> courseResults = challenge.fetchCourseDetailsParallel();
            System.out.println("Fetched " + courseResults.size() + " course details");

            // Test 5.1: Request with Timeout
            System.out.println("\n[TEST 5.1] Request with Timeout:");
            String timeoutResult = challenge.fetchWithTimeout(
                    "https://jsonplaceholder.typicode.com/posts/1",
                    5
            );
            System.out.println("Response received within timeout");

            // Test 5.2: Retry on Failure
            System.out.println("\n[TEST 5.2] Retry Mechanism:");
            String retryResult = challenge.fetchWithRetry(
                    "https://jsonplaceholder.typicode.com/posts/1",
                    3
            );
            System.out.println("Request succeeded (with retries if needed)");

            // Test 6.1: HTTP/2 Multiplexing
            System.out.println("\n[TEST 6.1] HTTP/2 Multiplexing:");
            List<String> http2Results = challenge.fetchWithHttp2(urls);
            System.out.println("Fetched " + http2Results.size() + " responses using HTTP/2");

            // Test 6.2: Compare HTTP/1.1 vs HTTP/2 Performance
            System.out.println("\n[TEST 6.2] HTTP/1.1 vs HTTP/2 Performance:");
            long[] http1vs2Results = challenge.compareHttpVersions();
            System.out.println("HTTP/1.1 Time: " + http1vs2Results[0] + " ms");
            System.out.println("HTTP/2 Time: " + http1vs2Results[1] + " ms");
            System.out.println("Time Saved with HTTP/2: " + http1vs2Results[2] + " ms");

            // Test 7.1: Logging Interceptor
            /*System.out.println("\n[TEST 7.1] Logging Interceptor:");
            challenge.fetchWithLogging("https://jsonplaceholder.typicode.com/posts/1");

            System.out.println("\n═══════════════════════════════════════════════════════════════════════════════");
            System.out.println("Challenge completed! Implement remaining TODOs to master advanced patterns.");
            System.out.println("═══════════════════════════════════════════════════════════════════════════════");*/

        } catch (Exception e) {
            System.err.println("Error during testing: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
