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

public class MainActivity2_1 extends AppCompatActivity {
    private Button B2;
    private Button B5;
    private EditText inputemail2, inputpassword2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2_1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2_1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        B2=findViewById(R.id.button3);
        inputemail2=findViewById(R.id.inputemail2);
        inputpassword2=findViewById(R.id.inputpassword2);

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCrendentials();

            }
        });
        B5=findViewById(R.id.Signup3);
        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2_1.this, "Opening Signup Page!", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MainActivity2_1.this, MainActivity2.class);
                startActivity(intent);
            }
        });

    }

    private void checkCrendentials() {
        String email = inputemail2.getText().toString();
        String password = inputpassword2.getText().toString();

        if (email.isEmpty() || !email.contains("@")) {
            showError(inputemail2, "Email is not valid");
            Toast.makeText(this,"Error!",Toast.LENGTH_SHORT).show();
            return;

        }
        if (password.isEmpty() || password.length()<7) {
            showError(inputpassword2, "Password must have 7 characters");
            Toast.makeText(this,"Error!",Toast.LENGTH_SHORT).show();
            return;

        }
        Toast.makeText(this,"Completed!",Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(MainActivity2_1.this, MainActivity3_1.class);
        startActivity(intent);

    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }


}