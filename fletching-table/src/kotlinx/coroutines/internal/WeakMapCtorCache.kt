package kotlinx.coroutines.internal

import java.util.WeakHashMap
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nExceptionsConstructor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExceptionsConstructor.kt\nkotlinx/coroutines/internal/WeakMapCtorCache\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,112:1\n1#2:113\n*E\n"])
private object WeakMapCtorCache : CtorCache {
   private final val cacheLock: ReentrantReadWriteLock = new ReentrantReadWriteLock()
   private final val exceptionCtors: WeakHashMap<Class<out Throwable>, (Throwable) -> Throwable?> = new WeakHashMap()

   public override fun get(key: Class<out Throwable>): (Throwable) -> Throwable? {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
      //   at org.jetbrains.java.decompiler.util.collections.ListStack.pop(ListStack.java:31)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processBlock(ExprProcessor.java:305)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:134)
      //
      // Bytecode:
      // 000: getstatic kotlinx/coroutines/internal/WeakMapCtorCache.cacheLock Ljava/util/concurrent/locks/ReentrantReadWriteLock;
      // 003: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock.readLock ()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
      // 006: astore 3
      // 007: aload 3
      // 008: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock.lock ()V
      // 00b: nop
      // 00c: bipush 0
      // 00d: istore 4
      // 00f: getstatic kotlinx/coroutines/internal/WeakMapCtorCache.exceptionCtors Ljava/util/WeakHashMap;
      // 012: aload 1
      // 013: invokevirtual java/util/WeakHashMap.get (Ljava/lang/Object;)Ljava/lang/Object;
      // 016: checkcast kotlin/jvm/functions/Function1
      // 019: dup
      // 01a: ifnull 02d
      // 01d: astore 5
      // 01f: bipush 0
      // 020: istore 6
      // 022: aload 5
      // 024: astore 7
      // 026: aload 3
      // 027: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock.unlock ()V
      // 02a: aload 7
      // 02c: areturn
      // 02d: pop
      // 02e: aconst_null
      // 02f: astore 4
      // 031: aload 3
      // 032: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock.unlock ()V
      // 035: goto 041
      // 038: astore 5
      // 03a: aload 3
      // 03b: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock.unlock ()V
      // 03e: aload 5
      // 040: athrow
      // 041: getstatic kotlinx/coroutines/internal/WeakMapCtorCache.cacheLock Ljava/util/concurrent/locks/ReentrantReadWriteLock;
      // 044: astore 2
      // 045: aload 2
      // 046: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock.readLock ()Ljava/util/concurrent/locks/ReentrantReadWriteLock$ReadLock;
      // 049: astore 3
      // 04a: aload 2
      // 04b: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock.getWriteHoldCount ()I
      // 04e: ifne 058
      // 051: aload 2
      // 052: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock.getReadHoldCount ()I
      // 055: goto 059
      // 058: bipush 0
      // 059: istore 4
      // 05b: bipush 0
      // 05c: istore 5
      // 05e: iload 5
      // 060: iload 4
      // 062: if_icmpge 06f
      // 065: aload 3
      // 066: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock.unlock ()V
      // 069: iinc 5 1
      // 06c: goto 05e
      // 06f: aload 2
      // 070: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock.writeLock ()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
      // 073: astore 5
      // 075: aload 5
      // 077: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock.lock ()V
      // 07a: nop
      // 07b: bipush 0
      // 07c: istore 6
      // 07e: getstatic kotlinx/coroutines/internal/WeakMapCtorCache.exceptionCtors Ljava/util/WeakHashMap;
      // 081: aload 1
      // 082: invokevirtual java/util/WeakHashMap.get (Ljava/lang/Object;)Ljava/lang/Object;
      // 085: checkcast kotlin/jvm/functions/Function1
      // 088: dup
      // 089: ifnull 0b1
      // 08c: astore 7
      // 08e: bipush 0
      // 08f: istore 8
      // 091: aload 7
      // 093: astore 12
      // 095: bipush 0
      // 096: istore 7
      // 098: iload 7
      // 09a: iload 4
      // 09c: if_icmpge 0a9
      // 09f: aload 3
      // 0a0: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock.lock ()V
      // 0a3: iinc 7 1
      // 0a6: goto 098
      // 0a9: aload 5
      // 0ab: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock.unlock ()V
      // 0ae: aload 12
      // 0b0: areturn
      // 0b1: pop
      // 0b2: aload 1
      // 0b3: invokestatic kotlinx/coroutines/internal/ExceptionsConstructorKt.access$createConstructor (Ljava/lang/Class;)Lkotlin/jvm/functions/Function1;
      // 0b6: astore 9
      // 0b8: aload 9
      // 0ba: astore 10
      // 0bc: bipush 0
      // 0bd: istore 7
      // 0bf: getstatic kotlinx/coroutines/internal/WeakMapCtorCache.exceptionCtors Ljava/util/WeakHashMap;
      // 0c2: checkcast java/util/Map
      // 0c5: aload 1
      // 0c6: aload 10
      // 0c8: invokeinterface java/util/Map.put (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object; 3
      // 0cd: pop
      // 0ce: nop
      // 0cf: aload 9
      // 0d1: astore 11
      // 0d3: bipush 0
      // 0d4: istore 7
      // 0d6: iload 7
      // 0d8: iload 4
      // 0da: if_icmpge 0e7
      // 0dd: aload 3
      // 0de: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock.lock ()V
      // 0e1: iinc 7 1
      // 0e4: goto 0d6
      // 0e7: aload 5
      // 0e9: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock.unlock ()V
      // 0ec: aload 11
      // 0ee: areturn
      // 0ef: astore 7
      // 0f1: bipush 0
      // 0f2: istore 8
      // 0f4: iload 8
      // 0f6: iload 4
      // 0f8: if_icmpge 105
      // 0fb: aload 3
      // 0fc: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock$ReadLock.lock ()V
      // 0ff: iinc 8 1
      // 102: goto 0f4
      // 105: aload 5
      // 107: invokevirtual java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock.unlock ()V
      // 10a: aload 7
      // 10c: athrow
   }
}
