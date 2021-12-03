package br.com.ucsal.db.biblioteca.methods;

public class Mensagem {
	
	public void retornoPesquisaMensagem() {
		System.out.println("\n\nA busca retornou o(s) seguinte(s) resultado(s):\n");
	}

	public void sairMensagem() {
		System.out.println("\n\nDigite [S] para ABORTAR ou [R] para REPETIR a operação.");
	}
	
	public void continuarMensagem(String operacao) {
		System.out.println("\n\nDigite [S] para VOLTAR ou [C] para CONTINUAR " + operacao + ".");
		
	}

	public void codigoExisteMensagem() {
		System.out.println("\n\nO código informado já existe.");
	}



	public void codigoInexistenteMensagem(String objeto) {
		System.out.println(
				"\n\nO código do objeto informado não existe no banco de dados. Digite [L] para exibir a LISTA de "
						+ objeto + " no banco de dados, [C] para CADASTRAR uma nova instância de " + objeto
						+ ", [R] para REPETIR a operação ou [S] para SAIR.");
	}

	public void adicionadoMensagem() {

		System.out.println("\n\nObjeto adicionado com sucesso ao banco de dados.\n\n");

	}

	public void operacaoAbortadaMensagem() {
		System.out.println("\n\nOperação abortada pelo usuário.\n\n");
	}

	public void nenhumResultadoMensagem() {
		System.out.println("\n\nA busca não retornou nenhum resultado.\n\n");
	}
	
	public void escolherItemMensagem (int numero, String opcao) {
		System.out.println("["+ numero + "]" + " " + opcao);

	}
	
	
		
	public void editarAtributoMensagem(String objeto) {
		System.out.print("\n\nInforme o novo valor de "+ objeto + ": ");
	}
	
	public void sairProgramaMensagem() {
		System.out.println("\n\nSaindo do programa.\n\n");
	}
	
	public void continuarMenuMensagem(String operacao) {
		System.out.println("\n\nDigite [S] para VOLTAR AO MENU PRINCIPAL ou [C] para CONTINUAR " + operacao + ".");
	}
	
	public void parametroInvalidoMensagem() {
		System.out.println("\n\nParâmetro informado é inválido.\n\n");
	}
	
	public void escolhaMensagem (String objeto) {
		System.out.println("\n\nQue item você deseja " + objeto + "?");
	}
	
	public void informeAtributoMensagem (String objeto) {
		System.out.print("\nInforme " + objeto + ": ");
	}
	
	public void atributoBuscaMensagem() {
		System.out.println("\n\nPor qual atributo você deseja realizar a busca?");
	}
	
	public void updateMensagem(String update) {
		System.out.println("\n\nO objeto foi " + update + ".\n\n");
	}
	
	public void updateErroMensagem(String update) {
		System.out.println("\n\nA " + update +" não será possível pois o registro está associado ao(s) seguinte(s) livro(s):\n");
	}
	
	
}
