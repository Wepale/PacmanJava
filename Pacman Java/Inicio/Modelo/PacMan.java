package Inicio.Modelo;
import java.awt.Image;
import javax.swing.ImageIcon;
/**
 * Esta clase contendra el modelo de Pacman.
 * 
 * @author Yeray Rodriguez Martin 
 * @version 1.0
 */
public class PacMan extends Componente
{
    //Posición inicial de Pacman
    private final int FILA_INICIAL=23;
    private final int COLUMNA_INICIAL=13;
    
    private Image imagenArriba;                 //Imagen de Pacman cuando se mueve hacia arriba
    private Image imagenAbajo;                  //Imagen de Pacman cuando se mueve hacia abajo
    private Image imagenDerecha;                //Imagen de Pacman cuando se mueve hacia la derecha

   /**
     * Crea a Pacman con sus correspondientes imagenes y su posición inicial en la matriz
     */
   public PacMan()
    {
        super();
        setPosicion(FILA_INICIAL, COLUMNA_INICIAL);
        setImagen(new ImageIcon(getClass().getResource("pacmanIzquierda.gif")).getImage().getScaledInstance(18,18,Image.SCALE_DEFAULT));
        imagenDerecha= new ImageIcon(getClass().getResource("pacmanDerecha.gif")).getImage().getScaledInstance(18,18,Image.SCALE_DEFAULT);
        imagenArriba= new ImageIcon(getClass().getResource("pacmanArriba.gif")).getImage().getScaledInstance(18,18,Image.SCALE_DEFAULT);
        imagenAbajo= new ImageIcon(getClass().getResource("pacmanAbajo.gif")).getImage().getScaledInstance(18,18,Image.SCALE_DEFAULT);
    }

   /**
    * Devuelve la imagen correspondiente cuando pacman se mueve hacia la derecha
    * @return imagenDerecha Imagen de pacman moviendose hacia la derecha
    */
   public Image getImagenDerecha(){
        return imagenDerecha;
    }
    
   /**
    * Devuelve la imagen correspondiente cuando pacman se mueve arriba
    * @return imagenArriba Imagen de pacman moviendose hacia arriba
    */
   public Image getImagenArriba(){
        return imagenArriba;
    }
    
   /**
    * Devuelve la imagen correspondiente cuando pacman se mueve hacia abajo
    * @return imagenAbajo Imagen de pacman moviendose hacia abajo
    */
   public Image getImagenAbajo(){
        return imagenAbajo;
    }
}