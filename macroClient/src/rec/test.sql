--comment comment

create table tables (
  id int identity not null,
  label varchar(15) not null,
  location int not null
)

create table locations(
  id int identity not null,
  name varchar(15) not null,
  owner varchar(50) not null
)

-- more comments