package br.com.papait.bruno.crudcidadeutfpr.cidade;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "cidade")
public class CidadeEntidade implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nome_cidade")
  private String nomeCidade;

  @Column(name = "nome_estado")
  private String nomeEstado;

  public CidadeEntidade() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNomeCidade() {
    return nomeCidade;
  }

  public void setNomeCidade(String nomeCidade) {
    this.nomeCidade = nomeCidade;
  }

  public String getNomeEstado() {
    return nomeEstado;
  }

  public void setNomeEstado(String nomeEstado) {
    this.nomeEstado = nomeEstado;
  }
}
