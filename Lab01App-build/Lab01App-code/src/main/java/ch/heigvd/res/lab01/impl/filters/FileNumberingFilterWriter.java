package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer. When
 * filter encounters a line separator, it sends it to the decorated writer. It then
 * sends the line number and a tab character, before resuming the write process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

   private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
   private long lineNumber = 1;
   private char previousChr;

   public FileNumberingFilterWriter(Writer out) {
      super(out);
   }

   @Override
   public void write(String str, int off, int len) throws IOException {
      String nextLine[] = Utils.getNextLine(str.substring(off, off + len));
      String s = "";
      
      if(lineNumber == 1)
         s = (lineNumber++) + "\t";
      
      while(!nextLine[0].isEmpty()) {
         s += nextLine[0] + (lineNumber++) + "\t";
         nextLine = Utils.getNextLine(nextLine[1]);
      }     
      
      s += nextLine[1];
      out.write(s);
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {
      write(new String(cbuf).substring(off, off + len));
   }

   @Override
   public void write(int c) throws IOException { 
      char chr = Character.toChars(c)[0]; 
      
      String s = "";
      
      if(lineNumber == 1
              || previousChr == '\n'
              || (previousChr == '\r' && chr != '\n'))
         s += (lineNumber++) + "\t";
      
      out.write(s + chr);      
      previousChr = chr;
   }
}
