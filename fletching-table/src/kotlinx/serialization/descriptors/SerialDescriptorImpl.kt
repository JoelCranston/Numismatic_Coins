package kotlinx.serialization.descriptors

import java.util.ArrayList
import java.util.Arrays
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.internal.CachedNames
import kotlinx.serialization.internal.Platform_commonKt
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptorKt

@SourceDebugExtension(["SMAP\nSerialDescriptors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerialDescriptors.kt\nkotlinx/serialization/descriptors/SerialDescriptorImpl\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Platform.kt\nkotlinx/serialization/internal/PlatformKt\n+ 5 PluginGeneratedSerialDescriptor.kt\nkotlinx/serialization/internal/PluginGeneratedSerialDescriptorKt\n*L\n1#1,393:1\n37#2:394\n36#2,3:395\n37#2:398\n36#2,3:399\n1563#3:402\n1634#3,3:403\n16#4:406\n16#4:407\n16#4:408\n21#4:409\n107#5,10:410\n*S KotlinDebug\n*F\n+ 1 SerialDescriptors.kt\nkotlinx/serialization/descriptors/SerialDescriptorImpl\n*L\n368#1:394\n368#1:395,3\n370#1:398\n370#1:399,3\n372#1:402\n372#1:403,3\n376#1:406\n378#1:407\n379#1:408\n380#1:409\n383#1:410,10\n*E\n"])
internal class SerialDescriptorImpl(serialName: String,
      kind: SerialKind,
      elementsCount: Int,
      typeParameters: List<SerialDescriptor>,
      builder: ClassSerialDescriptorBuilder
   ) :
   SerialDescriptor,
   CachedNames {
   public open val serialName: String
   public open val kind: SerialKind
   public open val elementsCount: Int
   public open val annotations: List<Annotation>
   public open val serialNames: Set<String>
   private final val elementNames: Array<String>
   private final val elementDescriptors: Array<SerialDescriptor>
   private final val elementAnnotations: Array<List<Annotation>>
   private final val elementOptionality: BooleanArray
   private final val name2Index: Map<String, Int>
   private final val typeParametersDescriptors: Array<SerialDescriptor>

   private final val _hashCode: Int
      private final get() {
         return (this._hashCode$delegate.getValue() as java.lang.Number).intValue();
      }


   init {
      this.serialName = serialName;
      this.kind = kind;
      this.elementsCount = elementsCount;
      this.annotations = builder.getAnnotations();
      this.serialNames = CollectionsKt.toHashSet(builder.getElementNames$kotlinx_serialization_core());
      this.elementNames = builder.getElementNames$kotlinx_serialization_core().toArray(new java.lang.String[0]);
      this.elementDescriptors = Platform_commonKt.compactArray(builder.getElementDescriptors$kotlinx_serialization_core());
      this.elementAnnotations = builder.getElementAnnotations$kotlinx_serialization_core().toArray(new java.util.List[0]);
      this.elementOptionality = CollectionsKt.toBooleanArray(builder.getElementOptionality$kotlinx_serialization_core());
      val var18: java.lang.Iterable = ArraysKt.withIndex(this.elementNames);
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var18, 10));

      for (Object item$iv$iv : var18) {
         `destination$iv$iv`.add(TuplesKt.to((`item$iv$iv` as IndexedValue).getValue(), (`item$iv$iv` as IndexedValue).getIndex()));
      }

      this.name2Index = MapsKt.toMap(`destination$iv$iv`);
      this.typeParametersDescriptors = Platform_commonKt.compactArray(typeParameters);
      this._hashCode$delegate = LazyKt.lazy(SerialDescriptorImpl::_hashCode_delegate$lambda$1);
   }

   public override fun getElementName(index: Int): String {
      return this.elementNames[index];
   }

   public override fun getElementIndex(name: String): Int {
      val var10000: Int = this.name2Index.get(name);
      return var10000 ?: -3;
   }

   public override fun getElementAnnotations(index: Int): List<Annotation> {
      return this.elementAnnotations[index];
   }

   public override fun getElementDescriptor(index: Int): SerialDescriptor {
      return this.elementDescriptors[index];
   }

   public override fun isElementOptional(index: Int): Boolean {
      return this.elementOptionality[index];
   }

   public override operator fun equals(other: Any?): Boolean {
      val `$this$equalsImpl$iv`: SerialDescriptor = this;
      val `other$iv`: Any = other;
      var var10000: Boolean;
      if (`$this$equalsImpl$iv` === other) {
         var10000 = true;
      } else if (other !is SerialDescriptorImpl) {
         var10000 = false;
      } else if (!(`$this$equalsImpl$iv`.getSerialName() == (other as SerialDescriptor).getSerialName())) {
         var10000 = false;
      } else if (!Arrays.equals((Object[])this.typeParametersDescriptors, (Object[])(other as SerialDescriptorImpl).typeParametersDescriptors)) {
         var10000 = false;
      } else if (`$this$equalsImpl$iv`.getElementsCount() != (other as SerialDescriptor).getElementsCount()) {
         var10000 = false;
      } else {
         var `index$iv`: Int = 0;
         val var8: Int = `$this$equalsImpl$iv`.getElementsCount();

         while (true) {
            if (`index$iv` >= var8) {
               var10000 = true;
               break;
            }

            if (!(
               `$this$equalsImpl$iv`.getElementDescriptor(`index$iv`).getSerialName()
                  == (`other$iv` as SerialDescriptor).getElementDescriptor(`index$iv`).getSerialName()
            )) {
               var10000 = false;
               break;
            }

            if (!(
               `$this$equalsImpl$iv`.getElementDescriptor(`index$iv`).getKind() == (`other$iv` as SerialDescriptor).getElementDescriptor(`index$iv`).getKind()
            )) {
               var10000 = false;
               break;
            }

            `index$iv`++;
         }
      }

      return var10000;
   }

   public override fun hashCode(): Int {
      return this.get_hashCode();
   }

   public override fun toString(): String {
      return PluginGeneratedSerialDescriptorKt.toStringImpl(this);
   }

   override fun isNullable(): Boolean {
      return SerialDescriptor.super.isNullable();
   }

   override fun isInline(): Boolean {
      return SerialDescriptor.super.isInline();
   }

   @JvmStatic
   fun `_hashCode_delegate$lambda$1`(`this$0`: SerialDescriptorImpl): Int {
      return PluginGeneratedSerialDescriptorKt.hashCodeImpl(`this$0`, `this$0`.typeParametersDescriptors);
   }
}
