package edu.usc.sunset.trojanow3;

import android.content.Context;
import android.location.LocationListener;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class Main extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Link to the compose activity
    public void onClickCompose(View view){
        Intent i = new Intent(this, Compose.class);
        startActivity(i);
    }

    // Link to the search activity
     public void onClickSearch(View view){
        Intent i = new Intent(this, Search.class);
        startActivity(i);
    }

    // Used in order to get back to the profile activity
    public void onClickProfile(View view){
        Intent i = new Intent(this, ProfilePage.class);
        startActivity(i);
    }

    // Used in order to get back to the search activity
    // TO.DO. ADD Sign Out Process here
    public void onClickSignOut(View view){
        Context context = getApplicationContext();
        CharSequence text = "Signing out in process...";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

        // Link to Login Page after sign out process
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
