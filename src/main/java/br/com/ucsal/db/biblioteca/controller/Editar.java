package br.com.ucsal.db.biblioteca.controller;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.ucsal.db.biblioteca.connection.Conexao;
import br.com.ucsal.db.biblioteca.methods.Mensagem;
import br.com.ucsal.db.biblioteca.model.Livro;

public class Editar {
	final String teclaContinuar = "C";
	final String teclaSair = "S";
	final String teclaRepetir = "R";
	final String teclaCadastrar = "C";
	final String teclaListar = "L";

	
	private int opcao;
	private String novaPalavra;
	private int novoInt;
	private String opSair;
	
	Scanner texto = new Scanner(System.in);
	Scanner scanner = new Scanner(System.in);
	
	Mensagem mensagem = new Mensagem();
	Buscar buscar = new Buscar();
	Listar listar = new Listar();
	Adicionar adicionar = new Adicionar();
	Validar validar = new Validar();
	
	
	
	
	

	
	
	/*
	 * 
	 * Editar Livro
	 * 
	 * */
	public void editarLivro(int codigo, Connection conexao) throws SQLException {
		if(!buscar.buscarLivroCodigo(codigo, conexao).isEmpty()) {
do {
		this.opSair = "";
				mensagem.escolhaMensagem("editar");
				mensagem.escolherItemMensagem(1, "Código");
				mensagem.escolherItemMensagem(2, "Nome");
				mensagem.escolherItemMensagem(3, "ISBN");
				mensagem.escolherItemMensagem(4, "Autor");
				mensagem.escolherItemMensagem(5, "Categoria");
				mensagem.escolherItemMensagem(6, "Editora");
				mensagem.escolherItemMensagem(7, "Ano");
				mensagem.escolherItemMensagem(0, "Sair");
				System.out.print("\nOpção: ");
				this.opcao = scanner.nextInt();
				
				switch(opcao) {
				case 1:
					mensagem.editarAtributoMensagem("código");
					this.novoInt = scanner.nextInt();
					if (buscar.buscarLivroCodigo(novoInt, conexao).isEmpty()) {
						ArrayList<Integer> backup = new ArrayList<Integer>();
						
						Statement st = conexao.createStatement();
						String comando = "SELECT cod_autor FROM public.livro_autor "
								+		" WHERE cod_livro = " + codigo;
						ResultSet autor = st.executeQuery(comando);
						
						while(autor.next()) {
							backup.add(autor.getInt(1));
						}
						
						comando = "DELETE FROM public.livro_autor "
								+ " WHERE cod_livro = " + codigo;
						st.executeUpdate(comando);
						
						comando = "UPDATE public.livro\r\n"
								+ "	SET cod_livro = "+ novoInt +"\r\n"
								+ "	WHERE cod_livro = " + codigo +";" ;
						st.executeUpdate(comando);
						
						for(Integer i : backup) {
							
							comando = "INSERT INTO public.livro_autor(\r\n"
									+ "	cod_livro, cod_autor)\r\n"
									+ "	VALUES (" + novoInt + ", " + i + " );";
							st.executeUpdate(comando);
							
						}
						mensagem.updateMensagem("editado");
					}else {
						mensagem.codigoExisteMensagem();
						do {
						mensagem.sairMensagem();
						this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));
					}
					break;
					
				case 2:
					mensagem.editarAtributoMensagem("nome");
					this.novaPalavra = texto.nextLine();
					
					if(validar.validarNome(novaPalavra)) {
						Statement st = conexao.createStatement();
						String comando = "UPDATE public.livro\r\n"
								+ "	SET titulo = '"+ novaPalavra +"'\r\n"
								+ "	WHERE cod_livro = " + codigo +";" ;
						st.executeUpdate(comando);
						
						mensagem.updateMensagem("editado");
					}else {
						do {
						mensagem.sairMensagem();
						this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));
					}
					
					break;
					
				case 3:
					mensagem.editarAtributoMensagem("ISBN");
					this.novoInt = scanner.nextInt();
					if (buscar.buscarLivroISBN(novoInt, conexao).isEmpty()) {
						Statement st = conexao.createStatement();
						String comando = "UPDATE public.livro\r\n"
								+ "	SET isbn = "+ novoInt +"\r\n"
								+ "	WHERE cod_livro = " + codigo +";" ;
						st.executeUpdate(comando);
						
						mensagem.updateMensagem("editado");
					}else {
						mensagem.codigoExisteMensagem();
						do {
						mensagem.sairMensagem();
						this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));
					}
					break;
					
				case 4:

					mensagem.escolhaMensagem("editar");
					mensagem.escolherItemMensagem(1, "Adicionar autor ao livro");
					mensagem.escolherItemMensagem(2, "Remover autor do livro");
					mensagem.escolherItemMensagem(0, "Sair");
					System.out.print("\nOpção: ");
					int op = scanner.nextInt();
					
					switch(op) {
					case 1:
						String opString = "";
						System.out.print("\nInforme o código do autor: ");
						this.novoInt = scanner.nextInt();
						
						if(buscar.buscarLivroAutor(novoInt, conexao) != null) {
							if(!buscar.buscarLivroAutor(novoInt, codigo, conexao)) {
								Statement st = conexao.createStatement();
								String comando = "INSERT INTO public.livro_autor(\r\n"
										+ "	cod_livro, cod_autor)\r\n"
										+ "	VALUES (" + codigo + ", " + novoInt + ");";
								st.executeUpdate(comando);
								
								mensagem.updateMensagem("adicionado");
							}else {
								System.out.println("\n\nO autor já está vinculado ao livro.\n\n");
							}
							
						}else {
							do {
							mensagem.codigoInexistenteMensagem("autor");
							opString = scanner.next();
							
							if(opString.equalsIgnoreCase("C")) {
								adicionar.adicionarAutor(conexao);
							}else if(opString.equalsIgnoreCase("L")) {
								listar.listarAutor(conexao);
							}
						} while (!this.opSair.equalsIgnoreCase(teclaCadastrar)
								&& !this.opSair.equalsIgnoreCase(teclaListar)
								&& !this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));
						}
						break;
						
					case 2:
						if(buscar.buscarLivroCodigo(codigo, conexao).size() > 1) {
							System.out.print("\nInforme o código do autor: ");
							this.novoInt = scanner.nextInt();
							if(buscar.buscarLivroAutor(novoInt, codigo, conexao)) {
								Statement st = conexao.createStatement();
								String comando = "DELETE FROM public.livro_autor\r\n"
										+ "	WHERE cod_autor = " + novoInt + " AND cod_livro = " + codigo + ";";
								st.executeUpdate(comando);
								mensagem.updateMensagem("excluído");
								
							}else {
								System.out.println("\n\nO código informado não pertence a nenhum dos autores do livro.\n\n");
							}
							
							
							
						}else {
							System.out.println("\n\nA exclusão de autor só está disponível para livros com mais de um autor.\n\n");
						}
						break;
						
					case 0:
					
						break;
						
					default:
						mensagem.parametroInvalidoMensagem();
						break;
					}
					break;
	
					
				case 5:
					mensagem.editarAtributoMensagem("categoria");
					this.novoInt = scanner.nextInt();
					if (buscar.buscarCategoriaCodigo(novoInt, conexao) != null) {
						Statement st = conexao.createStatement();
						String comando = "UPDATE public.livro\r\n"
								+ "	SET cod_categoria = "+ novoInt +"\r\n"
								+ "	WHERE cod_livro = " + codigo +";" ;
						st.executeUpdate(comando);
						
						mensagem.updateMensagem("editado");
						
					}else {
						do {
						mensagem.codigoInexistenteMensagem("categoria");
						this.opSair = scanner.next();
						if(this.opSair.equalsIgnoreCase("L")) {
							listar.listarCategoria(conexao);
						}else if (this.opSair.equalsIgnoreCase("C")) {
							adicionar.adicionarCategoria(conexao);
						}
					} while (!this.opSair.equalsIgnoreCase(teclaCadastrar)
							&& !this.opSair.equalsIgnoreCase(teclaListar)
							&& !this.opSair.equalsIgnoreCase(teclaSair)
							&& !this.opSair.equalsIgnoreCase(teclaRepetir));
					}
					break;
					
