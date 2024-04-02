package com.campusdual.cd2023bbe1g2.ws.core.rest;

import com.campusdual.cd2023bbe1g2.api.core.service.IBookingService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingRestController extends ORestController<IBookingService> {

    @Autowired
    private IBookingService iBookingService;

    @Override
    public IBookingService getService() {
        return this.iBookingService;
    }
}
