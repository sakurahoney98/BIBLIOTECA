package br.com.ucsal.db.biblioteca.model;

public class Editora {
	
	private	int codigo;
	private	String nome;
	private	String cnpj;
	public Editora(int codigo, String nome, String cnpj) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cnpj = cnpj;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCNPJ() {
		return cnpj;
	}
	public void setCNPJ(String cnpj) {
		cnpj = cnpj;
	}
	
	
	


}
