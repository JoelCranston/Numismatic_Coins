package kotlin.io.path

import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes

@WasExperimental(markerClass = [ExperimentalPathApi::class])
@SinceKotlin(version = "2.1")
public sealed interface FileVisitorBuilder {
   public abstract fun onPreVisitDirectory(function: (Path, BasicFileAttributes) -> FileVisitResult) {
   }

   public abstract fun onVisitFile(function: (Path, BasicFileAttributes) -> FileVisitResult) {
   }

   public abstract fun onVisitFileFailed(function: (Path, IOException) -> FileVisitResult) {
   }

   public abstract fun onPostVisitDirectory(function: (Path, IOException?) -> FileVisitResult) {
   }
}
