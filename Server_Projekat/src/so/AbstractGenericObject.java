/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import database.broker.DatabasaBroker;
import database.connection.ConnectionFactory;

/**
 *
 * @author Nikola
 */
public abstract class AbstractGenericObject {

    protected DatabasaBroker databasaBroker;

    public AbstractGenericObject() {
        this.databasaBroker = new DatabasaBroker();
    }

    public final void templateExecute(Object entity, String keyword) throws Exception {
        try {
            validate(entity);
            startTransaction();
            execute(entity, keyword);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        }
    }

    protected abstract void validate(Object obj) throws Exception;

    protected abstract void execute(Object obj, String keyword) throws Exception;

    private void startTransaction() throws Exception {
        ConnectionFactory.getInstance().getConnection().setAutoCommit(false);
    }

    private void commitTransaction() throws Exception {
        ConnectionFactory.getInstance().commit();
    }

    private void rollbackTransaction() throws Exception {
        ConnectionFactory.getInstance().rollback();
    }
}
