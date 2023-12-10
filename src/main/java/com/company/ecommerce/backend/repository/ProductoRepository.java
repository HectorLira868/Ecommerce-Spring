package com.company.ecommerce.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.ecommerce.backend.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
