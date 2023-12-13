package com.company.ecommerce.backend.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.ecommerce.backend.model.DetalleOrden;
import com.company.ecommerce.backend.repository.IDetalleOrdenRepository;

public class DetalleOrdenServiceImpl implements IDetalleOrdenService {

	@Autowired
	private IDetalleOrdenRepository detalleOrdenRepository;
	
	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenRepository.save(detalleOrden);
	}

}
