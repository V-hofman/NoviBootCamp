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

    private boolean isColored = false;

    private Color rowColor = new Color(255,242,243);

    public PDFExporter(List<Classrooms> listRooms)
    {
        this.listRooms = listRooms;
    }

    //Write the header for the PDF file
    private void writeTableHeader(PdfPTable table)
    {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(rowColor.darker());
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID:", font));
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

    //Write the actual classroom data to a table
    private void writeTableData(PdfPTable table)
    {
        PdfPCell cell = new PdfPCell();
        for(Classrooms room : listRooms)
        {
            if(isColored)
            {
                cell.setBackgroundColor(rowColor);
            }else
            {
                cell.setBackgroundColor(Color.WHITE);
            }
            cell.setPhrase(new Phrase(String.valueOf(room.getClassID())));
            table.addCell(cell);

            cell.setPhrase(new Phrase(room.getClassName()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(room.getClassroomNR()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(room.getClassDate()));
            table.addCell(cell);

            cell.setPhrase(new Phrase(room.getClassTeacher()));
            table.addCell(cell);

            isColored = !isColored;
        }
    }

    //Here we export it to a PDF file
    public void export(HttpServletResponse response) throws DocumentException, IOException
    {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);

        Paragraph p = new Paragraph("List of classes", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 0.8f, 3.0f, 3.0f, 1.5f, 2.7f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}
