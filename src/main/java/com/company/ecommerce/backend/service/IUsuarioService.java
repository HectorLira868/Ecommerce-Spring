package com.company.ecommerce.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.company.ecommerce.backend.model.Usuario;

@Service
public interface IUsuarioService {
	Optional<Usuario> findById(Integer id);

}
