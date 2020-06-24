/*
 * Create by Hamza elkhatri
 */

/*
 * Create by Hamza elkhatri
 */

/*
 * Create by Hamza elkhatri
 */

package com.example.netflix.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.netflix.R;
import com.example.netflix.constants.constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText UserName;
    private EditText Password;
    private EditText email;
    private Button btnRegister;
    private ProgressDialog ProgressD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserName=(EditText)findViewById(R.id.UserName);
        Password=(EditText)findViewById(R.id.password);
        email = (EditText)findViewById(R.id.email);
        btnRegister=(Button)findViewById(R.id.clickRegis);
        ProgressD=new ProgressDialog(this);
        btnRegister.setOnClickListener(this);
    }
    void registerUser()
    {
        final String userName = UserName.getText().toString();
        final String password=Password.getText().toString();
        final String Email=email.getText().toString();
        ProgressD.setMessage("Please Wait a moment ...");
        ProgressD.show();
        StringRequest Request=new StringRequest(com.android.volley.Request.Method.POST, constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            ProgressD.dismiss();
                try {
                    JSONObject jso =new JSONObject(response);
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
                Map<String,String> parmas=new HashMap<>();
                parmas.put("userName",userName);
                parmas.put("password",password);
                parmas.put("Email",Email);
                return parmas;
            }

        };
        RequestQueue   requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(Request);
    }
    public void onClick(View v)
    {
        if(v==btnRegister)
        {
            registerUser();
        }
    }
}
