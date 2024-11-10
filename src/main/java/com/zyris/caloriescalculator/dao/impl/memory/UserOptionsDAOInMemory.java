package com.zyris.caloriescalculator.dao.impl.memory;

import com.zyris.caloriescalculator.dao.UserOptionsDAO;
import com.zyris.caloriescalculator.service.command.operation.Operation;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserOptionsDAOInMemory implements UserOptionsDAO {
    private final Map<Long, Map<String, Operation>> operationMap = new HashMap<>();//todo it will not work in scaling mode

    @Override
    public void setUserOptions(Long idPerson, Map<String, Operation> chooseOptions) {
        operationMap.put(idPerson, chooseOptions);
    }

    @Override
    public void cleanUserOptions(Long idPerson) {
        operationMap.remove(idPerson);
    }

    @Override
    public Map<String, Operation> getUserOptions(Long idPerson) {
        return operationMap.getOrDefault(idPerson, Collections.emptyMap());
    }
}
