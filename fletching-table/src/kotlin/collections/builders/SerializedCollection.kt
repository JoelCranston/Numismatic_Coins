package kotlin.collections.builders

import java.io.Externalizable
import java.io.InvalidObjectException
import java.io.ObjectInput
import java.io.ObjectOutput
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nListBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ListBuilder.kt\nkotlin/collections/builders/SerializedCollection\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,722:1\n1#2:723\n*E\n"])
internal class SerializedCollection(collection: Collection<*>, tag: Int) : Externalizable {
   private final var collection: Collection<*>
   private final val tag: Int

   init {
      this.collection = collection;
      this.tag = tag;
   }

   public constructor() : this(CollectionsKt.emptyList(), 0)
   public override fun writeExternal(output: ObjectOutput) {
      output.writeByte(this.tag);
      output.writeInt(this.collection.size());

      for (Object element : this.collection) {
         output.writeObject(element);
      }
   }

   public override fun readExternal(input: ObjectInput) {
      val flags: Int = input.readByte();
      val tag: Int = flags and 1;
      if ((flags and -2) != 0) {
         throw new InvalidObjectException("Unsupported flags value: $flags.");
      } else {
         val size: Int = input.readInt();
         if (size < 0) {
            throw new InvalidObjectException("Illegal size value: $size.");
         } else {
            var var10000: SerializedCollection;
            var var10001: java.util.Collection;
            switch (tag) {
               case 0:
                  val var13: java.util.List = CollectionsKt.createListBuilder(size);
                  val var14: java.util.List = var13;

                  for (int var16 = 0; var16 < size; var16++) {
                     var14.add(input.readObject());
                  }

                  var10000 = this;
                  var10001 = CollectionsKt.build(var13);
                  break;
               case 1:
                  val var6: java.util.Set = SetsKt.createSetBuilder(size);
                  val `$this$readExternal_u24lambda_u241`: java.util.Set = var6;

                  for (int var9 = 0; var9 < size; var9++) {
                     `$this$readExternal_u24lambda_u241`.add(input.readObject());
                  }

                  var10000 = this;
                  var10001 = SetsKt.build(var6);
                  break;
               default:
                  throw new InvalidObjectException("Unsupported collection type tag: $tag.");
            }

            var10000.collection = var10001;
         }
      }
   }

   private fun readResolve(): Any {
      return this.collection;
   }

   public companion object {
      private const val serialVersionUID: Long
      public const val tagList: Int
      public const val tagSet: Int
   }
}
