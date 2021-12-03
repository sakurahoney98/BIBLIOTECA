package br.com.ucsal.db.biblioteca.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.ucsal.db.biblioteca.connection.Conexao;
import br.com.ucsal.db.biblioteca.model.Autor;
import br.com.ucsal.db.biblioteca.model.Categoria;
import br.com.ucsal.db.biblioteca.model.Editora;
import br.com.ucsal.db.biblioteca.model.Livro;
import br.com.ucsal.db.biblioteca.view.Janela;

public class Buscar {




	// Busca por Livro
	public ArrayList<Livro> buscarLivroCodigo(int codigo, Connection conexao) throws SQLException {
		ArrayList<Livro> livro = new ArrayList<Livro>();
		Statement st = conexao.createStatement();
		String comando = "SELECT l.cod_livro, isbn, titulo, ano, nome_editora, descricao_categoria, nome_autor\r\n"
				+ "	FROM public.livro_autor la  inner join public.livro l ON l.cod_livro = la.cod_livro\r\n"
				+ "								inner join public.autor ON autor.cod_autor = la.cod_autor\r\n"
				+ "								inner join public.categoria ON categoria.cod_categoria = l.cod_categoria\r\n"
				+ "								inner join public.editora ON editora.cod_editora = l.cod_editora\r\n"
				+ "								WHERE l.cod_livro = " + codigo;
				
		ResultSet resultado = st.executeQuery(comando);
		if (resultado != null) {
		while (resultado.next()) {
			Livro l = new Livro(resultado.getInt(1), resultado.getInt(2), resultado.getString(3), resultado.getInt(4), resultado.getString(5), resultado.getString(6), resultado.getString(7));
			livro.add(l);
		}
		}

		return livro;

	}

	public ArrayList<Livro> buscarLivroISBN(int ISBN, Connection conexao) throws SQLException {
		ArrayList<Livro> livro = new ArrayList<Livro>();
		Statement st = conexao.createStatement();
		String comando = "SELECT l.cod_livro, isbn, titulo, ano, nome_editora, descricao_categoria, nome_autor\r\n"
				+ "	FROM public.livro_autor la  inner join public.livro l ON l.cod_livro = la.cod_livro\r\n"
				+ "								inner join public.autor ON autor.cod_autor = la.cod_autor\r\n"
				+ "								inner join public.categoria ON categoria.cod_categoria = l.cod_categoria\r\n"
				+ "								inner join public.editora ON editora.cod_editora = l.cod_editora\r\n"
				+ "								WHERE isbn = " + ISBN;
				
		ResultSet resultado = st.executeQuery(comando);
		if (resultado != null) {
		while (resultado.next()) {
			Livro l = new Livro(resultado.getInt(1), resultado.getInt(2), resultado.getString(3), resultado.getInt(4), resultado.getString(5), resultado.getString(6), resultado.getString(7));
			livro.add(l);
		}
		}

		return livro;

	}

	public ArrayList<Livro> buscarLivroEditora(int editora, Connection conexao) throws SQLException {
		ArrayList<Livro> livro = new ArrayList<Livro>();
		Statement st = conexao.createStatement();
		String comando = "SELECT l.cod_livro, isbn, titulo, ano, nome_editora, descricao_categoria, nome_autor\r\n"
				+ "	FROM public.livro_autor la  inner join public.livro l ON l.cod_livro = la.cod_livro\r\n"
				+ "								inner join public.autor ON autor.cod_autor = la.cod_autor\r\n"
				+ "								inner join public.categoria ON categoria.cod_categoria = l.cod_categoria\r\n"
				+ "								inner join public.editora ON editora.cod_editora = l.cod_editora\r\n"
				+ "								WHERE l.cod_editora = " + editora;
				
		ResultSet resultado = st.executeQuery(comando);
		if (resultado != null) {
		while (resultado.next()) {
			Livro l = new Livro(resultado.getInt(1), resultado.getInt(2), resultado.getString(3), resultado.getInt(4), resultado.getString(5), resultado.getString(6), resultado.getString(7));
			livro.add(l);
		}
		}

		return livro;

	}
	
	
	public ArrayList<Livro> buscarLivroAutor(int autor, Connection conexao) throws SQLException {
		ArrayList<Livro> livro = new ArrayList<Livro>();
		Statement st = conexao.createStatement();
		String comando = "SELECT l.cod_livro, isbn, titulo, ano, nome_editora, descricao_categoria, nome_autor\r\n"
				+ "	FROM public.livro_autor la  inner join public.livro l ON l.cod_livro = la.cod_livro\r\n"
				+ "								inner join public.autor ON autor.cod_autor = la.cod_autor\r\n"
				+ "								inner join public.categoria ON categoria.cod_categoria = l.cod_categoria\r\n"
				+ "								inner join public.editora ON editora.cod_editora = l.cod_editora\r\n"
				+ "								WHERE la.cod_autor = " + autor;
				
		ResultSet resultado = st.executeQuery(comando);
		if (resultado != null) {
		while (resultado.next()) {
			Livro l = new Livro(resultado.getInt(1), resultado.getInt(2), resultado.getString(3), resultado.getInt(4), resultado.getString(5), resultado.getString(6), resultado.getString(7));
			livro.add(l);
		}
		}

		return livro;

	}
	
	
	public boolean buscarLivroAutor (int autor, int livro, Connection conexao) throws SQLException {
		Statement st = conexao.createStatement();
		String comando = "SELECT cod_livro, cod_autor FROM public.livro_autor\r\n"
				+		"WHERE cod_autor = " + autor + " AND cod_livro = " + livro;
		ResultSet resultado = st.executeQuery(comando);
		
		if(resultado.next()) {
			return true;
		}
		return false;
	}

