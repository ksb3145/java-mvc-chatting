package util;

import controller.ChatController;
import controller.IController;
import controller.LoginController;
import controller.ViewController;
import dto.ModelAndView;

public class HandlerMapping {
    private HandlerMapping(){}
    public static IController getController(ModelAndView modelAndView) {
    	String controller = GetUrlFirstPattern.getStringPattern(modelAndView);
    	if(controller.equals("/index")) return ViewController.getInstance();
    	else if(controller.equals("/view")) return ViewController.getInstance();
    	else if(controller.equals("/chat")) return ChatController.getInstance();
    	else if(controller.equals("/login")) return LoginController.getInstance();
    	else return null;
    }
}