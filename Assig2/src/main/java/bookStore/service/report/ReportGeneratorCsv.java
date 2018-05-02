package bookStore.service.report;

import bookStore.entity.Author;
import bookStore.entity.Book;
import bookStore.entity.Genre;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportGeneratorCsv implements ReportGenerator {
    @Override
    public void generateReport(List<Book> books) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File("C:\\Users\\Vlad\\Documents\\BookReports\\CsvReport.csv"));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Id");
            stringBuilder.append(",");
            stringBuilder.append("Name");
            stringBuilder.append(",");
            stringBuilder.append("Author");
            stringBuilder.append('\n');

            for(Book book:books)
            {
                stringBuilder.append(book.getId());
                stringBuilder.append(",");
                stringBuilder.append(book.getTitle());
                stringBuilder.append(",");
                stringBuilder.append(book.getAuthor());
                stringBuilder.append(",");
                stringBuilder.append(book.getGenre());
                stringBuilder.append(",");
                stringBuilder.append(book.getPrice());
                stringBuilder.append('\n');
            }

            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws Exception {
        List<Book> books=new ArrayList<>();
        Author author = new Author("author");
        Genre genre = new Genre("genre");
        Book book = new Book("title",10, 15.2,author,genre);
        Book book2 = new Book("title2",5, 12.2,author,genre);
        books.add(book);
        books.add(book2);
        ReportGeneratorCsv cr=new ReportGeneratorCsv();
        cr.generateReport(books);
    }
}
