package br.com.papait.bruno.crudcidadeutfpr.view;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public final class Cidade {

  @NotBlank(message = "{app.cidade.blank}")
  @Size(min = 5, max = 60, message = "{app.cidade.size}")
  private final String nomeCidade;

  @NotBlank(message = "{app.estado.blank}")
  @Size(min = 2, max = 2, message = "{app.estado.size}")
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
