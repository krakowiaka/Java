import kontroler.ZdarzenieGui;
import model.Model;
import widok.WidokGui;

import javax.swing.*;

/**
 * Klasa startujaca aplikacje
 */
public class Main {
    /**
     * Metoda glowna
     * @param args
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                //model
                Model m = new Model();

                m.ConnCreateInsert();

                //widok
                WidokGui w = new WidokGui(m);

                //kontroler
                ZdarzenieGui z = new ZdarzenieGui(w, m);
                w.setController(z);

                w.setVisible(true);
            }
        });
    }
}
