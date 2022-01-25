package br.com.papait.bruno.crudcidadeutfpr.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CidadeController {

  @GetMapping(value = "/")
  public String listar(){
    return "crud.html";
  }
}