				case 6:
					mensagem.editarAtributoMensagem("editora");
					this.novoInt = scanner.nextInt();
					if (buscar.buscarEditoraCodigo(novoInt, conexao) !=  null) {
						Statement st = conexao.createStatement();
						String comando = "UPDATE public.livro\r\n"
								+ "	SET cod_editora = "+ novoInt +"\r\n"
								+ "	WHERE cod_livro = " + codigo +";" ;
						st.executeUpdate(comando);
						mensagem.updateMensagem("editado");
					}else {
						do {
						mensagem.codigoInexistenteMensagem("editora");
						
						this.opSair = scanner.next();
						if(this.opSair.equalsIgnoreCase("L")) {
							listar.listarEditora(conexao);
						}else if (this.opSair.equalsIgnoreCase("C")) {
							adicionar.adicionarEditora(conexao);
						}
					} while (!this.opSair.equalsIgnoreCase(teclaCadastrar)
							&& !this.opSair.equalsIgnoreCase(teclaListar)
							&& !this.opSair.equalsIgnoreCase(teclaSair)
							&& !this.opSair.equalsIgnoreCase(teclaRepetir));
					}
					break;
					
				case 7:
					mensagem.editarAtributoMensagem("ano");
					this.novoInt = scanner.nextInt();
					if (validar.validarAno(this.novoInt)) {
						Statement st = conexao.createStatement();
						String comando = "UPDATE public.livro\r\n"
								+ "	SET ano = "+ novoInt +"\r\n"
								+ "	WHERE cod_livro = " + codigo +";" ;
						st.executeUpdate(comando);
						mensagem.updateMensagem("editado");
					}
					break;
					
