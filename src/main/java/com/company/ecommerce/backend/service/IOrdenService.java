package com.company.ecommerce.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.ecommerce.backend.model.Orden;

@Service
public interface IOrdenService {
	List<Orden> findAll();
	Orden save(Orden orden);
	String generarNumeroOrden();
}
