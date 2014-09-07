package org.eu.ingwar.test1;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import javax.servlet.http.HttpServletRequest;

/**
 * Utility class for JDO persistence, OAuth flow helpers, and others.
 *
 * @author Yaniv Inbar
 */
class Utils {

//  /**
//   * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
//   * globally shared instance across your application.
//   */
  private static final DataStoreFactory DATA_STORE_FACTORY = new MemoryDataStoreFactory();
  
//  /** Global instance of the HTTP transport. */
  static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

  /** Global instance of the JSON factory. */
  static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  private static GoogleClientSecrets clientSecrets = null;

  static GoogleClientSecrets getClientCredential() throws IOException {
    if (clientSecrets == null) {
        InputStream stream = Utils.class.getClassLoader().getResourceAsStream("client_secret.json");
        Preconditions.checkNotNull(stream, "Client secrets not found");
//        Utils.class.getClassLoader().getResources("client_secrets.json")
        
      clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(stream));
      Preconditions.checkArgument(!clientSecrets.getDetails().getClientId().startsWith("Enter ")
          && !clientSecrets.getDetails().getClientSecret().startsWith("Enter "),
          "Download client_secrets.json file from https://code.google.com/apis/console/?api=calendar into calendar-appengine-sample/src/main/resources/client_secrets.json");
    }
    return clientSecrets;
  }

  static String getRedirectUri(HttpServletRequest req) {
    GenericUrl url = new GenericUrl(req.getRequestURL().toString());
    url.setRawPath("/oauth2callback");
    return url.build();
  }

  static GoogleAuthorizationCodeFlow newFlow() throws IOException {
    return new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
        getClientCredential(), Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(
        DATA_STORE_FACTORY).build();
  }

  
  static Calendar loadCalendarClient() throws IOException {
//    String userId = UserServiceFactory.getUserService().getCurrentUser().getUserId();
//    Credential credential = newFlow().loadCredential("ingwar@ingwar.eu.org");
//    
    AppIdentityCredential credential = new AppIdentityCredential(Arrays.asList(CalendarScopes.CALENDAR));
    Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("Dudek").build();
    return service;
  }

  private Utils() {
  }

}