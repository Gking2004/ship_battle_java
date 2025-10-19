package battaglia_navale;
import java.io.Serializable;
import java.util.Vector;

/**
 * Classe che gestisce una lista di giocatori.
 * Permette di visualizzare tutti i giocatori e di cercarne uno per username,
 * creando un nuovo giocatore se non esiste.
 */
public class Players implements Serializable{

    /** Lista dei giocatori presenti */
    private Vector<Player> players;

    /** Contatore ID per tracciare il numero totale di giocatori creati */
    private int contatoreID;
    /**
     * Costruttore che inizializza la lista dei giocatori e il contatore.
     */
    public Players() {
        this.players = new Vector<>();
        this.contatoreID = 0;
    }

    /**
     * Restituisce una rappresentazione testuale di tutti i giocatori presenti,
     * concatenando le informazioni di ogni giocatore e mostrando il totale.
     *
     * @return stringa contenente tutti i giocatori e il numero totale
     */
    @Override
    public String toString() {
        String s = "";
        for (Player player : players) {
            s = s + player;  // aggiunge la rappresentazione di ogni player
        }
        s = s + "Total players: " + contatoreID;
        return s;
    }

    /**
     * Cerca un giocatore tramite username nella lista dei giocatori.
     * Se il giocatore esiste, lo restituisce.
     * Se non esiste, crea un nuovo Player con punteggio 0, lo aggiunge alla lista,
     * incrementa il contatore e lo restituisce.
     *
     * @param username username del giocatore da cercare
     * @return oggetto Player trovato o appena creato
     */
    public Player foundPlayer(String username) {
        for (Player player : players) {
            if (player.getUsername().equals(username)) {
                return player;
            }
        }
        // Se non trovato, crea un nuovo player
        Player player = new Player(username, 0);
        players.add(player);
        contatoreID++;
        return player;
    }
}
