package com.example.cm_sgdr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cm_sgdr.modelo.Pessoa;
import com.example.cm_sgdr.modelo.Republica;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

                    //Obtem informações da tela anterior

                    Intent intent = getIntent();
                    String nome = intent.getStringExtra("nome");
                    String user = intent.getStringExtra("user");
                    String senha = intent.getStringExtra("senha");
                    String email = intent.getStringExtra("email");
                    String codigo = intent.getStringExtra("codigo");
                    String new_ = intent.getStringExtra("new");

                    Pessoa p = new Pessoa();

                    //Informações Pessoais
                    p.setEmail(email);
                    p.setNome(nome);
                    p.setUser(user);
                    p.setSenha(senha);
                    p.setCod_republica(codigo);

                    //Informações Bancárias

                    p.setCpf(cpf.getText().toString());
                    p.setBanco_nome(banco.getText().toString());
                    p.setBanco_agencia(agencia.getText().toString());
                    p.setBanco_conta(conta.getText().toString());
                    p.setBanco_operacao(operacao.getText().toString());

                    //Criando usuário no Banco

                    DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();
                    raiz.child("Pessoa").push().setValue(p);

                    //Criando a entidade Republica no bd

                    if(new_.equals("new")) {
                        Republica rep = new Republica();
                        rep.setCodigo(p.getCod_republica());
                        rep.setResponsavel(p.getEmail());
                        raiz.child("Republica").push().setValue(rep);
                    }
                    Intent it = new Intent(MainActivity_cadastro2.this, MainActivity_principal.class);
                    startActivity(it);
                }
            }
        });
    }
}