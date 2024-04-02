package com.campusdual.cd2023bbe1g2.ws.core.rest;

import com.campusdual.cd2023bbe1g2.api.core.service.IBankAccountService;
import com.ontimize.jee.server.rest.ORestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bankaccounts")
public class BankAccountRestController extends ORestController<IBankAccountService> {

    @Autowired
    private IBankAccountService iBankAccountService;

    @Override
    public IBankAccountService getService() {
        return this.iBankAccountService;
    }
}
