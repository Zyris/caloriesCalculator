package com.zyris.caloriescalculator.dao;

import com.zyris.caloriescalculator.service.command.operation.Operation;

import java.util.Map;

public interface UserOptionsDAO {
    void setUserOptions(Long idPerson, Map<String, Operation> mode);

    void cleanUserOptions(Long idPerson);

    Map<String, Operation> getUserOptions(Long idPerson);

}
