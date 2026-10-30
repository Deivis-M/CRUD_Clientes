
package com.krakdev.clientes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.krakdev.clientes.entidades.Cliente;
import com.krakdev.clientes.services.ServicioCliente;

public class ServicioClienteTest {

	@Test
	public void testCrearClienteExitoso() {
		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente();
		cliente.setCedula("123");
		cliente.setNombre("Juan");
		cliente.setApellido("Perez");
		cliente.setEmail("juan@gmail.com");

		Cliente resultado = servicio.crear(cliente);

		assertEquals("123", resultado.getCedula());
		assertEquals("Juan", resultado.getNombre());
		assertEquals("Perez", resultado.getApellido());
		assertEquals("juan@gmail.com", resultado.getEmail());
		assertEquals(1, servicio.listar().size());
	}

	@Test
	public void testCrearClienteConCedulaDuplicada() {
		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente1 = new Cliente();
		cliente1.setCedula("123");
		cliente1.setNombre("Juan");
		cliente1.setApellido("Perez");
		cliente1.setEmail("juan@gmail.com");

		Cliente cliente2 = new Cliente();
		cliente2.setCedula("123");
		cliente2.setNombre("Pedro");
		cliente2.setApellido("Lopez");
		cliente2.setEmail("pedro@gmail.com");

		servicio.crear(cliente1);
		Cliente resultado = servicio.crear(cliente2);

		assertEquals(null, resultado);
		assertEquals(1, servicio.listar().size());
		assertEquals("juan@gmail.com", servicio.listar().get(0).getEmail());
	}

	@Test
	public void testBuscarClienteExistente() {
		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente();
		cliente.setCedula("123");
		cliente.setNombre("Juan");
		cliente.setApellido("Perez");
		cliente.setEmail("juan@gmail.com");

		servicio.crear(cliente);

		Cliente resultado = servicio.buscarPorCedula("123");

		assertEquals("123", resultado.getCedula());
		assertEquals("Juan", resultado.getNombre());
		assertEquals("Perez", resultado.getApellido());
		assertEquals("juan@gmail.com", resultado.getEmail());
	}

	@Test
	public void testBuscarClienteInexistente() {
		ServicioCliente servicio = new ServicioCliente();

		Cliente resultado = servicio.buscarPorCedula("999");

		assertEquals(null, resultado);
	}

	@Test
	public void testListarClientesConDatos() {
		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente1 = new Cliente();
		cliente1.setCedula("123");
		cliente1.setNombre("Juan");
		cliente1.setApellido("Perez");
		cliente1.setEmail("juan@gmail.com");

		Cliente cliente2 = new Cliente();
		cliente2.setCedula("456");
		cliente2.setNombre("Maria");
		cliente2.setApellido("Lopez");
		cliente2.setEmail("maria@gmail.com");

		servicio.crear(cliente1);
		servicio.crear(cliente2);

		assertEquals(2, servicio.listar().size());

		assertEquals("Juan", servicio.listar().get(0).getNombre());
		assertEquals("juan@gmail.com", servicio.listar().get(0).getEmail());

		assertEquals("Maria", servicio.listar().get(1).getNombre());
		assertEquals("maria@gmail.com", servicio.listar().get(1).getEmail());
	}

	@Test
	public void testListarClientesListaVacia() {
		ServicioCliente servicio = new ServicioCliente();

		assertEquals(0, servicio.listar().size());
	}

	@Test
	public void testActualizarClienteExistente() {
		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente();
		cliente.setCedula("123");
		cliente.setNombre("Juan");
		cliente.setApellido("Perez");
		cliente.setEmail("juan@gmail.com");

		servicio.crear(cliente);

		Cliente actualizado = new Cliente();
		actualizado.setNombre("Carlos");
		actualizado.setApellido("Gomez");
		actualizado.setEmail("carlos@gmail.com");

		Cliente resultado = servicio.actualizar("123", actualizado);

		assertEquals("123", resultado.getCedula());
		assertEquals("Carlos", resultado.getNombre());
		assertEquals("Gomez", resultado.getApellido());
		assertEquals("carlos@gmail.com", resultado.getEmail());
	}

	@Test
	public void testActualizarClienteInexistente() {
		ServicioCliente servicio = new ServicioCliente();

		Cliente actualizado = new Cliente();
		actualizado.setCedula("999");
		actualizado.setNombre("Carlos");
		actualizado.setApellido("Gomez");
		actualizado.setEmail("carlos@gmail.com");

		Cliente resultado = servicio.actualizar("999", actualizado);

		assertEquals(null, resultado);
		assertEquals(0, servicio.listar().size());
	}

	@Test
	public void testEliminarClienteExistente() {
		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente();
		cliente.setCedula("123");
		cliente.setNombre("Juan");
		cliente.setApellido("Perez");
		cliente.setEmail("juan@gmail.com");

		servicio.crear(cliente);

		boolean resultado = servicio.eliminar("123");

		assertTrue(resultado);
		assertEquals(0, servicio.listar().size());
	}

	@Test
    public void testEliminarClienteInexistente() {
        ServicioCliente servicio = new ServicioCliente();

        boolean resultado = servicio.eliminar("999");

        assertFalse(resultado);
        assertEquals(0, servicio.listar().size());
    }
}