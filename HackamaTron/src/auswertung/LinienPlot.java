
package auswertung;

import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;

public class LinienPlot {

  public static double[][] gibWerte() { // Nur zu Testzwecken
    double[][] eingabe = { { 0.43, 3.43 }, { 3.343, 6.54 } };
    return eingabe;
  }

  public static DefaultXYDataset erstelleDaten(double[][] name) { // zweidimensionales Array wird in passenden Datentyp
                                                                  // umgewandelt
    DefaultXYDataset dataset = new DefaultXYDataset();
    dataset.addSeries("Messwerte", name);
    return dataset;
  }

  public static void erstellePlotKonsole(DefaultXYDataset data) { // erstellt einen Plot auf der Konsole
    XYDotRenderer dot = new XYDotRenderer();
    dot.setDotHeight(5);
    dot.setDotWidth(5);
    NumberAxis xax = new NumberAxis("[Zeit]");
    NumberAxis yax = new NumberAxis("[Einheit]");
    XYPlot plot = new XYPlot(data, xax, yax, dot);
    JFreeChart chart2 = new JFreeChart("test", plot);

    // Erstellen eines Ausgabefensters
    ApplicationFrame punkteframe = new ApplicationFrame("Punkte"); // "Punkte" entspricht der Ueberschrift des Fensters
    ChartPanel chartPanel2 = new ChartPanel(chart2);
    punkteframe.setContentPane(chartPanel2);
    punkteframe.pack();
    punkteframe.setVisible(true);
  }

  public static void erstellePlotPNG(DefaultXYDataset data) throws IOException {
    XYDotRenderer dot = new XYDotRenderer();
    dot.setDotHeight(5);
    dot.setDotWidth(5);
    NumberAxis xax = new NumberAxis("x");
    NumberAxis yax = new NumberAxis("y");
    XYPlot plot = new XYPlot(data, xax, yax, dot);
    JFreeChart chart = new JFreeChart("test", plot);
    ChartUtilities.saveChartAsPNG(new File("/Users/meandmyself/Desktop/hackatron/charts/1.png"), chart, 10000, 1000);

  }
}
