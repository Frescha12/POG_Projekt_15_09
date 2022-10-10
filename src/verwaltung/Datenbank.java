package verwaltung;

import java.util.Arrays;
import java.util.Comparator;

public class Datenbank {
    public Auto[] autoDatenbank = new Auto[50];

    public void rueckgabe(UserInterface userInterface) {
        int counter = 0;
        for (Auto auto : autoDatenbank) {
            if (auto != null) {
                userInterface.ausgabe(counter, auto.getId(), auto.getHersteller(), auto.getFarbe(), auto.getPs());
                counter++;
            }
        }
        userInterface.anzahlAusgaben(userInterface, counter);
    }

    public boolean idPruefung(String eingabe) {
        for (Auto auto : autoDatenbank) {
            if (auto != null && (eingabe.equals(auto.getId()))) {
                return false;
            }
        }
        return true;
    }

    public boolean hinzufuegen(String id, String hersteller, String farbe, int ps) {
        for (int i = 0; i < autoDatenbank.length; i++) {
            if (autoDatenbank[i] == null) {
                autoDatenbank[i] = new Auto(id, hersteller, farbe, ps);
                return true;
            }
        }
        return false;
    }

    public void sucheID(UserInterface userInterface, String sucheEingabe) {
        int counter = 0;
        for (Auto auto : autoDatenbank) {
            if (auto != null && auto.getId().contains(sucheEingabe)) {
                counter++;
                userInterface.ausgabe(counter, auto.getId(), auto.getHersteller(), auto.getFarbe(), auto.getPs());
            }
        }
        userInterface.anzahlSuchausgaben(userInterface, counter);
    }

    public void sucheHersteller(UserInterface userInterface, String sucheEingabe) {
        int counter = 0;
        for (Auto auto : autoDatenbank) {
            if (auto != null && auto.getHersteller().contains(sucheEingabe)) {
                counter++;
                userInterface.ausgabe(counter, auto.getId(), auto.getHersteller(), auto.getFarbe(), auto.getPs());
            }
        }
        userInterface.anzahlSuchausgaben(userInterface, counter);
    }

    public void sucheFarbe(UserInterface userInterface, String sucheEingabe) {
        int counter = 0;
        for (Auto auto : autoDatenbank) {
            if (auto != null && auto.getFarbe().contains(sucheEingabe)) {
                counter++;
                userInterface.ausgabe(counter, auto.getId(), auto.getHersteller(), auto.getFarbe(), auto.getPs());
            }
        }
        userInterface.anzahlSuchausgaben(userInterface, counter);
    }

    public void suchePS(UserInterface userInterface, String sucheEingabe) {
        int counter = 0;
        for (Auto auto : autoDatenbank) {
            if (auto != null && Integer.parseInt(sucheEingabe) == auto.getPs()) {
                counter++;
                userInterface.ausgabe(counter, auto.getId(), auto.getHersteller(), auto.getFarbe(), auto.getPs());
            }
        }
        userInterface.anzahlSuchausgaben(userInterface, counter);
    }

    public boolean loeschen(String eingabe) {
        for (Auto value : autoDatenbank) {
            if (value != null && eingabe.equalsIgnoreCase(value.getId())) {
                return true;
            }
        }
        return false;
    }

    public void loeschenEndgueltig(UserInterface userInterface, String eingabe) {
        for (int i = 0; i < autoDatenbank.length; i++) {
            if (autoDatenbank[i] != null && eingabe.equalsIgnoreCase(autoDatenbank[i].getId())) {
                autoDatenbank[i] = null;
                userInterface.loeschenWiederholen(userInterface);
            }
        }
    }

    public int anzahlFreiePlaetzeArray() {
        int counter = 0;
        for (Auto value : autoDatenbank) {
            if (value == null) {
                counter++;
            }
        }
        return counter;
    }

    public void sortieren(UserInterface userInterface) {
        int counter = 0;
        int counter2 = 0;
        String id;
        String hersteller;
        String farbe;
        int ps;
        for (Auto value : autoDatenbank) {
            if (value != null) {
                counter++;
            }
        }
        Auto[] autoDatenbank2 = new Auto[counter];
        for (int i = 0; i < autoDatenbank.length; i++) {
            if (autoDatenbank[i] != null) {
                id = autoDatenbank[i].getId();
                hersteller = autoDatenbank[i].getHersteller();
                farbe = autoDatenbank[i].getFarbe();
                ps = autoDatenbank[i].getPs();
                autoDatenbank2[i] = new Auto(id, hersteller, farbe, ps);
            }
        }
        Arrays.sort(autoDatenbank2, Comparator.comparing(Auto::getHersteller));
        for (Auto auto : autoDatenbank2) {
            counter2++;
            userInterface.ausgabe(counter, auto.getId(), auto.getHersteller(), auto.getFarbe(), auto.getPs());
        }
        userInterface.anzahlAusgaben(userInterface, counter2);
    }
}