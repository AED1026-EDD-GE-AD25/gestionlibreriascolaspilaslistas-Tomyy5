package miPrincipal.iu;
import miPrincipal.servicio.ServicioDatos;
import miPrincipal.modelo.Libro;
import miPrincipal.modelo.Pedido;
import miPrincipal.modelo.Libreria;
import java.util.Scanner;
import utilerias.Fecha;
import listaDoble.ListaDoble;
import listaDoble.PosicionIlegalException;
import cola.Cola;
import pila.Pila;

import java.util.Scanner;

public class MenuOpciones{
    static Scanner scanner = new  Scanner(System.in);
    static private Libreria libreria = new Libreria();

    
    public static void agregarLibro(){
        System.out.print("ingrese título del libro: ");
        
        String titulo = scanner.nextLine();
        System.out.print("ingrese autor del Llibro: ");
        
        String autor = scanner.nextLine();
    System.out.print("ingreseISBN del libro: ");
        String isbn = scanner.nextLine();
        
        Libro libro = libreria.crearLibro(titulo, autor, isbn);
        libreria.agregarLibro(libro);
        System.out.println("libro agregado a la lista de  prestamos");
    }
    
    
    
    public static void mostrarLibros() throws PosicionIlegalException{
        ListaDoble<Libro> libros = libreria.obtenerLibros();
        
        if (libros.esVacia()) {
        System.out.println("No hay libros prestados.");
        } else {
            System.out.println("Libros prestados:");
            for (int i = 0; i < libros.getTamanio(); i++) {
        System.out.println(libros.getValor(i));
            }
        }
    }

    
    
    
public static void agregarLibroCola(){
        System.out.print("ingrese el título del libro a reservar: ");
        String titulo = scanner.nextLine();
    System.out.print("ingrese el autor del libro a rieservar: ");
        String autor = scanner.nextLine();
          
          System.out.print("ingrese ISBN del libro a reservar: ");
        String isbn = scanner.nextLine();
        Libro libro = libreria.crearLibro(titulo, autor, isbn);
        
        
             if (libreria.agregarLibroCola(libro)) {
            System.out.println("libro agregado a la cola de reservas");
        } else {
            System.out.println("no se pudo agregar el libro a la cola de reservas  ");
        }
    }

    
    
    public static void obtenerLibroCola(){
        Libro libro = libreria.obtenerLibroCola();
        if (libro != null) {
            System.out.println("Libro obtenido de la cola: " + libro);
    } else {
            System.out.println("No hay libros en la cola de reservas.");
    }
    }

    
    
    public static void mostrarReservaLibros(){
        Cola<Libro> cola = libreria.mostrarReservaLibros();
        System.out.println("no se puede mostrar la cola sin perder los datos");
    }

    
    public static void crearPedido(){
        System.out.print("Iingrese el título del libro para el pedido:");
        String tituloPedido = scanner.nextLine();

    System.out.print("ingrese el autor del libro para el pedido:");
        String autorPedido = scanner.nextLine();
        
        System.out.print("ingrese el ISBN del libro para el pedido:");
        String isbnPedido = scanner.nextLine();
        Libro libroPedido = libreria.crearLibro(tituloPedido, autorPedido, isbnPedido);
        Pedido pedido=null;
        if (libroPedido!=null){
            pedido = libreria.crearPedido(libroPedido, new Fecha());
            if (pedido !=null)
                System.out.println("pPedido creado exitosamente: "+pedido);
            else
                System.out.println("nofue posible crear el pedido");
        }else{
            System.out.println("error: no fue posible crear elLibro");
        }
    }

    
    public static void devolverLibro() throws PosicionIlegalException{
        System.out.print("ingrese el ISBN del libro a devolver: ");
    String isbn = scanner.nextLine();
        Libro libro = libreria.buscarLibro(isbn);
        if (libro != null) {
    if (libreria.devolverLibro(libro)) {
                
        System.out.println("libro devuelto exitosamente");
            } else {
                System.out.println("Nno se pudo devolver el libro");
            }
        } else {
            System.out.println("Llibro no encontrado en la lista de prestados");
        }
    }

    public static void eliminarUltimoLibro() throws PosicionIlegalException{
        Libro libro = libreria.eliminarUltimoLibro();
        
        if (libro != null) {
         System.out.println("libro eliminado: " + libro);
        } else {
            System.out.println("nohay libros para eliminar");
        }
    }

    public static void deshacerEliminarLibro(){
        Libro libro = libreria.deshacerEliminarLibro();
        if (libro != null) {
        
    System.out.println("libro restaurado: " + libro);
        } else {
            
            System.out.println("no hay libros para restaurar");
        }
    }
}
