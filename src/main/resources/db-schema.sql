create table if not exists country (
  id        identity,
  name      varchar(255),
  code_name varchar(2)
);

create table if not exists "user" (
  id        identity,
  firstName varchar(20),
  lastName  varchar(30)
);
