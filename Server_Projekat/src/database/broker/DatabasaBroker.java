/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.broker;

import database.connection.ConnectionFactory;
import domain.DomainObject;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikola
 */
public class DatabasaBroker {

    public DomainObject save(DomainObject domainObject) {
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();

            String upit = "INSERT INTO " + domainObject.getTableName()
                    + " (" + domainObject.getAttributeNamesForInsert() + ") VALUES (" + domainObject.getAttributeValuesForInsert() + ")";

            Statement statement = connection.createStatement();
            statement.execute(upit);

            statement.close();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(DatabasaBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return domainObject;
    }

    public int getMaxID(DomainObject domainObject) throws Exception {
        int max = 0;
        try {
            /*SELECT MAX(column_name)
                FROM table_name
                WHERE condition;*/
            Connection connection = ConnectionFactory.getInstance().getConnection();
            String upit = "SELECT MAX(" + domainObject.getKeyName() + ") as " + domainObject.getKeyName() + " FROM " + domainObject.getTableName();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(upit);
            while (resultSet.next()) {
                String keyName = domainObject.getKeyName();
                max = resultSet.getInt(keyName);
            }
            resultSet.close();
            statement.close();

            return max;
        } catch (SQLException | IOException ex) {
            Logger.getLogger(DatabasaBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return max;
    }

    public DomainObject update(DomainObject domainObject) {
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            String upit = "UPDATE " + domainObject.getTableName()
                    + " SET " + domainObject.getUpdateStatement()
                    + " WHERE " + domainObject.getKeyNameAndValues();
            //+domainObject.getKeyName()+" = "+domainObject.getKeyValue();
            Statement statement = connection.createStatement();
            statement.execute(upit);
            statement.close();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(DatabasaBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return domainObject;
    }

    public List<DomainObject> getAll(DomainObject domainObject, String str) throws Exception {
        List<DomainObject> lista = new ArrayList<>();
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            String upit = "SELECT * FROM " + domainObject.getTableName() + domainObject.getConstraints(str);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(upit);
            lista = domainObject.getList(resultSet);
            resultSet.close();
            statement.close();

            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DatabasaBroker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DatabasaBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public DomainObject delete(DomainObject domainObject) {
        try {
            Connection connection = ConnectionFactory.getInstance().getConnection();
            String upit = "DELETE FROM " + domainObject.getTableName()
                    + " WHERE " + domainObject.getKeyNameAndValues();
//                    domainObject.getKeyName()
//                    + " = " + domainObject.getKeyValue() + "";
            Statement statement = connection.createStatement();
            statement.execute(upit);

            statement.close();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(DatabasaBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return domainObject;
    }
}
