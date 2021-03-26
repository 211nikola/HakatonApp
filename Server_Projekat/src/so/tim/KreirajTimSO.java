/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.tim;

import domain.Clan;
import domain.DomainObject;
import domain.Tim;
import java.util.List;
import so.AbstractGenericObject;

/**
 *
 * @author Nikola
 */
public class KreirajTimSO extends AbstractGenericObject {

    private Tim tim;

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Tim)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        Tim timm = (Tim) entity;
        databasaBroker.save((DomainObject) timm);
        int max = databasaBroker.getMaxID(timm);
        timm.setTimID(max);
        List<Clan> listaClanova = timm.getClanovi();
        listaClanova.forEach((clan) -> {
            clan.setTim(timm);
            databasaBroker.save(clan);
        });

    }

    public Tim getTim() {
        return tim;
    }
}
