/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.VacioException;
import java.util.Random;



/**
 *
 * @author andy
 */
public class Control {
    public static Integer ASENDENTE = 0;
    public static Integer DESENDENTE = 1;
    
    ListaEnlazada<Integer> num = new ListaEnlazada<>();
    Integer cantidad = 20000; //
    Integer limite = 20000; //

    public Control() {
        this.num = new ListaEnlazada<>();
//        try {
//            generarRandom();
//            mergeSort(DESENDENTE);
//            mergeSort(ASENDENTE);
//            quickSort(ASENDENTE);
//        } catch (VacioException ex) {
//        }
    }

    public ListaEnlazada<Integer> getNum() {
        return num;
    }

    public void setNum(ListaEnlazada<Integer> num) {
        this.num = num;
    }
    
    
    public void generarRandom() throws VacioException {
        Random random = new Random();

        for (int i = 0; i < cantidad; i++) {
            num.insertar(random.nextInt(limite));
        }
        System.out.println("---------LISTA INICIO--------");
        num.imprimir();
        System.out.println("---------LISTA FIN--------");

    }
    
    public void quickSort(Integer tipo){
        var arreglo = num.toArray();
        num.quicksort(arreglo, 0, arreglo.length - 1, tipo);
        num = num.toList(arreglo);
        
        System.out.println("----Lista con QuickSort----");
//        long startTimeQuick = System.currentTimeMillis();

        try {
            num.imprimir();
        } catch (VacioException ex) {
        }
//        long finalTimeQuick = System.currentTimeMillis() - startTimeQuick;
//        System.out.println("Tiempo" + finalTimeQuick);
        System.out.println("----Fin Lista QuickSort----");
    }    
    
    public void mergeSort(Integer tipo) {
        var arreglo = num.toArray();
        num.mergesort(arreglo, 0, arreglo.length - 1, tipo);
        num = num.toList(arreglo);
        
        System.out.println("----Lista con MergeSort----");
//        long starTimeMerge = System.currentTimeMillis();
        
        try {
            num.imprimir();
        } catch (VacioException ex) {
        }
//        long finalTimeMerge = System.currentTimeMillis() - starTimeMerge;
//        System.out.println("Tiempo de demora de metodo Merge " + finalTimeMerge);
        System.out.println("----Fin Lista MergeSort---");

    }
    
//    public void ordenarMergesort() {
//        var arreglo = num.toArray();
//        num.mergesort(arreglo, 0, arreglo.length-1, num );
//        //num.mergesort(arreglo, 0, arreglo.length-1, num);
//        num = num.toList(arreglo);
//         System.out.println("Lista con MergeSort");
//        try {
//            num.imprimir();
//        } catch (VacioException ex) {
//        }
//    }
    
//     public void quicksort() {
//        NodoLista<Integer> cabecera = num.getCabecera();
//        num.quickSort(cabecera, obtenerUltimo(cabecera));
//        //num.quickSort(cabecera, num.get(size()-1));
////        NodoLista<Integer> ultimoNodo = obtenerUltimo(cabecera);
////        num.quickSort(cabecera, ultimoNodo);
//        System.out.println("Lista en QuickSort");
//        try {   
//            num.imprimir();
//        } catch (VacioException ex) {
//        }
//    }
//     
//    private NodoLista<Integer> obtenerUltimo(NodoLista<Integer> cabecera){
//        if(cabecera == null || cabecera.getSig() == null){
//            return cabecera;
//        }
//        NodoLista<Integer> actual = cabecera;
//        while(actual.getSig() != null){
//            actual = actual.getSig();
//        }
//        return actual;
//    }
    
//    public void mergesort(){        
//        NodoLista<Integer> cabecera = num.getCabecera();
//        num.setCabecera(num.mergesort(cabecera, ASENDENTE));
//        System.out.println("Lista en asendente MergeSort");
//        try {
//            num.imprimir();
//        } catch (VacioException ex) {
//        }
 
//    }
}


