package com.rich.stylespace.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rich.stylespace.Helper.HelperClass;
import com.rich.stylespace.R;

public class RegisterActivity extends AppCompatActivity {

    EditText signupName, signupEmail, signupPassword, signupConPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupConPassword = findViewById(R.id.signup_confirm);

        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String password = signupPassword.getText().toString();
                String confirmPassword = signupConPassword.getText().toString();

                if (!password.equals(confirmPassword)) {
                    signupConPassword.setError("Passwords do not match");
                    signupConPassword.requestFocus();
                } else {
                    database = FirebaseDatabase.getInstance();
                    reference = database.getReference("users");

                    HelperClass helperClass = new HelperClass(name, email, password);
                    reference.child(name).setValue(helperClass);

                    Toast.makeText(RegisterActivity.this, "SignUp Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}