package app.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import junit.framework.Test;

import androidexamples.fito.com.navdemowithfragments.MainActivity;
import androidexamples.fito.com.navdemowithfragments.R;
import app.testing.*;

/**
 * Created by Fito on 2/24/2016.
 */
public class LoginActivity extends AppCompatActivity {

    EditText ip;
    EditText port;
    EditText key;

    public final static String PORT = "PORT";
    public final static String IP = "IP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void Connect_To_Robot(View v){

        ip = (EditText)findViewById(R.id.robot_ip);
        port = (EditText)findViewById(R.id.robot_port);

        String str_ip = ip.getText().toString();
        String str_port = port.getText().toString();

        Tester tester = new Tester();
        Boolean ip_test = tester.test_ip(str_ip);

        if(ip_test){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(PORT, str_port);
            intent.putExtra(IP, str_ip);

            //Remove LoginActivity from the stack, users should not go back to login after completion
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Failed To Connect",
                    Toast.LENGTH_LONG).show();
        }


    }
}
