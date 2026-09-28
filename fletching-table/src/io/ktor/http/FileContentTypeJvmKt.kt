package io.ktor.http

import io.ktor.http.ContentType.Companion
import java.io.File
import java.nio.file.Path
import kotlin.io.path.PathsKt

public fun Companion.defaultForFile(file: File): ContentType {
   return FileContentTypeKt.selectDefault(FileContentTypeKt.fromFileExtension(ContentType.Companion, FilesKt.getExtension(file)));
}

public fun Companion.defaultForPath(path: Path): ContentType {
   return FileContentTypeKt.selectDefault(FileContentTypeKt.fromFileExtension(ContentType.Companion, PathsKt.getExtension(path)));
}
