package ar.edu.unju.escmi.tp6.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.tp6.dominio.Credito;

class CuotaTest {
	private Credito credito;

    @BeforeEach
    void setUp() {
        // Inicializa un crédito con una lista de cuotas vacía
        credito = new Credito(null, null, new ArrayList<>());
        credito.generarCuotas(30, 1500000); // Generar 30 cuotas
    }

    @Test
    void testListaDeCuotasNoEsNula() {
        assertNotNull(credito.getCuotas(), "La lista de cuotas no debe ser nula.");
    }

    @Test
    void testListaDeCuotas() {
        // Asegúrate de que el método generarCuotas funcione correctamente
        credito.generarCuotas(30, 1500000); 
        assertEquals(30, credito.getCuotas().size(), "La lista de cuotas debe contener siempre 30 cuotas.");
    }

    @Test
    void testTopeCuotas() {
        int numeroCuotas = 30; // Cambia esto si deseas simular un número diferente
        credito.generarCuotas(numeroCuotas, 1500000); 
        assertTrue(credito.getCuotas().size() <= 30, "La cantidad de cuotas generadas no debe superar 30.");
    }

}
