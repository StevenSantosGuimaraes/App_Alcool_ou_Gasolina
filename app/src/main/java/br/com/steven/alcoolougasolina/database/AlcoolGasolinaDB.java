package br.com.steven.alcoolougasolina.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.steven.alcoolougasolina.model.Combustivel;

public class AlcoolGasolinaDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "alcoolgasolina.db";
    private static final int DB_VERSION = 1;

    public AlcoolGasolinaDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("AlcoolGasolinaDB", "Criando tabela Combustivel...");
        String sqlTabelaCombustivel =
            "CREATE TABLE Combustivel (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT, " +
                    "preco REAL, " +
                    "recomendacao TEXT " +
                    ")";
        db.execSQL(sqlTabelaCombustivel);
        Log.d("AlcoolGasolinaDB", "Tabela Combustivel criada com sucesso.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Sem implementação.
    }

    public void salvarObjeto(String tabela, ContentValues dados) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(tabela, null, dados);
        db.close();
    }

    public List<Combustivel> listarDados() {
        List<Combustivel> lista = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Combustivel", null);
        if (cursor.moveToFirst()) {
            do {
                Combustivel registro = new Combustivel();
                registro.setId(cursor.getInt(0));
                registro.setNomeCombustivel(cursor.getString(1));
                registro.setPrecoCombustivel(cursor.getDouble(2));
                registro.setRecomendacao(cursor.getString(3));
                lista.add(registro);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    public void alterarObjeto(String tabela, ContentValues dados) {
        SQLiteDatabase db = getWritableDatabase();
        int pk = dados.getAsInteger("id");
        db.update(tabela, dados, "id=?", new String[]{Integer.toString(pk)});
        db.close();
    }

    public void apagarObjeto(String tabela, int pk) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(tabela, "id=?", new String[]{Integer.toString(pk)});
        db.close();
    }

}
