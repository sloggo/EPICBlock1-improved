package questiongame;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

//importing mongo extensions
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.model.Sorts;

public class database {
    // token used to access database
    String uri = "mongodb+srv://sloggo:sloggo@questiongame.ivpo6ff.mongodb.net/?retryWrites=true&w=majority";
    MongoClient mongoClient = MongoClients.create(uri);
//creating instance of mongoDB database
//funtion that creates user when user signs up
    public static User createUser(String usernameToCreate, String passwordToCreate){
        database dBController = new database();
        MongoDatabase mongoDB = dBController.mongoClient.getDatabase("questionGame");
        MongoCollection<Document> usersCollection = mongoDB.getCollection("users");

        Bson usernameFilter = Filters.eq("username", usernameToCreate);

        try {
            //checking to see if user already exists
            //if user already exists, prints error
            //if user does not exist,creates new user and pushes to the database
            Document existingUser = usersCollection.find(usernameFilter).first();
            if (existingUser != null) {
                System.out.println("ERROR: User already exists in database! Try a different username.");
                return null;
            } else {
                int score = 0;
                ObjectId newUserId = new ObjectId();
                Document newUser = new Document()
                        .append("userId", newUserId)
                        .append("username", usernameToCreate)
                        .append("password", User.hashPass(passwordToCreate, "securetoken"))
                        .append("score", score);
                usersCollection.insertOne(newUser);
                User newUserObj = new User(newUserId.toString(), usernameToCreate, passwordToCreate, score);
                System.out.println("Signed up!");
                return newUserObj;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
//login function
//searches database for inputted username
//if password correct, then login proceeds 
    public static User logIn(String user, String pass){
        database dBController = new database();
        MongoDatabase mongoDB = dBController.mongoClient.getDatabase("questionGame");
        MongoCollection<Document> usersCollection = mongoDB.getCollection("users");

        // Create separate filters for "username" and "password"
        Bson filter = Filters.eq("username", user);

        String userId = "";
        try {
            Document doc = usersCollection.find(filter).first();
            if (doc != null) {
                if(BCrypt.checkpw(pass, doc.get("password").toString())){
                    System.out.println("Logged in!");
                    User newUserObj = new User(doc);
                    return newUserObj;
                } else{
                    System.out.println("User not found or incorrect credentials.");
                    return null;
                }
            } else {
                System.out.println("User not found or incorrect credentials.");
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        
    }
//method that returns leaderboard once the quiz is over
    public static List<Document> getAllUsers() {
        System.out.println("Getting all users...");
        database dBController = new database();
        MongoDatabase mongoDB = dBController.mongoClient.getDatabase("questionGame");
        MongoCollection<Document> usersCollection = mongoDB.getCollection("users");

        List<Document> results = new ArrayList<>();
        usersCollection.find().sort(Sorts.descending("score")).into(results); // Use Sorts.descending to find highest

        return results;
    }
//method that updates database on users score
    public static void updateUser(User user){
        database dBController = new database();
        MongoDatabase mongoDB = dBController.mongoClient.getDatabase("questionGame");
        MongoCollection<Document> usersCollection = mongoDB.getCollection("users");

        Bson usernameFilter = Filters.eq("username", user.username);

        Document newUser = user.convertToMongo();

        ReplaceOptions opts = new ReplaceOptions().upsert(true);
        usersCollection.replaceOne(usernameFilter, newUser, opts);
    }

    public static Question[] getAllQuestions(){
        database dBController = new database();
        MongoDatabase mongoDB = dBController.mongoClient.getDatabase("questionGame");
        MongoCollection<Document> questionCollection = mongoDB.getCollection("questions");

        FindIterable<Document> documents = questionCollection.find();
        Question[] Questions = {};

        for(Document q : documents){ // loop through all collected documents to convert to questions class
            Question question = new Question(q);
            int length = Questions.length;
            Questions = Arrays.copyOf(Questions, length+1);
            Questions[length] = question;
        }

        return Questions;
    }

    public static void uploadQuestion(Question question){
        database dBController = new database();
        MongoDatabase mongoDB = dBController.mongoClient.getDatabase("questionGame");
        MongoCollection<Document> questionCollection = mongoDB.getCollection("questions");

        Document docQ = question.toMongo(); // returns mongo doc

        questionCollection.insertOne(docQ); // adds to collection
    }
}
