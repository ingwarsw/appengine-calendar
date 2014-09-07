package org.eu.ingwar.appengine.calendar.utils;

import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import java.io.IOException;
import java.util.Arrays;

public class ServiceUtils {
  
//  /** Global instance of the HTTP transport. */
  static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

  /** Global instance of the JSON factory. */
  static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
  
  public static Calendar getCalendarInstance() throws IOException {
    AppIdentityCredential credential = new AppIdentityCredential(Arrays.asList(CalendarScopes.CALENDAR));
    Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("whateva").build();
    return service;
  }

  private ServiceUtils() {
  }

}