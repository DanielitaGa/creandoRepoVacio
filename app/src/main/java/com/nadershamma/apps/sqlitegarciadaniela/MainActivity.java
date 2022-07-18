package com.nadershamma.apps.sqlitegarciadaniela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {
    TextView tvResgistrese;
    Button btnIngresar;

    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"BD1",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResgistrese = (TextView)findViewById(R.id.tvRegistrese);


        tvResgistrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),registro.class);
                startActivity(i);
            }
        });

        btnIngresar = (Button)findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            EditText txtusu = (EditText) findViewById(R.id.txtUsuario);
            EditText txtpass = (EditText) findViewById(R.id.txtPassword);

            try {
                Cursor cursor = helper.ConsultarUsuPas(
                        txtusu.getText().toString(),txtpass.getText().toString());
                if(cursor.getCount()>0){
                    Intent i = new Intent(getApplicationContext(),Principal.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"usuario y/o contraseña incorrectos",
                            Toast.LENGTH_LONG).show();
                }
                txtusu.setText("");
                txtpass.setText("");
                txtusu.findFocus();
            }catch (SQLException e){
                e.printStackTrace();
            }
            }
        });


    }
}