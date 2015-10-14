import java.util.List;

/**
 * Created by Alan on 10/13/2015.
 */
public class Weight {

    /**Attributes*/
    private
        Float weight;
        String Name;
        List<Grade> grades;

    public Weight(){}

    public Weight(Float w, String n){this.weight = w; this.Name = n;}

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

    public void addGrade(Float grade)
    {
        grades.add(grade);
    }
}
