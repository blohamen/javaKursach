package server.database;

import client.model.*;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler extends Config {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort +"/" + dbName + "?" + "useUnicode = true & useJDBCCompliantTimezoneShift = true & useLegacyDatetimeCode = false & serverTimezone = UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public User insertEmployee(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Constants.USER_TABLE + "(" +
                Constants.USER_LOGIN + "," + Constants.USER_PASSWORD + "," +
                Constants.USER_ROLE + "," + Constants.USER_TITLE + ")" + "VALUES(?,?,?,?)";

        User userInDb = authorizate(user.getLogin(), user.getPassword());
        if (userInDb == null) {
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);
                prSt.setString(1, user.getLogin());
                prSt.setString(2, user.getPassword());
                prSt.setInt(3, user.getRole());
                prSt.setString(4, user.getTitle());
                prSt.executeUpdate();
                return user;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public User authorizate(String login, String password) throws SQLException, ClassNotFoundException {
        ResultSet resultSet;
        String select =  "SELECT * FROM " + Constants.USER_TABLE;

        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        resultSet = prSt.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getString("login").equals(login)
                && resultSet.getString("password").equals(password)){
             return new User(
                     resultSet.getString("login"),
                     resultSet.getString("password"),
                     resultSet.getInt("role"),
                     resultSet.getString("title")
             );
            }
        }
        return null;
    }

    public User[] getAllUsers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet;
        String select =  "SELECT * FROM " + Constants.USER_TABLE;

        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        resultSet = prSt.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(new User(
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getInt("role"),
                    resultSet.getString("title")
            ));
        }
        return users.toArray(new User[users.size()]);
    }

    public ArrayList<DoctorRecord> getAllRecords(String doctorTitle) throws SQLException, ClassNotFoundException {
        ResultSet resultSet;
        ArrayList<DoctorRecord> records = new ArrayList<DoctorRecord>();
        String select = "SELECT * FROM " + Constants.DOCTORRECORDS_TABLE;
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        resultSet = prSt.executeQuery();
        while (resultSet.next()) {
            if (resultSet.getString("doctorTitle").equals(doctorTitle)) {
                records.add(new DoctorRecord(
                        resultSet.getString("fio"),
                        resultSet.getString("date"),
                        resultSet.getString("time"),
                        resultSet.getString("doctorTitle")
                ));
            }
        }
        return records;
    }

    public Patient getPatient(String firstName, String secondName,String thirdName, String address) throws SQLException, ClassNotFoundException {
        ResultSet allPatientsAnalysis;
        ResultSet allPatientsDiagnoses;
        ResultSet allPatients;

        String getAllPatientsSelect = "SELECT * FROM " + Constants.PATIENT_RECORDS_TABLE + " WHERE firstName = ? "
                +  " AND secondName = ? AND thirdName = ? AND address = ?";
        String getAllPatientsDiagnosiesSelect = "SELECT * FROM " + Constants.DIAGNOSIES_RECORDS_TABLE
                + " WHERE Pacient_numCard = ?";
        String getAllPatientsAnalysisSelect = "SELECT * FROM " + Constants.ANALYSIES_RECORDS_TABLE
                + " WHERE Diagnosies_idDiagnosies = ?";

        PreparedStatement prSt = getDbConnection().prepareStatement(getAllPatientsSelect);
        prSt.setString(1, firstName);
        prSt.setString(2, secondName);
        prSt.setString(3, thirdName);
        prSt.setString(4, address);
        allPatients = prSt.executeQuery();
        while (allPatients.next()) {
            ArrayList<PatientDiagnosies> patientDiagnosies = new ArrayList<PatientDiagnosies>();
            ArrayList<PatientAnalysies> patientAnalysies = new ArrayList<PatientAnalysies>();
            prSt = getDbConnection().prepareStatement(getAllPatientsDiagnosiesSelect);
            prSt.setString(1, allPatients.getString("numCard"));
            allPatientsDiagnoses = prSt.executeQuery();
            while (allPatientsDiagnoses.next()) {
                prSt = getDbConnection().prepareStatement(getAllPatientsAnalysisSelect);
                prSt.setString(1, allPatientsDiagnoses.getString("idDiagnosies"));
                allPatientsAnalysis = prSt.executeQuery();
                while (allPatientsAnalysis.next()) {
                    patientAnalysies.add(new PatientAnalysies(
                            allPatientsAnalysis.getString("name"),
                            allPatientsAnalysis.getString("result")
                    ));
                }
                patientDiagnosies.add(new PatientDiagnosies(
                        allPatientsDiagnoses.getString("nameDiagnosies"),
                        allPatientsDiagnoses.getString("description"),
                        allPatientsDiagnoses.getString("medicates"),
                        patientAnalysies.toArray(new PatientAnalysies[patientAnalysies.size()])
                ));
            }
            return new Patient(
                    allPatients.getString("numCard"),
                    allPatients.getString("firstName"),
                    allPatients.getString("secondName"),
                    allPatients.getString("thirdName"),
                    allPatients.getString("fullDate"),
                    allPatients.getString("fullAge"),
                    allPatients.getString("address"),
                    patientDiagnosies.toArray(new PatientDiagnosies[patientDiagnosies.size()])
            );
        }
        return null;
    }
    public void updateUserInfo(Patient patient) throws SQLException, ClassNotFoundException {
        String update = "UPDATE " + Constants.PATIENT_RECORDS_TABLE
                + " SET firstName = ?, secondName = ?, thirdName = ?, fullAge = ?, fullDate = ?, address = ? "
                + " WHERE numCard = ?";

        PreparedStatement prSt = getDbConnection().prepareStatement(update);
        prSt.setString(1, patient.getFirstName());
        prSt.setString(2, patient.getSurName());
        prSt.setString(3, patient.getThirdName());
        prSt.setString(4, patient.getFullAge());
        prSt.setString(5, patient.getDate());
        prSt.setString(6, patient.getAddress());
        prSt.setString(7, patient.getNumCard());
        prSt.executeUpdate();
    }

    public boolean addCoupon(DoctorRecord record) {
        String insert = "INSERT INTO " + Constants.DOCTORRECORDS_TABLE + "(fio, date, time, doctorTitle )" + "VALUES(?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, record.getFio());
            prSt.setString(2, record.getDate());
            prSt.setString(3, record.getTime());
            prSt.setString(4, record.getDoctorTitle());
            prSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean removeDoctor(String doctorTitle) {
        String delete = "DELETE FROM " + Constants.USER_TABLE + " WHERE title = ?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.setString(1, doctorTitle);
            prSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Analysis[] getAnalysies(String numCard) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM tests WHERE idNumcard = ?";

        ResultSet resultSet;
        ArrayList<Analysis> records = new ArrayList<Analysis>();
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, numCard);
        resultSet = prSt.executeQuery();
        while (resultSet.next()) {
                records.add(new Analysis (
                        resultSet.getString("doctorTitle"),
                        resultSet.getString("type"),
                        resultSet.getString("date"),
                        resultSet.getString("time")
                ));
        }
        return records.toArray(new Analysis[records.size()]);
    }

    public Recipe[] getRecipes(String numCard) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM recipes WHERE idNumcard = ?";

        ResultSet resultSet;
        ArrayList<Recipe> records = new ArrayList<Recipe>();
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, numCard);
        resultSet = prSt.executeQuery();
        while (resultSet.next()) {
                records.add(new Recipe (
                        resultSet.getString("doctorTitle"),
                        resultSet.getString("medicates"),
                        resultSet.getString("dose")
                ));
        }
        return records.toArray(new Recipe[records.size()]);
    }

    public SickDay[] getSickDays(String numCard) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM sickday WHERE idNumcard = ?";

        ResultSet resultSet;
        ArrayList<SickDay> records = new ArrayList<SickDay>();
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, numCard);
        resultSet = prSt.executeQuery();
        while (resultSet.next()) {
            records.add(new SickDay (
                    resultSet.getString("doctorTitle"),
                    resultSet.getString("dateStart"),
                    resultSet.getString("dateEnd"),
                    resultSet.getString("diagnosies")
            ));
        }
        return records.toArray(new SickDay[records.size()]);
    }

    public Coupon[] getCoupons(String numCard) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM coupons WHERE idNumcard = ?";

        ResultSet resultSet;
        ArrayList<Coupon> records = new ArrayList<Coupon>();
        PreparedStatement prSt = getDbConnection().prepareStatement(select);
        prSt.setString(1, numCard);
        resultSet = prSt.executeQuery();
        while (resultSet.next()) {
            records.add(new Coupon (
                    resultSet.getString("doctorTitle"),
                    resultSet.getString("date"),
                    resultSet.getString("time")
            ));
        }
        return records.toArray(new Coupon[records.size()]);
    }

//    public boolean addCoupon(DoctorRecord record) {
//        String insert = "INSERT INTO " + Constants.DOCTORRECORDS_TABLE + "(fio, date, time, doctorTitle )" + "VALUES(?,?,?,?)";
//        try {
//            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
//            prSt.setString(1, record.getFio());
//            prSt.setString(2, record.getDate());
//            prSt.setString(3, record.getTime());
//            prSt.setString(4, record.getDoctorTitle());
//            prSt.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

}
