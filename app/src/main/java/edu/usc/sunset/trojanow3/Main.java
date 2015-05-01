package edu.usc.sunset.trojanow3;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.transform.Result;

public class Main extends ActionBarActivity {

    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.new GetTweets().execute();
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

    private class GetTweets extends AsyncTask<String, String, String>{

        private String toString(final InputStream pInputStream) throws IOException {

            final StringBuilder myStringBuilder = new StringBuilder();
            final byte[] myBuffer = new byte[1024];
            int myNumberOfBytesRead = pInputStream.read(myBuffer);

            while (myNumberOfBytesRead != -1) {
                myStringBuilder.append(new String(myBuffer).substring(0, myNumberOfBytesRead));
                myNumberOfBytesRead = pInputStream.read(myBuffer);
            }
            return myStringBuilder.toString();
        }

        private List<String> mList = new ArrayList<String>();
        private ArrayAdapter<String> mArrayAdapter;

        @Override
        protected String doInBackground(String... strings) {
            mListView = (ListView) findViewById(R.id.tweetslist);
            try {
                final AssetManager assetManager = Main.this.getResources().getAssets();

                final InputStream inputStream = assetManager.open("local.properties");
                Properties properties = new Properties();
                properties.load(inputStream);
                Log.w("PROPERTIES_LOG_", "The properties are now loaded");
                String serverAddress = properties.getProperty("proxyHost");
                String serverPort = properties.getProperty("proxyPort");
                Log.w("PROPERTIES_LOG_", serverAddress + ":" + serverPort);

                final URL myUrl = new URL("http://" + serverAddress + ":" + serverPort + "/trojanow-web/TweetService");


                HttpURLConnection myConnection = (HttpURLConnection) myUrl.openConnection();
                myConnection.setRequestMethod("GET");

                myConnection.setDoOutput(false);
                myConnection.setDoInput(true);

                myConnection.connect();
                final InputStream myStream = myConnection.getInputStream();

                final String myString = this.toString(myStream);

                JSONArray myJsonArray = new JSONArray(myString);

                for (int i = 0 ; i < myJsonArray.length(); i++){
                    final JSONObject myObject = myJsonArray.getJSONObject(i);

                    mList.add(myObject.getString("message"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result){
            final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, mList);
            Main.this.mListView.setAdapter(myArrayAdapter);
            Main.this.mListView.deferNotifyDataSetChanged();
        }
    }
/*    private static class CustomListAdapter extends ArrayAdapter<String>{

        private Context mContext;
        private int id;
        private List <String>items;

        public CustomListAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
            super(context, resource, textViewResourceId, objects);

            mContext = context;
            id = textViewResourceId;
            items = objects ;
        }

        @Override
        public View getView(int position, View v, ViewGroup parent)
        {
            View mView = v ;
            if(mView == null){
                LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                mView = vi.inflate(id, null);
            }

            TextView text = (TextView) mView.findViewById(R.id.textView);

            if(items.get(position) != null )
            {
                text.setTextColor(Color.WHITE);
                text.setText(items.get(position));
                text.setBackgroundColor(Color.RED);
                int color = Color.argb( 200, 255, 64, 64 );
                text.setBackgroundColor( color );

            }

            return mView;
        }
    }*/
}
