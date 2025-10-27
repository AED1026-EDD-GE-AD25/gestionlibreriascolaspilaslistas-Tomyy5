package listaDoble;

public class ListaDoble<T> {
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamanio;

    public ListaDoble() {
cabeza = null;
    cola = null;
        tamanio = 0;
    }
public boolean esVacia() {
        return cabeza == null;
    }

    
    
    public int getTamanio() {
   return tamanio;
    }

    public void agregar(T valor) {
        Nodo<T> nuevo = new Nodo<>();
        nuevo.setValor(valor);

        if (esVacia()) {
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            
        nuevo.setAnterior(cola);
            cola = nuevo;
        }
        tamanio++;
    }

    
    
    



    public void insertar(T valor, int pos) throws PosicionIlegalException {
        if (pos < 0 || pos > tamanio) {
            
            throw new PosicionIlegalException();
        }

        Nodo<T> nuevo = new Nodo<>();
        nuevo.setValor(valor);

           if (pos == 0) {
            if (esVacia()) {
                cabeza = nuevo;
                cola = nuevo;
        } else {
                nuevo.setSiguiente(cabeza);
                cabeza.setAnterior(nuevo);
                
                cabeza = nuevo;

            }
        } else if (pos == tamanio) {
            agregar(valor);
            return; 
        } else {
            Nodo<T> aux = cabeza;
            for (int i = 0; i < pos - 1; i++) {
                aux = aux.getSiguiente();
            }
            
            
            Nodo<T> siguiente = aux.getSiguiente();
            aux.setSiguiente(nuevo);
            
        nuevo.setAnterior(aux);
            nuevo.setSiguiente(siguiente);
            siguiente.setAnterior(nuevo);
        }
        tamanio++;
    }

    
    
    
    public T remover(int pos) throws PosicionIlegalException {
        if (pos < 0 || pos >= tamanio) {
            throw new PosicionIlegalException();
        }

        T valor;
        if (pos == 0) {
            valor = cabeza.getValor();
            cabeza = cabeza.getSiguiente();
           
        if (cabeza != null) {
                cabeza.setAnterior(null);
            } else {
                cola = null;
            }
        
        
    } else if (pos == tamanio - 1) {
            valor = cola.getValor();
            cola = cola.getAnterior();
            cola.setSiguiente(null);
        } else {
            Nodo<T> aux = cabeza;
            for (int i = 0; i < pos; i++) {
                aux = aux.getSiguiente();
            }
            
            
            valor = aux.getValor();
            Nodo<T> anterior = aux.getAnterior();
            Nodo<T> siguiente = aux.getSiguiente();
            anterior.setSiguiente(siguiente);
            siguiente.setAnterior(anterior);
        }
        tamanio--;
        return valor;
    }

    
    
public int remover(T valor) throws PosicionIlegalException {
        int pos = 0;
        Nodo<T> aux = cabeza;
        while (aux != null) {
            if (aux.getValor().equals(valor)) {
        if (pos == 0) {
                cabeza = cabeza.getSiguiente();
                    
                
                if (cabeza != null) {
                        cabeza.setAnterior(null);
                    } else {
                        cola = null;
                    }
             } else if (pos == tamanio - 1) {
                    cola = cola.getAnterior();
                    cola.setSiguiente(null);
                } else {
            Nodo<T> anterior = aux.getAnterior();
                    Nodo<T> siguiente = aux.getSiguiente();
                    anterior.setSiguiente(siguiente);
                    siguiente.setAnterior(anterior);
                }
                tamanio--;
                return pos;
            }
            aux = aux.getSiguiente();
            pos++;
        }
        return -1;
    }

   
   
   
    public T getValor(int pos) throws PosicionIlegalException {
        if (pos < 0 || pos >= tamanio) {
            throw new PosicionIlegalException();
        }

        Nodo<T> aux = cabeza;
        for (int i = 0; i < pos; i++) {
            aux = aux.getSiguiente();
        }
       
        return aux.getValor();
    }



    public void limpiar() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

public boolean contiene(T valor) {
        Nodo<T> aux = cabeza;
        while (aux != null) {
            if (aux.getValor().equals(valor)) {
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Nodo<T> aux = cabeza;
        while (aux != null) {
            sb.append(aux.getValor().toString()).append(" <-> ");
            
            aux = aux.getSiguiente();
        }
        sb.append("null");
        return sb.toString();
    }
}