package uam.trabalhocm.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import uam.trabalhocm.R;

public class HomeActivity extends Activity {

    private TextView labelUsuario;
    private ImageButton btIMC, btRef, btDieta, btExs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        labelUsuario = (TextView) findViewById(R.id.lbUsuarioOn);
        labelUsuario.setText(getIntent().getStringExtra("nomeUsuario"));

        btIMC = (ImageButton) findViewById(R.id.imgIMC);
        btRef = (ImageButton) findViewById(R.id.imgRefeicao);
        btDieta = (ImageButton) findViewById(R.id.imgDieta);
        btExs = (ImageButton) findViewById(R.id.imgExercicios);

        //Redirecionamento para as Activities !!!
        //IMC
        btIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,IMCActivity.class);
                startActivity(intent);
            }
        });
        //REFEICOES
        btRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,RefeicaoActivity.class);
                startActivity(intent);
            }
        });
        //DIETA
        btDieta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,DietaActivity.class);
                startActivity(intent);
            }
        });
        //EXERCICIOS
        btExs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ExercicioActivity.class);
                startActivity(intent);
            }
        });

    }
}
