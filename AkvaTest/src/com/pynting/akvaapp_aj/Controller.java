package com.pynting.akvaapp_aj;


public class Controller {
    
    private static volatile Controller instance;
    
    private Controller(){//constructor
	
    }
    
    public static Controller getInstance() {
        if (instance == null) {
            synchronized (Controller.class) {
                if (instance == null) {
                    instance = new Controller();
                }
            }
        }
        return instance;
    }

}
