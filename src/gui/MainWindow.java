package gui; /**
 * Created by Сергей on 14.05.2019.
 */

import flat_management.Flat;
import flat_management.Human;
import flat_management.Student;
import flat_management.Worker;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainWindow {
    public MainWindow() {
    }

    public void display(Stage primaryStage) {
        final Flat flat = new Flat();
        final Stage mainStage = new Stage();
        mainStage.setResizable(false);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 300.0D, 305.0D);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10.0D));
        gridPane.setHgap(5.0D);
        gridPane.setVgap(5.0D);

        for(int i = 0; i < 2; ++i) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(50.0D);
            gridPane.getColumnConstraints().add(column);
        }

        final Label roomStatusLabel = new Label("Статус комнаты: " + this.refreshStatus(flat));
        gridPane.add(roomStatusLabel, 0, 0, 2, 1);
        Button allocateRoomButton = new Button("Выделить комнату");
        gridPane.add(allocateRoomButton, 0, 1);
        allocateRoomButton.setMaxWidth(1.7976931348623157E308D);
        allocateRoomButton.setMaxHeight(1.7976931348623157E308D);
        Button vacateRoomButton = new Button("Освободить комнату");
        gridPane.add(vacateRoomButton, 1, 1);
        vacateRoomButton.setMaxWidth(1.7976931348623157E308D);
        vacateRoomButton.setMaxHeight(1.7976931348623157E308D);
        Label label = new Label("Выберите кого заселить:");
        gridPane.add(label, 0, 2, 2, 1);
        RadioButton moveInStudentRadioButton = new RadioButton("Студента");
        RadioButton moveInWorkerRadioButton = new RadioButton("Рабочего");
        final ToggleGroup moveInToggleGroup = new ToggleGroup();
        moveInStudentRadioButton.setToggleGroup(moveInToggleGroup);
        moveInWorkerRadioButton.setToggleGroup(moveInToggleGroup);
        gridPane.add(moveInStudentRadioButton, 0, 3);
        gridPane.add(moveInWorkerRadioButton, 0, 4);
        Button moveInButton = new Button("Заселить");
        gridPane.add(moveInButton, 0, 5);
        moveInButton.setMaxWidth(1.7976931348623157E308D);
        moveInButton.setMaxHeight(1.7976931348623157E308D);
        final Button moveOutButton = new Button("Выселить");
        gridPane.add(moveOutButton, 1, 5);
        moveOutButton.setMaxWidth(1.7976931348623157E308D);
        moveOutButton.setMaxHeight(1.7976931348623157E308D);
        final ObservableList<String> langs = FXCollections.observableArrayList();
        ListView<String> langsListView = new ListView(langs);
        langsListView.setPrefSize(290.0D, 150.0D);
        langsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        langsListView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) {
                moveOutButton.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        try {
                            ListIterator<Human> it = flat.getTenants().listIterator();

                            for(int i = ((Integer)newValue).intValue(); i > 0; --i) {
                                it.next();
                            }

                            ((Human)it.next()).moveOut();
                            flat.evictTenant(it);
                            MainWindow.this.refreshTenantsList(flat, langs);
                        } catch (NoSuchElementException var4) {
                            MainWindow.this.callAlert("Error! NoSuchElementException..");
                        }

                    }
                });
            }
        });
        FlowPane flowPane = new FlowPane(Orientation.VERTICAL, 10.0D, 10.0D, new Node[]{langsListView});
        gridPane.add(flowPane, 0, 6, 2, 1);
        allocateRoomButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                flat.allocateRoom();
                roomStatusLabel.setText("Статус комнаты: " + MainWindow.this.refreshStatus(flat));
            }
        });
        vacateRoomButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                flat.vacateRoom();
                MainWindow.this.refreshTenantsList(flat, langs);
                roomStatusLabel.setText("Статус комнаты: " + MainWindow.this.refreshStatus(flat));
            }
        });
        moveInButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(flat.getRoom() != null) {
                    MoveInWindow moveInWindow = new MoveInWindow();

                    try {
                        RadioButton selection = (RadioButton)moveInToggleGroup.getSelectedToggle();
                        if(selection.getText().equals("Студента")) {
                            Student student = new Student();
                            moveInWindow.display(mainStage, student, flat, langs);
                        }

                        if(selection.getText().equals("Рабочего")) {
                            Worker worker = new Worker();
                            moveInWindow.display(mainStage, worker, flat, langs);
                        }
                    } catch (NullPointerException var5) {
                        MainWindow.this.callAlert("Error! NullPointerException..");
                    }
                } else {
                    MainWindow.this.callAlert("Комната не сдаётся!");
                }

            }
        });
        root.setCenter(gridPane);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void refreshTenantsList(Flat flat, ObservableList<String> langs) {
        langs.clear();
        ListIterator it = flat.getTenants().listIterator();

        while(it.hasNext()) {
            langs.add(((Human)it.next()).getInfo());
        }

    }

    public String refreshStatus(Flat flat) {
        return flat.getRoom() != null?"сдаётся":"не сдаётся";
    }

    public void callAlert(String msg) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText((String)null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}