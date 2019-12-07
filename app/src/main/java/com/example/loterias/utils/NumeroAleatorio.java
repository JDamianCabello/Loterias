package com.example.loterias.utils;

import java.util.ArrayList;

public class NumeroAleatorio {
    private static ArrayList<Integer> lista;


    /**
     * Método que genera la lista de Números Aleatorios
     * @return lista de números aleatorios
     */
    public static ArrayList<Integer> getListaNumerosAleatorios(int valorInicial, int valorFinal, int cantidad) {
        lista=new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            generaNumeroAleatorio(valorInicial,valorFinal);
        }
        return lista;

    }

    /**
     * Método que crea un número aleatorio en el rango establecido
     *
     * @return
     */
    public static  int numeroAleatorio(int valorInicial, int valorFinal) {
        return (int) (Math.random() * (valorFinal - valorInicial + 1) + valorInicial);
    }

    private static int generaNumeroAleatorio(int valorInicial, int valorFinal) {
        if (lista.size() < (valorFinal - valorInicial) + 1) {
            //Aun no se han generado todos los numeros
            int numero = numeroAleatorio(valorInicial,valorFinal);//genero un numero
            if (lista.isEmpty()) {//si la lista esta vacia
                lista.add(numero);
                return numero;
            } else {//si no esta vacia
                if (lista.contains(numero)) {//Si el numero que generé esta contenido en la lista
                    return generaNumeroAleatorio(valorInicial,valorFinal);//recursivamente lo mando a generar otra vez
                } else {//Si no esta contenido en la lista
                    lista.add(numero);
                    return numero;
                }
            }
        }
        return -1; //Ya se ha generado todos los números
    }
}
