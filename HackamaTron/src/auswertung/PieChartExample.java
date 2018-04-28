package auswertung;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;
import java.io.File;
import java.io.IOException;


public class PieChartExample {
	 public static void main(String[] args) throws IOException {
	 // Create a simple pie chart
		 
		 DefaultPieDataset pieDataset = new DefaultPieDataset();
		 
		 pieDataset.setValue("A", new Integer(75));
		 pieDataset.setValue("B", new Integer(10));
		 pieDataset.setValue("C", new Integer(10));
		 pieDataset.setValue("D", new Integer(5));
		 
		 JFreeChart chart = ChartFactory.createPieChart
				 ("CSC408 Mark Distribution", // Title
				 pieDataset, // Dataset
				 true, // Show legend
				 true, // Use tooltips
				 false // Configure chart to generate URLs?
				 );
	 
		 ChartUtilities.saveChartAsJPEG(new File("C:\\test1.jpg"), chart, 500, 300);
	 	
	 }
}
	