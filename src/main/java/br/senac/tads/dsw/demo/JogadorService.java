package br.senac.tads.dsw.demo;
//Para que serve: é o contrato do que o sistema sabe fazer com jogadores.
// Ela só lista os métodos, sem código.
// Quem implementa de verdade é a JogadorServiceJpaImpl


import java.util.List;
import java.util.Optional;

public interface JogadorService {

	List<JogadorDto> obterJogadores();

	Optional<JogadorDto> obterJogador(Long id);

	JogadorDto incluirNovoJogador(JogadorDto jogador);

	JogadorDto alterarJogador(Long id, JogadorDto jogador);

	void removerJogador(Long id);
   //void   removerJogador   (Long id)   ;
   // |           |              |       |
   // |           |              |       termina a declaração (interface não tem corpo)
    //|           |              o que o metodo RECEBE: um número do tipo Long, chamado id
   // |           o NOME do metodo
   // o que o metodo DEVOLVE: nada
}
