package com.example.credence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class MainActivity2_1 extends AppCompatActivity {
    private Button B2;
    private Button B5;
    TextView warn1;
    FirebaseAuth mAuth;
    ProgressBar progress2;
    private EditText inputemail2, inputpassword2;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent =new Intent(MainActivity2_1.this, MainGoal1.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2_1);
        FirebaseApp.initializeApp(this);
        mAuth=FirebaseAuth.getInstance();
        B2=findViewById(R.id.button3);
        B5=findViewById(R.id.Signup3);
        inputemail2=findViewById(R.id.inputemail2);
        inputpassword2=findViewById(R.id.inputpassword2);
        progress2=findViewById(R.id.progress2);
        warn1=findViewById(R.id.warn1);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2_1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress2.setVisibility(View.VISIBLE);
                String email = inputemail2.getText().toString();
                String password = inputpassword2.getText().toString();

                if (email.isEmpty() || !email.contains("@")) {
                    showError(inputemail2, "Email is not valid");
                    Toast.makeText(MainActivity2_1.this, "Error!", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (password.isEmpty() || password.length()<7) {
                    showError(inputpassword2, "Password must have 7 characters");
                    Toast.makeText(MainActivity2_1.this, "Error!", Toast.LENGTH_SHORT).show();
                    return;

                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progress2.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity2_1.this, "Login Successful!",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent =new Intent(MainActivity2_1.this, MainActivity3_1.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    warn1.setVisibility(View.VISIBLE);
                                    Toast.makeText(MainActivity2_1.this, "Authentication Failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2_1.this, "Opening Signup Page!", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MainActivity2_1.this, MainActivity2.class);
                startActivity(intent);
            }
        });

    }



    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }


}