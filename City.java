public class City implements CityInterface, Comparable<City>  {
    private int ID;
    private String name;
    private int population;
    private int influenzaCases;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getInfluenzaCases() {
        return influenzaCases;
    }

    public void setInfluenzaCases(int influenzaCases) {
        this.influenzaCases = influenzaCases;
    }

    public City(int ID, String name, int population, int influenzaCases) {
        this.ID = ID;
        this.name = name;
        this.population = population;
        this.influenzaCases = influenzaCases;
    }

    @Override
    public int compareTo(City otherCity) {
        double density1 = calculateDensity();
        double density2 = otherCity.calculateDensity();

        // Συγκρίνουμε βάσει της πυκνότητας κρουσμάτων
        int result = Double.compare(density2, density1);

        if (result == 0) {
            // Σε περίπτωση ισοβαθμίας, προτιμούμε το όνομα που προηγείται αλφαβητικά
            result = this.name.compareTo(otherCity.name);

            if (result == 0) {
                // Σε περίπτωση ισοβαθμίας στο όνομα, προτιμούμε το μικρότερο ID
                result = Integer.compare(this.ID, otherCity.ID);
            }
        }

        return result;
    }

    // Υπολογισμός πυκνότητας κρουσμάτων ανά 50,000 κατοίκους
    public double calculateDensity() {
        double p = population / 50000;
        return (double) influenzaCases / p;
    }
}

