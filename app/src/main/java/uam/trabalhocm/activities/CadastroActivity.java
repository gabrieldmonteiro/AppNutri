package uam.trabalhocm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uam.trabalhocm.R;
import uam.trabalhocm.classes.Usuario;
import uam.trabalhocm.database.DbHelper;

public class CadastroActivity extends Activity {

    private Button btContinuar;
    private EditText loginC,emailC,senhaC;
    private DbHelper dbh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        DbHelper dbh = new DbHelper(this);

        loginC = (EditText) findViewById(R.id.txtLoginCad);
        emailC = (EditText) findViewById(R.id.txtEmailCad);
        senhaC = (EditText) findViewById(R.id.txtSenhaCad);

        //Botao Continuar
        btContinuar = (Button) findViewById(R.id.btContinuarCad);
        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(loginC.getText().toString().trim().isEmpty()||emailC.getText().toString().trim().isEmpty()||senhaC.getText().toString().trim().isEmpty()){
                    Toast.makeText(null,"ERRO",Toast.LENGTH_SHORT).show();
                }else{

                Usuario u = new Usuario();

                u.setLogin(loginC.getText().toString().trim());
                u.setEmail(emailC.getText().toString().trim());
                u.setSenha(senhaC.getText().toString().trim());

                postDataToSQLite(u);

                }
            }
        });


    }


    private void postDataToSQLite(Usuario u){
        if (!dbh.checkUser(loginC.getText().toString().trim(),senhaC.getTag().toString().trim())){


            dbh.addUser(u);


            Toast.makeText(null,"Cadastro Realizado",Toast.LENGTH_SHORT).show();
            limparCampos();
            Intent intent = new Intent(CadastroActivity.this,HomeActivity.class);
            intent.putExtra("USUARIO", loginC.getText().toString().trim());
            startActivity(intent);


        } else {

            Toast.makeText(null,"Erro - Ja existe",Toast.LENGTH_SHORT).show();
        }


}

        public void limparCampos(){
        loginC.setText(null);
        emailC.setText(null);
        senhaC.setText(null);
        }

}