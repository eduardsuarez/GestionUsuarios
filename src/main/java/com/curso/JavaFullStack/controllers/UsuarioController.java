package com.curso.JavaFullStack.controllers;

import com.curso.JavaFullStack.dao.UsuarioDao;
import com.curso.JavaFullStack.models.Usuario;
import com.curso.JavaFullStack.utils.JwtUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "api/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Eduard");
        usuario.setApellido("Suárez");
        usuario.setEmail("suareze205@gmail.com");
        usuario.setTelefono("322741270");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> findAllUsuarios(@RequestHeader(value = "Authorization") String token) {

        if (!validarToken(token)) {
            return null;
        }
        return usuarioDao.findAllUsuarios();

    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;

    }

    @RequestMapping(value = "api/save", method = RequestMethod.POST)
    public void saveUsuarios(@RequestBody Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.save(usuario);

    }

    @RequestMapping(value = "api/update")
    public Usuario UpdateUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Eduard");
        usuario.setApellido("Suárez");
        usuario.setEmail("suareze205@gmail.com");
        usuario.setTelefono("322741270");
        return usuario;
    }

    @RequestMapping(value = "api/delete/{id}", method = RequestMethod.DELETE)
    public void deleteUsuario(@RequestHeader(value = "Authorization") String token, @PathVariable("id") Long id) {

        if (!validarToken(token)) {
            return;
        }
        usuarioDao.delete(id);

    }


}
