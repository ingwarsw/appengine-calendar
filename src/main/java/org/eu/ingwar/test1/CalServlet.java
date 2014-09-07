/**
 * Copyright 2012 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eu.ingwar.test1;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        CalendarService c = new CalendarService();
        Calendar service = c.test();

        resp.setContentType("text/plain");
        resp.getWriter().println("Acha");
        resp.getWriter().println("URL: " + service.getBaseUrl());
        resp.getWriter().println("Service path: " + service.getServicePath());
        
        resp.getWriter().println("Service path: " + service.events().list("en.polish#holiday@group.v.calendar.google.com").execute());
        
        com.google.api.services.calendar.model.Calendar model = new com.google.api.services.calendar.model.Calendar();
        model.setDescription("Pierwszy testowy");
        model.setSummary("Tescikowy kalendarz");
//        model.setId("en.polish#holiday@group.v.calendar.google.com");
//        model.
        String calId = service.calendars().insert(model).execute().getId();
        resp.getWriter().println("Dodano kalendarz o id: " + calId);
//        service.
        
        for (CalendarListEntry en : service.calendarList().list().execute().getItems()) {
            resp.getWriter().println("CalList: " + en.getDescription() + ": " + en.getId());
            
        }
    }
}
