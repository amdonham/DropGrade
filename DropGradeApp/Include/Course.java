import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alan on 9/15/2015.
 */
public class Course {

    /**
     * This class will manage all the data and information of the course.
     */

    /**Attributes*/
    private
        String courseName;
    	String professorFName;
    	String professorLName;
        Integer courseAverage;
        List<Weight> weights;

    public Course(){weights = new ArrayList<Weight>();}

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getProfessorFName() {
        return professorFName;
    }

    public void setProfessorFName(String professorFName) {
        this.professorFName = professorFName;
    }

    public Integer getCourseAverage() {
        return courseAverage;
    }

    public void setCourseAverage(Integer courseAverage) {
        this.courseAverage = courseAverage;
    }

    public String getProfessorLName() {
        return professorLName;
    }

    public void setProfessorLName(String professorLName) {
        this.professorLName = professorLName;
    }

    public List<Weight> getWeights() {
        return weights;
    }

    public void addWeight(String type, Float weight)
    {
        weights.add(new Weight(weight,type));
    }

    public void addGrade(String type, Float grade)
    {
        for(int i = 0; i < weights.size();i++)
        {
            if(weights.get(i).getName().equals(type))
            {
                weights.get(i).addGrade(grade);
            }
        }
    }



    public void getGrades() {
        /**fix this */
    }

}
