package edu.usc.sunset.trojanow3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/*
* MEHMET SEZER, USC CSCI 578, HW3
* This class is used in order to provide an activity for the message posting/composing page.
* This class will be getting environmental information from the Environment class which will
* perform the main dataflow from the phone.
* Additionally, the functions; likeMessage(), isAnonymous(), postMessage(), rePostMessage(),
* notificationViewer(), insertPhoto(), insertContacts() will be responsible of the
* operations regarding their title. All of the operations which require database connection
* will be handled by the DbHandler class.
* */
public class Compose extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        // Create a GoogleApiClient instance


    }

    /*

    implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        }

    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    */

    // Used in order to get back to the main class
    public void onClickSendMessage(View view){
        Intent i = new Intent(this, Main.class);
        startActivity(i);
    }

    // Used in order to get back to the environment activity
    public void onClickEnvironment(View view){
        Intent i = new Intent(this, Environment.class);
        startActivity(i);
    }

    /* THE FUNCTIONS PROVIDED BELOW WILL ALSO BE USED */

    /*
    public void onClickAddPicture(View view){
        // This function will be used in inserting pictures.
    }

    public void insertContacts(){
        // This function will be used in inserting contacts from the phone's contact list.
        // This function will require "Content Provider" in order to ge the contact information.
    }

    public void likeMessage(){
        // This function will be used in the process of liking a message.
    }

    public void isAnonymous(){
        // This function will be used in checking of anonymous messages/posts.
    }

    public void postMessage(){
        // This function will be used in posting a message to the server.
    }

    public void rePostMessage(){
        // This function will be used in reposting someone else's message/post.

    public void notificationViewer()
    {
        // This function will be used in getting/handling the notifications.
    }

    public void insertPhoto(){
        // This function will be used in inserting a photo from library or camera.
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compose, menu);
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
