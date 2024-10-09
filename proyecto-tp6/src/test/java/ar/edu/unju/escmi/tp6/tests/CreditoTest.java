package ar.edu.unju.escmi.tp6.tests;


import ar.edu.unju.escmi.tp6.dominio.Credito;
import ar.edu.unju.escmi.tp6.dominio.Cuota;
import ar.edu.unju.escmi.tp6.dominio.Factura;
import ar.edu.unju.escmi.tp6.dominio.TarjetaCredito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class CreditoTest {
	private Credito credito;
    private TarjetaCredito tarjetaCredito;
    private Factura factura;

    @BeforeEach
    void setUp() {
        tarjetaCredito = new TarjetaCredito();
        factura = new Factura();
        credito = new Credito(tarjetaCredito, factura, new ArrayList<>());
    }

    @Test
    void testMontoTotalCreditoNoSuperaPermitido(){
        double montoExcedido = 1600000.0; 

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            credito.generarCuotas(30, montoExcedido);
        });

        assertEquals("El monto total de la compra no debe superar los $1.500.000.", exception.getMessage());
    }

    @Test
    void testSumaDetallesIgualTotalFactura() {
        double totalCompra = 100000.0;
        credito.generarCuotas(2, totalCompra); 

        double totalFactura = credito.getCuotas().stream().mapToDouble(Cuota::getMonto).sum(); 
        assertEquals(totalCompra, totalFactura, "La suma de todos los importes de detalles debe ser igual al total de la factura");
    }

    @Test
    void testMontoTotalCompraNoSuperaLimiteCliente() {
        double montoCompraExcedido = 1600000.0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            credito.generarCuotas(30, montoCompraExcedido);
        });

        assertEquals("El monto total de la compra no debe superar los $1.500.000.", exception.getMessage());
    }

    @Test
    void testGenerarCuotasExcepcion() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            credito.generarCuotas(31, 1500000.0);
        });

        assertEquals("El número máximo de cuotas permitidas es 30.", exception.getMessage());
    }

    @Test
    void testCuotasGeneradasCorrectamente() {
        int numeroCuotas = 3;
        double totalCompra = 150000.0;

        credito.generarCuotas(numeroCuotas, totalCompra);

        assertEquals(numeroCuotas, credito.getCuotas().size(), "Se deben generar 3 cuotas.");
        for (Cuota cuota : credito.getCuotas()) {
            assertEquals(totalCompra / numeroCuotas, cuota.getMonto(), "El monto de cada cuota debe ser correcto.");
            assertNotNull(cuota.getFechaGeneracion(), "La fecha de generación no debe ser null.");
            assertNotNull(cuota.getFechaVencimiento(), "La fecha de vencimiento no debe ser null.");
        }
    }
}
