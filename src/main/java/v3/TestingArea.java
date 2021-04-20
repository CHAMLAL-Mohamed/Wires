package v3;

import v3.data.ConvertData;
import v3.data.ImportData;
import v3.data.ImportValues;
import v3.interfaces.Table;
import v3.models.*;
import v3.utils.JavaUtil;
import v3.utils.TemplateUtil;
import java.util.ArrayList;
import java.util.List;

public class TestingArea {
    static String maxFileName = "max.xlsx";

    static String crimpingFileName = "crimping.xlsx";
    static String crimpingConfigFileName = "crimping.conf";
    static String maxConfigFileName = "maxWireList.conf";
    static String maxTemplateName = "maxTemplate.xlsx";
    static String crimpingTemplateName = "crimping.xlsx";
    static String plausibilityFileName = "plausibility.xlsm";
    static String resultName = "maxResult.xlsx";

    public static void main(String[] args) throws Exception {

        Table table = ConvertData.convertSheetIntoTable(ImportData.importSheet(ImportValues.UPLOAD_FOLDER+maxFileName,0));
        Configs maxConfigs = ConvertData.convertStringToConfigs(ImportData.importText(ImportValues.CONFIG_FOLDER+maxConfigFileName));
        Template maxTemplate = TemplateUtil.loadTemplate(ImportValues.TEMPLATE_FOLDER+maxTemplateName,0);

        MaxTable maxTable = new MaxTable(maxConfigs,maxTemplate,table);

        Table crimpingTable = ConvertData.convertSheetIntoTable(ImportData.importSheet(ImportValues.UPLOAD_FOLDER+crimpingFileName,0));
        Configs crimpingConfigs = ConvertData.convertStringToConfigs(ImportData.importText(ImportValues.CONFIG_FOLDER+crimpingConfigFileName));
        Template crimpingTemplate = TemplateUtil.loadTemplate(ImportValues.TEMPLATE_FOLDER+crimpingTemplateName,0);
        DoubleTable doubleTable = new DoubleTable(crimpingConfigs,crimpingTemplate,crimpingTable);

        Table plausibilityTable = ConvertData.convertSheetIntoTable(ImportData.importSheet(ImportValues.UPLOAD_FOLDER+plausibilityFileName,"Plausibility"));
        PlausibilityTable plausibility = new PlausibilityTable(plausibilityTable);

//        v3.primitiveModels.Table convertedTable = TemplateUtil.setTemplate(table,template);
//        ExportData.exportTableToExcel(ImportValues.RESULT_FOLDER+resultName,"results",convertedTable);
//
//        List<String> values = new ArrayList<>();
//        values.add("abbc,");
//        values.add("abc15,");
//        values.add("dd9dde,");
//        values.add("dddd,");
//        System.out.println(JavaUtil.sortAndConcat(values));
    }
}
