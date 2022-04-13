/*
* Ihr werden die nötigen Klassen für das Programm impliziert
* java.io.* wird für das Einlesen von Daten gebraucht
* mit * habe ich die ganze Bibliothek von java.io impliziert
* java.nio.files.Files wird für das zugreifen auf Daten gebraucht
* mit java.util.* habe ich die Bibliothek impliziert da ich Listen und
* Zufalls Generator habe
 * */
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

//Das ist die zweite Klasse die, die Textdatei einliest und zur Main Klasse weitergibt
public class Fragenkatalog {

    // der path zum ziel Ordner wo die textdatei ist
    static String path = "C:\\Users\\BBRZ\\IdeaProjects\\MonkeyQuiz\\src\\Fragenkatalog.txt";
    static ArrayList<String> katalogInhalt = new ArrayList<>();
    //static ArrayList<Integer> checkListe = new ArrayList<>();
    static HashMap<Integer,String[]> fragen = new HashMap<>();
    static int indexErhoeher = 0;
    static int randomKey = 0;
    static Random random = new Random();
    static ArrayList<Integer> prevRandomKeys = new ArrayList<>();

    // Methode für das Einlesen der Datei
    public static void readFile() {
        // ihr wird Zeilen weisse ein Gelesen
        try (BufferedReader dateiLeser = Files.newBufferedReader(Path.of(path))) {

            String zeile;
            // ihr läuft die Schleife so lang bis Zeile nichts mehr in der Zeile steht
            while ((zeile = dateiLeser.readLine()) != null){
                // ihr wird jede Zeile in eine ArrayList gespeichert
                katalogInhalt.add(zeile);
            }
        }

        catch (IOException e) {
            System.out.println("Upps Fehler");
            e.printStackTrace();
        }

        mergeArraylistToHashmap();
        //Chose one Question

    }

    // Methode zum Erzeugen eines Zufälligen Listen Schlüssel
    public static String[] getRandomQuestion(boolean init){
        if(init) {
            randomKey = random.nextInt(0, fragen.size());
            prevRandomKeys.add(randomKey);
            return fragen.get(randomKey);
        }
        else
        {
            while (true){
                if(prevRandomKeys.size() >= fragen.size()/6)
                {
                    for(int prevRandomKey : prevRandomKeys)
                        if(randomKey == prevRandomKey)
                            randomKey = random.nextInt(0,fragen.size());
                        else
                        {
                            prevRandomKeys.add(randomKey);
                            return fragen.get(randomKey);
                        }
                }
                else
                {
                    System.out.println("keine fragen mehr...");
                    System.exit(0);
                }
            }
        }
    }

    // funktion zum Zusammenfügen von einer ArrayListe zu einer Hashmap
    public static void mergeArraylistToHashmap() {

        for (int j = 0; j < (katalogInhalt.size() / 6); j++) {
            // neues temp Array
            String[] tempFrage = new String[6];

            // Array befüllen
            for (int i = 0; i < tempFrage.length; i++) {
                tempFrage[i] = katalogInhalt.get(i + indexErhoeher);
            }
            indexErhoeher += 6;
            fragen.put(j,tempFrage);
        }
    }


}


