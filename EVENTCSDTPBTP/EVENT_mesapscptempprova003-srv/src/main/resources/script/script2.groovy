import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import groovy.json.*

def Message processData(Message message) 
{
    def body = message.getBody(String.class)
    def jsonSlurper = new JsonSlurper()
    def Event = jsonSlurper.parseText(body)
    message.setProperty("EndpointPath",Event.endpoint)
    Event.remove("endpoint")
    def regularJsonString = JsonOutput.toJson(Event)
    def beautifiedJsonString = JsonOutput.prettyPrint(regularJsonString)
    message.setProperty("PayloadJSON",beautifiedJsonString)
    message.setBody(beautifiedJsonString)
    return message
}
    