				case 0:
				
					break;
					
				default:
					mensagem.parametroInvalidoMensagem();
					break;
					
				}
				
				if(!this.opSair.equalsIgnoreCase("S") && !this.opSair.equalsIgnoreCase("R")) {
					do {
						mensagem.continuarMensagem("editando livro");
						this.opSair = scanner.next();
					} while (!this.opSair.equalsIgnoreCase(teclaSair)
							&& !this.opSair.equalsIgnoreCase(teclaContinuar));
				}
				
				
} while (!this.opSair.equalsIgnoreCase(teclaSair));
				
		
		}else {
			mensagem.nenhumResultadoMensagem();
		}
		
	
		
		
	}
	
	
	
	/*
	 * 
	 * Editar Autor
	 * 
	 * */
public void editarAutor(int codigo, Connection conexao) throws SQLException {
	if(buscar.buscarAutorCodigo(codigo, conexao) != null) {
		do {
	
		this.opSair = "";
			mensagem.escolhaMensagem("editar");
			mensagem.escolherItemMensagem(1, "Código");
			mensagem.escolherItemMensagem(2, "Nome");
			mensagem.escolherItemMensagem(0, "Sair");
			System.out.print("\nOpção: ");
			this.opcao = scanner.nextInt();
			
			
			switch(this.opcao) {
			case 1:
				mensagem.editarAtributoMensagem("código");
				this.novoInt = scanner.nextInt();
				
				if(buscar.buscarAutorCodigo(novoInt, conexao) == null) {
					
					if(buscar.buscarLivroAutor(codigo, conexao).isEmpty()) {
						Statement st = conexao.createStatement();
						String comando = "UPDATE public.autor\r\n"
								+" SET cod_autor = " + novoInt+"\r\n "
								+" WHERE cod_autor = " + codigo;
						st.executeUpdate(comando);
					}else {
					ArrayList<Livro> arrayLivro = buscar.buscarLivroAutor(codigo, conexao);
					
					
					Statement st = conexao.createStatement();
					String comando = "DELETE FROM public.livro_autor\r\n"
							+ "	WHERE cod_autor = " + codigo +";" ;
					st.executeUpdate(comando);
					
					
					
						comando = "UPDATE public.autor\r\n"
								+" SET cod_autor = " + novoInt +"\r\n "
								+" WHERE cod_autor = " + codigo;
						st.executeUpdate(comando);
					
					
					for(Livro l : arrayLivro) {
						comando = "INSERT INTO public.livro_autor(\r\n"
								+ "	cod_autor, cod_livro)\r\n"
								+ "	VALUES ("+ novoInt +", "+ l.getCodigo() +")";
						st.executeUpdate(comando);
					}
					
				}
					
					mensagem.updateMensagem("editado");
					
				}else {
					mensagem.codigoExisteMensagem();
					do {
					mensagem.sairMensagem();
					this.opSair = scanner.next();
					} while (!this.opSair.equalsIgnoreCase(teclaSair)
							&& !this.opSair.equalsIgnoreCase(teclaRepetir));
					}
				
				break;
				
			case 2:
				mensagem.editarAtributoMensagem("nome");
				this.novaPalavra = texto.nextLine();
				
				if(validar.validarNome(novaPalavra)) {
					Statement st = conexao.createStatement();
					String comando = "UPDATE public.autor\r\n"
							+ "	SET nome_autor = '"+ novaPalavra +"'\r\n"
							+ "	WHERE cod_autor = " + codigo +";" ;
					st.executeUpdate(comando);
					mensagem.updateMensagem("editado");
				}else {
					do {
					mensagem.sairMensagem();
					this.opSair = scanner.nextLine();
					} while (!this.opSair.equalsIgnoreCase(teclaSair)
							&& !this.opSair.equalsIgnoreCase(teclaRepetir));
				}
				
				break;
				
			case 0:
				break;
				
			default:
				mensagem.parametroInvalidoMensagem();
				break;
				
			}
			
			if(!this.opSair.equalsIgnoreCase("S") && !this.opSair.equalsIgnoreCase("R")) {
				do {
					mensagem.continuarMensagem("editando autor");
					this.opSair = scanner.next();
				} while (!this.opSair.equalsIgnoreCase(teclaSair)
						&& !this.opSair.equalsIgnoreCase(teclaContinuar));
			}
			
			
} while (!this.opSair.equalsIgnoreCase(teclaSair));
			
	
	}else {
		mensagem.nenhumResultadoMensagem();
	}
}



