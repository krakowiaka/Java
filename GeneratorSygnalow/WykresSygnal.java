package generatorsygnalow;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**Klasa odpowiedzialana za wyświetlanie powstałych sygnałów na wykresie
 *
 * @author AK_JR
 */
public class WykresSygnal  extends JFrame {
    
    /** Konstruktor klasy WykresSygnal
     * 
     * @param title  tytul okienka wyświetlającego wykres
     * @param database wyswietlane dane
     */
    public WykresSygnal(String title, XYDataset database) {
        super(title);
        JFreeChart wykSygnal = ChartFactory.createXYLineChart(
        "Wygenerowany sygnał",
        "t [s]",
        "A",
        database,
        PlotOrientation.VERTICAL,
        true, true, false);
        ChartPanel chartPanel = new ChartPanel(wykSygnal);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        final XYPlot plot = wykSygnal.getXYPlot();
        
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.BLACK );
      renderer.setSeriesPaint( 1 , new Color(61, 84, 240) );
      renderer.setSeriesStroke( 0 , new BasicStroke(1.5f) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 1.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
      
      //możliowść zapisu powstałego wykresu
      try{
          ChartUtils.saveChartAsPNG(new File("wykresAnalizy.png"), wykSygnal, 800, 600);
          
      }catch (IOException e){
          JOptionPane.showMessageDialog(null,"Blad z zapisem");
      }
  
    }
}