package generatorsygnalow;

/** Klasa glowna uruchamiajaca program
 *
 * @author AK_JR
 */
public class GeneratorSygnalow {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         gui();
        }
    
    /**
     * Metoda umożliwiająca wywołanie Gui
     */
    public static void gui(){
        GeneratorGui.tworzenieInterfejsu();
    }
}
