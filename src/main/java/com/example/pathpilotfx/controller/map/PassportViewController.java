package com.example.pathpilotfx.controller.map;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.pathpilotfx.controller.todolist.TaskController;
import com.example.pathpilotfx.database.CountryDAO;
import com.example.pathpilotfx.model.Country;
import com.example.pathpilotfx.model.Task;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Controller class for the passport view.
 */
public class PassportViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane passportGrid;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private Button nextButton;

    @FXML
    private Button previousButton;

    CountryDAO countries = new CountryDAO();
    private List<Country> countryList = countries.getAll();
    private List<List<Country>> splitCountries = splitList(countryList,6);

    private int sublist_no = 0;

    /**
     * Initializes the passport view.
     */
    @FXML
    void initialize() {


        for (List<Country> sublist : splitCountries) {
            for (Country country : sublist) {
                System.out.print(country.getCountryName() + " ");
            }
            System.out.println(); // New line for each sublist
        }

        if((countryList.size() > 6) && (splitCountries.get(sublist_no).size() == 6)){
            nextButton.setVisible(true);
        }
        else{
            nextButton.setVisible(false);
        }
        if(sublist_no != 0){
            previousButton.setVisible(true);
        }
        else{
            previousButton.setVisible(false);
        }

        displayPassport(sublist_no);


    }

    /**
     * Adds a country to the passport grid.
     *
     * @param country     The country to add.
     * @param columnIndex The column index.
     * @param rowIndex    The row index.
     */
    private void addCountry(Country country, int columnIndex, int rowIndex) {
        // create a task using todo(task).fxml
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/passport-location.fxml"));
            AnchorPane countryPane = loader.load();



            //add task to the vBox container in taskPage
            passportGrid.add(countryPane,columnIndex,rowIndex);
//            passportGrid.add(countryPane,0,1);
            //passportZeroZero.getChildren().add(countryPane);

            PassportLocationController controller = loader.getController();
            controller.setCountry(country);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Handles the next button click event.
     *
     * @param event The action event.
     */
    @FXML
    void next(ActionEvent event) {
        sublist_no++;
        initialize();
    }

    /**
     * Handles the previous button click event.
     *
     * @param event The action event.
     */
    @FXML
    void previous(ActionEvent event) {
        sublist_no--;
        initialize();
    }

    /**
     * Splits a list into sublists of a specified size.
     *
     * @param countries   The list of countries to split.
     * @param sublistSize The size of each sublist.
     * @return The list of sublists.
     */
    public static List<List<Country>> splitList(List<Country> countries, int sublistSize){
        List<List<Country>> sublists = new ArrayList<>();

        int fromIndex = 0;

        while (fromIndex < countries.size()){
            int toIndex = Math.min(fromIndex + sublistSize, countries.size());
            sublists.add(countries.subList(fromIndex, toIndex));
            fromIndex = toIndex;
        }

        return sublists;
    }

    /**
     * Displays the passport grid based on the current sublist.
     *
     * @param sublist The index of the current sublist.
     */
    private void displayPassport(int sublist){
        passportGrid.getChildren().clear();
        if (!countryList.isEmpty()){
            int column_count = 0;
            int row_count = 0;

            for (Country country : splitCountries.get(sublist)) {
                addCountry(country, column_count, row_count);
                column_count++;

                if(column_count > 2){
                    column_count = 0;
                    row_count++;
                }
            }
        }
    }
}

