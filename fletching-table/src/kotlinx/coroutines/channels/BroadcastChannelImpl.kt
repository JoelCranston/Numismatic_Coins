package kotlinx.coroutines.channels

import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.selects.SelectInstance

@SourceDebugExtension(["SMAP\nBroadcastChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BroadcastChannel.kt\nkotlinx/coroutines/channels/BroadcastChannelImpl\n+ 2 Concurrent.kt\nkotlinx/coroutines/internal/ConcurrentKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,363:1\n11#2:364\n11#2:365\n11#2:369\n11#2:372\n11#2:378\n11#2:379\n11#2:385\n11#2:388\n11#2:389\n11#2:390\n774#3:366\n865#3,2:367\n1863#3,2:370\n1755#3,3:373\n1863#3,2:376\n1863#3,2:380\n774#3:382\n865#3,2:383\n1863#3,2:386\n*S KotlinDebug\n*F\n+ 1 BroadcastChannel.kt\nkotlinx/coroutines/channels/BroadcastChannelImpl\n*L\n115#1:364\n137#1:365\n162#1:369\n186#1:372\n228#1:378\n280#1:379\n292#1:385\n304#1:388\n331#1:389\n343#1:390\n138#1:366\n138#1:367,2\n175#1:370,2\n191#1:373,3\n200#1:376,2\n282#1:380,2\n287#1:382\n287#1:383,2\n295#1:386,2\n*E\n"])
internal class BroadcastChannelImpl<E>(capacity: Int) : BufferedChannel(0, null), BroadcastChannel<E> {
   public final val capacity: Int
   private final val lock: ReentrantLock
   private final var subscribers: List<BufferedChannel<Any>>
   private final var lastConflatedElement: Any?
   private final val onSendInternalResult: HashMap<SelectInstance<*>, Any?>

   public open val isClosedForSend: Boolean
      public open get() {
         label15: {
            val var3: Lock = this.lock;
            this.lock.lock();

            try {
               val var8: Boolean = super.isClosedForSend();
            } catch (var6: java.lang.Throwable) {
               var3.unlock();
            }

            var3.unlock();
         }
      }


   public final val value: Any
      public final get() {
         label30: {
            val var3: Lock = this.lock;
            this.lock.lock();

            try {
               if (this.isClosedForSend()) {
                  var var10000: java.lang.Throwable = this.getCloseCause();
                  if (var10000 == null) {
                     var10000 = new IllegalStateException("This broadcast channel is closed");
                  }

                  throw var10000;
               }

               if (this.lastConflatedElement === BroadcastChannelKt.access$getNO_ELEMENT$p()) {
                  throw new IllegalStateException("No value".toString());
               }

               val var8: Any = this.lastConflatedElement;
            } catch (var6: java.lang.Throwable) {
               var3.unlock();
            }

            var3.unlock();
         }
      }


   public final val valueOrNull: Any?
      public final get() {
         label23: {
            val var3: Lock = this.lock;
            this.lock.lock();

            try {
               if (!this.isClosedForReceive()) {
                  if (this.lastConflatedElement != BroadcastChannelKt.access$getNO_ELEMENT$p()) {
                     ;
                  }
               }
            } catch (var6: java.lang.Throwable) {
               var3.unlock();
            }

            var3.unlock();
         }
      }


   init {
      this.capacity = capacity;
      if (this.capacity < 1 && this.capacity != -1) {
         throw new IllegalArgumentException(("BroadcastChannel capacity must be positive or Channel.CONFLATED, but ${this.capacity} was specified").toString());
      } else {
         this.lock = new ReentrantLock();
         this.subscribers = CollectionsKt.emptyList();
         this.lastConflatedElement = BroadcastChannelKt.access$getNO_ELEMENT$p();
         this.onSendInternalResult = new HashMap<>();
      }
   }

   public override fun openSubscription(): ReceiveChannel<Any> {
      label39: {
         val var3: Lock = this.lock;
         this.lock.lock();

         label36: {
            try {
               val s: BufferedChannel = if (this.capacity == -1)
                  new BroadcastChannelImpl.SubscriberConflated(this)
                  else
                  new BroadcastChannelImpl.SubscriberBuffered(this);
               if (this.isClosedForSend() && this.lastConflatedElement === BroadcastChannelKt.access$getNO_ELEMENT$p()) {
                  s.close(this.getCloseCause());
                  val var6: ReceiveChannel = s;
                  break label36;
               }

               if (this.lastConflatedElement != BroadcastChannelKt.access$getNO_ELEMENT$p()) {
                  s.trySend-JP2dKIU(this.getValue());
               }

               this.subscribers = CollectionsKt.plus(this.subscribers, s);
            } catch (var7: java.lang.Throwable) {
               var3.unlock();
            }

            var3.unlock();
         }

         var3.unlock();
      }
   }

