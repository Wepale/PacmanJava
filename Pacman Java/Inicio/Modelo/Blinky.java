package Inicio.Modelo;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * Esta clase contiene el modelo de Blinky
 * 
 * @author Yeray Rodriguez Martin
 * @version 1.0
 */
public class Blinky extends Fantasmas
{
    //Posicion Inicial de Blinky
    private final int FILA_INICIAL=14;
    private final int COLUMNA_INICIAL=14;   
    /**
     * Crea un fantasma con la imagen de Blinky y su posición inicial en la matriz
     */
    public Blinky()
    {
        super();
        setPosicion(FILA_INICIAL, COLUMNA_INICIAL);
        setImagen(new ImageIcon(getClass().getResource("Blinky.gif")).getImage().getScaledInstance(18,18,Image.SCALE_DEFAULT));       
    }

    /**
     * Reescribimos el metodo para Blinky.Para acercarse a Pac-Man calculará la distancia e 
     * intentará primero acercarse verticalmente y luego horizontalmente. Este metodo controlara que
     * sea un movimento valido, es decir, que no se salga del laberinto
     * 
     * @param pacman Nuestro Pacman, para saber su posición
     * @param tablero El tablero para que el fantasma sepa por donde circular
     */
    public Coordenadas calcularMovimiento(PacMan pacman, Tablero tablero)
    {
        switch (dondeMoverseVertical(pacman)) {
            
            case 0: //Se encuentra en la misma fila, por lo que tiene que acercarse horizontalmente                
                if(dondeMoverseHorizontal(pacman)==1){                     //Se tiene que mover hacia la izquierda
                    if(movimientoValido(moverIzquierda(getPosicion()))==true){
                        setPosicion(moverIzquierda(getPosicion()));
                        return getPosicion();
                    }else{                                           //Movimiento Invalido.Busca una calle horizontal hacia la izquierda
                        setPosicion(buscarCalleHorizontal(pacman,tablero));
                        return getPosicion();
                    }
                }else { //(dondeMoverserHorizontal(pacman)==2) Se mueve hacia la derecha. Solo podria ser 2 por que si fuera 0 estarian en la misma casilla
                    if(movimientoValido(moverDerecha(getPosicion()))==true){                        
                        setPosicion(moverDerecha(getPosicion()));
                        return getPosicion();
                    }else{
                        setPosicion(buscarCalleHorizontal(pacman,tablero));
                        return getPosicion();
                    }
                }
                
                  
            case 1: //Pacman se encuentra encima de Blinky. Blinky tiene que moverse verticalmente hacia arriba
                if(movimientoValido(moverArriba(getPosicion()))==true){
                    setPosicion(moverArriba(getPosicion()));
                    return getPosicion();
                }else{
                    setPosicion(buscarCalleVertical(pacman,tablero));
                    return getPosicion();
                }
                
                
            case 2: //Pacman se encuentra debajo de Blinky. Blinkt tiene que moverse vericalmente hacia abajo
                if(movimientoValido(moverAbajo(getPosicion()))==true){
                    setPosicion(moverAbajo(getPosicion()));
                    return getPosicion();
                }else{
                    setPosicion(buscarCalleVertical(pacman,tablero));
                    return getPosicion();
                }
            
            default:
            //Esto es imposible que pase
                return new Coordenadas(getPosicion().getFila(),getPosicion().getColumna());
               
            }
    }
}
