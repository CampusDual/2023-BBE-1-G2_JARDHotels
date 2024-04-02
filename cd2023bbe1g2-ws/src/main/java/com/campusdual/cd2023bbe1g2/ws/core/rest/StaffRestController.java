package com.campusdual.cd2023bbe1g2.ws.core.rest;

import com.campusdual.cd2023bbe1g2.api.core.service.IStaffService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class StaffRestController extends ORestController<IStaffService> {

    @Autowired
    private IStaffService iStaffService;

    @Override
    public IStaffService getService() {
        return this.iStaffService;
    }
}
