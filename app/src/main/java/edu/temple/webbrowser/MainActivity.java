package edu.temple.webbrowser;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentURL.urlInterface, BrowserFragment.browserInterface {

    ArrayList fragmentList = new ArrayList();
    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        FragmentURL urlFragment = new FragmentURL();
        loadFragment(R.id.URLFragment,urlFragment,false);


        BrowserFragment browserFragment = new BrowserFragment();
        loadFragment(R.id.BrowserFragment, browserFragment, false);

        fragmentList.add(browserFragment);

        Uri data = getIntent().getData();
        if(data != null) {
            Bundle args = new Bundle();
            String url = data.toString();
            args.putSerializable("urlData", url);
            BrowserFragment browserFragment2 = new BrowserFragment();
            browserFragment2.setArguments(args);
            loadFragment(R.id.BrowserFragment,browserFragment2,false);
            fragmentList.add(browserFragment2);
            index++;
        }


}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        }
        if(id==R.id.action_new){
            BrowserFragment browserFragment = new BrowserFragment();
            loadFragment(R.id.BrowserFragment, browserFragment, false);
            fragmentList.add(browserFragment);
            index = fragmentList.size();
        }

        if (id == R.id.action_previous) {
            if(index==0) {
                Toast.makeText(this, "You are at the first web page!", Toast.LENGTH_LONG).show();
            }
            else{
                index--;
                loadFragment(R.id.BrowserFragment,(BrowserFragment)fragmentList.get(index), false);
            }
        }

        if (id == R.id.action_next) {
            if(index==fragmentList.size()-1) {
                Toast.makeText(this, "You are at the last web page!", Toast.LENGTH_LONG).show();
            }
            else{
                index++;
                loadFragment(R.id.BrowserFragment,(BrowserFragment)fragmentList.get(index), false);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadFragment(int ID, Fragment fragment, boolean backStack){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction().replace(ID,fragment);
        if(backStack){
            ft.addToBackStack(null);
        }
        ft.commit();
        fm.executePendingTransactions();
    }

    public void setURL(String URL, BrowserFragment bFrag){
        FragmentManager fm = getFragmentManager();
        bFrag = (BrowserFragment) fm.findFragmentById(R.id.BrowserFragment);
        bFrag.changeURL(bFrag.getView(), URL);
    }

    @Override
    public void urlChange(String newURL) {
        FragmentManager fm = getFragmentManager();
            FragmentURL urlFrag = (FragmentURL) fm.findFragmentById(R.id.URLFragment);
            urlFrag.setURL(urlFrag.getView(), newURL);
        }

}