   private fun removeSubscriber(s: ReceiveChannel<Any>) {
      label31: {
         val var4: Lock = this.lock;
         this.lock.lock();

         try {
            val `$this$filter$iv`: java.lang.Iterable = this.subscribers;
            val `destination$iv$iv`: java.util.Collection = new ArrayList();

            for (Object element$iv$iv : $this$filter$iv) {
               if (`element$iv$iv` as BufferedChannel != s) {
                  `destination$iv$iv`.add(`element$iv$iv`);
               }
            }

            this.subscribers = `destination$iv$iv` as MutableList<BufferedChannel<E>>;
         } catch (var17: java.lang.Throwable) {
            var4.unlock();
         }

         var4.unlock();
      }
   }

   public override suspend fun send(element: Any) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
      //   at org.jetbrains.java.decompiler.util.collections.ListStack.pop(ListStack.java:31)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.<init>(FunctionExprent.java:159)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processBlock(ExprProcessor.java:459)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.processStatement(ExprProcessor.java:134)
      //
      // Bytecode:
      // 000: aload 2
      // 001: instanceof kotlinx/coroutines/channels/BroadcastChannelImpl$send$1
      // 004: ifeq 027
      // 007: aload 2
      // 008: checkcast kotlinx/coroutines/channels/BroadcastChannelImpl$send$1
      // 00b: astore 12
      // 00d: aload 12
      // 00f: getfield kotlinx/coroutines/channels/BroadcastChannelImpl$send$1.label I
      // 012: ldc -2147483648
      // 014: iand
      // 015: ifeq 027
      // 018: aload 12
      // 01a: dup
      // 01b: getfield kotlinx/coroutines/channels/BroadcastChannelImpl$send$1.label I
      // 01e: ldc -2147483648
      // 020: isub
      // 021: putfield kotlinx/coroutines/channels/BroadcastChannelImpl$send$1.label I
      // 024: goto 032
      // 027: new kotlinx/coroutines/channels/BroadcastChannelImpl$send$1
      // 02a: dup
      // 02b: aload 0
      // 02c: aload 2
      // 02d: invokespecial kotlinx/coroutines/channels/BroadcastChannelImpl$send$1.<init> (Lkotlinx/coroutines/channels/BroadcastChannelImpl;Lkotlin/coroutines/Continuation;)V
      // 030: astore 12
      // 032: aload 12
      // 034: getfield kotlinx/coroutines/channels/BroadcastChannelImpl$send$1.result Ljava/lang/Object;
      // 037: astore 11
      // 039: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03c: astore 13
      // 03e: aload 12
      // 040: getfield kotlinx/coroutines/channels/BroadcastChannelImpl$send$1.label I
      // 043: tableswitch 271 0 1 21 199
      // 058: aload 11
      // 05a: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 05d: aload 0
      // 05e: getfield kotlinx/coroutines/channels/BroadcastChannelImpl.lock Ljava/util/concurrent/locks/ReentrantLock;
      // 061: astore 4
      // 063: bipush 0
      // 064: istore 5
      // 066: aload 4
      // 068: checkcast java/util/concurrent/locks/Lock
      // 06b: astore 6
      // 06d: aload 6
      // 06f: invokeinterface java/util/concurrent/locks/Lock.lock ()V 1
      // 074: nop
      // 075: bipush 0
      // 076: istore 7
      // 078: aload 0
      // 079: invokevirtual kotlinx/coroutines/channels/BroadcastChannelImpl.isClosedForSend ()Z
      // 07c: ifeq 084
      // 07f: aload 0
      // 080: invokevirtual kotlinx/coroutines/channels/BroadcastChannelImpl.getSendException ()Ljava/lang/Throwable;
      // 083: athrow
      // 084: aload 0
      // 085: getfield kotlinx/coroutines/channels/BroadcastChannelImpl.capacity I
      // 088: bipush -1
      // 089: if_icmpne 091
      // 08c: aload 0
      // 08d: aload 1
      // 08e: putfield kotlinx/coroutines/channels/BroadcastChannelImpl.lastConflatedElement Ljava/lang/Object;
      // 091: aload 0
      // 092: getfield kotlinx/coroutines/channels/BroadcastChannelImpl.subscribers Ljava/util/List;
      // 095: astore 8
      // 097: aload 6
      // 099: invokeinterface java/util/concurrent/locks/Lock.unlock ()V 1
      // 09e: aload 8
      // 0a0: goto 0af
      // 0a3: astore 7
      // 0a5: aload 6
      // 0a7: invokeinterface java/util/concurrent/locks/Lock.unlock ()V 1
      // 0ac: aload 7
      // 0ae: athrow
      // 0af: nop
      // 0b0: astore 3
      // 0b1: aload 3
      // 0b2: checkcast java/lang/Iterable
      // 0b5: astore 4
      // 0b7: bipush 0
      // 0b8: istore 5
      // 0ba: aload 4
      // 0bc: invokeinterface java/lang/Iterable.iterator ()Ljava/util/Iterator; 1
      // 0c1: astore 6
      // 0c3: aload 6
      // 0c5: invokeinterface java/util/Iterator.hasNext ()Z 1
      // 0ca: ifeq 14d
      // 0cd: aload 6
      // 0cf: invokeinterface java/util/Iterator.next ()Ljava/lang/Object; 1
      // 0d4: astore 7
      // 0d6: aload 7
      // 0d8: checkcast kotlinx/coroutines/channels/BufferedChannel
      // 0db: astore 8
      // 0dd: bipush 0
      // 0de: istore 9
      // 0e0: aload 8
      // 0e2: aload 1
      // 0e3: aload 12
      // 0e5: aload 12
      // 0e7: aload 0
      // 0e8: putfield kotlinx/coroutines/channels/BroadcastChannelImpl$send$1.L$0 Ljava/lang/Object;
      // 0eb: aload 12
      // 0ed: aload 1
      // 0ee: putfield kotlinx/coroutines/channels/BroadcastChannelImpl$send$1.L$1 Ljava/lang/Object;
      // 0f1: aload 12
      // 0f3: aload 6
      // 0f5: putfield kotlinx/coroutines/channels/BroadcastChannelImpl$send$1.L$2 Ljava/lang/Object;
      // 0f8: aload 12
      // 0fa: bipush 1
      // 0fb: putfield kotlinx/coroutines/channels/BroadcastChannelImpl$send$1.label I
      // 0fe: invokevirtual kotlinx/coroutines/channels/BufferedChannel.sendBroadcast$kotlinx_coroutines_core (Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
      // 101: dup
      // 102: aload 13
      // 104: if_acmpne 130
      // 107: aload 13
      // 109: areturn
      // 10a: bipush 0
      // 10b: istore 5
      // 10d: bipush 0
      // 10e: istore 9
      // 110: aload 12
      // 112: getfield kotlinx/coroutines/channels/BroadcastChannelImpl$send$1.L$2 Ljava/lang/Object;
      // 115: checkcast java/util/Iterator
      // 118: astore 6
      // 11a: aload 12
      // 11c: getfield kotlinx/coroutines/channels/BroadcastChannelImpl$send$1.L$1 Ljava/lang/Object;
      // 11f: astore 1
      // 120: aload 12
      // 122: getfield kotlinx/coroutines/channels/BroadcastChannelImpl$send$1.L$0 Ljava/lang/Object;
      // 125: checkcast kotlinx/coroutines/channels/BroadcastChannelImpl
      // 128: astore 0
      // 129: aload 11
      // 12b: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 12e: aload 11
      // 130: checkcast java/lang/Boolean
      // 133: invokevirtual java/lang/Boolean.booleanValue ()Z
      // 136: istore 10
      // 138: iload 10
      // 13a: ifne 149
      // 13d: aload 0
      // 13e: invokevirtual kotlinx/coroutines/channels/BroadcastChannelImpl.isClosedForSend ()Z
      // 141: ifeq 149
      // 144: aload 0
      // 145: invokevirtual kotlinx/coroutines/channels/BroadcastChannelImpl.getSendException ()Ljava/lang/Throwable;
      // 148: athrow
      // 149: nop
      // 14a: goto 0c3
      // 14d: nop
      // 14e: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 151: areturn
      // 152: new java/lang/IllegalStateException
      // 155: dup
      // 156: ldc "call to 'resume' before 'invoke' with coroutine"
      // 158: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 15b: athrow
   }

   public override fun trySend(element: Any): ChannelResult<Unit> {
      label64: {
         val var4: Lock = this.lock;
         this.lock.lock();

         label61: {
            label60: {
               try {
                  if (this.isClosedForSend()) {
                     val var15: Any = super.trySend-JP2dKIU((E)element);
                     break label61;
                  }

                  val `$this$forEach$iv`: java.lang.Iterable = this.subscribers;
                  var var10000: Boolean;
                  if (this.subscribers is java.util.Collection && this.subscribers.isEmpty()) {
                     var10000 = false;
                  } else {
                     val var8: java.util.Iterator = `$this$forEach$iv`.iterator();

                     while (true) {
                        if (!var8.hasNext()) {
                           var10000 = false;
                           break;
                        }

                        if ((var8.next() as BufferedChannel).shouldSendSuspend$kotlinx_coroutines_core()) {
                           var10000 = true;
                           break;
                        }
                     }
                  }

                  if (var10000) {
                     val var14: Any = ChannelResult.Companion.failure-PtdJZtk();
                     break label60;
                  }

                  if (this.capacity == -1) {
                     this.lastConflatedElement = element;
                  }

                  for (Object element$iv : $this$any$iv) {
                     (var21 as BufferedChannel).trySend-JP2dKIU(element);
                  }

                  val var13: Any = ChannelResult.Companion.success-JP2dKIU(Unit.INSTANCE);
               } catch (var16: java.lang.Throwable) {
                  var4.unlock();
               }

               var4.unlock();
            }

            var4.unlock();
         }

         var4.unlock();
      }
   }

   protected override fun registerSelectForSend(select: SelectInstance<*>, element: Any?) {
      label26: {
         val var5: Lock = this.lock;
         this.lock.lock();

         label23: {
            try {
               val result: Any = this.onSendInternalResult.remove(select);
               if (result != null) {
                  select.selectInRegistrationPhase(result);
                  break label23;
               }
            } catch (var9: java.lang.Throwable) {
               var5.unlock();
            }

            var5.unlock();
         }

         var5.unlock();
      }
   }

   public override fun close(cause: Throwable?): Boolean {
      label35: {
         val var4: Lock = this.lock;
         this.lock.lock();

         try {
            val `$this$filter$iv`: java.lang.Iterable;
            for (Object element$iv : $this$filter$iv) {
               (`destination$iv$iv` as BufferedChannel).close(cause);
            }

            `$this$filter$iv` = this.subscribers;
            val var21: java.util.Collection = new ArrayList();

            for (Object element$iv$iv : $this$filter$iv) {
               if ((`element$iv$iv` as BufferedChannel).hasElements$kotlinx_coroutines_core()) {
                  var21.add(`element$iv$iv`);
               }
            }

            this.subscribers = var21 as MutableList<BufferedChannel<E>>;
            val var16: Boolean = super.close(cause);
         } catch (var17: java.lang.Throwable) {
            var4.unlock();
         }

         var4.unlock();
      }
   }

   internal override fun cancelImpl(cause: Throwable?): Boolean {
      label24: {
         val var4: Lock = this.lock;
         this.lock.lock();

         try {
            val `$this$forEach$iv`: java.lang.Iterable;
            for (Object element$iv : $this$forEach$iv) {
               (`element$iv` as BufferedChannel).cancelImpl$kotlinx_coroutines_core(cause);
            }

            this.lastConflatedElement = BroadcastChannelKt.access$getNO_ELEMENT$p();
            val var12: Boolean = super.cancelImpl$kotlinx_coroutines_core(cause);
         } catch (var13: java.lang.Throwable) {
            var4.unlock();
         }

         var4.unlock();
      }
   }

   public override fun toString(): String {
      return "${if (this.lastConflatedElement != BroadcastChannelKt.access$getNO_ELEMENT$p()) "CONFLATED_ELEMENT=${this.lastConflatedElement}; " else ""}BROADCAST=<${super.toString()}>; SUBSCRIBERS=${CollectionsKt.joinToString$default(
         this.subscribers, ";", "<", ">", 0, null, null, 56, null
      )}";
   }

   @SourceDebugExtension(["SMAP\nBroadcastChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BroadcastChannel.kt\nkotlinx/coroutines/channels/BroadcastChannelImpl$SubscriberBuffered\n+ 2 Concurrent.kt\nkotlinx/coroutines/internal/ConcurrentKt\n*L\n1#1,363:1\n11#2:364\n*S KotlinDebug\n*F\n+ 1 BroadcastChannel.kt\nkotlinx/coroutines/channels/BroadcastChannelImpl$SubscriberBuffered\n*L\n311#1:364\n*E\n"])
   private inner class SubscriberBuffered : BufferedChannel(`this$0`.getCapacity(), null, 2) {
      init {
         this.this$0 = `this$0`;
      }

      public open fun cancelImpl(cause: Throwable?): Boolean {
         label15: {
            val `$this$withLock$iv`: ReentrantLock = BroadcastChannelImpl.access$getLock$p(this.this$0);
            val var3: BroadcastChannelImpl = this.this$0;
            val var5: Lock = `$this$withLock$iv`;
            `$this$withLock$iv`.lock();

            try {
               BroadcastChannelImpl.access$removeSubscriber(var3, this);
               val var7: Boolean = super.cancelImpl$kotlinx_coroutines_core(cause);
            } catch (var9: java.lang.Throwable) {
               var5.unlock();
            }

            var5.unlock();
         }
      }
   }

   private inner class SubscriberConflated : ConflatedBufferedChannel(1, BufferOverflow.DROP_OLDEST, null, 4) {
      init {
         this.this$0 = `this$0`;
      }

      public open fun cancelImpl(cause: Throwable?): Boolean {
         BroadcastChannelImpl.access$removeSubscriber(this.this$0, this);
         return super.cancelImpl$kotlinx_coroutines_core(cause);
      }
   }
}
