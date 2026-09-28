package kotlinx.coroutines.internal

import java.util.Arrays
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.atomicfu.AtomicInt
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@SourceDebugExtension(["SMAP\nThreadSafeHeap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ThreadSafeHeap.kt\nkotlinx/coroutines/internal/ThreadSafeHeap\n+ 2 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 3 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,159:1\n29#2:160\n29#2:162\n29#2:164\n29#2:166\n29#2:168\n29#2:170\n29#2:172\n16#3:161\n16#3:163\n16#3:165\n16#3:167\n16#3:169\n16#3:171\n16#3:173\n1#4:174\n*S KotlinDebug\n*F\n+ 1 ThreadSafeHeap.kt\nkotlinx/coroutines/internal/ThreadSafeHeap\n*L\n33#1:160\n41#1:162\n43#1:164\n51#1:166\n60#1:168\n63#1:170\n72#1:172\n33#1:161\n41#1:163\n43#1:165\n51#1:167\n60#1:169\n63#1:171\n72#1:173\n*E\n"])
public open class ThreadSafeHeap<T extends ThreadSafeHeapNode & java.lang.Comparable<? super T>> {
   private final var a: Array<Any?>?
   private final val _size: AtomicInt

   public final var size: Int
      public final get() {
         return get_size$volatile$FU().get(this);
      }

      public final set(value) {
         get_size$volatile$FU().set(this, value);
      }


   public final val isEmpty: Boolean
      public final get() {
         return this.getSize() == 0;
      }


   public fun find(predicate: (Any) -> Boolean): Any? {
      var var12: ThreadSafeHeapNode;
      synchronized (this) {
         var i: Int = 0;
         val var7: Int = this.getSize();

         while (true) {
            if (i >= var7) {
               var12 = null;
               break;
            }

            var12 = if (this.a != null) this.a[i] else null;
            if (predicate.invoke(var12) as java.lang.Boolean) {
               var12 = var12;
               break;
            }

            i++;
         }

         var12 = var12;
      }

      return (T)var12;
   }

   public fun peek(): Any? {
      val var10000: ThreadSafeHeapNode;
      synchronized (this) {
         var10000 = this.firstImpl();
      }

      return (T)var10000;
   }

   public fun removeFirstOrNull(): Any? {
      val var10000: ThreadSafeHeapNode;
      synchronized (this) {
         var10000 = if (this.getSize() > 0) this.removeAtImpl(0) else null;
      }

      return (T)var10000;
   }

   public inline fun removeFirstIf(predicate: (Any) -> Boolean): Any? {
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
      // 00: bipush 0
      // 01: istore 2
      // 02: bipush 0
      // 03: istore 3
      // 04: bipush 0
      // 05: istore 4
      // 07: aload 0
      // 08: astore 5
      // 0a: aload 5
      // 0c: monitorenter
      // 0d: nop
      // 0e: bipush 0
      // 0f: istore 6
      // 11: aload 0
      // 12: invokevirtual kotlinx/coroutines/internal/ThreadSafeHeap.firstImpl ()Lkotlinx/coroutines/internal/ThreadSafeHeapNode;
      // 15: dup
      // 16: ifnonnull 2b
      // 19: pop
      // 1a: aconst_null
      // 1b: astore 9
      // 1d: bipush 2
      // 1e: invokestatic kotlin/jvm/internal/InlineMarker.finallyStart (I)V
      // 21: aload 5
      // 23: monitorexit
      // 24: bipush 2
      // 25: invokestatic kotlin/jvm/internal/InlineMarker.finallyEnd (I)V
      // 28: aload 9
      // 2a: areturn
      // 2b: astore 7
      // 2d: aload 1
      // 2e: aload 7
      // 30: invokeinterface kotlin/jvm/functions/Function1.invoke (Ljava/lang/Object;)Ljava/lang/Object; 2
      // 35: checkcast java/lang/Boolean
      // 38: invokevirtual java/lang/Boolean.booleanValue ()Z
      // 3b: ifeq 46
      // 3e: aload 0
      // 3f: bipush 0
      // 40: invokevirtual kotlinx/coroutines/internal/ThreadSafeHeap.removeAtImpl (I)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;
      // 43: goto 47
      // 46: aconst_null
      // 47: nop
      // 48: astore 8
      // 4a: bipush 1
      // 4b: invokestatic kotlin/jvm/internal/InlineMarker.finallyStart (I)V
      // 4e: aload 5
      // 50: monitorexit
      // 51: bipush 1
      // 52: invokestatic kotlin/jvm/internal/InlineMarker.finallyEnd (I)V
      // 55: aload 8
      // 57: goto 6a
      // 5a: astore 6
      // 5c: bipush 1
      // 5d: invokestatic kotlin/jvm/internal/InlineMarker.finallyStart (I)V
      // 60: aload 5
      // 62: monitorexit
      // 63: bipush 1
      // 64: invokestatic kotlin/jvm/internal/InlineMarker.finallyEnd (I)V
      // 67: aload 6
      // 69: athrow
      // 6a: nop
      // 6b: nop
      // 6c: areturn
   }

   public fun addLast(node: Any) {
      synchronized (this) {
         this.addImpl((T)node);
      }
   }

