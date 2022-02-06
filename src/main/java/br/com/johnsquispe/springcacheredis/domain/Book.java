package br.com.johnsquispe.springcacheredis.domain;

import br.com.johnsquispe.springcacheredis.DatabaseConfig;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = Book.TableInfo.NAME)
public class Book {

    @Id
    @GenericGenerator(name = TableInfo.SEQUENCE_GENERATOR,
            parameters = @org.hibernate.annotations.Parameter(name = DatabaseConfig.SEQUENCE_NAME_PARAM_VALUE, value = TableInfo.SEQUENCE_NAME),
            strategy = DatabaseConfig.GENERIC_GENERATOR_STRATEGY_PARAM_VALUE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TableInfo.SEQUENCE_GENERATOR)
    private Long id;

    @Column(name = ColumnsInfo.TITLE)
    private String title;

    @Column(name = ColumnsInfo.AUTHORS)
    private String authors;

    @Column(name = ColumnsInfo.EDITION)
    private Integer edition;

    @Column(name = ColumnsInfo.CREATION_DATE)
    private LocalDateTime creationDate;

    @JoinColumn(name = ColumnsInfo.CREATION_USER_ID, referencedColumnName = User.ColumnsInfo.ID,
    foreignKey = @ForeignKey(name = FK.BOOK_CREATION_USER_ID_FK))
    @ManyToOne(fetch = FetchType.LAZY)
    private User creationUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public User getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(User creationUser) {
        this.creationUser = creationUser;
    }

    public static class TableInfo {

        public static final String NAME = "BOOK";
        public static final String SEQUENCE_GENERATOR = NAME + "_SEQUENCE_GENERATOR";
        public static final String SEQUENCE_NAME = NAME + "_ID_SEQUENCE";

    }

    public static class ColumnsInfo {
        public static final String TITLE = "TITLE";
        public static final String AUTHORS = "AUTHORS";
        public static final String EDITION = "EDITION";
        public static final String CREATION_DATE = "CREATION_DATE";
        public static final String CREATION_USER_ID = "CREATION_USER_ID";
    }

    public static class FK {
        public static final String BOOK_CREATION_USER_ID_FK = "BOOK_CREATION_USER_ID_FK";
    }

}
