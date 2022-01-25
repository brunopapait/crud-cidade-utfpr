package br.com.papait.bruno.crudcidadeutfpr.view;

public final class Cidade {
  private final String nomeCidade;
  private final String nomeEstado;


  public Cidade(final String nomeCidade, final String nomeEstado) {
    this.nomeCidade = nomeCidade;
    this.nomeEstado = nomeEstado;
  }

  public String getNomeCidade() {
    return nomeCidade;
  }

  public String getNomeEstado() {
    return nomeEstado;
  }
}
