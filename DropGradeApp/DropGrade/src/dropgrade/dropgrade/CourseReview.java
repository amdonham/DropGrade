package dropgrade.dropgrade;

/**
 * Created by shanepeters on 9/15/15.
 */
public class CourseReview {

    /***Attributes**/
    private String courseName;
    private String professorName;
    //private String professorLName;
    private String review;
    private String rating;
    private Boolean wouldRecommend;
    private String Userid;
    private String ReviewID;
    
    public String getReviewID(){
    	return ReviewID;
    }
    public void setReviewID(String id){
    	this.ReviewID = id;
    }
    public CourseReview(){

    }
    public String getUserid(){
    	return Userid;
    }
    public void setUserid(String idd){
    	this.Userid = idd;
    }
    public String getReview(){
    	return review;
    }
    
    public void setReview(String review){
    	this.review= review;
    }
    public String getRating(){
    	return rating;
    }
    
    public void setRating(String rating){
    	this.rating= rating;
    }

    public String getCourseName(){
    	return courseName;
    }
    
    public void setCourseName(String name){
    	this.courseName= name;
    }

    public String getProfessorName(){
    	return professorName;
    }
    
    public void setProfessorName(String name){
    	this.professorName= name;
    }
    
    

   

}
