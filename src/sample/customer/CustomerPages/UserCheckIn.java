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

    private String customerTypeID;
    private String citizenID;
    private int maxPeople;
    private int newRoomCurrentStatus;
    private int currentFormNo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        findMaxPeople();
        updateChoiceBox();
        setAndShowCustomerInfo();
        userRoomChoicebox.setOnAction(this::setRoomInfo);
    }

    @FXML
    public void UserCheckInSubmitBtn(ActionEvent event) throws SQLException {
        String name = UserNameField.getText();
        String NID = UserNIDField.getText();
        String Email = UserEmailField.getText();
        String Phone = UserPhoneField.getText();
        String address = UserAddressField.getText();
        String RoomNo = userRoomChoicebox.getValue() + "";
        String CheckInDate = UserCheckIndate.getValue() + "";
        String roomNote = roomNoteField.getText() + "";
        String roomStatus = roomStatusField.getText() + "";
        String roomType = roomTypeField.getText() + "";
        String roomPrice = roomPriceField.getText() + "";

        // Kiểm tra kết nối
        Connection connection = DBConnection.getConnections();
        if (connection == null || connection.isClosed()) {
            CommonTask.showJFXAlert(rootPane, userCheckInPane, "error", "Error", "Cannot establish connection to the database!", JFXDialog.DialogTransition.CENTER);
            return;
        }

        // Kiểm tra các giá trị đầu vào
        if (roomType.isEmpty() || roomPrice.isEmpty() || roomStatus.isEmpty() || CheckInDate.equals("null")) {
            CommonTask.showJFXAlert(rootPane, userCheckInPane, "warning", "Warning!", "Field Can't be Empty!", JFXDialog.DialogTransition.CENTER);
            return;
        }

        String sql = "INSERT INTO ROOMRENTALFORM (ROOMNO, STARTDATE) VALUES (?, ?)";
        String sql1 = "INSERT INTO ROOMRENTALFORMDETAIL (ROOMFORMNO, NID, NAME, CUSTOMERTYPEID, CITIZENID, ADDRESS) VALUES (?, ?, ?, ?, ?, ?)";
        String sql2 = "UPDATE ROOMINFO SET STATUS = ? WHERE ROOMNO = ?";

        try {
            // Bắt đầu transaction
            connection.setAutoCommit(false);

            // Thực hiện chèn vào ROOMRENTALFORM
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, RoomNo);
            preparedStatement.setString(2, CheckInDate);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                // Lấy RoomFormNo (giả sử có khóa chính tự động tăng)
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int currentFormNo = generatedKeys.getInt(1);

                    // Thực hiện chèn vào ROOMRENTALFORMDETAIL
                    PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                    preparedStatement1.setInt(1, currentFormNo);
                    preparedStatement1.setString(2, NID);
                    preparedStatement1.setString(3, name);
                    preparedStatement1.setString(4, customerTypeID); // Đảm bảo biến này được định nghĩa và có giá trị
                    preparedStatement1.setString(5, citizenID); // Đảm bảo biến này được định nghĩa và có giá trị
                    preparedStatement1.setString(6, address);
                    preparedStatement1.executeUpdate();

                    // Cập nhật trạng thái phòng
                    PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                    preparedStatement2.setString(1, "Occupied"); // hoặc giá trị trạng thái mới thích hợp
                    preparedStatement2.setString(2, RoomNo);
                    preparedStatement2.executeUpdate();

                    // Commit transaction
                    connection.commit();
                    CommonTask.showJFXAlert(rootPane, userCheckInPane, "information", "Successful!", "Check In Successful!", JFXDialog.DialogTransition.CENTER);
                } else {
                    throw new SQLException("Failed to retrieve RoomFormNo.");
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            CommonTask.showJFXAlert(rootPane, userCheckInPane, "error", "Error!", "SQL Exception: " + e.getMessage(), JFXDialog.DialogTransition.CENTER);
        } finally {
            connection.setAutoCommit(true);
            DBConnection.closeConnections();
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
                String sql = "SELECT * FROM ROOMINFO WHERE ROOMNO = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, roomNo);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    String roomStatus = resultSet.getString("STATUS");
                    String roomNote = resultSet.getString("NOTE");
                    String roomType = resultSet.getString("ROOMTYPE");
                    String roomPriceDay = resultSet.getString("PRICEDAY");

                    roomStatusField.setText(roomStatus);
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

                String sql = "SELECT * FROM ROOMINFO WHERE NOTE = ?";
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setString(1,"Not Full");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
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
                    String customerAddress = resultSet.getString("ADDRESS");
                    customerTypeID = resultSet.getString("CUSTOMERTYPEID");
                    citizenID = resultSet.getString("CITIZENID");

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