   public inline fun addLastIf(node: Any, cond: (Any?) -> Boolean): Boolean {
      var var11: Boolean;
      synchronized (this) {
         if (cond.invoke(this.firstImpl()) as java.lang.Boolean) {
            this.addImpl((T)node);
            var11 = true;
         } else {
            var11 = false;
         }

         InlineMarker.finallyStart(1);
         InlineMarker.finallyEnd(1);
         var11 = var11;
      }

      return var11;
   }

   public fun remove(node: Any): Boolean {
      val var8: Boolean;
      synchronized (this) {
         val var10000: Boolean;
         if (node.getHeap() == null) {
            var10000 = false;
         } else {
            val index: Int = node.getIndex();
            if (DebugKt.getASSERTIONS_ENABLED() && index < 0) {
               throw new AssertionError();
            }

            this.removeAtImpl(index);
            var10000 = true;
         }

         var8 = var10000;
      }

      return var8;
   }

   @PublishedApi
   internal fun firstImpl(): Any? {
      return if (this.a != null) this.a[0] else null;
   }

   @PublishedApi
   internal fun removeAtImpl(index: Int): Any {
      if (DebugKt.getASSERTIONS_ENABLED() && this.getSize() <= 0) {
         throw new AssertionError();
      } else {
         var var10000: Array<ThreadSafeHeapNode>;
         var10000 = this.a;
         this.setSize(this.getSize() + -1);
         label35:
         if (index < this.getSize()) {
            this.swap(index, this.getSize());
            val var6: Int = (index - 1) / 2;
            if (index > 0) {
               val var8: ThreadSafeHeapNode = var10000[index];
               val var9: java.lang.Comparable = var8 as java.lang.Comparable;
               val var10001: ThreadSafeHeapNode = var10000[var6];
               if (var9.compareTo(var10001) < 0) {
                  this.swap(index, var6);
                  this.siftUpFrom(var6);
                  break label35;
               }
            }

            this.siftDownFrom(index);
         }

         val var10: ThreadSafeHeapNode = var10000[this.getSize()];
         if (DebugKt.getASSERTIONS_ENABLED() && var10.getHeap() != this) {
            throw new AssertionError();
         } else {
            var10.setHeap(null);
            var10.setIndex(-1);
            var10000[this.getSize()] = null;
            return (T)var10;
         }
      }
   }

   @PublishedApi
   internal fun addImpl(node: Any) {
      if (DebugKt.getASSERTIONS_ENABLED() && node.getHeap() != null) {
         throw new AssertionError();
      } else {
         node.setHeap(this);
         val var5: Array<ThreadSafeHeapNode> = this.realloc();
         val var4: Int = this.getSize();
         this.setSize(var4 + 1);
         var5[var4] = node;
         node.setIndex(var4);
         this.siftUpFrom(var4);
      }
   }

   private tailrec fun siftUpFrom(i: Int) {
      var var2: ThreadSafeHeap = this;

      while (i > 0) {
         val var10000: Array<ThreadSafeHeapNode> = var2.a;
         val j: Int = (i - 1) / 2;
         val var5: ThreadSafeHeapNode = var10000[(i - 1) / 2];
         val var6: java.lang.Comparable = var5 as java.lang.Comparable;
         val var10001: ThreadSafeHeapNode = var10000[i];
         if (var6.compareTo(var10001) <= 0) {
            return;
         }

         var2.swap(i, j);
         var2 = var2;
         i = j;
      }
   }

   private tailrec fun siftDownFrom(i: Int) {
      var var2: ThreadSafeHeap = this;

      while (true) {
         var j: Int = 2 * i + 1;
         if (2 * i + 1 >= var2.getSize()) {
            return;
         }

         val var10000: Array<ThreadSafeHeapNode> = var2.a;
         if (j + 1 < var2.getSize()) {
            val var7: ThreadSafeHeapNode = var10000[j + 1];
            val var8: java.lang.Comparable = var7 as java.lang.Comparable;
            val var10001: ThreadSafeHeapNode = var10000[j];
            if (var8.compareTo(var10001) < 0) {
               j++;
            }
         }

         val var9: ThreadSafeHeapNode = var10000[i];
         val var10: java.lang.Comparable = var9 as java.lang.Comparable;
         val var11: ThreadSafeHeapNode = var10000[j];
         if (var10.compareTo(var11) <= 0) {
            return;
         }

         var2.swap(i, j);
         var2 = var2;
         i = j;
      }
   }

   private fun realloc(): Array<Any?> {
      val a: Array<ThreadSafeHeapNode> = this.a;
      val var10000: Array<ThreadSafeHeapNode>;
      if (this.a == null) {
         val var2: Array<ThreadSafeHeapNode> = new ThreadSafeHeapNode[4];
         this.a = (T[])var2;
         var10000 = var2;
      } else if (this.getSize() >= a.length) {
         val var7: Array<Any> = Arrays.copyOf(a, this.getSize() * 2);
         this.a = (T[])var7;
         var10000 = var7 as Array<ThreadSafeHeapNode>;
      } else {
         var10000 = a;
      }

      return (T[])var10000;
   }

   private fun swap(i: Int, j: Int) {
      val var10000: Array<ThreadSafeHeapNode> = this.a;
      val var6: ThreadSafeHeapNode = var10000[j];
      val var7: ThreadSafeHeapNode = var10000[i];
      var10000[i] = var6;
      var10000[j] = var7;
      var6.setIndex(i);
      var7.setIndex(j);
   }
}
