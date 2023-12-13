package com.company.ecommerce.backend.service;

import org.springframework.stereotype.Service;

import com.company.ecommerce.backend.model.Orden;

@Service
public interface IOrdenService {
	Orden save(Orden orden);

}
