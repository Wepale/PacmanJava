package Inicio.Controlador;
import Inicio.Modelo.PacMan;
import java.awt.event.KeyEvent;
/**
 * Esta clase se encargara de comunicar el modelo de PacMan con la vista
 * 
 * @Yeray Rodríguez Martín 
 * @version 1.0
 */
public class ControladorPacman extends Controlador
{
    private int controladorImagen;
    /**
     * Constructor
     */
    public ControladorPacman(){
        super();
        controladorImagen=1;
    }
    
    /**
    * Devuelve el controlador de imagen
    * @return controladorDeImagen 1->izquierda, 2->derecha ,3->arriba, 4->abajo
    */
    public int getControladorImagen(){
        return controladorImagen;
    }
    
    /**
     * Este metodo es el encargado de mover a pacman por la pantalla
     * @pacman Nuestro pacman
     */
    public void moverPacman(PacMan pacman){
        dibujarNextPixel(posicionActual, posicionNext);
        if(desplazamientoX==21){
            resetearDesplazamientoX();
            posicionActual.setPosicion(posicionNext);
            controlMovimiento=true;
        }
        if(desplazamientoY==21){
            resetearDesplazamientoY();
            posicionActual.setPosicion(posicionNext);
            controlMovimiento=true;
        }
    }
    
    /**
     * Este método será el encargado de recibir los eventos de teclado que estén relacionados con Pacman. Solo será posible
     * asignar una nueva posición cuando la bandera controlMovimiento sea igual a true y esto solo ocurrira cuando
     * se halla desplazado los 20 píxeles que existen entre cada casilla
     * 
     * @param e Para recoger la tecla pulsada
     * @param pacman Nuestro pacman
     */
    public void keyPressed(KeyEvent e, PacMan pacman){
        
        int tecla= e.getKeyCode();
        
        if(tecla == KeyEvent.VK_LEFT){
            if(controlMovimiento){
                 if(pacman.movimientoValido(pacman.moverIzquierda(pacman.getPosicion()))){
                    posicionActual.setPosicion(pacman.getPosicion());
                    pacman.setPosicion(pacman.moverIzquierda(pacman.getPosicion()));
                    posicionNext.setPosicion(pacman.getPosicion());
                    controladorImagen=1;
                    controlMovimiento=false;
                   }else{
                   posicionActual.setPosicion(pacman.getPosicion());
                   posicionNext.setPosicion(pacman.getPosicion());
                }
            }else{
                //No se hace nada
            }
        }
        
        if(tecla == KeyEvent.VK_RIGHT){
            if(controlMovimiento){
                if(pacman.movimientoValido(pacman.moverDerecha(pacman.getPosicion()))){
                    posicionActual.setPosicion(pacman.getPosicion());
                    pacman.setPosicion(pacman.moverDerecha(pacman.getPosicion()));
                    posicionNext.setPosicion(pacman.getPosicion());
                    controladorImagen=2;
                    controlMovimiento=false;
                }else{
                   posicionActual.setPosicion(pacman.getPosicion());
                   posicionNext.setPosicion(pacman.getPosicion());
                }
            }else{
                //No se hace nada
            }
        }
        
        if(tecla == KeyEvent.VK_UP){
            if(controlMovimiento){
                if(pacman.movimientoValido(pacman.moverArriba(pacman.getPosicion()))){
                    posicionActual.setPosicion(pacman.getPosicion());
                    pacman.setPosicion(pacman.moverArriba(pacman.getPosicion()));
                    posicionNext.setPosicion(pacman.getPosicion());
                    controladorImagen=3;
                    controlMovimiento=false;
                }else{
                   posicionActual.setPosicion(pacman.getPosicion());
                   posicionNext.setPosicion(pacman.getPosicion());
                }
            }else{
                //No se hace nada
            }
        }
        
        if(tecla == KeyEvent.VK_DOWN){
            if(controlMovimiento){
                 if(pacman.movimientoValido(pacman.moverAbajo(pacman.getPosicion()))){
                    posicionActual.setPosicion(pacman.getPosicion());
                    pacman.setPosicion(pacman.moverAbajo(pacman.getPosicion()));
                    posicionNext.setPosicion(pacman.getPosicion());
                    controladorImagen=4;
                    controlMovimiento=false;
                }else{
                   posicionActual.setPosicion(pacman.getPosicion());
                   posicionNext.setPosicion(pacman.getPosicion());
                }
            }else{
                //No se hace nada
            }
        }
    }

    /**
    * Este metodo definira las acciones correspondientes asociadas al soltar de una tecla.
    * 
    * Realmente no hay nada que hacer
    */
   public void keyReleased(KeyEvent e){
         
        int tecla = e.getKeyCode();
        
        if(tecla == KeyEvent.VK_LEFT){
            //No se hace nada
        }
        
        if(tecla == KeyEvent.VK_RIGHT){
           //No se hace nada
        }
        
        if(tecla == KeyEvent.VK_UP){
           //No se hace nada
        }
            
        if(tecla == KeyEvent.VK_DOWN){
           //No de hace nada
        }

    }
}