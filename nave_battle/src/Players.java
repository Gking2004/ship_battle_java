import java.io.Serializable;
import java.util.Vector;

public class Players implements Serializable{
    private Vector<Player> players;
    public int contatoreID;

    public Players(){
        players= new Vector<Player>();
        this.contatoreID=0;
    }
    public void add(Player player){
        this.players.add(player);
        this.contatoreID++;
    }
    @Override
    public String toString() {
        String s = "";
        s=s+"Players: \n";
        for (Player player : players) {
            s=s+player;
        }
        s=s+"Total players: "+contatoreID;
        return s;
    }
    public Player foundPlayer(String username){
        for (Player player : players) {
            if (player.getUsername().equals(username)) {
            return player;
            }
        }
        Player player = new Player(username, 0);
        players.add(player);
        contatoreID++;
        return player;
    }
}
