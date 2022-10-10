package verwaltung;

import java.util.Scanner;

public class UserInterface {
    Scanner sc = new Scanner(System.in);
    Datenbank datenbank = new Datenbank();

    public void hauptmenue(UserInterface userInterface) {
        //"""
        System.out.println("""
                \n
                ------------------------------
                (R)ückgabe aller Datensätze
                (H)inzufügen eines neuen Datensatzes
                H(i)nzufügen mehrerer neuer Datensätze
                (S)uche nach Datensätzen
                (L)öschen eines Datensatzes
                S(o)rtierte Ausgabe der Datensätze alphabetisch nach Hersteller
                (B)eenden
                ------------------------------
                """);

        String eingabe = sc.nextLine();
        auswertungHauptmenueEingabe(userInterface, eingabe);
    }

    public void auswertungHauptmenueEingabe(UserInterface userInterface, String eingabe) {
        if (eingabe.equalsIgnoreCase("R")) {
            datenbank.rueckgabe(userInterface);
        } else if (eingabe.equalsIgnoreCase("H")) {
            if(datenbank.anzahlFreiePlaetzeArray() > 0) {
                hinzufuegenId(userInterface, false);
            }
            else{
                System.out.println("Die Datenbank ist bereits voll.");
                hauptmenue(userInterface);
            }
        } else if (eingabe.equalsIgnoreCase("I")) {
            if(datenbank.anzahlFreiePlaetzeArray() > 0) {
                anzahlHinzufuegen(userInterface);
            }
            else{
                System.out.println("Die Datenbank ist bereits voll.");
                hauptmenue(userInterface);
            }
        } else if (eingabe.equalsIgnoreCase("S")) {
            sucheMenu(userInterface);
        } else if (eingabe.equalsIgnoreCase("L")) {
            loeschen(userInterface);
        } else if (eingabe.equalsIgnoreCase("O")) {
            System.out.println("\nDatenbankeinträge werden nach hersteller sortiert.");
            datenbank.sortieren(userInterface);
        } else if (eingabe.equalsIgnoreCase("B")) {
            System.out.println("\nDas Programm wird beendet.");
            System.exit(0);
        } else {
            System.out.println("\nDies ist keine gültige Eingabe.");
            hauptmenue(userInterface);
        }
    }

    public void ausgabe(int pos, String id, String hersteller, String farbe, int ps) {
        System.out.println((pos+1) + ".  ID: " + id + "  Hersteller: " + hersteller + "  Farbe: " + farbe + "  PS: " + ps);
    }

    public void anzahlAusgaben(UserInterface userInterface, int counter) {
        if (counter == 0) {
            System.out.println("\nEnde der Ausgabe.\n" + counter + " Ergebnisse werden angezeigt!");
        } else if (counter == 1) {
            System.out.println("\nEnde der Ausgabe.\n" + counter + " Ergebnis wird angezeigt!");
        } else {
            System.out.println("\nEnde der Ausgabe.\n" + counter + " Ergebnisse werden angezeigt!");
        }
        hauptmenue(userInterface);
    }

    public void loeschen(UserInterface userInterface) {
        System.out.println("\nBitte die ID des zu löschenden Fahrzeuges eingeben.");
        String eingabe = sc.nextLine();
        if (datenbank.loeschen(eingabe)) {
            System.out.println("Wollen Sie den Datensatz mit der ID: " + eingabe + " wirklich löschen?\n(J)a / (N)ein");
            String eingabe2 = sc.nextLine();
            if (eingabe2.equalsIgnoreCase("J")) {
                datenbank.loeschenEndgueltig(userInterface, eingabe);
            } else {
                System.out.println("Es wurde kein Datensatz gelöscht.");
                loeschenWiederholen(userInterface);
            }
        } else {
            System.out.println("Die eingegebene ID ist nicht vorhanden.");
            loeschenWiederholen(userInterface);
        }
    }

