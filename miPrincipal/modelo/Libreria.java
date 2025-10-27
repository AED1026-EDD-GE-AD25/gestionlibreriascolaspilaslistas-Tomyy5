package miPrincipal.modelo;

import listaDoble.ListaDoble;
import listaDoble.PosicionIlegalException;
import pila.Pila;
import cola.Cola;
import utilerias.Fecha;
import miPrincipal.servicio.ServicioDatos;
import java.util.Scanner;

public class Libreria {
    ServicioDatos dataService;
    ListaDoble<Libro> listaLibros;
    Cola<Libro> colaLibros;
    Pila<Libro> pilaLibrosEliminados;
    ListaDoble<Pedido> listaPedidos;
    Scanner scanner; 

    public Libreria(){
        dataService = new ServicioDatos();
        scanner = new Scanner(System.in);
        listaLibros = new ListaDoble<>();
        colaLibros = new Cola<>();
        pilaLibrosEliminados = new Pila<>();
        listaPedidos = new ListaDoble<>();
    }

    // Método para agregar un libro a la lista de prestamos (libros prestados)
    public void agregarLibro(Libro libro) {
        listaLibros.agregar(libro);
    }

    // Método para obtener la lista de libros prestados
    public ListaDoble<Libro> obtenerLibros() {
        return listaLibros;
    }

    // Método para agregar un libro a la cola de reservas
    public boolean agregarLibroCola(Libro libro) {
        colaLibros.encolar(libro);
        return true;
    }

    // Método para obtener el siguiente libro de la cola de reservas (y removerlo)
    public Libro obtenerLibroCola() {
        return colaLibros.desencolar();
    }

    // Método para obtener el libro de la pila de eliminados (sin remover)
    public Libro obtenerLibroPila() {
        return pilaLibrosEliminados.cima();
    }

    // Método para mostrar la cola de reservas (no está en el diagrama, pero se usa en el menú)
    public Cola<Libro> mostrarReservaLibros() {
        return colaLibros;
    }

    // Método para crear un libro (no está en el diagrama, pero se usa en MenuOpciones)
    public Libro crearLibro(String titulo, String autor, String isbn) {
        return new Libro(titulo, autor, isbn);
    }

    // Método para crear un pedido
    public Pedido crearPedido(Libro libro, Fecha fecha) {
        Pedido pedido = new Pedido(libro, fecha);
        listaPedidos.agregar(pedido);
        return pedido;
    }

    // Método para devolver un libro (lo remueve de la lista de prestados)
    public boolean devolverLibro(Libro libro) {
        try {
            int pos = listaLibros.remover(libro);
            return pos != -1;
        } catch (PosicionIlegalException e) {
            return false;
        }
    }

    // Método para eliminar el último libro prestado (lo remueve de la lista y lo apila en eliminados)
    public Libro eliminarUltimoLibro() throws PosicionIlegalException {
        if (listaLibros.esVacia()) {
            return null;
        }
        Libro libro = listaLibros.remover(listaLibros.getTamanio() - 1);
        pilaLibrosEliminados.apilar(libro);
        return libro;
    }

    // Método para deshacer la eliminación (saca de la pila de eliminados y lo agrega a la lista)
    public Libro deshacerEliminarLibro() {
        Libro libro = pilaLibrosEliminados.retirar();
        if (libro != null) {
            listaLibros.agregar(libro);
        }
        return libro;
    }

    // Método para buscar un libro por ISBN en la lista de prestados
    public Libro buscarLibro(String isbn) {
        try {
            for (int i = 0; i < listaLibros.getTamanio(); i++) {
                Libro libro = listaLibros.getValor(i);
                if (libro.getIsbn().equals(isbn)) {
                    return libro;
                }
            }
        } catch (PosicionIlegalException e) {
            return null;
        }
        return null;
    }
}