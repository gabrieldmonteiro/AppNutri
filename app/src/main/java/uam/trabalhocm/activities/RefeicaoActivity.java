package uam.trabalhocm.activities;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import uam.trabalhocm.R;

public class RefeicaoActivity extends Activity {

    private EditText txtAlimento;
    private FloatingActionButton btAdd;
    private ListView listView;
    private SQLiteDatabase bancoDados;
    private ArrayAdapter<String> itensAdaptador;
    private ArrayList<String> itens;
    private ArrayList<Integer> ids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refeicao);



        try {
            txtAlimento = (EditText) findViewById(R.id.txtAlimentoRef);
            btAdd = (FloatingActionButton) findViewById(R.id.fabRef);
            //lista
            listView = (ListView) findViewById(R.id.listViewRefeicoesRef);


            //Banco de Dados
            bancoDados = openOrCreateDatabase("appnutrirefs", MODE_PRIVATE, null);



            //tabela refs
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS refeicoes(id INTEGER PRIMARY KEY AUTOINCREMENT, refeicao VARCHAR) ");

            btAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String alimento = txtAlimento.getText().toString().trim();
                    salvarRefeicao(alimento);
                }
            });



            listView.setLongClickable(true);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    removerRefeicao( ids.get( position ) );
                    return true;
                }
            });

            recuperarRefeicoes();


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void salvarRefeicao(String alimentoP){
        try {
            if (alimentoP.equals("")){
                Toast.makeText(RefeicaoActivity.this,"Preencha todos os campos",Toast.LENGTH_SHORT).show();
            }else {

                bancoDados.execSQL("INSERT INTO refeicoes (refeicao) VALUES ('" + alimentoP + "') ");
                Toast.makeText(RefeicaoActivity.this,"Refeicao adicionada !!!",Toast.LENGTH_SHORT).show();
                recuperarRefeicoes();
                txtAlimento.setText("");

            }
        }catch (Exception e ){
            e.printStackTrace();
        }

    }

    private void recuperarRefeicoes(){
        try{

            //recupera refeicoes
            Cursor cursor = bancoDados.rawQuery("SELECT * FROM refeicoes ORDER BY id DESC ",null);
            //recupera id das colunas
            int indiceColunaId = cursor.getColumnIndex("id");
            int indiceColunaRefeicao = cursor.getColumnIndex("refeicao");

            //criar adaptador
            itens = new ArrayList<String>();
            ids = new ArrayList<Integer>();
            itensAdaptador = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_list_item_2,
                    android.R.id.text2,
                    itens){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);
                    TextView text = (TextView) view.findViewById(android.R.id.text2);
                    text.setTextColor(Color.BLACK);
                    return view;

                }
            };

            listView.setAdapter(itensAdaptador);

            //lista refeicoes
            cursor.moveToFirst();
            while(cursor!=null){


                Log.i("Resultado -  ","Refeicao: "+ cursor.getString(indiceColunaRefeicao));
                itens.add(cursor.getString(indiceColunaRefeicao));
                ids.add( Integer.parseInt(cursor.getString(indiceColunaId))) ;
                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void removerRefeicao(int id){
        try{
            bancoDados.execSQL("DELETE FROM refeicoes WHERE id = "+id);
            recuperarRefeicoes();
            Toast.makeText(RefeicaoActivity.this, "Refeicao removida com sucesso!", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
