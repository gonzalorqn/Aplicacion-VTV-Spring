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
import com.vtv.vtv.servicios.MarcaService;
import com.vtv.vtv.servicios.ModeloService;
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
}
