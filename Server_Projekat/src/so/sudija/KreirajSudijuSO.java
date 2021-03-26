/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.sudija;

import domain.DomainObject;
import domain.Sudija;
import so.AbstractGenericObject;

/**
 *
 * @author Nikola
 */
public class KreirajSudijuSO extends AbstractGenericObject {

    private Sudija sudija;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Sudija)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        sudija = (Sudija) databasaBroker.save((DomainObject) entity);
    }

    public Sudija getSudija() {
        return sudija;
    }

}
