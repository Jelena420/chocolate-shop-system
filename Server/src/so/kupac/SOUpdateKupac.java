/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kupac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Kupac;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Jelena
 */
public class SOUpdateKupac extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Kupac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kupac!");
        }

        Kupac k = (Kupac) ado;

        if (!k.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new Exception("Email nije u ispravnom formatu!");
        }

        if (!k.getTelefon().matches("^[0-9+\\- ]{6,20}$")) {
            throw new Exception("Telefon nije u ispravnom formatu!");
        }

        ArrayList<Kupac> kupci = (ArrayList<Kupac>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Kupac kupac : kupci) {
            if (kupac.getKupacID() != k.getKupacID()) {
                if (kupac.getEmail().equals(k.getEmail())) {
                    throw new Exception("Kupac sa tim emailom vec postoji!");
                }
                if (kupac.getTelefon().equals(k.getTelefon())) {
                    throw new Exception("Kupac sa tim telefonom vec postoji!");
                }
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
