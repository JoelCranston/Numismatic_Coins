package kotlinx.serialization.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorKt
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind

@PublishedApi
@SourceDebugExtension(["SMAP\nEnums.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Enums.kt\nkotlinx/serialization/internal/EnumDescriptor\n+ 2 Platform.kt\nkotlinx/serialization/internal/PlatformKt\n+ 3 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,148:1\n16#2:149\n160#3:150\n1803#4,3:151\n*S KotlinDebug\n*F\n+ 1 Enums.kt\nkotlinx/serialization/internal/EnumDescriptor\n*L\n28#1:149\n46#1:150\n46#1:151,3\n*E\n"])
internal class EnumDescriptor(name: String, elementsCount: Int) : PluginGeneratedSerialDescriptor(name, null, elementsCount, 2) {
   public open val kind: SerialKind = SerialKind.ENUM.INSTANCE as SerialKind

   private final val elementDescriptors: Array<SerialDescriptor>
      private final get() {
         return this.elementDescriptors$delegate.getValue() as Array<SerialDescriptor>;
      }


   init {
      this.elementDescriptors$delegate = LazyKt.lazy(EnumDescriptor::elementDescriptors_delegate$lambda$0);
   }

   public override fun getElementDescriptor(index: Int): SerialDescriptor {
      return this.getElementDescriptors()[index];
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other == null) {
         return false;
      } else if (other !is SerialDescriptor) {
         return false;
      } else if ((other as SerialDescriptor).getKind() != SerialKind.ENUM.INSTANCE) {
         return false;
      } else if (!(this.getSerialName() == (other as SerialDescriptor).getSerialName())) {
         return false;
      } else {
         return Platform_commonKt.cachedSerialNames(this) == Platform_commonKt.cachedSerialNames(other as SerialDescriptor);
      }
   }

   public override fun toString(): String {
      return CollectionsKt.joinToString$default(SerialDescriptorKt.getElementNames(this), ", ", "${this.getSerialName()}(", ")", 0, null, null, 56, null);
   }

   public override fun hashCode(): Int {
      val result: Int = this.getSerialName().hashCode();
      val `$this$elementsHashCodeBy$iv`: java.lang.Iterable = SerialDescriptorKt.getElementNames(this);
      var `accumulator$iv$iv`: Int = 1;

      for (Object element$iv$iv : $this$elementsHashCodeBy$iv) {
         `accumulator$iv$iv` = 31 * `accumulator$iv$iv`
            + (if (`element$iv$iv` as java.lang.String != null) (`element$iv$iv` as java.lang.String).hashCode() else 0);
      }

      return 31 * result + `accumulator$iv$iv`;
   }

   @JvmStatic
   fun `elementDescriptors_delegate$lambda$0`(`$elementsCount`: Int, `$name`: java.lang.String, `this$0`: EnumDescriptor): Array<SerialDescriptor> {
      var var3: Int = 0;

      val var4: Array<SerialDescriptor>;
      for (var4 = new SerialDescriptor[$elementsCount]; var3 < $elementsCount; var3++) {
         var4[var3] = SerialDescriptorsKt.buildSerialDescriptor$default(
            "$`$name`.${`this$0`.getElementName(var3)}", StructureKind.OBJECT.INSTANCE, new SerialDescriptor[0], null, 8, null
         );
      }

      return var4;
   }
}
