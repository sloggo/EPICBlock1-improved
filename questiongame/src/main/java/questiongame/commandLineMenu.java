package questiongame;

import java.util.Scanner;

import org.bson.Document;

public class commandLineMenu {
//class for selecting gamemode
    public static void gameModeMenu(Question[] Questions, User user){
        Scanner scanner = new Scanner(System.in);
        System.out.println(user.isAdmin());
        modeController mode = new modeController(Questions, user);
        if(user.isAdmin() == true){
            System.out.println("Choose a game mode: Enter 1 for Random Quiz , 2 for Difficulty Based Quiz, or 3 for Sudden Death, or 4 for settings");
            int gameModeChoice = scanner.nextInt();
            if (gameModeChoice == 1){
                mode.randomMode();
            }else if (gameModeChoice == 2){
                mode.difficultyMode();
            }else if (gameModeChoice == 3){
                mode.suddenDeath();
            }else if (gameModeChoice == 4){
                user.settings();
            } else {
                System.out.println("Invalid input");
            }
        } else{
            System.out.println("Choose a game mode: Enter 1 for Random Quiz , 2 for Difficulty Based Quiz, or 3 for Sudden Death");
            int gameModeChoice = scanner.nextInt();
            if (gameModeChoice == 1){
                mode.randomMode();
            }else if (gameModeChoice == 2){
                mode.difficultyMode();
            }else if (gameModeChoice == 3){
                mode.suddenDeath();
            } else {
                System.out.println("Invalid input");
            }
        }

        
    }

}