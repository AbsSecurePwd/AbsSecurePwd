/**
 * 
 */
package itapp.turbolaundry.dataservices;

import itapp.turbolaundry.entities.*;
import itapp.turbolaundry.entities.calendar.Day;
import itapp.turbolaundry.entities.calendar.Window;
import itapp.turbolaundry.entities.interfaces.IBaseEntity;
import itapp.turbolaundry.model.calendar.DateUtility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sergey
 * 
 */
@Transactional
public class DataService {

	// Session Factory will be injected by Spring
	//
	
	public DataService () {
	}
	
	public DataService(SessionFactory sf) {
		this.sessionFactory = sf;
		this.sessionFactory.openSession();
	}
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// Current session
	public Session GetCurrentSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session.isConnected() && session.isOpen()) {
			return session;
		} else {
			return sessionFactory.openSession();
		}
	}
	
	private long getNewId(Class<?> c) {
		long newId = -1l;
		DetachedCriteria maxId = DetachedCriteria.forClass(c)
			    .setProjection( Projections.max("id") );
			@SuppressWarnings("unchecked")
			List<IBaseEntity> r = this.GetCurrentSession().createCriteria(c)
			    .add( Property.forName("id").eq(maxId) )
			    .list();
			if (r != null && !r.isEmpty()) {
				newId = r.get(0).getId() + 1;
			}
			
		return newId;
	}

	// Returns the list of num days that starts with date
	@SuppressWarnings("unchecked")
	public List<Day> getDayListByDate(Date date, int num, long userId) {
		try {
			LUser user = (LUser)this.GetCurrentSession().get(LUser.class, userId);
			long floorId = user.getRoom().getFloor().getId();
			Query q = this.GetCurrentSession().createQuery(
					"from Day where Date >= ? and FloorID = " + floorId +" order by Date asc");
			q.setDate(0, date);
			q.setMaxResults(num);
			List<Day> queryResult = q.list();
			for (Day d : queryResult) {
				d.getWindows();
				d.getDate();
				d.getFloor();
			}
			
			return queryResult;
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return null;
		} catch (HibernateException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Day> getDayListReport(Date from, Date to, long userId) {
		try {
			LUser user = (LUser)this.GetCurrentSession().get(LUser.class, userId);
			long floorId = user.getRoom().getFloor().getId();
			Query q = this.GetCurrentSession().createQuery(
					"from Day where Date >= ? and Date <= ? and FloorID = " + floorId +" order by Date asc");
			q.setDate(0, from);
			q.setDate(1, to);
			List<Day> queryResult = q.list();
			for (Day d : queryResult) {
				d.getWindows();
				d.getDate();
				d.getFloor();
			}
			
			return queryResult;
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return null;
		} catch (HibernateException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return null;
		}
	}
	
	// Returns the list of num days that starts with date
		@SuppressWarnings("unchecked")
		public List<Day> getDayListByDateFloor(Date date, int num, long floorId) {
			try {				
				Query q = this.GetCurrentSession().createQuery(
						"from Day where Date >= ? and FloorID = " + floorId +" order by Date asc");
				q.setDate(0, date);
				q.setMaxResults(num);
				List<Day> queryResult = q.list();
				for (Day d : queryResult) {
					d.getWindows();
					d.getDate();
					d.getFloor();
				}
				
				return queryResult;
			} catch (IllegalArgumentException e) {
				System.out.println("Exception: ");
				e.printStackTrace();
				return null;
			} catch (HibernateException e) {
				System.out.println("Exception: ");
				e.printStackTrace();
				return null;
			}
		}
		
				
// days from all floors, probably not needed
//	@SuppressWarnings("unchecked") 
//	public List<Day> getDayByDate(Date date) {
//		Query q = GetCurrentSession().createQuery("from Day where Date = ?");
//		q.setDate(0, date);
//		return q.list();
//	}
	
	public Day getDayByDate(Date date, long userId) {
		LUser user = (LUser) this.GetCurrentSession().get(LUser.class, userId);
		int floorNumber = user.getRoom().getFloor().getFloorNumber();
		return getDayByDate(date, floorNumber);
	}
	
	public Day getDayByDate(Date date, int floorNumber) {
		Day result = null;
		try {
			Query q = GetCurrentSession().createQuery("from Day where Date = ?");
			q.setDate(0, date);			
			@SuppressWarnings("unchecked")
			List<Day> days = (List<Day>)q.list();
			if (days != null && days.size() > 0) {
				for(Day day : days) {
					int currentFloorNumber = day.getFloor().getFloorNumber();
					if (currentFloorNumber == floorNumber) {
						result = day;
						result.getFloor();
						result.getWindows();
						break;
					}
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return null;
		} catch (HibernateException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return null;
		}
		return result;
	}	

	public Day getDayByID(Long DayId) {
		try {
			Day day = (Day) this.GetCurrentSession().get(Day.class, DayId);
			return day;
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return null;
		} catch (HibernateException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return null;
		}

	}
	
	// TODO: if window is not booked or locked should be checked in
	// SQL!!!
	public boolean bookWindow(long winId, long userId) {
		boolean result = false;
		Window window = (Window) this.GetCurrentSession().get(Window.class, winId);
		LUser user = (LUser) this.GetCurrentSession().get(LUser.class, userId);
		if (window != null && user != null) {
			window.setUser(user);
			try {
				this.GetCurrentSession().saveOrUpdate(window);			
				result = true;
			} catch (IllegalArgumentException e) {
				System.out.println("Exception: ");
				e.printStackTrace();
				return false;
			} catch (HibernateException e) {
				System.out.println("Exception: ");
				e.printStackTrace();
				return false;
			}
		}
		return result;
	}
	
	public Date getWindowDate(long winId) {		
		Window window = (Window) this.GetCurrentSession().get(Window.class, winId);		
		if (window != null) {
			return window.getDay().getDate();
		}
		return null;
	}
	
	public long getWindowUserId(long winId) {		
		Window window = (Window) this.GetCurrentSession().get(Window.class, winId);		
		if (window != null) {
			return window.getUser().getId();
		}
		return -1;
	}

	public List<Window> getWindowsByMonth(Date date) {
		DateTime startDate = new DateTime(date);
		DateTime endDate = startDate.plusMonths(1).minusDays(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Window> windows = new ArrayList<Window>();
		try {
			Query q = this.GetCurrentSession().createQuery(
					"from Day where Date >= :start and Date <= :end");

			q.setParameter("start", sdf.format(startDate.toDate()));
			q.setParameter("end", sdf.format(endDate.toDate()));
			@SuppressWarnings("unchecked")
			List<Day> days = (List<Day>)q.list();
			for (Day day : days) {
				windows.addAll(day.getWindows());
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
		} catch (HibernateException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
		}		
		
		return windows;
	}
	
	// TODO: if window is not booked or locked should be checked in
	// SQL!!!
	public boolean bookWindow(Date date, int winNumber, long userId) {
		Boolean result = false;
		try {
			LUser user = (LUser) this.GetCurrentSession().get(LUser.class, userId);			
			if (user != null) {
				int floorNumber = user.getRoom().getFloor().getFloorNumber();
				Day day = getDayByDate(date, floorNumber);
				if(day == null){
					return false;
				}
				Window window = GetWindowByDate(day, winNumber);	
				if(window != null){
					window.setUser(user);
					GetCurrentSession().update(window);
					result = true;
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return false;
		} catch (HibernateException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return false;
		}		
		return result;
	}

	public Window GetWindowByDate(Day day, int winNumber) {
		Query q = this.GetCurrentSession().createQuery(
				"from Window where DayID = " + day.getId()
						+ " and WindowNumber = " + winNumber);
		@SuppressWarnings("unchecked")
		List<Window> windows = q.list();
		if (windows != null && !windows.isEmpty()) {
			return (Window) windows.get(0);
		} else {
			return null;
		}
	}
	
	public boolean cancelWindow(Date date, int winNumber, long userId) {
		//TODO money should be refunded		
		Boolean result = false;
		try {
			LUser user = (LUser) this.GetCurrentSession().get(LUser.class, userId);			
			if (user != null) {
				int floorNumber = user.getRoom().getFloor().getFloorNumber();
				Day day = getDayByDate(date, floorNumber);
				if(day == null){
					return false;
				}
				Window window = GetWindowByDate(day, winNumber);	
				if(window != null){
					window.setUser(null);
					GetCurrentSession().update(window);
					result = true;
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return false;
		} catch (HibernateException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return false;
		}		
		return result;		
	}
	
	public boolean cancelWindow(long winId) {
		boolean result = false;
		Window window = (Window) this.GetCurrentSession().get(Window.class, winId);		
		if (window != null ) {
			window.setUser(null);
			try {
				this.GetCurrentSession().saveOrUpdate(window);			
				result = true;
			} catch (IllegalArgumentException e) {
				System.out.println("Exception: ");
				e.printStackTrace();
				return false;
			} catch (HibernateException e) {
				System.out.println("Exception: ");
				e.printStackTrace();
				return false;
			}
		}
		return result;
	}	
	
	public boolean lockWindow(Date date, int winNumber, long userId) {
		Boolean result = false;
		try {
			LUser user = (LUser) this.GetCurrentSession().get(LUser.class, userId);			
			if (user != null) {
				int floorNumber = user.getRoom().getFloor().getFloorNumber();
				Day day = getDayByDate(date, floorNumber);
				if(day == null){
					return false;
				}
				Window window = GetWindowByDate(day, winNumber);	
				if(window != null){
					window.setIsLocked(true);
					GetCurrentSession().update(window);
					result = true;
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return false;
		} catch (HibernateException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return false;
		}		
		return result;
	}
	
	public boolean lockWindow(long winId) {
		boolean result = false;
		Window window = (Window) this.GetCurrentSession().get(Window.class, winId);
		if (window != null) {
			window.setIsLocked(true);
			try {
				this.GetCurrentSession().saveOrUpdate(window);			
				result = true;
			} catch (IllegalArgumentException e) {
				System.out.println("Exception: ");
				e.printStackTrace();
				return false;
			} catch (HibernateException e) {
				System.out.println("Exception: ");
				e.printStackTrace();
				return false;
			}
		}
		return result;
	}	

	public boolean unlockWindow(Date date, int winNumber, long userId) {
		Boolean result = false;
		try {
			LUser user = (LUser) this.GetCurrentSession().get(LUser.class, userId);			
			if (user != null) {
				int floorNumber = user.getRoom().getFloor().getFloorNumber();
				Day day = getDayByDate(date, floorNumber);
				if(day == null){
					return false;
				}
				Window window = GetWindowByDate(day, winNumber);	
				if(window != null){
					window.setIsLocked(false);
					GetCurrentSession().update(window);
					result = true;
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return false;
		} catch (HibernateException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return false;
		}		
		return result;
	}	
	
	public boolean unlockWindow(long winId) {
		boolean result = false;
		Window window = (Window) this.GetCurrentSession().get(Window.class, winId);
		if (window != null) {
			window.setIsLocked(false);
			try {
				this.GetCurrentSession().saveOrUpdate(window);			
				result = true;
			} catch (IllegalArgumentException e) {
				System.out.println("Exception: ");
				e.printStackTrace();
				return false;
			} catch (HibernateException e) {
				System.out.println("Exception: ");
				e.printStackTrace();
				return false;
			}
		}
		return result;
	}	

	public boolean lockAllByDateId(Long dateId) {
		boolean result = false;
		Day day = (Day) this.GetCurrentSession().get(Day.class, dateId);
		if (day != null) {
			day.setLocked(true);
			Set<Window> windows = day.getWindows();
			for (Window win : windows) {
				win.setIsLocked(true);
			}
		} else {
			return result;
		}		
		try {
			this.GetCurrentSession().saveOrUpdate(day);			
			result = true;
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return false;
		} catch (HibernateException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return false;
		}
		return result;
	}
	
	public boolean unlockAllByDateId(Long dateId) {
		boolean result = false;
		Day day = (Day) this.GetCurrentSession().get(Day.class, dateId);
		if (day != null) {
			day.setLocked(false);
			Set<Window> windows =  day.getWindows();
			for (Window win : windows) {
				win.setIsLocked(false);
			}
		} else {
			return result;
		}		
		try {
			this.GetCurrentSession().saveOrUpdate(day);			
			result = true;
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return false;
		} catch (HibernateException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return false;
		}
		return result;
	}

	public boolean cancelAllByDateId(Long dateId) {
		// TODO: Money should be refunded
		boolean result = false;
		Day day = (Day) this.GetCurrentSession().get(Day.class, dateId);
		if (day != null) {
			Set<Window> windows = day.getWindows();
			for (Window win : windows) {
				win.setUser(null);
			}
		} else {
			return result;
		}		
		try {
			this.GetCurrentSession().saveOrUpdate(day);			
			result = true;
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return false;
		} catch (HibernateException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return false;
		}
		return result;
	}	
	
	public boolean isAdmin(long userId) {
		boolean result = false;
		Query q = this.GetCurrentSession().createQuery(
				"from AdminUser where LUserID = " + userId);
		List<?> administrators = q.list();
		if (administrators != null && !administrators.isEmpty()) {
			result = true;
		}

		return result;
	}

	public boolean isSuper(long userId) {
		boolean result = false;
		Query q = this.GetCurrentSession().createQuery(
				"from Supervisor where LUserID = " + userId);
		List<?> supervisors = q.list();
		if (supervisors != null && !supervisors.isEmpty()) {
			result = true;
		}

		return result;
	}

	public boolean isUserBlocked(long userId) {
		Blocked blocked = (Blocked) this.GetCurrentSession().get(Blocked.class,
				userId);
		if (blocked != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public long getUserIdByLogin(String login) {
		Query q = this.GetCurrentSession().createQuery(
				"from LUser where Login = ?");
		q.setString(0, login);
		@SuppressWarnings("rawtypes")
		List results = q.list();
		if (results != null && !results.isEmpty()) {
			// LoggableUserType results.get(0)
			LUser user = (LUser) results.get(0);
			return user.getId();
		}

		return 0;
	}

	public String getUserPassByLogin(String login) {
		Query q = this.GetCurrentSession().createQuery(
				"from LUser where Login = ?");
		q.setString(0, login);
		@SuppressWarnings("rawtypes")
		List results = q.list();
		if (results != null && !results.isEmpty()) {
			// LoggableUserType results.get(0)
			LUser user = (LUser) results.get(0);
			return user.getPassword().trim(); // remove blanks
		}

		return "error";
	}
	
	public int getNumberOfBookedWindows(Date monday, Date sunday, long userId){
		int result = 0;
		try {
			LUser user = (LUser) this.GetCurrentSession().get(LUser.class, userId);
			if (user != null) {
				Set<Window> windows = user.getWindows();
				if (windows != null && !windows.isEmpty()) {
					for(Window w : windows) {
						Date currentDate = w.getDay().getDate();						
						if ((currentDate.before(sunday) && currentDate.after(monday))
									|| DateUtility.compareDates(currentDate,sunday)
									|| DateUtility.compareDates(currentDate,monday)) {
							result++;
							System.out.println("**"+w.getDay().getId());
						}
					}	
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			result = 0;
		} catch (HibernateException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			result = 0;
		}
		
		return result;
	}

	
	//-------------------------------------------
	//-----------Methods for user part-----------
	//-------------------------------------------
	
	public LUser getUserByLogin(String login) {
		Query q = this.GetCurrentSession().createQuery(
				"from LUser where Login = ?");
		q.setString(0, login);
		@SuppressWarnings("rawtypes")
		List results = q.list();
		if (results != null && !results.isEmpty()) {
			// LoggableUserType results.get(0)
			LUser user = (LUser) results.get(0);
			user.getAccount();
			user.getWindows();
			user.getRoom().getFloor();
			return user;
		}

		return null;
	}
	
	public boolean changePasswordByLogin(String login, String password){
		Boolean result = false;
		LUser user = this.getUserByLogin(login);
		if (user != null) {
			user.setPassword(password);
			this.GetCurrentSession().saveOrUpdate(user);
			result = true;
		}
		
		return result;
	}
	
	public List<LUser> getUsersByFloor(int floorNumber) {
		List<LUser> users = new ArrayList<LUser>();
		try {
			Query q = this.GetCurrentSession().createQuery("from Floor where FloorNo = ?");
			q.setInteger(0, floorNumber);
			List<?> floors = q.list();
			if (floors != null && !floors.isEmpty()) {
				Floor currentFloor = (Floor) floors.get(0);
				Set<Room> rooms = currentFloor.getRooms();
				for (Room room : rooms) {
					users.addAll(room.getLUsers());
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return users;
		} catch (HibernateException e) {
			System.out.println("Exception: ");
			e.printStackTrace();
			return users;
		}
		
		return users;
	}

	public List<Room> getAllRooms() {
		Query q = this.GetCurrentSession().createQuery("from Room");
		@SuppressWarnings("rawtypes")
		List results = q.list();
		if (results != null && !results.isEmpty()) {
			return results;
		}

		return null;
	}	
	
	public LUser getUserById(long userId) {
		return (LUser) this.GetCurrentSession().get(LUser.class, userId);
	}
	
	public List<Floor> getAllFloors() {
		Query q = this.GetCurrentSession().createQuery("from Floor");
		@SuppressWarnings("rawtypes")
		List results = q.list();
		if (results != null && !results.isEmpty()) {
			return results;
		}

		return null;
	}	
	
	/*
	 * WARNING !!!
	 * KF: this method contains wrong logic !
	 * Don't know why, but indentity columns + generated values in entities does not work at all for me
	 * I have made temporary workarround
	 * 1. Getting last element from table
	 * 2. adding 1 to new id
	 * 3. saving
	 * 
	 * It is very ugly, and only for a tomorrow.
	 * I'll refactor whole services soon.
	 * TODO: refactor services
	 */
	public boolean createOrEditUser(LUser user) {
		boolean result = false;
		long id = user.getId();
		if (id <= 0l) {
			id = this.getNewId(LUser.class);
			user.setId(id);
		}
		
		LUser currentUser = (LUser)this.GetCurrentSession().get(LUser.class, user.getId());
		if (currentUser != null) {
			this.CopyUserProperties(user, currentUser);
			this.GetCurrentSession().update(currentUser);
		} else {
			Account currentAccount = user.getAccount();
			
			if (currentAccount == null) {
				currentAccount = new Account(0, 0f, 0);
				user.setAccount(currentAccount);
			}
			
			long accountId = currentAccount.getId();
			if (accountId <= 0l) {
				accountId = this.getNewId(Account.class);
				currentAccount.setId(accountId);
			}
			
			try {
				this.GetCurrentSession().save(user);
				result = true;
			} catch (IllegalArgumentException e) {
				System.out.println("Exception: ");
				e.printStackTrace();
				result = false;
			} catch (HibernateException e) {
				System.out.println("Exception: ");
				e.printStackTrace();
				result = false;
			}
		}
		
		return result;
	}
	
	private void CopyUserProperties(LUser source, LUser target) {
		target.setAccount(source.getAccount());
		target.setBlocked(source.getBlocked());
		target.setFirstName(source.getFirstName());
		target.setId(source.getId());
		target.setLastName(source.getLastName());
		target.setLogin(source.getLogin());
		target.setPassword(source.getPassword());
		target.setRoom(source.getRoom());
		target.setWindows(source.getWindows());
	}

	public void makeSupervisor(String userLogin) {
		LUser user = this.getUserByLogin(userLogin);
		Supervisor supervisor = new Supervisor(0,user);
		this.GetCurrentSession().save(supervisor);
	}

	public void makeUser(String userLogin) {
		LUser user = this.getUserByLogin(userLogin);
		Supervisor sv = this.getSuperVisor(user.getId());
		if (sv!= null) {
			this.GetCurrentSession().delete(sv);
		}
	}

	public void blockUser(Long id) {
		
		Blocked blocked = new Blocked();
		blocked.setUserId(id);
		blocked.setDate(new Date());
		
		this.GetCurrentSession().saveOrUpdate(blocked);
		
	}

	public void removeBlocking(Long id) {
		Blocked blocked = this.getUserBlocked(id);
		if(blocked != null)
		{
			this.GetCurrentSession().delete(blocked);
		}
		
	}
	
	
	public Blocked getUserBlocked(long userId) {
		Blocked blocked = (Blocked) this.GetCurrentSession().get(Blocked.class, userId);
		return blocked;
	}
	
	public void removeSuperVisor(Long id) {
		Supervisor sv  = this. getSuperVisor(id);
		if(sv != null)
		{
			this.GetCurrentSession().delete(sv);
		}
		
	}
	
	public Supervisor getSuperVisor(long userId) {
		Query q = this.GetCurrentSession().createQuery("from Supervisor where LUserID = " + userId);
		List<?> supervisors = q.list();
		if (supervisors != null && !supervisors.isEmpty()) {
			Supervisor s = (Supervisor) supervisors.get(0);
			s.getUser();
			return s;
		}
		
		return null;
	}
	
	public void chargeAccount(long userID, float money)
	{
		LUser user = this.getUserById(userID);
		if(user != null)
		{
			Account account = user.getAccount();
			if(account != null)
			{
				float current = account.getMoney();
				current += money;
				account.setMoney(current);
			}
		}
	}
	
	public Float getAccount(long userID)
	{
		LUser user = this.getUserById(userID);
		if(user != null)
		{
			Account account = user.getAccount();
			if(account != null)
			{
				return account.getMoney();
				
			}
		}
		return null;
	}
	public Supervisor getSuperVisorByFloor(int floor)
	{
		List<LUser> users = this.getUsersByFloor(floor);
		for(LUser user : users)
		{
			if(this.isSuper(user.getId()))
			{
				Supervisor sv = getSuperVisor(user.getId());
				return sv;
			}
			
		}
		return null;
	}

	public void updateSV(Supervisor sv) {
		this.GetCurrentSession().saveOrUpdate(sv);
		
	}

	public Room getRoomById(long id) {
		return (Room) this.GetCurrentSession().get(Room.class, id);
	}
}
