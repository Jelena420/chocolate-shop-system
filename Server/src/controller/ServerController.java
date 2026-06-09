/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Cokolada;
import domain.Grad;
import domain.Kupac;
import domain.Porudzbina;
import domain.VrstaCokolade;
import java.util.ArrayList;
import so.cokolada.SOAddCokolada;
import so.cokolada.SODeleteCokolada;
import so.cokolada.SOGetAllCokolada;
import so.cokolada.SOUpdateCokolada;
import so.grad.SOGetAllGrad;
import so.kupac.SOAddKupac;
import so.kupac.SODeleteKupac;
import so.kupac.SOGetAllKupac;
import so.kupac.SOUpdateKupac;
import so.login.SOLogin;
import so.porudzbina.SOAddPorudzbina;
import so.porudzbina.SODeletePorudzbina;
import so.porudzbina.SOGetAllPorudzbina;
import so.porudzbina.SOUpdatePorudzbina;
import so.vrsta_cokolade.SOGetAllVrstaCokolade;

/**
 *
 * @author Jelena
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Administrator> ulogovaniAdministratori = new ArrayList<>();

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Administrator> getUlogovaniAdministratori() {
        return ulogovaniAdministratori;
    }

    public void setUlogovaniAdministratori(ArrayList<Administrator> ulogovaniAdministratori) {
        this.ulogovaniAdministratori = ulogovaniAdministratori;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }

    public void addKupac(Kupac kupac) throws Exception {
        (new SOAddKupac()).templateExecute(kupac);
    }

    public void addCokolada(Cokolada cokolada) throws Exception {
        (new SOAddCokolada()).templateExecute(cokolada);
    }

    public void addPorudzbina(Porudzbina porudzbina) throws Exception {
        (new SOAddPorudzbina()).templateExecute(porudzbina);
    }

    public void deleteKupac(Kupac kupac) throws Exception {
        (new SODeleteKupac()).templateExecute(kupac);
    }

    public void deleteCokolada(Cokolada cokolada) throws Exception {
        (new SODeleteCokolada()).templateExecute(cokolada);
    }

    public void deletePorudzbina(Porudzbina porudzbina) throws Exception {
        (new SODeletePorudzbina()).templateExecute(porudzbina);
    }

    public void updateKupac(Kupac kupac) throws Exception {
        (new SOUpdateKupac()).templateExecute(kupac);
    }

    public void updateCokolada(Cokolada cokolada) throws Exception {
        (new SOUpdateCokolada()).templateExecute(cokolada);
    }

    public void updatePorudzbina(Porudzbina porudzbina) throws Exception {
        (new SOUpdatePorudzbina()).templateExecute(porudzbina);
    }

    public ArrayList<Kupac> getAllKupac() throws Exception {
        SOGetAllKupac so = new SOGetAllKupac();
        so.templateExecute(new Kupac());
        return so.getLista();
    }

    public ArrayList<Cokolada> getAllCokolada() throws Exception {
        SOGetAllCokolada so = new SOGetAllCokolada();
        so.templateExecute(new Cokolada());
        return so.getLista();
    }

    public ArrayList<Porudzbina> getAllPorudzbina(Kupac kupac) throws Exception {
        SOGetAllPorudzbina so = new SOGetAllPorudzbina();
        
        Porudzbina p = new Porudzbina();
        p.setKupac(kupac);
        
        so.templateExecute(p);
        return so.getLista();
    }

    public ArrayList<VrstaCokolade> getAllVrstaCokolade() throws Exception {
        SOGetAllVrstaCokolade so = new SOGetAllVrstaCokolade();
        so.templateExecute(new VrstaCokolade());
        return so.getLista();
    }

    public ArrayList<Grad> getAllGrad() throws Exception {
        SOGetAllGrad so = new SOGetAllGrad();
        so.templateExecute(new Grad());
        return so.getLista();
    }

    public void logout(Administrator ulogovani) {
        ulogovaniAdministratori.remove(ulogovani);
    }

}
