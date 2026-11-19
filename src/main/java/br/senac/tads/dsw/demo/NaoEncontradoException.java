package br.senac.tads.dsw.demo;

// Exceção própria para "o jogador pedido não existe".
// RuntimeException: não obriga a declarar "throws" nos métodos que a lançam.
public class NaoEncontradoException extends RuntimeException {


	// Recebe a mensagem de erro e a repassa para a classe mãe.
	public NaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
