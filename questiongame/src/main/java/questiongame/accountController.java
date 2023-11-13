package questiongame;

//importing java package to take user inputs
import java.util.Scanner;

import javax.print.Doc;

import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

//creating the main class
public class accountController {
    
    public static User menu(){
        Scanner scanner = new Scanner(System.in);
        //offering the user the choice to sign up or login
        System.out.println("Enter 0 to sign up or enter 1 to Log in");
        int menuchoice = scanner.nextInt();
        User user = null;
        if (menuchoice == 0){
        //calling in the 'signup' method    
            user = signup(); // will return user object
        }else if (menuchoice == 1){
        //calling in the 'login' method
            user = login(); // will return user object
        }else {
            System.out.println("Invalid input");
        }
        return user;

    }
    //method 'signup' created for first time users
    public static User signup(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a username: ");
        String newUserName = scanner.nextLine();

        System.out.println("Choose a password: ");
        String newPassword = scanner.nextLine();
        //printing an error if the user does not input anything for username or password
        if (newUserName != "" && newPassword != ""){
            System.out.print("Logging in...");
            return database.createUser(newUserName, newPassword); // logs into existing user
        }else { 
            System.out.println("Error. Please enter your username and password");
        }

        return null;

    }
    //method 'login' created for users that have already signed up 
    public static User login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username");
        String username = scanner.nextLine();

        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        if (username != "" && password != ""){
            System.out.print("Logging in...");
            return database.logIn(username, password); // logs into existing user
        }else { 
            System.out.println("Error. Please enter your username and password");
        }
        return null;
    }
}
