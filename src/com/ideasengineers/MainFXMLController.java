/* Ideas Engineers
 *  All rights by Marc Zicchino
 */
package com.ideasengineers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

/**
 *
 * @author Marc Zicchino
 */
public class MainFXMLController implements Initializable {
    
    

    @FXML private TableColumn<?, ?> priceRow;
    @FXML private Label bankField;
    @FXML private TableColumn<?, ?> nameMarketRow;
    @FXML private TableView<?> marketTable;
    @FXML private Label regionField;
    @FXML private Label gunsField;
    @FXML private Label debtField;
    @FXML private TextArea logField;
    @FXML private TableView<?> pocketTable;
    @FXML private TableColumn<?, ?> countRow;
    @FXML private TableColumn<?, ?> namePocketRow;
    @FXML private Label spaceField;
    @FXML private Label healthField;
    @FXML private Label cashField;
    @FXML private Label bitchesField;

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
}
