package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private TextFlow resultDisplay;

    @FXML
    private ScrollPane scrollPane;

    public ResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String commandTyped, String feedbackToUser) {
        requireNonNull(feedbackToUser);

        // 1. Prepare and Add the text (keeping your formatting)
        String prefix = resultDisplay.getChildren().isEmpty() ? "$ " : "\n$ ";
        Text prompt = new Text(prefix);
        prompt.setStyle("-fx-fill: #00ff00; -fx-font-weight: bold; -fx-font-family: 'Consolas'; -fx-font-size: 16px;");

        Text command = new Text(commandTyped + "\n");
        command.setStyle("-fx-fill: #ffffff; -fx-font-family: 'Consolas'; -fx-font-size: 16px;");

        Text result = new Text(feedbackToUser + "\n");
        result.setStyle("-fx-fill: #00ff00; -fx-font-family: 'Consolas'; -fx-font-size: 16px;");

        resultDisplay.getChildren().addAll(prompt, command, result);

        // 2. THE SNAP FIX: Bind the scroll to the bottom
        // We tell the ScrollPane to move its vertical value to 1.0
        // the moment the TextFlow's height changes.
        resultDisplay.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0);
        });
    }
}
