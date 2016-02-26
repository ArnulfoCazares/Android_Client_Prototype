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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentData extends Fragment implements View.OnClickListener{

    public EditText message_text;
    public TextView display_message;

    public Button button;
    public String return_message = null;

    boolean has_text_entered = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            return_message = savedInstanceState.getCharSequence("savedText").toString();
        }
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        button = (Button)getActivity().findViewById(R.id.set_text_button);
        button.setOnClickListener(this);

        display_message = (TextView) getActivity().findViewById(R.id.display_message);
        display_message.setText("");
        if (return_message != null){
            display_message = (TextView) getActivity().findViewById(R.id.display_message);
            display_message.setText(return_message);
        }
    }

    @Override
    public void onClick(View v) {
        has_text_entered = true;
        //message_text = (EditText)getActivity().findViewById(R.id.text_message);
        //String message = message_text.getText().toString();

        //display_message = (TextView)getActivity().findViewById(R.id.display_message);
        //display_message.setText(message);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TextView text = (TextView)getActivity().findViewById(R.id.display_message);
        CharSequence userText = text.getText();
        if(userText != null){outState.putCharSequence("savedText", userText);}
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
