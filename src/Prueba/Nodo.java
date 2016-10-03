
package Prueba;
/**
 *
 * @author Zuleyka Guzman, Jazmin Valentin, Ingrid Lopez, Gabriela Martinez
 */
public class Nodo<T>{
    /**
     * Este es el dato que va a contener el nodo
     */
    public T dato;
    /**
     * Esta es la liga para el siguiente nodo
     */
    public Nodo<T> siguiente;
    /**
     * Este es el constructor por default, inicializa las variables dato y siguiente en null
     **/
    public Nodo(){
        dato = null;
	siguiente = null;
	}
    /**
     * Este es el constructor que inicializa el dato 
     * en un valor especifico
     * @param dato es el dato a inicializar
     */
    public Nodo(T dato){
        this.dato = dato;
        siguiente = null;
    }
    /**
     * Este es el get de la propiedad dato
     * @return un dato de tipo Nodo
     */
    public T getDato() {
        return dato;
    }
    /**
     * Este es el set de la propiedad dato
     * @param dato 
     */
    public void setDato(T dato) {
        this.dato = dato;
    }
    /**
     * Este es el get de la propiedad Nodo
     * @return el siguiente Nodo
     */
    public Nodo<T> getSiguiente(){
        return this.siguiente;
    }
    /**
     * Este es el set de la propiedad Nodo
     * @param siguiente
     */
    public void setSiguiente(Nodo<T> siguiente){
        this.siguiente = siguiente;
    }
}


