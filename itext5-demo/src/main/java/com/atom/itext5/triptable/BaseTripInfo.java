package com.atom.itext5.triptable;

/**
 * 嗒嗒行程单信息
 *
 * @author Atom
 */
public class BaseTripInfo {

    /**
     * 行程ID
     */
    private String tripId;
    /**
     * 序号
     */
    private Integer seq;
    /**
     * 车型
     */
    private String vehicleType;

    /**
     * 上车事件
     */
    private String pickUpTime;

    /**
     * 城市
     */
    private String city;
    /**
     * 起点
     */
    private String pickUpPoint;
    /**
     * 终点
     */
    private String dropOffPlace;
    /**
     * 里程【公里】
     */
    private Double mileage;
    /**
     * 金额【元】
     */
    private Double amount;

    /**
     * 备注
     */
    private String comment;


    public BaseTripInfo(String tripId, Integer seq, String vehicleType, String pickUpTime, String city, String pickUpPoint, String dropOffPlace, Double mileage, Double amount, String comment) {
        this.tripId = tripId;
        this.seq = seq;
        this.vehicleType = vehicleType;
        this.pickUpTime = pickUpTime;
        this.city = city;
        this.pickUpPoint = pickUpPoint;
        this.dropOffPlace = dropOffPlace;
        this.mileage = mileage;
        this.amount = amount;
        this.comment = comment;
    }


    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPickUpPoint() {
        return pickUpPoint;
    }

    public void setPickUpPoint(String pickUpPoint) {
        this.pickUpPoint = pickUpPoint;
    }

    public String getDropOffPlace() {
        return dropOffPlace;
    }

    public void setDropOffPlace(String dropOffPlace) {
        this.dropOffPlace = dropOffPlace;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "BaseTripInfo{" +
                "tripId='" + tripId + '\'' +
                ", seq=" + seq +
                ", vehicleType='" + vehicleType + '\'' +
                ", pickUpTime='" + pickUpTime + '\'' +
                ", city='" + city + '\'' +
                ", pickUpPoint='" + pickUpPoint + '\'' +
                ", dropOffPlace='" + dropOffPlace + '\'' +
                ", mileage=" + mileage +
                ", amount=" + amount +
                ", comment='" + comment + '\'' +
                '}';
    }
}
