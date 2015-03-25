import java.io.Serializable;

/**
 * Created by ishan on 3/22/15.
 */
public class UserInfo implements Serializable {
    String userID, userUnixID, userTDEID;
    char[] userPWD, userUnixPWD, userTDEPWD;

    public UserInfo(String userID, char[] userPWD, String userUnixID, char[] userUnixPWD, String userTDEID, char[] userTDEPWD) {
        this.userID = userID;
        this.userPWD = userPWD;
        this.userUnixID = userUnixID;
        this.userUnixPWD = userUnixPWD;
        this.userTDEID = userTDEID;
        this.userTDEPWD = userTDEPWD;
    }

    @Override
    public String toString() {
        return userID + userPWD.toString() + userUnixID + userUnixPWD.toString() + userTDEID + userTDEPWD.toString();
    }
}
