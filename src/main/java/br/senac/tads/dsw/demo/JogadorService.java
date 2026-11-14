package br.senac.tads.dsw.demo;
//Para que serve: é o contrato do que o sistema sabe fazer com jogadores.
// Ela só lista os métodos, sem código.
// Quem implementa de verdade é a JogadorServiceJpaImpl


import java.util.List;
import java.util.Optional;

public interface JogadorService {

	List<JogadorDto> obterJogadores();

	Optional<JogadorDto> obterjogador();

	JogadorDto incluirNovoJogador(JogadorDto jogador);
}
