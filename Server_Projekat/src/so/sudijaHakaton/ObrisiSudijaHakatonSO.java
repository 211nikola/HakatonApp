/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.sudijaHakaton;

import domain.DomainObject;
import domain.SudijaHakaton;
import so.AbstractGenericObject;

/**
 *
 * @author Nikola
 */
public class ObrisiSudijaHakatonSO extends AbstractGenericObject {

    private SudijaHakaton sudijaHakaton;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof SudijaHakaton)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        sudijaHakaton = (SudijaHakaton) databasaBroker.delete((DomainObject) entity);
    }

    public SudijaHakaton getSudijaHakaton() {
        return sudijaHakaton;
    }
}
