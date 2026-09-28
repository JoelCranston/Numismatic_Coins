package kotlinx.serialization.internal

import java.util.Arrays
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
@SourceDebugExtension(["SMAP\nEnums.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Enums.kt\nkotlinx/serialization/internal/EnumSerializer\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,148:1\n13472#2,2:149\n*S KotlinDebug\n*F\n+ 1 Enums.kt\nkotlinx/serialization/internal/EnumSerializer\n*L\n120#1:149,2\n*E\n"])
internal class EnumSerializer<T extends java.lang.Enum<T>>(serialName: String, vararg values: Any) : KSerializer<T> {
   private final val values: Array<Any>
   private final var overriddenDescriptor: SerialDescriptor?

   public open val descriptor: SerialDescriptor
      public open get() {
         return this.descriptor$delegate.getValue() as SerialDescriptor;
      }


   init {
      this.values = (T[])values;
      this.descriptor$delegate = LazyKt.lazy(EnumSerializer::descriptor_delegate$lambda$0);
   }

   internal constructor(serialName: String, vararg values: Any, descriptor: SerialDescriptor) : this(serialName, (T[])values) {
      this.overriddenDescriptor = descriptor;
   }

   private fun createUnmarkedDescriptor(serialName: String): SerialDescriptor {
      val d: EnumDescriptor = new EnumDescriptor(serialName, this.values.length);

      val `$this$forEach$iv`: Any;
      for (Object element$iv : $this$forEach$iv) {
         PluginGeneratedSerialDescriptor.addElement$default(d, `element$iv`.name(), false, 2, null);
      }

      return d;
   }

   public open fun serialize(encoder: Encoder, value: Any) {
      val index: Int = ArraysKt.indexOf(this.values, value);
      if (index == -1) {
         val var10002: StringBuilder = new StringBuilder()
            .append(value)
            .append(" is not a valid enum ")
            .append(this.getDescriptor().getSerialName())
            .append(", must be one of ");
         val var10003: java.lang.String = Arrays.toString((Object[])this.values);
         throw new SerializationException(var10002.append(var10003).toString());
      } else {
         encoder.encodeEnum(this.getDescriptor(), index);
      }
   }

   public open fun deserialize(decoder: Decoder): Any {
      val index: Int = decoder.decodeEnum(this.getDescriptor());
      if (0 > index || index >= this.values.length) {
         throw new SerializationException("$index is not among valid ${this.getDescriptor().getSerialName()} enum values, values size is ${this.values.length}");
      } else {
         return this.values[index];
      }
   }

   public override fun toString(): String {
      return "kotlinx.serialization.internal.EnumSerializer<${this.getDescriptor().getSerialName()}>";
   }

   @JvmStatic
   fun `descriptor_delegate$lambda$0`(`this$0`: EnumSerializer, `$serialName`: java.lang.String): SerialDescriptor {
      var var10000: SerialDescriptor = `this$0`.overriddenDescriptor;
      if (`this$0`.overriddenDescriptor == null) {
         var10000 = `this$0`.createUnmarkedDescriptor(`$serialName`);
      }

      return var10000;
   }
}
