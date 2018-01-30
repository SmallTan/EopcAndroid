package app.ifox.com.eopcandroid.model;

/**
 * Created by 13118467271 on 2018/1/29.
 */

public class LoginJSON {
    public ParkUser getParkUser() {
        return parkUser;
    }

    public TokenModel getTokenModel() {
        return tokenModel;
    }

    public void setParkUser(ParkUser parkUser) {
        this.parkUser = parkUser;
    }

    public void setTokenModel(TokenModel tokenModel) {
        this.tokenModel = tokenModel;
    }

    private ParkUser parkUser;
    private TokenModel tokenModel;
    public LoginJSON(){

    }
    public LoginJSON(ParkUser parkUser,TokenModel tokenModel){
        this.parkUser = parkUser;
        this.tokenModel = tokenModel;
    }
}
