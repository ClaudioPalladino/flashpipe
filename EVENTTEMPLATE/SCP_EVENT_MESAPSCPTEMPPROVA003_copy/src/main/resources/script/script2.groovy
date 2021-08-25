import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.*

def Message processData(Message message) 
{
    def body = message.getBody(String.class)
    def jsonSlurper = new JsonSlurper()
    def sample = jsonSlurper.parseText(body)
    def regularJsonString = JsonOutput.toJson(sample)
    def beautifiedJsonString = JsonOutput.prettyPrint(regularJsonString)
    message.setProperty("PayloadJSON",beautifiedJsonString)
    message.setBody(beautifiedJsonString)
    return message
}
    