package com.example.usecase;

import com.example.usecase.RequestData;

import java.sql.SQLException;

public interface InputBoundary {
    void execute(RequestData requestData) throws SQLException;
}
