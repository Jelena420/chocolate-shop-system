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
public class Cokolada extends AbstractDomainObject {

    private int cokoladaID;
    private String naziv;
    private String opis;
    private double cena;
    private VrstaCokolade vrstaCokolade;

    @Override
    public String toString() {
        return naziv + " (Cena: " + cena + "din)";
    }

    public Cokolada(int cokoladaID, String naziv, String opis, double cena, VrstaCokolade vrstaCokolade) {
        this.cokoladaID = cokoladaID;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.vrstaCokolade = vrstaCokolade;
    }

    public Cokolada() {
    }

    @Override
    public String nazivTabele() {
        return " cokolada ";
    }

    @Override
    public String alijas() {
        return " c ";
    }

    @Override
    public String join() {
        return " JOIN VRSTACOKOLADE VC ON (VC.VRSTACOKOLADEID = C.VRSTACOKOLADEID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            VrstaCokolade vc = new VrstaCokolade(rs.getInt("vrstaCokoladeID"),
                    rs.getString("vc.naziv"));
            
            Cokolada c = new Cokolada(rs.getInt("cokoladaID"), rs.getString("c.naziv"), 
                    rs.getString("opis"), rs.getDouble("c.cena"), vc);

            lista.add(c);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv, opis, cena, vrstaCokoladeID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + naziv + "', '" + opis + "', "
                + " " + cena + ", " + vrstaCokolade.getVrstaCokoladeID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " cena = " + cena + " ";
    }

    @Override
    public String uslov() {
        return " cokoladaID = " + cokoladaID;
    }

    @Override
    public String uslovZaSelect() {
        return " ORDER BY C.COKOLADAID ASC ";
    }

    public int getCokoladaID() {
        return cokoladaID;
    }

    public void setCokoladaID(int cokoladaID) {
        this.cokoladaID = cokoladaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public VrstaCokolade getVrstaCokolade() {
        return vrstaCokolade;
    }

    public void setVrstaCokolade(VrstaCokolade vrstaCokolade) {
        this.vrstaCokolade = vrstaCokolade;
    }

}
