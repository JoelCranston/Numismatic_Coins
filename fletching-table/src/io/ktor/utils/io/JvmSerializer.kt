package io.ktor.utils.io

import java.io.Serializable

@InternalAPI
public interface JvmSerializer<T> : Serializable {
   public abstract fun jvmSerialize(value: Any): ByteArray {
   }

   public abstract fun jvmDeserialize(value: ByteArray): Any {
   }
}
