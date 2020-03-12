/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import logic.User;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import logic.UserManager;

/**
 *
 * @author User
 */
@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {


    private User user = new User();
    private UserManager userManager = new UserManager();
    
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    private List<SelectItem> genderTypes;

    public String getSelectedGender() {
        return user.getGender() != null ? getGenderLabel(user.getGender()) : null;
    }

    public String login() {
        if (userManager.loginUser(user.getUserName(), user.getPassword()) == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username or password is wrong", "Username or password is wrong");
            FacesContext.getCurrentInstance().addMessage("login", msg);
            
            return "login.xhtml";
        }
        userManager.loginUser(user.getUserName(), user.getPassword());
        return "dashboard.xhtml";
    }
    
    public String register() {
        return "register.xhtml";
    }
    
    public String submit() {
        
        if(!userManager.checkEmail(user.getEmail())){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email is already in use", "Email is already in use");
            FacesContext.getCurrentInstance().addMessage("error email", msg);
            
            return "register.xhtml";
            
        }
        userManager.registerUser(user);
        return "index.xhtml";
    }
    
    private String getGenderLabel(char gender) {
        if (gender == 'f')
            return "Female";
        return "Male";
    }  
    
     

}