/*
 * 
 * Editar Categoria
 * 
 * */
public void editarCategoria(int codigo, Connection conexao) throws SQLException {
if(buscar.buscarCategoriaCodigo(codigo, conexao) != null) {
do {
	this.opSair = "";
		mensagem.escolhaMensagem("editar");
		mensagem.escolherItemMensagem(1, "Código");
		mensagem.escolherItemMensagem(2, "Nome");
		mensagem.escolherItemMensagem(0, "Sair");
		System.out.println("\nOpção: ");
		this.opcao = scanner.nextInt();
		
		
		switch(this.opcao) {
		case 1:
			mensagem.editarAtributoMensagem("código");
			this.novoInt = scanner.nextInt();
			
			if(buscar.buscarCategoriaCodigo(this.novoInt, conexao) == null) {
				if(buscar.buscarLivroCategoria(codigo, conexao).isEmpty()) {
					Statement st = conexao.createStatement();
					String comando = "UPDATE public.categoria\r\n"
							+" SET cod_categoria = " + novoInt +"\r\n "
							+" WHERE cod_categoria = " + codigo;
					st.executeUpdate(comando);
				}else {
				ArrayList<Livro> arrayLivro = buscar.buscarLivroCategoria(codigo, conexao);
				
				
				Statement st = conexao.createStatement();
				
				
				String comando = "UPDATE public.livro\r\n"
						+" SET cod_categoria = null\r\n "
						+" WHERE cod_categoria = " + codigo;
				st.executeUpdate(comando);
				
					comando = "UPDATE public.categoria\r\n"
							+" SET cod_categoria = " + novoInt +"\r\n "
							+" WHERE cod_categoria = " + codigo;
					st.executeUpdate(comando);
				
				
				for(Livro l : arrayLivro) {
					comando = "UPDATE public.livro\r\n"
							+" SET cod_categoria = " + novoInt +"\r\n "
							+" WHERE cod_livro = " + l.getCodigo();
					st.executeUpdate(comando);
				}
				
			}
				
				mensagem.updateMensagem("editado");
				
			}else {
				mensagem.codigoExisteMensagem();
				do {
				mensagem.sairMensagem();
				this.opSair = scanner.next();
				} while (!this.opSair.equalsIgnoreCase(teclaSair)
						&& !this.opSair.equalsIgnoreCase(teclaRepetir));
			}
			
			break;
			
		case 2:
			mensagem.editarAtributoMensagem("nome");
			this.novaPalavra = texto.nextLine();
			
			if(validar.validarNome(novaPalavra)) {
				Statement st = conexao.createStatement();
				String comando = "UPDATE public.categoria\r\n"
						+ "	SET descricao_categoria= '"+ novaPalavra +"'\r\n"
						+ "	WHERE cod_categoria = " + codigo +";" ;
				st.executeUpdate(comando);
				mensagem.updateMensagem("editado");
			}else {
				do {
				mensagem.sairMensagem();
				this.opSair = scanner.next();
				} while (!this.opSair.equalsIgnoreCase(teclaSair)
						&& !this.opSair.equalsIgnoreCase(teclaRepetir));
			}
			
			break;
			
		case 0:
			
			break;
			
		default:
			mensagem.parametroInvalidoMensagem();
			break;
			
		}
		
		if(!this.opSair.equalsIgnoreCase("S") && !this.opSair.equalsIgnoreCase("R")) {
			do {
				mensagem.continuarMensagem("editando categoria");
				this.opSair = scanner.next();
			} while (!this.opSair.equalsIgnoreCase(teclaSair)
					&& !this.opSair.equalsIgnoreCase(teclaContinuar));
		}
		
		
} while (!this.opSair.equalsIgnoreCase(teclaSair));
		

}else {
	mensagem.nenhumResultadoMensagem();
}
}
	




/*
 * 
 * Editar Editora
 * 
 * */
