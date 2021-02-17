package model;

/**
 *  Klasa reprezentujaca Pacjenta
 */
public class Pacjent {

    private int id;
    private int pesel;
    private String nazwisko;
    private String imie;
    private String plec;
    private int wiek;

    public Pacjent(int PESEL, String nazwisko, String imie, String plec, int wiek){

        this.pesel = PESEL;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.plec = plec;
        this.wiek = wiek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }
}