package challenges;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveStreamChallenge {

    /**
     * ═══════════════════════════════════════════════════════════════════════════════
     * CHALLENGE 07: Primitive Streams (Performance & Stats)
     * ═══════════════════════════════════════════════════════════════════════════════
     * 
     * SCENARIO:
     * Working with boxed types (Integer, Double) is expensive due to overhead.
     * Use IntStream, LongStream, DoubleStream for high-performance numerical operations.
     * 
     * ═══════════════════════════════════════════════════════════════════════════════
     */

    /**
     * TASK 1: Range Generation
     * Generate an array of integers from start (inclusive) to end (exclusive).
     * Use IntStream.range()
     */
    public int[] generateRange(int start, int end) {
        return IntStream.range(start, end).toArray();
    }

    /**
     * TASK 2: Summary Statistics
     * Calculate count, sum, min, average, and max of an array of scores in one pass.
     * Use summaryStatistics()
     */
    public IntSummaryStatistics getScoreStats(int[] scores) {
        return IntStream.of(scores).summaryStatistics();
    }

    /**
     * TASK 3: Boxed Transformations
     * Convert an IntStream of ID numbers into a Stream of String "ID-{n}".
     * Use mapToObj()
     */
    public List<String> generateIds(int count) {
        return IntStream.range(0, count)
                    .mapToObj(i -> "ID-" + i)
                    .toList();
    }

    /**
     * TASK 4: Random Doubles
     * Generate 5 random double values between 0.0 and 1.0.
     * Use DoubleStream.generate or Random.doubles
     */
    public double[] generateRandomPrices() {
        // TODO: new Random().doubles(...).limit(5).toArray()
        return DoubleStream.generate(() -> Math.random()).limit(5).toArray();
    }
}
