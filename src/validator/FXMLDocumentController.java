/*
 * Lingwistyka Matematyczna Zadanie 5 - Analizator składniowy oparty na wyrażeniach regularnych.
 * Szymon Zawadzki 221515.
 */

package validator;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author Szaman
 */
public class FXMLDocumentController implements Initializable 
{
    //Pictures Stuff
    Image exit = new Image("validator/pictures/Exit.png");
    ImageView exitImage = new ImageView(exit);
    
    //FXML Stuff.
    @FXML
    private Button exitButton;
    
    @FXML
    private TextField userInputField;
    
    @FXML
    private Label validationResult;
    
    //AcceptButton
    @FXML
    void handleAccept()
    {
        String userInput = userInputField.getText();
        System.out.println("User Input: " + userInput);
        boolean isInputCorrect = validateInput(userInput);
        if(isInputCorrect)
        {
            validationResult.setText("Wyrażenie '" + userInput + "'\njest poprawne!");
            validationResult.setTextFill(Color.web("#00CC00"));
        }       
        else
        {
            if(userInput.length()==0)
            {
                validationResult.setText("Proszę podać wyrażenie!");
                validationResult.setTextFill(Color.web("#000000"));
            }
            else
            {
                validationResult.setText("Wyrażenie '" + userInput + "'\njest niepoprawne!");
                validationResult.setTextFill(Color.web("#FF0000"));
            }
        }
    }
    
    //Validation
    boolean validateInput(String input)
    {
        String errorMessage = "";
        Pattern pattern = Pattern.compile("([0-9]+(\\.[0-9]+)?([\\*\\/\\+\\/\\-][0-9]+(\\.[0-9]+)?)*;)+");
        Matcher matcher = pattern.matcher(input);
        boolean isInputCorrect = matcher.find();
        if(input.length() == 0)
        {
            errorMessage += "Brak wyrażenia" + "\n";
        }
        else
        {
            if(isInputCorrect == false)
            {
                errorMessage += "Wyrażenie jest niepoprawne!" + "\n";
            }
        }
        
        System.out.println(errorMessage);
        
        if(errorMessage.length() != 0) 
        {            
            return false;
        }
        else 
        {
            return true;
        }
    }
    
    //Exit Script.
    @FXML
    public void handleExit()
    {        
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", okButton, cancelButton);
        alert.setTitle("Wyjście");
        alert.setHeaderText(null);
        alert.setContentText("Na pewno?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == okButton)
        {
            System.exit(0);
        } 
        else 
        {
            alert.close();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //Exit initialization
        exitImage.setFitHeight(75);
        exitImage.setFitWidth(75);
        exitButton.setGraphic(exitImage);
        exitButton.setMaxHeight(75);
        exitButton.setMaxWidth(75);
        
        validationResult.setText("");
    }    
    
}
