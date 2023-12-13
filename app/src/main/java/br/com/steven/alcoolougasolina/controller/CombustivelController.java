package br.com.steven.alcoolougasolina.controller;

import android.content.SharedPreferences;

import br.com.steven.alcoolougasolina.model.Combustivel;
import br.com.steven.alcoolougasolina.view.MainActivity;

public class CombustivelController {

    SharedPreferences preferences;
    private SharedPreferences.Editor dadosPreferences;
    public static final String NOME_PREFERENCES = "pref_alcool_gasolina";

    public CombustivelController(MainActivity activity) {
        preferences = activity.getSharedPreferences(NOME_PREFERENCES, 0);
        dadosPreferences = preferences.edit();
    }

    public void buscar(Combustivel combustivel) {

    }

    public void salvar (Combustivel combustivel) {
        dadosPreferences.putString("Combustivel", combustivel.getNomeCombustivel());
        dadosPreferences.putFloat("Preco", (float) combustivel.getPrecoCombustivel());
        dadosPreferences.putString("Recomendacao", combustivel.getRecomendacao());
        dadosPreferences.apply();
    }

    public void apagarSharedPreferences () {
        dadosPreferences.clear();
        dadosPreferences.apply();
    }

}
