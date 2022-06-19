package br.com.johnsquispe.springcacheredis.domain;

import br.com.johnsquispe.springcacheredis.DatabaseConfig;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = User.TableInfo.NAME)
public class User implements Serializable {

    @Id
    @GenericGenerator(name = User.TableInfo.SEQUENCE_GENERATOR,
            parameters = @org.hibernate.annotations.Parameter(name = DatabaseConfig.SEQUENCE_NAME_PARAM_VALUE, value = User.TableInfo.SEQUENCE_NAME),
            strategy = DatabaseConfig.GENERIC_GENERATOR_STRATEGY_PARAM_VALUE)
    @GeneratedValue(generator = TableInfo.SEQUENCE_GENERATOR, strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = ColumnsInfo.NOME)
    private String name;

    @Column(name = ColumnsInfo.LOGIN)
    private String login;

    @Column(name = ColumnsInfo.SENHA)
    private String password;

    @Column(name = ColumnsInfo.CREATION_DATE)
    private LocalDateTime creationDate;

    public User () {

    }

    public User(String name, String login, String password) {
       this.name = name;
       this.login = login;
       this.password = password;
       this.creationDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
