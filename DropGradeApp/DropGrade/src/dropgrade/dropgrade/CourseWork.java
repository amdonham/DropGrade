package dropgrade.dropgrade;

import java.util.ArrayList;

public class CourseWork {
	
	String category;
	Integer grade;
	ArrayList<CourseWork> assignments;
	Integer count;
	String name;
	
	public CourseWork(){
	}
	
	public CourseWork(String category, int grade, String name ){
		this.category = category;
		this.grade = grade;
		this.name = name;
	}
	
	public void setCategory( String category){
		this.category = category;
	}
	
	public String getCategory(){
		return category;
	}
	
	public void setName(String name){
		this.name = name;
		
	}
	
	public String getName(){
		return name;
	}
	
	public void setGrade(int grade){
		this.grade = grade;
	}
	
	public Integer getGrade(){
		return grade;
				
	}
	
	
	
	
	

}
