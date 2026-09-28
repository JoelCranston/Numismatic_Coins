package okio

import java.util.RandomAccess

public class TypedOptions<T>(list: List<Any>, options: Options) : AbstractList<T>, RandomAccess {
   internal final val options: Options
   internal final val list: List<Any>

   public open val size: Int
      public open get() {
         return this.list.size();
      }


   init {
      this.options = options;
      this.list = CollectionsKt.toList(list);
      if (this.list.size() != this.options.size()) {
         throw new IllegalArgumentException("Failed requirement.".toString());
      }
   }

   public override operator fun get(index: Int): Any {
      return this.list.get(index);
   }

   public companion object {
      public inline fun <T : Any> of(values: Iterable<T>, encode: (T) -> ByteString): TypedOptions<T> {
         val list: java.util.List = CollectionsKt.toList(values);
         val var10000: Options.Companion = Options.Companion;
         var var6: Int = 0;
         val var7: Int = list.size();
         val var8: Array<ByteString> = new ByteString[var7];

         for (; var6 < var7; var6++) {
            var8[var6] = (ByteString)encode.invoke(list.get(var6));
         }

         return new TypedOptions<>(list, var10000.of(var8));
      }
   }
}
