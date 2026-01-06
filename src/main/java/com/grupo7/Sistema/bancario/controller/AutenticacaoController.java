package com.grupo7.Sistema.bancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo7.Sistema.bancario.infra.DadosTokenJwt;
import com.grupo7.Sistema.bancario.infra.TokenService;
import com.grupo7.Sistema.bancario.usuario.DadosAutenticacao;
import com.grupo7.Sistema.bancario.usuario.Usuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Login")
public class AutenticacaoController {
    
    @Autowired
    private AuthenticationManager manage;

    @Autowired
    private TokenService tokenService;

    @PostMapping 
    public ResponseEntity<?> login(@RequestBody @Valid DadosAutenticacao dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var autenticacao = manage.authenticate(token);

        var tokenJwt = tokenService.gerarToken((Usuario) autenticacao.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJwt(tokenJwt));

    }
}
