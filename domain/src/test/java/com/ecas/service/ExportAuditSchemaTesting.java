package com.ecas.service;

import com.ecas.base.AbstractDomain;
import com.ecas.base.BaseDomain;
import com.ecas.domain.*;
import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class ExportAuditSchemaTesting {
    static Logger log = Logger.getLogger(ExportAuditSchemaTesting.class.getName());

    @Test
    public void configureDbExport() {
        try {
            Configuration config = new Configuration();
            config.addAnnotatedClass(Enum.class);
            config.addAnnotatedClass(AbstractDomain.class);
            config.addAnnotatedClass(BaseDomain.class);
            config.addAnnotatedClass(User.class);
            config.addAnnotatedClass(Application.class);
            config.addAnnotatedClass(UserMessage.class);
            config.addAnnotatedClass(User.class);
            config.addAnnotatedClass(Setting.class);
            config.addAnnotatedClass(API.class);
            config.addAnnotatedClass(APIKey.class);
            config.addAnnotatedClass(Application.class);
            config.addAnnotatedClass(Permission.class);
            config.addAnnotatedClass(Setting.class);
            config.addAnnotatedClass(SystemMessage.class);
            config.addAnnotatedClass(Role.class);
            config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            SchemaExport export = new SchemaExport(config).setOutputFile("audit-schema.sql");
            export.setDelimiter(";");
            export.execute(true, false, false, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
