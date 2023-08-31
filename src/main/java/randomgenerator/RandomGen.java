package randomgenerator;

import java.util.Random;

public class RandomGen {
    // Values that may be returned by nextNum() private
    private final int[] randomNums;
    private final float[] cumulativeProbabilities;
    private final Random random;

    public RandomGen(int[] randomNums, float[] probabilities) {

        if (randomNums.length != probabilities.length) {
            throw new IllegalArgumentException(String.format("Length of randomNums and probabilities arrays " +
                    "must be the same, but was %s and %s respectively", randomNums.length, probabilities.length));
        }

        this.randomNums = randomNums;
        this.random = new Random();
        cumulativeProbabilities = new float[probabilities.length];
        this.calculateCumulativeProbabilities(probabilities);
    }

    private void calculateCumulativeProbabilities(float[] probabilities) {
        float cumulativeProbability = 0;
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            cumulativeProbabilities[i] = cumulativeProbability;
        }
        if(cumulativeProbability != 1) {
            throw new IllegalArgumentException(
                    String.format("Sum of probabilities must be 1, but was %f", cumulativeProbability));
        }
    }

    /**
     Returns one of the randomNums. When this method is called
     multiple times over a long period, it should return the
     numbers roughly with the initialized probabilities.
     */
    public int nextNum() {
        // generate a random float between 0 and 1
        float randomValue = random.nextFloat();

        for (int i = 0; i < cumulativeProbabilities.length; i++) {
            if (randomValue <= cumulativeProbabilities[i]) {
                return randomNums[i];
            }
        }
        // returning an int, but this line should never be reached
        return 0;
    }
}
