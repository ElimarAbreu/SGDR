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

    EditText codigo, Nome, user, Senha, c_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastro1);

        Button proximo = (Button) findViewById(R.id.button_cadastro1_proximo);
        Nome = (EditText) findViewById(R.id.editText_cadastro1_nome);
        user = (EditText) findViewById(R.id.editText_cadastro1_usuario);
        Senha = (EditText) findViewById(R.id.editText_cadastro1_senha);
        c_senha = (EditText) findViewById(R.id.editText_cadastro1_confirsenha);
        codigo = (EditText) findViewById(R.id.editText_cadastro1_codigo);

        codigo.setText("");
        c_senha.setText("");
        Senha.setText("");
        user.setText("");
        Nome.setText("");

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

                if(codigo.toString().equals("") | Nome.toString().equals("") | user.toString().equals("") | Senha.toString().equals("") | c_senha.toString().equals("")){
                    makeText(MainActivity_cadastro1.this, "Informações Incorretas", LENGTH_LONG).show();
                }
                else {
                    Intent it = new Intent(MainActivity_cadastro1.this, MainActivity_cadastro2.class);
                    startActivity(it);
                }
            }
        });
    }
}