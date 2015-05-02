package edu.usc.sunset.trojanow3;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

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

        // read properties file
        resources = this.getResources();
        assetManager = resources.getAssets();

        try {
            inputStream = assetManager.open("local.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            Log.w("PROPERTIES_LOG_", "The properties for login are now loaded");
            serverAddress = properties.getProperty("proxyHost");
            serverPort = properties.getProperty("proxyPort");
            Log.w("PROPERTIES_LOG_", serverAddress + ":" + serverPort);
        } catch (IOException e) {
            System.err.println("Failed to open log property file");
            e.printStackTrace();
        }
    }

    // Login Successful, to main page
    public void onClickLoginProcess(View view) {
        // login user

        Context context = getApplicationContext();
        CharSequence text;
        int duration = Toast.LENGTH_SHORT;
        Toast toast;

        if (email_login.getText().toString().equals("")) {
            Log.w("ONPOST.LOGIN : ", "FIELDS EMPTY, FAIL");
            password_login.setText("");

            // Show Toast
            text = "Please enter your email";
            toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        } else if (password_login.getText().toString().equals("")) {
            Log.w("ONPOST.LOGIN : ", "FIELDS EMPTY, FAIL");
            password_login.setText("");

            // Show Toast
            text = "Please enter your password.";
            toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();

        } else {
            new HttpLoginPost().execute("");
        }
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

    //private static
    class HttpLoginPost extends AsyncTask<String, String, String> {

        //static
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

        @Override
        protected String doInBackground(String... strings) {
            String myUserId = "-1";
            try {
                String email;
                String password;
                email = email_login.getText().toString();
                password = password_login.getText().toString();

                String data = URLEncoder.encode("email", "UTF-8") + "=" +
                        URLEncoder.encode(email, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(password, "UTF-8");

                final URL myUrl = new URL("http://" + serverAddress + ":" + serverPort + "/trojanow-web/AuthenticateService");
                HttpURLConnection myConnection = (HttpURLConnection) myUrl.openConnection();
                myConnection.setRequestMethod("POST");
                myConnection.setDoOutput(true);
                myConnection.setDoInput(true);
                OutputStreamWriter wr = new OutputStreamWriter(myConnection.getOutputStream());
                wr.write(data);
                wr.flush();
                wr.close();
                myConnection.connect();

                final InputStream myInputStream = myConnection.getInputStream();
                final String myJsonString = toString(myInputStream);
                try {
                    final String uID = new JSONObject(myJsonString).getString("userId");
                    myUserId = uID;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                myConnection.getInputStream();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return myUserId;
        }

        protected void onPostExecute(String id) {

            long user_id = Long.parseLong(id);

            Context context = getApplicationContext();
            CharSequence text;
            int duration = Toast.LENGTH_SHORT;
            Toast toast;

            if (user_id == (-1) || user_id == 0) {
                Log.w("ONPOST.LOGIN : ", "AUTHENTICATION FAIL");
                password_login.setText("");

                // Show Toast
                text = "An error occured while authenticating the user. Check your email and password.";
                toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            } else {
                Log.w("ONPOST.LOGIN.AUTH : ", "SUCCESS");

                // Show Toast
                text = "Logging you in ...";
                toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();

                // Launch Main Page Screen
                Intent mainpage = new Intent(getApplicationContext(), Main.class);

                GlobalVariables.getInstance().setUserId(id);

                // Close all views before launching Main Page
                mainpage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainpage);

                // Close Login Screen
                finish();
            }
        }
    }
}
