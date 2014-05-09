package br.liveo.utils;

public class Constantes {

	public static final String COISAS_FRAGMENT = "COISAS_FRAGMENT";	
	public static final String OBJ_COISAS = "OBJ_COISAS"; 
		
	public static final String BANCO = "ambulate.db";
	
	public static final String TABELA_COISAS = "COISAS";
	public static final String ID = "ID";	
	public static final String NOME = "NOME";	
	
	public static String[] CREATE_TABELAS() {
		return new String[] {CREATE_COISAS};
	}
	
	private static String CREATE_COISAS = " CREATE TABLE COISAS ( ID INTEGER CONSTRAINT 'PK_COISAS' PRIMARY KEY AUTOINCREMENT, "			
		     + " NOME CHAR(50) NOT NULL ); ";	
}
