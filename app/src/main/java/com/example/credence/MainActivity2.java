package com.example.credence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    private Button B1;
    private Button B6;
    private EditText inputname1, inputphone1, inputemail1, inputpassword1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        B1=findViewById(R.id.Signup1);
        inputname1=findViewById(R.id.inputname1);
        inputphone1=findViewById(R.id.inputphone1);
        inputemail1=findViewById(R.id.inputemail1);
        inputpassword1=findViewById(R.id.inputpassword1);





        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCrendentials();

            }
        });



        B6=findViewById(R.id.LogIn1);
        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Opening Login Page!", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MainActivity2.this, MainActivity2_1.class);
                startActivity(intent);
            }
        });
    }

    private void checkCrendentials() {
        String name = inputname1.getText().toString();
        String phone = inputphone1.getText().toString();
        String email = inputemail1.getText().toString();
        String password = inputpassword1.getText().toString();

        if(name.isEmpty() || name.length()<7){
            showError(inputname1, "Username is not valid");
            Toast.makeText(this,"Error!",Toast.LENGTH_SHORT).show();
            return;

        } if (phone.isEmpty() || phone.length()!=10) {
            showError(inputphone1, "Enter a valid phone number");
            Toast.makeText(this,"Error!",Toast.LENGTH_SHORT).show();
            return;

        }
            if (email.isEmpty() || !email.contains("@")) {
            showError(inputemail1, "Email is not valid");
                Toast.makeText(this,"Error!",Toast.LENGTH_SHORT).show();
            return;

        }
            if (password.isEmpty() || password.length()<7) {
                showError(inputpassword1, "Password must have 7 characters");
                Toast.makeText(this,"Error!",Toast.LENGTH_SHORT).show();
                return;

            }
                Toast.makeText(this,"Completed!",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);

    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();

    }

} 