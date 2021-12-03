package br.com.ucsal.db.biblioteca.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import br.com.ucsal.db.biblioteca.connection.Conexao;
import br.com.ucsal.db.biblioteca.methods.Mensagem;
import br.com.ucsal.db.biblioteca.methods.Metodos;


public class Janela {
	





	public static void main(String[] args) throws ParseException, SQLException {
		Conexao con = new Conexao();
		Connection conexao = con.getConexao();
		
		
		Scanner scanner = new Scanner(System.in);
		Metodos metodos = new Metodos();
		Mensagem mensagem = new Mensagem();
		
		
		int opcao = 0;
		String opSair;
		
	
		
		
		
		System.out.println("\n\n\n---------Bem vindo ao Sistema de Gestão de Biblioteca!---------");
		
		do {
			System.out.println("\n\nO que deseja fazer?");
			opSair = null;
		mensagem.escolherItemMensagem(1, "Adicionar");
		mensagem.escolherItemMensagem(2, "Buscar");
		mensagem.escolherItemMensagem(3, "Editar");
		mensagem.escolherItemMensagem(4, "Excluir");
		mensagem.escolherItemMensagem(5, "Listar");
		mensagem.escolherItemMensagem(6, "Adicionar Random");
		mensagem.escolherItemMensagem(0, "Sair");
		System.out.print("\nOpção: ");
		opcao = scanner.nextInt();
		
		switch(opcao) {
		case 1:
			metodos.adicionar(conexao);
			break;
			
		case 2:
			metodos.buscar(conexao);
			break;
			
		case 3:
			metodos.editar(conexao);
			break;
			
		case 4:
			metodos.exlcuir(conexao);
			break;
			
		case 5:
			metodos.listar(conexao);
			break;
			
		case 6:
			metodos.adicionarRandom(conexao);
			break;
			
		case 0:
			mensagem.sairProgramaMensagem();
			break;
			
		default:
			mensagem.parametroInvalidoMensagem();
			break;
		}
		
		
		
		}while(opcao != 0);
	}
	
	
	

}
