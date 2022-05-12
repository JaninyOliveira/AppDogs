package br.com.janiny.appdogs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private Spinner spCategorias;
    private Button btnSalvar;
    private CheckBox rdCastrado;
    private String acao;
    private Produto produto;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        spCategorias = findViewById(R.id.spCategorias);
        rdCastrado = findViewById(R.id.rdCastrado);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        acao = getIntent().getStringExtra("acao");
        if(acao.equals("editar")){
            carregarFormulario();
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
            produto.setCastracao(rdCastrado.isChecked() ? "Sim":"Não"); //TODO: trocar por valor do campo castracao
            if (acao.equals("inserir")){
                ProdutoDAO.inserir(this,produto);
                etNome.setText("");
                spCategorias.setSelection(0, true);
            } else {
                ProdutoDAO.editar(this,produto);
                finish();
            }


        }
    }
}