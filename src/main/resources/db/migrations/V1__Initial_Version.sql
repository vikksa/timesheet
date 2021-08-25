create table cost_head
(
    id         varbinary(16) not null
        primary key,
    created_at datetime      not null,
    created_by varchar(255)  not null,
    updated_at datetime      null,
    updated_by varchar(255)  null,
    archived   bit           null,
    name       varchar(255)  not null
);

create table project
(
    id         varbinary(16) not null
        primary key,
    created_at datetime      not null,
    created_by varchar(255)  not null,
    updated_at datetime      null,
    updated_by varchar(255)  null,
    archived   bit           null,
    name       varchar(255)  not null
);

create table project_permission
(
    id                            varbinary(16) not null
        primary key,
    created_at                    datetime      not null,
    created_by                    varchar(255)  not null,
    updated_at                    datetime      null,
    updated_by                    varchar(255)  null,
    billing_info_visibility       varchar(255)  null,
    member_access_team_dash_board bit           null,
    member_time_visibility        varchar(255)  null,
    project_status_visibility     varchar(255)  null,
    time_updation                 varchar(255)  null,
    project_id                    varbinary(16) not null
);

create index FKfkt40ba2lq62yoajor07vstbq
    on project_permission (project_id);

create table project_permission_all_member_permissions
(
    project_permission_id  varbinary(16) not null,
    all_member_permissions varchar(255)  null
);

create index FKprfotmu51nr4cbyis6kcjbe8r
    on project_permission_all_member_permissions (project_permission_id);

create table project_permission_all_member_visit_permissions
(
    project_permission_id        varbinary(16) not null,
    all_member_visit_permissions varchar(255)  null
);

create index FKbugrn9gdya0utn4xv39h9dswl
    on project_permission_all_member_visit_permissions (project_permission_id);

create table project_settings
(
    id                varbinary(16) not null
        primary key,
    created_at        datetime      not null,
    created_by        varchar(255)  not null,
    updated_at        datetime      null,
    updated_by        varchar(255)  null,
    billable          bit           null,
    is_timer_on       bit           null,
    logo              tinyblob      null,
    time_manual_entry bit           null,
    project_id        varbinary(16) not null
);

create index FKc5hxsu712yywha3q1oggqoutr
    on project_settings (project_id);

create table project_settings_days
(
    project_settings_id varbinary(16) not null,
    days                varchar(255)  null
);

create index FK3io92ixxt5ku9c7yfo0ykc6oo
    on project_settings_days (project_settings_id);

create table team
(
    id         varbinary(16) not null
        primary key,
    created_at datetime      not null,
    created_by varchar(255)  not null,
    updated_at datetime      null,
    updated_by varchar(255)  null,
    archived   bit           null,
    name       varchar(255)  not null
);

create table team_member
(
    user_id varbinary(16) not null,
    team_id varbinary(16) not null,
    primary key (user_id, team_id)
);

create index FK6ekua887re1isvsl4jhmleq3
    on team_member (team_id);

create table time_logs
(
    id                varbinary(16) not null
        primary key,
    created_at        datetime      not null,
    created_by        varchar(255)  not null,
    updated_at        datetime      null,
    updated_by        varchar(255)  null,
    description       varchar(255)  null,
    end_time          datetime      null,
    issue_number      varchar(255)  null,
    program_name      varchar(255)  null,
    start_time        datetime      null,
    cost_head_id      varbinary(16) not null,
    project_id        varbinary(16) not null,
    timesheet_user_id varbinary(16) not null
);

create index FK3ka7osw8ec8ejuaippfwjdhua
    on time_logs (project_id);

create index FKaj8ema9s1qevdydvuqlvq3pbb
    on time_logs (timesheet_user_id);

create index FKbnro85ko2k9hfh2mwgu7mypsb
    on time_logs (cost_head_id);

create table time_sheet_user
(
    id         varbinary(16) not null
        primary key,
    created_at datetime      not null,
    created_by varchar(255)  not null,
    updated_at datetime      null,
    updated_by varchar(255)  null,
    archived   bit           null,
    email      varchar(255)  not null,
    first_name varchar(255)  not null,
    last_name  varchar(255)  null,
    password   varchar(255)  null,
    user_type  varchar(255)  null,
    username   varchar(255)  not null
);

