package br.senac.tads.dsw.demo.repositorio;

import br.senac.tads.dsw.demo.entidade.JogadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//extends JpaRepository<JogadorEntity, Long>--- é uma ficha de um jogador e diz o tipo do id.
public interface JogadorRepository extends JpaRepository<JogadorEntity, Long> {
}
