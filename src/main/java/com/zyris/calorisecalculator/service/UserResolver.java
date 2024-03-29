package com.zyris.calorisecalculator.service;

import com.zyris.calorisecalculator.domain.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserResolver {
    private Map<Long, User> mapOfExistingUsers = new HashMap<>();

    public User resolve(Long userId) {
        if (!mapOfExistingUsers.containsKey(userId)) {
            mapOfExistingUsers.put(userId, new User(userId));
        }
        return mapOfExistingUsers.get(userId);
    }
}

