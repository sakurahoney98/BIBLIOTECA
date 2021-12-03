package br.com.ucsal.db.biblioteca.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.ucsal.db.biblioteca.methods.Mensagem;
import br.com.ucsal.db.biblioteca.model.Autor;
import br.com.ucsal.db.biblioteca.model.Categoria;
import br.com.ucsal.db.biblioteca.model.Editora;
import br.com.ucsal.db.biblioteca.model.Livro;

public class Listar {
	

	

	Mensagem mensagem = new Mensagem();

	/*
	 * 
	 * Listar Livro
	 * 
	 */

	
	public void listarLivro(ArrayList<Livro> livro) {
		
		int indiceRef = 0;
		String autor = "";
		int cont = 0;
		if (!livro.isEmpty()) {
			mensagem.retornoPesquisaMensagem();
			
			for(int i  = 0; i < livro.size();) {
				autor = livro.get(i).getAutor();
				indiceRef = i;
				
				while(cont < livro.size() - 1 && livro.get(cont).getCodigo() == livro.get(cont + 1).getCodigo()) {
					autor += " / " + livro.get(cont + 1).getAutor();
					cont++;
				}
				
				
				
				System.out.println("Código: " + livro.get(indiceRef).getCodigo());
				System.out.println("ISBN: " + livro.get(indiceRef).getISBN());
				System.out.println("Título: " + livro.get(indiceRef).getNome());
				System.out.println("Autor: " + autor);
				System.out.println("Ano: " + livro.get(indiceRef).getAno());
				System.out.println("Editora: " + livro.get(indiceRef).getEditora());
				System.out.println("Categoria: " + livro.get(indiceRef).getCategoria());
				System.out.println("-----------------------------------------------------------------");
				i++;
				cont++;
				
				if (cont > i) {
					i = cont;
				}
			}
			
		} else {
			mensagem.nenhumResultadoMensagem();
		}

	}
	
	public void listarLivroFormat(ArrayList<Livro> livro) {
		if (!livro.isEmpty()) {
			String autor = "";
			mensagem.retornoPesquisaMensagem();
			for(int i = 0; i < livro.size(); i++) {
				autor += livro.get(i).getAutor();
				
				if (i < (livro.size() - 1)) {
					autor += " / ";
				}
				
				
			}
			
			System.out.println("Código: " + livro.get(0).getCodigo());
			System.out.println("ISBN: " + livro.get(0).getISBN());
			System.out.println("Título: " + livro.get(0).getNome());
			System.out.println("Autor: " + autor);
			System.out.println("Ano: " + livro.get(0).getAno());
			System.out.println("Editora: " + livro.get(0).getEditora());
			System.out.println("Categoria: " + livro.get(0).getCategoria());
			

		} else {
			mensagem.nenhumResultadoMensagem();
		}

	}

	public void listarLivro(Connection conexao) throws SQLException {
		Statement st = conexao.createStatement();
		String comando = "SELECT l.cod_livro, isbn, titulo, nome_autor, ano, nome_editora, descricao_categoria\r\n"
				+ "	FROM public.livro_autor la  inner join public.livro l ON l.cod_livro = la.cod_livro\r\n"
				+ "								inner join public.autor ON autor.cod_autor = la.cod_autor\r\n"
				+ "					inner join public.categoria ON categoria.cod_categoria = l.cod_categoria\r\n"
				+ "						inner join public.editora ON editora.cod_editora = l.cod_editora\r\n";
				
		ResultSet resultado = st.executeQuery(comando);
		ArrayList<Livro> livro = new ArrayList<Livro>();
		
		while (resultado.next()) {
			Livro l = new Livro (resultado.getInt(1), resultado.getInt(2), resultado.getString(3), resultado.getInt(5), resultado.getString(6), resultado.getString(7), resultado.getString(4));
			livro.add(l);

		}
		int indiceRef = 0;
		String autor = "";
		int cont = 0;
		if (!livro.isEmpty()) {
			mensagem.retornoPesquisaMensagem();
			
			for(int i  = 0; i < livro.size();) {
				autor = livro.get(i).getAutor();
				indiceRef = i;
				
				while(cont < livro.size() - 1 && livro.get(cont).getCodigo() == livro.get(cont + 1).getCodigo()) {
					autor += " / " + livro.get(cont + 1).getAutor();
					cont++;
				}
				
				
				
				System.out.println("Código: " + livro.get(indiceRef).getCodigo());
				System.out.println("ISBN: " + livro.get(indiceRef).getISBN());
				System.out.println("Título: " + livro.get(indiceRef).getNome());
				System.out.println("Autor: " + autor);
				System.out.println("Ano: " + livro.get(indiceRef).getAno());
				System.out.println("Editora: " + livro.get(indiceRef).getEditora());
				System.out.println("Categoria: " + livro.get(indiceRef).getCategoria());
				System.out.println("-----------------------------------------------------------------");
				i++;
				cont++;
				
				if (cont > i) {
					i = cont;
				}
			}
			
		} else {
			mensagem.nenhumResultadoMensagem();
		}
	}
	/*
	 * 
	 * Listar Autor
	 * 
	 */

