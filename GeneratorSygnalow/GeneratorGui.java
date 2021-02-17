package generatorsygnalow;

import java.awt.*;
import java.awt. event.*;
import javax.swing.*;

/**Klasa GeneratorGui obsługuje cały interfejs użytkownika
 *
 * @author AK_JR
 */
public class GeneratorGui extends JFrame implements ActionListener, ItemListener{

    JPanel wybor;
    
    // opcje wyboru generowanego sygnału
    final static String OPCJE = "Wybierz opcje";
    final static String SYGNAPROSTO = "Sygnał prostokątny";
    final static String SYGNASIN = "Sygnał sinus";
    final static String SYGNATROJ = "Sygnał trójkątny";
    final static String RYSUNEK = "Generowany sygnał";
    
    // tworzenie obiektów do pobierania informacji - JField-pole tekstowe, JButton-przycisk
    private JTextField ampProsto, ampTroj, ampSin; 
    private JTextField okrProsto, okrTroj, okrSin; 
    private JTextField czRozProsto, czRozTroj, czRozSin;
    private JTextField czZakProsto, czZakTroj, czZakSin;
    private JTextField snrProsto, snrTroj, snrSin;
    private JTextField przesFazSin;
    private JButton zatProsto, zatTroj, zatSin, wyjscie;
    
    //deklaracja zmiennych potrzebnych do pobierania informacji z GUI
    private double ampSyg;
    private double okresSyg;
    private double przesFazSyg;
    private double czasRozSyg;
    private double czasZakSyg;
    private int rodzaj;   
    private double snrSyg;
    
