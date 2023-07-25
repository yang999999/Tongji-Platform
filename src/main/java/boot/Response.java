package boot;

import java.util.List;

public class Response {
    private List<Data> datas;
    private int page;
    private int pgPerCnt;
    private int resultFlag;
    private int tatol;

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPgPerCnt() {
        return pgPerCnt;
    }

    public void setPgPerCnt(int pgPerCnt) {
        this.pgPerCnt = pgPerCnt;
    }

    public int getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(int resultFlag) {
        this.resultFlag = resultFlag;
    }

    public int getTatol() {
        return tatol;
    }

    public void setTatol(int tatol) {
        this.tatol = tatol;
    }



}

