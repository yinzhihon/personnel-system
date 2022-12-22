package com.personnel.pojo;

public class Department {
    private Integer id;
    private String name;
    private Integer managerId;
    private String managerName;
    private String intro;

    public Department() {
    }

    public Department(Integer id, String name, Integer managerId, String managerName, String intro) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.managerName = managerName;
        this.intro = intro;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", managerId=" + managerId +
                ", managerName='" + managerName + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
