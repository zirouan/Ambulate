package br.liveo.utils;

public class Constantes {

	public static final String COISAS_FRAGMENT = "COISAS_FRAGMENT";	
	public static final String OBJ_COISAS = "OBJ_COISAS"; 
	
	public static String[] CREATE_TABELAS() {
		return new String[] {CREATE_COISAS, INSERT_UM, INSERT_DOIS, INSERT_TRES, INSERT_QUATRO, INSERT_CINCO, INSERT_SEIS};
	}
	
	private static String CREATE_COISAS = " CREATE TABLE COISAS ( ID INTEGER CONSTRAINT 'PK_COISAS' PRIMARY KEY AUTOINCREMENT, "			
		     + " NOME CHAR(50) NOT NULL ); ";	
	
	private static String INSERT_UM = "INSERT INTO COISAS( NOME ) VALUES ( 'Android' );"; 
	private static String INSERT_DOIS = "INSERT INTO COISAS( NOME ) VALUES ( 'IOS' );";
	private static String INSERT_TRES = "INSERT INTO COISAS( NOME ) VALUES ( 'Windows Phone' );";
	private static String INSERT_QUATRO = "INSERT INTO COISAS( NOME ) VALUES ( 'Google' );";	
	private static String INSERT_CINCO = "INSERT INTO COISAS( NOME ) VALUES ( 'Apple' );";	
	private static String INSERT_SEIS = "INSERT INTO COISAS( NOME ) VALUES ( 'MicroSoft' );";	
}
