package boot;

// 萧山提供的数据接口返回的数据类数据ID
public class Data {
    private int dId;  // 数据ID
    private String dTime; //采集时间
    private int dPrjId; //项目ID
    private String dPrjName; //项目名称
    private String dStruct; //项目结构
    private String dDtu; //Dtu号码
    private String dGthType; //采集器类型
    private String dGthNum; //采集器编号
    private int dSenAddr; //传感器通道
    private String dInstal; //传感器安装位置
    private String dSenNum; //传感器编号
    private String dSenType; //传感器类型
    private String dTempr; //温度（保留两位小数的字符串）
    private String dM1; //数据1（保留两位小数的字符串）
    private String dM2; //数据2（保留两位小数的字符串）
    private String dUnit; //数据单位

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
