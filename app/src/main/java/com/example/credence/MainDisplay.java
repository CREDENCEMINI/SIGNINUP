package com.example.credence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
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

public class MainDisplay extends AppCompatActivity {
    FirebaseAuth auth;
    private Button B6;
    TextView displaytext;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display);
        auth=FirebaseAuth.getInstance();
        B6=findViewById(R.id.Logout1);
        displaytext = findViewById(R.id.displaytext);
        user = auth.getCurrentUser();
        if(user==null){
            Intent intent =new Intent(getApplicationContext(), MainActivity2_1.class);
            startActivity(intent);
            finish();
        }
        else{
            displaytext.setText(user.getEmail());
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.display), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent =new Intent(MainDisplay.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
