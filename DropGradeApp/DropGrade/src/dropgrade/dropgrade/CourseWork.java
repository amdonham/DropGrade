package dropgrade.dropgrade;

import java.util.ArrayList;

public class CourseWork {
	
	String category;
	Integer grade;
	Integer weight;
	ArrayList<CourseWork> assignments;
	Integer count;
	String name;
	String ID;
	
	public CourseWork(){
	}
	
	public CourseWork(String category, int grade, String name, int weight ){
		this.category = category;
		this.grade = grade;
		this.name = name;
		this.weight = weight;
		
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
	public void setID(String AID){
		this.ID = AID;
	}
	public String getID(){
		return ID;
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
	
	public void setWeight(int weight){
		this.weight = weight;
	}
	
	public Integer getWeight(){
		return weight;
				
	}
	
	
	

}
