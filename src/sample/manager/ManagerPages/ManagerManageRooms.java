package sample.manager.ManagerPages;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import sample._BackEnd.CommonTask;
import sample._BackEnd.DBConnection;
import sample._BackEnd.TableView.ManagerRoomTable;
import sample.manager.ManagerPages.RoomInfoEdit.RoomInfoEdit;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import static sample.Main.xxx;
import static sample.Main.yyy;

public class ManagerManageRooms extends DBConnection implements Initializable {

    

    public TableView<ManagerRoomTable> roomTable;
    public TableColumn<ManagerRoomTable, String> roomNoCol;
    public TableColumn<ManagerRoomTable, String> roomTypeCol;
    public TableColumn<ManagerRoomTable, String> roomCapacityCol;
    public TableColumn<ManagerRoomTable, String> price_DayCol;
    public TableColumn<ManagerRoomTable, String> roomNoteCol;
    public JFXTextField roomTypeField;
    public JFXComboBox roomTypeComboBox;

    /*
    public JFXTextField price_dayField;
*/
    public JFXTextField roomNoField;
    public TableColumn actionCol;

    private String[] roomNote = {"Full", "Not Full"};

    private ObservableList<ManagerRoomTable> TABLEROW = FXCollections.observableArrayList();

    @FXML
    private TextField price_dayArea;

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
                        price_dayArea.setText(parameterValue);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public void addRoom(ActionEvent actionEvent) throws SQLException {
        Connection connection = getConnections();
        if (connection == null || connection.isClosed()) {
            CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Cannot establish connection to the database!");
            return;
        }

        String roomNo = roomNoField.getText();
        String roomType = roomTypeComboBox.getValue() + "";
        String price_day = price_dayArea.getText();

        if (roomNo.isEmpty() || roomType == null || price_day.isEmpty() || roomNote == null) {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Error", "Field can't be empty!");
            return;
        }

        // Kiểm tra xem số phòng đã tồn tại chưa
        String checkSql = "SELECT COUNT(*) FROM ROOMINFO WHERE ROOMNO = ?";
        PreparedStatement checkStatement = connection.prepareStatement(checkSql);
        checkStatement.setString(1, roomNo);
        ResultSet resultSet = checkStatement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        if (count > 0) {
            CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "This Room No. already exists!");
            return;
        }

        String sql = "INSERT INTO ROOMINFO (ROOMNO, ROOMTYPE, PRICEDAY, STATUS, NOTE) VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, roomNo);
        preparedStatement.setString(2, roomType);
        preparedStatement.setString(3, price_day);
        preparedStatement.setString(4, String.valueOf(0));
        preparedStatement.setString(5, "Not Full"); // Thiết lập giá trị STATUS = 0 khi tạo mới

