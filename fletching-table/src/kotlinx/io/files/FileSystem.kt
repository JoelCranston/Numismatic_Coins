package kotlinx.io.files

import kotlinx.io.RawSink
import kotlinx.io.RawSource

public sealed interface FileSystem {
   public abstract fun exists(path: Path): Boolean {
   }

   public abstract fun delete(path: Path, mustExist: Boolean = true) {
   }

   public abstract fun createDirectories(path: Path, mustCreate: Boolean = false) {
   }

   public abstract fun atomicMove(source: Path, destination: Path) {
   }

   public abstract fun source(path: Path): RawSource {
   }

   public abstract fun sink(path: Path, append: Boolean = false): RawSink {
   }

   public abstract fun metadataOrNull(path: Path): FileMetadata? {
   }

   public abstract fun resolve(path: Path): Path {
   }

   public abstract fun list(directory: Path): Collection<Path> {
   }
}
