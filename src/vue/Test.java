package vue;

import controleur.ControleurVue;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import modele.ModeleBateau;

public class Test extends Application {


    private modele.ModeleBateau bateau;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        TableView table = new TableView();
        Scene scene = new Scene(new Group());
        stage.setTitle("Plaisanciers");
        table.resize(800,400);

        final Label label = new Label("Liste des navires");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn nameCol = new TableColumn("Nom");
        TableColumn marqueCol = new TableColumn("Marque");
        TableColumn modeleCol = new TableColumn("Modele");
        TableColumn anneeCol = new TableColumn("Annee");
        TableColumn longueurCol = new TableColumn("Longueur");
        TableColumn largeurCol = new TableColumn("Largeur");
        TableColumn actionCol = new TableColumn("Action");

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("nom")
        );
        marqueCol.setCellValueFactory(
                new PropertyValueFactory<>("marque")
        );
        modeleCol.setCellValueFactory(
                new PropertyValueFactory<>("modele")
        );
        anneeCol.setCellValueFactory(
                new PropertyValueFactory<>("annee")
        );
        longueurCol.setCellValueFactory(
                new PropertyValueFactory<>("longueur")
        );
        largeurCol.setCellValueFactory(
                new PropertyValueFactory<>("largeur")
        );
        actionCol.setCellValueFactory(
                new PropertyValueFactory<>("DUMMY")
        );

        Callback<TableColumn<ModeleBateau, String>, TableCell<ModeleBateau, String>> cellFactory
                = //
                new Callback<TableColumn<ModeleBateau, String>, TableCell<ModeleBateau, String>>() {
                    @Override
                    public TableCell call(final TableColumn<ModeleBateau, String> param) {
                        final TableCell<ModeleBateau, String> cell = new TableCell<ModeleBateau, String>() {

                            final Button btn = new Button("Ajouter");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        //ControleurVue.getInstance().actionAjouterItem();
                                         bateau = getTableView().getItems().get(getIndex());
                                        System.out.println(bateau.toString());
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        actionCol.setCellFactory(cellFactory);


        table.setItems(donnees.BateauDAO.getInstance().recupererListeBateaux());
        //table.getChildrenUnmodifiable().add(btnActionModifier);

        table.getColumns().addAll(nameCol, marqueCol, modeleCol, anneeCol, longueurCol, largeurCol, actionCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();




    }
}