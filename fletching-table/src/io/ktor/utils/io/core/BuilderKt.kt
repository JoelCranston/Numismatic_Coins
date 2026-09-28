package io.ktor.utils.io.core

import kotlin.contracts.InvocationKind
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source

public inline fun buildPacket(block: (Sink) -> Unit): Source {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   val builder: Buffer = new Buffer();
   block.invoke(builder);
   return builder;
}
