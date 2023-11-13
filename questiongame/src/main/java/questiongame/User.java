package questiongame;

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
        this.password = hashPass(password, "securetoken");
        this.score = score;
    }

    public String hashPass(String passwordInput, String pepper){
        return BCrypt.hashpw(passwordInput, BCrypt.gensalt()+pepper);
    }
}
