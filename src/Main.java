import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        //Diseño de la matriz (10x10 rellena de *)
        char[][] matriz = new char [10][10];
        int vidas = 10;
        for (int fila=0;fila < matriz.length; fila++ ){
            for (int columna=0; columna < matriz.length; columna++){
                matriz[fila][columna]= '*';
            }
        }
        //Definir y colocar los valores robot (R) y final(F)
        char robot = 'R';
        matriz[0][0] = robot;
        char meta = 'F';
        matriz[9][9] = meta;
        // Establezco la variable Random y coloco 5 % y 1 B de forma que no se solapen unas y otras (Es decir cunado haya*)
        Random aleatorio = new Random();
        int filaRandom = aleatorio.nextInt(10);
        int columnaRandom = aleatorio.nextInt(10);
        for (int i=0; i<5; i++){
            do {
                filaRandom  = aleatorio.nextInt(10);
                columnaRandom = aleatorio.nextInt(10);
            } while (matriz[filaRandom][columnaRandom] != '*');
            matriz[filaRandom][columnaRandom] = 'X';
        }
        for (int i=0; i<1; i++){
            do {
                filaRandom  = aleatorio.nextInt(10);
                columnaRandom = aleatorio.nextInt(10);
            } while (matriz[filaRandom][columnaRandom] != '*');
            matriz[filaRandom][columnaRandom] = 'B';
        }

        // Intento de moverse el personaje y con sistema de vidas
        System.out.println("El jugador se encuentra en la posición 0,0");
        System.out.println("Para moverse use W A S D");
        Scanner sc = new Scanner(System.in);
        int filarobot = 0;
        int columnarobot= 0;




        //Imprimir la matriz
        for (int fila=0; fila < matriz.length; fila++){
            for (int columna=0; columna < matriz[fila].length; columna++){
                System.out.print(matriz[fila][columna] + " ");
            }
            System.out.println();
        }
        while (vidas > 0){
            System.out.println("The quedan " + vidas + " vidas");
            System.out.print("Dime una letra:");
            char desplazamiento = sc.next().toUpperCase().charAt(0);
            System.out.println("La letra elegida es " + desplazamiento);

            int nuevaFila = filarobot;
            int nuevaColumna = columnarobot;
            // Los controles del desplazamiento y evitando que se salga del tablero
            if (desplazamiento == 'D') {
                nuevaColumna++;
            } else if (desplazamiento == 'S') {
                nuevaFila++;
            } else if (desplazamiento == 'W') {
                nuevaFila--;
            } else if (desplazamiento == 'A'){
                nuevaColumna--;
            } else {
                System.out.println("Movimiento no válido. Usa W, A, S o D.");
                continue;
            }

            // Evitar que el personaje (R) se salga del tablero
            if (nuevaFila < 0 || nuevaFila >= matriz.length ||
                    nuevaColumna < 0 || nuevaColumna >= matriz[0].length) {
                System.out.println("¡No puedes salirte del tablero!");
                continue;
            }

            // Defino la funcion para cada una de las variabes (les doy una "utilidad")
            matriz[filarobot][columnarobot] = '*';
            filarobot = nuevaFila;
            columnarobot = nuevaColumna;

            if (matriz[filarobot][columnarobot] == 'X'){
                System.out.println("Movimiento bloqueado");
                if (desplazamiento == 'D') {
                    nuevaColumna--;
                } else if (desplazamiento == 'S') {
                    nuevaFila--;
                } else if (desplazamiento == 'W') {
                    nuevaFila++;
                } else if (desplazamiento == 'A'){
                    nuevaColumna++;
                } else {
                    System.out.println("Movimiento no válido. Usa W, A, S o D.");
                    continue;
                }
            }
            if (matriz[filarobot][columnarobot] == 'B'){
                System.out.println("Has recargado 2 baterias");
                vidas++;
                vidas++;
            }
            if (matriz[filarobot][columnarobot] == 'F') {
                System.out.println("¡Has llegado a la meta!");
            }
            matriz[filarobot][columnarobot] = robot;

            // imprimir la matriz
            for (int fila=0; fila< matriz.length; fila++){
                for (int columna=0 ; columna < matriz[fila].length; columna++){
                    System.out.print(matriz[fila][columna] + " ");
                }
                System.out.println();
            }
        }

    }
}
