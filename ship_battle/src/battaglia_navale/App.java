package battaglia_navale;


import java.io.Serializable;

/**
 * Classe principale del gioco "Battaglia Navale".
 * Richiama la classe Game per avviare il gioco.
 */
public class App implements Serializable {

    /**
     * Metodo principale.
     * Avvia il gioco creando un oggetto Game e chiamando play().
     *
     * @param args parametri della riga di comando (non utilizzati)
     * @throws Exception eventuali eccezioni di I/O o serializzazione
     */
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.play();
    }

    /**
     * Costruttore di default della classe App.
     */
    public App() {
        // Costruttore vuoto
    }
}
