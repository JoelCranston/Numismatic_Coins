package io.ktor.util.internal

public abstract class OpDescriptor {
   public abstract fun perform(affected: Any?): Any? {
   }
}
