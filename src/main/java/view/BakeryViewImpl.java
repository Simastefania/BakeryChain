package view;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.entity.Bakery;
import presenter.BakeryPresenter;
import presenter.BakeryView;

import java.util.List;

public class BakeryViewImpl extends VBox implements BakeryView {

    private TableView<Bakery> table = new TableView<>();

    private TextField nameField = new TextField();
    private TextField addressField = new TextField();

    private Button addButton = new Button("Add");
    private Button deleteButton = new Button("Delete");

    private BakeryPresenter presenter;

    public BakeryViewImpl() {
        initUI();
        this.presenter = new BakeryPresenter(this);
        presenter.loadBakeries();
    }


    private void initUI() {

        nameField.setPromptText("Name");
        addressField.setPromptText("Address");

        addButton.setOnAction(e ->
                presenter.handleAddBakery(
                        nameField.getText(),
                        addressField.getText()
                )
        );

        deleteButton.setOnAction(e -> {
            Bakery selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                presenter.handleDeleteBakery(selected.getId());
            }
        });

        getChildren().addAll(
                table,
                nameField,
                addressField,
                addButton,
                deleteButton
        );
    }

    @Override
    public void showBakeries(List<Bakery> bakeries) {
        table.getItems().setAll(bakeries);
    }

    @Override
    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }
}