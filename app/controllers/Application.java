package controllers;

import models.User;
import models.Stock;
import models.Lookup;
import play.mvc.*;
import play.data.Form;
import java.util.List;
import play.db.ebean.Model;
import play.data.DynamicForm;
import views.html.*;

import javax.servlet.http.HttpSession;

import static play.libs.Json.toJson;

public class Application extends Controller {

    public static Result index(){
        return ok(index.render("Index"));
    }//end index method

    public static Result getLogin(){
        return ok(login.render("Login"));
    }//end getLogingPage method

    public static Result login(){
        DynamicForm form = Form.form().bindFromRequest();
        String username = form.get("username");
        String password = form.get("password");

        User user = (User)new Model.Finder(String.class, User.class).byId(username);

        //DEBUGGING
//        System.out.println("username: " + username);
//        System.out.println("password: " + password);
        if(user!= null) {
            //System.out.println("user.password: " + user.password);
            //System.out.println("user.username: " + user.username);
        }//end if statement

        if((user != null) && (username.equals(user.username)) && (password.equals(user.password))){
            //create a session
            //display message explaining that user is logged in
            //go to profile
            session().clear();
            session("username", username);

            //DEBUGGING
            return ok(loginMessage.render("Login"));
        }//end if statement

        else{
            //display error message

            //DEBUGGING
            return badRequest(login.render("Login"));
        }//end else statement
    }//end login method

    public static Result getLogout(){
        return ok(logout.render("Logout"));
    }//end getLogout method

    public static Result logout(){
        session().clear();
        return ok(logoutMessage.render("Logout"));
    }//end logout method

    public static Result getRegister(){
        return ok(register.render("Register"));
    }//end getRegisterPage method

    public static Result addUser(){
        DynamicForm form = Form.form().bindFromRequest();
        Form userForm = Form.form(User.class).bindFromRequest();
        boolean exists = false;

        if(userForm.hasErrors()){
            return badRequest(register.render("Register"));
        }//end if statement

        else {
            User user = (User)userForm.get();
            User userCheck = (User) new Model.Finder(String.class, User.class).byId(user.username);
            if (userCheck != null) {
                exists = true;
            }//end if statement

            //DEBUGGING
//            System.out.println("form.get(\"username\"): " + form.get("username"));
//            System.out.println("form.get(\"password\"): " + form.get("password"));
//            System.out.println("form.get(\"confirm\"): " + form.get("confirm"));
//            System.out.println("user.username: " + user.username);
//            System.out.println("user.username: " + user.password);

            if (form.get("password").equals(form.get("confirm")) && (form.get("username") != null) && (form.get("password") != null) && !exists) {
                session().clear();
                session().put("username", user.username);
                user.save();
                return ok(profile.render("Profile"));
            }//end if statement

            else {
                return badRequest(register.render("Register"));
            }//end else statement
        }//end else statement
    }//end addUser method

    public static Result getUsers(){
        List<String> users = new Model.Finder(String.class, User.class).findIds();
        return ok(toJson(users));
    }//end getUsers method

    public static Result getProfile(){

        if(session().get("username") != null){
            return ok(profile.render("Profile"));
        }//end if statement

        else{
            return badRequest(login.render("Login"));
        }//end else statement
    }//end getRegisterPage method

    public static Result lookup(){

        if(!session().isEmpty()){
            DynamicForm form = Form.form().bindFromRequest();
            String ticker = form.get("stockTicker");
            ticker = ticker.trim().toUpperCase();
            StockController stockController = new StockController();
            Stock stock = stockController.getStock(ticker);
            Lookup lookup = new Lookup();

            lookup.dateStamp = stock.getDateStamp();
            lookup.stockTicker = stock.getTicker();
            lookup.stockPrice = stock.getPrice();
            lookup.username = session().get("username");
            lookup.save();

            return ok(lookupMessage.render("Profile", lookup.stockTicker, lookup.stockPrice));
        }//end if statement

        else{
            return badRequest(login.render("Login"));
        }//end else statement
    }//end lookup method

    public static Result getLookups(String username, String password){
        if(username == null || password == null || username.equals("") || password == ""){
            return badRequest(lookupsError.render("Index"));
        }//end if statement

        //if value is entered.
        else{
            User user = (User)new Model.Finder(String.class, User.class).byId(username);

            //if passwords match
            if(user != null && password.equals(user.password)){
                List<String> lookups = new Model.Finder(String.class, Lookup.class).where().ilike("username", username).findList();
                return ok(toJson(lookups));
            }//end if statement

            else{
                return badRequest(lookupsError.render("Index"));
            }//end else statement
        }//end else statement
    }//end getLookups method
}//end Application class


