package com.example.credence;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainGoal1_may extends AppCompatActivity {
    private Button B7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activitygoal1_may);
        B7=findViewById(R.id.GotIt);
        /*String name =getIntent().getStringExtra("nameUser");
        String phone =getIntent().getStringExtra("phoneUser");
        String email =getIntent().getStringExtra("emailUser");*/
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainGoal1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        B7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainGoal1_may.this, MainActivity_may_val.class);
                /*intent.putExtra("nameUser",name);
                intent.putExtra("phoneUser",phone);
                intent.putExtra("emailUser",email);*/
                startActivity(intent);
                finish();
            }
        });
    }
}
