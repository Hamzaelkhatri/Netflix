/*
 * Create by Hamza elkhatri
 */

/*
 * Create by Hamza elkhatri
 */

package com.example.netflix;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.netflix.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login_main extends AppCompatActivity implements View.OnClickListener {

    Button sign;
    EditText email;
    EditText password;
    ProgressDialog ProgressD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        sign=(Button)findViewById(R.id.SignIn);
        email=(EditText) findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        ProgressD = new ProgressDialog(this);
        sign.setOnClickListener(this);
    }

    private void loginUser()
    {
        final String Email=email.getText().toString();
        final String Password=password.getText().toString();
        constants.UserName = Email;
        StringRequest request = new StringRequest(StringRequest.Method.POST,constants.URL_LOGIN,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ProgressD.dismiss();
                        try {
                            JSONObject jso =new JSONObject(response);
                            if(jso.getString("message").equals("Welcome"))
                            {
                                Toast.makeText(getApplicationContext(),"GOOD",Toast.LENGTH_LONG).show();
                                Intent i =new Intent(login_main.this,avatar.class);
                                startActivity(i);
                            }
                            else
                                Toast.makeText(getApplicationContext(),"Please Check your email/password",Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),jso.getString("message"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ProgressD.hide();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("Email",Email);
                params.put("password",Password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void onClick(View v)
    {
        if(v == sign)
        {
            loginUser();
        }
    }
}