    public void loeschenWiederholen(UserInterface userInterface) {
        System.out.println("Möchten Sei einen weiteren Datensatz löschen?\n(J)a / (N)ein");
        String eingabe = sc.nextLine();
        if (eingabe.equalsIgnoreCase("J")) {
            loeschen(userInterface);
        } else {
            System.out.println("Zurück zum Hauptmenü.");
            hauptmenue(userInterface);
        }
    }

    public void sucheMenu(UserInterface userInterface) {
        System.out.println("""
                \n------------------------------
                Suche nach (I)D
                Suche nach (H)ersteller
                Suche nach (F)arbe
                Suche nach (P)S
                Suche (B)eenden
                ------------------------------
                """);
        String sucheMenuAuswahl = sc.nextLine();
        auswertungMenueSuchenEingabe(userInterface, sucheMenuAuswahl);
    }

    public void auswertungMenueSuchenEingabe(UserInterface userInterface, String sucheMenuAuswahl) {
        if (sucheMenuAuswahl.equalsIgnoreCase("I")) {
            sucheID(userInterface);
        } else if (sucheMenuAuswahl.equalsIgnoreCase("H")) {
            sucheHersteller(userInterface);
        } else if (sucheMenuAuswahl.equalsIgnoreCase("F")) {
            sucheFarbe(userInterface);
        } else if (sucheMenuAuswahl.equalsIgnoreCase("P")) {
            suchePS(userInterface);
        } else if (sucheMenuAuswahl.equalsIgnoreCase("B")) {
            hauptmenue(userInterface);
        } else {
            System.out.println("Dies ist keine gültige Eingabe.");
            sucheMenu(userInterface);
        }
    }

    public void sucheID(UserInterface userInterface) {
        System.out.println("\nBitte die zu suchende ID eingeben!");
        String sucheEingabe = sc.nextLine();
        System.out.println("Eingabe wird geprüft.");
        datenbank.sucheID(userInterface, sucheEingabe);
    }

    public void sucheHersteller(UserInterface userInterface) {
        System.out.println("\nBitte die zu suchenden Hersteller eingeben!");
        String sucheEingabe = sc.nextLine();
        System.out.println("Eingabe wird geprüft.");
        datenbank.sucheHersteller(userInterface, sucheEingabe);
    }

    public void sucheFarbe(UserInterface userInterface) {
        System.out.println("\nBitte die zu suchende Farbe eingeben!");
        String sucheEingabe = sc.nextLine();
        System.out.println("Eingabe wird geprüft.");
        datenbank.sucheFarbe(userInterface, sucheEingabe);
    }

    public void suchePS(UserInterface userInterface) {
        System.out.println("\nBitte die zu suchenden PS eingeben!");
        String sucheEingabe = sc.nextLine();
        System.out.println("Eingabe wird geprüft.");
        if (pruefungGanzzahl(sucheEingabe) && pruefungAufPositiveZahl(sucheEingabe)) {
            datenbank.suchePS(userInterface, sucheEingabe);
        } else {
            System.out.println("Die Suche nach PS kann nur als positiver Zahlenwert eroflgen.");
            suchePS(userInterface);
        }
    }

    public void anzahlSuchausgaben(UserInterface userInterface, int counter) {
        if (counter == 0) {
            System.out.println("\nEnde der Suchausgabe.\n" + counter + " Ergebnisse werden angezeigt!");
        } else if (counter == 1) {
            System.out.println("\nEnde der Suchausgabe.\n" + counter + " Ergebnis wird angezeigt!");
        } else {
            System.out.println("\nEnde der Suchausgabe.\n" + counter + " Ergebnisse werden angezeigt!");
        }
        sucheMenu(userInterface);
    }

    public boolean pruefungAufInhalt(String eingabe) {
        return !eingabe.equals("");
    }

    public boolean pruefungAufInhalt2(String eingabe) {
        char firstChar = eingabe.charAt(0);
        return firstChar != ' ';
    }

