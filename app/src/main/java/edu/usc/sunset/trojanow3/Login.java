package edu.usc.sunset.trojanow3;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

public class Login extends ActionBarActivity {

    public static EditText email_login;
    public static EditText password_login;

    public static String serverAddress;
    public static String serverPort;
    public static Resources resources;
    public static AssetManager assetManager;
    public static InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_login = (EditText) findViewById(R.id.login_email);
        password_login = (EditText) findViewById(R.id.login_password);

        resources = this.getResources();
        assetManager = resources.getAssets();

        try {
            inputStream = assetManager.open("local.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            Log.w("PROPERTIES_LOG_", "The properties are now loaded");
            serverAddress = properties.getProperty("proxyHost");
            serverPort = properties.getProperty("proxyPort");
            Log.w("PROPERTIES_LOG_", serverAddress + ":" + serverPort);
        } catch (IOException e) {
            System.err.println("Failed to open microlog property file");
            e.printStackTrace();
        }
    }

    // Login Successful, to main page
    public void onClickLoginProcess(View view) {
        /*
        // login user
        new HttpLoginPost().execute("test");

        // Show Toast Message
        Context context = getApplicationContext();
        CharSequence text = "Logging you in ...";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
        */

        // link to the Main page
        Intent i = new Intent(this, Main.class);
        i.putExtra("KEY", "User Details");
        startActivity(i);
    }

    // To reset password page
    public void onClickResetPassword(View view) {
        Intent i = new Intent(this, Password.class);
        startActivity(i);
    }

    // Used in order to get back to the forget password activity
    public void onClickRegister(View view) {
        Intent i = new Intent(this, Register.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    private static class HttpLoginPost extends AsyncTask<String, String, String> {

        private static String toString(final InputStream pInputStream) throws IOException {

            final StringBuilder myStringBuilder = new StringBuilder();

            final byte[] myBuffer = new byte[1024];

            int myNumberOfBytesRead = pInputStream.read(myBuffer);

            while (myNumberOfBytesRead != -1) {
                myStringBuilder.append(new String(myBuffer).substring(0, myNumberOfBytesRead));
                myNumberOfBytesRead = pInputStream.read(myBuffer);
            }

            return myStringBuilder.toString();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                String email;
                String password;

                email = email_login.getText().toString();
                password = password_login.getText().toString();

                // Need to be checked for the order
                String data = URLEncoder.encode("email", "UTF-8") + "="
                        + URLEncoder.encode(email, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "="
                        + URLEncoder.encode(password, "UTF-8");

                // Need to be checked for address change
                final URL myUrl = new URL("http://" + serverAddress + ":" + serverPort + "/trojanow-web/ProfileService");

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

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
