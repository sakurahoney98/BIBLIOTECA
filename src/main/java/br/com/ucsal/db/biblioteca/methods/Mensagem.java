package br.com.ucsal.db.biblioteca.methods;

public class Mensagem {
	
	public void retornoPesquisaMensagem() {
		System.out.println("\n\nA busca retornou o(s) seguinte(s) resultado(s):\n");
	}

	public void sairMensagem() {
		System.out.println("\n\nDigite [S] para ABORTAR ou [R] para REPETIR a opera��o.");
	}
	
	public void continuarMensagem(String operacao) {
		System.out.println("\n\nDigite [S] para VOLTAR ou [C] para CONTINUAR " + operacao + ".");
		
	}

	public void codigoExisteMensagem() {
		System.out.println("\n\nO c�digo informado j� existe.");
	}



	public void codigoInexistenteMensagem(String objeto) {
		System.out.println(
				"\n\nO c�digo do objeto informado n�o existe no banco de dados. Digite [L] para exibir a LISTA de "
						+ objeto + " no banco de dados, [C] para CADASTRAR uma nova inst�ncia de " + objeto
						+ ", [R] para REPETIR a opera��o ou [S] para SAIR.");
	}

	public void adicionadoMensagem() {

		System.out.println("\n\nObjeto adicionado com sucesso ao banco de dados.\n\n");

	}

	public void operacaoAbortadaMensagem() {
		System.out.println("\n\nOpera��o abortada pelo usu�rio.\n\n");
	}

	public void nenhumResultadoMensagem() {
		System.out.println("\n\nA busca n�o retornou nenhum resultado.\n\n");
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
		System.out.println("\n\nPar�metro informado � inv�lido.\n\n");
	}
	
	public void escolhaMensagem (String objeto) {
		System.out.println("\n\nQue item voc� deseja " + objeto + "?");
	}
	
	public void informeAtributoMensagem (String objeto) {
		System.out.print("\nInforme " + objeto + ": ");
	}
	
	public void atributoBuscaMensagem() {
		System.out.println("\n\nPor qual atributo voc� deseja realizar a busca?");
	}
	
	public void updateMensagem(String update) {
		System.out.println("\n\nO objeto foi " + update + ".\n\n");
	}
	
	public void updateErroMensagem(String update) {
		System.out.println("\n\nA " + update +" n�o ser� poss�vel pois o registro est� associado ao(s) seguinte(s) livro(s):\n");
	}
	
	
}
