
package Prueba;
/**
 *
 * @author Zuleyka Guzman, Jazmin Valentin, Ingrid Lopez, Gabriela Martinez
 */
public class ListaLigada<T> {
     /**
     * Este es el constructor de la clase
     * Inicializa la varible inicio en null
     */
    public ListaLigada(){
        inicio=null;
    }
    /**
     * Este es el inicio de la lista
     */
    private Nodo<T> inicio;
    /**
     * Este es el get de inicio
     * @return un dato de tipo Nodo
     */
    public Nodo<T> getInicio() {
        return inicio;
    }
    /**
     * Este es el set de inicio
     * @param inicio 
     */
    public void setInicio(Nodo<T> inicio) {
        this.inicio = inicio;
    }
    /**
     * Este metodo inserta un elemento al inicio de la lista.
     * @param dato 
     */
    public void inserta_inicio(T dato){
        Nodo aux = new Nodo(dato); 
        aux.setSiguiente(inicio);
        inicio=aux;
    }
    /**
     * Este metodo agrega un nodo al final de la lista.
     * @param dato 
     */
    public void inserta_final(T dato){
        if(inicio == null){
            inserta_inicio(dato);
        }
        else{
            Nodo<T> aux=inicio;
        
            while(aux.getSiguiente() != null){
                aux = aux.getSiguiente();
            }
            Nodo<T> a = new Nodo<T>(dato); 
            aux.setSiguiente(a);
        }
    }
    /**
     * Este metodo elimina el primer nodo de la lista
     * @return el dato que se elimino
     */
    public T elimina_primero(){
        Nodo <T> aux = inicio;
        if (inicio == null){
           System.out.println ("Lista vacía.");
        }else{
            inicio = inicio.getSiguiente();
            aux.setSiguiente(null);  
        }
        return aux.getDato();
    }
    /**
     * Este metodo recorre la lista
     * @return cada nodo de la lista
     */
    public String recorreIterativo(){
        Nodo aux = inicio;
        String s = " ";
        while(aux!=null){
           s+= "|"+aux.getDato()+"|\t";
            aux = aux.getSiguiente();
        }
        return s;
    }
    /**
     * Este metodo sobreescribe el metodo toString de la clase Object
     * @return un nodo de la lista
     */  
    public String toString(){
        return recorreIterativo();
    }
}

