package battaglia_navale;
import java.io.*;
import java.util.Vector;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Classe contenente varie funzioni di utilità per la gestione di vettori,
 * file CSV, input da tastiera e operazioni tra array (intersezione, EXOR, ecc.).
 * Implementa Serializable per permettere la serializzazione degli oggetti,
 * se necessario.
 */
class MieFunzioni implements Serializable{
/**
 * InputStreamReader collegato all'input standard (tastiera).
 * Utilizzato per leggere dati dall'utente.
 */
public static InputStreamReader in = new InputStreamReader(System.in);

/**
 * BufferedReader che incapsula l'InputStreamReader 'in'.
 * Permette di leggere stringhe intere dall'input dell'utente con readLine().
 */
public static BufferedReader tastiera = new BufferedReader(in);

/**
 * Costruttore vuoto della classe MieFunzioni.
 * Inizializza un'istanza della classe senza eseguire operazioni aggiuntive.
 */
public MieFunzioni() {
    // Costruttore vuoto
}


  /**
     * Chiede una stringa all'utente.
     *
     * @param domanda testo della domanda da visualizzare
     * @return stringa inserita dall'utente
     */
  public static String domanda(String domanda) {
    System.out.println(domanda);
    String risposta = "";
    try {
      risposta = tastiera.readLine();// leggo la risposta
    } catch (Exception e) {
      System.out.println(e);
    }
    return risposta;
  }

  /**
   * Legge un intero dall'input dell'utente.
   *
   * @param domanda testo della domanda da visualizzare
   * @return intero inserito dall'utente
   */

  public static int leggiIntero(String domanda) {
    String risposta;
    int convertitore = 0;
    while (true) {
      risposta = domanda(domanda);
      try {
        convertitore = Integer.valueOf(risposta).intValue();
        return convertitore;
      } catch (NumberFormatException e) {
        System.out.println("il valore non è valido");
      }
    }
  }

  /**
   * Legge una data dall'input dell'utente nel formato "yyyy-MM-dd".
   *
   * @param domanda testo della domanda da visualizzare
   * @return data inserita dall'utente come LocalDate
   */

  public static LocalDate leggiData(String domanda) {
      String risposta;
      LocalDate dataConvertita = null;
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

      while (true) {
          risposta = leggiString(domanda);
          try {
              dataConvertita = LocalDate.parse(risposta, formatter);
              return dataConvertita;
          } catch (Exception e) {
              System.out.println("La data non è valida. Assicurati di utilizzare il formato corretto.");
          }
      }
  }

  /**
   * Legge un vettore di interi dall'input dell'utente.
   *
   * @param domanda testo della domanda da visualizzare
   * @param separatore carattere utilizzato per separare i valori
   * @return vettore di interi inseriti dall'utente
   */
  public static int[] leggiVettoreInt(String domanda, String separatore) {
    String salva = domanda(domanda);
    String[] vettore_s = salva.split(separatore);// Eseguo la separazione con -
    int[] valori = new int[vettore_s.length];// creo vettore
    for (int i = 0; i < vettore_s.length; i++) {
      try {
        valori[i] = Integer.valueOf(vettore_s[i]).intValue();// converto ogni singolo valore
      } catch (NumberFormatException e) {
        valori[i] = leggiIntero("Hai sbagliato, inserire un numero corretto ");
      }
    }
    return valori;
  }

  /**
   * Ordina un vettore di interi utilizzando l'algoritmo di ordinamento a scambio (bubble sort).
   *
   * @param convertitore vettore di interi da ordinare
   */

  public static void vettoreOrdinatoScambio(int[] convertitore) {
    boolean scambio;
    int lunghezza = convertitore.length - 1;
    do {
      scambio = false;
      for (int i = 0; i < lunghezza; ++i) {
        int temp;
        if (convertitore[i] > convertitore[i + 1]) {
          temp = convertitore[i];
          convertitore[i] = convertitore[i + 1];
          convertitore[i + 1] = temp;
          scambio = true;
        }
      }
      --lunghezza;
    } while (scambio);
    System.out.println("Vettore ordinato per scambio: ");
    stampaVettore(convertitore);
  }

  /**
   * Ordina un vettore di double utilizzando l'algoritmo di ordinamento a scambio (bubble sort).
   *
   * @param convertitore vettore di double da ordinare
   */

  // FUNZIONE PER STAMPARE INT
  public static void stampaInt(int convertitore) {
    System.out.print("{");
    System.out.print(convertitore);
    System.out.print("\b}\n");
  }

  /**
   * Funzione per stampare un valore di tipo double.
   *
   * @param convertitore valore di tipo double da stampare
   */
  // FUNZIONE PER STAMPARE DOUBLE
  public static void stampaDouble(double convertitore) {
    System.out.print(convertitore);
  }

