package questiongame;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import org.bson.types.ObjectId;

import java.util.Random;

public class modeController {
//method for sorting through questions
    public static Question[] fetchSpecificQuestions(topic topic, difficulty difficulty, Question[] questions){
        //create questions array
            Question[] sortedQuestions = {};
            for(int i=0; i<questions.length; i++){
                if(topic != null && difficulty != null){ // if searching by both
                    if(questions[i].topic == topic && questions[i].difficulty == difficulty){
                        sortedQuestions = Arrays.copyOf(sortedQuestions, sortedQuestions.length + 1);
                        sortedQuestions[sortedQuestions.length - 1] = questions[i];
                    } 
                } else if(topic == null){ // if searching by difficulty only
                    if(questions[i].difficulty == difficulty){
                        sortedQuestions = Arrays.copyOf(sortedQuestions, sortedQuestions.length + 1);
                        sortedQuestions[sortedQuestions.length - 1] = questions[i];
                    } 
                } else if(difficulty == null){ // if searching by topic only
                    if(questions[i].topic == topic){
                        sortedQuestions = Arrays.copyOf(sortedQuestions, sortedQuestions.length + 1);
                        sortedQuestions[sortedQuestions.length - 1] = questions[i];
                    } 
                }
                
            }
            System.out.println(sortedQuestions.toString());
            return sortedQuestions;
    }


    public static void difficultyMode(Question[] questions, User user){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        int correct = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to difficulty mode!");

        System.out.println("1 - Novice | 2 - Intermediate | 3 - Expert");

        int input = scanner.nextInt();
        scanner.nextLine();
        questiongame.difficulty difficultySelection = null;
        switch(input){ // translate input to a difficulty selection
            case(1):
                difficultySelection = questiongame.difficulty.NOVICE; 
                break;
            case(2):
                difficultySelection = questiongame.difficulty.INTERMEDIATE;
                break;
            case(3):
                difficultySelection = questiongame.difficulty.EXPERT;
        }

        Question[] sortedQ = fetchSpecificQuestions(null, difficultySelection, questions);
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println(sortedQ.toString());

        for(Question q : sortedQ){ // loop through selected questions
            q.printQuestion();
            String ansString = scanner.nextLine();

            if(q.isCorrect(ansString)){
                System.out.println("Correct!\n");
                correct++;
            } else{
                System.out.println("Incorrect!\n");
            }
        }

        finishedQuiz(user, correct);
    }

    public static void randomMode(Question[] questions, User user){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Random Mode!");
        int correct = 0;
        Collection<ObjectId> selectedQ = new ArrayList<>(); // list of ids to keep track

        for(int i = 0; i<6; i++){
            if(selectedQ.size() == questions.length){
                break;
            }
            Question randomQ = null;

            while(randomQ == null || selectedQ.contains(randomQ.objectId)){
                randomQ = fetchRandomQuestion(questions);
            }

            randomQ.printQuestion();
            String ansString = scanner.nextLine();

            if(randomQ.isCorrect(ansString)){
                System.out.println("Correct!\n");
                correct++;
            } else{
                System.out.println("Incorrect!\n");
            }
        }

            System.out.print("\033[H\033[2J");
            System.out.flush();
            finishedQuiz(user, correct);
    }
//sudden death method
    public static void suddenDeath(Question[] questions, User user){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Sudden Death Mode!");

        boolean alive = true;
        int round = 1;

        Collection<ObjectId> selectedQ = new ArrayList<>(); // list of ids to keep track


        while(alive == true){

            if(selectedQ.size() == questions.length){
                System.out.println("You answered all the questions correctly!");
                break;
            }
            Question randomQ = null;

            while(randomQ != null && selectedQ.contains(randomQ.objectId)){
                randomQ = fetchRandomQuestion(questions);
            }

            randomQ.printQuestion();
            String ansString = scanner.nextLine();

            if(randomQ.isCorrect(ansString)){
                System.out.println("Correct!\n");
                round++;
            } else{
                System.out.println("Incorrect! You lasted "+round+" rounds.\n");
                alive = false;
            }
        }
        finishedQuiz(user, round);
    }
//when quiz finished, leaderboard is generated
    public static void finishedQuiz(User user, int increaseScore){
        score.updateScore(user, increaseScore);
        leaderboard.generateLeaderboard(increaseScore);
    }

    public static Question fetchRandomQuestion(Question[] questions){
        int questionLength = questions.length;
        Random random = new Random();
        int randomIndex = random.nextInt(questionLength);
        Question randomQ = questions[randomIndex];

        return randomQ;
    }

}
