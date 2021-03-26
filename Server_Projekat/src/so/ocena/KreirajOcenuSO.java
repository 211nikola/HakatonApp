/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.ocena;

import domain.DomainObject;
import domain.Ocena;
import so.AbstractGenericObject;

/**
 *
 * @author Nikola
 */
public class KreirajOcenuSO extends AbstractGenericObject {

    private Ocena ocena;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Ocena)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        ocena = (Ocena) databasaBroker.save((DomainObject) entity);
    }

    public Ocena getOcena() {
        return ocena;
    }
}