	public ArrayList<Livro> buscarLivroCategoria(int categoria, Connection conexao) throws SQLException {
		ArrayList<Livro> livro = new ArrayList<Livro>();
		Statement st = conexao.createStatement();
		String comando = "SELECT l.cod_livro, isbn, titulo, ano, nome_editora, descricao_categoria, nome_autor\r\n"
				+ "	FROM public.livro_autor la  inner join public.livro l ON l.cod_livro = la.cod_livro\r\n"
				+ "								inner join public.autor ON autor.cod_autor = la.cod_autor\r\n"
				+ "								inner join public.categoria ON categoria.cod_categoria = l.cod_categoria\r\n"
				+ "								inner join public.editora ON editora.cod_editora = l.cod_editora\r\n"
				+ "								WHERE l.cod_categoria = " + categoria;
				
		ResultSet resultado = st.executeQuery(comando);
		if (resultado != null) {
		while (resultado.next()) {
			Livro l = new Livro(resultado.getInt(1), resultado.getInt(2), resultado.getString(3), resultado.getInt(4), resultado.getString(5), resultado.getString(6), resultado.getString(7));
			livro.add(l);
		}
		}

		return livro;

	}

	public ArrayList<Livro> buscarLivroAno(int ano, Connection conexao) throws SQLException {
		ArrayList<Livro> livro = new ArrayList<Livro>();
		Statement st = conexao.createStatement();
		String comando = "SELECT l.cod_livro, isbn, titulo, ano, nome_editora, descricao_categoria, nome_autor\r\n"
				+ "	FROM public.livro_autor la  inner join public.livro l ON l.cod_livro = la.cod_livro\r\n"
				+ "								inner join public.autor ON autor.cod_autor = la.cod_autor\r\n"
				+ "								inner join public.categoria ON categoria.cod_categoria = l.cod_categoria\r\n"
				+ "								inner join public.editora ON editora.cod_editora = l.cod_editora\r\n"
				+ "								WHERE ano = " + ano;
				
		ResultSet resultado = st.executeQuery(comando);
		if (resultado != null) {
		while (resultado.next()) {
			Livro l = new Livro(resultado.getInt(1), resultado.getInt(2), resultado.getString(3), resultado.getInt(4), resultado.getString(5), resultado.getString(6), resultado.getString(7));
			livro.add(l);
		}
		}

		return livro;

	}

	public ArrayList<Livro> buscarLivroNome(String nome, Connection conexao) throws SQLException {
		ArrayList<Livro> livro = new ArrayList<Livro>();
		Statement st = conexao.createStatement();
		String comando = "SELECT l.cod_livro, isbn, titulo, ano, nome_editora, descricao_categoria, nome_autor\r\n"
				+ "	FROM public.livro_autor la  inner join public.livro l ON l.cod_livro = la.cod_livro\r\n"
				+ "								inner join public.autor ON autor.cod_autor = la.cod_autor\r\n"
				+ "								inner join public.categoria ON categoria.cod_categoria = l.cod_categoria\r\n"
				+ "								inner join public.editora ON editora.cod_editora = l.cod_editora\r\n"
				+ "								WHERE titulo LIKE '%" + nome +"%'";
				
		ResultSet resultado = st.executeQuery(comando);
		if (resultado != null) {
		while (resultado.next()) {
			Livro l = new Livro(resultado.getInt(1), resultado.getInt(2), resultado.getString(3), resultado.getInt(4), resultado.getString(5), resultado.getString(6), resultado.getString(7));
			livro.add(l);
		}
		}

		return livro;

	}

