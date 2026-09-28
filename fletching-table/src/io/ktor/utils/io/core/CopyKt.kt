package io.ktor.utils.io.core

import kotlinx.io.Sink
import kotlinx.io.Source

@Deprecated(message = "Use transferTo instead", replaceWith = @ReplaceWith(expression = "output.transferTo(this)", imports = ["kotlinx.io.transferTo"]), level = DeprecationLevel.ERROR)
public fun Source.copyTo(output: Sink): Long {
   return `$this$copyTo`.transferTo(output);
}
