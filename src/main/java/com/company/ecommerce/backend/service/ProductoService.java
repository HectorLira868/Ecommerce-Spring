package com.company.ecommerce.backend.service;

import java.util.Optional;

import com.company.ecommerce.backend.model.Producto;

public interface ProductoService {
	public Producto	save(Producto producto);
	public Optional<Producto> get(Integer id);
	public void	update(Producto producto);
	public void	delete(Integer id);

}
