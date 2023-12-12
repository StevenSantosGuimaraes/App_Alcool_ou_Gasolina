package br.com.steven.appalcoolougasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editAlcool;
    private EditText editGasolina;
    private Button btnCalcular;
    private TextView txtResposta;

    private Double precoAlcool;
    private Double precoGasolina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapearComponentes();
        btnCalcular.setOnClickListener(view -> {
            carregarDados();
            esconderTeclado();
            if (validarCompos()) {
                calcularPreco();
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

    public void calcularPreco() {
        Double resultado = precoAlcool / precoGasolina;
        if ( resultado >= 0.70) {
            txtResposta.setText("Melhor utilizar gasolina.");
        } else {
            txtResposta.setText("Melhor utilizar álcool.");
        }
    }

    public void esconderTeclado() {
        InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View focoAtual = getCurrentFocus();
        if (focoAtual != null) {
            input.hideSoftInputFromWindow(focoAtual.getWindowToken(), 0);
        }
    }

}