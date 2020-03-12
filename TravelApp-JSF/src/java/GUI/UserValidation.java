/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import logic.User;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;
import logic.UserManager;

/**
 *
 * @author User
 */
@FacesValidator(value = UserValidation.VALIDATOR_ID)
public class UserValidation {
      public static final String VALIDATOR_ID = "de.hsm.User";
      
    private UserManager userManager = new UserManager(); 
    
    public void validate(FacesContext ctx, UIComponent component, User user)
            throws ValidatorException {
        if (userManager.loginUser(user.getUserName(), user.getPassword()) == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                         "Your username or password is wrong", 
                         "Your username or password is wrong");
            throw new ValidatorException(msg);
        }
    
    }
}