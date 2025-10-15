import java.io.*;
import java.util.Vector;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
class MieFunzioni implements Serializable{
  public static InputStreamReader in = new InputStreamReader(System.in);
  public static BufferedReader tastiera = new BufferedReader(in);

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

  // FUNZIONE PER CONVERTIRE TUTTI I VALORI DA STRINGA AD INTERO
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
  // FUNZIONE PER SEPARARE I VALORI CON IL - E SALVARLI
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

  // FUNZIONE PER STAMPARE INT
  public static void stampaInt(int convertitore) {
    System.out.print("{");
    System.out.print(convertitore);
    System.out.print("\b}\n");
  }

  // FUNZIONE PER STAMPARE DOUBLE
  public static void stampaDouble(double convertitore) {
    System.out.print(convertitore);
  }

  // FUNZIONE PER STAMPARE IL VETTORE SCELTO
  public static void stampaVettore(int[] convertitore) {
    System.out.print("{");
    for (int i : convertitore) {
      System.out.print(i + ",");
    }
    System.out.print("\b}\n");
  }

  // FUNZIONE PER STAMPARE IL VETTORE SCELTO
  public static void stampaVettoreDouble(double[] convertitore) {
    System.out.print("{");
    for (double i : convertitore) {
      System.out.print(i + ",");
    }
    System.out.print("\b}\n");
  }

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

  public static int salvaIntSeInIntervallo(int valore, int nInf, int nSup) {
    if (valore >= nInf && valore <= nSup) {
      return valore;
    }
    String domanda = "Inserisci un numero compreso tra " + nInf + " e " + nSup;
    return leggiIntIntervallo(domanda, nInf, nSup);
  }

  public static double mediaInt(int[] convertitore) {
    int somma = 0;
    for (int i = 0; i < convertitore.length; i++) {
      int salva = convertitore[i];
      somma = somma + salva;
    }
    double media = somma / convertitore.length;
    return media;
  }

  public static double mediaDouble(double[] convertitore) {
    double somma = 0;
    for (int i = 0; i < convertitore.length; i++) {
      double salva = convertitore[i];
      somma = somma + salva;
    }
    double media = somma / convertitore.length;
    return media;
  }

  public static void saluto() {
    System.out.println("Grazie per aver usato il mio programma\nArrivederci.");
  }

  public static void attesa(int x) {
    double tempo;
    tempo = System.currentTimeMillis();
    x = x * 1000;
    do {
    } while (System.currentTimeMillis() < (tempo + x));
    clean();
  }

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
  public static void stampa(String s) {
    System.out.println(s+"\n");
  }
  public static void stampa(int s) {
    System.out.println(s+"\n");
  }
  public static void stampa(float s) {
    System.out.println(s+"\n");
  }
  public static void stampa(Double s) {
    System.out.println(s+"\n");
  }
  public static void trovato(boolean trovato) {
    if (!trovato) {
      stampa("Non è stato trovato niente\n");
    } else {
      stampa("È stato trovato\n");
    }
  }

  public static long fattoriale(int n) {
    if (n <= 1)// caso base
      return 1;
    else
      return n * fattoriale(n - 1);

  }

  public static int sommaNum(int n) {
    if (n == 1)// caso base
      return n;
    else
      return n + sommaNum(n - 1);
  }

  public static long potenza(int x, int n) {

    if (n == 0)
      return 1;
    else
      return x * potenza(x, n - 1);

  }

  public static int somma(int a, int b) {
    if (b == 0)
      return a;
    else
      return 1 + somma(a, b - 1);
  }

  public static int prodotto(int a, int b) {
    if (b == 0)
      return 0;

    else
      return a + prodotto(a, b - 1);

  }
  public static float potenza(float x, float n) {
    if (n == 0)
      return 1;
    else
      return x * potenza(x, n - 1);}
//funzione sommaNum con float
    public static float sommaNum(float n) {
      if (n == 1)
      return n;
    else
      return n + sommaNum(n - 1);}
  //funzione somma con float
    public static float somma(float a, float b) {
      if (b == 0)
      return a;
    else
        return 1 + somma(a, b - 1);}
  //funzione prodotto con float
      public static float prodotto(float a, float b) {
        if (b == 0)
          return 0;
        else
          return a + prodotto(a, b - 1);}
  //
  public static void binario(int n) {
    if (n != 0) {
      binario(n / 2);
      System.out.print(n % 2);
    }
  }

  public static int minimoArray(int v[], int i, int min) {
    if (i == v.length)
      return min;
    if (v[i] < min)
      return minimoArray(v, i + 1, v[i]);
    return minimoArray(v, i + 1, min);
  }

