package dropgrade.dropgrade;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder>{

    private List<CourseReview> reviews;
    private int rowLayout;
    private Context mContext;

    public ReviewAdapter(List<CourseReview> reviews, int rowLayout, Context context) {
        this.reviews = reviews;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        CourseReview review = reviews.get(i);
        viewHolder.professorName.setText(review.getProfessorName());
        viewHolder.courseName.setText(review.getCourseName());
        viewHolder.rating.setText(review.getRating());
        viewHolder.review.setText(review.getReview());
       
    }

    @Override
    public int getItemCount() {
        return reviews == null ? 0 : reviews.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView professorName;
        public TextView courseName;
        public TextView rating;
        public TextView review;
        

        public ViewHolder(View itemView) {
            super(itemView);
            professorName = (TextView) itemView.findViewById(R.id.p_name);
            courseName = (TextView) itemView.findViewById(R.id.c_name);
            rating = (TextView) itemView.findViewById(R.id.p_rating);
            review = (TextView) itemView.findViewById(R.id.p_review);

        }

    }
}
