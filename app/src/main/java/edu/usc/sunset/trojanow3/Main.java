package edu.usc.sunset.trojanow3;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends ActionBarActivity {

    public static TextView tweetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tweetText = (TextView) findViewById(R.id.main_tweet_textview);
        tweetText.setMovementMethod(new ScrollingMovementMethod());

        String dummy_tweets = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit." +
        "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis" +
        "parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu," +
        "pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet" +
        "nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo." +
        "Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus" +
        "elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu," +
        "consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a," +
        "tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet." +
        "Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui." +
        "Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero," +
        "sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar," +
        "hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut" +
        "libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus" +
        "tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna." +
        "Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, quis";

        tweetText.setText(dummy_tweets);


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

        // Sign Out Process

        // Show Toast Message
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
