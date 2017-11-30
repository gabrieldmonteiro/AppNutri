package uam.trabalhocm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import uam.trabalhocm.classes.Usuario;

/**
 * Created by Gabriel on 28/11/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "appnutri.db";
    private static final String TABLE_NAME = "usuarios.db";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_SENHA = "senha";
    private static final String COLUMN_PESO = "peso";
    private static final String COLUMN_ALTURA = "altura";
    SQLiteDatabase db ;

    private static  final String TABLE_CREATE = "create table" + TABLE_NAME+ " (id integer primary key not null auto_increment," +
            "login text not null, email text not null, senha text not null, peso float not null, altura float not null);";

    public DbHelper (Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertUsuario(Usuario u){
        db = this.getWritableDatabase();
        ContentValues values  = new ContentValues();


        String query = "select * from usuarios";
        Cursor cursor = db.rawQuery(query,null);
        int cont = cursor.getCount();

        values.put(COLUMN_ID,cont);
        values.put(COLUMN_LOGIN, u.getLogin());
        values.put(COLUMN_EMAIL, u.getEmail());
        values.put(COLUMN_PESO, u.getPeso());
        values.put(COLUMN_ALTURA, u.getAltura());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchSenha(String uLogin){
        db = this.getReadableDatabase();
        String query  = "SELECT * login,senha FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String l,s ;
        s = "Nao encontrado";

        if(cursor.moveToFirst()){
            do{
                l = cursor.getString(0);
                if(l.equals(uLogin)){
                    s = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return s;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
