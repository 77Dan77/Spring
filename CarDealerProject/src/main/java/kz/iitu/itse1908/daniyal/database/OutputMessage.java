package kz.iitu.itse1908.daniyal.database;

import lombok.Data;

import java.util.Date;

public class OutputMessage extends Message{

    public OutputMessage(String from, String text, String time) {
        super(from, text);
    }

}
