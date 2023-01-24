package main.java.es.unex.cum.mdp.ef3.controller;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;

public class MultiLineCell<T> extends TableCell<T, String> {
    private final TextArea textArea;

    public MultiLineCell() {
        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setEditable(false);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        } else {
            textArea.setText(item);
            setGraphic(textArea);
        }
    }

    
}

