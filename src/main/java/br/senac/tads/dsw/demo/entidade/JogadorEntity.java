package br.senac.tads.dsw.demo.entidade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.Length;

@Entity
@Table(name = "tb_jogadores")
public class JogadorEntity {

	@Id @GeneratedValue
	private long id;  //autoincremento (o banco gera o valor)

	@Column(nullable = false, length = 100)
	private String nome; //Obrigatório (NOT NULL), até 100 caracteres

	@Column(name="numero_camisa", nullable=false)
	private Integer numeroCamisa;// aqui NAO eh feita a validacao se ta entre 1 e 99

	@Column(nullable = false, length = 100)
	private String time;

	@Column(nullable = false)
	private Boolean lesionado;// Obrigatório: true = lesionado, false = não lesionado.

	@Column(length = 500)
	private String observacoes;


	// O JPA exige um construtor sem argumentos.
	public JogadorEntity() {
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

