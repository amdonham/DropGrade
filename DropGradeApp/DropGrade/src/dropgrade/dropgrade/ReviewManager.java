package dropgrade.dropgrade;

import java.util.ArrayList;
import java.util.List;



public class ReviewManager {

    //private static String[] reviews = {};
    

    private static ReviewManager mInstance;
    private List<CourseReview> reviews;

    public static ReviewManager getInstance() {
        if (mInstance == null) {
            mInstance = new ReviewManager();
        }

        return mInstance;
    }

    
    public List<CourseReview> getReviews() {
        //if (reviews == null) {
            reviews = new ArrayList<CourseReview>();
            CourseReview review = new CourseReview();
            review.setProfessorName("Borie");
            review.setCourseName("CS360");
            review.setRating("2");
            review.setReview("dwhdiuwqdiwqhdiqw");
            reviews.add(review);
            
            review = new CourseReview();
            review.setProfessorName("Bories");
            review.setCourseName("CS475");
            review.setRating("1");
            review.setReview("dwhddwdddddddiuwqdiwqhdiqw");
            reviews.add(review);
            
        
//            for (String review : reviews) {
//            	CourseReview preview = new CourseReview();
//            	preview.getProfessorName() = review;
                
            //}
        //}

        return  reviews;
    }

}