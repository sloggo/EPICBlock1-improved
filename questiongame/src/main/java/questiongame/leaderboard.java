package questiongame;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
//method that manages leaderboard 
public class leaderboard {
    public static void generateLeaderboard(int userScore){
        List<Document> userArray = database.getAllUsers();

        // Convert the list to an array
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n\tLEADERBOARD - You got "+userScore+" score!\n");
        System.out.println("Username \t\t Score");
        System.out.println("-----------------------------------");

        List<Integer> scores = new ArrayList<>();

        for (Document user : userArray) {
            System.out.println(user.get("username") + " \t\t\t "+ user.get("score"));
            scores.add(Integer.parseInt(user.get("score").toString()));
        }

        double mean = score.meanCalculator(scores);
        System.out.println("\nMean score: "+mean);

        double median = score.medianCalculator(scores);
        System.out.println("Median score: "+median);

        double sd = score.standardDeviation(scores);
        System.out.println("Standard Deviation score: "+sd+"\n");
    }
    public static void getStatistics(){

    }
}