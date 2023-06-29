/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.tabla;

import controlador.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andy
 */
public class ModeloTablaN extends AbstractTableModel{
    
    private ListaEnlazada<Integer> lista = new ListaEnlazada<>();

    public ListaEnlazada<Integer> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Integer> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Integer in = null;
        try {
            in = lista.get(i);
        } catch (Exception e) {
        }
         switch (i1) {
            case 0: return in;
            
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch (column) {
            case 0: return "Numeros";
                
            default:
                throw new AssertionError();
        }
    }  
}
