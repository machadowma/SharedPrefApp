package com.github.machadowma.sharedprefapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {
    SQLiteDatabase bancoDados;
    EditText editTextLogin,editTextSenha;
    Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextSenha = (EditText) findViewById(R.id.editTextSenha);
        buttonCadastrar = (Button) findViewById(R.id.buttonCadastrar);

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });
    }

    public void cadastrar(){
        if(TextUtils.isEmpty(editTextLogin.getText().toString())){
            editTextLogin.setError("Campo obrigatório!");
        } else if(TextUtils.isEmpty(editTextSenha.getText().toString())){
            editTextSenha.setError("Campo obrigatório!");
        } else {
            try {
                bancoDados = openOrCreateDatabase("sharedpref", MODE_PRIVATE, null);
                String sql = "INSERT INTO usuario (login,senha) VALUES (?,?)";
                SQLiteStatement stmt = bancoDados.compileStatement(sql);
                stmt.bindString(1, editTextLogin.getText().toString());
                stmt.bindString(2, editTextSenha.getText().toString());
                stmt.executeInsert();
                bancoDados.close();
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}