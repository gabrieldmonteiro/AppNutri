package uam.trabalhocm.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uam.trabalhocm.R;
import uam.trabalhocm.classes.Usuario;
import uam.trabalhocm.database.DbHelper;


public class CadastroActivity extends Activity {

    DbHelper dbH = new DbHelper(this);

    private Button btcadastrar;
    private EditText login,email,senha,peso,altura;
    DbAdapter db = new DbAdapter(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //PEGANDO COMPONENTES DAS VIEWS
        login = (EditText) findViewById(R.id.txtLoginCad);
        email = (EditText) findViewById(R.id.txtEmailCad);
        senha = (EditText) findViewById(R.id.txtSenhaCad);
        peso = (EditText) findViewById(R.id.txtPeso);
        altura = (EditText) findViewById(R.id.txtAltura);

        // VARS PARA MANIPULACAO E VALIDACAO
        final String strLogin = login.getText().toString();
        final String strEmail = email.getText().toString() ;
        final String strSenha = senha.getText().toString() ;
        final String strPeso = peso.getText().toString() ;
        final String strAltura = altura.getText().toString();
        final float fltPeso = Float.parseFloat(strPeso);
        final float fltAltura= Float.parseFloat(strAltura);





        //BOTAO CADASTRAR
        btcadastrar = (Button) findViewById(R.id.btContinuarCad);

        btcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(strLogin.trim().length() == 0 && strEmail.trim().length()==0 && strSenha.trim().length()==0 && strPeso.trim().length()==0 && strAltura.trim().length()==0){
                    Toast.makeText(CadastroActivity.this, "Preencha todos os campos !!! ", Toast.LENGTH_SHORT).show();
                }else {
                    Usuario objUsuario = new Usuario();
                    objUsuario.setLogin(strLogin);
                    objUsuario.setEmail(strEmail);
                    objUsuario.setSenha(strSenha);
                    objUsuario.setPeso(fltPeso);
                    objUsuario.setAltura(fltAltura);

                    dbH.insertUsuario(objUsuario);




                    //---Add Usuário Banco---
                /*db.open();
                long id = db.insertUsuario(login.getText().toString(),email.getText().toString(),
                        senha.getText().toString(),
                        Float.parseFloat(peso.getText().toString()),Float.parseFloat(altura.getText().toString()));
                db.close();*/

                    //TOAST CADASTRO OK
                  Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso !!! :)", Toast.LENGTH_SHORT).show();

                    //NAV ACTIVITY
                    Intent intent = new Intent(CadastroActivity.this, HomeActivity.class);
                    //Put Extra p/ nao precisar consultar de novo no DB
                    intent.putExtra("nomeUsuario",login.getText().toString());
                    startActivity(intent);
                }

            }
        });
    }
}
