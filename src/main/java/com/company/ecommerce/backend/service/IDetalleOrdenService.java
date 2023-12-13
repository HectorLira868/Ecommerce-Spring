package com.company.ecommerce.backend.service;

import org.springframework.stereotype.Service;

import com.company.ecommerce.backend.model.DetalleOrden;

@Service
public interface IDetalleOrdenService {
	DetalleOrden save(DetalleOrden detalleOrden);

}
