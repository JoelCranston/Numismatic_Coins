package io.ktor.client.plugins.cache.storage

import io.ktor.client.plugins.cache.HttpCacheKt
import io.ktor.client.plugins.cache.storage.FileCacheStorage.readCache.3
import io.ktor.client.plugins.cache.storage.FileCacheStorage.store.2
import io.ktor.client.plugins.cache.storage.FileCacheStorage.updateCache.mutex.1
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLUtilsKt
import io.ktor.http.Url
import io.ktor.util.CryptoKt
import io.ktor.util.StringValuesKt
import io.ktor.util.collections.ConcurrentMap
import io.ktor.util.date.DateJvmKt
import io.ktor.util.date.GMTDate
import io.ktor.util.logging.LoggerJvmKt
import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import java.io.File
import java.security.MessageDigest
import java.util.Locale
import java.util.Map.Entry
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.MutexKt
import org.slf4j.Logger

@SourceDebugExtension(["SMAP\nFileCacheStorage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileCacheStorage.kt\nio/ktor/client/plugins/cache/storage/FileCacheStorage\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n+ 3 Logger.kt\nio/ktor/util/logging/LoggerKt\n+ 4 CoroutineScope.kt\nkotlinx/coroutines/CoroutineScopeKt\n*L\n1#1,262:1\n116#2,11:263\n116#2,11:274\n116#2,8:285\n125#2,2:295\n38#3,2:293\n38#3,2:298\n38#3,2:300\n375#4:297\n*S KotlinDebug\n*F\n+ 1 FileCacheStorage.kt\nio/ktor/client/plugins/cache/storage/FileCacheStorage\n*L\n120#1:263,11\n128#1:274,11\n136#1:285,8\n136#1:295,2\n143#1:293,2\n165#1:298,2\n187#1:300,2\n164#1:297\n*E\n"])
private class FileCacheStorage(directory: File, dispatcher: CoroutineDispatcher = Dispatchers.getIO()) : CacheStorage {
   private final val directory: File
   private final val dispatcher: CoroutineDispatcher
   private final val mutexes: ConcurrentMap<String, Mutex>

   init {
      this.directory = directory;
      this.dispatcher = dispatcher;
      this.mutexes = new ConcurrentMap<>(0, 1, null);
      this.directory.mkdirs();
   }

   public override suspend fun store(url: Url, data: CachedResponseData) {
      val var10000: Any = BuildersKt.withContext(this.dispatcher, new 2(this, url, data, null), `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   public override suspend fun findAll(url: Url): Set<CachedResponseData> {
      return BuildersKt.withContext(this.dispatcher, new io.ktor.client.plugins.cache.storage.FileCacheStorage.findAll.2(this, url, null), `$completion`);
   }

   public override suspend fun find(url: Url, varyKeys: Map<String, String>): CachedResponseData? {
      return BuildersKt.withContext(this.dispatcher, new io.ktor.client.plugins.cache.storage.FileCacheStorage.find.2(this, url, varyKeys, null), `$completion`);
   }

   public override suspend fun remove(url: Url, varyKeys: Map<String, String>) {
      val var10000: Any = BuildersKt.withContext(
         this.dispatcher, new io.ktor.client.plugins.cache.storage.FileCacheStorage.remove.2(this, url, varyKeys, null), `$completion`
      );
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   public override suspend fun removeAll(url: Url) {
      val var10000: Any = BuildersKt.withContext(
         this.dispatcher, new io.ktor.client.plugins.cache.storage.FileCacheStorage.removeAll.2(this, url, null), `$completion`
      );
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   private fun key(url: Url): String {
      val var10000: ByteArray = MessageDigest.getInstance("SHA-256").digest(StringsKt.encodeToByteArray(url.toString()));
      return CryptoKt.hex(var10000);
   }

   private suspend fun readCache(urlHex: String): Set<CachedResponseData> {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.IndexOutOfBoundsException: Index -1 out of bounds for length 0
      //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
      //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
      //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
      //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
      //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.removeExceptionInstructionsEx(FinallyProcessor.java:1064)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.verifyFinallyEx(FinallyProcessor.java:565)
      //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.iterateGraph(FinallyProcessor.java:90)
      //
      // Bytecode:
      // 000: aload 2
      // 001: instanceof io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1
      // 004: ifeq 027
      // 007: aload 2
      // 008: checkcast io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1
      // 00b: astore 11
      // 00d: aload 11
      // 00f: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.label I
      // 012: ldc -2147483648
      // 014: iand
      // 015: ifeq 027
      // 018: aload 11
      // 01a: dup
      // 01b: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.label I
      // 01e: ldc -2147483648
      // 020: isub
      // 021: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.label I
      // 024: goto 032
      // 027: new io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1
      // 02a: dup
      // 02b: aload 0
      // 02c: aload 2
      // 02d: invokespecial io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.<init> (Lio/ktor/client/plugins/cache/storage/FileCacheStorage;Lkotlin/coroutines/Continuation;)V
      // 030: astore 11
      // 032: aload 11
      // 034: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.result Ljava/lang/Object;
      // 037: astore 10
      // 039: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03c: astore 12
      // 03e: aload 11
      // 040: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.label I
      // 043: tableswitch 310 0 2 25 111 222
      // 05c: aload 10
      // 05e: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 061: aload 0
      // 062: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage.mutexes Lio/ktor/util/collections/ConcurrentMap;
      // 065: aload 1
      // 066: invokedynamic invoke ()Lkotlin/jvm/functions/Function0; bsm=java/lang/invoke/LambdaMetafactory.metafactory (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite; args=[ ()Ljava/lang/Object;, io/ktor/client/plugins/cache/storage/FileCacheStorage.readCache$lambda$0 ()Lkotlinx/coroutines/sync/Mutex;, ()Lkotlinx/coroutines/sync/Mutex; ]
      // 06b: invokevirtual io/ktor/util/collections/ConcurrentMap.computeIfAbsent (Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;
      // 06e: checkcast kotlinx/coroutines/sync/Mutex
      // 071: astore 3
      // 072: aload 3
      // 073: astore 4
      // 075: aconst_null
      // 076: astore 5
      // 078: bipush 0
      // 079: istore 6
      // 07b: aload 4
      // 07d: aload 5
      // 07f: aload 11
      // 081: aload 11
      // 083: aload 1
      // 084: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.L$0 Ljava/lang/Object;
      // 087: aload 11
      // 089: aload 3
      // 08a: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 08d: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.L$1 Ljava/lang/Object;
      // 090: aload 11
      // 092: aload 4
      // 094: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.L$2 Ljava/lang/Object;
      // 097: aload 11
      // 099: iload 6
      // 09b: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.I$0 I
      // 09e: aload 11
      // 0a0: bipush 1
      // 0a1: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.label I
      // 0a4: invokeinterface kotlinx/coroutines/sync/Mutex.lock (Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object; 3
      // 0a9: dup
      // 0aa: aload 12
      // 0ac: if_acmpne 0df
      // 0af: aload 12
      // 0b1: areturn
      // 0b2: aload 11
      // 0b4: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.I$0 I
      // 0b7: istore 6
      // 0b9: aconst_null
      // 0ba: astore 5
      // 0bc: aload 11
      // 0be: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.L$2 Ljava/lang/Object;
      // 0c1: checkcast kotlinx/coroutines/sync/Mutex
      // 0c4: astore 4
      // 0c6: aload 11
      // 0c8: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.L$1 Ljava/lang/Object;
      // 0cb: checkcast kotlinx/coroutines/sync/Mutex
      // 0ce: astore 3
      // 0cf: aload 11
      // 0d1: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.L$0 Ljava/lang/Object;
      // 0d4: checkcast java/lang/String
      // 0d7: astore 1
      // 0d8: aload 10
      // 0da: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 0dd: aload 10
      // 0df: pop
      // 0e0: nop
      // 0e1: bipush 0
      // 0e2: istore 7
      // 0e4: aload 0
      // 0e5: aload 1
      // 0e6: aload 11
      // 0e8: aload 11
      // 0ea: aload 1
      // 0eb: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 0ee: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.L$0 Ljava/lang/Object;
      // 0f1: aload 11
      // 0f3: aload 3
      // 0f4: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 0f7: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.L$1 Ljava/lang/Object;
      // 0fa: aload 11
      // 0fc: aload 4
      // 0fe: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.L$2 Ljava/lang/Object;
      // 101: aload 11
      // 103: iload 6
      // 105: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.I$0 I
      // 108: aload 11
      // 10a: iload 7
      // 10c: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.I$1 I
      // 10f: aload 11
      // 111: bipush 2
      // 112: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.label I
      // 115: invokespecial io/ktor/client/plugins/cache/storage/FileCacheStorage.readCacheUnsafe (Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
      // 118: dup
      // 119: aload 12
      // 11b: if_acmpne 156
      // 11e: aload 12
      // 120: areturn
      // 121: aload 11
      // 123: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.I$1 I
      // 126: istore 7
      // 128: aload 11
      // 12a: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.I$0 I
      // 12d: istore 6
      // 12f: aconst_null
      // 130: astore 5
      // 132: aload 11
      // 134: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.L$2 Ljava/lang/Object;
      // 137: checkcast kotlinx/coroutines/sync/Mutex
      // 13a: astore 4
      // 13c: aload 11
      // 13e: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.L$1 Ljava/lang/Object;
      // 141: checkcast kotlinx/coroutines/sync/Mutex
      // 144: astore 3
      // 145: aload 11
      // 147: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCache$1.L$0 Ljava/lang/Object;
      // 14a: checkcast java/lang/String
      // 14d: astore 1
      // 14e: nop
      // 14f: aload 10
      // 151: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 154: aload 10
      // 156: checkcast java/util/Set
      // 159: astore 8
      // 15b: aload 4
      // 15d: aload 5
      // 15f: invokeinterface kotlinx/coroutines/sync/Mutex.unlock (Ljava/lang/Object;)V 2
      // 164: goto 175
      // 167: astore 9
      // 169: aload 4
      // 16b: aload 5
      // 16d: invokeinterface kotlinx/coroutines/sync/Mutex.unlock (Ljava/lang/Object;)V 2
      // 172: aload 9
      // 174: athrow
      // 175: aload 8
      // 177: nop
      // 178: areturn
      // 179: new java/lang/IllegalStateException
      // 17c: dup
      // 17d: ldc_w "call to 'resume' before 'invoke' with coroutine"
      // 180: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 183: athrow
   }

   private suspend inline fun updateCache(urlHex: String, transform: (Set<CachedResponseData>) -> List<CachedResponseData>) {
      label17: {
         val mutex: Mutex = access$getMutexes$p(this).computeIfAbsent(urlHex, 1.INSTANCE);
         InlineMarker.mark(0);
         mutex.lock(null, `$completion`);
         InlineMarker.mark(1);

         try {
            InlineMarker.mark(3);
            InlineMarker.mark(0);
            val var10000: Any = access$readCacheUnsafe(this, urlHex, null);
            InlineMarker.mark(1);
            val var10002: java.util.List = transform.invoke(var10000 as java.util.Set) as java.util.List;
            InlineMarker.mark(3);
            InlineMarker.mark(0);
            access$writeCacheUnsafe(this, urlHex, var10002, null);
            InlineMarker.mark(1);
         } catch (var13: java.lang.Throwable) {
            InlineMarker.finallyStart(1);
            mutex.unlock(null);
            InlineMarker.finallyEnd(1);
         }

         InlineMarker.finallyStart(1);
         mutex.unlock(null);
         InlineMarker.finallyEnd(1);
      }
   }

   private suspend fun deleteCache(urlHex: String) {
      label50: {
         var `$continuation`: Continuation;
         label48: {
            if (`$completion` is io.ktor.client.plugins.cache.storage.FileCacheStorage.deleteCache.1) {
               `$continuation` = `$completion` as io.ktor.client.plugins.cache.storage.FileCacheStorage.deleteCache.1;
               if (((`$completion` as io.ktor.client.plugins.cache.storage.FileCacheStorage.deleteCache.1).label and Integer.MIN_VALUE) != 0) {
                  `$continuation`.label -= Integer.MIN_VALUE;
                  break label48;
               }
            }

            `$continuation` = new io.ktor.client.plugins.cache.storage.FileCacheStorage.deleteCache.1(this, `$completion`);
         }

         val `$result`: Any = `$continuation`.result;
         val var18: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var `$this$withLock_u24default$iv`: Mutex;
         var `owner$iv`: Any;
         switch ($continuation.label) {
            case 0: {
               ResultKt.throwOnFailure(`$result`);
               val var23: Mutex = this.mutexes.computeIfAbsent(urlHex, FileCacheStorage::deleteCache$lambda$0);
               `$this$withLock_u24default$iv` = var23;
               `owner$iv` = null;
               `$continuation`.L$0 = urlHex;
               `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var23);
               `$continuation`.L$2 = var23;
               `$continuation`.I$0 = 0;
               `$continuation`.label = 1;
               if (var23.lock(null, `$continuation`) === var18) {
                  return var18;
               }
               break;
            }
            case 1: {
               val `$i$f$withLock`: Int = `$continuation`.I$0;
               `owner$iv` = null;
               `$this$withLock_u24default$iv` = `$continuation`.L$2 as Mutex;
               val mutex: Mutex = `$continuation`.L$1 as Mutex;
               urlHex = `$continuation`.L$0 as java.lang.String;
               ResultKt.throwOnFailure(`$result`);
               break;
            }
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         try {
            val file: File = new File(this.directory, urlHex);
            if (file.exists()) {
               try {
                  file.delete();
               } catch (var19: Exception) {
                  val `$this$trace$iv`: Logger = HttpCacheKt.getLOGGER();
                  if (LoggerJvmKt.isTraceEnabled(`$this$trace$iv`)) {
                     `$this$trace$iv`.trace("Exception during cache deletion in a file: ${ExceptionsKt.stackTraceToString(var19)}");
                  }
               }
            }
         } catch (var20: java.lang.Throwable) {
            `$this$withLock_u24default$iv`.unlock(`owner$iv`);
         }

