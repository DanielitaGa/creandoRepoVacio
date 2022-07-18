package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite_OpenHelper extends SQLiteOpenHelper {


    public SQLite_OpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table usuarios(_ID integer primary key autoincrement, " +
                "Nombre text, Distrito text, Correo text, Password text);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Método que me permite abrir la BD
    public void abrir(){
        this.getReadableDatabase();
    }

    //Método que me permite cerrar la BD
    public void cerrar(){
        this.close();
    }

    //Método que me permite insertar registros en la tabla ususarios
    public void insertarReg(String nom, String dis, String cor, String pas){
        ContentValues valores = new ContentValues();
        valores.put("Nombre",nom);
        valores.put("Distriro",dis);
        valores.put("Correo",cor);
        valores.put("Password",pas);
        this.getWritableDatabase().insert("usuarios",null,valores);
    }

    //Método que permite validar si el usuario existe
    public Cursor ConsultarUsuPas(String usu, String pas) throws SQLException{
            Cursor mcursor = null;
            mcursor = this.getReadableDatabase().query("usuarios",new String[] {"_ID",
        "Nombre", "Distrito","Correo","Password"},"Correo like '"+usu+"' "+
                    "and Password like '"+pas+"'",null,null,null,null);
        return mcursor;
    }



}
