package tesina.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import tesina.data.Biglietto;

public class GeneratoreBigliettoPDF {
	
	private Biglietto biglietto;
 
    public static final String SRC = "D:/TICKETS/cinema_ticket_form.pdf";
    private String DEST = "D:/TICKETS/EMAIL/PRENOTAZIONE/ticket_NUMBER.pdf";
 
    public GeneratoreBigliettoPDF(Biglietto b) {
    	biglietto = b;
    }
    
    public void generaPDF() throws DocumentException, IOException {
    	System.out.println(SRC);
        PdfReader reader = new PdfReader(SRC);
        String dest = DEST.replace("NUMBER", biglietto.getNumero_biglietto());
        dest = dest.replace("EMAIL", biglietto.getEmail());
        dest = dest.replace("PRENOTAZIONE", biglietto.getNumero_biglietto().split("-")[0]);
        
        File theDir = new File("D:/TICKETS/"+biglietto.getEmail()+"/"+biglietto.getNumero_biglietto().split("-")[0]);
        theDir.mkdirs();
        
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream(dest));
        AcroFields fields = stamper.getAcroFields();
        fields.setField("data_1", biglietto.getData());
        fields.setField("data_2", biglietto.getData());
        fields.setField("posto_1", biglietto.getPosto());
        fields.setField("posto_2", biglietto.getPosto());
        fields.setField("sala_1", String.valueOf(biglietto.getSala()));
        fields.setField("sala_2", String.valueOf(biglietto.getSala()));
        fields.setField("cinema_1", biglietto.getCinema());
        fields.setField("cinema_2", biglietto.getCinema());
        fields.setField("numero_biglietto_2", biglietto.getNumero_biglietto());
        fields.setField("numero_biglietto_1", biglietto.getNumero_biglietto());
        fields.setField("titolo_film", biglietto.getTitolo());
        
        fields.setFieldProperty( "data_1", "setfflags", PdfFormField.FF_READ_ONLY, null );
        fields.setFieldProperty( "data_2", "setfflags", PdfFormField.FF_READ_ONLY, null );
        fields.setFieldProperty( "posto_1", "setfflags", PdfFormField.FF_READ_ONLY, null );
        fields.setFieldProperty( "posto_2", "setfflags", PdfFormField.FF_READ_ONLY, null );
        fields.setFieldProperty( "sala_1", "setfflags", PdfFormField.FF_READ_ONLY, null );
        fields.setFieldProperty( "sala_2", "setfflags", PdfFormField.FF_READ_ONLY, null );
        fields.setFieldProperty( "cinema_1", "setfflags", PdfFormField.FF_READ_ONLY, null );
        fields.setFieldProperty( "cinema_2", "setfflags", PdfFormField.FF_READ_ONLY, null );
        fields.setFieldProperty( "numero_biglietto_2", "setfflags", PdfFormField.FF_READ_ONLY, null );
        fields.setFieldProperty( "numero_biglietto_1", "setfflags", PdfFormField.FF_READ_ONLY, null );
        fields.setFieldProperty( "titolo_film", "setfflags", PdfFormField.FF_READ_ONLY, null );
        
        stamper.close();
        reader.close();
    }
}