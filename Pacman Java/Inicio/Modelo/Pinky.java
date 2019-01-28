package Inicio.Modelo;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * Esta clase contiene el modelo de Pinky
 * 
 * @author Yeray Rodríguez Martín 
 * @version 1.0
 */
public class Pinky extends Fantasmas
{
    //Posición inicial de Pinky
    private final int FILA_INICIAL=14;
    private final int COLUMNA_INICIAL=12;
    /**
     * Crea un fastasma con la imagen de blinky y su posición inicial en la matriz
     */
    public Pinky()
    {
        super();
        setPosicion(FILA_INICIAL, COLUMNA_INICIAL);
        setImagen(new ImageIcon(getClass().getResource("Pinky.gif")).getImage().getScaledInstance(18,18,Image.SCALE_DEFAULT));
    }
    
    /**
    * Reescribimos el método para Pinky. Para acercarse a Pac-Man calculará la distancia e intentará primero acercarse horizontalmente
    * y luego verticalmente. Este método controlara que sea un movimento válido, es decir, que no se salga del laberinto
    * @param pacman Nuestro Pacman, para saber donde se encuentra
    * @param tablero El tablero para que el fantasma sepa por donde circular
    */
    public Coordenadas calcularMovimiento(PacMan pacman, Tablero tablero)
    {
        switch(dondeMoverseHorizontal(pacman)) {
            
            case 0:                                                          //Pacman y Pinky ya se encuentran en la misma columna, por lo que Pinky tendra que acercarse verticalmente
                if(dondeMoverseVertical(pacman)==1){
                    if(movimientoValido(moverArriba(getPosicion()))==true){  //El movimiento es valido
                        setPosicion(moverArriba(getPosicion()));
                        return getPosicion();
                    }else{                                                   //El movimiento no es valido, debe de encontrar la primera calle vertical hacia arriba
                        setPosicion(buscarCalleVertical(pacman,tablero));
                        return getPosicion();
                    }
                }else{                                                      //dondeMoverseVertical(pacman)==2 ya que si fuera 0 estaria en la misma casilla que pacman
                    if(movimientoValido(moverAbajo(getPosicion()))==true){   //El movimiento es valido
                        setPosicion(moverAbajo(getPosicion()));
                        return getPosicion();
                    }else{                                                  //El moviento no es valido, Pinky tendra que buscar la primera calle vertical hacia abajo
                        setPosicion(buscarCalleVertical(pacman,tablero));
                        return getPosicion();
                    }
                }
             
            case 1:                                                         //Pacman se encuentra a la izquierda de Pinky.
                if(movimientoValido(moverIzquierda(getPosicion()))==true){    //El movimiento es valido
                    setPosicion(moverIzquierda(getPosicion()));
                    return getPosicion();
                }else{                                                      //El movimiento es invalido. Pinky tendra que buscar la primera calle horizontal hacia la izquierda
                    setPosicion(buscarCalleHorizontal(pacman,tablero));
                    return getPosicion();
                }
                
            case 2:                                                         //Pacman se encuentra a la derecha del fantasma.
                if(movimientoValido(moverDerecha(getPosicion()))==true){    //El movimiento es valido
                    setPosicion(moverDerecha(getPosicion()));
                    return getPosicion();
                }else{                                                      //El movimiento es invalido, Pinky tendra que buscar la primera calle horizontal hacia la derecha
                    setPosicion(buscarCalleHorizontal(pacman,tablero));
                    return getPosicion();
                }
                
            default:
            //Esto no pasara nunca
            return new Coordenadas(getPosicion().getFila(),getPosicion().getColumna());   
        }
    }
}
