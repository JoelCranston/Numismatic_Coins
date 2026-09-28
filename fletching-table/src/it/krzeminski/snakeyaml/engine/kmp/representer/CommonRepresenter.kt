package it.krzeminski.snakeyaml.engine.kmp.representer

import it.krzeminski.snakeyaml.engine.kmp.api.DumpSettings
import it.krzeminski.snakeyaml.engine.kmp.api.RepresentToNode
import it.krzeminski.snakeyaml.engine.kmp.common.FlowStyle
import it.krzeminski.snakeyaml.engine.kmp.common.NonPrintableStyle
import it.krzeminski.snakeyaml.engine.kmp.common.ScalarStyle
import it.krzeminski.snakeyaml.engine.kmp.exceptions.YamlEngineException
import it.krzeminski.snakeyaml.engine.kmp.internal.Math_jvmKt
import it.krzeminski.snakeyaml.engine.kmp.nodes.Node
import it.krzeminski.snakeyaml.engine.kmp.nodes.Tag
import it.krzeminski.snakeyaml.engine.kmp.representer.CommonRepresenter.representIterator.lambda.9..inlined.Iterable.1
import it.krzeminski.snakeyaml.engine.kmp.scanner.StreamReader
import java.util.LinkedHashMap
import kotlin.io.encoding.Base64
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass

@SourceDebugExtension(["SMAP\nCommonRepresenter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CommonRepresenter.kt\nit/krzeminski/snakeyaml/engine/kmp/representer/CommonRepresenter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,248:1\n223#1:249\n223#1:250\n223#1:251\n223#1:252\n223#1:253\n223#1:260\n223#1:261\n1285#2,2:254\n1299#2,4:256\n*S KotlinDebug\n*F\n+ 1 CommonRepresenter.kt\nit/krzeminski/snakeyaml/engine/kmp/representer/CommonRepresenter\n*L\n82#1:249\n92#1:250\n101#1:251\n111#1:252\n146#1:253\n157#1:260\n166#1:261\n155#1:254,2\n155#1:256,4\n*E\n"])
public open class CommonRepresenter(settings: DumpSettings) : BaseRepresenter(settings) {
   private final val settings: DumpSettings
   protected final val classTags: MutableMap<KClass<*>, Tag>
   private final val representString: RepresentToNode
   protected final val representBoolean: RepresentToNode
   protected final val representNumber: RepresentToNode
   private final val representList: RepresentToNode
   private final val representIterator: RepresentToNode
   private final val representArray: RepresentToNode
   private final val representPrimitiveArray: RepresentToNode
   private final val representMap: RepresentToNode
   private final val representSet: RepresentToNode
   private final val representEnum: RepresentToNode
   private final val representByteArray: RepresentToNode

   init {
      this.settings = settings;
      this.classTags = new LinkedHashMap<>();
      this.representString = CommonRepresenter::representString$lambda$0;
      this.representBoolean = CommonRepresenter::representBoolean$lambda$1;
      this.representNumber = CommonRepresenter::representNumber$lambda$4;
      this.representList = CommonRepresenter::representList$lambda$6;
      this.representIterator = CommonRepresenter::representIterator$lambda$9;
      this.representArray = CommonRepresenter::representArray$lambda$10;
      this.representPrimitiveArray = CommonRepresenter::representPrimitiveArray$lambda$11;
      this.representMap = CommonRepresenter::representMap$lambda$13;
      this.representSet = CommonRepresenter::representSet$lambda$16;
      this.representEnum = CommonRepresenter::representEnum$lambda$18;
      this.representByteArray = CommonRepresenter::representByteArray$lambda$19;
      this.representers
         .putAll(
            MapsKt.mapOf(
               new Pair[]{
                  TuplesKt.to(java.lang.String::class, this.representString),
                  TuplesKt.to(java.lang.Boolean::class, this.representBoolean),
                  TuplesKt.to(Character::class, this.representString),
                  TuplesKt.to(ByteArray::class, this.representByteArray),
                  TuplesKt.to(ShortArray::class, this.representPrimitiveArray),
                  TuplesKt.to(IntArray::class, this.representPrimitiveArray),
                  TuplesKt.to(LongArray::class, this.representPrimitiveArray),
                  TuplesKt.to(FloatArray::class, this.representPrimitiveArray),
                  TuplesKt.to(DoubleArray::class, this.representPrimitiveArray),
                  TuplesKt.to(CharArray::class, this.representPrimitiveArray),
                  TuplesKt.to(BooleanArray::class, this.representPrimitiveArray)
               }
            )
         );
      this.parentClassRepresenters
         .putAll(
            MapsKt.mapOf(
               new Pair[]{
                  TuplesKt.to(java.lang.Number::class, this.representNumber),
                  TuplesKt.to(java.util.List::class, this.representList),
                  TuplesKt.to(java.util.Map::class, this.representMap),
                  TuplesKt.to(java.util.Set::class, this.representSet),
                  TuplesKt.to(java.util.Iterator::class, this.representIterator),
                  TuplesKt.to(Array<Any>::class, this.representArray),
                  TuplesKt.to(java.lang.Enum::class, this.representEnum)
               }
            )
         );
   }

