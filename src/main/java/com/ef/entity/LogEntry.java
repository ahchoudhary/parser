package com.ef.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
/**
 * Created by husain on 9/18/18.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "log_entry")
@SequenceGenerator(name="seq", initialValue=1000)
public class LogEntry {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    long id;
    Date entryDateTime;
    String ip;
    String httpMethod;
    int httpStatus;
    String logDetails;
}
