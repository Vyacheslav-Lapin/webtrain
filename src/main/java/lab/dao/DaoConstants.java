package lab.dao;

public interface DaoConstants {
    String CREATE_COUNTRY_TABLE_SQL = "create table country(id identity, name varchar (255), code_name varchar (255))";
    String DROP_COUNTRY_TABLE_SQL = "drop table country";
}
