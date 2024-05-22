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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static sample.customer.Login.UserLogin.currentCustomerNID;
public class UserCheckIn implements Initializable {

    public Label roomNoteField;
    public Label roomTypeField;
    public Label roomPriceField;
    public Label roomStatusField;
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

    int maxPeople;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        findMaxPeople();
        updateChoiceBox();
        setAndShowCustomerInfo();
        userRoomChoicebox.setOnAction(this::setRoomInfo);
    }

    @FXML
    void UserCheckInSubmitBtn(ActionEvent event) throws SQLException {
        String name = UserNameField.getText();
        String NID = UserNIDField.getText();
        String Email = UserEmailField.getText();
        String Phone = UserPhoneField.getText();
        String Address = UserAddressField.getText();
        String RoomNo = userRoomChoicebox.getValue()+"";
        String CheckInDate = UserCheckIndate.getValue()+"";
        String roomNote = roomNoteField.getText()+"";
        String roomStatus = roomStatusField.getText()+"";
        String roomType = roomTypeField.getText()+"";
        String roomPrice = roomPriceField.getText()+"";
        Connection connection = DBConnection.getConnections();
        if (roomType.equals("") || roomPrice.equals("") || roomStatus.equals("") || CheckInDate.equals("null")) {
//            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
            CommonTask.showJFXAlert(rootPane, userCheckInPane, "warning", "Warning!", "Field Can't be Empty!", JFXDialog.DialogTransition.CENTER);
        } else {
            //here
            String sql = "INSERT INTO ROOMRENTALFORMDETAIL (NAME, NID, EMAIL, PHONE, ADDRESS, ROOMNO, CHECKEDIN, ROOMTYPE, NOTE, PRICEDAY) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


//            prepare information to add the checkin
            try{
                preparedStatement.execute();

                //rewrite status of the room
                String sql1 = "UPDATE ROOMINFO SET STATUS = 'Occupied' WHERE ROOM_NO = ?";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.setString(1, RoomNo);
                preparedStatement1.execute();
//                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Check-in Successful!");
                CommonTask.showJFXAlert(rootPane, userCheckInPane, "information", "Successful!", "Check In Successful!", JFXDialog.DialogTransition.CENTER);
            } catch (SQLException e){
                CommonTask.showJFXAlert(rootPane, userCheckInPane, "information", "Error!", "SQL Exception Happened!", JFXDialog.DialogTransition.CENTER);
            } finally {
                DBConnection.closeConnections();
            }
        }
        updateChoiceBox();
        clearTextFields();
    }

    private void findMaxPeople() {
        Connection connection = DBConnection.getConnections();
        try {
            if (!connection.isClosed()){
                String sql = "SELECT PARAMETERVALUE FROM PARAMETERS WHERE PARAMETERNAME = ?";

                // Create PreparedStatement
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, "MAXCUSTOMER");

                // Execute query
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()){
                    maxPeople = resultSet.getInt("PARAMETERVALUE");
                }
            }
        } catch (Exception e){
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
                String sql = "SELECT * FROM ROOMINFO WHERE ROOM_NO = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, roomNo);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    String roomNote = resultSet.getString("NOTE");
                    String roomType = resultSet.getString("TYPE");
                    String roomPriceDay = resultSet.getString("PRICEDAY");

                    roomNoteField.setText(roomNote);
                    roomPriceField.setText(roomPriceDay);
                    roomTypeField.setText(roomType);
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

                String sql = "SELECT * FROM ROOMINFO WHERE STATUS = ?";
                PreparedStatement statement = connection.prepareStatement(sql);

                int[] status = new int[maxPeople];
                for (int i = 0; i < maxPeople; i++) {
                    status[i] = i;
                }

                for (int i : status) {
                    statement.setString(1, String.valueOf(i));
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        rooms.add(resultSet.getString("ROOMNO"));
                    }
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
                    String customerAddress = resultSet.getString("ADDRESS");

                    UserNameField.setText(customerName);
                    UserNIDField.setText(customerNID);
                    UserEmailField.setText(customerEmail);
                    UserPhoneField.setText(customerPhone);
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

    private void clearTextFields() {
        roomTypeField.setText("");
        roomStatusField.setText("");
        roomNoteField.setText("");
        roomPriceField.setText("");
        UserCheckIndate.getEditor().clear();
//        roomChoiceBox.setValue(null);
    }

}