  public static void salutaRicorsione(int n) {
    if (n == 0)
      return;

    salutaRicorsione(n - 1);
    System.out.println("Ciao-->" + n);
    // saluta(n - 1);

  }
public static int[] aggiungiElemento(int[] v, int c) {
    int[] n;
    try {
      n = new int[v.length + 1];// se v é vuoto crea eccezzione NullPointerException
      for (int i = 0; i < v.length; i++) {
        n[i] = v[i];
      }
      n[v.length] = c;
    } catch (NullPointerException e) {// vuol dire che v é null, quindi prima lo creo di un elemento e poi assegno c,
                                      // quindi avró un array con un solo elemento di valore c
      n = new int[1];
      n[0] = c;
    }
    return n;
  }

  // aggiungi elemento//
  public static String[] aggiungiElemento(String[] v, String c) {
    String[] n;
    try {
      n = new String[v.length + 1];// se v é vuoto crea eccezzione NullPointerException
      for (int i = 0; i < v.length; i++) {
        n[i] = v[i];
      }
      n[v.length] = c;
    } catch (NullPointerException e) {// vuol dire che v é null, quindi prima lo creo di un elemento e poi assegno c,
                                      // quindi avró un array con un solo elemento di valore c
      n = new String[1];
      n[0] = c;
    }
    return n;
  }
// funzione unione//
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
  public static boolean presente(String[] a, String v) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] == v)
        return true;
    }
    return false;
  }
 public static boolean presente(int[] a, int v) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] == v)
        return true;
    }
    return false;
  }
// funzione intersezione//
  public static int[] intersezioneVettori(int[] b, int[] a) {
    int[] c = null;
    for (int i = 0; i < a.length; i++) {
      if (presente(b, a[i]))
        c = aggiungiElemento(c, a[i]);
    }
    return c;
  }

  // funzione intersezione//
  public static String[] intersezioneVettori(String[] b, String[] a) {
    String[] c = null;
    for (int i = 0; i < a.length; i++) {
      if (presente(b, a[i]))
        c = aggiungiElemento(c, a[i]);
    }
    return c;
  }

  // funzione per exor//
  public static int[] exorVettori(int[] b, int[] a) {
    int[] unione = unisciVettori(b, a);
    int[] intersezione = intersezioneVettori(b, a);
    // la exor é la differenza tra unione ed intersezione
    int[] c = null;
    for (int i = 0; i < unione.length; i++) {// per tutti gli elementi dell'unione
      if (!presente(intersezione, unione[i]))// se il valore non é presente nell'intersezione
        // e quindi é presente solo in a o in b
        c = aggiungiElemento(c, unione[i]);
    }
    return c;
  }

  // funzione pestringr exor//
  public static String[] exorVettori(String[] b, String[] a) {
    String[] unione = unisciVettori(b, a);
    String[] intersezione = intersezioneVettori(b, a);
    // la exor é la differenza tra unione ed intersezione
    String[] c = null;
    for (int i = 0; i < unione.length; i++) {// per tutti gli elementi dell'unione
      if (!presente(intersezione, unione[i]))// se il valore non é presente nell'intersezione
        // e quindi é presente solo in a o in b
        c = aggiungiElemento(c, unione[i]);
    }
    return c;
  }
public static void stampaVettore(String[] v) {
    stampaVettore(v, "");
  }

  public static void stampaVettore(String[] v, String titolo) {
    stampaVettore(v, titolo, "v");
  }

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
public static Vector<String> readCSV(String nameFile) {
    Vector<String> rows = new Vector<String>();
    FileReader f = null;
    BufferedReader fIN;
    String s;
    try {
      f = new FileReader(nameFile);
      fIN = new BufferedReader(f);
      s=fIN.readLine();
      while (s!=null) {
        rows.addElement(s);
        s=fIN.readLine();
      }
    } catch (FileNotFoundException e) {// il file non esiste
      System.out.println("il file indicato non esiste");
      return null;
    } catch (EOFException e) {// esci dal while
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
    } catch (EOFException e) {// esci dal while
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void writeCSV(String[] rows, String nameFile) {
    writeCSV(rows, nameFile, false);
  }

  public static boolean leggiBool(String domanda){
    String risposta;
    boolean convertitore = false;
    while (true) {
      risposta = domanda(domanda);
      try {
        if(risposta.equals("Y") || risposta.equals("y")){
          convertitore = true;
        }else if(risposta.equals("N") || risposta.equals("n")){
        }else{
          throw new IllegalArgumentException("Risposta non valida: inserisci Y/y o N/n");
        }
        return convertitore;
      } catch (NumberFormatException e) {
        System.out.println("il valore non è valido");
      }
    }
  }



}
