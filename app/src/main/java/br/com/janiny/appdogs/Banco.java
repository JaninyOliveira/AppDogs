package br.com.janiny.appdogs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "AppDogs";
    private static final int VERSAO = 7;

    public Banco(Context context){

        super(context, NOME_BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS produto " +
                "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "categoria TEXT NOT NULL,"+
                "castracao TEXT NOT NULL,"+
                "raca TEXT NOT NULL,"+
                "porte TEXT NOT NULL,"+
                "idade TEXT NOT NULL,"+
                "ONG TEXT NOT NULL,"+
                "contato TEXT NOT NULL,"+
                "foto BLOB NOT NULL,"+
                "observacoes TEXTO);");
                ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS produto ");
        onCreate(sqLiteDatabase);
    }
}
