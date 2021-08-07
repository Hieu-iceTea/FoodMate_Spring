package Hieu_iceTea.FoodMate_Spring.controller.rest.exception;

public class RestaurantErrorResponse {

    //region - Define Fields -
    private int status;
    private String message;
    private long timeStamp;
    //endregion


    //region - Constructor -
    public RestaurantErrorResponse() {
    }

    public RestaurantErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }
    //endregion


    //region - Getter, Setter -
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    //endregion

}
