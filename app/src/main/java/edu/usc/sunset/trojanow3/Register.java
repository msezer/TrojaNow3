package edu.usc.sunset.trojanow3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.util.Log;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;

public class Register extends ActionBarActivity {

    EditText fullname_register;
    EditText email_register;
    EditText password_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname_register = (EditText) findViewById(R.id.reg_fullname);
        email_register = (EditText) findViewById(R.id.reg_email);
        password_register = (EditText) findViewById(R.id.reg_password);
    }

    // Registration process and link to the main page
    public void onClickRegisterProcess(View view) {
        Intent i = new Intent(this, Main.class);
        startActivity(i);

        /*
        String urlString;
        urlString = "http://104.32.183.230:8080/trojanow-web/Profile";
        Log.d("OnClicktoREGISTER", "Register Post Sent");
        new HttpRegisterPost().execute(urlString);
        */
    }

    // Link to login page
    public void onClickToLogin(View view) {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
/*
    private class HttpRegisterPost extends AsyncTask<String, String, String> {

        String fullname = fullname_register.getText().toString();
        String email = email_register.getText().toString();
        String password = password_register.getText().toString();

        private String readStream(InputStream is) {
            try {
                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                int i = is.read();
                while (i != -1) {
                    bo.write(i);
                    i = is.read();
                }
                return bo.toString();
            } catch (IOException e) {
                return "";
            }
        }

        @Override
        protected String doInBackground(String... params) {

            String urlString = params[0]; // URL to call

            final List<NameValuePair> myNameValuePairList = new ArrayList<NameValuePair>();

            myNameValuePairList.add(new BasicNameValuePair("fullname", "TEST_FULLNAME"));
            myNameValuePairList.add(new BasicNameValuePair("password", "TEST_PASSWORD"));
            myNameValuePairList.add(new BasicNameValuePair("email", "TEST_EMAIL"));
            try {

                final String myFailureAuthenticationResponse = doPost("AuthenticateService", myNameValuePairList);
                assertEquals(new JSONObject(myFailureAuthenticationResponse).get("status"), IResponse.Status.FAILURE.toString());

                final String myResponse;
                myResponse = doPost("ProfileService", myNameValuePairList);

                JSONObject myResponseJsonObject = new JSONObject(myResponse);

                assertEquals(myResponseJsonObject.get("status"), IResponse.Status.SUCCESS.toString());

                final String mySuccessAuthenticationResponse = doPost("AuthenticateService", myNameValuePairList);
                assertEquals(new JSONObject(mySuccessAuthenticationResponse).get("status"), IResponse.Status.SUCCESS.toString());
            } catch (JSONException e) {
                fail(e.getMessage());
            }
        }

        protected void onPostExecute(String result) {
            //Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            //Intent intent = new Intent(this, ResultActivity.class);
            //intent.putExtra(EXTRA_MESSAGE, result);
            //startActivity(intent);

            //fullname_register.setText("Registration Completed");
        }

    } // end CallAPI
    */
}
