package com.example.passworld.database;

public class DBConst {
    public static final String DATABASE_NAME = "passwords.db";
    public static final int DATABASE_VERSION = 1;

    public static final String PASSWORD_TABLE_NAME = "passwords";
    public static final String PASSWORD_ID = "id";
    public static final String PASSWORD_IDPAS = "idpas";
    public static final String PASSWORD_TEXT = "text";

    public static final String PASSWORD_CREATE_TABLE = "create table if not exists "
            + PASSWORD_TABLE_NAME+ " ( " + PASSWORD_ID + " integer primary key autoincrement, "+
            PASSWORD_IDPAS +" Text, " + PASSWORD_TEXT +" text ) ";

    public static final String PASSWORD_DELETE_TABLE = "delete table if exists "+ PASSWORD_TABLE_NAME;
}
