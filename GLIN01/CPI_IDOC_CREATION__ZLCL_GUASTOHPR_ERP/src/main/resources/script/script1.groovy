import com.sap.gateway.ip.core.customdev.util.Message;

def Message setMPLCustomHeaderProperty(Message message) {
    
    def messageLog = messageLogFactory.getMessageLog(message);
    
    messageLog.addCustomHeaderProperty("SalesOrder", "XXX10202910129XXX")
       
    return message
}