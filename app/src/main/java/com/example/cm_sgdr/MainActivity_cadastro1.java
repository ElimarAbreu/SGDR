package com.example.cm_sgdr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cm_sgdr.modelo.Pessoa;
import com.example.cm_sgdr.modelo.Republica;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class MainActivity_cadastro1 extends AppCompatActivity {

    private EditText nome, usuario, senha, csenha, email, codigo;
    DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();
    boolean ver = true;
    boolean aux = false;

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
                    aux = true;
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
                if(nome.getText().toString().isEmpty() || usuario.getText().toString().isEmpty() || senha.getText().toString().isEmpty() || csenha.getText().toString().isEmpty() || email.getText().toString().isEmpty() || codigo.getText().toString().isEmpty())
                    makeText(MainActivity_cadastro1.this, "Preencha todos os campos!", LENGTH_LONG).show();

                else {
                    ver = false;

                    //Verifica

                    Query query1 = raiz.child("Republica");

                    query1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Republica rep;

                            for(DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                                rep = objSnapshot.getValue(Republica.class);

                                Log.v("MTAG",rep.getCodigo());
                                Log.v("MTAa",rep.getResponsavel());

                                if (rep.getCodigo().equals(codigo.getText().toString())) {
                                    ver = true;
                                    Log.v("MTA1", "True");
                                    Intent it = new Intent(MainActivity_cadastro1.this, MainActivity_cadastro2.class);
                                    it.putExtra("nome", nome.getText().toString());
                                    it.putExtra("user", usuario.getText().toString());
                                    it.putExtra("senha", senha.getText().toString());
                                    it.putExtra("csenha", csenha.getText().toString());
                                    it.putExtra("email", email.getText().toString());
                                    it.putExtra("codigo", codigo.getText().toString());
                                    it.putExtra("new", "null");
                                    startActivity(it);
                                    break;
                                }
                            }

                            if(!ver)
                                alert("Republica Inexistente");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });

                    if (aux){
                        Intent it = new Intent(MainActivity_cadastro1.this, MainActivity_cadastro2.class);
                        it.putExtra("nome", nome.getText().toString());
                        it.putExtra("user", usuario.getText().toString());
                        it.putExtra("senha", senha.getText().toString());
                        it.putExtra("csenha", csenha.getText().toString());
                        it.putExtra("email", email.getText().toString());
                        it.putExtra("codigo", codigo.getText().toString());
                        it.putExtra("new", "new");
                        startActivity(it);
                    }
                }
            }
        });
    }

    public void alert(String s){
        Toast.makeText(this,s, Toast.LENGTH_LONG).show();
    }
}