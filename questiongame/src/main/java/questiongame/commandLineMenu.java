package questiongame;

import java.util.Scanner;

import org.bson.Document;

public class commandLineMenu {
//class for selecting gamemode
    public static void gameModeMenu(Question[] Questions, User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a game mode: Enter 1 for Random Quiz , 2 for Difficulty Based Quiz, or 3 for Sudden Death");
        int gameModeChoice = scanner.nextInt();
        if (gameModeChoice == 1){
            modeController.randomMode(Questions, user);
        }else if (gameModeChoice == 2){
            modeController.difficultyMode(Questions, user);
        }else if (gameModeChoice == 3){
            modeController.suddenDeath(Questions, user);
        } else {
            System.out.println("Invalid input");
        }
    }

}