  /**
   * Funzione per stampare un vettore di interi.
   *
   * @param convertitore vettore di interi da stampare
   */
  // FUNZIONE PER STAMPARE IL VETTORE SCELTO
  public static void stampaVettore(int[] convertitore) {
    System.out.print("{");
    for (int i : convertitore) {
      System.out.print(i + ",");
    }
    System.out.print("\b}\n");
  }

  /**
   * Funzione per stampare un vettore di double.
   *
   * @param convertitore vettore di double da stampare
   */
  // FUNZIONE PER STAMPARE IL VETTORE SCELTO
  public static void stampaVettoreDouble(double[] convertitore) {
    System.out.print("{");
    for (double i : convertitore) {
      System.out.print(i + ",");
    }
    System.out.print("\b}\n");
  }

  /**
   * Ordina un vettore di interi utilizzando l'algoritmo di ordinamento per selezione.
   *
   * @param convertitore vettore di interi da ordinare
   */
  // FUNZIONE PER ORDINARE IL VETTORE CON IL METODO DELLA SELEZIONE
  public static void vettoreOrdinatoSelezione(int[] convertitore) {
    int cont = 0;
    for (int i = 0; i < convertitore.length - 1; ++i) {
      int cont2 = i;
      for (int j = i + 1; j < convertitore.length; ++j) {
        if (convertitore[cont2] > convertitore[j]) {
          cont2 = j;
        }
        if (i != cont2) {
          int scambio = convertitore[cont2];
          convertitore[cont2] = convertitore[i];
          convertitore[i] = scambio;
        }
      }
      ++cont;
    }
    System.out.println("Cicli fatti per ordinarlo il vettore: " + cont);
    System.out.println("Vettore ordinato per selezione: ");
    stampaVettore(convertitore);
  }

  /**
   * Ordina un vettore di double utilizzando l'algoritmo di ordinamento per selezione.
   *
   * @param convertitore vettore di double da ordinare
   */
  // FUNZIONE PER ORDINARE IL VETTORE CON IL METODO DELLA SELEZIONE
  public static void vettoreOrdinatoSelezioneD(double[] convertitore) {
    int cont = 0;
    for (int i = 0; i < convertitore.length - 1; ++i) {
      int cont2 = i;
      for (int j = i + 1; j < convertitore.length; ++j) {
        if (convertitore[cont2] > convertitore[j]) {
          cont2 = j;
        }
        if (i != cont2) {
          double scambio = convertitore[cont2];
          convertitore[cont2] = convertitore[i];
          convertitore[i] = scambio;
        }
      }
      ++cont;
    }
    System.out.println("Cicli fatti per ordinarlo il vettore: " + cont);
    System.out.println("Vettore ordinato per selezione: ");
    stampaVettoreDouble(convertitore);
  }

  /**
   * Funzione per chiedere in input due numeri decimali richiamando la funzione
   * leggiString
   * 
   * @param domanda1 è la domanda da porre all'utente all'inserimento del numero
   * @return restituisce il numero letto
   */
  public static double leggiDouble(String domanda1) {
    String risposta = "";
    double input1 = 0;
    while (true) {
      try {
        risposta = domanda(domanda1);
        input1 = Double.valueOf(risposta).doubleValue();
        return input1;
      } catch (NumberFormatException e) {
        System.out.println("Devi inserire un numero decimale, hai inserito " + risposta);
      }
    }
  }

  /**
   * Funzione per chiedere in input una stringa con gestione delle eccezioni
   * 
   * @param domanda è la domanda da porre all'utente
   * @return restituisce la stringa da leggere
   */
  public static String leggiString(String domanda) {
    String risposta = "";
    while (true) {
      try {
        System.out.println(domanda);// chiedo all'utente di inserire una stringa
        risposta = tastiera.readLine();// ricevo la stringa e la memorizzo in risposta
        return risposta;
      } catch (IOException e) {// gestisco il caso in cui ci siano errori di input
        System.out.println("tastiera non disponibile");
      }
    }
  }

  /**
   * Funzione per chiedere in input un intero maggiore di un numero stabilito
   * richiamando la funzione leggiInt e gestendo le eventuali eccezioni
   * 
   * @param domanda è la domanda da porre all'utente
   * @param limite  è il numero rispetto al quale il numero inserito deve essere
   *                minore
   * @return restituisce l'intero letto
   */
  public static int leggiIntMaggioreDi(String domanda, int limite) {
    int input; // intero letto con leggiInt
    do {
      input = leggiIntero(domanda);// converto risposta in int e la memorizzo in input
      if (input > limite) {
        return input;// se il numero dato in input è maggiore di limite lo restituisco
      } else {
        System.out.println("Devi inserire un intero maggiore di " + limite + ", riprova");// se il numero dato in input
                                                                                          // è minore di limite avverto
                                                                                          // l'utente
      }
    } while (true);
  }

