package uam.trabalhocm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;


 public class DbAdapter {
    public static final String KEY_ID = "_id";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_EMAIL = "email";
     public static final String KEY_SENHA = "senha";
     public static final String KEY_PESO = "peso";
     public static final String KEY_ALTURA = "altura";
    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "AppNutriDB";
    private static final String DATABASE_TABLE = "usuarios";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE =
            "create table usuarios (_id integer primary key autoincrement, "
                    + "login text not null, email text not null, senha text not null," +
                    "peso float not null, altura float not null);";

    private final Context context;

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DbAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS usuarios");
            onCreate(db);
        }
    }

    //---opens the database---
    public DbAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }

    //---insert a contact into the database---
    public long insertUsuario(String login, String email, String senha, float peso,float altura)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_LOGIN, login);
        initialValues.put(KEY_EMAIL, email);
        initialValues.put(KEY_SENHA, senha);
        initialValues.put(KEY_PESO,peso);
        initialValues.put(KEY_ALTURA,altura);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular contact---
    public boolean deleteContact(long rowId)
    {
        return db.delete(DATABASE_TABLE, KEY_ID + "=" + rowId, null) > 0;
    }

    //---retrieves all the contacts---
    public Cursor getAllContacts()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ID, KEY_LOGIN,
                KEY_EMAIL,KEY_PESO,KEY_ALTURA}, null, null, null, null, null);
    }

    //---retrieves a particular contact---
    public Cursor getContact(long rowId) throws SQLException
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ID,
                                KEY_LOGIN, KEY_EMAIL,KEY_PESO,KEY_ALTURA}, KEY_ID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a contact---
    public boolean updateContact(long rowId, String login, String email,float peso, float altura)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_LOGIN, login);
        args.put(KEY_EMAIL, email);
        args.put(KEY_PESO, peso);
        args.put(KEY_ALTURA, altura);
        return db.update(DATABASE_TABLE, args, KEY_ID + "=" + rowId, null) > 0;
    }
}

