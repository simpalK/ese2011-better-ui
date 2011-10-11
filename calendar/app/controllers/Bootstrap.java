package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import play.*;
import play.jobs.*;
import play.test.*;
 
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job {
 
    public void doJob() {

        if(Database.getUsers().isEmpty()) {
        	User user1;
        	Calendar calendar1;
        	
        	SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
    
        user1 = new User("simpal.kumar@gmail.com", "1");
    		user1.addCalendar("fallsem2011");
    		calendar1 = user1.getCalendarByName("fallsem2011");
    		try {
	    		calendar1.addEvent("lecture", df.parse("17.10.2011 10:00"), df.parse("17.10.2011 13:00"),true);
	    		calendar1.addEvent("seminar", df.parse("18.10.2011 13:00"), df.parse("18.10.2011 15:00"),false);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            Database.addUser(user1);
        }

    }
}