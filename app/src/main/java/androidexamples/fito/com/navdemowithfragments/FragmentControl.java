package androidexamples.fito.com.navdemowithfragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Fito on 2/5/2016.
 */
public class FragmentControl extends Fragment{

    ButtonSetListener buttonSetListener;
    Button app_button;
    Button robot_button;
    Button graph_button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_control, container, false);

        app_button = (Button)view.findViewById(R.id.app_button);
        robot_button = (Button)view.findViewById(R.id.robot_button);
        graph_button = (Button)view.findViewById(R.id.graph_button);

        app_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSetListener.app_log_update("Test APP Entry", R.drawable.tablet);
            }
        });

        robot_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSetListener.app_log_update("Test ROBOT Entry", R.drawable.robot);
            }
        });

        graph_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSetListener.app_graph_update();
            }
        });



        return view;
    }

    public interface ButtonSetListener{
        public void app_log_update(String data, int icon);
        public void app_graph_update();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            buttonSetListener = (ButtonSetListener)activity;
        } catch (Exception e){

        }
    }
}
