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

}

