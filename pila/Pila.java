package pila;

import listaDoble.ListaDoble;
import listaDoble.PosicionIlegalException;



public class Pila<T> {
private ListaDoble<T> lista;

        
        public Pila() {
        lista = new ListaDoble<>();
    }

    
    
    
public void apilar(T valor) {
    try {
            lista.insertar(valor, lista.getTamanio());
        } catch (PosicionIlegalException e) {
          
        }
    }

    
    
    public T retirar() {
if (estaVacia()) {
            return null;
        }
        try {
    
            return lista.remover(lista.getTamanio() - 1);
        } catch (PosicionIlegalException e) {
            return null;
        }
    }

    
    
    public T cima() {
        if (estaVacia()) {
            return null;
        }
        
        try {
            return lista.getValor(lista.getTamanio() - 1);
    } catch (PosicionIlegalException e) {
            return null;
        }
    }

    
    
    
    public boolean estaVacia() {
        
        return lista.esVacia();
    }

   
   
   
   
   
    public int tamanio() {
        return lista.getTamanio();
    }
}