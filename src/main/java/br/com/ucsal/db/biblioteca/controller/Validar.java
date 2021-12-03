package br.com.ucsal.db.biblioteca.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;

public class Validar {
	
	final int qtdeMaxima = 8000;

	public boolean validarNome(String nome) {
		if (nome.equals("") || nome.equals(null)) {
			System.out.println("\n\nNome não pode ficar em branco.\n\n");
			return false;
		} else {
			return true;
		}
	}

	public boolean validarCodigo(int codigo) {
		if (codigo <= 0) {
			System.out.println("\n\nO valor do código não pode ser menor ou igual a 0.\n\n");
			return false;
		} else {
			return true;
		}
	}

	public boolean validarAno(int ano) {
		if (ano > Year.now().getValue()) {
			System.out.println("\n\nO ano informado é maior do que o ano atual.\n\n");
			return false;
		} else {
			return true;
		}
	}
	
	public boolean quantMaximaLivro (Connection conexao) throws SQLException {
		Statement st = conexao.createStatement();
		String comando = "select count(cod_livro) from public.livro ";
		ResultSet resultado = st.executeQuery(comando);
		 int quant = 0;
		if (resultado.next()) {
			quant = resultado.getInt(1);
			if(quant >= qtdeMaxima) {
				return false;
			}
		}
		 
		
		
		return true;
	}
	
	public boolean quantMaximaAutor (Connection conexao) throws SQLException {
		Statement st = conexao.createStatement();
		String comando = "select count(cod_autor) from public.autor ";
		ResultSet resultado = st.executeQuery(comando);
		 int quant = 0;
		if (resultado.next()) {
			quant = resultado.getInt(1);
			if(quant > qtdeMaxima) {
				return false;
			}
		}
		 
		
		
		return true;
	}
	
	public boolean quantMaximaCategoria (Connection conexao) throws SQLException {
		Statement st = conexao.createStatement();
		String comando = "select count(cod_categoria) from public.categoria ";
		ResultSet resultado = st.executeQuery(comando);
		 int quant = 0;
		if (resultado.next()) {
			quant = resultado.getInt(1);
			if(quant > qtdeMaxima) {
				return false;
			}
		}
		 
		
		
		return true;
	}
	
	public boolean quantMaximaEditora (Connection conexao) throws SQLException {
		Statement st = conexao.createStatement();
		String comando = "select count(cod_editora) from public.editora ";
		ResultSet resultado = st.executeQuery(comando);
		 int quant = 0;
		if (resultado.next()) {
			quant = resultado.getInt(1);
			if(quant > qtdeMaxima) {
				return false;
			}
		}
		 
		
		
		return true;
	}

}
