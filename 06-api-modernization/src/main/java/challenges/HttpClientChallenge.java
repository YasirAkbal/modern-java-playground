package challenges;

import data.SampleDataGenerator;
import model.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * CHALLENGE 01: Java 11 HTTP Client - Modern API Communication
 * ═══════════════════════════════════════════════════════════════════════════════
 *
 * SCENARIO:
 * You are building the Integration Module for EduMaster that needs to communicate
 * with external REST APIs. The legacy HttpURLConnection is verbose and difficult
 * to use. You'll use the new HttpClient API introduced in Java 11 to perform
 * synchronous and asynchronous HTTP requests.
 *
 * FOCUS:
 * - HttpClient configuration and creation
 * - Synchronous requests with send()
 * - Asynchronous requests with sendAsync()
 * - Request building with HttpRequest.Builder
 * - POST requests with JSON body
 * - Response handling with BodyHandlers
 *
 * ═══════════════════════════════════════════════════════════════════════════════
 */
public class HttpClientChallenge {

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Instructor> instructors;
    private final HttpClient client;

    public HttpClientChallenge() {
        this.students = SampleDataGenerator.generateStudents(20);
        this.instructors = SampleDataGenerator.generateInstructors(5);
        this.courses = SampleDataGenerator.generateCourses(10, instructors);

        // Create a configured HttpClient with timeout and redirect policy
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 1: Synchronous HTTP Requests
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 1.1: Basic Synchronous GET Request
     *
     * Fetch a single post from the JSONPlaceholder API synchronously.
     * The HttpClient.send() method blocks until the response is received.
     *
     * EXAMPLE:
     * String content = fetchPostSync();
     * // Returns: "{\n  \"userId\": 1,\n  \"id\": 1,\n  \"title\": \"...\", ...}"
     *
     * @return JSON response body as String
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public String fetchPostSync() throws IOException, InterruptedException {
        return null;
    }

    /**
     * TASK 1.2: Fetch with Custom Headers
     *
     * Make a GET request with custom headers (User-Agent, Accept).
     * Return the response status code.
     *
     * @param url The URL to fetch
     * @return HTTP status code (e.g., 200, 404, 500)
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public int fetchWithHeaders(String url) throws IOException, InterruptedException {
        return 0;
    }

    /**
     * TASK 1.3: Fetch Course Data from API
     *
     * In a real scenario, you would fetch course data from your backend API.
     * For this exercise, fetch data from JSONPlaceholder and simulate
     * returning course count.
     *
     * URL: https://jsonplaceholder.typicode.com/posts
     *
     * @return Number of items in the response (simulating course count)
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public int fetchCourseCount() throws IOException, InterruptedException {
        // TODO: Create GET request to /posts endpoint
        // TODO: Parse response and count items (hint: count '{' occurrences or use JSON library)
        return 0;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 2: Asynchronous HTTP Requests
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 2.1: Asynchronous GET Request
     *
     * Fetch content asynchronously using sendAsync().
     * This returns a CompletableFuture that completes when the response arrives.
     * The calling thread is not blocked.
     *
     * EXAMPLE:
     * CompletableFuture<String> future = fetchPostAsync();
     * future.thenAccept(body -> System.out.println(body));
     *
     * @return CompletableFuture containing the response body
     */
    public CompletableFuture<String> fetchPostAsync() {
        return null;    
    }

    /**
     * TASK 2.2: Async Request with Transformation
     *
     * Fetch data asynchronously and transform the response.
     * Extract the "title" field from the JSON response (simple string parsing).
     *
     * @return CompletableFuture containing the extracted title
     */
    public CompletableFuture<String> fetchPostTitleAsync() {
        // TODO: sendAsync to /posts/1
        // TODO: Use thenApply to extract title from JSON (hint: indexOf "\"title\":")
        return null;
    }

    /**
     * TASK 2.3: Handle Async Errors
     *
     * Make an async request and handle potential errors gracefully.
     * If the request fails, return a default message.
     *
     * @param url URL to fetch
     * @return CompletableFuture with response body or error message
     */
    public CompletableFuture<String> fetchWithErrorHandling(String url) {
        // TODO: sendAsync with exceptionally() to catch errors
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 3: POST Requests with JSON Data
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 3.1: Create Post with JSON Body
     *
     * Send a POST request with a JSON body to create a new resource.
     * The JSONPlaceholder API will echo back the created resource with an ID.
     *
     * EXAMPLE:
     * String response = createPost();
     * // Returns: "{\n  \"title\": \"foo\",\n  \"body\": \"bar\",\n  \"userId\": 1,\n  \"id\": 101\n}"
     *
     * @return Response body containing the created post
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public String createPost() throws IOException, InterruptedException {
        return null;
    }

    /**
     * TASK 3.2: Post Course Data to API
     *
     * Create a JSON payload from a Course object and POST it to the API.
     * Use the first course from the courses list.
     *
     * Simulate posting to: https://jsonplaceholder.typicode.com/posts
     *
     * EXAMPLE JSON:
     * {
     *   "title": "Complete Java Masterclass",
     *   "body": "A comprehensive Java course",
     *   "userId": 1
     * }
     *
     * @return Response body from the API
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public String postCourseData() throws IOException, InterruptedException {
        return null;
    }

    /**
     * TASK 3.3: POST with Async
     *
     * Send a POST request asynchronously.
     * Create a new "comment" on a post.
     *
     * @param postId The post ID to comment on
     * @param name Commenter name
     * @param email Commenter email
     * @param body Comment text
     * @return CompletableFuture with the response body
     */
    public CompletableFuture<String> createCommentAsync(int postId, String name, String email, String body) {
        // TODO: Build JSON with postId, name, email, body
        // TODO: sendAsync POST to /posts/{postId}/comments
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 4: Response Handling and Status Codes
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 4.1: Check Response Status
     *
     * Make a request and verify if the response was successful (status 200-299).
     *
     * @param url URL to check
     * @return true if status code is 2xx, false otherwise
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public boolean isResponseSuccessful(String url) throws IOException, InterruptedException {
        // TODO: Send request and check response.statusCode() >= 200 && < 300
        return false;
    }

    /**
     * TASK 4.2: Extract Response Headers
     *
     * Fetch a resource and return the Content-Type header value.
     *
     * @param url URL to fetch
     * @return Content-Type header value
     * @throws IOException if network error occurs
     * @throws InterruptedException if request is interrupted
     */
    public String getContentType(String url) throws IOException, InterruptedException {
        // TODO: Send request and extract headers().firstValue("Content-Type")
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // PART 5: Practical Integration Example
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * TASK 5.1: Batch Course Upload
     *
     * Upload multiple courses to the API asynchronously.
     * Use CompletableFuture.allOf() to wait for all uploads to complete.
     *
     * @param coursesToUpload List of courses to upload
     * @return CompletableFuture that completes when all uploads finish
     */
    public CompletableFuture<Void> batchUploadCourses(List<Course> coursesToUpload) {
        // TODO: Map each course to a POST request (use sendAsync)
        // TODO: Collect all futures and use CompletableFuture.allOf()
        return null;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // TEST RUNNER
    // ═══════════════════════════════════════════════════════════════════════════

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");
        System.out.println("           HTTP Client Challenge - Modern API Communication");
        System.out.println("═══════════════════════════════════════════════════════════════════════════════");

        HttpClientChallenge challenge = new HttpClientChallenge();

        try {
            // Test 1.1: Synchronous GET
            System.out.println("\n[TEST 1.1] Synchronous GET Request:");
            String post = challenge.fetchPostSync();
            System.out.println("Fetched post: " + post.substring(0, Math.min(100, post.length())) + "...");

            // Test 1.2: Custom Headers
            System.out.println("\n[TEST 1.2] GET with Custom Headers:");
            int statusCode = challenge.fetchWithHeaders("https://jsonplaceholder.typicode.com/posts/1");
            System.out.println("Status Code: " + statusCode);

            // Test 2.1: Asynchronous GET
            System.out.println("\n[TEST 2.1] Asynchronous GET Request:");
            challenge.fetchPostAsync()
                    .thenAccept(body -> System.out.println("Async response: " + body.substring(0, Math.min(100, body.length())) + "..."))
                    .join(); // Wait for completion (for demo purposes)

            // Test 3.1: POST Request
            System.out.println("\n[TEST 3.1] POST Request:");
            String created = challenge.createPost();
            System.out.println("Created post: " + created);

            // Test 3.2: Post Course Data
            System.out.println("\n[TEST 3.2] POST Course Data:");
            String courseResponse = challenge.postCourseData();
            System.out.println("Course posted: " + courseResponse);

            System.out.println("\n═══════════════════════════════════════════════════════════════════════════════");
            System.out.println("Challenge completed! Implement remaining TODOs to master HttpClient.");
            System.out.println("═══════════════════════════════════════════════════════════════════════════════");

        } catch (Exception e) {
            System.err.println("Error during testing: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
