package verwaltung;

public class Auto {
    private String id;
    private String hersteller;
    private String farbe;
    private int ps;

    public Auto(String id, String hersteller, String farbe, int ps) {
        this.id = id;
        this.hersteller = hersteller;
        this.farbe = farbe;
        this.ps = ps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }
}