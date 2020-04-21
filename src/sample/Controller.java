package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TextField textToTranslateTF;
    @FXML
    public TextField translatedTextTF;

    public String getTextToTranslate() {
        return textToTranslate.get();
    }

    public StringProperty textToTranslateProperty() {
        return textToTranslate;
    }

    public void setTextToTranslate(String textToTranslate) {
        this.textToTranslate.set(textToTranslate);
    }

    public String getTranslatedText() {
        return translatedText.get();
    }

    public StringProperty translatedTextProperty() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText.set(translatedText);
    }

    private final StringProperty textToTranslate = new SimpleStringProperty("default value");
    private final StringProperty translatedText = new SimpleStringProperty("default value");

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        textToTranslateTF.textProperty().bindBidirectional(textToTranslate());
        translatedTextTF.textProperty().bindBidirectional(translatedText());

        Bindings.bindBidirectional(textToTranslate(), textToTranslateTF.textProperty());
        Bindings.bindBidirectional(translatedText(), translatedTextTF.textProperty());

    }

    private Property<String> textToTranslate() {
        return textToTranslate;
    }

    private Property<String> translatedText() {
        return translatedText;
    }

    @FXML
    public void translate(ActionEvent actionEvent) {
        System.out.println(getTextToTranslate());
        setTranslatedText(getTextToTranslate());
    }
}
