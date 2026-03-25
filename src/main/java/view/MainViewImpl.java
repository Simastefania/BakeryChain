package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import presenter.MainPresenter;
import presenter.MainView;

import java.io.FileInputStream;
import java.util.List;

public class MainViewImpl extends VBox implements MainView {

    private final MainPresenter presenter;

    private final ListView<String> bakeryList = new ListView<>();
    private final ListView<String> cakeList = new ListView<>();
    private final ListView<String> stockList = new ListView<>();

    private final TextField bakeryIdField = new TextField();
    private final TextField bakeryNameField = new TextField();
    private final TextField bakeryAddressField = new TextField();

    private final TextField cakeIdField = new TextField();
    private final TextField cakeNameField = new TextField();
    private final TextField cakePriceField = new TextField();
    private final TextField cakeImageField = new TextField();

    private final TextField cakeBakeryIdField = new TextField();
    private final TextField cakeQuantityField = new TextField();
    private final TextField cakeExpiryDateField = new TextField();
    private final TextField cakeAvailableField = new TextField();


    private final TextField filterBakeryIdField = new TextField();
    private final TextField searchCakeField = new TextField();

    private final ImageView cakeImageView = new ImageView();

    public MainViewImpl() {
        this.presenter = new MainPresenter(this);
        initUI();
        presenter.loadBakeries();
        presenter.loadCakesSorted();
    }

    private void initUI() {
        setSpacing(10);
        setPadding(new Insets(10));

        TabPane tabPane = new TabPane();

        Tab bakeryTab = new Tab("Bakeries");
        bakeryTab.setClosable(false);
        bakeryTab.setContent(createBakerySection());

        Tab cakeTab = new Tab("Cakes");
        cakeTab.setClosable(false);
        cakeTab.setContent(createCakeSection());

        Tab filterTab = new Tab("Filters");
        filterTab.setClosable(false);
        filterTab.setContent(createFilterSection());

        tabPane.getTabs().addAll(bakeryTab, cakeTab, filterTab);

        getChildren().add(tabPane);
    }

    private VBox createBakerySection() {
        bakeryIdField.setPromptText("Bakery id");
        bakeryNameField.setPromptText("Bakery name");
        bakeryAddressField.setPromptText("Bakery address");

        Button addButton = new Button("Add bakery");
        Button updateButton = new Button("Update bakery");
        Button deleteButton = new Button("Delete bakery");
        Button refreshButton = new Button("Refresh bakeries");

        addButton.setOnAction(e ->
                presenter.addBakery(
                        bakeryNameField.getText(),
                        bakeryAddressField.getText()
                )
        );

        updateButton.setOnAction(e ->
                presenter.updateBakery(
                        bakeryIdField.getText(),
                        bakeryNameField.getText(),
                        bakeryAddressField.getText()
                )
        );

        deleteButton.setOnAction(e ->
                presenter.deleteBakery(bakeryIdField.getText())
        );

        refreshButton.setOnAction(e -> presenter.loadBakeries());

        VBox box = new VBox(10,
                bakeryList,
                bakeryIdField,
                bakeryNameField,
                bakeryAddressField,
                addButton,
                updateButton,
                deleteButton,
                refreshButton
        );

        return box;
    }

    private VBox createCakeSection() {
        cakeIdField.setPromptText("Cake id");
        cakeNameField.setPromptText("Cake name");
        cakePriceField.setPromptText("Cake price");
        cakeImageField.setPromptText("Image path");
        searchCakeField.setPromptText("Search cake by name");
        cakeBakeryIdField.setPromptText("Bakery id");
        cakeQuantityField.setPromptText("Quantity");
        cakeExpiryDateField.setPromptText("yyyy-MM-dd");
        cakeAvailableField.setPromptText("true / false");

        cakeImageView.setFitWidth(200);
        cakeImageView.setFitHeight(200);
        cakeImageView.setPreserveRatio(true);

        cakeList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                String[] parts = newVal.split("\\|");
                if (parts.length >= 4) {
                    cakeIdField.setText(parts[0].trim());
                    cakeNameField.setText(parts[1].trim());
                    cakePriceField.setText(parts[2].trim());
                    cakeImageField.setText(parts[3].trim());
                    loadImage(parts[3].trim());
                }
            }
        });

        Button addButton = new Button("Add cake");
        Button updateButton = new Button("Update cake");
        Button deleteButton = new Button("Delete cake");
        Button refreshButton = new Button("Show all cakes");
        Button searchButton = new Button("Search");

        addButton.setOnAction(e ->
                presenter.addCakeWithStock(
                        cakeNameField.getText(),
                        cakePriceField.getText(),
                        cakeImageField.getText(),
                        cakeBakeryIdField.getText(),
                        cakeQuantityField.getText(),
                        cakeExpiryDateField.getText(),
                        cakeAvailableField.getText()
                )
        );

        updateButton.setOnAction(e ->
                presenter.updateCake(
                        cakeIdField.getText(),
                        cakeNameField.getText(),
                        cakePriceField.getText(),
                        cakeImageField.getText()
                )
        );

        deleteButton.setOnAction(e ->
                presenter.deleteCake(cakeIdField.getText())
        );

        refreshButton.setOnAction(e -> presenter.loadCakesSorted());

        searchButton.setOnAction(e ->
                presenter.searchCakeByName(searchCakeField.getText())
        );

        VBox box = new VBox(2,
                cakeList,
                cakeImageView,
                cakeIdField,
                cakePriceField,
                cakeNameField,
                cakeImageField,
                cakeBakeryIdField,
                cakeQuantityField,
                cakeExpiryDateField,
                cakeAvailableField,
                addButton,
                updateButton,
                deleteButton,
                refreshButton,
                searchCakeField,
                searchButton
        );

        return box;
    }

    private VBox createFilterSection() {
        filterBakeryIdField.setPromptText("Bakery id");

        Button availableButton = new Button("Filter available cakes");
        Button validButton = new Button("Filter valid cakes");

        availableButton.setOnAction(e ->
                presenter.filterAvailableCakesByBakery(filterBakeryIdField.getText())
        );

        validButton.setOnAction(e ->
                presenter.filterValidCakesByBakery(filterBakeryIdField.getText())
        );

        VBox box = new VBox(10,
                filterBakeryIdField,
                availableButton,
                validButton,
                stockList
        );

        return box;
    }

    private void loadImage(String path) {
        try {
            Image image = new Image(new FileInputStream(path));
            cakeImageView.setImage(image);
        } catch (Exception e) {
            cakeImageView.setImage(null);
        }
    }

    @Override
    public void showBakeries(List<String> bakeries) {
        bakeryList.getItems().setAll(bakeries);
    }

    @Override
    public void showCakes(List<String> cakes) {
        cakeList.getItems().setAll(cakes);
    }

    @Override
    public void showStocks(List<String> stocks) {
        stockList.getItems().setAll(stocks);
    }

    @Override
    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.show();
    }

    @Override
    public void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information");
        alert.setContentText(message);
        alert.show();
    }
}