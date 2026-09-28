package kotlinx.io.files

import kotlinx.io.files.FileSystemJvmKt.SystemFileSystem.1

private final val mover: Mover by LazyKt.lazy(FileSystemJvmKt::mover_delegate$lambda$0)
   private final get() {
      return mover$delegate.getValue() as Mover;
   }


public final val SystemFileSystem: FileSystem = (new 1()) as FileSystem
public final val SystemTemporaryDirectory: Path
internal final val isWindows: Boolean

fun `mover_delegate$lambda$0`(): Mover {
   var var0: Mover;
   try {
      Class.forName("java.nio.file.Files");
      var0 = new NioMover();
   } catch (var2: ClassNotFoundException) {
      var0 = new kotlinx.io.files.FileSystemJvmKt.mover.2.1();
   }

   return var0;
}

@JvmSynthetic
fun `access$getMover`(): Mover {
   return getMover();
}
