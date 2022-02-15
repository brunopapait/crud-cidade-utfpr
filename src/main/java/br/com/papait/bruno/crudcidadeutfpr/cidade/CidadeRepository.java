package br.com.papait.bruno.crudcidadeutfpr.cidade;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<CidadeEntidade, Long> {

  Optional<CidadeEntidade> findByNomeCidadeAndNomeEstado(String nomeCidade, String nomeEstado);
}