   protected inline fun getTag(clazz: KClass<*>, defaultTag: () -> Tag): Tag {
      var var10000: Any = access$getClassTags(this).get(clazz);
      if (var10000 == null) {
         var10000 = defaultTag.invoke();
      }

      return var10000 as Tag;
   }

   @JvmStatic
   fun `representString$lambda$0`(`this$0`: CommonRepresenter, data: Any): Node {
      var style: ScalarStyle = ScalarStyle.PLAIN;
      var value: java.lang.String = data.toString();
      val var6: Tag;
      if (`this$0`.settings.getNonPrintableStyle() === NonPrintableStyle.BINARY && !StreamReader.Companion.isPrintable(value)) {
         var6 = Tag.BINARY;
         val bytes: ByteArray = StringsKt.encodeToByteArray(value);
         if (!(StringsKt.decodeToString(bytes) == value)) {
            throw new YamlEngineException("invalid string value has occurred");
         }

         value = Base64.encode$default(Base64.Default, bytes, 0, 0, 6, null);
         style = ScalarStyle.LITERAL;
      } else {
         var6 = Tag.STR;
         value = data.toString();
      }

      if (`this$0`.defaultScalarStyle === ScalarStyle.PLAIN && MULTILINE_PATTERN.containsMatchIn(value)) {
         style = ScalarStyle.LITERAL;
      }

      return `this$0`.representScalar(var6, value, style);
   }

   @JvmStatic
   fun `representBoolean$lambda$1`(`this$0`: CommonRepresenter, data: Any): Node {
      return BaseRepresenter.representScalar$default(`this$0`, Tag.BOOL, if (data == true) "true" else "false", null, 4, null);
   }

   @JvmStatic
   fun `representNumber$lambda$4`(`this$0`: CommonRepresenter, data: Any): Node {
      val number: java.lang.Number = data as java.lang.Number;
      val var14: Node;
      if (Math_jvmKt.isInteger(data as java.lang.Number)) {
         val value: java.lang.String = data.toString();
         val var10000: BaseRepresenter = `this$0`;
         var var10001: Any = access$getClassTags(`this$0`).get(data.getClass()::class);
         if (var10001 == null) {
            var10001 = Tag.INT;
         }

         var14 = BaseRepresenter.representScalar$default(var10000, var10001 as Tag, value, null, 4, null);
      } else {
         val var9: java.lang.String = if (CommonRepresenterKt.access$isNotANumber(number))
            ".nan"
            else
            (if (CommonRepresenterKt.access$isInfinity(number)) (if (CommonRepresenterKt.access$isPositive(number)) ".inf" else "-.inf") else number.toString());
         val var15: BaseRepresenter = `this$0`;
         var var16: Any = access$getClassTags(`this$0`).get(data.getClass()::class);
         if (var16 == null) {
            var16 = Tag.FLOAT;
         }

         var14 = BaseRepresenter.representScalar$default(var15, var16 as Tag, var9, null, 4, null);
      }

      return var14;
   }

   @JvmStatic
   fun `representList$lambda$6`(`this$0`: CommonRepresenter, data: Any): Node {
      var var10000: CommonRepresenter = `this$0`;
      var var10001: Any = access$getClassTags(`this$0`).get(data.getClass()::class);
      if (var10001 == null) {
         var10001 = Tag.SEQ;
         var10000 = `this$0`;
      }

      return var10000.representSequence(var10001 as Tag, data as java.util.List, `this$0`.settings.getDefaultFlowStyle());
   }

