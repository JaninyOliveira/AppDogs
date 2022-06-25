package br.com.janiny.appdogs;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FichaDogsActivity extends AppCompatActivity {

    private TextView etNome;
    private TextView spCategorias;
    private TextView rdCastrado;
    private TextView etRaca;
    private TextView etPorte;
    private TextView etIdade;
    private TextView etONG;
    private TextView etContato;
    private ImageView imFoto;
    private TextView etObservacoes;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ficha_dogs);

        etNome = findViewById(R.id.etNome);
        spCategorias = findViewById(R.id.spCategorias);
        rdCastrado = findViewById(R.id.rdCastrado);
        etRaca = findViewById(R.id.etRaca);
        etPorte = findViewById(R.id.etPorte);
        etIdade = findViewById(R.id.etIdade);
        etONG = findViewById(R.id.etONG);
        etContato = findViewById(R.id.etContato);
        imFoto = findViewById(R.id.imFoto);
        etObservacoes = findViewById(R.id.etObservacoes);

        carregarFicha();

    }


    private void carregarFicha(){
        int idProduto = getIntent().getIntExtra("idProduto", 0);
        Produto produto = ProdutoDAO.getProdutoByID(this, idProduto);
        etNome.setText(produto.getNome());
        spCategorias.setText(produto.getCategoria());
        rdCastrado.setText(produto.getCastracao());
        etRaca.setText(produto.getRaca());
        etPorte.setText(produto.getPorte());
        etIdade.setText(produto.getIdade());
        etONG.setText(produto.getONG());
        etContato.setText(produto.getContato());
        Bitmap bmp = BitmapFactory.decodeByteArray(produto.getFoto(), 0, produto.getFoto().length);
        imFoto.setImageBitmap(bmp);
        etObservacoes.setText(produto.getObservacoes());


    }

}