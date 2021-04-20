package com.danskeit.srs2.util;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import com.danskeit.srs2.bean.*;

public class ReservationPDFExport {
	ReservationBean rb;
	private List<PassengerBean> passengers;
    
    public ReservationPDFExport(List<PassengerBean> passengers,ReservationBean rb) {
        this.passengers = passengers;
        this.rb = rb;
    }
 
   
    
    private void writeTableHeader2(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLACK);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Name", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Age", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Gender", font));
        table.addCell(cell);
           
        
    }
     

    private void writeTableData2(PdfPTable table) {
        
       
        for (PassengerBean user : passengers) {
            table.addCell(String.valueOf(user.getName()));
            table.addCell(user.getAge());
            table.addCell(user.getGender());
        }
        
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);
        
        Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
        font1.setSize(12);
        font1.setColor(Color.BLACK);
         
        Paragraph p = new Paragraph("Reservation Details", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
       
         
        document.add(p);
        
        Paragraph p3 = new Paragraph("Reservation ID: " + rb.getReservationid(), font1);
        p3.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p6 = new Paragraph("User ID: " + rb.getCredentials().getUserid(), font1);
        p6.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p4 = new Paragraph("Source: " + rb.getSchedules().getRoute().getSource(), font1);
        p4.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p5 = new Paragraph("Destination: " + rb.getSchedules().getRoute().getDestination(), font1);
        p5.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p7 = new Paragraph("Booking Date: " + rb.getBookingdate(), font1);
        p7.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p8 = new Paragraph("Journey Date: " + rb.getJourneydate(), font1);
        p8.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p9 = new Paragraph("Seats: " + rb.getNoofseats(), font1);
        p9.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p10 = new Paragraph("Total Fare: " + rb.getTotalfare(), font1);
        p10.setAlignment(Paragraph.ALIGN_LEFT);
        p3.setSpacingBefore(20);
        p10.setSpacingAfter(20);
        document.add(p3);
        document.add(p6);
        document.add(p4);
        document.add(p5);
        document.add(p7);
        document.add(p8);
        document.add(p9);
        document.add(p10);
        
         
        
        
       
        PdfPTable table1 = new PdfPTable(3);
        table1.setWidthPercentage(100f);
        table1.setWidths(new float[] {1.5f, 3.5f, 3.0f});
        table1.setSpacingBefore(10);
         
        
        Paragraph p2 = new Paragraph("Passenger Details", font);
        p2.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p2);
         
        
        writeTableHeader2(table1);
        writeTableData2(table1);
         
       
        document.add(table1);
        document.close();
         
    }
}
