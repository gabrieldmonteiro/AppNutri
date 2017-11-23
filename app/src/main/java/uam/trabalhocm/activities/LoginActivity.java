package uam.trabalhocm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import uam.trabalhocm.R;


public class LoginActivity extends Activity {

    private EditText login,senha;
    private Button entrar,cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (EditText) findViewById(R.id.txtLogin);
        senha = (EditText) findViewById(R.id.txtSenha);

        entrar = (Button) findViewById(R.id.btEntrar);
        cadastro = (Button) findViewById(R.id.btCadastro);

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
