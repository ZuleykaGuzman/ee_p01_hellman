
package Prueba;
import java.math.BigInteger;
/**
 * En esta clase se encripta una cadena.
 * @author Zuleyka Guzman, Jazmin Valentin, Ingrid Lopez, Gabriela Martinez
 */
public class EncriptarCadena {
    static private ListaLigada<Character> listaCadena= new ListaLigada<Character>();
    static private ListaLigada<BigInteger> mensajeEncriptado= new ListaLigada<BigInteger>();
    private Nodo<Character> aux= new Nodo<Character>();
    private Encriptacion enc=new Encriptacion();  //EncriptacionCaracter
    /**
     * Este es el metodo encriptar() en el cual se manda a llamar al metodo generarLLaves() de la clase Encriptacion
     * Luego de esto guardamos cada caracter de la cadena en una lista de tipo Character,
     * para posteriormente recorrer esta lista e ir encriptando cada elemento de la misma.
     * Los mensajes encriptados se van almacenando en una lista.
     * @param cadena a encriptar
     */
    public void encriptarCadena(String cadena){
        enc.generarLLaves();
        for (int x=0;x<cadena.length();x++){
            listaCadena.inserta_final(cadena.charAt(x));
        }
        aux=listaCadena.getInicio();
        while(aux!=null){
            enc.msjEncriptado(aux.getDato());
            mensajeEncriptado.inserta_final(enc.getMensajeEncriptado());
            enc.desencriptar();
            aux=aux.getSiguiente();
        }
    }
    
    /**
     * Este es el metodo main en el cual se manda a llamar al metodo encriptarCadena() al cual se le pasa como parametro
     * la cadena "Hola mundo" y despues se imprime la lista de mensajes encriptados
     * @param args 
     */
    public static void main(String[] args) {
        EncriptarCadena en=new EncriptarCadena();
        en.encriptarCadena("Hola Mundo");
        System.out.println("\n");
        System.out.println("Lista de mensajes encriptados: " + mensajeEncriptado.recorreIterativo());
    }
}
