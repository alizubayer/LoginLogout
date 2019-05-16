package com.example.loginlogout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText name,email,username,password,cpassword;
    private Button singup,login;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);

        myDatabaseHelper = new MyDatabaseHelper(this);



        singup = findViewById(R.id.singup);
        login = findViewById(R.id.login);

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getText().toString();
                String Email = email.getText().toString();
                String User_Name = username.getText().toString();
                String PassWord = password.getText().toString();
                String Confirm_PassWord = cpassword.getText().toString();

                if (Name.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Enter Your Name", Toast.LENGTH_SHORT).show();

                }
                else if (Email.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
                }
                else if (User_Name.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Enter Your User Name", Toast.LENGTH_SHORT).show();
                }
                else if (PassWord.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Enter Your PassWord", Toast.LENGTH_SHORT).show();
                }
                else if (!Confirm_PassWord.equalsIgnoreCase(PassWord))
                {
                    Toast.makeText(MainActivity.this, "Don't Match Your PassWord", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    long rowid = myDatabaseHelper.insertData(Name,Email,User_Name,PassWord,Confirm_PassWord);
                    if (rowid==-1)
                    {
                        Toast.makeText(MainActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                    }
                }
                name.setText("");
                email.setText("");
                username.setText("");
                password.setText("");
                cpassword.setText("");

                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);

            }


        });
    }
}
