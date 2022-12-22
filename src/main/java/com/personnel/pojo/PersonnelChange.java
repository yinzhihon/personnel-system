package com.personnel.pojo;

public class PersonnelChange {
    Integer code;
    String description;

    public PersonnelChange() {
    }

    public PersonnelChange(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PersonnelChange{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
