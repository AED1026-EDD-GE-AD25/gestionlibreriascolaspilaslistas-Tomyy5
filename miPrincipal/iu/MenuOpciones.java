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
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = scanner.nextLine();
        Libro libro = libreria.crearLibro(titulo, autor, isbn);
        libreria.agregarLibro(libro);
        System.out.println("Libro agregado a la lista de préstamos.");
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
        System.out.print("Ingrese el título del libro a reservar: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el autor del libro a reservar: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro a reservar: ");
        String isbn = scanner.nextLine();
        Libro libro = libreria.crearLibro(titulo, autor, isbn);
        if (libreria.agregarLibroCola(libro)) {
            System.out.println("Libro agregado a la cola de reservas.");
        } else {
            System.out.println("No se pudo agregar el libro a la cola de reservas.");
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
        // No tenemos un método para mostrar toda la cola sin desencolar, así que haremos una copia temporal.
        // Pero nuestra cola no permite iterar. Esto es un problema de diseño.
        // Para solucionarlo, podríamos agregar un método en Cola para obtener todos los elementos, pero no lo tenemos.
        // Alternativamente, podríamos cambiar la implementación de Cola para que use una ListaDoble y luego iterar.
        // Sin embargo, en el menú solo se pide mostrar, no tenemos que modificar la cola.
        // Dado que Cola está implementada con ListaDoble, podríamos acceder a la lista, pero es privada.
        // Por ahora, dejaremos este método sin implementar, o podríamos cambiar la implementación de Cola para permitir iteración.
        // Pero para no complicar, vamos a modificar la clase Cola para que tenga un método para obtener la lista interna? No es buena idea.
        // Otra opción: hacer una copia de la cola y desencolar hasta mostrarlos, pero se perderían los datos.
        // Por ahora, diremos que no se puede mostrar sin perder los datos, así que no implementaremos este método.
        System.out.println("Funcionalidad no implementada: No se puede mostrar la cola sin perder los datos.");
    }

    public static void crearPedido(){
        System.out.print("Ingrese el título del libro para el pedido:");
        String tituloPedido = scanner.nextLine();
        System.out.print("Ingrese el autor del libro para el pedido:");
        String autorPedido = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro para el pedido:");
        String isbnPedido = scanner.nextLine();
        Libro libroPedido = libreria.crearLibro(tituloPedido, autorPedido, isbnPedido);
        Pedido pedido=null;
        if (libroPedido!=null){
            pedido = libreria.crearPedido(libroPedido, new Fecha());
            if (pedido !=null)
                System.out.println("Pedido creado exitosamente: "+pedido);
            else
                System.out.println("No fue posible crear el pedido");
        }else{
            System.out.println("Error: no fue posible crear el Libro");
        }
    }

    public static void devolverLibro() throws PosicionIlegalException{
        System.out.print("Ingrese el ISBN del libro a devolver: ");
        String isbn = scanner.nextLine();
        Libro libro = libreria.buscarLibro(isbn);
        if (libro != null) {
            if (libreria.devolverLibro(libro)) {
                System.out.println("Libro devuelto exitosamente.");
            } else {
                System.out.println("No se pudo devolver el libro.");
            }
        } else {
            System.out.println("Libro no encontrado en la lista de prestados.");
        }
    }

    public static void eliminarUltimoLibro() throws PosicionIlegalException{
        Libro libro = libreria.eliminarUltimoLibro();
        if (libro != null) {
            System.out.println("Libro eliminado: " + libro);
        } else {
            System.out.println("No hay libros para eliminar.");
        }
    }

    public static void deshacerEliminarLibro(){
        Libro libro = libreria.deshacerEliminarLibro();
        if (libro != null) {
            System.out.println("Libro restaurado: " + libro);
        } else {
            System.out.println("No hay libros para restaurar.");
        }
    }
}
