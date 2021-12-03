package br.com.ucsal.db.biblioteca.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.ucsal.db.biblioteca.connection.Conexao;
import br.com.ucsal.db.biblioteca.methods.Mensagem;
import br.com.ucsal.db.biblioteca.view.Janela;

public class Adicionar {
	final String teclaSair = "S";
	final String teclaRepetir = "R";
	final String teclaContinuar = "C";
	final String teclaCadastrar = "C";
	final String teclaListar = "L";

	Scanner texto = new Scanner(System.in);
	Scanner scanner = new Scanner(System.in);

	Validar validar = new Validar();
	Buscar buscar = new Buscar();
	Mensagem mensagem = new Mensagem();
	Listar listar = new Listar();

	boolean proximo;
	String opSair;

	

	/*
	 * 
	 * Adicionar Autor
	 * 
	 */
	public void adicionarAutor(Connection conexao) throws SQLException {
		int codigo = 0;
		String nome = null;
		this.proximo = false;
		this.opSair = "";
		do {
			// Capturando e validando código
			do {
				mensagem.informeAtributoMensagem("código do autor");
				codigo = scanner.nextInt();

				if (validar.validarCodigo(codigo)) {
					if (buscar.buscarAutorCodigo(codigo, conexao) == null) {
						this.proximo = true;
					} else {

						mensagem.codigoExisteMensagem();
						do {
							mensagem.sairMensagem();
							this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));

					}

				} else {
					do {
						mensagem.sairMensagem();
						this.opSair = scanner.next();
					} while (!this.opSair.equalsIgnoreCase(teclaSair) && !this.opSair.equalsIgnoreCase(teclaRepetir));
				}
			} while (!this.proximo && !this.opSair.equalsIgnoreCase(teclaSair));

			// Capturando e validando o nome
			if (!this.opSair.equalsIgnoreCase(teclaSair)) {
				this.proximo = false;
				do {
					mensagem.informeAtributoMensagem("nome do autor");
					nome = texto.nextLine();

					if (validar.validarNome(nome)) {
						this.proximo = true;
					} else {
						do {
							mensagem.sairMensagem();
							this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));

					}

				} while (!this.proximo && !this.opSair.equalsIgnoreCase(teclaSair));
			}

			if (this.proximo && !this.opSair.equalsIgnoreCase(teclaSair)) {
				Statement st = conexao.createStatement();
				String comando = "INSERT INTO public.autor(\r\n" + "	cod_autor, nome_autor)\r\n" + "	VALUES ("
						+ codigo + ", '" + nome + "');";
				st.executeUpdate(comando);

				mensagem.adicionadoMensagem();

			} else {
				mensagem.operacaoAbortadaMensagem();
			}

