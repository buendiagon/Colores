package com.example.bienestaraprendiz.colorapp.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Crud extends SQLiteOpenHelper{
    public Crud(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_puntaje(id integer primary key autoincrement,puntaje int)");
        for(int i=0;i<5;i++){
            ContentValues registro=new ContentValues();
            registro.put("puntaje","0");
            db.insert("tb_puntaje",null,registro);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tb_puntaje");
        db.execSQL("create table tb_puntaje(id integer primary key autoincrement,puntaje text)");
    }
    public void modificar(Context context,ContentValues registro,String id){
        Crud admin=new Crud(context,"color",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        db.update("tb_puntaje",registro,"id="+id,null);
    }
    public void consultar(Context context, ArrayList<Integer> puntaje){
        Crud admin=new Crud(context,"color",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from tb_puntaje",null);
        while (cursor.moveToNext()){
            puntaje.add(Integer.valueOf(cursor.getString(1)));
        }
        cursor.close();
    }
    public void iniciar(Context context){
        Crud admin=new Crud(context,"color",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
    }
}
