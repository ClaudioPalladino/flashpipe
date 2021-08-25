import com.sap.gateway.ip.core.customdev.util.Message;
import java.util.HashMap;
import com.sap.it.api.ITApiFactory;
import com.sap.it.api.securestore.SecureStoreService;
import com.sap.it.api.securestore.UserCredential;
def Message processData(Message message)
{
  /* Read Credentials. Name of Deployed Credentials */
  def messageLog = messageLogFactory.getMessageLog(message);
  String payload = message.getBody(java.lang.String)
  def map = message.getHeaders();
  def CommunicationUser = map.get("CSDTP_Communication_User");
  def service = ITApiFactory.getApi(SecureStoreService.class, null);
  def credential = service.getUserCredential(CommunicationUser);
  if (credential == null)
  {
    throw new IllegalStateException("No credential found for alias");
  }
  messageLog.setStringProperty("Credential","Credentials Retrieved");
  String userName = credential.getUsername();
  String password = new String(credential.getPassword());
  message.setProperty("username", userName);
  message.setProperty("password", password);
  message.setBody("grant_type=password&username="+userName+"&password="+password);
  message.setHeader("Content-Type","application/x-www-form-urlencoded");
  return message;
}