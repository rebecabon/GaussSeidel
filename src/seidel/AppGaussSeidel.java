package seidel;

import java.util.Scanner;

public class AppGaussSeidel {

    private static String problema;
    private static double[][] matriz;
    private static int nEcuaciones, nIncognitas, maxCalculos;
    private static double[] incognitas;
    private static double error;

    public static void main(String[] args) {
        Encabezado(); // datos generales del programa
        Formulario(); // cuestionario sobre informacion del problema
        // Prueba1(); // prepara datos para el problema 1
        // Prueba2(); // prepara datos para el problema 2
        System.out.println("PROBLEMA: " + problema);
        GaussSeidel(); // procesamiento del sistema de ecuaciones
        Resultados(); // imprimir valores finales
    }

    public static void GaussSeidel() {
        double eAX = 0;
        double [] valoresAnteriores = new double[nIncognitas];
        int contador = 1;

        // imprimir encabezado de la tabla
        System.out.format("+---------------+---------------+---------------+---------------+---------------+---------------+%n");
        System.out.print("| No.\t\t\t");
        for (int f = 1; f <= nIncognitas; f++) {
            System.out.print("| x" + f + "\t\t\t");
        }
        System.out.print("| ERROR TOTAL\t|\n");
        System.out.format("+---------------+---------------+---------------+---------------+---------------+---------------+%n");

        // comienzo de los calculos con Gauss Seidel
        do {
            // imprimir renglon de la tabla
            System.out.print(contador + "\t\t\t\t");
            for (int g = 0; g < nIncognitas; g++) {
                System.out.print(String.format("%.6f", incognitas[g]) + "\t\t");
            }
            System.out.print(String.format("%.6f", eAX));
            contador++;
            // preparar valores anteriores
            eAX = 0;
            for (int h = 0; h < nEcuaciones; h++){
                valoresAnteriores[h] = incognitas[h];
            }
            // despejar la incognita correspondiente a la diagonal principal en cada ecuacion
            for (int i = 0; i < nEcuaciones; i++) {
                // tomar el resultado original de la ecuacion
                incognitas[i] = matriz[i][incognitas.length];
                // restarle los productos de las incognitas secundarias con sus coeficientes
                for (int j = 0; j < nIncognitas; j++) {
                    if (j == i) {
                        // no restar la incognita despejada
                        continue;
                    }
                    incognitas[i] = incognitas[i] - (matriz[i][j] * incognitas[j]);
                }
                // dividir entre el coeficiente original de la incognita despejada
                incognitas[i] = incognitas[i] / matriz[i][i];
                // calculo del error
                eAX += Math.abs(Math.abs(incognitas[i]) - Math.abs(valoresAnteriores[i]));
            }
            System.out.println();
        } while (eAX > error);
        // imprimir ultimo renglon
        System.out.print(contador + "\t\t\t\t");
        for (int g = 0; g < nIncognitas; g++) {
            System.out.print(String.format("%.6f", incognitas[g]) + "\t\t");
        }
        System.out.println(String.format("%.6f", eAX));
        System.out.format("+---------------+---------------+---------------+---------------+---------------+---------------+%n");
    }

    public static void Resultados() {
        System.out.println("*** RESULTADOS ***");
        for (int i = 0; i < nIncognitas; i++) {
            System.out.println("INCOGNITA " + (i + 1) + ": " + String.format("%.6f", incognitas[i]));
        }
    }

    public static void Formulario() {
        Scanner leer = new Scanner(System.in);

        System.out.print("PROBLEMA: ");
        problema = leer.nextLine();

        // solicitud de los datos de la matriz inicial
        System.out.print("NUMERO DE ECUACIONES EN EL SISTEMA: ");
        nEcuaciones = leer.nextInt();
        System.out.print("NUMERO DE INCOGNITAS EN EL SISTEMA: ");
        nIncognitas = leer.nextInt();
        incognitas = new double[nIncognitas];
        matriz = new double[nEcuaciones][nIncognitas + 1];

        // datos adicionales al problema
        System.out.print("ERROR TOLERABLE: ");
        error = Math.abs(leer.nextDouble());
        System.out.print("CANTIDAD MAXIMA DE CALCULOS: ");
        maxCalculos = leer.nextInt();

        // solicitud de los valores iniciales en las incognitas del sistema
        System.out.println("\n*** VALORES INICIALES DE LAS INCOGNITAS ***");
        for (int i = 0; i < nIncognitas; i++){
            System.out.print("VALOR INICIAL DE x" + (i + 1) + ": ");
            incognitas[i] = leer.nextDouble();
        }

        // solicitud de los coeficientes en las ecuaciones de la matriz
        for (int i = 0; i < nEcuaciones; i++){
            System.out.println("\n*** ECUACION " + (i + 1) + " ***");
            for (int j = 0; j < nIncognitas; j++){
                System.out.print("COEFICIENTE EN VARIABLE x" + (j + 1) + " = ");
                matriz[i][j] = leer.nextDouble();
            }
            System.out.print("RESULTADO DE ECUACION " + (i + 1) + " = ");
            matriz[i][nIncognitas] = leer.nextDouble();
        }
    }

