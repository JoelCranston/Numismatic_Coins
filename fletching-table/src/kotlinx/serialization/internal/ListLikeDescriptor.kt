package kotlinx.serialization.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind

@ExperimentalSerializationApi
@SourceDebugExtension(["SMAP\nCollectionDescriptors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CollectionDescriptors.kt\nkotlinx/serialization/internal/ListLikeDescriptor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,138:1\n1#2:139\n*E\n"])
internal sealed class ListLikeDescriptor protected constructor(elementDescriptor: SerialDescriptor) : SerialDescriptor {
   public final val elementDescriptor: SerialDescriptor

   public open val kind: SerialKind
      public open get() {
         return StructureKind.LIST.INSTANCE;
      }


   public open val elementsCount: Int

   init {
      this.elementDescriptor = elementDescriptor;
      this.elementsCount = 1;
   }

   public override fun getElementName(index: Int): String {
      return java.lang.String.valueOf(index);
   }

   public override fun getElementIndex(name: String): Int {
      val var10000: Int = StringsKt.toIntOrNull(name);
      if (var10000 != null) {
         return var10000;
      } else {
         throw new IllegalArgumentException("$name is not a valid list index");
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
         return this.elementDescriptor;
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is ListLikeDescriptor) {
         return false;
      } else {
         return this.elementDescriptor == (other as ListLikeDescriptor).elementDescriptor
            && this.getSerialName() == (other as ListLikeDescriptor).getSerialName();
      }
   }

   public override fun hashCode(): Int {
      return this.elementDescriptor.hashCode() * 31 + this.getSerialName().hashCode();
   }

   public override fun toString(): String {
      return "${this.getSerialName()}(${this.elementDescriptor})";
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
