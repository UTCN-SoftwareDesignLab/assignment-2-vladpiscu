package bookStore.service.report;

import bookStore.entity.Author;
import bookStore.entity.Book;
import bookStore.entity.Genre;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportGeneratorPdf implements ReportGenerator {

    @Override
    public void generateReport(List<Book> books) {

        PDDocument document = new PDDocument();
        PDPage page =new PDPage();
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont( PDType1Font.TIMES_ROMAN, 11 );
            contentStream.setLeading(14);
            contentStream.newLineAtOffset(150, 700);
            int rowsNb = 0;
            for(Book book:books)
            {
                contentStream.showText(book.toString());
                contentStream.newLine();
                rowsNb++;
                if(rowsNb%45==0)
                {
                    contentStream.endText();
                    contentStream.close();
                    document.addPage(page);
                    page=new PDPage();
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.beginText();
                    contentStream.setFont( PDType1Font.TIMES_ROMAN, 11 );
                    contentStream.setLeading(14);
                    contentStream.newLineAtOffset(150, 700);
                }
            }
            contentStream.endText();
            contentStream.close();
            document.addPage(page);
            document.save("C:\\Users\\Vlad\\Documents\\BookReports\\PdfReport.pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        List<Book> books=new ArrayList<>();
        Author author = new Author("author");
        Genre genre = new Genre("genre");
        for(int i = 0; i < 200; i++) {
            Book book = new Book("title", 10, 15.2, author, genre);
            books.add(book);
        }
        ReportGeneratorPdf reportGeneratorPdf=new ReportGeneratorPdf();
        reportGeneratorPdf.generateReport(books);
    }
}
