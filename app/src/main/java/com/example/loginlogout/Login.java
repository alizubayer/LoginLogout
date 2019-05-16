package com.example.loginlogout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText password1,username1;
    private Button login1;
    String user,pass;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        password1 = (EditText)findViewById(R.id.password1);
        username1 = (EditText) findViewById(R.id.username1);
        login1 = (Button) findViewById(R.id.login1);

        myDatabaseHelper = new MyDatabaseHelper(this);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = username1.getText().toString();
                pass = password1.getText().toString();

                if(user.isEmpty())
                {
                    Toast.makeText(Login.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                }

                else if (pass.isEmpty())
                {
                    Toast.makeText(Login.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Boolean result = myDatabaseHelper.findPassword(user,pass);

                    if(result==true)
                    {
                        Intent intent = new Intent(Login.this,HomeActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(Login.this, "Wrong Username & Password", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });
    }
}