         `$this$withLock_u24default$iv`.unlock(`owner$iv`);
      }
   }

   private suspend fun writeCacheUnsafe(urlHex: String, caches: List<CachedResponseData>) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
      //
      // Bytecode:
      // 000: aload 3
      // 001: instanceof io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1
      // 004: ifeq 027
      // 007: aload 3
      // 008: checkcast io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1
      // 00b: astore 11
      // 00d: aload 11
      // 00f: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1.label I
      // 012: ldc -2147483648
      // 014: iand
      // 015: ifeq 027
      // 018: aload 11
      // 01a: dup
      // 01b: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1.label I
      // 01e: ldc -2147483648
      // 020: isub
      // 021: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1.label I
      // 024: goto 032
      // 027: new io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1
      // 02a: dup
      // 02b: aload 0
      // 02c: aload 3
      // 02d: invokespecial io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1.<init> (Lio/ktor/client/plugins/cache/storage/FileCacheStorage;Lkotlin/coroutines/Continuation;)V
      // 030: astore 11
      // 032: aload 11
      // 034: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1.result Ljava/lang/Object;
      // 037: astore 10
      // 039: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03c: astore 12
      // 03e: aload 11
      // 040: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1.label I
      // 043: tableswitch 250 0 1 21 100
      // 058: aload 10
      // 05a: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 05d: new io/ktor/utils/io/ByteChannel
      // 060: dup
      // 061: bipush 0
      // 062: bipush 1
      // 063: aconst_null
      // 064: invokespecial io/ktor/utils/io/ByteChannel.<init> (ZILkotlin/jvm/internal/DefaultConstructorMarker;)V
      // 067: astore 4
      // 069: nop
      // 06a: new io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$2
      // 06d: dup
      // 06e: aload 0
      // 06f: aload 1
      // 070: aload 4
      // 072: aload 2
      // 073: aconst_null
      // 074: invokespecial io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$2.<init> (Lio/ktor/client/plugins/cache/storage/FileCacheStorage;Ljava/lang/String;Lio/ktor/utils/io/ByteChannel;Ljava/util/List;Lkotlin/coroutines/Continuation;)V
      // 077: checkcast kotlin/jvm/functions/Function2
      // 07a: aload 11
      // 07c: aload 11
      // 07e: aload 1
      // 07f: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 082: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1.L$0 Ljava/lang/Object;
      // 085: aload 11
      // 087: aload 2
      // 088: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 08b: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1.L$1 Ljava/lang/Object;
      // 08e: aload 11
      // 090: aload 4
      // 092: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1.L$2 Ljava/lang/Object;
      // 095: aload 11
      // 097: bipush 1
      // 098: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1.label I
      // 09b: invokestatic kotlinx/coroutines/CoroutineScopeKt.coroutineScope (Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
      // 09e: dup
      // 09f: aload 12
      // 0a1: if_acmpne 0cb
      // 0a4: aload 12
      // 0a6: areturn
      // 0a7: aload 11
      // 0a9: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1.L$2 Ljava/lang/Object;
      // 0ac: checkcast io/ktor/utils/io/ByteChannel
      // 0af: astore 4
      // 0b1: aload 11
      // 0b3: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1.L$1 Ljava/lang/Object;
      // 0b6: checkcast java/util/List
      // 0b9: astore 2
      // 0ba: aload 11
      // 0bc: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$writeCacheUnsafe$1.L$0 Ljava/lang/Object;
      // 0bf: checkcast java/lang/String
      // 0c2: astore 1
      // 0c3: nop
      // 0c4: aload 10
      // 0c6: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 0c9: aload 10
      // 0cb: pop
      // 0cc: aload 4
      // 0ce: invokevirtual io/ktor/utils/io/ByteChannel.close ()V
      // 0d1: goto 139
      // 0d4: astore 5
      // 0d6: aload 5
      // 0d8: instanceof java/util/concurrent/CancellationException
      // 0db: ifeq 0ec
      // 0de: bipush 0
      // 0df: istore 6
      // 0e1: aload 11
      // 0e3: invokeinterface kotlin/coroutines/Continuation.getContext ()Lkotlin/coroutines/CoroutineContext; 1
      // 0e8: nop
      // 0e9: invokestatic kotlinx/coroutines/JobKt.ensureActive (Lkotlin/coroutines/CoroutineContext;)V
      // 0ec: invokestatic io/ktor/client/plugins/cache/HttpCacheKt.getLOGGER ()Lorg/slf4j/Logger;
      // 0ef: astore 6
      // 0f1: bipush 0
      // 0f2: istore 7
      // 0f4: aload 6
      // 0f6: invokestatic io/ktor/util/logging/LoggerJvmKt.isTraceEnabled (Lorg/slf4j/Logger;)Z
      // 0f9: ifeq 126
      // 0fc: aload 6
      // 0fe: astore 9
      // 100: bipush 0
      // 101: istore 8
      // 103: new java/lang/StringBuilder
      // 106: dup
      // 107: invokespecial java/lang/StringBuilder.<init> ()V
      // 10a: ldc_w "Exception during saving a cache to a file: "
      // 10d: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 110: aload 5
      // 112: checkcast java/lang/Throwable
      // 115: invokestatic kotlin/ExceptionsKt.stackTraceToString (Ljava/lang/Throwable;)Ljava/lang/String;
      // 118: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 11b: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 11e: aload 9
      // 120: swap
      // 121: invokeinterface org/slf4j/Logger.trace (Ljava/lang/String;)V 2
      // 126: nop
      // 127: aload 4
      // 129: invokevirtual io/ktor/utils/io/ByteChannel.close ()V
      // 12c: goto 139
      // 12f: astore 5
      // 131: aload 4
      // 133: invokevirtual io/ktor/utils/io/ByteChannel.close ()V
      // 136: aload 5
      // 138: athrow
      // 139: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
      // 13c: areturn
      // 13d: new java/lang/IllegalStateException
      // 140: dup
      // 141: ldc_w "call to 'resume' before 'invoke' with coroutine"
      // 144: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 147: athrow
   }

   private suspend fun readCacheUnsafe(urlHex: String): Set<CachedResponseData> {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.code.cfg.ExceptionRangeCFG.isCircular()" because "range" is null
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.graphToStatement(DomHelper.java:84)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.parseGraph(DomHelper.java:203)
      //   at org.jetbrains.java.decompiler.modules.decompiler.decompose.DomHelper.createStatement(DomHelper.java:27)
      //   at org.jetbrains.java.decompiler.main.rels.MethodProcessor.codeToJava(MethodProcessor.java:157)
      //
      // Bytecode:
      // 000: aload 2
      // 001: instanceof io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1
      // 004: ifeq 027
      // 007: aload 2
      // 008: checkcast io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1
      // 00b: astore 16
      // 00d: aload 16
      // 00f: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.label I
      // 012: ldc -2147483648
      // 014: iand
      // 015: ifeq 027
      // 018: aload 16
      // 01a: dup
      // 01b: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.label I
      // 01e: ldc -2147483648
      // 020: isub
      // 021: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.label I
      // 024: goto 032
      // 027: new io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1
      // 02a: dup
      // 02b: aload 0
      // 02c: aload 2
      // 02d: invokespecial io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.<init> (Lio/ktor/client/plugins/cache/storage/FileCacheStorage;Lkotlin/coroutines/Continuation;)V
      // 030: astore 16
      // 032: aload 16
      // 034: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.result Ljava/lang/Object;
      // 037: astore 15
      // 039: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
      // 03c: astore 17
      // 03e: aload 16
      // 040: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.label I
      // 043: tableswitch 804 0 3 29 208 408 620
      // 060: aload 15
      // 062: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 065: new java/io/File
      // 068: dup
      // 069: aload 0
      // 06a: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage.directory Ljava/io/File;
      // 06d: aload 1
      // 06e: invokespecial java/io/File.<init> (Ljava/io/File;Ljava/lang/String;)V
      // 071: astore 3
      // 072: aload 3
      // 073: invokevirtual java/io/File.exists ()Z
      // 076: ifne 07d
      // 079: invokestatic kotlin/collections/SetsKt.emptySet ()Ljava/util/Set;
      // 07c: areturn
      // 07d: nop
      // 07e: new java/io/FileInputStream
      // 081: dup
      // 082: aload 3
      // 083: invokespecial java/io/FileInputStream.<init> (Ljava/io/File;)V
      // 086: checkcast java/io/InputStream
      // 089: astore 4
      // 08b: sipush 8192
      // 08e: istore 5
      // 090: aload 4
      // 092: instanceof java/io/BufferedInputStream
      // 095: ifeq 0a0
      // 098: aload 4
      // 09a: checkcast java/io/BufferedInputStream
      // 09d: goto 0ab
      // 0a0: new java/io/BufferedInputStream
      // 0a3: dup
      // 0a4: aload 4
      // 0a6: iload 5
      // 0a8: invokespecial java/io/BufferedInputStream.<init> (Ljava/io/InputStream;I)V
      // 0ab: checkcast java/io/Closeable
      // 0ae: astore 4
      // 0b0: aconst_null
      // 0b1: astore 5
      // 0b3: nop
      // 0b4: aload 4
      // 0b6: checkcast java/io/BufferedInputStream
      // 0b9: astore 6
      // 0bb: bipush 0
      // 0bc: istore 7
      // 0be: aload 6
      // 0c0: checkcast java/io/InputStream
      // 0c3: aconst_null
      // 0c4: aconst_null
      // 0c5: bipush 3
      // 0c6: aconst_null
      // 0c7: invokestatic io/ktor/utils/io/jvm/javaio/ReadingKt.toByteReadChannelWithArrayPool$default (Ljava/io/InputStream;Lkotlin/coroutines/CoroutineContext;Lio/ktor/utils/io/pool/ObjectPool;ILjava/lang/Object;)Lio/ktor/utils/io/ByteReadChannel;
      // 0ca: astore 8
      // 0cc: aload 8
      // 0ce: aload 16
      // 0d0: aload 16
      // 0d2: aload 1
      // 0d3: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 0d6: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$0 Ljava/lang/Object;
      // 0d9: aload 16
      // 0db: aload 3
      // 0dc: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 0df: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$1 Ljava/lang/Object;
      // 0e2: aload 16
      // 0e4: aload 4
      // 0e6: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$2 Ljava/lang/Object;
      // 0e9: aload 16
      // 0eb: aload 6
      // 0ed: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 0f0: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$3 Ljava/lang/Object;
      // 0f3: aload 16
      // 0f5: aload 8
      // 0f7: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$4 Ljava/lang/Object;
      // 0fa: aload 16
      // 0fc: iload 7
      // 0fe: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.I$0 I
      // 101: aload 16
      // 103: bipush 1
      // 104: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.label I
      // 107: invokestatic io/ktor/utils/io/ByteReadChannelOperationsKt.readInt (Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
      // 10a: dup
      // 10b: aload 17
      // 10d: if_acmpne 155
      // 110: aload 17
      // 112: areturn
      // 113: aload 16
      // 115: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.I$0 I
      // 118: istore 7
      // 11a: aload 16
      // 11c: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$4 Ljava/lang/Object;
      // 11f: checkcast io/ktor/utils/io/ByteReadChannel
      // 122: astore 8
      // 124: aload 16
      // 126: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$3 Ljava/lang/Object;
      // 129: checkcast java/io/BufferedInputStream
      // 12c: astore 6
      // 12e: aconst_null
      // 12f: astore 5
      // 131: aload 16
      // 133: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$2 Ljava/lang/Object;
      // 136: checkcast java/io/Closeable
      // 139: astore 4
      // 13b: aload 16
      // 13d: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$1 Ljava/lang/Object;
      // 140: checkcast java/io/File
      // 143: astore 3
      // 144: aload 16
      // 146: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$0 Ljava/lang/Object;
      // 149: checkcast java/lang/String
      // 14c: astore 1
      // 14d: nop
      // 14e: aload 15
      // 150: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 153: aload 15
      // 155: checkcast java/lang/Number
      // 158: invokevirtual java/lang/Number.intValue ()I
      // 15b: istore 9
      // 15d: new java/util/LinkedHashSet
      // 160: dup
      // 161: invokespecial java/util/LinkedHashSet.<init> ()V
      // 164: checkcast java/util/Set
      // 167: astore 10
      // 169: bipush 0
      // 16a: istore 11
      // 16c: iload 11
      // 16e: iload 9
      // 170: if_icmpge 24e
      // 173: aload 10
      // 175: astore 12
      // 177: aload 0
      // 178: aload 8
      // 17a: aload 16
      // 17c: aload 16
      // 17e: aload 1
      // 17f: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 182: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$0 Ljava/lang/Object;
      // 185: aload 16
      // 187: aload 3
      // 188: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 18b: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$1 Ljava/lang/Object;
      // 18e: aload 16
      // 190: aload 4
      // 192: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$2 Ljava/lang/Object;
      // 195: aload 16
      // 197: aload 6
      // 199: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 19c: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$3 Ljava/lang/Object;
      // 19f: aload 16
      // 1a1: aload 8
      // 1a3: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$4 Ljava/lang/Object;
      // 1a6: aload 16
      // 1a8: aload 10
      // 1aa: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$5 Ljava/lang/Object;
      // 1ad: aload 16
      // 1af: aload 12
      // 1b1: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$6 Ljava/lang/Object;
      // 1b4: aload 16
      // 1b6: iload 7
      // 1b8: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.I$0 I
      // 1bb: aload 16
      // 1bd: iload 9
      // 1bf: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.I$1 I
      // 1c2: aload 16
      // 1c4: iload 11
      // 1c6: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.I$2 I
      // 1c9: aload 16
      // 1cb: bipush 2
      // 1cc: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.label I
      // 1cf: invokespecial io/ktor/client/plugins/cache/storage/FileCacheStorage.readCache (Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
      // 1d2: dup
      // 1d3: aload 17
      // 1d5: if_acmpne 23f
      // 1d8: aload 17
      // 1da: areturn
      // 1db: aload 16
      // 1dd: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.I$2 I
      // 1e0: istore 11
      // 1e2: aload 16
      // 1e4: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.I$1 I
      // 1e7: istore 9
      // 1e9: aload 16
      // 1eb: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.I$0 I
      // 1ee: istore 7
      // 1f0: aload 16
      // 1f2: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$6 Ljava/lang/Object;
      // 1f5: checkcast java/util/Set
      // 1f8: astore 12
      // 1fa: aload 16
      // 1fc: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$5 Ljava/lang/Object;
      // 1ff: checkcast java/util/Set
      // 202: astore 10
      // 204: aload 16
      // 206: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$4 Ljava/lang/Object;
      // 209: checkcast io/ktor/utils/io/ByteReadChannel
      // 20c: astore 8
      // 20e: aload 16
      // 210: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$3 Ljava/lang/Object;
      // 213: checkcast java/io/BufferedInputStream
      // 216: astore 6
      // 218: aconst_null
      // 219: astore 5
      // 21b: aload 16
      // 21d: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$2 Ljava/lang/Object;
      // 220: checkcast java/io/Closeable
      // 223: astore 4
      // 225: aload 16
      // 227: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$1 Ljava/lang/Object;
      // 22a: checkcast java/io/File
      // 22d: astore 3
      // 22e: aload 16
      // 230: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$0 Ljava/lang/Object;
      // 233: checkcast java/lang/String
      // 236: astore 1
      // 237: nop
      // 238: aload 15
      // 23a: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 23d: aload 15
      // 23f: aload 12
      // 241: swap
      // 242: invokeinterface java/util/Set.add (Ljava/lang/Object;)Z 2
      // 247: pop
      // 248: iinc 11 1
      // 24b: goto 16c
      // 24e: aload 8
      // 250: lconst_0
      // 251: aload 16
      // 253: bipush 1
      // 254: aconst_null
      // 255: aload 16
      // 257: aload 1
      // 258: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 25b: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$0 Ljava/lang/Object;
      // 25e: aload 16
      // 260: aload 3
      // 261: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 264: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$1 Ljava/lang/Object;
      // 267: aload 16
      // 269: aload 4
      // 26b: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$2 Ljava/lang/Object;
      // 26e: aload 16
      // 270: aload 6
      // 272: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 275: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$3 Ljava/lang/Object;
      // 278: aload 16
      // 27a: aload 8
      // 27c: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
      // 27f: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$4 Ljava/lang/Object;
      // 282: aload 16
      // 284: aload 10
      // 286: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$5 Ljava/lang/Object;
      // 289: aload 16
      // 28b: aconst_null
      // 28c: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$6 Ljava/lang/Object;
      // 28f: aload 16
      // 291: iload 7
      // 293: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.I$0 I
      // 296: aload 16
      // 298: iload 9
      // 29a: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.I$1 I
      // 29d: aload 16
      // 29f: bipush 3
      // 2a0: putfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.label I
      // 2a3: invokestatic io/ktor/utils/io/ByteReadChannelOperationsKt.discard$default (Lio/ktor/utils/io/ByteReadChannel;JLkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
      // 2a6: dup
      // 2a7: aload 17
      // 2a9: if_acmpne 302
      // 2ac: aload 17
      // 2ae: areturn
      // 2af: aload 16
      // 2b1: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.I$1 I
      // 2b4: istore 9
      // 2b6: aload 16
      // 2b8: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.I$0 I
      // 2bb: istore 7
      // 2bd: aload 16
      // 2bf: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$5 Ljava/lang/Object;
      // 2c2: checkcast java/util/Set
      // 2c5: astore 10
      // 2c7: aload 16
      // 2c9: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$4 Ljava/lang/Object;
      // 2cc: checkcast io/ktor/utils/io/ByteReadChannel
      // 2cf: astore 8
      // 2d1: aload 16
      // 2d3: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$3 Ljava/lang/Object;
      // 2d6: checkcast java/io/BufferedInputStream
      // 2d9: astore 6
      // 2db: aconst_null
      // 2dc: astore 5
      // 2de: aload 16
      // 2e0: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$2 Ljava/lang/Object;
      // 2e3: checkcast java/io/Closeable
      // 2e6: astore 4
      // 2e8: aload 16
      // 2ea: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$1 Ljava/lang/Object;
      // 2ed: checkcast java/io/File
      // 2f0: astore 3
      // 2f1: aload 16
      // 2f3: getfield io/ktor/client/plugins/cache/storage/FileCacheStorage$readCacheUnsafe$1.L$0 Ljava/lang/Object;
      // 2f6: checkcast java/lang/String
      // 2f9: astore 1
      // 2fa: nop
      // 2fb: aload 15
      // 2fd: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
      // 300: aload 15
      // 302: pop
      // 303: aload 10
      // 305: astore 13
      // 307: aload 4
      // 309: aload 5
      // 30b: invokestatic kotlin/io/CloseableKt.closeFinally (Ljava/io/Closeable;Ljava/lang/Throwable;)V
      // 30e: aload 13
      // 310: areturn
      // 311: astore 7
      // 313: aload 7
      // 315: astore 5
      // 317: aload 7
      // 319: athrow
      // 31a: astore 7
      // 31c: aload 4
      // 31e: aload 5
      // 320: invokestatic kotlin/io/CloseableKt.closeFinally (Ljava/io/Closeable;Ljava/lang/Throwable;)V
      // 323: aload 7
      // 325: athrow
      // 326: astore 5
      // 328: invokestatic io/ktor/client/plugins/cache/HttpCacheKt.getLOGGER ()Lorg/slf4j/Logger;
      // 32b: astore 6
      // 32d: bipush 0
      // 32e: istore 7
      // 330: aload 6
      // 332: invokestatic io/ktor/util/logging/LoggerJvmKt.isTraceEnabled (Lorg/slf4j/Logger;)Z
      // 335: ifeq 362
      // 338: aload 6
      // 33a: astore 14
      // 33c: bipush 0
      // 33d: istore 8
      // 33f: new java/lang/StringBuilder
      // 342: dup
      // 343: invokespecial java/lang/StringBuilder.<init> ()V
      // 346: ldc_w "Exception during cache lookup in a file: "
      // 349: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 34c: aload 5
      // 34e: checkcast java/lang/Throwable
      // 351: invokestatic kotlin/ExceptionsKt.stackTraceToString (Ljava/lang/Throwable;)Ljava/lang/String;
      // 354: invokevirtual java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
      // 357: invokevirtual java/lang/StringBuilder.toString ()Ljava/lang/String;
      // 35a: aload 14
      // 35c: swap
      // 35d: invokeinterface org/slf4j/Logger.trace (Ljava/lang/String;)V 2
      // 362: nop
      // 363: invokestatic kotlin/collections/SetsKt.emptySet ()Ljava/util/Set;
      // 366: areturn
      // 367: new java/lang/IllegalStateException
      // 36a: dup
      // 36b: ldc_w "call to 'resume' before 'invoke' with coroutine"
      // 36e: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
      // 371: athrow
   }

   private suspend fun writeCache(channel: ByteChannel, cache: CachedResponseData) {
      var `$continuation`: Continuation;
      label186: {
         if (`$completion` is io.ktor.client.plugins.cache.storage.FileCacheStorage.writeCache.1) {
            `$continuation` = `$completion` as io.ktor.client.plugins.cache.storage.FileCacheStorage.writeCache.1;
            if (((`$completion` as io.ktor.client.plugins.cache.storage.FileCacheStorage.writeCache.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label186;
            }
         }

         `$continuation` = new io.ktor.client.plugins.cache.storage.FileCacheStorage.writeCache.1(this, `$completion`);
      }

      var var5: java.util.Iterator;
      var var11: Any;
      var var17: java.util.List;
      label223: {
         label224: {
            label198: {
               label199: {
                  val `$result`: Any = `$continuation`.result;
                  var11 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  switch ($continuation.label) {
                     case 0:
                        ResultKt.throwOnFailure(`$result`);
                        var var36: ByteWriteChannel = channel;
                        val var57: java.lang.String = "${cache.getUrl()}
";
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = cache;
                        `$continuation`.label = 1;
                        if (ByteWriteChannelOperationsKt.writeStringUtf8(var36, var57, `$continuation`) === var11) {
                           return var11;
                        }

                        var36 = channel;
                        val var58: Int = cache.getStatusCode().getValue();
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = cache;
                        `$continuation`.label = 2;
                        if (ByteWriteChannelOperationsKt.writeInt(var36, var58, `$continuation`) === var11) {
                           return var11;
                        }
                        break;
                     case 1:
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        val var35: ByteWriteChannel = channel;
                        val var56: Int = cache.getStatusCode().getValue();
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = cache;
                        `$continuation`.label = 2;
                        if (ByteWriteChannelOperationsKt.writeInt(var35, var56, `$continuation`) === var11) {
                           return var11;
                        }
                        break;
                     case 2:
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        break;
                     case 3:
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        val var34: ByteWriteChannel = channel;
                        val var55: java.lang.String = "${cache.getVersion()}
";
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = cache;
                        `$continuation`.label = 4;
                        if (ByteWriteChannelOperationsKt.writeStringUtf8(var34, var55, `$continuation`) === var11) {
                           return var11;
                        }
                        break label199;
                     case 4:
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        break label199;
                     case 5:
                        var17 = `$continuation`.L$2 as java.util.List;
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        var5 = var17.iterator();
                        break label198;
                     case 6:
                        val var27: java.lang.String = `$continuation`.L$5 as java.lang.String;
                        val var22: java.lang.String = `$continuation`.L$4 as java.lang.String;
                        var5 = `$continuation`.L$3 as java.util.Iterator;
                        var17 = `$continuation`.L$2 as java.util.List;
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        val var33: ByteWriteChannel = channel;
                        val var54: java.lang.String = "$var27
";
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = cache;
                        `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
                        `$continuation`.L$3 = var5;
                        `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var22);
                        `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(var27);
                        `$continuation`.label = 7;
                        if (ByteWriteChannelOperationsKt.writeStringUtf8(var33, var54, `$continuation`) === var11) {
                           return var11;
                        }
                        break label198;
                     case 7:
                        val valuex: java.lang.String = `$continuation`.L$5 as java.lang.String;
                        val keyx: java.lang.String = `$continuation`.L$4 as java.lang.String;
                        var5 = `$continuation`.L$3 as java.util.Iterator;
                        var17 = `$continuation`.L$2 as java.util.List;
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        break label198;
                     case 8:
                        var17 = `$continuation`.L$2 as java.util.List;
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        val var32: ByteWriteChannel = channel;
                        val var53: Long = cache.getResponseTime().getTimestamp();
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = cache;
                        `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
                        `$continuation`.label = 9;
                        if (ByteWriteChannelOperationsKt.writeLong(var32, var53, `$continuation`) === var11) {
                           return var11;
                        }
                        break label224;
                     case 9:
                        var17 = `$continuation`.L$2 as java.util.List;
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        break label224;
                     case 10:
                        var17 = `$continuation`.L$2 as java.util.List;
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        val var31: ByteWriteChannel = channel;
                        val var52: Int = cache.getVaryKeys().size();
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = cache;
                        `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
                        `$continuation`.label = 11;
                        if (ByteWriteChannelOperationsKt.writeInt(var31, var52, `$continuation`) === var11) {
                           return var11;
                        }

                        var5 = cache.getVaryKeys().entrySet().iterator();
                        break label223;
                     case 11:
                        var17 = `$continuation`.L$2 as java.util.List;
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        var5 = cache.getVaryKeys().entrySet().iterator();
                        break label223;
                     case 12:
                        val valuexx: java.lang.String = `$continuation`.L$5 as java.lang.String;
                        val keyxx: java.lang.String = `$continuation`.L$4 as java.lang.String;
                        var5 = `$continuation`.L$3 as java.util.Iterator;
                        var17 = `$continuation`.L$2 as java.util.List;
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        val var30: ByteWriteChannel = channel;
                        val var51: java.lang.String = "$valuexx
";
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = cache;
                        `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
                        `$continuation`.L$3 = var5;
                        `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(keyxx);
                        `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(valuexx);
                        `$continuation`.label = 13;
                        if (ByteWriteChannelOperationsKt.writeStringUtf8(var30, var51, `$continuation`) === var11) {
                           return var11;
                        }
                        break label223;
                     case 13:
                        val valuexxx: java.lang.String = `$continuation`.L$5 as java.lang.String;
                        val keyxxx: java.lang.String = `$continuation`.L$4 as java.lang.String;
                        var5 = `$continuation`.L$3 as java.util.Iterator;
                        var17 = `$continuation`.L$2 as java.util.List;
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        break label223;
                     case 14:
                        var17 = `$continuation`.L$2 as java.util.List;
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        val var10000: ByteWriteChannel = channel;
                        val var10001: ByteArray = cache.getBody();
                        `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(channel);
                        `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(cache);
                        `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
                        `$continuation`.label = 15;
                        if (ByteWriteChannelOperationsKt.writeFully$default(var10000, var10001, 0, 0, `$continuation`, 6, null) === var11) {
                           return var11;
                        }

                        return Unit.INSTANCE;
                     case 15:
                        var17 = `$continuation`.L$2 as java.util.List;
                        cache = `$continuation`.L$1 as CachedResponseData;
                        channel = `$continuation`.L$0 as ByteChannel;
                        ResultKt.throwOnFailure(`$result`);
                        return Unit.INSTANCE;
                     default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  var var38: ByteWriteChannel = channel;
                  var var59: java.lang.String = "${cache.getStatusCode().getDescription()}
";
                  `$continuation`.L$0 = channel;
                  `$continuation`.L$1 = cache;
                  `$continuation`.label = 3;
                  if (ByteWriteChannelOperationsKt.writeStringUtf8(var38, var59, `$continuation`) === var11) {
                     return var11;
                  }

                  var38 = channel;
                  var59 = "${cache.getVersion()}
";
                  `$continuation`.L$0 = channel;
                  `$continuation`.L$1 = cache;
                  `$continuation`.label = 4;
                  if (ByteWriteChannelOperationsKt.writeStringUtf8(var38, var59, `$continuation`) === var11) {
                     return var11;
                  }
               }

               var17 = StringValuesKt.flattenEntries(cache.getHeaders());
               val var40: ByteWriteChannel = channel;
               val var61: Int = var17.size();
               `$continuation`.L$0 = channel;
               `$continuation`.L$1 = cache;
               `$continuation`.L$2 = var17;
               `$continuation`.label = 5;
               if (ByteWriteChannelOperationsKt.writeInt(var40, var61, `$continuation`) === var11) {
                  return var11;
               }

               var5 = var17.iterator();
            }

            while (var5.hasNext()) {
               val var6: Pair = var5.next() as Pair;
               val keyx: java.lang.String = var6.component1() as java.lang.String;
               val valuex: java.lang.String = var6.component2() as java.lang.String;
               var var41: ByteWriteChannel = channel;
               var var62: java.lang.String = "$keyx
";
               `$continuation`.L$0 = channel;
               `$continuation`.L$1 = cache;
               `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
               `$continuation`.L$3 = var5;
               `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(keyx);
               `$continuation`.L$5 = valuex;
               `$continuation`.label = 6;
               if (ByteWriteChannelOperationsKt.writeStringUtf8(var41, var62, `$continuation`) === var11) {
                  return var11;
               }

               var41 = channel;
               var62 = "$valuex
";
               `$continuation`.L$0 = channel;
               `$continuation`.L$1 = cache;
               `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
               `$continuation`.L$3 = var5;
               `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(keyx);
               `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(valuex);
               `$continuation`.label = 7;
               if (ByteWriteChannelOperationsKt.writeStringUtf8(var41, var62, `$continuation`) === var11) {
                  return var11;
               }
            }

            var var43: ByteWriteChannel = channel;
            var var64: Long = cache.getRequestTime().getTimestamp();
            `$continuation`.L$0 = channel;
            `$continuation`.L$1 = cache;
            `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
            `$continuation`.L$3 = null;
            `$continuation`.L$4 = null;
            `$continuation`.L$5 = null;
            `$continuation`.label = 8;
            if (ByteWriteChannelOperationsKt.writeLong(var43, var64, `$continuation`) === var11) {
               return var11;
            }

            var43 = channel;
            var64 = cache.getResponseTime().getTimestamp();
            `$continuation`.L$0 = channel;
            `$continuation`.L$1 = cache;
            `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
            `$continuation`.label = 9;
            if (ByteWriteChannelOperationsKt.writeLong(var43, var64, `$continuation`) === var11) {
               return var11;
            }
         }

         var var45: ByteWriteChannel = channel;
         val var66: Long = cache.getExpires().getTimestamp();
         `$continuation`.L$0 = channel;
         `$continuation`.L$1 = cache;
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
         `$continuation`.label = 10;
         if (ByteWriteChannelOperationsKt.writeLong(var45, var66, `$continuation`) === var11) {
            return var11;
         }

         var45 = channel;
         val var67: Int = cache.getVaryKeys().size();
         `$continuation`.L$0 = channel;
         `$continuation`.L$1 = cache;
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
         `$continuation`.label = 11;
         if (ByteWriteChannelOperationsKt.writeInt(var45, var67, `$continuation`) === var11) {
            return var11;
         }

         var5 = cache.getVaryKeys().entrySet().iterator();
      }

      while (var5.hasNext()) {
         val var19: Entry = var5.next() as Entry;
         val keyxx: java.lang.String = var19.getKey() as java.lang.String;
         val valuexx: java.lang.String = var19.getValue() as java.lang.String;
         var var47: ByteWriteChannel = channel;
         var var68: java.lang.String = "$keyxx
";
         `$continuation`.L$0 = channel;
         `$continuation`.L$1 = cache;
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
         `$continuation`.L$3 = var5;
         `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(keyxx);
         `$continuation`.L$5 = valuexx;
         `$continuation`.label = 12;
         if (ByteWriteChannelOperationsKt.writeStringUtf8(var47, var68, `$continuation`) === var11) {
            return var11;
         }

         var47 = channel;
         var68 = "$valuexx
";
         `$continuation`.L$0 = channel;
         `$continuation`.L$1 = cache;
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
         `$continuation`.L$3 = var5;
         `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(keyxx);
         `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(valuexx);
         `$continuation`.label = 13;
         if (ByteWriteChannelOperationsKt.writeStringUtf8(var47, var68, `$continuation`) === var11) {
            return var11;
         }
      }

      var var49: ByteWriteChannel = channel;
      val var70: Int = cache.getBody().length;
      `$continuation`.L$0 = channel;
      `$continuation`.L$1 = cache;
      `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
      `$continuation`.L$3 = null;
      `$continuation`.L$4 = null;
      `$continuation`.L$5 = null;
      `$continuation`.label = 14;
      if (ByteWriteChannelOperationsKt.writeInt(var49, var70, `$continuation`) === var11) {
         return var11;
      } else {
         var49 = channel;
         val var71: ByteArray = cache.getBody();
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(channel);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(cache);
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var17);
         `$continuation`.label = 15;
         return if (ByteWriteChannelOperationsKt.writeFully$default(var49, var71, 0, 0, `$continuation`, 6, null) === var11) var11 else Unit.INSTANCE;
      }
   }

   private suspend fun readCache(channel: ByteReadChannel): CachedResponseData {
      var `$continuation`: Continuation;
      label186: {
         if (`$completion` is 3) {
            `$continuation` = `$completion` as 3;
            if (((`$completion` as 3).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label186;
            }
         }

         `$continuation` = new 3(this, `$completion`);
      }

      var var15: Int;
      var var26: Any;
      var var30: Int;
      var var41: Int;
      var var43: java.util.Map;
      var var45: java.util.Map;
      var var47: Int;
      var url: java.lang.String;
      var status: HttpStatusCode;
      var version: HttpProtocolVersion;
      var headers: HeadersBuilder;
      var requestTime: GMTDate;
      var responseTime: GMTDate;
      var expirationTime: GMTDate;
      label223: {
         var var56: Any;
         label224: {
            label198: {
               var var19: HttpProtocolVersion.Companion;
               label199: {
                  val `$result`: Any = `$continuation`.result;
                  var26 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  switch ($continuation.label) {
                     case 0:
                        ResultKt.throwOnFailure(`$result`);
                        `$continuation`.L$0 = channel;
                        `$continuation`.label = 1;
                        var56 = ByteReadChannelOperationsKt.readUTF8Line$default(channel, 0, `$continuation`, 1, null);
                        if (var56 === var26) {
                           return var26;
                        }

                        url = var56 as java.lang.String;
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = url;
                        `$continuation`.label = 2;
                        var56 = ByteReadChannelOperationsKt.readInt(channel, `$continuation`);
                        if (var56 === var26) {
                           return var26;
                        }
                        break;
                     case 1:
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        url = `$result` as java.lang.String;
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = url;
                        `$continuation`.label = 2;
                        var56 = ByteReadChannelOperationsKt.readInt(channel, `$continuation`);
                        if (var56 === var26) {
                           return var26;
                        }
                        break;
                     case 2:
                        url = `$continuation`.L$1 as java.lang.String;
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        var56 = `$result`;
                        break;
                     case 3:
                        val var20: Int = `$continuation`.I$0;
                        url = `$continuation`.L$1 as java.lang.String;
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        status = new HttpStatusCode(var20, `$result` as java.lang.String);
                        var19 = HttpProtocolVersion.Companion;
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = url;
                        `$continuation`.L$2 = status;
                        `$continuation`.L$3 = var19;
                        `$continuation`.label = 4;
                        var56 = ByteReadChannelOperationsKt.readUTF8Line$default(channel, 0, `$continuation`, 1, null);
                        if (var56 === var26) {
                           return var26;
                        }
                        break label199;
                     case 4:
                        var19 = `$continuation`.L$3 as HttpProtocolVersion.Companion;
                        status = `$continuation`.L$2 as HttpStatusCode;
                        url = `$continuation`.L$1 as java.lang.String;
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        var56 = `$result`;
                        break label199;
                     case 5:
                        version = `$continuation`.L$3 as HttpProtocolVersion;
                        status = `$continuation`.L$2 as HttpStatusCode;
                        url = `$continuation`.L$1 as java.lang.String;
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        var30 = (`$result` as java.lang.Number).intValue();
                        headers = new HeadersBuilder(0, 1, null);
                        var33 = 0;
                        break label198;
                     case 6:
                        var33 = `$continuation`.I$1;
                        var30 = `$continuation`.I$0;
                        headers = `$continuation`.L$4 as HeadersBuilder;
                        version = `$continuation`.L$3 as HttpProtocolVersion;
                        status = `$continuation`.L$2 as HttpStatusCode;
                        url = `$continuation`.L$1 as java.lang.String;
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        val var35: java.lang.String = `$result` as java.lang.String;
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = url;
                        `$continuation`.L$2 = status;
                        `$continuation`.L$3 = version;
                        `$continuation`.L$4 = headers;
                        `$continuation`.L$5 = var35;
                        `$continuation`.I$0 = var30;
                        `$continuation`.I$1 = var33;
                        `$continuation`.label = 7;
                        var56 = ByteReadChannelOperationsKt.readUTF8Line$default(channel, 0, `$continuation`, 1, null);
                        if (var56 === var26) {
                           return var26;
                        }

                        headers.append(var35, var56 as java.lang.String);
                        var33++;
                        break label198;
                     case 7: {
                        var33 = `$continuation`.I$1;
                        var30 = `$continuation`.I$0;
                        val var34: java.lang.String = `$continuation`.L$5 as java.lang.String;
                        headers = `$continuation`.L$4 as HeadersBuilder;
                        version = `$continuation`.L$3 as HttpProtocolVersion;
                        status = `$continuation`.L$2 as HttpStatusCode;
                        url = `$continuation`.L$1 as java.lang.String;
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        headers.append(var34, `$result` as java.lang.String);
                        var33++;
                        break label198;
                     }
                     case 8:
                        var30 = `$continuation`.I$0;
                        headers = `$continuation`.L$4 as HeadersBuilder;
                        version = `$continuation`.L$3 as HttpProtocolVersion;
                        status = `$continuation`.L$2 as HttpStatusCode;
                        url = `$continuation`.L$1 as java.lang.String;
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        requestTime = DateJvmKt.GMTDate(`$result` as java.lang.Long);
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = url;
                        `$continuation`.L$2 = status;
                        `$continuation`.L$3 = version;
                        `$continuation`.L$4 = headers;
                        `$continuation`.L$5 = requestTime;
                        `$continuation`.I$0 = var30;
                        `$continuation`.label = 9;
                        var56 = ByteReadChannelOperationsKt.readLong(channel, `$continuation`);
                        if (var56 === var26) {
                           return var26;
                        }
                        break label224;
                     case 9:
                        var30 = `$continuation`.I$0;
                        requestTime = `$continuation`.L$5 as GMTDate;
                        headers = `$continuation`.L$4 as HeadersBuilder;
                        version = `$continuation`.L$3 as HttpProtocolVersion;
                        status = `$continuation`.L$2 as HttpStatusCode;
                        url = `$continuation`.L$1 as java.lang.String;
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        var56 = `$result`;
                        break label224;
                     case 10:
                        var30 = `$continuation`.I$0;
                        responseTime = `$continuation`.L$6 as GMTDate;
                        requestTime = `$continuation`.L$5 as GMTDate;
                        headers = `$continuation`.L$4 as HeadersBuilder;
                        version = `$continuation`.L$3 as HttpProtocolVersion;
                        status = `$continuation`.L$2 as HttpStatusCode;
                        url = `$continuation`.L$1 as java.lang.String;
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        expirationTime = DateJvmKt.GMTDate(`$result` as java.lang.Long);
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = url;
                        `$continuation`.L$2 = status;
                        `$continuation`.L$3 = version;
                        `$continuation`.L$4 = headers;
                        `$continuation`.L$5 = requestTime;
                        `$continuation`.L$6 = responseTime;
                        `$continuation`.L$7 = expirationTime;
                        `$continuation`.I$0 = var30;
                        `$continuation`.label = 11;
                        var56 = ByteReadChannelOperationsKt.readInt(channel, `$continuation`);
                        if (var56 === var26) {
                           return var26;
                        }

                        var41 = (var56 as java.lang.Number).intValue();
                        var43 = MapsKt.createMapBuilder();
                        var45 = var43;
                        var15 = 0;
                        var47 = 0;
                        break label223;
                     case 11:
                        var30 = `$continuation`.I$0;
                        expirationTime = `$continuation`.L$7 as GMTDate;
                        responseTime = `$continuation`.L$6 as GMTDate;
                        requestTime = `$continuation`.L$5 as GMTDate;
                        headers = `$continuation`.L$4 as HeadersBuilder;
                        version = `$continuation`.L$3 as HttpProtocolVersion;
                        status = `$continuation`.L$2 as HttpStatusCode;
                        url = `$continuation`.L$1 as java.lang.String;
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        var41 = (`$result` as java.lang.Number).intValue();
                        var43 = MapsKt.createMapBuilder();
                        var45 = var43;
                        var15 = 0;
                        var47 = 0;
                        break label223;
                     case 12:
                        var47 = `$continuation`.I$3;
                        var15 = `$continuation`.I$2;
                        var41 = `$continuation`.I$1;
                        var30 = `$continuation`.I$0;
                        var45 = `$continuation`.L$9 as java.util.Map;
                        var43 = `$continuation`.L$8 as java.util.Map;
                        expirationTime = `$continuation`.L$7 as GMTDate;
                        responseTime = `$continuation`.L$6 as GMTDate;
                        requestTime = `$continuation`.L$5 as GMTDate;
                        headers = `$continuation`.L$4 as HeadersBuilder;
                        version = `$continuation`.L$3 as HttpProtocolVersion;
                        status = `$continuation`.L$2 as HttpStatusCode;
                        url = `$continuation`.L$1 as java.lang.String;
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        var56 = (`$result` as java.lang.String).toLowerCase(Locale.ROOT);
                        `$continuation`.L$0 = channel;
                        `$continuation`.L$1 = url;
                        `$continuation`.L$2 = status;
                        `$continuation`.L$3 = version;
                        `$continuation`.L$4 = headers;
                        `$continuation`.L$5 = requestTime;
                        `$continuation`.L$6 = responseTime;
                        `$continuation`.L$7 = expirationTime;
                        `$continuation`.L$8 = var43;
                        `$continuation`.L$9 = var45;
                        `$continuation`.L$10 = var56;
                        `$continuation`.I$0 = var30;
                        `$continuation`.I$1 = var41;
                        `$continuation`.I$2 = var15;
                        `$continuation`.I$3 = var47;
                        `$continuation`.label = 13;
                        var56 = ByteReadChannelOperationsKt.readUTF8Line$default(channel, 0, `$continuation`, 1, null);
                        if (var56 === var26) {
                           return var26;
                        }

                        var45.put(var56, var56 as java.lang.String);
                        var47++;
                        break label223;
                     case 13: {
                        var47 = `$continuation`.I$3;
                        var15 = `$continuation`.I$2;
                        var41 = `$continuation`.I$1;
                        var30 = `$continuation`.I$0;
                        val key: java.lang.String = `$continuation`.L$10 as java.lang.String;
                        var45 = `$continuation`.L$9 as java.util.Map;
                        var43 = `$continuation`.L$8 as java.util.Map;
                        expirationTime = `$continuation`.L$7 as GMTDate;
                        responseTime = `$continuation`.L$6 as GMTDate;
                        requestTime = `$continuation`.L$5 as GMTDate;
                        headers = `$continuation`.L$4 as HeadersBuilder;
                        version = `$continuation`.L$3 as HttpProtocolVersion;
                        status = `$continuation`.L$2 as HttpStatusCode;
                        url = `$continuation`.L$1 as java.lang.String;
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        var45.put(key, `$result` as java.lang.String);
                        var47++;
                        break label223;
                     }
                     case 14:
                        var41 = `$continuation`.I$1;
                        var30 = `$continuation`.I$0;
                        val varyKeys: java.util.Map = `$continuation`.L$8 as java.util.Map;
                        expirationTime = `$continuation`.L$7 as GMTDate;
                        responseTime = `$continuation`.L$6 as GMTDate;
                        requestTime = `$continuation`.L$5 as GMTDate;
                        headers = `$continuation`.L$4 as HeadersBuilder;
                        version = `$continuation`.L$3 as HttpProtocolVersion;
                        status = `$continuation`.L$2 as HttpStatusCode;
                        url = `$continuation`.L$1 as java.lang.String;
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        val var42: Int = (`$result` as java.lang.Number).intValue();
                        val body: ByteArray = new byte[var42];
                        `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(channel);
                        `$continuation`.L$1 = url;
                        `$continuation`.L$2 = status;
                        `$continuation`.L$3 = version;
                        `$continuation`.L$4 = headers;
                        `$continuation`.L$5 = requestTime;
                        `$continuation`.L$6 = responseTime;
                        `$continuation`.L$7 = expirationTime;
                        `$continuation`.L$8 = varyKeys;
                        `$continuation`.L$9 = body;
                        `$continuation`.I$0 = var30;
                        `$continuation`.I$1 = var41;
                        `$continuation`.I$2 = var42;
                        `$continuation`.label = 15;
                        if (ByteReadChannelOperationsKt.readFully$default(channel, body, 0, 0, `$continuation`, 6, null) === var26) {
                           return var26;
                        }

                        return new CachedResponseData(
                           URLUtilsKt.Url(url), status, requestTime, responseTime, version, expirationTime, headers.build(), varyKeys, body
                        );
                     case 15:
                        val bodyCount: Int = `$continuation`.I$2;
                        var41 = `$continuation`.I$1;
                        var30 = `$continuation`.I$0;
                        val body: ByteArray = `$continuation`.L$9 as ByteArray;
                        val varyKeys: java.util.Map = `$continuation`.L$8 as java.util.Map;
                        expirationTime = `$continuation`.L$7 as GMTDate;
                        responseTime = `$continuation`.L$6 as GMTDate;
                        requestTime = `$continuation`.L$5 as GMTDate;
                        headers = `$continuation`.L$4 as HeadersBuilder;
                        version = `$continuation`.L$3 as HttpProtocolVersion;
                        status = `$continuation`.L$2 as HttpStatusCode;
                        url = `$continuation`.L$1 as java.lang.String;
                        channel = `$continuation`.L$0 as ByteReadChannel;
                        ResultKt.throwOnFailure(`$result`);
                        return new CachedResponseData(
                           URLUtilsKt.Url(url), status, requestTime, responseTime, version, expirationTime, headers.build(), varyKeys, body
                        );
                     default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  val var52: Int = (var56 as java.lang.Number).intValue();
                  `$continuation`.L$0 = channel;
                  `$continuation`.L$1 = url;
                  `$continuation`.I$0 = var52;
                  `$continuation`.label = 3;
                  var56 = ByteReadChannelOperationsKt.readUTF8Line$default(channel, 0, `$continuation`, 1, null);
                  if (var56 === var26) {
                     return var26;
                  }

                  status = new HttpStatusCode(var52, var56 as java.lang.String);
                  var19 = HttpProtocolVersion.Companion;
                  `$continuation`.L$0 = channel;
                  `$continuation`.L$1 = url;
                  `$continuation`.L$2 = status;
                  `$continuation`.L$3 = var19;
                  `$continuation`.label = 4;
                  var56 = ByteReadChannelOperationsKt.readUTF8Line$default(channel, 0, `$continuation`, 1, null);
                  if (var56 === var26) {
                     return var26;
                  }
               }

               version = var19.parse(var56 as java.lang.CharSequence);
               `$continuation`.L$0 = channel;
               `$continuation`.L$1 = url;
               `$continuation`.L$2 = status;
               `$continuation`.L$3 = version;
               `$continuation`.label = 5;
               var56 = ByteReadChannelOperationsKt.readInt(channel, `$continuation`);
               if (var56 === var26) {
                  return var26;
               }

               var30 = (var56 as java.lang.Number).intValue();
               headers = new HeadersBuilder(0, 1, null);
               var33 = 0;
            }

            while (j < headersCount) {
               `$continuation`.L$0 = channel;
               `$continuation`.L$1 = url;
               `$continuation`.L$2 = status;
               `$continuation`.L$3 = version;
               `$continuation`.L$4 = headers;
               `$continuation`.L$5 = null;
               `$continuation`.I$0 = var30;
               `$continuation`.I$1 = var33;
               `$continuation`.label = 6;
               var56 = ByteReadChannelOperationsKt.readUTF8Line$default(channel, 0, `$continuation`, 1, null);
               if (var56 === var26) {
                  return var26;
               }

               val var36: java.lang.String = var56 as java.lang.String;
               `$continuation`.L$0 = channel;
               `$continuation`.L$1 = url;
               `$continuation`.L$2 = status;
               `$continuation`.L$3 = version;
               `$continuation`.L$4 = headers;
               `$continuation`.L$5 = var36;
               `$continuation`.I$0 = var30;
               `$continuation`.I$1 = var33;
               `$continuation`.label = 7;
               var56 = ByteReadChannelOperationsKt.readUTF8Line$default(channel, 0, `$continuation`, 1, null);
               if (var56 === var26) {
                  return var26;
               }

               headers.append(var36, var56 as java.lang.String);
               var33++;
            }

            `$continuation`.L$0 = channel;
            `$continuation`.L$1 = url;
            `$continuation`.L$2 = status;
            `$continuation`.L$3 = version;
            `$continuation`.L$4 = headers;
            `$continuation`.L$5 = null;
            `$continuation`.I$0 = var30;
            `$continuation`.label = 8;
            var56 = ByteReadChannelOperationsKt.readLong(channel, `$continuation`);
            if (var56 === var26) {
               return var26;
            }

            requestTime = DateJvmKt.GMTDate(var56 as java.lang.Long);
            `$continuation`.L$0 = channel;
            `$continuation`.L$1 = url;
            `$continuation`.L$2 = status;
            `$continuation`.L$3 = version;
            `$continuation`.L$4 = headers;
            `$continuation`.L$5 = requestTime;
            `$continuation`.I$0 = var30;
            `$continuation`.label = 9;
            var56 = ByteReadChannelOperationsKt.readLong(channel, `$continuation`);
            if (var56 === var26) {
               return var26;
            }
         }

         responseTime = DateJvmKt.GMTDate(var56 as java.lang.Long);
         `$continuation`.L$0 = channel;
         `$continuation`.L$1 = url;
         `$continuation`.L$2 = status;
         `$continuation`.L$3 = version;
         `$continuation`.L$4 = headers;
         `$continuation`.L$5 = requestTime;
         `$continuation`.L$6 = responseTime;
         `$continuation`.I$0 = var30;
         `$continuation`.label = 10;
         var56 = ByteReadChannelOperationsKt.readLong(channel, `$continuation`);
         if (var56 === var26) {
            return var26;
         }

         expirationTime = DateJvmKt.GMTDate(var56 as java.lang.Long);
         `$continuation`.L$0 = channel;
         `$continuation`.L$1 = url;
         `$continuation`.L$2 = status;
         `$continuation`.L$3 = version;
         `$continuation`.L$4 = headers;
         `$continuation`.L$5 = requestTime;
         `$continuation`.L$6 = responseTime;
         `$continuation`.L$7 = expirationTime;
         `$continuation`.I$0 = var30;
         `$continuation`.label = 11;
         var56 = ByteReadChannelOperationsKt.readInt(channel, `$continuation`);
         if (var56 === var26) {
            return var26;
         }

         var41 = (var56 as java.lang.Number).intValue();
         var43 = MapsKt.createMapBuilder();
         var45 = var43;
         var15 = 0;
         var47 = 0;
      }

      while (j < varyKeysCount) {
         `$continuation`.L$0 = channel;
         `$continuation`.L$1 = url;
         `$continuation`.L$2 = status;
         `$continuation`.L$3 = version;
         `$continuation`.L$4 = headers;
         `$continuation`.L$5 = requestTime;
         `$continuation`.L$6 = responseTime;
         `$continuation`.L$7 = expirationTime;
         `$continuation`.L$8 = var43;
         `$continuation`.L$9 = var45;
         `$continuation`.L$10 = null;
         `$continuation`.I$0 = var30;
         `$continuation`.I$1 = var41;
         `$continuation`.I$2 = var15;
         `$continuation`.I$3 = var47;
         `$continuation`.label = 12;
         var var68: Any = ByteReadChannelOperationsKt.readUTF8Line$default(channel, 0, `$continuation`, 1, null);
         if (var68 === var26) {
            return var26;
         }

         var68 = (var68 as java.lang.String).toLowerCase(Locale.ROOT);
         `$continuation`.L$0 = channel;
         `$continuation`.L$1 = url;
         `$continuation`.L$2 = status;
         `$continuation`.L$3 = version;
         `$continuation`.L$4 = headers;
         `$continuation`.L$5 = requestTime;
         `$continuation`.L$6 = responseTime;
         `$continuation`.L$7 = expirationTime;
         `$continuation`.L$8 = var43;
         `$continuation`.L$9 = var45;
         `$continuation`.L$10 = var68;
         `$continuation`.I$0 = var30;
         `$continuation`.I$1 = var41;
         `$continuation`.I$2 = var15;
         `$continuation`.I$3 = var47;
         `$continuation`.label = 13;
         var68 = ByteReadChannelOperationsKt.readUTF8Line$default(channel, 0, `$continuation`, 1, null);
         if (var68 === var26) {
            return var26;
         }

         var45.put(var68, var68 as java.lang.String);
         var47++;
      }

      val varyKeys: java.util.Map = MapsKt.build(var43);
      `$continuation`.L$0 = channel;
      `$continuation`.L$1 = url;
      `$continuation`.L$2 = status;
      `$continuation`.L$3 = version;
      `$continuation`.L$4 = headers;
      `$continuation`.L$5 = requestTime;
      `$continuation`.L$6 = responseTime;
      `$continuation`.L$7 = expirationTime;
      `$continuation`.L$8 = varyKeys;
      `$continuation`.L$9 = null;
      `$continuation`.L$10 = null;
      `$continuation`.I$0 = var30;
      `$continuation`.I$1 = var41;
      `$continuation`.label = 14;
      val var71: Any = ByteReadChannelOperationsKt.readInt(channel, `$continuation`);
      if (var71 === var26) {
         return var26;
      } else {
         val var44: Int = (var71 as java.lang.Number).intValue();
         val body: ByteArray = new byte[var44];
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(channel);
         `$continuation`.L$1 = url;
         `$continuation`.L$2 = status;
         `$continuation`.L$3 = version;
         `$continuation`.L$4 = headers;
         `$continuation`.L$5 = requestTime;
         `$continuation`.L$6 = responseTime;
         `$continuation`.L$7 = expirationTime;
         `$continuation`.L$8 = varyKeys;
         `$continuation`.L$9 = body;
         `$continuation`.I$0 = var30;
         `$continuation`.I$1 = var41;
         `$continuation`.I$2 = var44;
         `$continuation`.label = 15;
         return if (ByteReadChannelOperationsKt.readFully$default(channel, body, 0, 0, `$continuation`, 6, null) === var26)
            var26
            else
            new CachedResponseData(URLUtilsKt.Url(url), status, requestTime, responseTime, version, expirationTime, headers.build(), varyKeys, body);
      }
   }

   @JvmStatic
   fun `readCache$lambda$0`(): Mutex {
      return MutexKt.Mutex$default(false, 1, null);
   }

   @JvmStatic
   fun `deleteCache$lambda$0`(): Mutex {
      return MutexKt.Mutex$default(false, 1, null);
   }
}
