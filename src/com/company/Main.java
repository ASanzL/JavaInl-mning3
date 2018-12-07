package com.company;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    ArrayList<Consert> list = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Boka konsert");

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "1",
                        "2",
                        "3"
                );
        final ComboBox comboBox = new ComboBox(options);
        comboBox.getSelectionModel().selectFirst();

        Text text = new Text("");

        Button btn = new Button();
        btn.setText("Lägg till");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                switch (comboBox.getValue().toString()) {
                    case "1":
                        list.add(new Consert("Konsert 1", 5000));
                }
                updateListText(text);
            }
        });

        Separator separator1 = new Separator();

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(comboBox, 0, 0);
        grid.add(btn, 1, 0);
        grid.add(separator1, 0, 1);
        grid.add(text, 0, 2);

        stage.setScene(new Scene(grid, 400, 300));
        stage.show();
    }

    private void updateListText(Text text) {
        String s = "";
        for (Consert list : list) {
            s += list.getName() + " - " + list.getPrice() + "kr." + "\n";
        }
        text.setText(s);
    }
}
