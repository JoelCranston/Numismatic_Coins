package kotlinx.io.files

import java.io.File
import kotlinx.io.CoreKt
import kotlinx.io.Sink
import kotlinx.io.Source

public final val SystemPathSeparator: Char = File.separatorChar

public fun Path(path: String): Path {
   return new Path(new File(path));
}

@JvmName(name = "source")
@PublishedApi
internal fun Path.sourceHack(): Source {
   return CoreKt.buffered(FileSystemJvmKt.SystemFileSystem.source(`$this$sourceHack`));
}

@JvmName(name = "sink")
@PublishedApi
internal fun Path.sinkHack(): Sink {
   return CoreKt.buffered(FileSystem.sink$default(FileSystemJvmKt.SystemFileSystem, `$this$sinkHack`, false, 2, null));
}
