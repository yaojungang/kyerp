package com.tyopf.util.msn;

import rath.msnm.MSNMessenger;
import rath.msnm.SwitchboardSession;
import rath.msnm.UserStatus;
import rath.msnm.entity.MsnFriend;
import rath.msnm.event.MsnAdapter;
import rath.msnm.msg.MimeMessage;

public class TestMsn {
	public static void main(String[] argv){
		MSNMessenger msn = new MSNMessenger( "y109@tyopf.com", "jzlandyjg" );
		  msn.setInitialStatus( UserStatus.ONLINE );
		   msn.addMsnListener( new MsnAdapter() {
		       public void progressTyping( SwitchboardSession ss,
		         MsnFriend friend, String typingUser )
		     {
		          System.out.println( "Typing on " + friend.getLoginName() );
		       }
		       public void instantMessageReceived( SwitchboardSession ss,
		         MsnFriend friend, MimeMessage mime )
		      {
		         System.out.println( "*** MimeMessage from " + friend.getLoginName() );
		         System.out.println( mime.getMessage() );
		         System.out.println( "*****************************" );
		      }
		   });
		  msn.login();
	}
}
