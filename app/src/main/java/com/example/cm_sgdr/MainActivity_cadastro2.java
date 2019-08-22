package com.example.cm_sgdr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class MainActivity_cadastro2 extends AppCompatActivity {

    private EditText cpf, banco, agencia, operacao, conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro2);

        Button confirmar = (Button) findViewById(R.id.button_cadastro2_confirmar);
        cpf = (EditText) findViewById(R.id.editText_cadastro2_cpf);
        banco = (EditText) findViewById(R.id.editText_cadastro2_banco);
        agencia = (EditText) findViewById(R.id.editText_cadastro2_agencia);
        operacao = (EditText) findViewById(R.id.editText_cadastro2_op);
        conta = (EditText) findViewById(R.id.editText_cadastro2_conta);

        confirmar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(cpf.getText().toString().isEmpty() || banco.getText().toString().isEmpty() || agencia.getText().toString().isEmpty() || operacao.getText().toString().isEmpty() || conta.getText().toString().isEmpty()){
                    makeText(MainActivity_cadastro2.this, "Preencha todos os campos!", LENGTH_LONG).show();
                }else {
                    Intent it = new Intent(MainActivity_cadastro2.this, MainActivity_principal.class);
                    startActivity(it);
                }
            }
        });
    }
}