    public boolean pruefungGanzzahl(String eingabe) {
        try {
            Integer.parseInt(eingabe);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean pruefungAufPositiveZahl(String eingabe) {
        return eingabe != null && eingabe.matches("[+-]?\\d*(\\.\\d+)?") && Integer.parseInt(eingabe) > 0;
    }

    public void hinzufuegen(UserInterface userInterface) {
        System.out.println("Möchten Sie mehrere Datensätze eintragen?\n(J)a / (N)ein");
        String eingabe = sc.nextLine();
        if (eingabe.equalsIgnoreCase("J")) {
            anzahlHinzufuegen(userInterface);
        } else {
            hinzufuegenId(userInterface,false);
        }
    }

    public void anzahlHinzufuegen(UserInterface userInterface) {
        int counter = 0;
        System.out.println("\nWie viele Datensätze möchten Sie eintragen?");
        String eingabeAnzahl = sc.nextLine();
        if (pruefungAufInhalt(eingabeAnzahl)) {
            if (pruefungGanzzahl(eingabeAnzahl)) {
                if (pruefungAufPositiveZahl(eingabeAnzahl)) {
                    if (Integer.parseInt(eingabeAnzahl) <= datenbank.anzahlFreiePlaetzeArray()) {
                        for (int i = 0; i < Integer.parseInt(eingabeAnzahl); i++) {
                            if (i != 0 && i < Integer.parseInt(eingabeAnzahl)) {
                                System.out.println("\nNächsten Datensatz eintragen?\n(J)a / (N)ein");
                                String eingabe2 = sc.nextLine();
                                if (!(eingabe2.equalsIgnoreCase("J"))) {
                                    anzahlEingetragenerDatensaezte(userInterface, counter);
                                    System.out.println("Zurück zum Hauptmenü");
                                    hauptmenue(userInterface);
                                    break;
                                }
                            }
                            hinzufuegenId(userInterface, true);
                            counter++;
                        }
                        anzahlEingetragenerDatensaezte(userInterface, counter);
                    } else {
                        System.out.println("Es sind nur noch " + datenbank.anzahlFreiePlaetzeArray() + " freie Plätze in der Datenbank vorhanden.");
                        anzahlHinzufuegen(userInterface);
                    }
                } else {
                    System.out.println("Die Eingabe muss größer 0 sein.");
                    anzahlHinzufuegen(userInterface);
                }

            } else {
                System.out.println("Die Eingabe muss eine Ganzzahl sein.\n");
                anzahlHinzufuegen(userInterface);
            }
        } else {
            System.out.println("Die Eingabe darf nicht leer sein.\n");
            anzahlHinzufuegen(userInterface);
        }
    }

    public void anzahlEingetragenerDatensaezte(UserInterface userInterface, int anzahl) {
        if (anzahl == 1) {
            System.out.println("\nEs wurde " + anzahl + " Datensatz in die Datenbank eingetragen.");
        } else {
            System.out.println("\nEs wurden " + anzahl + " Datensätze in die Datenbank eingetragen.");
        }
        hauptmenue(userInterface);
    }

    public void hinzufuegenId(UserInterface userInterface, boolean mehrereDatensaetze) {
        System.out.println("\nBitte tragen Sie die ID des Fahrzeuges ein.");
        String id = sc.nextLine();
        if (pruefungAufInhalt(id)) {
            if (pruefungAufInhalt2(id)) {
                if (datenbank.idPruefung(id)) {
                    hinzufuegenHersteller(userInterface, mehrereDatensaetze, id);
                } else {
                    System.out.println("Die eingegebene ID ist bereits vorhanden. Bitte tragen Sie eine andere ID ein.");
                    hinzufuegenId(userInterface, mehrereDatensaetze);
                }
            } else {
                System.out.println("Das Eingabefeld darf nicht mit einem Leerzeichen beginnen.");
                hinzufuegenId(userInterface, mehrereDatensaetze);
            }
        } else {
            System.out.println("Das Eingabefeld darf nicht leer sein.");
            hinzufuegenId(userInterface, mehrereDatensaetze);
        }
    }

    public void hinzufuegenHersteller(UserInterface userInterface, boolean mehrereDatensaetze, String id) {
        System.out.println("\nBitte den Hersteller des Fahrzeugs eintragen");
        String hersteller = sc.nextLine();
        if (pruefungAufInhalt(hersteller)) {
            if (pruefungAufInhalt2(hersteller)) {
                hinzufuegenFarbe(userInterface, mehrereDatensaetze, id, hersteller);
            } else {
                System.out.println("Das Eingabefeld darf nicht mit einem Leerzeichen beginnen.");
                hinzufuegenHersteller(userInterface, mehrereDatensaetze, id);
            }
        } else {
            System.out.println("Das Eingabefeld darf nicht leer sein.");
            hinzufuegenHersteller(userInterface, mehrereDatensaetze, id);
        }
    }

    public void hinzufuegenFarbe(UserInterface userInterface, boolean mehrereDatensaetze, String id, String hersteller) {
        System.out.println("\nBitte die Farbe des Fahrzeuges eintragen");
        String farbe = sc.nextLine();
        if (pruefungAufInhalt(farbe)) {
            if (pruefungAufInhalt2(farbe)) {
                hinzufuegenPs(userInterface, mehrereDatensaetze, id, hersteller, farbe);
            } else {
                System.out.println("Das Eingabefeld darf nicht mit einem Leerzeichen beginnen.");
                hinzufuegenFarbe(userInterface, mehrereDatensaetze, id, farbe);
            }
        } else {
            System.out.println("Das Eingabefeld darf nicht leer sein.");
            hinzufuegenFarbe(userInterface, mehrereDatensaetze, id, hersteller);
        }
    }

    public void hinzufuegenPs(UserInterface userInterface, boolean mehrereDatensaetze, String id, String hersteller, String farbe) {
        System.out.println("\nBitte die PS des Fahrzeuges eintragen.");
        String ps = sc.nextLine();
        if (pruefungAufInhalt(ps)) {
            if (pruefungAufInhalt2(ps)) {
                if (pruefungGanzzahl(ps)) {
                    if (pruefungAufPositiveZahl(ps)) {
                        if (datenbank.hinzufuegen(id, hersteller, farbe, Integer.parseInt(ps))) {
                            System.out.println("\nDer Datensatz wurde erfolgreich angelegt.");
                            if (!mehrereDatensaetze) {
                                hinzufuegenWiederholen(userInterface);
                            }
                        } else {
                            System.out.println("Eingabe war nicht erfolgreich!");
                            hauptmenue(userInterface);
                        }
                    } else {
                        System.out.println("Die Eingabe muss größer 0 sein.");
                        hinzufuegenPs(userInterface, mehrereDatensaetze, id, hersteller, farbe);
                    }
                } else {
                    System.out.println("Die Eingabe muss als Ganzzahl erfolgen");
                    hinzufuegenPs(userInterface, mehrereDatensaetze, id, hersteller, farbe);
                }
            } else {
                System.out.println("Das Eingabefeld darf nicht mit einem Leerzeichen beginnen.");
                hinzufuegenPs(userInterface, mehrereDatensaetze, id, hersteller, farbe);
            }
        } else {
            System.out.println("Das Eingabefeld darf nicht leer sein.");
            hinzufuegenPs(userInterface, mehrereDatensaetze, id, hersteller, farbe);
        }
    }

    public void hinzufuegenWiederholen(UserInterface userInterface) {
        System.out.println("\nMöchten Sie weitere Datensätze eingeben?\n(J)a / (N)ein");
        String eingabe = sc.nextLine();
        if (eingabe.equalsIgnoreCase("J")) {
            hinzufuegen(userInterface);
        } else {
            System.out.println("Zurück zum Hauptmenü");
            hauptmenue(userInterface);
        }
    }
}