package ar.edu.unju.escmi.tp6.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import ar.edu.unju.escmi.tp6.collections.*;
import ar.edu.unju.escmi.tp6.dominio.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CollectionCliente.precargarClientes();
        CollectionTarjetaCredito.precargarTarjetas();
        CollectionProducto.precargarProductos();
        CollectionStock.precargarStocks();

        int opcion;
        do {
            System.out.println("\n====== Menu Principal =====");
            System.out.println("1- Realizar una gestion");
            System.out.println("2- Realizar una venta");
            System.out.println("3- Revisar compras realizadas por el cliente (debe ingresar el DNI del cliente)");
            System.out.println("4- Mostrar lista de los electrodomésticos");
            System.out.println("5- Consultar stock");
            System.out.println("6- Revisar creditos de un cliente (debe ingresar el DNI del cliente)");
            System.out.println("7- Salir");
            System.out.print("Ingrese su opcion: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        gestionar();
                        break;
                    case 2:
                        // Implementar lógica para realizar una venta
                        break;
                    case 3:
                        // Implementar lógica para revisar compras
                        break;
                    case 4:
                        // Implementar lógica para mostrar electrodomésticos
                        break;
                    case 5:
                        // Implementar lógica para consultar stock
                        break;
                    case 6:
                        // Implementar lógica para revisar créditos
                        break;
                    case 7:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer
                opcion = 0; // Forzar que vuelva a mostrar el menú
            }
        } while (opcion != 7);

        scanner.close();
    }

    private static void gestionar() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- MENÚ DE GESTIÓN ---");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Productos");
            System.out.println("3. Gestión de Tarjetas de Créditos");
            System.out.println("4. Gestión de Stocks");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcionb = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcionb) {
                    case 1:
                        gestionClientes(scanner);
                        break;
                    case 2:
                    	gestionProductos(scanner);
                    	break;
                    case 3:
                    	gestionTarjetasdeCreditos(scanner);
                    	break;
                    case 4:
                    	gestionStocks(scanner);
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
    }
    private static void gestionClientes(Scanner scanner) {
        System.out.println("\n--- GESTIÓN DE CLIENTES ---");
        System.out.println("1. Agregar Cliente");
        System.out.println("2. Consultar Cliente por DNI");
        System.out.println("3. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");

        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    long dni = 0;
                    boolean dniValido = false;
                    while (!dniValido) {
                    	System.out.print("Ingrese DNI: ");

                        try {
                            dni = scanner.nextLong();
                            scanner.nextLine(); // Limpiar el buffer
                            Cliente clienteBuscado = CollectionCliente.buscarCliente(dni);
                            if (clienteBuscado != null) {
                                System.out.println("Error: El DNI " + dni + " ya está registrado. Intente con otro.");
                            } else {
                                dniValido = true; // DNI válido, salir del bucle
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Por favor, ingrese un número válido para el DNI.");
                            scanner.nextLine(); // Limpiar el buffer del scanner
                        }
                    }
                    System.out.print("Ingrese Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese Dirección: ");
                    String direccion = scanner.nextLine();
                    System.out.print("Ingrese Teléfono: ");
                    String telefono = scanner.nextLine();
                    Cliente cliente = new Cliente(dni, nombre, direccion, telefono);
                    CollectionCliente.agregarCliente(cliente);
                    System.out.println("Cliente agregado: " + cliente.getNombre());
                    break;
                case 2:
                    System.out.print("Ingrese el DNI del cliente a consultar: ");

                	try {
                    dni = scanner.nextLong();
                    scanner.nextLine(); // Limpiar el buffer

                    Cliente clienteBuscado = CollectionCliente.buscarCliente(dni);
                    if (clienteBuscado != null) {
                        System.out.println("Detalles del cliente: " + clienteBuscado);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                	} catch (InputMismatchException e) {
	                    System.out.println("Error: Entrada inválida. Por favor, ingrese un número para el código del producto.");
	                    scanner.nextLine(); 
	                } catch (Exception e) {
	                    System.out.println("Error al consultar el producto: " + e.getMessage());
	                }
                    break;
                case 3:
                    return; 
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }
    private static void gestionProductos(Scanner scanner) {
        System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
        System.out.println("1. Agregar Producto");
        System.out.println("2. Consultar Productos");
        System.out.println("3. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
        
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    try {
                        boolean codValido = false;
                        long codigo = 0;
                        while (!codValido) {
                            System.out.print("Ingrese Código del Producto: ");
                            codigo = scanner.nextLong();

                            Producto productoBuscado = CollectionProducto.buscarProducto(codigo);
                            if (productoBuscado != null) {
                                System.out.println("Error: El codigo " + codigo + " ya está registrado. Intente con otro.");
                            } else {
                                codValido = true; // Código válido, salir del bucle
                            }
                        }

                        scanner.nextLine(); // Limpiar el buffer después de ingresar el código
                        System.out.print("Ingrese Descripción: ");
                        String descripcion = scanner.nextLine(); // Capturar correctamente la descripción

                        System.out.print("Ingrese Precio Unitario: ");
                        double precioUnitario = scanner.nextDouble();
                        scanner.nextLine(); // Limpiar el buffer

                        System.out.print("Ingrese Origen de Fabricación: ");
                        String origenFabricacion = scanner.nextLine();
                        
                        System.out.print("¿El producto es para cuotas? (1: Sí): ");
                        int op = scanner.nextInt();
                        boolean cuo = (op == 1); // Si op es 1, cuo será true, de lo contrario será false

                        Producto producto = new Producto(codigo, descripcion, precioUnitario, origenFabricacion, cuo);
                        CollectionProducto.agregarProducto(producto); // Agregar producto a la colección

                        System.out.println("Producto agregado: " + producto);
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Entrada inválida. Asegúrate de ingresar un número donde se solicita.");
                        scanner.nextLine(); // Limpiar el buffer en caso de error
                    } catch (Exception e) {
                        System.out.println("Error al agregar el producto: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el código del producto a consultar: ");

                    try {
                        long codigo = scanner.nextLong();
                        scanner.nextLine(); // Limpiar el buffer

                        Producto productoBuscado = CollectionProducto.buscarProducto(codigo);
                        if (productoBuscado != null) {
                            System.out.println("Detalles del producto: " + productoBuscado);
                        } else {
                            System.out.println("Producto no encontrado.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Entrada inválida. Por favor, ingrese un número para el código del producto.");
                        scanner.nextLine(); 
                    } catch (Exception e) {
                        System.out.println("Error al consultar el producto: " + e.getMessage());
                    }
                    break;

                case 3:
                    return; // Volver al menú principal
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }

    private static void gestionTarjetasdeCreditos(Scanner scanner) {
    	System.out.println("\n--- GESTIÓN DE TARJETAS DE CRÉDITO ---");
	    System.out.println("1. Agregar Tarjeta de Crédito");
	    System.out.println("2. Consultar Tarjetas de Crédito por Cliente");
	    System.out.println("3. Volver al Menú Principal");
	    System.out.print("Seleccione una opción: ");
    	try {
    	    int opcion = scanner.nextInt();
    	    scanner.nextLine(); // Limpiar el buffer
    	    switch (opcion) {
	        case 1:
	        	System.out.println("\n--- AGREGAR TARJETA DE CRÉDITO ---");
	        	try {
	                // Ingresar datos del cliente
	                System.out.print("Ingrese el DNI del cliente: ");
	                long dniCliente = scanner.nextLong();
	                scanner.nextLine(); // Limpiar el buffer
	                Cliente cliente = CollectionCliente.buscarCliente(dniCliente);
	                
	                if (cliente == null) {
	                    System.out.println("Cliente no encontrado.");
	                    return;
	                }

	                // Ingresar los detalles de la tarjeta de crédito
	                boolean numTarje = false;
                    long numeroTarjeta = 0;
                    while (!numTarje) {
    	            System.out.print("Ingrese el número de la tarjeta de crédito: ");

                    numeroTarjeta = scanner.nextLong();

                    TarjetaCredito tarjetaBusc = CollectionTarjetaCredito.buscarTarjetaCredito(numeroTarjeta);
                    if (tarjetaBusc != null) {
                        System.out.println("Error: El numero de tarjeta: " + numeroTarjeta + " ya está registrado. Intente con otro.");
                    } else {
                        numTarje = true; // DNI válido, salir del bucle
                    }
                    }
	                
	                
	                scanner.nextLine(); // Limpiar el buffer
	                System.out.print("Ingrese el límite de compra: ");
	                double limiteCompra = scanner.nextDouble();
	                scanner.nextLine(); // Limpiar el buffer
	                System.out.print("Ingrese la fecha de caducación (YYYY-MM-DD): ");
	                String fechaCaducacionStr = scanner.nextLine();
	                LocalDate fechaCaducacion = LocalDate.parse(fechaCaducacionStr);  // Convertir a LocalDate

	                // Crear nueva tarjeta de crédito
	                TarjetaCredito tarjeta = new TarjetaCredito(numeroTarjeta, fechaCaducacion, cliente, limiteCompra);

	                // Agregar tarjeta a la colección de tarjetas de crédito
	                CollectionTarjetaCredito.agregarTarjetaCredito(tarjeta);

	                System.out.println("Tarjeta de crédito agregada exitosamente para el cliente: " + cliente.getNombre());
	                System.out.println("Detalles de la tarjeta: " + tarjeta);

	            } catch (InputMismatchException e) {
	                System.out.println("Entrada inválida. Por favor, ingrese un valor numérico correcto.");
	                scanner.nextLine(); // Limpiar el buffer
	            } catch (Exception e) {
	                System.out.println("Error al agregar la tarjeta de crédito. Por favor, intente nuevamente.");
	            }
	        break;
	        case 2:
	        	try {
	        	    System.out.print("Ingrese el DNI del cliente: ");
	        	    long dniCliente = scanner.nextLong();
	        	    scanner.nextLine(); // Limpiar el buffer

	        	    Cliente cliente = CollectionCliente.buscarCliente(dniCliente);
	        	    if (cliente == null) {
	        	        System.out.println("Cliente no encontrado.");
	        	        return;
	        	    }

	        	    // Buscar las tarjetas del cliente manualmente
	        	    List<TarjetaCredito> tarjetasDelCliente = new ArrayList();
	        	    for (TarjetaCredito tarjeta : CollectionTarjetaCredito.tarjetas) {
	        	        if (tarjeta.getCliente().equals(cliente)) {
	        	            tarjetasDelCliente.add(tarjeta);  // Agregar la tarjeta si pertenece al cliente
	        	        }
	        	    }

	        	    if (tarjetasDelCliente.isEmpty()) {
	        	        System.out.println("No se encontraron tarjetas de crédito para el cliente.");
	        	    } else {
	        	        System.out.println("\n--- TARJETAS DE CRÉDITO DEL CLIENTE ---");
	        	        for (TarjetaCredito tarjeta : tarjetasDelCliente) {
	        	            System.out.println("Número: " + tarjeta.getNumero() + 
	        	                               ", Fecha de Caducación: " + tarjeta.getFechaCaducacion() + 
	        	                               ", Límite de Compra: " + tarjeta.getLimiteCompra());
	        	        }
	        	    }
	        	} catch (Exception e) {
	        	    System.out.println("Error: " + e.getMessage());
	        	}

	        	break;
	        case 3: 
	        	return;
	        	default:
		            System.out.println("Opción inválida, intente nuevamente.");
	        		break;
    	    }
        }catch (InputMismatchException e) {
	        System.out.println("Entrada inválida. Por favor, ingrese un número.");
	        scanner.nextLine(); // Limpiar el buffer
	    }
    }
    private static void gestionStocks(Scanner scanner) {
        try {
            int opcion;
            do {
                System.out.println("\n--- GESTIÓN DE STOCKS ---");
                System.out.println("1. Listar todos los stocks");
                System.out.println("2. Agregar stock a un producto");
                System.out.println("3. Reducir stock de un producto");
                System.out.println("4. Buscar stock por producto");
                System.out.println("0. Volver al menú principal");
                System.out.print("Ingrese una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        try {
                            // Listar todos los stocks
                            if (CollectionStock.stocks.isEmpty()) {
                                System.out.println("No hay stocks registrados.");
                            } else {
                                System.out.println("\n--- LISTA DE STOCKS ---");
                                for (Stock stock : CollectionStock.stocks) {
                                    System.out.println("Producto: " + stock.getProducto().getDescripcion() +
                                            ", Cantidad en stock: " + stock.getCantidad());
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error al listar los stocks: " + e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            // Agregar stock a un producto
                            System.out.print("Ingrese el código del producto para agregar stock: ");
                            long codigoProductoAgregar = scanner.nextLong();
                            scanner.nextLine(); // Limpiar buffer

                            Producto productoAgregar = CollectionProducto.buscarProducto(codigoProductoAgregar);
                            if (productoAgregar == null) {
                                System.out.println("Producto no encontrado.");
                            } else {
                                Stock stockExistente = CollectionStock.buscarStock(productoAgregar);
                                if (stockExistente != null) {
                                    // Producto ya tiene stock
                                    System.out.println("El producto ya tiene stock disponible.");
                                } else {
                                    System.out.print("Ingrese la cantidad de stock a agregar: ");
                                    int cantidadAgregar = scanner.nextInt();
                                    scanner.nextLine(); // Limpiar buffer

                                    Stock nuevoStock = new Stock(cantidadAgregar, productoAgregar);
                                    CollectionStock.agregarStock(nuevoStock);
                                    System.out.println("Stock agregado correctamente para el producto: " + productoAgregar.getDescripcion());
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error al agregar stock: " + e.getMessage());
                        }
                        break;

                    case 3:
                        try {
                            // Reducir stock de un producto
                            System.out.print("Ingrese el código del producto para reducir stock: ");
                            long codigoProductoReducir = scanner.nextLong();
                            scanner.nextLine(); // Limpiar buffer

                            Producto productoReducir = CollectionProducto.buscarProducto(codigoProductoReducir);
                            if (productoReducir == null) {
                                System.out.println("Producto no encontrado.");
                            } else {
                                System.out.print("Ingrese la cantidad de stock a reducir: ");
                                int cantidadReducir = scanner.nextInt();
                                scanner.nextLine(); // Limpiar buffer

                                Stock stockReducir = CollectionStock.buscarStock(productoReducir);
                                if (stockReducir != null && stockReducir.getCantidad() >= cantidadReducir) {
                                    CollectionStock.reducirStock(stockReducir, cantidadReducir);
                                    System.out.println("Stock reducido correctamente.");
                                } else {
                                    System.out.println("No hay suficiente stock para reducir.");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error al reducir stock: " + e.getMessage());
                        }
                        break;

                    case 4:
                        try {
                            // Buscar stock por producto
                            System.out.print("Ingrese el código del producto: ");
                            long codigoProductoBuscar = scanner.nextLong();
                            scanner.nextLine(); // Limpiar buffer

                            Producto productoBuscar = CollectionProducto.buscarProducto(codigoProductoBuscar);
                            if (productoBuscar == null) {
                                System.out.println("Producto no encontrado.");
                            } else {
                                Stock stockBuscar = CollectionStock.buscarStock(productoBuscar);
                                if (stockBuscar != null) {
                                    System.out.println("Producto: " + stockBuscar.getProducto().getDescripcion() +
                                            ", Cantidad en stock: " + stockBuscar.getCantidad());
                                } else {
                                    System.out.println("No se encontró stock para este producto.");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error al buscar stock: " + e.getMessage());
                        }
                        break;

                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 0);
        } catch (Exception e) {
            System.out.println("Error en la gestión de stocks: " + e.getMessage());
            scanner.nextLine(); // Limpiar el buffer en caso de error
        }
    }


}
