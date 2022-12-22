package com.personnel.pojo;

import java.time.LocalDate;
import java.util.Objects;

public class Person {
    private Integer id;
    private String passwd;
    private String authority;
    private String name;
    private String sex;
    private LocalDate birthday;
    private Integer departmentId;
    private String departmentName;
    private Integer jobId;
    private String jobName;
    private Integer eduLevelId;
    private String eduLevelName;
    private String specialty;
    private String address;
    private String tel;
    private String email;
    private String state;
    private String remake;

    public Person() {
    }

    public Person(Integer id, String name, String sex, String departmentName, String jobName, String state,String remake) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.departmentName = departmentName;
        this.jobName = jobName;
        this.state =state;
        this.remake = remake;
    }

    public Person(String passwd, String authority, String name, String sex, LocalDate birthday, String departmentName, String jobName, String eduLevelName, String specialty, String address, String tel, String email, String state, String remake) {
        this.passwd = passwd;
        this.authority = authority;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.departmentName = departmentName;
        this.jobName = jobName;
        this.eduLevelName = eduLevelName;
        this.specialty = specialty;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.state = state;
        this.remake = remake;
    }

    public Person(Integer id, String passwd, String authority, String name, String sex,
                  LocalDate birthday, Integer departmentId, Integer jobId, Integer eduLevelId, String specialty,
                  String address, String tel, String email, String state, String remake) {
        this.id = id;
        this.passwd = passwd;
        this.authority = authority;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.departmentId = departmentId;
        this.jobId = jobId;
        this.eduLevelId = eduLevelId;
        this.specialty = specialty;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.state = state;
        this.remake = remake;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getEduLevelId() {
        return eduLevelId;
    }

    public void setEduLevelId(Integer eduLevelId) {
        this.eduLevelId = eduLevelId;
    }

    public String getEduLevelName() {
        return eduLevelName;
    }

    public void setEduLevelName(String eduLevelName) {
        this.eduLevelName = eduLevelName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getPasswd(), person.getPasswd()) && Objects.equals(getAuthority(), person.getAuthority()) && Objects.equals(getName(), person.getName()) && Objects.equals(getSex(), person.getSex()) && Objects.equals(getBirthday(), person.getBirthday()) && Objects.equals(getDepartmentId(), person.getDepartmentId()) && Objects.equals(getDepartmentName(), person.getDepartmentName()) && Objects.equals(getJobId(), person.getJobId()) && Objects.equals(getJobName(), person.getJobName()) && Objects.equals(getEduLevelId(), person.getEduLevelId()) && Objects.equals(getEduLevelName(), person.getEduLevelName()) && Objects.equals(getSpecialty(), person.getSpecialty()) && Objects.equals(getAddress(), person.getAddress()) && Objects.equals(getTel(), person.getTel()) && Objects.equals(getEmail(), person.getEmail()) && Objects.equals(getState(), person.getState()) && Objects.equals(getRemake(), person.getRemake());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPasswd(), getAuthority(), getName(), getSex(), getBirthday(), getDepartmentId(), getDepartmentName(), getJobId(), getJobName(), getEduLevelId(), getEduLevelName(), getSpecialty(), getAddress(), getTel(), getEmail(), getState(), getRemake());
    }
}
