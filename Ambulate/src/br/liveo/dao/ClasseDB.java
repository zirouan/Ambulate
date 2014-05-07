package br.liveo.dao;

import br.liveo.utils.Constantes;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ClasseDB extends SQLiteOpenHelper {

	private static ClasseDB mInstance = null;
	private static String TAG = "ClasseDB";	
	
	public ClasseDB(Context context) {
		super(context, "ambulate.db", null, 1);
		// TODO Auto-generated constructor stub
	}
	
	public static synchronized ClasseDB getInstance(Context context) {		
		if (mInstance == null) {
			mInstance = new ClasseDB(context.getApplicationContext());
		}
		return mInstance;
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		
    	try {

    		String[] TABELAS = Constantes.CREATE_TABELAS();    		
    		for (int i = 0; i < TABELAS.length; i++) {				
    			database.execSQL(TABELAS[i]);
    			Log.i(TAG, TABELAS[i]);
			}
    		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, "O Banco papocou xapa o-O");			
		}
	}
		
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	}	
}
