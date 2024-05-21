package sample.customer.CustomerPages.CustomerInfo;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.Main;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;

import static sample.customer.Login.UserLogin.currentCustomerNID;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserInfoEdit implements Initializable {

    public Button UserConfirm;
    public StackPane rootPane;
    public AnchorPane rootAnchorPane;

    @FXML
    private TextField UserNameEdit;

    @FXML
    private TextField UserNidEdit;

    @FXML
    private TextField UserEmailEdit;

    @FXML
    private TextField UserPhoneEdit;

    @FXML
    private TextField UserPassEdit;

    @FXML
    private TextField UserAddressEdit;

    @FXML
    private TextField CitizenIDEdit;

    @FXML
    private JFXComboBox CustomerTypeIDChoicebox;

    public static boolean editedFlag = false;

    @FXML
    void UserConfirmEdit(ActionEvent event) throws IOException, SQLException {
        Connection connection = DBConnection.getConnections();
        String customerName = UserNameEdit.getText();
        String customerNID = UserNidEdit.getText();
        String customerPassword = UserPassEdit.getText();
        String customerEmail = UserEmailEdit.getText();
        String customerPhone = UserPhoneEdit.getText();
        String customerAddress = UserAddressEdit.getText();
        String citizenID = CitizenIDEdit.getText();
        String customerType = CustomerTypeIDChoicebox.getValue()+"";

//        System.out.println(customerPhone);
        if (customerName.isEmpty() || customerNID.isEmpty() || customerPassword.isEmpty() || customerEmail.isEmpty() || customerAddress.isEmpty() || customerPhone.isEmpty() || citizenID.isEmpty() || customerType.isEmpty()) {
            CommonTask.showJFXAlert(rootPane, rootAnchorPane, "warning", "Warning!", "Text field can't be empty!", JFXDialog.DialogTransition.CENTER);
        } else {
            String sql = "UPDATE CUSTOMERINFO SET SET NAME = ?, NID = ?, PASSWORD = ?, EMAIL = ?, PHONE = ?, ADDRESS = ?, CITIZENID = ?, CUSTOMERTYPEID = ? WHERE NID = ?";
            PreparedStatement preparedStatementUpdate = connection.prepareStatement(sql);
            preparedStatementUpdate.setString(1, customerName);
            preparedStatementUpdate.setString(2, customerNID);
            preparedStatementUpdate.setString(3, customerPassword);
            preparedStatementUpdate.setString(4, customerEmail);
            preparedStatementUpdate.setString(5, customerPhone);
            preparedStatementUpdate.setString(6, customerAddress);
            preparedStatementUpdate.setString(7, citizenID);
            preparedStatementUpdate.setString(8, customerType);
            try {
                preparedStatementUpdate.execute();
//                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Update Successful!");]
                editedFlag = true;
                CommonTask.pageNavigation("/sample/customer/CustomerPages/CustomerInfo/UserInfo.fxml", (Stage) UserConfirm.getScene().getWindow(),this.getClass(),"User Home", 550, 400);

            } catch (SQLException e){
                CommonTask.showJFXAlert(rootPane, rootAnchorPane, "ERROR", "ERROR!", "Connection Problem!", JFXDialog.DialogTransition.CENTER);
            } finally {
                DBConnection.closeConnections();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateChoicebox();
        setCustomerInfo();
    }

    private void updateChoicebox() {
        CustomerTypeIDChoicebox.getItems().addAll("DOMESTIC","FOREIGN");
    }

    public void setCustomerInfo(){
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
                    String customerPassword = resultSet.getString("PASSWORD");
                    String customerAddress = resultSet.getString("ADDRESS");
                    String citizenID = resultSet.getString("CITIZENID");
                    String customerTypeID = resultSet.getString("CUSTOMERTYPEID");

                    UserNameEdit.setText(customerName);
                    UserNidEdit.setText(customerNID);
                    UserEmailEdit.setText(customerEmail);
                    UserPhoneEdit.setText(customerPhone);
                    UserPassEdit.setText(customerPassword);
                    UserAddressEdit.setText(customerAddress);
                    CitizenIDEdit.setText(citizenID);
                    CustomerTypeIDChoicebox.setValue(customerTypeID);
                } else {
//                    CommonTask.showAlert(Alert.AlertType.ERROR, "ERROR", "Can't get/set Info!");
                    CommonTask.showJFXAlert(rootPane, rootAnchorPane, "ERROR", "ERROR!", "Connection Problem!", JFXDialog.DialogTransition.CENTER);

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBConnection.closeConnections();
        }
    }

    public void BackBtn(ActionEvent event) throws IOException {
        CommonTask.pageNavigation("/sample/customer/CustomerPages/CustomerInfo/UserInfo.fxml", (Stage) UserConfirm.getScene().getWindow(),this.getClass(),"User Home", 550, 400);
    }
}
