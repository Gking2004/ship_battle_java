import java.io.Serializable;

public class Player implements Serializable {
    private String username;
    private int score;
    private int id;

    public Player(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public void updateScore(int score) {
        this.score += score;
    }

    public void updateUsername(String newUser) {
        this.username = newUser;
    }

    public String getUsername() {
        return this.username;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public String toString() {
        return "Username: " + this.username + " Score: " + this.score;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}
