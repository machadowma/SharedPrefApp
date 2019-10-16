package com.github.machadowma.sharedprefapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    SQLiteDatabase bancoDados;
    EditText editTextLogin,editTextSenha;
    Button buttonEntrar;
    TextView textViewCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        criarBancoDados();

        textViewCadastrar = (TextView) findViewById(R.id.textViewCadastrar);
        editTextLogin = (EditText) findViewById(R.id.editTextLogin);
        editTextSenha = (EditText) findViewById(R.id.editTextSenha);
        buttonEntrar = (Button) findViewById(R.id.buttonCadastrar);

        textViewCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastro();
            }
        });

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entrar();
            }
        });
    }

    public void criarBancoDados(){
        try {
            bancoDados = openOrCreateDatabase("sharedpref", MODE_PRIVATE, null);
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS usuario(" +
                    "   login VARCHAR PRIMARY KEY" +
                    " , senha VARCHAR NOT NULL" +
                    " ) " );
            bancoDados.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirCadastro(){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    public void entrar(){
        bancoDados = openOrCreateDatabase("sharedpref", MODE_PRIVATE, null);
        String login = editTextLogin.getText().toString();
        String senha = editTextSenha.getText().toString();
        Cursor cursor = bancoDados.rawQuery("SELECT login FROM usuario WHERE login = '"+login+"' AND senha = '"+senha+"'", null);
        if(cursor.moveToFirst()){
            SharedPreferences sharedPref = getSharedPreferences("sharedpref", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("login", login);
            editor.commit();
            Intent intent = new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuário ou senha inexistente!", Toast.LENGTH_SHORT).show();
        }
    }
}