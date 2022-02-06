package br.com.johnsquispe.springcacheredis.domain;

import br.com.johnsquispe.springcacheredis.DatabaseConfig;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = User.TableInfo.NAME)
public class User {

    @Id
    @GenericGenerator(name = User.TableInfo.SEQUENCE_GENERATOR,
            parameters = @org.hibernate.annotations.Parameter(name = DatabaseConfig.SEQUENCE_NAME_PARAM_VALUE, value = User.TableInfo.SEQUENCE_NAME),
            strategy = DatabaseConfig.GENERIC_GENERATOR_STRATEGY_PARAM_VALUE)
    private Long id;

    @Column(name = ColumnsInfo.NOME)
    private String nome;

    @Column(name = ColumnsInfo.LOGIN)
    private String login;

    @Column(name = ColumnsInfo.SENHA)
    private String senha;

    @Column(name = ColumnsInfo.CREATION_DATE)
    private LocalDateTime creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public static class TableInfo {
        public static final String NAME = "USER";
        public static final String SEQUENCE_GENERATOR = NAME + "_SEQUENCE_GENERATOR";
        public static final String SEQUENCE_NAME = NAME + "_ID_SEQUENCE";
    }

    public static class ColumnsInfo {
        public static final String ID = "ID";
        public static final String NOME = "NOME";
        public static final String LOGIN = "LOGIN";
        public static final String SENHA = "SENHA";
        public static final String CREATION_DATE = "CREATION_DATE";
    }
}
