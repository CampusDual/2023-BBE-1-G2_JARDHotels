package com.campusdual.cd2023bbe1g2.model.core.service;

import com.campusdual.cd2023bbe1g2.api.core.service.IUserService;
import com.campusdual.cd2023bbe1g2.model.core.dao.UserDao;
import com.ontimize.jee.common.dto.EntityResult;
import com.ontimize.jee.common.dto.EntityResultMapImpl;
import com.ontimize.jee.common.security.PermissionsProviderSecured;
import com.ontimize.jee.server.dao.DefaultOntimizeDaoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Lazy
@Service("UserService")
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private DefaultOntimizeDaoHelper daoHelper;

	public void loginQuery(Map<?, ?> key, List<?> attr) {
	}

	@Secured({ PermissionsProviderSecured.SECURED })
	public EntityResult userQuery(Map<String, Object> keyMap, List<String> attrList) {

		boolean deleteId = false;
		if (!attrList.contains("id")) {
			attrList.add("id");
			deleteId = true;
		}

		EntityResult result = this.daoHelper.query(userDao, keyMap, attrList);

		if(!result.toString().contains("username")){
			EntityResult error = new EntityResultMapImpl();
			error.setCode(EntityResult.OPERATION_WRONG);
			error.setMessage("User not found");
			return error;
		}

		if (deleteId) {
			result.remove("id");
		}
		return result;
	}

	public EntityResult userInsert(Map<String, Object> attrMap) {
		return this.daoHelper.insert(userDao, attrMap);
	}

	public EntityResult userUpdate(Map<String, Object> attrMap, Map<String, Object> keyMap) {
		return this.daoHelper.update(userDao, attrMap, keyMap);
	}

	public EntityResult userDelete(Map<String, Object> keyMap) {
		Map<Object, Object> attrMap = new HashMap<>();
		attrMap.put("user_down_date", new Timestamp(Calendar.getInstance().getTimeInMillis()));
		return this.daoHelper.update(this.userDao, attrMap, keyMap);
	}

}
