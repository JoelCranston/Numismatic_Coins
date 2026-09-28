package io.ktor.util

import java.util.LinkedHashMap
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Cache.kt\nio/ktor/util/LRUCache\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,61:1\n1#2:62\n*E\n"])
internal class LRUCache<K, V> internal constructor(supplier: (Any) -> Any, close: (Any) -> Unit, maxSize: Int) : LinkedHashMap(10, 0.75F, true) {
   private final val supplier: (Any) -> Any
   private final val close: (Any) -> Unit
   private final val maxSize: Int

   init {
      this.supplier = supplier;
      this.close = close;
      this.maxSize = maxSize;
   }

   protected override fun removeEldestEntry(eldest: kotlin.collections.Map.Entry<Any, Any>): Boolean {
      val var2: Boolean = this.size() > this.maxSize;
      if (var2) {
         this.close.invoke((V)eldest.getValue());
      }

      return var2;
   }

   public override operator fun get(key: Any): Any {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
      //   at org.jetbrains.java.decompiler.util.collections.ListStack.pop(ListStack.java:31)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processBlock(ExprProcessor.java:441)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:134)
      //
      // Bytecode:
      // 00: aload 0
      // 01: getfield io/ktor/util/LRUCache.maxSize I
      // 04: ifne 14
      // 07: aload 0
      // 08: getfield io/ktor/util/LRUCache.supplier Lkotlin/jvm/functions/Function1;
      // 0b: aload 1
      // 0c: invokeinterface kotlin/jvm/functions/Function1.invoke (Ljava/lang/Object;)Ljava/lang/Object; 2
      // 11: goto 5c
      // 14: aload 0
      // 15: astore 2
      // 16: aload 2
      // 17: monitorenter
      // 18: nop
      // 19: bipush 0
      // 1a: istore 3
      // 1b: aload 0
      // 1c: aload 1
      // 1d: invokespecial java/util/LinkedHashMap.get (Ljava/lang/Object;)Ljava/lang/Object;
      // 20: dup
      // 21: ifnull 32
      // 24: astore 4
      // 26: bipush 0
      // 27: istore 5
      // 29: aload 4
      // 2b: astore 7
      // 2d: aload 2
      // 2e: monitorexit
      // 2f: aload 7
      // 31: areturn
      // 32: pop
      // 33: aload 0
      // 34: getfield io/ktor/util/LRUCache.supplier Lkotlin/jvm/functions/Function1;
      // 37: aload 1
      // 38: invokeinterface kotlin/jvm/functions/Function1.invoke (Ljava/lang/Object;)Ljava/lang/Object; 2
      // 3d: astore 6
      // 3f: bipush 0
      // 40: istore 4
      // 42: aload 0
      // 43: aload 1
      // 44: aload 6
      // 46: invokevirtual io/ktor/util/LRUCache.put (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      // 49: pop
      // 4a: aload 6
      // 4c: nop
      // 4d: nop
      // 4e: astore 3
      // 4f: aload 2
      // 50: monitorexit
      // 51: aload 3
      // 52: goto 5c
      // 55: astore 4
      // 57: aload 2
      // 58: monitorexit
      // 59: aload 4
      // 5b: athrow
      // 5c: areturn
   }
}
