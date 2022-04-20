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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;

import com.vtv.vtv.entidades.Automovil;
import com.vtv.vtv.entidades.Propietario;
import com.vtv.vtv.servicios.AutomovilService;
import com.vtv.vtv.servicios.MarcaService;
import com.vtv.vtv.servicios.ModeloService;
import com.vtv.vtv.servicios.PropietarioService;
import com.vtv.vtv.servicios.TipoService;

@Controller
@RequestMapping("/automovil")
public class AutomovilController {

	@Autowired
	private AutomovilService automovilService;
	
	@Autowired
	private PropietarioService propietarioService;
	
	@Autowired
	private TipoService tipoService;
	
	@Autowired
	private ModeloService modeloService;
	
	@Autowired
	private MarcaService marcaService;
	
	@GetMapping("/agregar")
	public String agregarAutomovil(Automovil automovil, Model model) {
		var marcas = marcaService.listarMarcas();
		model.addAttribute("marcas", marcas);
		var modelos = modeloService.listarModelos();
		model.addAttribute("modelos", modelos);
		var tipos = tipoService.listarTipos();
		model.addAttribute("tipos", tipos);
		return "agregarAutomovil";
	}
	
	@ResponseBody
	@GetMapping("/json/modelos")
	public String jsonModelosPorMarca(@RequestParam("idMarca") int idMarca) {
		Gson gson = new Gson();
		return gson.toJson(modeloService.listarModelosPorMarca(idMarca));
	}
	
	@PostMapping("/guardar")
	public String guardarAutomovil(@Valid Automovil automovil, BindingResult errores, Model model) {
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
			var marcas = marcaService.listarMarcas();
			model.addAttribute("marcas", marcas);
			var modelos = modeloService.listarModelos();
			model.addAttribute("modelos", modelos);
			var tipos = tipoService.listarTipos();
			model.addAttribute("tipos", tipos);
			return "agregarAutomovil";
		}
		automovilService.guardar(automovil);
		return "redirect:/";
	}
	
	@GetMapping("/editar/{dominio}")
	public String editarAutomovil(Automovil automovil, Model model) {
		automovil = automovilService.encontrarAutomovil(automovil);
		model.addAttribute("automovil", automovil);
		return "modificarAutomovil";
	}
	
	@PostMapping("/modificar")
	public String modificarAutomovil(@Valid Automovil automovil, BindingResult errores) {
		if(errores.hasErrors()) {
			return "modificarAutomovil";
		}
		automovilService.guardar(automovil);
		return "redirect:/";
	}
	
	@GetMapping("/eliminar/{dominio}")
	public String eliminarAutomovil(Automovil automovil, Model model) {
		automovil = automovilService.encontrarAutomovil(automovil);
		model.addAttribute("automovil", automovil);
		return "borrarAutomovil";
	}
	
	@PostMapping("/borrar")
	public String borrarAutomovil(Automovil automovil) {
		automovilService.eliminar(automovil);
		return "redirect:/";
	}
}
