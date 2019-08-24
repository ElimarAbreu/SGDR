package com.example.cm_sgdr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        Button cadastrar = (Button) findViewById(R.id.button_login_cadastrar);
        Button entrar = (Button) findViewById(R.id.button_login_entrar);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MainActivity_login.this, MainActivity_cadastro1.class);
                startActivity(it);
            }
        });

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tLogin = (TextView) findViewById(R.id.editText_login_email);
                TextView tSenha = (TextView) findViewById(R.id.editText_login_senha);
                String login = tLogin.getText().toString();
                String senha = tSenha.getText().toString();
                if (login.equals("joao1@gmail.com")&&senha.equals("123456")){
                    alert("Login realizado com sucesso");
                    Intent it = new Intent(MainActivity_login.this, MainActivity_principal.class);
                    startActivity(it);
                }else{
                    alert("Login ou senha incorretos");
                }
            }
        });
    }

    public void alert(String s){
        Toast.makeText(this,s, Toast.LENGTH_LONG).show();
    }

}