package sample;

import com.gtranslate.Language;
import com.gtranslate.Translator;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
    public void translate(ActionEvent actionEvent) throws IOException {

        // INSERT YOU URL HERE
        String urlStr = "https://script.google.com/macros/s/AKfycby4pL1DmY4fHsw_aWNBHpj_EYsmWp87ufzvV12tKlRrLpypR48/exec" +
                "?q=" + URLEncoder.encode(getTextToTranslate(), StandardCharsets.UTF_8) +
                "&target=" + "hu" +
                "&source=" + "en";
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String trasnlatedText = response.toString();
        setTranslatedText(trasnlatedText);
    }
}
