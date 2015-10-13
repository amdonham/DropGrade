/**
 * Created by Alan on 10/13/2015.
 */
public class Weight {

    /**Attributes*/
    private
        Float weight;
        String Name;

    public void Weight(){}

    public void Weight(Float w, String n){this.weight = w; this.Name = n;}

    public void setName(String name) {
        this.Name = name;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getWeight() {
        return weight;
    }

    public String getName() {
        return Name;
    }
}
