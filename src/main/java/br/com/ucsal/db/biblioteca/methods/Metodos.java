package br.com.ucsal.db.biblioteca.methods;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import br.com.ucsal.db.biblioteca.controller.Adicionar;
import br.com.ucsal.db.biblioteca.controller.AdicionarRandom;
import br.com.ucsal.db.biblioteca.controller.Buscar;
import br.com.ucsal.db.biblioteca.controller.Editar;
import br.com.ucsal.db.biblioteca.controller.Excluir;
import br.com.ucsal.db.biblioteca.controller.Listar;

public class Metodos {
	final String teclaSair = "S";
	final String teclaRepetir = "C";
	final String teclaSim = "S";
	final String teclaNao = "N";
	
	Scanner scanner = new Scanner(System.in);
	Scanner texto = new Scanner(System.in);
	
	Mensagem mensagem = new Mensagem();
	Adicionar adicionar = new Adicionar();
	Editar editar = new Editar();
	Buscar buscar = new Buscar();
	Listar listar = new Listar();
	Excluir excluir = new Excluir();
	AdicionarRandom adicionarRandom = new AdicionarRandom();
	
	private int opcao;
	private String opSair;
	private int valorInt;
	private String valorString;
	private boolean proximo;
	private int op2;
	
	
	/*
	 * 
	 * Adicionar
	 * 
	 * */
	public void adicionar(Connection conexao) throws SQLException {
		
		do {


		mensagem.escolhaMensagem("adicionar");
		mensagem.escolherItemMensagem(1, "Livro");
		mensagem.escolherItemMensagem(2, "Autor");
		mensagem.escolherItemMensagem(3, "Categoria");
		mensagem.escolherItemMensagem(4, "Editora");
		mensagem.escolherItemMensagem(0, "Sair");
		System.out.print("\nOpção: ");
		this.opcao = scanner.nextInt();
		
		try {
			switch(opcao) {
			case 1:
				adicionar.adicionarLivro(conexao);
				break;
			
			case 2:
				adicionar.adicionarAutor(conexao);
				break;
				
			case 3:
				adicionar.adicionarCategoria(conexao);
				break;
				
			case 4:
				adicionar.adicionarEditora(conexao);
				break;
				
			case 0:
				
				break;
				
			default:
				mensagem.parametroInvalidoMensagem();
				break;
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(this.opcao == 0) {
			this.opSair = "S";
		}else {
			do {
			mensagem.continuarMenuMensagem("adicionando objetos");
			this.opSair = scanner.next();
			}while (!this.opSair.equalsIgnoreCase(teclaSair) && !this.opSair.equalsIgnoreCase(teclaRepetir));
		}
		
		
		
		}while(!this.opSair.equalsIgnoreCase(teclaSair));
			
	
	}
	
	
	
	/*
	 * 
	 * Editar
	 * 
	 * */
	public void editar(Connection conexao) throws SQLException {
		

do {
			mensagem.escolhaMensagem("editar");
			mensagem.escolherItemMensagem(1, "Livro");
			mensagem.escolherItemMensagem(2, "Autor");
			mensagem.escolherItemMensagem(3, "Categoria");
			mensagem.escolherItemMensagem(4, "Editora");
			mensagem.escolherItemMensagem(0, "Sair");
			System.out.print("\nOpção: ");
			this.opcao = scanner.nextInt();
			
			try {
				switch(opcao) {
				case 1:
					mensagem.informeAtributoMensagem("código");
					this.valorInt = scanner.nextInt();
					editar.editarLivro(this.valorInt, conexao);
					break;
				
				case 2:
					mensagem.informeAtributoMensagem("código");
					this.valorInt = scanner.nextInt();
					editar.editarAutor(this.valorInt, conexao);
					break;
					
				case 3:
					mensagem.informeAtributoMensagem("código");
					this.valorInt = scanner.nextInt();
					editar.editarCategoria(this.valorInt, conexao);
					break;
					
				case 4:
					mensagem.informeAtributoMensagem("código");
					this.valorInt = scanner.nextInt();
					editar.editarEditora(this.valorInt, conexao);
					break;
					
				case 0:
					
					break;
					
				default:
					mensagem.parametroInvalidoMensagem();
					break;
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(this.opcao == 0) {
				this.opSair = "S";
			}else {
				do {
				mensagem.continuarMenuMensagem("editando objetos");
				this.opSair = scanner.next();
				}while (!this.opSair.equalsIgnoreCase(teclaSair) && !this.opSair.equalsIgnoreCase(teclaRepetir));
			}
			
			}while(!this.opSair.equalsIgnoreCase(teclaSair));	
			
		
	}
	
	
	/*
	 * 
	 * Excluir
	 * 
	 * */
	public void exlcuir(Connection conexao) throws SQLException {
		
		do {
		mensagem.escolhaMensagem("excluir");
		mensagem.escolherItemMensagem(1, "Livro");
		mensagem.escolherItemMensagem(2, "Autor");
		mensagem.escolherItemMensagem(3, "Categoria");
		mensagem.escolherItemMensagem(4, "Editora");
		mensagem.escolherItemMensagem(5, "Tudo");
		mensagem.escolherItemMensagem(0, "Sair");
		
		System.out.print("\nOpção: ");
		this.opcao = scanner.nextInt();
		
		switch(opcao) {
		case 1:
			mensagem.informeAtributoMensagem("código");
			this.valorInt = scanner.nextInt();
			excluir.excluirLivro(this.valorInt, conexao);
			break;
		
		case 2:
			mensagem.informeAtributoMensagem("código");
			this.valorInt = scanner.nextInt();
			excluir.excluirAutor(this.valorInt, conexao);
			break;
			
		case 3:
			mensagem.informeAtributoMensagem("código");
			this.valorInt = scanner.nextInt();
			excluir.excluirCategoria(this.valorInt, conexao);
			break;
			
		case 4:
			mensagem.informeAtributoMensagem("código");
			this.valorInt = scanner.nextInt();
			excluir.excluirEditora(this.valorInt, conexao);
			break;
			
		case 5:
			do {
				System.out.println("\nTem certeza que desejar apagar os dados de todas as tabelas? [S] Sim [N] Não:");
				this.opSair = scanner.next();
			}while(!this.opSair.equalsIgnoreCase(teclaSim) && !this.opSair.equalsIgnoreCase(teclaNao));
			if(this.opSair.equalsIgnoreCase("S")) {
				excluir.excluirTudo(conexao);
				mensagem.updateMensagem("excluido");
			}else {
				System.out.println("\n\nOperação abortada pelo usuário.\n\n");
			}
			
			break;
			
		case 0:
			
			
			break;
			
		default:
			mensagem.parametroInvalidoMensagem();
			break;
		
		}
		if(this.opcao == 0) {
			this.opSair = "S";
		}else {
			do {
			mensagem.continuarMenuMensagem("excluindo objetos");
			this.opSair = scanner.next();
			}while (!this.opSair.equalsIgnoreCase(teclaSair) && !this.opSair.equalsIgnoreCase(teclaRepetir));
		}
		
		}while(!this.opSair.equalsIgnoreCase(teclaSair));
		
		
		
	
	}
	
	
	
	
	
	/*
	 * 
	 * Buscar
	 * 
	 * */
	public void buscar(Connection conexao) throws SQLException {
		
		do {
		mensagem.escolhaMensagem("Buscar");
		mensagem.escolherItemMensagem(1, "Livro");
		mensagem.escolherItemMensagem(2, "Autor");
		mensagem.escolherItemMensagem(3, "Categoria");
		mensagem.escolherItemMensagem(4, "Editora");
		mensagem.escolherItemMensagem(0, "Sair");
		System.out.print("\nOpção: ");
		this.opcao = scanner.nextInt();
		
		switch(opcao) {
		case 1:
			mensagem.atributoBuscaMensagem();
			mensagem.escolherItemMensagem(1, "Código");
			mensagem.escolherItemMensagem(2, "Nome");
			mensagem.escolherItemMensagem(3, "Autor");
			mensagem.escolherItemMensagem(4, "ISBN");
			mensagem.escolherItemMensagem(5, "Categoria");
			mensagem.escolherItemMensagem(6, "Editora");
			mensagem.escolherItemMensagem(7, "Ano");
			mensagem.escolherItemMensagem(0, "Sair");
			System.out.print("\nOpção: ");
			this.op2 = scanner.nextInt();
			
			switch(this.op2) {
			case 1:
				mensagem.informeAtributoMensagem("código");
				this.valorInt = scanner.nextInt();
				if(!buscar.buscarLivroCodigo(this.valorInt, conexao).isEmpty()) {
					listar.listarLivroFormat(buscar.buscarLivroCodigo(this.valorInt, conexao));
				}else {
					mensagem.nenhumResultadoMensagem();
				}
				break;
				
			case 2:
				mensagem.informeAtributoMensagem("nome");
				this.valorString = texto.nextLine();
				if(buscar.buscarLivroNome(this.valorString, conexao) != null) {
					listar.listarLivro(buscar.buscarLivroNome(this.valorString, conexao));
				}else {
					mensagem.nenhumResultadoMensagem();
				}
				break;
			
		
			
		case 3:
			mensagem.informeAtributoMensagem("código do autor");
			this.valorInt = scanner.nextInt();
			if(!buscar.buscarLivroAutor(this.valorInt, conexao).isEmpty()) {
				listar.listarLivro(buscar.buscarLivroAutor(this.valorInt, conexao));
			}else {
				mensagem.nenhumResultadoMensagem();
			}
			break;
			
			
		case 4:
			mensagem.informeAtributoMensagem("ISBN");
			this.valorInt = scanner.nextInt();
			if(!buscar.buscarLivroISBN(this.valorInt, conexao).isEmpty()) {
				listar.listarLivroFormat(buscar.buscarLivroISBN(this.valorInt, conexao));
			}else {
				mensagem.nenhumResultadoMensagem();
			}
			break;
			
		case 5:
			mensagem.informeAtributoMensagem("código da categoria");
			this.valorInt = texto.nextInt();
			if(!buscar.buscarLivroCategoria(this.valorInt, conexao).isEmpty()) {
				listar.listarLivro(buscar.buscarLivroCategoria(this.valorInt, conexao));
			}else {
				mensagem.nenhumResultadoMensagem();
			}
			break;
			
		case 6:
			mensagem.informeAtributoMensagem("código da editora");
			this.valorInt = texto.nextInt();
			if(!buscar.buscarLivroEditora(this.valorInt, conexao).isEmpty()) {
				listar.listarLivro(buscar.buscarLivroEditora(this.valorInt, conexao));
			}else {
				mensagem.nenhumResultadoMensagem();
			}
			break;
			
		case 7:
			mensagem.informeAtributoMensagem("ano");
			this.valorInt = texto.nextInt();
			if(!buscar.buscarLivroAno(this.valorInt, conexao).isEmpty()) {
				listar.listarLivro(buscar.buscarLivroAno(this.valorInt, conexao));
			}else {
				mensagem.nenhumResultadoMensagem();
			}
			break;
			
		case 0:
			
			break;
			
		default:
			mensagem.parametroInvalidoMensagem();
			break;
			}
			break;
		case 2:
			mensagem.atributoBuscaMensagem();
			mensagem.escolherItemMensagem(1, "Código");
			mensagem.escolherItemMensagem(2, "Nome");
			mensagem.escolherItemMensagem(0, "Sair");
			System.out.print("Opçaõ: ");
			op2 = scanner.nextInt();
			switch (this.op2) {
			case 1:
				mensagem.informeAtributoMensagem("código");
				this.valorInt = scanner.nextInt();
				if(buscar.buscarAutorCodigo(this.valorInt, conexao) != null) {
					listar.listarAutor(buscar.buscarAutorCodigo(this.valorInt, conexao));
				}else {
					mensagem.nenhumResultadoMensagem();
				}
				break;
			case 2:
				mensagem.informeAtributoMensagem("nome");
				this.valorString = texto.nextLine();
				if(!buscar.buscarAutorNome(this.valorString, conexao).isEmpty()) {
					listar.listarAutor(buscar.buscarAutorNome(this.valorString, conexao));
				}else {
					mensagem.nenhumResultadoMensagem();
				}
				break;
				
			case 0:
				
				break;
				
			default:
				mensagem.parametroInvalidoMensagem();
				break;
			
			}
			break;
			
		case 3:
			mensagem.atributoBuscaMensagem();
			mensagem.escolherItemMensagem(1, "Código");
			mensagem.escolherItemMensagem(2, "Nome");
			mensagem.escolherItemMensagem(0, "Sair");
			System.out.print("Opçaõ: ");
			this.op2 = scanner.nextInt();
			switch (this.op2) {
			case 1:
				mensagem.informeAtributoMensagem("código");
				this.valorInt = scanner.nextInt();
				if(buscar.buscarCategoriaCodigo(this.valorInt, conexao) != null) {
					listar.listarCategoria(buscar.buscarCategoriaCodigo(this.valorInt, conexao));
				}else {
					mensagem.nenhumResultadoMensagem();
				}
				break;
			case 2:
				mensagem.informeAtributoMensagem("nome");
				this.valorString = texto.nextLine();
				if(!buscar.buscarCategoriaNome(this.valorString, conexao).isEmpty()) {
					listar.listarCategoria(buscar.buscarCategoriaNome(this.valorString, conexao));
				}else {
					mensagem.nenhumResultadoMensagem();
				}
				break;
				
			case 0:
				
				break;
				
			default:
				mensagem.parametroInvalidoMensagem();
				break;
			
			}
			break;
			
		case 4:
			mensagem.atributoBuscaMensagem();
			mensagem.escolherItemMensagem(1, "Código");
			mensagem.escolherItemMensagem(2, "Nome");
			mensagem.escolherItemMensagem(3, "CNPJ");
			mensagem.escolherItemMensagem(0, "Sair");
			System.out.print("Opçaõ: ");
			this.op2 = scanner.nextInt();
			switch (this.op2) {
			case 1:
				mensagem.informeAtributoMensagem("código");
				this.valorInt = scanner.nextInt();
				if(buscar.buscarEditoraCodigo(this.valorInt, conexao) != null) {
					listar.listarEditora(buscar.buscarEditoraCodigo(this.valorInt, conexao));
				}else {
					mensagem.nenhumResultadoMensagem();
				}
				break;
			case 2:
				mensagem.informeAtributoMensagem("nome");
				this.valorString = texto.nextLine();
				if(!buscar.buscarEditoraNome(this.valorString, conexao).isEmpty()) {
					listar.listarEditora(buscar.buscarEditoraNome(this.valorString, conexao));
				}else {
					mensagem.nenhumResultadoMensagem();
				}
				break;
				
			case 3:
				mensagem.informeAtributoMensagem("CNPJ");
				this.valorString = texto.nextLine();
				if(buscar.buscarEditoraCNPJ(this.valorString, conexao) != null) {
					listar.listarEditora(buscar.buscarEditoraCNPJ(this.valorString, conexao));
				}else {
					mensagem.nenhumResultadoMensagem();
				}
				break;
				
			case 0:
				
				break;
				
			default:
				mensagem.parametroInvalidoMensagem();
				break;
			
			}
			break;
			
		case 0:
			
			break;
			
		default:
			mensagem.parametroInvalidoMensagem();
			break;
		
		}
		
		if(this.opcao == 0) {
			this.opSair = "S";
		}else {
			do {
			mensagem.continuarMenuMensagem("pesquisando objetos");
			this.opSair = scanner.next();
			}while (!this.opSair.equalsIgnoreCase(teclaSair) && !this.opSair.equalsIgnoreCase(teclaRepetir));
		}
		
		}while(!this.opSair.equalsIgnoreCase(teclaSair));
		
	
	}
	
	
	/*
	 * 
	 * Listar
	 * 
	 * */
	
	public void listar(Connection conexao) throws SQLException {
		
		do {
		mensagem.escolhaMensagem("listar");
		mensagem.escolherItemMensagem(1, "Livro");
		mensagem.escolherItemMensagem(2, "Autor");
		mensagem.escolherItemMensagem(3, "Categoria");
		mensagem.escolherItemMensagem(4, "Editora");
		mensagem.escolherItemMensagem(0, "Sair");
		System.out.print("\nOpção: ");
		this.opcao = scanner.nextInt();
		
		switch(this.opcao) {
		case 1:
			listar.listarLivro(conexao);
			break;
			
		case 2:
			listar.listarAutor(conexao);
			break;
			
		case 3:
			listar.listarCategoria(conexao);
			break;
			
		case 4:
			listar.listarEditora(conexao);
			break;
			
		case 0:
			
			break;
		
		default:
			mensagem.parametroInvalidoMensagem();
			break;
			
		}
		if(this.opcao == 0) {
			this.opSair = "S";
		}else {
			do {
			mensagem.continuarMenuMensagem("listando objetos");
			this.opSair = scanner.next();
			}while (!this.opSair.equalsIgnoreCase(teclaSair) && !this.opSair.equalsIgnoreCase(teclaRepetir));
		}
		
		}while(!this.opSair.equalsIgnoreCase(teclaSair));
	}
	
	
	
public void adicionarRandom(Connection conexao) throws SQLException {
		
		do {


		mensagem.escolhaMensagem("adicionar aletoriamente");
		mensagem.escolherItemMensagem(1, "Livro");
		mensagem.escolherItemMensagem(2, "Autor");
		mensagem.escolherItemMensagem(3, "Categoria");
		mensagem.escolherItemMensagem(4, "Editora");
		mensagem.escolherItemMensagem(0, "Sair");
		System.out.print("\nOpção: ");
		this.opcao = scanner.nextInt();
		
		try {
			switch(opcao) {
			case 1:
				adicionarRandom.adicionarLivro(conexao);
				break;
			
			case 2:
				adicionarRandom.adicionarAutor(conexao);
				break;
				
			case 3:
				adicionarRandom.adicionarCategoria(conexao);
				break;
				
			case 4:
				adicionarRandom.adicionarEditora(conexao);
				break;
				
			case 0:
				
				break;
				
			default:
				mensagem.parametroInvalidoMensagem();
				break;
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(this.opcao == 0) {
			this.opSair = "S";
		}else {
			do {
			mensagem.continuarMenuMensagem("adicionando objetos aleatoriaamente");
			this.opSair = scanner.next();
			}while (!this.opSair.equalsIgnoreCase(teclaSair) && !this.opSair.equalsIgnoreCase(teclaRepetir));
		}
		
		
		
		}while(!this.opSair.equalsIgnoreCase(teclaSair));
			
	
	}

/*
 * 
 * Excluir Tudo
 * 
 * */

public void excluirTudo(Connection conexao) throws SQLException {
	
}

}
