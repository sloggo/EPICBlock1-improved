package questiongame;

import java.util.Arrays;

public class Question {
    topic topic;
    difficulty difficulty;
    String question;
    String answer;
    String[] options;

    public Question(topic topic, difficulty difficulty, String question, String answer, String[] options){
        this.topic = topic;
        this.difficulty = difficulty;
        this.question = question;
        this.answer = answer;
        this.options = options;
    }

    public void printQuestion(){
        System.out.println("------------------------------------------------------");
        System.out.println(question);
        System.out.println("");

            for(String ans: options){
                int index = Arrays.asList(options).indexOf(ans);
                System.out.println((index+1)+ ": "+ans+"\t");
            }
            System.out.println("");
    }

    public Boolean isCorrect(String ans){
        if(ans.equals(answer)){
            return true;
        } else{
            return false;
        }
    }

}

