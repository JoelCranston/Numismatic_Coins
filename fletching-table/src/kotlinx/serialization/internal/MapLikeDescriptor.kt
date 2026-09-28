package kotlinx.serialization.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind

@SourceDebugExtension(["SMAP\nCollectionDescriptors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionDescriptors.kt\nkotlinx/serialization/internal/MapLikeDescriptor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,138:1\n1#2:139\n*E\n"])
internal sealed class MapLikeDescriptor protected constructor(serialName: String, keyDescriptor: SerialDescriptor, valueDescriptor: SerialDescriptor) :
   SerialDescriptor {
   public open val serialName: String
   public final val keyDescriptor: SerialDescriptor
   public final val valueDescriptor: SerialDescriptor

   public open val kind: SerialKind
      public open get() {
         return StructureKind.MAP.INSTANCE;
      }


   public open val elementsCount: Int

   init {
      this.serialName = serialName;
      this.keyDescriptor = keyDescriptor;
      this.valueDescriptor = valueDescriptor;
      this.elementsCount = 2;
   }

   public override fun getElementName(index: Int): String {
      return java.lang.String.valueOf(index);
   }

   public override fun getElementIndex(name: String): Int {
      val var10000: Int = StringsKt.toIntOrNull(name);
      if (var10000 != null) {
         return var10000;
      } else {
         throw new IllegalArgumentException("$name is not a valid map index");
      }
   }

   public override fun isElementOptional(index: Int): Boolean {
      if (index < 0) {
         throw new IllegalArgumentException(("Illegal index $index, ${this.getSerialName()} expects only non-negative indices").toString());
      } else {
         return false;
      }
   }

   public override fun getElementAnnotations(index: Int): List<Annotation> {
      if (index < 0) {
         throw new IllegalArgumentException(("Illegal index $index, ${this.getSerialName()} expects only non-negative indices").toString());
      } else {
         return CollectionsKt.emptyList();
      }
   }

   public override fun getElementDescriptor(index: Int): SerialDescriptor {
      if (index < 0) {
         throw new IllegalArgumentException(("Illegal index $index, ${this.getSerialName()} expects only non-negative indices").toString());
      } else {
         var var10000: SerialDescriptor;
         switch (index % 2) {
            case 0:
               var10000 = this.keyDescriptor;
               break;
            case 1:
               var10000 = this.valueDescriptor;
               break;
            default:
               throw new IllegalStateException("Unreached".toString());
         }

         return var10000;
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is MapLikeDescriptor) {
         return false;
      } else if (!(this.getSerialName() == (other as MapLikeDescriptor).getSerialName())) {
         return false;
      } else if (!(this.keyDescriptor == (other as MapLikeDescriptor).keyDescriptor)) {
         return false;
      } else {
         return this.valueDescriptor == (other as MapLikeDescriptor).valueDescriptor;
      }
   }

   public override fun hashCode(): Int {
      return 31 * (31 * this.getSerialName().hashCode() + this.keyDescriptor.hashCode()) + this.valueDescriptor.hashCode();
   }

   public override fun toString(): String {
      return "${this.getSerialName()}(${this.keyDescriptor}, ${this.valueDescriptor})";
   }

   override fun isNullable(): Boolean {
      return SerialDescriptor.super.isNullable();
   }

   override fun isInline(): Boolean {
      return SerialDescriptor.super.isInline();
   }

   override fun getAnnotations(): MutableList<java.lang.annotation.Annotation> {
      return SerialDescriptor.super.getAnnotations();
   }
}
