/*Monkey Quiz
Datum: 22.12.2021
Version: 01.0
Author: Rene Hütter
 */
import java.util.Scanner;                                 //Ihr wird die Klasse Scanner ins Programm impliziert

    public class Quiz {                                  //Main klasse

            static Scanner scanner = new Scanner(System.in);   //ScannerObjekt anlegen

            //Diese Var können von allen Methoden abgerufen werden
            static int leben = 3;                               //Leben der Spieler
            static int points = 0;
            static int pointsMax = 6;                          //Die Maximale Punkte zu erreichen
            static int Zustand = 0;                            //Zustand 0 ist für den Start des Program wichtig
            //Diese Var von Typ String sind schon vor formatiert für die Ausgabe
            static String win = "\tGratulation du hast die maximale Punktezahl erreicht!!\n";
            static String lose = "\tDanke fürs Spielen! Auf Wiedersehen.\n";

            static boolean init = true;

            // ihr ist die main Methode die das Programm ausführt
        public static void main(String[] args) {
            //Var anlegen
           String[] aktuelleFrage = new String[6];
           // Ihr startet die StateMachine eine endlos Schleife da die Bedienung Wahr ist (Boolesche Werte ist True)
           while (true) {
               // Spiel läuft bis keine leben vorhanden sind
               if (leben > 0) {
                   switch (Zustand) {
                       case 0:
                           //Ihr im Zustand 0 wird die Zufallszahl erzeugt, die Textdatei eingelesen und
                           // die Begrüssung Ausgeben
                           Fragenkatalog.readFile();
                           System.out.println("Willkommen zum Monkey Quiz"+"\n\t\t"+"Leben: "+leben+"\n");
                           Zustand = 1;
                           break;

                       case 1:
                           //Ihr im Zustand 1 wird geschaut ob die max Punkte erreicht sind wenn ja dan wird das Program Beendet.
                           // Wenn das nicht der Fall ist läuft das Programm normal weiter.
                           if (points==pointsMax){
                            System.out.println(win);
                            System.exit(0);
                            break;
                           }
                           //frage aus der liste holen
                           aktuelleFrage = Fragenkatalog.getRandomQuestion(init);
                           init = false;
                           System.out.println(aktuelleFrage[0]);
                           Zustand = 2;
                           break;

                       case 2:
                           //Antwormöglichkeiten der akuellen Frage ausgeben
                           printAntworten(aktuelleFrage);
                           Zustand = 3;
                           break;

                       case 3:
                            // Ihr im Zustand 3 wird überprüft ob die gebene Antwort richtig ist
                           if (checkAntwort(aktuelleFrage[5], getUserAntwort())) {
                               Zustand = 1; //True
                               System.out.println("Punkte: "+points+" von "+pointsMax+"\nLeben: "+leben+"\n");
                           } else
                               Zustand = 4; //False
                           break;

                       case 4:
                           //Leben abziehen und schauen ob das Spiel vorbei ist
                           String temp = getGameOver(leben);
                           if (temp.equals("1"))
                               //Wenn Spiel nicht vorbei ist
                               Zustand = 1;
                           else if (temp.equals("2"))
                               //Wenn das Spiel vorbei ist
                               Zustand = 5;
                           break;

                       case 5:
                           System.out.println(lose);
                           System.exit(0);
                       default:
                           throw new IllegalStateException("Unexpected value: "+Zustand);
                   }
               }
           }

        }

        private static void printAntworten(String[] aktuelleFrage) {
            // string[1] bis string[4] sind die antwort möglichkeiten die angezeigt werden
            for( int i = 1; i <= 4; ++i)
                System.out.println(aktuelleFrage[i]);
        }

        private static boolean checkAntwort( String richtigeAntwort, String antwort) {
            if (antwort.equals(richtigeAntwort)){    // überprufung ob antwort richtig ist
                    System.out.println("Richtig");    // Ausgabe das es richtig ist
                    points++;
                    return true;
                }
                else {
                    leben--;
                    System.out.println("Deine Antwort  "+antwort+" war nicht Richtig.");// Ausgabe das es falsch ist
                    System.out.println("Die Richtige Antwort wäre "+richtigeAntwort+"\n"+"Du hast noch "+leben+" Leben\n");
                    return false;
                }
        }

        /*
        * gibt die eingelesene Antwort des Users zurück.
        */
        private static String getUserAntwort() {
            System.out.println("Bitte Antwort wählen: 1,2,3 oder 4");
            String antwort = scanner.nextLine();
            System.out.println("Deine Antwort ist " + antwort);
            return antwort;
        }

        /*
         *Gibt zurück, ob das Spiel vorbei ist oder nicht.
         * @param leben
         * @return
         */
        private static String getGameOver( int leben){
            String playStatus = "1";

            if (leben == 0 )
            {
                System.out.println("Bitte wähle aus:\n 1. noch mal Spielen\n 2. Exit");
                String number = scanner.nextLine();
                System.out.println("Deine wahl war: " +number+"\n"+lose);
                System.exit(0);
            }
            return playStatus;
        }

    }


