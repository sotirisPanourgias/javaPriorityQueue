import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class  DynamicInfluenza_k_withPQ {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        // Διαβάζουμε το αρχείο και δημιουργούμε τη ουρα προτεραιοτητας  με τις πόλεις
        // Το πρώτο όρισμα είναι το όνομα του αρχείου
        PQ pq =new PQ();
        try (Scanner scanner = new Scanner(System.in)) {
            // Ζητάμε από τον χρήστη να εισαγάγει έναν ακέραιο αριθμό


            // Διαβάζουμε τον αριθμό που εισήγαγε ο χρήστης
            int k = Integer.parseInt(args[0]);

            try (BufferedReader br = new BufferedReader(new FileReader(args[1]))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] fields = line.split(" ");
                    int id = Integer.parseInt(fields[0]);
                    String name = fields[1];
                    int population = Integer.parseInt(fields[2]);
                    int cases = Integer.parseInt(fields[3]);

                    City city = new City(id, name, population, cases);
                    pq.insert(city);

                }

                if(k>pq.size()){
                    System.out.println("The number of k is invalid");
                }else{
                    System.out.println("The top "+ " "+ k +" are:");
                    for(int i=0;i<k;i++){
                        City c=pq.getmin();
                        System.out.println(  c.getName() );

                    }
                }



            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
