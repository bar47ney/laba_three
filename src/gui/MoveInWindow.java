package gui; /**
 * Created by Сергей on 14.05.2019.
 */
import flat_management.Flat;
import flat_management.Human;
import java.util.ListIterator;
import javafx.collections.ObservableList;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MoveInWindow {
    public MoveInWindow() {
    }

    public void display(Stage mainStage, final Human person, final Flat flat, final ObservableList<String> langs) {
        final Stage moveInStage = new Stage();
        moveInStage.setResizable(false);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 180.0D, 100.0D);
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(10.0D));
        gridpane.setHgap(5.0D);
        gridpane.setVgap(5.0D);
        moveInStage.initModality(Modality.WINDOW_MODAL);
        moveInStage.initOwner(mainStage);

        for(int i = 0; i < 2; ++i) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(50.0D);
            gridpane.getColumnConstraints().add(column);
        }

        Label label = new Label("Имя:");
        gridpane.add(label, 0, 0);
        final TextField firstNameField = new TextField();
        gridpane.add(firstNameField, 1, 0);
        label = new Label("Фамилия:");
        gridpane.add(label, 0, 1);
        final TextField lastNameField = new TextField();
        gridpane.add(lastNameField, 1, 1);
        Button moveInButton = new Button("Заселить");
        gridpane.add(moveInButton, 0, 2, 2, 1);
        moveInButton.setMaxWidth(1.7976931348623157E308D);
        moveInButton.setMaxHeight(1.7976931348623157E308D);
        moveInButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    person.setFirstName(firstNameField.getText());
                    if(firstNameField.getText().equals("")) {
                        throw new EmptyStringException();
                    }

                    if(!firstNameField.getText().matches("\\D*$")) {
                        throw new NumberInStringException();
                    }

                    person.setLastName(lastNameField.getText());
                    if(lastNameField.getText().equals("")) {
                        throw new EmptyStringException();
                    }

                    if(!lastNameField.getText().matches("\\D*$")) {
                        throw new NumberInStringException();
                    }

                    person.setAddress(flat.getAddress());
                    person.setFlat(flat);
                    flat.acceptTenant(person);
                    MoveInWindow.this.refreshTenantsList(flat, langs);
                } catch (EmptyStringException var3) {
                    MoveInWindow.this.callAlert("Error! gui.EmptyStringException..");
                } catch (NumberInStringException var4) {
                    MoveInWindow.this.callAlert("Error! gui.NumberInStringException..");
                }

                moveInStage.close();
            }
        });
        root.setCenter(gridpane);
        moveInStage.setScene(scene);
        moveInStage.show();
    }

    public void callAlert(String msg) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText((String)null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void refreshTenantsList(Flat flat, ObservableList<String> langs) {
        langs.clear();
        ListIterator it = flat.getTenants().listIterator();

        while(it.hasNext()) {
            langs.add(((Human)it.next()).getInfo());
        }

    }
}