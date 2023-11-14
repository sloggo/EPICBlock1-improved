package questiongame;
//importing java extensions
import java.util.Arrays;
//importing scanner package
import java.util.Scanner;
import org.bson.Document;

//creating public class
public class main {
    
    public static void main(String[] args){

        Question[] Questions = {
        };
        //login/signup prompt loop
        boolean loggedIn = false;

        while(!loggedIn){
            System.out.println("\n\nWELCOME TO QUIZWIZZ!");
            System.out.println("Copyright 2023 - KISS Solutions Plc");

            User user = accountController.menu();
            if(user != null){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                //clearing console
                System.out.println("Welcome "+user.username+"!");
                loggedIn = true;
                commandLineMenu.gameModeMenu(Questions, user);
            }
        }
    }  


}