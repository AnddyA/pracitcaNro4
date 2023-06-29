/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.ed.lista;

import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author andy
 */
public class ListaEnlazada<E> {

    private NodoLista<E> cabecera;
    private Integer size = 0;

    public NodoLista getCabecera() {
        return cabecera;
    }

    public void setCabecera(NodoLista cabecera) {
        this.cabecera = cabecera;
    }

    public boolean isEmpty() {
        return cabecera == null;
    }

    public boolean insertar(E info) {
        NodoLista<E> nuevo = new NodoLista<>(info, null);
        if (isEmpty()) {
            this.cabecera = nuevo;
            this.size++;
        } else {
            NodoLista<E> aux = cabecera;
            /*for(int i = 0; i < size()-1; i++){
                aux = aux.getSig();
            }*/

            while (aux.getSig() != null) {
                aux = aux.getSig();
            }

            aux.setSig(nuevo);
            this.size++;
        }

        return true;
    }

    public void imprimir() throws VacioException {
        if (isEmpty()) {
            throw new VacioException();
        } else {
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < size(); i++) {
                System.out.println(aux.getInfo() + " ");
                aux = aux.getSig();
            }
        }
    }

    public void deleteAll() {
        this.cabecera = null;
        this.size = 0;
    }

    public void insertarInicio(E info) {

        if (isEmpty()) {
            insertar(info);
        } else {
            NodoLista<E> nuevo = new NodoLista<>(info, null);
            nuevo.setSig(cabecera);
            cabecera = nuevo;
            size++;
        }
    }

    public void insertarPosicion(E info, Integer pos) throws PosicionException {
        if (isEmpty()) {
            insertar(info);
        } else if (pos >= 0 && pos < size() && pos == 0) {
            insertarInicio(info);
        } else if (pos >= 0 && pos < size()) {
            NodoLista<E> nuevo = new NodoLista<>(info, null);
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < (pos - 1); i++) {
                aux = aux.getSig();
            }
            NodoLista<E> sig = aux.getSig();
            aux.setSig(nuevo);
            nuevo.setSig(sig);
            size++;
        } else {
            throw new PosicionException();
        }
    }

    public E get(Integer pos) throws VacioException, PosicionException {

        if (isEmpty()) {
            throw new VacioException();
        } else {
            E dato = null;
            if (pos >= 0 && pos < size()) {
                if (pos == 0) {
                    dato = cabecera.getInfo();
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < pos; i++) {
                        aux = aux.getSig();
                    }
                    dato = aux.getInfo();
                }
            } else {
                throw new PosicionException();
            }
            return dato;
        }

    }

    public E delete(Integer pos) throws VacioException, PosicionException {

        if (isEmpty()) {
            throw new VacioException();
        } else {
            E dato = null;
            if (pos >= 0 && pos < size()) {
                if (pos == 0) {
                    dato = cabecera.getInfo();
                    cabecera = cabecera.getSig();
                    size--;
                } else {
                    NodoLista<E> aux = cabecera;
                    for (int i = 0; i < (pos - 1); i++) {
                        aux = aux.getSig();
                    }

                    //if((pos + 1) == size()){
                    NodoLista<E> aux1 = aux.getSig();
                    dato = aux1.getInfo();
                    //}else{
                    //dato = aux.getInfo();
                    //}
                    NodoLista<E> proximo = aux.getSig();
                    aux.setSig(proximo.getSig());
                    size--;
                }
            } else {
                throw new PosicionException();
            }
            return dato;
        }

    }

    public E[] toArray() {
        E[] matriz = null;
        if (this.size > 0) {
            matriz = (E[]) java.lang.reflect.Array.newInstance(cabecera.getInfo().getClass(), this.size);
            NodoLista<E> aux = cabecera;
            for (int i = 0; i < this.size; i++) {
                matriz[i] = aux.getInfo();
                aux = aux.getSig();
            }
        }
        return matriz;
    }

    public ListaEnlazada<E> toList(E[] matriz) {
        this.deleteAll();
        for (int i = 0; i < matriz.length; i++) {
            this.insertar(matriz[i]);
        }
        return this;
    }

    public Integer size() {
        return size;
    }
    
    //Metodo mquickSort
    
    public void quicksort(E[] array, int inicio, int fin, Integer tipo) {

        if (inicio < fin) {
            int pi = particion(array, inicio, fin, tipo);

            quicksort(array, inicio, pi - 1, tipo);
            quicksort(array, pi + 1, fin, tipo);
        }
    }

    public int particion(E[] array, int inicio, int fin, Integer tipo) {
        E pivote = array[fin];
        int i = (inicio - 1);
        for (int j = inicio; j < fin; j++) {
            if (tipo == 0) {
                if ((Integer) (array[j]) <= ((Integer) pivote)) {
                    i++;
                    E temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            } else {
                if ((Integer) (array[j]) >= ((Integer) pivote)) {
                    i++;
                    E temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
//            if ((Integer)(array[j]) <= ((Integer)pivote)){
//                i++;
//                E temp = array[i];
//                array[i] = array[j];
//                array[j] = temp;
//            }
        }

        E temp = array[i + 1];
        array[i + 1] = array[fin];
        array[fin] = temp;

        return i + 1;
    }

    //metodo MergeSort
    public void mergesort(E[] array, int inicio, int fin, Integer tipo) {
        if (inicio < fin) {
            int mid = inicio + (fin - inicio) / 2;
            mergesort(array, inicio, mid, tipo);
            mergesort(array, mid + 1, fin, tipo);
            merge(array, inicio, mid, fin, tipo);
        }
    }

    public void merge(E[] array, int inicio, int fin, int high, Integer tipo) {
        int n1 = fin - inicio + 1;
        int n2 = high - fin;

        E[] left = Arrays.copyOfRange(array, inicio, fin + 1);
        E[] right = Arrays.copyOfRange(array, fin + 1, high + 1);

        int i = 0, j = 0, k = inicio;
        while (i < n1 && j < n2) {
            if (tipo == 0) {
                if (((Integer) left[i]) <= ((Integer) right[j])) {
                    array[k] = left[i];
                    i++;
                } else {
                    array[k] = right[j];
                    j++;
                }
                k++;
            } else {
                if (((Integer) left[i]) >= ((Integer) right[j])) {
                    array[k] = left[i];
                    i++;
                } else {
                    array[k] = right[j];
                    j++;
                }
                k++;
            }
//            if (((Integer) left[i]) <= ((Integer) right[j])) {
//                array[k] = left[i];
//                i++;
//            } else {
//                array[k] = right[j];
//                j++;
//            }
//            k++;
        }
        while (i < n1) {
            array[k] = left[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = right[j];
            j++;
            k++;
        }
    }
    
    //desordenar numeros
    
    public void desordenacion() throws VacioException {

        var desArr = this.toArray();

        Random random = new Random();

        for (int i = 0; i < desArr.length; i++) {

            int num = random.nextInt(desArr.length - 1);

            var tmp = desArr[i];

            desArr[i] = desArr[num];

            desArr[num] = tmp;

        }

        this.toList(desArr);

    }

    // ...
}

//    public void quickSort(NodoLista<E> inicio, NodoLista<E> fin) {
//        if (inicio != null && fin != null && inicio != fin && inicio != fin.getSig()) {
//            NodoLista<E> pivote = particion(inicio, fin);
//            quickSort(inicio, pivote);
//            quickSort(pivote.getSig(), fin);
//        }
//    }
//
//    private NodoLista<E> particion(NodoLista<E> inicio, NodoLista<E> fin) {
//        E pivote = fin.getInfo();
//        NodoLista<E> i = inicio;
//
//        for (NodoLista<E> j = inicio; j != fin; j = j.getSig()) {
//            if (j.getInfo().toString().compareToIgnoreCase(pivote.toString()) <= 0) {
//                E temp = i.getInfo();
//                i.setInfo(j.getInfo());
//                j.setInfo(temp);
//                i = i.getSig();
//            }
//            j = j.getSig();
//        }
//
//        E temp = i.getInfo();
//        i.setInfo(fin.getInfo());
//        fin.setInfo(temp);
//
//        return i;
//    }
//    public void quickSort() {
//        cabecera = quickSortRecursivo(cabecera, null);
//    }
//
//    private NodoLista<E> quickSortRecursivo(NodoLista<E> inicio, NodoLista<E> fin) {
//        if (inicio == fin || inicio == null || inicio.getSig() == null) {
//            return inicio;
//        }
//
//        NodoLista<E> pivoteR = particion(inicio, fin);
//        quickSortRecursivo(inicio, pivoteR);
//        quickSortRecursivo(pivoteR.getSig().getSig(), fin);
//
//        return pivoteR.getSig();
//    }
//
//    private NodoLista<E> particion(NodoLista<E> inicio, NodoLista<E> fin) {
//        E pivote = fin.getInfo();
//        NodoLista<E> i = inicio.getSig();
//        NodoLista<E> j = inicio;
//
//        while (i != fin) {
//            if (i.getInfo().toString().compareToIgnoreCase(pivote.toString()) <= 0) {
//                j = (j == null) ? inicio : j.getSig();
//                cambiar(i, j);
//            }
//            i = i.getSig();
//        }
//        if (j == null) {
//            j = inicio;
//        } else {
//            j = j.getSig();
//        }
//
//        cambiar(j, fin);
//        return j;
//    }
//
//    private void cambiar(NodoLista<E> node1, NodoLista<E> node2) {
//        E temp = node1.getInfo();
//        node1.setInfo(node2.getInfo());
//        node2.setInfo(temp);
//
//    }
//metodo mergeSort
//    private NodoLista<E> merge(NodoLista<E> inicio, NodoLista<E> fin, Integer tipo) {
//        NodoLista<E> resultado = null;
//
//        if (inicio == null) {
//            return fin;
//        }
//        if (fin == null) {
//            return inicio;
//        }
//
//        if (tipo == 0) {
//            if (inicio.getInfo().toString().compareToIgnoreCase(fin.getInfo().toString()) <= 0) {
//                resultado = inicio;
//                resultado.setSig(merge(inicio.getSig(), fin, tipo));
//            } else {
//                resultado = fin;
//                resultado.setSig(merge(inicio, fin.getSig(), tipo));
//            }
//        } else {
//            if (inicio.getInfo().toString().compareToIgnoreCase(fin.getInfo().toString()) >= 0) {
//                resultado = inicio;
//                resultado.setSig(merge(inicio.getSig(), fin, tipo));
//            } else {
//                resultado = fin;
//                resultado.setSig(merge(inicio, fin.getSig(), tipo));
//            }
//        }
//
//        return resultado;
//    }
//
//    public NodoLista<E> mergesort(NodoLista<E> cabecera, Integer tipo) {
//        if (cabecera == null || cabecera.getSig() == null) {
//            return cabecera;
//        }
//
//        NodoLista<E> cambio = intercambiar(cabecera);
//        NodoLista<E> sigCambio = cambio.getSig();
//        cambio.setSig(null);
//
//        NodoLista<E> inicio = mergesort(cabecera, tipo);
//        NodoLista<E> fin = mergesort(sigCambio, tipo);
//
//        return merge(inicio, fin, tipo);
//    }
//
//    private NodoLista<E> intercambiar(NodoLista<E> cabecera) {
//        if (cabecera == null) {
//            return cabecera;
//        }
//
//        NodoLista<E> inicio = cabecera;
//        NodoLista<E> fin = cabecera;
//
//        while (fin.getSig() != null && fin.getSig().getSig() != null) {
//            inicio = inicio.getSig();
//            fin = fin.getSig().getSig();
//        }
//
//        return inicio;
//    }

