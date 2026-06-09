/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Cokolada;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Jelena
 */
public class TableModelCokolade extends AbstractTableModel implements Runnable {

    private ArrayList<Cokolada> lista;
    private String[] kolone = {"ID", "Naziv", "Opis", "Cena", "Vrsta"};
    private String parametar = "";

    public TableModelCokolade() {
        try {
            lista = ClientController.getInstance().getAllCokolada();
        } catch (Exception ex) {
            Logger.getLogger(TableModelCokolade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Cokolada c = lista.get(row);

        switch (column) {
            case 0:
                return c.getCokoladaID();
            case 1:
                return c.getNaziv();
            case 2:
                return c.getOpis();
            case 3:
                return c.getCena();
            case 4:
                return c.getVrstaCokolade();
            default:
                return null;
        }
    }

    public Cokolada getSelectedCokolada(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelCokolade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllCokolada();
            if (!parametar.equals("")) {
                ArrayList<Cokolada> novaLista = new ArrayList<>();
                for (Cokolada c : lista) {
                    if (c.getNaziv().toLowerCase().contains(parametar.toLowerCase())
                            || c.getOpis().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(c);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