  /**
   * Funzione per chiedere in input un numero maggiore di un numero stabilito
   * richiamando la funzione leggiInt e gestendo le eventuali eccezioni
   * 
   * @param domanda è la domanda da porre all'utente
   * @param limite  è il numero rispetto al quale il numero inserito deve essere
   *                minore
   * @return restituisce il numero letto
   */
  public static double leggiDoubleMaggioreDi(String domanda, double limite) {
    double input; // numero letto con leggiInt
    do {
      input = leggiDouble(domanda);// converto risposta in int e la memorizzo in input
      if (input > limite) {
        return input;// se il numero dato in input è maggiore di limite lo restituisco
      } else {
        System.out.println("Devi inserire un numero maggiore di " + limite + ", riprova");// se il numero dato in input
                                                                                          // è minore di limite avverto
                                                                                          // l'utente
      }
    } while (true);
  }

  /**
   * Funzione per chiedere in input una stringa composta da elementi separati da
   * un elemento separatore attraverso la funzione leggiString e per memorizzare
   * separatamente gli elementi in un vettore attraverso la funzione .split()
   * 
   * @param domanda    è la domanda da porre all'utente
   * @param separatore è il carattere con cui separare gli elementi del vettore
   *                   nella stringa
   * @return restituisce il vettore di stringhe ottenuto con .split
   */
  public static String[] leggiVettoreString(String domanda, String separatore) {
    boolean flag = true; // uso un flag per ripetere il ciclo in caso il vettore fosse nullo
    String[] vet = { "prova" };// inizializzo il vettore fuori dal while in modo tale da ritornarlo
    while (flag) {// scrivo un ciclo while per ripetere l'input in caso qualcosa vada storto
      String risposta = leggiString(domanda);// leggo una stringa contenente gli elementi che andranno nel vettore
                                             // separate dal carattere separatore
      vet = risposta.split(separatore);// separo gli elementi della stringa attraverso .split(separatore) e li
                                       // memorizzo in vet
      if (vet.length == 0) {
        System.out.println("L'insieme non ha elementi, riprova");// se il vettore ha 0 elementi, ripeto il ciclo
      } else {
        flag = false;// se il vettore esiste faccio diventare falso il flag, interrompendo il ciclo
      }
    }
    return vet;// restituisco vet
  }

  /**
   * Funzione per chiedere in input un intero richiamando la funzione leggiInt
   * assicurandosi che il valore inserito rientri in un determinato intervallo
   * 
   * @param domanda è la domanda da porre all'utente
   * @param nInf    è il numero minimo dell'intervallo
   * @param nSup    è il numero massimo dell'intervallo
   * @return restituisce l'intero letto
   */
  public static int leggiIntIntervallo(String domanda, int nInf, int nSup) {
    while (true) {
      int input = leggiIntero(domanda);
      if (input >= nInf && input <= nSup) {
        return input;
      } else {
        System.out.println("L'intero che hai inserito deve essere compreso tra " + nInf + " e " + nSup + ",riprova");
         attesa(2);
    clean();
      }
    }

  }

  /**
   * Funzione per salvare un intero se rientra in un determinato intervallo
   * 
   * @param valore è l'intero da controllare
   * @param nInf   è il numero minimo dell'intervallo
   * @param nSup   è il numero massimo dell'intervallo
   * @return restituisce l'intero se rientra nell'intervallo, altrimenti richiama
   *         la funzione leggiIntIntervallo per chiedere un nuovo intero
   */

  public static int salvaIntSeInIntervallo(int valore, int nInf, int nSup) {
    if (valore >= nInf && valore <= nSup) {
      return valore;
    }
    String domanda = "Inserisci un numero compreso tra " + nInf + " e " + nSup;
    return leggiIntIntervallo(domanda, nInf, nSup);
  }

  /**
   * Calcola la media di un vettore di interi.
   *
   * @param convertitore vettore di interi
   * @return media dei valori nel vettore
   */
  public static double mediaInt(int[] convertitore) {
    int somma = 0;
    for (int i = 0; i < convertitore.length; i++) {
      int salva = convertitore[i];
      somma = somma + salva;
    }
    double media = somma / convertitore.length;
    return media;
  }

  /**
   * Calcola la media di un vettore di double.
   *
   * @param convertitore vettore di double
   * @return media dei valori nel vettore
   */
  public static double mediaDouble(double[] convertitore) {
    double somma = 0;
    for (int i = 0; i < convertitore.length; i++) {
      double salva = convertitore[i];
      somma = somma + salva;
    }
    double media = somma / convertitore.length;
    return media;
  }

  /** Funzione di saluto finale. */
  public static void saluto() {
    System.out.println("Grazie per aver usato il mio programma\nArrivederci.");
  }

