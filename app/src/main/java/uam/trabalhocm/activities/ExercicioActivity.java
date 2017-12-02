package uam.trabalhocm.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import uam.trabalhocm.R;


public class ExercicioActivity extends AppCompatActivity {

    private String[] objetivos = new String[]{"","Emagrecer","Ganhar Massa Muscular","Resistencia"};
    private Spinner sp;
    private Button btGerar;
    private String selecionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,objetivos);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        sp  = (Spinner) findViewById(R.id.spinnerObjetivos);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 selecionado = (String) sp.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btGerar = (Button) findViewById(R.id.btGerarExercicios);
        btGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url= "https://www.google.com.br/";
                if(selecionado.equals("")){
                    Toast.makeText(getApplicationContext(),"Selecione um objetivo !!!",Toast.LENGTH_SHORT).show();
                }else {

                    switch (selecionado) {
                        case "Emagrecer":
                            url = "https://tinyurl.com/emagrecerappnutri";
                            break;

                        case "Ganhar Massa Muscular":
                            url = "https://tinyurl.com/massamuscularappnutri";
                            break;

                        case "Resistencia":
                            url = "https://tinyurl.com/resistenciaappnutri";
                            break;
                    }

                    Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                    startActivity(viewIntent);
                }
            }
        });

    }
}
