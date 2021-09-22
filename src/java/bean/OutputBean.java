package bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import model.Students;
import model.connection;
import model.User;
import model.Rail;

/**
 *
 * @author Carlos Ortigoza
 */
@ManagedBean(name = "outputbean", eager = true)
@SessionScoped
public class OutputBean implements Serializable {

    private ArrayList<Students> listStudents;

    private ArrayList<Rail> listRail;

    public ArrayList<Students> getListStudents() {
        return listStudents;
    }

    public void setListStudents(ArrayList<Students> listStudents) {
        this.listStudents = listStudents;
    }
    private boolean showAlert;
    private String identification;
    private String phone;
    private String mail;
    private String password;
    private String username;
    private int idLogin;

    private String nameStudent;
    private String sectionStudent;

    private boolean hideArrive = true;
    private boolean showReady = false;

    private String rail;

    public String getRail() {
        return rail;
    }

    public ArrayList<Rail> getListRail() {
        return listRail;
    }

    public void setListRail(ArrayList<Rail> listRail) {
        this.listRail = listRail;
    }

    public void setRail(String rail) {
        this.rail = rail;
    }

    public boolean isHideArrive() {
        return hideArrive;
    }

    public void setHideArrive(boolean hideArrive) {
        this.hideArrive = hideArrive;
    }

    public boolean isShowReady() {
        return showReady;
    }

    public void setShowReady(boolean showReady) {
        this.showReady = showReady;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getSectionStudent() {
        return sectionStudent;
    }

    public void setSectionStudent(String sectionStudent) {
        this.sectionStudent = sectionStudent;
    }

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    private String message = "Hola";

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isShowAlert() {
        return showAlert;
    }

    public void setShowAlert(boolean showAlert) {
        this.showAlert = showAlert;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Creates a new instance of OutputBean
     */
    public OutputBean() {

    }

    /*
    *
    *method to enter the panel
    **/
    public String login() throws SQLException {
        if (!"".equals(identification) && !"".equals(password)) {
            connection.getConnection();
            String sql = "SELECT identification, id FROM users "
                    + "WHERE identification = " + identification + " "
                    + " AND pass= '" + password + "'";

            ResultSet rs = connection.executeQuery(sql);
            while (rs.next()) {
                setIdLogin(rs.getInt("id"));
                getStudentsbyParent();
                return "parentPanel";
            }
        }

        return "index";
    }

    public String register() throws SQLException, IOException {
        connection.getConnection();
        String sql = "INSERT INTO users (name, identification, phone, mail, pass) "
                + "VALUES('" + username + "', " + identification + ", '" + phone + "', '" + mail + "', '" + password + "')";
        connection.updateQuery(sql);
        return "index";
    }

    public ArrayList<Students> getStudentsbyParent() throws SQLException {
        //aqui voy a traer todos los estudiantes del usuario logeado
        //

        connection.getConnection();
        String sql = "SELECT s.name, s.section FROM users u "
                + "LEFT JOIN output o "
                + "ON u.id=o.id_user "
                + "LEFT JOIN students s "
                + "ON o.id_student=s.id "
                + "WHERE u.identification = " + identification + " ";

        ResultSet rs = connection.executeQuery(sql);
        listStudents = new ArrayList<>();
        while (rs.next()) {
            listStudents.add(new Students(rs.getString("name"), rs.getString("section")));
        }
        return (listStudents.isEmpty()) ? null : listStudents;
    }

    public void InsertStudent() throws SQLException {
        connection.getConnection();
        String sql = "INSERT INTO students (name, section) "
                + "VALUES('" + nameStudent + "', '" + sectionStudent + "')";
        int idStudent = connection.updateQuery(sql);

        String sql2 = "INSERT INTO output (id_user, id_student) "
                + "VALUES('" + idLogin + "', '" + idStudent + "')";

        connection.updateQuery(sql2);

        getStudentsbyParent();
    }

    public void DeleteStudent(Students student) throws SQLException {
        connection.getConnection();
        String sql = "DELETE FROM students "
                + "WHERE  name LIKE '" + student.getName() + "'"
                + " AND section ='" + student.getSection() + "'";

        connection.updateQuery(sql);
        getStudentsbyParent();
    }

    public void listControl() throws SQLException {
        //aqui traer todos los estudiantes que el padre habilito  y ordenarlos segun el color en la tabla
        connection.getConnection();
        String sql = "SELECT "
                + "    r.rail, s.name as student, s.section, u.name as parent  "
                + "FROM "
                + "    output o "
                + "LEFT JOIN users u ON "
                + "    u.id = o.id_user "
                + "LEFT JOIN students s ON "
                + "    s.id = o.id_student "
                + "LEFT JOIN rails r ON "
                + "    r.id_user = u.id "
                + "WHERE r.rail != '' OR r.id_user != ''";

        ResultSet rs = connection.executeQuery(sql);

        listRail = new ArrayList<>();
        while (rs.next()) {
            listRail.add(new Rail(rs.getString("rail"), rs.getString("student"), rs.getString("section")));
        }
    }

    public boolean validatorRail(int column, String data) {
        return (column == Integer.parseInt(data));
    }

    public void ReadyGo() throws SQLException {
        //dentro del panel de usuario si ya recogio se debe desactivar ese o esos estudiantes ponerlo en 0
        connection.getConnection();
        String sql = "DELETE FROM rails "
                + "WHERE  id_user ='" + idLogin + "'";

        connection.updateQuery(sql);
    }

    public void arrive() throws SQLException {
        //aqui se accionara y se mostrara los estudiantes que tiene ese usuario relacionados para activarlos en el carril especifico
        hideArrive = false;
        showReady = true;
        connection.getConnection();
        String sql = "INSERT INTO rails (rail, id_user) "
                + "VALUES('" + rail + "', '" + idLogin + "')";

        connection.updateQuery(sql);
    }

    public String closeSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }


    /*
    *
    *method redirect default from ui
    *
     */
    public String processPage(String path) {
        return path;
    }

}
