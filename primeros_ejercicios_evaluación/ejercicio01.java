package primeros_ejercicios_evaluación;

import java.util.*;

public class ejercicio01 {
    public static void main(String args[]) {
        ArrayList<Integer> numeros = recogerNumeros();
        // System.out.println(numeros);
        HashMap<Integer, Integer> numCant = rellenarHash(numeros);
        // System.out.println(numCant);
        ArrayList<Integer> numFreq = masRepetido(numCant);
        // System.out.println(numFreq);
        int numFinal = repetidoOMenor(numFreq);
        System.out.println(numFinal);
    }

    private static ArrayList<Integer> recogerNumeros() {
        ArrayList<Integer> numeros = new ArrayList<>();
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

    private static HashMap<Integer, Integer> rellenarHash(ArrayList<Integer> numeros) {
        HashMap<Integer, Integer> numCant = new HashMap<>();
        for (int i = 0; i < numeros.size(); i++) {
            if (numCant.containsKey(numeros.get(i))) {
                numCant.put(numeros.get(i), numCant.get(numeros.get(i)) + 1);
            } else {
                numCant.put(numeros.get(i), 1);
            }
        }
        return numCant;
    }

    private static ArrayList<Integer> masRepetido(HashMap<Integer, Integer> numCant) {
        int mayorRep = 0;
        ArrayList<Integer> numFreq = new ArrayList<>();
        for (int numeros : numCant.keySet()) {
            if (numCant.get(numeros) == mayorRep) {
                numFreq.add(numeros);
            } else if (numCant.get(numeros) > mayorRep) {
                numFreq.clear();
                numFreq.add(numeros);
                mayorRep = numCant.get(numeros);
            }
        }
        return numFreq;
    }

    private static int repetidoOMenor(ArrayList<Integer> numFreq) {
        int numero = numFreq.get(0);
        if (numFreq.size() == 1) {
            return numero;
        }
        for (int num : numFreq) {
            if (num < numero) {
                numero = num;
            }
        }
        return numero;
    }
}