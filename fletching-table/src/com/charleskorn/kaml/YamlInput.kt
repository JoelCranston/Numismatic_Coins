package com.charleskorn.kaml

import java.lang.annotation.Annotation
import java.util.LinkedHashMap
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.ContextAwareKt
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.modules.SerializersModule

public sealed class YamlInput protected constructor(node: YamlNode, yaml: Yaml, serializersModule: SerializersModule, configuration: YamlConfiguration)
   : AbstractDecoder {
   public final val node: YamlNode
   public final val yaml: Yaml

   public open var serializersModule: SerializersModule
      internal final set

   public final val configuration: YamlConfiguration

   init {
      this.node = node;
      this.yaml = yaml;
      this.serializersModule = serializersModule;
      this.configuration = configuration;
   }

   public override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T {
      try {
         return (T)super.decodeSerializableValue(deserializer);
      } catch (var3: SerializationException) {
         this.throwIfMissingRequiredPropertyException(var3);
         throw var3;
      }
   }

   private fun throwIfMissingRequiredPropertyException(e: SerializationException) {
      val var10000: Regex = missingFieldExceptionMessage;
      val var10001: java.lang.String = e.getMessage();
      val var3: MatchResult = var10000.matchEntire(var10001);
      if (var3 != null) {
         throw new MissingRequiredPropertyException(var3.getGroupValues().get(1), this.node.getPath(), e);
      }
   }

   public abstract fun getCurrentLocation(): Location {
   }

   public abstract fun getCurrentPath(): YamlPath {
   }

   @SourceDebugExtension(["SMAP\nYamlInput.kt\nKotlin\n*S Kotlin\n*F\n+ 1 YamlInput.kt\ncom/charleskorn/kaml/YamlInput$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 YamlNode.kt\ncom/charleskorn/kaml/YamlMap\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,226:1\n1#2:227\n227#3,2:228\n229#3,6:232\n295#4,2:230\n1761#4,3:245\n488#5,7:238\n*S KotlinDebug\n*F\n+ 1 YamlInput.kt\ncom/charleskorn/kaml/YamlInput$Companion\n*L\n175#1:228,2\n175#1:232,6\n175#1:230,2\n182#1:245,3\n179#1:238,7\n*E\n"])
   internal companion object {
      private final val missingFieldExceptionMessage: Regex

      private final val isContentBasedPolymorphic: Boolean
         private final get() {
            val `$this$any$iv`: java.lang.Iterable = `$this$isContentBasedPolymorphic`.getAnnotations();
            var var10000: Boolean;
            if (`$this$any$iv` is java.util.Collection && (`$this$any$iv` as java.util.Collection).isEmpty()) {
               var10000 = false;
            } else {
               val var4: java.util.Iterator = `$this$any$iv`.iterator();

               while (true) {
                  if (!var4.hasNext()) {
                     var10000 = false;
                     break;
                  }

                  if (var4.next() as Annotation is YamlContentPolymorphicSerializer.Marker) {
                     var10000 = true;
                     break;
                  }
               }
            }

            return var10000;
         }


      internal fun createFor(node: YamlNode, yaml: Yaml, context: SerializersModule, configuration: YamlConfiguration, descriptor: SerialDescriptor): YamlInput {
         if (descriptor.isInline()) {
            return this.createFor$kaml(node, yaml, context, configuration, descriptor.getElementDescriptor(0));
         } else {
            val var10000: YamlInput;
            if (node is YamlNull) {
               if (descriptor.getKind() is PolymorphicKind && !descriptor.isNullable()) {
                  throw new MissingTypeTagException((node as YamlNull).getPath());
               }

               var10000 = new YamlNullInput(node as YamlNull, yaml, context, configuration);
            } else if (node is YamlScalar) {
               if (descriptor.getKind() is PrimitiveKind || descriptor.getKind() is SerialKind.ENUM) {
                  var10000 = new YamlScalarInput(node as YamlScalar, yaml, context, configuration);
               } else if (descriptor.getKind() is SerialKind.CONTEXTUAL) {
                  var10000 = this.createContextual(node, yaml, context, configuration, descriptor);
               } else {
                  if (descriptor.getKind() !is PolymorphicKind) {
                     throw new IncorrectTypeException(
                        "Expected ${YamlInputKt.access$getFriendlyDescription(descriptor.getKind())}, but got a scalar value", (node as YamlScalar).getPath()
                     );
                  }

                  if (!this.isContentBasedPolymorphic(descriptor)) {
                     throw new MissingTypeTagException((node as YamlScalar).getPath());
                  }

                  var10000 = this.createContextual(node, yaml, context, configuration, descriptor);
               }
            } else if (node is YamlList) {
               val var7: SerialKind = descriptor.getKind();
               if (var7 is StructureKind.LIST) {
                  var10000 = new YamlListInput(node as YamlList, yaml, context, configuration);
               } else if (var7 is SerialKind.CONTEXTUAL) {
                  var10000 = this.createContextual(node, yaml, context, configuration, descriptor);
               } else {
                  if (var7 !is PolymorphicKind) {
                     throw new IncorrectTypeException(
                        "Expected ${YamlInputKt.access$getFriendlyDescription(descriptor.getKind())}, but got a list", (node as YamlList).getPath()
                     );
                  }

                  if (!this.isContentBasedPolymorphic(descriptor)) {
                     throw new MissingTypeTagException((node as YamlList).getPath());
                  }

                  var10000 = this.createContextual(node, yaml, context, configuration, descriptor);
               }
            } else if (node is YamlMap) {
               val var8: SerialKind = descriptor.getKind();
               if (var8 is StructureKind.CLASS || var8 == StructureKind.OBJECT.INSTANCE) {
                  var10000 = new YamlObjectInput(node as YamlMap, yaml, context, configuration);
               } else if (var8 is StructureKind.MAP) {
                  var10000 = new YamlMapInput(node as YamlMap, yaml, context, configuration);
               } else if (var8 is SerialKind.CONTEXTUAL) {
                  var10000 = this.createContextual(node, yaml, context, configuration, descriptor);
               } else {
                  if (var8 !is PolymorphicKind) {
                     throw new IncorrectTypeException(
                        "Expected ${YamlInputKt.access$getFriendlyDescription(descriptor.getKind())}, but got a map", (node as YamlMap).getPath()
                     );
                  }

                  if (this.isContentBasedPolymorphic(descriptor)) {
                     var10000 = this.createContextual(node, yaml, context, configuration, descriptor);
                  } else {
                     switch (YamlInput.Companion.WhenMappings.$EnumSwitchMapping$0[configuration.getPolymorphismStyle$kaml().ordinal()]) {
                        case 1:
                           throw new IncorrectTypeException(
                              "Encountered a polymorphic map descriptor but PolymorphismStyle is 'None'", (node as YamlMap).getPath()
                           );
                        case 2:
                           throw new MissingTypeTagException((node as YamlMap).getPath());
                        case 3:
                           var10000 = this.createPolymorphicMapDeserializer(node as YamlMap, yaml, context, configuration);
                           break;
                        default:
                           throw new NoWhenBranchMatchedException();
                     }
                  }
               }
            } else {
               if (node !is YamlTaggedNode) {
                  throw new NoWhenBranchMatchedException();
               }

               if (descriptor.getKind() is PolymorphicKind && configuration.getPolymorphismStyle$kaml() === PolymorphismStyle.None) {
                  throw new IncorrectTypeException("Encountered a tagged polymorphic descriptor but PolymorphismStyle is 'None'", node.getPath());
               }

               var10000 = if (descriptor.getKind() is PolymorphicKind && configuration.getPolymorphismStyle$kaml() === PolymorphismStyle.Tag)
                  new YamlPolymorphicInput(
                     (node as YamlTaggedNode).getTag(), node.getPath(), (node as YamlTaggedNode).getInnerNode(), yaml, context, configuration
                  )
                  else
                  this.createFor$kaml((node as YamlTaggedNode).getInnerNode(), yaml, context, configuration, descriptor);
            }

            return var10000;
         }
      }

      private fun createContextual(node: YamlNode, yaml: Yaml, context: SerializersModule, configuration: YamlConfiguration, descriptor: SerialDescriptor): YamlInput {
         val var6: SerialDescriptor = ContextAwareKt.getContextualDescriptor(context, descriptor);
         if (var6 != null) {
            val var7: YamlInput = YamlInput.Companion.createFor$kaml(node, yaml, context, configuration, var6);
            if (var7 != null) {
               return var7;
            }
         }

         return new YamlContextualInput(node, yaml, context, configuration);
      }

      private fun createPolymorphicMapDeserializer(node: YamlMap, yaml: Yaml, context: SerializersModule, configuration: YamlConfiguration): YamlPolymorphicInput {
         val desiredKey: java.lang.String = configuration.getPolymorphismPropertyName$kaml();
         val typeName: YamlNode = this.getValue(node, desiredKey);
         if (typeName is YamlList) {
            throw new InvalidPropertyValueException(desiredKey, "expected a string, but got a list", (typeName as YamlList).getPath(), null, 8, null);
         } else if (typeName is YamlMap) {
            throw new InvalidPropertyValueException(desiredKey, "expected a string, but got a map", (typeName as YamlMap).getPath(), null, 8, null);
         } else if (typeName is YamlNull) {
            throw new InvalidPropertyValueException(desiredKey, "expected a string, but got a null value", (typeName as YamlNull).getPath(), null, 8, null);
         } else if (typeName is YamlTaggedNode) {
            throw new InvalidPropertyValueException(desiredKey, "expected a string, but got a tagged value", typeName.getPath(), null, 8, null);
         } else if (typeName is YamlScalar) {
            return new YamlPolymorphicInput(
               (typeName as YamlScalar).getContent(), (typeName as YamlScalar).getPath(), this.withoutKey(node, desiredKey), yaml, context, configuration
            );
         } else {
            throw new NoWhenBranchMatchedException();
         }
      }

      private fun YamlMap.getValue(desiredKey: String): YamlNode {
         val `key$iv`: java.lang.String = desiredKey;
         val var8: java.util.Iterator = `$this$getValue`.getEntries().entrySet().iterator();

         var var10000: Any;
         while (true) {
            if (var8.hasNext()) {
               val `element$iv$iv`: Any = var8.next();
               if (!(((`element$iv$iv` as Entry).getKey() as YamlScalar).getContent() == `key$iv`)) {
                  continue;
               }

               var10000 = (YamlNode)`element$iv$iv`;
               break;
            }

            var10000 = null;
            break;
         }

         label28: {
            val var12: Entry = var10000 as Entry;
            if (var10000 as Entry != null) {
               val var14: YamlNode = var12.getValue() as YamlNode;
               if (var14 != null) {
                  var10000 = var14;
                  if (var14 !is YamlNode) {
                     var10000 = null;
                  }

                  if (var10000 == null) {
                     throw new IncorrectTypeException(
                        "Expected element to be ${(YamlNode::class).getSimpleName()} but is ${(var14.getClass()::class).getSimpleName()}", var14.getPath()
                     );
                  }
                  break label28;
               }
            }

            var10000 = null;
         }

         if (var10000 == null) {
            throw new MissingRequiredPropertyException(desiredKey, `$this$getValue`.getPath(), null, 4, null);
         } else {
            return var10000;
         }
      }

      private fun YamlMap.withoutKey(key: String): YamlMap {
         val `$this$filterKeys$iv`: java.util.Map = `$this$withoutKey`.getEntries();
         val `result$iv`: LinkedHashMap = new LinkedHashMap();

         for (Entry entry$iv : $this$filterKeys$iv.entrySet()) {
            if (!((`entry$iv`.getKey() as YamlScalar).getContent() == key)) {
               `result$iv`.put(`entry$iv`.getKey(), `entry$iv`.getValue());
            }
         }

         return YamlMap.copy$default(`$this$withoutKey`, `result$iv`, null, 2, null);
      }
   }
}
