
package Prueba;

import java.math.BigInteger;
import java.util.Random;
/**
 * 
 * @author Zuleyka Guzman, Jazmin Valentin, Ingrid Lopez, Gabriela Martinez
 */
public class Encriptacion{
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
    * Genera un numero aleatorio BigInteger entre 1 y q
    */
    public BigInteger aleatorios(BigInteger tope){
        Random rnd=new Random();
        BigInteger numeroAleatorio;
        
        do{
            numeroAleatorio=new BigInteger(tope.bitLength(),rnd);
        }
        while(numeroAleatorio.compareTo(tope)>=0);
        
        return numeroAleatorio;
    }
    
    public void encriptar(){
        
        ListaLigada<BigInteger> lista01=new ListaLigada<BigInteger>();
        BigInteger q,r;

        for(int x=0;x<8;x++){
            lista01.inserta_final(aleatorio().add(sumatoria(lista01.getInicio())));
        }
        Integer inte=new Integer(65);

        System.out.println("w="+lista01.recorreIterativo());
        //System.out.println("Sum="+sumatoria(lista01.getInicio()));
        q=aleatorio().add(sumatoria(lista01.getInicio()));
        System.out.println("q="+q);

    /**
    * do while para que siempre sea un numero coprimo de q
    */
        do{
            r=aleatorios(q);
        }
        while(r.gcd(q).compareTo(new BigInteger("1"))!=0);
        
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
        
        System.out.println("LLave Publica: "+llavePublica.recorreIterativo());
        aux=llavePublica.getInicio();
        /**
        * convertimos nuestros caracteres en forma binaria
        */
        char caracter='h';
        
        BigInteger mensajeEncriptado=new BigInteger("0");
        
        //System.out.println((int)caracter);
        for(int i=0;i<8;i++){
          //  System.out.print((caracter & 128)==0?"0":"1");
            
            if((caracter&128)!=0){
                mensajeEncriptado=mensajeEncriptado.add(aux.getDato());
            }
            aux=aux.getSiguiente();
            caracter<<=1;
        }
        
        System.out.println("Mensaje encriptado: " + mensajeEncriptado); 
    }
    /**
     * Este es el metodo main de la clase en donde se manda a llamar al metodo encriptar
     * @param args 
     */
    public static void main(String [] args){
        
        Encriptacion enc = new Encriptacion();
        
        enc.encriptar();

    }
}


