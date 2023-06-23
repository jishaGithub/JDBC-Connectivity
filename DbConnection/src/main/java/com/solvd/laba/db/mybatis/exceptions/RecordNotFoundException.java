package com.solvd.laba.db.mybatis.exceptions;

public class RecordNotFoundException extends Exception {

    private int id;
    private final String tableName;
    public RecordNotFoundException(int id, String tableName) {
        this.id = id;
        this.tableName = tableName;
    }

    @Override
    public String getMessage() {
        return String.format("No record found for id %d in table %s",id, tableName);
    }
}
