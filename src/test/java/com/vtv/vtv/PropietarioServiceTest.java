package com.vtv.vtv;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aopalliance.intercept.Invocation;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.vtv.vtv.dao.PropietarioDao;
import com.vtv.vtv.entidades.Propietario;
import com.vtv.vtv.servicios.PropietarioService;

@SpringBootTest
public class PropietarioServiceTest {
	
	@Autowired
	private PropietarioService propietarioService;
	
	@MockBean
	private PropietarioDao propietarioDao;

	private static final Propietario PROPIETARIO_1 = new Propietario("nombre1", "apellido1", "1234567", false);
	private static final Propietario PROPIETARIO_2 = new Propietario("algun nombre", "apellido2", "1234568", true);
	private static final Propietario PROPIETARIO_3 = new Propietario("nombre3", "apellido3", "1234569", false);
	
	@Test
	void listarPropietariosTest() {
		List<Propietario> datos = new ArrayList<>();
		datos.add(PROPIETARIO_1);
		datos.add(PROPIETARIO_2);
		datos.add(PROPIETARIO_3);
		when(propietarioDao.findAll()).thenReturn(datos);
		
		List<Propietario> propietarios = propietarioService.listarPropietarios();
		
		assertFalse(propietarios.isEmpty());
		assertEquals(3, propietarios.size());
		assertTrue(propietarios.contains(PROPIETARIO_3));
		
		verify(propietarioDao).findAll();
	}
	
	@Test
	void encontrarPropietarioTest() {
		when(propietarioDao.findById(PROPIETARIO_2.getDni())).thenReturn(Optional.of(PROPIETARIO_2));
		
		Propietario propietario1 = propietarioService.encontrarPropietario(PROPIETARIO_2.getDni());
		Propietario propietario2 = propietarioService.encontrarPropietario(PROPIETARIO_2.getDni());
		
		assertSame(propietario1, propietario2);
		assertEquals("algun nombre", propietario1.getNombre());
		assertEquals("algun nombre", propietario2.getNombre());
		
		verify(propietarioDao, times(2)).findById(PROPIETARIO_2.getDni());
	}
	
	@Test
	void guardarPropietarioTest() {
		ArgumentCaptor<Propietario> captor = ArgumentCaptor.forClass(Propietario.class);
		
		propietarioService.guardar(PROPIETARIO_1);
		
		verify(propietarioDao).save(PROPIETARIO_1);
		verify(propietarioDao).save(captor.capture());
		assertTrue(captor.getValue().getDni().equals(PROPIETARIO_1.getDni()));
	}
	
	@Test
	void eliminarPropietarioTest() {
		propietarioService.guardar(PROPIETARIO_1);

		propietarioService.eliminar(PROPIETARIO_1);
		
		assertNull(propietarioService.encontrarPropietario(PROPIETARIO_1.getDni()));
		
		verify(propietarioDao).save(PROPIETARIO_1);
		verify(propietarioDao).delete(PROPIETARIO_1);
	}
}
