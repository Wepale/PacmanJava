package Inicio.Controlador;
import Inicio.Modelo.Fantasmas;
import Inicio.Modelo.Clyde;
import Inicio.Modelo.Coordenadas;
import Inicio.Modelo.PacMan;
import Inicio.Modelo.Tablero;
import Inicio.Vista.AyudaDibujo;
/**
 * Esta clase sera la encargada de realizar los movimientos en el tablero matématico del juego (la matriz) de los fantasmas y trasladar estos cambios
 * a la vista
 * 
 * @author Yeray Rodriguez Martin 
 * @version 1.0
 */
public class ControladorFantasmas extends Controlador 
{
    private boolean controlCaja;                    //Para saber si hay que calcular un nuevo movimiento cuando el fantasma se encuentra dentro de la caja
    private boolean aSalido;                        //Para saber si el fantasma a salido de la caja de fantasmas
    private boolean buscarCasillaAnterior;          //Para saber si he de buscar la casilla anterior para comenzar un cambio de direccion
    private boolean continuarMovimiento;            //Para saber si se debe continuar con el mismo sentido el movimiento
    private boolean primerMovimiento;               //Para saber si es el primer movimiento de Clyde cunado sale de la caja
    private int direccion;                          //Para saber en que direccion tienen que desplazarse los fantasmas en caso de colision
    private int sentidoClyde;                       //El sentido en el que circulara Clyde
    
    /**
     * Constructor
     * Inicializa los campos
     */
    public ControladorFantasmas()
    {
        super();
        controlCaja=true;
        aSalido=false;
        buscarCasillaAnterior=true;
        direccion=0;
        primerMovimiento=true;
    }
    
                                        //METODOS DE ACCESO
    /**
     * Devuelve el campo aSalido
     * 
     * @return el campo aSalido
     */
    public boolean getASalido()
    {
        return aSalido;
    }
    /**
     * Modifica el campo aSalido
     * 
     * @param x True o false
     */
    public void setASalido(boolean x){
        aSalido=x;
    }
    /**
     * Modifica el campo controlCaja
     * 
     * @param x True o false
     */
    public void setControlCaja(boolean x)
    {
        controlCaja=x;
    }
    /**
     * Devuelve el campo buscarCasillaAnterior
     * 
     * @return el campo buscarCasillaAnterior
     */
    public boolean getBuscarCasillaAnterior()
    {
        return buscarCasillaAnterior;
    }
     /**
     * Modifica el campo buscarCasillaAnterior
     * 
     * @param x True o false
     */
    public void setBuscarCasillaAnterior(boolean x)
    {
        buscarCasillaAnterior=x;
    }
    /**
     * Devuelve el campo buscarMoviento
     * 
     * @return el campo buscarMovimiento
     */
    public boolean getBuscarMovimiento()
    {
        return continuarMovimiento;
    }
    /**
     * Modifica el campo buscarMovimiento
     * 
     * @param x True o false
     */
    public void setBuscarMovimiento(boolean x)
    {
        continuarMovimiento=x;
    }
    /**
     * Modifica el campo primerMovimiento
     * 
     * @param x True o false
     */
    public void setPrimerMovimiento(boolean x)
    {
        primerMovimiento=x;
    }
    
                                                            //FIN METODOS DE ACCESO
                                        
