package com.personnel.pojo;

import java.util.Objects;

public class Personnel {
    private Integer id;
    private Integer personId;
    private String personName;
    private Integer changeCode;
    private String changeName;
    private String description;

    public Personnel() {
    }

    public Personnel(Integer id, Integer personId, String personName, Integer changeCode, String changeName, String description) {
        this.id = id;
        this.personId = personId;
        this.personName = personName;
        this.changeCode = changeCode;
        this.changeName = changeName;
        this.description = description;
    }

    public Personnel(Integer id, String personName, String changeName, String description) {
        this.id = id;
        this.personName = personName;
        this.changeName = changeName;
        this.description = description;
    }

    public Personnel(Integer id, Integer personId, String personName,String changeName, String description) {
        this.id = id;
        this.personId = personId;
        this.personName = personName;
        this.changeName = changeName;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getChangeCode() {
        return changeCode;
    }

    public void setChangeCode(Integer changeCode) {
        this.changeCode = changeCode;
    }

    public String getChangeName() {
        return changeName;
    }

    public void setChangeName(String changeName) {
        this.changeName = changeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Personnel{" +
                "id=" + id +
                ", personId=" + personId +
                ", personName='" + personName + '\'' +
                ", changeCode=" + changeCode +
                ", changeName='" + changeName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personnel personnel = (Personnel) o;
        return Objects.equals(getId(), personnel.getId()) && Objects.equals(getPersonId(), personnel.getPersonId()) && Objects.equals(getPersonName(), personnel.getPersonName()) && Objects.equals(getChangeCode(), personnel.getChangeCode()) && Objects.equals(getChangeName(), personnel.getChangeName()) && Objects.equals(getDescription(), personnel.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPersonId(), getPersonName(), getChangeCode(), getChangeName(), getDescription());
    }
}
