package br.com.steven.alcoolougasolina.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.steven.alcoolougasolina.model.Combustivel;

public class AlcoolGasolinaDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "alcoolgasolina.db";
    private static final int DB_VERSION = 1;

    Cursor cursor;
    SQLiteDatabase db;

    public AlcoolGasolinaDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL(
        db.execSQL(
                "CREATE TABLE Combustivel (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT, " +
                    "preco REAL, " +
                    "recomendacao TEXT " +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void salvarObjeto(String tabela, ContentValues dados) {
        db.insert(tabela, null, dados);
    }

    public List<Combustivel> listarDados() {
        List<Combustivel> lista = new ArrayList<>();
        Combustivel registro;
        cursor = db.rawQuery("SELECT * FROM Combustivel", null);
        if (cursor.moveToFirst()) {
            do {
                registro = new Combustivel();
                registro.setId(cursor.getInt(0));
                registro.setNomeCombustivel(cursor.getString(1));
                registro.setPrecoCombustivel(cursor.getDouble(2));
                registro.setRecomendacao(cursor.getString(3));
                lista.add(registro);
            } while (cursor.moveToNext());
        } else {

        }
        return lista;
    }

    public void alterarObjeto(String tabela, ContentValues dados) {
        int pk = dados.getAsInteger("id");
        db.update(tabela, dados, "id=?", new String[] {
                Integer.toString(pk)
        });
    }

    public void apagarObjeto(String tabela, int pk) {
        db.delete(tabela, "id=?", new String[] {
                Integer.toString(pk)
        });
    }

}
