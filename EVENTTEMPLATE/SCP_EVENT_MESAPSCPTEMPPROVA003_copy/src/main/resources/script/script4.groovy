import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.JsonSlurper
def Message processData(Message message) throws Exception
{
	def body = message.getBody(java.lang.String)
	def slurper = new JsonSlurper().parseText(body)
	message.setHeader("Authorization",slurper.access_token);
	message.setHeader("Expiry",System.currentTimeMillis()+60*1000*30); //30 minutes
	return message;
}