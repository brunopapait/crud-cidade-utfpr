package br.com.papait.bruno.crudcidadeutfpr.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  Usuario findByNome(String name);
}
