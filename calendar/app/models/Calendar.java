package models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

import java.util.*;
import javax.persistence.*;

import org.joda.time.DateTime;
 
import play.data.validation.Check;
import play.data.validation.CheckWith;
import play.data.validation.Required;
import play.db.jpa.*;

@Entity
public class Calendar extends Model{
	 @Required 
	 public String calName;
	
	 @ManyToOne
	 @Required
     public User ownerCalendar;
	 
	
	 @OneToMany
	 @Required
     public List<Event> calEvents;
    
     public Calendar(String name,User username){
    	 super();
    	 calName=name;
    	 ownerCalendar = username;
    	 calEvents = new LinkedList<Event>();
     }
     
     public String getCalName(){
    	 return calName;
     }
     
     public User calOwner(){
    	 return ownerCalendar;
     }
     
     public void addEvent(String eveName, Date eveStartDate, Date eveEndDate,boolean visi){
    	 calEvents.add(new Event(eveName,eveStartDate,eveEndDate,visi));
     }
     public void addEvent(Event event){
 		calEvents.add(event);
 	}
     public List<Event> publicEventsOnDay(User userCal, Date day){
    	 List<Event> todayEvent = new LinkedList<Event>();
 		for(Event event: calEvents){
 			if(event.isAtDay(day) && (event.isPublic() || this.ownerCalendar.getUserName().equals(userCal.getUserName())))
 				todayEvent.add(event);
 		}
 		return todayEvent;
     }
    
     public List<Event> myEventsOnDay(User userCal, Date day){
    	 List<Event> todayEvent = new LinkedList<Event>();
 		for(Event event: calEvents){
 			if(event.isAtDay(day) && userCal.equals(ownerCalendar) && !(event.isPublic()))
 				todayEvent.add(event);
 		}
 		return todayEvent;
     }
     public List<Event> myEventsAfterDay(User userCal, Date day){
    	 List<Event> todayEvent = new LinkedList<Event>();
 		for(Event event: calEvents){
 			if(event.startEventDate.after(day) && userCal.equals(ownerCalendar)  && !(event.isPublic()))
 				todayEvent.add(event);
 		}
 		return todayEvent;
     }
     public List<Event> allEventsAfterDay(Date day){
    	 List<Event> todayEvent = new LinkedList<Event>();
 		for(Event event: calEvents){
 			if(event.startEventDate.after(day))
 				todayEvent.add(event);
 		}
 		return todayEvent;
     }
    public List<Event> allEventsCalendar(){
    	return calEvents;
    }
     public long totalEvents(){
    	 return calEvents.size();
     }
}
