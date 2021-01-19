package fr.training.spring.library.infrastructure.http.dto;

public class AuthorInfo {
    private String name;
    private String personal_name;
    private DateInfo last_modified;
    private String key;
    private TypeInfo type;
    private int id;
    private int revision;

    public String getName() {
        return name;
    }

    public String getPersonal_name() {
        return personal_name;
    }

    public DateInfo getLast_modified() {
        return last_modified;
    }

    public String getKey() {
        return key;
    }

    public TypeInfo getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public int getRevision() {
        return revision;
    }
}
