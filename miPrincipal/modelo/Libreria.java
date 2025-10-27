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

    
    public void agregarLibro(Libro libro) {
    listaLibros.agregar(libro);
    }

    public ListaDoble<Libro> obtenerLibros() {
        
        return listaLibros;
    }

   
    public boolean agregarLibroCola(Libro libro) {
           colaLibros.encolar(libro);
        return true;
    }

    
    
    public Libro obtenerLibroCola() {
        Libro libro = colaLibros.desencolar();
        if (libro != null) {
            System.out.println("libro obtenido de reservas: " + libro.getTitulo());
        }
        return libro;
    }




   public Libro obtenerLibroPila() {
       return pilaLibrosEliminados.cima();
   }

   public Cola<Libro> mostrarReservaLibros() {
        return colaLibros;
    }

    
    
    
    
    public Libro crearLibro(String titulo, String autor, String isbn) {
    return new Libro(titulo, autor, isbn);
    }

   
         public Pedido crearPedido(Libro libro, Fecha fecha) {
        Pedido pedido = new Pedido(libro, fecha);
        listaPedidos.agregar(pedido);
        return pedido;
    }

    
      public boolean devolverLibro(Libro libro) {
        try {
            int pos = listaLibros.remover(libro);
            return pos != -1;
        } catch (PosicionIlegalException e) {
            return false;
        }
    }

    
    public Libro eliminarUltimoLibro() throws PosicionIlegalException {
        if (listaLibros.esVacia()) {
                
            return null;
        
        }
        Libro libro = listaLibros.remover(listaLibros.getTamanio() - 1);
        pilaLibrosEliminados.apilar(libro);
        return libro;
    }

    
public Libro deshacerEliminarLibro() {
        Libro libro = pilaLibrosEliminados.retirar();
        if (libro != null) {
            listaLibros.agregar(libro);
        }
          
        return libro;
    }

    
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