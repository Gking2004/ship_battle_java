import java.math.*;

/**
 * La classe Ship rappresenta una nave all'interno di una griglia (campo di battaglia),
 * come nel classico gioco "Battaglia Navale".
 * Ogni nave ha una posizione iniziale (riga e colonna), un orientamento (verticale/orizzontale),
 * una lunghezza (large) e una griglia locale (coordinate) che memorizza le celle occupate.
 *
 * Supporta sia la modalità giocatore (con input) che CPU (con posizionamento casuale).
 */
public class Ship {

    /** Riga di partenza della nave (0-based, verticale) */
    public int position_o;

    /** Colonna di partenza della nave (0-based, orizzontale) */
    public int position_v;

    /** Lunghezza della nave (numero di celle occupate) */
    public int large;

    /** Matrice che rappresenta la griglia, con "O" = vuoto e "X" = parte di nave */
    public String[][] coordinate;

    /** True se la nave è disposta verticalmente, false se orizzontalmente */
    public boolean vertical;

    public Player player;


    /**
     * Costruttore per il giocatore umano.
     * Chiede in input la posizione iniziale, l'orientamento e la lunghezza della nave.
     * Si assicura che la nave possa essere posizionata interamente nella griglia.
     *
     * @param grid dimensione della griglia (es. 5x5)
     */
    public Ship(int grid) {
        boolean is_true = false;

        // Cicla finché la posizione della nave non è valida
        while (!is_true) { 
            this.position_v = controll_v(); // input colonna (A-E)
            this.position_o = controll_o(); // input riga (1-5)
            this.coordinate = init_coordinate(new String[grid][grid]);

            // Chiede la lunghezza (da 1 a 5)
            do { 
                this.large = MieFunzioni.leggiIntero("Inserire la larghezza della nave (max 5)");
            } while (large > 5 || large < 1);

            // Chiede orientamento verticale/orizzontale
            this.vertical = MieFunzioni.leggiBool("Inserire la barca verticalmente (Y) oppure orizzontalmente (N)");

            // Tenta di posizionare la nave, ripete se non valida
            is_true = setCoordinate(position_o, position_v, coordinate, vertical, large, grid, false);
        }
    }


    /**
     * Costruttore per la CPU (posizionamento casuale).
     * Genera coordinate, lunghezza e orientamento randomici.
     *
     * @param grid dimensione della griglia
     * @param cpu  flag per distinguere la logica CPU (true)
     */
    public Ship(int grid, boolean cpu) {
        boolean is_true = false;

        while (!is_true) { 
            this.position_o = (int)(Math.random() * grid); // riga random
            this.position_v = (int)(Math.random() * grid); // colonna random
            this.large = 3; // dimensione fissa per CPU
            this.vertical = (Math.random() < 0.5); // 50% verticale/orizzontale

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
     * Chiede all'utente la posizione verticale (riga, 1–5) e la converte in indice 0-based.
     *
     * @return indice della riga (0-based)
     */
    public int controll_o() {
        int position_o;
        do { 
            position_o = MieFunzioni.leggiIntero("Inserire la posizione verticale (1-5)*");
        } while (position_o < 1 || position_o > 5);
        return position_o - 1; // converte da 1-based a 0-based
    }


    /**
     * Chiede all'utente la posizione orizzontale (colonna, A–E) e la converte in indice numerico.
     *
     * @return indice della colonna (0-based)
     */
    public int controll_v() {
        String position_v;
        int position_v_convert;
        do { 
            position_v = MieFunzioni.leggiString("Inserire la posizione orizzontale (A-E)*");
            position_v_convert = convert_position(position_v);
        } while (position_v_convert == -1);
        return position_v_convert;
    }


    /**
     * Converte una lettera di colonna (A–E) nel corrispondente indice numerico.
     *
     * @param position lettera inserita dall'utente
     * @return indice numerico (0–4) oppure -1 se non valido
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
     * @param vertical   true se la nave è verticale
     * @param large      lunghezza della nave
     * @param grid       dimensione della griglia
     * @param cpu        flag CPU (per eventuali messaggi)
     * @return true se la nave è stata posizionata correttamente, false se fuori dai limiti
     */
    public static boolean setCoordinate(int position_o, int position_v, String[][] coordinate, boolean vertical, int large, int grid, boolean cpu) {
        if (vertical) {
            // Controllo: non deve uscire dalla griglia in verticale
            if (position_o + large > grid) return false;
            for (int i = 0; i < large; i++) {
                coordinate[position_o + i][position_v] = "X";
            }
        } else {
            // Controllo: non deve uscire dalla griglia in orizzontale
            if (position_v + large > grid) return false;
            for (int i = 0; i < large; i++) {
                coordinate[position_o][position_v + i] = "X";
            }
        }
        return true;
    }


    /**
     * Controlla se una cella colpita corrisponde a una parte di nave ("X").
     *
     * @param ship nave da colpire
     * @param position_v colonna dell’attacco
     * @param position_o riga dell’attacco
     * @return true se la cella contiene una nave, false se vuota
     */
    public boolean controll_attack(Ship ship, int position_v, int position_o,String[][] grid) {
        MieFunzioni.stampa("Attacco su riga " + (position_o + 1) + ", colonna " + (position_v + 1));
        boolean is_in=false;
        if(ship.coordinate[position_o][position_v].equals("X")){
            is_in=true;
             grid[position_o][position_v]="X";
        }else{
            grid[position_o][position_v]="~";
        }



        return is_in;
    }


    /**
     * Esegue un attacco leggendo le coordinate dall’utente.
     * Mostra “Colpito!” o “Vuoto.” a seconda del risultato.
     *
     * @param ship nave avversaria da colpire
     * @return 1 se colpito, 0 se mancato
     */
    public int attack(Ship ship,String[][] grid) {
        int position_v = controll_v(); // colonna
        int position_o = controll_o(); // riga
        boolean is_found = controll_attack(ship, position_v, position_o,grid);
        if (is_found) {
            MieFunzioni.stampa("Colpito!");
            return 1;
        } else {
            MieFunzioni.stampa("Vuoto.");
            return 0;
        }
    }


    /**
     * Esegue un attacco con coordinate già fornite (usato per la CPU).
     *
     * @param ship nave avversaria da colpire
     * @param position_o riga dell’attacco
     * @param position_v colonna dell’attacco
     * @return 1 se colpito, 0 se mancato
     */
    public int attack(Ship ship, int position_o, int position_v,String[][] grid) {
        boolean is_found = controll_attack(ship, position_v, position_o,grid);
        if (is_found) {
            MieFunzioni.stampa("Colpito!");
            return 1;
        } else {
            MieFunzioni.stampa("Vuoto.");
            return 0;
        }
    }


    public void setPlayer(Player player){
        this.player=new Player(player.getUsername(),player.getScore());
    }
}
