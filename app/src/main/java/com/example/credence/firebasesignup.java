package com.example.credence;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class firebasesignup extends AppCompatActivity {

    private DatabaseReference databaseReference;

    // UI Elements
    private TextView fullNameTextView;
    private TextView phoneNumberTextView;
    private TextView emailTextView;
    private TextView passwordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2); // Ensure this matches your XML layout file name

        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("Users"); // Adjust "Users" to your node name

        // Initialize UI elements
        fullNameTextView = findViewById(R.id.inputname1);
        phoneNumberTextView = findViewById(R.id.inputphone1);
        emailTextView = findViewById(R.id.inputemail1);
        passwordTextView = findViewById(R.id.inputpassword1);

        // Button to fetch data
        Button fetchDataButton = findViewById(R.id.Signup1); // Assuming you want to fetch data on button click
        fetchDataButton.setOnClickListener(v -> fetchUserData());
    }

    private void fetchUserData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String fullName = userSnapshot.child("fullName").getValue(String.class);
                        String phoneNumber = userSnapshot.child("phoneNumber").getValue(String.class);
                        String email = userSnapshot.child("email").getValue(String.class);
                        String password = userSnapshot.child("password").getValue(String.class);

                        // Set data to UI elements
                        fullNameTextView.setText(fullName);
                        phoneNumberTextView.setText(phoneNumber);
                        emailTextView.setText(email);
                        passwordTextView.setText(password);
                    }
                } else {
                    Toast.makeText(firebasesignup.this, "No data found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(firebasesignup.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
