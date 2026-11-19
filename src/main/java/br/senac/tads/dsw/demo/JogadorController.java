package br.senac.tads.dsw.demo;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

	// PUT /jogadores/{id}: altera um jogador. O id vem da URL, os novos dados vêm do JSON.
	// @Valid aplica as mesmas regras do POST (se falhar, vira 400).
	@PutMapping("/{id}")
	public JogadorDto alterarJogador(@PathVariable Long id, @Valid @RequestBody JogadorDto jogador) {
		return jogadorService.alterarJogador(id, jogador);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerJogador(@PathVariable Long id){
		jogadorService.removerJogador(id);
	}

	// Se o Service lançar NaoEncontradoException (PUT ou DELETE com id inexistente),
	// este metodo transforma a exceção numa resposta 404 em JSON com a mensagem.
	@ExceptionHandler(NaoEncontradoException.class)
	public ProblemDetail tratarNaoEncontrado(NaoEncontradoException ex){
		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	// Se o @Valid reprovar os dados (POST ou PUT), devolve 400 em JSON listando
	// cada campo inválido com a sua mensagem (as que estão no JogadorDto).
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail tratarValidacao(MethodArgumentNotValidException ex) {
		Map<String, String> erros = new LinkedHashMap<>();
		for(FieldError erro : ex.getBindingResult().getFieldErrors()){
			erros.put(erro.getField(), erro.getDefaultMessage());
		}
		ProblemDetail problema = ProblemDetail.forStatusAndDetail(
			HttpStatus.BAD_REQUEST,"Dados inválidos");
		problema.setProperty("erros", erros);
		return problema;
	}}





















