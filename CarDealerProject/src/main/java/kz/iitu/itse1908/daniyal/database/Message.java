package kz.iitu.itse1908.daniyal.database;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private String from;
    private String text;
}
