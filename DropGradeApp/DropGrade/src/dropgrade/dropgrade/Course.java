package dropgrade.dropgrade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 9/15/2015.
 */
public class Course {

    /**
     * This class will manage all the data and information of the course.
     */

    /**Attributes*/
    
        String courseName;
    	String courseSubject;
    	Integer courseNum;
    	String professorFName;
    	String professorLName;
        Integer courseAverage;
        String semsTaught;
        List<Weight> weights;

    public Course(){weights = new ArrayList<Weight>();}

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public void setCourseSubject(String subject){
    	this.courseSubject = subject;
    }
    
    public String getCourseSubject(){
    	return courseSubject;
    }
    
    public void setCourseNum(int num){
    	this.courseNum = num;
    	
    }
    
    public Integer getCourseNum(){
    	return courseNum;
    }




    public Integer getCourseAverage() {
        return courseAverage;
    }

    public void setCourseAverage(Integer courseAverage) {
        this.courseAverage = courseAverage;
    }
    
    public void setSemsTaught(String sem){
    	this.semsTaught = sem;
    }
    
    public String getSemsTaught(){
    	return  semsTaught;
    }
    
    public String getCourseInfo(){
    	return courseSubject + " " + courseName;
    }


    public void setProfessorFName(String professorFName) {
        this.professorFName = professorFName;
    }
    
    public String getProfessorFName() {
        return professorFName;
    }

    public void setProfessorLName(String professorLName) {
        this.professorLName = professorLName;
    }
    public String getProfessorLName() {
        return professorLName;
    }
    
    public String getProfessorName(){
    	return professorFName;
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

    public List<Grade> getGrades(String type)
    {
        for(int i = 0; i < weights.size(); i++)
        {
            if(weights.get(i).getName().equals(type))
            {
                return weights.get(i).getGrades();
            }
        }
        return new ArrayList<Grade>();
    }



    public void getGrades() {
        /**fix this */
    }
    
    @Override
    public String toString(){
    	return "Course = " + courseName + ", Professor Name = " +
    			getProfessorName() + ", semester = " +semsTaught;
    }

}
