package com.example.bienestaraprendiz.colorapp.Pantallas;

import android.content.ContentValues;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.bienestaraprendiz.colorapp.BD.Crud;
import com.example.bienestaraprendiz.colorapp.MainActivity;
import com.example.bienestaraprendiz.colorapp.R;

import java.util.ArrayList;

public class Resultados extends AppCompatActivity {
    String puntaje,crono;
    TextView palabra,acertar,tiempo,porcentaje;
    ArrayList<Integer> lista;
    Button regresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lista=new ArrayList<>();
        setContentView(R.layout.activity_resultados);
        puntaje=getIntent().getStringExtra("puntaje");
        crono=getIntent().getStringExtra("tiempo");
        palabra=findViewById(R.id.palabrasId);
        acertar=findViewById(R.id.acertarId);
        tiempo=findViewById(R.id.tiempoId);
        porcentaje=findViewById(R.id.porcentajeId);
        regresar=findViewById(R.id.volver);
        verificar();
        palabra.setText(String.valueOf(Integer.valueOf(puntaje)+3));
        acertar.setText(puntaje);
        tiempo.setText(crono);
        double valor= ((Double.valueOf(puntaje))/(Double.valueOf(puntaje)+3)*100);
        porcentaje.setText(String.valueOf(valor)+"%");
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Resultados.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void verificar() {
        Crud crud=new Crud(this,"color",null,1);
        crud.consultar(this,lista);
        ContentValues registro = new ContentValues();
        registro.put("puntaje",puntaje);
        for(int i=0;i<5;i++){
            if(Integer.valueOf(puntaje)>=lista.get(i)){
                crud.modificar(this,registro,String.valueOf(i+1));
                break;
            }
        }
    }
}
