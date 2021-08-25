package com.mobdeve.s13.group38.paws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TextView tvRegister;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private ProgressBar pbLogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initFirebase();
        this.initComponents();
    }

    private void initFirebase(){
        this.mAuth = FirebaseAuth.getInstance();
    }

    private void initComponents(){
        this.tvRegister = findViewById(R.id.tv_register_login);
        this.etEmail = findViewById(R.id.et_email_login);
        this.etPassword = findViewById(R.id.et_password_login);
        this.btnLogin = findViewById(R.id.btn_login);
        this.pbLogin = findViewById(R.id.pb_login);

        this.tvRegister.setOnClickListener(view->{
            Intent i = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(i);
        });

        this.btnLogin.setOnClickListener(view->{
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            signIn(email,password);
        });
    }

    private void signIn(String email, String password){
        this.pbLogin.setVisibility(View.VISIBLE);

        if(!email.isEmpty() && !password.isEmpty()) {
            this.mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                MainActivity.this.pbLogin.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
        else{
            MainActivity.this.pbLogin.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();
        }
    }
}