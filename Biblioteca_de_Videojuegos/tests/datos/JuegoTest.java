package datos;

import static org.junit.Assert.*;

import javax.swing.JLabel;

import org.junit.Before;
import org.junit.Test;

public class JuegoTest {
	private Juego j;

	@Before
	public void setUp() throws Exception {
		j= new Juego("Nombre", 18, "c", 60.00, false, null);
	}

	@Test
	public void testGetNombre() {
		assertEquals("Nombre",j.getNombre());
	}
	
	@Test
	public void testGetEdadNecesaria() {
		assertEquals(18,j.getEdadnecesaria());
	}

	@Test
	public void testGetCategoria() {
		assertEquals("c",j.getCategoria());
	}
	
	@Test
	public void testGetPrecio() {
		assertEquals(60.00,j.getPrecio(), 0.0001);
	}
	
	@Test
	public void testIsPrestamo() {
		assertEquals(false,j.isPrestamo());
	}
	
}
