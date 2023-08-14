package bean;

import boot.GetDataToLocal;

import java.io.IOException;
import java.util.HashMap;

// 获取拿远端数据时，传参对应的表面沉降需要的所有参数
public class CMapper {
    public static void main(String[] args) throws IOException {
        GetDataToLocal getDataToLocal = new GetDataToLocal();
        getDataToLocal.getSettlementDataToLocal("2023-04-18 00:00:00","2023-04-19 00:00:00","C10-1");
        getDataToLocal.getSettlementDataToLocal("2023-04-18 00:00:00","2023-04-19 00:00:00","C10-2");
    }
    // 通过dInstal拿到沉降点所需的传参
    public static HashMap<String, CGetDataParam> dInstalToParam = new HashMap<>();

    public static HashMap<String, String> dInstalToExcelJName = new HashMap<>();
    static {
        // 北一区
        CGetDataParam c11 = new CGetDataParam("基点", "284E622A21220972", "C1-1", 120.81, 370.93);
        CGetDataParam c12 = new CGetDataParam("基点", "284E622A21220972", "C1-2", 120.81, 491.49);
        CGetDataParam c13 = new CGetDataParam("基点", "284E622A21220972", "C1-3", 120.81, 409.73);
        CGetDataParam c21 = new CGetDataParam("基点", "284E622A21220972", "C2-1", 120.81, 418.39);
        CGetDataParam c22 = new CGetDataParam("基点", "284E622A21220972", "C2-2", 120.81, 493.66);
        // 北二区
        CGetDataParam c41 = new CGetDataParam("基点", "289B0F040D000026", "C4-1", 118.45, 369.97);
        CGetDataParam c53 = new CGetDataParam("基点", "289B0F040D000026", "C5-3", 118.45, 334.46);
        CGetDataParam c52 = new CGetDataParam("基点", "289B0F040D000026", "C5-2", 118.45, 443.15);
        CGetDataParam c51 = new CGetDataParam("基点", "289B0F040D000026", "C5-1", 118.45, 422.49);
        CGetDataParam c31 = new CGetDataParam("基点", "289B0F040D000026", "C3-1", 118.45, 549.58);
        CGetDataParam c32 = new CGetDataParam("基点", "289B0F040D000026", "C3-2", 118.45, 460.27);
        CGetDataParam c33 = new CGetDataParam("基点", "289B0F040D000026", "C3-3", 118.45, 382.82);
        // 北三区
        CGetDataParam c61 = new CGetDataParam("基点", "280A5CD524220662", "C6-1", 117.58, 438.53);
        CGetDataParam c71 = new CGetDataParam("基点", "280A5CD524220662", "C7-1", 117.58, 443.09);
        CGetDataParam c81 = new CGetDataParam("基点", "280A5CD524220662", "C8-1", 117.58, 409.67);
        // 南区
        CGetDataParam c91 = new CGetDataParam("基点", "2816B9040D000055", "C9-1", 92.48, 591.41);
        CGetDataParam c92 = new CGetDataParam("基点", "2816B9040D000055", "C9-2", 92.48, 478.02);
        CGetDataParam c93 = new CGetDataParam("基点", "2816B9040D000055", "C9-3", 92.48, 447.10);
        CGetDataParam c101 = new CGetDataParam("基点", "2816B9040D000055", "C10-1", 92.48, 523.60);
        CGetDataParam c102 = new CGetDataParam("基点", "2816B9040D000055", "C10-2", 92.48, 485.32);
        CGetDataParam c103 = new CGetDataParam("基点", "2816B9040D000055", "C10-3", 92.48, 472.98);

        dInstalToParam.put("C1-1", c11);
        dInstalToParam.put("C1-2", c12);
        dInstalToParam.put("C1-3", c13);
        dInstalToParam.put("C2-1", c21);
        dInstalToParam.put("C2-2", c22);
        dInstalToParam.put("C3-1", c31);
        dInstalToParam.put("C3-2", c32);
        dInstalToParam.put("C3-3", c33);
        dInstalToParam.put("C4-1", c41);
        dInstalToParam.put("C5-1", c51);
        dInstalToParam.put("C5-2", c52);
        dInstalToParam.put("C5-3", c53);
        dInstalToParam.put("C6-1", c61);
        dInstalToParam.put("C7-1", c71);
        dInstalToParam.put("C8-1", c81);
        dInstalToParam.put("C9-1", c91);
        dInstalToParam.put("C9-2", c92);
        dInstalToParam.put("C9-3", c93);
        dInstalToParam.put("C10-1", c101);
        dInstalToParam.put("C10-2", c102);
        dInstalToParam.put("C10-3", c103);

        // 根据测点拿出对应区域保存基点excel的名字
        dInstalToExcelJName.put("C1-1", "北一基点");
        dInstalToExcelJName.put("C1-2", "北一基点");
        dInstalToExcelJName.put("C1-3", "北一基点");
        dInstalToExcelJName.put("C2-1", "北一基点");
        dInstalToExcelJName.put("C2-2", "北一基点");
        dInstalToExcelJName.put("C3-1", "北二基点");
        dInstalToExcelJName.put("C3-2", "北二基点");
        dInstalToExcelJName.put("C3-3", "北二基点");
        dInstalToExcelJName.put("C4-1", "北二基点");
        dInstalToExcelJName.put("C5-1", "北二基点");
        dInstalToExcelJName.put("C5-2", "北二基点");
        dInstalToExcelJName.put("C5-3", "北二基点");
        dInstalToExcelJName.put("C6-1", "北三基点");
        dInstalToExcelJName.put("C7-1", "北三基点");
        dInstalToExcelJName.put("C8-1", "北三基点");
        dInstalToExcelJName.put("C9-1", "南区基点");
        dInstalToExcelJName.put("C9-2", "南区基点");
        dInstalToExcelJName.put("C9-3", "南区基点");
        dInstalToExcelJName.put("C10-1", "南区基点");
        dInstalToExcelJName.put("C10-2", "南区基点");
        dInstalToExcelJName.put("C10-3", "南区基点");
    }
}
