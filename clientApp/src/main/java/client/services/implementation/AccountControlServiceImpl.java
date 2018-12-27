package client.services.implementation;

import org.springframework.stereotype.Service;

@Service
public class AccountControlServiceImpl {

    public final String LOGGED_OUT = "offline";
    public final String LOGGED_IN = "online";

    private String status = LOGGED_OUT;

    public void login(){
        status = LOGGED_IN;
    }

    public void logout(){
        status = LOGGED_OUT;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
