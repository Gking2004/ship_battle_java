import java.io.*;
import java.math.*;
import java.util.Random;

/**
 * Classe principale del gioco "Battaglia Navale".
 * Gestisce l’inizializzazione della griglia, il posizionamento delle navi (giocatore e CPU)
 * e il ciclo di gioco che alterna gli attacchi finché una delle due navi non viene affondata.
 */
public class App {
    /**
     * Metodo principale.
     * Crea la griglia di gioco, posiziona le navi del giocatore e della CPU,
     * e gestisce il loop degli attacchi finché una nave non è completamente affondata.
     */
    public static void main(String[] args) throws Exception {
        Players users=new Players();
        users=downloadFile("users.dat");
         
        // Numero di celle colpite dal giocatore e dalla CPU
        int affondate = 0;
        int affondate_cpu = 0;

        // Dimensione della griglia (5x5)
        int grid_large = 5;

        // Inizializzazione e stampa della griglia vuota
        String grid[][] = new String[grid_large][grid_large];
        String grid_cpu[][] = new String[grid_large][grid_large];
        grid = init_grid(grid);
        print_grid(grid);
        grid_cpu=init_grid(grid_cpu);

        String username=MieFunzioni.leggiString("Inserire il tuo username");
        Player player=users.foundPlayer(username);
        System.out.println(player);
        Player cpu=users.foundPlayer("cpu_"+username);
        // Creazione della nave del giocatore (input manuale)
        Ship ship = new Ship(grid_large);
        ship.setPlayer(player);
        print_grid(ship.coordinate);
        // Creazione della nave della CPU (posizionamento casuale)
        Ship ship_cpu = create_ship_cpu(grid_large);
        ship_cpu.setPlayer(cpu);
        // print_grid(ship_cpu.coordinate); // Debug: mostra posizione CPU

        // Ciclo principale del gioco
        while (true) {
            // Turno del giocatore: attacco alla nave della CPU
            affondate += ship.attack(ship_cpu,grid);
            if (affondate == 3) {
                MieFunzioni.stampa("Hai affondato la nave nemica!");
                player.updateScore(1);
                break;
            }

            // Turno della CPU: genera coordinate casuali per l’attacco
            int position_o = (int)(Math.random() * grid_large);
            int position_v = (int)(Math.random() * grid_large);

            // CPU attacca la nave del giocatore
            affondate_cpu += ship.attack(ship, position_o, position_v,grid_cpu);
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
        saveFile("users.dat", users);

    }

    /**
     * Inizializza una griglia 5x5 riempiendola con spazi vuoti (" ").
     *
     * @param grid matrice da inizializzare
     * @return la griglia inizializzata
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
     * Stampa la griglia di gioco in formato leggibile con coordinate A–E e 1–5.
     * 
     * Esempio:
     *   A  B  C  D  E
     * 1  O  O  O  O  O
     * 2  O  X  O  O  O
     *
     * @param grid matrice da stampare
     */
    public static void print_grid(String[][] grid) {
        System.out.println("  A  B  C  D  E ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print((i + 1)); // Stampa numero riga
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
        Ship ship_cpu = new Ship(grid, true);
        return ship_cpu;
    }

    // Crea un file vuoto con un oggetto Players iniziale
    public static void createFile(String nameFile) {
        try {
            File file = new File(nameFile);
            if (file.createNewFile()) {
                System.out.println("File creato: " + nameFile);
                // inizializza il file con un oggetto Players vuoto
                saveFile(nameFile, new Players());
            } else {
                System.out.println("Il file esiste già.");
            }
        } catch (IOException e) {
            System.out.println("Errore nella creazione del file: " + e.getMessage());
        }
    }

    // Carica i dati dal file
    public static Players downloadFile(String nameFile) {
        Players users = null;
        try {
            FileInputStream f = new FileInputStream(nameFile);
            ObjectInputStream fIN = new ObjectInputStream(f);
            users = (Players) fIN.readObject();
            fIN.close();
            f.close();
            System.out.println("Dati caricati!");
        } catch (FileNotFoundException e) {
            System.out.println("File non trovato. Creazione del file...");
            createFile(nameFile);
            return downloadFile(nameFile);
        } catch (Exception e) {
            System.out.println("Errore nel caricamento: " + e.getMessage());
        }
        return users;
    }

    // Salva i dati nel file
    public static void saveFile(String nameFile, Players users) {
        try {
            FileOutputStream f = new FileOutputStream(nameFile);
            ObjectOutputStream fOUT = new ObjectOutputStream(f);
            fOUT.writeObject(users);
            fOUT.flush();
            fOUT.close();
            f.close();
            System.out.println("Dati salvati!");
        } catch (Exception e) {
            System.out.println("Errore nel salvataggio: " + e.getMessage());
        }
    }
}
