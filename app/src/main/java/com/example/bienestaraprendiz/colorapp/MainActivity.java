package com.example.bienestaraprendiz.colorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bienestaraprendiz.colorapp.BD.Crud;
import com.example.bienestaraprendiz.colorapp.Pantallas.Juego;
import com.example.bienestaraprendiz.colorapp.Pantallas.Resultados;
import com.example.bienestaraprendiz.colorapp.Pantallas.puntajes;

public class MainActivity extends AppCompatActivity {
    Button btnIniciar,btnPuntaje,btnConfiguracion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Crud crud=new Crud(this,"color",null,1);
        crud.iniciar(this);
        btnIniciar=findViewById(R.id.iniciar);
        btnPuntaje=findViewById(R.id.puntaje);
        btnConfiguracion=findViewById(R.id.configuracion);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Juego.class);
                startActivity(intent);
            }
        });
        btnPuntaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,puntajes.class);
                startActivity(intent);
            }
        });
        btnConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Juego.class);//cambiar pantalla
                startActivity(intent);
            }
        });
    }
}
