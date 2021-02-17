package generatorsygnalow;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.data.xy.XYSeries; 

/**Klasa odpowiedzialna za przechowywanie Sygnału
 *
 * @author AK_JR
 */
public class Sygnal {
    
    private double amplituda;
    private double okres;
    private double przesFaz;
    private double czasRoz;
    private double czasZak;
    private int rodzaj; 
    double[] tablicaWartosci;
    double[] tablicaArgumentow;
    double snr;

    /** Bazowy konstruktor klasy Sygnal
     * @param A amplituda    
     * @param T okres
     * @param phi przesuniecie fazowe
     * @param t1 czas rozpoczecia
     * @param t2  czas zakonczenia
     * @param r typ sygnalu, jako int ( 0 - sinus, 1- prostokatny, 2 - trojkatny)
     * @param snr stosunek sygnalu do szumu, snr=(Asyg/Aszum)^2
     */
    public  Sygnal(double A, double T, double phi, double t1, double t2, int r, double snr ){
        
        this.amplituda = A;
        this.okres = T;
        this.przesFaz = phi;
        this.czasRoz = t1;
        this.czasZak = t2;
        this.rodzaj = r;
        int liczbaElementow = 200;
        this.tablicaWartosci = new double[liczbaElementow];
        this.tablicaArgumentow = new double[liczbaElementow];
        this.snr = snr;

        }
    
    /**Metoda do wyliczania wartości przyjmowanych przez wybrany Sygnal do stworzenia wykresu
     * @param liczbaElementow liczba elementow sygnalu
     * @param t1 czas rozpoczecia sygnalu
     * @param t2 czas zakonczenia sygnalu
     * @return baza baza danych do wyswietlenia
     */
    public XYDataset wyliczenieElementow(int liczbaElementow, double t1, double t2 ){
        double Tp = (this.czasZak-this.czasRoz)/liczbaElementow;
        for(int i= 0; i<liczbaElementow; i++){
        this.tablicaArgumentow[i]=this.czasRoz+Tp*i;
        }
        
        XYSeriesCollection baza = new XYSeriesCollection();
        
        //Funkcja nadajaca wartosci elementom tablicaWartosci:
            switch (rodzaj) {
            case 0: { //sinus
                XYSeries wykresSzum = new XYSeries("Sygnał zaszumiony") ;
                XYSeries wykresBrakSzumu = new XYSeries("Idealny sygnał") ;
                    
                Szum s = new Szum(this.amplituda, liczbaElementow, this.snr);

                for(int i= 0; i<liczbaElementow; i++){
                    tablicaWartosci[i]= this.getAmplituda()*Math.sin(2*Math.PI/this.getOkres()*tablicaArgumentow[i]-Math.toRadians(this.getPrzesFaz()));
                    wykresSzum.add(tablicaArgumentow[i],tablicaWartosci[i]+s.getWartosc(i));
                    wykresBrakSzumu.add(tablicaArgumentow[i],tablicaWartosci[i]);
                }
                
                baza.addSeries(wykresBrakSzumu); 
                baza.addSeries(wykresSzum);
                    
                break;
            }
            case 1: { //sygnal prostokatny
                XYSeries wykresSzum = new XYSeries("Sygnał zaszumiony") ;
                XYSeries wykresBrakSzumu = new XYSeries("Idealny sygnał") ;
                Szum s = new Szum(this.amplituda, liczbaElementow, this.snr);

                for(int i= 0; i<liczbaElementow; i++){
                   tablicaWartosci[i] = Math.signum(Math.sin(2*Math.PI/this.getOkres()*tablicaArgumentow[i]))*this.getAmplituda();
  
                    wykresSzum.add(tablicaArgumentow[i],tablicaWartosci[i]+s.getWartosc(i));
                    wykresBrakSzumu.add(tablicaArgumentow[i],tablicaWartosci[i]);
                }
            
                baza.addSeries(wykresBrakSzumu); 
                baza.addSeries(wykresSzum);
                break;
            }
            case 2: { //sygnal trojkatny
                
                XYSeries wykresSzum = new XYSeries("Sygnał zaszumiony") ;
                XYSeries wykresBrakSzumu = new XYSeries("Idealny sygnał") ;
                Szum s = new Szum(this.amplituda, liczbaElementow, this.snr);

                for(int i= 0; i<liczbaElementow; i++){
                  tablicaWartosci[i] = (2*this.getAmplituda()/Math.PI)*Math.asin(Math.sin(2*Math.PI*tablicaArgumentow[i]/this.getOkres()));
                    
                    wykresSzum.add(tablicaArgumentow[i],tablicaWartosci[i]+s.getWartosc(i));
                    wykresBrakSzumu.add(tablicaArgumentow[i],tablicaWartosci[i]);
                }
            
                baza.addSeries(wykresBrakSzumu); 
                baza.addSeries(wykresSzum);
                break;

            }
            default: {
                 System.out.println("Niepoprawny typ sygnalu, blad programu");
                break;
            }
            }
        return baza;
    }
    
    public double getAmplituda() {
        return amplituda;
    }

    public double getOkres() {
        return okres;
    }

    public double getPrzesFaz() {
        return przesFaz;
    }

    public double getCzasRoz() {
        return czasRoz;
    }

    public double getCzasZak() {
        return czasZak;
    }

    public int getRodzaj() {
        return rodzaj;
    }

    public void setAmplituda(double amplituda) {
        this.amplituda = amplituda;
    }

    public void setOkres(double okres) {
        this.okres = okres;
    }

    public void setPrzesFaz(double przesFaz) {
        this.przesFaz = przesFaz;
    }

    public void setCzasRoz(double czasRoz) {
        this.czasRoz = czasRoz;
    }

    public void setCzasZak(double czasZak) {
        this.czasZak = czasZak;
    }

    public void setRodzaj(int rodzaj) {
        this.rodzaj = rodzaj;
    }
}