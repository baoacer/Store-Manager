package com.example.usecase;

import java.sql.SQLException;

public interface InputBoundary {
    void execute(RequestData requestData) throws SQLException;
}