  /** Funzione di attesa per un numero specificato di secondi.
   * @param x numero di secondi da attendere
   */
  public static void attesa(int x) {
    double tempo;
    tempo = System.currentTimeMillis();
    x = x * 1000;
    do {
    } while (System.currentTimeMillis() < (tempo + x));
    clean();
  }

  /** Funzione per pulire la console. */
  public static void clean() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }


  /*
  public static void menu() {
    clean();
    ProgAlbero a = new ProgAlbero();// Creo l'oggetto albero
    String domanda = "BENVENUTO\nInserire:\n1 Aggiungere un dato\n2 Eliminare un dato\n3 Ricerca di un dato\n4 Stampa ordinata\n5 Stampa in preordine\n6 Stampa in postordine\n6 Uscire\nRisposta= ";//domanda del menù
    int risp = 0;// variabile che prende in input l'opione scelta dall'utente
    do {
      risp = leggiIntero(domanda);
      clean();
      switch (risp) {
        case 1:
          a.aggiunta();
          break;
        case 2:
          a.eliminazione();
          break;
        case 3:
          a.ricerca();
          break;
        case 4:
         a.stampaInOrdine();
          new java.util.Scanner(System.in).nextLine();
          break;
        case 5:
        a.stampaPreOrdine();
          new java.util.Scanner(System.in).nextLine();
          break;
        case 6:
          a.stampaPostOrdine();
          new java.util.Scanner(System.in).nextLine();
          break;
        case 7:
          saluto();
          return;
        default:
          stampa("Hai sbaglaito!!\nInserire un valore da 1 a 6\n");

      }
      attesa(2);
    } while (risp != 7);
  }
*/

  /**
 * Stampa una stringa a video, seguita da una riga vuota.
 *
 * @param s la stringa da stampare
 */
public static void stampa(String s) {
    System.out.println(s + "\n");
}

/**
 * Stampa un intero a video, seguito da una riga vuota.
 *
 * @param s l'intero da stampare
 */
public static void stampa(int s) {
    System.out.println(s + "\n");
}

/**
 * Stampa un float a video, seguito da una riga vuota.
 *
 * @param s il float da stampare
 */
public static void stampa(float s) {
    System.out.println(s + "\n");
}

/**
 * Stampa un Double a video, seguito da una riga vuota.
 *
 * @param s il Double da stampare
 */
public static void stampa(Double s) {
    System.out.println(s + "\n");
}

/**
 * Stampa un messaggio che indica se un elemento è stato trovato o meno.
 *
 * @param trovato true se l'elemento è stato trovato, false altrimenti
 */
public static void trovato(boolean trovato) {
    if (!trovato) {
        stampa("Non è stato trovato niente\n");
    } else {
        stampa("È stato trovato\n");
    }
}

  /**
   * Funzione per calcolare il fattoriale di un numero intero n.
   * @param n numero intero di cui calcolare il fattoriale
   * @return il fattoriale di n
   */
  public static long fattoriale(int n) {
    if (n <= 1)// caso base
      return 1;
    else
      return n * fattoriale(n - 1);

  }

  /**
   * Funzione per calcolare la somma dei numeri da 1 a n.
   * @param n numero intero fino al quale calcolare la somma
   * @return la somma dei numeri da 1 a n
   */
  public static int sommaNum(int n) {
    if (n == 1)// caso base
      return n;
    else
      return n + sommaNum(n - 1);
  }

  /**
   * Funzione per calcolare la potenza di un numero
   * @param x base
   * @param n esponente
   * @return potenza della base
   */

  public static long potenza(int x, int n) {

    if (n == 0)
      return 1;
    else
      return x * potenza(x, n - 1);

  }

  /**
   * Funzione per calcolare la somma di due numeri interi utilizzando la ricorsione.
   * @param a primo numero intero
   * @param b secondo numero intero
   * @return somma di a e b
   */
  public static int somma(int a, int b) {
    if (b == 0)
      return a;
    else
      return 1 + somma(a, b - 1);
  }

  /**
   * Funzione per calcolare il prodotto di due numeri interi utilizzando la ricorsione.
   * @param a primo numero intero
   * @param b secondo numero intero
   * @return prodotto di a e b
   */
  public static int prodotto(int a, int b) {
    if (b == 0)
      return 0;

    else
      return a + prodotto(a, b - 1);

  }

  /**
   * Funzione per calcolare la potenza di un numero float
   * @param x base
   * @param n esponente
   * @return potenza della base
   */
  public static float potenza(float x, float n) {
    if (n == 0)
      return 1;
    else
      return x * potenza(x, n - 1);}

  
/**
 * Calcola ricorsivamente la somma dei numeri da 1 a n (float).
 *
 * @param n numero fino a cui calcolare la somma
 * @return somma dei numeri da 1 a n
 */
