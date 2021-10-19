package com.example.doggyout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class RegisterPage extends AppCompatActivity {
    EditText UserId,Password,First_name,Last_name,Date_of_birth,Gender,Country,State,City,Street,Home_number,Phone_number,Email,Introduction_text;
    AlertDialog.Builder builder;
    Button RegButton;
    String server_url = "http://192.168.64.2/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        RegButton = (Button)findViewById(R.id.register_button);

        //UserId = (EditText) findViewById(R.id.RegEmail);
        Email = (EditText)findViewById(R.id.RegEmail);
        Password = (EditText)findViewById(R.id.RegPassword);
        First_name = (EditText)findViewById(R.id.FirstNameReg);
        Last_name = (EditText)findViewById(R.id.SecondNameReg);
        Date_of_birth = (EditText)findViewById(R.id.RegDate);
        Gender = (EditText)findViewById(R.id.GenderReg);
        Country = (EditText)findViewById(R.id.CountryReg);
        State = (EditText)findViewById(R.id.StateReg);
        City = (EditText)findViewById(R.id.CityReg);
        Street = (EditText)findViewById(R.id.StreetReg);
        //Home_number = (EditText)findViewById(R.id.RegEmail);
        Phone_number = (EditText)findViewById(R.id.RegPhone);
        //Introduction_text = (EditText)findViewById(R.id.RegEmail);
        builder = new AlertDialog.Builder(RegisterPage.this);
        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userId,password,first_name,last_name,date_of_birth,gender,country,state,city,street,home_number,phone_number,email,introduction_text;
                email = Email.getText().toString();
                password = Password.getText().toString();
                userId = UserId.getText().toString();
                first_name = First_name.getText().toString();
                last_name = Last_name.getText().toString();
                date_of_birth = Date_of_birth.getText().toString();
                gender = Gender.getText().toString();
                country = Country.getText().toString();
                state = State.getText().toString();
                city = City.getText().toString();
                street = Street.getText().toString();
                home_number = Home_number.getText().toString();
                phone_number = Phone_number.getText().toString();
                introduction_text = Introduction_text.getText().toString();

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
                        Toast.makeText(RegisterPage.this, "some error found .....", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map <String,String, String> Params = new HashMap<String, String, String>();
                        Params.put("email",email);
                        Params.put("password",password);
                        Params.put("first_name",first_name);
                        return Params;

                    }
                };
                Mysingleton.getInstance(RegisterPage.this).addTorequestque(stringRequest);
            }
        });
    }
}