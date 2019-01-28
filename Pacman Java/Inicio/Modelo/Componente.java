package Inicio.Modelo;
import java.awt.Image;
/**
 * Esta clase servirá de base para los componentes del juego, ya sea Pacman o los fantasmas.
 * Simplemente contará de un objeto Imagen y un objeto Coordenada, así como metodos para moverse en cada dirección
 * del tablero cuando sea necesario.
 * 
 * Esta será la superclase de pacman y los fantasmas
 * 
 * @author Yeray Rodriguez Martin
 * @version 1.0 
 */

public abstract class Componente
   
{
    private Coordenadas posicion;
    private Image imagen;
    /**
     * Constructor
     * 
     * Simplemente incialiazamos todos los campos, posteriormente cada subclase modificara estos campos
     */
    
    public Componente()
    {
       posicion= new Coordenadas(0,0);
       Image imagen=null;;
    }
    
                                                                //METODOS DE ACCESO
    
    /**
     * Devuelve el campo posicion
     * @return posicion El objeto de tipo Coordenadas que contiene la posicion en el tablero
     */
    public Coordenadas getPosicion()
    {
        return posicion;
    }

    /**
     * Devuelve el campo Imagen
     * @return La imagen de nuestro componente
     */
    public Image getImagen(){
        return imagen;
    }
    
    /**
     * Modifica el campo Imagen
     * @return La imagen de nuestro componente
     */
    public void setImagen(Image imagen){
         this.imagen=imagen;
    }
    
    
    /**
     * Modifica el campo posicion
     * @param fila La fila
     * @param columna La columna
     */
    public void setPosicion(int fila, int columna)
    {
        posicion.setFila(fila);
        posicion.setColumna(columna);
    }
    
    /**
     * Modifica el campo posicion
     * @param coordenada La coordenada que queremos almacenar
     */
    public void setPosicion(Coordenadas coordenada)
    {
        posicion.setPosicion(coordenada);
    }
    
    
                                                           //FIN METODOS DE ACCESO
    
    
    /**
     * Dada una pocicion en un punto (x,y) este metodo calcula si es posible desplazarse a dicha posicion
     * 
     * @param  coordenada  Objeto Coordenadas que contendrá las coordenadas a donde se quiere relaizar el movimiento
     * @return true se si puede mover, false en caso contrario
     */
    public boolean movimientoValido(Coordenadas coordenada)
    {
        Tablero tablero=new Tablero();
        if(tablero.getTablero(coordenada.getFila(), coordenada.getColumna())==-1){
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * Dada una posicion se calcula la posicion a la derecha
     * @param coordenada Objeto Cordenada que almacena la posicion actual
     * @return La posicion a la derecha
     */
    public Coordenadas moverDerecha(Coordenadas coordenada)
    {
        int fila= coordenada.getFila();
        int columna=coordenada.getColumna();
        columna++;
        Coordenadas coordenadaAux= new Coordenadas(fila,columna);
        return coordenadaAux;
    }
    
    /**
     * Dada una posicion dada se calcula la posición a la izquierda
     * @param coordenada Objeto Coordenada que almacena la posición actual
     * @return La posicion a la izquierda
     */
    public Coordenadas moverIzquierda(Coordenadas coordenada)
    {
        int fila= coordenada.getFila();
        int columna= coordenada.getColumna();
        columna--;
        Coordenadas coordenadaAux= new Coordenadas(fila,columna);
        return coordenadaAux;
        
    }
     
    /**
     * Dada una posicion dada se calcula la posición superior
     * @param coordenada Objeto Coordenada que almacena la posición actual
     * @return La posicion superior
     */
    public Coordenadas moverArriba(Coordenadas coordenada)
    {
        int fila= coordenada.getFila();
        int columna= coordenada.getColumna();
        fila--;
        Coordenadas coordenadaAux= new Coordenadas(fila,columna);
        return coordenadaAux;
    }
    
    /**
     * Dada una posición dada se calcula la posición inferior
     * @param coordenada Objeto Coordenada que almacena la posición actual
     * @return La posicion inferior
     */
    public Coordenadas moverAbajo(Coordenadas coordenada)
    {
        int fila= coordenada.getFila();
        int columna= coordenada.getColumna();
        fila++;
        Coordenadas coordenadaAux= new Coordenadas(fila,columna);
        return coordenadaAux;
    }
}
