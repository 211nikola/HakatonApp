/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.clan;

import domain.Clan;
import domain.DomainObject;
import so.AbstractGenericObject;

/**
 *
 * @author Nikola
 */
public class ObrisiClanaSO extends AbstractGenericObject {

    private Clan clan;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Clan)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        clan = (Clan) databasaBroker.delete((DomainObject) entity);
    }

    public Clan getClan() {
        return clan;
    }
}
