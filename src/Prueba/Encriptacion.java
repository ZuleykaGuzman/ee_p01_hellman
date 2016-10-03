
package Prueba;

import java.math.BigInteger;
import java.util.Random;
/**
 *Esta clase encripta y desencripta un caracter
 * @author Zuleyka Guzman, Jazmin Valentin, Ingrid Lopez, Gabriela Martinez
 */
public class Encriptacion{
    private BigInteger q,r;
    private char c;
    private Nodo<BigInteger> aux= new Nodo<BigInteger>();
    private BigInteger mensajeEncriptado=new BigInteger("0");
    private ListaLigada<BigInteger> lista01=new ListaLigada<BigInteger>();
    private ListaLigada<BigInteger> mensaje= new ListaLigada<BigInteger>();
    private ListaLigada<BigInteger> listaAux=new ListaLigada<BigInteger>();
    private ListaLigada<BigInteger> listaAux2=new ListaLigada<BigInteger>();
    private ListaLigada<Character> listaCadena= new ListaLigada<Character>();
    private ListaLigada<BigInteger> llavePublica=new ListaLigada<BigInteger>();
    
    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }
    /**
     * Este es el get de la lista01
     * @return lista01
     */
    public ListaLigada<BigInteger> getLista01() {
        return lista01;
    }
    /**
     * Este es el set de la lista01
     * @param lista01 
     */
    public void setLista01(ListaLigada<BigInteger> lista01) {
        this.lista01 = lista01;
    }
    /**
     * Este es el get de la listaAux2
     * @return listaAux2
     */
    public ListaLigada<BigInteger> getListaAux2() {
        return listaAux2;
    }
    /**
     * Este es el set de la listaAux2
     * @param listaAux2 
     */
    public void setListaAux2(ListaLigada<BigInteger> listaAux2) {
        this.listaAux2 = listaAux2;
    }
    /**
     * Este es el get de la variable mensajeEncriptado
     * @return mensajeEncriptado
     */
    public BigInteger getMensajeEncriptado() {
        return mensajeEncriptado;
    }
    /**
     * Este es el set de la variable mensajeEncriptado
     * @param mensajeEncriptado 
     */
    public void setMensajeEncriptado(BigInteger mensajeEncriptado) {
        this.mensajeEncriptado = mensajeEncriptado;
    }
    /**
     * Este es el get de la variable q
     * @return q
     */
    public BigInteger getQ() {
        return q;
    }
    /**
     * Este es el set de la variable q
     * @param q 
     */
    public void setQ(BigInteger q) {
        this.q = q;
    }
    /**
     * Este es el get de la variable r
     * @return r
     */
    public BigInteger getR() {
        return r;
    }
    /**
     * Este es el set de la variable r
     * @param r 
     */
    public void setR(BigInteger r) {
        this.r = r;
    }
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
     * @return un numero aleatorio
     */
    public BigInteger aleatorio(){
        Random rnd=new Random();
        return new BigInteger(String.valueOf(rnd.nextInt(1000000)));

    }
    /**
     * Este metodo genera un numero aleatorio BigInteger entre 1 y q
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
     **/
    public void generarLLaves(){
        
        for(int x=0;x<8;x++){
            lista01.inserta_final(aleatorio().add(sumatoria(lista01.getInicio())));
        }

        Integer inte=new Integer(65);

        System.out.println("\n"+"\n\nw="+lista01.recorreIterativo());
        System.out.println("Sum="+sumatoria(lista01.getInicio()));
        q=aleatorio().add(sumatoria(lista01.getInicio()));
        System.out.println("q="+q);

        /**
         * do while para que siempre sea un numero coprimo de q
         */
        do{
            r=aleatorios(q);
        }while(r.gcd(q).compareTo(new BigInteger("1"))!=0);
        System.out.println("r="+r);
        System.out.println(r.gcd(q));

        /**
         * creamos una nueva lista... y generamos nuestra llave publica 
         */
        aux=lista01.getInicio();
        while(aux!=null){
            llavePublica.inserta_final(aux.getDato().multiply(r).mod(q));
            aux=aux.getSiguiente();
        }
        System.out.println("Llave Publica: "+llavePublica.recorreIterativo());
        
    }
    /**
     * En este metodo convertimos un caracter en forma binaria y generamos nuestro mensaje encriptado.
     * @param caracter a encriptar
     */
    public void msjEncriptado(char caracter){
        aux=llavePublica.getInicio();
        for(int i=0;i<8;i++){
            //System.out.print((caracter & 128)==0?"0":"1");
            if((caracter&128)!=0){
                mensajeEncriptado=mensajeEncriptado.add(aux.getDato());
            }
            aux=aux.getSiguiente();
            caracter<<=1;
        }
        System.out.println();
        
        System.out.println("Mensaje encriptado:" + mensajeEncriptado);
    }
    /**
     * En este metodo creamos una lista auxiiar que contiene los datos de nuestra lista w
     * solo que de manera invertida.
     * Esta lista nos servira para descencriptar el mensaje.
     */
    private void llenarNuevaLista(){
        listaAux=lista01;
        while(listaAux.getInicio()!=null){
            listaAux2.inserta_inicio(listaAux.elimina_primero()) ;
        }
    }
    /**
     * En este metodo se desencripta el mensaje creando una variable y de tipo BigInteger, 
     * a la cual se le asigna un valor segun la formula necesaria para desencriptar nuestro mensaje.
     * Despues comparamos cada uno de los elementos de la lista auxiliar con y, 
     * si es menor se resta "y" y se le agrega un 1 a la cadena s, si no se agrega un 0
     * Despues sacamos un valor en codigo ascci de esa cadena y posteriormente convertimos
     * ese valor a un dato de tipo char con lo cual desencriptamos nuestro caracter.
     */
    public void desencriptar(){
       llenarNuevaLista();
       Nodo <BigInteger> aux1=new Nodo<BigInteger>();
       String s="",cadena="";
       char x;
       int valor=0;
       BigInteger y;
       y=(mensajeEncriptado.multiply(r.modInverse(q)).mod(q));
       System.out.println("y="+y);
       
       aux1=listaAux2.getInicio();
       while(aux1!=null){
           if(aux1.getDato().compareTo(y)==-1 || aux1.getDato().compareTo(y)==0){
               y = y.subtract(aux1.getDato()) ;
               s+="1";
           }else{
               s+="0";
           }
           aux1=aux1.getSiguiente();
        }
        
       for(int i=0;i<s.length();i++){
           if(s.charAt(i)!='0'){
               valor+=(int)Math.pow(2,i);
           }
       }
       x=(char) valor;
        for(int i=s.length()-1;i>=0;i--){
            cadena+=s.charAt(i);
        }
        System.out.println(cadena);
        System.out.println(x);
        c=x;
        mensajeEncriptado = new BigInteger("0");
        y = new BigInteger("0");
    }
    /**
     * Este es el metodo main en el cual se mandan a llamar  al metodo generarLlaves()
     * @param args 
     */
     public static void main(String[] args) {
        Encriptacion ec=new Encriptacion();
        ec.generarLLaves();
    }
}

