package boot;

public class RequestBody {
    private String token;
    private String beginTime;
    private String endTime;
    private String prjId;
    private String gthNum;
    private String[] sensor;
    private int pgPerCnt;
    private String page;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPrjId() {
        return prjId;
    }

    public void setPrjId(String prjId) {
        this.prjId = prjId;
    }

    public String getGthNum() {
        return gthNum;
    }

    public void setGthNum(String gthNum) {
        this.gthNum = gthNum;
    }

    public String[] getSensor() {
        return sensor;
    }

    public void setSensor(String[] sensor) {
        this.sensor = sensor;
    }

    public int getPgPerCnt() {
        return pgPerCnt;
    }

    public void setPgPerCnt(int pgPerCnt) {
        this.pgPerCnt = pgPerCnt;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}