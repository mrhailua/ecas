
    create table ecas_api (
        id integer not null auto_increment unique,
        update_time datetime,
        updateUser tinyblob,
        active boolean,
        description longtext,
        name varchar(255) not null,
        application_id integer,
        primary key (id)
    );

    create table ecas_api_key (
        id integer not null auto_increment unique,
        update_time datetime,
        updateUser tinyblob,
        active boolean,
        code varchar(255),
        description longtext,
        name varchar(255) not null,
        white_list_ip varchar(255),
        api_id integer not null,
        primary key (id)
    );

    create table ecas_application (
        id integer not null auto_increment unique,
        update_time datetime,
        updateUser tinyblob,
        description longtext not null,
        name varchar(255) not null,
        primary key (id)
    );

    create table ecas_permission (
        id integer not null auto_increment unique,
        update_time datetime,
        updateUser tinyblob,
        active boolean,
        application_id tinyblob,
        approve_user_id tinyblob,
        description longtext,
        name varchar(255) not null,
        primary key (id)
    );

    create table ecas_role (
        id integer not null auto_increment unique,
        update_time datetime,
        updateUser tinyblob,
        active boolean,
        description longtext,
        name varchar(255) not null,
        application_id integer not null,
        primary key (id)
    );

    create table ecas_setting (
        id integer not null auto_increment unique,
        update_time datetime,
        updateUser tinyblob,
        contact_email varchar(100),
        timezone varchar(10),
        web_domain varchar(255),
        primary key (id)
    );

    create table ecas_system_message (
        id integer not null auto_increment unique,
        update_time datetime,
        updateUser tinyblob,
        code varchar(255) not null,
        value longtext,
        primary key (id)
    );

    create table ecas_user (
        id integer not null auto_increment unique,
        activation_code varchar(45),
        email varchar(255),
        expireable boolean,
        system_admin boolean DEFAULT 0,
        NAME varchar(255),
        password varchar(255),
        status varchar(20),
        primary key (id)
    );

    create table ecas_user_message (
        id integer not null auto_increment unique,
        update_time datetime,
        updateUser tinyblob,
        description longtext,
        is_read boolean,
        target_url varchar(255),
        title varchar(255) not null,
        to_user_id integer not null,
        primary key (id)
    );

    create table role_has_permission (
        role_id integer not null,
        permission_id integer not null
    );

    create table user_has_role (
        user_id integer not null,
        role_id integer not null
    );

    alter table ecas_api 
        add index FK1CE6EAAB6B635D61 (application_id), 
        add constraint FK1CE6EAAB6B635D61 
        foreign key (application_id) 
        references ecas_application (id);

    alter table ecas_api_key 
        add index FK24F86ACB683A4421 (api_id), 
        add constraint FK24F86ACB683A4421 
        foreign key (api_id) 
        references ecas_api (id);

    alter table ecas_role 
        add index FK7FFE22056B635D61 (application_id), 
        add constraint FK7FFE22056B635D61 
        foreign key (application_id) 
        references ecas_application (id);

    alter table ecas_user_message 
        add index FKA7AC3A224D86344F (to_user_id), 
        add constraint FKA7AC3A224D86344F 
        foreign key (to_user_id) 
        references ecas_user (id);

    alter table role_has_permission 
        add index FKE853F77D20D86633 (role_id), 
        add constraint FKE853F77D20D86633 
        foreign key (role_id) 
        references ecas_role (id);

    alter table role_has_permission 
        add index FKE853F77D2365ECD3 (permission_id), 
        add constraint FKE853F77D2365ECD3 
        foreign key (permission_id) 
        references ecas_permission (id);

    alter table user_has_role 
        add index FKB6790EF20D86633 (role_id), 
        add constraint FKB6790EF20D86633 
        foreign key (role_id) 
        references ecas_role (id);

    alter table user_has_role 
        add index FKB6790EFC6032A13 (user_id), 
        add constraint FKB6790EFC6032A13 
        foreign key (user_id) 
        references ecas_user (id);
