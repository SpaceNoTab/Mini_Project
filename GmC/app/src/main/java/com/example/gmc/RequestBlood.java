package com.example.gmc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.attr.name;
import static android.R.attr.password;

public class RequestBlood extends AppCompatActivity {
    EditText name, phone;
    String Name, PhoneNumber, group,Email;
    Context ctx = this;
   Button b1;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_blood);

        name = (EditText) findViewById(R.id.req_name);
        phone = (EditText) findViewById(R.id.req_phone);
         final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Blood_Group, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        // group = spinner.getSelectedItem().toString();
           Toast.makeText(ctx, group, Toast.LENGTH_LONG).show();

        b1 = (Button) findViewById(R.id.pro_req);
        b1.setOnClickListener(new View.OnClickListener() {
                                  public void onClick(View v) {



                                      Name = name.getText().toString();
                                      PhoneNumber = phone.getText().toString();
                                      group = spinner.getSelectedItem().toString();

                                      RequestBlood.BackGround b = new BackGround();
                                      b.execute(Name,PhoneNumber,group);

                                  }
                              });
                TextView click = (TextView) findViewById(R.id.click_here);
        click.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(RequestBlood.this).create();
                alertDialog.setTitle("Caution");
                alertDialog.setMessage("You are about to contact the bloodbank of Govt Medical College Manjeri ");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Uri number = Uri.parse("tel:8593078460");
                        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                        startActivity(callIntent);
                        dialog.dismiss();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.setIcon(R.drawable.caution);
                alertDialog.show();
            }
        });
    }



    class BackGround extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String phone = params[1];
            String group = params[2];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://192.168.43.87/blood/request.php");
                String urlParams = "name="+name+"&phone="+phone+"&group="+group;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }
                is.close();
                httpURLConnection.disconnect();

                return data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("")){
                s="Data saved successfully.";
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();

            AlertDialog alertDialog;
            alertDialog = new AlertDialog.Builder(RequestBlood.this).create();
            alertDialog.setTitle("Successful");
            alertDialog.setMessage("Your Request is Successful ");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK" , new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    name.setText("");

                    phone.setText("");
                    startActivity(new Intent(RequestBlood.this,MainActivity.class));
                    dialog.dismiss();


                }
            });
            alertDialog.setIcon(R.drawable.person);
            alertDialog.show();




        }
    }

}