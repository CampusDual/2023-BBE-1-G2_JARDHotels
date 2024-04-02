package com.campusdual.cd2023bbe1g2.ws.core.rest;

import com.campusdual.cd2023bbe1g2.api.core.service.IJobService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobRestController extends ORestController<IJobService> {

    @Autowired
    private IJobService iJobService;

    @Override
    public IJobService getService() {
        return this.iJobService;
    }
}
