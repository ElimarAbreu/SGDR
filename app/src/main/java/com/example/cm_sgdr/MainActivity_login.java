package com.example.cm_sgdr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cm_sgdr.modelo.Pessoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity_login extends AppCompatActivity {

    DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();

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

                Query query1 = raiz.child("Pessoa");

                query1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Pessoa p;
                        TextView tLogin = (TextView) findViewById(R.id.editText_login_email);
                        TextView tSenha = (TextView) findViewById(R.id.editText_login_senha);
                        String login = tLogin.getText().toString();
                        String senha = tSenha.getText().toString();
                        boolean verificar = true;
                        for(DataSnapshot objSnapshot:dataSnapshot.getChildren()) {

                            p = objSnapshot.getValue(Pessoa.class);

                            Log.v("MTAG",p.getEmail());
                            Log.v("MTAa",p.getSenha());

                            if (login.equals(p.getEmail()) && senha.equals(p.getSenha())) {
                                alert("Login realizado com sucesso");
                                Intent it = new Intent(MainActivity_login.this, MainActivity_principal.class);

                                it.putExtra("c_p", p.getEmail());
                                it.putExtra("c_r", p.getCod_republica());
                                verificar = false;
                                startActivity(it);
                            }
                        }

                        if(verificar){
                            alert("Login ou senha incorretos");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void alert(String s){
        Toast.makeText(this,s, Toast.LENGTH_LONG).show();
    }

}