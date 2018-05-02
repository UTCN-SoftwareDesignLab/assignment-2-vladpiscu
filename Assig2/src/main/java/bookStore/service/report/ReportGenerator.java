package bookStore.service.report;

import bookStore.entity.Book;

import java.util.List;

public interface ReportGenerator {
    void generateReport(List<Book> books);
}
