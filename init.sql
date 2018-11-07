/*权限表*/

create table permission(
  pid int(11) not null auto_increment,
  name varchar (255) not null  default '',
  url varchar (255) default '',
  primary key (pid)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

/*用户表*/
create table user(
  uid int(11) not null auto_increment,
  username varchar (255) not null default '',
  password varchar (255) not null default '',
  primary key(uid)
) ENGINE = InnoDB default CHARSET = utf8;

/*角色表*/
create table role(
  rid int(11) not null auto_increment,
  rname varchar (255) not null default '',
  primary key(rid)
)ENGINE = InnoDB default CHARSET = utf8;

/*权限角色关系表*/
create table permission_role(
  rid int (11) not null,
  pid int (11) not null,
  key idx_rid(rid),
  key idx_pid(pid)
)ENGINE = InnoDB default CHARSET = utf8;

/*用户角色关系表*/
create table user_role(
  uid int (11) not null,
  rid int (11) not null,
  key idx_uid(uid),
  key idx_rid(rid)
)ENGINE = InnoDb DEFAULT CHARSET = utf8;

/*初始化权限表数据*/
INSERT INTO permission values(1,'add','');
INSERT INTO permission values(2,'delete','');
INSERT INTO permission values(3,'edit','');
INSERT INTO permission values(4,'query','');

/*初始化用户表数据*/
INSERT INTO user values(1,'admin','123');
INSERT INTO user values(2,'demo','123');


/*初始化角色表数据*/
INSERT INTO role values(1,'admin');
INSERT INTO role values(2,'customer');
INSERT INTO role values(3,'normal');

/*初始化权限角色关系表 */
INSERT INTO permission_role values (1,1);
INSERT INTO permission_role values (1,2);
INSERT INTO permission_role values (1,3);
INSERT INTO permission_role values (1,4);
INSERT INTO permission_role values (2,1);
INSERT INTO permission_role values (2,4);

/*初始化用户角色关系表*/
INSERT into user_role values (1,1);
INSERT into user_role values (2,2);
INSERT into user_role values (1,3);