//é a classe que recebe os dados que chegam pela API
// (o JSON do POST) e que também devolve os dados nas respostas.
// Ela carrega as regras de validação.
// A entidade fica só para o banco.
package br.senac.tads.dsw.demo;

import jakarta.validation.constraints.*;

public class JogadorDto {
	//gerado pelo banco: não é enviado no POST, por isso não tem validação
	private Long id;

	@NotBlank(message="O nome é obrigatório")
	@Size(min=2, message="O nome deve ter no mínimo 2 caracteres")
	private String nome;

	@NotNull(message = "O número da camisa é obrigatório")
	@Min(value = 1, message = "O número da camisa deve ser no mínimo 1")
	@Max(value = 99, message = "O número da camisa deve ser no máximo 99")
	private Integer numeroCamisa;

	@NotBlank(message = "O time é obrigatório")
	@Size(min = 2, message = "O time deve ter no mínimo 2 caracteres")
	private String time;

	@NotNull(message = "Informe se o jogador está lesionado")
	private Boolean lesionado;

	// opcional: sem validação
	private String observacoes;

	//construtor
	public JogadorDto() {
	}

	//getters e setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumeroCamisa() {
		return numeroCamisa;
	}
	public void setNumeroCamisa(Integer numeroCamisa) {
		this.numeroCamisa = numeroCamisa;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public Boolean getLesionado() {
		return lesionado;
	}
	public void setLesionado(Boolean lesionado) {
		this.lesionado = lesionado;
	}

	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


}
