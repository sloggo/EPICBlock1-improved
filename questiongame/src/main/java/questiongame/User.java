package questiongame;

import java.util.Arrays;
import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

public class User {
    String userId;
    String username;
    String password;
    int score;
    private boolean isAdmin;

    public User(String userId, String username, String password, int score){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.score = score;


        if(userId == "65525e039384ca1a0ddfd54a"){ // if the user is an admin
            this.isAdmin = true;
        } else{
            this.isAdmin = false;
        }
        
    }

    public User(Document doc){ // convert mongoDB document to user
        this.userId = doc.get("_id").toString();
        this.username = doc.get("username").toString();
        this.password =  doc.get("password").toString();
        this.score = Integer.parseInt(doc.get("score").toString());

        if(this.userId == "65525e039384ca1a0ddfd54a"){
            this.isAdmin = true;
        } else{
            this.isAdmin = false;
        }
    }

    public static String hashPass(String passwordInput, String pepper){ // returns hashed password
        return BCrypt.hashpw(passwordInput, BCrypt.gensalt()+pepper);
    }

    public Document convertToMongo(){
        Document newUser = new Document() // convert updated user back into a document to insert into mongodb again
                        .append("userId", new ObjectId(userId))
                        .append("username", username)
                        .append("password", password)
                        .append("score", score);

        return newUser;
    }

    public void settings(){
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Admin Settings -------------------\n");
        
        System.out.println("1 - Add Question, 2 - Back to Menu");

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        switch(input){
            case(1): // option 1 - add question
                addQuestion();
                break;
            case(2): // option 2 - back to menu
                commandLineMenu.gameModeMenu(database.getAllQuestions(), this);
        }
    }

    public void addQuestion(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the question:");
        String question = scanner.nextLine();

        System.out.println();

        System.out.println("Enter the amount of answers:");
        int amountAns = scanner.nextInt();

        System.out.println();

        String[] answers = {};

        for(int i = 0; i<amountAns; i++){ // take in x amount of possible answers for multiple choice
            System.out.println("Enter answer "+(i+1)+":");
            String ans = scanner.nextLine();

            System.out.println();

            int length = answers.length;
            answers = Arrays.copyOf(answers, length+1);

            answers[length] = ans;
        }

        System.out.println("Which answer is the correct one? (enter the number of the question, ex. 2):");
        String ans = scanner.nextLine();

        System.out.println();

        System.out.println("What topic? 1- Maths, 2- Comp Sci, 3- Comp Org");
        int opt = scanner.nextInt();
        topic topic = null;
        switch(opt){
            case(1):
                topic  = topic.DISCMATHS;
            case(2):
                topic = topic.COMPSCIFOUND;
            case(3):
                topic = topic.COMPORG;
        }
        System.out.println();

        System.out.println("What difficulty? 1- Novice, 2- Intermediate, 3- Expert");
        int opt2 = scanner.nextInt();
        difficulty difficulty = null;
        switch(opt2){
            case(1):
                difficulty  = difficulty.NOVICE;
            case(2):
                difficulty = difficulty.INTERMEDIATE;
            case(3):
                difficulty = difficulty.EXPERT;
        }
        System.out.println();


        Question newQ = new Question(topic, difficulty, question, ans, answers); // make new question class
        database.uploadQuestion(newQ); // upload to database

        this.settings();
    }

    public boolean isAdmin(){
        return isAdmin;
    }
}