public void editarEditora(int codigo, Connection conexao) throws SQLException {
if(buscar.buscarEditoraCodigo(codigo, conexao) != null) {
do {
	this.opSair = "";
		mensagem.escolhaMensagem("editar");
		mensagem.escolherItemMensagem(1, "Código");
		mensagem.escolherItemMensagem(2, "Nome");
		mensagem.escolherItemMensagem(3, "CNPJ");
		mensagem.escolherItemMensagem(0, "Sair");
		System.out.print("\nOpção: ");
		this.opcao = scanner.nextInt();
		
		
		switch(this.opcao) {
		case 1:
			mensagem.editarAtributoMensagem("código");
			this.novoInt = scanner.nextInt();
			
			if(buscar.buscarEditoraCodigo(novoInt, conexao) == null) {
				if(buscar.buscarLivroEditora(codigo, conexao).isEmpty()) {
					Statement st = conexao.createStatement();
					String comando = "UPDATE public.editora\r\n "
							+" SET cod_editora = " + novoInt +"\r\n "
							+" WHERE cod_editora = " + codigo;
					st.executeUpdate(comando);
				}else {
				ArrayList<Livro> arrayLivro = buscar.buscarLivroEditora(codigo, conexao);
				
				
				Statement st = conexao.createStatement();
				
				
				String comando = "UPDATE public.livro\r\n"
						+" SET cod_editora = null\r\n "
						+" WHERE cod_editora = " + codigo;
				st.executeUpdate(comando);
				
					comando = "UPDATE public.editora\r\n"
							+" SET cod_editora = " + novoInt +"\r\n "
							+" WHERE cod_editora = " + codigo;
					st.executeUpdate(comando);
				
				
				for(Livro l : arrayLivro) {
					comando = "UPDATE public.livro\r\n"
							+" SET cod_editora = " + novoInt +"\r\n "
							+" WHERE cod_livro = " + l.getCodigo();
					st.executeUpdate(comando);
				}
				
			}
				
				mensagem.updateMensagem("editado");
			}else {
				mensagem.codigoExisteMensagem();
				do {
				mensagem.sairMensagem();
				this.opSair = scanner.next();
				} while (!this.opSair.equalsIgnoreCase(teclaSair)
						&& !this.opSair.equalsIgnoreCase(teclaRepetir));
			}
			
			
			
			break;
			
		case 2:
			mensagem.editarAtributoMensagem("nome");
			this.novaPalavra = texto.nextLine();
			
			if(validar.validarNome(novaPalavra)) {
				Statement st = conexao.createStatement();
				String comando = "UPDATE public.editora\r\n"
						+ "	SET nome_editora = '"+ novaPalavra +"'\r\n"
						+ "	WHERE cod_editora = " + codigo +";" ;
				st.executeUpdate(comando);
				
				mensagem.updateMensagem("editado");
			}else {
				do {
				mensagem.sairMensagem();
				this.opSair = scanner.next();
				} while (!this.opSair.equalsIgnoreCase(teclaSair)
						&& !this.opSair.equalsIgnoreCase(teclaRepetir));
			}
			
			break;
			
		case 3:
			mensagem.editarAtributoMensagem("CNPJ");
			this.novaPalavra = texto.nextLine();
			
			if(buscar.buscarEditoraCNPJ(novaPalavra, conexao) == null) {
				Statement st = conexao.createStatement();
				String comando = "UPDATE public.editora\r\n"
						+ "	SET cnpj_editora= '"+ novaPalavra +"'\r\n"
						+ "	WHERE cod_editora = " + codigo +";" ;
				st.executeUpdate(comando);
				mensagem.updateMensagem("editado");
			}else {
				System.out.println("\n\nO CNPJ informado já está sendo utilizado por outra empresa.\n\n");
				do {
				mensagem.sairMensagem();
				this.opSair = scanner.next();
				} while (!this.opSair.equalsIgnoreCase(teclaSair)
						&& !this.opSair.equalsIgnoreCase(teclaRepetir));
			}
			break;
			
		case 0:
			
			break;
			
		default:
			mensagem.parametroInvalidoMensagem();
			break;
			
		}
		
		if(!this.opSair.equalsIgnoreCase("S") && !this.opSair.equalsIgnoreCase("R")) {
			do {
				mensagem.continuarMensagem("editando editora");
				this.opSair = scanner.next();
			} while (!this.opSair.equalsIgnoreCase(teclaSair)
					&& !this.opSair.equalsIgnoreCase(teclaContinuar));
		}
		
		
} while (!this.opSair.equalsIgnoreCase(teclaSair));
	}
else {
	mensagem.nenhumResultadoMensagem();
}
}


}
