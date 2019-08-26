package com.example.cm_sgdr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cm_sgdr.modelo.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);

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
        List<Card_despesa> cardtList = new ArrayList<Card_despesa>();
        Card_despesa card;
        // 1
        card = new Card_despesa();
        card.setDespesa("Internet");
        card.setResponsavel("Elimar");
        card.setRsatual("R$ Atual");
        card.setVencimento("Vencimento");
        card.setRsanterior("R$ Anterior");
        card.setVrsatual("R$ 100.00");
        card.setVvencimento("27/09/2019");
        card.setVrsanterior("R$ 150.00");
        cardtList.add(card);
        //2
        card = new Card_despesa();
        card.setDespesa("Água");
        card.setResponsavel("Adão");
        card.setRsatual("R$ Atual");
        card.setVencimento("Vencimento");
        card.setRsanterior("R$ Anterior");
        card.setVrsatual("R$ 100.00");
        card.setVvencimento("25/09/2019");
        card.setVrsanterior("R$ 120.00");
        cardtList.add(card);

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
            Intent intent = getIntent();
            String codigo = intent.getStringExtra("c_r");

            Intent it = new Intent(MainActivity_principal.this, MainActivity_cadastrodespesa.class);
            it.putExtra("c_r", codigo);
            startActivity(it);
        }
        return true;
    }

}

