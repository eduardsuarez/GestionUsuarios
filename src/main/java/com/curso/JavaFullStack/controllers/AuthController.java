package com.curso.JavaFullStack.controllers;

import com.curso.JavaFullStack.dao.UsuarioDao;
import com.curso.JavaFullStack.models.Usuario;
import com.curso.JavaFullStack.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){

        Usuario usuarioLogueado = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
       if (usuarioLogueado != null){

           String tokenJwt =  jwtUtil.create(String.valueOf(usuario.getId()), usuarioLogueado.getEmail());
           return tokenJwt;
       }
       return "FAIL";
    }
}
