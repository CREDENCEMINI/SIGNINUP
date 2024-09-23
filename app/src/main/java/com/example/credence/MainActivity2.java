package com.example.credence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    private Button B1;
    private Button B6;
    FirebaseAuth mAuth;
    ProgressBar progress1;
    private EditText inputname1, inputphone1, inputemail1, inputpassword1;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent =new Intent(MainActivity2.this, MainGoal1.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        FirebaseApp.initializeApp(this);
        mAuth=FirebaseAuth.getInstance();
        B1=findViewById(R.id.Signup1);
        B6=findViewById(R.id.LogIn1);
        inputname1=findViewById(R.id.inputname1);
        inputphone1=findViewById(R.id.inputphone1);
        inputemail1=findViewById(R.id.inputemail1);
        inputpassword1=findViewById(R.id.inputpassword1);
        progress1=findViewById(R.id.progress1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress1.setVisibility(View.VISIBLE);
                String name = String.valueOf(inputname1.getText());
                String phone = String.valueOf(inputphone1.getText());
                String email = String.valueOf(inputemail1.getText());
                String password = String.valueOf(inputpassword1.getText());

                if(name.isEmpty() || name.length()<7){
                    showError(inputname1, "Username is not valid");
                    Toast.makeText(MainActivity2.this, "Error!", Toast.LENGTH_SHORT).show();
                    return;

                } if (phone.isEmpty() || phone.length()!=10) {
                    showError(inputphone1, "Enter a valid phone number");
                    Toast.makeText(MainActivity2.this, "Error!", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (email.isEmpty() || !email.contains("@")) {
                    showError(inputemail1, "Email is not valid");
                    Toast.makeText(MainActivity2.this, "Error!", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (password.isEmpty() || password.length()<7) {
                    showError(inputpassword1, "Password must have 7 characters");
                    Toast.makeText(MainActivity2.this, "Error!", Toast.LENGTH_SHORT).show();
                    return;

                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progress1.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity2.this, "Account Created!",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent =new Intent(MainActivity2.this, MainActivity3.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(MainActivity2.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });




        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Opening Login Page!", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MainActivity2.this, MainActivity2_1.class);
                startActivity(intent);
                finish();
            }
        });
    }



    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();

    }

} 