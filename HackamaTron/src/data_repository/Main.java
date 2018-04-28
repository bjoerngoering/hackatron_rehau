
package data_repository;

import java.io.*;
import java.util.*;
import org.json.*;
import auswertung.*;

@SuppressWarnings("unused")
public class Main {

  public static ArrayList<Data> data_list_first  = new ArrayList<Data>();
  public static ArrayList<Data> data_list_second = new ArrayList<Data>();

  public static void main(String[] args) throws Exception {

    Data.parser(data_list_first, data_list_second);
    
    DataAnalysis analyse = new DataAnalysis(data_list_first, data_list_second);
    analyse.bad_humidity();

//    double[][] plot = new double[2][data_list_first.size()];
//    for (int i = 0; i < data_list_first.size(); ++i) {
//      plot[0][i] = i; // System.currentTimeMillis()-data_list_first.get(i).timestamp;
//      plot[1][i] = data_list_second.get(i).roomhumidity[3];
//    }
//    auswertung.LinienPlot.erstellePlotPNG(auswertung.LinienPlot.erstelleDaten(plot));

    
  }

  

}
