package io.ktor.client.content

import io.ktor.http.ContentType
import io.ktor.http.FileContentTypeKt
import io.ktor.util.PathKt
import java.io.File

public fun LocalFileContent(
   baseDir: File,
   relativePath: String,
   contentType: ContentType = FileContentTypeKt.defaultForFilePath(ContentType.Companion, relativePath)
): LocalFileContent {
   return new LocalFileContent(PathKt.combineSafe(baseDir, relativePath), contentType);
}

@JvmSynthetic
fun `LocalFileContent$default`(var0: File, var1: java.lang.String, var2: ContentType, var3: Int, var4: Any): LocalFileContent {
   if ((var3 and 4) != 0) {
      var2 = FileContentTypeKt.defaultForFilePath(ContentType.Companion, var1);
   }

   return LocalFileContent(var0, var1, var2);
}
