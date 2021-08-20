package com.example.vsis3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.example.vsis3.R;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputEditText edtEmail;
    private TextInputEditText edtSenha;
    private Button btnLogin;
    private Button btnCad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnCad = findViewById(R.id.btnCadastro);

        if(loggedUser()){
            openMainWindow();
        }
        else {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!edtEmail.getText().toString().equals("") && !edtSenha.getText().toString().equals("")) {
                        validateLogin(edtEmail.getText().toString(), edtSenha.getText().toString());
                    } else
                        Toast.makeText(LoginActivity.this, "Informe email e senha!", Toast.LENGTH_SHORT).show();
                }
            });
            btnCad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this, AddUser.class);
                    startActivity(intent);
                }
            });

            mAuth = FirebaseAuth.getInstance();
        }
    }

    private Boolean loggedUser(){
        Log.d("Login", "instance"+ FirebaseAuth.getInstance());
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser == null) return false;
        return true;
    }
    private void validateLogin(String email, String senha){

        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    openMainWindow();
                    Toast.makeText(LoginActivity.this, "sucesso!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Dados de login inválidos!", Toast.LENGTH_SHORT).show();
                    Log.d("LOGIN", "dados inválidos!");
                }

            }
        });

    }

    private void openMainWindow(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
