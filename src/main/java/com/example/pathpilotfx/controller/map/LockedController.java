package com.example.pathpilotfx.controller.map;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import com.example.pathpilotfx.controller.countries.SelectedCountry;
import com.example.pathpilotfx.controller.todolist.TaskController;
import com.example.pathpilotfx.database.CountryDAO;
import com.example.pathpilotfx.database.ExplorationDAO;
import com.example.pathpilotfx.model.Country;
import com.example.pathpilotfx.model.Exploration;
import com.example.pathpilotfx.model.Task;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Controller class for the passport view.
 */
public class LockedController {

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

    @FXML
    private Button unselectButton;

    @FXML
    private Button viewButton;

    //used to fetch countries and create appropriate countryList (ordered and split)
    CountryDAO countries = new CountryDAO();

    private List<Country> countryList = countries.getLocked();
    private List<List<Country>> splitCountries = splitList(countryList,6);
    private int sublist_no = 0;

    // below variables used to determine selected country
    private PassportLocationController selectedLocationController;
    private Country selectedCountry;
    private Map<Country, PassportLocationController> countryControllerMap = new HashMap<>();

    /**
     * Initializes the passport view.
     */
    @FXML
    void initialize() {

//        for(Country country: countryList){
//            System.out.println(country.getCountryName());
//        }
//
//        for (List<Country> sublist : splitCountries) {
//            for (Country country : sublist) {
//                System.out.print(country.getCountryName() + " ");
//            }
//            System.out.println(); // New line for each sublist
//        }

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

        unselectViewVisibility();



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
            PassportLocationController controller = loader.getController();
            controller.setCountry(country);
            addCountryControllerMapping(country,controller);

            // Set the PassportViewController instance
            controller.setLockedController(this);

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
     * Updates the selected country when a country is clicked.
     *
     * @param clickedCountry The country that was clicked.
     */
    public void updateSelectedCountry(Country clickedCountry) {
        // If a location is already selected, deselect it
        if (selectedLocationController != null) {
            selectedLocationController.removeBorder();
        }

        // Select the new location and update the selectedCountry reference
        selectedCountry = clickedCountry;
        selectedLocationController = countryControllerMap.get(clickedCountry);
        selectedLocationController.applyBorder();

        unselectViewVisibility();

    }

    /**
     * Handles the unselect button click event.
     *
     * @param event The action event.
     */
    @FXML
    void unselect(ActionEvent event) {
        if (selectedLocationController != null) {
            selectedLocationController.removeBorder();
        }
        selectedCountry = null;
        unselectViewVisibility();

    }

    /**
     * Handles the view button click event.
     *
     * @param event The action event.
     */
    @FXML
    void view(ActionEvent event) {
        SelectedCountry.setSelectedCountry(selectedCountry);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/testmix.fxml"));
            AnchorPane countryView = loader.load();
            rootAnchorPane.getChildren().setAll(countryView);

        } catch(IOException e){
            System.out.println("countryView has not been found");
            throw new RuntimeException(e);
        }

    }

    /**
     * Sets the visibility of unselect and view buttons based on the selected country.
     */
    public void unselectViewVisibility(){
        if (selectedCountry == null){
            unselectButton.setVisible(false);
            viewButton.setVisible(false);
        }
        else{
            unselectButton.setVisible(true);
            viewButton.setVisible(true);
        }
    }

    /**
     * Adds a country and its associated controller to the map.
     *
     * @param country     The country.
     * @param controller  The associated controller.
     */
    public void addCountryControllerMapping(Country country, PassportLocationController controller) {
        countryControllerMap.put(country, controller);
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
                country.setLocked(true);
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

