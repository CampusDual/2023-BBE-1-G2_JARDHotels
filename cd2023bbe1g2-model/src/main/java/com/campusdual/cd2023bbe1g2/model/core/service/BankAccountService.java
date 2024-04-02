package com.campusdual.cd2023bbe1g2.model.core.service;

import com.campusdual.cd2023bbe1g2.api.core.service.IBankAccountService;
import com.campusdual.cd2023bbe1g2.model.core.dao.BankAccountDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.security.PermissionsProviderSecured;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("BankAccountService")
public class BankAccountService implements IBankAccountService {

    @Autowired
    private DefaultOntimizeDaoHelper daoHelper;

    @Autowired
    private BankAccountDao bankAccountDao;

    @Autowired
    private PersonService personService;

    @Override
    @Secured({ PermissionsProviderSecured.SECURED })
    public EntityResult bankaccountQuery(Map<String, Object> keyMap, List<String> attrList) {

        boolean deleteId = false;
        if (!attrList.contains("id")) {
            attrList.add("id");
            deleteId = true;
        }

        EntityResult result = this.daoHelper.query(this.bankAccountDao,keyMap,attrList);
        if(result.toString().contains("id")) {
            result.setMessage("");
            if (deleteId) {
                result.remove("id");
            }

        } else {
            result.setMessage("The bank account format doesn't exist");
            result.setCode(EntityResult.OPERATION_WRONG);
            result.setColumnSQLTypes(new HashMap<>());
        }

        return result;
    }


}