public static float sommaNum(float n) {
    if (n == 1)
        return n;
    else
        return n + sommaNum(n - 1);
}

/**
 * Calcola ricorsivamente la somma di due numeri float.
 *
 * @param a primo numero
 * @param b secondo numero
 * @return somma dei due numeri
 */
public static float somma(float a, float b) {
    if (b == 0)
        return a;
    else
        return 1 + somma(a, b - 1);
}

/**
 * Calcola ricorsivamente il prodotto di due numeri float.
 *
 * @param a primo numero
 * @param b secondo numero
 * @return prodotto dei due numeri
 */
public static float prodotto(float a, float b) {
    if (b == 0)
        return 0;
    else
        return a + prodotto(a, b - 1);
}

/**
 * Stampa la rappresentazione binaria di un numero intero.
 *
 * @param n numero da convertire in binario
 */
public static void binario(int n) {
    if (n != 0) {
        binario(n / 2);
        System.out.print(n % 2);
    }
}

/**
 * Restituisce il valore minimo in un array di interi a partire dall'indice i.
 *
 * @param v array di interi
 * @param i indice di partenza per la ricerca del minimo
 * @param min valore minimo corrente
 * @return valore minimo dell'array
 */
public static int minimoArray(int v[], int i, int min) {
    if (i == v.length)
        return min;
    if (v[i] < min)
        return minimoArray(v, i + 1, v[i]);
    return minimoArray(v, i + 1, min);
}

/**
 * Stampa un saluto numerato in ordine crescente usando la ricorsione.
 *
 * @param n numero di saluti da stampare
 */
public static void salutaRicorsione(int n) {
    if (n == 0)
        return;

    salutaRicorsione(n - 1);
    System.out.println("Ciao-->" + n);
}

/**
 * Aggiunge un elemento a un array di interi. Se l'array è null, crea un nuovo array di dimensione 1.
 *
 * @param v array di interi originale (può essere null)
 * @param c elemento da aggiungere
 * @return nuovo array contenente tutti gli elementi originali più l'elemento aggiunto
 */
public static int[] aggiungiElemento(int[] v, int c) {
    int[] n;
    try {
        n = new int[v.length + 1];
        for (int i = 0; i < v.length; i++) {
            n[i] = v[i];
        }
        n[v.length] = c;
    } catch (NullPointerException e) {
        n = new int[1];
        n[0] = c;
    }
    return n;
}

/**
 * Aggiunge un elemento a un array di stringhe. Se l'array è null, crea un nuovo array di dimensione 1.
 *
 * @param v array di stringhe originale (può essere null)
 * @param c elemento da aggiungere
 * @return nuovo array contenente tutti gli elementi originali più l'elemento aggiunto
 */
public static String[] aggiungiElemento(String[] v, String c) {
    String[] n;
    try {
        n = new String[v.length + 1];
        for (int i = 0; i < v.length; i++) {
            n[i] = v[i];
        }
        n[v.length] = c;
    } catch (NullPointerException e) {
        n = new String[1];
        n[0] = c;
    }
    return n;
}

/**
 * Unisce due array di interi in un unico array senza duplicati.
 *
 * @param b primo array di interi
 * @param a secondo array di interi
 * @return array contenente tutti gli elementi di a e b senza duplicati
 */
public static int[] unisciVettori(int[] b, int[] a) {
    int[] c = new int[1];
    c[0] = a[0];
    for (int idx = 1; idx < a.length; idx++) {
        if (!presente(c, a[idx]))
            c = aggiungiElemento(c, a[idx]);
    }
    for (int idx = 0; idx < b.length; idx++) {
        if (!presente(c, b[idx]))
            c = aggiungiElemento(c, b[idx]);
    }
    return c;
}


/**
 * Unisce due array di stringhe in un unico array senza duplicati.
 *
 * @param b primo array di stringhe
 * @param a secondo array di stringhe
 * @return array contenente tutti gli elementi di a e b senza duplicati
 */
public static String[] unisciVettori(String[] b, String[] a) {
    String[] c = new String[1];
    c[0] = a[0];
    for (int idx = 1; idx < a.length; idx++) {
      if (!presente(c, a[idx]))
        c = aggiungiElemento(c, a[idx]);
    }
    for (int idx = 0; idx < b.length; idx++) {
      if (!presente(c, b[idx]))
        c = aggiungiElemento(c, b[idx]);
    }
    return c;
  }

/**
 * Controlla se un valore stringa è presente in un array di stringhe.
 *
 * @param a l'array di stringhe in cui cercare
 * @param v la stringa da cercare
 * @return true se la stringa è presente nell'array, false altrimenti
 */
public static boolean presente(String[] a, String v) {
    for (int i = 0; i < a.length; i++) {
        if (a[i] == v)  // Attenzione: in Java per le stringhe si usa .equals() per il confronto di contenuto
            return true;
    }
    return false;
}

