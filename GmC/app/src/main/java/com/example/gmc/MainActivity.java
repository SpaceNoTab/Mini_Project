package com.example.gmc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class MainActivity extends Activity {
    TextView cont;
    EditText name,password;
    String Name, Password;
    Context ctx = this;
    String NAME = null, PASSWORD = null, EMAIL = null;
    Button button,button1;
    String err = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        name = (EditText) findViewById(R.id.main_name);
        password = (EditText) findViewById(R.id.main_password);


        button1 = (Button) findViewById(R.id.main_request);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this, RequestBlood.class);
                Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        cont = (TextView) findViewById(R.id.contact);
        cont.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this, Contact.class);
                Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


        button = (Button) findViewById(R.id.main_login);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Name = name.getText().toString();
                Password = password.getText().toString();
                if (Name.equals("") || Password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_LONG).show();
                    return;

                } else {


                    BackGround b = new BackGround();
                    b.execute(Name, Password);
                /*    String s=NAME;
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();

*/


                }
            }
        });
    }


    public void main_register(View v) {
        startActivity(new Intent(this, Register.class));
    }


    class BackGround extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String data = "";
            int tmp;

            try {
                URL url = new URL("http://192.168.43.87/blood/login.php");
                String urlParams = "name=" + name + "&password=" + password;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while ((tmp = is.read()) != -1) {
                    data += (char) tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                NAME = user_data.getString("name");
                PASSWORD = user_data.getString("password");
                EMAIL = user_data.getString("email");

            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }



            // Staring MainActivity
           // Intent i = new Intent(getApplicationContext(), ProfileLog.class);
            //startActivity(i);
            pdLoading.dismiss();


            if (Name.equals(NAME)  && Password.equals(PASSWORD)) {

                Intent i = new Intent(ctx, ProfileLog.class);
                i.putExtra("name", NAME);
                i.putExtra("password", PASSWORD);
                i.putExtra("email", EMAIL);
                i.putExtra("err", err);
                String e=NAME;
                Toast.makeText(getApplicationContext(), e, Toast.LENGTH_LONG).show();


                startActivity(i);
                finish();
            } else if( Name.equals(null) || Password.equals(null)) {

                Toast.makeText(getApplicationContext(), "error null vaues", Toast.LENGTH_LONG).show();
                return;



            }
            else{
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                return;

            }

        }
    }
}


