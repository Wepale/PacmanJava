package Inicio.Modelo;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.util.Random;
/**
 * Esta clase contiene el modelo de Clyde
 * 
 * @author Yeray Rodriguez Martin   
 * @version 1.0
 */
public class Clyde extends Fantasmas
{
    //Posición inicial de Clyde
    private final int FILA_INICIAL=14;
    private final int COLUMNA_INICIAL=15;
    private int sentido;                                                //El sentido que llevará Clyde
    private Random aleatorio;                                           //Para elegir aleatoriamente un camino
    private boolean caminoEncontrado;                                   //Bandera para saber si ha encontrado un camino valido
    private double probabilidad;                                        //Almacenará el resultado del valor de aleatorio.nextDouble()
    /**
     * Crea un fantasma con la imagen de Clyde y su posición inicial en la matriz
     */
    public Clyde()
    {
        super();
        setPosicion(FILA_INICIAL, COLUMNA_INICIAL);
        setImagen(new ImageIcon(getClass().getResource("Clyde.gif")).getImage().getScaledInstance(18,18,Image.SCALE_DEFAULT));
        aleatorio= new Random();
        caminoEncontrado=false;
        sentido=2;
    }
    
    /**
     * Modifica el sentido en el que circula Clyde
     * 
     * @param sentido 1->Abajo; 2->Arriba; 3->Derecha; 4->Izquierda
     */
    public void setSentido(int sentido){
        this.sentido=sentido;
    }
    /**
     * Modifica la bandera que indica si se ha encontrado un camino valido
     * 
     * @param x true si lo ha encontrado, false en caso contrario
     */
    public void setCaminoEncontrado(boolean x)
    {
        caminoEncontrado=x;
    }
    