/**
 * Controlla se un valore intero è presente in un array di interi.
 *
 * @param a l'array di interi in cui cercare
 * @param v l'intero da cercare
 * @return true se l'intero è presente nell'array, false altrimenti
 */
public static boolean presente(int[] a, int v) {
    for (int i = 0; i < a.length; i++) {
        if (a[i] == v)
            return true;
    }
    return false;
}

/**
 * Calcola l'intersezione tra due array di interi, restituendo un nuovo array
 * contenente solo gli elementi presenti in entrambi gli array.
 *
 * @param a il primo array di interi
 * @param b il secondo array di interi
 * @return un array contenente gli elementi comuni ai due array
 */
public static int[] intersezioneVettori(int[] b, int[] a) {
    int[] c = null;
    for (int i = 0; i < a.length; i++) {
        if (presente(b, a[i]))
            c = aggiungiElemento(c, a[i]); // Presumibilmente aggiunge un elemento all'array (funzione non mostrata)
    }
    return c;
}

/**
 * Calcola l'intersezione tra due array di stringhe, restituendo un nuovo array
 * contenente solo gli elementi presenti in entrambi gli array.
 *
 * @param a il primo array di stringhe
 * @param b il secondo array di stringhe
 * @return un array contenente gli elementi comuni ai due array
 */
public static String[] intersezioneVettori(String[] b, String[] a) {
    String[] c = null;
    for (int i = 0; i < a.length; i++) {
        if (presente(b, a[i]))
            c = aggiungiElemento(c, a[i]); // Presumibilmente aggiunge un elemento all'array (funzione non mostrata)
    }
    return c;
}

/**
 * Calcola l'EXOR (differenza simmetrica) tra due array di interi.
 * La EXOR include gli elementi presenti in uno solo dei due array.
 *
 * @param a il primo array di interi
 * @param b il secondo array di interi
 * @return un array contenente gli elementi presenti solo in a o solo in b
 */
public static int[] exorVettori(int[] b, int[] a) {
    int[] unione = unisciVettori(b, a);           // Unione dei due array
    int[] intersezione = intersezioneVettori(b, a); // Elementi comuni
    int[] c = null;
    for (int i = 0; i < unione.length; i++) {
        if (!presente(intersezione, unione[i])) // Se l'elemento non è nell'intersezione
            c = aggiungiElemento(c, unione[i]); // Aggiungilo al risultato
    }
    return c;
}

/**
 * Calcola l'EXOR (differenza simmetrica) tra due array di stringhe.
 * La EXOR include gli elementi presenti in uno solo dei due array.
 *
 * @param a il primo array di stringhe
 * @param b il secondo array di stringhe
 * @return un array contenente gli elementi presenti solo in a o solo in b
 */
public static String[] exorVettori(String[] b, String[] a) {
    String[] unione = unisciVettori(b, a);
    String[] intersezione = intersezioneVettori(b, a);
    String[] c = null;
    for (int i = 0; i < unione.length; i++) {
        if (!presente(intersezione, unione[i]))
            c = aggiungiElemento(c, unione[i]);
    }
    return c;
}

/**
 * Stampa un array di stringhe senza titolo.
 *
 * @param v l'array di stringhe da stampare
 */
public static void stampaVettore(String[] v) {
    stampaVettore(v, "");
}

/**
 * Stampa un array di stringhe con un titolo.
 *
 * @param v l'array di stringhe da stampare
 * @param titolo il titolo da visualizzare prima dell'array
 */
public static void stampaVettore(String[] v, String titolo) {
    stampaVettore(v, titolo, "v");
}

/**
 * Stampa un array di stringhe con titolo e indicazione della lettera
 * che rappresenta l'array (es. "v[0] - elemento").
 *
 * @param v l'array di stringhe da stampare
 * @param titolo il titolo da visualizzare prima dell'array
 * @param lettera la lettera che rappresenta l'array negli indici
 */
public static void stampaVettore(String[] v, String titolo, String lettera) {
    try {
        System.out.println(titolo);
        for (int i = 0; i < v.length; i++) {
            System.out.println(lettera + "[" + i + "]- " + v[i]);
        }
    } catch (Exception e) {
        System.out.println("vettore vuoto");
    }
}



/**
 * Legge un file CSV riga per riga e restituisce le righe in un Vector di stringhe.
 *
 * @param nameFile il percorso del file CSV da leggere
 * @return un Vector contenente tutte le righe del file, oppure null se il file non esiste
 */
