package uam.trabalhocm.activities;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;


import uam.trabalhocm.R;
import uam.trabalhocm.classes.Usuario;
import uam.trabalhocm.database.DbHelper;
import uam.trabalhocm.util.InputValidation;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener {

        private final AppCompatActivity activity = CadastroActivity.this;

        private NestedScrollView nestedScrollView;

        private TextInputLayout textInputLayoutLogin;
        private TextInputLayout textInputLayoutEmail;
        private TextInputLayout textInputLayoutSenha;
        private TextInputLayout textInputLayoutConfirmaSenha;

        private TextInputEditText textInputEditTextLogin;
        private TextInputEditText textInputEditTextEmail;
        private TextInputEditText textInputEditTextSenha;
        private TextInputEditText textInputEditTextConfirmaSenha;

        private AppCompatButton appCompatButtonContinuar;

        private InputValidation inputValidation;
        private DbHelper databaseHelper;
        private Usuario user;

        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cadastro);
            getSupportActionBar().hide();

            initViews();
            initListeners();
            initObjects();
        }

        private void initViews(){
            nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

            textInputLayoutLogin = (TextInputLayout) findViewById(R.id.textInputLayoutLogin);
            textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
            textInputLayoutSenha = (TextInputLayout) findViewById(R.id.textInputLayoutSenha);
            textInputLayoutConfirmaSenha = (TextInputLayout) findViewById(R.id.textInputEditTextConfirmaSenha);

            textInputEditTextLogin = (TextInputEditText) findViewById(R.id.textInputEditTextLogin);
            textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
            textInputEditTextSenha = (TextInputEditText) findViewById(R.id.textInputEditTextSenha);
            textInputEditTextConfirmaSenha = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmaSenha);

            appCompatButtonContinuar = (AppCompatButton) findViewById(R.id.appCompatButtonContinuar);


        }

        private void initListeners(){
            appCompatButtonContinuar.setOnClickListener(this);
        }

        private void initObjects(){
            inputValidation = new InputValidation(activity);
            databaseHelper = new DbHelper(activity);
            user = new Usuario();
        }

        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.appCompatButtonContinuar:
                    postDataToSQLite();
                    break;
            }
        }

        private void postDataToSQLite(){
            if (!inputValidation.isInputEditTextFieldFilled(textInputEditTextLogin, textInputLayoutLogin, getString(R.string.error_message_Login))) {
                return;
            }
            if (!inputValidation.isInputEditTextFieldFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
                return;
            }
            if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
                return;
            }
            if (!inputValidation.isInputEditTextFieldFilled(textInputEditTextSenha, textInputLayoutSenha, getString(R.string.error_message_Senha))) {
                return;
            }
            if (!inputValidation.isInputEditTextMatches(textInputEditTextSenha, textInputEditTextConfirmaSenha,
                    textInputLayoutConfirmaSenha, getString(R.string.error_Senha_match))) {
                return;
            }

            if (!databaseHelper.checkUser(textInputEditTextLogin.getText().toString().trim(),textInputEditTextSenha.getTag().toString().trim())){

                user.setLogin(textInputEditTextLogin.getText().toString().trim());
                user.setEmail(textInputEditTextEmail.getText().toString().trim());
                user.setSenha(textInputEditTextSenha.getText().toString().trim());

                databaseHelper.addUser(user);


                Snackbar.make(nestedScrollView, "OK", Snackbar.LENGTH_LONG).show();
                limparCampos();


            } else {

                Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
            }


        }

        private void limparCampos(){
            textInputEditTextLogin.setText(null);
            textInputEditTextEmail.setText(null);
            textInputEditTextSenha.setText(null);
            textInputEditTextConfirmaSenha.setText(null);
        }
}
