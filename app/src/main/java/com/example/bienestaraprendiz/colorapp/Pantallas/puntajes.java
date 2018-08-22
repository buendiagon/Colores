package com.example.bienestaraprendiz.colorapp.Pantallas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bienestaraprendiz.colorapp.BD.Crud;
import com.example.bienestaraprendiz.colorapp.R;

import java.util.ArrayList;

public class puntajes extends AppCompatActivity {
    ArrayList<Integer> lista;
    TextView pun1,pun2,pun3,pun4,pun5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntajes);
        lista=new ArrayList<>();
        Crud crud=new Crud(this,"color",null,1);
        crud.consultar(this,lista);
        pun1=findViewById(R.id.puntaje1);
        pun2=findViewById(R.id.puntaje2);
        pun3=findViewById(R.id.puntaje3);
        pun4=findViewById(R.id.puntaje4);
        pun5=findViewById(R.id.puntaje5);
        pun1.setText(lista.get(0).toString());
        pun2.setText(lista.get(1).toString());
        pun3.setText(lista.get(2).toString());
        pun4.setText(lista.get(3).toString());
        pun5.setText(lista.get(4).toString());
    }
}
