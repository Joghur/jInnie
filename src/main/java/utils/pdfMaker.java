package utils;

import entities.Customer;
import entities.MasterData;
import entities.Ordrer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class pdfMaker {

    private File file;
    private PDDocument doc;
    private PDPage page;
    private PDPageContentStream contents;
    private List<String> textListe;

    public pdfMaker() throws IOException {
        file = new File("src/main/resources/pdf/EmptyPDF.pdf");
        doc = PDDocument.load(file);
        page = doc.getPage(0);
        contents = new PDPageContentStream(doc, page);
    }

    public static void main(String[] args) throws IOException {
//        new pdfMaker().invoicePDFFlow();
    }

    public void invoicePDFFlow(MasterData ma, List<String> ol1List,
            Customer cust, Ordrer or, float totalPrice) throws IOException {
        List<String> subtext = new ArrayList<>();
        List<String> subtext2 = new ArrayList<>();
        List<String> firmInfos = new ArrayList<>();
        List<String> firmAddress = new ArrayList<>();
        List<String> customerInfo = new ArrayList<>();
        List<String> totalPriceText = new ArrayList<>();

        imagePDF();

        // address field
        Collections.addAll(firmAddress, ma.getAddress().split(","));
        textsPDF(410, 700, firmAddress, PDType1Font.COURIER, 12);

        firmInfos.add("CVR-nr:          " + ma.getCvr());
        firmInfos.add("Telefon:         " + ma.getPhone());
        firmInfos.add("Email:           " + ma.getEmail());
        firmInfos.add("Konto:           " + ma.getAccount());
        for (int i = 0; i < 2; i++) {
            firmInfos.add("");
        }
        // info fields
        firmInfos.add("Kundenummer:     " + cust.getCustomerNumber());
        firmInfos.add("Kontakt:         " + cust.getCustomerContactName());
        firmInfos.add("Fakturanummer:   " + or.getInvoiceID());
        firmInfos.add("Dato:            " + or.getInvoiceDate());
        textsPDF(330, 630, firmInfos, PDType1Font.COURIER_BOLD, 12);

        // Customer address
        customerInfo.add("Kundenummer " + cust.getCustomerNumber());
        customerInfo.add("");
        customerInfo.add(cust.getCustomerFirmName());
        Collections.addAll(customerInfo, cust.getCustomerFirmAddress().split(","));
        textsPDF(40, 720, customerInfo, PDType1Font.COURIER, 12);

        // OrderLines header
        String text = "Ydelse"
                + "                                          Dato"
                + "         Antal"
                + "   Beløb";
        textPDF(30, 460, text, PDType1Font.COURIER_BOLD, 12);

        // OrderLines
        textsPDF(30, 440, ol1List, PDType1Font.COURIER, 12);

        // Total price
        totalPriceText.add("Total ex. moms");
        totalPriceText.add(String.format("%.2f", totalPrice));
        totalPriceText.add("");
        totalPriceText.add("Moms (25%)");
        totalPriceText.add(String.format("%.2f", totalPrice * 0.25));
        totalPriceText.add("");
        totalPriceText.add("Total inkl. moms DKK");
        totalPriceText.add(String.format("%.2f", totalPrice * 1.25));
        textsPDF(420, 220, totalPriceText, PDType1Font.COURIER_BOLD, 12);

        // Subtext
        subtext.add(ma.getName());
        subtext.add(ma.getAddress());
        subtext.add("Tlf: " + ma.getPhone());
        subtext.add("Email: " + ma.getEmail());
        textPDF(50, 50, String.join(" * ", subtext), PDType1Font.COURIER, 8);

        subtext2.add("CVR: " + ma.getCvr());
        subtext2.add("Bank: " + ma.getBank());
        subtext2.add("Konto: " + ma.getAccount());
        textPDF(130, 30, String.join(" * ", subtext2), PDType1Font.COURIER, 8);

        System.out.println("Content added");
        contents.close();
        doc.save("src/main/resources/pdf/sample.pdf");
        doc.close();
    }

    private void imagePDF() throws IOException {
        // Drawing the image in the PDF document
        PDImageXObject pdImage = PDImageXObject.createFromFile("src/main/resources/image/logo.png", doc);
        System.out.println("Image inserted");
        contents.drawImage(pdImage, 370, 720);
    }

    private void textPDF(float tx, float ty, String text, PDType1Font font, int fontSize) throws IOException {
        contents.beginText();
        contents.setFont(font, fontSize);
        contents.setLeading(14.5f);
        contents.newLineAtOffset(tx, ty);
        contents.showText(text);
        contents.endText();
    }

    private void textsPDF(float tx, float ty, List<String> texts, PDType1Font font, int fontSize) throws IOException {
        contents.beginText();
        contents.setFont(font, fontSize);
        contents.setLeading(14.5f);
        contents.newLineAtOffset(tx, ty);
        for (String text : texts) {
            contents.showText(text);
            contents.newLine();
        }
        contents.endText();
    }

}
