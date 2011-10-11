package models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.data.validation.Match;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model {
    @Required(message="user name is required")
    @Match("[A-Z][1-10]{@}")
	public String userName;
    
    @Required(message="password is required")
    public String password;
    
    @Required
    @OneToMany(cascade=CascadeType.ALL)
    public List<Calendar> userCalendars;
    
    public User(String name, String pwd){
    	super();
    	userName=name;
    	password=pwd;
    	userCalendars = new LinkedList<Calendar>();
    	
    }
    public boolean equals(User calUser){
    	return (calUser.userName.equals(this.userName));
    }
    public void addCalendar(String calName){
    	userCalendars.add(new Calendar(calName,this));
    }
    public Calendar getCalendarByName(String name){
		for(Calendar calendar: userCalendars)
			if(calendar.getCalName().equals(name))
				return calendar;
		return null;
	}

    public String getUserName() {
		return userName;
	}
	public void setUserName(String name) {
		this.userName = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String pwd) {
		this.password = pwd;
	}
	public List<Calendar> getUserCalendars() {
		return userCalendars;
	}
	public static User connect(String name) {
	    return find("byName", name).first();
	}
}
