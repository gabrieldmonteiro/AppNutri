package uam.trabalhocm.activities;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uam.trabalhocm.R;
import uam.trabalhocm.database.DbAdapter;

public class CadastroActivity extends Activity {

    private Button btcadastrar;
    private EditText login,email,senha,peso,altura;
    DbAdapter db = new DbAdapter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        login = (EditText) findViewById(R.id.txtLoginCad);
        email = (EditText) findViewById(R.id.txtEmailCad);
        senha = (EditText) findViewById(R.id.txtSenhaCad);
        peso = (EditText) findViewById(R.id.txtPeso);
        altura = (EditText) findViewById(R.id.txtAltura);






        btcadastrar = (Button) findViewById(R.id.btContinuarCad);

        btcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(login.getText().toString().trim().length()==0 && email.getText().toString().trim().length()==0&&
                        senha.getText().toString().trim().length()==0&& peso.getText().toString().trim().length()==0&&
                        altura.getText().toString().trim().length()==0){
                    Toast.makeText(CadastroActivity.this, "Preencha todos os campos !!! ", Toast.LENGTH_SHORT).show();

                }else {


                    //---Add Usuário Banco---
                /*db.open();
                long id = db.insertUsuario(login.getText().toString(),email.getText().toString(),
                        senha.getText().toString(),
                        Float.parseFloat(peso.getText().toString()),Float.parseFloat(altura.getText().toString()));
                db.close();
*/                  Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso !!! :)", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CadastroActivity.this, HomeActivity.class);
                    intent.putExtra("nomeUsuario",login.getText().toString());
                    startActivity(intent);
                }

            }
        });
    }
}
