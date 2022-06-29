package br.com.janiny.appdogs;

import static br.com.janiny.appdogs.R.id.imFoto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private Spinner spCategorias;
    private Button btnSalvar;
    private CheckBox rdCastrado;
    private EditText etRaca;
    private EditText etPorte;
    private EditText etIdade;
    private EditText etONG;
    private EditText etContato;
    private ImageView imFoto;
    private EditText etObservacoes;
    private String acao;
    private Produto produto;

    Uri selectedImage;
    String part_image;
    private static final int PICK_IMAGE_REQUEST = 9544;
    // Permissão para acessar o arquivo
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

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
        btnSalvar = findViewById(R.id.btnSalvar);

        imFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick();

            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        acao = getIntent().getStringExtra("acao");

    }

    public void pick() {
        verifyStoragePermissions(FormularioActivity.this);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Open Gallery"), PICK_IMAGE_REQUEST);
    }

    // Method to get the absolute path of the selected image from its URI
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST) {
            if (resultCode == RESULT_OK) {
                selectedImage = data.getData();                                                         // Get the image file URI
                String[] imageProjection = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, imageProjection, null, null, null);
                if(cursor != null) {
                    cursor.moveToFirst();
                    int indexImage = cursor.getColumnIndex(imageProjection[0]);
                    part_image = cursor.getString(indexImage);
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imFoto.setImageBitmap(bitmap);                                                       // Set the ImageView with the bitmap of the image
                }
            }
        }
    }


    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    private void carregarFormulario(){
        int idProduto = getIntent().getIntExtra("idProduto", 0);
        produto = ProdutoDAO.getProdutoByID(this,idProduto);
        etNome.setText(produto.getNome());
        String[] categorias = getResources().getStringArray(R.array.categorias);

        for( int i = 0; i< categorias.length; i++) {
            if (produto.getCategoria().equals(categorias[i])) {
                spCategorias.setSelection(i);
                break;
            }
        }
        etRaca.setText(produto.getRaca());
        etPorte.setText(produto.getRaca());
        etIdade.setText(produto.getIdade());
        etONG.setText(produto.getONG());
        etContato.setText(produto.getContato());
        Bitmap bmp = BitmapFactory.decodeByteArray(produto.getFoto(), 0, produto.getFoto().length);
        imFoto.setImageBitmap(bmp);
        etObservacoes.setText(produto.getObservacoes());



    }
    private void salvar(){
        String nome = etNome.getText().toString();

        if(nome.isEmpty() || spCategorias.getSelectedItemPosition()==0){
            Toast.makeText(this,"Você deve preencher todos os campos!",Toast.LENGTH_LONG).show();
        }else{
            if (acao.equals("inserir")){
                produto = new Produto();
            }

            produto.setNome(nome);
            produto.setCategoria(spCategorias.getSelectedItem().toString());
            produto.setCastracao(rdCastrado.isChecked() ? "Sim":"Não");

            Bitmap bitmap = ((BitmapDrawable) imFoto.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bytesData = stream.toByteArray();
            produto.setFoto(bytesData);
            produto.setRaca(etRaca.getText().toString());
            produto.setPorte(etPorte.getText().toString());
            produto.setIdade(etIdade.getText().toString());
            produto.setONG(etONG.getText().toString());
            produto.setContato(etContato.getText().toString());
            produto.setObservacoes(etObservacoes.getText().toString());

            if (acao.equals("inserir")){
                ProdutoDAO.inserir(this,produto);
                etNome.setText("");
                spCategorias.setSelection(0, true);
                finish();
            } 
            Intent intent = new Intent(FormularioActivity.this,MainActivity.class);
            startActivity(intent);


        }
    }
}