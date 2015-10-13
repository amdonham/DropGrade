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
        List<Grade> grades;

    public Course(){}

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

    public List<Grade> getGrades() {
        return grades;
    }

    /**add get weight and grade*/


}
