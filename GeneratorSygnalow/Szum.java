package generatorsygnalow;

import java.util.Random;

/**Klasa przechowujaca Sygnal - Szum
 * 
 * @author AK_JR
 */
public class Szum {
    
    private double AmpSzum;
    private int n;
    private double [] wartosci;
    
    /** Konstruktor klasy Szum
    * @param A amplituda szumu, która będzie zalezna od amplitudy generowanego sygnalu klasy (Sygnal)
    * @param n ilość wyliczanych wartości 
    * @param SNR stosunek sygnalu do szumu (Asygn/Aszum)^2
    */
    public Szum (double A,  int n, double SNR){
        double amp=(A*A)/SNR;
        this.AmpSzum=Math.sqrt(amp);
        this.n = n;
        this.wartosci = new double[n];
        
        for(int i = 0; i < n; i++){
            Random r = new Random();
            this.wartosci[i] = r.nextGaussian()*this.AmpSzum;
        }
    }
    
    /**Metoda, ktora zwraca wartosc dla pojedynczego argumentu szumu
     * @param i numer wybranego argumentu w tablicy
     * @return wartosc dla danego argumentu w tablicy
     */
   public double getWartosc(int i){
       if (i>0){
               if (i<n){
                 return wartosci[i];
               }
        }             
        return 0;
   }

}