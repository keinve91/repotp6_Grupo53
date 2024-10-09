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
      
        Producto producto = new Producto(1, "Televisor", 100000, "Nacional", true);
        stock = new Stock(10, producto);
        CollectionStock.stocks.add(stock);
    }

    @Test
    void testDecrementarStock() {
        int cantidadDecremento = 3; 
        int stockInicial = stock.getCantidad();


        CollectionStock.reducirStock(stock, cantidadDecremento);

 
        assertEquals(stockInicial - cantidadDecremento, stock.getCantidad(),
                     "El stock debe decrementar en la cantidad indicada.");
    }

    @Test
    void testNoDecrementarStockNegativo() {
        int cantidadDecremento = 15; 
        CollectionStock.reducirStock(stock, cantidadDecremento);

        assertTrue(stock.getCantidad() >= 0, "El stock no debe ser negativo, debe permanecer igual.");
    }
}