    /**
     * Calcula el siguiente movimiento que debe hacer el fantasma para acercarse a Pacman y almacena la casilla a la que debe diriguirse y
     * la casilla en la que se encuentra en ese momento
     * 
     * @param fantasma El fantasma sobre el que queremos calcular el siguiente movimiento
     * @param pacman Nuestro Pacman para saber donde se encuentra
     * @param tablero Nuestro tablero matemático para circular correctamente por el
     * 
     */
    public void calcularNextMovimiento(Fantasmas fantasma, PacMan pacman, Tablero tablero)
    {
        posicionActual.setPosicion(fantasma.getPosicion());
        fantasma.calcularMovimiento(pacman,tablero);
        posicionNext.setPosicion(fantasma.getPosicion());
    }
    
    
    /**
     * Combina el método de la superclase "dibujarNextPixel" con el método "calculaNextMovimiento". Una vez calcula la posicion a la que tiene
     * que desplazarse no volverá a calcular la siguiente posición hasta que se haya desplazado totalmente.
     * 
     * @param fantasma El fantasma sobre el que queremos calcular el siguiente movimiento
     * @param pacman Nuestro Pacman para saber donde se encuentra
     * @param tablero Nuestro tablero matemático para circular correctamente por el
     */
    public void dibujarNextPosicion(Fantasmas fantasma, PacMan pacman,Tablero tablero)
    {
        if(controlMovimiento){
            calcularNextMovimiento(fantasma, pacman, tablero);
            controlMovimiento=false;
        }
        dibujarNextPixel(posicionActual, posicionNext);
        if(desplazamientoX==21){
            resetearDesplazamientoX();
            controlMovimiento=true;
        }
        if(desplazamientoY==21){
            resetearDesplazamientoY();
            controlMovimiento=true;
        }
    }
    
    /**
     * Este método será especial para Clyde. Cuando Clyde llegue a un cruce, tendrá las misma posibilidades de escoger cualquier dirección,
     * exepto cambiar su sentido.
     * 
     * @param clyde El fantasma clyde
     * @param pacman Nuestro pacman, no lo necesitamos realmente, pero tenemos que pasarlo como parámetro al método abstracto
     *               de Clyde "calcularMovimiento(PacMan pacman, Tablero tablero)"
     * @param tablero Nuestra matriz
     */

    public void dibujarNextPosicionAleatoria(Clyde clyde, PacMan pacman, Tablero tablero)
    {
        if(primerMovimiento){
            posicionActual.setPosicion(clyde.getPosicion());
            clyde.calcularMovimiento(pacman, tablero);
            posicionNext.setPosicion(clyde.getPosicion());
            primerMovimiento=false;
            controlMovimiento=false;
        }
        if(controlMovimiento){
            sentidoClyde= posicionActual.porDondeVenia(clyde.getPosicion());
            clyde.setSentido(sentidoClyde);
            posicionActual.setPosicion(clyde.getPosicion());
            clyde.calcularMovimiento(pacman, tablero);
            posicionNext.setPosicion(clyde.getPosicion());
            controlMovimiento=false;
        }
        dibujarNextPixel(posicionActual, posicionNext);
        if(desplazamientoX==21 || desplazamientoY==21){
            resetearDesplazamientoX();
            resetearDesplazamientoY();
            controlMovimiento=true;
        }
        
    }
    
