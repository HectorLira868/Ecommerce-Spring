package com.company.ecommerce.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.company.ecommerce.backend.model.Orden;
import com.company.ecommerce.backend.model.Usuario;

@Service
public interface IOrdenService {
	List<Orden> findAll();
	Orden save(Orden orden);
	String generarNumeroOrden();
	List<Orden> findByUsuario(Usuario usuario);
	Optional<Orden> findById(Integer id);
}
