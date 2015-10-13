import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alan on 9/15/2015.
 */

public class User
{

    /**
     * This class will manage all the data and information of the user.
     */

    /**Attributes**/
    private
        String userName;
        String password;
        String emailAddress;
        List<Course> Courses;


    /**Methods**/

    public
        User()
        {
            Courses = new ArrayList<>();
        }

    public void setUserName(String name)
    {
        this.userName = name;
    }

    public String getUserName()
    {
        return userName;
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
