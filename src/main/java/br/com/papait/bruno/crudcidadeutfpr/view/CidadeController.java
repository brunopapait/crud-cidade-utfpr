package br.com.papait.bruno.crudcidadeutfpr.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class CidadeController {

  private Set<Cidade> cidades = new HashSet<>();

  @GetMapping(value = "/")
  public String listar(Model memoria) {

    memoria.addAttribute("listaCidades", cidades);
    return "/crud";
  }

  @PostMapping("/criar")
  public String criar(@Valid Cidade cidade, BindingResult bindingResult, Model memoria) {

    if (bindingResult.hasErrors()) {
      bindingResult
              .getFieldErrors()
              .forEach(error ->
                      memoria.addAttribute(
                              error.getField(), error.getDefaultMessage()));

      memoria.addAttribute("nomeCidadeInformado", cidade.getNomeCidade());
      memoria.addAttribute("nomeEstadoInformado", cidade.getNomeEstado());
      memoria.addAttribute("listaCidades", cidades);

      return "/crud";
    } else {
      cidades.add(cidade);
      return "redirect:/";
    }
  }

  @GetMapping("/excluir")
  public String excluir(
          @RequestParam(value = "nome") String nomeCidade,
          @RequestParam(value = "estado") String nomeEstado) {

    cidades.removeIf(cidade -> cidade.getNomeCidade().equals(nomeCidade) && cidade.getNomeEstado().equals(nomeEstado));
    return "redirect:/";
  }

  @GetMapping("/preparaAlterar")
  public String preparaAlterar(
          @RequestParam(value = "nome") String nomeCidade,
          @RequestParam(value = "estado") String nomeEstado,
          Model memoria
  ) {

    var cidade = cidades
            .stream()
            .filter(cidadeAux -> cidadeAux.getNomeCidade().equals(nomeCidade) && cidadeAux.getNomeEstado().equals(nomeEstado))
            .findAny();

    if (cidade.isPresent()) {
      memoria.addAttribute("cidadeAtual", cidade.get());
      memoria.addAttribute("listaCidades", cidades);
    }

    return "/crud";
  }

  @PostMapping("/alterar")
  public String alterar(
          @RequestParam String nomeCidadeAtual,
          @RequestParam String nomeEstadoAtual,
          Cidade cidade,
          BindingResult bindingResult,
          Model memoria
  ) {

    cidades
            .removeIf(cidadeAux -> cidadeAux.getNomeCidade().equals(nomeCidadeAtual) && cidadeAux.getNomeEstado().equals(nomeEstadoAtual));

    return criar(cidade, bindingResult, memoria);
  }
}
