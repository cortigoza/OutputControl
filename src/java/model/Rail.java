/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author carlo
 */
public class Rail {

    private String rail;
    private String student;
    private String section;

    public Rail(String r1, String r2, String r3) {
        rail = r1;
        student = r2;
        section = r3;
    }

    public String getRail() {
        return rail;
    }

    public void setRail(String rail) {
        this.rail = rail;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String validatorColor(String data) {
        if ("primaria".equals(data)) {
            return "danger";
        } else if ("pre-escolar".equals(data)) {
            return "warning";
        }
        return "success";
    }

}
