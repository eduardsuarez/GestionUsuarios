package com.curso.JavaFullStack.dao;

import com.curso.JavaFullStack.models.Usuario;

import java.util.List;

public interface UsuarioDao {

    List<Usuario> findAllUsuarios();

    void delete(Long id);

    void save(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales (Usuario usuario);
}
