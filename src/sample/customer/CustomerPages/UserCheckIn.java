package sample.customer.CustomerPages;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static sample.customer.Login.UserLogin.currentCustomerNID;
public class UserCheckIn implements Initializable {

    public Label roomCapacityField;
    public Label roomTypeField;
    public Label roomPriceField;
    public AnchorPane userCheckInPane;
    public JFXComboBox userRoomChoicebox;
    public StackPane rootPane;
    @FXML
    private Label UserNameField;
    @FXML
    private Label UserNIDField;
    @FXML
    private Label UserEmailField;
    @FXML
    private Label UserPhoneField;
    @FXML
    private Label UserAddressField;
    @FXML
    public DatePicker UserCheckIndate;
    @FXML
    public Label roomStatusField;
    @FXML
    public Label roomNoteField;

    public int maxPeople = 0;



    @FXML
    void UserCheckInSubmitBtn(ActionEvent event) throws SQLException {
        String name = UserNameField.getText();
        String NID = UserNIDField.getText();
        String Email = UserEmailField.getText();
        String Phone = UserPhoneField.getText();
        String Address = UserAddressField.getText();
        String RoomNo = userRoomChoicebox.getValue()+"";
        String CheckInDate = UserCheckIndate.getValue()+"";
        String roomStatus = roomStatusField.getText()+"";
//        String roomNote = roomNoteField.getText()+"";
        String roomType = roomTypeField.getText()+"";
        String roomPrice = roomPriceField.getText()+"";
        Connection connection = DBConnection.getConnections();

        if (roomType.equals("") || roomPrice.equals("") || roomStatus.equals("") || CheckInDate.equals("null")) {
//            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
            CommonTask.showJFXAlert(rootPane, userCheckInPane, "warning", "Warning!", "Field Can't be Empty!", JFXDialog.DialogTransition.CENTER);
        } else {
            String sql = "INSERT INTO CHECKINOUTINFO (NAME, EMAIL, PHONE, ADDRESS, NID, ROOMNO, ROOMTYPE, ROOMSTATUS, CHECKEDIN, PRICEDAY) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, Email);
            preparedStatement.setString(3, Phone);
            preparedStatement.setString(4, Address);
            preparedStatement.setString(5, NID);
            preparedStatement.setString(6, RoomNo);
            preparedStatement.setString(7, roomType);
            preparedStatement.setString(8, roomStatus);
            preparedStatement.setString(9, CheckInDate);
            preparedStatement.setString(10, roomPrice);

            try{
                preparedStatement.execute();

                if(!connection.isClosed()){
                    String sql1 = "UPDATE ROOMINFO SET STATUS = STATUS + 1 WHERE ROOMNO = ?";
                    PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                    preparedStatement1.setString(1, RoomNo);
                    preparedStatement1.execute();
                }

                if (!connection.isClosed()) {
                    String sql2 = "SELECT STATUS FROM ROOMINFO WHERE ROOMNO = ?";
                    PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                    preparedStatement2.setString(1, RoomNo);
                    ResultSet resultSet = preparedStatement2.executeQuery();

                    if (resultSet.next()) {
                        int currentStatus = resultSet.getInt("STATUS");

                        if (currentStatus == maxPeople) {
                            String sql3 = "UPDATE ROOMINFO SET NOTE = 'Full' WHERE ROOMNO = ?";
                            PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
                            preparedStatement3.setString(1, RoomNo);
                            preparedStatement3.execute();
                        }
                    }
                }
                CommonTask.showJFXAlert(rootPane, userCheckInPane, "information", "Successful!", "Check In Successful!", JFXDialog.DialogTransition.CENTER);
            } catch (SQLException e){
                e.printStackTrace();
                CommonTask.showJFXAlert(rootPane, userCheckInPane, "information", "Error!", "SQL Exception Happened!", JFXDialog.DialogTransition.CENTER);
            } finally {
                DBConnection.closeConnections();
            }
        }
        updateChoiceBox();
        roomTypeField.setText(null);
        roomPriceField.setText(null);
        roomStatusField.setText(null);
        roomNoteField.setText(null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        findMaxPeople();
        updateChoiceBox();
        setAndShowCustomerInfo();
        userRoomChoicebox.setOnAction(this::setRoomInfo);
    }

    private void findMaxPeople() {
        Connection connection = DBConnection.getConnections();
        try{
            if(!connection.isClosed()) {
                String sql = "SELECT * FROM PARAMETERS WHERE PARAMETERNAME = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, "MAXCUSTOMER");
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    maxPeople = resultSet.getInt("PARAMETERVALUE");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }

    public void setRoomInfo(Event event) {
        String roomNo = userRoomChoicebox.getValue()+"";
        Connection connection = DBConnection.getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM ROOMINFO WHERE ROOMNO = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, roomNo);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    String roomStatus = resultSet.getString("STATUS");
                    String roomType = resultSet.getString("ROOMTYPE");
                    String roomPriceDay = resultSet.getString("PRICEDAY");
                    String roomNote = resultSet.getString("NOTE");

                    roomStatusField.setText(roomStatus);
                    roomPriceField.setText(roomPriceDay);
                    roomTypeField.setText(roomType);
                    roomNoteField.setText(roomNote);
                } else {
//                    CommonTask.showAlert(Alert.AlertType.ERROR, "ERROR", "Can't get/set Info!");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }


    public void updateChoiceBox(){
        List<String> rooms = new ArrayList<String>();
        Connection connection = DBConnection.getConnections();
        try{
            if(!connection.isClosed()) {
                String sql = "SELECT * FROM ROOMINFO WHERE STATUS < ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, String.valueOf(maxPeople));
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    rooms.add(resultSet.getString("ROOMNO"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
        userRoomChoicebox.getItems().setAll(rooms);
        userRoomChoicebox.setValue(null);
    }

    public void setAndShowCustomerInfo(){
        Connection connection = DBConnection.getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM CUSTOMERINFO WHERE NID = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, currentCustomerNID);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    String customerName = resultSet.getString("NAME");
                    String customerNID = resultSet.getString("NID");
                    String customerEmail = resultSet.getString("EMAIL");
                    String customerPhone = resultSet.getString("PHONE");
                    //String customerPassword = resultSet.getString("PASSWORD");
                    String customerAddress = resultSet.getString("ADDRESS");

                    UserNameField.setText(customerName);
                    UserNIDField.setText(customerNID);
                    UserEmailField.setText(customerEmail);
                    UserPhoneField.setText(customerPhone);
                    //UserPasswordLabel.setText(customerPassword);
                    UserAddressField.setText(customerAddress);
                } else {
                    CommonTask.showAlert(Alert.AlertType.ERROR, "ERROR", "SQL connection Error!");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }
}