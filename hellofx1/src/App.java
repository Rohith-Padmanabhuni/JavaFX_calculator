import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    private TextField displayField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sample_Calculator");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(50));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        

        displayField = new TextField();
        displayField.setEditable(true);
        displayField.setPrefColumnCount(10);
        displayField.setPrefSize(200,50);
        displayField.setStyle(
            "-fx-font-size: 20px;" +
            "-fx-alignment: CENTER-RIGHT;"+
            " -fx-background-radius: 10em;"
        );
        gridPane.add(displayField, 0, 0, 4, 1);

        String[][] buttonLabels = {
                {"7", "8", "9", "/"},
                {"4", "5", "6", "*"},
                {"1", "2", "3", "-"},
                {"0", "C", "=", "+"}
        };
        for (int i = 0; i < buttonLabels.length; i++) {
            for (int j = 0; j < buttonLabels[i].length; j++) {
                Button button = new Button(buttonLabels[i][j]);
                button.setPrefSize(70, 70);

                button.setStyle(
                    "-fx-background-radius: 50em; "+
                    "-fx-font-size: 20px;");

                gridPane.add(button, j, i + 1);
                button.setOnAction(event -> {
                    String buttonText = button.getText();
                    if (buttonText.equals("=")) {
                        calculate();
                    } 
                    else if(buttonText.equals("C")){
                        displayField.clear();
                    }
                    else {
                        displayField.appendText(buttonText);
                    }
                });
            }
            
        }
        

        Scene scene = new Scene(gridPane, 480, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    private void calculate() {
        String expression = displayField.getText();
        try {
            double result = evaluate(expression);
            displayField.setText(Double.toString(result));
        } catch (Exception e) {
            displayField.setText("Error");
        }
    }

    private double evaluate(String expression) {
    
        String[] tokens = expression.split("(?<=[-+*/])|(?=[-+*/])");
        double result = Double.parseDouble(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            double operand = Double.parseDouble(tokens[i + 1]);
            switch (operator) {
                case "+":
                    result += operand;
                    break;
                case "-":
                    result -= operand;
                    break;
                case "*":
                    result *= operand;
                    break;
                case "/":
                    result /= operand;
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
