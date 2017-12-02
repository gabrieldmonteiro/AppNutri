package uam.trabalhocm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import uam.trabalhocm.R;
import uam.trabalhocm.classes.Usuario;
import uam.trabalhocm.database.DbHelper;

public class CadastroActivity extends Activity {

    private Button btContinuar;
    private EditText loginC,emailC,senhaC;
    private DbHelper dbh;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        firebaseAuth = FirebaseAuth.getInstance();


        DbHelper dbh = new DbHelper(this);


        emailC = (EditText) findViewById(R.id.txtEmailCad);
        senhaC = (EditText) findViewById(R.id.txtSenhaCad);

        //Botao Continuar
        btContinuar = (Button) findViewById(R.id.btContinuarCad);
        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(emailC.getText().toString().trim().isEmpty()||senhaC.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Preencha todos os campos",Toast.LENGTH_SHORT).show();
                }else{

                  firebaseAuth.createUserWithEmailAndPassword(emailC.getText().toString().trim(),senhaC.getText().toString().trim())
                          .addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {
                                  if (task.isSuccessful()){
                                      Toast.makeText(getApplicationContext(), "Usuario Cadastrado",Toast.LENGTH_SHORT).show();
                                      Intent intent = new Intent(CadastroActivity.this,HomeActivity.class);
                                      startActivity(intent);
                                  }else {
                                      limparCampos();
                                      Toast.makeText(getApplicationContext(), "Erro ao Cadastrar Usuario",Toast.LENGTH_SHORT).show();
                                  }
                              }
                          });

                }
            }
        });


    }

        public void limparCampos(){

        emailC.setText(null);
        senhaC.setText(null);
        }

}