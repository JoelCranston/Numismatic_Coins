package io.ktor.utils.io

import java.io.Externalizable
import java.io.ObjectInput
import java.io.ObjectOutput

@PublishedApi
internal class DefaultJvmSerializerReplacement<T>(serializer: JvmSerializer<Any>?, value: Any?) : Externalizable {
   private final var serializer: JvmSerializer<Any>?
   private final var value: Any?

   init {
      this.serializer = serializer;
      this.value = (T)value;
   }

   public constructor() : this(null, null)
   public override fun writeExternal(out: ObjectOutput) {
      out.writeObject(this.serializer);
      val var10001: JvmSerializer = this.serializer;
      val var10002: Any = this.value;
      out.writeObject(var10001.jvmSerialize(var10002));
   }

   public override fun readExternal(`in`: ObjectInput) {
      var var10001: JvmSerializer = (JvmSerializer)`in`.readObject();
      this.serializer = var10001;
      var10001 = this.serializer;
      val var10002: Any = `in`.readObject();
      this.value = (T)var10001.jvmDeserialize(var10002 as ByteArray);
   }

   private fun readResolve(): Any {
      val var10000: Any = this.value;
      return var10000;
   }

   public companion object {
      private const val serialVersionUID: Long
   }
}