	public void listarAutor(Autor autor) {
		if (!(autor == null)) {
			mensagem.retornoPesquisaMensagem();
			

			System.out.println("Código: " + autor.getCodigo());
			System.out.println("Nome: " + autor.getNome());
			System.out.println("-----------------------------------------------------------------");

		} else {
			mensagem.nenhumResultadoMensagem();
		}

	}

	public void listarAutor(ArrayList<Autor> autor) {
		if (!autor.isEmpty()) {
			mensagem.retornoPesquisaMensagem();
			for(Autor a : autor) {
				System.out.println("Código: " + a.getCodigo());
				System.out.println("Nome: " + a.getNome());
				System.out.println("-----------------------------------------------------------------");
			}

		} else {
			mensagem.nenhumResultadoMensagem();
		}

	}

	public void listarAutor(Connection conexao) throws SQLException {

		Statement st = conexao.createStatement();
		String comando = "SELECT cod_autor, nome_autor FROM public.AUTOR";
		ResultSet resultado = st.executeQuery(comando);
		mensagem.retornoPesquisaMensagem();
		while (resultado.next()) {
			System.out.println("Código: " + resultado.getInt(1) + "\nNome: " + resultado.getString(2) + "\n");
			System.out.println("-----------------------------------------------------------------");
		}
	

	}

	/*
	 * 
	 * Listar Categoria
	 * 
	 */

	public void listarCategoria(Categoria categoria) {
		if (!(categoria == null)) {
			mensagem.retornoPesquisaMensagem();

			System.out.println("Código: " + categoria.getCodigo());
			System.out.println("Nome: " + categoria.getDescricao());
			System.out.println("-----------------------------------------------------------------");

		} else {
			mensagem.nenhumResultadoMensagem();
		}
		

	}

	public void listarCategoria(ArrayList<Categoria> categoria) {
		if (!categoria.isEmpty()) {
			mensagem.retornoPesquisaMensagem();
			for(Categoria a : categoria) {
				System.out.println("Código: " + a.getCodigo());
				System.out.println("Nome: " + a.getDescricao());
				System.out.println("-----------------------------------------------------------------");
			}

		} else {
			mensagem.nenhumResultadoMensagem();
		}
		

	}

	public void listarCategoria(Connection conexao) throws SQLException {
		Statement st = conexao.createStatement();
		String comando = "SELECT cod_categoria, descricao_categoria FROM public.categoria";
		ResultSet resultado = st.executeQuery(comando);
		mensagem.retornoPesquisaMensagem();
		while (resultado.next()) {
			System.out.println("Código: " + resultado.getInt(1) + "\nDescrição: " + resultado.getString(2) + "\n");
			System.out.println("-----------------------------------------------------------------");
		}
		
	}

	/*
	 * 
	 * Listar Editora
	 * 
	 */

	public void listarEditora(Editora editora) {
		if (!(editora == null)) {
			
			mensagem.retornoPesquisaMensagem();

			System.out.println("Código: " + editora.getCodigo());
			System.out.println("Nome: " + editora.getNome());
			System.out.println("CNPJ: "+ editora.getCNPJ());
			System.out.println("-----------------------------------------------------------------");


		} else {
			mensagem.nenhumResultadoMensagem();
		}
	

	}

	public void listarEditora(ArrayList<Editora> editora) {
		if (!editora.isEmpty()) {
			mensagem.retornoPesquisaMensagem();
			
			for(Editora e : editora) {
				System.out.println("Código: " + e.getCodigo());
				System.out.println("Nome: " + e.getNome());
				System.out.println("CNPJ: "+ e.getCNPJ());
				System.out.println("-----------------------------------------------------------------");

			}

		} else {
			mensagem.nenhumResultadoMensagem();
		}
		

	}

	public void listarEditora(Connection conexao) throws SQLException {
		Statement st = conexao.createStatement();
		String comando = "SELECT cod_editora, nome_editora, cnpj_editora FROM public.editora";
		ResultSet resultado = st.executeQuery(comando);
		
		mensagem.retornoPesquisaMensagem();
		
		while (resultado.next()) {
			System.out.println("Código: " + resultado.getInt(1) + "\nNome: " + resultado.getString(2) + "\nCNPJ: " + resultado.getString(3) + "\n");

		}
st.close();
resultado.close();
	
	}
	
	
	
}
