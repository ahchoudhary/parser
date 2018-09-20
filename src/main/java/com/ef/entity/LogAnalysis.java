package com.ef.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by husain on 9/19/18.
 */
@Entity
@Table(name="ip_analysis")
@SequenceGenerator(name="analysis_seq", initialValue=1000)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LogAnalysis {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="analysis_seq")
    long id;
    String ip;
    String comment;

}
