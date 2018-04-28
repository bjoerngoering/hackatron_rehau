package auswertung;

import java.util.ArrayList;
import java.util.Date;
import data_repository.Data;

public class DataAnalysis {

  private ArrayList<Data> data_list_first;
  private ArrayList<Data> data_list_second;
  
  public DataAnalysis(ArrayList<Data> d1, ArrayList<Data> d2) {
    data_list_first = d1;
    data_list_second = d2;
  }
  
  public void bad_humidity() {
    for (Data d : data_list_first) {
      if (d.roomhumidity[3] > 48.0) {
        Date e = new Date(d.timestamp);
        System.out.println(e.toString()+": " + d.roomhumidity[3]);
      }
    }
    System.out.println("SECOND");
    for (Data d : data_list_second) {
      if (d.roomhumidity[3] > 48.0) {
        Date e = new Date(d.timestamp);
        System.out.println(e.toString()+": " + d.roomhumidity[3]);
      }
    }
  }
  
}
