import java.security.PublicKey;
import java.util.ArrayList;
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
            Courses = new ArrayList<Course>();
        }

    public Boolean addCourse(Course course) {
        try {
            Courses.add(course);
            return true;
            }
        catch(ArrayStoreException e)
        {
            return false;
        }
    }

}
