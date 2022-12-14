package entity;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private int id;
    private String name;
    private String phone;

    public UserInfo() {}

    public UserInfo(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        UserInfo userInformation = (UserInfo)obj;
        return id == userInformation.id &&
                name.equals(userInformation.name) &&
                phone.equals(userInformation.phone);
    }

    @Override
    public int hashCode() {
        final int mul = 44;
        int result = 4;
        result = mul * result +  id;
        result = mul * result + ((name == null) ? 0 : name.hashCode());
        result = mul * result +  ((phone==null) ? 0: phone.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id +
                ", name='" + name +
                ", phone=" + phone +
                '}';
    }
}
