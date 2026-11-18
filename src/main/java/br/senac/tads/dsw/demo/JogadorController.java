package br.senac.tads.dsw.demo;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

	//o controller depende da interface e nao da implementacao
	private final JogadorService jogadorService;

	// Injeção de dependência pelo construtor.
	public JogadorController(JogadorService jogadorService) {
		this.jogadorService = jogadorService;
	}

	// GET /jogadores -> lista todos os jogadores.
	@GetMapping
	public List<JogadorDto> obterJogadores() {
		return jogadorService.obterJogadores();
	}

	@GetMapping("/{id}")
	public JogadorDto obterJogador(@PathVariable Long id) {
		return jogadorService.obterJogador(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public JogadorDto incluirNovoJogador(@Valid @RequestBody JogadorDto jogador) {
		return jogadorService.incluirNovoJogador(jogador);
	}

}
