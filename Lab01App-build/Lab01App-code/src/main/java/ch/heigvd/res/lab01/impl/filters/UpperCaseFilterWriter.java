package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
     out.write(str.substring(off, off + len).toUpperCase());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
     out.write(new String(cbuf).substring(off, off + len).toUpperCase());
  }

  @Override
  public void write(int c) throws IOException {
     out.write(Character.toUpperCase(c));
  }
  
  /* Bien implémenter chacune des 3 méthodes */
  /* Implementer uppercasefilterWriter */
  /* Avant de propager l'appel sur le flux de sortie décoré, on convertit ces valeurs en uppercase */

}
