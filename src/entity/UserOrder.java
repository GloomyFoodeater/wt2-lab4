package entity;

import java.sql.Timestamp;

import java.io.Serializable;

public class UserOrder implements Serializable {

    private int id;
    private String status;
    private int userId;
    private int apartmentId;
    private Timestamp startTime;
    private int leaseDuration;


    public UserOrder() {}

    public UserOrder(int id, String status, int userId, int apartmentId, Timestamp startTime, int leaseDuration) {
        this.id = id;
        this.status = status;
        this.userId = userId;
        this.apartmentId = apartmentId;
        this.startTime = startTime;
        this.leaseDuration = leaseDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public int getLeaseDuration() {
        return leaseDuration;
    }

    public void setLeaseDuration(int leaseDuration) {
        this.leaseDuration = leaseDuration;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        UserOrder userOrder = (UserOrder)obj;
        return id == userOrder.id &&
                startTime.equals(userOrder.startTime) &&
                leaseDuration == userOrder.leaseDuration &&
                userId == userOrder.userId &&
                apartmentId == userOrder.apartmentId &&
                status.equals(userOrder.status);
    }

    @Override
    public int hashCode() {
        final int mul = 44;
        int result = 4;
        result = mul * result +  id;
        result = mul * result + leaseDuration;
        result = mul * result + startTime.hashCode();
        result = mul * result + apartmentId;
        result = mul * result + userId;
        result = mul * result + ((status == null) ? 0 : status.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id +
                ", leaseDuration='" + leaseDuration + '\'' +
                ", startTime=" + startTime +
                ", apartmentId=" + apartmentId +
                ", userId=" + userId +
                ", status='" + status + '\'' +
                '}';
    }
}
