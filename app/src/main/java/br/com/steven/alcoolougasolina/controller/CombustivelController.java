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
    }

    public List<Combustivel> getListaDeDados() {
        return listarDados();
    }

    public void salvar (Combustivel combustivel) {
        ContentValues dados = new ContentValues();
        dados.put("nome", combustivel.getNomeCombustivel());
        dados.put("preco", combustivel.getPrecoCombustivel());
        dados.put("recomendacao", combustivel.getRecomendacao());
        salvarObjeto("Combustivel", dados);
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

}
