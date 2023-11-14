package questiongame;
import org.bson.Document;

public class Admin extends User {
    public Admin(String userId, String username, String password, int score){
        super(userId, username, password, score);
    }

    public Admin(Document doc){
        super(doc);
    }
}
