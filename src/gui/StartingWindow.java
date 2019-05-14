package gui; /**
 * Created by Сергей on 14.05.2019.
 */
import flat_management.Flat;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StartingWindow {
    public StartingWindow() {
    }

    public void display(final Stage primaryStage) {
        try {
            final Flat flat = new Flat();
            final Stage startingStage = new Stage();
            startingStage.setResizable(false);
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 250.0D, 170.0D);
            GridPane gridpane = new GridPane();
            gridpane.setPadding(new Insets(10.0D));
            gridpane.setHgap(5.0D);
            gridpane.setVgap(5.0D);

            for(int i = 0; i < 2; ++i) {
                ColumnConstraints column = new ColumnConstraints();
                column.setPercentWidth(50.0D);
                gridpane.getColumnConstraints().add(column);
            }

            Label label = new Label("Welcome!");
            gridpane.add(label, 0, 0);
            label = new Label("Введите адрес вашей квартиры.");
            gridpane.add(label, 0, 1, 2, 1);
            label = new Label("Улица: ");
            gridpane.add(label, 0, 2);
            final TextField streetField = new TextField();
            gridpane.add(streetField, 1, 2);
            label = new Label("Дом: ");
            gridpane.add(label, 0, 3);
            final TextField houseField = new TextField();
            gridpane.add(houseField, 1, 3);
            label = new Label("Квартира: ");
            gridpane.add(label, 0, 4);
            final TextField flatNumberField = new TextField();
            gridpane.add(flatNumberField, 1, 4);
            Button continueButton = new Button("Продолжить");
            gridpane.add(continueButton, 0, 5);
            continueButton.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    try {
                        flat.setAddress(streetField.getText(), Integer.parseInt(houseField.getText()), Integer.parseInt(flatNumberField.getText()));
                        if(streetField.getText().equals("")) {
                            throw new EmptyStringException();
                        }

                        if(!streetField.getText().matches("\\D*$")) {
                            throw new NumberInStringException();
                        }

                        MainWindow mainWindow = new MainWindow();
                        mainWindow.display(primaryStage);
                        startingStage.close();
                    } catch (NumberFormatException var3) {
                        StartingWindow.this.callAlert("Error! NumberFormatException..");
                    } catch (EmptyStringException var4) {
                        StartingWindow.this.callAlert("Error! gui.EmptyStringException..");
                    } catch (NumberInStringException var5) {
                        StartingWindow.this.callAlert("Error! gui.NumberInStringException..");
                    }

                }
            });
            root.setCenter(gridpane);
            startingStage.setScene(scene);
            startingStage.show();
        } catch (Exception var12) {
            var12.printStackTrace();
        }

    }

    public void callAlert(String exceptionMessage) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText((String)null);
        alert.setContentText(exceptionMessage);
        alert.showAndWait();
    }
}