package com.campusdual.cd2023bbe1g2.ws.core.rest;

import com.campusdual.cd2023bbe1g2.api.core.service.IPantryService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pantries")
public class PantryRestController extends ORestController<IPantryService> {

    @Autowired
    private IPantryService iPantryService;

    @Override
    public IPantryService getService() {
        return this.iPantryService;
    }
}
