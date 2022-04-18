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

import com.vtv.vtv.entidades.Automovil;
import com.vtv.vtv.entidades.Inspeccion;
import com.vtv.vtv.entidades.Inspector;
import com.vtv.vtv.entidades.Propietario;
import com.vtv.vtv.servicios.AutomovilService;
import com.vtv.vtv.servicios.InspeccionService;
import com.vtv.vtv.servicios.InspectorService;
import com.vtv.vtv.servicios.PropietarioService;
import com.vtv.vtv.servicios.TipoService;

@Controller
public class HomeController {
	
	@Autowired
	private PropietarioService propietarioService;
	
	@Autowired
	private InspectorService inspectorService;
	
	@Autowired
	private AutomovilService automovilService;
	
	@Autowired
	private InspeccionService inspeccionService;
	
	@Autowired
	private TipoService tipoService;

	@GetMapping("/")
	public String inicio(Model model) {
		var propietarios = propietarioService.listarPropietarios();
		model.addAttribute("propietarios", propietarios);
		var inspectores = inspectorService.listarInspectores();
		model.addAttribute("inspectores", inspectores);
		var autos = automovilService.listarAutomoviles();
		model.addAttribute("autos", autos);
		var inspecciones = inspeccionService.listarInspecciones();
		model.addAttribute("inspecciones", inspecciones);
		var tipos = tipoService.listarTipos();
		model.addAttribute("tipos", tipos);
		return "index";
	}
	
	@GetMapping("/propietario/agregar")
	public String agregarPropietario(Propietario propietario) {
		return "agregarPropietario";
	}
	
	@PostMapping("/propietario/guardar")
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
	
	@GetMapping("/propietario/editar/{dni}")
	public String editarPropietario(Propietario propietario, Model model) {
		propietario = propietarioService.encontrarPropietario(propietario);
		model.addAttribute("propietario", propietario);
		return "modificarPropietario";
	}
	
	@PostMapping("/propietario/modificar")
	public String modificarPropietario(@Valid Propietario propietario, BindingResult errores) {
		if(errores.hasErrors()) {
			return "modificarPropietario";
		}
		propietarioService.guardar(propietario);
		return "redirect:/";
	}
	
	@GetMapping("/propietario/eliminar/{dni}")
	public String eliminarPropietario(Propietario propietario, Model model) {
		propietario = propietarioService.encontrarPropietario(propietario);
		model.addAttribute("propietario", propietario);
		return "borrarPropietario";
	}
	
	@PostMapping("/propietario/borrar")
	public String borrarPropietario(Propietario propietario) {
		propietarioService.eliminar(propietario);
		return "redirect:/";
	}
	
	@GetMapping("/inspector/agregar")
	public String agregarInspector(Inspector inspector) {
		return "agregarInspector";
	}
	
	@PostMapping("/inspector/guardar")
	public String guardarInspector(@Valid Inspector inspector, BindingResult errores) {
		String dni = String.valueOf(inspector.getDni());
		String numero = String.valueOf(inspector.getNumero());
		String salario = String.valueOf(inspector.getSalario());
		int dniAux;
		var p = inspectorService.encontrarInspector(inspector);
		if(p != null) {
			FieldError error = new FieldError("inspector", "dni", "Ya existe un inspector con ese DNI.");
			errores.addError(error);
		}
		if(dni.length()>8 || dni.length()<7) {
			FieldError error = new FieldError("inspector", "dni", "Los caracteres del dni deben ser 7 como minimo y 8 como maximo.");
			errores.addError(error);
		}
		try {
			dniAux = Integer.parseInt(dni);
			if (dniAux == 0) {
				FieldError error = new FieldError("inspector", "dni", "El dni de la persona no puede ser 0.");
				errores.addError(error);
			}
		}
		catch (NumberFormatException e) {
			FieldError error = new FieldError("inspector", "dni", "Los caracteres del dni deben ser numeros.");
			errores.addError(error);
		}
		
		if (inspector.getNumero() == 0) {
			FieldError error = new FieldError("inspector", "numero", "El numero del inspector no puede ser 0.");
			errores.addError(error);
		}
		if(numero.length() != 3) {
			FieldError error = new FieldError("inspector", "numero", "El numero del inspector debe ser de 3 digitos.");
			errores.addError(error);
		}
		if(numero.startsWith("0")) {
			FieldError error = new FieldError("inspector", "numero", "El numero del inspector no puede comenzar con 0.");
			errores.addError(error);
		}
		
		if (inspector.getSalario() == 0) {
			FieldError error = new FieldError("inspector", "salario", "El salario del inspector no puede ser 0.");
			errores.addError(error);
		}
		if(salario.length()>6 || salario.length()<5) {
			FieldError error = new FieldError("inspector", "salario", "El salario debe ser de 5 o 6 cifras.");
			errores.addError(error);
		}
		
		if(errores.hasErrors()) {
			return "agregarInspector";
		}
		inspectorService.guardar(inspector);
		return "redirect:/";
	}
	
	@GetMapping("/inspector/editar/{dni}")
	public String editarInspector(Inspector inspector, Model model) {
		inspector = inspectorService.encontrarInspector(inspector);
		model.addAttribute("inspector", inspector);
		return "modificarInspector";
	}
	
	@PostMapping("/inspector/modificar")
	public String modificarInspector(@Valid Inspector inspector, BindingResult errores) {
		String salario = String.valueOf(inspector.getSalario());
		if (inspector.getSalario() == 0) {
			FieldError error = new FieldError("inspector", "salario", "El salario del inspector no puede ser 0.");
			errores.addError(error);
		}
		if(salario.length()>6 || salario.length()<5) {
			FieldError error = new FieldError("inspector", "salario", "El salario debe ser de 5 o 6 cifras.");
			errores.addError(error);
		}
		
		if(errores.hasErrors()) {
			return "modificarInspector";
		}
		inspectorService.guardar(inspector);
		return "redirect:/";
	}
	
