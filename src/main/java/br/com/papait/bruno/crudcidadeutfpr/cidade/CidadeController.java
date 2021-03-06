package br.com.papait.bruno.crudcidadeutfpr.cidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Controller
public class CidadeController {

  @Autowired
  private CidadeRepository cidadeRepository;


  @GetMapping(value = "/")
  public String listar(Model memoria, Principal principal, HttpSession httpSession, HttpServletResponse httpServletResponse) {

    httpServletResponse.addCookie(new Cookie("listar", LocalDateTime.now().toString()));

    memoria.addAttribute("listaCidades", cidadeRepository
            .findAll()
            .stream()
            .map(cidade -> new Cidade(cidade.getNomeCidade(), cidade.getNomeEstado()))
            .collect(Collectors.toUnmodifiableList()));

    httpSession.setAttribute("nomeUsuarioLogado", principal.getName());

    return "/crud";
  }

  @PostMapping("/criar")
  public String criar(@Valid Cidade cidade, BindingResult bindingResult, Model memoria, HttpServletResponse httpServletResponse) {
    httpServletResponse.addCookie(new Cookie("criar", LocalDateTime.now().toString()));
    if (bindingResult.hasErrors()) {
      bindingResult
              .getFieldErrors()
              .forEach(error ->
                      memoria.addAttribute(
                              error.getField(), error.getDefaultMessage()));

      memoria.addAttribute("nomeCidadeInformado", cidade.getNomeCidade());
      memoria.addAttribute("nomeEstadoInformado", cidade.getNomeEstado());
      memoria.addAttribute("listaCidades", cidadeRepository
              .findAll()
              .stream()
              .map(cidadeP -> new Cidade(cidadeP.getNomeCidade(), cidadeP.getNomeEstado()))
              .collect(Collectors.toUnmodifiableList()));

      return "/crud";
    } else {
      cidadeRepository.save(cidade.clonar());
      return "redirect:/";
    }
  }

  @GetMapping("/excluir")
  public String excluir(
          @RequestParam(value = "nome") String nomeCidade,
          @RequestParam(value = "estado") String nomeEstado,
          HttpServletResponse httpServletResponse) {
    httpServletResponse.addCookie(new Cookie("excluir", LocalDateTime.now().toString()));
    var cidadeEstadoEncontrado = cidadeRepository.findByNomeCidadeAndNomeEstado(nomeCidade, nomeEstado);
    cidadeEstadoEncontrado.ifPresent(cidadeRepository::delete);

    return "redirect:/";
  }

  @GetMapping("/preparaAlterar")
  public String preparaAlterar(
          @RequestParam(value = "nome") String nomeCidade,
          @RequestParam(value = "estado") String nomeEstado,
          Model memoria
  ) {

    var cidadeAtual = cidadeRepository.findByNomeCidadeAndNomeEstado(nomeCidade, nomeEstado);
    cidadeAtual.ifPresent(cidadeEncontrada -> {
      memoria.addAttribute("cidadeAtual", cidadeEncontrada);
      memoria.addAttribute("listaCidades", cidadeRepository.findAll());
    });

    return "/crud";
  }

  @PostMapping("/alterar")
  public String alterar(
          @RequestParam String nomeCidadeAtual,
          @RequestParam String nomeEstadoAtual,
          Cidade cidade,
          BindingResult bindingResult,
          Model memoria,
          HttpServletResponse httpServletResponse
  ) {
    httpServletResponse.addCookie(new Cookie("alterar", LocalDateTime.now().toString()));
    var cidadeAtual = cidadeRepository.findByNomeCidadeAndNomeEstado(nomeCidadeAtual, nomeEstadoAtual);

    if (cidadeAtual.isPresent()) {
      var cidadeEncontrada = cidadeAtual.get();
      cidadeEncontrada.setNomeCidade(cidade.getNomeCidade());
      cidadeEncontrada.setNomeEstado(cidade.getNomeEstado());

      cidadeRepository.saveAndFlush(cidadeEncontrada);
    }

    return "redirect:/";
  }

  @GetMapping("/mostrarValorCookie")
  @ResponseBody
  public String mostrarCookie(@CookieValue String listar) {
    return "Ultimo acesso ao m??todo " + listar;
  }

}
