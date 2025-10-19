package battaglia_navale;
import java.io.*;
import java.util.Random;

/**
 * La classe Game gestisce l’inizializzazione della griglia,
 * il posizionamento delle navi (giocatore e CPU)
 * e il ciclo di gioco che alterna gli attacchi finché una delle due navi non viene affondata.
 */
public class Game implements Serializable {

    /** Percorso del file dei giocatori */
    private static final String nameFile = System.getProperty("user.dir") + File.separator + "data" + File.separator + "users.dat";
    /** Dimensione della griglia di gioco */
    private static final byte grid_large = 5;
    /**
     * Costruttore vuoto della classe Game.
     */
    public Game() {
        // Costruttore vuoto
    }

    /**
     * Avvia il gioco della battaglia navale.
     * Gestisce la creazione delle navi, i turni del giocatore e della CPU,
     * e aggiorna il punteggio dei giocatori.
     */
    public static void play() {
        Players users = new Players();
        System.out.println("Benvenuto nella Battaglia Navale!");
        System.out.println("Inizia la partita...");
        users = downloadFile(nameFile);

        int affondate = 0;
        int affondate_cpu = 0;

        String grid[][] = new String[grid_large][grid_large];
        String grid_cpu[][] = new String[grid_large][grid_large];
        grid = init_grid(grid);
        print_grid(grid);
        grid_cpu = init_grid(grid_cpu);

        String username = MieFunzioni.leggiString("Inserire il tuo username");
        Player player = users.foundPlayer(username);
        Player cpu = users.foundPlayer("cpu_" + username);
        player.setMatchPlayed(player.getScore()+cpu.getScore());
        System.out.println(player);
        Ship ship = new Ship(grid_large);
        ship.setPlayer(player);
        print_grid(ship.getCoordinate());

        Ship ship_cpu = create_ship_cpu(grid_large);
        ship_cpu.setPlayer(cpu);

        while (true) {
            affondate += ship.attack(ship_cpu, grid);
            if (affondate == 3) {
                MieFunzioni.stampa("Hai affondato la nave nemica!");
                player.updateScore(1);
                break;
            }
            affondate_cpu += ship.attack(ship, grid_cpu,grid_large);
            if (affondate_cpu == 3) {
                MieFunzioni.stampa("La tua nave è stata affondata!");
                cpu.updateScore(1);
                break;
            }
            MieFunzioni.stampa("Griglia:");
            print_grid(grid);
            MieFunzioni.stampa("Griglia dell'avversario");
            print_grid(grid_cpu);
        }
        saveFile(nameFile, users);
    }

    /**
     * Inizializza una griglia 5x5 riempiendola con spazi vuoti (" ").
     *
     * @param grid matrice da inizializzare
     * @return griglia inizializzata
     */
    public static String[][] init_grid(String[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = " ";
            }
        }
        return grid;
    }

    /**
     * Stampa la griglia in formato leggibile con coordinate A–E e 1–5.
     *
     * @param grid matrice da stampare
     */
    public static void print_grid(String[][] grid) {
        System.out.println("  A  B  C  D  E ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print((i + 1));
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(" " + grid[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    /**
     * Crea una nave della CPU posizionata casualmente.
     *
     * @param grid dimensione della griglia
     * @return oggetto Ship della CPU
     */
    public static Ship create_ship_cpu(int grid) {
        return new Ship(grid, true);
    }

    /**
     * Crea il file dei giocatori se non esiste e crea eventuali cartelle mancanti.
     *
     * @param nameFile percorso completo del file
     */
    public static void createFile(String nameFile) {
        try {
            File file = new File(nameFile);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            if (file.createNewFile()) {
                saveFile(nameFile, new Players());
            }
        } catch (IOException e) {
            System.out.println("Errore nella creazione del file: " + e.getMessage());
        }
    }

    /**
     * Carica i dati dei giocatori da file.
     * Se il file non esiste, lo crea.
     *
     * @param nameFile percorso del file
     * @return oggetto Players contenente i giocatori
     */
    public static Players downloadFile(String nameFile) {
        Players users = null;
        try {
            FileInputStream f = new FileInputStream(nameFile);
            ObjectInputStream fIN = new ObjectInputStream(f);
            users = (Players) fIN.readObject();
            fIN.close();
            f.close();
        } catch (FileNotFoundException e) {
            createFile(nameFile);
            return downloadFile(nameFile);
        } catch (Exception e) {
            System.out.println("Errore nel caricamento: " + e.getMessage());
        }
        return users;
    }

    /**
     * Salva i dati dei giocatori nel file.
     *
     * @param nameFile percorso del file
     * @param users oggetto Players da salvare
     */
    public static void saveFile(String nameFile, Players users) {
        try {
            FileOutputStream f = new FileOutputStream(nameFile);
            ObjectOutputStream fOUT = new ObjectOutputStream(f);
            fOUT.writeObject(users);
            fOUT.flush();
            fOUT.close();
            f.close();
        } catch (Exception e) {
            System.out.println("Errore nel salvataggio: " + e.getMessage());
        }
    }
}
    