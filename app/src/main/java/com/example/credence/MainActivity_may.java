package com.example.credence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity_may extends AppCompatActivity {
    private Button BUTTONLOGIN;
    private Button BUTTONSIGNUP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_may);
        BUTTONLOGIN=findViewById(R.id.BUTTONLOGIN);
        BUTTONLOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity_may.this, "Opening Login Page", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity_may.this, MainActivity2_1_may.class);
                startActivity(intent);
            }
        });
        BUTTONSIGNUP=findViewById(R.id.BUTTONSIGNUP);
        BUTTONSIGNUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity_may.this, "Opening Signup Page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity_may.this , MainActivity2_may.class);
                startActivity(intent);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    /*public void openActivity(View v){
        Toast.makeText(this, "Opening Signup Page", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity2_may.class);
        startActivity(intent);
        finish();
    }*/
}