package com.example.doggyout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText Email,Password;
    Button Login_Button;
    Button Register_Button;
    String server_url = "http://192.168.64.2/dbconfig.php";
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login_Button = (Button) findViewById(R.id.login_button);
        Register_Button = (Button) findViewById(R.id.register_button);

        Email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        Password = (EditText) findViewById(R.id.editTextTextPassword);
        builder = new AlertDialog.Builder(MainActivity.this);
        Register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),RegisterPage.class);
                startActivity(startIntent);
            }
        });
        Login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email,password;
                email = Email.getText().toString();
                password = Password.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        builder.setTitle("Server Response");
                        builder.setMessage("Response :" + response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Email.setText("");
                                Password.setText("");
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
                        ,new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "some error found .....", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map <String,String> Params = new HashMap<String, String>();
                        Params.put("email",email);
                        Params.put("password",password);
                        return Params;

                    }
                };
                Mysingleton.getInstance(MainActivity.this).addTorequestque(stringRequest);
            }
        });
    }
}