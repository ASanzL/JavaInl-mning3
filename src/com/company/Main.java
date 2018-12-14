package com.company;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Program för att köpa konsertbiljetter.
 * @author Andreas Sanz
 */
//TO-DO: Göra så man kan välja antal biljetter, med +/-
public class Main extends Application {
    ArrayList<Consert> consertsList = new ArrayList<>();
    Consert markKnopfler = new Consert("Mark Knopfler", 630);
    Consert bobDylan = new Consert("Bob Dylan", 645);
    Consert metallica = new Consert("Metallica", 525);

    Text allSelectedConsertsText = new Text("");
    Text selectedConsertPriceText = new Text("");
    Text allConsertTotalPriceText = new Text("");

    ComboBox comboBox;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Boka konsert");

        allSelectedConsertsText.setId("all-selected-conserts");
        selectedConsertPriceText.setId("selected-price");
        allConsertTotalPriceText.setId("total-price");

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Mark Knopfler",
                        "Bob Dylan",
                        "Metallica"
                );
        comboBox = new ComboBox(options);
        comboBox.getSelectionModel().selectFirst();
        comboBox.setId("dropdown");

        selectedConsertPriceText.setText(updatePriceText(comboBox.getValue().toString()));
        comboBox.setOnAction(e ->
                selectedConsertPriceText.setText(updatePriceText(comboBox.getValue().toString()))
        );

        Button addConsertButton = new Button();
        addConsertButton.setText("Lägg till");
        addConsertButton.setId("add-consert");

        addConsertButton.setOnAction(e ->
                onAddConsert()
                );

        Button clearConsertsButton = new Button();
        clearConsertsButton.setText("Rensa");
        clearConsertsButton.setId("clear-conserts");

        clearConsertsButton.setOnAction(e ->
            onClearConserts()
        );

        Button buyButton = new Button();
        buyButton.setText("Köp");
        buyButton.setId("buy-conserts");

        buyButton.setOnAction(e -> {
            if(!consertsList.isEmpty()) {
                stage.close();
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setAlignment(Pos.TOP_CENTER);

        grid.add(comboBox, 0, 0);
        grid.add(selectedConsertPriceText, 1, 0);
        grid.add(addConsertButton, 1, 1);
        grid.add(clearConsertsButton, 2, 1);
        grid.add(buyButton, 1, 2);
        grid.add(allSelectedConsertsText, 0, 3);
        grid.add(allConsertTotalPriceText, 0, 2);

        Scene scene = new Scene(grid, 600, 400);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Läser värdet ur combobox och lägger in rätt värde i consertlist.
     */
    private void onAddConsert() {
        switch (comboBox.getValue().toString()) {
            case "Mark Knopfler":
                consertsList.add(markKnopfler);
                break;
            case "Bob Dylan":
                consertsList.add(bobDylan);
                break;
            case "Metallica":
                consertsList.add(metallica);
                break;
        }
        updateListText();
        updateTotalPriceText();
    }

    /**
     * Rensar consertlist
     */
    private void onClearConserts() {
        consertsList.clear();
        updateListText();
        updateTotalPriceText();
    }

    /**
     * Skapar textsträng med vald konserts pris
     * @param s Konsert namn
     * @return Pris i kr
     */
    private String updatePriceText(String s) {
        switch (s) {
            case "Mark Knopfler":
                return markKnopfler.getPrice() + " kr";
            case "Bob Dylan":
                return bobDylan.getPrice() + " kr";
            case "Metallica":
                return metallica.getPrice() + " kr";
        }
        return "";
    }

    /**
     * Uppdaterar ett textfält med alla valda konserter.
     */
    private void updateListText() {
        String s = "";
        for (Consert list : consertsList) {
            s += list.getName() + " | " + list.getPrice() + "kr." + "\n";
        }
        allSelectedConsertsText.setText(s);
    }

    /**
     * Uppdaterar ett textfält med det totala priset för alla valda konserter.
     */
    private void updateTotalPriceText() {
        int i = 0;
        for (Consert list : consertsList) {
            i += list.getPrice();
        }
        allConsertTotalPriceText.setText("Totalpris: " + i + " kr");
    }
}
