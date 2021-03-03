package util;

import javax.servlet.http.HttpSession;

public class OperationSession {

	public static void removeSessionAttribute(HttpSession session,String flag)
	{
		session.removeAttribute(flag);
		if(flag.equals("login"))
		{
			session.removeAttribute("loginCode");
			session.removeAttribute("loginGetCodeTime");
		}
		else if(flag.equals("addUser"))
		{
			session.removeAttribute("addUsercode");
			session.removeAttribute("addUserGetCodeTime");
		}
	}
	
	public static void setSessionAttribute(HttpSession session,String flag,String code)
	{
		session.setAttribute(flag,flag);
		if(flag.equals("login"))
		{
			session.setAttribute("loginCode",code);
			session.setAttribute("loginGetCodeTime",NowTime.getNowTime());
		}
		else if(flag.equals("addUser"))
		{
			session.setAttribute("addUsercode",code);
			session.setAttribute("addUserGetCodeTime",NowTime.getNowTime());
		}
	}
	
}
