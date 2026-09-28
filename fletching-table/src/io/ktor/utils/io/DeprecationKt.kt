package io.ktor.utils.io

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.Utf8Kt

internal const val IO_DEPRECATION_MESSAGE: String =
   "\n    We're migrating to the new kotlinx-io library.\n    This declaration is deprecated and will be removed in Ktor 4.0.0\n    If you have any problems with migration, please contact us in \n    https://youtrack.jetbrains.com/issue/KTOR-6030/Migrate-to-new-kotlinx.io-library\n    "

public fun Source.readText(): String {
   return Utf8Kt.readString(`$this$readText`);
}

@Deprecated(message = "Use close() instead", replaceWith = @ReplaceWith(expression = "close()", imports = []))
public fun Sink.release() {
   `$this$release`.close();
}
