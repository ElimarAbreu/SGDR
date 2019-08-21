package com.example.cm_sgdr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class MainActivity_cadastro1 extends AppCompatActivity {

    private EditText nome, usuario, senha, csenha, email, codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro1);

        Button proximo = (Button) findViewById(R.id.button_cadastro1_proximo);
        nome = (EditText) findViewById(R.id.editText_cadastro1_nome);
        usuario = (EditText) findViewById(R.id.editText_cadastro1_usuario);
        senha = (EditText) findViewById(R.id.editText_cadastro1_senha);
        csenha = (EditText) findViewById(R.id.editText_cadastro1_confirsenha);
        email = (EditText) findViewById(R.id.editText_cadastro1_email);
        codigo = (EditText) findViewById(R.id.editText_cadastro1_codigo);

        Spinner spinner_republica = (Spinner) findViewById(R.id.spinner_cadastro1_republica);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.menu, android.R.layout.simple_spinner_item);
        spinner_republica.setAdapter(adapter);

        spinner_republica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    codigo.setText(RandomStringUtils.random(8, "0123456789abcdef"));
                }
                else{
                    codigo.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        proximo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(nome.getText().toString().isEmpty() || usuario.getText().toString().isEmpty() || senha.getText().toString().isEmpty() || csenha.getText().toString().isEmpty() || email.getText().toString().isEmpty() || codigo.getText().toString().isEmpty()){
                    makeText(MainActivity_cadastro1.this, "Preencha todos os campos!", LENGTH_LONG).show();
                }
                else {
                    Intent it = new Intent(MainActivity_cadastro1.this, MainActivity_cadastro2.class);
                    startActivity(it);
                }
            }
        });
    }
}