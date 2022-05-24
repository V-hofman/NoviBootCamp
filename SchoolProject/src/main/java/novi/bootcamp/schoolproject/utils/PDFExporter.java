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

    //Create a list that we want to grab the data from
    private List<Classrooms> listRooms;

    //Used to toggle the background color for the rows
    private boolean isColored = false;

    //The actual color, same as the color of the box in the HTML
    private Color rowColor = new Color(255,242,243);

    public PDFExporter(List<Classrooms> listRooms)
    {
        this.listRooms = listRooms;
    }

    //Write the header for the PDF file
    private void writeTableHeader(PdfPTable table)
    {
        //Create a cell and have it colored a bit darker than the row and set some padding to it
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(rowColor.darker());
        cell.setPadding(5);

        //Have a nice font that should be supported on default and make the color white
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        //Here we add the actual cells with the text in them, this is the top bar
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
        //Create a cell to color it
        PdfPCell cell = new PdfPCell();

        //For each object in the list we create a row
        for(Classrooms room : listRooms)
        {
            //Check if this row should be colored and change the color
            if(isColored)
            {
                cell.setBackgroundColor(rowColor);
            }else
            {
                cell.setBackgroundColor(Color.WHITE);
            }

            //Here we write the data to the row
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

            //To make sure the background color changes
            isColored = !isColored;
        }
    }

    //Here we export it to a PDF file
    public void export(HttpServletResponse response) throws DocumentException, IOException
    {
        //Create a new document with A4 size
        Document document = new Document(PageSize.A4);

        //Create a writer to write to the file
        PdfWriter.getInstance(document, response.getOutputStream());

        //open the document on the stream
        document.open();

        //Set the font settings which we write it with
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);

        //Add a nice Title to the PDF
        Paragraph p = new Paragraph("List of classes", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        //Here we create the table and set the values we need
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 0.8f, 3.0f, 3.0f, 1.5f, 2.7f});
        table.setSpacingBefore(10);

        //Here we tell it write the header and the data
        writeTableHeader(table);
        writeTableData(table);

        //We add the table to the PDF file and close the stream
        document.add(table);
        document.close();
    }
}
