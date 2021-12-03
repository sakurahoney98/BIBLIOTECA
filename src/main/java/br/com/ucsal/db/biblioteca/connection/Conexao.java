package br.com.ucsal.db.biblioteca.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private	String url;
	private	String usuario;
	private	String senha;
	private	Connection conexao;
	
	public Conexao(){
		url="jdbc:postgresql://localhost:5432/BIBLIOTECA"; 
		usuario ="postgres";
		senha="postgres";
		
		try {
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(url,usuario, senha);
			System.out.println("Conexão estabelecida");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

	public Connection getConexao() {
		return conexao;
	}
	
	


}
