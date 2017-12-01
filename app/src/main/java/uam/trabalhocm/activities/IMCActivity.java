package uam.trabalhocm.activities;


import android.os.Bundle;

import uam.trabalhocm.R;

import android.app.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IMCActivity extends Activity implements View.OnClickListener {

    private EditText altura,peso;
    private Button botao;
    private TextView resposta;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);


        altura = (EditText) findViewById(R.id.txtAltura);
        peso = (EditText) findViewById(R.id.txtPeso);
        resposta = (TextView) findViewById(R.id.lbResp);
        botao = (Button) findViewById(R.id.btIMC);


        botao.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        float vAltura, vPeso, imc;
        vAltura = Float.parseFloat(altura.getText().toString());
        vPeso = Float.parseFloat(peso.getText().toString());
        calculaIMC(vAltura, vPeso);

        resposta.setText(String.format("%s: %.2f m\n%s: %.1f kg\n\n%s: %.1f  \n%s: %s",
                getResources().getString(R.string.label_Altura),vAltura,
                getResources().getString(R.string.label_Peso), vPeso,getResources().getString(R.string.app_name),
                calculaIMC(vAltura, vPeso),getResources().getString(R.string.label_situacao),exibeSit(vAltura, vPeso)));

        altura.setText("");
        peso.setText("");


    }




    public float calculaIMC(float altP, float pesoP){
        float imc = pesoP/(altP*altP);
        return imc;
    }

    public String exibeSit(float altP, float pesoP){
        float imc = pesoP/(altP*altP);
        String resp = "";

        if (imc<18.5){
            resp = getResources().getString(R.string.situacao_api);
        }else{
            if(imc>=18.5&&imc<=24.9){
                resp = getResources().getString(R.string.situacao_pn);
            }else{
                if(imc>=25.0&&imc<=29.9){
                    resp = getResources().getString(R.string.situacao_ap);

                }else{
                    if(imc>=30.0&&imc<=34.9){
                        resp = getResources().getString(R.string.situacao_o1);

                    }else{
                        if(imc>=35.0&&imc<=39.9){
                            resp = getResources().getString(R.string.situacao_o2);

                        }else{
                            resp = getResources().getString(R.string.situacao_o3);

                        }
                    }
                }
            }
        }

        return resp;
    }



}
