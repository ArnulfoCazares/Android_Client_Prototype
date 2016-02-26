package androidexamples.fito.com.navdemowithfragments;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Fito on 2/9/2016.
 */
public class CustomAdapter extends BaseAdapter {

    private static final String TAG = "CustomAdaptet";

    private ArrayList<RowObject> object_list = new ArrayList<RowObject>();

    public static final int TYPE_ROBOT = 0;
    public static final int TYPE_APP = 1;

    Context context;
    //int layoutResourceId;

    public CustomAdapter(Context context, ArrayList<RowObject> row) {
        this.object_list = row;
        this.context = context;
    }

    public void addItem(RowObject object){
        object_list.add(object);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return object_list.size();
    }

    @Override
    public Object getItem(int position) {
        return object_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return object_list.get(position).getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ObjectHolder holder = null;
        RowObject element = object_list.get(position);
        int rowObjectItemType = getItemViewType(position);

        if(convertView == null){
            if(rowObjectItemType == TYPE_ROBOT){
                convertView = LayoutInflater.from((Activity)context).inflate(R.layout.listview_item_row_robot, null);
            } else if(rowObjectItemType == TYPE_APP){
                convertView = LayoutInflater.from((Activity)context).inflate(R.layout.listview_item_row_app, null);
            }

            holder = new ObjectHolder();
            holder.imgIcon = (ImageView)convertView.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)convertView.findViewById(R.id.textTitle);

            convertView.setTag(holder);
        } else {
            holder = (ObjectHolder)convertView.getTag();
        }

        holder.txtTitle.setText(element.data);
        holder.imgIcon.setImageResource(element.icon);

        return convertView;

        /*View row = convertView;
        ObjectHolder holder = null;

        //Check resource for robot or app icon
        Log.d(TAG, "Position: " + position);
        if(object_list.get(position).icon == R.drawable.robot){
            Log.d(TAG, "Icon: " + object_list.get(position).icon);
            layoutResourceId = R.layout.listview_item_row_robot;
        } else if(object_list.get(position).icon == R.drawable.tablet){
            Log.d(TAG, "Icon: " + object_list.get(position).icon);
            layoutResourceId = R.layout.listview_item_row_app;
        }

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ObjectHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.textTitle);

            row.setTag(holder);
        } else {
            holder = (ObjectHolder)row.getTag();
        }

        RowObject element = object_list.get(position);
        holder.txtTitle.setText(element.data);
        holder.imgIcon.setImageResource(element.icon);

        return row;
        */
    }

    public static class ObjectHolder{
        ImageView imgIcon;
        TextView txtTitle;
    }
}
