package com.company.ecommerce.backend.service;

import java.util.List;
import java.util.Optional;

import com.company.ecommerce.backend.model.Producto;

public interface IProductoService {
	public Producto	save(Producto producto);
	public Optional<Producto> get(Integer id);
	public void	update(Producto producto);
	public void	delete(Integer id);

	List<Producto> findAll();
}
