package com.aiti.jdbc.handler;

import java.sql.ResultSet;

import java.util.List;

public interface IResultSetHandler{
    List handle(ResultSet rs) throws Exception;
}