        try {
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                CommonTask.showAlert(Alert.AlertType.INFORMATION, "Successful", "Room Added Successfully!");
                showRoomTable();
            } else {
                CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "Room could not be added.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            CommonTask.showAlert(Alert.AlertType.ERROR, "Error", "This Room No. already exists!");
        } finally {
            closeConnections();
        }
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomTypeComboBox.getItems().addAll("A", "B", "C");
        roomTypeComboBox.setOnAction(event -> onComboBoxSelectionChanged());
        roomNoCol.setCellValueFactory(new PropertyValueFactory<ManagerRoomTable, String>("ROOMNO"));
        roomTypeCol.setCellValueFactory(new PropertyValueFactory<ManagerRoomTable, String>("ROOMTYPE"));
        price_DayCol.setCellValueFactory(new PropertyValueFactory<ManagerRoomTable, String>("PRICEDAY"));
        roomNoteCol.setCellValueFactory(new PropertyValueFactory<ManagerRoomTable, String>("NOTE"));
        showRoomTable();
        actionButtons();
    }

    public void showRoomTable(){
        TABLEROW.clear();
        Connection connection = getConnections();
        try {
            if(!connection.isClosed()){
                String sql = "SELECT * FROM ROOMINFO ORDER BY STATUS";
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    String ROOMNO = resultSet.getString("ROOMNO"); //SQL COL NAMES NID
                    String ROOMTYPE = resultSet.getString("ROOMTYPE");
                    String PRICE_DAY = resultSet.getString("PRICEDAY");
                    String NOTE = resultSet.getString("NOTE");
                    String STATUS = resultSet.getString("STATUS");

                    ManagerRoomTable roomTablee = new ManagerRoomTable(ROOMNO, ROOMTYPE, PRICE_DAY, NOTE, STATUS);

                    TABLEROW.add(roomTablee);
                }
                roomTable.getItems().setAll(TABLEROW);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeConnections();
        }
    }


    private void actionButtons() {
        Callback<TableColumn<ManagerRoomTable, String>, TableCell<ManagerRoomTable, String>> cellCallback =
                new Callback<TableColumn<ManagerRoomTable, String>, TableCell<ManagerRoomTable, String>>() {
                    @Override
                    public TableCell<ManagerRoomTable, String> call(TableColumn<ManagerRoomTable, String> param) {

                        TableCell<ManagerRoomTable, String> cell = new TableCell<ManagerRoomTable, String>() {

                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                            FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.EDIT);

                            public HBox hBox = new HBox(25, editIcon, deleteIcon);

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty){
                                    setGraphic(null);
                                    setText(null);
                                }else{

                                    deleteIcon.setStyle(
                                            " -fx-cursor: hand ;"
                                                    + "-glyph-size:20px;"
                                                    + "-fx-fill:#ffffff;"
                                    );

                                    deleteIcon.setOnMouseEntered((MouseEvent event) ->{
                                        deleteIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:khaki;"
                                        );
                                    });

                                    deleteIcon.setOnMouseExited((MouseEvent event2) ->{
                                        deleteIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        + "-fx-fill:white;"
                                        );
                                    });

                                    deleteIcon.setOnMouseClicked((MouseEvent event2) ->{
                                        deleteIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:lightgreen;"
                                        );

                                        ManagerRoomTable managerRoomTable = getTableView().getItems().get(getIndex());
                                        tableRowDelete(managerRoomTable);

                                    });

                                    editIcon.setStyle(
                                            " -fx-cursor: hand ;"
                                                    + "-glyph-size:20px;"
                                                    + "-fx-fill:#ffffff;"
                                    );

                                    editIcon.setOnMouseEntered((MouseEvent event) ->{
                                        editIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:khaki;"
                                        );
                                    });

                                    editIcon.setOnMouseExited((MouseEvent event2) ->{
                                        editIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        + "-fx-fill:white;"
                                        );
                                    });

                                    editIcon.setOnMouseClicked((MouseEvent event2) ->{
                                        editIcon.setStyle(
                                                " -fx-cursor: hand ;"
                                                        +
                                                        "-glyph-size:20px;"
                                                        +"-fx-fill:lightgreen;"
                                        );
                                        ManagerRoomTable managerRoomTable = getTableView().getItems().get(getIndex());
//                                        System.out.println(managerRoomTable.getROOMNO());
                                        try {
                                            editTableRowInfo(managerRoomTable);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    });



                                    hBox.setStyle("-fx-alignment:center");
//                                    hBox.setMaxWidth(40);
//                                    HBox.setMargin(editIcon, new Insets(2, 10, 0, 10));
//                                    HBox.setMargin(deleteIcon, new Insets(2, 10, 0, 10));
                                    setGraphic(hBox);
                                }
                            }
                        };

                        return cell;
                    }
                };
        actionCol.setCellFactory(cellCallback);
    }

    public void tableRowDelete(ManagerRoomTable managerRoomTable) {
        String roomStatus = managerRoomTable.getNOTE();
        if (!roomStatus.equals("Full")) {
            Connection connection = getConnections();
            try {
                if (!connection.isClosed()) {
                    String sql = "DELETE FROM RoomInfo where ROOMNO=?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1, managerRoomTable.getROOMNO());

                    statement.execute();

                    CommonTask.showAlert(Alert.AlertType.INFORMATION, "Delete Operation Successfull", "Room No " + managerRoomTable.getROOMNO() + " is deleted from database!");

                    roomTable.getItems().remove(managerRoomTable);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                closeConnections();
            }
        } else {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Delete Unsuccessful", "Can't delete. It's currently booked by a customer.");
        }
    }

    public void editTableRowInfo(ManagerRoomTable managerRoomTable) throws IOException {
        if (!(managerRoomTable.getNOTE()).equals("Full")) {
            Connection connection = getConnections();
            try {
                if (!connection.isClosed()) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/sample/manager/ManagerPages/RoomInfoEdit/roomInfoEdit.fxml"));
                    Parent viewContact = loader.load();
                    Scene scene = new Scene(viewContact);
                    // update information
                    RoomInfoEdit roomInfoEdit = loader.getController();
                    roomInfoEdit.setRoomInfo(managerRoomTable.getROOMNO(), managerRoomTable.getROOMTYPE(), managerRoomTable.getPRICEDAY(), managerRoomTable.getNOTE());
//                    System.out.println(managerRoomTable.getROOMNO() + " " + managerRoomTable.getROOMTYPE() + " " + managerRoomTable.getCAPACITY() + " " + managerRoomTable.getPRICEDAY());
                    Stage window = new Stage();
                    window.setScene(scene);
                    window.initStyle(StageStyle.UNDECORATED);

                    stagePosition(window, viewContact);

                    window.showAndWait();
                    showRoomTable();
                }

            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            } finally {
                closeConnections();
            }
        } else {
            CommonTask.showAlert(Alert.AlertType.WARNING, "Can't Edit!", " Currently booked by a customer.");
        }
    }

    public void stagePosition(Stage primaryStage, Parent root) {
        AtomicReference<Double> x = new AtomicReference<>(primaryStage.getX());
        AtomicReference<Double> y = new AtomicReference<>(primaryStage.getY());
        root.setOnMousePressed(event -> {
            xxx = event.getSceneX();
            yyy = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
//            if(event.getButton() == MouseButton.SECONDARY) {
            primaryStage.setX(event.getScreenX() - xxx);
            primaryStage.setY(event.getScreenY() - yyy);
            x.set(primaryStage.getX());
            y.set(primaryStage.getY());
//            }
        });
    }

}

