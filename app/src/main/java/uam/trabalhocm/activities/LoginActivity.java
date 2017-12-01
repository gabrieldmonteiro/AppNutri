package uam.trabalhocm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uam.trabalhocm.R;
import uam.trabalhocm.database.DbHelper;
import uam.trabalhocm.util.InputValidation;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutLogin;
    private TextInputLayout textInputLayoutSenha;

    private TextInputEditText textInputEditTextLogin;
    private TextInputEditText textInputEditTextSenha;

    private AppCompatButton btLogin;
    private AppCompatTextView btCadastro;

    private InputValidation inputValidation;
    private DbHelper dbh;

    private void initViews(){
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutLogin = (TextInputLayout) findViewById(R.id.textInputLayoutLogin);
        textInputLayoutSenha = (TextInputLayout) findViewById(R.id.textInputLayoutSenha);

        textInputEditTextLogin = (TextInputEditText) findViewById(R.id.textInputEditTextLogin);
        textInputEditTextSenha = (TextInputEditText) findViewById(R.id.textInputEditTextSenha);

        btLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        btCadastro = (AppCompatTextView) findViewById(R.id.textViewLinkCadastro);
    }

    private void initListeners(){
        btLogin.setOnClickListener(this);
        btCadastro.setOnClickListener(this);
    }


    private void initObjects(){
        dbh = new DbHelper(activity);
        inputValidation = new InputValidation(activity);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;

            case  R.id.textViewLinkCadastro:
                Intent intentCadastro = new Intent(getApplicationContext(),CadastroActivity.class);
                startActivity(intentCadastro);
                break;

        }
    }

    private void verifyFromSQLite(){
        if(!inputValidation.isInputEditTextFieldFilled(textInputEditTextLogin,textInputLayoutLogin,"ERRO - LOGIN")){
            return;
        }

        if(!inputValidation.isInputEditTextFieldFilled(textInputEditTextSenha,textInputLayoutSenha,"ERRO - SENHA")){
            return;
        }

        //caso mudar de login -> email
        /*if(!inputValidation.isInputEditTextEmail(textInputEditTextEmail,textInputLayoutEmail,"ERRO - EMAIL")){
            return;
        }*/

        if(dbh.checkUser(textInputEditTextLogin.getText().toString().trim(),textInputEditTextSenha.getText().toString().trim())){
            Intent intentHome = new Intent(activity,HomeActivity.class);
            intentHome.putExtra("USUARIO", textInputEditTextLogin.getText().toString().trim());
            limparCampos();
            startActivity(intentHome);
        }else{
            Snackbar.make(nestedScrollView,"Erro ao efetuar Login", Snackbar.LENGTH_LONG).show();
        }

    }

    private void limparCampos(){
        textInputEditTextLogin.setText(null);
        textInputEditTextSenha.setText(null);
    }





}
