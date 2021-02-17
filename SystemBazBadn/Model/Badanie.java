package model;

/**
 *  Klasa reprezentujaca Badanie
 */
public class Badanie {

    private int idBadania;
    private int pesel;
    private String dataBadania;
    private float leukocyty;
    private float erytrocyty;
    private float trombocyty;
    private float monocyty;
    private float limfocyty;

    public Badanie(int PESEL, String dataBadania, float leuk, float e, float  t, float m, float limf){

        this.pesel = PESEL;
        this.dataBadania = dataBadania;
        this.leukocyty = leuk;
        this.erytrocyty = e;
        this.trombocyty = t;
        this.monocyty = m;
        this.limfocyty = limf;
    }

    public int getIdBadania() {
        return idBadania;
    }

    public void setIdBadania(int idBadania) {
        this.idBadania = idBadania;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public String getDataBadania() {
        return dataBadania;
    }

    public void setDataBadania(String dataBadania) {
        this.dataBadania = dataBadania;
    }

    public float getLeukocyty() {
        return leukocyty;
    }

    public void setLeukocyty(float leukocyty) {
        this.leukocyty = leukocyty;
    }

    public float getErytrocyty() {
        return erytrocyty;
    }

    public void setErytrocyty(float erytrocyty) {
        this.erytrocyty = erytrocyty;
    }

    public float getTrombocyty() {
        return trombocyty;
    }

    public void setTrombocyty(float trombocyty) {
        this.trombocyty = trombocyty;
    }

    public float getMonocyty() {
        return monocyty;
    }

    public void setMonocyty(float monocyty) {
        this.monocyty = monocyty;
    }

    public float getLimfocyty() {
        return limfocyty;
    }

    public void setLimfocyty(float limfocyty) {
        this.limfocyty = limfocyty;
    }
}