   @JvmStatic
   fun `representIterator$lambda$9`(`this$0`: CommonRepresenter, data: Any): Node {
      val iter: java.util.Iterator = data as java.util.Iterator;
      var var10000: CommonRepresenter = `this$0`;
      var var10001: Any = access$getClassTags(`this$0`).get(iter.getClass()::class);
      if (var10001 == null) {
         var10001 = Tag.SEQ;
         var10000 = `this$0`;
      }

      return var10000.representSequence(var10001 as Tag, new 1(iter), `this$0`.settings.getDefaultFlowStyle());
   }

   @JvmStatic
   fun `representArray$lambda$10`(`this$0`: CommonRepresenter, data: Any): Node {
      return `this$0`.representSequence(Tag.SEQ, ArraysKt.asIterable(data as Array<Any>), `this$0`.settings.getDefaultFlowStyle());
   }

   @JvmStatic
   fun `representPrimitiveArray$lambda$11`(`this$0`: CommonRepresenter, data: Any): Node {
      val style: FlowStyle = `this$0`.settings.getDefaultFlowStyle();
      val var10000: java.lang.Iterable;
      if (data is ByteArray) {
         var10000 = ArraysKt.asIterable(data as ByteArray);
      } else if (data is ShortArray) {
         var10000 = ArraysKt.asIterable(data as ShortArray);
      } else if (data is IntArray) {
         var10000 = ArraysKt.asIterable(data as IntArray);
      } else if (data is LongArray) {
         var10000 = ArraysKt.asIterable(data as LongArray);
      } else if (data is FloatArray) {
         var10000 = ArraysKt.asIterable(data as FloatArray);
      } else if (data is DoubleArray) {
         var10000 = ArraysKt.asIterable(data as DoubleArray);
      } else if (data is CharArray) {
         var10000 = ArraysKt.asIterable(data as CharArray);
      } else {
         if (data !is BooleanArray) {
            throw new YamlEngineException("Unexpected primitive '${data.getClass()::class}'");
         }

         var10000 = ArraysKt.asIterable(data as BooleanArray);
      }

      return `this$0`.representSequence(Tag.SEQ, var10000, style);
   }

   @JvmStatic
   fun `representMap$lambda$13`(`this$0`: CommonRepresenter, data: Any): Node {
      var var10000: CommonRepresenter = `this$0`;
      var var10001: Any = access$getClassTags(`this$0`).get(data.getClass()::class);
      if (var10001 == null) {
         var10001 = Tag.MAP;
         var10000 = `this$0`;
      }

      return var10000.representMapping(var10001 as Tag, data as MutableMap<*, *>, `this$0`.settings.getDefaultFlowStyle());
   }

   @JvmStatic
   fun `representSet$lambda$16`(`this$0`: CommonRepresenter, data: Any): Node {
      val `this_$iv`: java.lang.Iterable = data as java.util.Set;
      val `$i$f$getTag`: LinkedHashMap = new LinkedHashMap(
         RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(data as java.util.Set, 10)), 16)
      );

      for (Object element$iv$iv : $this$associateWith$iv) {
         `$i$f$getTag`.put(`element$iv$iv`, null);
      }

      val value: java.util.Map = `$i$f$getTag`;
      var var10000: CommonRepresenter = `this$0`;
      var var10001: Any = access$getClassTags(`this$0`).get(data.getClass()::class);
      if (var10001 == null) {
         var10001 = Tag.SET;
         var10000 = `this$0`;
      }

      return var10000.representMapping(var10001 as Tag, value, `this$0`.settings.getDefaultFlowStyle());
   }

   @JvmStatic
   fun `representEnum$lambda$18`(`this$0`: CommonRepresenter, data: Any): Node {
      val var10000: BaseRepresenter = `this$0`;
      var var10001: Any = access$getClassTags(`this$0`).get(data.getClass()::class);
      if (var10001 == null) {
         val var7: Tag.Companion = Tag.Companion;
         var10001 = (data.getClass()::class).getSimpleName();
         var10001 = var7.forType((java.lang.String)var10001);
      }

      return BaseRepresenter.representScalar$default(var10000, var10001 as Tag, (data as java.lang.Enum).name(), null, 4, null);
   }

   @JvmStatic
   fun `representByteArray$lambda$19`(`this$0`: CommonRepresenter, data: Any): Node {
      return `this$0`.representScalar(Tag.BINARY, Base64.encode$default(Base64.Default, data as ByteArray, 0, 0, 6, null), ScalarStyle.LITERAL);
   }

   public companion object {
      private final val MULTILINE_PATTERN: Regex
   }
}
