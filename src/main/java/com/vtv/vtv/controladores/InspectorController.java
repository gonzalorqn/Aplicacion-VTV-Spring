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

import com.vtv.vtv.entidades.Inspector;
import com.vtv.vtv.servicios.InspectorService;

@Controller
@RequestMapping("/inspector")
public class InspectorController {

	@Autowired
	private InspectorService inspectorService;
	
	@GetMapping("/")
	public String inicio(Model model) {
		var inspectores = inspectorService.listarInspectores();
		model.addAttribute("inspectores", inspectores);
		return "inspectores/inspectores";
	}
	
	@GetMapping("/agregar")
	public String agregarInspector(Inspector inspector) {
		return "inspectores/agregarInspector";
	}
	
	@PostMapping("/guardar")
	public String guardarInspector(@Valid Inspector inspector, BindingResult errores) {
		String dni = String.valueOf(inspector.getDni());
		String numero = String.valueOf(inspector.getNumero());
		String salario = String.valueOf(inspector.getSalario());
		int dniAux;
		var p = inspectorService.encontrarInspector(inspector);
		if(dni.length()>8 || dni.length()<7) {
			FieldError error = new FieldError("inspector", "dni", "Los caracteres del dni deben ser 7 como minimo y 8 como maximo.");
			errores.addError(error);
		}
		else {
			try {
				dniAux = Integer.parseInt(dni);
				if (dniAux == 0) {
					FieldError error = new FieldError("inspector", "dni", "El dni de la persona no puede ser 0.");
					errores.addError(error);
				}
				else if(p != null) {
					FieldError error = new FieldError("inspector", "dni", "Ya existe un inspector con ese DNI.");
					errores.addError(error);
				}
			}
			catch (NumberFormatException e) {
				FieldError error = new FieldError("inspector", "dni", "Los caracteres del dni deben ser numeros.");
				errores.addError(error);
			}
		}
		
		if(numero.length() != 3) {
			FieldError error = new FieldError("inspector", "numero", "El numero del inspector debe ser de 3 digitos.");
			errores.addError(error);
		}
		/*else if (inspector.getNumero() == 0) {
			FieldError error = new FieldError("inspector", "numero", "El numero del inspector no puede ser 0.");
			errores.addError(error);
		}
		else if(numero.startsWith("0")) {
			FieldError error = new FieldError("inspector", "numero", "El numero del inspector no puede comenzar con 0.");
			errores.addError(error);
		}*/
		
		if(salario.length()>6 || salario.length()<5) {
			FieldError error = new FieldError("inspector", "salario", "El salario debe ser de 5 o 6 cifras.");
			errores.addError(error);
		}
		/*if (inspector.getSalario() == 0) {
			FieldError error = new FieldError("inspector", "salario", "El salario del inspector no puede ser 0.");
			errores.addError(error);
		}*/
		
		if(errores.hasErrors()) {
			return "inspectores/agregarInspector";
		}
		inspectorService.guardar(inspector);
		return "redirect:/inspector/";
	}
	
	@GetMapping("/editar/{dni}")
	public String editarInspector(Inspector inspector, Model model) {
		inspector = inspectorService.encontrarInspector(inspector);
		model.addAttribute("inspector", inspector);
		return "inspectores/modificarInspector";
	}
	
	@PostMapping("/modificar")
	public String modificarInspector(@Valid Inspector inspector, BindingResult errores) {
		String salario = String.valueOf(inspector.getSalario());
		if(salario.length()>6 || salario.length()<5) {
			FieldError error = new FieldError("inspector", "salario", "El salario debe ser de 5 o 6 cifras.");
			errores.addError(error);
		}
		/*if (inspector.getSalario() == 0) {
			FieldError error = new FieldError("inspector", "salario", "El salario del inspector no puede ser 0.");
			errores.addError(error);
		}*/
		
		if(errores.hasErrors()) {
			return "inspectores/modificarInspector";
		}
		inspectorService.guardar(inspector);
		return "redirect:/inspector/";
	}
	
	@GetMapping("/eliminar/{dni}")
	public String eliminarInspector(Inspector inspector, Model model) {
		inspector = inspectorService.encontrarInspector(inspector);
		model.addAttribute("inspector", inspector);
		return "inspectores/borrarInspector";
	}
	
	@PostMapping("/borrar")
	public String borrarInspector(Inspector inspector) {
		inspectorService.eliminar(inspector);
		return "redirect:/inspector/";
	}
}
