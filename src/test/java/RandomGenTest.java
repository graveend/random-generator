package test.java;

import main.java.RandomGen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomGenTest {
    @Test
    public void testNextNumGeneratesNumbersWithExpectedProbabilities() {
        int[] randomNums = {-1, 0, 1, 2, 3};
        float[] probabilities = {0.01f, 0.3f, 0.58f, 0.1f, 0.01f};
        RandomGen randomGen = new RandomGen(randomNums, probabilities);

        // call nextNum() multiple times and count the occurrences
        int[] count = new int[randomNums.length];
        int numIterations = 100000;
        for (int i = 0; i < numIterations; i++) {
            int num = randomGen.nextNum();
            for (int j = 0; j < randomNums.length; j++) {
                if (num == randomNums[j]) {
                    count[j]++;
                    break;
                }
            }
        }

        // calculate the actual probabilities based on the counts
        float[] actualProbabilities = new float[probabilities.length];
        for (int i = 0; i < actualProbabilities.length; i++) {
            actualProbabilities[i] = (float) count[i] / numIterations;
        }

        // compare the actual probabilities with the expected probabilities with a tolerance
        float tolerance = 0.02f;
        for (int i = 0; i < randomNums.length; i++) {
            assertEquals(probabilities[i], actualProbabilities[i], tolerance);
        }
    }
}
