package entity;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String email;
    private int userInformationId;
    private int roleId;

    public User() {}

    public User(int id, int userInformation, int roleId) {
        this.id = id;
        this.userInformationId = userInformation;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserInformationId() {
        return userInformationId;
    }

    public void setUserInformationId(int userInformationId) {
        this.userInformationId = userInformationId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        User user = (User)obj;
        return id == user.id &&
                email.equals(user.email) &&
                userInformationId == user.userInformationId &&
                roleId == user.roleId;
    }

    @Override
    public int hashCode() {
        final int mul = 44;
        int result = 4;
        result = mul * result +  id;
        result = mul * result + ((email == null) ? 0 : email.hashCode());
        result = mul * result +  userInformationId;
        result = mul * result +  roleId;

        return result;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id +
                ", email=" + email +
                ", roleId='" + roleId +
                ", userInfoId=" + userInformationId +
                '}';
    }
}
