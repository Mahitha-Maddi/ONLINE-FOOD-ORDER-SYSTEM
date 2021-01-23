/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.View;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.mycompany.pojo.OrderItem;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.view.document.AbstractPdfView;

/**
 *
 * @author mahit
 */
public class MyPdfInvoiceView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        response.addHeader("Content-Disposition", "attachment;filename=download.pdf");
        document.open();
        Paragraph header = new Paragraph(new Chunk("Invoice of the Customer", FontFactory.getFont(FontFactory.HELVETICA, 20)));
        document.add(header);

        double total = 0;
        ArrayList<OrderItem> orderItemsList = (ArrayList<OrderItem>) session.getAttribute("orderItemsList1");
        if (orderItemsList != null) {

            for (OrderItem orderItem : orderItemsList) {
                total = total + orderItem.getTotalPrice();

            }
        }

        for (OrderItem item : orderItemsList) {
            Paragraph paragraph = new Paragraph("Item Name: " + item.getItemName());
            document.add(paragraph);
            Paragraph paragraph2 = new Paragraph("Item Price: " + item.getItemPrice());
            document.add(paragraph2);
            Paragraph paragraph3 = new Paragraph("Item Quantity: " + item.getQuantity());
            document.add(paragraph3);
            Paragraph paragraph1 = new Paragraph("Total Price: " + item.getTotalPrice());
            document.add(paragraph1);
            Paragraph paragraph5 = new Paragraph("---------------------------------------------------------------------------------------");
            document.add(paragraph5);
        }

        Paragraph totalAmount = new Paragraph("Total Amount Paid: " + total);
        document.add(totalAmount);
        
        document.close();
    }

}
