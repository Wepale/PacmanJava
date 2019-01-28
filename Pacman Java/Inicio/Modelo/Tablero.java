package Inicio.Modelo;
/**
 * Esta clase contendrá el tablero matemático del juego. Estará formado por una matriz en la que cada casilla podrá contener a
 * pacman, un fantasma, un punto pequeño, un punto grande, (o nada si ya a sido comido) o un muro.
 * Todo nuestro juego se basará en desplazarse en este tablero.
 * 
 * @author Yeray Rodriguez Martin 
 * @version 1.0
 */
public class Tablero
{
    private final int FILAS= 31;
    private final int COLUMNAS=28;
    int [][] tablero;
    /**
     * Constructor
     * 
     * Crea una nueva matriz con el valor de la variables FILAS y COLUMNAS
     */
    public Tablero()
    {
        tablero=new int[FILAS][COLUMNAS];
        rellenarTablero();
    }
    
    
                                                                    //METODOS DE ACCESO
    
    /**
     * Devuelve el valor que contiene una determinada posición
     * @param fila La fila a la que queremos acceder
     * @param columna La columna a la que queremos acceder
     * @return El valor de dicha posicion
     */
    public int getTablero(int fila, int columna)
    {
         return tablero[fila][columna]; 
    }
    
    /**
     * Actualiza el valor de una determinada posición
     * @param valor El valor que queremos introducir
     * @param fila La fila a la que queremos acceder
     * @param columna La columna a la que queremos acceder
     */
    public void setTablero(int valor, int fila, int columna)
    {
        tablero[fila][columna]=valor;
    }
    
    /**
     * Devuelve el número de filas de tablero
     * @return El número de filas
     */
    public int getFilas(){
        return FILAS;
    }
    
