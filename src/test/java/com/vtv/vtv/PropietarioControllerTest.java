package com.vtv.vtv;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.vtv.vtv.controladores.PropietarioController;
import com.vtv.vtv.entidades.Propietario;
import com.vtv.vtv.servicios.PropietarioService;


@WebMvcTest(PropietarioController.class)
class PropietarioControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PropietarioService propietarioService;
	
	private static final Propietario PROPIETARIO_1 = new Propietario("nombre1", "apellido1", "1234567", false);
	private static final Propietario PROPIETARIO_2 = new Propietario("nombre2", "apellido2", "1234568", true);
	private static final Propietario PROPIETARIO_3 = new Propietario("nombre3", "apellido3", "1234569", false);
	
	@Test
	void test() throws Exception {
		when(propietarioService.encontrarPropietario(PROPIETARIO_1.getDni())).thenReturn(PROPIETARIO_1);
		
		//mvc.perform(get("/propietario/" + PROPIETARIO_1.getDni()).contentType(MediaType.APPLICATION_JSON))
		//	.andExpect(status().isOk());
			//.andExpect(content().contentType(MediaType.APPLICATION_JSON));
			//.andExpect(jsonPath("$.nombre").value("nombre1"))
			//.andExpect(jsonPath("$.apellido").value("apellido1"));
	}
	
	@Test
	void listarPropietariosTest() throws Exception {
		List<Propietario> propietarios = new ArrayList<>();
		propietarios.add(PROPIETARIO_1);
		propietarios.add(PROPIETARIO_2);
		propietarios.add(PROPIETARIO_3);
		when(propietarioService.listarPropietarios()).thenReturn(propietarios);
		
		mvc.perform(get("/propietario/").contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(xpath("count(//tr)").string("4"));
		
		verify(propietarioService).listarPropietarios();
	}
	
	@Test
	void editarPropietariosTest() throws Exception {
		when(propietarioService.encontrarPropietario(PROPIETARIO_1.getDni())).thenReturn(PROPIETARIO_1);
		
		mvc.perform(get("/propietario/editar/" + PROPIETARIO_1.getDni()).contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@id='dni']/@value").string(PROPIETARIO_1.getDni()))
			.andExpect(xpath("//input[@id='nombre']/@value").string(PROPIETARIO_1.getNombre()))
			.andExpect(xpath("//input[@id='apellido']/@value").string(PROPIETARIO_1.getApellido()));
		
		verify(propietarioService).encontrarPropietario(any());
	}
}
