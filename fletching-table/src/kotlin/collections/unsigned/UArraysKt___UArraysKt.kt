package kotlin.collections.unsigned

import java.util.ArrayList
import java.util.Arrays
import java.util.Comparator
import java.util.LinkedHashMap
import java.util.NoSuchElementException
import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.random.Random

@SourceDebugExtension(["SMAP\n_UArrays.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _UArrays.kt\nkotlin/collections/unsigned/UArraysKt___UArraysKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,11226:1\n3976#1:11276\n3984#1:11277\n3992#1:11278\n4000#1:11279\n3976#1:11280\n3984#1:11281\n3992#1:11282\n4000#1:11283\n3976#1:11284\n3984#1:11285\n3992#1:11286\n4000#1:11287\n3976#1:11344\n3984#1:11345\n3992#1:11346\n4000#1:11347\n3976#1:11348\n3984#1:11349\n3992#1:11350\n4000#1:11351\n3976#1:11352\n3984#1:11353\n3992#1:11354\n4000#1:11355\n3976#1:11356\n3984#1:11357\n3992#1:11358\n4000#1:11359\n3976#1:11360\n3984#1:11361\n3992#1:11362\n4000#1:11363\n3976#1:11364\n3984#1:11365\n3992#1:11366\n4000#1:11367\n3976#1:11368\n3984#1:11369\n3992#1:11370\n4000#1:11371\n3976#1:11372\n3984#1:11373\n3992#1:11374\n4000#1:11375\n3976#1:11376\n3984#1:11377\n3992#1:11378\n4000#1:11379\n3976#1:11380\n3984#1:11381\n3992#1:11382\n4000#1:11383\n3976#1:11384\n3984#1:11385\n3992#1:11386\n4000#1:11387\n3976#1:11388\n3984#1:11389\n3992#1:11390\n4000#1:11391\n3976#1:11392\n3984#1:11393\n3992#1:11394\n4000#1:11395\n3976#1:11396\n3984#1:11397\n3992#1:11398\n4000#1:11399\n3976#1:11400\n3984#1:11401\n3992#1:11402\n4000#1:11403\n3976#1:11404\n3984#1:11405\n3992#1:11406\n4000#1:11407\n3976#1:11408\n3984#1:11409\n3992#1:11410\n4000#1:11411\n3976#1:11412\n3984#1:11413\n3992#1:11414\n4000#1:11415\n3976#1:11416\n3984#1:11417\n3992#1:11418\n4000#1:11419\n3976#1:11420\n3984#1:11421\n3992#1:11422\n4000#1:11423\n3976#1:11424\n3984#1:11425\n3992#1:11426\n4000#1:11427\n3976#1:11428\n3984#1:11429\n3992#1:11430\n4000#1:11431\n3976#1:11432\n3984#1:11433\n3992#1:11434\n4000#1:11435\n3976#1:11436\n3984#1:11437\n3992#1:11438\n4000#1:11439\n3976#1:11440\n3984#1:11441\n3992#1:11442\n4000#1:11443\n3976#1:11444\n3984#1:11445\n3992#1:11446\n4000#1:11447\n3976#1:11448\n3984#1:11449\n3992#1:11450\n4000#1:11451\n3976#1:11452\n3984#1:11453\n3992#1:11454\n4000#1:11455\n3976#1:11456\n3984#1:11457\n3992#1:11458\n4000#1:11459\n3976#1:11460\n3984#1:11461\n3992#1:11462\n4000#1:11463\n3976#1:11464\n3984#1:11465\n3992#1:11466\n4000#1:11467\n3976#1:11468\n3984#1:11469\n3992#1:11470\n4000#1:11471\n3976#1:11472\n3984#1:11473\n3992#1:11474\n4000#1:11475\n3976#1:11476\n3984#1:11477\n3992#1:11478\n4000#1:11479\n3976#1:11480\n3984#1:11481\n3992#1:11482\n4000#1:11483\n3976#1:11484\n3984#1:11485\n3992#1:11486\n4000#1:11487\n3976#1:11488\n3984#1:11489\n3992#1:11490\n4000#1:11491\n3976#1:11492\n3984#1:11493\n3992#1:11494\n4000#1:11495\n1808#2,6:11227\n1820#2,6:11233\n1784#2,6:11239\n1796#2,6:11245\n1916#2,6:11251\n1928#2,6:11257\n1892#2,6:11263\n1904#2,6:11269\n1#3:11275\n382#4,7:11288\n382#4,7:11295\n382#4,7:11302\n382#4,7:11309\n382#4,7:11316\n382#4,7:11323\n382#4,7:11330\n382#4,7:11337\n*S KotlinDebug\n*F\n+ 1 _UArrays.kt\nkotlin/collections/unsigned/UArraysKt___UArraysKt\n*L\n1775#1:11276\n1792#1:11277\n1809#1:11278\n1826#1:11279\n2603#1:11280\n2620#1:11281\n2637#1:11282\n2654#1:11283\n2970#1:11284\n2986#1:11285\n3002#1:11286\n3018#1:11287\n5774#1:11344\n5794#1:11345\n5814#1:11346\n5834#1:11347\n5855#1:11348\n5877#1:11349\n5899#1:11350\n5921#1:11351\n6036#1:11352\n6057#1:11353\n6078#1:11354\n6099#1:11355\n6128#1:11356\n6164#1:11357\n6200#1:11358\n6236#1:11359\n6268#1:11360\n6300#1:11361\n6332#1:11362\n6364#1:11363\n6396#1:11364\n6421#1:11365\n6446#1:11366\n6471#1:11367\n6496#1:11368\n6521#1:11369\n6546#1:11370\n6571#1:11371\n6596#1:11372\n6623#1:11373\n6650#1:11374\n6677#1:11375\n6702#1:11376\n6725#1:11377\n6748#1:11378\n6771#1:11379\n6794#1:11380\n6817#1:11381\n6840#1:11382\n6863#1:11383\n6886#1:11384\n6911#1:11385\n6936#1:11386\n6961#1:11387\n6988#1:11388\n7015#1:11389\n7042#1:11390\n7069#1:11391\n7094#1:11392\n7119#1:11393\n7144#1:11394\n7169#1:11395\n7188#1:11396\n7205#1:11397\n7222#1:11398\n7239#1:11399\n7258#1:11400\n7277#1:11401\n7296#1:11402\n7315#1:11403\n7330#1:11404\n7345#1:11405\n7360#1:11406\n7375#1:11407\n7396#1:11408\n7417#1:11409\n7438#1:11410\n7459#1:11411\n7488#1:11412\n7524#1:11413\n7560#1:11414\n7596#1:11415\n7628#1:11416\n7660#1:11417\n7692#1:11418\n7724#1:11419\n7756#1:11420\n7781#1:11421\n7806#1:11422\n7831#1:11423\n7856#1:11424\n7881#1:11425\n7906#1:11426\n7931#1:11427\n7956#1:11428\n7983#1:11429\n8010#1:11430\n8037#1:11431\n8062#1:11432\n8085#1:11433\n8108#1:11434\n8131#1:11435\n8154#1:11436\n8177#1:11437\n8200#1:11438\n8223#1:11439\n8246#1:11440\n8271#1:11441\n8296#1:11442\n8321#1:11443\n8348#1:11444\n8375#1:11445\n8402#1:11446\n8429#1:11447\n8454#1:11448\n8479#1:11449\n8504#1:11450\n8529#1:11451\n8548#1:11452\n8565#1:11453\n8582#1:11454\n8599#1:11455\n8618#1:11456\n8637#1:11457\n8656#1:11458\n8675#1:11459\n8690#1:11460\n8705#1:11461\n8720#1:11462\n8735#1:11463\n8953#1:11464\n8978#1:11465\n9003#1:11466\n9028#1:11467\n9053#1:11468\n9078#1:11469\n9103#1:11470\n9128#1:11471\n9152#1:11472\n9176#1:11473\n9200#1:11474\n9224#1:11475\n9248#1:11476\n9272#1:11477\n9296#1:11478\n9320#1:11479\n9342#1:11480\n9367#1:11481\n9392#1:11482\n9417#1:11483\n9442#1:11484\n9468#1:11485\n9494#1:11486\n9520#1:11487\n9545#1:11488\n9570#1:11489\n9595#1:11490\n9620#1:11491\n9645#1:11492\n9669#1:11493\n9693#1:11494\n9717#1:11495\n881#1:11227,6\n891#1:11233,6\n901#1:11239,6\n911#1:11245,6\n921#1:11251,6\n931#1:11257,6\n941#1:11263,6\n951#1:11269,6\n4992#1:11288,7\n5012#1:11295,7\n5032#1:11302,7\n5052#1:11309,7\n5073#1:11316,7\n5094#1:11323,7\n5115#1:11330,7\n5136#1:11337,7\n*E\n"])
internal class UArraysKt___UArraysKt : UArraysKt___UArraysJvmKt {
   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public final val indices: IntRange
      public final inline get() {
         return ArraysKt.getIndices(var0);
      }


   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public final val indices: IntRange
      public final inline get() {
         return ArraysKt.getIndices(var0);
      }


   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public final val indices: IntRange
      public final inline get() {
         return ArraysKt.getIndices(var0);
      }


   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public final val indices: IntRange
      public final inline get() {
         return ArraysKt.getIndices(var0);
      }


   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public final val lastIndex: Int
      public final inline get() {
         return ArraysKt.getLastIndex(var0);
      }


   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public final val lastIndex: Int
      public final inline get() {
         return ArraysKt.getLastIndex(var0);
      }


   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public final val lastIndex: Int
      public final inline get() {
         return ArraysKt.getLastIndex(var0);
      }


