package com.campusland.views;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.campusland.respository.models.Cliente;
import com.campusland.respository.models.Factura;
import com.campusland.respository.models.ItemFactura;
import com.campusland.respository.models.Producto;
import com.campusland.services.impl.ServiceFacturaImpl;

public class ViewFactura extends ViewMain{

    public static void startMenu() {

        int op = 0;

        do {

            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearFactura();
                    break;
                case 2:
                    listarFactura();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (op >= 1 && op < 3);

    }


    public static int mostrarMenu() {
        System.out.println("----Menu--Factura----");
        System.out.println("1. Crear factura.");
        System.out.println("2. Listar factura.");      
        System.out.println("3. Salir ");
        return leer.nextInt();
    }

    public static void listarFactura() {
        System.out.println("Lista de Facturas");
        for (Factura factura : serviceFactura.listar()) {
            factura.display();
            System.out.println();
        }
    }

    public static void crearFactura(){

        System.out.println("-------CREAR FACTURA-------");
        Cliente cliente = ViewCliente.buscarGetCliente();
        if (cliente == null) {
            cliente = crearCliente();
        }
        
        Factura fac = new Factura(LocalDateTime.now(), cliente);
        List <ItemFactura> item =añadirItems();
        fac.setItems(item);
        serviceFactura.crear(fac);
    }

    public static Cliente crearCliente() {
        leer.nextLine();
        System.out.print("Nombre: ");
        String nombre = leer.nextLine();
        System.out.print("Apellido: ");
        String apellido = leer.nextLine();
        System.out.print("Email: ");
        String email = leer.nextLine();
        System.out.print("Celular: ");
        String celular = leer.nextLine();
        System.out.print("Dirección: ");
        String direccion = leer.nextLine();
        System.out.print("Documentos: ");
        String documento = leer.nextLine();
        Cliente cliente = new Cliente(nombre, apellido, email, celular, direccion, documento);
        serviceCliente.crear(cliente);
        return cliente;
    }

    public static List <ItemFactura> añadirItems(){
        String op = "SI";
        Producto producto=null;
        int cantidad = 0;
        List <ItemFactura> itemFactura = new ArrayList<>();
        while(!op.toUpperCase().equals("NO")){
            try{
                producto = ViewProducto.buscarGetProducto();
                if (producto == null) {
                    System.out.println("--------------------------");
                    System.out.println("No existe el producto");
                    System.out.println("--------------------------");
                    continue;
                }
                System.out.print("Cantidad: ");
                cantidad = leer.nextInt();
                System.out.println("Desea seguir añadiendo productos(SI /NO)");
                op = leer.next();  
                itemFactura.add(new ItemFactura(cantidad, producto));           
            }catch(Exception e){
                System.out.println("Error");
            }
        }
        
        return itemFactura;
    }

    
}
