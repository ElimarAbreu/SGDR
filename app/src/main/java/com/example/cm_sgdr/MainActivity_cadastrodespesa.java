package com.example.cm_sgdr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.RadioAccessSpecifier;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.cm_sgdr.modelo.Conta;
import com.example.cm_sgdr.modelo.Pessoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class MainActivity_cadastrodespesa extends AppCompatActivity {

    private List<String> moradores = new ArrayList<String>();
    private Spinner spinner_morador;
    private String responsavel = "1111111";
    private ListView listV_dados;
    private List<String> listPessoa = new ArrayList<String>();
    private ArrayAdapter<String> arrayAdapterPessoa;
    private DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();
    private String button_;
    Pessoa pessoaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cadastrodespesa);

        listV_dados = (ListView)findViewById(R.id.listV_dados);
        final EditText nome_conta = findViewById(R.id.editText_cadastrodesp_nome);
        RadioGroup radioGroup = findViewById(R.id.grup);
        Button confirmar = findViewById(R.id.button_cadastrodesp_confirmar);
        final RadioButton fixab = findViewById(R.id.radioButton_cadastrodesp_fixa);
        final RadioButton nfixab = findViewById(R.id.radioButton_cadastrodesp_nfixa);


        // Populando o spinner
        // Obtendo lista de moradores da republica

        Intent intent = getIntent();
        final String codigo = intent.getStringExtra("c_r");

        Log.v("MTAa","Codigo");
        Log.v("MTAa",codigo);

        Log.v("MTAa","Cheguei!!!");



        Query query1 = raiz.child("Pessoa").orderByChild("cod_republica").equalTo(codigo);

        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listPessoa.clear();
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Pessoa p = objSnapshot.getValue(Pessoa.class);
                    listPessoa.add(p.getNome());
                    Log.v("MTAa", p.getNome());
                }
                arrayAdapterPessoa = new ArrayAdapter<String>(MainActivity_cadastrodespesa.this, android.R.layout.simple_list_item_1, listPessoa);
                listV_dados.setAdapter(arrayAdapterPessoa);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        // Obtendo o responsável
        listV_dados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //adapterView.getChildAt(i).setBackgroundColor(Color.parseColor("#808080"));

                responsavel = (String)adapterView.getItemAtPosition(i);
                Log.v("MTAa", responsavel);
            }
        });

        // Obtendo o Tipo de Conta

        /*
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = group.findViewById(checkedId);
                if (null != rb) {
                    button_ = rb.getText().toString();
                    //Toast.makeText(MainActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });
        */
        // Adcionar conta no bd
        // Criando intância de conta

        confirmar.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 if (fixab.isChecked()) {
                     button_ = fixab.getText().toString();
                 }
                 else if(nfixab.isChecked()){
                     button_ = nfixab.getText().toString();
                 }

                 if(codigo.isEmpty() || nome_conta.getText().toString().isEmpty() || responsavel.isEmpty() || button_.isEmpty()){
                     makeText(MainActivity_cadastrodespesa.this, "Preencha todos os campos!", LENGTH_LONG).show();
                 }
                 else{
                     Conta conta_ = new Conta();

                     conta_.setCodigo_republica(codigo);
                     conta_.setNome(nome_conta.getText().toString());
                     conta_.setResponsavel(responsavel);
                     conta_.setTipo(button_);

                     raiz.child("Conta").push().setValue(conta_);

                     Intent it = new Intent(MainActivity_cadastrodespesa.this, MainActivity_principal.class);
                     startActivity(it);
                 }
             }
         });


    }

        /*
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, moradores);
        //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner_morador = (Spinner) findViewById(R.id.spinner_cadastrodespesa_resp);
        spinner_morador.setAdapter(adapter);


        responsavel = moradores.get(1);
        spinner_morador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                responsavel = moradores.get(i);
                Log.v("MTA1", moradores.get(i));
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    */
}
