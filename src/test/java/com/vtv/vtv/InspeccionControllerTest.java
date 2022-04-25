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

import com.vtv.vtv.controladores.InspeccionController;
import com.vtv.vtv.entidades.Inspeccion;
import com.vtv.vtv.servicios.AutomovilService;
import com.vtv.vtv.servicios.InspeccionService;
import com.vtv.vtv.servicios.InspectorService;

@WebMvcTest(InspeccionController.class)
class InspeccionControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private InspeccionService inspeccionService;
	
	@MockBean
	private InspectorService inspectorService;
	
	@MockBean
	private AutomovilService automovilService;

	private static final Inspeccion INSPECCION_1 = new Inspeccion(2001, "apto", "1234567", "JJJ 111", "2022-04-21");
	private static final Inspeccion INSPECCION_2 = new Inspeccion(2002, "condicional", "1234568", "KKK 222", "2022-04-22");
	private static final Inspeccion INSPECCION_3 = new Inspeccion(2003, "rechazado", "1234569", "LLL 333", "2022-04-23");

	@Test
	void listarInspeccionesTest() throws Exception {
		List<Inspeccion> inspecciones = new ArrayList<>();
		inspecciones.add(INSPECCION_1);
		inspecciones.add(INSPECCION_2);
		inspecciones.add(INSPECCION_3);
		when(inspeccionService.listarInspecciones()).thenReturn(inspecciones);
		
		mvc.perform(get("/inspeccion/").contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(xpath("count(//tr)").string("4"));
		
		verify(inspeccionService).listarInspecciones();
		verify(inspectorService, never()).encontrarInspector(any());
		verify(automovilService, never()).encontrarAutomovil(any());
	}
	
	@Test
	void editarInspeccionesTest() throws Exception {
		when(inspeccionService.encontrarInspeccion(INSPECCION_1.getNumero())).thenReturn(INSPECCION_1);
		
		mvc.perform(get("/inspeccion/editar/" + INSPECCION_1.getNumero()).contentType(MediaType.TEXT_HTML))
			.andExpect(status().isOk())
			.andExpect(xpath("//input[@id='numero']/@value").string(String.valueOf(INSPECCION_1.getNumero())))
			.andExpect(xpath("//input[@id='dominio']/@value").string(INSPECCION_1.getDominio()))
			.andExpect(xpath("//input[@id='dniInspector']/@value").string(INSPECCION_1.getDniInspector()))
			.andExpect(xpath("//input[@id='fecha']/@value").string(INSPECCION_1.getFecha()));
		
		verify(inspeccionService).encontrarInspeccion(INSPECCION_1.getNumero());
		verify(inspectorService, never()).encontrarInspector(any());
		verify(automovilService, never()).encontrarAutomovil(any());
	}
}
