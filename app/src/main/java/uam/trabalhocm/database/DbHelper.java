package uam.trabalhocm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import uam.trabalhocm.classes.Usuario;

/**
 * Created by Gabriel on 30/11/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AppNutri.db";
    private static final String TABLE_USER = "user";
    private static  final String COLUMN_USER_ID = "user_id";
    private static  final String COLUMN_USER_NAME = "user_name";
    private static  final String COLUMN_USER_EMAIL = "user_email";
    private static  final String COLUMN_USER_PASSWORD = "user_password";

    private String CREATE_USER_TABLE = "CREATE TABLE "+TABLE_USER+ "("
            +COLUMN_USER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL +" TEXT,"+COLUMN_USER_PASSWORD+ " TEXT"+")";


    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(Usuario u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, u.getLogin());
        values.put(COLUMN_USER_EMAIL,u.getEmail());
        values.put(COLUMN_USER_PASSWORD,u.getSenha());

        db.insert(TABLE_USER,null,values);
        db.close();
    }


    public boolean checkUser(String login, String password){ // Ou email
        String[] collums = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_NAME + "= ?"+" AND "+ COLUMN_USER_PASSWORD + " =?"; // Ou email
        String[] selectionArgs = {login,password}; //Ou email

        Cursor cursor = db.query(TABLE_USER, collums,selection,selectionArgs,null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if(cursorCount>0){
            return true;
        }
        return false;

    }
}
