package FileLockManagement;

public class User {
    private String username;
    private String lockedFile;
    private int userStatus;
    public User(){
        this.username="";
        this.lockedFile="";
        this.userStatus=0;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username=username;
    }

    public String getLockedFile() {
        return lockedFile;
    }

    public void setLockedFile(String lockedFile) {
        this.lockedFile = lockedFile;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
}
