package Inicio.Controlador;
import Inicio.Modelo.Tablero;
/**
 * Esta clase sera la encargada de controlar por que casillas pasa pacman para comer las bolas, borrarlas del
 * labarentito,sumar los puntos y llevar el conteo de las bolas a comido Pacman. Distingira entre una bola grande o una bola pequeña.
 * 
 * @author Yeray Rodríguez Martin
 * @version 1.0
 */
public class Marcador
{
    private int puntos;                             //Los puntos que llevara pacman
    private int numeroBolas;                        //El numero de bolas que hay inicialmente en el laberinto
    private final int PUNTOSXBOLA=10;               //Los puntos que otorgara comer una bola
    private final int PUNTOSXFANTASMA=100;          //Los puntos que otrogara comer a un fantasma
    
    public Marcador(){
        puntos=0;
        numeroBolas=256;
    }
    
    /**
     * Realiza la suma de los puntos correspondientes a una bola
     */
    public void sumarBola()
    {
        puntos += PUNTOSXBOLA;
    }
    
    /**
     * Realiza la suma de los puntos correspondientes a un fantasma
     */
    public void sumarFantasma()
    {
        puntos += PUNTOSXFANTASMA;
    }
    
    /**
     * Devuelve el campo puntos
     * @return puntos
     */
    public int getPuntos()
    {
        return puntos;
    }
    
    /**
     * Devuelve el campo bolasComidas
     * @return bolasComidas
     */
    public int getNumeroBolas()
    {
        return numeroBolas;
    }
    
    /**
     * Actualiza una casilla del tablero a valor 0. Se usará para controlar cuantas bolas ha comido Pacman.
     * Si es una bola pequeña la borrará del tablero y sumara los puntos. Si es una bola grande la quitara del tablero,
     * sumara los puntos y devolverá la bandera que posteriormente se utilizará para activar la protección
     * 
     * @param controlador Nuestro controlador de Pacman
     * @param tablero Nuestro tablero para realizar modificaciones
     * @return true se si come una bola grande, false en caso contrario.
     */
    public boolean bolaComida(ControladorPacman controlador, Tablero tablero)
    {
        int fila=controlador.getPosicionActual().getFila();
        int columna=controlador.getPosicionActual().getColumna();
        if(tablero.getTablero(fila,columna) == 1){
            tablero.setTablero(0, fila, columna);
            sumarBola();
            numeroBolas--;
            return false;
        }else if(tablero.getTablero(fila,columna) == 2){
            tablero.setTablero(0, fila, columna);
            //No se suman puntos por comer una bola grande
            numeroBolas--;
            return true;
        }else{
            return false;
        }
    }
}
