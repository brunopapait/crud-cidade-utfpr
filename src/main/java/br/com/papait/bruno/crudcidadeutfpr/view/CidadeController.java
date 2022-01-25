package br.com.papait.bruno.crudcidadeutfpr.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class CidadeController {

  @GetMapping(value = "/")
  public String listar(Model memoria){
    Set<Cidade> cidades = Set.of(
            new Cidade("Araruna", "Paraná"),
            new Cidade("Aracaju", "Sergipe"),
            new Cidade("Vilhena", "Rondônia"),
            new Cidade("Manaus", "Amazonas")
    );

    memoria.addAttribute("listaCidades", cidades);
    return "/crud";
  }
}
