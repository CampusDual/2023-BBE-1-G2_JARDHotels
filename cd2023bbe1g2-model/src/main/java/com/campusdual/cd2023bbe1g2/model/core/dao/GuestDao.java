package com.campusdual.cd2023bbe1g2.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository(value = "GuestDao")
@Lazy
@ConfigurationFile(configurationFile = "dao/GuestDao.xml", configurationFilePlaceholder = "dao/placeholders.properties")
public class GuestDao extends OntimizeJdbcDaoSupport {

    public static final String ATTR_ID = "id";
}
