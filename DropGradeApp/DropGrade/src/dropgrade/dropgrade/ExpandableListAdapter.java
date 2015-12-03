package dropgrade.dropgrade;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listHeaders; // List of Headers
    // List View content as a Map of Header with its Items
    private HashMap<String, List<CourseWork>> listItems;

    public ExpandableListAdapter(Context context, List<String> listHeader,
                                 HashMap<String, List<CourseWork>> listItems2) {
        this.context = context;
        this.listHeaders = listHeader;
        this.listItems = listItems2;
    }

    @Override
    public CourseWork getChild(int groupPosition, int itemPosititon) {
        return this.listItems.get(this.listHeaders.get(groupPosition))
                .get(itemPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int item) {
        return item;
    }

    @Override
    public View getChildView(int groupPosition, final int itemPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String itemTitle = (String) getChild(groupPosition, itemPosition).getName();
        final String itemNum = String.valueOf(getChild(groupPosition, itemPosition).getGrade());
        final String id = String.valueOf(getChild(groupPosition, itemPosition).getID());
        
        
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        title.setText(itemTitle);
        TextView num = (TextView) convertView.findViewById(R.id.number);
        num.setText(itemNum);
        TextView itemID = (TextView) convertView.findViewById(R.id.itemID);
		itemID.setText(id);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listItems.get(this.listHeaders.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listHeaders.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listHeaders.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.header, null);
        }

        TextView header = (TextView) convertView.findViewById(R.id.header);
        header.setText(headerTitle);
        TextView count = (TextView) convertView.findViewById(R.id.count);
        count.setTextColor(Color.WHITE);
        count.setText(" (" + getChildrenCount(groupPosition) + ")");

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
