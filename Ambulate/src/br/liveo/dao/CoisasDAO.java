package br.liveo.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.liveo.modelo.Coisas;
import br.liveo.utils.Constantes;

public class CoisasDAO {
	
	private static final String TAG = "CoisasDAO";
	
	private Cursor cursor;
	private Context context;
	private ClasseDB classeDB;	
	private SQLiteDatabase db = null;
	
	private static CoisasDAO instance;	
	public CoisasDAO(Context context){
		super();
		this.context = context;	
	}

	public static CoisasDAO getInstance(Context context){
		if(instance == null){			
			synchronized (CoisasDAO.class) {
				if(instance == null){
					instance = new CoisasDAO(context);
				}
			}			
		}
		return instance;
	}
	
	private void abrirConexao() {
		// TODO Auto-generated method stub		
		try{					
			this.classeDB = ClasseDB.getInstance(this.context);
			db = classeDB.getWritableDatabase();			
		}catch(Exception e){			
			Log.e(TAG, "abrirConexao - CoisasDAO " + e.getMessage());			
		}				
	}	

	private void fecharTodasConexoes() {
		// TODO Auto-generated method stub		
		try{			
			if(null != db){				 
				db.close();
				db = null;
				classeDB.close();				
			}			
		}catch(Exception e){			
		}			
	}

	public void salvarCoisa(String nome){
				
		try {
			ContentValues colunas = new ContentValues();
			colunas.put(Constantes.NOME, nome);
			
			abrirConexao();
			db.insertOrThrow(Constantes.TABELA_COISAS, Constantes.BANCO, colunas);
		} catch (Exception e) {
			e.getMessage();
		}finally{
			fecharTodasConexoes();
		}
	}
	
	public ArrayList<Coisas> obterTudoCoisas() {
		// TODO Auto-generated method stub
		ArrayList<Coisas> arrayListCoisas = null;

		StringBuilder qrBuilder = new StringBuilder();
		
		qrBuilder.append(" SELECT ID, NOME ");
		qrBuilder.append(" FROM COISAS " );
		qrBuilder.append(" ORDER BY ID ASC " );		

		try {

			abrirConexao();
			cursor = db.rawQuery(qrBuilder.toString(), null);

			if (cursor != null) {
				cursor.moveToFirst();
			}

			if (cursor.getCount() > 0) {
				arrayListCoisas = new ArrayList<Coisas>();
				Coisas coisas = null;
				
				while (!cursor.isAfterLast()) {
					coisas = new Coisas();

					coisas.setId(cursor.getInt(0));
					coisas.setNome(cursor.getString(1));
						
					arrayListCoisas.add(coisas);
					cursor.moveToNext();
				}
			}

			cursor.close();
		} catch (Exception e) {
			Log.e(TAG, "Erro CoisasDAO obterTudoCoisas: ", e);
		} finally {
			fecharTodasConexoes();
		}

		return arrayListCoisas;
	}			
}
