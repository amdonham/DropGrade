package dropgrade.dropgrade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 9/15/2015.
 * 
 * Edited by Madison on 11/24/2015
 */

public class User
{

    /**
     * This class will manage all the data and information of the user.
     */

    /**Attributes**/
    
        String userName;
        String password;
        String emailAddress;
        String Name;
        List<Course> Courses;
        List<CourseWork> CourseWork;
        


    /**Methods**/

    public User(){
    	Courses = new ArrayList<Course>();
    	CourseWork = new ArrayList<CourseWork>();
    }
    public User(String username,String name, String pass, String email) {
    	this.userName = username; 
    	this.password = pass; 
    	this.emailAddress = email; 
    	this.Name = name;
        	Courses = new ArrayList<Course>();
        	CourseWork = new ArrayList<CourseWork>();
    	}

    public void setUserName(String name)
    {
        this.userName = name;
    }

    public String getUserName()
    {
        return userName;
    }
    
    public void setName(String name)
    {
        this.Name = name;
    }

    public String getName()
    {
        return Name;
    }


    public void setPassword(String pw)
    {
        this.password = pw;
    }

    public Boolean confirmPassword(String s)
    {
        return password.equals(s);
    }

    public void setEmail(String email)
    {
        this.emailAddress = email;
    }

    public String getEmail()
    {
        return emailAddress;
    }

    public Boolean addCourse(Course course)
    {
        try
        {
            this.Courses.add(course);
            return true;
        }
        catch(ArrayStoreException e)
        {
            return false;
        }
    }

    public Boolean deleteCourse(Course course)
    {
        try
        {
            this.Courses.remove(course);
            return true;
        }
        catch(ArrayStoreException e)
        {
            return false;
        }
    }


    public List<Course> getCourses()
    {
        return Courses;
    }
    
   
}
