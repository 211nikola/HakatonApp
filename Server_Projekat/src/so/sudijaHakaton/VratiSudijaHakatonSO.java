/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.sudijaHakaton;

import domain.DomainObject;
import domain.SudijaHakaton;
import java.util.List;
import so.AbstractGenericObject;

/**
 *
 * @author Nikola
 */
public class VratiSudijaHakatonSO extends AbstractGenericObject {

    private List<DomainObject> lista;

    @Override
    protected void validate(Object obj) throws Exception {
        if (!(obj instanceof SudijaHakaton)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    protected void execute(Object obj, String keyword) throws Exception {
        lista = databasaBroker.getAll((SudijaHakaton) obj, keyword);
    }

    public List<DomainObject> getLista() {
        return lista;
    }
}
