import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Dynamic_Median {
    public static void main(String args[]) throws FileNotFoundException, IOException {
        PQ minPQ = new PQ();
        PQ maxPQ = new PQ();

        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line;
            int i =1;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(" ");
                int id = Integer.parseInt(fields[0]);
                String name = fields[1];
                int population = Integer.parseInt(fields[2]);
                int cases = Integer.parseInt(fields[3]);

                City city = new City(id, name, population, cases);
                
                // Προσθήκη στην κατάλληλη ουρά προτεραιότητας
                if (minPQ.isEmpty() || city.compareTo(minPQ.min()) > 0) {
                    minPQ.insert(city);
                } else {
                    maxPQ.insert(city);
                }

                // Εξισορρόπηση των ουρών
                if (minPQ.size() > maxPQ.size() + 1) {
                    maxPQ.insert(minPQ.getmin());
                    minPQ.remove(minPQ.getHeap()[minPQ.size()].getID());
                } else if (maxPQ.size() > minPQ.size()) {
                    minPQ.insert(maxPQ.getmin());
                    maxPQ.remove(maxPQ.getHeap()[maxPQ.size()].getID());
                }

                // Υπολογισμός και εκτύπωση του median
                if (minPQ.size() == maxPQ.size()) {
                    double median = (minPQ.min().getInfluenzaCases() + maxPQ.min().getInfluenzaCases()) / 2.0 / 
                                    (minPQ.min().getPopulation() + maxPQ.min().getPopulation()) * 100;
                    if (i%5==0) {
                        System.out.println(median);
                    }
                   
                } else {
                    if (i%5==0) {
                        System.out.println(minPQ.min().getInfluenzaCases() / minPQ.min().getPopulation() * 100);
                    }
                }   
                i++;
            }
        }
    }
}