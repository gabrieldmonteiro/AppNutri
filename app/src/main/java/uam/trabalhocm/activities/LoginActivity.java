package uam.trabalhocm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uam.trabalhocm.R;
import uam.trabalhocm.database.DbHelper;


public class LoginActivity extends Activity {

    DbHelper dbH = new DbHelper(this);

    private EditText login,senha;
    private Button entrar,cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //PEGANDO COMPONENTES DA VIEW
        login = (EditText) findViewById(R.id.txtLogin);
        senha = (EditText) findViewById(R.id.txtSenha);

        //VARS PARA MANIPULACAO E VALIDACAO
        final String strLogin = login.getText().toString();
        final String strSenha = senha.getText().toString();

        entrar = (Button) findViewById(R.id.btEntrar);
        cadastro = (Button) findViewById(R.id.btCadastro);

        //Click botao Entrar
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String verifSenha = dbH.searchSenha(strLogin);

                if(strSenha.equals(verifSenha)){

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("iLogin", strLogin);
                    startActivity(intent);
                }else{
                    Toast temp = Toast.makeText(LoginActivity.this, "Login e/ou Senha invalidos !", Toast.LENGTH_SHORT);
                    temp.show();
                }
            }
        });



        //Click botão Cadastrar
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

    }
}
