package com.klaus.timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class JobTimer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {

	/*	Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {                          
                
                WorkerService worker=(WorkerService) MyBeansFactory.getBeans("scorewrworker");
                worker.start();
                
            }
        }, 1000, 5000);
        
        
        Timer timer1 = new Timer();
        timer1.scheduleAtFixedRate(new TimerTask() {
            public void run() {
            	WorkerService worker=(WorkerService) MyBeansFactory.getBeans("empwrworker");
                worker.start();
            }
        }, 6000, 5000);
        
        
        Timer timer2 = new Timer();
        timer2.scheduleAtFixedRate(new TimerTask() {
            public void run() {
            	WorkerService worker=(WorkerService) MyBeansFactory.getBeans("stuabilityworker");
                worker.start();
            }
        }, 6000, 5000);
        
		*/
	}

	
}
