package com.vtv.vtv.controladores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vtv.vtv.entidades.Automovil;
import com.vtv.vtv.entidades.Inspeccion;
import com.vtv.vtv.entidades.Inspector;
import com.vtv.vtv.servicios.AutomovilService;
import com.vtv.vtv.servicios.InspeccionService;
import com.vtv.vtv.servicios.InspectorService;

@Controller
@RequestMapping("/inspeccion")
public class InspeccionController {
	
	@Autowired
	private InspeccionService inspeccionService;
	
	@Autowired
	private InspectorService inspectorService;
	
	@Autowired
	private AutomovilService automovilService;

	@GetMapping("/agregar")
	public String agregarInspeccion(Inspeccion inspeccion) {
		return "agregarInspeccion";
	}
	
	@PostMapping("/guardar")
	public String guardarInspeccion(@Valid Inspeccion inspeccion, BindingResult errores) {
		String dni = String.valueOf(inspeccion.getDniInspector());
		String dominio = inspeccion.getDominio();
		int dniAux;
		Inspector inspector = new Inspector();
		inspector.setDni(dni);
		Automovil automovil = new Automovil();
		automovil.setDominio(dominio);
		var i = inspectorService.encontrarInspector(inspector);
		var a = automovilService.encontrarAutomovil(automovil);
		if(i == null) {
			FieldError error = new FieldError("inspeccion", "dniInspector", "No existe ningún inspector con ese DNI.");
			errores.addError(error);
		}
		if(dni.length()>8 || dni.length()<7) {
			FieldError error = new FieldError("inspeccion", "dniInspector", "Los caracteres del dni deben ser 7 como minimo y 8 como maximo.");
			errores.addError(error);
		}
		try {
			dniAux = Integer.parseInt(dni);
			if (dniAux == 0) {
				FieldError error = new FieldError("inspeccion", "dniInspector", "El dni de la persona no puede ser 0.");
				errores.addError(error);
			}
		}
		catch (NumberFormatException e) {
			FieldError error = new FieldError("inspeccion", "dniInspector", "Los caracteres del dni deben ser numeros.");
			errores.addError(error);
		}
		
		if(a == null) {
			FieldError error = new FieldError("inspeccion", "dominio", "No existe ningún automóvil con ese dominio.");
			errores.addError(error);
		}
		if(!(dominio.length()==7 || dominio.length()==9)) {
			FieldError error = new FieldError("inspeccion", "dominio", "El dominio solo puede tener 7 o 9 caracteres.");
			errores.addError(error);
		}
		if(dominio.length() == 7) {
			Pattern pattern = Pattern.compile("[A-Z][A-Z][A-Z] [0-9][0-9][0-9]");
		    Matcher matcher = pattern.matcher(dominio);
		    if(!matcher.find()) {
		    	FieldError error = new FieldError("inspeccion", "dominio", "El dominio debe seguir el formato correcto. Ejemplo: AAA 111");
				errores.addError(error);
		    }
		}
		if(dominio.length() == 9) {
			Pattern pattern = Pattern.compile("[A-Z][A-Z] [0-9][0-9][0-9] [A-Z][A-Z]");
		    Matcher matcher = pattern.matcher(dominio);
		    if(!matcher.find()) {
		    	FieldError error = new FieldError("inspeccion", "dominio", "El dominio debe seguir el formato correcto. Ejemplo: AA 111 AA");
				errores.addError(error);
		    }
		}
		
		if(errores.hasErrors()) {
			return "agregarInspeccion";
		}
		inspeccionService.guardar(inspeccion);
		return "redirect:/";
	}
	
	@GetMapping("/editar/{numero}")
	public String editarInspeccion(Inspeccion inspeccion, Model model) {
		inspeccion = inspeccionService.encontrarInspeccion(inspeccion);
		model.addAttribute("inspeccion", inspeccion);
		return "modificarInspeccion";
	}
	
	@PostMapping("/modificar")
	public String modificarInspeccion(@Valid Inspeccion inspeccion, BindingResult errores) {
		String dni = String.valueOf(inspeccion.getDniInspector());
		int dniAux;
		Inspector inspector = new Inspector();
		inspector.setDni(dni);
		var i = inspectorService.encontrarInspector(inspector);
		if(i == null) {
			FieldError error = new FieldError("inspeccion", "dniInspector", "No existe ningún inspector con ese DNI.");
			errores.addError(error);
		}
		if(dni.length()>8 || dni.length()<7) {
			FieldError error = new FieldError("inspeccion", "dniInspector", "Los caracteres del dni deben ser 7 como minimo y 8 como maximo.");
			errores.addError(error);
		}
		try {
			dniAux = Integer.parseInt(dni);
			if (dniAux == 0) {
				FieldError error = new FieldError("inspeccion", "dniInspector", "El dni de la persona no puede ser 0.");
				errores.addError(error);
			}
		}
		catch (NumberFormatException e) {
			FieldError error = new FieldError("inspeccion", "dniInspector", "Los caracteres del dni deben ser numeros.");
			errores.addError(error);
		}
		
		if(errores.hasErrors()) {
			return "modificarInspeccion";
		}
		inspeccionService.guardar(inspeccion);
		return "redirect:/";
	}
	
	@GetMapping("/eliminar/{numero}")
	public String eliminarInspeccion(Inspeccion inspeccion, Model model) {
		inspeccion = inspeccionService.encontrarInspeccion(inspeccion);
		model.addAttribute("inspeccion", inspeccion);
		return "borrarInspeccion";
	}
	
	@PostMapping("/borrar")
	public String borrarInspeccion(Inspeccion inspeccion) {
		inspeccionService.eliminar(inspeccion);
		return "redirect:/";
	}
}
