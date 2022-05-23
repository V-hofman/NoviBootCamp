package novi.bootcamp.schoolproject.utils;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import novi.bootcamp.schoolproject.models.Classrooms;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PDFExporter {

    private List<Classrooms> listRooms;

    public PDFExporter(List<Classrooms> listRooms)
    {
        this.listRooms = listRooms;
    }

    private void writeTableHeader(PdfPTable table)
    {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Class ID:", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Course:", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Location:", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Date:", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Teacher:", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table)
    {
        for(Classrooms room : listRooms)
        {
            table.addCell(String.valueOf(room.getClassID()));
            table.addCell(room.getClassName());
            table.addCell(room.getClassroomNR());
            table.addCell(room.getClassDate());
            table.addCell(room.getClassTeacher());
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException
    {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of classes", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 1.5f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}
