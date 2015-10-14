/**
 * Created by Alan on 10/13/2015.
 */
public class Grade {
    /** Will need a weight type to associate this with*/

    public
        Float grade;
        String type;

    public Grade(){}

    public Grade(Float g,String t){this.grade = g; this.type = t;}

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
