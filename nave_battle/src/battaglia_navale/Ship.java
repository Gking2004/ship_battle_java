package battaglia_navale;
import java.io.Serializable;
import java.math.*;
import java.util.Vector;

/**
 * La classe Ship rappresenta una nave all'interno di una griglia di gioco simile
 * a "Battaglia Navale". Ogni nave ha una posizione iniziale, un orientamento,
 * una lunghezza e una griglia locale che memorizza le celle occupate.
 * <p>
 * Supporta sia la modalità giocatore (input manuale) che CPU (posizionamento casuale).
 */
public class Ship implements Serializable{

    /** Riga di partenza della nave (0-based, verticale) */
    private int position_o;

    /** Colonna di partenza della nave (0-based, orizzontale) */
    private int position_v;

    /** Lunghezza della nave (numero di celle occupate) */
    private int large;

    /** Matrice che rappresenta la griglia della nave ("O" = vuoto, "X" = parte di nave) */
    private String[][] coordinate;

    /** True se la nave è verticale, false se orizzontale */
    private boolean vertical;

    /** Giocatore proprietario della nave */
    private Player player;

    /** Vettore delle coordinate già usate durante gli attacchi */
    private Vector<String> usedCoordinates;

    /**
     * Costruttore per il giocatore umano.
     * Richiede input per coordinate iniziali, lunghezza e orientamento della nave.
     * Ripete la richiesta finché non viene inserita una posizione valida.
     *
     * @param grid dimensione della griglia (es. 5 per una 5x5)
     */
    public Ship(int grid) {
        this.usedCoordinates = new Vector<>();
        boolean is_true = false;
        while (!is_true) {
            // Chiede le coordinate della nave
            char coordinates[] = new char[2];
            coordinates = setCoordinate(coordinates);

            // Converte le coordinate in indici numerici
            this.position_v = convert_position(String.valueOf(coordinates[0]));
            this.position_o = Character.getNumericValue(coordinates[1]) - 1;

            // Inizializza la griglia della nave
            this.coordinate = init_coordinate(new String[grid][grid]);

            // Richiede lunghezza della nave (1–5)
            do {
                this.large = MieFunzioni.leggiIntero("Inserire la larghezza della nave (max 5)");
            } while (large > 5 || large < 1);

            // Richiede orientamento verticale (Y/N)
            this.vertical = MieFunzioni.leggiBool("Inserire la barca verticalmente (Y) oppure orizzontalmente (N)");

            // Tenta di posizionare la nave nella griglia
            is_true = setCoordinate(position_o, position_v, coordinate, vertical, large, grid, false);
        }
    }


    /**
     * Costruttore per la CPU.
     * Genera coordinate, lunghezza e orientamento casuali finché non trova una posizione valida.
     *
     * @param grid dimensione della griglia
     * @param cpu  flag per distinguere la logica CPU (true)
     */
    public Ship(int grid, boolean cpu) {
        this.usedCoordinates = new Vector<>();
        boolean is_true = false;

        while (!is_true) {
            // Coordinate casuali
            this.position_o = (int)(Math.random() * grid);
            this.position_v = (int)(Math.random() * grid);

            // Lunghezza fissa della nave per CPU
            this.large = 3;

            // Orientamento casuale
            this.vertical = (Math.random() < 0.5);

            // Inizializza griglia e prova a posizionare la nave
            this.coordinate = init_coordinate(new String[grid][grid]);
            is_true = setCoordinate(position_o, position_v, coordinate, vertical, large, grid, cpu);
        }
    }


