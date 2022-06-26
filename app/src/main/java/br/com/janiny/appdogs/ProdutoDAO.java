package br.com.janiny.appdogs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static void inserir(Context context, Produto produto){

        ContentValues valores = new ContentValues();
        valores.put("nome",produto.getNome());
        valores.put("categoria",produto.getCategoria());
        valores.put("castracao",produto.getCastracao());
        valores.put("raca",produto.getRaca());
        valores.put("porte",produto.getPorte());
        valores.put("idade",produto.getIdade());
        valores.put("ONG",produto.getONG());
        valores.put("contato",produto.getContato());
        valores.put("foto",produto.getFoto());
        valores.put("observacoes",produto.getObservacoes());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("produto", null, valores);


    }


    public static List<Produto> getProdutos(Context context){

        List<Produto> lista = new ArrayList<>();

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM produto ORDER BY nome",null);

        if (cursor.getCount() > 0 ){

            cursor.moveToFirst();

            do{
                Produto pro = new Produto();
                pro.setId(cursor.getInt(0));
                pro.setNome(cursor.getString(1));
                pro.setCategoria(cursor.getString(2));
                pro.setCastracao(cursor.getString(3));
                pro.setRaca(cursor.getString(4));
                pro.setPorte(cursor.getString(5));
                pro.setIdade(cursor.getString(6));
                pro.setONG(cursor.getString(7));
                pro.setContato(cursor.getString(8));
                pro.setFoto(cursor.getBlob(9));
                pro.setObservacoes(cursor.getString(10));
                lista.add(pro);
            }while (cursor.moveToNext());

        }
        return lista;

    }

    public static Produto getProdutoByID(Context context, int idProduto){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM produto WHERE id ="+idProduto,null);

        if (cursor.getCount() > 0 ){

            cursor.moveToFirst();

            Produto pro = new Produto();
            pro.setId(cursor.getInt(0));
            pro.setNome(cursor.getString(1));
            pro.setCategoria(cursor.getString(2));
            pro.setCastracao(cursor.getString(3));
            pro.setRaca(cursor.getString(4));
            pro.setPorte(cursor.getString(5));
            pro.setIdade(cursor.getString(6));
            pro.setONG(cursor.getString(7));
            pro.setContato(cursor.getString(8));
            pro.setFoto(cursor.getBlob(9));
            pro.setObservacoes(cursor.getString(10));

            return pro;
        } else{
            return null;}

    }
}
