package ar.edu.unju.escmi.tp6.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Credito {

    private TarjetaCredito tarjetaCredito;
    private Factura factura;
    private List<Cuota> cuotas = new ArrayList<Cuota>();



    public Credito(TarjetaCredito tarjetaCredito, Factura factura, List<Cuota> cuotas) {
        this.tarjetaCredito = tarjetaCredito;
        this.factura = factura;
        this.cuotas = cuotas;
    }


    public TarjetaCredito getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public List<Cuota> getCuotas() {
        return cuotas;
    }

 // Solo generar cuotas si están vacías
    public void generarCuotas(int numeroCuotas, double totalCompra) {
        if (cuotas.isEmpty()) { 
            double montoCuota = totalCompra / numeroCuotas; 
            int nroCuota = 0;
            LocalDate currentDate = LocalDate.now(); 

            for (int i = 0; i < numeroCuotas; i++) {
                nroCuota++;
                Cuota cuota = new Cuota();
                cuota.setMonto(montoCuota);
                cuota.setNroCuota(nroCuota);
                cuota.setFechaGeneracion(currentDate);
                
                LocalDate fechaVencimiento = currentDate.plusMonths(nroCuota); 
                cuota.setFechaVencimiento(fechaVencimiento);
                
                cuotas.add(cuota);
            }
        }
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Créditos asociados:\n");

        for (Cuota cuota : cuotas) {
            sb.append("Monto: ").append(cuota.getMonto())
              .append(", N° De Cuota: ").append(cuota.getNroCuota())
              .append(", Fecha De Generacion: ").append(cuota.getFechaGeneracion())
              .append(", Fecha De Vencimiento: ").append(cuota.getFechaVencimiento())
              .append("\n");
        }
        return sb.toString();
    }
}
