package com.zyris.caloriescalculator.dao.impl.memory;

import com.zyris.caloriescalculator.dao.UserModeDAO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserModeDAOInMemory implements UserModeDAO {
    private final Map<Long, Mode> map = new HashMap<>();

    public void setUserMode(Long idPerson, Mode mode) {
        this.map.put(idPerson, mode);
    }
    public Mode getUserMode(Long idPerson) {
        return this.map.getOrDefault(idPerson, Mode.NONE);
    }

}
