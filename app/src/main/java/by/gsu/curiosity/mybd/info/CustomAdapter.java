package by.gsu.curiosity.mybd.info;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import by.gsu.curiosity.mybd.R;

public class CustomAdapter extends BaseExpandableListAdapter {
    private LayoutInflater inflater;
    private ArrayList<ContactGroup> data;

    public CustomAdapter(Context context, ArrayList<ContactGroup> data) {
        inflater = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    @Override
    public ContactItem getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getContactItem(childPosition);
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getContactsList().size();
    }

    @Override
    public ContactGroup getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.row_group, null);
        }
        ContactGroup group = data.get(groupPosition);

        TextView name = (TextView)view.findViewById(R.id.name);
        name.setText(group.getName());

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            view = inflater.inflate(R.layout.row_child, null);
        }
        ContactItem item =
                data.get(groupPosition).getContactItem(childPosition);

        ImageView image = (ImageView)view.findViewById(R.id.image);
        TextView name = (TextView)view.findViewById(R.id.name);
        TextView phone = (TextView)view.findViewById(R.id.phone);

        if (childPosition%2==0){
            name.setBackgroundColor(Color.parseColor("#6dcff6"));
        } else {
            if ((childPosition % 1) == 0) {
                name.setBackgroundColor(Color.parseColor("#444444"));
            }
        }
        image.setImageResource(item.getPhotoID());
        name.setText(item.getName());
        phone.setText(item.getPhone());

        return view;
    }
}