/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.porudzbina;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Porudzbina;
import domain.StavkaPorudzbine;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import so.AbstractSO;

/**
 *
 * @author Jelena
 */
public class SOUpdatePorudzbina extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Porudzbina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Porudzbina!");
        }

        Porudzbina p = (Porudzbina) ado;

        if (!p.getDatumIsporuke().after(new Date())) {
            throw new Exception("Datum isporuke mora biti posle danasnjeg datuma!");
        }

        if (p.getStavkePorudzbine().isEmpty()) {
            throw new Exception("Porudzbina mora da ima minimum jednu stavku!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Porudzbina p = (Porudzbina) ado;

        // Azuriramo porudzbinu
        DBBroker.getInstance().update(p);

        // Radimo: DELETE samo za uklonjene, UPDATE za zadrzane, INSERT za nove
        // Uzimamo stare stavke iz baze
        ArrayList<StavkaPorudzbine> stareStavke
                = (ArrayList<StavkaPorudzbine>) (ArrayList<?>) DBBroker.getInstance()
                        .select(new StavkaPorudzbine(p, 0, 0, 0, 0, null));

        HashMap<Integer, StavkaPorudzbine> mapaStarih = new HashMap<>();
        for (StavkaPorudzbine sp : stareStavke) {
            mapaStarih.put(sp.getRb(), sp);
        }

        // Pravimo mapu novih stavki po rb
        HashMap<Integer, StavkaPorudzbine> mapaNovih = new HashMap<>();
        for (StavkaPorudzbine nova : p.getStavkePorudzbine()) {
            mapaNovih.put(nova.getRb(), nova);
        }

        // Redosled: DELETE -> UPDATE -> INSERT
        // Ako imamo manje stavki u novim nego u starim -> radimo DELETE rb koji ne postoji u novim i UPDATE koji postoji
        // Ako imamo vise stavki u novim nego u starim -> radimo INSERT rb koji ne postoji i UPDATE koji postoji
        // Ako imamo isto stavki u starim i novim -> radimo samo UPDATE za sve
        // U sustini imamo 3 opcije:
        // 1. DELETE + UPDATE
        // 2. UPDATE + INSERT
        // 3. SAMO UPDATE
        // DELETE – obrišemo one koje su u starim, a nema ih u novim
        for (StavkaPorudzbine stara : stareStavke) {
            if (!mapaNovih.containsKey(stara.getRb())) {
                DBBroker.getInstance().delete(stara);
            }
        }

        // UPDATE - azuriramo one koje postoje i u starim i u novim
        // Dakle, ako rb postoji i u starim i u novim, samo azuriramo
        for (StavkaPorudzbine nova : p.getStavkePorudzbine()) {
            if (mapaStarih.containsKey(nova.getRb())) {
                DBBroker.getInstance().update(nova);
            }
        }

        // INSERT - dodamo one koje su samo u novim
        // Dakle, ako nema rb u starim, samo dodamo nove koje su dodate
        for (StavkaPorudzbine nova : p.getStavkePorudzbine()) {
            if (!mapaStarih.containsKey(nova.getRb())) {
                DBBroker.getInstance().insert(nova);
            }
        }
    }

}
