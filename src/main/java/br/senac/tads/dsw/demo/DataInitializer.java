package br.senac.tads.dsw.demo;

import br.senac.tads.dsw.demo.entidade.JogadorEntity;
import br.senac.tads.dsw.demo.repositorio.JogadorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component // avisa ao Spring: "gerencie esta classe"
// CommandLineRunner é um contrato: quem implementa precisa ter um método run,
// e o Spring o chama quando termina de subir.
public class DataInitializer implements CommandLineRunner {

	private final JogadorRepository jogadorRepository;

	// construtor: o Spring entrega o repositório pronto
	public DataInitializer(JogadorRepository jogadorRepository) {
		this.jogadorRepository = jogadorRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		// 1º jogador
		JogadorEntity j1 = new JogadorEntity();
		j1.setNome("Vini Jr");
		j1.setNumeroCamisa(7);
		j1.setTime("Real Madrid");
		j1.setLesionado(false);
		jogadorRepository.save(j1);

		// 2º jogador
		JogadorEntity j2 = new JogadorEntity();
		j2.setNome("Neymar");
		j2.setNumeroCamisa(10);
		j2.setTime("Santos");
		j2.setLesionado(true);
		j2.setObservacoes("Quase aposentado");
		jogadorRepository.save(j2);
	}
}