    public static void Encabezado() {
        System.out.println("INSTITUTO TECNOLOGICO DE CULIACAN");
        System.out.println("Ing. en Sistemas Computacionales");
        System.out.println("\nALUMNA: Bon Cabanillas Rebeca Sofia");
        System.out.println("METODOS NUMERICOS: METODO DE GAUSS SEIDEL");
        System.out.println("GRUPO: De 13:00 a 14:00 HRS");
        System.out.println("\nEste programa ejecuta la solucion de un sistema" +
                "\nde ecuaciones utilizando el metodo de Gauss Seidel\n");
    }

    public static void Prueba2() {
        // problema
        problema = "¿Cual es la poblacion de cada especie de peces que habita el lago" +
                "\nconsiderando que todo el alimento diario se consume?";
        // crear matriz y arreglo
        nIncognitas = 4;
        nEcuaciones = 4;
        incognitas = new double[nIncognitas];
        matriz = new double[nEcuaciones][nIncognitas + 1];
        // llenar matriz de coeficientes
        // ecuacion 1
        matriz[0][0] = 20;
        matriz[0][1] = 3;
        matriz[0][2] = 6;
        matriz[0][3] = 8;
        matriz[0][4] = 3000;
        // ecuacion 2
        matriz[1][0] = 9;
        matriz[1][1] = 18;
        matriz[1][2] = 5;
        matriz[1][3] = 3;
        matriz[1][4] = 4200;
        // ecuacion 3
        matriz[2][0] = 3;
        matriz[2][1] = 3;
        matriz[2][2] = 12;
        matriz[2][3] = 7;
        matriz[2][4] = 4800;
        // ecuacion 4
        matriz[3][0] = 2;
        matriz[3][1] = 2;
        matriz[3][2] = 6;
        matriz[3][3] = 19;
        matriz[3][4] = 3600;
        // llenar arreglo de valores iniciales p/incognitas
        incognitas[0] = 100;
        incognitas[1] = 100;
        incognitas[2] = 100;
        incognitas[3] = 100;
        // datos adicionales
        error = 0.0001;
        maxCalculos = 50;
    }

    public static void Prueba1() {
        // problema
        problema = "¿Cual es el precio de cada caja de vitaminas, considerando que" +
                "\nel precio no haya variado en el periodo de compras?";
        // crear matriz y arreglo
        nIncognitas = 4;
        nEcuaciones = 4;
        incognitas = new double[nIncognitas];
        matriz = new double[nEcuaciones][nIncognitas + 1];
        // llenar matriz de coeficientes
        // ecuacion 1
        matriz[0][0] = 42;
        matriz[0][1] = 15;
        matriz[0][2] = 20;
        matriz[0][3] = 10;
        matriz[0][4] = 79100;
        // ecuacion 2
        matriz[1][0] = 12;
        matriz[1][1] = 35;
        matriz[1][2] = 20;
        matriz[1][3] = 8;
        matriz[1][4] = 70700;
        // ecuacion 3
        matriz[2][0] = 15;
        matriz[2][1] = 15;
        matriz[2][2] = 40;
        matriz[2][3] = 10;
        matriz[2][4] = 77500;
        // ecuacion 4
        matriz[3][0] = 10;
        matriz[3][1] = 10;
        matriz[3][2] = 15;
        matriz[3][3] = 50;
        matriz[3][4] = 92000;
        // llenar arreglo de valores iniciales p/incognitas
        incognitas[0] = 700;
        incognitas[1] = 800;
        incognitas[2] = 900;
        incognitas[3] = 1000;
        // datos adicionales
        error = 0.0001;
        maxCalculos = 50;
    }
}
