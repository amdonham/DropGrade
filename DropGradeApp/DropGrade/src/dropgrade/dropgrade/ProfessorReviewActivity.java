package dropgrade.dropgrade;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class ProfessorReviewActivity extends Activity {

    private RecyclerView mRecyclerView;
    private ReviewAdapter mAdapter;
	String UserID;
	LoginDataBaseAdapter loginDataBaseAdapter;
	String PName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.professor_recycleview);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    UserID = extras.getString("UID");
		    PName = extras.getString("PName");
		}
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
        mRecyclerView = (RecyclerView)findViewById(R.id.professor_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//      
        mAdapter = new ReviewAdapter(getProfReviews(), R.layout.professor_review_activty, this);
        mRecyclerView.setAdapter(mAdapter);
    }
    
    public List<CourseReview> getProfReviews() {
    	List<CourseReview> reviews = new ArrayList<CourseReview>();
    	//Toast.makeText(ProfessorReviewActivity.this,PName,Toast.LENGTH_SHORT).show();
        ArrayList<String> PReviews = loginDataBaseAdapter.getAllReviews(PName);
		for(int i = 0; i < PReviews.size(); i++){
			CourseReview review = new CourseReview();

			String[] splitReview = PReviews.get(i).split("~");
			String ProfName = PName;
			String CourseName = splitReview[0];
			String Rating = splitReview[1];
			String Review = splitReview[2];
			String RID = splitReview[3].toString();
			review.setUserid(UserID);
			review.setReviewID(RID);
			review.setCourseName(CourseName);
			review.setProfessorName(ProfName);
			review.setRating(Rating);
			review.setReview(Review);
			reviews.add(review);
			//Toast.makeText(ProfessorReviewActivity.this,CourseName,Toast.LENGTH_SHORT).show();
			
		}
        return reviews;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