	@GetMapping("/inspector/eliminar/{dni}")
	public String eliminarInspector(Inspector inspector, Model model) {
		inspector = inspectorService.encontrarInspector(inspector);
		model.addAttribute("inspector", inspector);
		return "borrarInspector";
	}
	
	@PostMapping("/inspector/borrar")
	public String borrarInspector(Inspector inspector) {
		inspectorService.eliminar(inspector);
		return "redirect:/";
	}
	
	@GetMapping("/automovil/agregar")
	public String agregarAutomovil(Automovil automovil) {
		return "agregarAutomovil";
	}
	
	@PostMapping("/automovil/guardar")
	public String guardarAutomovil(@Valid Automovil automovil, BindingResult errores) {
		String dni = String.valueOf(automovil.getDniPropietario());
		String dominio = automovil.getDominio();
		int dniAux;
		Propietario propietario = new Propietario();
		propietario.setDni(dni);
		var p = propietarioService.encontrarPropietario(propietario);
		var a = automovilService.encontrarAutomovil(automovil);
		if(p == null) {
			FieldError error = new FieldError("automovil", "dniPropietario", "No existe ningún propietario con ese DNI.");
			errores.addError(error);
		}
		if(dni.length()>8 || dni.length()<7) {
			FieldError error = new FieldError("automovil", "dniPropietario", "Los caracteres del dni deben ser 7 como minimo y 8 como maximo.");
			errores.addError(error);
		}
		try {
			dniAux = Integer.parseInt(dni);
			if (dniAux == 0) {
				FieldError error = new FieldError("automovil", "dniPropietario", "El dni de la persona no puede ser 0.");
				errores.addError(error);
			}
		}
		catch (NumberFormatException e) {
			FieldError error = new FieldError("automovil", "dniPropietario", "Los caracteres del dni deben ser numeros.");
			errores.addError(error);
		}
		
		if(a != null) {
			FieldError error = new FieldError("automovil", "dominio", "Ya existe un automóvil con ese dominio.");
			errores.addError(error);
		}
		if(!(dominio.length()==7 || dominio.length()==9)) {
			FieldError error = new FieldError("automovil", "dominio", "El dominio solo puede tener 7 o 9 caracteres.");
			errores.addError(error);
		}
		if(dominio.length() == 7) {
			Pattern pattern = Pattern.compile("[A-Z][A-Z][A-Z] [0-9][0-9][0-9]");
		    Matcher matcher = pattern.matcher(dominio);
		    if(!matcher.find()) {
		    	FieldError error = new FieldError("automovil", "dominio", "El dominio debe seguir el formato correcto. Ejemplo: AAA 111");
				errores.addError(error);
		    }
		}
		if(dominio.length() == 9) {
			Pattern pattern = Pattern.compile("[A-Z][A-Z] [0-9][0-9][0-9] [A-Z][A-Z]");
		    Matcher matcher = pattern.matcher(dominio);
		    if(!matcher.find()) {
		    	FieldError error = new FieldError("automovil", "dominio", "El dominio debe seguir el formato correcto. Ejemplo: AA 111 AA");
				errores.addError(error);
		    }
		}
		
		if(errores.hasErrors()) {
			return "agregarAutomovil";
		}
		automovilService.guardar(automovil);
		return "redirect:/";
	}
	
	@GetMapping("/automovil/editar/{dominio}")
	public String editarAutomovil(Automovil automovil, Model model) {
		automovil = automovilService.encontrarAutomovil(automovil);
		model.addAttribute("automovil", automovil);
		return "modificarAutomovil";
	}
	
	@PostMapping("/automovil/modificar")
	public String modificarAutomovil(@Valid Automovil automovil, BindingResult errores) {
		if(errores.hasErrors()) {
			return "modificarAutomovil";
		}
		automovilService.guardar(automovil);
		return "redirect:/";
	}
	
	@GetMapping("/automovil/eliminar/{dominio}")
	public String eliminarAutomovil(Automovil automovil, Model model) {
		automovil = automovilService.encontrarAutomovil(automovil);
		model.addAttribute("automovil", automovil);
		return "borrarAutomovil";
	}
	
	@PostMapping("/automovil/borrar")
	public String borrarAutomovil(Automovil automovil) {
		automovilService.eliminar(automovil);
		return "redirect:/";
	}
	
	@GetMapping("/inspeccion/agregar")
	public String agregarInspeccion(Inspeccion inspeccion) {
		return "agregarInspeccion";
	}
	
	@PostMapping("/inspeccion/guardar")
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
	
	@GetMapping("/inspeccion/editar/{numero}")
	public String editarInspeccion(Inspeccion inspeccion, Model model) {
		inspeccion = inspeccionService.encontrarInspeccion(inspeccion);
		model.addAttribute("inspeccion", inspeccion);
		return "modificarInspeccion";
	}
	
	@PostMapping("/inspeccion/modificar")
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
	
	@GetMapping("/inspeccion/eliminar/{numero}")
	public String eliminarInspeccion(Inspeccion inspeccion, Model model) {
		inspeccion = inspeccionService.encontrarInspeccion(inspeccion);
		model.addAttribute("inspeccion", inspeccion);
		return "borrarInspeccion";
	}
	
	@PostMapping("/inspeccion/borrar")
	public String borrarInspeccion(Inspeccion inspeccion) {
		inspeccionService.eliminar(inspeccion);
		return "redirect:/";
	}
}
