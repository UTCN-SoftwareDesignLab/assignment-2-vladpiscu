package bookStore.service.report;

import bookStore.entity.Book;

import java.util.List;

public interface ReportService {
    void generateOutOfStockReport(String type);
}
