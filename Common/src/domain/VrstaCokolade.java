/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jelena
 */
public class VrstaCokolade extends AbstractDomainObject {

    private int vrstaCokoladeID;
    private String naziv;

    @Override
    public String toString() {
        return naziv;
    }

    public VrstaCokolade(int vrstaCokoladeID, String naziv) {
        this.vrstaCokoladeID = vrstaCokoladeID;
        this.naziv = naziv;
    }

    public VrstaCokolade() {
    }

    @Override
    public String nazivTabele() {
        return " vrstaCokolade ";
    }

    @Override
    public String alijas() {
        return " vc ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            VrstaCokolade vc = new VrstaCokolade(rs.getInt("vrstaCokoladeID"),
                    rs.getString("vc.naziv"));

            lista.add(vc);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "' ";
    }

    @Override
    public String uslov() {
        return " vrstaCokoladeID = " + vrstaCokoladeID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public int getVrstaCokoladeID() {
        return vrstaCokoladeID;
    }

    public void setVrstaCokoladeID(int vrstaCokoladeID) {
        this.vrstaCokoladeID = vrstaCokoladeID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}
