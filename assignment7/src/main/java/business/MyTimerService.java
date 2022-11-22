package business;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

@Stateless
public class MyTimerService {
	
	public static final Logger logger = Logger.getLogger("business.MyTimerService");
	
	@Resource
	TimerService timerService;
    /**
     * Default constructor. 
     */
    public MyTimerService() {
    }
    
    public void setTimer(long interval) {
    	timerService.createTimer(interval, "My Timer");
    }
    
    @Timeout
    public void programmaticTimer(Timer timer) {
    	logger.info("@Timeout in the programmatic timer: " + new java.util.Date());
    }
	
	@SuppressWarnings("unused")
	@Schedule(second="*/1000000", minute="*", hour="0-23", dayOfWeek="Mon-Fri",
      dayOfMonth="*", month="*", year="*", info="MyTimer")
    private void scheduledTimeout(final Timer t) {
        System.out.println("@Schedule called at: " + new java.util.Date());
    }
}