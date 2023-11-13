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
            //Discrete Maths questions 74
            //Novice
            new Question(questiongame.topic.DISCMATHS, questiongame.difficulty.NOVICE, "Let A = {1,2,3} and B = {3,4,5}. Find A u B", "1", new String[]{"{1,2,3,4,5}", "{1,2}", "{4,5}", "{3}"}),
            new Question(questiongame.topic.DISCMATHS, questiongame.difficulty.NOVICE, "Determine the truth value of the statement: (P ^ Q) v (¬P ^ Q) when P = True and Q =False", "2", new String[]{"True", "False",}),
            //Intermediate
            new Question(questiongame.topic.DISCMATHS, questiongame.difficulty.INTERMEDIATE, "- Find the truth value of the statement: (P ^ Q) v (¬P ^ R) when P = True, Q = False, and R = True", "2", new String[]{"True", "False"}),
            new Question(questiongame.topic.DISCMATHS, questiongame.difficulty.INTERMEDIATE, "Find the walks of length two from a to a in the following matrix :\n a b c \n ---\n a | 1 0 1 \n b | 0 1 0 \n c | 1 0 1", "1", new String[]{"2", "0", "1", "4"}),
            //Expert
            new Question(questiongame.topic.DISCMATHS, questiongame.difficulty.EXPERT, "-\tOn the fabled Island of Knights and Knaves, we meet three people, A, B, and C, one of whom is a knight, one a knave, and one a spy. The knight always tells the truth, the knave always lies, and the spy can either lie or tell the truth.\r\n" + //
                    "A says: \"C is a knave.\"\r\n" + //
                    "B says: \"A is a knight.\"\r\n" + //
                    "C says: \"I am the spy.\"\r\n" + //
                    "Who is the knight, who is the knave, and who is the spy?\r\n" + //
                    "", "4", new String[]{"A: Knight, B:Knave, C:Spy", "A:Spy, B:Knight, C:Knave", "A:Knave, B:Spy, C:Knight", "A:Knight, B:Spy, C:Knave"}),
            //need to insert matrix image in question on next line
            new Question(questiongame.topic.DISCMATHS, questiongame.difficulty.EXPERT, "Find B when [ 3, 7 * B = [ 2, 8\n \t9, 3 ] \t\t 7, 6 ]", "2", new String[]{"[5/3, -5/27 \n 11/6, -1]", "[35/27, 14/27\n -1/9, 3/2]", "[-3/5, -27/5 \n 6/11 1]", "[-5/3, 5/27 \n -11/6, -1]"}),
            //Computer Science
            //Novice
            new Question(questiongame.topic.COMPSCIFOUND, questiongame.difficulty.NOVICE, "-\tWhich option best desribes Backus Naur Form", "2", new String[]{"Binary code written in a human readable form", "Defines the grammar for a programming language", "Defines the semantics of a program", "The infima of a lattice"}),
            new Question(questiongame.topic.COMPSCIFOUND, questiongame.difficulty.NOVICE, "-\tWhat does the term BDD stand for?", "4", new String[]{"Bernhard Description Diagram", "Braunt Design Diagram", "Backus Definition Diagram", "Boolean Decision Diagram"}),
            //Intermediate
            new Question(questiongame.topic.COMPSCIFOUND, questiongame.difficulty.INTERMEDIATE, "-\tWhat is the supremum of this set? A = {1,2,3,4}", "1", new String[]{"4", "2", "3", "1"}),
            new Question(questiongame.topic.COMPSCIFOUND, questiongame.difficulty.INTERMEDIATE, "-\t Which of the following is not a predicate", "1", new String[]{"Paris is the capital of France.", "P(x) = {x is a positive integer.}", "S(x, y) = {x is greater than the square of }", "R(x) = {x is a square number}"}),
            //Expert
            new Question(questiongame.topic.COMPSCIFOUND, questiongame.difficulty.EXPERT, "-\tWhat does \'reductio ad absurdum\'' mean", "2", new String[]{"Proof by induction", "Proof by contradiction", "Direct Proof", "Proof by cases"}),
            new Question(questiongame.topic.COMPSCIFOUND, questiongame.difficulty.EXPERT, "-\tWhich of the following is an example of commutativity ", "3", new String[]{"A u (A n B) = A n B", "A u B = A'(A n B)", "A n B = B n A", "B u B = A n B"}),
            //Computer Organisation
            //Novice
            new Question(questiongame.topic.COMPORG, questiongame.difficulty.NOVICE, "-\tOut of registers and the cache in a computer, which of the two is located inside the CPU.", "2", new String[]{"Cache", "Registers", "Both", "Neither"}),
            new Question(questiongame.topic.COMPORG, questiongame.difficulty.NOVICE, "-\tWhich of the below best describes the Von Neumann bottleneck", "2", new String[]{"when the CPU can't handle large quantities of data", "limited bandwidth and simultaneous data transfer capabilities between a computer's central processing unit (CPU) and memory", "Having too much RAM on your computer", "Too much requests of the CPU, causing it to overload"}),
            //Intermediate
            new Question(questiongame.topic.COMPORG, questiongame.difficulty.INTERMEDIATE, "-\tConvert the binary number 1101 to decimal", "3", new String[]{"33", "3", "13", "53"}),
            new Question(questiongame.topic.COMPORG, questiongame.difficulty.INTERMEDIATE, "-\tWhich of the below are NOT found in the original Von Neumann machine ", "1", new String[]{"Audio Processor", "Memory", "Control Unit", "Arithmetic logic unit"}),
            //Expert
            new Question(questiongame.topic.COMPORG, questiongame.difficulty.EXPERT, "-\tWhat is Moores law for CPU chips", "2", new String[]{"the number of transistors on a microchip doubles approximately every year", 
            "The number of transistors on a microchip doubles approximately every two years", "The number of registers in a CPU doubles approximately every two years", "The RAM doubles in a computer approximately every two years"}),
            new Question(questiongame.topic.COMPORG, questiongame.difficulty.EXPERT, "Convert the hexadecimal number 2A3F to binary", "4", new String[]{"1010101010101010", "0001011101000110", "1000111011101101", "1010001001111111"}),
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