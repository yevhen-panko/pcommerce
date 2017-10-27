package com.yevhenpanko.pcommerce.dto;

import java.io.Serializable;

public class UserShortDTO implements Serializable {
    private long id;
    private String firstName;
    private String lastName;
    private UserRoleDTO userRole;

    public UserShortDTO(long id, String firstName, String lastName, UserRoleDTO userRole) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
    }

    public UserShortDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public UserRoleDTO getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleDTO userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserShortDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
