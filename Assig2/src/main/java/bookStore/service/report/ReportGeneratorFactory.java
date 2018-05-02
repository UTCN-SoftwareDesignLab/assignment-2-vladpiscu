package bookStore.service.report;

public class ReportGeneratorFactory {
    public ReportGenerator getReportGenerator(String type){
        switch(type) {
            case "CSV":
                return new ReportGeneratorCsv();
            case "PDF":
                return new ReportGeneratorPdf();
            default:
                return null;
        }
    }
}
