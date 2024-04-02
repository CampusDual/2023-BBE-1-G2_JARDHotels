package com.campusdual.cd2023bbe1g2.ws.core.rest;

import com.campusdual.cd2023bbe1g2.api.core.service.IPersonService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonRestController extends ORestController<IPersonService> {

    @Autowired
    private IPersonService iPersonService;

    @Override
    public IPersonService getService() {
        return this.iPersonService;
    }
}
