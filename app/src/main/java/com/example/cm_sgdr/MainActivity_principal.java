package com.example.cm_sgdr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cm_sgdr.modelo.Conta;
import com.example.cm_sgdr.modelo.Fatura;
import com.example.cm_sgdr.modelo.Pessoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_principal extends AppCompatActivity {

    private Intent intent;
    private String codigo;
    private Query query1;
    private Query query2;
    private Conta conta_;
    private List<Card_despesa> cardtList = new ArrayList<Card_despesa>();
    private Card_despesa card1;
    private Fatura fatura;

    private DatabaseReference raiz = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);

        intent = getIntent();
        codigo = intent.getStringExtra("c_r");

        List<Card_despesa> listCards = getAllCards();
        ListView listaDeDespesas = (ListView) findViewById(R.id.card1);
        BaseAdapter adapter = new AdapterPersonalizado(listCards, this);
        listaDeDespesas.setAdapter(adapter);
    }

    public class AdapterPersonalizado extends BaseAdapter {
        private final List<Card_despesa> cards;
        private final Activity act;
        public AdapterPersonalizado(List<Card_despesa> cards, Activity act) {
            this.cards = cards;
            this.act = act;
        }

        @Override
        public int getCount() {
            return cards.size();
        }

        @Override
        public Object getItem(int position) {
            return cards.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder holder;

            if (convertView == null) {
                view = act.getLayoutInflater().inflate(R.layout.activity_card_despesa, parent, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }

            final Card_despesa card = cards.get(position);
            //Colocando valores
            holder.despe.setText(card.getDespesa());
            holder.responsa.setText(card.getResponsavel());
            holder.rsatua.setText(card.getRsatual());
            holder.venci.setText(card.getVencimento());
            holder.rsante.setText(card.getRsanterior());
            holder.vrsatua.setText(card.getVrsanterior());
            holder.vvenci.setText(card.getVvencimento());
            holder.vrsante.setText(card.getVrsanterior());
            //Listener colocado para mostrar possibilidade de clique na lista
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(act, card.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }

    }

    public class ViewHolder {

        final TextView despe;
        final TextView responsa;
        final TextView rsatua;
        final TextView venci;
        final TextView rsante;
        final TextView vrsatua;
        final TextView vvenci;
        final TextView vrsante;

        public ViewHolder(View view) {
            despe = view.findViewById(R.id.textView_card_despesa);
            responsa = view.findViewById(R.id.textView_card_respon);
            rsatua = view.findViewById(R.id.textView_card_atual);
            venci = view.findViewById(R.id.textView_card_venc);
            rsante = view.findViewById(R.id.textView_card_anterior);
            vrsatua = view.findViewById(R.id.textView_card_vatual);
            vvenci = view.findViewById(R.id.textView_card_vvenc);
            vrsante = view.findViewById(R.id.textView_card_vanterior);
        }

    }

    private List<Card_despesa> getAllCards() {

        query1 = raiz.child("Conta").orderByChild("codigo_republica");

        query1.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                    conta_ = objSnapshot.getValue(Conta.class);
                    Log.v("MTAa",conta_.getNome());
                    Log.v("MTAa",conta_.getResponsavel());
                    // Buscar Faturas
                    query2 = raiz.child("Fatura").orderByChild("c_conta").equalTo(conta_.getCodigo_conta());
                    query2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for(DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                                fatura = objSnapshot.getValue(Fatura.class);

                                Log.v("MTAa",fatura.getTotal());
                                Log.v("MTAa",fatura.getData());

                                card1 = new Card_despesa();
                                card1.setDespesa(conta_.getNome());
                                card1.setResponsavel(conta_.getResponsavel());
                                card1.setRsatual("R$ Atual");
                                card1.setVencimento("Vencimento");
                                card1.setRsanterior("R$ Anterior");
                                card1.setVrsatual("R$ " + fatura.getTotal());
                                Log.v("MTAa","R$ " + fatura.getTotal());
                                card1.setVvencimento(fatura.getData());
                                card1.setVrsanterior("R$ -");
                                cardtList.add(card1);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SystemClock.sleep(2000);
        for(Card_despesa elem: cardtList){
            Log.v("MTAa","INICIO");
            Log.v("MTAa","R$ " + elem.getResponsavel());
            Log.v("MTAa","R$ " + elem.getDespesa());
        }
        Log.v("MTAa",Integer.toString(cardtList.size()));
        return cardtList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id  = item.getItemId();

        if(id == R.id.menu_novo){


            Intent it = new Intent(MainActivity_principal.this, MainActivity_cadastrodespesa.class);
            it.putExtra("c_r", codigo);
            startActivity(it);
        }
        return true;
    }

}

