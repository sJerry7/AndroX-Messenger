package com.example.androxmessenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androxmessenger.Models.Users;
import com.example.androxmessenger.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();   // Firebase authorization object which is only used to create a new user object or authenticate old user
        database = FirebaseDatabase.getInstance();      // Firebase Database object which is used to store all users data in our database(using UID)
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Creating Account");
        pd.setMessage("We are creating your account...");

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 pd.show();
                 mAuth.createUserWithEmailAndPassword(binding.etEmail.getText().toString(),binding.etPassword.getText().toString())
                          .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task) {
                                 pd.dismiss();
                                 if(task.isSuccessful())
                                 {
                                     Toast.makeText(SignUpActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                                     Users user = new Users(binding.etUsername.getText().toString(),
                                             binding.etEmail.getText().toString(),binding.etPassword.getText().toString());

                                     String id = task.getResult().getUser().getUid();
                                     database.getReference().child("Users").child(id).setValue(user);
                                 }
                                 else
                                     {
                                     Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                 }
                             }
                         });
                 //Now here we need to use Models So first create Models of name "Users"
            }
        });
        binding.tvClickLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(i1);
            }
        });

    }
}