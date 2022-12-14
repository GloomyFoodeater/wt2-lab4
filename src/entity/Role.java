package entity;

import java.io.Serializable;

public class Role implements Serializable {
    private int id;
    private String role;

    public Role() {}

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String name) {
        this.role = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        Role role = (Role) obj;
        return id == role.id && role.equals(role.role);
    }

    @Override
    public int hashCode() {
        final int mul = 44;
        int result = 4;
        result = mul * result + id;
        result = mul * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id +
                "role=" + role +
                '}';
    }
}
