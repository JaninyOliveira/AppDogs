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

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("produto", null, valores);


    }

    public static void editar(Context context, Produto produto){

        ContentValues valores = new ContentValues();
        valores.put("nome",produto.getNome());
        valores.put("categoria",produto.getCategoria());

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("produto",valores, "id = " + produto.getId(),null);

    }

    public static void excluir(Context context, int idProduto){

        Banco conn = new Banco(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("produto","id ="+idProduto,null);
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

            return pro;
        } else{
            return null;}

    }
}
