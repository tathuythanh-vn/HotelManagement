package sample.manager.ManagerPages.RoomInfoEdit;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample._BackEnd.DBConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static sample._BackEnd.DBConnection.closeConnections;
import static sample._BackEnd.DBConnection.getConnections;

public class RoomInfoEdit implements Initializable {
    public Button UserConfirm;

    public TextField roomNoField;
    public JFXComboBox roomTypeComboBox;
    public TextField priceDayField;
    public JFXComboBox noteCbox;
    private String[] roomStats = {"Full", "Not Full"};

    public void closeBtn(ActionEvent event) {
        Stage stage = (Stage) UserConfirm.getScene().getWindow();
        stage.close();
    }

    private Connection connection = DBConnection.getConnections();

    private void onComboBoxSelectionChanged() {
        String selectedParameter = (String) roomTypeComboBox.getValue();
        if (selectedParameter != null) {
            // Truy vấn cơ sở dữ liệu để lấy giá trị tương ứng từ bảng parameters
            String query = "SELECT PARAMETERVALUE FROM parameters WHERE PARAMETERNAME = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, selectedParameter);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String parameterValue = resultSet.getString("PARAMETERVALUE");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveBtn(ActionEvent event) {
        Connection connection = getConnections();
        try {
            if (!connection.isClosed()){
                String sql = "UPDATE RoomInfo SET ROOMTYPE = ?, PRICEDAY = ?, NOTE = ? where ROOMNO = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, roomTypeComboBox.getValue()+"");
                statement.setString(2, priceDayField.getText());
                statement.setString(3, noteCbox.getValue()+"");
                statement.setString(4, roomNoField.getText());
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeConnections();
        }
        Stage stage = (Stage) UserConfirm.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        noteCbox.getItems().setAll(roomStats);
    }

    public void setRoomInfo(String roomNo, String type, String priceDay, String note) {
        roomNoField.setText(roomNo);
        roomNoField.setDisable(true);
        roomTypeComboBox.setValue(type);
        priceDayField.setText(priceDay);
        noteCbox.setValue(note);
    }
}
