/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tim;

import domain.DomainObject;
import domain.Tim;
import so.AbstractGenericObject;

/**
 *
 * @author Nikola
 */
public class ObrisiTimSO extends AbstractGenericObject {

    private Tim tim;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Tim)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        Tim tim = (Tim) databasaBroker.delete((DomainObject) entity);
    }

    public Tim geTim() {
        return tim;
    }
}
