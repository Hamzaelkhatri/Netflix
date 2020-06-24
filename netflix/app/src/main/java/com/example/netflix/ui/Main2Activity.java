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

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.netflix.R;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button signUp;
    private TextView signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        signUp=(Button)findViewById(R.id.SignUp);
        signIn=(TextView) findViewById(R.id.SignIn);
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        if(v==signIn)
        {
            Intent i =new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else if (v==signUp )
        {
            Intent i = new Intent(this, login_main.class);
            startActivity(i);
        }
    }
}
