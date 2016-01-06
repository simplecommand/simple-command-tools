package de.mwolff.examples.loginvalidator;

import java.util.ArrayList;
import java.util.List;

import de.mwolff.commons.command.iface.Context;

public class LoginParameter implements Context {

    private String loginName;
    private String password;
    private int length;

    private final List<String> errors = new ArrayList<String>();

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return this.length;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

}
