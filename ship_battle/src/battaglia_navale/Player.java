package battaglia_navale;
import java.io.Serializable;

/**
 * La classe Player rappresenta un giocatore con username, punteggio e ID.
 * Implementa Serializable per consentire la serializzazione dell'oggetto.
 */
public class Player implements Serializable {

    /** Username del giocatore */
    private String username;

    /** Punteggio del giocatore */
    private int score;

    /** ID del giocatore */
    private int id;

    /** Numero di partite giocate dal giocatore */
    private int matchPlayed;

    /**
     * Costruttore per creare un nuovo giocatore con username e punteggio iniziale.
     *
     * @param username nome del giocatore
     * @param score punteggio iniziale del giocatore
     */
    public Player(String username, int score) {
        this.username = username;
        this.score = score;
        this.matchPlayed = 0;
    }

    /**
     * Aggiorna il punteggio del giocatore aggiungendo il valore specificato.
     *
     * @param score punteggio da aggiungere
     */
    public void updateScore(int score) {
        this.score += score;
    }

    /**
     * Aggiorna l'username del giocatore.
     *
     * @param newUser nuovo username
     */
    public void updateUsername(String newUser) {
        this.username = newUser;
    }

    /**
     * Restituisce l'username del giocatore.
     *
     * @return username del giocatore
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Restituisce il punteggio del giocatore.
     *
     * @return punteggio del giocatore
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Imposta l'ID del giocatore.
     *
     * @param id identificativo univoco del giocatore
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce l'ID del giocatore.
     *
     * @return ID del giocatore
     */
    public int getId() {
        return this.id;
    }

    /**
     * Restituisce il numero di partite giocate dal giocatore.
     *
     * @return numero di partite giocate
     */

    public int getMatchPlayed() {
        return matchPlayed;
    }

    /**
     * Imposta il numero di partite giocate dal giocatore.
     *
     * @param matchPlayed numero di partite giocate
     */

    public void setMatchPlayed(int matchPlayed) {
        this.matchPlayed = matchPlayed;
    }

    /**
     * Restituisce una rappresentazione testuale del giocatore,
     * includendo username e punteggio.
     *
     * @return stringa descrittiva del giocatore
     */
    @Override
    public String toString() {
        return "Username: " + this.username + " Score: " + this.score + " Partite giocate: " + this.matchPlayed;
    }
}
