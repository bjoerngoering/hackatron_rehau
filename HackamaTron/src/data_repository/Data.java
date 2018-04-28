
package data_repository;

import java.io.*;

import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;



public class Data {

  public enum type{
    TIMESTAMP,
    ROOMTEMPERATURE,
    ROOMHUMIDITY,
    PRESSURE,
    CO2,
    PM25,
    PM100,
    COMFORT,
    VOC
  }
  
  
  private static String device_id_first  = "4b893152317ebb55bdeefa238b1db9fff0d77d825b841947d05486727f451227";
  private static String device_id_second = "207e2c464c496fe1bc0869554dbb6b414bcf67d22924bf5bee752628032ea7f9";

  public long   timestamp;
  public double roomtemperature[];
  public double roomhumidity[];
  public double pressure[];
  public double co2[];
  public double pm25[];
  public double pm100[];
  public double comfort[];
  public double voc[];

  Data() {
    /*
     * 0 = min 1 = median 2 = max 3 = mean
     */
    roomtemperature = new double[4];
    roomhumidity = new double[4];
    pressure = new double[4];
    co2 = new double[4];
    pm25 = new double[4];
    pm100 = new double[4];
    voc = new double[4];
    comfort = new double[4];
  }
  
 // public get_date(type t, )

  public String to_string() {
    String ret = "Room temp: min:" + roomtemperature[0] + " max: " + roomtemperature[1];
    return ret;
  }

  public static long start_timer() {
    return System.currentTimeMillis();
  }

  public static void stop_timer(long start) {
    System.out.println("Time was: " + (System.currentTimeMillis() - start));
  }

  public static void parser(ArrayList<Data> data_list_first, ArrayList<Data> data_list_second) {

    File f = new File("../../resource");
    long time = start_timer();
    File[] files = f.listFiles();
    try {
      if (files != null) { // Erforderliche Berechtigungen etc. sind vorhanden
        for (int i = 0; i < files.length; ++i) {
          BufferedReader br = new BufferedReader(new FileReader(files[i]));
          String zeile = null;
          if (files[i].getAbsoluteFile().getName().contains(device_id_first) && data_list_first != null) {
            while ((zeile = br.readLine()) != null) {
              data_list_first.add(line_to_data(zeile));
            }
          } else if (files[i].getAbsoluteFile().getName().contains(device_id_second) && data_list_second != null) {
            while ((zeile = br.readLine()) != null) {
              data_list_second.add(line_to_data(zeile));
            }
          } else {
            System.out.println("There was an unknown file in the folder");
          }

          br.close();
        }
      }
    } catch (JSONException je) {
      System.out.println("Parsing error");
    } catch (FileNotFoundException fnfe) {
      System.out.println("File not found");
    } catch (IOException ioe) {
      System.out.println("Error handling file.");
    }
    stop_timer(time);
  }

  public void make_graph() {

  }

  public static Data line_to_data(String zeile) throws JSONException {

    Data data = new Data();
    JSONObject jo = new JSONObject(zeile);
    data.timestamp = jo.getLong("Timestamp");
    JSONObject payload = jo.getJSONObject("payload");

    JSONObject current = payload.getJSONObject("roomhumidity");
    fill_double_array(current, data.roomhumidity);
    current = payload.getJSONObject("roomtemperature");
    fill_double_array(current, data.roomtemperature);
    current = payload.getJSONObject("pressure");
    fill_double_array(current, data.pressure);
    current = payload.getJSONObject("co2");
    fill_double_array(current, data.co2);
    current = payload.getJSONObject("pm25");
    fill_double_array(current, data.pm25);
    current = payload.getJSONObject("pm100");
    fill_double_array(current, data.pm100);
    current = payload.getJSONObject("voc");
    fill_double_array(current, data.voc);
    current = payload.getJSONObject("comfort");
    fill_double_array(current, data.comfort);

    return data;
  }

  public static void fill_double_array(JSONObject jo, double[] array) throws JSONException {

    array[0] = jo.getDouble("min");
    array[1] = jo.getDouble("median");
    array[2] = jo.getDouble("max");
    array[3] = jo.getDouble("mean");
  }
}
