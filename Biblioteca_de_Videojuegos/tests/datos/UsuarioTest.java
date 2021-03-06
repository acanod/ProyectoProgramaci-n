package datos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	private Usuario u;
	
	@Before
	public void setUp() throws Exception {
		u= new Usuario("Nombre", "Apellido", "pass", "1997/12/30","nombre@gmail.com", "Espanya", 12, 12.63, false);
	}

	@Test
	public void testGetNombre() {
		assertEquals("Nombre",u.getNombre());
	}

	@Test
	public void testGetApellido() {
		assertEquals("Apellido",u.getApellido());
	}
	   
	@Test
	public void testGetEmail() {
		assertEquals("NM@gmail.com",u.getEmail());
	}

	@Test 
	public void testGetPais() {
		assertEquals("Espanya",u.getPais());
	}
	
	@Test
	public void testGetPassword() {
		assertEquals("nombreapellido33",u.getPassword());
	}
	
	@Test
	public void testGetNumeroDeJuegos() {
		assertEquals(12,u.getNumeroDeJuegos());
	}
	
	@Test
	public void testGetSaldo() {
		assertEquals(12.12,u.getSaldo(),0.001);
	}
}
