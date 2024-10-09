package ar.edu.unju.escmi.tp6.tests;

import ar.edu.unju.escmi.tp6.collections.CollectionStock;
import ar.edu.unju.escmi.tp6.dominio.Producto;
import ar.edu.unju.escmi.tp6.dominio.Stock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StockTest {
	private Stock stock;

    @BeforeEach
    void setUp() {
        // Inicializa un producto
        Producto producto = new Producto(1, "Televisor", 100000, "Nacional", true);
        // Inicializa el stock con una cantidad
        stock = new Stock(10, producto); // stock inicial de 10 unidades
        // Agregar el stock a la colección para poder reducirlo
        CollectionStock.stocks.add(stock);
    }

    @Test
    void testDecrementarStock() {
        int cantidadDecremento = 3; // Cantidad a decrementar
        int stockInicial = stock.getCantidad(); // Obtener cantidad inicial

        // Decrementar stock en la cantidad indicada usando el método de la colección
        CollectionStock.reducirStock(stock, cantidadDecremento);

        // Verificar que el stock se decrementó correctamente
        assertEquals(stockInicial - cantidadDecremento, stock.getCantidad(),
                     "El stock debe decrementar en la cantidad indicada.");
    }

    @Test
    void testNoDecrementarStockNegativo() {
        int cantidadDecremento = 15; // Intentar decrementar más de lo que hay en stock
        // Intentar decrementar stock usando el método de la colección
        CollectionStock.reducirStock(stock, cantidadDecremento);

        // Verificar que el stock no sea negativo
        assertTrue(stock.getCantidad() >= 0, "El stock no debe ser negativo, debe permanecer igual.");
    }
}
