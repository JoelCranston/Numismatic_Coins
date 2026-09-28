package kotlinx.io.files

import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.StandardCopyOption

private class NioMover : Mover {
   @AnimalSnifferIgnore
   public override fun move(source: Path, destination: Path) {
      if (!source.getFile$kotlinx_io_core().exists()) {
         throw new FileNotFoundException("Source file does not exist: ${source.getFile$kotlinx_io_core()}");
      } else {
         try {
            Files.move(
               source.getFile$kotlinx_io_core().toPath(),
               destination.getFile$kotlinx_io_core().toPath(),
               StandardCopyOption.ATOMIC_MOVE,
               StandardCopyOption.REPLACE_EXISTING
            );
         } catch (var5: java.lang.Throwable) {
            if (var5 is IOException) {
               throw var5;
            } else {
               throw new IOException("Move failed", var5);
            }
         }
      }
   }
}
