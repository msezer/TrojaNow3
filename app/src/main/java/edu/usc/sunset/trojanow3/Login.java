package edu.usc.sunset.trojanow3;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Login extends ActionBarActivity {

    EditText emailFunc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailFunc = (EditText) findViewById(R.id.login_email);
    }

    //EditText emailFunc = (EditText) findViewById(R.id.login_email);
    //public final static String EXTRA_MESSAGE = "E.MESAAGE_";

    // Login Successful, to main page
    public void onClickLoginProcess(View view) {
        //Intent i = new Intent(this, Main.class);
        //startActivity(i);

        Log.d("OnClicktoLOGIN", "Http req sent");
        String urlString;
        urlString = "http://hmkcode.com/examples/index.php";
        new CallAPI().execute(urlString);
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

    private class CallAPI extends AsyncTask<String, String, String> {

        private String convertInputStreamToString(InputStream inputStream) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while ((line = bufferedReader.readLine()) != null)
                result += line;
            inputStream.close();
            return result;
        }

        @Override
        protected String doInBackground(String... params) {

            String urlString = params[0]; // URL to call
            InputStream inputStream = null;
            String resultToDisplay = "";

            // HTTP Get
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                resultToDisplay = convertInputStreamToString(inputStream);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }
            return resultToDisplay;
        }

        protected void onPostExecute(String result) {
            //Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            //Intent intent = new Intent(this, ResultActivity.class);
            //intent.putExtra(EXTRA_MESSAGE, result);
            //startActivity(intent);

            emailFunc.setText(result);
        }

    } // end CallAPI

    // verifyEmail method should be called after login process
    public void verifyEmail(View view) {
        //EditText emailEditText = (EditText) findViewById(R.id.login_email);
        //String email = emailEditText.getText().toString();
        //EditText passwordEditText = (EditText) findViewById(R.id.login_password);
        //String password = emailEditText.getText().toString();

        //if (email != null && !email.isEmpty() && password != null && !password.isEmpty()) {
            //String urlString = "http://hmkcode.com/examples/index.php";
            /*urlString = apiURL + "LicenseInfo.RegisteredUser.UserID=" + strikeIronUserName
                    + "&LicenseInfo.RegisteredUser.Password=" + strikeIronPassword + "&VerifyEmail.Email="
                    + email + "&VerifyEmail.Timeout=30";
            */
            //new CallAPI().execute(urlString);
        //}
    }
}
