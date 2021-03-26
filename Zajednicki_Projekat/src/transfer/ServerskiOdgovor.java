/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Nikola
 */
public class ServerskiOdgovor implements Serializable {

    private Exception exception;
    private Object odgovor;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Exception exception, Object odgovor) {
        this.exception = exception;
        this.odgovor = odgovor;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

}
