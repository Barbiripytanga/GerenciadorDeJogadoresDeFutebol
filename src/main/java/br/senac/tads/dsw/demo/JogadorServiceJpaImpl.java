package br.senac.tads.dsw.demo;
//Para que serve: é onde o contrato do JogadorService ganha código de verdade.
// Ela usa o JogadorRepository para falar com o banco e converte entre JogadorDto (o que a API usa)
// e JogadorEntity (o que o banco usa).

import org.springframework.stereotype.Service;

@Service
public class JogadorServiceJpaImpl implements JogadorService {
	private final 
}
