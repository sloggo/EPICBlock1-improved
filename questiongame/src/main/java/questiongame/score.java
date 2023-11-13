package questiongame;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class score{

    public static double meanCalculator(List<Integer> userScores){

        // Calculating the sum of the numbers
        int sum = 0;
        for (int number : userScores) {
            sum += number;
        }

        // Calculating the mean
        double mean = (double) sum / userScores.size();

        // Returning the mean
        return mean;
    }
    public static double medianCalculator(List<Integer> userScores){
        Collections.sort(userScores);

        double median;
        int length = userScores.size();
        if (length % 2 == 0) {
            // If the length is even, average the middle two elements
            median = (double) (userScores.get(userScores.size() / 2 - 1) + userScores.get(userScores.size() / 2) / 2);
        } else {
            // If the length is odd, pick the middle element
            median = userScores.get(length/2);
        }
        // Returning the median
        return median;
}
    public static double standardDeviation(List<Integer> userScores){
        //Calculating the variance
        double variance = 0;
        double mean = meanCalculator(userScores);
        for (int number : userScores) {
            variance += Math.pow(number - mean, 2);
        }
        variance /= userScores.size();

        // Step 3: Calculate the standard deviation (square root of the variance)
        double standardDeviation = Math.sqrt(variance);

        // Return the standard deviation
        return standardDeviation;
    }

    public static void updateScore(User user, int increase){
        user.score += increase;
        database.updateUser(user);
    }
}