   @SinceKotlin(
      version = "1.3"
   )
   @ExperimentalUnsignedTypes
   public final val lastIndex: Int
      public final inline get() {
         return ArraysKt.getLastIndex(var0);
      }


   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UIntArray.component1(): UInt {
      return UIntArray.get-pVg5ArA(var0, 0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun ULongArray.component1(): ULong {
      return ULongArray.get-s-VKNKU(var0, 0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UByteArray.component1(): UByte {
      return UByteArray.get-w2LRezQ(var0, 0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UShortArray.component1(): UShort {
      return UShortArray.get-Mh2AYeg(var0, 0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UIntArray.component2(): UInt {
      return UIntArray.get-pVg5ArA(var0, 1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun ULongArray.component2(): ULong {
      return ULongArray.get-s-VKNKU(var0, 1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UByteArray.component2(): UByte {
      return UByteArray.get-w2LRezQ(var0, 1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UShortArray.component2(): UShort {
      return UShortArray.get-Mh2AYeg(var0, 1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UIntArray.component3(): UInt {
      return UIntArray.get-pVg5ArA(var0, 2);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun ULongArray.component3(): ULong {
      return ULongArray.get-s-VKNKU(var0, 2);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UByteArray.component3(): UByte {
      return UByteArray.get-w2LRezQ(var0, 2);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UShortArray.component3(): UShort {
      return UShortArray.get-Mh2AYeg(var0, 2);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UIntArray.component4(): UInt {
      return UIntArray.get-pVg5ArA(var0, 3);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun ULongArray.component4(): ULong {
      return ULongArray.get-s-VKNKU(var0, 3);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UByteArray.component4(): UByte {
      return UByteArray.get-w2LRezQ(var0, 3);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UShortArray.component4(): UShort {
      return UShortArray.get-Mh2AYeg(var0, 3);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UIntArray.component5(): UInt {
      return UIntArray.get-pVg5ArA(var0, 4);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun ULongArray.component5(): ULong {
      return ULongArray.get-s-VKNKU(var0, 4);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UByteArray.component5(): UByte {
      return UByteArray.get-w2LRezQ(var0, 4);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UShortArray.component5(): UShort {
      return UShortArray.get-Mh2AYeg(var0, 4);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.elementAtOrElse(index: Int, defaultValue: (Int) -> UInt): UInt {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < UIntArray.getSize-impl(var0)) UIntArray.get-pVg5ArA(var0, index) else (defaultValue.invoke(index) as UInt).unbox-impl();
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.elementAtOrElse(index: Int, defaultValue: (Int) -> ULong): ULong {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < ULongArray.getSize-impl(var0))
         ULongArray.get-s-VKNKU(var0, index)
         else
         (defaultValue.invoke(index) as ULong).unbox-impl();
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.elementAtOrElse(index: Int, defaultValue: (Int) -> UByte): UByte {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < UByteArray.getSize-impl(var0))
         UByteArray.get-w2LRezQ(var0, index)
         else
         (defaultValue.invoke(index) as UByte).unbox-impl();
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.elementAtOrElse(index: Int, defaultValue: (Int) -> UShort): UShort {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < UShortArray.getSize-impl(var0))
         UShortArray.get-Mh2AYeg(var0, index)
         else
         (defaultValue.invoke(index) as UShort).unbox-impl();
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.elementAtOrNull(index: Int): UInt? {
      return UArraysKt.getOrNull-qFRl0hI(var0, index);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.elementAtOrNull(index: Int): ULong? {
      return UArraysKt.getOrNull-r7IrZao(var0, index);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.elementAtOrNull(index: Int): UByte? {
      return UArraysKt.getOrNull-PpDY95g(var0, index);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.elementAtOrNull(index: Int): UShort? {
      return UArraysKt.getOrNull-nggk6HY(var0, index);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.find(predicate: (UInt) -> Boolean): UInt? {
      val var2: IntArray = var0;
      var var3: Int = 0;
      val var4: Int = UIntArray.getSize-impl(var0);

      var var10000: UInt;
      while (true) {
         if (var3 >= var4) {
            var10000 = null;
            break;
         }

         val var5: Int = UIntArray.get-pVg5ArA(var2, var3);
         if (predicate.invoke(UInt.box-impl(var5)) as java.lang.Boolean) {
            var10000 = UInt.box-impl(var5);
            break;
         }

         var3++;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.find(predicate: (ULong) -> Boolean): ULong? {
      val var2: LongArray = var0;
      var var3: Int = 0;
      val var4: Int = ULongArray.getSize-impl(var0);

      var var10000: ULong;
      while (true) {
         if (var3 >= var4) {
            var10000 = null;
            break;
         }

         val var5: Long = ULongArray.get-s-VKNKU(var2, var3);
         if (predicate.invoke(ULong.box-impl(var5)) as java.lang.Boolean) {
            var10000 = ULong.box-impl(var5);
            break;
         }

         var3++;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.find(predicate: (UByte) -> Boolean): UByte? {
      val var2: ByteArray = var0;
      var var3: Int = 0;
      val var4: Int = UByteArray.getSize-impl(var0);

      var var10000: UByte;
      while (true) {
         if (var3 >= var4) {
            var10000 = null;
            break;
         }

         val var5: Byte = UByteArray.get-w2LRezQ(var2, var3);
         if (predicate.invoke(UByte.box-impl(var5)) as java.lang.Boolean) {
            var10000 = UByte.box-impl(var5);
            break;
         }

         var3++;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.find(predicate: (UShort) -> Boolean): UShort? {
      val var2: ShortArray = var0;
      var var3: Int = 0;
      val var4: Int = UShortArray.getSize-impl(var0);

      var var10000: UShort;
      while (true) {
         if (var3 >= var4) {
            var10000 = null;
            break;
         }

         val var5: Short = UShortArray.get-Mh2AYeg(var2, var3);
         if (predicate.invoke(UShort.box-impl(var5)) as java.lang.Boolean) {
            var10000 = UShort.box-impl(var5);
            break;
         }

         var3++;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.findLast(predicate: (UInt) -> Boolean): UInt? {
      val var2: IntArray = var0;
      var var3: Int = UIntArray.getSize-impl(var0) + -1;
      if (0 <= var3) {
         do {
            val var5: Int = UIntArray.get-pVg5ArA(var2, var3--);
            if (predicate.invoke(UInt.box-impl(var5)) as java.lang.Boolean) {
               return UInt.box-impl(var5);
            }
         } while (0 <= var3);
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.findLast(predicate: (ULong) -> Boolean): ULong? {
      val var2: LongArray = var0;
      var var3: Int = ULongArray.getSize-impl(var0) + -1;
      if (0 <= var3) {
         do {
            val var5: Long = ULongArray.get-s-VKNKU(var2, var3--);
            if (predicate.invoke(ULong.box-impl(var5)) as java.lang.Boolean) {
               return ULong.box-impl(var5);
            }
         } while (0 <= var3);
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.findLast(predicate: (UByte) -> Boolean): UByte? {
      val var2: ByteArray = var0;
      var var3: Int = UByteArray.getSize-impl(var0) + -1;
      if (0 <= var3) {
         do {
            val var5: Byte = UByteArray.get-w2LRezQ(var2, var3--);
            if (predicate.invoke(UByte.box-impl(var5)) as java.lang.Boolean) {
               return UByte.box-impl(var5);
            }
         } while (0 <= var3);
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.findLast(predicate: (UShort) -> Boolean): UShort? {
      val var2: ShortArray = var0;
      var var3: Int = UShortArray.getSize-impl(var0) + -1;
      if (0 <= var3) {
         do {
            val var5: Short = UShortArray.get-Mh2AYeg(var2, var3--);
            if (predicate.invoke(UShort.box-impl(var5)) as java.lang.Boolean) {
               return UShort.box-impl(var5);
            }
         } while (0 <= var3);
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.first(): UInt {
      return UInt.constructor-impl(ArraysKt.first(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.first(): ULong {
      return ULong.constructor-impl(ArraysKt.first(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.first(): UByte {
      return UByte.constructor-impl(ArraysKt.first(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.first(): UShort {
      return UShort.constructor-impl(ArraysKt.first(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.first(predicate: (UInt) -> Boolean): UInt {
      var var2: Int = 0;

      for (int var3 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$first$0); var2 < var3; var2++) {
         val element: Int = UIntArray.get-pVg5ArA(var0, var2);
         if (predicate.invoke(UInt.box-impl(element)) as java.lang.Boolean) {
            return element;
         }
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.first(predicate: (ULong) -> Boolean): ULong {
      var var2: Int = 0;

      for (int var3 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$first$0); var2 < var3; var2++) {
         val element: Long = ULongArray.get-s-VKNKU(var0, var2);
         if (predicate.invoke(ULong.box-impl(element)) as java.lang.Boolean) {
            return element;
         }
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.first(predicate: (UByte) -> Boolean): UByte {
      var var2: Int = 0;

      for (int var3 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$first$0); var2 < var3; var2++) {
         val element: Byte = UByteArray.get-w2LRezQ(var0, var2);
         if (predicate.invoke(UByte.box-impl(element)) as java.lang.Boolean) {
            return element;
         }
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.first(predicate: (UShort) -> Boolean): UShort {
      var var2: Int = 0;

      for (int var3 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$first$0); var2 < var3; var2++) {
         val element: Short = UShortArray.get-Mh2AYeg(var0, var2);
         if (predicate.invoke(UShort.box-impl(element)) as java.lang.Boolean) {
            return element;
         }
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.firstOrNull(): UInt? {
      return if (UIntArray.isEmpty-impl(var0)) null else UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.firstOrNull(): ULong? {
      return if (ULongArray.isEmpty-impl(var0)) null else ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.firstOrNull(): UByte? {
      return if (UByteArray.isEmpty-impl(var0)) null else UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.firstOrNull(): UShort? {
      return if (UShortArray.isEmpty-impl(var0)) null else UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.firstOrNull(predicate: (UInt) -> Boolean): UInt? {
      var var2: Int = 0;

      for (int var3 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$firstOrNull$0); var2 < var3; var2++) {
         val element: Int = UIntArray.get-pVg5ArA(var0, var2);
         if (predicate.invoke(UInt.box-impl(element)) as java.lang.Boolean) {
            return UInt.box-impl(element);
         }
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.firstOrNull(predicate: (ULong) -> Boolean): ULong? {
      var var2: Int = 0;

      for (int var3 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$firstOrNull$0); var2 < var3; var2++) {
         val element: Long = ULongArray.get-s-VKNKU(var0, var2);
         if (predicate.invoke(ULong.box-impl(element)) as java.lang.Boolean) {
            return ULong.box-impl(element);
         }
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.firstOrNull(predicate: (UByte) -> Boolean): UByte? {
      var var2: Int = 0;

      for (int var3 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$firstOrNull$0); var2 < var3; var2++) {
         val element: Byte = UByteArray.get-w2LRezQ(var0, var2);
         if (predicate.invoke(UByte.box-impl(element)) as java.lang.Boolean) {
            return UByte.box-impl(element);
         }
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.firstOrNull(predicate: (UShort) -> Boolean): UShort? {
      var var2: Int = 0;

      for (int var3 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$firstOrNull$0); var2 < var3; var2++) {
         val element: Short = UShortArray.get-Mh2AYeg(var0, var2);
         if (predicate.invoke(UShort.box-impl(element)) as java.lang.Boolean) {
            return UShort.box-impl(element);
         }
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.getOrElse(index: Int, defaultValue: (Int) -> UInt): UInt {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < UIntArray.getSize-impl(var0)) UIntArray.get-pVg5ArA(var0, index) else (defaultValue.invoke(index) as UInt).unbox-impl();
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.getOrElse(index: Int, defaultValue: (Int) -> ULong): ULong {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < ULongArray.getSize-impl(var0))
         ULongArray.get-s-VKNKU(var0, index)
         else
         (defaultValue.invoke(index) as ULong).unbox-impl();
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.getOrElse(index: Int, defaultValue: (Int) -> UByte): UByte {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < UByteArray.getSize-impl(var0))
         UByteArray.get-w2LRezQ(var0, index)
         else
         (defaultValue.invoke(index) as UByte).unbox-impl();
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.getOrElse(index: Int, defaultValue: (Int) -> UShort): UShort {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < UShortArray.getSize-impl(var0))
         UShortArray.get-Mh2AYeg(var0, index)
         else
         (defaultValue.invoke(index) as UShort).unbox-impl();
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.getOrNull(index: Int): UInt? {
      return if (0 <= index && index < UIntArray.getSize-impl(var0)) UInt.box-impl(UIntArray.get-pVg5ArA(var0, index)) else null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.getOrNull(index: Int): ULong? {
      return if (0 <= index && index < ULongArray.getSize-impl(var0)) ULong.box-impl(ULongArray.get-s-VKNKU(var0, index)) else null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.getOrNull(index: Int): UByte? {
      return if (0 <= index && index < UByteArray.getSize-impl(var0)) UByte.box-impl(UByteArray.get-w2LRezQ(var0, index)) else null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.getOrNull(index: Int): UShort? {
      return if (0 <= index && index < UShortArray.getSize-impl(var0)) UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index)) else null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.indexOf(element: UInt): Int {
      return ArraysKt.indexOf(var0, var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.indexOf(element: ULong): Int {
      return ArraysKt.indexOf(var0, var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.indexOf(element: UByte): Int {
      return ArraysKt.indexOf(var0, var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.indexOf(element: UShort): Int {
      return ArraysKt.indexOf(var0, var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.indexOfFirst(predicate: (UInt) -> Boolean): Int {
      val `$this$indexOfFirst$iv`: IntArray = var0;
      var `index$iv`: Int = 0;
      val var5: Int = var0.length;

      var var10000: Int;
      while (true) {
         if (`index$iv` >= var5) {
            var10000 = -1;
            break;
         }

         if (predicate.invoke(UInt.box-impl(UInt.constructor-impl(`$this$indexOfFirst$iv`[`index$iv`]))) as java.lang.Boolean) {
            var10000 = `index$iv`;
            break;
         }

         `index$iv`++;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.indexOfFirst(predicate: (ULong) -> Boolean): Int {
      val `$this$indexOfFirst$iv`: LongArray = var0;
      var `index$iv`: Int = 0;
      val var5: Int = var0.length;

      var var10000: Int;
      while (true) {
         if (`index$iv` >= var5) {
            var10000 = -1;
            break;
         }

         if (predicate.invoke(ULong.box-impl(ULong.constructor-impl(`$this$indexOfFirst$iv`[`index$iv`]))) as java.lang.Boolean) {
            var10000 = `index$iv`;
            break;
         }

         `index$iv`++;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.indexOfFirst(predicate: (UByte) -> Boolean): Int {
      val `$this$indexOfFirst$iv`: ByteArray = var0;
      var `index$iv`: Int = 0;
      val var5: Int = var0.length;

      var var10000: Int;
      while (true) {
         if (`index$iv` >= var5) {
            var10000 = -1;
            break;
         }

         if (predicate.invoke(UByte.box-impl(UByte.constructor-impl(`$this$indexOfFirst$iv`[`index$iv`]))) as java.lang.Boolean) {
            var10000 = `index$iv`;
            break;
         }

         `index$iv`++;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.indexOfFirst(predicate: (UShort) -> Boolean): Int {
      val `$this$indexOfFirst$iv`: ShortArray = var0;
      var `index$iv`: Int = 0;
      val var5: Int = var0.length;

      var var10000: Int;
      while (true) {
         if (`index$iv` >= var5) {
            var10000 = -1;
            break;
         }

         if (predicate.invoke(UShort.box-impl(UShort.constructor-impl(`$this$indexOfFirst$iv`[`index$iv`]))) as java.lang.Boolean) {
            var10000 = `index$iv`;
            break;
         }

         `index$iv`++;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.indexOfLast(predicate: (UInt) -> Boolean): Int {
      val `$this$indexOfLast$iv`: IntArray = var0;
      var var4: Int = var0.length + -1;
      if (0 <= var0.length + -1) {
         do {
            val `index$iv`: Int = var4--;
            if (predicate.invoke(UInt.box-impl(UInt.constructor-impl(`$this$indexOfLast$iv`[`index$iv`]))) as java.lang.Boolean) {
               return `index$iv`;
            }
         } while (0 <= var4);
      }

      return -1;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.indexOfLast(predicate: (ULong) -> Boolean): Int {
      val `$this$indexOfLast$iv`: LongArray = var0;
      var var4: Int = var0.length + -1;
      if (0 <= var0.length + -1) {
         do {
            val `index$iv`: Int = var4--;
            if (predicate.invoke(ULong.box-impl(ULong.constructor-impl(`$this$indexOfLast$iv`[`index$iv`]))) as java.lang.Boolean) {
               return `index$iv`;
            }
         } while (0 <= var4);
      }

      return -1;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.indexOfLast(predicate: (UByte) -> Boolean): Int {
      val `$this$indexOfLast$iv`: ByteArray = var0;
      var var4: Int = var0.length + -1;
      if (0 <= var0.length + -1) {
         do {
            val `index$iv`: Int = var4--;
            if (predicate.invoke(UByte.box-impl(UByte.constructor-impl(`$this$indexOfLast$iv`[`index$iv`]))) as java.lang.Boolean) {
               return `index$iv`;
            }
         } while (0 <= var4);
      }

      return -1;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.indexOfLast(predicate: (UShort) -> Boolean): Int {
      val `$this$indexOfLast$iv`: ShortArray = var0;
      var var4: Int = var0.length + -1;
      if (0 <= var0.length + -1) {
         do {
            val `index$iv`: Int = var4--;
            if (predicate.invoke(UShort.box-impl(UShort.constructor-impl(`$this$indexOfLast$iv`[`index$iv`]))) as java.lang.Boolean) {
               return `index$iv`;
            }
         } while (0 <= var4);
      }

      return -1;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.last(): UInt {
      return UInt.constructor-impl(ArraysKt.last(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.last(): ULong {
      return ULong.constructor-impl(ArraysKt.last(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.last(): UByte {
      return UByte.constructor-impl(ArraysKt.last(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.last(): UShort {
      return UShort.constructor-impl(ArraysKt.last(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.last(predicate: (UInt) -> Boolean): UInt {
      var var2: Int = UIntArray.getSize-impl(var0) + -1;
      if (0 <= var2) {
         do {
            val element: Int = UIntArray.get-pVg5ArA(var0, var2--);
            if (predicate.invoke(UInt.box-impl(element)) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var2);
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.last(predicate: (ULong) -> Boolean): ULong {
      var var2: Int = ULongArray.getSize-impl(var0) + -1;
      if (0 <= var2) {
         do {
            val element: Long = ULongArray.get-s-VKNKU(var0, var2--);
            if (predicate.invoke(ULong.box-impl(element)) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var2);
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.last(predicate: (UByte) -> Boolean): UByte {
      var var2: Int = UByteArray.getSize-impl(var0) + -1;
      if (0 <= var2) {
         do {
            val element: Byte = UByteArray.get-w2LRezQ(var0, var2--);
            if (predicate.invoke(UByte.box-impl(element)) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var2);
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.last(predicate: (UShort) -> Boolean): UShort {
      var var2: Int = UShortArray.getSize-impl(var0) + -1;
      if (0 <= var2) {
         do {
            val element: Short = UShortArray.get-Mh2AYeg(var0, var2--);
            if (predicate.invoke(UShort.box-impl(element)) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var2);
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.lastIndexOf(element: UInt): Int {
      return ArraysKt.lastIndexOf(var0, var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.lastIndexOf(element: ULong): Int {
      return ArraysKt.lastIndexOf(var0, var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.lastIndexOf(element: UByte): Int {
      return ArraysKt.lastIndexOf(var0, var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.lastIndexOf(element: UShort): Int {
      return ArraysKt.lastIndexOf(var0, var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.lastOrNull(): UInt? {
      return if (UIntArray.isEmpty-impl(var0)) null else UInt.box-impl(UIntArray.get-pVg5ArA(var0, UIntArray.getSize-impl(var0) - 1));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.lastOrNull(): ULong? {
      return if (ULongArray.isEmpty-impl(var0)) null else ULong.box-impl(ULongArray.get-s-VKNKU(var0, ULongArray.getSize-impl(var0) - 1));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.lastOrNull(): UByte? {
      return if (UByteArray.isEmpty-impl(var0)) null else UByte.box-impl(UByteArray.get-w2LRezQ(var0, UByteArray.getSize-impl(var0) - 1));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.lastOrNull(): UShort? {
      return if (UShortArray.isEmpty-impl(var0)) null else UShort.box-impl(UShortArray.get-Mh2AYeg(var0, UShortArray.getSize-impl(var0) - 1));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.lastOrNull(predicate: (UInt) -> Boolean): UInt? {
      var var2: Int = UIntArray.getSize-impl(var0) + -1;
      if (0 <= var2) {
         do {
            val element: Int = UIntArray.get-pVg5ArA(var0, var2--);
            if (predicate.invoke(UInt.box-impl(element)) as java.lang.Boolean) {
               return UInt.box-impl(element);
            }
         } while (0 <= var2);
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.lastOrNull(predicate: (ULong) -> Boolean): ULong? {
      var var2: Int = ULongArray.getSize-impl(var0) + -1;
      if (0 <= var2) {
         do {
            val element: Long = ULongArray.get-s-VKNKU(var0, var2--);
            if (predicate.invoke(ULong.box-impl(element)) as java.lang.Boolean) {
               return ULong.box-impl(element);
            }
         } while (0 <= var2);
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.lastOrNull(predicate: (UByte) -> Boolean): UByte? {
      var var2: Int = UByteArray.getSize-impl(var0) + -1;
      if (0 <= var2) {
         do {
            val element: Byte = UByteArray.get-w2LRezQ(var0, var2--);
            if (predicate.invoke(UByte.box-impl(element)) as java.lang.Boolean) {
               return UByte.box-impl(element);
            }
         } while (0 <= var2);
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.lastOrNull(predicate: (UShort) -> Boolean): UShort? {
      var var2: Int = UShortArray.getSize-impl(var0) + -1;
      if (0 <= var2) {
         do {
            val element: Short = UShortArray.get-Mh2AYeg(var0, var2--);
            if (predicate.invoke(UShort.box-impl(element)) as java.lang.Boolean) {
               return UShort.box-impl(element);
            }
         } while (0 <= var2);
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.random(): UInt {
      return UArraysKt.random-2D5oskM(var0, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.random(): ULong {
      return UArraysKt.random-JzugnMA(var0, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.random(): UByte {
      return UArraysKt.random-oSF2wD8(var0, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.random(): UShort {
      return UArraysKt.random-s5X_as8(var0, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.random(random: Random): UInt {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return UIntArray.get-pVg5ArA(var0, random.nextInt(UIntArray.getSize-impl(var0)));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.random(random: Random): ULong {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return ULongArray.get-s-VKNKU(var0, random.nextInt(ULongArray.getSize-impl(var0)));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.random(random: Random): UByte {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return UByteArray.get-w2LRezQ(var0, random.nextInt(UByteArray.getSize-impl(var0)));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.random(random: Random): UShort {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return UShortArray.get-Mh2AYeg(var0, random.nextInt(UShortArray.getSize-impl(var0)));
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.randomOrNull(): UInt? {
      return UArraysKt.randomOrNull-2D5oskM(var0, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.randomOrNull(): ULong? {
      return UArraysKt.randomOrNull-JzugnMA(var0, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.randomOrNull(): UByte? {
      return UArraysKt.randomOrNull-oSF2wD8(var0, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.randomOrNull(): UShort? {
      return UArraysKt.randomOrNull-s5X_as8(var0, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.randomOrNull(random: Random): UInt? {
      return if (UIntArray.isEmpty-impl(var0)) null else UInt.box-impl(UIntArray.get-pVg5ArA(var0, random.nextInt(UIntArray.getSize-impl(var0))));
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.randomOrNull(random: Random): ULong? {
      return if (ULongArray.isEmpty-impl(var0)) null else ULong.box-impl(ULongArray.get-s-VKNKU(var0, random.nextInt(ULongArray.getSize-impl(var0))));
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.randomOrNull(random: Random): UByte? {
      return if (UByteArray.isEmpty-impl(var0)) null else UByte.box-impl(UByteArray.get-w2LRezQ(var0, random.nextInt(UByteArray.getSize-impl(var0))));
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.randomOrNull(random: Random): UShort? {
      return if (UShortArray.isEmpty-impl(var0)) null else UShort.box-impl(UShortArray.get-Mh2AYeg(var0, random.nextInt(UShortArray.getSize-impl(var0))));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.single(): UInt {
      return UInt.constructor-impl(ArraysKt.single(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.single(): ULong {
      return ULong.constructor-impl(ArraysKt.single(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.single(): UByte {
      return UByte.constructor-impl(ArraysKt.single(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.single(): UShort {
      return UShort.constructor-impl(ArraysKt.single(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.single(predicate: (UInt) -> Boolean): UInt {
      var single: UInt = null;
      var found: Boolean = false;
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$single$0); var4 < var5; var4++) {
         val element: Int = UIntArray.get-pVg5ArA(var0, var4);
         if (predicate.invoke(UInt.box-impl(element)) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Array contains more than one matching element.");
            }

            single = UInt.box-impl(element);
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Array contains no element matching the predicate.");
      } else {
         return single.unbox-impl();
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.single(predicate: (ULong) -> Boolean): ULong {
      var single: ULong = null;
      var found: Boolean = false;
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$single$0); var4 < var5; var4++) {
         val element: Long = ULongArray.get-s-VKNKU(var0, var4);
         if (predicate.invoke(ULong.box-impl(element)) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Array contains more than one matching element.");
            }

            single = ULong.box-impl(element);
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Array contains no element matching the predicate.");
      } else {
         return single.unbox-impl();
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.single(predicate: (UByte) -> Boolean): UByte {
      var single: UByte = null;
      var found: Boolean = false;
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$single$0); var4 < var5; var4++) {
         val element: Byte = UByteArray.get-w2LRezQ(var0, var4);
         if (predicate.invoke(UByte.box-impl(element)) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Array contains more than one matching element.");
            }

            single = UByte.box-impl(element);
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Array contains no element matching the predicate.");
      } else {
         return single.unbox-impl();
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.single(predicate: (UShort) -> Boolean): UShort {
      var single: UShort = null;
      var found: Boolean = false;
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$single$0); var4 < var5; var4++) {
         val element: Short = UShortArray.get-Mh2AYeg(var0, var4);
         if (predicate.invoke(UShort.box-impl(element)) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Array contains more than one matching element.");
            }

            single = UShort.box-impl(element);
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Array contains no element matching the predicate.");
      } else {
         return single.unbox-impl();
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.singleOrNull(): UInt? {
      return if (UIntArray.getSize-impl(var0) == 1) UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0)) else null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.singleOrNull(): ULong? {
      return if (ULongArray.getSize-impl(var0) == 1) ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0)) else null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.singleOrNull(): UByte? {
      return if (UByteArray.getSize-impl(var0) == 1) UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0)) else null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.singleOrNull(): UShort? {
      return if (UShortArray.getSize-impl(var0) == 1) UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0)) else null;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.singleOrNull(predicate: (UInt) -> Boolean): UInt? {
      var single: UInt = null;
      var found: Boolean = false;
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$singleOrNull$0); var4 < var5; var4++) {
         val element: Int = UIntArray.get-pVg5ArA(var0, var4);
         if (predicate.invoke(UInt.box-impl(element)) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = UInt.box-impl(element);
            found = true;
         }
      }

      return if (!found) null else single;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.singleOrNull(predicate: (ULong) -> Boolean): ULong? {
      var single: ULong = null;
      var found: Boolean = false;
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$singleOrNull$0); var4 < var5; var4++) {
         val element: Long = ULongArray.get-s-VKNKU(var0, var4);
         if (predicate.invoke(ULong.box-impl(element)) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = ULong.box-impl(element);
            found = true;
         }
      }

      return if (!found) null else single;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.singleOrNull(predicate: (UByte) -> Boolean): UByte? {
      var single: UByte = null;
      var found: Boolean = false;
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$singleOrNull$0); var4 < var5; var4++) {
         val element: Byte = UByteArray.get-w2LRezQ(var0, var4);
         if (predicate.invoke(UByte.box-impl(element)) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = UByte.box-impl(element);
            found = true;
         }
      }

      return if (!found) null else single;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.singleOrNull(predicate: (UShort) -> Boolean): UShort? {
      var single: UShort = null;
      var found: Boolean = false;
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$singleOrNull$0); var4 < var5; var4++) {
         val element: Short = UShortArray.get-Mh2AYeg(var0, var4);
         if (predicate.invoke(UShort.box-impl(element)) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = UShort.box-impl(element);
            found = true;
         }
      }

      return if (!found) null else single;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.drop(n: Int): List<UInt> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return UArraysKt.takeLast-qFRl0hI(var0, RangesKt.coerceAtLeast(UIntArray.getSize-impl(var0) - n, 0));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.drop(n: Int): List<ULong> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return UArraysKt.takeLast-r7IrZao(var0, RangesKt.coerceAtLeast(ULongArray.getSize-impl(var0) - n, 0));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.drop(n: Int): List<UByte> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return UArraysKt.takeLast-PpDY95g(var0, RangesKt.coerceAtLeast(UByteArray.getSize-impl(var0) - n, 0));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.drop(n: Int): List<UShort> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return UArraysKt.takeLast-nggk6HY(var0, RangesKt.coerceAtLeast(UShortArray.getSize-impl(var0) - n, 0));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.dropLast(n: Int): List<UInt> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return UArraysKt.take-qFRl0hI(var0, RangesKt.coerceAtLeast(UIntArray.getSize-impl(var0) - n, 0));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.dropLast(n: Int): List<ULong> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return UArraysKt.take-r7IrZao(var0, RangesKt.coerceAtLeast(ULongArray.getSize-impl(var0) - n, 0));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.dropLast(n: Int): List<UByte> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return UArraysKt.take-PpDY95g(var0, RangesKt.coerceAtLeast(UByteArray.getSize-impl(var0) - n, 0));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.dropLast(n: Int): List<UShort> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return UArraysKt.take-nggk6HY(var0, RangesKt.coerceAtLeast(UShortArray.getSize-impl(var0) - n, 0));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.dropLastWhile(predicate: (UInt) -> Boolean): List<UInt> {
      for (int index = ArraysKt.getLastIndex($v$c$kotlin-UIntArray$-$this$dropLastWhile$0); -1 < index; index--) {
         if (!predicate.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, index))) as java.lang.Boolean) {
            return UArraysKt.take-qFRl0hI(var0, index + 1);
         }
      }

      return CollectionsKt.emptyList();
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.dropLastWhile(predicate: (ULong) -> Boolean): List<ULong> {
      for (int index = ArraysKt.getLastIndex($v$c$kotlin-ULongArray$-$this$dropLastWhile$0); -1 < index; index--) {
         if (!predicate.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, index))) as java.lang.Boolean) {
            return UArraysKt.take-r7IrZao(var0, index + 1);
         }
      }

      return CollectionsKt.emptyList();
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.dropLastWhile(predicate: (UByte) -> Boolean): List<UByte> {
      for (int index = ArraysKt.getLastIndex($v$c$kotlin-UByteArray$-$this$dropLastWhile$0); -1 < index; index--) {
         if (!predicate.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, index))) as java.lang.Boolean) {
            return UArraysKt.take-PpDY95g(var0, index + 1);
         }
      }

      return CollectionsKt.emptyList();
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.dropLastWhile(predicate: (UShort) -> Boolean): List<UShort> {
      for (int index = ArraysKt.getLastIndex($v$c$kotlin-UShortArray$-$this$dropLastWhile$0); -1 < index; index--) {
         if (!predicate.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index))) as java.lang.Boolean) {
            return UArraysKt.take-nggk6HY(var0, index + 1);
         }
      }

      return CollectionsKt.emptyList();
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.dropWhile(predicate: (UInt) -> Boolean): List<UInt> {
      var yielding: Boolean = false;
      val list: ArrayList = new ArrayList();
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$dropWhile$0); var4 < var5; var4++) {
         val item: Int = UIntArray.get-pVg5ArA(var0, var4);
         if (yielding) {
            list.add(UInt.box-impl(item));
         } else if (!predicate.invoke(UInt.box-impl(item)) as java.lang.Boolean) {
            list.add(UInt.box-impl(item));
            yielding = true;
         }
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.dropWhile(predicate: (ULong) -> Boolean): List<ULong> {
      var yielding: Boolean = false;
      val list: ArrayList = new ArrayList();
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$dropWhile$0); var4 < var5; var4++) {
         val item: Long = ULongArray.get-s-VKNKU(var0, var4);
         if (yielding) {
            list.add(ULong.box-impl(item));
         } else if (!predicate.invoke(ULong.box-impl(item)) as java.lang.Boolean) {
            list.add(ULong.box-impl(item));
            yielding = true;
         }
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.dropWhile(predicate: (UByte) -> Boolean): List<UByte> {
      var yielding: Boolean = false;
      val list: ArrayList = new ArrayList();
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$dropWhile$0); var4 < var5; var4++) {
         val item: Byte = UByteArray.get-w2LRezQ(var0, var4);
         if (yielding) {
            list.add(UByte.box-impl(item));
         } else if (!predicate.invoke(UByte.box-impl(item)) as java.lang.Boolean) {
            list.add(UByte.box-impl(item));
            yielding = true;
         }
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.dropWhile(predicate: (UShort) -> Boolean): List<UShort> {
      var yielding: Boolean = false;
      val list: ArrayList = new ArrayList();
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$dropWhile$0); var4 < var5; var4++) {
         val item: Short = UShortArray.get-Mh2AYeg(var0, var4);
         if (yielding) {
            list.add(UShort.box-impl(item));
         } else if (!predicate.invoke(UShort.box-impl(item)) as java.lang.Boolean) {
            list.add(UShort.box-impl(item));
            yielding = true;
         }
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.filter(predicate: (UInt) -> Boolean): List<UInt> {
      val var2: IntArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$filter$0); var4 < var5; var4++) {
         val var6: Int = UIntArray.get-pVg5ArA(var2, var4);
         if (predicate.invoke(UInt.box-impl(var6)) as java.lang.Boolean) {
            var3.add(UInt.box-impl(var6));
         }
      }

      return var3 as MutableList<UInt>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.filter(predicate: (ULong) -> Boolean): List<ULong> {
      val var2: LongArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$filter$0); var4 < var5; var4++) {
         val var6: Long = ULongArray.get-s-VKNKU(var2, var4);
         if (predicate.invoke(ULong.box-impl(var6)) as java.lang.Boolean) {
            var3.add(ULong.box-impl(var6));
         }
      }

      return var3 as MutableList<ULong>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.filter(predicate: (UByte) -> Boolean): List<UByte> {
      val var2: ByteArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$filter$0); var4 < var5; var4++) {
         val var6: Byte = UByteArray.get-w2LRezQ(var2, var4);
         if (predicate.invoke(UByte.box-impl(var6)) as java.lang.Boolean) {
            var3.add(UByte.box-impl(var6));
         }
      }

      return var3 as MutableList<UByte>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.filter(predicate: (UShort) -> Boolean): List<UShort> {
      val var2: ShortArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$filter$0); var4 < var5; var4++) {
         val var6: Short = UShortArray.get-Mh2AYeg(var2, var4);
         if (predicate.invoke(UShort.box-impl(var6)) as java.lang.Boolean) {
            var3.add(UShort.box-impl(var6));
         }
      }

      return var3 as MutableList<UShort>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.filterIndexed(predicate: (Int, UInt) -> Boolean): List<UInt> {
      val var3: java.util.Collection = new ArrayList();
      val var4: IntArray = var0;
      val var5: Int = 0;
      var var6: Int = 0;

      for (int var7 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$filterIndexed$0); var6 < var7; var6++) {
         val var8: Int = UIntArray.get-pVg5ArA(var4, var6);
         if (predicate.invoke(var5++, UInt.box-impl(var8)) as java.lang.Boolean) {
            var3.add(UInt.box-impl(var8));
         }
      }

      return var3 as MutableList<UInt>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.filterIndexed(predicate: (Int, ULong) -> Boolean): List<ULong> {
      val var3: java.util.Collection = new ArrayList();
      val var4: LongArray = var0;
      val var5: Int = 0;
      var var6: Int = 0;

      for (int var7 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$filterIndexed$0); var6 < var7; var6++) {
         val var8: Long = ULongArray.get-s-VKNKU(var4, var6);
         if (predicate.invoke(var5++, ULong.box-impl(var8)) as java.lang.Boolean) {
            var3.add(ULong.box-impl(var8));
         }
      }

      return var3 as MutableList<ULong>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.filterIndexed(predicate: (Int, UByte) -> Boolean): List<UByte> {
      val var3: java.util.Collection = new ArrayList();
      val var4: ByteArray = var0;
      val var5: Int = 0;
      var var6: Int = 0;

      for (int var7 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$filterIndexed$0); var6 < var7; var6++) {
         val var8: Byte = UByteArray.get-w2LRezQ(var4, var6);
         if (predicate.invoke(var5++, UByte.box-impl(var8)) as java.lang.Boolean) {
            var3.add(UByte.box-impl(var8));
         }
      }

      return var3 as MutableList<UByte>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.filterIndexed(predicate: (Int, UShort) -> Boolean): List<UShort> {
      val var3: java.util.Collection = new ArrayList();
      val var4: ShortArray = var0;
      val var5: Int = 0;
      var var6: Int = 0;

      for (int var7 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$filterIndexed$0); var6 < var7; var6++) {
         val var8: Short = UShortArray.get-Mh2AYeg(var4, var6);
         if (predicate.invoke(var5++, UShort.box-impl(var8)) as java.lang.Boolean) {
            var3.add(UShort.box-impl(var8));
         }
      }

      return var3 as MutableList<UShort>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <C : MutableCollection<in UInt>> UIntArray.filterIndexedTo(destination: C, predicate: (Int, UInt) -> Boolean): C {
      val var3: IntArray = var0;
      val var4: Int = 0;
      var var5: Int = 0;

      for (int var6 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$filterIndexedTo$0); var5 < var6; var5++) {
         val var7: Int = UIntArray.get-pVg5ArA(var3, var5);
         if (predicate.invoke(var4++, UInt.box-impl(var7)) as java.lang.Boolean) {
            destination.add(UInt.box-impl(var7));
         }
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <C : MutableCollection<in ULong>> ULongArray.filterIndexedTo(destination: C, predicate: (Int, ULong) -> Boolean): C {
      val var3: LongArray = var0;
      val var4: Int = 0;
      var var5: Int = 0;

      for (int var6 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$filterIndexedTo$0); var5 < var6; var5++) {
         val var7: Long = ULongArray.get-s-VKNKU(var3, var5);
         if (predicate.invoke(var4++, ULong.box-impl(var7)) as java.lang.Boolean) {
            destination.add(ULong.box-impl(var7));
         }
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <C : MutableCollection<in UByte>> UByteArray.filterIndexedTo(destination: C, predicate: (Int, UByte) -> Boolean): C {
      val var3: ByteArray = var0;
      val var4: Int = 0;
      var var5: Int = 0;

      for (int var6 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$filterIndexedTo$0); var5 < var6; var5++) {
         val var7: Byte = UByteArray.get-w2LRezQ(var3, var5);
         if (predicate.invoke(var4++, UByte.box-impl(var7)) as java.lang.Boolean) {
            destination.add(UByte.box-impl(var7));
         }
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <C : MutableCollection<in UShort>> UShortArray.filterIndexedTo(destination: C, predicate: (Int, UShort) -> Boolean): C {
      val var3: ShortArray = var0;
      val var4: Int = 0;
      var var5: Int = 0;

      for (int var6 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$filterIndexedTo$0); var5 < var6; var5++) {
         val var7: Short = UShortArray.get-Mh2AYeg(var3, var5);
         if (predicate.invoke(var4++, UShort.box-impl(var7)) as java.lang.Boolean) {
            destination.add(UShort.box-impl(var7));
         }
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.filterNot(predicate: (UInt) -> Boolean): List<UInt> {
      val var2: IntArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$filterNot$0); var4 < var5; var4++) {
         val var6: Int = UIntArray.get-pVg5ArA(var2, var4);
         if (!predicate.invoke(UInt.box-impl(var6)) as java.lang.Boolean) {
            var3.add(UInt.box-impl(var6));
         }
      }

      return var3 as MutableList<UInt>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.filterNot(predicate: (ULong) -> Boolean): List<ULong> {
      val var2: LongArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$filterNot$0); var4 < var5; var4++) {
         val var6: Long = ULongArray.get-s-VKNKU(var2, var4);
         if (!predicate.invoke(ULong.box-impl(var6)) as java.lang.Boolean) {
            var3.add(ULong.box-impl(var6));
         }
      }

      return var3 as MutableList<ULong>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.filterNot(predicate: (UByte) -> Boolean): List<UByte> {
      val var2: ByteArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$filterNot$0); var4 < var5; var4++) {
         val var6: Byte = UByteArray.get-w2LRezQ(var2, var4);
         if (!predicate.invoke(UByte.box-impl(var6)) as java.lang.Boolean) {
            var3.add(UByte.box-impl(var6));
         }
      }

      return var3 as MutableList<UByte>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.filterNot(predicate: (UShort) -> Boolean): List<UShort> {
      val var2: ShortArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$filterNot$0); var4 < var5; var4++) {
         val var6: Short = UShortArray.get-Mh2AYeg(var2, var4);
         if (!predicate.invoke(UShort.box-impl(var6)) as java.lang.Boolean) {
            var3.add(UShort.box-impl(var6));
         }
      }

      return var3 as MutableList<UShort>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <C : MutableCollection<in UInt>> UIntArray.filterNotTo(destination: C, predicate: (UInt) -> Boolean): C {
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$filterNotTo$0); var3 < var4; var3++) {
         val element: Int = UIntArray.get-pVg5ArA(var0, var3);
         if (!predicate.invoke(UInt.box-impl(element)) as java.lang.Boolean) {
            destination.add(UInt.box-impl(element));
         }
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <C : MutableCollection<in ULong>> ULongArray.filterNotTo(destination: C, predicate: (ULong) -> Boolean): C {
      var var3: Int = 0;

      for (int var4 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$filterNotTo$0); var3 < var4; var3++) {
         val element: Long = ULongArray.get-s-VKNKU(var0, var3);
         if (!predicate.invoke(ULong.box-impl(element)) as java.lang.Boolean) {
            destination.add(ULong.box-impl(element));
         }
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <C : MutableCollection<in UByte>> UByteArray.filterNotTo(destination: C, predicate: (UByte) -> Boolean): C {
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$filterNotTo$0); var3 < var4; var3++) {
         val element: Byte = UByteArray.get-w2LRezQ(var0, var3);
         if (!predicate.invoke(UByte.box-impl(element)) as java.lang.Boolean) {
            destination.add(UByte.box-impl(element));
         }
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <C : MutableCollection<in UShort>> UShortArray.filterNotTo(destination: C, predicate: (UShort) -> Boolean): C {
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$filterNotTo$0); var3 < var4; var3++) {
         val element: Short = UShortArray.get-Mh2AYeg(var0, var3);
         if (!predicate.invoke(UShort.box-impl(element)) as java.lang.Boolean) {
            destination.add(UShort.box-impl(element));
         }
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <C : MutableCollection<in UInt>> UIntArray.filterTo(destination: C, predicate: (UInt) -> Boolean): C {
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$filterTo$0); var3 < var4; var3++) {
         val element: Int = UIntArray.get-pVg5ArA(var0, var3);
         if (predicate.invoke(UInt.box-impl(element)) as java.lang.Boolean) {
            destination.add(UInt.box-impl(element));
         }
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <C : MutableCollection<in ULong>> ULongArray.filterTo(destination: C, predicate: (ULong) -> Boolean): C {
      var var3: Int = 0;

      for (int var4 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$filterTo$0); var3 < var4; var3++) {
         val element: Long = ULongArray.get-s-VKNKU(var0, var3);
         if (predicate.invoke(ULong.box-impl(element)) as java.lang.Boolean) {
            destination.add(ULong.box-impl(element));
         }
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <C : MutableCollection<in UByte>> UByteArray.filterTo(destination: C, predicate: (UByte) -> Boolean): C {
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$filterTo$0); var3 < var4; var3++) {
         val element: Byte = UByteArray.get-w2LRezQ(var0, var3);
         if (predicate.invoke(UByte.box-impl(element)) as java.lang.Boolean) {
            destination.add(UByte.box-impl(element));
         }
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <C : MutableCollection<in UShort>> UShortArray.filterTo(destination: C, predicate: (UShort) -> Boolean): C {
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$filterTo$0); var3 < var4; var3++) {
         val element: Short = UShortArray.get-Mh2AYeg(var0, var3);
         if (predicate.invoke(UShort.box-impl(element)) as java.lang.Boolean) {
            destination.add(UShort.box-impl(element));
         }
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.slice(indices: IntRange): List<UInt> {
      return if (indices.isEmpty())
         CollectionsKt.emptyList()
         else
         UArraysKt.asList--ajY-9A(UIntArray.constructor-impl(ArraysKt.copyOfRange(var0, indices.getStart(), indices.getEndInclusive() + 1)));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.slice(indices: IntRange): List<ULong> {
      return if (indices.isEmpty())
         CollectionsKt.emptyList()
         else
         UArraysKt.asList-QwZRm1k(ULongArray.constructor-impl(ArraysKt.copyOfRange(var0, indices.getStart(), indices.getEndInclusive() + 1)));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.slice(indices: IntRange): List<UByte> {
      return if (indices.isEmpty())
         CollectionsKt.emptyList()
         else
         UArraysKt.asList-GBYM_sE(UByteArray.constructor-impl(ArraysKt.copyOfRange(var0, indices.getStart(), indices.getEndInclusive() + 1)));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.slice(indices: IntRange): List<UShort> {
      return if (indices.isEmpty())
         CollectionsKt.emptyList()
         else
         UArraysKt.asList-rL5Bavg(UShortArray.constructor-impl(ArraysKt.copyOfRange(var0, indices.getStart(), indices.getEndInclusive() + 1)));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.slice(indices: Iterable<Int>): List<UInt> {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: ArrayList = new ArrayList(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            list.add(UInt.box-impl(UIntArray.get-pVg5ArA(var0, (var4.next() as java.lang.Number).intValue())));
         }

         return list;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.slice(indices: Iterable<Int>): List<ULong> {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: ArrayList = new ArrayList(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            list.add(ULong.box-impl(ULongArray.get-s-VKNKU(var0, (var4.next() as java.lang.Number).intValue())));
         }

         return list;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.slice(indices: Iterable<Int>): List<UByte> {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: ArrayList = new ArrayList(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            list.add(UByte.box-impl(UByteArray.get-w2LRezQ(var0, (var4.next() as java.lang.Number).intValue())));
         }

         return list;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.slice(indices: Iterable<Int>): List<UShort> {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: ArrayList = new ArrayList(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            list.add(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, (var4.next() as java.lang.Number).intValue())));
         }

         return list;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.sliceArray(indices: Collection<Int>): UIntArray {
      return UIntArray.constructor-impl(ArraysKt.sliceArray(var0, indices));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.sliceArray(indices: Collection<Int>): ULongArray {
      return ULongArray.constructor-impl(ArraysKt.sliceArray(var0, indices));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.sliceArray(indices: Collection<Int>): UByteArray {
      return UByteArray.constructor-impl(ArraysKt.sliceArray(var0, indices));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.sliceArray(indices: Collection<Int>): UShortArray {
      return UShortArray.constructor-impl(ArraysKt.sliceArray(var0, indices));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.sliceArray(indices: IntRange): UIntArray {
      return UIntArray.constructor-impl(ArraysKt.sliceArray(var0, indices));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.sliceArray(indices: IntRange): ULongArray {
      return ULongArray.constructor-impl(ArraysKt.sliceArray(var0, indices));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.sliceArray(indices: IntRange): UByteArray {
      return UByteArray.constructor-impl(ArraysKt.sliceArray(var0, indices));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.sliceArray(indices: IntRange): UShortArray {
      return UShortArray.constructor-impl(ArraysKt.sliceArray(var0, indices));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.take(n: Int): List<UInt> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else if (n >= UIntArray.getSize-impl(var0)) {
         return CollectionsKt.toList(UIntArray.box-impl(var0));
      } else if (n == 1) {
         return CollectionsKt.listOf(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0)));
      } else {
         val count: Int = 0;
         val list: ArrayList = new ArrayList(n);
         var var4: Int = 0;

         for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$take$0); var4 < var5; var4++) {
            list.add(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var4)));
            if (++count == n) {
               break;
            }
         }

         return list;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.take(n: Int): List<ULong> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else if (n >= ULongArray.getSize-impl(var0)) {
         return CollectionsKt.toList(ULongArray.box-impl(var0));
      } else if (n == 1) {
         return CollectionsKt.listOf(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0)));
      } else {
         val count: Int = 0;
         val list: ArrayList = new ArrayList(n);
         var var4: Int = 0;

         for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$take$0); var4 < var5; var4++) {
            list.add(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var4)));
            if (++count == n) {
               break;
            }
         }

         return list;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.take(n: Int): List<UByte> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else if (n >= UByteArray.getSize-impl(var0)) {
         return CollectionsKt.toList(UByteArray.box-impl(var0));
      } else if (n == 1) {
         return CollectionsKt.listOf(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0)));
      } else {
         val count: Int = 0;
         val list: ArrayList = new ArrayList(n);
         var var4: Int = 0;

         for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$take$0); var4 < var5; var4++) {
            list.add(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var4)));
            if (++count == n) {
               break;
            }
         }

         return list;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.take(n: Int): List<UShort> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else if (n >= UShortArray.getSize-impl(var0)) {
         return CollectionsKt.toList(UShortArray.box-impl(var0));
      } else if (n == 1) {
         return CollectionsKt.listOf(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0)));
      } else {
         val count: Int = 0;
         val list: ArrayList = new ArrayList(n);
         var var4: Int = 0;

         for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$take$0); var4 < var5; var4++) {
            list.add(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var4)));
            if (++count == n) {
               break;
            }
         }

         return list;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.takeLast(n: Int): List<UInt> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         val size: Int = UIntArray.getSize-impl(var0);
         if (n >= size) {
            return CollectionsKt.toList(UIntArray.box-impl(var0));
         } else if (n == 1) {
            return CollectionsKt.listOf(UInt.box-impl(UIntArray.get-pVg5ArA(var0, size - 1)));
         } else {
            val list: ArrayList = new ArrayList(n);

            for (int index = size - n; index < size; index++) {
               list.add(UInt.box-impl(UIntArray.get-pVg5ArA(var0, index)));
            }

            return list;
         }
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.takeLast(n: Int): List<ULong> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         val size: Int = ULongArray.getSize-impl(var0);
         if (n >= size) {
            return CollectionsKt.toList(ULongArray.box-impl(var0));
         } else if (n == 1) {
            return CollectionsKt.listOf(ULong.box-impl(ULongArray.get-s-VKNKU(var0, size - 1)));
         } else {
            val list: ArrayList = new ArrayList(n);

            for (int index = size - n; index < size; index++) {
               list.add(ULong.box-impl(ULongArray.get-s-VKNKU(var0, index)));
            }

            return list;
         }
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.takeLast(n: Int): List<UByte> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         val size: Int = UByteArray.getSize-impl(var0);
         if (n >= size) {
            return CollectionsKt.toList(UByteArray.box-impl(var0));
         } else if (n == 1) {
            return CollectionsKt.listOf(UByte.box-impl(UByteArray.get-w2LRezQ(var0, size - 1)));
         } else {
            val list: ArrayList = new ArrayList(n);

            for (int index = size - n; index < size; index++) {
               list.add(UByte.box-impl(UByteArray.get-w2LRezQ(var0, index)));
            }

            return list;
         }
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.takeLast(n: Int): List<UShort> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         val size: Int = UShortArray.getSize-impl(var0);
         if (n >= size) {
            return CollectionsKt.toList(UShortArray.box-impl(var0));
         } else if (n == 1) {
            return CollectionsKt.listOf(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, size - 1)));
         } else {
            val list: ArrayList = new ArrayList(n);

            for (int index = size - n; index < size; index++) {
               list.add(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index)));
            }

            return list;
         }
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.takeLastWhile(predicate: (UInt) -> Boolean): List<UInt> {
      for (int index = ArraysKt.getLastIndex($v$c$kotlin-UIntArray$-$this$takeLastWhile$0); -1 < index; index--) {
         if (!predicate.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, index))) as java.lang.Boolean) {
            return UArraysKt.drop-qFRl0hI(var0, index + 1);
         }
      }

      return CollectionsKt.toList(UIntArray.box-impl(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.takeLastWhile(predicate: (ULong) -> Boolean): List<ULong> {
      for (int index = ArraysKt.getLastIndex($v$c$kotlin-ULongArray$-$this$takeLastWhile$0); -1 < index; index--) {
         if (!predicate.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, index))) as java.lang.Boolean) {
            return UArraysKt.drop-r7IrZao(var0, index + 1);
         }
      }

      return CollectionsKt.toList(ULongArray.box-impl(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.takeLastWhile(predicate: (UByte) -> Boolean): List<UByte> {
      for (int index = ArraysKt.getLastIndex($v$c$kotlin-UByteArray$-$this$takeLastWhile$0); -1 < index; index--) {
         if (!predicate.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, index))) as java.lang.Boolean) {
            return UArraysKt.drop-PpDY95g(var0, index + 1);
         }
      }

      return CollectionsKt.toList(UByteArray.box-impl(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.takeLastWhile(predicate: (UShort) -> Boolean): List<UShort> {
      for (int index = ArraysKt.getLastIndex($v$c$kotlin-UShortArray$-$this$takeLastWhile$0); -1 < index; index--) {
         if (!predicate.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index))) as java.lang.Boolean) {
            return UArraysKt.drop-nggk6HY(var0, index + 1);
         }
      }

      return CollectionsKt.toList(UShortArray.box-impl(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.takeWhile(predicate: (UInt) -> Boolean): List<UInt> {
      val list: ArrayList = new ArrayList();
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$takeWhile$0); var3 < var4; var3++) {
         val item: Int = UIntArray.get-pVg5ArA(var0, var3);
         if (!predicate.invoke(UInt.box-impl(item)) as java.lang.Boolean) {
            break;
         }

         list.add(UInt.box-impl(item));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.takeWhile(predicate: (ULong) -> Boolean): List<ULong> {
      val list: ArrayList = new ArrayList();
      var var3: Int = 0;

      for (int var4 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$takeWhile$0); var3 < var4; var3++) {
         val item: Long = ULongArray.get-s-VKNKU(var0, var3);
         if (!predicate.invoke(ULong.box-impl(item)) as java.lang.Boolean) {
            break;
         }

         list.add(ULong.box-impl(item));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.takeWhile(predicate: (UByte) -> Boolean): List<UByte> {
      val list: ArrayList = new ArrayList();
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$takeWhile$0); var3 < var4; var3++) {
         val item: Byte = UByteArray.get-w2LRezQ(var0, var3);
         if (!predicate.invoke(UByte.box-impl(item)) as java.lang.Boolean) {
            break;
         }

         list.add(UByte.box-impl(item));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.takeWhile(predicate: (UShort) -> Boolean): List<UShort> {
      val list: ArrayList = new ArrayList();
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$takeWhile$0); var3 < var4; var3++) {
         val item: Short = UShortArray.get-Mh2AYeg(var0, var3);
         if (!predicate.invoke(UShort.box-impl(item)) as java.lang.Boolean) {
            break;
         }

         list.add(UShort.box-impl(item));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.reverse() {
      ArraysKt.reverse(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.reverse() {
      ArraysKt.reverse(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.reverse() {
      ArraysKt.reverse(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.reverse() {
      ArraysKt.reverse(var0);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.reverse(fromIndex: Int, toIndex: Int) {
      ArraysKt.reverse(var0, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.reverse(fromIndex: Int, toIndex: Int) {
      ArraysKt.reverse(var0, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.reverse(fromIndex: Int, toIndex: Int) {
      ArraysKt.reverse(var0, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.reverse(fromIndex: Int, toIndex: Int) {
      ArraysKt.reverse(var0, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.reversed(): List<UInt> {
      if (UIntArray.isEmpty-impl(var0)) {
         return CollectionsKt.emptyList();
      } else {
         val list: java.util.List = CollectionsKt.toMutableList(UIntArray.box-impl(var0));
         CollectionsKt.reverse(list);
         return list;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.reversed(): List<ULong> {
      if (ULongArray.isEmpty-impl(var0)) {
         return CollectionsKt.emptyList();
      } else {
         val list: java.util.List = CollectionsKt.toMutableList(ULongArray.box-impl(var0));
         CollectionsKt.reverse(list);
         return list;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.reversed(): List<UByte> {
      if (UByteArray.isEmpty-impl(var0)) {
         return CollectionsKt.emptyList();
      } else {
         val list: java.util.List = CollectionsKt.toMutableList(UByteArray.box-impl(var0));
         CollectionsKt.reverse(list);
         return list;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.reversed(): List<UShort> {
      if (UShortArray.isEmpty-impl(var0)) {
         return CollectionsKt.emptyList();
      } else {
         val list: java.util.List = CollectionsKt.toMutableList(UShortArray.box-impl(var0));
         CollectionsKt.reverse(list);
         return list;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.reversedArray(): UIntArray {
      return UIntArray.constructor-impl(ArraysKt.reversedArray(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.reversedArray(): ULongArray {
      return ULongArray.constructor-impl(ArraysKt.reversedArray(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.reversedArray(): UByteArray {
      return UByteArray.constructor-impl(ArraysKt.reversedArray(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.reversedArray(): UShortArray {
      return UShortArray.constructor-impl(ArraysKt.reversedArray(var0));
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.shuffle() {
      UArraysKt.shuffle-2D5oskM(var0, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.shuffle() {
      UArraysKt.shuffle-JzugnMA(var0, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.shuffle() {
      UArraysKt.shuffle-oSF2wD8(var0, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.shuffle() {
      UArraysKt.shuffle-s5X_as8(var0, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.shuffle(random: Random) {
      for (int i = ArraysKt.getLastIndex($v$c$kotlin-UIntArray$-$this$shuffle$0); 0 < i; i--) {
         val j: Int = random.nextInt(i + 1);
         val var5: Int = UIntArray.get-pVg5ArA(var0, i);
         UIntArray.set-VXSXFK8(var0, i, UIntArray.get-pVg5ArA(var0, j));
         UIntArray.set-VXSXFK8(var0, j, var5);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.shuffle(random: Random) {
      for (int i = ArraysKt.getLastIndex($v$c$kotlin-ULongArray$-$this$shuffle$0); 0 < i; i--) {
         val j: Int = random.nextInt(i + 1);
         val var6: Long = ULongArray.get-s-VKNKU(var0, i);
         ULongArray.set-k8EXiF4(var0, i, ULongArray.get-s-VKNKU(var0, j));
         ULongArray.set-k8EXiF4(var0, j, var6);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.shuffle(random: Random) {
      for (int i = ArraysKt.getLastIndex($v$c$kotlin-UByteArray$-$this$shuffle$0); 0 < i; i--) {
         val j: Int = random.nextInt(i + 1);
         val var5: Byte = UByteArray.get-w2LRezQ(var0, i);
         UByteArray.set-VurrAj0(var0, i, UByteArray.get-w2LRezQ(var0, j));
         UByteArray.set-VurrAj0(var0, j, var5);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.shuffle(random: Random) {
      for (int i = ArraysKt.getLastIndex($v$c$kotlin-UShortArray$-$this$shuffle$0); 0 < i; i--) {
         val j: Int = random.nextInt(i + 1);
         val var5: Short = UShortArray.get-Mh2AYeg(var0, i);
         UShortArray.set-01HTLdE(var0, i, UShortArray.get-Mh2AYeg(var0, j));
         UShortArray.set-01HTLdE(var0, j, var5);
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.sortDescending() {
      if (UIntArray.getSize-impl(var0) > 1) {
         UArraysKt.sort--ajY-9A(var0);
         ArraysKt.reverse(var0);
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.sortDescending() {
      if (ULongArray.getSize-impl(var0) > 1) {
         UArraysKt.sort-QwZRm1k(var0);
         ArraysKt.reverse(var0);
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.sortDescending() {
      if (UByteArray.getSize-impl(var0) > 1) {
         UArraysKt.sort-GBYM_sE(var0);
         ArraysKt.reverse(var0);
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.sortDescending() {
      if (UShortArray.getSize-impl(var0) > 1) {
         UArraysKt.sort-rL5Bavg(var0);
         ArraysKt.reverse(var0);
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.sorted(): List<UInt> {
      val var10000: IntArray = Arrays.copyOf(var0, var0.length);
      val var1: IntArray = UIntArray.constructor-impl(var10000);
      UArraysKt.sort--ajY-9A(var1);
      return UArraysKt.asList--ajY-9A(var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.sorted(): List<ULong> {
      val var10000: LongArray = Arrays.copyOf(var0, var0.length);
      val var1: LongArray = ULongArray.constructor-impl(var10000);
      UArraysKt.sort-QwZRm1k(var1);
      return UArraysKt.asList-QwZRm1k(var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.sorted(): List<UByte> {
      val var10000: ByteArray = Arrays.copyOf(var0, var0.length);
      val var1: ByteArray = UByteArray.constructor-impl(var10000);
      UArraysKt.sort-GBYM_sE(var1);
      return UArraysKt.asList-GBYM_sE(var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.sorted(): List<UShort> {
      val var10000: ShortArray = Arrays.copyOf(var0, var0.length);
      val var1: ShortArray = UShortArray.constructor-impl(var10000);
      UArraysKt.sort-rL5Bavg(var1);
      return UArraysKt.asList-rL5Bavg(var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.sortedArray(): UIntArray {
      if (UIntArray.isEmpty-impl(var0)) {
         return var0;
      } else {
         val var10000: IntArray = Arrays.copyOf(var0, var0.length);
         val var1: IntArray = UIntArray.constructor-impl(var10000);
         UArraysKt.sort--ajY-9A(var1);
         return var1;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.sortedArray(): ULongArray {
      if (ULongArray.isEmpty-impl(var0)) {
         return var0;
      } else {
         val var10000: LongArray = Arrays.copyOf(var0, var0.length);
         val var1: LongArray = ULongArray.constructor-impl(var10000);
         UArraysKt.sort-QwZRm1k(var1);
         return var1;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.sortedArray(): UByteArray {
      if (UByteArray.isEmpty-impl(var0)) {
         return var0;
      } else {
         val var10000: ByteArray = Arrays.copyOf(var0, var0.length);
         val var1: ByteArray = UByteArray.constructor-impl(var10000);
         UArraysKt.sort-GBYM_sE(var1);
         return var1;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.sortedArray(): UShortArray {
      if (UShortArray.isEmpty-impl(var0)) {
         return var0;
      } else {
         val var10000: ShortArray = Arrays.copyOf(var0, var0.length);
         val var1: ShortArray = UShortArray.constructor-impl(var10000);
         UArraysKt.sort-rL5Bavg(var1);
         return var1;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.sortedArrayDescending(): UIntArray {
      if (UIntArray.isEmpty-impl(var0)) {
         return var0;
      } else {
         val var10000: IntArray = Arrays.copyOf(var0, var0.length);
         val var1: IntArray = UIntArray.constructor-impl(var10000);
         UArraysKt.sortDescending--ajY-9A(var1);
         return var1;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.sortedArrayDescending(): ULongArray {
      if (ULongArray.isEmpty-impl(var0)) {
         return var0;
      } else {
         val var10000: LongArray = Arrays.copyOf(var0, var0.length);
         val var1: LongArray = ULongArray.constructor-impl(var10000);
         UArraysKt.sortDescending-QwZRm1k(var1);
         return var1;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.sortedArrayDescending(): UByteArray {
      if (UByteArray.isEmpty-impl(var0)) {
         return var0;
      } else {
         val var10000: ByteArray = Arrays.copyOf(var0, var0.length);
         val var1: ByteArray = UByteArray.constructor-impl(var10000);
         UArraysKt.sortDescending-GBYM_sE(var1);
         return var1;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.sortedArrayDescending(): UShortArray {
      if (UShortArray.isEmpty-impl(var0)) {
         return var0;
      } else {
         val var10000: ShortArray = Arrays.copyOf(var0, var0.length);
         val var1: ShortArray = UShortArray.constructor-impl(var10000);
         UArraysKt.sortDescending-rL5Bavg(var1);
         return var1;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.sortedDescending(): List<UInt> {
      val var10000: IntArray = Arrays.copyOf(var0, var0.length);
      val var1: IntArray = UIntArray.constructor-impl(var10000);
      UArraysKt.sort--ajY-9A(var1);
      return UArraysKt.reversed--ajY-9A(var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.sortedDescending(): List<ULong> {
      val var10000: LongArray = Arrays.copyOf(var0, var0.length);
      val var1: LongArray = ULongArray.constructor-impl(var10000);
      UArraysKt.sort-QwZRm1k(var1);
      return UArraysKt.reversed-QwZRm1k(var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.sortedDescending(): List<UByte> {
      val var10000: ByteArray = Arrays.copyOf(var0, var0.length);
      val var1: ByteArray = UByteArray.constructor-impl(var10000);
      UArraysKt.sort-GBYM_sE(var1);
      return UArraysKt.reversed-GBYM_sE(var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.sortedDescending(): List<UShort> {
      val var10000: ShortArray = Arrays.copyOf(var0, var0.length);
      val var1: ShortArray = UShortArray.constructor-impl(var10000);
      UArraysKt.sort-rL5Bavg(var1);
      return UArraysKt.reversed-rL5Bavg(var1);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.asByteArray(): ByteArray {
      return var0;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.asIntArray(): IntArray {
      return var0;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.asLongArray(): LongArray {
      return var0;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.asShortArray(): ShortArray {
      return var0;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.asUByteArray(): UByteArray {
      return UByteArray.constructor-impl(`$this$asUByteArray`);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.asUIntArray(): UIntArray {
      return UIntArray.constructor-impl(`$this$asUIntArray`);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.asULongArray(): ULongArray {
      return ULongArray.constructor-impl(`$this$asULongArray`);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.asUShortArray(): UShortArray {
      return UShortArray.constructor-impl(`$this$asUShortArray`);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun UIntArray?.contentEquals(other: UIntArray?): Boolean {
      var var10000: IntArray = `$this$contentEquals_u2dKJPZfPQ`;
      if (`$this$contentEquals_u2dKJPZfPQ` == null) {
         var10000 = null;
      }

      var var10001: IntArray = other;
      if (other == null) {
         var10001 = null;
      }

      return Arrays.equals(var10000, var10001);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun ULongArray?.contentEquals(other: ULongArray?): Boolean {
      var var10000: LongArray = `$this$contentEquals_u2dlec5QzE`;
      if (`$this$contentEquals_u2dlec5QzE` == null) {
         var10000 = null;
      }

      var var10001: LongArray = other;
      if (other == null) {
         var10001 = null;
      }

      return Arrays.equals(var10000, var10001);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun UByteArray?.contentEquals(other: UByteArray?): Boolean {
      var var10000: ByteArray = `$this$contentEquals_u2dkV0jMPg`;
      if (`$this$contentEquals_u2dkV0jMPg` == null) {
         var10000 = null;
      }

      var var10001: ByteArray = other;
      if (other == null) {
         var10001 = null;
      }

      return Arrays.equals(var10000, var10001);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun UShortArray?.contentEquals(other: UShortArray?): Boolean {
      var var10000: ShortArray = `$this$contentEquals_u2dFGO6Aew`;
      if (`$this$contentEquals_u2dFGO6Aew` == null) {
         var10000 = null;
      }

      var var10001: ShortArray = other;
      if (other == null) {
         var10001 = null;
      }

      return Arrays.equals(var10000, var10001);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray?.contentHashCode(): Int {
      var var10000: IntArray = `$this$contentHashCode_u2dXUkPCBk`;
      if (`$this$contentHashCode_u2dXUkPCBk` == null) {
         var10000 = null;
      }

      return Arrays.hashCode(var10000);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray?.contentHashCode(): Int {
      var var10000: LongArray = `$this$contentHashCode_u2duLth9ew`;
      if (`$this$contentHashCode_u2duLth9ew` == null) {
         var10000 = null;
      }

      return Arrays.hashCode(var10000);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray?.contentHashCode(): Int {
      var var10000: ByteArray = `$this$contentHashCode_u2d2csIQuQ`;
      if (`$this$contentHashCode_u2d2csIQuQ` == null) {
         var10000 = null;
      }

      return Arrays.hashCode(var10000);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray?.contentHashCode(): Int {
      var var10000: ShortArray = `$this$contentHashCode_u2dd_u2d6D3K8`;
      if (`$this$contentHashCode_u2dd_u2d6D3K8` == null) {
         var10000 = null;
      }

      return Arrays.hashCode(var10000);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray?.contentToString(): String {
      if (`$this$contentToString_u2dXUkPCBk` != null) {
         val var10000: java.lang.String = CollectionsKt.joinToString$default(
            UIntArray.box-impl(`$this$contentToString_u2dXUkPCBk`), ", ", "[", "]", 0, null, null, 56, null
         );
         if (var10000 != null) {
            return var10000;
         }
      }

      return "null";
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray?.contentToString(): String {
      if (`$this$contentToString_u2duLth9ew` != null) {
         val var10000: java.lang.String = CollectionsKt.joinToString$default(
            ULongArray.box-impl(`$this$contentToString_u2duLth9ew`), ", ", "[", "]", 0, null, null, 56, null
         );
         if (var10000 != null) {
            return var10000;
         }
      }

      return "null";
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray?.contentToString(): String {
      if (`$this$contentToString_u2d2csIQuQ` != null) {
         val var10000: java.lang.String = CollectionsKt.joinToString$default(
            UByteArray.box-impl(`$this$contentToString_u2d2csIQuQ`), ", ", "[", "]", 0, null, null, 56, null
         );
         if (var10000 != null) {
            return var10000;
         }
      }

      return "null";
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray?.contentToString(): String {
      if (`$this$contentToString_u2dd_u2d6D3K8` != null) {
         val var10000: java.lang.String = CollectionsKt.joinToString$default(
            UShortArray.box-impl(`$this$contentToString_u2dd_u2d6D3K8`), ", ", "[", "]", 0, null, null, 56, null
         );
         if (var10000 != null) {
            return var10000;
         }
      }

      return "null";
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.copyInto(destination: UIntArray, destinationOffset: Int = ..., startIndex: Int = ..., endIndex: Int = ...): UIntArray {
      ArraysKt.copyInto(var0, var1, destinationOffset, startIndex, endIndex);
      return var1;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.copyInto(destination: ULongArray, destinationOffset: Int = ..., startIndex: Int = ..., endIndex: Int = ...): ULongArray {
      ArraysKt.copyInto(var0, var1, destinationOffset, startIndex, endIndex);
      return var1;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.copyInto(destination: UByteArray, destinationOffset: Int = ..., startIndex: Int = ..., endIndex: Int = ...): UByteArray {
      ArraysKt.copyInto(var0, var1, destinationOffset, startIndex, endIndex);
      return var1;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.copyInto(destination: UShortArray, destinationOffset: Int = ..., startIndex: Int = ..., endIndex: Int = ...): UShortArray {
      ArraysKt.copyInto(var0, var1, destinationOffset, startIndex, endIndex);
      return var1;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.copyOf(): UIntArray {
      val var10000: IntArray = Arrays.copyOf(var0, var0.length);
      return UIntArray.constructor-impl(var10000);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.copyOf(): ULongArray {
      val var10000: LongArray = Arrays.copyOf(var0, var0.length);
      return ULongArray.constructor-impl(var10000);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.copyOf(): UByteArray {
      val var10000: ByteArray = Arrays.copyOf(var0, var0.length);
      return UByteArray.constructor-impl(var10000);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.copyOf(): UShortArray {
      val var10000: ShortArray = Arrays.copyOf(var0, var0.length);
      return UShortArray.constructor-impl(var10000);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.copyOf(newSize: Int): UIntArray {
      val var10000: IntArray = Arrays.copyOf(var0, newSize);
      return UIntArray.constructor-impl(var10000);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.copyOf(newSize: Int): ULongArray {
      val var10000: LongArray = Arrays.copyOf(var0, newSize);
      return ULongArray.constructor-impl(var10000);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.copyOf(newSize: Int): UByteArray {
      val var10000: ByteArray = Arrays.copyOf(var0, newSize);
      return UByteArray.constructor-impl(var10000);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.copyOf(newSize: Int): UShortArray {
      val var10000: ShortArray = Arrays.copyOf(var0, newSize);
      return UShortArray.constructor-impl(var10000);
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalStdlibApi
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.copyOf(newSize: Int, init: (Int) -> UInt): UIntArray {
      if (newSize < 0) {
         throw new IllegalArgumentException(("Invalid new array size: $newSize.").toString());
      } else {
         val oldSize: Int = UIntArray.getSize-impl(var0);
         val var10000: IntArray = Arrays.copyOf(var0, newSize);
         val copy: IntArray = UIntArray.constructor-impl(var10000);

         for (int idx = oldSize; idx < newSize; idx++) {
            UIntArray.set-VXSXFK8(copy, idx, (init.invoke(idx) as UInt).unbox-impl());
         }

         return copy;
      }
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalStdlibApi
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.copyOf(newSize: Int, init: (Int) -> ULong): ULongArray {
      if (newSize < 0) {
         throw new IllegalArgumentException(("Invalid new array size: $newSize.").toString());
      } else {
         val oldSize: Int = ULongArray.getSize-impl(var0);
         val var10000: LongArray = Arrays.copyOf(var0, newSize);
         val copy: LongArray = ULongArray.constructor-impl(var10000);

         for (int idx = oldSize; idx < newSize; idx++) {
            ULongArray.set-k8EXiF4(copy, idx, (init.invoke(idx) as ULong).unbox-impl());
         }

         return copy;
      }
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalStdlibApi
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.copyOf(newSize: Int, init: (Int) -> UByte): UByteArray {
      if (newSize < 0) {
         throw new IllegalArgumentException(("Invalid new array size: $newSize.").toString());
      } else {
         val oldSize: Int = UByteArray.getSize-impl(var0);
         val var10000: ByteArray = Arrays.copyOf(var0, newSize);
         val copy: ByteArray = UByteArray.constructor-impl(var10000);

         for (int idx = oldSize; idx < newSize; idx++) {
            UByteArray.set-VurrAj0(copy, idx, (init.invoke(idx) as UByte).unbox-impl());
         }

         return copy;
      }
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalStdlibApi
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.copyOf(newSize: Int, init: (Int) -> UShort): UShortArray {
      if (newSize < 0) {
         throw new IllegalArgumentException(("Invalid new array size: $newSize.").toString());
      } else {
         val oldSize: Int = UShortArray.getSize-impl(var0);
         val var10000: ShortArray = Arrays.copyOf(var0, newSize);
         val copy: ShortArray = UShortArray.constructor-impl(var10000);

         for (int idx = oldSize; idx < newSize; idx++) {
            UShortArray.set-01HTLdE(copy, idx, (init.invoke(idx) as UShort).unbox-impl());
         }

         return copy;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.copyOfRange(fromIndex: Int, toIndex: Int): UIntArray {
      return UIntArray.constructor-impl(ArraysKt.copyOfRange(var0, fromIndex, toIndex));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.copyOfRange(fromIndex: Int, toIndex: Int): ULongArray {
      return ULongArray.constructor-impl(ArraysKt.copyOfRange(var0, fromIndex, toIndex));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.copyOfRange(fromIndex: Int, toIndex: Int): UByteArray {
      return UByteArray.constructor-impl(ArraysKt.copyOfRange(var0, fromIndex, toIndex));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.copyOfRange(fromIndex: Int, toIndex: Int): UShortArray {
      return UShortArray.constructor-impl(ArraysKt.copyOfRange(var0, fromIndex, toIndex));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.fill(element: UInt, fromIndex: Int = ..., toIndex: Int = ...) {
      ArraysKt.fill(var0, var1, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.fill(element: ULong, fromIndex: Int = ..., toIndex: Int = ...) {
      ArraysKt.fill(var0, var1, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.fill(element: UByte, fromIndex: Int = ..., toIndex: Int = ...) {
      ArraysKt.fill(var0, var1, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.fill(element: UShort, fromIndex: Int = ..., toIndex: Int = ...) {
      ArraysKt.fill(var0, var1, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UIntArray.plus(element: UInt): UIntArray {
      return UIntArray.constructor-impl(ArraysKt.plus(var0, var1));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun ULongArray.plus(element: ULong): ULongArray {
      return ULongArray.constructor-impl(ArraysKt.plus(var0, var1));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UByteArray.plus(element: UByte): UByteArray {
      return UByteArray.constructor-impl(ArraysKt.plus(var0, var1));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UShortArray.plus(element: UShort): UShortArray {
      return UShortArray.constructor-impl(ArraysKt.plus(var0, var1));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public operator fun UIntArray.plus(elements: Collection<UInt>): UIntArray {
      var index: Int = UIntArray.getSize-impl(var0);
      val var10000: IntArray = Arrays.copyOf(var0, UIntArray.getSize-impl(var0) + elements.size());
      val result: IntArray = var10000;
      val var4: java.util.Iterator = elements.iterator();

      while (var4.hasNext()) {
         result[index++] = (var4.next() as UInt).unbox-impl();
      }

      return UIntArray.constructor-impl(result);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public operator fun ULongArray.plus(elements: Collection<ULong>): ULongArray {
      var index: Int = ULongArray.getSize-impl(var0);
      val var10000: LongArray = Arrays.copyOf(var0, ULongArray.getSize-impl(var0) + elements.size());
      val result: LongArray = var10000;
      val var4: java.util.Iterator = elements.iterator();

      while (var4.hasNext()) {
         result[index++] = (var4.next() as ULong).unbox-impl();
      }

      return ULongArray.constructor-impl(result);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public operator fun UByteArray.plus(elements: Collection<UByte>): UByteArray {
      var index: Int = UByteArray.getSize-impl(var0);
      val var10000: ByteArray = Arrays.copyOf(var0, UByteArray.getSize-impl(var0) + elements.size());
      val result: ByteArray = var10000;
      val var4: java.util.Iterator = elements.iterator();

      while (var4.hasNext()) {
         result[index++] = (var4.next() as UByte).unbox-impl();
      }

      return UByteArray.constructor-impl(result);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public operator fun UShortArray.plus(elements: Collection<UShort>): UShortArray {
      var index: Int = UShortArray.getSize-impl(var0);
      val var10000: ShortArray = Arrays.copyOf(var0, UShortArray.getSize-impl(var0) + elements.size());
      val result: ShortArray = var10000;
      val var4: java.util.Iterator = elements.iterator();

      while (var4.hasNext()) {
         result[index++] = (var4.next() as UShort).unbox-impl();
      }

      return UShortArray.constructor-impl(result);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UIntArray.plus(elements: UIntArray): UIntArray {
      return UIntArray.constructor-impl(ArraysKt.plus(var0, var1));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun ULongArray.plus(elements: ULongArray): ULongArray {
      return ULongArray.constructor-impl(ArraysKt.plus(var0, var1));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UByteArray.plus(elements: UByteArray): UByteArray {
      return UByteArray.constructor-impl(ArraysKt.plus(var0, var1));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline operator fun UShortArray.plus(elements: UShortArray): UShortArray {
      return UShortArray.constructor-impl(ArraysKt.plus(var0, var1));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.sort() {
      if (UIntArray.getSize-impl(var0) > 1) {
         UArraySortingKt.sortArray-oBK06Vg(var0, 0, UIntArray.getSize-impl(var0));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.sort() {
      if (ULongArray.getSize-impl(var0) > 1) {
         UArraySortingKt.sortArray--nroSd4(var0, 0, ULongArray.getSize-impl(var0));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.sort() {
      if (UByteArray.getSize-impl(var0) > 1) {
         UArraySortingKt.sortArray-4UcCI2c(var0, 0, UByteArray.getSize-impl(var0));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.sort() {
      if (UShortArray.getSize-impl(var0) > 1) {
         UArraySortingKt.sortArray-Aa5vz7o(var0, 0, UShortArray.getSize-impl(var0));
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.sort(fromIndex: Int = ..., toIndex: Int = ...) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, UIntArray.getSize-impl(var0));
      if (fromIndex < toIndex - 1) {
         UArraySortingKt.sortArray-oBK06Vg(var0, fromIndex, toIndex);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.sort(fromIndex: Int = ..., toIndex: Int = ...) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, ULongArray.getSize-impl(var0));
      if (fromIndex < toIndex - 1) {
         UArraySortingKt.sortArray--nroSd4(var0, fromIndex, toIndex);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.sort(fromIndex: Int = ..., toIndex: Int = ...) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, UByteArray.getSize-impl(var0));
      if (fromIndex < toIndex - 1) {
         UArraySortingKt.sortArray-4UcCI2c(var0, fromIndex, toIndex);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.sort(fromIndex: Int = ..., toIndex: Int = ...) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, UShortArray.getSize-impl(var0));
      if (fromIndex < toIndex - 1) {
         UArraySortingKt.sortArray-Aa5vz7o(var0, fromIndex, toIndex);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.sortDescending(fromIndex: Int, toIndex: Int) {
      UArraysKt.sort-oBK06Vg(var0, fromIndex, toIndex);
      ArraysKt.reverse(var0, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.sortDescending(fromIndex: Int, toIndex: Int) {
      UArraysKt.sort--nroSd4(var0, fromIndex, toIndex);
      ArraysKt.reverse(var0, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.sortDescending(fromIndex: Int, toIndex: Int) {
      UArraysKt.sort-4UcCI2c(var0, fromIndex, toIndex);
      ArraysKt.reverse(var0, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.sortDescending(fromIndex: Int, toIndex: Int) {
      UArraysKt.sort-Aa5vz7o(var0, fromIndex, toIndex);
      ArraysKt.reverse(var0, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.toByteArray(): ByteArray {
      val var10000: ByteArray = Arrays.copyOf(var0, var0.length);
      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.toIntArray(): IntArray {
      val var10000: IntArray = Arrays.copyOf(var0, var0.length);
      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.toLongArray(): LongArray {
      val var10000: LongArray = Arrays.copyOf(var0, var0.length);
      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.toShortArray(): ShortArray {
      val var10000: ShortArray = Arrays.copyOf(var0, var0.length);
      return var10000;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.toTypedArray(): Array<UInt> {
      var var1: Int = 0;
      val var2: Int = UIntArray.getSize-impl(var0);

      val var3: Array<UInt>;
      for (var3 = new UInt[var2]; var1 < var2; var1++) {
         var3[var1] = UInt.box-impl(UIntArray.get-pVg5ArA(var0, var1));
      }

      return var3;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.toTypedArray(): Array<ULong> {
      var var1: Int = 0;
      val var2: Int = ULongArray.getSize-impl(var0);

      val var3: Array<ULong>;
      for (var3 = new ULong[var2]; var1 < var2; var1++) {
         var3[var1] = ULong.box-impl(ULongArray.get-s-VKNKU(var0, var1));
      }

      return var3;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.toTypedArray(): Array<UByte> {
      var var1: Int = 0;
      val var2: Int = UByteArray.getSize-impl(var0);

      val var3: Array<UByte>;
      for (var3 = new UByte[var2]; var1 < var2; var1++) {
         var3[var1] = UByte.box-impl(UByteArray.get-w2LRezQ(var0, var1));
      }

      return var3;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.toTypedArray(): Array<UShort> {
      var var1: Int = 0;
      val var2: Int = UShortArray.getSize-impl(var0);

      val var3: Array<UShort>;
      for (var3 = new UShort[var2]; var1 < var2; var1++) {
         var3[var1] = UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var1));
      }

      return var3;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun Array<out UByte>.toUByteArray(): UByteArray {
      val var1: Int = `$this$toUByteArray`.length;
      var var2: Int = 0;

      val var3: ByteArray;
      for (var3 = new byte[var1]; var2 < var1; var2++) {
         var3[var2] = `$this$toUByteArray`[var2].unbox-impl();
      }

      return UByteArray.constructor-impl(var3);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.toUByteArray(): UByteArray {
      val var10000: ByteArray = Arrays.copyOf(`$this$toUByteArray`, `$this$toUByteArray`.length);
      return UByteArray.constructor-impl(var10000);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun Array<out UInt>.toUIntArray(): UIntArray {
      val var1: Int = `$this$toUIntArray`.length;
      var var2: Int = 0;

      val var3: IntArray;
      for (var3 = new int[var1]; var2 < var1; var2++) {
         var3[var2] = `$this$toUIntArray`[var2].unbox-impl();
      }

      return UIntArray.constructor-impl(var3);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.toUIntArray(): UIntArray {
      val var10000: IntArray = Arrays.copyOf(`$this$toUIntArray`, `$this$toUIntArray`.length);
      return UIntArray.constructor-impl(var10000);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun Array<out ULong>.toULongArray(): ULongArray {
      val var1: Int = `$this$toULongArray`.length;
      var var2: Int = 0;

      val var3: LongArray;
      for (var3 = new long[var1]; var2 < var1; var2++) {
         var3[var2] = `$this$toULongArray`[var2].unbox-impl();
      }

      return ULongArray.constructor-impl(var3);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.toULongArray(): ULongArray {
      val var10000: LongArray = Arrays.copyOf(`$this$toULongArray`, `$this$toULongArray`.length);
      return ULongArray.constructor-impl(var10000);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun Array<out UShort>.toUShortArray(): UShortArray {
      val var1: Int = `$this$toUShortArray`.length;
      var var2: Int = 0;

      val var3: ShortArray;
      for (var3 = new short[var1]; var2 < var1; var2++) {
         var3[var2] = `$this$toUShortArray`[var2].unbox-impl();
      }

      return UShortArray.constructor-impl(var3);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.toUShortArray(): UShortArray {
      val var10000: ShortArray = Arrays.copyOf(`$this$toUShortArray`, `$this$toUShortArray`.length);
      return UShortArray.constructor-impl(var10000);
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <V> UIntArray.associateWith(valueSelector: (UInt) -> V): Map<UInt, V> {
      val result: LinkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(UIntArray.getSize-impl(var0)), 16));
      val var3: IntArray = var0;
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$associateWith$0); var4 < var5; var4++) {
         val var6: Int = UIntArray.get-pVg5ArA(var3, var4);
         result.put(UInt.box-impl(var6), valueSelector.invoke(UInt.box-impl(var6)));
      }

      return result;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <V> ULongArray.associateWith(valueSelector: (ULong) -> V): Map<ULong, V> {
      val result: LinkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(ULongArray.getSize-impl(var0)), 16));
      val var3: LongArray = var0;
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$associateWith$0); var4 < var5; var4++) {
         val var6: Long = ULongArray.get-s-VKNKU(var3, var4);
         result.put(ULong.box-impl(var6), valueSelector.invoke(ULong.box-impl(var6)));
      }

      return result;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <V> UByteArray.associateWith(valueSelector: (UByte) -> V): Map<UByte, V> {
      val result: LinkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(UByteArray.getSize-impl(var0)), 16));
      val var3: ByteArray = var0;
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$associateWith$0); var4 < var5; var4++) {
         val var6: Byte = UByteArray.get-w2LRezQ(var3, var4);
         result.put(UByte.box-impl(var6), valueSelector.invoke(UByte.box-impl(var6)));
      }

      return result;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <V> UShortArray.associateWith(valueSelector: (UShort) -> V): Map<UShort, V> {
      val result: LinkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(UShortArray.getSize-impl(var0)), 16));
      val var3: ShortArray = var0;
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$associateWith$0); var4 < var5; var4++) {
         val var6: Short = UShortArray.get-Mh2AYeg(var3, var4);
         result.put(UShort.box-impl(var6), valueSelector.invoke(UShort.box-impl(var6)));
      }

      return result;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <V, M : MutableMap<in UInt, in V>> UIntArray.associateWithTo(destination: M, valueSelector: (UInt) -> V): M {
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$associateWithTo$0); var3 < var4; var3++) {
         val element: Int = UIntArray.get-pVg5ArA(var0, var3);
         destination.put(UInt.box-impl(element), valueSelector.invoke(UInt.box-impl(element)));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <V, M : MutableMap<in ULong, in V>> ULongArray.associateWithTo(destination: M, valueSelector: (ULong) -> V): M {
      var var3: Int = 0;

      for (int var4 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$associateWithTo$0); var3 < var4; var3++) {
         val element: Long = ULongArray.get-s-VKNKU(var0, var3);
         destination.put(ULong.box-impl(element), valueSelector.invoke(ULong.box-impl(element)));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <V, M : MutableMap<in UByte, in V>> UByteArray.associateWithTo(destination: M, valueSelector: (UByte) -> V): M {
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$associateWithTo$0); var3 < var4; var3++) {
         val element: Byte = UByteArray.get-w2LRezQ(var0, var3);
         destination.put(UByte.box-impl(element), valueSelector.invoke(UByte.box-impl(element)));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <V, M : MutableMap<in UShort, in V>> UShortArray.associateWithTo(destination: M, valueSelector: (UShort) -> V): M {
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$associateWithTo$0); var3 < var4; var3++) {
         val element: Short = UShortArray.get-Mh2AYeg(var0, var3);
         destination.put(UShort.box-impl(element), valueSelector.invoke(UShort.box-impl(element)));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.flatMap(transform: (UInt) -> Iterable<R>): List<R> {
      val var2: IntArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$flatMap$0); var4 < var5; var4++) {
         CollectionsKt.addAll(var3, transform.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var2, var4))) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.flatMap(transform: (ULong) -> Iterable<R>): List<R> {
      val var2: LongArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$flatMap$0); var4 < var5; var4++) {
         CollectionsKt.addAll(var3, transform.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var2, var4))) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.flatMap(transform: (UByte) -> Iterable<R>): List<R> {
      val var2: ByteArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$flatMap$0); var4 < var5; var4++) {
         CollectionsKt.addAll(var3, transform.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var2, var4))) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.flatMap(transform: (UShort) -> Iterable<R>): List<R> {
      val var2: ShortArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$flatMap$0); var4 < var5; var4++) {
         CollectionsKt.addAll(var3, transform.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var2, var4))) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.flatMapIndexed(transform: (Int, UInt) -> Iterable<R>): List<R> {
      val var2: IntArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;
      var var5: Int = 0;

      for (int var6 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$flatMapIndexed$0); var5 < var6; var5++) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, UInt.box-impl(UIntArray.get-pVg5ArA(var2, var5))) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.flatMapIndexed(transform: (Int, ULong) -> Iterable<R>): List<R> {
      val var2: LongArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;
      var var5: Int = 0;

      for (int var6 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$flatMapIndexed$0); var5 < var6; var5++) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, ULong.box-impl(ULongArray.get-s-VKNKU(var2, var5))) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.flatMapIndexed(transform: (Int, UByte) -> Iterable<R>): List<R> {
      val var2: ByteArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;
      var var5: Int = 0;

      for (int var6 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$flatMapIndexed$0); var5 < var6; var5++) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, UByte.box-impl(UByteArray.get-w2LRezQ(var2, var5))) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.flatMapIndexed(transform: (Int, UShort) -> Iterable<R>): List<R> {
      val var2: ShortArray = var0;
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;
      var var5: Int = 0;

      for (int var6 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$flatMapIndexed$0); var5 < var6; var5++) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, UShort.box-impl(UShortArray.get-Mh2AYeg(var2, var5))) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> UIntArray.flatMapIndexedTo(destination: C, transform: (Int, UInt) -> Iterable<R>): C {
      var index: Int = 0;
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$flatMapIndexedTo$0); var4 < var5; var4++) {
         CollectionsKt.addAll(destination, transform.invoke(index++, UInt.box-impl(UIntArray.get-pVg5ArA(var0, var4))) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> ULongArray.flatMapIndexedTo(destination: C, transform: (Int, ULong) -> Iterable<R>): C {
      var index: Int = 0;
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$flatMapIndexedTo$0); var4 < var5; var4++) {
         CollectionsKt.addAll(destination, transform.invoke(index++, ULong.box-impl(ULongArray.get-s-VKNKU(var0, var4))) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> UByteArray.flatMapIndexedTo(destination: C, transform: (Int, UByte) -> Iterable<R>): C {
      var index: Int = 0;
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$flatMapIndexedTo$0); var4 < var5; var4++) {
         CollectionsKt.addAll(destination, transform.invoke(index++, UByte.box-impl(UByteArray.get-w2LRezQ(var0, var4))) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> UShortArray.flatMapIndexedTo(destination: C, transform: (Int, UShort) -> Iterable<R>): C {
      var index: Int = 0;
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$flatMapIndexedTo$0); var4 < var5; var4++) {
         CollectionsKt.addAll(destination, transform.invoke(index++, UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var4))) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> UIntArray.flatMapTo(destination: C, transform: (UInt) -> Iterable<R>): C {
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$flatMapTo$0); var3 < var4; var3++) {
         CollectionsKt.addAll(destination, transform.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var3))) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> ULongArray.flatMapTo(destination: C, transform: (ULong) -> Iterable<R>): C {
      var var3: Int = 0;

      for (int var4 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$flatMapTo$0); var3 < var4; var3++) {
         CollectionsKt.addAll(destination, transform.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var3))) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> UByteArray.flatMapTo(destination: C, transform: (UByte) -> Iterable<R>): C {
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$flatMapTo$0); var3 < var4; var3++) {
         CollectionsKt.addAll(destination, transform.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var3))) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> UShortArray.flatMapTo(destination: C, transform: (UShort) -> Iterable<R>): C {
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$flatMapTo$0); var3 < var4; var3++) {
         CollectionsKt.addAll(destination, transform.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var3))) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K> UIntArray.groupBy(keySelector: (UInt) -> K): Map<K, List<UInt>> {
      val var2: IntArray = var0;
      val var3: java.util.Map = new LinkedHashMap();
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$groupBy$0); var4 < var5; var4++) {
         val var6: Int = UIntArray.get-pVg5ArA(var2, var4);
         val var7: Any = keySelector.invoke(UInt.box-impl(var6));
         var var10000: Any = var3.get(var7);
         if (var10000 == null) {
            val var10: java.util.List = new ArrayList();
            var3.put(var7, var10);
            var10000 = var10;
         }

         (var10000 as java.util.List).add(UInt.box-impl(var6));
      }

      return var3;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K> ULongArray.groupBy(keySelector: (ULong) -> K): Map<K, List<ULong>> {
      val var2: LongArray = var0;
      val var3: java.util.Map = new LinkedHashMap();
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$groupBy$0); var4 < var5; var4++) {
         val var6: Long = ULongArray.get-s-VKNKU(var2, var4);
         val var8: Any = keySelector.invoke(ULong.box-impl(var6));
         var var10000: Any = var3.get(var8);
         if (var10000 == null) {
            val var11: java.util.List = new ArrayList();
            var3.put(var8, var11);
            var10000 = var11;
         }

         (var10000 as java.util.List).add(ULong.box-impl(var6));
      }

      return var3;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K> UByteArray.groupBy(keySelector: (UByte) -> K): Map<K, List<UByte>> {
      val var2: ByteArray = var0;
      val var3: java.util.Map = new LinkedHashMap();
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$groupBy$0); var4 < var5; var4++) {
         val var6: Byte = UByteArray.get-w2LRezQ(var2, var4);
         val var7: Any = keySelector.invoke(UByte.box-impl(var6));
         var var10000: Any = var3.get(var7);
         if (var10000 == null) {
            val var10: java.util.List = new ArrayList();
            var3.put(var7, var10);
            var10000 = var10;
         }

         (var10000 as java.util.List).add(UByte.box-impl(var6));
      }

      return var3;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K> UShortArray.groupBy(keySelector: (UShort) -> K): Map<K, List<UShort>> {
      val var2: ShortArray = var0;
      val var3: java.util.Map = new LinkedHashMap();
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$groupBy$0); var4 < var5; var4++) {
         val var6: Short = UShortArray.get-Mh2AYeg(var2, var4);
         val var7: Any = keySelector.invoke(UShort.box-impl(var6));
         var var10000: Any = var3.get(var7);
         if (var10000 == null) {
            val var10: java.util.List = new ArrayList();
            var3.put(var7, var10);
            var10000 = var10;
         }

         (var10000 as java.util.List).add(UShort.box-impl(var6));
      }

      return var3;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> UIntArray.groupBy(keySelector: (UInt) -> K, valueTransform: (UInt) -> V): Map<K, List<V>> {
      val var3: IntArray = var0;
      val var4: java.util.Map = new LinkedHashMap();
      var var5: Int = 0;

      for (int var6 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$groupBy$0); var5 < var6; var5++) {
         val var7: Int = UIntArray.get-pVg5ArA(var3, var5);
         val var8: Any = keySelector.invoke(UInt.box-impl(var7));
         var var10000: Any = var4.get(var8);
         if (var10000 == null) {
            val var11: java.util.List = new ArrayList();
            var4.put(var8, var11);
            var10000 = var11;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(UInt.box-impl(var7)));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> ULongArray.groupBy(keySelector: (ULong) -> K, valueTransform: (ULong) -> V): Map<K, List<V>> {
      val var3: LongArray = var0;
      val var4: java.util.Map = new LinkedHashMap();
      var var5: Int = 0;

      for (int var6 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$groupBy$0); var5 < var6; var5++) {
         val var7: Long = ULongArray.get-s-VKNKU(var3, var5);
         val var9: Any = keySelector.invoke(ULong.box-impl(var7));
         var var10000: Any = var4.get(var9);
         if (var10000 == null) {
            val var12: java.util.List = new ArrayList();
            var4.put(var9, var12);
            var10000 = var12;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(ULong.box-impl(var7)));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> UByteArray.groupBy(keySelector: (UByte) -> K, valueTransform: (UByte) -> V): Map<K, List<V>> {
      val var3: ByteArray = var0;
      val var4: java.util.Map = new LinkedHashMap();
      var var5: Int = 0;

      for (int var6 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$groupBy$0); var5 < var6; var5++) {
         val var7: Byte = UByteArray.get-w2LRezQ(var3, var5);
         val var8: Any = keySelector.invoke(UByte.box-impl(var7));
         var var10000: Any = var4.get(var8);
         if (var10000 == null) {
            val var11: java.util.List = new ArrayList();
            var4.put(var8, var11);
            var10000 = var11;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(UByte.box-impl(var7)));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K, V> UShortArray.groupBy(keySelector: (UShort) -> K, valueTransform: (UShort) -> V): Map<K, List<V>> {
      val var3: ShortArray = var0;
      val var4: java.util.Map = new LinkedHashMap();
      var var5: Int = 0;

      for (int var6 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$groupBy$0); var5 < var6; var5++) {
         val var7: Short = UShortArray.get-Mh2AYeg(var3, var5);
         val var8: Any = keySelector.invoke(UShort.box-impl(var7));
         var var10000: Any = var4.get(var8);
         if (var10000 == null) {
            val var11: java.util.List = new ArrayList();
            var4.put(var8, var11);
            var10000 = var11;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(UShort.box-impl(var7)));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K, M : MutableMap<in K, MutableList<UInt>>> UIntArray.groupByTo(destination: M, keySelector: (UInt) -> K): M {
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$groupByTo$0); var3 < var4; var3++) {
         val element: Int = UIntArray.get-pVg5ArA(var0, var3);
         val key: Any = keySelector.invoke(UInt.box-impl(element));
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var13: Any = new ArrayList();
            destination.put(key, var13);
            var10000 = var13;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(UInt.box-impl(element));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K, M : MutableMap<in K, MutableList<ULong>>> ULongArray.groupByTo(destination: M, keySelector: (ULong) -> K): M {
      var var3: Int = 0;

      for (int var4 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$groupByTo$0); var3 < var4; var3++) {
         val element: Long = ULongArray.get-s-VKNKU(var0, var3);
         val key: Any = keySelector.invoke(ULong.box-impl(element));
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var14: Any = new ArrayList();
            destination.put(key, var14);
            var10000 = var14;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(ULong.box-impl(element));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K, M : MutableMap<in K, MutableList<UByte>>> UByteArray.groupByTo(destination: M, keySelector: (UByte) -> K): M {
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$groupByTo$0); var3 < var4; var3++) {
         val element: Byte = UByteArray.get-w2LRezQ(var0, var3);
         val key: Any = keySelector.invoke(UByte.box-impl(element));
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var13: Any = new ArrayList();
            destination.put(key, var13);
            var10000 = var13;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(UByte.box-impl(element));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K, M : MutableMap<in K, MutableList<UShort>>> UShortArray.groupByTo(destination: M, keySelector: (UShort) -> K): M {
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$groupByTo$0); var3 < var4; var3++) {
         val element: Short = UShortArray.get-Mh2AYeg(var0, var3);
         val key: Any = keySelector.invoke(UShort.box-impl(element));
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var13: Any = new ArrayList();
            destination.put(key, var13);
            var10000 = var13;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(UShort.box-impl(element));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, MutableList<V>>> UIntArray.groupByTo(destination: M, keySelector: (UInt) -> K, valueTransform: (UInt) -> V): M {
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$groupByTo$0); var4 < var5; var4++) {
         val element: Int = UIntArray.get-pVg5ArA(var0, var4);
         val key: Any = keySelector.invoke(UInt.box-impl(element));
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var14: Any = new ArrayList();
            destination.put(key, var14);
            var10000 = var14;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(UInt.box-impl(element)));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, MutableList<V>>> ULongArray.groupByTo(destination: M, keySelector: (ULong) -> K, valueTransform: (ULong) -> V): M {
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$groupByTo$0); var4 < var5; var4++) {
         val element: Long = ULongArray.get-s-VKNKU(var0, var4);
         val key: Any = keySelector.invoke(ULong.box-impl(element));
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var15: Any = new ArrayList();
            destination.put(key, var15);
            var10000 = var15;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(ULong.box-impl(element)));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, MutableList<V>>> UByteArray.groupByTo(destination: M, keySelector: (UByte) -> K, valueTransform: (UByte) -> V): M {
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$groupByTo$0); var4 < var5; var4++) {
         val element: Byte = UByteArray.get-w2LRezQ(var0, var4);
         val key: Any = keySelector.invoke(UByte.box-impl(element));
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var14: Any = new ArrayList();
            destination.put(key, var14);
            var10000 = var14;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(UByte.box-impl(element)));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, MutableList<V>>> UShortArray.groupByTo(
      destination: M,
      keySelector: (UShort) -> K,
      valueTransform: (UShort) -> V
   ): M {
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$groupByTo$0); var4 < var5; var4++) {
         val element: Short = UShortArray.get-Mh2AYeg(var0, var4);
         val key: Any = keySelector.invoke(UShort.box-impl(element));
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var14: Any = new ArrayList();
            destination.put(key, var14);
            var10000 = var14;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(UShort.box-impl(element)));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.map(transform: (UInt) -> R): List<R> {
      val var2: IntArray = var0;
      val var3: java.util.Collection = new ArrayList(UIntArray.getSize-impl(var0));
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$map$0); var4 < var5; var4++) {
         var3.add(transform.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var2, var4))));
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.map(transform: (ULong) -> R): List<R> {
      val var2: LongArray = var0;
      val var3: java.util.Collection = new ArrayList(ULongArray.getSize-impl(var0));
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$map$0); var4 < var5; var4++) {
         var3.add(transform.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var2, var4))));
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.map(transform: (UByte) -> R): List<R> {
      val var2: ByteArray = var0;
      val var3: java.util.Collection = new ArrayList(UByteArray.getSize-impl(var0));
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$map$0); var4 < var5; var4++) {
         var3.add(transform.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var2, var4))));
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.map(transform: (UShort) -> R): List<R> {
      val var2: ShortArray = var0;
      val var3: java.util.Collection = new ArrayList(UShortArray.getSize-impl(var0));
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$map$0); var4 < var5; var4++) {
         var3.add(transform.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var2, var4))));
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.mapIndexed(transform: (Int, UInt) -> R): List<R> {
      val var2: IntArray = var0;
      val var3: java.util.Collection = new ArrayList(UIntArray.getSize-impl(var0));
      var var4: Int = 0;
      var var5: Int = 0;

      for (int var6 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$mapIndexed$0); var5 < var6; var5++) {
         var3.add(transform.invoke(var4++, UInt.box-impl(UIntArray.get-pVg5ArA(var2, var5))));
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.mapIndexed(transform: (Int, ULong) -> R): List<R> {
      val var2: LongArray = var0;
      val var3: java.util.Collection = new ArrayList(ULongArray.getSize-impl(var0));
      var var4: Int = 0;
      var var5: Int = 0;

      for (int var6 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$mapIndexed$0); var5 < var6; var5++) {
         var3.add(transform.invoke(var4++, ULong.box-impl(ULongArray.get-s-VKNKU(var2, var5))));
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.mapIndexed(transform: (Int, UByte) -> R): List<R> {
      val var2: ByteArray = var0;
      val var3: java.util.Collection = new ArrayList(UByteArray.getSize-impl(var0));
      var var4: Int = 0;
      var var5: Int = 0;

      for (int var6 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$mapIndexed$0); var5 < var6; var5++) {
         var3.add(transform.invoke(var4++, UByte.box-impl(UByteArray.get-w2LRezQ(var2, var5))));
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.mapIndexed(transform: (Int, UShort) -> R): List<R> {
      val var2: ShortArray = var0;
      val var3: java.util.Collection = new ArrayList(UShortArray.getSize-impl(var0));
      var var4: Int = 0;
      var var5: Int = 0;

      for (int var6 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$mapIndexed$0); var5 < var6; var5++) {
         var3.add(transform.invoke(var4++, UShort.box-impl(UShortArray.get-Mh2AYeg(var2, var5))));
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> UIntArray.mapIndexedTo(destination: C, transform: (Int, UInt) -> R): C {
      var index: Int = 0;
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$mapIndexedTo$0); var4 < var5; var4++) {
         destination.add(transform.invoke(index++, UInt.box-impl(UIntArray.get-pVg5ArA(var0, var4))));
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> ULongArray.mapIndexedTo(destination: C, transform: (Int, ULong) -> R): C {
      var index: Int = 0;
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$mapIndexedTo$0); var4 < var5; var4++) {
         destination.add(transform.invoke(index++, ULong.box-impl(ULongArray.get-s-VKNKU(var0, var4))));
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> UByteArray.mapIndexedTo(destination: C, transform: (Int, UByte) -> R): C {
      var index: Int = 0;
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$mapIndexedTo$0); var4 < var5; var4++) {
         destination.add(transform.invoke(index++, UByte.box-impl(UByteArray.get-w2LRezQ(var0, var4))));
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> UShortArray.mapIndexedTo(destination: C, transform: (Int, UShort) -> R): C {
      var index: Int = 0;
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$mapIndexedTo$0); var4 < var5; var4++) {
         destination.add(transform.invoke(index++, UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var4))));
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> UIntArray.mapTo(destination: C, transform: (UInt) -> R): C {
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$mapTo$0); var3 < var4; var3++) {
         destination.add(transform.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var3))));
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> ULongArray.mapTo(destination: C, transform: (ULong) -> R): C {
      var var3: Int = 0;

      for (int var4 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$mapTo$0); var3 < var4; var3++) {
         destination.add(transform.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var3))));
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> UByteArray.mapTo(destination: C, transform: (UByte) -> R): C {
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$mapTo$0); var3 < var4; var3++) {
         destination.add(transform.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var3))));
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> UShortArray.mapTo(destination: C, transform: (UShort) -> R): C {
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$mapTo$0); var3 < var4; var3++) {
         destination.add(transform.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var3))));
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.withIndex(): Iterable<IndexedValue<UInt>> {
      return new IndexingIterable<>(UArraysKt___UArraysKt::withIndex__ajY_9A$lambda$0$UArraysKt___UArraysKt);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.withIndex(): Iterable<IndexedValue<ULong>> {
      return new IndexingIterable<>(UArraysKt___UArraysKt::withIndex_QwZRm1k$lambda$0$UArraysKt___UArraysKt);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.withIndex(): Iterable<IndexedValue<UByte>> {
      return new IndexingIterable<>(UArraysKt___UArraysKt::withIndex_GBYM_sE$lambda$0$UArraysKt___UArraysKt);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.withIndex(): Iterable<IndexedValue<UShort>> {
      return new IndexingIterable<>(UArraysKt___UArraysKt::withIndex_rL5Bavg$lambda$0$UArraysKt___UArraysKt);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.all(predicate: (UInt) -> Boolean): Boolean {
      var var2: Int = 0;

      for (int var3 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$all$0); var2 < var3; var2++) {
         if (!predicate.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var2))) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.all(predicate: (ULong) -> Boolean): Boolean {
      var var2: Int = 0;

      for (int var3 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$all$0); var2 < var3; var2++) {
         if (!predicate.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var2))) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.all(predicate: (UByte) -> Boolean): Boolean {
      var var2: Int = 0;

      for (int var3 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$all$0); var2 < var3; var2++) {
         if (!predicate.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var2))) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.all(predicate: (UShort) -> Boolean): Boolean {
      var var2: Int = 0;

      for (int var3 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$all$0); var2 < var3; var2++) {
         if (!predicate.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var2))) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.any(): Boolean {
      return ArraysKt.any(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.any(): Boolean {
      return ArraysKt.any(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.any(): Boolean {
      return ArraysKt.any(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.any(): Boolean {
      return ArraysKt.any(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.any(predicate: (UInt) -> Boolean): Boolean {
      var var2: Int = 0;

      for (int var3 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$any$0); var2 < var3; var2++) {
         if (predicate.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var2))) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.any(predicate: (ULong) -> Boolean): Boolean {
      var var2: Int = 0;

      for (int var3 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$any$0); var2 < var3; var2++) {
         if (predicate.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var2))) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.any(predicate: (UByte) -> Boolean): Boolean {
      var var2: Int = 0;

      for (int var3 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$any$0); var2 < var3; var2++) {
         if (predicate.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var2))) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.any(predicate: (UShort) -> Boolean): Boolean {
      var var2: Int = 0;

      for (int var3 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$any$0); var2 < var3; var2++) {
         if (predicate.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var2))) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.count(predicate: (UInt) -> Boolean): Int {
      var count: Int = 0;
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$count$0); var3 < var4; var3++) {
         if (predicate.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var3))) as java.lang.Boolean) {
            count++;
         }
      }

      return count;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.count(predicate: (ULong) -> Boolean): Int {
      var count: Int = 0;
      var var3: Int = 0;

      for (int var4 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$count$0); var3 < var4; var3++) {
         if (predicate.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var3))) as java.lang.Boolean) {
            count++;
         }
      }

      return count;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.count(predicate: (UByte) -> Boolean): Int {
      var count: Int = 0;
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$count$0); var3 < var4; var3++) {
         if (predicate.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var3))) as java.lang.Boolean) {
            count++;
         }
      }

      return count;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.count(predicate: (UShort) -> Boolean): Int {
      var count: Int = 0;
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$count$0); var3 < var4; var3++) {
         if (predicate.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var3))) as java.lang.Boolean) {
            count++;
         }
      }

      return count;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.fold(initial: R, operation: (R, UInt) -> R): R {
      var accumulator: Any = initial;
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$fold$0); var4 < var5; var4++) {
         accumulator = operation.invoke(accumulator, UInt.box-impl(UIntArray.get-pVg5ArA(var0, var4)));
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.fold(initial: R, operation: (R, ULong) -> R): R {
      var accumulator: Any = initial;
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$fold$0); var4 < var5; var4++) {
         accumulator = operation.invoke(accumulator, ULong.box-impl(ULongArray.get-s-VKNKU(var0, var4)));
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.fold(initial: R, operation: (R, UByte) -> R): R {
      var accumulator: Any = initial;
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$fold$0); var4 < var5; var4++) {
         accumulator = operation.invoke(accumulator, UByte.box-impl(UByteArray.get-w2LRezQ(var0, var4)));
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.fold(initial: R, operation: (R, UShort) -> R): R {
      var accumulator: Any = initial;
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$fold$0); var4 < var5; var4++) {
         accumulator = operation.invoke(accumulator, UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var4)));
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.foldIndexed(initial: R, operation: (Int, R, UInt) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;
      var var5: Int = 0;

      for (int var6 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$foldIndexed$0); var5 < var6; var5++) {
         accumulator = operation.invoke(index++, accumulator, UInt.box-impl(UIntArray.get-pVg5ArA(var0, var5)));
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.foldIndexed(initial: R, operation: (Int, R, ULong) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;
      var var5: Int = 0;

      for (int var6 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$foldIndexed$0); var5 < var6; var5++) {
         accumulator = operation.invoke(index++, accumulator, ULong.box-impl(ULongArray.get-s-VKNKU(var0, var5)));
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.foldIndexed(initial: R, operation: (Int, R, UByte) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;
      var var5: Int = 0;

      for (int var6 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$foldIndexed$0); var5 < var6; var5++) {
         accumulator = operation.invoke(index++, accumulator, UByte.box-impl(UByteArray.get-w2LRezQ(var0, var5)));
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.foldIndexed(initial: R, operation: (Int, R, UShort) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;
      var var5: Int = 0;

      for (int var6 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$foldIndexed$0); var5 < var6; var5++) {
         accumulator = operation.invoke(index++, accumulator, UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var5)));
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.foldRight(initial: R, operation: (UInt, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(var0);
      var accumulator: Any = initial;

      while (index >= 0) {
         accumulator = operation.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, index--)), accumulator);
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.foldRight(initial: R, operation: (ULong, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(var0);
      var accumulator: Any = initial;

      while (index >= 0) {
         accumulator = operation.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, index--)), accumulator);
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.foldRight(initial: R, operation: (UByte, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(var0);
      var accumulator: Any = initial;

      while (index >= 0) {
         accumulator = operation.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, index--)), accumulator);
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.foldRight(initial: R, operation: (UShort, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(var0);
      var accumulator: Any = initial;

      while (index >= 0) {
         accumulator = operation.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index--)), accumulator);
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.foldRightIndexed(initial: R, operation: (Int, UInt, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(var0);

      var accumulator: Any;
      for (accumulator = initial; index >= 0; index--) {
         accumulator = operation.invoke(index, UInt.box-impl(UIntArray.get-pVg5ArA(var0, index)), accumulator);
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.foldRightIndexed(initial: R, operation: (Int, ULong, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(var0);

      var accumulator: Any;
      for (accumulator = initial; index >= 0; index--) {
         accumulator = operation.invoke(index, ULong.box-impl(ULongArray.get-s-VKNKU(var0, index)), accumulator);
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.foldRightIndexed(initial: R, operation: (Int, UByte, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(var0);

      var accumulator: Any;
      for (accumulator = initial; index >= 0; index--) {
         accumulator = operation.invoke(index, UByte.box-impl(UByteArray.get-w2LRezQ(var0, index)), accumulator);
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.foldRightIndexed(initial: R, operation: (Int, UShort, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(var0);

      var accumulator: Any;
      for (accumulator = initial; index >= 0; index--) {
         accumulator = operation.invoke(index, UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index)), accumulator);
      }

      return (R)accumulator;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.forEach(action: (UInt) -> Unit) {
      var var2: Int = 0;

      for (int var3 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$forEach$0); var2 < var3; var2++) {
         action.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var2)));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.forEach(action: (ULong) -> Unit) {
      var var2: Int = 0;

      for (int var3 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$forEach$0); var2 < var3; var2++) {
         action.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var2)));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.forEach(action: (UByte) -> Unit) {
      var var2: Int = 0;

      for (int var3 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$forEach$0); var2 < var3; var2++) {
         action.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var2)));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.forEach(action: (UShort) -> Unit) {
      var var2: Int = 0;

      for (int var3 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$forEach$0); var2 < var3; var2++) {
         action.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var2)));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.forEachIndexed(action: (Int, UInt) -> Unit) {
      var index: Int = 0;
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$forEachIndexed$0); var3 < var4; var3++) {
         action.invoke(index++, UInt.box-impl(UIntArray.get-pVg5ArA(var0, var3)));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.forEachIndexed(action: (Int, ULong) -> Unit) {
      var index: Int = 0;
      var var3: Int = 0;

      for (int var4 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$forEachIndexed$0); var3 < var4; var3++) {
         action.invoke(index++, ULong.box-impl(ULongArray.get-s-VKNKU(var0, var3)));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.forEachIndexed(action: (Int, UByte) -> Unit) {
      var index: Int = 0;
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$forEachIndexed$0); var3 < var4; var3++) {
         action.invoke(index++, UByte.box-impl(UByteArray.get-w2LRezQ(var0, var3)));
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.forEachIndexed(action: (Int, UShort) -> Unit) {
      var index: Int = 0;
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$forEachIndexed$0); var3 < var4; var3++) {
         action.invoke(index++, UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var3)));
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.max(): UInt {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var max: Int = UIntArray.get-pVg5ArA(var0, 0);
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var3) {
            while (true) {
               val e: Int = UIntArray.get-pVg5ArA(var0, i);
               if (Integer.compareUnsigned(max, e) < 0) {
                  max = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.max(): ULong {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var max: Long = ULongArray.get-s-VKNKU(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Long = ULongArray.get-s-VKNKU(var0, i);
               if (java.lang.Long.compareUnsigned(max, e) < 0) {
                  max = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.max(): UByte {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var max: Byte = UByteArray.get-w2LRezQ(var0, 0);
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var3) {
            while (true) {
               val e: Byte = UByteArray.get-w2LRezQ(var0, i);
               if (Intrinsics.compare(max and 255, e and 255) < 0) {
                  max = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.max(): UShort {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var max: Short = UShortArray.get-Mh2AYeg(var0, 0);
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var3) {
            while (true) {
               val e: Short = UShortArray.get-Mh2AYeg(var0, i);
               if (Intrinsics.compare(max and '\uffff', e and '\uffff') < 0) {
                  max = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow-U")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UIntArray.maxBy(selector: (UInt) -> R): UInt {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Int = UIntArray.get-pVg5ArA(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(UInt.box-impl(maxElem)) as java.lang.Comparable;
            var var8: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Int = UIntArray.get-pVg5ArA(var0, var8);
                  val v: java.lang.Comparable = selector.invoke(UInt.box-impl(e)) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (var8 == lastIndex) {
                     break;
                  }

                  var8++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow-U")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ULongArray.maxBy(selector: (ULong) -> R): ULong {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Long = ULongArray.get-s-VKNKU(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(ULong.box-impl(maxElem)) as java.lang.Comparable;
            var var10: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Long = ULongArray.get-s-VKNKU(var0, var10);
                  val v: java.lang.Comparable = selector.invoke(ULong.box-impl(e)) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (var10 == lastIndex) {
                     break;
                  }

                  var10++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow-U")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UByteArray.maxBy(selector: (UByte) -> R): UByte {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Byte = UByteArray.get-w2LRezQ(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(UByte.box-impl(maxElem)) as java.lang.Comparable;
            var var8: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Byte = UByteArray.get-w2LRezQ(var0, var8);
                  val v: java.lang.Comparable = selector.invoke(UByte.box-impl(e)) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (var8 == lastIndex) {
                     break;
                  }

                  var8++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow-U")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UShortArray.maxBy(selector: (UShort) -> R): UShort {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Short = UShortArray.get-Mh2AYeg(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(UShort.box-impl(maxElem)) as java.lang.Comparable;
            var var8: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Short = UShortArray.get-Mh2AYeg(var0, var8);
                  val v: java.lang.Comparable = selector.invoke(UShort.box-impl(e)) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (var8 == lastIndex) {
                     break;
                  }

                  var8++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UIntArray.maxByOrNull(selector: (UInt) -> R): UInt? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxElem: Int = UIntArray.get-pVg5ArA(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return UInt.box-impl(maxElem);
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(UInt.box-impl(maxElem)) as java.lang.Comparable;
            var var8: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Int = UIntArray.get-pVg5ArA(var0, var8);
                  val v: java.lang.Comparable = selector.invoke(UInt.box-impl(e)) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (var8 == lastIndex) {
                     break;
                  }

                  var8++;
               }
            }

            return UInt.box-impl(maxElem);
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ULongArray.maxByOrNull(selector: (ULong) -> R): ULong? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxElem: Long = ULongArray.get-s-VKNKU(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return ULong.box-impl(maxElem);
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(ULong.box-impl(maxElem)) as java.lang.Comparable;
            var var10: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Long = ULongArray.get-s-VKNKU(var0, var10);
                  val v: java.lang.Comparable = selector.invoke(ULong.box-impl(e)) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (var10 == lastIndex) {
                     break;
                  }

                  var10++;
               }
            }

            return ULong.box-impl(maxElem);
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UByteArray.maxByOrNull(selector: (UByte) -> R): UByte? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxElem: Byte = UByteArray.get-w2LRezQ(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return UByte.box-impl(maxElem);
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(UByte.box-impl(maxElem)) as java.lang.Comparable;
            var var8: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Byte = UByteArray.get-w2LRezQ(var0, var8);
                  val v: java.lang.Comparable = selector.invoke(UByte.box-impl(e)) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (var8 == lastIndex) {
                     break;
                  }

                  var8++;
               }
            }

            return UByte.box-impl(maxElem);
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UShortArray.maxByOrNull(selector: (UShort) -> R): UShort? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxElem: Short = UShortArray.get-Mh2AYeg(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return UShort.box-impl(maxElem);
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(UShort.box-impl(maxElem)) as java.lang.Comparable;
            var var8: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Short = UShortArray.get-Mh2AYeg(var0, var8);
                  val v: java.lang.Comparable = selector.invoke(UShort.box-impl(e)) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (var8 == lastIndex) {
                     break;
                  }

                  var8++;
               }
            }

            return UShort.box-impl(maxElem);
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.maxOf(selector: (UInt) -> Double): Double {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.maxOf(selector: (ULong) -> Double): Double {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.maxOf(selector: (UByte) -> Double): Double {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.maxOf(selector: (UShort) -> Double): Double {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.maxOf(selector: (UInt) -> Float): Float {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.maxOf(selector: (ULong) -> Float): Float {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.maxOf(selector: (UByte) -> Float): Float {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.maxOf(selector: (UShort) -> Float): Float {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UIntArray.maxOf(selector: (UInt) -> R): R {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i))) as java.lang.Comparable;
               if (maxValue.compareTo(v) < 0) {
                  maxValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ULongArray.maxOf(selector: (ULong) -> R): R {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i))) as java.lang.Comparable;
               if (maxValue.compareTo(v) < 0) {
                  maxValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UByteArray.maxOf(selector: (UByte) -> R): R {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i))) as java.lang.Comparable;
               if (maxValue.compareTo(v) < 0) {
                  maxValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UShortArray.maxOf(selector: (UShort) -> R): R {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i))) as java.lang.Comparable;
               if (maxValue.compareTo(v) < 0) {
                  maxValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.maxOfOrNull(selector: (UInt) -> Double): Double? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.maxOfOrNull(selector: (ULong) -> Double): Double? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.maxOfOrNull(selector: (UByte) -> Double): Double? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.maxOfOrNull(selector: (UShort) -> Double): Double? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.maxOfOrNull(selector: (UInt) -> Float): Float? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.maxOfOrNull(selector: (ULong) -> Float): Float? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.maxOfOrNull(selector: (UByte) -> Float): Float? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.maxOfOrNull(selector: (UShort) -> Float): Float? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UIntArray.maxOfOrNull(selector: (UInt) -> R): R? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i))) as java.lang.Comparable;
               if (maxValue.compareTo(v) < 0) {
                  maxValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ULongArray.maxOfOrNull(selector: (ULong) -> R): R? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i))) as java.lang.Comparable;
               if (maxValue.compareTo(v) < 0) {
                  maxValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UByteArray.maxOfOrNull(selector: (UByte) -> R): R? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i))) as java.lang.Comparable;
               if (maxValue.compareTo(v) < 0) {
                  maxValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UShortArray.maxOfOrNull(selector: (UShort) -> R): R? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i))) as java.lang.Comparable;
               if (maxValue.compareTo(v) < 0) {
                  maxValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.maxOfWith(comparator: Comparator<in R>, selector: (UInt) -> R): R {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i)));
               if (comparator.compare(maxValue, v) < 0) {
                  maxValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.maxOfWith(comparator: Comparator<in R>, selector: (ULong) -> R): R {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i)));
               if (comparator.compare(maxValue, v) < 0) {
                  maxValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.maxOfWith(comparator: Comparator<in R>, selector: (UByte) -> R): R {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i)));
               if (comparator.compare(maxValue, v) < 0) {
                  maxValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.maxOfWith(comparator: Comparator<in R>, selector: (UShort) -> R): R {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i)));
               if (comparator.compare(maxValue, v) < 0) {
                  maxValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.maxOfWithOrNull(comparator: Comparator<in R>, selector: (UInt) -> R): R? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i)));
               if (comparator.compare(maxValue, v) < 0) {
                  maxValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.maxOfWithOrNull(comparator: Comparator<in R>, selector: (ULong) -> R): R? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i)));
               if (comparator.compare(maxValue, v) < 0) {
                  maxValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.maxOfWithOrNull(comparator: Comparator<in R>, selector: (UByte) -> R): R? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i)));
               if (comparator.compare(maxValue, v) < 0) {
                  maxValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.maxOfWithOrNull(comparator: Comparator<in R>, selector: (UShort) -> R): R? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i)));
               if (comparator.compare(maxValue, v) < 0) {
                  maxValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)maxValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.maxOrNull(): UInt? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var max: Int = UIntArray.get-pVg5ArA(var0, 0);
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var3) {
            while (true) {
               val e: Int = UIntArray.get-pVg5ArA(var0, i);
               if (Integer.compareUnsigned(max, e) < 0) {
                  max = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return UInt.box-impl(max);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.maxOrNull(): ULong? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var max: Long = ULongArray.get-s-VKNKU(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Long = ULongArray.get-s-VKNKU(var0, i);
               if (java.lang.Long.compareUnsigned(max, e) < 0) {
                  max = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return ULong.box-impl(max);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.maxOrNull(): UByte? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var max: Byte = UByteArray.get-w2LRezQ(var0, 0);
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var3) {
            while (true) {
               val e: Byte = UByteArray.get-w2LRezQ(var0, i);
               if (Intrinsics.compare(max and 255, e and 255) < 0) {
                  max = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return UByte.box-impl(max);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.maxOrNull(): UShort? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var max: Short = UShortArray.get-Mh2AYeg(var0, 0);
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var3) {
            while (true) {
               val e: Short = UShortArray.get-Mh2AYeg(var0, i);
               if (Intrinsics.compare(max and '\uffff', e and '\uffff') < 0) {
                  max = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return UShort.box-impl(max);
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxWithOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.maxWith(comparator: Comparator<in UInt>): UInt {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var max: Int = UIntArray.get-pVg5ArA(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Int = UIntArray.get-pVg5ArA(var0, i);
               if (comparator.compare(UInt.box-impl(max), UInt.box-impl(e)) < 0) {
                  max = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxWithOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.maxWith(comparator: Comparator<in ULong>): ULong {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var max: Long = ULongArray.get-s-VKNKU(var0, 0);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val e: Long = ULongArray.get-s-VKNKU(var0, i);
               if (comparator.compare(ULong.box-impl(max), ULong.box-impl(e)) < 0) {
                  max = e;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxWithOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.maxWith(comparator: Comparator<in UByte>): UByte {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var max: Byte = UByteArray.get-w2LRezQ(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Byte = UByteArray.get-w2LRezQ(var0, i);
               if (comparator.compare(UByte.box-impl(max), UByte.box-impl(e)) < 0) {
                  max = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxWithOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.maxWith(comparator: Comparator<in UShort>): UShort {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var max: Short = UShortArray.get-Mh2AYeg(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Short = UShortArray.get-Mh2AYeg(var0, i);
               if (comparator.compare(UShort.box-impl(max), UShort.box-impl(e)) < 0) {
                  max = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.maxWithOrNull(comparator: Comparator<in UInt>): UInt? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var max: Int = UIntArray.get-pVg5ArA(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Int = UIntArray.get-pVg5ArA(var0, i);
               if (comparator.compare(UInt.box-impl(max), UInt.box-impl(e)) < 0) {
                  max = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return UInt.box-impl(max);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.maxWithOrNull(comparator: Comparator<in ULong>): ULong? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var max: Long = ULongArray.get-s-VKNKU(var0, 0);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val e: Long = ULongArray.get-s-VKNKU(var0, i);
               if (comparator.compare(ULong.box-impl(max), ULong.box-impl(e)) < 0) {
                  max = e;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return ULong.box-impl(max);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.maxWithOrNull(comparator: Comparator<in UByte>): UByte? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var max: Byte = UByteArray.get-w2LRezQ(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Byte = UByteArray.get-w2LRezQ(var0, i);
               if (comparator.compare(UByte.box-impl(max), UByte.box-impl(e)) < 0) {
                  max = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return UByte.box-impl(max);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.maxWithOrNull(comparator: Comparator<in UShort>): UShort? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var max: Short = UShortArray.get-Mh2AYeg(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Short = UShortArray.get-Mh2AYeg(var0, i);
               if (comparator.compare(UShort.box-impl(max), UShort.box-impl(e)) < 0) {
                  max = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return UShort.box-impl(max);
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.min(): UInt {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var min: Int = UIntArray.get-pVg5ArA(var0, 0);
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var3) {
            while (true) {
               val e: Int = UIntArray.get-pVg5ArA(var0, i);
               if (Integer.compareUnsigned(min, e) > 0) {
                  min = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.min(): ULong {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var min: Long = ULongArray.get-s-VKNKU(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Long = ULongArray.get-s-VKNKU(var0, i);
               if (java.lang.Long.compareUnsigned(min, e) > 0) {
                  min = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.min(): UByte {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var min: Byte = UByteArray.get-w2LRezQ(var0, 0);
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var3) {
            while (true) {
               val e: Byte = UByteArray.get-w2LRezQ(var0, i);
               if (Intrinsics.compare(min and 255, e and 255) > 0) {
                  min = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.min(): UShort {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var min: Short = UShortArray.get-Mh2AYeg(var0, 0);
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var3) {
            while (true) {
               val e: Short = UShortArray.get-Mh2AYeg(var0, i);
               if (Intrinsics.compare(min and '\uffff', e and '\uffff') > 0) {
                  min = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow-U")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UIntArray.minBy(selector: (UInt) -> R): UInt {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minElem: Int = UIntArray.get-pVg5ArA(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(UInt.box-impl(minElem)) as java.lang.Comparable;
            var var8: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Int = UIntArray.get-pVg5ArA(var0, var8);
                  val v: java.lang.Comparable = selector.invoke(UInt.box-impl(e)) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (var8 == lastIndex) {
                     break;
                  }

                  var8++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow-U")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ULongArray.minBy(selector: (ULong) -> R): ULong {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minElem: Long = ULongArray.get-s-VKNKU(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(ULong.box-impl(minElem)) as java.lang.Comparable;
            var var10: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Long = ULongArray.get-s-VKNKU(var0, var10);
                  val v: java.lang.Comparable = selector.invoke(ULong.box-impl(e)) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (var10 == lastIndex) {
                     break;
                  }

                  var10++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow-U")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UByteArray.minBy(selector: (UByte) -> R): UByte {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minElem: Byte = UByteArray.get-w2LRezQ(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(UByte.box-impl(minElem)) as java.lang.Comparable;
            var var8: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Byte = UByteArray.get-w2LRezQ(var0, var8);
                  val v: java.lang.Comparable = selector.invoke(UByte.box-impl(e)) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (var8 == lastIndex) {
                     break;
                  }

                  var8++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow-U")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UShortArray.minBy(selector: (UShort) -> R): UShort {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minElem: Short = UShortArray.get-Mh2AYeg(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(UShort.box-impl(minElem)) as java.lang.Comparable;
            var var8: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Short = UShortArray.get-Mh2AYeg(var0, var8);
                  val v: java.lang.Comparable = selector.invoke(UShort.box-impl(e)) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (var8 == lastIndex) {
                     break;
                  }

                  var8++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UIntArray.minByOrNull(selector: (UInt) -> R): UInt? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minElem: Int = UIntArray.get-pVg5ArA(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return UInt.box-impl(minElem);
         } else {
            var minValue: java.lang.Comparable = selector.invoke(UInt.box-impl(minElem)) as java.lang.Comparable;
            var var8: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Int = UIntArray.get-pVg5ArA(var0, var8);
                  val v: java.lang.Comparable = selector.invoke(UInt.box-impl(e)) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (var8 == lastIndex) {
                     break;
                  }

                  var8++;
               }
            }

            return UInt.box-impl(minElem);
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ULongArray.minByOrNull(selector: (ULong) -> R): ULong? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minElem: Long = ULongArray.get-s-VKNKU(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return ULong.box-impl(minElem);
         } else {
            var minValue: java.lang.Comparable = selector.invoke(ULong.box-impl(minElem)) as java.lang.Comparable;
            var var10: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Long = ULongArray.get-s-VKNKU(var0, var10);
                  val v: java.lang.Comparable = selector.invoke(ULong.box-impl(e)) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (var10 == lastIndex) {
                     break;
                  }

                  var10++;
               }
            }

            return ULong.box-impl(minElem);
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UByteArray.minByOrNull(selector: (UByte) -> R): UByte? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minElem: Byte = UByteArray.get-w2LRezQ(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return UByte.box-impl(minElem);
         } else {
            var minValue: java.lang.Comparable = selector.invoke(UByte.box-impl(minElem)) as java.lang.Comparable;
            var var8: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Byte = UByteArray.get-w2LRezQ(var0, var8);
                  val v: java.lang.Comparable = selector.invoke(UByte.box-impl(e)) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (var8 == lastIndex) {
                     break;
                  }

                  var8++;
               }
            }

            return UByte.box-impl(minElem);
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UShortArray.minByOrNull(selector: (UShort) -> R): UShort? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minElem: Short = UShortArray.get-Mh2AYeg(var0, 0);
         val lastIndex: Int = ArraysKt.getLastIndex(var0);
         if (lastIndex == 0) {
            return UShort.box-impl(minElem);
         } else {
            var minValue: java.lang.Comparable = selector.invoke(UShort.box-impl(minElem)) as java.lang.Comparable;
            var var8: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Short = UShortArray.get-Mh2AYeg(var0, var8);
                  val v: java.lang.Comparable = selector.invoke(UShort.box-impl(e)) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (var8 == lastIndex) {
                     break;
                  }

                  var8++;
               }
            }

            return UShort.box-impl(minElem);
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.minOf(selector: (UInt) -> Double): Double {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.minOf(selector: (ULong) -> Double): Double {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.minOf(selector: (UByte) -> Double): Double {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.minOf(selector: (UShort) -> Double): Double {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.minOf(selector: (UInt) -> Float): Float {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.minOf(selector: (ULong) -> Float): Float {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.minOf(selector: (UByte) -> Float): Float {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.minOf(selector: (UShort) -> Float): Float {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UIntArray.minOf(selector: (UInt) -> R): R {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i))) as java.lang.Comparable;
               if (minValue.compareTo(v) > 0) {
                  minValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ULongArray.minOf(selector: (ULong) -> R): R {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i))) as java.lang.Comparable;
               if (minValue.compareTo(v) > 0) {
                  minValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UByteArray.minOf(selector: (UByte) -> R): R {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i))) as java.lang.Comparable;
               if (minValue.compareTo(v) > 0) {
                  minValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UShortArray.minOf(selector: (UShort) -> R): R {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i))) as java.lang.Comparable;
               if (minValue.compareTo(v) > 0) {
                  minValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.minOfOrNull(selector: (UInt) -> Double): Double? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.minOfOrNull(selector: (ULong) -> Double): Double? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.minOfOrNull(selector: (UByte) -> Double): Double? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.minOfOrNull(selector: (UShort) -> Double): Double? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0))) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i))) as java.lang.Number).doubleValue());
               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.minOfOrNull(selector: (UInt) -> Float): Float? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.minOfOrNull(selector: (ULong) -> Float): Float? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.minOfOrNull(selector: (UByte) -> Float): Float? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.minOfOrNull(selector: (UShort) -> Float): Float? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0))) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i))) as java.lang.Number).floatValue());
               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UIntArray.minOfOrNull(selector: (UInt) -> R): R? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i))) as java.lang.Comparable;
               if (minValue.compareTo(v) > 0) {
                  minValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ULongArray.minOfOrNull(selector: (ULong) -> R): R? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i))) as java.lang.Comparable;
               if (minValue.compareTo(v) > 0) {
                  minValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UByteArray.minOfOrNull(selector: (UByte) -> R): R? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i))) as java.lang.Comparable;
               if (minValue.compareTo(v) > 0) {
                  minValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> UShortArray.minOfOrNull(selector: (UShort) -> R): R? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0))) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i))) as java.lang.Comparable;
               if (minValue.compareTo(v) > 0) {
                  minValue = v;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.minOfWith(comparator: Comparator<in R>, selector: (UInt) -> R): R {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i)));
               if (comparator.compare(minValue, v) > 0) {
                  minValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.minOfWith(comparator: Comparator<in R>, selector: (ULong) -> R): R {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i)));
               if (comparator.compare(minValue, v) > 0) {
                  minValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.minOfWith(comparator: Comparator<in R>, selector: (UByte) -> R): R {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i)));
               if (comparator.compare(minValue, v) > 0) {
                  minValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.minOfWith(comparator: Comparator<in R>, selector: (UShort) -> R): R {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i)));
               if (comparator.compare(minValue, v) > 0) {
                  minValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.minOfWithOrNull(comparator: Comparator<in R>, selector: (UInt) -> R): R? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: Any = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i)));
               if (comparator.compare(minValue, v) > 0) {
                  minValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.minOfWithOrNull(comparator: Comparator<in R>, selector: (ULong) -> R): R? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: Any = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i)));
               if (comparator.compare(minValue, v) > 0) {
                  minValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.minOfWithOrNull(comparator: Comparator<in R>, selector: (UByte) -> R): R? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: Any = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i)));
               if (comparator.compare(minValue, v) > 0) {
                  minValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.minOfWithOrNull(comparator: Comparator<in R>, selector: (UShort) -> R): R? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var minValue: Any = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, 0)));
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i)));
               if (comparator.compare(minValue, v) > 0) {
                  minValue = v;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return (R)minValue;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.minOrNull(): UInt? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var min: Int = UIntArray.get-pVg5ArA(var0, 0);
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var3) {
            while (true) {
               val e: Int = UIntArray.get-pVg5ArA(var0, i);
               if (Integer.compareUnsigned(min, e) > 0) {
                  min = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return UInt.box-impl(min);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.minOrNull(): ULong? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var min: Long = ULongArray.get-s-VKNKU(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Long = ULongArray.get-s-VKNKU(var0, i);
               if (java.lang.Long.compareUnsigned(min, e) > 0) {
                  min = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return ULong.box-impl(min);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.minOrNull(): UByte? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var min: Byte = UByteArray.get-w2LRezQ(var0, 0);
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var3) {
            while (true) {
               val e: Byte = UByteArray.get-w2LRezQ(var0, i);
               if (Intrinsics.compare(min and 255, e and 255) > 0) {
                  min = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return UByte.box-impl(min);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.minOrNull(): UShort? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var min: Short = UShortArray.get-Mh2AYeg(var0, 0);
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var3) {
            while (true) {
               val e: Short = UShortArray.get-Mh2AYeg(var0, i);
               if (Intrinsics.compare(min and '\uffff', e and '\uffff') > 0) {
                  min = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return UShort.box-impl(min);
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minWithOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.minWith(comparator: Comparator<in UInt>): UInt {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var min: Int = UIntArray.get-pVg5ArA(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Int = UIntArray.get-pVg5ArA(var0, i);
               if (comparator.compare(UInt.box-impl(min), UInt.box-impl(e)) > 0) {
                  min = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minWithOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.minWith(comparator: Comparator<in ULong>): ULong {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var min: Long = ULongArray.get-s-VKNKU(var0, 0);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val e: Long = ULongArray.get-s-VKNKU(var0, i);
               if (comparator.compare(ULong.box-impl(min), ULong.box-impl(e)) > 0) {
                  min = e;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minWithOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.minWith(comparator: Comparator<in UByte>): UByte {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var min: Byte = UByteArray.get-w2LRezQ(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Byte = UByteArray.get-w2LRezQ(var0, i);
               if (comparator.compare(UByte.box-impl(min), UByte.box-impl(e)) > 0) {
                  min = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minWithOrThrow-U")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.minWith(comparator: Comparator<in UShort>): UShort {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new NoSuchElementException();
      } else {
         var min: Short = UShortArray.get-Mh2AYeg(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Short = UShortArray.get-Mh2AYeg(var0, i);
               if (comparator.compare(UShort.box-impl(min), UShort.box-impl(e)) > 0) {
                  min = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UIntArray.minWithOrNull(comparator: Comparator<in UInt>): UInt? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var min: Int = UIntArray.get-pVg5ArA(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Int = UIntArray.get-pVg5ArA(var0, i);
               if (comparator.compare(UInt.box-impl(min), UInt.box-impl(e)) > 0) {
                  min = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return UInt.box-impl(min);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun ULongArray.minWithOrNull(comparator: Comparator<in ULong>): ULong? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var min: Long = ULongArray.get-s-VKNKU(var0, 0);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               val e: Long = ULongArray.get-s-VKNKU(var0, i);
               if (comparator.compare(ULong.box-impl(min), ULong.box-impl(e)) > 0) {
                  min = e;
               }

               if (i == var5) {
                  break;
               }

               i++;
            }
         }

         return ULong.box-impl(min);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UByteArray.minWithOrNull(comparator: Comparator<in UByte>): UByte? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var min: Byte = UByteArray.get-w2LRezQ(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Byte = UByteArray.get-w2LRezQ(var0, i);
               if (comparator.compare(UByte.box-impl(min), UByte.box-impl(e)) > 0) {
                  min = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return UByte.box-impl(min);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public fun UShortArray.minWithOrNull(comparator: Comparator<in UShort>): UShort? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var min: Short = UShortArray.get-Mh2AYeg(var0, 0);
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               val e: Short = UShortArray.get-Mh2AYeg(var0, i);
               if (comparator.compare(UShort.box-impl(min), UShort.box-impl(e)) > 0) {
                  min = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return UShort.box-impl(min);
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.none(): Boolean {
      return UIntArray.isEmpty-impl(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.none(): Boolean {
      return ULongArray.isEmpty-impl(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.none(): Boolean {
      return UByteArray.isEmpty-impl(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.none(): Boolean {
      return UShortArray.isEmpty-impl(var0);
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.none(predicate: (UInt) -> Boolean): Boolean {
      var var2: Int = 0;

      for (int var3 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$none$0); var2 < var3; var2++) {
         if (predicate.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var2))) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.none(predicate: (ULong) -> Boolean): Boolean {
      var var2: Int = 0;

      for (int var3 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$none$0); var2 < var3; var2++) {
         if (predicate.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var2))) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.none(predicate: (UByte) -> Boolean): Boolean {
      var var2: Int = 0;

      for (int var3 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$none$0); var2 < var3; var2++) {
         if (predicate.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var2))) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.none(predicate: (UShort) -> Boolean): Boolean {
      var var2: Int = 0;

      for (int var3 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$none$0); var2 < var3; var2++) {
         if (predicate.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var2))) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.onEach(action: (UInt) -> Unit): UIntArray {
      val `$this$onEach_jgv0xPQ_u24lambda_u240`: IntArray = var0;
      var var5: Int = 0;

      for (int var6 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$onEach$0); var5 < var6; var5++) {
         action.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(`$this$onEach_jgv0xPQ_u24lambda_u240`, var5)));
      }

      return var0;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.onEach(action: (ULong) -> Unit): ULongArray {
      val `$this$onEach_MShoTSo_u24lambda_u240`: LongArray = var0;
      var var5: Int = 0;

      for (int var6 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$onEach$0); var5 < var6; var5++) {
         action.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(`$this$onEach_MShoTSo_u24lambda_u240`, var5)));
      }

      return var0;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.onEach(action: (UByte) -> Unit): UByteArray {
      val `$this$onEach_JOV_ifY_u24lambda_u240`: ByteArray = var0;
      var var5: Int = 0;

      for (int var6 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$onEach$0); var5 < var6; var5++) {
         action.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(`$this$onEach_JOV_ifY_u24lambda_u240`, var5)));
      }

      return var0;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.onEach(action: (UShort) -> Unit): UShortArray {
      val `$this$onEach_xTcfx_M_u24lambda_u240`: ShortArray = var0;
      var var5: Int = 0;

      for (int var6 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$onEach$0); var5 < var6; var5++) {
         action.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(`$this$onEach_xTcfx_M_u24lambda_u240`, var5)));
      }

      return var0;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.onEachIndexed(action: (Int, UInt) -> Unit): UIntArray {
      val var5: IntArray = var0;
      var var6: Int = 0;
      var var7: Int = 0;

      for (int var8 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$onEachIndexed$0); var7 < var8; var7++) {
         action.invoke(var6++, UInt.box-impl(UIntArray.get-pVg5ArA(var5, var7)));
      }

      return var0;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.onEachIndexed(action: (Int, ULong) -> Unit): ULongArray {
      val var5: LongArray = var0;
      var var6: Int = 0;
      var var7: Int = 0;

      for (int var8 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$onEachIndexed$0); var7 < var8; var7++) {
         action.invoke(var6++, ULong.box-impl(ULongArray.get-s-VKNKU(var5, var7)));
      }

      return var0;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.onEachIndexed(action: (Int, UByte) -> Unit): UByteArray {
      val var5: ByteArray = var0;
      var var6: Int = 0;
      var var7: Int = 0;

      for (int var8 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$onEachIndexed$0); var7 < var8; var7++) {
         action.invoke(var6++, UByte.box-impl(UByteArray.get-w2LRezQ(var5, var7)));
      }

      return var0;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.onEachIndexed(action: (Int, UShort) -> Unit): UShortArray {
      val var5: ShortArray = var0;
      var var6: Int = 0;
      var var7: Int = 0;

      for (int var8 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$onEachIndexed$0); var7 < var8; var7++) {
         action.invoke(var6++, UShort.box-impl(UShortArray.get-Mh2AYeg(var5, var7)));
      }

      return var0;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.reduce(operation: (UInt, UInt) -> UInt): UInt {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Int = UIntArray.get-pVg5ArA(var0, 0);
         var index: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               accumulator = (operation.invoke(UInt.box-impl(accumulator), UInt.box-impl(UIntArray.get-pVg5ArA(var0, index))) as UInt).unbox-impl();
               if (index == var4) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.reduce(operation: (ULong, ULong) -> ULong): ULong {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Long = ULongArray.get-s-VKNKU(var0, 0);
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(ULong.box-impl(accumulator), ULong.box-impl(ULongArray.get-s-VKNKU(var0, index))) as ULong).unbox-impl();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.reduce(operation: (UByte, UByte) -> UByte): UByte {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Byte = UByteArray.get-w2LRezQ(var0, 0);
         var index: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               accumulator = (operation.invoke(UByte.box-impl(accumulator), UByte.box-impl(UByteArray.get-w2LRezQ(var0, index))) as UByte).unbox-impl();
               if (index == var4) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.reduce(operation: (UShort, UShort) -> UShort): UShort {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Short = UShortArray.get-Mh2AYeg(var0, 0);
         var index: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               accumulator = (operation.invoke(UShort.box-impl(accumulator), UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index))) as UShort).unbox-impl();
               if (index == var4) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.reduceIndexed(operation: (Int, UInt, UInt) -> UInt): UInt {
      if (UIntArray.isEmpty-impl(var0)) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Int = UIntArray.get-pVg5ArA(var0, 0);
         var index: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               accumulator = (operation.invoke(index, UInt.box-impl(accumulator), UInt.box-impl(UIntArray.get-pVg5ArA(var0, index))) as UInt).unbox-impl();
               if (index == var4) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.reduceIndexed(operation: (Int, ULong, ULong) -> ULong): ULong {
      if (ULongArray.isEmpty-impl(var0)) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Long = ULongArray.get-s-VKNKU(var0, 0);
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(index, ULong.box-impl(accumulator), ULong.box-impl(ULongArray.get-s-VKNKU(var0, index))) as ULong).unbox-impl();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.reduceIndexed(operation: (Int, UByte, UByte) -> UByte): UByte {
      if (UByteArray.isEmpty-impl(var0)) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Byte = UByteArray.get-w2LRezQ(var0, 0);
         var index: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               accumulator = (operation.invoke(index, UByte.box-impl(accumulator), UByte.box-impl(UByteArray.get-w2LRezQ(var0, index))) as UByte).unbox-impl();
               if (index == var4) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.reduceIndexed(operation: (Int, UShort, UShort) -> UShort): UShort {
      if (UShortArray.isEmpty-impl(var0)) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Short = UShortArray.get-Mh2AYeg(var0, 0);
         var index: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               accumulator = (operation.invoke(index, UShort.box-impl(accumulator), UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index))) as UShort)
                  .unbox-impl();
               if (index == var4) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.reduceIndexedOrNull(operation: (Int, UInt, UInt) -> UInt): UInt? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var accumulator: Int = UIntArray.get-pVg5ArA(var0, 0);
         var index: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               accumulator = (operation.invoke(index, UInt.box-impl(accumulator), UInt.box-impl(UIntArray.get-pVg5ArA(var0, index))) as UInt).unbox-impl();
               if (index == var4) {
                  break;
               }

               index++;
            }
         }

         return UInt.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.reduceIndexedOrNull(operation: (Int, ULong, ULong) -> ULong): ULong? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var accumulator: Long = ULongArray.get-s-VKNKU(var0, 0);
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(index, ULong.box-impl(accumulator), ULong.box-impl(ULongArray.get-s-VKNKU(var0, index))) as ULong).unbox-impl();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return ULong.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.reduceIndexedOrNull(operation: (Int, UByte, UByte) -> UByte): UByte? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var accumulator: Byte = UByteArray.get-w2LRezQ(var0, 0);
         var index: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               accumulator = (operation.invoke(index, UByte.box-impl(accumulator), UByte.box-impl(UByteArray.get-w2LRezQ(var0, index))) as UByte).unbox-impl();
               if (index == var4) {
                  break;
               }

               index++;
            }
         }

         return UByte.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.reduceIndexedOrNull(operation: (Int, UShort, UShort) -> UShort): UShort? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var accumulator: Short = UShortArray.get-Mh2AYeg(var0, 0);
         var index: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               accumulator = (operation.invoke(index, UShort.box-impl(accumulator), UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index))) as UShort)
                  .unbox-impl();
               if (index == var4) {
                  break;
               }

               index++;
            }
         }

         return UShort.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.reduceOrNull(operation: (UInt, UInt) -> UInt): UInt? {
      if (UIntArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var accumulator: Int = UIntArray.get-pVg5ArA(var0, 0);
         var index: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               accumulator = (operation.invoke(UInt.box-impl(accumulator), UInt.box-impl(UIntArray.get-pVg5ArA(var0, index))) as UInt).unbox-impl();
               if (index == var4) {
                  break;
               }

               index++;
            }
         }

         return UInt.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.reduceOrNull(operation: (ULong, ULong) -> ULong): ULong? {
      if (ULongArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var accumulator: Long = ULongArray.get-s-VKNKU(var0, 0);
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(ULong.box-impl(accumulator), ULong.box-impl(ULongArray.get-s-VKNKU(var0, index))) as ULong).unbox-impl();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return ULong.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.reduceOrNull(operation: (UByte, UByte) -> UByte): UByte? {
      if (UByteArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var accumulator: Byte = UByteArray.get-w2LRezQ(var0, 0);
         var index: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               accumulator = (operation.invoke(UByte.box-impl(accumulator), UByte.box-impl(UByteArray.get-w2LRezQ(var0, index))) as UByte).unbox-impl();
               if (index == var4) {
                  break;
               }

               index++;
            }
         }

         return UByte.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.reduceOrNull(operation: (UShort, UShort) -> UShort): UShort? {
      if (UShortArray.isEmpty-impl(var0)) {
         return null;
      } else {
         var accumulator: Short = UShortArray.get-Mh2AYeg(var0, 0);
         var index: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(var0);
         if (1 <= var4) {
            while (true) {
               accumulator = (operation.invoke(UShort.box-impl(accumulator), UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index))) as UShort).unbox-impl();
               if (index == var4) {
                  break;
               }

               index++;
            }
         }

         return UShort.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.reduceRight(operation: (UInt, UInt) -> UInt): UInt {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Int = UIntArray.get-pVg5ArA(var0, index--);

         while (index >= 0) {
            accumulator = (operation.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, index--)), UInt.box-impl(accumulator)) as UInt).unbox-impl();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.reduceRight(operation: (ULong, ULong) -> ULong): ULong {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Long = ULongArray.get-s-VKNKU(var0, index--);

         while (index >= 0) {
            accumulator = (operation.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, index--)), ULong.box-impl(accumulator)) as ULong).unbox-impl();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.reduceRight(operation: (UByte, UByte) -> UByte): UByte {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Byte = UByteArray.get-w2LRezQ(var0, index--);

         while (index >= 0) {
            accumulator = (operation.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, index--)), UByte.box-impl(accumulator)) as UByte).unbox-impl();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.reduceRight(operation: (UShort, UShort) -> UShort): UShort {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Short = UShortArray.get-Mh2AYeg(var0, index--);

         while (index >= 0) {
            accumulator = (operation.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index--)), UShort.box-impl(accumulator)) as UShort).unbox-impl();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.reduceRightIndexed(operation: (Int, UInt, UInt) -> UInt): UInt {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Int;
         for (accumulator = UIntArray.get-pVg5ArA($v$c$kotlin-UIntArray$-$this$reduceRightIndexed$0, index--); index >= 0; index--) {
            accumulator = (operation.invoke(index, UInt.box-impl(UIntArray.get-pVg5ArA(var0, index)), UInt.box-impl(accumulator)) as UInt).unbox-impl();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.reduceRightIndexed(operation: (Int, ULong, ULong) -> ULong): ULong {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Long;
         for (accumulator = ULongArray.get-s-VKNKU($v$c$kotlin-ULongArray$-$this$reduceRightIndexed$0, index--); index >= 0; index--) {
            accumulator = (operation.invoke(index, ULong.box-impl(ULongArray.get-s-VKNKU(var0, index)), ULong.box-impl(accumulator)) as ULong).unbox-impl();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.reduceRightIndexed(operation: (Int, UByte, UByte) -> UByte): UByte {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Byte;
         for (accumulator = UByteArray.get-w2LRezQ($v$c$kotlin-UByteArray$-$this$reduceRightIndexed$0, index--); index >= 0; index--) {
            accumulator = (operation.invoke(index, UByte.box-impl(UByteArray.get-w2LRezQ(var0, index)), UByte.box-impl(accumulator)) as UByte).unbox-impl();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.reduceRightIndexed(operation: (Int, UShort, UShort) -> UShort): UShort {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Short;
         for (accumulator = UShortArray.get-Mh2AYeg($v$c$kotlin-UShortArray$-$this$reduceRightIndexed$0, index--); index >= 0; index--) {
            accumulator = (operation.invoke(index, UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index)), UShort.box-impl(accumulator)) as UShort).unbox-impl();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.reduceRightIndexedOrNull(operation: (Int, UInt, UInt) -> UInt): UInt? {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Int;
         for (accumulator = UIntArray.get-pVg5ArA($v$c$kotlin-UIntArray$-$this$reduceRightIndexedOrNull$0, index--); index >= 0; index--) {
            accumulator = (operation.invoke(index, UInt.box-impl(UIntArray.get-pVg5ArA(var0, index)), UInt.box-impl(accumulator)) as UInt).unbox-impl();
         }

         return UInt.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.reduceRightIndexedOrNull(operation: (Int, ULong, ULong) -> ULong): ULong? {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Long;
         for (accumulator = ULongArray.get-s-VKNKU($v$c$kotlin-ULongArray$-$this$reduceRightIndexedOrNull$0, index--); index >= 0; index--) {
            accumulator = (operation.invoke(index, ULong.box-impl(ULongArray.get-s-VKNKU(var0, index)), ULong.box-impl(accumulator)) as ULong).unbox-impl();
         }

         return ULong.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.reduceRightIndexedOrNull(operation: (Int, UByte, UByte) -> UByte): UByte? {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Byte;
         for (accumulator = UByteArray.get-w2LRezQ($v$c$kotlin-UByteArray$-$this$reduceRightIndexedOrNull$0, index--); index >= 0; index--) {
            accumulator = (operation.invoke(index, UByte.box-impl(UByteArray.get-w2LRezQ(var0, index)), UByte.box-impl(accumulator)) as UByte).unbox-impl();
         }

         return UByte.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.reduceRightIndexedOrNull(operation: (Int, UShort, UShort) -> UShort): UShort? {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Short;
         for (accumulator = UShortArray.get-Mh2AYeg($v$c$kotlin-UShortArray$-$this$reduceRightIndexedOrNull$0, index--); index >= 0; index--) {
            accumulator = (operation.invoke(index, UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index)), UShort.box-impl(accumulator)) as UShort).unbox-impl();
         }

         return UShort.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.reduceRightOrNull(operation: (UInt, UInt) -> UInt): UInt? {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Int = UIntArray.get-pVg5ArA(var0, index--);

         while (index >= 0) {
            accumulator = (operation.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, index--)), UInt.box-impl(accumulator)) as UInt).unbox-impl();
         }

         return UInt.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.reduceRightOrNull(operation: (ULong, ULong) -> ULong): ULong? {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Long = ULongArray.get-s-VKNKU(var0, index--);

         while (index >= 0) {
            accumulator = (operation.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, index--)), ULong.box-impl(accumulator)) as ULong).unbox-impl();
         }

         return ULong.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.reduceRightOrNull(operation: (UByte, UByte) -> UByte): UByte? {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Byte = UByteArray.get-w2LRezQ(var0, index--);

         while (index >= 0) {
            accumulator = (operation.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, index--)), UByte.box-impl(accumulator)) as UByte).unbox-impl();
         }

         return UByte.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.reduceRightOrNull(operation: (UShort, UShort) -> UShort): UShort? {
      var index: Int = ArraysKt.getLastIndex(var0);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Short = UShortArray.get-Mh2AYeg(var0, index--);

         while (index >= 0) {
            accumulator = (operation.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index--)), UShort.box-impl(accumulator)) as UShort).unbox-impl();
         }

         return UShort.box-impl(accumulator);
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.runningFold(initial: R, operation: (R, UInt) -> R): List<R> {
      if (UIntArray.isEmpty-impl(var0)) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(UIntArray.getSize-impl(var0) + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var8: Any = initial;
         var `$this$runningFold_zi1B2BA_u24lambda_u240`: Int = 0;

         for (int var9 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$runningFold$0);
            $this$runningFold_zi1B2BA_u24lambda_u240 < var9;
            $this$runningFold_zi1B2BA_u24lambda_u240++
         ) {
            var8 = operation.invoke(var8, UInt.box-impl(UIntArray.get-pVg5ArA(var0, `$this$runningFold_zi1B2BA_u24lambda_u240`)));
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.runningFold(initial: R, operation: (R, ULong) -> R): List<R> {
      if (ULongArray.isEmpty-impl(var0)) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(ULongArray.getSize-impl(var0) + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var9: Any = initial;
         var `$this$runningFold_A8wKCXQ_u24lambda_u240`: Int = 0;

         for (int var10 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$runningFold$0);
            $this$runningFold_A8wKCXQ_u24lambda_u240 < var10;
            $this$runningFold_A8wKCXQ_u24lambda_u240++
         ) {
            var9 = operation.invoke(var9, ULong.box-impl(ULongArray.get-s-VKNKU(var0, `$this$runningFold_A8wKCXQ_u24lambda_u240`)));
            result.add(var9);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.runningFold(initial: R, operation: (R, UByte) -> R): List<R> {
      if (UByteArray.isEmpty-impl(var0)) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(UByteArray.getSize-impl(var0) + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var8: Any = initial;
         var `$this$runningFold_yXmHNn8_u24lambda_u240`: Int = 0;

         for (int var9 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$runningFold$0);
            $this$runningFold_yXmHNn8_u24lambda_u240 < var9;
            $this$runningFold_yXmHNn8_u24lambda_u240++
         ) {
            var8 = operation.invoke(var8, UByte.box-impl(UByteArray.get-w2LRezQ(var0, `$this$runningFold_yXmHNn8_u24lambda_u240`)));
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.runningFold(initial: R, operation: (R, UShort) -> R): List<R> {
      if (UShortArray.isEmpty-impl(var0)) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(UShortArray.getSize-impl(var0) + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var8: Any = initial;
         var `$this$runningFold_zww5nb8_u24lambda_u240`: Int = 0;

         for (int var9 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$runningFold$0);
            $this$runningFold_zww5nb8_u24lambda_u240 < var9;
            $this$runningFold_zww5nb8_u24lambda_u240++
         ) {
            var8 = operation.invoke(var8, UShort.box-impl(UShortArray.get-Mh2AYeg(var0, `$this$runningFold_zww5nb8_u24lambda_u240`)));
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.runningFoldIndexed(initial: R, operation: (Int, R, UInt) -> R): List<R> {
      if (UIntArray.isEmpty-impl(var0)) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(UIntArray.getSize-impl(var0) + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var7: Any = initial;
         var index: Int = 0;

         for (int var8 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$runningFoldIndexed$0); index < var8; index++) {
            var7 = operation.invoke(index, var7, UInt.box-impl(UIntArray.get-pVg5ArA(var0, index)));
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.runningFoldIndexed(initial: R, operation: (Int, R, ULong) -> R): List<R> {
      if (ULongArray.isEmpty-impl(var0)) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(ULongArray.getSize-impl(var0) + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var7: Any = initial;
         var index: Int = 0;

         for (int var8 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$runningFoldIndexed$0); index < var8; index++) {
            var7 = operation.invoke(index, var7, ULong.box-impl(ULongArray.get-s-VKNKU(var0, index)));
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.runningFoldIndexed(initial: R, operation: (Int, R, UByte) -> R): List<R> {
      if (UByteArray.isEmpty-impl(var0)) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(UByteArray.getSize-impl(var0) + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var7: Any = initial;
         var index: Int = 0;

         for (int var8 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$runningFoldIndexed$0); index < var8; index++) {
            var7 = operation.invoke(index, var7, UByte.box-impl(UByteArray.get-w2LRezQ(var0, index)));
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.runningFoldIndexed(initial: R, operation: (Int, R, UShort) -> R): List<R> {
      if (UShortArray.isEmpty-impl(var0)) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(UShortArray.getSize-impl(var0) + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var7: Any = initial;
         var index: Int = 0;

         for (int var8 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$runningFoldIndexed$0); index < var8; index++) {
            var7 = operation.invoke(index, var7, UShort.box-impl(UShortArray.get-Mh2AYeg(var0, index)));
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.runningReduce(operation: (UInt, UInt) -> UInt): List<UInt> {
      if (UIntArray.isEmpty-impl(var0)) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Int = UIntArray.get-pVg5ArA(var0, 0);
         val index: ArrayList = new ArrayList(UIntArray.getSize-impl(var0));
         index.add(UInt.box-impl(var7));
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduce_WyvcNBI_u24lambda_u240 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$runningReduce$0);
            index < $this$runningReduce_WyvcNBI_u24lambda_u240;
            index++
         ) {
            var7 = (operation.invoke(UInt.box-impl(var7), UInt.box-impl(UIntArray.get-pVg5ArA(var0, var8))) as UInt).unbox-impl();
            result.add(UInt.box-impl(var7));
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.runningReduce(operation: (ULong, ULong) -> ULong): List<ULong> {
      if (ULongArray.isEmpty-impl(var0)) {
         return CollectionsKt.emptyList();
      } else {
         var var9: Long = ULongArray.get-s-VKNKU(var0, 0);
         val index: ArrayList = new ArrayList(ULongArray.getSize-impl(var0));
         index.add(ULong.box-impl(var9));
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduce_s8dVfGU_u24lambda_u240 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$runningReduce$0);
            index < $this$runningReduce_s8dVfGU_u24lambda_u240;
            index++
         ) {
            var9 = (operation.invoke(ULong.box-impl(var9), ULong.box-impl(ULongArray.get-s-VKNKU(var0, var8))) as ULong).unbox-impl();
            result.add(ULong.box-impl(var9));
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.runningReduce(operation: (UByte, UByte) -> UByte): List<UByte> {
      if (UByteArray.isEmpty-impl(var0)) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Byte = UByteArray.get-w2LRezQ(var0, 0);
         val index: ArrayList = new ArrayList(UByteArray.getSize-impl(var0));
         index.add(UByte.box-impl(var7));
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduce_ELGow60_u24lambda_u240 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$runningReduce$0);
            index < $this$runningReduce_ELGow60_u24lambda_u240;
            index++
         ) {
            var7 = (operation.invoke(UByte.box-impl(var7), UByte.box-impl(UByteArray.get-w2LRezQ(var0, var8))) as UByte).unbox-impl();
            result.add(UByte.box-impl(var7));
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.runningReduce(operation: (UShort, UShort) -> UShort): List<UShort> {
      if (UShortArray.isEmpty-impl(var0)) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Short = UShortArray.get-Mh2AYeg(var0, 0);
         val index: ArrayList = new ArrayList(UShortArray.getSize-impl(var0));
         index.add(UShort.box-impl(var7));
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduce_xzaTVY8_u24lambda_u240 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$runningReduce$0);
            index < $this$runningReduce_xzaTVY8_u24lambda_u240;
            index++
         ) {
            var7 = (operation.invoke(UShort.box-impl(var7), UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var8))) as UShort).unbox-impl();
            result.add(UShort.box-impl(var7));
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.runningReduceIndexed(operation: (Int, UInt, UInt) -> UInt): List<UInt> {
      if (UIntArray.isEmpty-impl(var0)) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Int = UIntArray.get-pVg5ArA(var0, 0);
         val index: ArrayList = new ArrayList(UIntArray.getSize-impl(var0));
         index.add(UInt.box-impl(var7));
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduceIndexed_D40WMg8_u24lambda_u240 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$runningReduceIndexed$0);
            index < $this$runningReduceIndexed_D40WMg8_u24lambda_u240;
            index++
         ) {
            var7 = (operation.invoke(var8, UInt.box-impl(var7), UInt.box-impl(UIntArray.get-pVg5ArA(var0, var8))) as UInt).unbox-impl();
            result.add(UInt.box-impl(var7));
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.runningReduceIndexed(operation: (Int, ULong, ULong) -> ULong): List<ULong> {
      if (ULongArray.isEmpty-impl(var0)) {
         return CollectionsKt.emptyList();
      } else {
         var var9: Long = ULongArray.get-s-VKNKU(var0, 0);
         val index: ArrayList = new ArrayList(ULongArray.getSize-impl(var0));
         index.add(ULong.box-impl(var9));
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduceIndexed_z1zDJgo_u24lambda_u240 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$runningReduceIndexed$0);
            index < $this$runningReduceIndexed_z1zDJgo_u24lambda_u240;
            index++
         ) {
            var9 = (operation.invoke(var8, ULong.box-impl(var9), ULong.box-impl(ULongArray.get-s-VKNKU(var0, var8))) as ULong).unbox-impl();
            result.add(ULong.box-impl(var9));
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.runningReduceIndexed(operation: (Int, UByte, UByte) -> UByte): List<UByte> {
      if (UByteArray.isEmpty-impl(var0)) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Byte = UByteArray.get-w2LRezQ(var0, 0);
         val index: ArrayList = new ArrayList(UByteArray.getSize-impl(var0));
         index.add(UByte.box-impl(var7));
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduceIndexed_EOyYB1Y_u24lambda_u240 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$runningReduceIndexed$0);
            index < $this$runningReduceIndexed_EOyYB1Y_u24lambda_u240;
            index++
         ) {
            var7 = (operation.invoke(var8, UByte.box-impl(var7), UByte.box-impl(UByteArray.get-w2LRezQ(var0, var8))) as UByte).unbox-impl();
            result.add(UByte.box-impl(var7));
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.runningReduceIndexed(operation: (Int, UShort, UShort) -> UShort): List<UShort> {
      if (UShortArray.isEmpty-impl(var0)) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Short = UShortArray.get-Mh2AYeg(var0, 0);
         val index: ArrayList = new ArrayList(UShortArray.getSize-impl(var0));
         index.add(UShort.box-impl(var7));
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduceIndexed_aLgx1Fo_u24lambda_u240 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$runningReduceIndexed$0);
            index < $this$runningReduceIndexed_aLgx1Fo_u24lambda_u240;
            index++
         ) {
            var7 = (operation.invoke(var8, UShort.box-impl(var7), UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var8))) as UShort).unbox-impl();
            result.add(UShort.box-impl(var7));
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.scan(initial: R, operation: (R, UInt) -> R): List<R> {
      val var3: IntArray = var0;
      val var10000: java.util.List;
      if (UIntArray.isEmpty-impl(var0)) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(UIntArray.getSize-impl(var0) + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var9: Any = initial;
         var var5: Int = 0;

         for (int var7 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$scan$0); var5 < var7; var5++) {
            var9 = operation.invoke(var9, UInt.box-impl(UIntArray.get-pVg5ArA(var3, var5)));
            var6.add(var9);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.scan(initial: R, operation: (R, ULong) -> R): List<R> {
      val var3: LongArray = var0;
      val var10000: java.util.List;
      if (ULongArray.isEmpty-impl(var0)) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(ULongArray.getSize-impl(var0) + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var10: Any = initial;
         var var5: Int = 0;

         for (int var7 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$scan$0); var5 < var7; var5++) {
            var10 = operation.invoke(var10, ULong.box-impl(ULongArray.get-s-VKNKU(var3, var5)));
            var6.add(var10);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.scan(initial: R, operation: (R, UByte) -> R): List<R> {
      val var3: ByteArray = var0;
      val var10000: java.util.List;
      if (UByteArray.isEmpty-impl(var0)) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(UByteArray.getSize-impl(var0) + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var9: Any = initial;
         var var5: Int = 0;

         for (int var7 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$scan$0); var5 < var7; var5++) {
            var9 = operation.invoke(var9, UByte.box-impl(UByteArray.get-w2LRezQ(var3, var5)));
            var6.add(var9);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.scan(initial: R, operation: (R, UShort) -> R): List<R> {
      val var3: ShortArray = var0;
      val var10000: java.util.List;
      if (UShortArray.isEmpty-impl(var0)) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(UShortArray.getSize-impl(var0) + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var9: Any = initial;
         var var5: Int = 0;

         for (int var7 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$scan$0); var5 < var7; var5++) {
            var9 = operation.invoke(var9, UShort.box-impl(UShortArray.get-Mh2AYeg(var3, var5)));
            var6.add(var9);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UIntArray.scanIndexed(initial: R, operation: (Int, R, UInt) -> R): List<R> {
      val var3: IntArray = var0;
      val var10000: java.util.List;
      if (UIntArray.isEmpty-impl(var0)) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(UIntArray.getSize-impl(var0) + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var8: Any = initial;
         var var5: Int = 0;

         for (int var7 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$scanIndexed$0); var5 < var7; var5++) {
            var8 = operation.invoke(var5, var8, UInt.box-impl(UIntArray.get-pVg5ArA(var3, var5)));
            var6.add(var8);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> ULongArray.scanIndexed(initial: R, operation: (Int, R, ULong) -> R): List<R> {
      val var3: LongArray = var0;
      val var10000: java.util.List;
      if (ULongArray.isEmpty-impl(var0)) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(ULongArray.getSize-impl(var0) + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var8: Any = initial;
         var var5: Int = 0;

         for (int var7 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$scanIndexed$0); var5 < var7; var5++) {
            var8 = operation.invoke(var5, var8, ULong.box-impl(ULongArray.get-s-VKNKU(var3, var5)));
            var6.add(var8);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UByteArray.scanIndexed(initial: R, operation: (Int, R, UByte) -> R): List<R> {
      val var3: ByteArray = var0;
      val var10000: java.util.List;
      if (UByteArray.isEmpty-impl(var0)) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(UByteArray.getSize-impl(var0) + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var8: Any = initial;
         var var5: Int = 0;

         for (int var7 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$scanIndexed$0); var5 < var7; var5++) {
            var8 = operation.invoke(var5, var8, UByte.box-impl(UByteArray.get-w2LRezQ(var3, var5)));
            var6.add(var8);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R> UShortArray.scanIndexed(initial: R, operation: (Int, R, UShort) -> R): List<R> {
      val var3: ShortArray = var0;
      val var10000: java.util.List;
      if (UShortArray.isEmpty-impl(var0)) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(UShortArray.getSize-impl(var0) + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var8: Any = initial;
         var var5: Int = 0;

         for (int var7 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$scanIndexed$0); var5 < var7; var5++) {
            var8 = operation.invoke(var5, var8, UShort.box-impl(UShortArray.get-Mh2AYeg(var3, var5)));
            var6.add(var8);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.sumBy(selector: (UInt) -> UInt): UInt {
      var sum: Int = 0;
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$sumBy$0); var3 < var4; var3++) {
         sum = UInt.constructor-impl(sum + (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var3))) as UInt).unbox-impl());
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.sumBy(selector: (ULong) -> UInt): UInt {
      var sum: Int = 0;
      var var3: Int = 0;

      for (int var4 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$sumBy$0); var3 < var4; var3++) {
         sum = UInt.constructor-impl(sum + (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var3))) as UInt).unbox-impl());
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.sumBy(selector: (UByte) -> UInt): UInt {
      var sum: Int = 0;
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$sumBy$0); var3 < var4; var3++) {
         sum = UInt.constructor-impl(sum + (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var3))) as UInt).unbox-impl());
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.sumBy(selector: (UShort) -> UInt): UInt {
      var sum: Int = 0;
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$sumBy$0); var3 < var4; var3++) {
         sum = UInt.constructor-impl(sum + (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var3))) as UInt).unbox-impl());
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.sumByDouble(selector: (UInt) -> Double): Double {
      var sum: Double = 0.0;
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$sumByDouble$0); var4 < var5; var4++) {
         sum += (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var4))) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.sumByDouble(selector: (ULong) -> Double): Double {
      var sum: Double = 0.0;
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$sumByDouble$0); var4 < var5; var4++) {
         sum += (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var4))) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.sumByDouble(selector: (UByte) -> Double): Double {
      var sum: Double = 0.0;
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$sumByDouble$0); var4 < var5; var4++) {
         sum += (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var4))) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.sumByDouble(selector: (UShort) -> Double): Double {
      var sum: Double = 0.0;
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$sumByDouble$0); var4 < var5; var4++) {
         sum += (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var4))) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.sumOf(selector: (UInt) -> Double): Double {
      var sum: Double = 0.0;
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$sumOf$0); var4 < var5; var4++) {
         sum += (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var4))) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.sumOf(selector: (ULong) -> Double): Double {
      var sum: Double = 0.0;
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$sumOf$0); var4 < var5; var4++) {
         sum += (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var4))) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.sumOf(selector: (UByte) -> Double): Double {
      var sum: Double = 0.0;
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$sumOf$0); var4 < var5; var4++) {
         sum += (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var4))) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.sumOf(selector: (UShort) -> Double): Double {
      var sum: Double = 0.0;
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$sumOf$0); var4 < var5; var4++) {
         sum += (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var4))) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.sumOf(selector: (UInt) -> Int): Int {
      var sum: Int = 0;
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$sumOf$0); var3 < var4; var3++) {
         sum += (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var3))) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.sumOf(selector: (ULong) -> Int): Int {
      var sum: Int = 0;
      var var3: Int = 0;

      for (int var4 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$sumOf$0); var3 < var4; var3++) {
         sum += (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var3))) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.sumOf(selector: (UByte) -> Int): Int {
      var sum: Int = 0;
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$sumOf$0); var3 < var4; var3++) {
         sum += (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var3))) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.sumOf(selector: (UShort) -> Int): Int {
      var sum: Int = 0;
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$sumOf$0); var3 < var4; var3++) {
         sum += (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var3))) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.sumOf(selector: (UInt) -> Long): Long {
      var sum: Long = 0L;
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$sumOf$0); var4 < var5; var4++) {
         sum += (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var4))) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.sumOf(selector: (ULong) -> Long): Long {
      var sum: Long = 0L;
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$sumOf$0); var4 < var5; var4++) {
         sum += (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var4))) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.sumOf(selector: (UByte) -> Long): Long {
      var sum: Long = 0L;
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$sumOf$0); var4 < var5; var4++) {
         sum += (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var4))) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.sumOf(selector: (UShort) -> Long): Long {
      var sum: Long = 0L;
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$sumOf$0); var4 < var5; var4++) {
         sum += (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var4))) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.sumOf(selector: (UInt) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);
      var var3: Int = 0;

      for (int var4 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$sumOf$0); var3 < var4; var3++) {
         sum = UInt.constructor-impl(sum + (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var3))) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.sumOf(selector: (ULong) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);
      var var3: Int = 0;

      for (int var4 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$sumOf$0); var3 < var4; var3++) {
         sum = UInt.constructor-impl(sum + (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var3))) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.sumOf(selector: (UByte) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$sumOf$0); var3 < var4; var3++) {
         sum = UInt.constructor-impl(sum + (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var3))) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.sumOf(selector: (UShort) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$sumOf$0); var3 < var4; var3++) {
         sum = UInt.constructor-impl(sum + (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var3))) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.sumOf(selector: (UInt) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);
      var var4: Int = 0;

      for (int var5 = UIntArray.getSize-impl($v$c$kotlin-UIntArray$-$this$sumOf$0); var4 < var5; var4++) {
         sum = ULong.constructor-impl(sum + (selector.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, var4))) as ULong).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.sumOf(selector: (ULong) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);
      var var4: Int = 0;

      for (int var5 = ULongArray.getSize-impl($v$c$kotlin-ULongArray$-$this$sumOf$0); var4 < var5; var4++) {
         sum = ULong.constructor-impl(sum + (selector.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, var4))) as ULong).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.sumOf(selector: (UByte) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);
      var var4: Int = 0;

      for (int var5 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$sumOf$0); var4 < var5; var4++) {
         sum = ULong.constructor-impl(sum + (selector.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, var4))) as ULong).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.sumOf(selector: (UShort) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);
      var var4: Int = 0;

      for (int var5 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$sumOf$0); var4 < var5; var4++) {
         sum = ULong.constructor-impl(sum + (selector.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, var4))) as ULong).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun <R> UIntArray.zip(other: Array<out R>): List<Pair<UInt, R>> {
      val var2: IntArray = var0;
      val var3: Int = Math.min(UIntArray.getSize-impl(var0), other.length);
      val var4: ArrayList = new ArrayList(var3);

      for (int var5 = 0; var5 < var3; var5++) {
         var4.add(TuplesKt.to(UInt.box-impl(UIntArray.get-pVg5ArA(var2, var5)), other[var5]));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun <R> ULongArray.zip(other: Array<out R>): List<Pair<ULong, R>> {
      val var2: LongArray = var0;
      val var3: Int = Math.min(ULongArray.getSize-impl(var0), other.length);
      val var4: ArrayList = new ArrayList(var3);

      for (int var5 = 0; var5 < var3; var5++) {
         var4.add(TuplesKt.to(ULong.box-impl(ULongArray.get-s-VKNKU(var2, var5)), other[var5]));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun <R> UByteArray.zip(other: Array<out R>): List<Pair<UByte, R>> {
      val var2: ByteArray = var0;
      val var3: Int = Math.min(UByteArray.getSize-impl(var0), other.length);
      val var4: ArrayList = new ArrayList(var3);

      for (int var5 = 0; var5 < var3; var5++) {
         var4.add(TuplesKt.to(UByte.box-impl(UByteArray.get-w2LRezQ(var2, var5)), other[var5]));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun <R> UShortArray.zip(other: Array<out R>): List<Pair<UShort, R>> {
      val var2: ShortArray = var0;
      val var3: Int = Math.min(UShortArray.getSize-impl(var0), other.length);
      val var4: ArrayList = new ArrayList(var3);

      for (int var5 = 0; var5 < var3; var5++) {
         var4.add(TuplesKt.to(UShort.box-impl(UShortArray.get-Mh2AYeg(var2, var5)), other[var5]));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, V> UIntArray.zip(other: Array<out R>, transform: (UInt, R) -> V): List<V> {
      val size: Int = Math.min(UIntArray.getSize-impl(var0), other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i)), other[i]));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, V> ULongArray.zip(other: Array<out R>, transform: (ULong, R) -> V): List<V> {
      val size: Int = Math.min(ULongArray.getSize-impl(var0), other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i)), other[i]));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, V> UByteArray.zip(other: Array<out R>, transform: (UByte, R) -> V): List<V> {
      val size: Int = Math.min(UByteArray.getSize-impl(var0), other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i)), other[i]));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, V> UShortArray.zip(other: Array<out R>, transform: (UShort, R) -> V): List<V> {
      val size: Int = Math.min(UShortArray.getSize-impl(var0), other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i)), other[i]));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun <R> UIntArray.zip(other: Iterable<R>): List<Pair<UInt, R>> {
      val var2: IntArray = var0;
      val var3: Int = UIntArray.getSize-impl(var0);
      val var4: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), var3));
      var var5: Int = 0;

      for (Object var7 : other) {
         if (var5 >= var3) {
            break;
         }

         var4.add(TuplesKt.to(UInt.box-impl(UIntArray.get-pVg5ArA(var2, var5++)), var7));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun <R> ULongArray.zip(other: Iterable<R>): List<Pair<ULong, R>> {
      val var2: LongArray = var0;
      val var3: Int = ULongArray.getSize-impl(var0);
      val var4: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), var3));
      var var5: Int = 0;

      for (Object var7 : other) {
         if (var5 >= var3) {
            break;
         }

         var4.add(TuplesKt.to(ULong.box-impl(ULongArray.get-s-VKNKU(var2, var5++)), var7));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun <R> UByteArray.zip(other: Iterable<R>): List<Pair<UByte, R>> {
      val var2: ByteArray = var0;
      val var3: Int = UByteArray.getSize-impl(var0);
      val var4: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), var3));
      var var5: Int = 0;

      for (Object var7 : other) {
         if (var5 >= var3) {
            break;
         }

         var4.add(TuplesKt.to(UByte.box-impl(UByteArray.get-w2LRezQ(var2, var5++)), var7));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun <R> UShortArray.zip(other: Iterable<R>): List<Pair<UShort, R>> {
      val var2: ShortArray = var0;
      val var3: Int = UShortArray.getSize-impl(var0);
      val var4: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), var3));
      var var5: Int = 0;

      for (Object var7 : other) {
         if (var5 >= var3) {
            break;
         }

         var4.add(TuplesKt.to(UShort.box-impl(UShortArray.get-Mh2AYeg(var2, var5++)), var7));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, V> UIntArray.zip(other: Iterable<R>, transform: (UInt, R) -> V): List<V> {
      val arraySize: Int = UIntArray.getSize-impl(var0);
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), arraySize));
      var i: Int = 0;

      for (Object element : other) {
         if (i >= arraySize) {
            break;
         }

         list.add(transform.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i++)), element));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, V> ULongArray.zip(other: Iterable<R>, transform: (ULong, R) -> V): List<V> {
      val arraySize: Int = ULongArray.getSize-impl(var0);
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), arraySize));
      var i: Int = 0;

      for (Object element : other) {
         if (i >= arraySize) {
            break;
         }

         list.add(transform.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i++)), element));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, V> UByteArray.zip(other: Iterable<R>, transform: (UByte, R) -> V): List<V> {
      val arraySize: Int = UByteArray.getSize-impl(var0);
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), arraySize));
      var i: Int = 0;

      for (Object element : other) {
         if (i >= arraySize) {
            break;
         }

         list.add(transform.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i++)), element));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <R, V> UShortArray.zip(other: Iterable<R>, transform: (UShort, R) -> V): List<V> {
      val arraySize: Int = UShortArray.getSize-impl(var0);
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), arraySize));
      var i: Int = 0;

      for (Object element : other) {
         if (i >= arraySize) {
            break;
         }

         list.add(transform.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i++)), element));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun UIntArray.zip(other: UIntArray): List<Pair<UInt, UInt>> {
      val var2: IntArray = var0;
      val var3: Int = Math.min(UIntArray.getSize-impl(var0), UIntArray.getSize-impl(var1));
      val var4: ArrayList = new ArrayList(var3);

      for (int var5 = 0; var5 < var3; var5++) {
         var4.add(TuplesKt.to(UInt.box-impl(UIntArray.get-pVg5ArA(var2, var5)), UInt.box-impl(UIntArray.get-pVg5ArA(var1, var5))));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun ULongArray.zip(other: ULongArray): List<Pair<ULong, ULong>> {
      val var2: LongArray = var0;
      val var3: Int = Math.min(ULongArray.getSize-impl(var0), ULongArray.getSize-impl(var1));
      val var4: ArrayList = new ArrayList(var3);

      for (int var5 = 0; var5 < var3; var5++) {
         var4.add(TuplesKt.to(ULong.box-impl(ULongArray.get-s-VKNKU(var2, var5)), ULong.box-impl(ULongArray.get-s-VKNKU(var1, var5))));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun UByteArray.zip(other: UByteArray): List<Pair<UByte, UByte>> {
      val var2: ByteArray = var0;
      val var3: Int = Math.min(UByteArray.getSize-impl(var0), UByteArray.getSize-impl(var1));
      val var4: ArrayList = new ArrayList(var3);

      for (int var5 = 0; var5 < var3; var5++) {
         var4.add(TuplesKt.to(UByte.box-impl(UByteArray.get-w2LRezQ(var2, var5)), UByte.box-impl(UByteArray.get-w2LRezQ(var1, var5))));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @JvmStatic
   public infix fun UShortArray.zip(other: UShortArray): List<Pair<UShort, UShort>> {
      val var2: ShortArray = var0;
      val var3: Int = Math.min(UShortArray.getSize-impl(var0), UShortArray.getSize-impl(var1));
      val var4: ArrayList = new ArrayList(var3);

      for (int var5 = 0; var5 < var3; var5++) {
         var4.add(TuplesKt.to(UShort.box-impl(UShortArray.get-Mh2AYeg(var2, var5)), UShort.box-impl(UShortArray.get-Mh2AYeg(var1, var5))));
      }

      return var4;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <V> UIntArray.zip(other: UIntArray, transform: (UInt, UInt) -> V): List<V> {
      val size: Int = Math.min(UIntArray.getSize-impl(var0), UIntArray.getSize-impl(var1));
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(var0, i)), UInt.box-impl(UIntArray.get-pVg5ArA(var1, i))));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <V> ULongArray.zip(other: ULongArray, transform: (ULong, ULong) -> V): List<V> {
      val size: Int = Math.min(ULongArray.getSize-impl(var0), ULongArray.getSize-impl(var1));
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(var0, i)), ULong.box-impl(ULongArray.get-s-VKNKU(var1, i))));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <V> UByteArray.zip(other: UByteArray, transform: (UByte, UByte) -> V): List<V> {
      val size: Int = Math.min(UByteArray.getSize-impl(var0), UByteArray.getSize-impl(var1));
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(var0, i)), UByte.box-impl(UByteArray.get-w2LRezQ(var1, i))));
      }

      return list;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun <V> UShortArray.zip(other: UShortArray, transform: (UShort, UShort) -> V): List<V> {
      val size: Int = Math.min(UShortArray.getSize-impl(var0), UShortArray.getSize-impl(var1));
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(var0, i)), UShort.box-impl(UShortArray.get-Mh2AYeg(var1, i))));
      }

      return list;
   }

   @JvmName(name = "sumOfUInt")
   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun Array<out UInt>.sum(): UInt {
      var sum: Int = 0;
      var var2: Int = 0;

      for (int var3 = $this$sum.length; var2 < var3; var2++) {
         sum = UInt.constructor-impl(sum + `$this$sum`[var2].unbox-impl());
      }

      return sum;
   }

   @JvmName(name = "sumOfULong")
   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun Array<out ULong>.sum(): ULong {
      var sum: Long = 0L;
      var var3: Int = 0;

      for (int var4 = $this$sum.length; var3 < var4; var3++) {
         sum = ULong.constructor-impl(sum + `$this$sum`[var3].unbox-impl());
      }

      return sum;
   }

   @JvmName(name = "sumOfUByte")
   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun Array<out UByte>.sum(): UInt {
      var sum: Int = 0;
      var var2: Int = 0;

      for (int var3 = $this$sum.length; var2 < var3; var2++) {
         sum = UInt.constructor-impl(sum + UInt.constructor-impl(`$this$sum`[var2].unbox-impl() and 255));
      }

      return sum;
   }

   @JvmName(name = "sumOfUShort")
   @SinceKotlin(version = "1.5")
   @JvmStatic
   public fun Array<out UShort>.sum(): UInt {
      var sum: Int = 0;
      var var2: Int = 0;

      for (int var3 = $this$sum.length; var2 < var3; var2++) {
         sum = UInt.constructor-impl(sum + UInt.constructor-impl(`$this$sum`[var2].unbox-impl() and '\uffff'));
      }

      return sum;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UIntArray.sum(): UInt {
      return UInt.constructor-impl(ArraysKt.sum(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun ULongArray.sum(): ULong {
      return ULong.constructor-impl(ArraysKt.sum(var0));
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UByteArray.sum(): UInt {
      val var1: ByteArray = var0;
      var var2: Int = UInt.constructor-impl(0);
      var var3: Int = 0;

      for (int var4 = UByteArray.getSize-impl($v$c$kotlin-UByteArray$-$this$sum$0); var3 < var4; var3++) {
         var2 = UInt.constructor-impl(var2 + UInt.constructor-impl(UByteArray.get-w2LRezQ(var1, var3) and 255));
      }

      return var2;
   }

   @SinceKotlin(version = "1.3")
   @ExperimentalUnsignedTypes
   @InlineOnly
   @JvmStatic
   public inline fun UShortArray.sum(): UInt {
      val var1: ShortArray = var0;
      var var2: Int = UInt.constructor-impl(0);
      var var3: Int = 0;

      for (int var4 = UShortArray.getSize-impl($v$c$kotlin-UShortArray$-$this$sum$0); var3 < var4; var3++) {
         var2 = UInt.constructor-impl(var2 + UInt.constructor-impl(UShortArray.get-Mh2AYeg(var1, var3) and '\uffff'));
      }

      return var2;
   }

   @JvmStatic
   fun `withIndex__ajY_9A$lambda$0$UArraysKt___UArraysKt`(var0: IntArray): java.util.Iterator {
      return UIntArray.iterator-impl(var0);
   }

   @JvmStatic
   fun `withIndex_QwZRm1k$lambda$0$UArraysKt___UArraysKt`(var0: LongArray): java.util.Iterator {
      return ULongArray.iterator-impl(var0);
   }

   @JvmStatic
   fun `withIndex_GBYM_sE$lambda$0$UArraysKt___UArraysKt`(var0: ByteArray): java.util.Iterator {
      return UByteArray.iterator-impl(var0);
   }

   @JvmStatic
   fun `withIndex_rL5Bavg$lambda$0$UArraysKt___UArraysKt`(var0: ShortArray): java.util.Iterator {
      return UShortArray.iterator-impl(var0);
   }

   open fun UArraysKt___UArraysKt() {
   }
}
