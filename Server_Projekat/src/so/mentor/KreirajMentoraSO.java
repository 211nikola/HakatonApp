/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.mentor;

import domain.DomainObject;
import domain.Mentor;
import so.AbstractGenericObject;

/**
 *
 * @author Nikola
 */
public class KreirajMentoraSO extends AbstractGenericObject {

    private Mentor mentor;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Mentor)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        mentor = (Mentor) databasaBroker.save((DomainObject) entity);
    }

    public Mentor geMentor() {
        return mentor;
    }
}
