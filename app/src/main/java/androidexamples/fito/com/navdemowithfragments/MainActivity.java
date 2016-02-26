package androidexamples.fito.com.navdemowithfragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentControl.ButtonSetListener{

    public final static String PORT = "PORT";
    public final static String IP = "IP";
    private static final String TAG = MainActivity.class.getSimpleName();

    private Fragment currFragment;
    private Fragment fragment_data;
    private Fragment fragment_graph;
    private Fragment fragment_log;
    private Fragment fragment_control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // However, if we're being restored from a previous state,
        // then we don't need to do anything and should return or else
        // we could end up with overlapping fragments.
        if(savedInstanceState == null){

            fragment_data = new FragmentData();
            fragment_graph = new FragmentGraph();
            fragment_log = new FragmentLog();
            fragment_control = new FragmentControl();

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            FragmentTransaction fragmentTransaction_Control = getFragmentManager().beginTransaction();

            //Add fragment_data, fragment_log, fragment_graph into fragment_control_panel
            //Hide after each call leaving fragment_data the only fragment visible
            fragmentTransaction.add(R.id.fragment_data_panel, fragment_log, "FRAGMENT_LOG");
            fragmentTransaction.hide(fragment_log);
            fragmentTransaction.add(R.id.fragment_data_panel, fragment_graph, "FRAGMENT_GRAPH");
            fragmentTransaction.hide(fragment_graph);
            fragmentTransaction.add(R.id.fragment_data_panel, fragment_data, "FRAGMENT_DATA");
            fragmentTransaction.commit();

            //fragmentControl is called into fragment_control_panel, this is never altered.
            fragmentTransaction_Control.add(R.id.fragment_control_panel, fragment_control, "FRAGMENT_CONTROL");
            fragmentTransaction_Control.commit();

            currFragment = fragment_data;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        Intent intent = getIntent();
        String recovered_ip = intent.getStringExtra(IP);
        String recovered_port = intent.getStringExtra(PORT);

        TextView t1 = (TextView)findViewById(R.id.ip_display);
        t1.setText("IP: " + recovered_ip);

        TextView t2 = (TextView)findViewById(R.id.port_display);
        t2.setText("PORT: " + recovered_port);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.data_log && currFragment != fragment_log) {
            PanelManager(fragment_log);
        } else if (id == R.id.data_graph && currFragment != fragment_graph) {
            PanelManager(fragment_graph);
        } else if (id == R.id.data_data && currFragment != fragment_data) {
            PanelManager(fragment_data);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void PanelManager(Fragment fragment){

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.show(fragment);
        fragmentTransaction.hide(currFragment);
        fragmentTransaction.commit();
        currFragment = fragment;

    }

    //Call updateList method in FragmentLog to update ListView
    @Override
    public void app_log_update(String data, int icon) {
        FragmentLog fragmentLog = (FragmentLog)getFragmentManager().findFragmentByTag("FRAGMENT_LOG");
        fragmentLog.updateList(data, icon);
    }

    public void app_graph_update(){
        FragmentGraph fragmentGraph = (FragmentGraph)getFragmentManager().findFragmentByTag("FRAGMENT_GRAPH");
        fragmentGraph.addEntry();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save fragment instances
        getFragmentManager().putFragment(outState, "current", currFragment);
        getFragmentManager().putFragment(outState, "fragment_data", fragment_data);
        getFragmentManager().putFragment(outState, "fragment_graph", fragment_graph);
        getFragmentManager().putFragment(outState, "fragment_log", fragment_log);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //Recall fragment instances
        currFragment = getFragmentManager().getFragment(savedInstanceState, "current");
        fragment_data = getFragmentManager().getFragment(savedInstanceState, "fragment_data");
        fragment_graph = getFragmentManager().getFragment(savedInstanceState, "fragment_graph");
        fragment_log = getFragmentManager().getFragment(savedInstanceState, "fragment_log");
    }
}
