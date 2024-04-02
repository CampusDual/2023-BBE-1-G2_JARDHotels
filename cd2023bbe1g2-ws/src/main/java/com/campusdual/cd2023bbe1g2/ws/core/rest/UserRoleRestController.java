package com.campusdual.cd2023bbe1g2.ws.core.rest;

import com.campusdual.cd2023bbe1g2.api.core.service.IUserRoleService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_role")
public class UserRoleRestController extends ORestController<IUserRoleService> {

    @Autowired
    private IUserRoleService iUserRoleService;

    @Override
    public IUserRoleService getService() {
        return this.iUserRoleService;
    }
}
