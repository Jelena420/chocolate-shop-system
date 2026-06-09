/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domain.StavkaPorudzbine;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelStavkePorudzbine extends AbstractTableModel {

    private ArrayList<StavkaPorudzbine> lista;
    private String[] kolone = {"Rb", "Cokolada", "Cena po komadu", "Kolicina", "Iznos"};
    private int rb;

    public TableModelStavkePorudzbine() {
        lista = new ArrayList<>();
    }

    public TableModelStavkePorudzbine(ArrayList<StavkaPorudzbine> stavkePorudzbine) {
        lista = stavkePorudzbine;
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
        StavkaPorudzbine sp = lista.get(row);

        switch (column) {
            case 0:
                return sp.getRb();
            case 1:
                return sp.getCokolada().getNaziv();
            case 2:
                return sp.getCokolada().getCena() + "din";
            case 3:
                return sp.getKolicina() + "kom";
            case 4:
                return sp.getIznos();

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaPorudzbine sp) {

        for (StavkaPorudzbine stavkaPorudzbine : lista) {
            if (stavkaPorudzbine.getCokolada().getCokoladaID() == sp.getCokolada().getCokoladaID()) {
                stavkaPorudzbine.setIznos(stavkaPorudzbine.getIznos() + sp.getIznos());
                stavkaPorudzbine.setKolicina(stavkaPorudzbine.getKolicina() + sp.getKolicina());
                fireTableDataChanged();
                return;
            }
        }

        rb = lista.size();
        sp.setRb(++rb);
        lista.add(sp);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaPorudzbine stavkaPorudzbine : lista) {
            stavkaPorudzbine.setRb(++rb);
        }

        fireTableDataChanged();
    }

    public double vratiUkupanIznos() {
        double ukupanIznos = 0;

        for (StavkaPorudzbine stavkaPorudzbine : lista) {
            ukupanIznos += stavkaPorudzbine.getIznos();
        }

        return ukupanIznos;
    }

    public ArrayList<StavkaPorudzbine> getLista() {
        return lista;
    }

}
