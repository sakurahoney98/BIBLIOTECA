package br.com.ucsal.db.biblioteca.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.ucsal.db.biblioteca.connection.Conexao;
import br.com.ucsal.db.biblioteca.methods.Mensagem;
import br.com.ucsal.db.biblioteca.view.Janela;

public class Excluir {
	Buscar buscar = new Buscar();
	Mensagem mensagem = new Mensagem();
	Listar listar = new Listar();
	
	

	
	/*
	 * 
	 * Excluir Livro
	 * 
	 * */
	public void excluirLivro(int codigo, Connection conexao) throws SQLException {
	if (!buscar.buscarLivroCodigo(codigo, conexao).isEmpty()) {
		Statement st = conexao.createStatement();
		String comando = "DELETE FROM public.livro_autor\r\n"
				+ "	WHERE cod_livro = " + codigo;
		st.executeUpdate(comando);
		comando = "DELETE FROM public.livro\r\n"
				+ "	WHERE cod_livro = " + codigo;
		st.executeUpdate(comando);
		mensagem.updateMensagem("excluido");
		
		
	}else {
		System.out.println("\n\nO código informado não existe no banco de dados.\n\n");
	}
	}
	
	
	/*
	 * 
	 * Excluir Autor
	 * 
	 * */
	public void excluirAutor(int codigo, Connection conexao) throws SQLException {
		if(buscar.buscarAutorCodigo(codigo, conexao)!= null) {
			if(buscar.buscarLivroAutor(codigo, conexao).isEmpty()) {
				Statement st = conexao.createStatement();
				String comando = "DELETE FROM public.livro_autor\r\n"
						+ "	WHERE cod_autor = " + codigo;
				st.executeUpdate(comando);
				comando = "DELETE FROM public.autor\r\n"
						+ "	WHERE cod_autor = " + codigo;
				st.executeUpdate(comando);
				
				mensagem.updateMensagem("excluído");
			}else {
				mensagem.updateErroMensagem("exclusão");
				listar.listarLivro(buscar.buscarLivroAutor(codigo, conexao));
			}
			
			
			
		}else {
			System.out.println("\n\nO código informado não existe no banco de dados.\n\n");
		}
		
		
	}
	
	
	
	/*
	 * 
	 * Excluir Categoria
	 * 
	 * */
	public void excluirCategoria (int codigo, Connection conexao) throws SQLException {
		if(buscar.buscarCategoriaCodigo(codigo, conexao) != null) {
			if(buscar.buscarLivroCategoria(codigo, conexao).isEmpty()) {
				Statement st = conexao.createStatement();
				String comando = "DELETE FROM public.categoria\r\n"
						+ "	WHERE cod_categoria = " + codigo;
				st.executeUpdate(comando);
								
				mensagem.updateMensagem("excluído");
			}else {
				mensagem.updateErroMensagem("exclusão");
				listar.listarLivro(buscar.buscarLivroCategoria(codigo, conexao));
			}
			
		}else {
			System.out.println("\n\nO código informado não existe no banco de dados.\n\n");
		}
		
		
	}
	
	
	
	/*
	 * 
	 * Excluir Editora
	 * 
	 * */
	public void excluirEditora(int codigo, Connection conexao) throws SQLException {
		if(buscar.buscarEditoraCodigo(codigo, conexao) != null) {
			if(buscar.buscarLivroEditora(codigo, conexao).isEmpty()) {
				Statement st = conexao.createStatement();
				String comando = "DELETE FROM public.editora\r\n"
						+ "	WHERE cod_editora = " + codigo;
				st.executeUpdate(comando);
								
				mensagem.updateMensagem("excluído");
			}else {
				mensagem.updateErroMensagem("exclusão");
				listar.listarLivro(buscar.buscarLivroEditora(codigo, conexao));
			}
			
		}else {
			System.out.println("\n\nO código informado não existe no banco de dados.\n\n");
		}
		
		
	}
	
	
	
	/*
	 * 
	 * Excluir Tudo
	 * 
	 * */
	
	
	public void excluirTudo(Connection conexao) throws SQLException {
		Statement st = conexao.createStatement();
		String comando = "DELETE FROM public.livro_autor\r\n";
		st.executeUpdate(comando);
		
		comando = "DELETE FROM public.livro\r\n";
		st.executeUpdate(comando);
		
		comando = "DELETE FROM public.autor\r\n";
		st.executeUpdate(comando);
		
		comando = "DELETE FROM public.categoria\r\n";
		st.executeUpdate(comando);
		
		comando = "DELETE FROM public.editora\r\n";
		st.executeUpdate(comando);
		
		
		System.out.println("\n\nTodos os registros foram excluídos do banco de dados.\n\n");
	}

}