	// Busca por Autor
	public Autor buscarAutorCodigo(int codigo, Connection conexao) throws SQLException {
		Statement st = conexao.createStatement();
		String comando = "SELECT cod_autor, nome_autor from public.autor "
				+ "WHERE cod_autor = " + codigo;
		ResultSet resultado = st.executeQuery(comando);
		
		
		if(resultado != null) {
		while(resultado.next()) {
			
			return new Autor(resultado.getInt(1), resultado.getString(2));
			
		}
		}
			
		return null;
	}

	public ArrayList<Autor> buscarAutorNome(String nome, Connection conexao) throws SQLException {
		ArrayList<Autor> autor = new ArrayList<Autor>();
		
		Statement st = conexao.createStatement();
		String comando = "SELECT cod_autor, nome_autor from public.autor"
				+ 		" WHERE nome_autor LIKE '%" + nome + "%'";
		ResultSet resultado = st.executeQuery(comando);
		
		
		if(resultado != null) {
		while(resultado.next()) {
			Autor a = new Autor(resultado.getInt(1), resultado.getString(2));
			autor.add(a);
		}
		}

		return autor;
	}

	// Busca por Categoria
	public Categoria buscarCategoriaCodigo(int codigo, Connection conexao) throws SQLException {
		
		
		Statement st = conexao.createStatement();
		String comando = "SELECT cod_categoria, descricao_categoria from public.categoria "
				+ "WHERE cod_categoria = " + codigo;
		ResultSet resultado = st.executeQuery(comando);
		
		
		if(resultado != null) {
		while(resultado.next()) {
			
			return new Categoria(resultado.getInt(1), resultado.getString(2));
			
		}
		}
			
		return null;
		
		

		
	}

	public ArrayList<Categoria> buscarCategoriaNome(String nome, Connection conexao) throws SQLException {
ArrayList<Categoria> categoria = new ArrayList<Categoria>();
		
		Statement st = conexao.createStatement();
		String comando = "SELECT cod_categoria, descricao_categoria from public.categoria"
				+ 		" WHERE descricao_categoria LIKE '%" + nome +"%'";
		ResultSet resultado = st.executeQuery(comando);
		
		
		if(resultado != null) {
		while(resultado.next()) {
			Categoria c = new Categoria(resultado.getInt(1), resultado.getString(2));
			categoria.add(c);
		}
		}

		return categoria;
	}

	// Busca por Editora
	public Editora buscarEditoraCodigo(int codigo, Connection conexao) throws SQLException {
		Statement st = conexao.createStatement();
		String comando = "SELECT cod_editora, nome_editora, cnpj_editora from public.editora "
				+ "WHERE cod_editora = " + codigo;
		ResultSet resultado = st.executeQuery(comando);
		
		
		if(resultado != null) {
		while(resultado.next()) {
			
			return new Editora(resultado.getInt(1), resultado.getString(2), resultado.getString(3));
			
		}
		}
			
		return null;
	}

	public Editora buscarEditoraCNPJ(String cnpj, Connection conexao) throws SQLException {
		Statement st = conexao.createStatement();
		String comando = "SELECT cod_editora, nome_editora, cnpj_editora FROM public.editora\r\n "
				+ " WHERE cnpj_editora = '" + cnpj + "';";
		ResultSet resultado = st.executeQuery(comando);
		
		
		if(resultado != null) {
		while(resultado.next()) {
			
			return new Editora(resultado.getInt(1), resultado.getString(2), resultado.getString(3));
			
		}
		}
			
		return null;
	}

	public ArrayList<Editora> buscarEditoraNome(String nome, Connection conexao) throws SQLException {
		ArrayList<Editora> editora = new ArrayList<Editora>();
		
		Statement st = conexao.createStatement();
		String comando = "SELECT cod_editora, nome_editora, cnpj_editora from public.editora"
				+ 		" WHERE nome_editora LIKE '%" + nome + "%'";
		ResultSet resultado = st.executeQuery(comando);
		
		
		if(resultado != null) {
		while(resultado.next()) {
			Editora e = new Editora(resultado.getInt(1), resultado.getString(2), resultado.getString(3));
			editora.add(e);
		}
		}
		return editora;
	}

}



