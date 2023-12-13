package br.com.steven.alcoolougasolina.controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import br.com.steven.alcoolougasolina.database.AlcoolGasolinaDB;
import br.com.steven.alcoolougasolina.model.Combustivel;
import br.com.steven.alcoolougasolina.view.MainActivity;

public class CombustivelController extends AlcoolGasolinaDB {

    SharedPreferences preferences;
    private SharedPreferences.Editor dadosPreferences;
    public static final String NOME_PREFERENCES = "pref_alcool_gasolina";

    public CombustivelController(Context context) {
        super(context);
        preferences = context.getSharedPreferences(NOME_PREFERENCES, 0);
        dadosPreferences = preferences.edit();
    }

    public void buscar(Combustivel combustivel) {

    }

    public void salvar (Combustivel combustivel) {

        ContentValues dados = new ContentValues();

        dadosPreferences.putString("Combustivel", combustivel.getNomeCombustivel());
        dadosPreferences.putFloat("Preco", (float) combustivel.getPrecoCombustivel());
        dadosPreferences.putString("Recomendacao", combustivel.getRecomendacao());
        dadosPreferences.apply();

        dados.put("nome", combustivel.getNomeCombustivel());
        dados.put("preco", combustivel.getPrecoCombustivel());
        dados.put("recomendacao", combustivel.getRecomendacao());

        salvarObjeto("Combustivel", dados);

    }

    public List<Combustivel> getListaDeDados() {
        return listarDados();
    }

    public void alterar(Combustivel combustivel) {

        ContentValues dados = new ContentValues();

        dados.put("id", combustivel.getId());
        dados.put("nome", combustivel.getNomeCombustivel());
        dados.put("preco", combustivel.getPrecoCombustivel());
        dados.put("recomendacao", combustivel.getRecomendacao());

        alterarObjeto("Combustivel", dados);

    }

    public void apagar(int pk) {
        apagarObjeto("Combustivel", pk);
    }

    public void apagarSharedPreferences () {
        dadosPreferences.clear();
        dadosPreferences.apply();
    }

}