public static Vector<String> readCSV(String nameFile) {
    Vector<String> rows = new Vector<String>();
    FileReader f = null;
    BufferedReader fIN;
    String s;
    try {
        f = new FileReader(nameFile);
        fIN = new BufferedReader(f);
        s = fIN.readLine();
        while (s != null) {
            rows.addElement(s);
            s = fIN.readLine();
        }
    } catch (FileNotFoundException e) { // il file non esiste
        System.out.println("il file indicato non esiste");
        return null;
    } catch (EOFException e) { // fine file, si esce dal while
    } catch (Exception e) {
        System.out.println(e);
    }
    try {
        f.close();
    } catch (IOException e) {
        System.out.println(e);
    }
    return rows;
}

/**
 * Scrive un array di stringhe in un file CSV.
 *
 * @param rows l'array di stringhe da scrivere nel file
 * @param nameFile il percorso del file CSV
 * @param append true per aggiungere al file esistente, false per sovrascriverlo
 */
public static void writeCSV(String[] rows, String nameFile, boolean append) {
    FileWriter f = null;
    PrintWriter fOUT;
    try {
        f = new FileWriter(nameFile, append);
        fOUT = new PrintWriter(f);
        for (String s : rows) {
            fOUT.println(s);
        }
        fOUT.flush();
        f.close();
    } catch (EOFException e) { // ignorato
    } catch (Exception e) {
        System.out.println(e);
    }
}

/**
 * Scrive un array di stringhe in un file CSV sovrascrivendo il file esistente.
 *
 * @param rows l'array di stringhe da scrivere nel file
 * @param nameFile il percorso del file CSV
 */
public static void writeCSV(String[] rows, String nameFile) {
    writeCSV(rows, nameFile, false);
}

/**
 * Chiede all'utente una risposta Y/N e restituisce il valore booleano corrispondente.
 *
 * @param domanda la domanda da mostrare all'utente
 * @return true se l'utente risponde Y/y, false se risponde N/n
 * @throws IllegalArgumentException se la risposta non è Y/y o N/n
 */
public static boolean leggiBool(String domanda) {
    String risposta;
    boolean convertitore = false;
    while (true) {
        risposta = domanda(domanda); // presumibilmente una funzione che legge input da tastiera
        try {
            if (risposta.equals("Y") || risposta.equals("y")) {
                convertitore = true;
            } else if (risposta.equals("N") || risposta.equals("n")) {
                // convertitore resta false
            } else {
                throw new IllegalArgumentException("Risposta non valida: inserisci Y/y o N/n");
            }
            return convertitore;
        } catch (NumberFormatException e) {
            System.out.println("il valore non è valido");
        }
    }
}
  /**
   * Legge una data dall'input dell'utente nel formato specificato.
   *
   * @param domanda testo della domanda da visualizzare
   * @param formato formato della data (es. "dd/MM/yyyy")
   * @return data inserita dall'utente come oggetto LocalDate
   */
  public static LocalDate leggiData(String domanda, String formato) {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
      LocalDate dataConvertita;
      while (true) {
          String risposta = domanda(domanda + " (formato: " + formato + "): ");
          try {
              dataConvertita = LocalDate.parse(risposta, formatter);
              return dataConvertita;
          } catch (Exception e) {
              System.out.println("La data non è valida. Assicurati di utilizzare il formato corretto.");
          }
      }
    }

// ------------------- Vettori -------------------

  /**
   * Funzione per controllare che un elemento sia presente in un vettore di String.
   *
   * @param elemento l'elemento da cercare
   * @param vettore  il vettore in cui cercare
   * @return true se l'elemento è presente, false altrimenti
   */
  public static boolean contieneElemento(String elemento, Vector<String> vettore) {
      return vettore.contains(elemento);
  }

  /**
   * Funzione per controllare che un elemento sia presente in un vettore di Integer.
   *
   * @param elemento l'elemento da cercare
   * @param vettore  il vettore in cui cercare
   * @return true se l'elemento è presente, false altrimenti
   */
  public static boolean contieneElemento(Integer elemento, Vector<Integer> vettore) {
      return vettore.contains(elemento);
  }

  /**
   * Funzione per controllare che un elemento sia presente in un vettore di Double.
   *
   * @param elemento l'elemento da cercare
   * @param vettore  il vettore in cui cercare
   * @return true se l'elemento è presente, false altrimenti
   */
  public static boolean contieneElemento(Double elemento, Vector<Double> vettore) {
      return vettore.contains(elemento);
  }

  /**
   * Funzione per controllare che un elemento sia presente in un vettore di LocalDate.
   *
   * @param elemento l'elemento da cercare
   * @param vettore  il vettore in cui cercare
   * @return true se l'elemento è presente, false altrimenti
   */
  public static boolean contieneElemento(LocalDate elemento, Vector<LocalDate> vettore) {
      return vettore.contains(elemento);
  }

// ------------------- Array monodimensionali -------------------

/**
 * Controlla se un elemento intero è presente in un array di int.
 *
 * @param elemento l'elemento da cercare
 * @param array l'array in cui cercare
 * @return true se l'elemento è presente, false altrimenti
 */
