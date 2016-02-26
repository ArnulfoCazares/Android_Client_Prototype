package androidexamples.fito.com.navdemowithfragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fito on 2/5/2016.
 */
public class FragmentLog extends Fragment{

    private static final String TAG = "FragmentLog";

    private CustomAdapter adapter;
    private ArrayList<RowObject> rowObjects = new ArrayList<RowObject>();
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log, container, false);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        adapter = new CustomAdapter(getActivity(), rowObjects);
        listView = (ListView)getActivity().findViewById(R.id.log_view);
        listView.setAdapter(adapter);
        listView.setSelection(listView.getAdapter().getCount() - 1);
    }

    public void updateList(String name, int icon){

        int type;

        if(icon == R.drawable.robot){
            type = 0;
        } else {
            type = 1;
        }


        RowObject element = new RowObject(name, icon, type);
        adapter.addItem(element);

        listView = (ListView)getActivity().findViewById(R.id.log_view);
        listView.setAdapter(adapter);
        listView.setSelection(listView.getAdapter().getCount() - 1);
    }

}











