package com.zyris.caloriescalculator.repository;

public interface UserModeDAO {
    void setUserMode(Long idPerson, Mode mode);

    Mode getUserMode(Long idPerson);

    public enum Mode {
        NONE, ADD_PRODUCT, CHOOSE_OPTION
    }
}
