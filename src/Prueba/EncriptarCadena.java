
package Prueba;
import java.math.BigInteger;
/**
 * En esta clase se encripta una cadena.
 * @author Zuleyka Guzman, Jazmin Valentin, Ingrid Lopez, Gabriela Martinez
 */
public class EncriptarCadena {
    static private ListaLigada<Character> listaCadena= new ListaLigada<Character>();
    static private ListaLigada<BigInteger> mensajeEn= new ListaLigada<BigInteger>();
    private Nodo<Character> aux= new Nodo<Character>();
    private Encriptacion enc=new Encriptacion();  //EncriptacionCaracter
    /**
     * Este es el metodo generar() en el cual se manda a llamar al metodo generarLLaves() de la clase Encriptacion
     */
    public void generar(){
        enc.generarLLaves();
    }
    /**
     * Este es el metodo encriptar() en el que guardamos cada caracter de la cadena en una lista de tipo Character,
     * para posteriormente recorrer esta lista e ir encriptando cada elemento de la misma.
     * Los mensajes encriptados se van almacenando en una lista.
     * @param cadena a encriptar
     */
    public void encriptarCadena(String cadena){
       // enc.generarLLaves();
       String s="";
        for (int x=0;x<cadena.length();x++){
            listaCadena.inserta_final(cadena.charAt(x));
        }
        aux=listaCadena.getInicio();
        while(aux!=null){
            enc.msjEncriptado(aux.getDato());
            /**enc.desencriptar(); // sin esta linea se guardan nuestros mensajes encriptados y con la linea no se guardan nuestros 
            * mensajes encriptados pero se encripta y desencripta en el mismo metodo, pues no hemos conseguido desencriptar en 
            * un metodo aparte
            **/
            s+=enc.getC();
            mensajeEn.inserta_final(enc.getMensajeEncriptado());
            aux=aux.getSiguiente();
        }
    }
    
    /**public void desencriptar(){
        Nodo<BigInteger> aux1= new Nodo<BigInteger>();
        aux1=mensajeEn.getInicio();
        while(aux1!=null){
            //enc.desencriptar(aux1.getDato());
            aux1=aux1.getSiguiente();
        }
    }**/
    
    /**
     * Este es el metodo main en el cual se manda a llamar al metodo encriptarCadena() al cual se le pasa como parametro
     * la cadena "Hola mundo" y despues se imprime la lista de mensajes encriptados
     * @param args 
     */
    public static void main(String[] args) {
        EncriptarCadena en=new EncriptarCadena();
        en.generar();
        en.encriptarCadena("Hola Mundo");
        System.out.println("\n");
        System.out.println("Lista de mensajes encriptados: " + mensajeEn.recorreIterativo());
        //en.desencriptar();
    }
}
