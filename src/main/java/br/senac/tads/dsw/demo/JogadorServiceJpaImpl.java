package br.senac.tads.dsw.demo;
//Para que serve: é onde o contrato do JogadorService ganha código de verdade.
// Ela usa o JogadorRepository para falar com o banco e converte entre JogadorDto (o que a API usa)
// e JogadorEntity (o que o banco usa).

import br.senac.tads.dsw.demo.entidade.JogadorEntity;
import br.senac.tads.dsw.demo.repositorio.JogadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service marca a classe como a camada de regras de negócio.
// O Spring cria uma única instância dela e a entrega a quem pedir um JogadorService.
@Service
public class JogadorServiceJpaImpl implements JogadorService {

	//acesso ao BD. "final" garante que nao sera trocado depois de criado
	private final JogadorRepository jogadorRepository;

	// Injeção de dependência pelo construtor: o Spring entrega o repositório pronto.
	public JogadorServiceJpaImpl(JogadorRepository jogadorRepository) {
		this.jogadorRepository = jogadorRepository;
	}

	// Busca todos os jogadores no banco e converte cada entidade em DTO.
	@Override
	public List<JogadorDto> obterJogadores() {
		return jogadorRepository.findAll().stream()
			.map(this::toDto)
			.toList();
	}

	// findById devolve um Optional (pode não existir).
	// O map converte para DTO apenas se o jogador foi encontrado.
	@Override
	public Optional<JogadorDto> obterJogador(Long id) {
		return jogadorRepository.findById(id).map(this::toDto);
	}


	@Override
	public JogadorDto incluirNovoJogador(JogadorDto jogador) {
		JogadorEntity salvo = jogadorRepository.save(toEntity(jogador));
		return toDto(salvo);
	}//exemplo do que essa parte de cima faz
	// Situação: chega este JSON, sem id:
	//{ "nome": "Messi", "numeroCamisa": 10, "time": "Inter Miami", "lesionado": false }
	//
	//Passo A: vira um DTO. O Spring pega o JSON e monta um JogadorDto:
	//nome = Messi, camisa = 10, id = vazio.
	//
	//Passo B: toEntity. O banco não aceita DTO, só entidade. Então o toEntity copia os dados do DTO para uma JogadorEntity nova:
	//nome = Messi, camisa = 10, id = vazio.
	//
	//Passo C: save. O repositório grava no banco, que escolhe o id (por exemplo, 3). A entidade que volta já está assim:
	//nome = Messi, camisa = 10, id = 3.
	//
	//Passo D: toDto. A resposta para quem chamou tem que ser um DTO, não uma entidade. O toDto copia tudo para um JogadorDto, agora com o id = 3.
	//
	//Passo E: resposta. O DTO vira JSON e volta:
	//{ "id": 3, "nome": "Messi", ... }
	//tudo isso nessa parte do codigo
	//JogadorEntity salvo = jogadorRepository.save(toEntity(jogador));
	//return toDto(salvo);

	@Override
	public JogadorDto alterarJogador(Long id, JogadorDto jogador){
		JogadorEntity entidade= jogadorRepository.findById(id)
			.orElseThrow(()-> new NaoEncontradoException("Jogador não encontrado: " + id ));

// copia os novos dados do DTO para a entidade que veio do banco (o id não muda)
		entidade.setNome(jogador.getNome());
		entidade.setNumeroCamisa(jogador.getNumeroCamisa());
		entidade.setTime(jogador.getTime());
		entidade.setLesionado(jogador.getLesionado());
		entidade.setObservacoes(jogador.getObservacoes());

		// como a entidade já tem id, o save faz UPDATE (e não cria outro jogador)
    JogadorEntity salvo =  jogadorRepository.save(entidade);
	return toDto(salvo);
	}

	@Override
	public void removerJogador(Long id){
		if(!jogadorRepository.existsById(id)){
			throw new NaoEncontradoException("Jogador não encontrado: " + id );
		}
		jogadorRepository.deleteById(id);
	}


	//entidade(banco) -> dto(api). copia o id tambem para a tela saber qual é o jogador.
	private JogadorDto toDto(JogadorEntity entidade) {
		JogadorDto dto = new JogadorDto();
		dto.setId(entidade.getId());
		dto.setNome(entidade.getNome());
		dto.setNumeroCamisa(entidade.getNumeroCamisa());
		dto.setTime(entidade.getTime());
		dto.setLesionado(entidade.getLesionado());
		dto.setObservacoes(entidade.getObservacoes());
		return dto;
	}

	//DTO(API) -> entidade (banco) nao copia o id, quem gera eh o banco.
// DTO (API) -> entidade (banco). Não copia o id: quem gera é o banco.
	private JogadorEntity toEntity(JogadorDto dto) {
		JogadorEntity entidade = new JogadorEntity();
		entidade.setNome(dto.getNome());
		entidade.setNumeroCamisa(dto.getNumeroCamisa());
		entidade.setTime(dto.getTime());
		entidade.setLesionado(dto.getLesionado());
		entidade.setObservacoes(dto.getObservacoes());
		return entidade;
	}

}
