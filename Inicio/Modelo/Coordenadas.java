package Inicio.Modelo;
/**
 * Esta clase creará un objeto que se usara para contener las coordenadas que usarán los componentes
 * del juego para moverse por la matriz del tablero.
 * 
 * El motivo de usar esta clase en lugar de la clase Point incluida en la librería de Java es por simple
 * interés. Quería que el nombre de los campos y de los metodos fuesen mucho mas intuitivos, acordes con
 * el sistema que quiero implementar
 * 
 * @author Yeray Rodriguez
 * @version 1.0
 */
public class Coordenadas
{
    
    private int fila;  //coordendada para las filas
    private int columna;  //coordenada para las columnas

    /**
     * Contructor
     * @param fila El valor de la fila
     * @param columna El valor de la columna
     */
    public Coordenadas(int fila, int columna)
    {
        this.fila= fila;
        this.columna= columna;
    }
    
    /**
     * Devuelve la posición de la fila
     * 
     * @return  x  La posición de la fila
     */
    public int getFila()
    {
        return fila;
    }
    
    /**
     * Devuelve la posición de la columna 
     * 
     * @return y La posición de la columna
     */
    public int getColumna()
    {
       return columna;
    }
    
    /**
     * Le asigna una nueva posición en la fila sin modificar la columna
     * @param ejeX La nueva posición en el eje Y
     */
    public void setFila(int fila)
    {
        this.fila= fila;
    }
    
    /**
     * Le asigna una nueva posición al componente en la columna sin modificar la fila
     * @param y La nueva posición del fantasma en el eje Y
     */
    public void setColumna(int columna)
    {
        this.columna= columna;
    }
    
    /**
     * Asignar una posición completamente nueva introduciendo la fila y la columna
     * @param ejeX La posicion en el eje X
     * @param ejeY La posicion en el eje Y
     */
    
    public void setPosicion(int fila, int columna)
    {
        this.fila= fila;
        this.columna= columna;
    }
    
    /**
     * Asignar una posición completamente nueva introduciendo otro objeto de tipo Coordenadas
     * @param Coordenada El objeto Coordenadas con la nueva posicion
     */
    public void setPosicion(Coordenadas coordenada)
    {
        fila=coordenada.getFila();
        columna=coordenada.getColumna();
    }
    
    /**
     * Compara nuestra coordenada con otra coordenada adyacente y devuelve determinados valores dependiendo de la posición respecto
     * a esa otra coordenada.
     * 
     * Cabe destacar que este método solo lo usaré para comprobar coordendas adyacentes (arriba y abajo, izquierda y derecha) y estará
     * implementado para tal uso. Si se introduce una coordenada que no sea adyacente, el resultado devuelto no será el correcto.
     * 
     * 
     * @param coordenada La coordenada sobre la que que queremos realizar la comparación
     * @return 1 si nuestra coordenada es una coordenada superior a la que estamos comparando, 2 si es inferior, 3 si se encuentra a la izquierda
     *         y 4 si se encuentra a la derecha. 0 si es la misma coordenada
     */
    public int porDondeVenia(Coordenadas coordenada)
    {
        int filaAdyacente= coordenada.getFila();
        int columnaAdyacente= coordenada.getColumna();
        if(columna==columnaAdyacente){                       //Misma columna, la coordenada adyacente solo puede estar arriba o abajo     
            if(fila<filaAdyacente){                          //Mi coordenada esta una fila mas arriba           
                return 1;
            }else{                                           //Mi coordendada esta una fila mas abajo
                return 2;
            }
        }
        
        if(fila==filaAdyacente){                            //Misma fila, la coordenada adyacente solo puede a la derecha o a la izquierda
            if(columna<columnaAdyacente){                   //Mi coordenada esta una columna a la izquierda
                return 3;
            }else{
                return 4;                                   //Mi coordenada esta una columna a la derecha
            }
        }
        return 0;
    }
}
