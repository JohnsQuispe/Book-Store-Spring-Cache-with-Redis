package br.com.johnsquispe.springcacheredis.api;

public class LoginOutput {

    private final String jwt;

    public LoginOutput(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}
