package questiongame;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

public class User {
    String userId;
    String username;
    String password;
    int score;

    public User(String userId, String username, String password, int score){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.score = score;
    }

    public User(Document doc){
        this.userId = doc.get("_id").toString();
        this.username = doc.get("username").toString();
        this.password =  doc.get("password").toString();
        this.score = Integer.parseInt(doc.get("score").toString());
    }

    public static String hashPass(String passwordInput, String pepper){
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
}
