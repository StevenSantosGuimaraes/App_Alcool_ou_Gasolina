package br.com.steven.alcoolougasolina.util;

import android.content.Context;

import br.com.steven.alcoolougasolina.R;

public class UtilAlcoolGasolina {

    private static double LIMIAR_ALCOOL_GASOLINA = 0.7;

    public static double calcularFatorLitro(double alcool, double gasolina) {
        return alcool / gasolina;
    }

    public static String analisarPrecoPorLitro(double fator, Context context) {
        String resposta = "";
        if ( fator >= LIMIAR_ALCOOL_GASOLINA) {
            resposta = context.getString(R.string.melhor_utilizar_gasolina);
        } else {
            resposta = context.getString(R.string.melhor_utilizar_alcool);
        }
        return resposta;
    }

}
