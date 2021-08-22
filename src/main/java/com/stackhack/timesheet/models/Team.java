package com.stackhack.timesheet.models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

//@Entity
public class Team extends AuditedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "VARBINARY(16)")
    private UUID id;

    private String name;

    private List<TimeSheetUser> teamMembers;
}
