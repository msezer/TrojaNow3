package edu.usc.sunset.trojanow3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class ProfilePage extends ActionBarActivity {

    EditText emailEd_profilePage;
    EditText fullnameEd_profilePage;
    EditText password_one_ED_profilePage;
    EditText password_two_ED_profilePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        // Assign Current Profile
        emailEd_profilePage = (EditText) findViewById(R.id.profile_email);
        fullnameEd_profilePage = (EditText) findViewById(R.id.profile_fullname);
        password_one_ED_profilePage = (EditText) findViewById(R.id.profile_password_first);
        password_two_ED_profilePage = (EditText) findViewById(R.id.profile_password_second);
    }

    // Cancel Update and Link to the Main
    public void onClickToMain(View view){
        Intent i = new Intent(this, Main.class);
        startActivity(i);
    }

    // Update Profile and Link to the Main
    // TO.DO : Update Profile
    public void onClickUpdateProfile (View view){

        // Update Profile

        // Show Toast Message
        Context contextP = getApplicationContext();
        CharSequence textP = "Updating Your Profile...";
        int durationP = Toast.LENGTH_SHORT;
        Toast toastP = Toast.makeText(contextP, textP, durationP);
        toastP.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toastP.show();

        // Link to Main Page
        Intent in_main = new Intent(this, Main.class);
        startActivity(in_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
