package bookStore.service.report;

import bookStore.entity.Book;
import bookStore.service.BookService;
import bookStore.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    BookService bookService;

    public ReportServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void generateOutOfStockReport(String type) {
        List<Book> books = bookService.getAll().stream().filter(b -> b.getQuantity()==0).collect(Collectors.toList());
        ReportGeneratorFactory reportGeneratorFactory = new ReportGeneratorFactory();
        reportGeneratorFactory.getReportGenerator(type).generateReport(books);
    }
}
