package com.jsfcourse.person;
import com.jsf.dao.UserDAO;
import com.jsf.entities.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.simplesecurity.RemoteClient;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
@Named
@RequestScoped
public class LoginBB {
    private static final String PAGE_MAIN = "/webapp/index.xhtml/index?faces-redirect=true";
    private static final String PAGE_LOGIN = "/webapp/login.xhtml/login?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;
    private String login;
    private String pass;
    private String pas="false";

    @Inject
    UserDAO userDAO;

    public LoginBB() {
    }
    public String getPas() {
        return this.pas;
    }

    public void setPas(String pass) {
        this.pas = pass;
    }
    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public String slowo() {
    	return "ups";
    }

    public String doLogin() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        User user = this.userDAO.getUserFromDatabase(this.login, this.pass);
        if (user == null) {
            ctx.addMessage((String)null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niepoprawny login lub hasło", (String)null));
            return PAGE_STAY_AT_THE_SAME;
        } else {
            RemoteClient<User> client = new RemoteClient<>();
            client.setDetails(user);
            List<String> roles = this.userDAO.getUserRolesFromDatabase(user);
            if (roles != null) {
                Iterator var6 = roles.iterator();

                while(var6.hasNext()) {
                    String role = (String)var6.next();
                    client.getRoles().add(role);
                }
            }

            HttpServletRequest request = (HttpServletRequest)ctx.getExternalContext().getRequest();
            client.store(request);
            setPas("true");
            if(roles.get(0) =="admin") {
            	 return "/personList.xhtml/personList?faces-redirect=true";
            }
            else
            return "/index.xhtml/index?faces-redirect=true";
        }
    }

    public String doLogout() {
        HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        setPas("false");
        return "/login.xhtml/login?faces-redirect=true";
    }
}