    /**
     * Este método será el encargado de, cuando los fantasma colisionen, hacer que cambien de dirección hasta que se encuentren con un muro.
     * La bandera buscarCasillaAnterior me indicará si es una nueva colision, por lo que tendre que buscar la direccion de la cual venía
     * y hacer circular al fantasma justo al contrario. Si se puede realizar el movimiento, se realizara. En caso de que haya un muro, no se
     * moverá y tomará de nuevo su movimiento natural 
     * 
     * @param El fantasma que queremos hacer cambiar de dirección
     */
    public void cambiarDireccion(Fantasmas fantasma)
    {
        if(buscarCasillaAnterior){
            direccion= posicionActual.porDondeVenia(posicionNext);
            buscarCasillaAnterior=false;
            continuarMovimiento=true;
        }
        if(continuarMovimiento){
            switch(direccion){
                case 1:                 //Circulaba hacia abajo, por lo que tiene que ir hacia arriba
                    if(fantasma.movimientoValido(fantasma.moverArriba(fantasma.getPosicion()))){
                        posicionActual.setPosicion(fantasma.getPosicion());
                        fantasma.setPosicion(fantasma.moverArriba(fantasma.getPosicion()));
                        posicionNext.setPosicion(fantasma.getPosicion());
                        continuarMovimiento=false;
                    }else{
                        posicionActual.setPosicion(fantasma.getPosicion());
                        posicionNext.setPosicion(fantasma.getPosicion());
                        buscarCasillaAnterior=true;
                        controlMovimiento=true;
                    }
                    break;
                case 2:                 //Circulaba hacia arriba, por lo que tiene que ir hacia abajo
                    if(fantasma.movimientoValido(fantasma.moverAbajo(fantasma.getPosicion()))){
                        posicionActual.setPosicion(fantasma.getPosicion());
                        fantasma.setPosicion(fantasma.moverAbajo(fantasma.getPosicion()));
                        posicionNext.setPosicion(fantasma.getPosicion());
                        continuarMovimiento=false;
                    }else{
                        posicionActual.setPosicion(fantasma.getPosicion());
                        posicionNext.setPosicion(fantasma.getPosicion());
                        buscarCasillaAnterior=true;
                        controlMovimiento=true;
                    }
                    break;
                case 3:                 //Circulaba hacia la derecha, por lo que tengo que ir hacia la izquierda
                    if(fantasma.movimientoValido(fantasma.moverIzquierda(fantasma.getPosicion()))){
                        posicionActual.setPosicion(fantasma.getPosicion());
                        fantasma.setPosicion(fantasma.moverIzquierda(fantasma.getPosicion()));
                        posicionNext.setPosicion(fantasma.getPosicion());
                        continuarMovimiento=false;
                    }else{
                        posicionActual.setPosicion(fantasma.getPosicion());
                        posicionNext.setPosicion(fantasma.getPosicion());
                        buscarCasillaAnterior=true;
                        controlMovimiento=true;
                    }    
                    break;
                case 4:                //Circulaba hacia la izquierda, por lo que tengo que ir hacia la derecha
                    if(fantasma.movimientoValido(fantasma.moverDerecha(fantasma.getPosicion()))){
                        posicionActual.setPosicion(fantasma.getPosicion());
                        fantasma.setPosicion(fantasma.moverDerecha(fantasma.getPosicion()));
                        posicionNext.setPosicion(fantasma.getPosicion());
                        continuarMovimiento=false;
                    }else{
                        posicionActual.setPosicion(fantasma.getPosicion());
                        posicionNext.setPosicion(fantasma.getPosicion());
                        buscarCasillaAnterior=true;
                        controlMovimiento=true;
                    }
                    break;
                }
            }
        dibujarNextPixel(posicionActual, posicionNext);
        if(desplazamientoX==21 || desplazamientoY==21){
            resetearDesplazamientoX();
            resetearDesplazamientoY();
            continuarMovimiento=true;
        }
    
    }
    
    /**
     * Este método sirve para hacer salir a los fantasmas de la caja de fantasmas
     * 
     * @param fantasma El fantasma que queremos hacer salir
     */
    public void salirCasaDeFantasmas(Fantasmas fantasma)
    {
        //Fila final en la que los fantasmas estarán fuera de la caja
        int filaFinal=11;
                                                               
        if(controlCaja==true && aSalido==false){
             posicionActual.setPosicion(fantasma.getPosicion());
             posicionNext.setPosicion(fantasma.moverArriba(posicionActual));
             fantasma.setPosicion(posicionNext);
             controlCaja=false;
        }
        if(posicionActual.getFila()==filaFinal){
             aSalido=true;
             fantasma.setPosicion(posicionActual);
             primerMovimiento=true;
        }
        if(aSalido==false){
            dibujarNextPixel(posicionActual, posicionNext);
        }
        if(desplazamientoX==21 || desplazamientoY==21){
             resetearDesplazamientoX();
             resetearDesplazamientoY();
             controlCaja=true;
        }
    }
}