			do {
				mensagem.continuarMensagem("adicionando autor");
				this.opSair = scanner.next();
			} while (!this.opSair.equalsIgnoreCase(teclaSair) && !this.opSair.equalsIgnoreCase(teclaContinuar));

		} while (!this.opSair.equalsIgnoreCase(teclaSair));
	}

	/*
	 * 
	 * Adicionar Categoria
	 * 
	 */
	public void adicionarCategoria(Connection conexao) throws SQLException {
		int codigo = 0;
		String nome = null;
		this.proximo = false;
		this.opSair = "";

		do {
			// Capturando e validando código
			do {
				mensagem.informeAtributoMensagem("código da categoria");
				codigo = scanner.nextInt();

				if (validar.validarCodigo(codigo)) {
					if (buscar.buscarCategoriaCodigo(codigo, conexao) == null) {
						this.proximo = true;
					} else {

						mensagem.codigoExisteMensagem();
						do {
							mensagem.sairMensagem();
							this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));

					}

				} else {
					do {
						mensagem.sairMensagem();
						this.opSair = scanner.next();
					} while (!this.opSair.equalsIgnoreCase(teclaSair) && !this.opSair.equalsIgnoreCase(teclaRepetir));
				}
			} while (!this.proximo && !this.opSair.equalsIgnoreCase(teclaSair));

			// Capturando e validando o nome
			if (!this.opSair.equalsIgnoreCase(teclaSair)) {
				this.proximo = false;
				do {
					mensagem.informeAtributoMensagem("nome da categoria");
					nome = texto.nextLine();

					if (validar.validarNome(nome)) {
						this.proximo = true;
					} else {
						do {
							mensagem.sairMensagem();
							this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));

					}

				} while (!this.proximo && !this.opSair.equalsIgnoreCase(teclaSair));
			}

			if (this.proximo && !this.opSair.equalsIgnoreCase(teclaSair)) {
				Statement st = conexao.createStatement();
				String comando = "INSERT INTO public.categoria(\r\n" + "	cod_categoria, descricao_categoria)\r\n"
						+ "	VALUES (" + codigo + ", '" + nome + "');";
				st.executeUpdate(comando);

				mensagem.adicionadoMensagem();

			} else {
				mensagem.operacaoAbortadaMensagem();
			}

			do {
				mensagem.continuarMensagem("adicionando categoria");
				this.opSair = scanner.next();
			} while (!this.opSair.equalsIgnoreCase(teclaSair) && !this.opSair.equalsIgnoreCase(teclaContinuar));

		} while (!this.opSair.equalsIgnoreCase(teclaSair));

	}

	/*
	 * 
	 * Adicionar Editora
	 * 
	 */
	public void adicionarEditora(Connection conexao) throws SQLException {
		int codigo = 0;
		String nome = null, cnpj = null;
		this.proximo = false;
		this.opSair = "";

		do {

			// Capturando e validando código
			do {
				mensagem.informeAtributoMensagem("código da editora");
				codigo = scanner.nextInt();

				if (validar.validarCodigo(codigo)) {
					if (buscar.buscarEditoraCodigo(codigo, conexao) == null) {
						this.proximo = true;
					} else {

						mensagem.codigoExisteMensagem();
						do {
							mensagem.sairMensagem();
							this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));
					}

				} else {
					do {
						mensagem.sairMensagem();
						this.opSair = scanner.next();
					} while (!this.opSair.equalsIgnoreCase(teclaSair) && !this.opSair.equalsIgnoreCase(teclaRepetir));
				}
			} while (!this.proximo && !this.opSair.equalsIgnoreCase(teclaSair));

			// Capturando e validando o nome
			if (!this.opSair.equalsIgnoreCase(teclaSair) && this.proximo==true) {
				this.proximo = false;
				do {
					mensagem.informeAtributoMensagem("nome da editora");
					nome = texto.nextLine();

					if (validar.validarNome(nome)) {
						this.proximo = true;
					} else {
						do {
							mensagem.sairMensagem();
							this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));
					}

				} while (!this.proximo && !this.opSair.equalsIgnoreCase(teclaSair));
			}

			// Capturando e validando CNPJ
			if (!this.opSair.equalsIgnoreCase(teclaSair)) {
				this.proximo = false;
				do {
					System.out.print("\nInforme o número do CNPJ: ");
					cnpj = texto.nextLine();

					if (cnpj.equals("") || buscar.buscarEditoraCNPJ(cnpj, conexao) == null) {
						this.proximo = true;
					} else {
						System.out.println("\n\nO CNPJ informado já está em uso por outra empresa.");
						do {
							mensagem.sairMensagem();
							this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));
					}

				} while (!this.proximo && !this.opSair.equalsIgnoreCase(teclaSair));
			}

			if (this.proximo && !this.opSair.equalsIgnoreCase(teclaSair)) {
				Statement st = conexao.createStatement();
				String comando = "INSERT INTO public.editora(\r\n" + "	cod_editora, nome_editora, cnpj_editora)\r\n"
						+ "	VALUES (" + codigo + ", '" + nome + "', '" + cnpj + "');";
				st.executeUpdate(comando);
				mensagem.adicionadoMensagem();

			} else {
				mensagem.operacaoAbortadaMensagem();
			}

			do {
				mensagem.continuarMensagem("adicionando editora");
				this.opSair = scanner.next();
			} while (!this.opSair.equalsIgnoreCase(teclaSair) && !this.opSair.equalsIgnoreCase(teclaContinuar));

		} while (!this.opSair.equalsIgnoreCase(teclaSair));
	}

	/*
	 * 
	 * Adicionar Livro
	 * 
	 */
	public void adicionarLivro(Connection conexao) throws SQLException {
		int codigo = 0, ano = 0, editora = 0, categoria = 0, autor = 0, isbn = 0, qtdeAutor = 0;
		String nome = "";
		
		
		this.opSair = "";
		this.proximo = false;

		do {
			ArrayList<Integer> arrayAutor = new ArrayList<Integer>();
			// Capturar e validar código
			do {
				mensagem.informeAtributoMensagem("código do livro");
				codigo = scanner.nextInt();
				if (validar.validarCodigo(codigo)) {

					if (buscar.buscarLivroCodigo(codigo, conexao).isEmpty()) {
						this.proximo = true;
					} else {

						mensagem.codigoExisteMensagem();
						do {
							mensagem.sairMensagem();
							this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));
					}

				} else {
					do {
						mensagem.sairMensagem();
						this.opSair = scanner.next();
					} while (!this.opSair.equalsIgnoreCase(teclaSair) && !this.opSair.equalsIgnoreCase(teclaRepetir));
				}
			} while (!this.proximo && !this.opSair.equalsIgnoreCase(teclaSair));

			// Capturar e validar nome
			if (!this.opSair.equalsIgnoreCase(teclaSair)) {
				this.proximo = false;

				do {
					mensagem.informeAtributoMensagem("nome do livro");
					nome = texto.nextLine();

					if (validar.validarNome(nome)) {
						this.proximo = true;
					} else {
						do {
							mensagem.sairMensagem();
							this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));
					}

				} while (!this.proximo && !this.opSair.equalsIgnoreCase(teclaSair));

			}
			// Capturar e validar ISBN
			if (!this.opSair.equalsIgnoreCase(teclaSair)) {
				this.proximo = false;
				do {
					mensagem.informeAtributoMensagem("ISBN");
					isbn = scanner.nextInt();

					if (buscar.buscarLivroISBN(isbn, conexao).isEmpty()) {
						this.proximo = true;
					} else {

						System.out.println("\n\nISBN já cadastrado.");
						do {
							mensagem.sairMensagem();
							this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));
					}
				} while (!this.proximo && !this.opSair.equalsIgnoreCase(teclaSair));
			}

			// Capturar e validar o ano
			if (!this.opSair.equalsIgnoreCase(teclaSair)) {
				this.proximo = false;
				do {
					mensagem.informeAtributoMensagem("ano do livro");
					ano = scanner.nextInt();

					if (validar.validarAno(ano)) {
						this.proximo = true;
					} else {

						System.out.println("\n\nAno informado é maior que o ano atual.");
						do {
							mensagem.sairMensagem();
							this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));
					}

				} while (!this.proximo && !this.opSair.equalsIgnoreCase(teclaSair));
			}

			// Capturar e validar editora
			if (!this.opSair.equalsIgnoreCase(teclaSair)) {
				this.proximo = false;
				do {
					mensagem.informeAtributoMensagem("código da editora");
					editora = scanner.nextInt();

					if (!(buscar.buscarEditoraCodigo(editora, conexao) == null)) {
						this.proximo = true;
					} else {
						do {
							mensagem.codigoInexistenteMensagem("editora");
							this.opSair = scanner.next();

							if (this.opSair.equalsIgnoreCase(teclaListar)) {
								listar.listarEditora(conexao);
							} else if (this.opSair.equalsIgnoreCase(teclaCadastrar)) {
								String aux = this.opSair;
								adicionarEditora(conexao);
								this.opSair = aux;
							}
						} while (!this.opSair.equalsIgnoreCase(teclaCadastrar)
								&& !this.opSair.equalsIgnoreCase(teclaListar)
								&& !this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));

					}
				} while (!this.proximo && !this.opSair.equalsIgnoreCase(teclaSair));
			}

			// Capturar e validar categoria
			if (!this.opSair.equalsIgnoreCase(teclaSair)) {
				this.proximo = false;
				do {
					mensagem.informeAtributoMensagem("código da categoria");
					categoria = scanner.nextInt();

					if (!(buscar.buscarCategoriaCodigo(categoria, conexao) == null)) {
						this.proximo = true;
					} else {
						do {
						mensagem.codigoInexistenteMensagem("categoria");
						this.opSair = scanner.next();

						if (this.opSair.equalsIgnoreCase(teclaListar)) {
							listar.listarCategoria(conexao);
						} else if (this.opSair.equalsIgnoreCase(teclaCadastrar)) {
							String aux = this.opSair;
							adicionarCategoria(conexao);
							this.opSair = aux;
						}
						} while (!this.opSair.equalsIgnoreCase(teclaCadastrar)
								&& !this.opSair.equalsIgnoreCase(teclaListar)
								&& !this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));

					}
				} while (!this.proximo && !this.opSair.equalsIgnoreCase(teclaSair));
			}

			// Capturar e validar Autor

			if (!this.opSair.equalsIgnoreCase(teclaSair)) {
				this.proximo = false;
				do {
					mensagem.informeAtributoMensagem("a quantidade de autores do livro");
					qtdeAutor = scanner.nextInt();

					if (qtdeAutor > 0) {
						
						if (qtdeAutor < 2) {
							mensagem.informeAtributoMensagem("código do autor");
							autor = scanner.nextInt();
							if (!(buscar.buscarAutorCodigo(autor, conexao) == null)) {
								this.proximo = true;
							} else {
								
								do {
								mensagem.codigoInexistenteMensagem("autor");
								this.opSair = scanner.next();

								if (this.opSair.equalsIgnoreCase(teclaCadastrar)) {
									String aux = this.opSair;
									adicionarAutor(conexao);
									this.opSair = aux;
									this.proximo = false;
								} else if (this.opSair.equalsIgnoreCase(teclaListar)) {
									listar.listarAutor(conexao);
								}
							} while (!this.opSair.equalsIgnoreCase(teclaCadastrar)
									&& !this.opSair.equalsIgnoreCase(teclaListar)
									&& !this.opSair.equalsIgnoreCase(teclaSair)
									&& !this.opSair.equalsIgnoreCase(teclaRepetir));
							}
						} else {
							
							for (int i = 0; i < qtdeAutor && !this.opSair.equalsIgnoreCase("S");) {
								mensagem.informeAtributoMensagem("código do " + (i + 1) + "º autor");
								autor = scanner.nextInt();

								if (buscar.buscarAutorCodigo(autor, conexao) != null) {
									if(!arrayAutor.contains(autor)) {
										arrayAutor.add(autor);
										i++;
									}else {
										
										System.out.println("\n\nEste autor já foi adicionado ao livro.\n\n");
										do {
											mensagem.sairMensagem();
											this.opSair = scanner.next();

											
											} while (!this.opSair.equalsIgnoreCase(teclaSair)
													&& !this.opSair.equalsIgnoreCase(teclaRepetir));
									}
									
								} else {
								
									do {
									mensagem.codigoInexistenteMensagem("autor");
									this.opSair = scanner.next();

									if (this.opSair.equalsIgnoreCase(teclaCadastrar)) {
										String aux = this.opSair;
										adicionarAutor(conexao);
										this.opSair = aux;
										this.proximo = false;
									} else if (this.opSair.equalsIgnoreCase(teclaListar)) {
										listar.listarAutor(conexao);
									}
									} while (!this.opSair.equalsIgnoreCase(teclaCadastrar)
											&& !this.opSair.equalsIgnoreCase(teclaListar)
											&& !this.opSair.equalsIgnoreCase(teclaSair)
											&& !this.opSair.equalsIgnoreCase(teclaRepetir));
								}

							}

							if (!this.opSair.equalsIgnoreCase(teclaSair)) {
								this.proximo = true;
							}
						}

					} else {
						this.proximo = false;
						System.out.println("\n\nA quantidade de autores não pode ser menor que um.");
						do {
							mensagem.sairMensagem();
							this.opSair = scanner.next();
						} while (!this.opSair.equalsIgnoreCase(teclaSair)
								&& !this.opSair.equalsIgnoreCase(teclaRepetir));
					}
				} while (!this.proximo && !this.opSair.equalsIgnoreCase(teclaSair));

			}
			Statement st = conexao.createStatement();
			String comando = "";
			if (this.proximo && !this.opSair.equalsIgnoreCase(teclaSair)) {
				comando = "INSERT INTO public.livro(\r\n"
						+ "	cod_livro, isbn, titulo, ano, cod_editora, cod_categoria)\r\n" + "	VALUES (" + codigo
						+ ", " + isbn + ", '" + nome + "', " + ano + ", " + editora + ", " + categoria + " );";
				st.executeUpdate(comando);

				if (qtdeAutor < 2) {
					comando = "INSERT INTO public.livro_autor(\r\n" + "	cod_livro, cod_autor)\r\n" + "	VALUES ("
							+ codigo + ", " + autor + " );";
					st.executeUpdate(comando);

				} else {

					for (int i = 0; i < qtdeAutor; i++) {
						comando = "INSERT INTO public.livro_autor(\r\n" + "	cod_livro, cod_autor)\r\n" + "	VALUES ("
								+ codigo + ", " + arrayAutor.get(i) + ");";
						st.executeUpdate(comando);

					}

				}

				mensagem.adicionadoMensagem();

			} else {
				mensagem.operacaoAbortadaMensagem();
			}

			do {
				mensagem.continuarMensagem("adicionando livro");
				this.opSair = scanner.next();
			} while (!this.opSair.equalsIgnoreCase(teclaSair) && !this.opSair.equalsIgnoreCase(teclaContinuar));

		} while (!this.opSair.equalsIgnoreCase(teclaSair));

	}

}
