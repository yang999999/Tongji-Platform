package bean;

public class CGetDataParam {
    private String dInstalJ; // 为String值："基点"
    private String dSenNumJ; // 不同区域基点对应的传感器编号
    private String dInstal; // 传感器安装位置
    private Double jBase; // 基准点的校准值
    private Double cBase; // 沉降测点的校准值

    public CGetDataParam(String dInstalJ, String dSenNum, String dInstal, Double jBase, Double cBase) {
        this.dInstalJ = dInstalJ;
        this.dSenNumJ = dSenNum;
        this.dInstal = dInstal;
        this.jBase = jBase;
        this.cBase = cBase;
    }

    public String getdInstalJ() {
        return dInstalJ;
    }

    public void setdInstalJ(String dInstalJ) {
        this.dInstalJ = dInstalJ;
    }

    public String getdSenNum() {
        return dSenNumJ;
    }

    public void setdSenNum(String dSenNum) {
        this.dSenNumJ = dSenNum;
    }

    public String getdInstal() {
        return dInstal;
    }

    public void setdInstal(String dInstal) {
        this.dInstal = dInstal;
    }

    public Double getjBase() {
        return jBase;
    }

    public void setjBase(Double jBase) {
        this.jBase = jBase;
    }

    public Double getcBase() {
        return cBase;
    }

    public void setcBase(Double cBase) {
        this.cBase = cBase;
    }

    @Override
    public String toString() {
        return "CGetDataParam{" +
                "dInstalJ='" + dInstalJ + '\'' +
                ", dSenNum='" + dSenNumJ + '\'' +
                ", dInstal='" + dInstal + '\'' +
                ", jBase=" + jBase +
                ", cBase=" + cBase +
                '}';
    }
}
