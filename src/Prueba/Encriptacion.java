
package Prueba;

import java.math.BigInteger;
import java.util.Random;
/**
 * 
 * @author Zuleyka Guzman, Jazmin Valentin, Ingrid Lopez, Gabriela Martinez
 */
public class Encriptacion {
    private ListaLigada<BigInteger> lista01=new ListaLigada<BigInteger>();
    private ListaLigada<BigInteger> listaAux=new ListaLigada<BigInteger>();
    private ListaLigada<BigInteger> listaAux2=new ListaLigada<BigInteger>();
    static private BigInteger mensajeEncriptado=new BigInteger("0");
    private BigInteger q,r;
    /**
     * Este metodo calcula la sumatoria de la lista w
     * @param aux un nodo de tipo BigInteger
     * @return la sumatoria de la lista
     */
    public BigInteger sumatoria(Nodo<BigInteger> aux){
        BigInteger sumatoria= new BigInteger ("0");
        while(aux!=null){
            sumatoria=sumatoria.add(aux.getDato());
            aux=aux.getSiguiente();
        }
        return sumatoria;
    }
    /**
     * Este metodo genera un numero aleatorio de tipo int entre 0 y 1000000 con la clase Random 
     * y lo convierte a BigInteger
     * @return 
     */
    public BigInteger aleatorio(){
        Random rnd=new Random();
        return new BigInteger(String.valueOf(rnd.nextInt(1000000)));

    }
    /**
    * Este metodo enera un numero aleatorio BigInteger entre 1 y q
    */
    public BigInteger aleatorios(BigInteger tope){
        Random rnd=new Random();
        BigInteger numeroAleatorio;
        do{
            numeroAleatorio=new BigInteger(tope.bitLength(),rnd);
        }while(numeroAleatorio.compareTo(tope)>=0);
        return numeroAleatorio;
    }
    /**
     * En este metodo se genera cada numero de la lista w de manera aleatoria, creando asi nuestra llave privada.
     * Luego se calculan los numeros q y r tambien de manera aleatoria y verificando que r sea un numero coprimo.
     * Despues creamos una nueva lista y generamos nuestra llave publica.
     * Y finalmente convertimos nuestro caracter en forma binaria y generamos nuestro mensaje encriptado.
     **/
    public void encriptar(char caracter){
        for(int x=0;x<8;x++){
            lista01.inserta_final(aleatorio().add(sumatoria(lista01.getInicio())));
        }

        Integer inte=new Integer(65);

        System.out.println("w="+lista01.recorreIterativo());
        System.out.println("Sum="+sumatoria(lista01.getInicio()));
        q=aleatorio().add(sumatoria(lista01.getInicio()));
        System.out.println("q="+q);

        do{
            r=aleatorios(q);
        }while(r.gcd(q).compareTo(new BigInteger("1"))!=0);
        System.out.println("r="+r);
        //System.out.println(r.gcd(q));

        /**
         * creamos una nueva lista... y generamos nuestra llave publica 
         */
        ListaLigada<BigInteger> llavePublica=new ListaLigada<BigInteger>();
        Nodo<BigInteger> aux=lista01.getInicio();
        while(aux!=null){
            llavePublica.inserta_final(aux.getDato().multiply(r).mod(q));
            aux=aux.getSiguiente();
        }
        System.out.println("Llave Publica: "+llavePublica.recorreIterativo());
        aux=llavePublica.getInicio();
        
        /**
         * convertimos nuestro caracter en forma binaria
         */
        for(int i=0;i<8;i++){
            //System.out.print((caracter & 128)==0?"0":"1");
            if((caracter&128)!=0){
                mensajeEncriptado=mensajeEncriptado.add(aux.getDato());
            }
            aux=aux.getSiguiente();
            caracter<<=1;
        }
        System.out.println("Mensaje encriptado:" + mensajeEncriptado);

    }  
    /**
     * En este metodo creamos una lista auxiiar que contiene los datos de nuestra lista w
     * solo que de manera invertida.
     * Esta lista nos servira para descencriptar el mensaje.
     */
    public void llenarNuevaLista(){
        listaAux=lista01;
        while(listaAux.getInicio()!=null){
            listaAux2.inserta_inicio(listaAux.elimina_primero()) ;
        }
        System.out.println("Lista Auxiliar:"+ listaAux2.recorreIterativo());
    }
    /**
     * En este metodo se desencripta el mensaje creando una variable y de tipo BigInteger, 
     * a la cual se leasigna un valor segun la formula necesaria para desencriptar nuestro mensaje.
     * Despues comparamos cada uno de los elementos de la lista auxiliar con y, 
     * si es menor se resta "y" y se le agrega un 1 a la cadena s, si no se agrega un 0
     * Despues sacamos un valor en codigo ascci de esa cadena y posteriormente convertimos
     * ese valor a un dato de tipo char con lo cual desencriptamos nuestro caracter.
     */
    public void desencriptar(){
       Nodo <BigInteger> aux=new Nodo<BigInteger>();
       String s="",cadena="";
       int valor=0;
       BigInteger y;
       y=(mensajeEncriptado.multiply(r.modInverse(q)).mod(q));
       System.out.println("y="+y);
       aux=listaAux2.getInicio();
       while(aux!=null){
           if(aux.getDato().compareTo(y)==-1 || aux.getDato().compareTo(y)==0){
               y = y.subtract(aux.getDato()) ;
               s+="1";
           }else{
               s+="0";
           }
           aux=aux.getSiguiente();
        }
        
       for(int i=0;i<s.length();i++){
           if(s.charAt(i)!='0'){
               valor+=(int)Math.pow(2,i);
           }
       }
       char x=(char) valor;
        System.out.println(valor);
        System.out.println(x);
    }
    /**
     * Este es el metodo main en el cual se mandan a llamar 
     * @param args 
     */
    public static void main(String [] args){
        Encriptacion e=new Encriptacion();
        e.encriptar('a');
        e.llenarNuevaLista();
        e.desencriptar();
    }
}