    /**
     * Inizializza una matrice di coordinate impostando tutte le celle su "O" (vuote).
     *
     * @param grid matrice da inizializzare
     * @return la matrice riempita
     */
    public String[][] init_coordinate(String[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = "O";
            }
        }
        return grid;
    }


    /**
     * Converte una lettera di colonna (A–E) nel corrispondente indice numerico (0–4).
     *
     * @param position lettera della colonna
     * @return indice numerico (0–4), oppure -1 se la lettera non è valida
     */
    public static int convert_position(String position) {
        String[] letters = {"A", "B", "C", "D", "E"};
        position = position.toUpperCase();
        for (int i = 0; i < letters.length; i++) {
            if (position.equals(letters[i])) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Posiziona la nave sulla griglia se le coordinate sono valide.
     *
     * @param position_o riga iniziale
     * @param position_v colonna iniziale
     * @param coordinate matrice di gioco
     * @param vertical   true se verticale
     * @param large      lunghezza della nave
     * @param grid       dimensione della griglia
     * @param cpu        flag CPU (per eventuali messaggi)
     * @return true se la nave è stata posizionata correttamente, false se fuori dai limiti
     */
    public static boolean setCoordinate(int position_o, int position_v, String[][] coordinate, boolean vertical, int large, int grid, boolean cpu) {
        if (vertical) {
            if (position_o + large > grid) return false;
            for (int i = 0; i < large; i++) {
                coordinate[position_o + i][position_v] = "X";
            }
        } else {
            if (position_v + large > grid) return false;
            for (int i = 0; i < large; i++) {
                coordinate[position_o][position_v + i] = "X";
            }
        }
        return true;
    }


    /**
     * Legge le coordinate della nave dall'utente e le valida.
     * Accetta coordinate come lettera+numero (es. A2) o numero+lettera (2A).
     *
     * @param coordinates array di 2 char in cui salvare le coordinate
     * @return array aggiornato con coordinate valide
     */
    public char[] setCoordinate(char[] coordinates) {
        String coordinate;
        char position_o_char = '1';
        char position_v_char = '1';

        do {
            // Richiede input
            coordinate = MieFunzioni.leggiString("Inserire le coordinate della nave ");
            coordinates = coordinate.toCharArray();

            if (coordinates.length != 2) {
                MieFunzioni.stampa("Inserimento non valido. Riprova.");
                position_o_char = '1';
                position_v_char = '1';
            } else if (((coordinates[0] >= 'a' && coordinates[0] <= 'e') || (coordinates[0] >= 'A' && coordinates[0] <= 'E'))
                    && (coordinates[1] >= '1' && coordinates[1] <= '5')) {
                // formato lettera+numero
                position_v_char = coordinates[0];
                position_o_char = coordinates[1];
            } else if (((coordinates[1] >= 'a' && coordinates[1] <= 'e') || (coordinates[1] >= 'A' && coordinates[1] <= 'E'))
                    && (coordinates[0] >= '1' && coordinates[0] <= '5')) {
                // formato numero+lettera
                position_o_char = coordinates[0];
                position_v_char = coordinates[1];
            } else {
                MieFunzioni.stampa("Inserimento non valido. Riprova.");
                position_o_char = '1';
                position_v_char = '1';
            }

        } while (position_o_char == '1' && position_v_char == '1');

        coordinates[0] = position_v_char;
        coordinates[1] = position_o_char;
        return coordinates;
    }


    /**
     * Controlla se un attacco colpisce una nave.
     *
     * @param ship nave avversaria
     * @param position_v colonna dell'attacco
     * @param position_o riga dell'attacco
     * @param grid griglia su cui segnare colpi/mancati
     * @return true se colpito, false se vuoto
     */
    public boolean controll_attack(Ship ship, int position_v, int position_o, String[][] grid) {
        MieFunzioni.stampa("Attacco su riga " + (position_o + 1) + ", colonna " + (position_v + 1));
        boolean is_in = false;
        if (ship.coordinate[position_o][position_v].equals("X")) {
            is_in = true;
            grid[position_o][position_v] = "X";
        } else {
            grid[position_o][position_v] = "~";
        }
        return is_in;
    }


    /**
 * Esegue un attacco della CPU con coordinate generate casualmente.
 * Se la coordinata è già stata usata, ne genera un’altra.
 *
 * @param ship nave avversaria da attaccare
 * @param grid griglia su cui segnare colpi e mancati
 * @param grid_large dimensione della griglia (numero di righe/colonne)
 * @return 1 se il colpo ha centrato la nave, 0 se mancato
 */
public int attack(Ship ship, String[][] grid, int grid_large) {
    int position_o=-1;
    int position_v=-1;
    boolean isIn=true;
    while(isIn==true){
        position_o = (int) (Math.random() * grid_large);
        position_v = (int) (Math.random() * grid_large);
        String elemento = String.valueOf(position_o)+String.valueOf(position_v);
        isIn = MieFunzioni.contieneElemento(elemento, ship.getUsedCoordinates());
        if(isIn==false){
            ship.getUsedCoordinates().add(elemento);
        }
    }
    boolean is_found = controll_attack(ship, position_v, position_o, grid);
    if (is_found) {
        MieFunzioni.stampa("Colpito!");
        return 1;
    } else {
        MieFunzioni.stampa("Vuoto.");
        return 0;
    }
}

/**
 * Esegue un attacco del giocatore leggendo le coordinate dall’utente.
 * Se la coordinata è già stata usata, chiede all’utente di reinserirla.
 *
 * @param ship nave avversaria da attaccare
 * @param grid griglia su cui segnare colpi e mancati
 * @return 1 se il colpo ha centrato la nave, 0 se mancato
 */
public int attack(Ship ship, String[][] grid) {
    char coordinates[] = new char[2];
    boolean isIn = true;
    while (isIn == true) {
        coordinates = ship.setCoordinate(coordinates);
        String elemento = String.valueOf(convert_position(String.valueOf(coordinates[0]))) + String.valueOf(coordinates[1]);
        isIn = MieFunzioni.contieneElemento(elemento, ship.getUsedCoordinates());
        if (isIn == false) {
            ship.getUsedCoordinates().add(elemento);
        } else {
            MieFunzioni.stampa("Coordinata già usata, riprova.");
        }
    }
    boolean is_found = controll_attack(ship, convert_position(String.valueOf(coordinates[0])),
            Character.getNumericValue(coordinates[1]) - 1, grid);
    if (is_found) {
        MieFunzioni.stampa("Colpito!");
        return 1;
    } else {
        MieFunzioni.stampa("Vuoto.");
        return 0;
    }
}

    /**
     * Imposta il giocatore proprietario della nave.
     *
     * @param player oggetto Player
     */
    public void setPlayer(Player player) {
        this.player = new Player(player.getUsername(), player.getScore());
    }


    /**
     * Restituisce il giocatore proprietario della nave.
     *
     * @return oggetto Player
     */
    public Player getPlayer() {
        return this.player;
    }


    /**
     * Restituisce la griglia della nave.
     *
     * @return matrice di coordinate
     */
    public String[][] getCoordinate() {
        return this.coordinate;
    }


    /**
     * Restituisce la lunghezza della nave.
     *
     * @return lunghezza della nave
     */
    public int getLarge() {
        return this.large;
    }

    /**
     * Restituisce le coordinate già usate.
     * @return vettore di stringhe con le coordinate già usate
     */

    public Vector<String> getUsedCoordinates() {
        return usedCoordinates;
    }

    /**
     * Imposta le coordinate già usate.
     * @param usedCoordinates vettore di stringhe con le coordinate già usate
     */

    public void setUsedCoordinates(Vector<String> usedCoordinates) {
        this.usedCoordinates = usedCoordinates;
    }
}
