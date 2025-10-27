package cola;

import listaDoble.ListaDoble;
import listaDoble.PosicionIlegalException;

public class Cola<T> {
    private ListaDoble<T> lista;

    public Cola() {
        lista = new ListaDoble<>();
    }

    public void encolar(T valor) {
        lista.agregar(valor);
    }

    public T desencolar() {
        if (estaVacia()) {
            return null;
        }
        try {
            return lista.remover(0);
        } catch (PosicionIlegalException e) {
            return null;
        }
    }

    public T frente() {
        if (estaVacia()) {
            return null;
        }
        try {
            return lista.getValor(0);
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