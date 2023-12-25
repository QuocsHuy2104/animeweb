package utilities;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.PrintService;

public class Test implements Printable{
	public static void main(String [] args)
    {
        try
        {
            PrintService[] services = PrinterJob.lookupPrintServices();
            if (services.length > 0) 
            {
                 System.out.println("selected printer: " + services[0]);
                 PrinterJob pjob = PrinterJob.getPrinterJob();
                 pjob.setPrintService(services[0]);
                 pjob.setPrintable(new Test(), new PageFormat());
             }
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }
    }

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}
}
