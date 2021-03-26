/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Nikola
 */
public interface DomainObject {

    public String getTableName();

    public String getAttributeNamesForInsert();

    public String getAttributeValuesForInsert();

    public List<DomainObject> getList(ResultSet resultSet) throws Exception;

    public String getConstraints(String str);

    public String getKeyName();

    public String getKeyValue();

    public String getUpdateStatement();

    public String getKeyNameAndValues();
}
