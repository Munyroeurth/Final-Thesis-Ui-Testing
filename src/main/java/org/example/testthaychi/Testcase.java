package org.example.testthaychi;

public  class Testcase {
    String username;
    String password;
    String login;
    String expectedResult;

    public Testcase(String username, String password, String login, String expectedResult) {
        this.username = username;
        this.password = password;
        this.login = login;
        this.expectedResult = expectedResult;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }
}