public static boolean contieneElemento(int elemento, int[] array) {
    for (int e : array) {
        if (e == elemento) return true;
    }
    return false;
}

/**
 * Controlla se un elemento double è presente in un array di double.
 *
 * @param elemento l'elemento da cercare
 * @param array l'array in cui cercare
 * @return true se l'elemento è presente, false altrimenti
 */
public static boolean contieneElemento(double elemento, double[] array) {
    for (double e : array) {
        if (e == elemento) return true;
    }
    return false;
}

/**
 * Controlla se un elemento float è presente in un array di float.
 *
 * @param elemento l'elemento da cercare
 * @param array l'array in cui cercare
 * @return true se l'elemento è presente, false altrimenti
 */
public static boolean contieneElemento(float elemento, float[] array) {
    for (float e : array) {
        if (e == elemento) return true;
    }
    return false;
}

/**
 * Controlla se un elemento long è presente in un array di long.
 *
 * @param elemento l'elemento da cercare
 * @param array l'array in cui cercare
 * @return true se l'elemento è presente, false altrimenti
 */
public static boolean contieneElemento(long elemento, long[] array) {
    for (long e : array) {
        if (e == elemento) return true;
    }
    return false;
}

/**
 * Controlla se un elemento String è presente in un array di String.
 *
 * @param elemento l'elemento da cercare
 * @param array l'array in cui cercare
 * @return true se l'elemento è presente, false altrimenti
 */
public static boolean contieneElemento(String elemento, String[] array) {
    for (String e : array) {
        if (e.equals(elemento)) return true;
    }
    return false;
}

/**
 * Controlla se un elemento LocalDate è presente in un array di LocalDate.
 *
 * @param elemento l'elemento da cercare
 * @param array l'array in cui cercare
 * @return true se l'elemento è presente, false altrimenti
 */
public static boolean contieneElemento(LocalDate elemento, LocalDate[] array) {
    for (LocalDate e : array) {
        if (e.equals(elemento)) return true;
    }
    return false;
}

// ------------------- Matrici bidimensionali -------------------

/**
 * Controlla se un elemento intero è presente in una matrice di int.
 *
 * @param elemento l'elemento da cercare
 * @param matrice la matrice in cui cercare
 * @return true se l'elemento è presente, false altrimenti
 */
public static boolean contieneElemento(int elemento, int[][] matrice) {
    for (int[] riga : matrice) {
        for (int colonna : riga) {
            if (colonna == elemento) return true;
        }
    }
    return false;
}

/**
 * Controlla se un elemento double è presente in una matrice di double.
 *
 * @param elemento l'elemento da cercare
 * @param matrice la matrice in cui cercare
 * @return true se l'elemento è presente, false altrimenti
 */
public static boolean contieneElemento(double elemento, double[][] matrice) {
    for (double[] riga : matrice) {
        for (double colonna : riga) {
            if (colonna == elemento) return true;
        }
    }
    return false;
}

/**
 * Controlla se un elemento float è presente in una matrice di float.
 *
 * @param elemento l'elemento da cercare
 * @param matrice la matrice in cui cercare
 * @return true se l'elemento è presente, false altrimenti
 */
public static boolean contieneElemento(float elemento, float[][] matrice) {
    for (float[] riga : matrice) {
        for (float colonna : riga) {
            if (colonna == elemento) return true;
        }
    }
    return false;
}

/**
 * Controlla se un elemento long è presente in una matrice di long.
 *
 * @param elemento l'elemento da cercare
 * @param matrice la matrice in cui cercare
 * @return true se l'elemento è presente, false altrimenti
 */
public static boolean contieneElemento(long elemento, long[][] matrice) {
    for (long[] riga : matrice) {
        for (long colonna : riga) {
            if (colonna == elemento) return true;
        }
    }
    return false;
}

/**
 * Controlla se un elemento String è presente in una matrice di String.
 *
 * @param elemento l'elemento da cercare
 * @param matrice la matrice in cui cercare
 * @return true se l'elemento è presente, false altrimenti
 */
public static boolean contieneElemento(String elemento, String[][] matrice) {
    for (String[] riga : matrice) {
        for (String colonna : riga) {
            if (colonna.equals(elemento)) return true;
        }
    }
    return false;
}

/**
 * Controlla se un elemento LocalDate è presente in una matrice di LocalDate.
 *
 * @param elemento l'elemento da cercare
 * @param matrice la matrice in cui cercare
 * @return true se l'elemento è presente, false altrimenti
 */
public static boolean contieneElemento(LocalDate elemento, LocalDate[][] matrice) {
    for (LocalDate[] riga : matrice) {
        for (LocalDate colonna : riga) {
            if (colonna.equals(elemento)) return true;
        }
    }
    return false;
}


}