    /**
     * Este método devolverá una bandera que indicará si Clyde ha llegado a un cruce cuando circulaba de forma vertical.
     * @param tablero El tablero matemático del juego
     * @return true si hay un cruce, false en caso contrario
     */
    public boolean existeCruceHorizontal(Tablero tablero)
    {
        int filaActual= getPosicion().getFila();
        int columnaActual= getPosicion().getColumna();
        if(tablero.getTablero(filaActual, columnaActual-1)!=-1 || tablero.getTablero(filaActual, columnaActual+1)!=-1){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Este método devolverá una bandera que indicará si Clyde ha llegado a un cruce cuando circulaba de forma horizontal.
     * @param tablero El tablero matemático del juego
     * @return true si hay un cruce, false en caso contrario
     */
    public boolean existeCruceVertical(Tablero tablero)
    {
        int filaActual= getPosicion().getFila();
        int columnaActual= getPosicion().getColumna();
        if(tablero.getTablero(filaActual-1, columnaActual)!=-1 || tablero.getTablero(filaActual+1, columnaActual)!=-1){
            return true;
        }else{
            return false;
        }
    }
    
    
    /**
     * Reescribimos el método para Clyde. Clyde no sigue ninguna ruta espeficífica para acercarse a Pacman,
     * simplemente deambula por el laberinto aleatoriamente.
     * 
     * La forma en la que Clyde se movera aleatoriamente será la siguiente: 
     * Clyde circula por una calle hasta que se encuentre con un muro o un cruce. Solo en este momento podra cambiar de dirección,
     * eligiendo una de las calles del cruce, pero nunca podrá dar un giro de 180º. 
     * 
     * @param pacman Nuestro Pacman, aunque en este caso no lo necesitaremos
     * @param tablero El tablero para que el fantasma sepa por donde circular
     */
    public Coordenadas calcularMovimiento(PacMan pacman, Tablero tablero)
    {
        switch(sentido){
            
                case 1:                                                                             //Circula hacia abajo
                    if(existeCruceHorizontal(tablero)){
                        while(!caminoEncontrado){
                            probabilidad= aleatorio.nextDouble();
                            if(probabilidad<0.33){                                                        
                                if(movimientoValido(moverAbajo(getPosicion()))){
                                    setPosicion(moverAbajo(getPosicion()));
                                    return getPosicion();
                                }
                            }else if(probabilidad<0.66){
                                    if(movimientoValido(moverIzquierda(getPosicion()))){
                                        setPosicion(moverIzquierda(getPosicion()));
                                        return getPosicion();
                                    }
                            }else if(probabilidad<0.99){
                                    if(movimientoValido(moverDerecha(getPosicion()))){
                                        setPosicion(moverDerecha(getPosicion()));
                                        return getPosicion();
                                    }
                            }
                        }
                }else{
                    if(movimientoValido(moverAbajo(getPosicion()))){
                                setPosicion(moverAbajo(getPosicion()));
                                return getPosicion();
                    }
                }
                break;
                
                case 2:                                                                                 //Circula hacia arriba     
                     if(existeCruceHorizontal(tablero)){
                         while(!caminoEncontrado){
                             probabilidad= aleatorio.nextDouble();
                             if(probabilidad<0.33){                                                        
                                 if(movimientoValido(moverArriba(getPosicion()))){
                                     setPosicion(moverArriba(getPosicion()));
                                     return getPosicion();
                                    }
                             }else if(probabilidad<0.66){
                                    if(movimientoValido(moverIzquierda(getPosicion()))){
                                      setPosicion(moverIzquierda(getPosicion()));
                                      return getPosicion();
                                    }
                             }else if(probabilidad<0.99){
                                  if(movimientoValido(moverDerecha(getPosicion()))){
                                      setPosicion(moverDerecha(getPosicion()));
                                      return getPosicion();
                                  }
                             }
                            }
                }else{
                    if(movimientoValido(moverArriba(getPosicion()))){
                          setPosicion(moverArriba(getPosicion()));
                          return getPosicion();
                    }
                }    
                break;
                
                case 3:                                                                                     //Circula hacia la derecha
                     if(existeCruceVertical(tablero)){
                         while(!caminoEncontrado){
                             probabilidad= aleatorio.nextDouble();
                             if(probabilidad<0.33){                                                        
                                 if(movimientoValido(moverDerecha(getPosicion()))){
                                     setPosicion(moverDerecha(getPosicion()));
                                     return getPosicion();
                                    }
                             }else if(probabilidad<0.66){
                                    if(movimientoValido(moverArriba(getPosicion()))){
                                      setPosicion(moverArriba(getPosicion()));
                                      return getPosicion();
                                    }
                             }else if(probabilidad<0.99){
                                  if(movimientoValido(moverAbajo(getPosicion()))){
                                      setPosicion(moverAbajo(getPosicion()));
                                      return getPosicion();
                                  }
                             }
                            }
                }else{
                    if(movimientoValido(moverDerecha(getPosicion()))){
                          setPosicion(moverDerecha(getPosicion()));
                          return getPosicion();
                    }
                }
                break;
                
                case 4:                                                                                   //Circula hacia la izquierda
                     if(existeCruceVertical(tablero)){
                         while(!caminoEncontrado){
                             probabilidad= aleatorio.nextDouble();
                             if(probabilidad<0.33){                                                        
                                 if(movimientoValido(moverIzquierda(getPosicion()))){
                                     setPosicion(moverIzquierda(getPosicion()));
                                     return getPosicion();
                                    }
                             }else if(probabilidad<0.66){
                                    if(movimientoValido(moverArriba(getPosicion()))){
                                      setPosicion(moverArriba(getPosicion()));
                                      return getPosicion();
                                    }
                             }else if(probabilidad<0.99){
                                  if(movimientoValido(moverAbajo(getPosicion()))){
                                      setPosicion(moverAbajo(getPosicion()));
                                      return getPosicion();
                                  }
                             }
                         }
                }else{
                    if(movimientoValido(moverIzquierda(getPosicion()))){
                          setPosicion(moverIzquierda(getPosicion()));
                          return getPosicion();
                    }
                }
                break;
                
                default:
                    //Esto no pasará nunca
                    return new Coordenadas(1,1);
        }
        //Esto no pasará nunca
        return new Coordenadas(1,1);
    }
}
