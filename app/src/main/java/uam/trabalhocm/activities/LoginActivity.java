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
import uam.trabalhocm.database.DbHelper;

public class LoginActivity extends Activity {

    private Button btCadastrar,btEntrar;
    private EditText login,senha;

    private DbHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (EditText) findViewById(R.id.txtLogin);
        senha = (EditText) findViewById(R.id.txtSenha);


        //Botao Entrar - ?
        btEntrar = (Button) findViewById(R.id.btEntrar);
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyFromSQLite();
            }
        });



        //Botao Cadastrar  -  OK
        btCadastrar = (Button) findViewById(R.id.btCadastro);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }



    private void verifyFromSQLite(){
        if(login.getText().toString().trim().isEmpty()||senha.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"Erro",Toast.LENGTH_SHORT).show();
        }else{
        //Fica
        if(dbh.checkUser(login.getText().toString().trim(),senha.getText().toString().trim())){
            Intent intentHome = new Intent(LoginActivity.this,HomeActivity.class);
            intentHome.putExtra("USUARIO", login.getText().toString().trim());
            limparCampos();
            startActivity(intentHome);
        }else{
            Toast.makeText(this,"Erro",Toast.LENGTH_SHORT).show();
        }

    }
    }

    private void limparCampos(){
        login.setText(null);
        login.setText(null);
    }
}

