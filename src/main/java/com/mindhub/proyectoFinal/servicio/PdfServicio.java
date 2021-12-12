package com.mindhub.proyectoFinal.servicio;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@Service
public class PdfServicio {


    public void generarPDF(HttpServletResponse respuesta,String nombre,String cancha,String fecha,String horaIngreso, String horaSalida, Double precio) throws IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento,respuesta.getOutputStream());
        documento.setMargins(0,0,15,0);
        documento.open();
        documento.addTitle("Ball D`or");
        Image logo = Image.getInstance("https://ballon-dor.herokuapp.com/web/assets/logo.png");
        logo.setAbsolutePosition(460,765);
        logo.scaleToFit(60,60);
        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuenteTitulo.setColor(Color.black);
        fuenteTitulo.setSize(28);
        Paragraph titulo = new Paragraph("Ball D`or\n\n",fuenteTitulo);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(logo);
        documento.add(titulo);
        Font fuenteSubTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuenteSubTitulo.setSize(18);
        fuenteSubTitulo.setColor(Color.WHITE);
        Font fuenteTexto = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuenteTexto.setSize(15);
        Font fuenteTabla = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuenteTexto.setSize(12);
        //TABLA TITULO
        PdfPTable tablaTitulo = new PdfPTable(1);
        PdfPCell celda = new PdfPCell(new Phrase("DATOS DE LA RESERVA",fuenteSubTitulo));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(30,81,40));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(12);
        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingBefore(15);
        tablaTitulo.setSpacingAfter(30);
        documento.add(tablaTitulo);


        //TABLA SUBTITULO
        PdfPTable tabla = new PdfPTable(6);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[] {1.2f,0.6f,0.7f,0.6f,0.6f,0.5f});
        PdfPCell celdaPrincipal = new PdfPCell();
        celdaPrincipal.setBackgroundColor(new Color(216,233,168));
        celdaPrincipal.setPadding(5);
        celdaPrincipal.setPhrase(new Phrase("NOMBRE",fuenteTexto));
        celdaPrincipal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPrincipal.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celdaPrincipal);
        celdaPrincipal.setPhrase(new Phrase("CANCHA",fuenteTexto));
        celdaPrincipal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPrincipal.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celdaPrincipal);
        celdaPrincipal.setPhrase(new Phrase("FECHA",fuenteTexto));
        celdaPrincipal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPrincipal.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celdaPrincipal);
        celdaPrincipal.setPhrase(new Phrase("INGRESO",fuenteTexto));
        celdaPrincipal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPrincipal.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celdaPrincipal);
        celdaPrincipal.setPhrase(new Phrase("SALIDA",fuenteTexto));
        celdaPrincipal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPrincipal.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celdaPrincipal);
        celdaPrincipal.setPhrase(new Phrase("PRECIO",fuenteTexto));
        celdaPrincipal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPrincipal.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celdaPrincipal);

        //CONTENIDO TABLA
        celdaPrincipal.setBackgroundColor(new Color(230,230,230));
        celdaPrincipal.setPadding(0);
        celdaPrincipal.setPaddingBottom(5);
        celdaPrincipal.setPaddingTop(5);
        celdaPrincipal.setPhrase(new Phrase(nombre,fuenteTabla));
        celdaPrincipal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPrincipal.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celdaPrincipal);
        celdaPrincipal.setPhrase(new Phrase(cancha,fuenteTabla));
        celdaPrincipal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPrincipal.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celdaPrincipal);
        celdaPrincipal.setPhrase(new Phrase(fecha,fuenteTabla));
        celdaPrincipal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPrincipal.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celdaPrincipal);
        celdaPrincipal.setPhrase(new Phrase(horaIngreso+":00hs",fuenteTabla));
        celdaPrincipal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPrincipal.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celdaPrincipal);
        celdaPrincipal.setPhrase(new Phrase(horaSalida+":00hs",fuenteTabla));
        celdaPrincipal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPrincipal.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celdaPrincipal);
        celdaPrincipal.setPhrase(new Phrase("$"+precio,fuenteTabla));
        celdaPrincipal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaPrincipal.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celdaPrincipal);

        documento.add(tabla);

        Font fuentePresentar = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
        fuentePresentar.setColor(Color.GRAY);
        Paragraph presentar = new Paragraph("*Presentar este comprobante en la cancha",fuentePresentar);
        presentar.setSpacingBefore(40);
        presentar.setAlignment(Element.ALIGN_CENTER);
        documento.add(presentar);


        documento.close();

    }
}
