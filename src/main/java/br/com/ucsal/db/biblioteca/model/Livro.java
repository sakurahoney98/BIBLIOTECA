package br.com.ucsal.db.biblioteca.model;

import java.util.ArrayList;

public class Livro {
	
	private	int codigo;
	private	int ISBN;
	private	String nome;
	private	String categoria;
	private	String editora;
	private int ano;
	private String autor;
	
	
	public Livro(int codigo, int iSBN, String nome, int ano, String editora, String categoria, String autor) {
		super();
		this.codigo = codigo;
		this.ISBN = iSBN;
		this.nome = nome;
		this.categoria = categoria;
		this.editora = editora;
		this.ano = ano;
		this.autor = autor;
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
	
	


}
