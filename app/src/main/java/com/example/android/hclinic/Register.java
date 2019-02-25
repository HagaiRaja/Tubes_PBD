package com.example.android.hclinic;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    Button b1;
    EditText ed1, ed2, ed3;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        b1 = (Button) findViewById(R.id.button);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed2 = (EditText) findViewById(R.id.editText2);


// ...
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();



        mAuth.createUserWithEmailAndPassword(ed2.getText().toString(),ed3.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Register", "signInWithCustomToken:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Register", "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }

        @Override
        public void onStart() {
            super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = mAuth.getCurrentUser();
        }
}

