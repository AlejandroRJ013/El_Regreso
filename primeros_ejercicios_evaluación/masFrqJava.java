package primeros_ejercicios_evaluación;

import java.util.*;

public class masFrqJava {
    private int numero = 0;
    private int contador = 0;
    private HashMap<Integer, Integer> numCant = new HashMap<>();

    public static void main(String args[]) {
        ArrayList<Integer> numeros = recogerNumeros();
        System.out.println(numeros);
    }

    private static ArrayList<Integer> recogerNumeros() {
        ArrayList<Integer> numeros = new ArrayList<>();s
        System.out.println("¿Cuantos números quieres ingresar?");
        Scanner sc = new Scanner(System.in);
        int cantidad = sc.nextInt();
        System.out.println("Inserte los números:");
        int num = 0;
        for (int i = 0; i < cantidad; i++) {
            num = sc.nextInt();
            numeros.add(num);
        }
        sc.close();
        return numeros;
    }

    private static HashMap<Integer,Integer>
}