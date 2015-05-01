package edu.usc.sunset.trojanow3;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.EditText;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;


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

        this.new HttpSendPost().execute();

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

    private class HttpSendPost extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            final String myUserId = GlobalVariables.getInstance().getUserId();
            final String myMessage = ((EditText) findViewById(R.id.tweet_msg)).getText().toString();

            // Need to be checked for the order
            String data = null;
            try {
                data = URLEncoder.encode("userid", "UTF-8") + "=" +
                        URLEncoder.encode(myUserId, "UTF-8");
                data += "&" + URLEncoder.encode("message", "UTF-8") + "=" +
                        URLEncoder.encode(myMessage, "UTF-8");

                final AssetManager assetManager = Compose.this.getResources().getAssets();


                final InputStream inputStream = assetManager.open("local.properties");
                Properties properties = new Properties();
                properties.load(inputStream);
                Log.w("PROPERTIES_LOG_", "The properties are now loaded");
                String serverAddress = properties.getProperty("proxyHost");
                String serverPort = properties.getProperty("proxyPort");
                Log.w("PROPERTIES_LOG_", serverAddress + ":" + serverPort);

                final URL myUrl = new URL("http://" + serverAddress + ":" + serverPort + "/trojanow-web/TweetService");

                HttpURLConnection myConnection = (HttpURLConnection) myUrl.openConnection();

                myConnection.setRequestMethod("POST");

                myConnection.setDoOutput(true);
                myConnection.setDoInput(true);
                OutputStreamWriter wr = new OutputStreamWriter(myConnection.getOutputStream());
                wr.write(data);
                wr.flush();
                wr.close();

                myConnection.connect();
                myConnection.getInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
