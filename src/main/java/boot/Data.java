package boot;

public class Data {
    private int dId;
    private String dTime;
    private int dPrjId;
    private String dPrjName;
    private String dStruct;
    private String dDtu;
    private String dGthType;
    private String dGthNum;
    private int dSenAddr;
    private String dInstal;
    private String dSenNum;
    private String dSenType;
    private String dTempr;
    private String dM1;
    private String dM2;
    private String dUnit;

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public String getdTime() {
        return dTime;
    }

    public void setdTime(String dTime) {
        this.dTime = dTime;
    }

    public int getdPrjId() {
        return dPrjId;
    }

    public void setdPrjId(int dPrjId) {
        this.dPrjId = dPrjId;
    }

    public String getdPrjName() {
        return dPrjName;
    }

    public void setdPrjName(String dPrjName) {
        this.dPrjName = dPrjName;
    }

    public String getdStruct() {
        return dStruct;
    }

    public void setdStruct(String dStruct) {
        this.dStruct = dStruct;
    }

    public String getdDtu() {
        return dDtu;
    }

    public void setdDtu(String dDtu) {
        this.dDtu = dDtu;
    }

    public String getdGthType() {
        return dGthType;
    }

    public void setdGthType(String dGthType) {
        this.dGthType = dGthType;
    }

    public String getdGthNum() {
        return dGthNum;
    }

    public void setdGthNum(String dGthNum) {
        this.dGthNum = dGthNum;
    }

    public int getdSenAddr() {
        return dSenAddr;
    }

    public void setdSenAddr(int dSenAddr) {
        this.dSenAddr = dSenAddr;
    }

    public String getdInstal() {
        return dInstal;
    }

    public void setdInstal(String dInstal) {
        this.dInstal = dInstal;
    }

    public String getdSenNum() {
        return dSenNum;
    }

    public void setdSenNum(String dSenNum) {
        this.dSenNum = dSenNum;
    }

    public String getdSenType() {
        return dSenType;
    }

    public void setdSenType(String dSenType) {
        this.dSenType = dSenType;
    }

    public String getdTempr() {
        return dTempr;
    }

    public void setdTempr(String dTempr) {
        this.dTempr = dTempr;
    }

    public String getdM1() {
        return dM1;
    }

    public void setdM1(String dM1) {
        this.dM1 = dM1;
    }

    public String getdM2() {
        return dM2;
    }

    public void setdM2(String dM2) {
        this.dM2 = dM2;
    }

    public String getdUnit() {
        return dUnit;
    }

    public void setdUnit(String dUnit) {
        this.dUnit = dUnit;
    }

    @Override
    public String toString() {
        return "Data{" +
                "dId=" + dId +
                ", dTime='" + dTime + '\'' +
                ", dPrjId=" + dPrjId +
                ", dPrjName='" + dPrjName + '\'' +
                ", dStruct='" + dStruct + '\'' +
                ", dDtu='" + dDtu + '\'' +
                ", dGthType='" + dGthType + '\'' +
                ", dGthNum='" + dGthNum + '\'' +
                ", dSenAddr=" + dSenAddr +
                ", dInstal='" + dInstal + '\'' +
                ", dSenNum='" + dSenNum + '\'' +
                ", dSenType='" + dSenType + '\'' +
                ", dTempr='" + dTempr + '\'' +
                ", dM1='" + dM1 + '\'' +
                ", dM2='" + dM2 + '\'' +
                ", dUnit='" + dUnit + '\'' +
                '}';
    }
}
