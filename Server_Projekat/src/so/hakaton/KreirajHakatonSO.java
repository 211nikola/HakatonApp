/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.hakaton;

import domain.DomainObject;
import domain.Hakaton;
import domain.Sudija;
import domain.SudijaHakaton;
import java.util.List;
import so.AbstractGenericObject;

/**
 *
 * @author Nikola
 */
public class KreirajHakatonSO extends AbstractGenericObject {

    private Hakaton hakaton;

    @Override
    protected void validate(Object entity) throws Exception {
//        if (!(entity instanceof Hakaton)) {
//            throw new Exception("Invalid entity parameter!");
//        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        Object[] parametri = (Object[]) entity;
        Hakaton h = (Hakaton) parametri[0];
        List<Sudija> listaSudija = (List<Sudija>) parametri[1];
        hakaton = (Hakaton) databasaBroker.save((DomainObject) h);
        int max = databasaBroker.getMaxID(hakaton);
        hakaton.setHakatonID(databasaBroker.getMaxID(hakaton));
        listaSudija.forEach((sudija) -> {
            databasaBroker.save(new SudijaHakaton(sudija, hakaton, hakaton.getAdmin()));
        });
    }

    public Hakaton gethaHakaton() {
        return hakaton;
    }
}
