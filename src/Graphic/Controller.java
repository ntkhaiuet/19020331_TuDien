package Graphic;

import CommandLine.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML TextField  targetSearch, targetAdd, targetEdit, targetRemove, soundAdd;
    @FXML TextArea explainAdd, explainRemove, explainEdit,explainSearch;
    @FXML
    ListView<String> listViewSearch = new ListView<String>();
    @FXML ListView<String> listViewAdd = new ListView<String>(), listViewRemove = new ListView<String>(), listViewEdit = new ListView<String>();
    @FXML
    Label  targetShow, warningAdd, warningRemove, warningEdit;
    @FXML
    Button buttonAdd, buttonRemove, buttonEdit;
    @FXML TabPane tabpane;

    private static DictionaryCommandline dic = new DictionaryCommandline();

    public void lookupSearch() {
        String target = targetSearch.getText();
        Word word = dic.dictionaryLookup(target);
        targetShow.setText(target);
        explainSearch.setText(word.toString());
    }

    public void lookupRemove() {
        String target = targetRemove.getText();
        Word word = dic.dictionaryLookup(target);
        explainRemove.setText(word.getWord_explain());
    }

    public void lookupEdit() {
        String target = targetEdit.getText();
        Word word = dic.dictionaryLookup(target);
        explainEdit.setText(word.getWord_explain());
    }

    public void searchHelper() {
        String target = targetSearch.getText();
        listViewSearch.getItems().clear();
        if (target.equals("")) return;
        ArrayList<String> list = dic.dictionarySearcher(target);
        ObservableList<String> data = FXCollections.observableArrayList(list);
        listViewSearch.getItems().addAll(data);
    }

    public void addHelper()
    {
        String target = targetAdd.getText();
        target.trim();
        listViewAdd.getItems().clear();
        if (target.equals("")) {
            warningAdd.setText("");
            return;
        }
        ArrayList<String> list = dic.dictionarySearcher(target);
        ObservableList<String> data = FXCollections.observableArrayList(list);
        listViewAdd.getItems().addAll(data);
        if (!list.isEmpty() && list.get(0).trim().equals(target)) warningAdd.setText("This word is already existed");
        else warningAdd.setText("This word can be added");
    }

    public void removeHelper() {
        String target = targetRemove.getText();
        target.trim();
        listViewRemove.getItems().clear();
        if (target.equals("")) {
            warningRemove.setText("");
            return;
        }
        ArrayList<String> list = dic.dictionarySearcher(target);
        ObservableList<String> data = FXCollections.observableArrayList(list);
        listViewRemove.getItems().addAll(data);
        if (list.isEmpty() || !list.get(0).trim().equals(target)) warningRemove.setText("This word is not already existed");
        else warningRemove.setText("This word can be removed");
    }

    public void editHelper()
    {
        String target = targetEdit.getText();
        target.trim();
        listViewEdit.getItems().clear();
        if (target.equals("")) {
            warningEdit.setText("");
            return;
        }
        ArrayList<String> list = dic.dictionarySearcher(target);
        ObservableList<String> data = FXCollections.observableArrayList(list);
        listViewEdit.getItems().addAll(data);
        if (list.isEmpty() || !list.get(0).trim().equals(target)) warningEdit.setText("This word is not already existed");
        else warningEdit.setText("This word can be edited");
    }

    public void AddWord() {
        String target = targetAdd.getText();
        String sound = soundAdd.getText();
        String explain = explainAdd.getText();
        warningAdd.setText(dic.addWord(target,sound,explain));
    }

    public void RemoveWord() {
        String target = targetRemove.getText();
        warningRemove.setText(dic.deleteWord(target));
        explainRemove.setText("");
    }

    public void EditWord() {
        String target = targetEdit.getText();
        String explain = explainEdit.getText();
        warningEdit.setText(dic.fixWord(target,explain));
        explainEdit.setText("");
    }

    public void ResetToDeFaultDictionary() {
        dic.resetToDefaultDictionary();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Reset to default Dictionary");
        alert.setHeaderText("Reset successfully");
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        targetSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    lookupSearch();
                }
                if (ke.getCode() == KeyCode.DOWN) {
                    listViewSearch.requestFocus();
                    listViewSearch.getSelectionModel().selectFirst();
                }
            }
        });
        listViewSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String target =  listViewSearch.getSelectionModel().getSelectedItem();
                targetSearch.setText(target);
                lookupSearch();
            }
        });
        listViewSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    String target =  listViewSearch.getSelectionModel().getSelectedItem();
                    targetSearch.setText(target);
                    lookupSearch();
                }
                if (ke.getCode() == KeyCode.UP){
                    if (listViewSearch.getSelectionModel().getSelectedIndex() == 0)
                    targetSearch.requestFocus();
                }
            }
        });
        targetAdd.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.DOWN){
                    listViewAdd.requestFocus();
                    listViewAdd.getSelectionModel().selectFirst();
                }
            }
        });
        listViewAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        String target = listViewAdd.getSelectionModel().getSelectedItem();
                        targetSearch.setText(target);
                        lookupSearch();
                        tabpane.getSelectionModel().select(0);
                    }
                }
            }
        });
        listViewAdd.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    String target =  listViewAdd.getSelectionModel().getSelectedItem();
                    targetAdd.setText(target);
                    lookupSearch();
                }
                if (ke.getCode() == KeyCode.UP) {
                    if (listViewAdd.getSelectionModel().getSelectedIndex() == 0)
                        targetAdd.requestFocus();

                }
            }
        });
        targetRemove.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    lookupRemove();
                }
                if (ke.getCode() == KeyCode.DOWN) {
                    listViewRemove.requestFocus();
                    listViewRemove.getSelectionModel().selectFirst();
                }
            }
        });
        listViewRemove.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                String target =  listViewRemove.getSelectionModel().getSelectedItem();
                targetRemove.setText(target);
                lookupRemove();
            }
        });
        listViewRemove.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    String target =  listViewRemove.getSelectionModel().getSelectedItem();
                    targetRemove.setText(target);
                    lookupRemove();
                }
                if (ke.getCode() == KeyCode.UP){
                    if (listViewRemove.getSelectionModel().getSelectedIndex() == 0)
                        targetRemove.requestFocus();
                }
            }
        });
        targetEdit.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    lookupEdit();
                }
                if (ke.getCode() == KeyCode.DOWN){
                    listViewEdit.requestFocus();
                    listViewEdit.getSelectionModel().selectFirst();
                }
            }
        });
        listViewEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String target =  listViewEdit.getSelectionModel().getSelectedItem();
                targetEdit.setText(target);
                lookupEdit();
            }
        });
        listViewEdit.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ENTER) {
                    String target =  listViewEdit.getSelectionModel().getSelectedItem();
                    targetEdit.setText(target);
                    lookupEdit();
                }
                if (ke.getCode() == KeyCode.UP) {
                    if (listViewEdit.getSelectionModel().getSelectedIndex() == 0)
                        targetEdit.requestFocus();
                }
            }
        });
        try {
            dic.insertFromDict();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static class XCell extends ListCell<String> {
        HBox hbox = new HBox();
        Label label = new Label("(empty)");
        Pane pane = new Pane();
        ToggleButton button = new ToggleButton();
        String lastItem;

        public XCell() {
            super();
            hbox.getChildren().addAll(label, pane, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            label.setMaxWidth(500);
            label.setMaxHeight(90);
            label.setWrapText(true);
            button.selectedProperty().addListener(((observable, oldValue, newValue) -> {
                String target = lastItem;
                Word word = dic.dictionaryLookup(target);
                dic.arr.remove(word);
                dic.arr.add(word);
            }));
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            if (empty) {
                lastItem = null;
                setGraphic(null);
            } else {
                lastItem = item;
                if (item!=null) {
                    Word word = dic.dictionaryLookup(item);
                    label.setText(word.toString2());
                }
                setGraphic(hbox);
            }
        }
    }
}
