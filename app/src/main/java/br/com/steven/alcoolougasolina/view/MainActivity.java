package br.com.steven.alcoolougasolina.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.steven.alcoolougasolina.R;
import br.com.steven.alcoolougasolina.util.UtilAlcoolGasolina;

public class MainActivity extends AppCompatActivity {

    private EditText editAlcool;
    private EditText editGasolina;
    private Button btnSalvar;
    private Button btnLimpar;
    private Button btnCalcular;
    private TextView txtResposta;

    private Double precoAlcool;
    private Double precoGasolina;
    private Double fatorCombustivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapearComponentes();

        ImageView icone = findViewById(R.id.iconeTelaInicial);
        Drawable drawable = icone.getDrawable();
        drawable.setColorFilter(ContextCompat.getColor(this, R.color.red), PorterDuff.Mode.SRC_IN);

        btnSalvar.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "Salvando conteudo (Implementando).", Toast.LENGTH_SHORT).show();
            limparCampos();
        });

        btnLimpar.setOnClickListener(view -> limparCampos());

        btnCalcular.setOnClickListener(view -> {
            carregarDados();
            esconderTeclado();
            if (validarCompos()) {
                fatorCombustivel = UtilAlcoolGasolina.calcularFatorLitro(precoAlcool, precoGasolina);
                txtResposta.setText(UtilAlcoolGasolina.analisarPrecoPorLitro(fatorCombustivel, this));
            } else {
                Toast.makeText(this, "Informe o preço do álcool e da gasolina para efetuar o cálculo.", Toast.LENGTH_SHORT).show();
            }
        });

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

    public void limparCampos() {
        editAlcool.setText("");
        editGasolina.setText("");
        txtResposta.setText("");
        Toast.makeText(this, "Formulário reiniciado.", Toast.LENGTH_LONG).show();
    }

}
