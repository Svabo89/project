package edu.rit.edgeconverter.view;

import java.io.File;
import java.io.InputStream;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ConverterGUI {

  private final Stage stage;
  // Moved buttonDefineRelations to be a class-level variable
  Button buttonDefineRelations;
  Button buttonCreateDDL;

  // Constructor to create the ConverterGUI object
  public ConverterGUI(Stage primaryStage) {
    this.stage = primaryStage;
  }

  // This method is now a member of the ConverterGUI class, not inside start()
  public void setButtonEnabled(boolean isEnabled) {
    buttonDefineRelations.setDisable(!isEnabled);
  }

  public void setCreateDDLButtonEnabled(boolean isEnabled) {
    buttonCreateDDL.setDisable(!isEnabled);
  }

  // Start method to initialize the user interface
  public void start() {
    // Setting the window title
    stage.setTitle("EdgeConverter - G2");

    // Create the main container
    Pane root = new Pane();
    root.setPrefSize(900, 500);

    //     Load and set the background image
    InputStream is = getClass().getResourceAsStream("/style/img/test.jpg"); // Adjusted path
    if (is != null) {
      Image backgroundImage = new Image(is);
      BackgroundImage background = new BackgroundImage(
        backgroundImage,
        BackgroundRepeat.NO_REPEAT,
        BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT
      );
      root.setBackground(new Background(background));
    } else {
      System.err.println("Image not found");
    }

    // Create a menu bar
    MenuBar menuBar = new MenuBar();
    menuBar.setPrefWidth(902);

    // Create File menu with items
    Menu fileMenu = new Menu("File");
    MenuItem menuOpenEdgeFile = new MenuItem("Open Edge File");
    MenuItem menuOpenSaveFile = new MenuItem("Open Save File");
    MenuItem menuSave = new MenuItem("Save");
    MenuItem menuSaveAs = new MenuItem("Save as");
    MenuItem menuExit = new MenuItem("Exit");
    fileMenu
      .getItems()
      .addAll(
        menuOpenEdgeFile,
        menuOpenSaveFile,
        menuSave,
        menuSaveAs,
        menuExit
      );
    menuOpenEdgeFile.setOnAction(e -> {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Edge File");
    fileChooser.setInitialDirectory(new File("src/main/resources/resources")); // Set initial directory to the resources folder

    // Set extension filters
    FileChooser.ExtensionFilter edgFilter = new FileChooser.ExtensionFilter("EDG files (*.edg)", "*.edg");
    FileChooser.ExtensionFilter jsonFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
    FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
    fileChooser.getExtensionFilters().addAll(edgFilter, jsonFilter, xmlFilter);

    File selectedFile = fileChooser.showOpenDialog(stage);
    if (selectedFile != null) {
        // TODO: Parse the selected file and update the listViewTables
    }
});
    // Create Options menu with items
    Menu optionsMenu = new Menu("Options");
    MenuItem menuShowOutput = new MenuItem(
      "Show Output File Definition Location"
    );
    MenuItem menuShowDatabase = new MenuItem(
      "Show Database Products Available"
    );
    optionsMenu.getItems().addAll(menuShowOutput, menuShowDatabase);

    // Create Help menu with items
    Menu helpMenu = new Menu("Help");
    MenuItem menuAbout = new MenuItem("About");
    helpMenu.getItems().addAll(menuAbout);

    // Add menus to the menu bar
    menuBar.getMenus().addAll(fileMenu, optionsMenu, helpMenu);

    // Create list views for tables and fields
    ListView<String> listViewTables = new ListView<>();
    listViewTables.setLayoutX(14);
    listViewTables.setLayoutY(65);
    listViewTables.setPrefHeight(354);
    listViewTables.setPrefWidth(276);

    ListView<String> listViewFieldList = new ListView<>();
    listViewFieldList.setLayoutX(312);
    listViewFieldList.setLayoutY(65);
    listViewFieldList.setPrefHeight(354);
    listViewFieldList.setPrefWidth(276);

    // Create buttons for Define Relations and Create DDL
    buttonDefineRelations = new Button("Define Relations");
    buttonDefineRelations.setLayoutX(80);
    buttonDefineRelations.setLayoutY(444);
    buttonDefineRelations.setPrefHeight(20); // Set a smaller height
    buttonDefineRelations.setPrefWidth(150); // Set a smaller width
    buttonDefineRelations.setDisable(true);

    buttonCreateDDL = new Button("Create DDL");
    buttonCreateDDL.setLayoutX(369);
    buttonCreateDDL.setLayoutY(444);
    buttonCreateDDL.setPrefHeight(20); // Set a smaller height
    buttonCreateDDL.setPrefWidth(150); // Set a smaller width
    buttonCreateDDL.setDisable(true); // I

    // Labels for All Tables and Field List
    Label labelAllTables = new Label("All Tables");
    labelAllTables.setLayoutX(127);
    labelAllTables.setLayoutY(39);

    Label labelFieldList = new Label("Field List");
    labelFieldList.setLayoutX(428);
    labelFieldList.setLayoutY(39);

    // Radio buttons for data types
    RadioButton radioButtonVarchar = new RadioButton("Varchar");
    radioButtonVarchar.setLayoutX(618);
    radioButtonVarchar.setLayoutY(133);

    RadioButton radioButtonBoolean = new RadioButton("Boolean");
    radioButtonBoolean.setLayoutX(618);
    radioButtonBoolean.setLayoutY(206);

    RadioButton radioButtonInteger = new RadioButton("Integer");
    radioButtonInteger.setLayoutX(618);
    radioButtonInteger.setLayoutY(277);

    RadioButton radioButtonDouble = new RadioButton("Double");
    radioButtonDouble.setLayoutX(618);
    radioButtonDouble.setLayoutY(351);

    // Text fields for Varchar Length and Default Value
    TextField textFieldVarcharLength = new TextField();
    textFieldVarcharLength.setId("textFieldVarcharLength");
    textFieldVarcharLength.setLayoutX(717);
    textFieldVarcharLength.setLayoutY(65);
    textFieldVarcharLength.setPrefHeight(67);
    textFieldVarcharLength.setPrefWidth(171);
    textFieldVarcharLength.setStyle("-fx-alignment: center;");

    TextField textFieldDefaultValue = new TextField();
    textFieldDefaultValue.setId("textFieldDefaultValue");
    textFieldDefaultValue.setLayoutX(715);
    textFieldDefaultValue.setLayoutY(327);
    textFieldDefaultValue.setPrefHeight(67);
    textFieldDefaultValue.setPrefWidth(171);
    textFieldDefaultValue.setStyle("-fx-alignment: center;");

    // Buttons for setting Varchar Length and Default Value
    Button buttonSetVarcharLength = new Button("Set Varchar Length");
    buttonSetVarcharLength.setLayoutX(717);
    buttonSetVarcharLength.setLayoutY(132);
    buttonSetVarcharLength.setPrefHeight(25);
    buttonSetVarcharLength.setPrefWidth(171);

    Button buttonSetDefaultValue = new Button("Set Default Value");
    buttonSetDefaultValue.setLayoutX(715);
    buttonSetDefaultValue.setLayoutY(394);
    buttonSetDefaultValue.setPrefHeight(25);
    buttonSetDefaultValue.setPrefWidth(171);

    // Checkboxes for Primary Key and Disallow Null
    CheckBox checkBoxPrimaryKey = new CheckBox("Primary Key");
    checkBoxPrimaryKey.setLayoutX(755);
    checkBoxPrimaryKey.setLayoutY(207);

    CheckBox checkBoxDisallowNull = new CheckBox("Disallow Null");
    checkBoxDisallowNull.setLayoutX(755);
    checkBoxDisallowNull.setLayoutY(261);

    // Add all UI components to the root pane
    root
      .getChildren()
      .addAll(
        menuBar,
        listViewTables,
        listViewFieldList,
        buttonDefineRelations,
        buttonCreateDDL,
        labelAllTables,
        labelFieldList,
        radioButtonVarchar,
        radioButtonBoolean,
        radioButtonInteger,
        radioButtonDouble,
        textFieldVarcharLength,
        textFieldDefaultValue,
        buttonSetVarcharLength,
        buttonSetDefaultValue,
        checkBoxPrimaryKey,
        checkBoxDisallowNull
      );

    // Create and set the scene
    Scene scene = new Scene(root);
    //calling css file
    scene
      .getStylesheets()
      .add(getClass().getResource("../style/style.css").toExternalForm());
    // Apply the CSS class "title-bar" to the window title bar
    scene.getRoot().getStyleClass().add("title-bar");
    stage.setScene(scene);
    stage.show();
  }
}
