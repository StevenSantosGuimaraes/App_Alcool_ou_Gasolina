package br.com.steven.alcoolougasolina.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.steven.alcoolougasolina.R;
import br.com.steven.alcoolougasolina.adapter.CombustivelAdapter;
import br.com.steven.alcoolougasolina.controller.CombustivelController;
import br.com.steven.alcoolougasolina.model.Combustivel;
import br.com.steven.alcoolougasolina.util.UtilAlcoolGasolina;

public class MainActivity extends AppCompatActivity implements CombustivelAdapter.OnItemLongClickListener{

    Combustivel combustivelAlcool;
    Combustivel combustivelGasolina;

    CombustivelController combustivelController;

    private EditText editAlcool;
    private EditText editGasolina;
    private Button btnSalvar;
    private Button btnLimpar;
    private Button btnCalcular;
    private TextView txtResposta;

    private Double precoAlcool;
    private Double precoGasolina;
    private Double fatorCombustivel;
    private String analise;

    private List<Combustivel> dados;
    private RecyclerView recyclerView;
    private CombustivelAdapter combustivelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapearComponentes();

        btnSalvar.setEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        combustivelController = new CombustivelController(this);
        dados = combustivelController.getListaDeDados();

        combustivelAdapter = new CombustivelAdapter(dados, (CombustivelAdapter.OnItemLongClickListener) this);
        recyclerView.setAdapter(combustivelAdapter);

        btnSalvar.setOnClickListener(view -> {

            combustivelAlcool =  new Combustivel();
            combustivelAlcool.setNomeCombustivel("Alcool");
            combustivelAlcool.setPrecoCombustivel(precoAlcool);
            combustivelAlcool.setRecomendacao(analise);

            combustivelGasolina =  new Combustivel();
            combustivelGasolina.setNomeCombustivel("Gasolina");
            combustivelGasolina.setPrecoCombustivel(precoGasolina);
            combustivelGasolina.setRecomendacao(analise);

            combustivelController.salvar(combustivelAlcool);
            combustivelController.salvar(combustivelGasolina);

            atualizarListaTela();
            limparCamposFormulario();
        });

        btnLimpar.setOnClickListener(view -> {
            limparCamposFormulario();
        });

        btnCalcular.setOnClickListener(view -> {
            carregarDados();
            esconderTeclado();
            if (validarCompos()) {
                fatorCombustivel = UtilAlcoolGasolina.calcularFatorLitro(precoAlcool, precoGasolina);
                analise = UtilAlcoolGasolina.analisarPrecoPorLitro(fatorCombustivel, this);
                txtResposta.setText(analise);
                btnSalvar.setEnabled(true);
            } else {
                limparCamposFormulario();
                Toast.makeText(this, "O preço do álcool e da gasolina são obrigatórios para efetuar o cálculo.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void atualizarListaTela() {
        dados.clear();
        dados.addAll(combustivelController.getListaDeDados());
        combustivelAdapter.notifyDataSetChanged();
    }

    private boolean validarCompos() {
        return precoAlcool != null && precoGasolina != null;
    }

    public void mapearComponentes() {
        editAlcool = findViewById(R.id.editTextAlcool);
        editGasolina = findViewById(R.id.editTextGasolina);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtResposta = findViewById(R.id.textViewResposta);
        recyclerView = findViewById(R.id.recyclerViewMinhaLista);
    }

    public void carregarDados() {
        try {
            precoAlcool = Double.parseDouble(editAlcool.getText().toString());
            precoGasolina = Double.parseDouble(editGasolina.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void esconderTeclado() {
        InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View focoAtual = getCurrentFocus();
        if (focoAtual != null) {
            input.hideSoftInputFromWindow(focoAtual.getWindowToken(), 0);
        }
    }

    public void limparCamposFormulario() {
        editAlcool.setText("");
        editGasolina.setText("");
        txtResposta.setText("");
        btnSalvar.setEnabled(false);
        Toast.makeText(this, "Formulário reiniciado.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemLongClick(int position) {
        showConfirmationDialog(position);
    }

    private void showConfirmationDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmação");
        builder.setMessage("Deseja remover este item?");

        builder.setPositiveButton("Sim", (dialog, which) -> {
            removerItemBancoDados(position);
        });

        builder.setNegativeButton("Não", (dialog, which) -> {
            // Fechar o diálogo
        });

        builder.create().show();
    }

    private void removerItemBancoDados(int position) {
        Combustivel combustivel = dados.get(position);
        combustivelController.apagar(combustivel.getId());
        atualizarListaTela();
    }

}
