/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.sudija;

import domain.DomainObject;
import domain.Sudija;
import java.util.List;
import so.AbstractGenericObject;

/**
 *
 * @author Nikola
 */
public class VratiSudijeSO extends AbstractGenericObject {

    private List<DomainObject> lista;

    @Override
    protected void validate(Object obj) throws Exception {
        if (!(obj instanceof Sudija)) {
            throw new Exception("Invalid entity parameter!");
        }
    }

    @Override
    protected void execute(Object obj, String keyword) throws Exception {
        lista = databasaBroker.getAll((Sudija) obj, keyword);
    }

    public List<DomainObject> getLista() {
        return lista;
    }

}
