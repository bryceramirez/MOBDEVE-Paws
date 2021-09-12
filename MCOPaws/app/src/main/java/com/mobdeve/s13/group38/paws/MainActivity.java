package com.mobdeve.s13.group38.paws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "100";
    //    private static final String TAG = ;
    private TextView tvRegister;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private ProgressBar pbLogin;

    private FirebaseAuth mAuth;
    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 444;
    private ImageButton ibGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initFirebase();
        this.initComponents();
        this.googleSignIn();

//        ibGoogle.setOnClickListener(new View.onClickListener());
    }

    private void initFirebase(){
        this.mAuth = FirebaseAuth.getInstance();
    }

    private void initComponents(){
        this.ibGoogle = findViewById(R.id.btn_google);
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


        this.ibGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInGoogle();
            }
        });
    }

    private void googleSignIn(){
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }
    private void signInGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }



    private void firebaseAuthWithGoogle(String idToken) {
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