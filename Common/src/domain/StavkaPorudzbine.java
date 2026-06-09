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
public class StavkaPorudzbine extends AbstractDomainObject {

    private Porudzbina porudzbina;
    private int rb;
    private int kolicina;
    private double cena;
    private double iznos;
    private Cokolada cokolada;

    public StavkaPorudzbine(Porudzbina porudzbina, int rb, int kolicina, double cena, double iznos, Cokolada cokolada) {
        this.porudzbina = porudzbina;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.iznos = iznos;
        this.cokolada = cokolada;
    }

    public StavkaPorudzbine() {
    }

    @Override
    public String nazivTabele() {
        return " StavkaPorudzbine ";
    }

    @Override
    public String alijas() {
        return " sp ";
    }

    @Override
    public String join() {
        return " JOIN PORUDZBINA P ON (P.PORUDZBINAID = SP.PORUDZBINAID)\n"
                + "JOIN KUPAC K ON (K.KUPACID = P.KUPACID)\n"
                + "JOIN GRAD G ON (G.GRADID = K.GRADID)\n"
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = P.ADMINISTRATORID)\n"
                + "JOIN COKOLADA C ON (C.COKOLADAID = SP.COKOLADAID)\n"
                + "JOIN VRSTACOKOLADE VC ON (VC.VRSTACOKOLADEID = C.VRSTACOKOLADEID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getInt("AdministratorID"),
                    rs.getString("a.Ime"), rs.getString("a.Prezime"),
                    rs.getString("korisnickoIme"), rs.getString("lozinka"));

            Grad g = new Grad(rs.getInt("GradID"), rs.getString("g.naziv"));

            Kupac k = new Kupac(rs.getInt("KupacID"),
                    rs.getString("k.Ime"), rs.getString("k.Prezime"),
                    rs.getString("email"), rs.getString("telefon"), g);

            Porudzbina p = new Porudzbina(rs.getInt("porudzbinaID"),
                    rs.getTimestamp("datumVreme"), rs.getDate("datumIsporuke"),
                    rs.getDouble("ukupanIznos"), k, a, new ArrayList<>());

            VrstaCokolade vc = new VrstaCokolade(rs.getInt("vrstaCokoladeID"),
                    rs.getString("vc.naziv"));

            Cokolada r = new Cokolada(rs.getInt("cokoladaID"), rs.getString("c.naziv"),
                    rs.getString("opis"), rs.getDouble("c.cena"), vc);

            StavkaPorudzbine sp = new StavkaPorudzbine(p, rs.getInt("rb"),
                    rs.getInt("kolicina"), rs.getDouble("sp.cena"),
                    rs.getDouble("iznos"), r);

            lista.add(sp);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (porudzbinaID, rb, kolicina, cena, iznos, cokoladaID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + porudzbina.getPorudzbinaID() + ", " + rb + ", "
                + " " + kolicina + ", " + cena + ", " + iznos + ", " + cokolada.getCokoladaID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " kolicina = " + kolicina
                + ", cena = " + cena
                + ", iznos = " + iznos
                + ", cokoladaID = " + cokolada.getCokoladaID();
    }

    @Override
    public String uslov() {
        return " porudzbinaID = " + porudzbina.getPorudzbinaID()
                + " AND rb = " + rb;
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE P.PORUDZBINAID = " + porudzbina.getPorudzbinaID();
    }

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Cokolada getCokolada() {
        return cokolada;
    }

    public void setCokolada(Cokolada cokolada) {
        this.cokolada = cokolada;
    }

}
