package mix.Aggregator;

/**
 * Created by Gebruiker on 20-2-2019.
 */
public class AggregationID {

    private static int idGenerator = 0;

    private int ID;
    private String identificationName;

    public AggregationID()
    {
        idGenerator++;
        this.ID = idGenerator;
        this.identificationName = "AggredationID";
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getIdentificationName() {
        return identificationName;
    }
    public void setIdentificationName(String identificationName) {
        this.identificationName = identificationName;
    }
}
