package utilidadesteatromoro;

import java.util.Scanner;

public class UtilidadesTeatroMoro {

    public static void mostrarZona(String nombre, char[][] zona, int numeroZona, int precio) {
        System.out.println(numeroZona + ". " + nombre + ": $" + precio);
        for (int i = 0; i < zona.length; i++) {
            System.out.print("F" + (i + 1) + ": ");
            for (int j = 0; j < zona[i].length; j++) {
                System.out.print("[" + zona[i][j] + "] ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        char[][] vip = {
            {'●', '●', '●', '●'}
        };

        char[][] platea = {
            {'●', '●', '●', '●', '●', '●'},
            {'●', '●', '●', '●', '●', '●'}
        };

        char[][] palco = {
            {'●', '●', '●', '●'}
        };

        String[] opciones = {"Comprar entrada", "Salir"};
        String[] ubicaciones = {"VIP", "Platea", "Palco"};
        String[] tipoPago = {"crédito", "débito"};
        int[] precioBase = {15000, 10000, 8000};

        String[] resumenZonas = new String[10];
        String[] resumenAsientos = new String[10];
        int[] resumenPrecios = new int[10];
        double[] resumenFinales = new double[10];
        int totalCompras = 0;

        int opcion;
        do {
            System.out.println("\n¡Bienvenido a la plataforma de reserva de tickets de Teatro Moro!");
            System.out.println("Por favor, selecciona una opción para continuar:");
            for (int k = 0; k < opciones.length; k++) {
                System.out.println((k + 1) + ". " + opciones[k]);
            }
            opcion = sc.nextInt();

            if (opcion != 1 && opcion != 2) {
                System.out.println("Por favor, ingresa una opción válida.");
            }
        } while (opcion != 1 && opcion != 2);

        switch (opcion) {
            case 1:
                boolean seguirComprando;
                do {
                    System.out.println();
                    System.out.println("¡Este es el plano del teatro!");
                    System.out.println("Las ubicaciones marcadas con \"●\" están libres, y las marcadas con \"X\" ya están reservadas.\n");
                    mostrarZona("VIP", vip, 1, precioBase[0]);
                    mostrarZona("PLATEA", platea, 2, precioBase[1]);
                    mostrarZona("PALCO", palco, 3, precioBase[2]);

                    System.out.println("¡Contamos con 10% OFF para estudiantes, y 15% OFF para adultos mayores!");

                    int edad = -1;

                    do {
                        System.out.println("\n¿Cuál es la edad (en años) de la persona que utilizará este ticket? (Recuerda que verificaremos tu edad con tu cédula de identidad al momento de ingresar al teatro).");
                        sc.nextLine();
                        String edadTexto = sc.nextLine();

                        try {
                            edad = Integer.parseInt(edadTexto);

                            if (edad < 0 || edad > 99) {
                                System.out.println("Por favor, ingresa una edad válida.");
                                edad = -1;
                            }

                        } catch(NumberFormatException e) {
                            System.out.println("Por favor, ingresa una edad válida (sin puntos ni comas).");
                            edad = -1;
                        }
                    } while (edad == -1);

                    double descuento = 0;
                    if (edad < 23) {
                        descuento = 0.10;
                    } else if (edad >= 60) {
                        descuento = 0.15;
                    }

                    int zonaElegida;
                    do {
                        System.out.println("\n¿En qué zona quieres comprar tu entrada? (1-3).");
                        while (!sc.hasNextInt()) {
                            System.out.println("Opción inválida. Por favor, inténtalo nuevamente");
                            sc.next();
                        }
                        zonaElegida = sc.nextInt();
                        if (zonaElegida < 1 || zonaElegida > ubicaciones.length) {
                            System.out.println("Opción inválida. Por favor, inténtalo nuevamente.");
                        }
                    } while (zonaElegida < 1 || zonaElegida > ubicaciones.length);
                    System.out.println("¡Súper! Has elegido la zona " + ubicaciones[zonaElegida - 1] + ".");

                    char[][] zonaSeleccionada;
                    String nombreZona;
                    int precio;

                    if (zonaElegida == 1) {
                        zonaSeleccionada = vip;
                        nombreZona = "VIP";
                        precio = precioBase[0];
                    } else if (zonaElegida == 2) {
                        zonaSeleccionada = platea;
                        nombreZona = "PLATEA";
                        precio = precioBase[1];
                    } else {
                        zonaSeleccionada = palco;
                        nombreZona = "PALCO";
                        precio = precioBase[2];
                    }

                    double precioFinal = precio - (precio * descuento);

                    if (edad < 23) {
                        System.out.println("La tarifa diferenciada para esta ubicación es de $" + precioFinal + ". ¡Obtuviste un descuento del 10% sobre el precio general! (categoría estudiantes).");
                    } else if (edad >= 60) {
                        System.out.println("La tarifa diferenciada para esta ubicación es de $" + precioFinal + ". ¡Obtuviste un descuento del 15% sobre el precio general! (categoría adulto mayor).");
                    } else {
                        System.out.println("La tarifa regular para esta ubicación es de $" + precioFinal);
                    }
                    
                    boolean asientoValido = false;
                   
                    int fila;
                    do {
                        System.out.println("\n¿En qué fila (F) quieres sentarte? (1 a " + zonaSeleccionada.length + ")");
                        while (!sc.hasNextInt()) {
                            System.out.println("El número de fila ingresado es inválido. Inténtalo nuevamente.");
                            sc.next();
                        }
                        fila = sc.nextInt();
                        if (fila < 1 || fila > zonaSeleccionada.length) {
                            System.out.println("El número de fila ingresado es inválido. Inténtalo nuevamente.");
                        }
                    } while (fila < 1 || fila > zonaSeleccionada.length);

                    while (!asientoValido) {
                    int asiento;
                    do {
                        System.out.println("\n¿Qué asiento quieres reservar? (1 a " + zonaSeleccionada[0].length + ")");
                        while (!sc.hasNextInt()) {
                            System.out.println("El número de asiento ingresado es inválido. Inténtalo nuevamente.");
                            sc.next();
                        }
                        asiento = sc.nextInt();
                        if (asiento < 1 || asiento > zonaSeleccionada[0].length) {
                            System.out.println("El número de asiento ingresado es inválido. Inténtalo nuevamente.");
                        }
                    } while (asiento < 1 || asiento > zonaSeleccionada[0].length);

                    if (zonaSeleccionada[fila - 1][asiento - 1] == 'X') {
                        System.out.println("¡Ups! Este asiento ya está reservado. Recuerda que puedes escoger los que estén marcados con el símbolo \"●\".");
                    } else {
                        zonaSeleccionada[fila - 1][asiento - 1] = 'X'; 
                        asientoValido = true;

                        System.out.println("\n¡Perfecto! Estamos guardando tus elecciones...");
                        Thread.sleep(2000);

                        System.out.println("\nEste es el subtotal de tu carrito de compras:");
                        System.out.println("Zona: " + nombreZona);
                        System.out.println("Fila: " + fila);
                        System.out.println("Asiento: " + asiento);
                        System.out.println("Precio base: $" + precio);
                        System.out.println("Descuento aplicado: " + (int)(descuento * 100) + "%");
                        System.out.println("Precio final: $" + precioFinal);

                        resumenZonas[totalCompras] = nombreZona;
                        resumenAsientos[totalCompras] = "F" + fila + "A" + asiento;
                        resumenPrecios[totalCompras] = precio;
                        resumenFinales[totalCompras] = precioFinal;
                        totalCompras++;
                    }
                }
 
                System.out.println("¿Deseas comprar otra entrada? (s/n)");
                        String respuesta = sc.next().toLowerCase();
                        seguirComprando = respuesta.equals("s");
    
                } while (seguirComprando);

                double total = 0;
                System.out.println("\nEste es tu carrito de compras actualizado:");
                for (int l = 0; l < totalCompras; l++) {
                    System.out.println((l + 1) + ". Zona: " + resumenZonas[l] + " | Asiento: " + resumenAsientos[l] + " | Precio base: $" + resumenPrecios[l] + " | Precio final: $" + resumenFinales[l]);
                    total += resumenFinales[l];
                }
                sc.nextLine();
                System.out.println("\nTotal a pagar: $" + total);
                
                int pagoElegido;
                
                do {
                    System.out.println();
                    System.out.println("Por favor, selecciona tu medio de pago:");
                    System.out.println("1. Crédito");
                    System.out.println("2. Débito");
                    pagoElegido = sc.nextInt();
                 
                    if (pagoElegido < 1 || pagoElegido > 2) {
                        System.out.println("Por favor, ingresa una opción válida (1-2)");
                    } 
                } while (pagoElegido < 1 || pagoElegido > 2); 
                
                System.out.println("Estamos procesando tu compra, por favor espera unos segundos...");
                Thread.sleep(2000);
        
                System.out.println();
                System.out.println("✅ ¡Compra realizada con éxito!");
                System.out.println("Tu ticket ha sido generado. Medio de pago: tarjeta de " + tipoPago[pagoElegido - 1] + ".");
                System.out.println("¡Gracias por tu compra! Esperamos que distrutes la función 🎭");
                
                sc.close();
                
                break;

            case 2:
                System.out.println("Has salido de la plataforma. ¡Esperamos verte pronto!");
                break;
        }
    }

}
