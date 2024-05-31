package com.example.pathpilotfx.controller.map;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.controller.countries.SelectedCountry;
import com.example.pathpilotfx.database.CountryDAO;
import com.example.pathpilotfx.database.ExplorationDAO;
import com.example.pathpilotfx.model.Country;
import com.example.pathpilotfx.model.Exploration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


/**
 * Controller class for the passport view.
 */
public class PassportViewController {

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

    private final CountryDAO countries = new CountryDAO();
    private final ExplorationDAO user_countries = new ExplorationDAO();
    private List<Country> countryList;
    private List<List<Country>> splitCountries;
    private int sublist_no = 0;
    private PassportLocationController selectedLocationController;
    private Country selectedCountry;
    private final Map<Country, PassportLocationController> countryControllerMap = new HashMap<>();

    /**
     * Initializes the passport view.
     */
    @FXML
    public void initialize() {
        countryList = countries.getAll();
        List<Exploration> user_countryList = user_countries.getAllbyUser(SessionManager.getLoggedInUserId());
        List<Country> sortedList = orderList(countryList, user_countryList);
        splitCountries = splitList(sortedList, 6);
        nextButton.setVisible((countryList.size() > 6) && (splitCountries.get(sublist_no).size() == 6));
        previousButton.setVisible(sublist_no != 0);
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
    public void addCountry(Country country, int columnIndex, int rowIndex) {
        // Load the country view
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/passport-location.fxml"));
            AnchorPane countryPane = loader.load();

            // add task to the vBox container in taskPage
            passportGrid.add(countryPane, columnIndex, rowIndex);
            PassportLocationController controller = loader.getController();
            controller.setCountry(country);
            addCountryControllerMapping(country, controller);

            // Set the PassportViewController instance
            controller.setPassportViewController(this);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the next button click event.
     */
    @FXML
    public void next() {
        sublist_no++;
        initialize();
    }

    /**
     * Handles the previous button click event.
     */
    @FXML
    public void previous() {
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
    public static List<List<Country>> splitList(List<Country> countries, int sublistSize) {
        List<List<Country>> sublists = new ArrayList<>();
        int fromIndex = 0;

        while (fromIndex < countries.size()) {
            int toIndex = Math.min(fromIndex + sublistSize, countries.size());
            sublists.add(countries.subList(fromIndex, toIndex));
            fromIndex = toIndex;
        }

        return sublists;
    }

    /**
     * Orders the list of countries based on exploration status.
     *
     * @param countries        The list of countries.
     * @param user_countryList The list of user countries.
     * @return The sorted list of countries.
     */
    public static List<Country> orderList(List<Country> countries, List<Exploration> user_countryList) {
        // Create a map from countryID to Country
        Map<Integer, Country> countryMap = new HashMap<>();
        for (Country country : countries) {
            countryMap.put(country.getCountryID(), country);
        }

        // Iterate over the user_countryList and update corresponding Country objects
        for (Exploration userCountry : user_countryList) {
            int countryID = userCountry.getCountryID();
            Country country = countryMap.get(countryID);
            if (country != null) {
                country.setCurrent_loc(userCountry.getStatus().equals("Exploring"));
                country.setLocked(userCountry.isLocked());
            }
        }

        // Sort the list based on current location, then unlocked, then locked
        return countries.stream().sorted(Comparator.comparing(Country::isCurrent_loc).reversed().thenComparing(Country::isLocked)).collect(Collectors.toList());
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
     */
    @FXML
    public void unselect() {
        if (selectedLocationController != null) {
            selectedLocationController.removeBorder();
        }
        selectedCountry = null;
        unselectViewVisibility();
    }

    /**
     * Handles the view button click event.
     */
    @FXML
    public void view() {
        SelectedCountry.setSelectedCountry(selectedCountry);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/country-view.fxml"));
            AnchorPane countryView = loader.load();
            rootAnchorPane.getChildren().setAll(countryView);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the visibility of unselect and view buttons based on the selected country.
     */
    public void unselectViewVisibility() {
        if (selectedCountry == null) {
            unselectButton.setVisible(false);
            viewButton.setVisible(false);
        } else {
            unselectButton.setVisible(true);
            viewButton.setVisible(true);
        }
    }

    /**
     * Adds a country and its associated controller to the map.
     *
     * @param country    The country.
     * @param controller The associated controller.
     */
    public void addCountryControllerMapping(Country country, PassportLocationController controller) {
        countryControllerMap.put(country, controller);
    }

    /**
     * Displays the passport grid based on the current sublist.
     *
     * @param sublist The index of the current sublist.
     */
    public void displayPassport(int sublist) {
        passportGrid.getChildren().clear();
        if (!countryList.isEmpty()) {
            int column_count = 0;
            int row_count = 0;

            for (Country country : splitCountries.get(sublist)) {
                addCountry(country, column_count, row_count);
                column_count++;

                if (column_count > 2) {
                    column_count = 0;
                    row_count++;
                }
            }
        }
    }
}

