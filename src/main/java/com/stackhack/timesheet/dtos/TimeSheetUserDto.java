package com.stackhack.timesheet.dtos;

import com.stackhack.timesheet.models.TimeSheetUser;
import com.stackhack.timesheet.models.UserType;

import java.util.Objects;
import java.util.UUID;

public class TimeSheetUserDto extends AuditedDto{

    private UUID id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean archived;
    private UserType userType;

    public TimeSheetUserDto() {
    }

    public TimeSheetUserDto(TimeSheetUser userEntity) {
        super(userEntity);
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.email =userEntity.getEmail();
        this.firstName = userEntity.getFirstName();
        this.lastName = userEntity.getLastName();
        this.archived = userEntity.getArchived();
        this.userType = userEntity.getUserType();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TimeSheetUserDto user = (TimeSheetUserDto) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, username, email, password, firstName, lastName);
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
