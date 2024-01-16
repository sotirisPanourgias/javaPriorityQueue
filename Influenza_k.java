import java.io.*;
import java.util.*;

public class Influenza_k {
    public static void main(String[] args) {
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(args[1]));
            int number_of_cities_to_print = Integer.parseInt(args[0]);
            ArrayList<City> c = new ArrayList<City>();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int population = Integer.parseInt(parts[2]);
                int influenzaCases = Integer.parseInt(parts[3]);
                City city = new City(id,name,population,influenzaCases);
                c.add(city);
            }
            reader.close();
            int end = c.size() - 1 ;
            QuickSort(c,0,end);
            if(number_of_cities_to_print>c.size()){
                System.out.print("Δεν υπαρχουν τοσες πολεις");
            }else {
                System.out.println("Οι " + number_of_cities_to_print + " πολεις με την μικροτερη πικνοτητα κρουσματων");
                for (int i = 0; i <= number_of_cities_to_print - 1 && i < c.size(); i++) {
                    City c1 = c.get(i);
                    System.out.println(c1.getName());
                }
            }
        }catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public static void QuickSort(ArrayList<City> city,int begin,int end){
        if ( begin<end){
            int partInd = partInd(city,begin,end);

            QuickSort(city,begin,partInd-1);
            QuickSort(city,partInd+1,end);

        }
    }
    private static int partInd(ArrayList<City> city,int begin,int end){
        City pivot = city.get(end);
        int i = begin-1;

        for (int j=begin;j<end;j++){
            if(pivot.compareTo(city.get(j)) <0) {
                i++;

                City swapTemp = city.get(i);
                city.set(i,city.get(j));
                city.set(j,swapTemp);
            }
        }
        City swapTemp = city.get(i+1);
        city.set(i+1,city.get(end));
        city.set(end,swapTemp);

        return i+1;
    }
}