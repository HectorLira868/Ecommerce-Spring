package com.company.ecommerce.backend.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.company.ecommerce.backend.model.Producto;
import com.company.ecommerce.backend.model.Usuario;
import com.company.ecommerce.backend.service.IProductoService;
import com.company.ecommerce.backend.service.IUsuarioService;
import com.company.ecommerce.backend.service.UploadFileService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private UploadFileService upload;
	
	@GetMapping("")
	public String show(Model model) {
		model.addAttribute("productos", productoService.findAll());
		return "productos/show";
	}
	
	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}
	
	@PostMapping("/save")
	public String save(Producto producto,@RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		LOGGER.info("Este es el objeto producto {}", producto);

		Usuario user = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		producto.setUsuario(user);

		
		//Imagen
		if (producto.getId() == null) {	//Cuando se crea el producto
			String nombreImagen = upload.saveImages(file);
			producto.setImagen(nombreImagen);
		}else {
			if (file.isEmpty()) { //Es cuando editamos el producto pero no cambiamos la imagen
				Producto p = new Producto();
				p = productoService.get(producto.getId()).get(); 
				producto.setImagen(p.getImagen());
			}else {
				String nombreImagen = upload.saveImages(file);
				producto.setImagen(nombreImagen);
			}
		}
		
		productoService.save(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = productoService.get(id);
		producto = optionalProducto.get();

		LOGGER.info("Producto buscado: {}", producto);
		model.addAttribute("producto", producto);
		return "productos/edit";
	}
	
	@PostMapping("/update")
	public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
		Producto p = new Producto();
		p = productoService.get(producto.getId()).get(); 
				
		if (file.isEmpty()) { //Es cuando editamos el producto pero no cambiamos la imagen
			producto.setImagen(p.getImagen());
		}else { //Cuando se edita tambien la imagen	
			//Eliminar cuando no sea la imagen por defecto
			if (p.getImagen().equals("default.jpg")) {
				upload.delete(p.getImagen());
			}
			String nombreImagen = upload.saveImages(file);
			producto.setImagen(nombreImagen);
		}
		producto.setUsuario(p.getUsuario());
		productoService.update(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		Producto p = new Producto();
		p = productoService.get(id).get();
		
		//Eliminar cuando no sea la imagen por defecto
		if (p.getImagen().equals("default.jpg")) {
			upload.delete(p.getImagen());
		}
		
		productoService.delete(id);
		return "redirect:/productos";
	}

}
