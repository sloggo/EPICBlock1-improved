package questiongame;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

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

    public Question(Document mongoDocument){
        this.topic = topic.valueOf(mongoDocument.getString("topic"));
        this.difficulty = difficulty.valueOf(mongoDocument.getString("difficulty"));
        this.question = mongoDocument.getString("question");
        this.answer = mongoDocument.getString("answer");
        List<String> optionsList = mongoDocument.getList("options", String.class);
        this.options = optionsList.toArray(new String[0]);
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

    public Document toMongo(){
        Document doc = new Document() // convert updated user back into a document to insert into mongodb again
                        .append("topic", topic)
                        .append("difficulty", difficulty)
                        .append("question", question)
                        .append("answer", answer)
                        .append("options", options);

        return doc;
    }
}

