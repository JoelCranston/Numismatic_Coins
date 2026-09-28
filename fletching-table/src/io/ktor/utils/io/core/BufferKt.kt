package io.ktor.utils.io.core

import kotlinx.io.Buffer

public fun Buffer.canRead(): Boolean {
   return !`$this$canRead`.exhausted();
}

/** @deprecated */
@Deprecated(message = "\n    We're migrating to the new kotlinx-io library.\n    This declaration is deprecated and will be removed in Ktor 4.0.0\n    If you have any problems with migration, please contact us in \n    https://youtrack.jetbrains.com/issue/KTOR-6030/Migrate-to-new-kotlinx.io-library\n    ", replaceWith = @ReplaceWith(expression = "Buffer", imports = ["kotlinx.io.Buffer"]))
@JvmSynthetic
fun `Buffer$annotations`() {
}
