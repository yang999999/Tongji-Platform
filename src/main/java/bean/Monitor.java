package bean;

import java.util.Objects;

public class Monitor {
    Integer dId;
    String dTime;
    Integer dPrjId;
    String dPrjName;
    String dStruct;
    String dDtu;
    String dGthType;
    private String dGthNum;
    private Integer dSenAddr;
    private String dInstal;
    private String dSenNum;
    private String dSenType;
    private String dTempr;
    private String dM1;
    private String dM2;
    private String dUnit;

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getdTime() {
        return dTime;
    }

    public void setdTime(String dTime) {
        this.dTime = dTime;
    }

    public Integer getdPrjId() {
        return dPrjId;
    }

    public void setdPrjId(Integer dPrjId) {
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

    public Integer getdSenAddr() {
        return dSenAddr;
    }

    public void setdSenAddr(Integer dSenAddr) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monitor monitor = (Monitor) o;
        return Objects.equals(dId, monitor.dId) && Objects.equals(dTime, monitor.dTime) && Objects.equals(dPrjId, monitor.dPrjId) && Objects.equals(dPrjName, monitor.dPrjName) && Objects.equals(dStruct, monitor.dStruct) && Objects.equals(dDtu, monitor.dDtu) && Objects.equals(dGthType, monitor.dGthType) && Objects.equals(dGthNum, monitor.dGthNum) && Objects.equals(dSenAddr, monitor.dSenAddr) && Objects.equals(dInstal, monitor.dInstal) && Objects.equals(dSenNum, monitor.dSenNum) && Objects.equals(dSenType, monitor.dSenType) && Objects.equals(dTempr, monitor.dTempr) && Objects.equals(dM1, monitor.dM1) && Objects.equals(dM2, monitor.dM2) && Objects.equals(dUnit, monitor.dUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dId, dTime, dPrjId, dPrjName, dStruct, dDtu, dGthType, dGthNum, dSenAddr, dInstal, dSenNum, dSenType, dTempr, dM1, dM2, dUnit);
    }

    @Override
    public String toString() {
        return "Monitor{" +
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
