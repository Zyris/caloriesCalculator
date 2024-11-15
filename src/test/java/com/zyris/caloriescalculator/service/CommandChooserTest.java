package com.zyris.caloriescalculator.service;

import com.zyris.caloriescalculator.service.command.CommandChooser;
import com.zyris.caloriescalculator.service.command.CommandOperator;
import com.zyris.caloriescalculator.service.command.impl.DefaultNotExistCommandOperator;
import com.zyris.caloriescalculator.service.command.impl.HelpCommandOperator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommandChooserTest {

    @Test
    void checkProperChoosing() {
        DefaultNotExistCommandOperator defaultOperator = new DefaultNotExistCommandOperator();
        HelpCommandOperator helpOperator = new HelpCommandOperator();
        List<CommandOperator> listOfCommands = List.of(defaultOperator, helpOperator);

        CommandChooser tested = new CommandChooser(listOfCommands, defaultOperator);

        assertThat(tested.chooseCommand("/asd")).isEqualTo(defaultOperator);
        assertThat(tested.chooseCommand("/help")).isEqualTo(helpOperator);
    }
}