    /**Gui przy użyciu CardLayout
     * 
     * @param w kontener przechowujący komponenty
     */
    public void dodOpcjiDoPanelu(Container w){
                
        //tworzenie listy wyboru 
        JPanel PanelListaWyboru = new JPanel();
        String listaWyboru[] = {OPCJE, SYGNAPROSTO, SYGNASIN, SYGNATROJ};
        JComboBox lista = new JComboBox(listaWyboru);
        lista.setEditable(false);
        lista.addItemListener(this);
        PanelListaWyboru.add(lista);
        
        //tworzenie poszczegolnych panelów (dla Sygnalow)
        
        JPanel wyborOpcji = new JPanel();
         
        //Panel dla sygnału Prostokątnego
        JPanel sygProsto = new JPanel(new GridLayout(0,3));
        
        JLabel ampP = new JLabel("Amplituda:");
        this.ampProsto = new JTextField();
        JLabel jed1P = new JLabel("");
        sygProsto.add(ampP);
        sygProsto.add(this.ampProsto);
        sygProsto.add(jed1P);
        
        JLabel okP = new JLabel("Okres sygnału:");
        this.okrProsto = new JTextField();
        JLabel jed2P = new JLabel("s");
        sygProsto.add(okP);
        sygProsto.add(this.okrProsto);
        sygProsto.add(jed2P);
        
        JLabel czasRP = new JLabel("Czas rozpoczęcia sygnału:");
        this.czRozProsto = new JTextField();
        JLabel jed3P = new JLabel("s");
        sygProsto.add(czasRP);
        sygProsto.add(this.czRozProsto);
        sygProsto.add(jed3P);
        
        JLabel czasZP = new JLabel("Czas zakończenia sygnału:");
        this.czZakProsto = new JTextField();
        JLabel jed4P = new JLabel("s");
        sygProsto.add(czasZP);
        sygProsto.add(czZakProsto);
        sygProsto.add(jed4P);
        
        JLabel snrP = new JLabel("Stosunek sygnalu do szumu:");
        this.snrProsto = new JTextField();
        sygProsto.add(snrP);
        sygProsto.add(this.snrProsto);
        sygProsto.add(new JLabel(""));

        this.zatProsto = new JButton("Zatwierdź");
        sygProsto.add(this.zatProsto);
        this.zatProsto.addActionListener(this);
        
        //Panel dla sygnału Sinusa
        JPanel sygSin = new JPanel(new GridLayout(0,3)); 
      
        JLabel ampS = new JLabel("Amplituda:");
        this.ampSin = new JTextField();
        JLabel jed1S = new JLabel("");
        sygSin.add(ampS);
        sygSin.add(this.ampSin);
        sygSin.add(jed1S);
        
        JLabel okS = new JLabel("Okres sygnału:");
        this.okrSin = new JTextField();
        JLabel jed2S = new JLabel("s");
        sygSin.add(okS);
        sygSin.add(this.okrSin);
        sygSin.add(jed2S);
        
        JLabel czasRS = new JLabel("Czas rozpoczęcia sygnału:");
        this.czRozSin = new JTextField();
        JLabel jed3S = new JLabel("s");
        sygSin.add(czasRS);
        sygSin.add(this.czRozSin);
        sygSin.add(jed3S);
        
        JLabel czasZS = new JLabel("Czas zakończenia sygnału:");
        this.czZakSin  = new JTextField();
        JLabel jed4S = new JLabel("s");
        sygSin.add(czasZS);
        sygSin.add(this.czZakSin);
        sygSin.add(jed4S);
        
        JLabel przesFazS = new JLabel("Przesunięcie fazowe:");
        this.przesFazSin = new JTextField();
        JLabel jed6S = new JLabel("\u00B0");
        sygSin.add(przesFazS);
        sygSin.add(this.przesFazSin);
        sygSin.add(jed6S);
        
        JLabel snrS = new JLabel("Stosunek sygnalu do szumu:");
        this.snrSin = new JTextField();
        JLabel jed7S = new JLabel("");
        sygSin.add(snrS);
        sygSin.add(this.snrSin);
        sygSin.add(jed7S);
        
        this.zatSin = new JButton("Zatwierdź");
        sygSin.add(this.zatSin);
        this.zatSin.addActionListener(this);
         
        
        //Panel dla sygnału Trójkątnego
        JPanel sygTroj = new JPanel(new GridLayout(0,3));
        
        JLabel ampT = new JLabel("Amplituda:");
        this.ampTroj = new JTextField();
        JLabel jed1T = new JLabel("");
        sygTroj.add(ampT);
        sygTroj.add(this.ampTroj);
        sygTroj.add(jed1T);
        
        JLabel okT = new JLabel("Okres sygnału:");
        this.okrTroj = new JTextField();
        JLabel jed2T = new JLabel("s");
        sygTroj.add(okT);
        sygTroj.add(this.okrTroj);
        sygTroj.add(jed2T);
        
        JLabel czasRT = new JLabel("Czas rozpoczęcia sygnału:");
        this.czRozTroj = new JTextField();
        JLabel jed3T = new JLabel("s");
        sygTroj.add(czasRT);
        sygTroj.add(this.czRozTroj);
        sygTroj.add(jed3T);
        
        JLabel czasZT = new JLabel("Czas zakończenia sygnału:");
        this.czZakTroj = new JTextField();
        JLabel jed4T = new JLabel("s");
        sygTroj.add(czasZT);
        sygTroj.add(czZakTroj);
        sygTroj.add(jed4T);
        
        JLabel snrT = new JLabel("Stosunek sygnalu do szumu:");
        this.snrTroj = new JTextField();
        sygTroj.add(snrT);
        sygTroj.add(this.snrTroj);
        sygTroj.add(new JLabel(""));
        this.zatTroj = new JButton("Zatwierdź");
        sygTroj.add(this.zatTroj);
        this.zatTroj.addActionListener(this);
        
        
        wybor = new JPanel(new CardLayout());
        wybor.add(wyborOpcji, OPCJE);
        wybor.add(sygProsto,SYGNAPROSTO);
        wybor.add(sygSin, SYGNASIN);
        wybor.add(sygTroj, SYGNATROJ);
                
        w.add(PanelListaWyboru, BorderLayout.PAGE_START);
        w.add(wybor, BorderLayout.CENTER);
    }
    /**
    * Metoda odpowiedzialana za wszystkie zdarzenia aplikacji
    */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.zatProsto){
            try{
                System.out.println("Sygnał Prostokątny");
                String AString = this.ampProsto.getText();
                ampSyg = Double.parseDouble(AString);
                String OString = this.okrProsto.getText();
                okresSyg = Double.parseDouble(OString);
                String CRString = this.czRozProsto.getText();
                czasRozSyg = Double.parseDouble(CRString);
                String CZString = this.czZakProsto.getText();
                czasZakSyg = Double.parseDouble(CZString);
                rodzaj = 1;
                String snrString = this.snrProsto.getText();
                snrSyg = Double.parseDouble(snrString);
                if (czasZakSyg<=czasRozSyg){
                    JOptionPane.showMessageDialog(null,"Niepoprawna wartosc: czas zakonczenia musi byc wiekszy niz czas rozpoczecia");
                    
                }
                else if (!(snrSyg>0))
                {
                     JOptionPane.showMessageDialog(null,"Niepoprawna wartosc: SNR musi byc dodatnie");                   
                }
                else if(!(okresSyg>0)){
                 JOptionPane.showMessageDialog(null,"Niepoprawna wartosc: okres sygnalu musi byc dodatni");                      
                }
                else{
                Sygnal SygCzysty = new Sygnal(this.ampSyg, this.okresSyg, 0.0,
                   this.czasRozSyg, this.czasZakSyg, this.rodzaj, this.snrSyg);
             
                WykresSygnal sygnal = new WykresSygnal("Analiza Sygnału Prostokątnego",
                        SygCzysty.wyliczenieElementow(200, this.czasRozSyg, this.czasZakSyg));
                sygnal.pack();         
                sygnal.setVisible(true);
                }
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Blad podczas wprowadzania danych, w kazdym polu wprowadz dane liczbowe");
            }
        }
        else if (ae.getSource() == this.zatSin){
            try{
                System.out.println("Sygnał Sinusa");
                String AString = this.ampSin.getText();
                ampSyg = Double.parseDouble(AString);
                String OString = this.okrSin.getText();
                okresSyg = Double.parseDouble(OString);
                String CRString = this.czRozSin.getText();
                czasRozSyg = Double.parseDouble(CRString);
                String CZString = this.czZakSin.getText();
                czasZakSyg = Double.parseDouble(CZString);
                String PFString = this.przesFazSin.getText();
                przesFazSyg = Double.parseDouble(PFString);
                rodzaj = 0;
                String snrString = this.snrSin.getText();
                snrSyg = Double.parseDouble(snrString);
                 
                if (czasZakSyg<=czasRozSyg){
                     JOptionPane.showMessageDialog(null,"Niepoprawna wartosc: czas zakonczenia musi byc wiekszy niz czas rozpoczecia");

                }
                else if (!(snrSyg>0))
                {
                     JOptionPane.showMessageDialog(null,"Niepoprawna wartosc: SNR musi byc dodatnie");                   
                }
                else if(!(okresSyg>0)){
                    JOptionPane.showMessageDialog(null,"Niepoprawna wartosc: okres sygnalu musi byc dodatni");                      
                }
                else{
                Sygnal SygCzysty = new Sygnal(this.ampSyg, this.okresSyg, this.przesFazSyg, 
                    this.czasRozSyg, this.czasZakSyg, this.rodzaj, this.snrSyg);
                WykresSygnal sygnal = new WykresSygnal("Analiza Syganłu Sinusoidalnego",
                        SygCzysty.wyliczenieElementow(200, this.czasRozSyg, this.czasZakSyg) );
                sygnal.pack();         
                sygnal.setVisible(true);
                }
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Blad podczas wprowadzania danych, wprowadz dane liczbowe");
            }
        }
        else if (ae.getSource() == this.zatTroj){
            try{
                String AString = this.ampTroj.getText();
                ampSyg = Double.parseDouble(AString);
                String OString = this.okrTroj.getText();
                okresSyg = Double.parseDouble(OString);
                String CRString = this.czRozTroj.getText();
                czasRozSyg = Double.parseDouble(CRString);
                String CZString = this.czZakTroj.getText();
                czasZakSyg = Double.parseDouble(CZString);;
                rodzaj = 2;
                String snrString = this.snrTroj.getText();
                snrSyg = Double.parseDouble(snrString);
                
                if (czasZakSyg<=czasRozSyg){
                    JOptionPane.showMessageDialog(null,"Niepoprawna wartosc: czas zakonczenia musi byc wiekszy niz czas rozpoczecia");
                    
                }
                else if (!(snrSyg>0))
                {
                     JOptionPane.showMessageDialog(null,"Niepoprawna wartosc: SNR musi byc dodatnie");                   
                }
                else if(!(okresSyg>0)){
                 JOptionPane.showMessageDialog(null,"Niepoprawna wartosc: okres sygnalu musi byc dodatni");                      
                }
                
                else{
                Sygnal SygCzysty = new Sygnal(this.ampSyg, this.okresSyg, 0.0, 
                                      this.czasRozSyg, this.czasZakSyg, this.rodzaj, this.snrSyg);
                WykresSygnal sygnal = new WykresSygnal("Analiza Syganłu Trojkatnego",
                                        SygCzysty.wyliczenieElementow(200, this.czasRozSyg, this.czasZakSyg) );
            
                sygnal.pack();         
                sygnal.setVisible(true);
                }
            }
            catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Blad podczas wprowadzania danych, wprowadz dane liczbowe");
            }
        }
    }
    /**
    * Metoda, która ,,wysłuchuje" jaki sygnał został wybrany
    */
    @Override
    public void itemStateChanged(ItemEvent ie) {
        CardLayout cl = (CardLayout)(wybor.getLayout());
        cl.show(wybor, (String)ie.getItem());
    }
    /**
     * Metoda odpowiedzialna za tworzenie interfejsu
     */
    public static void tworzenieInterfejsu(){
        JFrame frame = new JFrame("GeneratorSygnałów");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GeneratorGui gs = new GeneratorGui();
        gs.dodOpcjiDoPanelu(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }    

    public double getOkresSyg() {
        return okresSyg;
    }

    public void setOkresSyg(double okresSyg) {
        this.okresSyg = okresSyg;
    }

    public double getCzasRozSyg() {
        return czasRozSyg;
    }

    public void setCzasRozSyg(double czasRozSyg) {
        this.czasRozSyg = czasRozSyg;
    }

    public double getCzasZakSyg() {
        return czasZakSyg;
    }

    public void setCzasZakSyg(double czasZakSyg) {
        this.czasZakSyg = czasZakSyg;
    }
    
}