    /**
     * Devuelve el número de columnas del tablero
     * @return El número de columnas
     */
    public int getColumnas(){
        return COLUMNAS;
    }     
                                                                    //FIN METODOS DE ACCESO    
    /**
     * Rellena la matriz con los valores adecuados
     */                                                                
    public void rellenarTablero(){
                //-1=MURO ; 0=NADA ; 1=PUNTO PEQUEÑO ; 2=PUNTO GRANDE;
        
        //Los bordes del tablero son muros
        
        //Borde superior e inferior
        for(int i=0; i<COLUMNAS; i++){
            tablero[0][i]=-1 ;
            tablero[30][i]=-1;
        }
        //Borde izquierdo y derecho
        for(int i=0; i<FILAS; i++){
            tablero[i][0]=-1;
            tablero[i][27]=-1;
        }
        
        //FILA 1
        
        tablero[1][1]=1;  tablero[1][2]=1;  tablero[1][3]=1;  tablero[1][4]=1;  tablero[1][5]=1;  tablero[1][6]=1;  
        tablero[1][7]=1;  tablero[1][8]=1;  tablero[1][9]=1;  tablero[1][10]=1;  tablero[1][11]=1;  tablero[1][12]=1;  
        tablero[1][13]=-1;  tablero[1][14]=-1;  tablero[1][15]=1;  tablero[1][16]=1;  tablero[1][17]=1;  tablero[1][18]=1;  
        tablero[1][19]=1;  tablero[1][20]=1;  tablero[1][21]=1;  tablero[1][22]=1;  tablero[1][23]=1;  tablero[1][24]=1;  
        tablero[1][25]=1;  tablero[1][26]=1; 
        
        //FILA 2
        
        tablero[2][1]=1;  tablero[2][2]=-1;  tablero[2][3]=-1;  tablero[2][4]=-1;  tablero[2][5]=-1;  tablero[2][6]=1;  
        tablero[2][7]=-1;  tablero[2][8]=-1;  tablero[2][9]=-1;  tablero[2][10]=-1;  tablero[2][11]=-1;  tablero[2][12]=1;  
        tablero[2][13]=-1;  tablero[2][14]=-1;  tablero[2][15]=1;  tablero[2][16]=-1;  tablero[2][17]=-1;  tablero[2][18]=-1;  
        tablero[2][19]=-1;  tablero[2][20]=-1;  tablero[2][21]=1;  tablero[2][22]=-1;  tablero[2][23]=-1;  tablero[2][24]=-1;  
        tablero[2][25]=-1;  tablero[2][26]=1;
        
     
        //FILA 3
        
        tablero[3][1]=2;  tablero[3][2]=-1;  tablero[3][3]=-1;  tablero[3][4]=-1;  tablero[3][5]=-1;  tablero[3][6]=1;  
        tablero[3][7]=-1;  tablero[3][8]=-1;  tablero[3][9]=-1;  tablero[3][10]=-1;  tablero[3][11]=-1;  tablero[3][12]=1;  
        tablero[3][13]=-1;  tablero[3][14]=-1;  tablero[3][15]=1;  tablero[3][16]=-1;  tablero[3][17]=-1;  tablero[3][18]=-1;  
        tablero[3][19]=-1;  tablero[3][20]=-1;  tablero[3][21]=1;  tablero[3][22]=-1;  tablero[3][23]=-1;  tablero[3][24]=-1;  
        tablero[3][25]=-1;  tablero[3][26]=2;
        
        //FILA 4
        
        tablero[4][1]=1;  tablero[4][2]=-1;  tablero[4][3]=-1;  tablero[4][4]=-1;  tablero[4][5]=-1;  tablero[4][6]=1;  
        tablero[4][7]=-1;  tablero[4][8]=-1;  tablero[4][9]=-1;  tablero[4][10]=-1;  tablero[4][11]=-1;  tablero[4][12]=1;  
        tablero[4][13]=-1;  tablero[4][14]=-1;  tablero[4][15]=1;  tablero[4][16]=-1;  tablero[4][17]=-1;  tablero[4][18]=-1;  
        tablero[4][19]=-1;  tablero[4][20]=-1;  tablero[4][21]=1;  tablero[4][22]=-1;  tablero[4][23]=-1;  tablero[4][24]=-1;  
        tablero[4][25]=-1;  tablero[4][26]=1;
        
        //FILA 5 *Fila completa de bolas pequeñas*
        
        for(int i= 1; i<COLUMNAS-1; i++){
            tablero[5][i]=1;
        }
        
        //FILA 6
        
        tablero[6][1]=1;  tablero[6][2]=-1;  tablero[6][3]=-1;  tablero[6][4]=-1;  tablero[6][5]=-1;  tablero[6][6]=1;  
        tablero[6][7]=-1;  tablero[6][8]=-1;  tablero[6][9]=1;  tablero[6][10]=-1;  tablero[6][11]=-1;  tablero[6][12]=-1;  
        tablero[6][13]=-1;  tablero[6][14]=-1;  tablero[6][15]=-1;  tablero[6][16]=-1;  tablero[6][17]=-1;  tablero[6][18]=1;  
        tablero[6][19]=-1;  tablero[6][20]=-1;  tablero[6][21]=1;  tablero[6][22]=-1;  tablero[6][23]=-1;  tablero[6][24]=-1;  
        tablero[6][25]=-1;  tablero[6][26]=1;
        
        //FILA 7
        
        tablero[7][1]=1;  tablero[7][2]=-1;  tablero[7][3]=-1;  tablero[7][4]=-1;  tablero[7][5]=-1;  tablero[7][6]=1;  
        tablero[7][7]=-1;  tablero[7][8]=-1;  tablero[7][9]=1;  tablero[7][10]=-1;  tablero[7][11]=-1;  tablero[7][12]=-1;  
        tablero[7][13]=-1;  tablero[7][14]=-1;  tablero[7][15]=-1;  tablero[7][16]=-1;  tablero[7][17]=-1;  tablero[7][18]=1;  
        tablero[7][19]=-1;  tablero[7][20]=-1;  tablero[7][21]=1;  tablero[7][22]=-1;  tablero[7][23]=-1;  tablero[7][24]=-1;  
        tablero[7][25]=-1;  tablero[7][26]=1;
        
        //FILA 8
        
        tablero[8][1]=1;  tablero[8][2]=1;  tablero[8][3]=1;  tablero[8][4]=1;  tablero[8][5]=1;  tablero[8][6]=1;  
        tablero[8][7]=-1;  tablero[8][8]=-1;  tablero[8][9]=1;  tablero[8][10]=1;  tablero[8][11]=1;  tablero[8][12]=1;  
        tablero[8][13]=-1;  tablero[8][14]=-1;  tablero[8][15]=1;  tablero[8][16]=1;  tablero[8][17]=1;  tablero[8][18]=1;  
        tablero[8][19]=-1;  tablero[8][20]=-1;  tablero[8][21]=1;  tablero[8][22]=1;  tablero[8][23]=1;  tablero[8][24]=1;  
        tablero[8][25]=1;  tablero[8][26]=1;
        
        //FILA 9
        
        tablero[9][1]=-1;  tablero[9][2]=-1;  tablero[9][3]=-1;  tablero[9][4]=-1;  tablero[9][5]=-1;  tablero[9][6]=1;  
        tablero[9][7]=-1;  tablero[9][8]=-1;  tablero[9][9]=-1;  tablero[9][10]=-1;  tablero[9][11]=-1;  tablero[9][12]=0;  
        tablero[9][13]=-1;  tablero[9][14]=-1;  tablero[9][15]=0;  tablero[9][16]=-1;  tablero[9][17]=-1;  tablero[9][18]=-1;  
        tablero[9][19]=-1;  tablero[9][20]=-1;  tablero[9][21]=1;  tablero[9][22]=-1;  tablero[9][23]=-1;  tablero[9][24]=-1;  
        tablero[9][25]=-1;  tablero[9][26]=-1;
        
        //FILA 10
        
        tablero[10][1]=-1;  tablero[10][2]=-1;  tablero[10][3]=-1;  tablero[10][4]=-1;  tablero[10][5]=-1;  tablero[10][6]=1;  
        tablero[10][7]=-1;  tablero[10][8]=-1;  tablero[10][9]=-1;  tablero[10][10]=-1;  tablero[10][11]=-1;  tablero[10][12]=0;  
        tablero[10][13]=-1;  tablero[10][14]=-1;  tablero[10][15]=0;  tablero[10][16]=-1;  tablero[10][17]=-1;  tablero[10][18]=-1;  
        tablero[10][19]=-1;  tablero[10][20]=-1;  tablero[10][21]=1;  tablero[10][22]=-1;  tablero[10][23]=-1;  tablero[10][24]=-1;  
        tablero[10][25]=-1;  tablero[10][26]=-1;
        
        //FILA 11
        
        tablero[11][1]=-1;  tablero[11][2]=-1;  tablero[11][3]=-1;  tablero[11][4]=-1;  tablero[11][5]=-1;  tablero[11][6]=1;  
        tablero[11][7]=-1;  tablero[11][8]=-1;  tablero[11][9]=0;  tablero[11][10]=0;  tablero[11][11]=0;  tablero[11][12]=0;  
        tablero[11][13]=0;  tablero[11][14]=0;  tablero[11][15]=0;  tablero[11][16]=0;  tablero[11][17]=0;  tablero[11][18]=0;  
        tablero[11][19]=-1;  tablero[11][20]=-1;  tablero[11][21]=1;  tablero[11][22]=-1;  tablero[11][23]=-1;  tablero[11][24]=-1;  
        tablero[11][25]=-1;  tablero[11][26]=-1;
        
        //FILA 12
        
        tablero[12][1]=-1;  tablero[12][2]=-1;  tablero[12][3]=-1;  tablero[12][4]=-1;  tablero[12][5]=-1;  tablero[12][6]=1;  
        tablero[12][7]=-1;  tablero[12][8]=-1;  tablero[12][9]=0;  tablero[12][10]=-1;  tablero[12][11]=-1;  tablero[12][12]=-1;  
        tablero[12][13]=-1;  tablero[12][14]=-1;  tablero[12][15]=-1;  tablero[12][16]=-1;  tablero[12][17]=-1;  tablero[12][18]=0;  
        tablero[12][19]=-1;  tablero[12][20]=-1;  tablero[12][21]=1;  tablero[12][22]=-1;  tablero[12][23]=-1;  tablero[12][24]=-1;  
        tablero[12][25]=-1;  tablero[12][26]=-1;
        
        //FILA 13
        
        tablero[13][1]=-1;  tablero[13][2]=-1;  tablero[13][3]=-1;  tablero[13][4]=-1;  tablero[13][5]=-1;  tablero[13][6]=1;  
        tablero[13][7]=-1;  tablero[13][8]=-1;  tablero[13][9]=0;  tablero[13][10]=-1;  tablero[13][11]=0;  tablero[13][12]=0;  
        tablero[13][13]=0;  tablero[13][14]=0;  tablero[13][15]=0;  tablero[13][16]=0;  tablero[13][17]=-1;  tablero[13][18]=0;  
        tablero[13][19]=-1;  tablero[13][20]=-1;  tablero[13][21]=1;  tablero[13][22]=-1;  tablero[13][23]=-1;  tablero[13][24]=-1;  
        tablero[13][25]=-1;  tablero[13][26]=-1;
        
        //FILA 14
        
        tablero[14][1]=-1;  tablero[14][2]=-1;  tablero[14][3]=-1;  tablero[14][4]=-1;  tablero[14][5]=-1;  tablero[14][6]=1;  
        tablero[14][7]=-1;  tablero[14][8]=-1;  tablero[14][9]=0;  tablero[14][10]=-1;  tablero[14][11]=0;  tablero[14][12]=0;  
        tablero[14][13]=0;  tablero[14][14]=0;  tablero[14][15]=0;  tablero[14][16]=0;  tablero[14][17]=-1;  tablero[14][18]=0;  
        tablero[14][19]=-1;  tablero[14][20]=-1;  tablero[14][21]=1;  tablero[14][22]=-1;  tablero[14][23]=-1;  tablero[14][24]=-1;  
        tablero[14][25]=-1;  tablero[14][26]=-1;
        
        //FILA 15
        
        tablero[15][1]=-1;  tablero[15][2]=-1;  tablero[15][3]=-1;  tablero[15][4]=-1;  tablero[15][5]=-1;  tablero[15][6]=1;  
        tablero[15][7]=-1;  tablero[15][8]=-1;  tablero[15][9]=0;  tablero[15][10]=-1;  tablero[15][11]=0;  tablero[15][12]=0;  
        tablero[15][13]=0;  tablero[15][14]=0;  tablero[15][15]=0;  tablero[15][16]=0;  tablero[15][17]=-1;  tablero[15][18]=0;  
        tablero[15][19]=-1;  tablero[15][20]=-1;  tablero[15][21]=1;  tablero[15][22]=-1;  tablero[15][23]=-1;  tablero[15][24]=-1;  
        tablero[15][25]=-1;  tablero[15][26]=-1;
        
        //FILA 16
        
        tablero[16][1]=-1;  tablero[16][2]=-1;  tablero[16][3]=-1;  tablero[16][4]=-1;  tablero[16][5]=-1;  tablero[16][6]=1;  
        tablero[16][7]=-1;  tablero[16][8]=-1;  tablero[16][9]=0;  tablero[16][10]=-1;  tablero[16][11]=-1;  tablero[16][12]=-1;  
        tablero[16][13]=-1;  tablero[16][14]=-1;  tablero[16][15]=-1;  tablero[16][16]=-1;  tablero[16][17]=-1;  tablero[16][18]=0;  
        tablero[16][19]=-1;  tablero[16][20]=-1;  tablero[16][21]=1;  tablero[16][22]=-1;  tablero[16][23]=-1;  tablero[16][24]=-1;  
        tablero[16][25]=-1;  tablero[16][26]=-1;
        
        //FILA 17
        
        tablero[17][1]=-1;  tablero[17][2]=-1;  tablero[17][3]=-1;  tablero[17][4]=-1;  tablero[17][5]=-1;  tablero[17][6]=1;  
        tablero[17][7]=-1;  tablero[17][8]=-1;  tablero[17][9]=0;  tablero[17][10]=0;  tablero[17][11]=0;  tablero[17][12]=0;  
        tablero[17][13]=0;  tablero[17][14]=0;  tablero[17][15]=0;  tablero[17][16]=0;  tablero[17][17]=0;  tablero[17][18]=0;  
        tablero[17][19]=-1;  tablero[17][20]=-1;  tablero[17][21]=1;  tablero[17][22]=-1;  tablero[17][23]=-1;  tablero[17][24]=-1;  
        tablero[17][25]=-1;  tablero[17][26]=-1;
        
        //FILA 18
        
        tablero[18][1]=-1;  tablero[18][2]=-1;  tablero[18][3]=-1;  tablero[18][4]=-1;  tablero[18][5]=-1;  tablero[18][6]=1;  
        tablero[18][7]=-1;  tablero[18][8]=-1;  tablero[18][9]=0;  tablero[18][10]=-1;  tablero[18][11]=-1;  tablero[18][12]=-1;  
        tablero[18][13]=-1;  tablero[18][14]=-1;  tablero[18][15]=-1;  tablero[18][16]=-1;  tablero[18][17]=-1;  tablero[18][18]=0;  
        tablero[18][19]=-1;  tablero[18][20]=-1;  tablero[18][21]=1;  tablero[18][22]=-1;  tablero[18][23]=-1;  tablero[18][24]=-1;  
        tablero[18][25]=-1;  tablero[18][26]=-1;
        
        //FILA 19
        
        tablero[19][1]=-1;  tablero[19][2]=-1;  tablero[19][3]=-1;  tablero[19][4]=-1;  tablero[19][5]=-1;  tablero[19][6]=1;  
        tablero[19][7]=-1;  tablero[19][8]=-1;  tablero[19][9]=0;  tablero[19][10]=-1;  tablero[19][11]=-1;  tablero[19][12]=-1;  
        tablero[19][13]=-1;  tablero[19][14]=-1;  tablero[19][15]=-1;  tablero[19][16]=-1;  tablero[19][17]=-1;  tablero[19][18]=0;  
        tablero[19][19]=-1;  tablero[19][20]=-1;  tablero[19][21]=1;  tablero[19][22]=-1;  tablero[19][23]=-1;  tablero[19][24]=-1;  
        tablero[19][25]=-1;  tablero[19][26]=-1;
                
        //FILA 20
        
        tablero[20][1]=1;  tablero[20][2]=1;  tablero[20][3]=1;  tablero[20][4]=1;  tablero[20][5]=1;  tablero[20][6]=1;  
        tablero[20][7]=1;  tablero[20][8]=1;  tablero[20][9]=1;  tablero[20][10]=1;  tablero[20][11]=1;  tablero[20][12]=1;  
        tablero[20][13]=-1;  tablero[20][14]=-1;  tablero[20][15]=1;  tablero[20][16]=1;  tablero[20][17]=1;  tablero[20][18]=1;  
        tablero[20][19]=1;  tablero[20][20]=1;  tablero[20][21]=1;  tablero[20][22]=1;  tablero[20][23]=1;  tablero[20][24]=1;  
        tablero[20][25]=1;  tablero[20][26]=1;        
        
        //FILA 21
        
        tablero[21][1]=1;  tablero[21][2]=-1;  tablero[21][3]=-1;  tablero[21][4]=-1;  tablero[21][5]=-1;  tablero[21][6]=1;  
        tablero[21][7]=-1;  tablero[21][8]=-1;  tablero[21][9]=-1;  tablero[21][10]=-1;  tablero[21][11]=-1;  tablero[21][12]=1;  
        tablero[21][13]=-1;  tablero[21][14]=-1;  tablero[21][15]=1;  tablero[21][16]=-1;  tablero[21][17]=-1;  tablero[21][18]=-1;  
        tablero[21][19]=-1;  tablero[21][20]=-1;  tablero[21][21]=1;  tablero[21][22]=-1;  tablero[21][23]=-1;  tablero[21][24]=-1;  
        tablero[21][25]=-1;  tablero[21][26]=1;        
        
        //FILA 22
        
        tablero[22][1]=1;  tablero[22][2]=-1;  tablero[22][3]=-1;  tablero[22][4]=-1;  tablero[22][5]=-1;  tablero[22][6]=1;  
        tablero[22][7]=-1;  tablero[22][8]=-1;  tablero[22][9]=-1;  tablero[22][10]=-1;  tablero[22][11]=-1;  tablero[22][12]=1;  
        tablero[22][13]=-1;  tablero[22][14]=-1;  tablero[22][15]=1;  tablero[22][16]=-1;  tablero[22][17]=-1;  tablero[22][18]=-1;  
        tablero[22][19]=-1;  tablero[22][20]=-1;  tablero[22][21]=1;  tablero[22][22]=-1;  tablero[22][23]=-1;  tablero[22][24]=-1;  
        tablero[22][25]=-1;  tablero[22][26]=1;        
        
        //FILA 23
        
        tablero[23][1]=2;  tablero[23][2]=1;  tablero[23][3]=1;  tablero[23][4]=-1;  tablero[23][5]=-1;  tablero[23][6]=1;  
        tablero[23][7]=1;  tablero[23][8]=1;  tablero[23][9]=1;  tablero[23][10]=1;  tablero[23][11]=1;  tablero[23][12]=1;  
        tablero[23][13]=1;  tablero[23][14]=1;  tablero[23][15]=1;  tablero[23][16]=1;  tablero[23][17]=1;  tablero[23][18]=1;  
        tablero[23][19]=1;  tablero[23][20]=1;  tablero[23][21]=1;  tablero[23][22]=-1;  tablero[23][23]=-1;  tablero[23][24]=1;  
        tablero[23][25]=1;  tablero[23][26]=2;        
        
        //FILA 24
        
        tablero[24][1]=-1;  tablero[24][2]=-1;  tablero[24][3]=1;  tablero[24][4]=-1;  tablero[24][5]=-1;  tablero[24][6]=1;  
        tablero[24][7]=-1;  tablero[24][8]=-1;  tablero[24][9]=1;  tablero[24][10]=-1;  tablero[24][11]=-1;  tablero[24][12]=-1;  
        tablero[24][13]=-1;  tablero[24][14]=-1;  tablero[24][15]=-1;  tablero[24][16]=-1;  tablero[24][17]=-1;  tablero[24][18]=1;  
        tablero[24][19]=-1;  tablero[24][20]=-1;  tablero[24][21]=1;  tablero[24][22]=-1;  tablero[24][23]=-1;  tablero[24][24]=1;  
        tablero[24][25]=-1;  tablero[24][26]=-1;        
        
        //FILA 25
        
        tablero[25][1]=-1;  tablero[25][2]=-1;  tablero[25][3]=1;  tablero[25][4]=-1;  tablero[25][5]=-1;  tablero[25][6]=1;  
        tablero[25][7]=-1;  tablero[25][8]=-1;  tablero[25][9]=1;  tablero[25][10]=-1;  tablero[25][11]=-1;  tablero[25][12]=-1;  
        tablero[25][13]=-1;  tablero[25][14]=-1;  tablero[25][15]=-1;  tablero[25][16]=-1;  tablero[25][17]=-1;  tablero[25][18]=1;  
        tablero[25][19]=-1;  tablero[25][20]=-1;  tablero[25][21]=1;  tablero[25][22]=-1;  tablero[25][23]=-1;  tablero[25][24]=1;  
        tablero[25][25]=-1;  tablero[25][26]=-1;        
        
        //FILA 26
        
        tablero[26][1]=1;  tablero[26][2]=1;  tablero[26][3]=1;  tablero[26][4]=1;  tablero[26][5]=1;  tablero[26][6]=1;  
        tablero[26][7]=-1;  tablero[26][8]=-1;  tablero[26][9]=1;  tablero[26][10]=1;  tablero[26][11]=1;  tablero[26][12]=1;  
        tablero[26][13]=-1;  tablero[26][14]=-1;  tablero[26][15]=1;  tablero[26][16]=1;  tablero[26][17]=1;  tablero[26][18]=1;  
        tablero[26][19]=-1;  tablero[26][20]=-1;  tablero[26][21]=1;  tablero[26][22]=1;  tablero[26][23]=1;  tablero[26][24]=1;  
        tablero[26][25]=1;  tablero[26][26]=1;
        
        //FILA 27
        
        tablero[27][1]=1;  tablero[27][2]=-1;  tablero[27][3]=-1;  tablero[27][4]=-1;  tablero[27][5]=-1;  tablero[27][6]=-1;  
        tablero[27][7]=-1;  tablero[27][8]=-1;  tablero[27][9]=-1;  tablero[27][10]=-1;  tablero[27][11]=-1;  tablero[27][12]=1;  
        tablero[27][13]=-1;  tablero[27][14]=-1;  tablero[27][15]=1;  tablero[27][16]=-1;  tablero[27][17]=-1;  tablero[27][18]=-1;  
        tablero[27][19]=-1;  tablero[27][20]=-1;  tablero[27][21]=-1;  tablero[27][22]=-1;  tablero[27][23]=-1;  tablero[27][24]=-1;  
        tablero[27][25]=-1;  tablero[27][26]=1;
        
        //FILA 28
        
        tablero[28][1]=1;  tablero[28][2]=-1;  tablero[28][3]=-1;  tablero[28][4]=-1;  tablero[28][5]=-1;  tablero[28][6]=-1;  
        tablero[28][7]=-1;  tablero[28][8]=-1;  tablero[28][9]=-1;  tablero[28][10]=-1;  tablero[28][11]=-1;  tablero[28][12]=1;  
        tablero[28][13]=-1;  tablero[28][14]=-1;  tablero[28][15]=1;  tablero[28][16]=-1;  tablero[28][17]=-1;  tablero[28][18]=-1;  
        tablero[28][19]=-1;  tablero[28][20]=-1;  tablero[28][21]=-1;  tablero[28][22]=-1;  tablero[28][23]=-1;  tablero[28][24]=-1;  
        tablero[28][25]=-1;  tablero[28][26]=1;
        
        //FILA 29 *Filas completas de bolas pequeñas*
        
        for(int i=1; i<COLUMNAS-1; i++){
            tablero[29][i]=1;
        }
    }
}