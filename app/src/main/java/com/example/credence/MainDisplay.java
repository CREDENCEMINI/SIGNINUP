package com.example.credence;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainDisplay extends AppCompatActivity {
    /*GoogleSignInOptions gso;
    GoogleSignInClient gsc;*/
    FirebaseAuth auth;
    private Button B6;
    TextView displaytext,displayname;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display);
        auth=FirebaseAuth.getInstance();
        B6=findViewById(R.id.Logout1);
        displayname = findViewById(R.id.displayname);
        displaytext = findViewById(R.id.displaytext);
        /*gso =new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct!=null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            displayname.setText(personName);
            displaytext.setText(personEmail);
        }*/

        user = auth.getCurrentUser();
        if(user==null){
            Intent intent =new Intent(getApplicationContext(), MainActivity2_1_may.class);
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
                Intent intent =new Intent(MainDisplay.this, MainActivity_may.class);
                startActivity(intent);
                finish();
            /*signout();*/
            }
        });
    }

    /*private void signout() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete( Task<Void> task) {
                finish();
                startActivity(new Intent(MainDisplay.this,MainActivity_may.class));
            }
        });
    }*/
}
