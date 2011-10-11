package models;

import java.util.Calendar;
import java.util.Date;

import java.util.*;
import javax.persistence.*;

import org.joda.time.DateTime;
 
import play.data.validation.Check;
import play.data.validation.CheckWith;
import play.data.validation.InFuture;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.db.jpa.*;
/*  this is an Event class with event name, event start and end date, along with id 
 * a constructor method will set a new event and getter functions are there to retrive the event attributes.
 * to check event at particular day defined a function which checkes if startEventDate is < date current time to be checked 
 * and same for end Date
 * author simpal kumar
 * */
@Entity
public class Event extends Model{
	@Required(message="event name is required")
	public String eventName;
	
	@Required(message="start date is required")
	@InFuture Date startEventDate;
	
	@Required(message="end date is required")
	@InFuture Date endEventDate;
	public static long eventCount=1;
	
	public long event_id;
	
	public boolean visible;
	
	public Event(String name,Date startDate, Date endDate, boolean visi){
		super();
		Calendar currentDate = Calendar.getInstance();
		eventName=name;
		startEventDate=startDate;
		endEventDate=endDate;
		Validation.future(currentDate.getTime().toString(), startEventDate, endEventDate);
		event_id=eventCount;
		visible =visi;
		Event.eventCount++;
	}
	public boolean isPublic(){
		return visible;
	}
	
	public String getName(){
		return eventName;
	}
	public void setName(String name){
		eventName = name;
	}
	public Date getStartEveDate(){
		return startEventDate;
	}
	public void setStartEveDate(Date start){
		startEventDate=start;
	}
	public Date getEndEveDate(){
		return endEventDate;
	}
	public void setEndEveDate(Date end){
		endEventDate = end;
	}
	public Long getId() {
		return id;
	}
	public boolean isAtDay(Date day){
		int factor = 1000*60*60*24;
		return (this.startEventDate.getTime()/factor <= day.getTime()/factor && day.getTime()/factor <= this.endEventDate.getTime()/factor);
	}
	
}
