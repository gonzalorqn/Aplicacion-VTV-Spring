package com.vtv.vtv.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vtv.vtv.entidades.Propietario;
import com.vtv.vtv.servicios.PropietarioService;

@Controller
@RequestMapping("/propietario")
public class PropietarioController {
	
	@Autowired
	private PropietarioService propietarioService;

	@GetMapping("/agregar")
	public String agregarPropietario(Propietario propietario) {
		return "agregarPropietario";
	}
	
	@PostMapping("/guardar")
	public String guardarPropietario(@Valid Propietario propietario, BindingResult errores) {
		String dni = String.valueOf(propietario.getDni());
		int dniAux;
		var p = propietarioService.encontrarPropietario(propietario);
		if(p != null) {
			FieldError error = new FieldError("propietario", "dni", "Ya existe un propietario con ese DNI.");
			errores.addError(error);
		}
		if(dni.length()>8 || dni.length()<7) {
			FieldError error = new FieldError("propietario", "dni", "Los caracteres del dni deben ser 7 como minimo y 8 como maximo.");
			errores.addError(error);
		}
		try {
			dniAux = Integer.parseInt(dni);
			if (dniAux == 0) {
				FieldError error = new FieldError("propietario", "dni", "El dni de la persona no puede ser 0.");
				errores.addError(error);
			}
		}
		catch (NumberFormatException e) {
			FieldError error = new FieldError("propietario", "dni", "Los caracteres del dni deben ser numeros.");
			errores.addError(error);
		}
		
		if(errores.hasErrors()) {
			return "agregarPropietario";
		}
		propietarioService.guardar(propietario);
		return "redirect:/";
	}
	
	@GetMapping("/editar/{dni}")
	public String editarPropietario(Propietario propietario, Model model) {
		propietario = propietarioService.encontrarPropietario(propietario);
		model.addAttribute("propietario", propietario);
		return "modificarPropietario";
	}
	
	@PostMapping("/modificar")
	public String modificarPropietario(@Valid Propietario propietario, BindingResult errores) {
		if(errores.hasErrors()) {
			return "modificarPropietario";
		}
		propietarioService.guardar(propietario);
		return "redirect:/";
	}
	
	@GetMapping("/eliminar/{dni}")
	public String eliminarPropietario(Propietario propietario, Model model) {
		propietario = propietarioService.encontrarPropietario(propietario);
		model.addAttribute("propietario", propietario);
		return "borrarPropietario";
	}
	
	@PostMapping("/borrar")
	public String borrarPropietario(Propietario propietario) {
		propietarioService.eliminar(propietario);
		return "redirect:/";
	}
}
