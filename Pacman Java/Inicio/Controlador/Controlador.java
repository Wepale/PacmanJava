package Inicio.Controlador;
import Inicio.Modelo.Coordenadas;
import Inicio.Vista.AyudaDibujo;
/**
 * Esta clase es la base de los controladores de Pacman y los fantasmas. Tanto esta clase como las subclases tendran la finalidad de transformar las
 * posiciones que ocupan los componentes del juego en posiciones de pixeles en la pantalla.
 * 
 * @author Yeray Rodriguez Martin 
 * @version 1.0
 */
public abstract class Controlador
{
    protected AyudaDibujo dibujar;
    protected Coordenadas posicionActual;       //La posición actual que cupa el componente
    protected Coordenadas posicionNext;         //La posición a la que se quiere desplazar
    protected int pixelX;                       //El píxel en el eje x en nuestra pantalla
    protected int pixelY;                       //El píxel en el eje y en nuestra pantalla
    protected int desplazamientoX;              //La cantidad de píxeles a desplazarse en el eje x
    protected int desplazamientoY;              //La cantidad de píxeles a desplazarse en el eje y
    protected boolean controlMovimiento;        //Para saber si el componente ha llegado a la casilla de destino
        
    /**
     * Constructor
     */
    public Controlador()
    {
        desplazamientoX=1;
        desplazamientoY=1;
        posicionActual=new Coordenadas(23,13);
        posicionNext= new Coordenadas(23,13);
        dibujar= new AyudaDibujo();
        controlMovimiento=true;
    }
    
                                                            //METODOS DE ACCESO
                                                            
    /**
     * Devulve la coordenda que contiene la posicion actual
     * @return posicionActual
     */                               
    public Coordenadas getPosicionActual()
    {
        return posicionActual;
    }
    
    /**
     * Devulve la coordenda a la que nos estamos desplazando
     * @return posicionNext
     */                               
    public Coordenadas getPosicionNext()
    {
        return posicionNext;
    }
    
    /**
     * Devulve el pixel en el eje X  en el que se encuentra la imagen de nuestro fantasma
     * @return pixelX
     */
    public int getPixelX()
    {
        return pixelX;
    }
    
    /**
     * Devulve el pixel en el eje Y en el que se encuentra la imagen de nuestro fantasma
     * @return pixelY
     */
    public int getPixelY()
    {
        return pixelY;
    }
    
    /**
     * Devuelve el desplazamiento en el eje X
     * @return desplazamientoX
     */
    public int getDesplazamientoX()
    {
        return desplazamientoX;
    }
    
    /**
     * Devuelve el desplazamiento en el eje Y
     * @return desplazamientoY
     */
    public int getDesplazamientoY()
    {
        return desplazamientoY;
    } 
    
    /**
     * Resetea el desplazamiento en el eje X poniendolo de nuevo a 1.
     */
    public void resetearDesplazamientoX()
    {
        desplazamientoX=1;
    }
    
    /**
     * Resetea el desplazamiento en el eje Y poniendolo de nuevo a 1.
     */
    public void resetearDesplazamientoY()
    {
        desplazamientoY=1;
    }
    
    /**
     * Modifica el valor de la bandera del campo controlMovimiento
     * 
     * @param x El valor con el cual queremos modificar el campo
     */
    public void setControlMovimiento(boolean x)
    {
        controlMovimiento=x;
    }
    /**
     * Dibuja la imagen de nuestro Componente en el siguiente píxel en el que se encuentra, siempre dependiendo de la casilla a la que
     * tiene que desplazarse
     * 
     * @param posicionInicial La posición que ocupa actualmente nuestro Componenente
     * @param posicionFinal La posición a la que quiere desplazarse
     */
    public void dibujarNextPixel(Coordenadas posicionInicial, Coordenadas posicionFinal)
    {
        if(posicionInicial.getFila() < posicionFinal.getFila() && posicionInicial.getColumna() == posicionFinal.getColumna() ){
            pixelY= dibujar.getCasillaY(posicionInicial.getFila())+desplazamientoY;
            pixelX= dibujar.getCasillaX(posicionInicial.getColumna());
            desplazamientoY++;
        }
        if(posicionInicial.getFila() > posicionFinal.getFila() && posicionInicial.getColumna() == posicionFinal.getColumna()){
            pixelY= dibujar.getCasillaY(posicionInicial.getFila())-desplazamientoY;
            pixelX= dibujar.getCasillaX(posicionInicial.getColumna());
            desplazamientoY++;
        }
        if(posicionInicial.getFila() == posicionFinal.getFila() && posicionInicial.getColumna() < posicionFinal.getColumna()){
            pixelY= dibujar.getCasillaY(posicionInicial.getFila());
            pixelX= dibujar.getCasillaX(posicionInicial.getColumna())+desplazamientoX;
            desplazamientoX++;
        }
        if(posicionInicial.getFila() == posicionFinal.getFila() && posicionInicial.getColumna() > posicionFinal.getColumna()){
            pixelY= dibujar.getCasillaY(posicionInicial.getFila());
            pixelX= dibujar.getCasillaX(posicionInicial.getColumna())-desplazamientoX;
            desplazamientoX++;
        }
        if(posicionInicial.getFila() == posicionFinal.getFila() && posicionInicial.getColumna() == posicionFinal.getColumna()){
            pixelX= dibujar.getCasillaX(posicionInicial.getColumna());
            pixelY= dibujar.getCasillaY(posicionInicial.getFila());
        }
    }
}
