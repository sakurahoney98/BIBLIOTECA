package br.com.ucsal.db.biblioteca.controller;

import java.nio.file.attribute.AclEntryPermission;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class AdicionarRandom {
	Random gerador = new Random();
	Scanner sc = new Scanner(System.in);
	Buscar buscar = new Buscar();
	Validar validar = new Validar();
	
	final int anoMinimo = 1990;
	final int anoMaximo = Year.now().getValue();
	final int qtdeAutorMax = 5;
	
	public void adicionarAutor(Connection conexao) throws SQLException {
		
		System.out.print("\n\nInforme a quantidade de autores a serem gerados: ");
		int quant = sc.nextInt();
		int contador = 0;
		for(int i = 0; i < quant;) {
			if(validar.quantMaximaAutor(conexao)) {
				boolean prox = true;
				int codigo = gerador.nextInt(9000) + 1000;
				while(buscar.buscarAutorCodigo(codigo, conexao) != null) {
					codigo = gerador.nextInt(9000) + 1000;
				}
				String nome = "RandomAutor " + codigo;
				
				try {
				Statement st = conexao.createStatement();
				String comando = "INSERT INTO public.autor(\r\n" + "	cod_autor, nome_autor)\r\n" + "	VALUES ("
						+ codigo + ", '" + nome + "');";
				st.executeUpdate(comando);
				
			} catch (Exception e) {
				prox = false;
			}
			
			if(prox) {
				i++;
				contador++;
			}
			}else {
				System.out.println("\nQuantidade máxima atingida, não é possível adicionar mais autor.\n");
				break;
			}
		
		}
		
		System.out.println("\n\nForam adicionados " + contador + " autores com sucesso.\n\n");
		
	}
	
	public void adicionarCategoria(Connection conexao) throws SQLException {
		System.out.print("\n\nInforme a quantidade de Categorias a serem geradas: ");
		int quant = sc.nextInt();
		int contador = 0;
		for(int i = 0; i < quant;) {
			if(validar.quantMaximaCategoria(conexao)) {
				boolean prox = true;
				int codigo = gerador.nextInt(9000) + 1000;
				while(buscar.buscarAutorCodigo(codigo, conexao) != null) {
					codigo = gerador.nextInt(9000) + 1000;
				}
				String nome = "RandomCategoria " + codigo;
				
				try {
				Statement st = conexao.createStatement();
				String comando = "INSERT INTO public.categoria(\r\n" + "	cod_categoria, descricao_categoria)\r\n"
						+ "	VALUES (" + codigo + ", '" + nome + "');";
				st.executeUpdate(comando);
				} catch (Exception e) {
					prox = false;
				}
				
				if(prox) {
					i++;
					contador++;
				}
			
			}else {
				System.out.println("\nQuantidade máxima atingida, não é possível adicionar mais categoria.\n");
				break;	
			}
		}
		System.out.println("\n\nForam adicionadas " + contador + " categorias com sucesso.\n\n");
		
		
	}
	
	
	public void adicionarEditora(Connection conexao) throws SQLException {
		System.out.print("\n\nInforme a quantidade de Editoras a serem geradas: ");
		int quant = sc.nextInt();
		int contador = 0;
		
		for(int i = 0; i < quant;) {
			if(validar.quantMaximaEditora(conexao)) {
				boolean prox = true;
				int codigo = gerador.nextInt(9000) + 1000;
				while(buscar.buscarAutorCodigo(codigo, conexao) != null) {
					codigo = gerador.nextInt(9000) + 1000;
				}
				String nome = "RandomEditora " + codigo;
				String cnpj = (gerador.nextInt(10) + 10)   + "." + (gerador.nextInt(900) + 100) + "." + (gerador.nextInt(900) + 100) + "/000" + (gerador.nextInt(9) + 1) + "-" + (gerador.nextInt(10) + 10);
				while(buscar.buscarEditoraCNPJ(cnpj, conexao) != null) {
					cnpj = (gerador.nextInt(10) + 10)   + "." + (gerador.nextInt(900) + 100) + "." + (gerador.nextInt(900) + 100) + "/000" + (gerador.nextInt(9) + 1) + "-" + (gerador.nextInt(90) + 10);
				}
				try {
				Statement st = conexao.createStatement();
				String comando = "INSERT INTO public.editora(\r\n" + "	cod_editora, nome_editora, cnpj_editora)\r\n"
						+ "	VALUES (" + codigo + ", '" + nome + "', '" + cnpj + "');";
				st.executeUpdate(comando);
				} catch (Exception e) {
					prox = false;
				}
				
				if(prox) {
					i++;
					contador++;
				}
			}else {
				System.out.println("\nQuantidade máxima atingida, não é possível adicionar mais editora.\n");
				break;	
			}
		
		}
		System.out.println("\n\nForam adicionadas " + contador + " categorias com sucesso.\n\n");
	}
	
	
	public void adicionarLivro (Connection conexao) throws SQLException {
		System.out.print("\n\nInforme a quantidade de Livros a serem gerados: ");
		int quant = sc.nextInt();
		int contador = 0;
		for(int i = 0; i < quant;) {
			if(validar.quantMaximaLivro(conexao)) {
				boolean prox = true;
				ArrayList<Integer> arrayAux = new ArrayList<Integer>();
				int editora = 0, categoria = 0, autor = 0, qtdeAutor = 0;
				int codigo = gerador.nextInt(9000) + 1000;
				while(buscar.buscarAutorCodigo(codigo, conexao) != null) {
					codigo = gerador.nextInt(9000) + 1000;
				}
				String nome = "RandomLivro " + codigo;
				
				int ano = gerador.nextInt((anoMaximo + 1) - anoMinimo) + anoMinimo; 
				
						
				int isbn = gerador.nextInt(9000000) + 1000000; 
				
				while(!buscar.buscarLivroISBN(isbn, conexao).isEmpty()) {
					isbn = gerador.nextInt(9000000) + 1000000; 
				}
				
				Statement st = conexao.createStatement();
				String comando = "SELECT cod_editora FROM public.editora";
				ResultSet resultado = st.executeQuery(comando);
				
				while(resultado.next()) {
					arrayAux.add(resultado.getInt(1));
				}
				
				if(!arrayAux.isEmpty()) {
					int indice = gerador.nextInt(arrayAux.size());
					editora = arrayAux.get(indice);
					arrayAux.clear();
					
					st = conexao.createStatement();
					comando = "SELECT cod_categoria FROM public.categoria";
					resultado = st.executeQuery(comando);
					while(resultado.next()) {
						arrayAux.add(resultado.getInt(1));
					}
					if(!arrayAux.isEmpty()) {
						indice = gerador.nextInt(arrayAux.size());
						categoria = arrayAux.get(indice);
						arrayAux.clear();
						
						st = conexao.createStatement();
						comando = "SELECT cod_autor FROM public.autor";
						resultado = st.executeQuery(comando);
						while(resultado.next()) {
							arrayAux.add(resultado.getInt(1));
						}
						
						if(!arrayAux.isEmpty()) {
							
							
							qtdeAutor = gerador.nextInt(qtdeAutorMax) + 1;
							
							while(qtdeAutor >= arrayAux.size()) {
								qtdeAutor = gerador.nextInt(qtdeAutorMax) + 1;
							}
						
							try {
							comando = "INSERT INTO public.livro(\r\n"
									+ "	cod_livro, isbn, titulo, ano, cod_editora, cod_categoria)\r\n" + "	VALUES (" + codigo
									+ ", " + isbn + ", '" + nome + "', " + ano + ", " + editora + ", " + categoria + " );";
							st.executeUpdate(comando);
							
							for(int j = 0; j < qtdeAutor; ) {
								
								indice = gerador.nextInt(arrayAux.size());
								if(!buscar.buscarLivroAutor(arrayAux.get(indice), codigo, conexao)) {
									
										comando = "INSERT INTO public.livro_autor(\r\n" + "	cod_livro, cod_autor)\r\n" + "	VALUES ("
												+ codigo + ", " + arrayAux.get(indice) + ");";
										st.executeUpdate(comando);
										j++;
								
									
								}
							}
							} catch (Exception e) {
								prox = false;
								
							}
							if(prox) {
								i++;
								contador++;
							}
							
							
							
				

							
							
						}else {
							System.out.println("\n\nAntes de adicionar um livro é preciso cadastrar:\n* Autor\n* Editora\n* Categoria.\n\n");
						break;
						}
						
					}else {
						System.out.println("\n\nAntes de adicionar um livro é preciso cadastrar:\n* Autor\n* Editora\n* Categoria.\n\n");
					break;	
					}
					
					
				
						
			}else {
				System.out.println("\n\nAntes de adicionar um livro é preciso cadastrar:\n* Autor\n* Editora\n* Categoria.\n\n");
			break;
			}
			}else {
				System.out.println("\nQuantidade máxima atingida, não é possível adicionar mais livro.\n");
				break;
			}
		
		
		
		
	}
		System.out.println("\n\nForam adicionados " + contador + " livros com sucesso.\n\n");
		
	}

}
