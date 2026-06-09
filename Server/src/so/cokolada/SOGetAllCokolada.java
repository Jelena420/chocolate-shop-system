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
public class SOGetAllCokolada extends AbstractSO {

    private ArrayList<Cokolada> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Cokolada)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Cokolada!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> cokolade = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Cokolada>) (ArrayList<?>) cokolade;
    }

    public ArrayList<Cokolada> getLista() {
        return lista;
    }

}
