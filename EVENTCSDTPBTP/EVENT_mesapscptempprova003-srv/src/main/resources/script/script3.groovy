import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;

def Message processData ( Message message ) 
{
	//Body
	    def map = message.getHeaders()
	    try{
		if(map.get("Token") == null) {
			
			message.setProperty("AUTH", "MISSING");
			return message;
		}
		def expiry =  map.get("Expiry")
		if(expiry == null || expiry < System.currentTimeMillis()) {
			message.setProperty("AUTH", "MISSING");
			return message;
		}
		message.setProperty("AUTH", "OK");
	    }catch(Exception e){
	        message.setProperty("AUTH", "MISSING");
		
	    }
	   return message;
}