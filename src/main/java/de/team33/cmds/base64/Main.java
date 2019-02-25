package de.team33.cmds.base64;

import static java.nio.file.StandardOpenOption.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;


public final class Main
{

  private static final byte[] NEW_LINE = "\r\n".getBytes(StandardCharsets.US_ASCII);

  public static void main(final String[] args) throws IOException
  {
    encode(args[0]);
  }

  private static void encode(final String path) throws IOException
  {
    final Base64.Encoder encoder = Base64.getMimeEncoder(80, NEW_LINE);
    try (final OutputStream out = Files.newOutputStream(Paths.get(path + ".base64"),
                                                        CREATE,
                                                        WRITE,
                                                        TRUNCATE_EXISTING))
    {
      Files.copy(Paths.get(path), encoder.wrap(out));
    }
  }
}
