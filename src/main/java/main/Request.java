package main;

public class Request {
    private String tm_name;
    private String pa_name;
    private String p_name;
    private String status = "pending"; // accept, deny, pending

    public Request()
    {
        super();
    }
    public Request(String tm_name, String pa_name, String p_name) {
        this.tm_name = tm_name;
        this.pa_name = pa_name;
        this.p_name = p_name;
    }
    //getters and setters
    public String getTm_name() {
        return tm_name;
    }

    public void setTm_name(String tm_name) {
        this.tm_name = tm_name;
    }

    public String getPa_name() {
        return pa_name;
    }

    public void setPa_name(String pa_name) {
        this.pa_name = pa_name;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
