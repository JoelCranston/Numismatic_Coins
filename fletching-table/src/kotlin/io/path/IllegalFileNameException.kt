package kotlin.io.path

import java.nio.file.FileSystemException
import java.nio.file.Path

internal class IllegalFileNameException(file: Path, other: Path?, message: String?) : FileSystemException(
      file.toString(), if (other != null) other.toString() else null, message
   ) {
   public constructor(file: Path) : this(file, null, null)}
