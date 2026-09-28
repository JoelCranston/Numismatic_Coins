package kotlinx.serialization.descriptors

import java.util.ArrayList
import java.util.HashSet
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.ExperimentalSerializationApi

@SourceDebugExtension(["SMAP\nSerialDescriptors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerialDescriptors.kt\nkotlinx/serialization/descriptors/ClassSerialDescriptorBuilder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,393:1\n1#2:394\n*E\n"])
public class ClassSerialDescriptorBuilder internal constructor(serialName: String) {
   public final val serialName: String

   @ExperimentalSerializationApi
   @Deprecated(
      message = "isNullable inside buildSerialDescriptor is deprecated. Please use SerialDescriptor.nullable extension on a builder result.",
      level = DeprecationLevel.ERROR
   )
   public final var isNullable: Boolean

   @ExperimentalSerializationApi
   public final var annotations: List<Annotation>

   internal final val elementNames: MutableList<String>
   private final val uniqueNames: MutableSet<String>
   internal final val elementDescriptors: MutableList<SerialDescriptor>
   internal final val elementAnnotations: MutableList<List<Annotation>>
   internal final val elementOptionality: MutableList<Boolean>

   init {
      this.serialName = serialName;
      this.annotations = CollectionsKt.emptyList();
      this.elementNames = new ArrayList<>();
      this.uniqueNames = new HashSet<>();
      this.elementDescriptors = new ArrayList<>();
      this.elementAnnotations = new ArrayList<>();
      this.elementOptionality = new ArrayList<>();
   }

   public fun element(elementName: String, descriptor: SerialDescriptor, annotations: List<Annotation> = CollectionsKt.emptyList(), isOptional: Boolean = false) {
      if (!this.uniqueNames.add(elementName)) {
         throw new IllegalArgumentException(("Element with name '$elementName' is already registered in ${this.serialName}").toString());
      } else {
         this.elementNames.add(elementName);
         this.elementDescriptors.add(descriptor);
         this.elementAnnotations.add(annotations);
         this.elementOptionality.add(isOptional);
      }
   }
}
