/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.cokolada;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Cokolada;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Jelena
 */
public class SOAddCokolada extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Cokolada)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Cokolada!");
        }

        Cokolada c = (Cokolada) ado;

        if (c.getCena() < 50 || c.getCena() > 50000) {
            throw new Exception("Cena cokolade mora biti izmedju 50din i 50000din!");
        }

        ArrayList<Cokolada> cokolade = (ArrayList<Cokolada>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Cokolada cokolada : cokolade) {
            if (cokolada.getNaziv().equals(c.getNaziv())) {
                throw new Exception("Cokolada sa tim nazivom vec postoji!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }

}
