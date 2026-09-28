package kotlin.collections

import java.util.ArrayList
import java.util.Arrays
import java.util.Comparator
import java.util.HashSet
import java.util.LinkedHashMap
import java.util.LinkedHashSet
import java.util.NoSuchElementException
import kotlin.collections.ArraysKt___ArraysKt.asIterable..inlined.Iterable.3
import kotlin.collections.ArraysKt___ArraysKt.asIterable..inlined.Iterable.4
import kotlin.collections.ArraysKt___ArraysKt.asIterable..inlined.Iterable.5
import kotlin.collections.ArraysKt___ArraysKt.asIterable..inlined.Iterable.6
import kotlin.collections.ArraysKt___ArraysKt.asIterable..inlined.Iterable.7
import kotlin.collections.ArraysKt___ArraysKt.asIterable..inlined.Iterable.8
import kotlin.collections.ArraysKt___ArraysKt.asIterable..inlined.Iterable.9
import kotlin.comparisons.ComparisonsKt__ComparisonsKt.compareBy.2
import kotlin.comparisons.ComparisonsKt__ComparisonsKt.compareByDescending.1
import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.ArrayIteratorKt
import kotlin.jvm.internal.ArrayIteratorsKt
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.random.Random

@SourceDebugExtension(["SMAP\n_Arrays.kt\nKotlin\n*S Kotlin\n*F\n+ 1 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,25600:1\n13020#1,2:25601\n13030#1,2:25603\n1400#1,2:25605\n1408#1,2:25607\n1416#1,2:25609\n1424#1,2:25611\n1432#1,2:25613\n1440#1,2:25615\n1448#1,2:25617\n1456#1,2:25619\n1464#1,2:25621\n2443#1,5:25623\n2456#1,5:25628\n2469#1,5:25633\n2482#1,5:25638\n2495#1,5:25643\n2508#1,5:25648\n2521#1,5:25653\n2534#1,5:25658\n2547#1,5:25663\n4434#1,2:25669\n4444#1,2:25671\n4454#1,2:25673\n4464#1,2:25675\n4474#1,2:25677\n4484#1,2:25679\n4494#1,2:25681\n4504#1,2:25683\n4514#1,2:25685\n4101#1:25687\n13870#1,2:25688\n4102#1,2:25690\n13872#1:25692\n4104#1:25693\n4115#1:25694\n13880#1,2:25695\n4116#1,2:25697\n13882#1:25699\n4118#1:25700\n4129#1:25701\n13890#1,2:25702\n4130#1,2:25704\n13892#1:25706\n4132#1:25707\n4143#1:25708\n13900#1,2:25709\n4144#1,2:25711\n13902#1:25713\n4146#1:25714\n4157#1:25715\n13910#1,2:25716\n4158#1,2:25718\n13912#1:25720\n4160#1:25721\n4171#1:25722\n13920#1,2:25723\n4172#1,2:25725\n13922#1:25727\n4174#1:25728\n4185#1:25729\n13930#1,2:25730\n4186#1,2:25732\n13932#1:25734\n4188#1:25735\n4199#1:25736\n13940#1,2:25737\n4200#1,2:25739\n13942#1:25741\n4202#1:25742\n4213#1:25743\n13950#1,2:25744\n4214#1,2:25746\n13952#1:25748\n4216#1:25749\n13870#1,3:25750\n13880#1,3:25753\n13890#1,3:25756\n13900#1,3:25759\n13910#1,3:25762\n13920#1,3:25765\n13930#1,3:25768\n13940#1,3:25771\n13950#1,3:25774\n4234#1,2:25777\n4344#1,2:25779\n4354#1,2:25781\n4364#1,2:25783\n4374#1,2:25785\n4384#1,2:25787\n4394#1,2:25789\n4404#1,2:25791\n4414#1,2:25793\n4424#1,2:25795\n9584#1,4:25797\n9599#1,4:25801\n9614#1,4:25805\n9629#1,4:25809\n9644#1,4:25813\n9659#1,4:25817\n9674#1,4:25821\n9689#1,4:25825\n9704#1,4:25829\n9297#1,4:25833\n9313#1,4:25837\n9329#1,4:25841\n9345#1,4:25845\n9361#1,4:25849\n9377#1,4:25853\n9393#1,4:25857\n9409#1,4:25861\n9425#1,4:25865\n9441#1,4:25869\n9457#1,4:25873\n9473#1,4:25877\n9489#1,4:25881\n9505#1,4:25885\n9521#1,4:25889\n9537#1,4:25893\n9553#1,4:25897\n9569#1,4:25901\n9872#1,4:25905\n10890#1,5:25909\n10901#1,5:25914\n10912#1,5:25919\n10923#1,5:25924\n10934#1,5:25929\n10945#1,5:25934\n10956#1,5:25939\n10967#1,5:25944\n10978#1,5:25949\n10993#1,5:25954\n11234#1,3:25959\n11237#1,3:25969\n11251#1,3:25972\n11254#1,3:25982\n11268#1,3:25985\n11271#1,3:25995\n11285#1,3:25998\n11288#1,3:26008\n11302#1,3:26011\n11305#1,3:26021\n11319#1,3:26024\n11322#1,3:26034\n11336#1,3:26037\n11339#1,3:26047\n11353#1,3:26050\n11356#1,3:26060\n11370#1,3:26063\n11373#1,3:26073\n11388#1,3:26076\n11391#1,3:26086\n11406#1,3:26089\n11409#1,3:26099\n11424#1,3:26102\n11427#1,3:26112\n11442#1,3:26115\n11445#1,3:26125\n11460#1,3:26128\n11463#1,3:26138\n11478#1,3:26141\n11481#1,3:26151\n11496#1,3:26154\n11499#1,3:26164\n11514#1,3:26167\n11517#1,3:26177\n11532#1,3:26180\n11535#1,3:26190\n11896#1,3:26319\n11906#1,3:26322\n11916#1,3:26325\n11926#1,3:26328\n11936#1,3:26331\n11946#1,3:26334\n11956#1,3:26337\n11966#1,3:26340\n11976#1,3:26343\n11762#1,4:26346\n11775#1,4:26350\n11788#1,4:26354\n11801#1,4:26358\n11814#1,4:26362\n11827#1,4:26366\n11840#1,4:26370\n11853#1,4:26374\n11866#1,4:26378\n11751#1:26382\n13870#1,2:26383\n13872#1:26386\n11752#1:26387\n13870#1,3:26388\n11887#1:26391\n13805#1:26392\n13806#1:26394\n11888#1:26395\n13805#1,2:26396\n13870#1,3:26398\n13880#1,3:26401\n13890#1,3:26404\n13900#1,3:26407\n13910#1,3:26410\n13920#1,3:26413\n13930#1,3:26416\n13940#1,3:26419\n13950#1,3:26422\n21873#1,2:26425\n21875#1,6:26428\n22089#1,2:26434\n22091#1,6:26437\n24212#1,6:26443\n24228#1,6:26449\n24244#1,6:26455\n24260#1,6:26461\n24276#1,6:26467\n24292#1,6:26473\n24308#1,6:26479\n24324#1,6:26485\n24340#1,6:26491\n24446#1,8:26497\n24464#1,8:26505\n24482#1,8:26513\n24500#1,8:26521\n24518#1,8:26529\n24536#1,8:26537\n24554#1,8:26545\n24572#1,8:26553\n24590#1,8:26561\n24688#1,6:26569\n24704#1,6:26575\n24720#1,6:26581\n24736#1,6:26587\n24752#1,6:26593\n24768#1,6:26599\n24784#1,6:26605\n24800#1,6:26611\n1#2:25668\n1#2:26385\n1#2:26393\n1#2:26427\n1#2:26436\n382#3,7:25962\n382#3,7:25975\n382#3,7:25988\n382#3,7:26001\n382#3,7:26014\n382#3,7:26027\n382#3,7:26040\n382#3,7:26053\n382#3,7:26066\n382#3,7:26079\n382#3,7:26092\n382#3,7:26105\n382#3,7:26118\n382#3,7:26131\n382#3,7:26144\n382#3,7:26157\n382#3,7:26170\n382#3,7:26183\n382#3,7:26193\n382#3,7:26200\n382#3,7:26207\n382#3,7:26214\n382#3,7:26221\n382#3,7:26228\n382#3,7:26235\n382#3,7:26242\n382#3,7:26249\n382#3,7:26256\n382#3,7:26263\n382#3,7:26270\n382#3,7:26277\n382#3,7:26284\n382#3,7:26291\n382#3,7:26298\n382#3,7:26305\n382#3,7:26312\n*S KotlinDebug\n*F\n+ 1 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n647#1:25601,2\n656#1:25603,2\n950#1:25605,2\n960#1:25607,2\n970#1:25609,2\n980#1:25611,2\n990#1:25613,2\n1000#1:25615,2\n1010#1:25617,2\n1020#1:25619,2\n1030#1:25621,2\n1040#1:25623,5\n1050#1:25628,5\n1060#1:25633,5\n1070#1:25638,5\n1080#1:25643,5\n1090#1:25648,5\n1100#1:25653,5\n1110#1:25658,5\n1120#1:25663,5\n3919#1:25669,2\n3928#1:25671,2\n3937#1:25673,2\n3946#1:25675,2\n3955#1:25677,2\n3964#1:25679,2\n3973#1:25681,2\n3982#1:25683,2\n3991#1:25685,2\n4002#1:25687\n4002#1:25688,2\n4002#1:25690,2\n4002#1:25692\n4002#1:25693\n4013#1:25694\n4013#1:25695,2\n4013#1:25697,2\n4013#1:25699\n4013#1:25700\n4024#1:25701\n4024#1:25702,2\n4024#1:25704,2\n4024#1:25706\n4024#1:25707\n4035#1:25708\n4035#1:25709,2\n4035#1:25711,2\n4035#1:25713\n4035#1:25714\n4046#1:25715\n4046#1:25716,2\n4046#1:25718,2\n4046#1:25720\n4046#1:25721\n4057#1:25722\n4057#1:25723,2\n4057#1:25725,2\n4057#1:25727\n4057#1:25728\n4068#1:25729\n4068#1:25730,2\n4068#1:25732,2\n4068#1:25734\n4068#1:25735\n4079#1:25736\n4079#1:25737,2\n4079#1:25739,2\n4079#1:25741\n4079#1:25742\n4090#1:25743\n4090#1:25744,2\n4090#1:25746,2\n4090#1:25748\n4090#1:25749\n4101#1:25750,3\n4115#1:25753,3\n4129#1:25756,3\n4143#1:25759,3\n4157#1:25762,3\n4171#1:25765,3\n4185#1:25768,3\n4199#1:25771,3\n4213#1:25774,3\n4225#1:25777,2\n4244#1:25779,2\n4253#1:25781,2\n4262#1:25783,2\n4271#1:25785,2\n4280#1:25787,2\n4289#1:25789,2\n4298#1:25791,2\n4307#1:25793,2\n4316#1:25795,2\n8903#1:25797,4\n8918#1:25801,4\n8933#1:25805,4\n8948#1:25809,4\n8963#1:25813,4\n8978#1:25817,4\n8993#1:25821,4\n9008#1:25825,4\n9023#1:25829,4\n9038#1:25833,4\n9053#1:25837,4\n9068#1:25841,4\n9083#1:25845,4\n9098#1:25849,4\n9113#1:25853,4\n9128#1:25857,4\n9143#1:25861,4\n9158#1:25865,4\n9172#1:25869,4\n9186#1:25873,4\n9200#1:25877,4\n9214#1:25881,4\n9228#1:25885,4\n9242#1:25889,4\n9256#1:25893,4\n9270#1:25897,4\n9284#1:25901,4\n9723#1:25905,4\n10468#1:25909,5\n10477#1:25914,5\n10486#1:25919,5\n10495#1:25924,5\n10504#1:25929,5\n10513#1:25934,5\n10522#1:25939,5\n10531#1:25944,5\n10540#1:25949,5\n10553#1:25954,5\n11009#1:25959,3\n11009#1:25969,3\n11021#1:25972,3\n11021#1:25982,3\n11033#1:25985,3\n11033#1:25995,3\n11045#1:25998,3\n11045#1:26008,3\n11057#1:26011,3\n11057#1:26021,3\n11069#1:26024,3\n11069#1:26034,3\n11081#1:26037,3\n11081#1:26047,3\n11093#1:26050,3\n11093#1:26060,3\n11105#1:26063,3\n11105#1:26073,3\n11118#1:26076,3\n11118#1:26086,3\n11131#1:26089,3\n11131#1:26099,3\n11144#1:26102,3\n11144#1:26112,3\n11157#1:26115,3\n11157#1:26125,3\n11170#1:26128,3\n11170#1:26138,3\n11183#1:26141,3\n11183#1:26151,3\n11196#1:26154,3\n11196#1:26164,3\n11209#1:26167,3\n11209#1:26177,3\n11222#1:26180,3\n11222#1:26190,3\n11561#1:26319,3\n11571#1:26322,3\n11581#1:26325,3\n11591#1:26328,3\n11601#1:26331,3\n11611#1:26334,3\n11621#1:26337,3\n11631#1:26340,3\n11641#1:26343,3\n11651#1:26346,4\n11661#1:26350,4\n11671#1:26354,4\n11681#1:26358,4\n11691#1:26362,4\n11701#1:26366,4\n11711#1:26370,4\n11721#1:26374,4\n11731#1:26378,4\n11741#1:26382\n11741#1:26383,2\n11741#1:26386\n11741#1:26387\n11751#1:26388,3\n11879#1:26391\n11879#1:26392\n11879#1:26394\n11879#1:26395\n11887#1:26396,2\n20159#1:26398,3\n20171#1:26401,3\n20183#1:26404,3\n20195#1:26407,3\n20207#1:26410,3\n20219#1:26413,3\n20231#1:26416,3\n20243#1:26419,3\n20255#1:26422,3\n22703#1:26425,2\n22703#1:26428,6\n22856#1:26434,2\n22856#1:26437,6\n24121#1:26443,6\n24131#1:26449,6\n24141#1:26455,6\n24151#1:26461,6\n24161#1:26467,6\n24171#1:26473,6\n24181#1:26479,6\n24191#1:26485,6\n24201#1:26491,6\n24355#1:26497,8\n24365#1:26505,8\n24375#1:26513,8\n24385#1:26521,8\n24395#1:26529,8\n24405#1:26537,8\n24415#1:26545,8\n24425#1:26553,8\n24435#1:26561,8\n24607#1:26569,6\n24617#1:26575,6\n24627#1:26581,6\n24637#1:26587,6\n24647#1:26593,6\n24657#1:26599,6\n24667#1:26605,6\n24677#1:26611,6\n11741#1:26385\n11879#1:26393\n22703#1:26427\n22856#1:26436\n11009#1:25962,7\n11021#1:25975,7\n11033#1:25988,7\n11045#1:26001,7\n11057#1:26014,7\n11069#1:26027,7\n11081#1:26040,7\n11093#1:26053,7\n11105#1:26066,7\n11118#1:26079,7\n11131#1:26092,7\n11144#1:26105,7\n11157#1:26118,7\n11170#1:26131,7\n11183#1:26144,7\n11196#1:26157,7\n11209#1:26170,7\n11222#1:26183,7\n11236#1:26193,7\n11253#1:26200,7\n11270#1:26207,7\n11287#1:26214,7\n11304#1:26221,7\n11321#1:26228,7\n11338#1:26235,7\n11355#1:26242,7\n11372#1:26249,7\n11390#1:26256,7\n11408#1:26263,7\n11426#1:26270,7\n11444#1:26277,7\n11462#1:26284,7\n11480#1:26291,7\n11498#1:26298,7\n11516#1:26305,7\n11534#1:26312,7\n*E\n"])
internal class ArraysKt___ArraysKt : ArraysKt___ArraysJvmKt {
   public final val indices: IntRange
      public final get() {
         return new IntRange(0, ArraysKt.getLastIndex(`$this$indices`));
      }


   public final val indices: IntRange
      public final get() {
         return new IntRange(0, ArraysKt.getLastIndex(`$this$indices`));
      }


   public final val indices: IntRange
      public final get() {
         return new IntRange(0, ArraysKt.getLastIndex(`$this$indices`));
      }


   public final val indices: IntRange
      public final get() {
         return new IntRange(0, ArraysKt.getLastIndex(`$this$indices`));
      }


   public final val indices: IntRange
      public final get() {
         return new IntRange(0, ArraysKt.getLastIndex(`$this$indices`));
      }


   public final val indices: IntRange
      public final get() {
         return new IntRange(0, ArraysKt.getLastIndex(`$this$indices`));
      }


   public final val indices: IntRange
      public final get() {
         return new IntRange(0, ArraysKt.getLastIndex(`$this$indices`));
      }


   public final val indices: IntRange
      public final get() {
         return new IntRange(0, ArraysKt.getLastIndex(`$this$indices`));
      }


   public final val indices: IntRange
      public final get() {
         return new IntRange(0, ArraysKt.getLastIndex(`$this$indices`));
      }


   public final val lastIndex: Int
      public final get() {
         return `$this$lastIndex`.length - 1;
      }


   public final val lastIndex: Int
      public final get() {
         return `$this$lastIndex`.length - 1;
      }


   public final val lastIndex: Int
      public final get() {
         return `$this$lastIndex`.length - 1;
      }


   public final val lastIndex: Int
      public final get() {
         return `$this$lastIndex`.length - 1;
      }


   public final val lastIndex: Int
      public final get() {
         return `$this$lastIndex`.length - 1;
      }


   public final val lastIndex: Int
      public final get() {
         return `$this$lastIndex`.length - 1;
      }


   public final val lastIndex: Int
      public final get() {
         return `$this$lastIndex`.length - 1;
      }


   public final val lastIndex: Int
      public final get() {
         return `$this$lastIndex`.length - 1;
      }


   public final val lastIndex: Int
      public final get() {
         return `$this$lastIndex`.length - 1;
      }


   @InlineOnly
   @JvmStatic
   public inline operator fun <T> Array<out T>.component1(): T {
      return (T)`$this$component1`[0];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun ByteArray.component1(): Byte {
      return `$this$component1`[0];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun ShortArray.component1(): Short {
      return `$this$component1`[0];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun IntArray.component1(): Int {
      return `$this$component1`[0];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun LongArray.component1(): Long {
      return `$this$component1`[0];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun FloatArray.component1(): Float {
      return `$this$component1`[0];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun DoubleArray.component1(): Double {
      return `$this$component1`[0];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun BooleanArray.component1(): Boolean {
      return `$this$component1`[0];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun CharArray.component1(): Char {
      return `$this$component1`[0];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun <T> Array<out T>.component2(): T {
      return (T)`$this$component2`[1];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun ByteArray.component2(): Byte {
      return `$this$component2`[1];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun ShortArray.component2(): Short {
      return `$this$component2`[1];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun IntArray.component2(): Int {
      return `$this$component2`[1];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun LongArray.component2(): Long {
      return `$this$component2`[1];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun FloatArray.component2(): Float {
      return `$this$component2`[1];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun DoubleArray.component2(): Double {
      return `$this$component2`[1];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun BooleanArray.component2(): Boolean {
      return `$this$component2`[1];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun CharArray.component2(): Char {
      return `$this$component2`[1];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun <T> Array<out T>.component3(): T {
      return (T)`$this$component3`[2];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun ByteArray.component3(): Byte {
      return `$this$component3`[2];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun ShortArray.component3(): Short {
      return `$this$component3`[2];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun IntArray.component3(): Int {
      return `$this$component3`[2];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun LongArray.component3(): Long {
      return `$this$component3`[2];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun FloatArray.component3(): Float {
      return `$this$component3`[2];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun DoubleArray.component3(): Double {
      return `$this$component3`[2];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun BooleanArray.component3(): Boolean {
      return `$this$component3`[2];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun CharArray.component3(): Char {
      return `$this$component3`[2];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun <T> Array<out T>.component4(): T {
      return (T)`$this$component4`[3];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun ByteArray.component4(): Byte {
      return `$this$component4`[3];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun ShortArray.component4(): Short {
      return `$this$component4`[3];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun IntArray.component4(): Int {
      return `$this$component4`[3];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun LongArray.component4(): Long {
      return `$this$component4`[3];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun FloatArray.component4(): Float {
      return `$this$component4`[3];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun DoubleArray.component4(): Double {
      return `$this$component4`[3];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun BooleanArray.component4(): Boolean {
      return `$this$component4`[3];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun CharArray.component4(): Char {
      return `$this$component4`[3];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun <T> Array<out T>.component5(): T {
      return (T)`$this$component5`[4];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun ByteArray.component5(): Byte {
      return `$this$component5`[4];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun ShortArray.component5(): Short {
      return `$this$component5`[4];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun IntArray.component5(): Int {
      return `$this$component5`[4];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun LongArray.component5(): Long {
      return `$this$component5`[4];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun FloatArray.component5(): Float {
      return `$this$component5`[4];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun DoubleArray.component5(): Double {
      return `$this$component5`[4];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun BooleanArray.component5(): Boolean {
      return `$this$component5`[4];
   }

   @InlineOnly
   @JvmStatic
   public inline operator fun CharArray.component5(): Char {
      return `$this$component5`[4];
   }

   @JvmStatic
   public operator fun <T> Array<out T>.contains(element: T): Boolean {
      return ArraysKt.indexOf(`$this$contains`, element) >= 0;
   }

   @JvmStatic
   public operator fun ByteArray.contains(element: Byte): Boolean {
      return ArraysKt.indexOf(`$this$contains`, element) >= 0;
   }

   @JvmStatic
   public operator fun ShortArray.contains(element: Short): Boolean {
      return ArraysKt.indexOf(`$this$contains`, element) >= 0;
   }

   @JvmStatic
   public operator fun IntArray.contains(element: Int): Boolean {
      return ArraysKt.indexOf(`$this$contains`, element) >= 0;
   }

   @JvmStatic
   public operator fun LongArray.contains(element: Long): Boolean {
      return ArraysKt.indexOf(`$this$contains`, element) >= 0;
   }

   @JvmStatic
   public operator fun BooleanArray.contains(element: Boolean): Boolean {
      return ArraysKt.indexOf(`$this$contains`, element) >= 0;
   }

   @JvmStatic
   public operator fun CharArray.contains(element: Char): Boolean {
      return ArraysKt.indexOf(`$this$contains`, element) >= 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.elementAtOrElse(index: Int, defaultValue: (Int) -> T): T {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return (T)(if (0 <= index && index < `$this$elementAtOrElse`.length) `$this$elementAtOrElse`[index] else defaultValue.invoke(index));
   }

   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.elementAtOrElse(index: Int, defaultValue: (Int) -> Byte): Byte {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$elementAtOrElse`.length)
         `$this$elementAtOrElse`[index]
         else
         (defaultValue.invoke(index) as java.lang.Number).byteValue();
   }

   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.elementAtOrElse(index: Int, defaultValue: (Int) -> Short): Short {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$elementAtOrElse`.length)
         `$this$elementAtOrElse`[index]
         else
         (defaultValue.invoke(index) as java.lang.Number).shortValue();
   }

   @InlineOnly
   @JvmStatic
   public inline fun IntArray.elementAtOrElse(index: Int, defaultValue: (Int) -> Int): Int {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$elementAtOrElse`.length)
         `$this$elementAtOrElse`[index]
         else
         (defaultValue.invoke(index) as java.lang.Number).intValue();
   }

   @InlineOnly
   @JvmStatic
   public inline fun LongArray.elementAtOrElse(index: Int, defaultValue: (Int) -> Long): Long {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$elementAtOrElse`.length)
         `$this$elementAtOrElse`[index]
         else
         (defaultValue.invoke(index) as java.lang.Number).longValue();
   }

   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.elementAtOrElse(index: Int, defaultValue: (Int) -> Float): Float {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$elementAtOrElse`.length)
         `$this$elementAtOrElse`[index]
         else
         (defaultValue.invoke(index) as java.lang.Number).floatValue();
   }

   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.elementAtOrElse(index: Int, defaultValue: (Int) -> Double): Double {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$elementAtOrElse`.length)
         `$this$elementAtOrElse`[index]
         else
         (defaultValue.invoke(index) as java.lang.Number).doubleValue();
   }

   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.elementAtOrElse(index: Int, defaultValue: (Int) -> Boolean): Boolean {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$elementAtOrElse`.length) `$this$elementAtOrElse`[index] else defaultValue.invoke(index) as java.lang.Boolean;
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharArray.elementAtOrElse(index: Int, defaultValue: (Int) -> Char): Char {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$elementAtOrElse`.length) `$this$elementAtOrElse`[index] else defaultValue.invoke(index) as Character;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.elementAtOrNull(index: Int): T? {
      return (T)ArraysKt.getOrNull(`$this$elementAtOrNull`, index);
   }

   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.elementAtOrNull(index: Int): Byte? {
      return ArraysKt.getOrNull(`$this$elementAtOrNull`, index);
   }

   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.elementAtOrNull(index: Int): Short? {
      return ArraysKt.getOrNull(`$this$elementAtOrNull`, index);
   }

   @InlineOnly
   @JvmStatic
   public inline fun IntArray.elementAtOrNull(index: Int): Int? {
      return ArraysKt.getOrNull(`$this$elementAtOrNull`, index);
   }

   @InlineOnly
   @JvmStatic
   public inline fun LongArray.elementAtOrNull(index: Int): Long? {
      return ArraysKt.getOrNull(`$this$elementAtOrNull`, index);
   }

   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.elementAtOrNull(index: Int): Float? {
      return ArraysKt.getOrNull(`$this$elementAtOrNull`, index);
   }

   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.elementAtOrNull(index: Int): Double? {
      return ArraysKt.getOrNull(`$this$elementAtOrNull`, index);
   }

   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.elementAtOrNull(index: Int): Boolean? {
      return ArraysKt.getOrNull(`$this$elementAtOrNull`, index);
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharArray.elementAtOrNull(index: Int): Char? {
      return ArraysKt.getOrNull(`$this$elementAtOrNull`, index);
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.find(predicate: (T) -> Boolean): T? {
      val `$this$firstOrNull$iv`: Array<Any> = `$this$find`;
      var var4: Int = 0;
      val var5: Int = `$this$find`.length;

      var var10000: Any;
      while (true) {
         if (var4 >= var5) {
            var10000 = null;
            break;
         }

         val `element$iv`: Any = `$this$firstOrNull$iv`[var4];
         if (predicate.invoke(`$this$firstOrNull$iv`[var4]) as java.lang.Boolean) {
            var10000 = `element$iv`;
            break;
         }

         var4++;
      }

      return (T)var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.find(predicate: (Byte) -> Boolean): Byte? {
      val `$this$firstOrNull$iv`: ByteArray = `$this$find`;
      var var4: Int = 0;
      val var5: Int = `$this$find`.length;

      var var10000: java.lang.Byte;
      while (true) {
         if (var4 >= var5) {
            var10000 = null;
            break;
         }

         val `element$iv`: Byte = `$this$firstOrNull$iv`[var4];
         if (predicate.invoke(`$this$firstOrNull$iv`[var4]) as java.lang.Boolean) {
            var10000 = `element$iv`;
            break;
         }

         var4++;
      }

      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.find(predicate: (Short) -> Boolean): Short? {
      val `$this$firstOrNull$iv`: ShortArray = `$this$find`;
      var var4: Int = 0;
      val var5: Int = `$this$find`.length;

      var var10000: java.lang.Short;
      while (true) {
         if (var4 >= var5) {
            var10000 = null;
            break;
         }

         val `element$iv`: Short = `$this$firstOrNull$iv`[var4];
         if (predicate.invoke(`$this$firstOrNull$iv`[var4]) as java.lang.Boolean) {
            var10000 = `element$iv`;
            break;
         }

         var4++;
      }

      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun IntArray.find(predicate: (Int) -> Boolean): Int? {
      val `$this$firstOrNull$iv`: IntArray = `$this$find`;
      var var4: Int = 0;
      val var5: Int = `$this$find`.length;

      var var10000: Int;
      while (true) {
         if (var4 >= var5) {
            var10000 = null;
            break;
         }

         val `element$iv`: Int = `$this$firstOrNull$iv`[var4];
         if (predicate.invoke(`$this$firstOrNull$iv`[var4]) as java.lang.Boolean) {
            var10000 = `element$iv`;
            break;
         }

         var4++;
      }

      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun LongArray.find(predicate: (Long) -> Boolean): Long? {
      val `$this$firstOrNull$iv`: LongArray = `$this$find`;
      var var4: Int = 0;
      val var5: Int = `$this$find`.length;

      var var10000: java.lang.Long;
      while (true) {
         if (var4 >= var5) {
            var10000 = null;
            break;
         }

         val `element$iv`: Long = `$this$firstOrNull$iv`[var4];
         if (predicate.invoke(`$this$firstOrNull$iv`[var4]) as java.lang.Boolean) {
            var10000 = `element$iv`;
            break;
         }

         var4++;
      }

      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.find(predicate: (Float) -> Boolean): Float? {
      val `$this$firstOrNull$iv`: FloatArray = `$this$find`;
      var var4: Int = 0;
      val var5: Int = `$this$find`.length;

      var var10000: java.lang.Float;
      while (true) {
         if (var4 >= var5) {
            var10000 = null;
            break;
         }

         val `element$iv`: Float = `$this$firstOrNull$iv`[var4];
         if (predicate.invoke(`$this$firstOrNull$iv`[var4]) as java.lang.Boolean) {
            var10000 = `element$iv`;
            break;
         }

         var4++;
      }

      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.find(predicate: (Double) -> Boolean): Double? {
      val `$this$firstOrNull$iv`: DoubleArray = `$this$find`;
      var var4: Int = 0;
      val var5: Int = `$this$find`.length;

      var var10000: java.lang.Double;
      while (true) {
         if (var4 >= var5) {
            var10000 = null;
            break;
         }

         val `element$iv`: Double = `$this$firstOrNull$iv`[var4];
         if (predicate.invoke(`$this$firstOrNull$iv`[var4]) as java.lang.Boolean) {
            var10000 = `element$iv`;
            break;
         }

         var4++;
      }

      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.find(predicate: (Boolean) -> Boolean): Boolean? {
      val `$this$firstOrNull$iv`: BooleanArray = `$this$find`;
      var var4: Int = 0;
      val var5: Int = `$this$find`.length;

      var var10000: java.lang.Boolean;
      while (true) {
         if (var4 >= var5) {
            var10000 = null;
            break;
         }

         val `element$iv`: Boolean = `$this$firstOrNull$iv`[var4];
         if (predicate.invoke(`$this$firstOrNull$iv`[var4]) as java.lang.Boolean) {
            var10000 = `element$iv`;
            break;
         }

         var4++;
      }

      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharArray.find(predicate: (Char) -> Boolean): Char? {
      val `$this$firstOrNull$iv`: CharArray = `$this$find`;
      var var4: Int = 0;
      val var5: Int = `$this$find`.length;

      var var10000: Character;
      while (true) {
         if (var4 >= var5) {
            var10000 = null;
            break;
         }

         val `element$iv`: Char = `$this$firstOrNull$iv`[var4];
         if (predicate.invoke(`$this$firstOrNull$iv`[var4]) as java.lang.Boolean) {
            var10000 = `element$iv`;
            break;
         }

         var4++;
      }

      return var10000;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.findLast(predicate: (T) -> Boolean): T? {
      val `$this$lastOrNull$iv`: Array<Any> = `$this$findLast`;
      var var4: Int = `$this$findLast`.length + -1;
      if (0 <= `$this$findLast`.length + -1) {
         do {
            val `element$iv`: Any = `$this$lastOrNull$iv`[var4--];
            if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
               return (T)`element$iv`;
            }
         } while (0 <= var4);
      }

      return null;
   }

   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.findLast(predicate: (Byte) -> Boolean): Byte? {
      val `$this$lastOrNull$iv`: ByteArray = `$this$findLast`;
      var var4: Int = `$this$findLast`.length + -1;
      if (0 <= `$this$findLast`.length + -1) {
         do {
            val `element$iv`: Byte = `$this$lastOrNull$iv`[var4--];
            if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
               return `element$iv`;
            }
         } while (0 <= var4);
      }

      return null;
   }

   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.findLast(predicate: (Short) -> Boolean): Short? {
      val `$this$lastOrNull$iv`: ShortArray = `$this$findLast`;
      var var4: Int = `$this$findLast`.length + -1;
      if (0 <= `$this$findLast`.length + -1) {
         do {
            val `element$iv`: Short = `$this$lastOrNull$iv`[var4--];
            if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
               return `element$iv`;
            }
         } while (0 <= var4);
      }

      return null;
   }

   @InlineOnly
   @JvmStatic
   public inline fun IntArray.findLast(predicate: (Int) -> Boolean): Int? {
      val `$this$lastOrNull$iv`: IntArray = `$this$findLast`;
      var var4: Int = `$this$findLast`.length + -1;
      if (0 <= `$this$findLast`.length + -1) {
         do {
            val `element$iv`: Int = `$this$lastOrNull$iv`[var4--];
            if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
               return `element$iv`;
            }
         } while (0 <= var4);
      }

      return null;
   }

   @InlineOnly
   @JvmStatic
   public inline fun LongArray.findLast(predicate: (Long) -> Boolean): Long? {
      val `$this$lastOrNull$iv`: LongArray = `$this$findLast`;
      var var4: Int = `$this$findLast`.length + -1;
      if (0 <= `$this$findLast`.length + -1) {
         do {
            val `element$iv`: Long = `$this$lastOrNull$iv`[var4--];
            if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
               return `element$iv`;
            }
         } while (0 <= var4);
      }

      return null;
   }

   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.findLast(predicate: (Float) -> Boolean): Float? {
      val `$this$lastOrNull$iv`: FloatArray = `$this$findLast`;
      var var4: Int = `$this$findLast`.length + -1;
      if (0 <= `$this$findLast`.length + -1) {
         do {
            val `element$iv`: Float = `$this$lastOrNull$iv`[var4--];
            if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
               return `element$iv`;
            }
         } while (0 <= var4);
      }

      return null;
   }

   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.findLast(predicate: (Double) -> Boolean): Double? {
      val `$this$lastOrNull$iv`: DoubleArray = `$this$findLast`;
      var var4: Int = `$this$findLast`.length + -1;
      if (0 <= `$this$findLast`.length + -1) {
         do {
            val `element$iv`: Double = `$this$lastOrNull$iv`[var4--];
            if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
               return `element$iv`;
            }
         } while (0 <= var4);
      }

      return null;
   }

   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.findLast(predicate: (Boolean) -> Boolean): Boolean? {
      val `$this$lastOrNull$iv`: BooleanArray = `$this$findLast`;
      var var4: Int = `$this$findLast`.length + -1;
      if (0 <= `$this$findLast`.length + -1) {
         do {
            val `element$iv`: Boolean = `$this$lastOrNull$iv`[var4--];
            if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
               return `element$iv`;
            }
         } while (0 <= var4);
      }

      return null;
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharArray.findLast(predicate: (Char) -> Boolean): Char? {
      val `$this$lastOrNull$iv`: CharArray = `$this$findLast`;
      var var4: Int = `$this$findLast`.length + -1;
      if (0 <= `$this$findLast`.length + -1) {
         do {
            val `element$iv`: Char = `$this$lastOrNull$iv`[var4--];
            if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
               return `element$iv`;
            }
         } while (0 <= var4);
      }

      return null;
   }

   @JvmStatic
   public fun <T> Array<out T>.first(): T {
      if (`$this$first`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return (T)`$this$first`[0];
      }
   }

   @JvmStatic
   public fun ByteArray.first(): Byte {
      if (`$this$first`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$first`[0];
      }
   }

   @JvmStatic
   public fun ShortArray.first(): Short {
      if (`$this$first`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$first`[0];
      }
   }

   @JvmStatic
   public fun IntArray.first(): Int {
      if (`$this$first`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$first`[0];
      }
   }

   @JvmStatic
   public fun LongArray.first(): Long {
      if (`$this$first`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$first`[0];
      }
   }

   @JvmStatic
   public fun FloatArray.first(): Float {
      if (`$this$first`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$first`[0];
      }
   }

   @JvmStatic
   public fun DoubleArray.first(): Double {
      if (`$this$first`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$first`[0];
      }
   }

   @JvmStatic
   public fun BooleanArray.first(): Boolean {
      if (`$this$first`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$first`[0];
      }
   }

   @JvmStatic
   public fun CharArray.first(): Char {
      if (`$this$first`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$first`[0];
      }
   }

   @JvmStatic
   public inline fun <T> Array<out T>.first(predicate: (T) -> Boolean): T {
      for (Object element : $this$first) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return (T)element;
         }
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun ByteArray.first(predicate: (Byte) -> Boolean): Byte {
      for (byte element : $this$first) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun ShortArray.first(predicate: (Short) -> Boolean): Short {
      for (short element : $this$first) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun IntArray.first(predicate: (Int) -> Boolean): Int {
      for (int element : $this$first) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun LongArray.first(predicate: (Long) -> Boolean): Long {
      for (long element : $this$first) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun FloatArray.first(predicate: (Float) -> Boolean): Float {
      for (float element : $this$first) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun DoubleArray.first(predicate: (Double) -> Boolean): Double {
      for (double element : $this$first) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun BooleanArray.first(predicate: (Boolean) -> Boolean): Boolean {
      for (boolean element : $this$first) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun CharArray.first(predicate: (Char) -> Boolean): Char {
      for (char element : $this$first) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R : Any> Array<out T>.firstNotNullOf(transform: (T) -> R?): R {
      val var2: Array<Any> = `$this$firstNotNullOf`;
      var var3: Int = 0;
      val var4: Int = `$this$firstNotNullOf`.length;

      var var10000: Any;
      while (true) {
         if (var3 >= var4) {
            var10000 = null;
            break;
         }

         var10000 = transform.invoke(var2[var3]);
         if (var10000 != null) {
            break;
         }

         var3++;
      }

      if (var10000 == null) {
         throw new NoSuchElementException("No element of the array was transformed to a non-null value.");
      } else {
         return (R)var10000;
      }
   }

   @SinceKotlin(version = "1.5")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R : Any> Array<out T>.firstNotNullOfOrNull(transform: (T) -> R?): R? {
      for (Object element : $this$firstNotNullOfOrNull) {
         val result: Any = transform.invoke(element);
         if (result != null) {
            return (R)result;
         }
      }

      return null;
   }

   @JvmStatic
   public fun <T> Array<out T>.firstOrNull(): T? {
      return (T)(if (`$this$firstOrNull`.length == 0) null else `$this$firstOrNull`[0]);
   }

   @JvmStatic
   public fun ByteArray.firstOrNull(): Byte? {
      return if (`$this$firstOrNull`.length == 0) null else `$this$firstOrNull`[0];
   }

   @JvmStatic
   public fun ShortArray.firstOrNull(): Short? {
      return if (`$this$firstOrNull`.length == 0) null else `$this$firstOrNull`[0];
   }

   @JvmStatic
   public fun IntArray.firstOrNull(): Int? {
      return if (`$this$firstOrNull`.length == 0) null else `$this$firstOrNull`[0];
   }

   @JvmStatic
   public fun LongArray.firstOrNull(): Long? {
      return if (`$this$firstOrNull`.length == 0) null else `$this$firstOrNull`[0];
   }

   @JvmStatic
   public fun FloatArray.firstOrNull(): Float? {
      return if (`$this$firstOrNull`.length == 0) null else `$this$firstOrNull`[0];
   }

   @JvmStatic
   public fun DoubleArray.firstOrNull(): Double? {
      return if (`$this$firstOrNull`.length == 0) null else `$this$firstOrNull`[0];
   }

   @JvmStatic
   public fun BooleanArray.firstOrNull(): Boolean? {
      return if (`$this$firstOrNull`.length == 0) null else `$this$firstOrNull`[0];
   }

   @JvmStatic
   public fun CharArray.firstOrNull(): Char? {
      return if (`$this$firstOrNull`.length == 0) null else `$this$firstOrNull`[0];
   }

   @JvmStatic
   public inline fun <T> Array<out T>.firstOrNull(predicate: (T) -> Boolean): T? {
      for (Object element : $this$firstOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return (T)element;
         }
      }

      return null;
   }

   @JvmStatic
   public inline fun ByteArray.firstOrNull(predicate: (Byte) -> Boolean): Byte? {
      for (byte element : $this$firstOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      return null;
   }

   @JvmStatic
   public inline fun ShortArray.firstOrNull(predicate: (Short) -> Boolean): Short? {
      for (short element : $this$firstOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      return null;
   }

   @JvmStatic
   public inline fun IntArray.firstOrNull(predicate: (Int) -> Boolean): Int? {
      for (int element : $this$firstOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      return null;
   }

   @JvmStatic
   public inline fun LongArray.firstOrNull(predicate: (Long) -> Boolean): Long? {
      for (long element : $this$firstOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      return null;
   }

   @JvmStatic
   public inline fun FloatArray.firstOrNull(predicate: (Float) -> Boolean): Float? {
      for (float element : $this$firstOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      return null;
   }

   @JvmStatic
   public inline fun DoubleArray.firstOrNull(predicate: (Double) -> Boolean): Double? {
      for (double element : $this$firstOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      return null;
   }

   @JvmStatic
   public inline fun BooleanArray.firstOrNull(predicate: (Boolean) -> Boolean): Boolean? {
      for (boolean element : $this$firstOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      return null;
   }

   @JvmStatic
   public inline fun CharArray.firstOrNull(predicate: (Char) -> Boolean): Char? {
      for (char element : $this$firstOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return element;
         }
      }

      return null;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.getOrElse(index: Int, defaultValue: (Int) -> T): T {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return (T)(if (0 <= index && index < `$this$getOrElse`.length) `$this$getOrElse`[index] else defaultValue.invoke(index));
   }

   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.getOrElse(index: Int, defaultValue: (Int) -> Byte): Byte {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$getOrElse`.length) `$this$getOrElse`[index] else (defaultValue.invoke(index) as java.lang.Number).byteValue();
   }

   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.getOrElse(index: Int, defaultValue: (Int) -> Short): Short {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$getOrElse`.length) `$this$getOrElse`[index] else (defaultValue.invoke(index) as java.lang.Number).shortValue();
   }

   @InlineOnly
   @JvmStatic
   public inline fun IntArray.getOrElse(index: Int, defaultValue: (Int) -> Int): Int {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$getOrElse`.length) `$this$getOrElse`[index] else (defaultValue.invoke(index) as java.lang.Number).intValue();
   }

   @InlineOnly
   @JvmStatic
   public inline fun LongArray.getOrElse(index: Int, defaultValue: (Int) -> Long): Long {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$getOrElse`.length) `$this$getOrElse`[index] else (defaultValue.invoke(index) as java.lang.Number).longValue();
   }

   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.getOrElse(index: Int, defaultValue: (Int) -> Float): Float {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$getOrElse`.length) `$this$getOrElse`[index] else (defaultValue.invoke(index) as java.lang.Number).floatValue();
   }

   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.getOrElse(index: Int, defaultValue: (Int) -> Double): Double {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$getOrElse`.length) `$this$getOrElse`[index] else (defaultValue.invoke(index) as java.lang.Number).doubleValue();
   }

   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.getOrElse(index: Int, defaultValue: (Int) -> Boolean): Boolean {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$getOrElse`.length) `$this$getOrElse`[index] else defaultValue.invoke(index) as java.lang.Boolean;
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharArray.getOrElse(index: Int, defaultValue: (Int) -> Char): Char {
      contract {
         callsInPlace(defaultValue, InvocationKind.AT_MOST_ONCE)
      }

      return if (0 <= index && index < `$this$getOrElse`.length) `$this$getOrElse`[index] else defaultValue.invoke(index) as Character;
   }

   @JvmStatic
   public fun <T> Array<out T>.getOrNull(index: Int): T? {
      return (T)(if (0 <= index && index < `$this$getOrNull`.length) `$this$getOrNull`[index] else null);
   }

   @JvmStatic
   public fun ByteArray.getOrNull(index: Int): Byte? {
      return if (0 <= index && index < `$this$getOrNull`.length) `$this$getOrNull`[index] else null;
   }

   @JvmStatic
   public fun ShortArray.getOrNull(index: Int): Short? {
      return if (0 <= index && index < `$this$getOrNull`.length) `$this$getOrNull`[index] else null;
   }

   @JvmStatic
   public fun IntArray.getOrNull(index: Int): Int? {
      return if (0 <= index && index < `$this$getOrNull`.length) `$this$getOrNull`[index] else null;
   }

   @JvmStatic
   public fun LongArray.getOrNull(index: Int): Long? {
      return if (0 <= index && index < `$this$getOrNull`.length) `$this$getOrNull`[index] else null;
   }

   @JvmStatic
   public fun FloatArray.getOrNull(index: Int): Float? {
      return if (0 <= index && index < `$this$getOrNull`.length) `$this$getOrNull`[index] else null;
   }

   @JvmStatic
   public fun DoubleArray.getOrNull(index: Int): Double? {
      return if (0 <= index && index < `$this$getOrNull`.length) `$this$getOrNull`[index] else null;
   }

   @JvmStatic
   public fun BooleanArray.getOrNull(index: Int): Boolean? {
      return if (0 <= index && index < `$this$getOrNull`.length) `$this$getOrNull`[index] else null;
   }

   @JvmStatic
   public fun CharArray.getOrNull(index: Int): Char? {
      return if (0 <= index && index < `$this$getOrNull`.length) `$this$getOrNull`[index] else null;
   }

   @JvmStatic
   public fun <T> Array<out T>.indexOf(element: T): Int {
      if (element == null) {
         var index: Int = 0;

         for (int var3 = $this$indexOf.length; index < var3; index++) {
            if (`$this$indexOf`[index] == null) {
               return index;
            }
         }
      } else {
         var var4: Int = 0;

         for (int var5 = $this$indexOf.length; index < var5; index++) {
            if (element == `$this$indexOf`[var4]) {
               return var4;
            }
         }
      }

      return -1;
   }

   @JvmStatic
   public fun ByteArray.indexOf(element: Byte): Int {
      var index: Int = 0;

      for (int var3 = $this$indexOf.length; index < var3; index++) {
         if (element == `$this$indexOf`[index]) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public fun ShortArray.indexOf(element: Short): Int {
      var index: Int = 0;

      for (int var3 = $this$indexOf.length; index < var3; index++) {
         if (element == `$this$indexOf`[index]) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public fun IntArray.indexOf(element: Int): Int {
      var index: Int = 0;

      for (int var3 = $this$indexOf.length; index < var3; index++) {
         if (element == `$this$indexOf`[index]) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public fun LongArray.indexOf(element: Long): Int {
      var index: Int = 0;

      for (int var4 = $this$indexOf.length; index < var4; index++) {
         if (element == `$this$indexOf`[index]) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public fun BooleanArray.indexOf(element: Boolean): Int {
      var index: Int = 0;

      for (int var3 = $this$indexOf.length; index < var3; index++) {
         if (element == `$this$indexOf`[index]) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public fun CharArray.indexOf(element: Char): Int {
      var index: Int = 0;

      for (int var3 = $this$indexOf.length; index < var3; index++) {
         if (element == `$this$indexOf`[index]) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public inline fun <T> Array<out T>.indexOfFirst(predicate: (T) -> Boolean): Int {
      var index: Int = 0;

      for (int var4 = $this$indexOfFirst.length; index < var4; index++) {
         if (predicate.invoke(`$this$indexOfFirst`[index]) as java.lang.Boolean) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public inline fun ByteArray.indexOfFirst(predicate: (Byte) -> Boolean): Int {
      var index: Int = 0;

      for (int var4 = $this$indexOfFirst.length; index < var4; index++) {
         if (predicate.invoke(`$this$indexOfFirst`[index]) as java.lang.Boolean) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public inline fun ShortArray.indexOfFirst(predicate: (Short) -> Boolean): Int {
      var index: Int = 0;

      for (int var4 = $this$indexOfFirst.length; index < var4; index++) {
         if (predicate.invoke(`$this$indexOfFirst`[index]) as java.lang.Boolean) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public inline fun IntArray.indexOfFirst(predicate: (Int) -> Boolean): Int {
      var index: Int = 0;

      for (int var4 = $this$indexOfFirst.length; index < var4; index++) {
         if (predicate.invoke(`$this$indexOfFirst`[index]) as java.lang.Boolean) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public inline fun LongArray.indexOfFirst(predicate: (Long) -> Boolean): Int {
      var index: Int = 0;

      for (int var4 = $this$indexOfFirst.length; index < var4; index++) {
         if (predicate.invoke(`$this$indexOfFirst`[index]) as java.lang.Boolean) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public inline fun FloatArray.indexOfFirst(predicate: (Float) -> Boolean): Int {
      var index: Int = 0;

      for (int var4 = $this$indexOfFirst.length; index < var4; index++) {
         if (predicate.invoke(`$this$indexOfFirst`[index]) as java.lang.Boolean) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public inline fun DoubleArray.indexOfFirst(predicate: (Double) -> Boolean): Int {
      var index: Int = 0;

      for (int var4 = $this$indexOfFirst.length; index < var4; index++) {
         if (predicate.invoke(`$this$indexOfFirst`[index]) as java.lang.Boolean) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public inline fun BooleanArray.indexOfFirst(predicate: (Boolean) -> Boolean): Int {
      var index: Int = 0;

      for (int var4 = $this$indexOfFirst.length; index < var4; index++) {
         if (predicate.invoke(`$this$indexOfFirst`[index]) as java.lang.Boolean) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public inline fun CharArray.indexOfFirst(predicate: (Char) -> Boolean): Int {
      var index: Int = 0;

      for (int var4 = $this$indexOfFirst.length; index < var4; index++) {
         if (predicate.invoke(`$this$indexOfFirst`[index]) as java.lang.Boolean) {
            return index;
         }
      }

      return -1;
   }

   @JvmStatic
   public inline fun <T> Array<out T>.indexOfLast(predicate: (T) -> Boolean): Int {
      var var3: Int = `$this$indexOfLast`.length + -1;
      if (0 <= `$this$indexOfLast`.length + -1) {
         do {
            val index: Int = var3--;
            if (predicate.invoke(`$this$indexOfLast`[index]) as java.lang.Boolean) {
               return index;
            }
         } while (0 <= var3);
      }

      return -1;
   }

   @JvmStatic
   public inline fun ByteArray.indexOfLast(predicate: (Byte) -> Boolean): Int {
      var var3: Int = `$this$indexOfLast`.length + -1;
      if (0 <= `$this$indexOfLast`.length + -1) {
         do {
            val index: Int = var3--;
            if (predicate.invoke(`$this$indexOfLast`[index]) as java.lang.Boolean) {
               return index;
            }
         } while (0 <= var3);
      }

      return -1;
   }

   @JvmStatic
   public inline fun ShortArray.indexOfLast(predicate: (Short) -> Boolean): Int {
      var var3: Int = `$this$indexOfLast`.length + -1;
      if (0 <= `$this$indexOfLast`.length + -1) {
         do {
            val index: Int = var3--;
            if (predicate.invoke(`$this$indexOfLast`[index]) as java.lang.Boolean) {
               return index;
            }
         } while (0 <= var3);
      }

      return -1;
   }

   @JvmStatic
   public inline fun IntArray.indexOfLast(predicate: (Int) -> Boolean): Int {
      var var3: Int = `$this$indexOfLast`.length + -1;
      if (0 <= `$this$indexOfLast`.length + -1) {
         do {
            val index: Int = var3--;
            if (predicate.invoke(`$this$indexOfLast`[index]) as java.lang.Boolean) {
               return index;
            }
         } while (0 <= var3);
      }

      return -1;
   }

   @JvmStatic
   public inline fun LongArray.indexOfLast(predicate: (Long) -> Boolean): Int {
      var var3: Int = `$this$indexOfLast`.length + -1;
      if (0 <= `$this$indexOfLast`.length + -1) {
         do {
            val index: Int = var3--;
            if (predicate.invoke(`$this$indexOfLast`[index]) as java.lang.Boolean) {
               return index;
            }
         } while (0 <= var3);
      }

      return -1;
   }

   @JvmStatic
   public inline fun FloatArray.indexOfLast(predicate: (Float) -> Boolean): Int {
      var var3: Int = `$this$indexOfLast`.length + -1;
      if (0 <= `$this$indexOfLast`.length + -1) {
         do {
            val index: Int = var3--;
            if (predicate.invoke(`$this$indexOfLast`[index]) as java.lang.Boolean) {
               return index;
            }
         } while (0 <= var3);
      }

      return -1;
   }

   @JvmStatic
   public inline fun DoubleArray.indexOfLast(predicate: (Double) -> Boolean): Int {
      var var3: Int = `$this$indexOfLast`.length + -1;
      if (0 <= `$this$indexOfLast`.length + -1) {
         do {
            val index: Int = var3--;
            if (predicate.invoke(`$this$indexOfLast`[index]) as java.lang.Boolean) {
               return index;
            }
         } while (0 <= var3);
      }

      return -1;
   }

   @JvmStatic
   public inline fun BooleanArray.indexOfLast(predicate: (Boolean) -> Boolean): Int {
      var var3: Int = `$this$indexOfLast`.length + -1;
      if (0 <= `$this$indexOfLast`.length + -1) {
         do {
            val index: Int = var3--;
            if (predicate.invoke(`$this$indexOfLast`[index]) as java.lang.Boolean) {
               return index;
            }
         } while (0 <= var3);
      }

      return -1;
   }

   @JvmStatic
   public inline fun CharArray.indexOfLast(predicate: (Char) -> Boolean): Int {
      var var3: Int = `$this$indexOfLast`.length + -1;
      if (0 <= `$this$indexOfLast`.length + -1) {
         do {
            val index: Int = var3--;
            if (predicate.invoke(`$this$indexOfLast`[index]) as java.lang.Boolean) {
               return index;
            }
         } while (0 <= var3);
      }

      return -1;
   }

   @JvmStatic
   public fun <T> Array<out T>.last(): T {
      if (`$this$last`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return (T)`$this$last`[ArraysKt.getLastIndex(`$this$last`)];
      }
   }

   @JvmStatic
   public fun ByteArray.last(): Byte {
      if (`$this$last`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$last`[ArraysKt.getLastIndex(`$this$last`)];
      }
   }

   @JvmStatic
   public fun ShortArray.last(): Short {
      if (`$this$last`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$last`[ArraysKt.getLastIndex(`$this$last`)];
      }
   }

   @JvmStatic
   public fun IntArray.last(): Int {
      if (`$this$last`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$last`[ArraysKt.getLastIndex(`$this$last`)];
      }
   }

   @JvmStatic
   public fun LongArray.last(): Long {
      if (`$this$last`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$last`[ArraysKt.getLastIndex(`$this$last`)];
      }
   }

   @JvmStatic
   public fun FloatArray.last(): Float {
      if (`$this$last`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$last`[ArraysKt.getLastIndex(`$this$last`)];
      }
   }

   @JvmStatic
   public fun DoubleArray.last(): Double {
      if (`$this$last`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$last`[ArraysKt.getLastIndex(`$this$last`)];
      }
   }

   @JvmStatic
   public fun BooleanArray.last(): Boolean {
      if (`$this$last`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$last`[ArraysKt.getLastIndex(`$this$last`)];
      }
   }

   @JvmStatic
   public fun CharArray.last(): Char {
      if (`$this$last`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$last`[ArraysKt.getLastIndex(`$this$last`)];
      }
   }

   @JvmStatic
   public inline fun <T> Array<out T>.last(predicate: (T) -> Boolean): T {
      var var3: Int = `$this$last`.length + -1;
      if (0 <= `$this$last`.length + -1) {
         do {
            val element: Any = `$this$last`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return (T)element;
            }
         } while (0 <= var3);
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun ByteArray.last(predicate: (Byte) -> Boolean): Byte {
      var var3: Int = `$this$last`.length + -1;
      if (0 <= `$this$last`.length + -1) {
         do {
            val element: Byte = `$this$last`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun ShortArray.last(predicate: (Short) -> Boolean): Short {
      var var3: Int = `$this$last`.length + -1;
      if (0 <= `$this$last`.length + -1) {
         do {
            val element: Short = `$this$last`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun IntArray.last(predicate: (Int) -> Boolean): Int {
      var var3: Int = `$this$last`.length + -1;
      if (0 <= `$this$last`.length + -1) {
         do {
            val element: Int = `$this$last`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun LongArray.last(predicate: (Long) -> Boolean): Long {
      var var3: Int = `$this$last`.length + -1;
      if (0 <= `$this$last`.length + -1) {
         do {
            val element: Long = `$this$last`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun FloatArray.last(predicate: (Float) -> Boolean): Float {
      var var3: Int = `$this$last`.length + -1;
      if (0 <= `$this$last`.length + -1) {
         do {
            val element: Float = `$this$last`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun DoubleArray.last(predicate: (Double) -> Boolean): Double {
      var var3: Int = `$this$last`.length + -1;
      if (0 <= `$this$last`.length + -1) {
         do {
            val element: Double = `$this$last`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun BooleanArray.last(predicate: (Boolean) -> Boolean): Boolean {
      var var3: Int = `$this$last`.length + -1;
      if (0 <= `$this$last`.length + -1) {
         do {
            val element: Boolean = `$this$last`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public inline fun CharArray.last(predicate: (Char) -> Boolean): Char {
      var var3: Int = `$this$last`.length + -1;
      if (0 <= `$this$last`.length + -1) {
         do {
            val element: Char = `$this$last`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      throw new NoSuchElementException("Array contains no element matching the predicate.");
   }

   @JvmStatic
   public fun <T> Array<out T>.lastIndexOf(element: T): Int {
      if (element == null) {
         var var2: Int = `$this$lastIndexOf`.length + -1;
         if (0 <= `$this$lastIndexOf`.length + -1) {
            do {
               val index: Int = var2--;
               if (`$this$lastIndexOf`[index] == null) {
                  return index;
               }
            } while (0 <= var2);
         }
      } else {
         var var4: Int = `$this$lastIndexOf`.length + -1;
         if (0 <= `$this$lastIndexOf`.length + -1) {
            do {
               val var5: Int = var4--;
               if (element == `$this$lastIndexOf`[var5]) {
                  return var5;
               }
            } while (0 <= var4);
         }
      }

      return -1;
   }

   @JvmStatic
   public fun ByteArray.lastIndexOf(element: Byte): Int {
      var var2: Int = `$this$lastIndexOf`.length + -1;
      if (0 <= `$this$lastIndexOf`.length + -1) {
         do {
            val index: Int = var2--;
            if (element == `$this$lastIndexOf`[index]) {
               return index;
            }
         } while (0 <= var2);
      }

      return -1;
   }

   @JvmStatic
   public fun ShortArray.lastIndexOf(element: Short): Int {
      var var2: Int = `$this$lastIndexOf`.length + -1;
      if (0 <= `$this$lastIndexOf`.length + -1) {
         do {
            val index: Int = var2--;
            if (element == `$this$lastIndexOf`[index]) {
               return index;
            }
         } while (0 <= var2);
      }

      return -1;
   }

   @JvmStatic
   public fun IntArray.lastIndexOf(element: Int): Int {
      var var2: Int = `$this$lastIndexOf`.length + -1;
      if (0 <= `$this$lastIndexOf`.length + -1) {
         do {
            val index: Int = var2--;
            if (element == `$this$lastIndexOf`[index]) {
               return index;
            }
         } while (0 <= var2);
      }

      return -1;
   }

   @JvmStatic
   public fun LongArray.lastIndexOf(element: Long): Int {
      var var3: Int = `$this$lastIndexOf`.length + -1;
      if (0 <= `$this$lastIndexOf`.length + -1) {
         do {
            val index: Int = var3--;
            if (element == `$this$lastIndexOf`[index]) {
               return index;
            }
         } while (0 <= var3);
      }

      return -1;
   }

   @JvmStatic
   public fun BooleanArray.lastIndexOf(element: Boolean): Int {
      var var2: Int = `$this$lastIndexOf`.length + -1;
      if (0 <= `$this$lastIndexOf`.length + -1) {
         do {
            val index: Int = var2--;
            if (element == `$this$lastIndexOf`[index]) {
               return index;
            }
         } while (0 <= var2);
      }

      return -1;
   }

   @JvmStatic
   public fun CharArray.lastIndexOf(element: Char): Int {
      var var2: Int = `$this$lastIndexOf`.length + -1;
      if (0 <= `$this$lastIndexOf`.length + -1) {
         do {
            val index: Int = var2--;
            if (element == `$this$lastIndexOf`[index]) {
               return index;
            }
         } while (0 <= var2);
      }

      return -1;
   }

   @JvmStatic
   public fun <T> Array<out T>.lastOrNull(): T? {
      return (T)(if (`$this$lastOrNull`.length == 0) null else `$this$lastOrNull`[`$this$lastOrNull`.length - 1]);
   }

   @JvmStatic
   public fun ByteArray.lastOrNull(): Byte? {
      return if (`$this$lastOrNull`.length == 0) null else `$this$lastOrNull`[`$this$lastOrNull`.length - 1];
   }

   @JvmStatic
   public fun ShortArray.lastOrNull(): Short? {
      return if (`$this$lastOrNull`.length == 0) null else `$this$lastOrNull`[`$this$lastOrNull`.length - 1];
   }

   @JvmStatic
   public fun IntArray.lastOrNull(): Int? {
      return if (`$this$lastOrNull`.length == 0) null else `$this$lastOrNull`[`$this$lastOrNull`.length - 1];
   }

   @JvmStatic
   public fun LongArray.lastOrNull(): Long? {
      return if (`$this$lastOrNull`.length == 0) null else `$this$lastOrNull`[`$this$lastOrNull`.length - 1];
   }

   @JvmStatic
   public fun FloatArray.lastOrNull(): Float? {
      return if (`$this$lastOrNull`.length == 0) null else `$this$lastOrNull`[`$this$lastOrNull`.length - 1];
   }

   @JvmStatic
   public fun DoubleArray.lastOrNull(): Double? {
      return if (`$this$lastOrNull`.length == 0) null else `$this$lastOrNull`[`$this$lastOrNull`.length - 1];
   }

   @JvmStatic
   public fun BooleanArray.lastOrNull(): Boolean? {
      return if (`$this$lastOrNull`.length == 0) null else `$this$lastOrNull`[`$this$lastOrNull`.length - 1];
   }

   @JvmStatic
   public fun CharArray.lastOrNull(): Char? {
      return if (`$this$lastOrNull`.length == 0) null else `$this$lastOrNull`[`$this$lastOrNull`.length - 1];
   }

   @JvmStatic
   public inline fun <T> Array<out T>.lastOrNull(predicate: (T) -> Boolean): T? {
      var var3: Int = `$this$lastOrNull`.length + -1;
      if (0 <= `$this$lastOrNull`.length + -1) {
         do {
            val element: Any = `$this$lastOrNull`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return (T)element;
            }
         } while (0 <= var3);
      }

      return null;
   }

   @JvmStatic
   public inline fun ByteArray.lastOrNull(predicate: (Byte) -> Boolean): Byte? {
      var var3: Int = `$this$lastOrNull`.length + -1;
      if (0 <= `$this$lastOrNull`.length + -1) {
         do {
            val element: Byte = `$this$lastOrNull`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      return null;
   }

   @JvmStatic
   public inline fun ShortArray.lastOrNull(predicate: (Short) -> Boolean): Short? {
      var var3: Int = `$this$lastOrNull`.length + -1;
      if (0 <= `$this$lastOrNull`.length + -1) {
         do {
            val element: Short = `$this$lastOrNull`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      return null;
   }

   @JvmStatic
   public inline fun IntArray.lastOrNull(predicate: (Int) -> Boolean): Int? {
      var var3: Int = `$this$lastOrNull`.length + -1;
      if (0 <= `$this$lastOrNull`.length + -1) {
         do {
            val element: Int = `$this$lastOrNull`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      return null;
   }

   @JvmStatic
   public inline fun LongArray.lastOrNull(predicate: (Long) -> Boolean): Long? {
      var var3: Int = `$this$lastOrNull`.length + -1;
      if (0 <= `$this$lastOrNull`.length + -1) {
         do {
            val element: Long = `$this$lastOrNull`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      return null;
   }

   @JvmStatic
   public inline fun FloatArray.lastOrNull(predicate: (Float) -> Boolean): Float? {
      var var3: Int = `$this$lastOrNull`.length + -1;
      if (0 <= `$this$lastOrNull`.length + -1) {
         do {
            val element: Float = `$this$lastOrNull`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      return null;
   }

   @JvmStatic
   public inline fun DoubleArray.lastOrNull(predicate: (Double) -> Boolean): Double? {
      var var3: Int = `$this$lastOrNull`.length + -1;
      if (0 <= `$this$lastOrNull`.length + -1) {
         do {
            val element: Double = `$this$lastOrNull`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      return null;
   }

   @JvmStatic
   public inline fun BooleanArray.lastOrNull(predicate: (Boolean) -> Boolean): Boolean? {
      var var3: Int = `$this$lastOrNull`.length + -1;
      if (0 <= `$this$lastOrNull`.length + -1) {
         do {
            val element: Boolean = `$this$lastOrNull`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      return null;
   }

   @JvmStatic
   public inline fun CharArray.lastOrNull(predicate: (Char) -> Boolean): Char? {
      var var3: Int = `$this$lastOrNull`.length + -1;
      if (0 <= `$this$lastOrNull`.length + -1) {
         do {
            val element: Char = `$this$lastOrNull`[var3--];
            if (predicate.invoke(element) as java.lang.Boolean) {
               return element;
            }
         } while (0 <= var3);
      }

      return null;
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.random(): T {
      return (T)ArraysKt.random(`$this$random`, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.random(): Byte {
      return ArraysKt.random(`$this$random`, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.random(): Short {
      return ArraysKt.random(`$this$random`, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.random(): Int {
      return ArraysKt.random(`$this$random`, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.random(): Long {
      return ArraysKt.random(`$this$random`, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.random(): Float {
      return ArraysKt.random(`$this$random`, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.random(): Double {
      return ArraysKt.random(`$this$random`, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.random(): Boolean {
      return ArraysKt.random(`$this$random`, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.random(): Char {
      return ArraysKt.random(`$this$random`, Random.Default);
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun <T> Array<out T>.random(random: Random): T {
      if (`$this$random`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return (T)`$this$random`[random.nextInt(`$this$random`.length)];
      }
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun ByteArray.random(random: Random): Byte {
      if (`$this$random`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$random`[random.nextInt(`$this$random`.length)];
      }
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun ShortArray.random(random: Random): Short {
      if (`$this$random`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$random`[random.nextInt(`$this$random`.length)];
      }
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun IntArray.random(random: Random): Int {
      if (`$this$random`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$random`[random.nextInt(`$this$random`.length)];
      }
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun LongArray.random(random: Random): Long {
      if (`$this$random`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$random`[random.nextInt(`$this$random`.length)];
      }
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun FloatArray.random(random: Random): Float {
      if (`$this$random`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$random`[random.nextInt(`$this$random`.length)];
      }
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun DoubleArray.random(random: Random): Double {
      if (`$this$random`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$random`[random.nextInt(`$this$random`.length)];
      }
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun BooleanArray.random(random: Random): Boolean {
      if (`$this$random`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$random`[random.nextInt(`$this$random`.length)];
      }
   }

   @SinceKotlin(version = "1.3")
   @JvmStatic
   public fun CharArray.random(random: Random): Char {
      if (`$this$random`.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return `$this$random`[random.nextInt(`$this$random`.length)];
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.randomOrNull(): T? {
      return (T)ArraysKt.randomOrNull(`$this$randomOrNull`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.randomOrNull(): Byte? {
      return ArraysKt.randomOrNull(`$this$randomOrNull`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.randomOrNull(): Short? {
      return ArraysKt.randomOrNull(`$this$randomOrNull`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.randomOrNull(): Int? {
      return ArraysKt.randomOrNull(`$this$randomOrNull`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.randomOrNull(): Long? {
      return ArraysKt.randomOrNull(`$this$randomOrNull`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.randomOrNull(): Float? {
      return ArraysKt.randomOrNull(`$this$randomOrNull`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.randomOrNull(): Double? {
      return ArraysKt.randomOrNull(`$this$randomOrNull`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.randomOrNull(): Boolean? {
      return ArraysKt.randomOrNull(`$this$randomOrNull`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.randomOrNull(): Char? {
      return ArraysKt.randomOrNull(`$this$randomOrNull`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T> Array<out T>.randomOrNull(random: Random): T? {
      return (T)(if (`$this$randomOrNull`.length == 0) null else `$this$randomOrNull`[random.nextInt(`$this$randomOrNull`.length)]);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ByteArray.randomOrNull(random: Random): Byte? {
      return if (`$this$randomOrNull`.length == 0) null else `$this$randomOrNull`[random.nextInt(`$this$randomOrNull`.length)];
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ShortArray.randomOrNull(random: Random): Short? {
      return if (`$this$randomOrNull`.length == 0) null else `$this$randomOrNull`[random.nextInt(`$this$randomOrNull`.length)];
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun IntArray.randomOrNull(random: Random): Int? {
      return if (`$this$randomOrNull`.length == 0) null else `$this$randomOrNull`[random.nextInt(`$this$randomOrNull`.length)];
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun LongArray.randomOrNull(random: Random): Long? {
      return if (`$this$randomOrNull`.length == 0) null else `$this$randomOrNull`[random.nextInt(`$this$randomOrNull`.length)];
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun FloatArray.randomOrNull(random: Random): Float? {
      return if (`$this$randomOrNull`.length == 0) null else `$this$randomOrNull`[random.nextInt(`$this$randomOrNull`.length)];
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun DoubleArray.randomOrNull(random: Random): Double? {
      return if (`$this$randomOrNull`.length == 0) null else `$this$randomOrNull`[random.nextInt(`$this$randomOrNull`.length)];
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun BooleanArray.randomOrNull(random: Random): Boolean? {
      return if (`$this$randomOrNull`.length == 0) null else `$this$randomOrNull`[random.nextInt(`$this$randomOrNull`.length)];
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun CharArray.randomOrNull(random: Random): Char? {
      return if (`$this$randomOrNull`.length == 0) null else `$this$randomOrNull`[random.nextInt(`$this$randomOrNull`.length)];
   }

   @JvmStatic
   public fun <T> Array<out T>.single(): T {
      switch ($this$single.length) {
         case 0:
            throw new NoSuchElementException("Array is empty.");
         case 1:
            return (T)`$this$single`[0];
         default:
            throw new IllegalArgumentException("Array has more than one element.");
      }
   }

   @JvmStatic
   public fun ByteArray.single(): Byte {
      switch ($this$single.length) {
         case 0:
            throw new NoSuchElementException("Array is empty.");
         case 1:
            return `$this$single`[0];
         default:
            throw new IllegalArgumentException("Array has more than one element.");
      }
   }

   @JvmStatic
   public fun ShortArray.single(): Short {
      switch ($this$single.length) {
         case 0:
            throw new NoSuchElementException("Array is empty.");
         case 1:
            return `$this$single`[0];
         default:
            throw new IllegalArgumentException("Array has more than one element.");
      }
   }

   @JvmStatic
   public fun IntArray.single(): Int {
      switch ($this$single.length) {
         case 0:
            throw new NoSuchElementException("Array is empty.");
         case 1:
            return `$this$single`[0];
         default:
            throw new IllegalArgumentException("Array has more than one element.");
      }
   }

   @JvmStatic
   public fun LongArray.single(): Long {
      switch ($this$single.length) {
         case 0:
            throw new NoSuchElementException("Array is empty.");
         case 1:
            return `$this$single`[0];
         default:
            throw new IllegalArgumentException("Array has more than one element.");
      }
   }

   @JvmStatic
   public fun FloatArray.single(): Float {
      switch ($this$single.length) {
         case 0:
            throw new NoSuchElementException("Array is empty.");
         case 1:
            return `$this$single`[0];
         default:
            throw new IllegalArgumentException("Array has more than one element.");
      }
   }

   @JvmStatic
   public fun DoubleArray.single(): Double {
      switch ($this$single.length) {
         case 0:
            throw new NoSuchElementException("Array is empty.");
         case 1:
            return `$this$single`[0];
         default:
            throw new IllegalArgumentException("Array has more than one element.");
      }
   }

   @JvmStatic
   public fun BooleanArray.single(): Boolean {
      switch ($this$single.length) {
         case 0:
            throw new NoSuchElementException("Array is empty.");
         case 1:
            return `$this$single`[0];
         default:
            throw new IllegalArgumentException("Array has more than one element.");
      }
   }

   @JvmStatic
   public fun CharArray.single(): Char {
      switch ($this$single.length) {
         case 0:
            throw new NoSuchElementException("Array is empty.");
         case 1:
            return `$this$single`[0];
         default:
            throw new IllegalArgumentException("Array has more than one element.");
      }
   }

   @JvmStatic
   public inline fun <T> Array<out T>.single(predicate: (T) -> Boolean): T {
      var single: Any = null;
      var found: Boolean = false;

      for (Object element : $this$single) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Array contains more than one matching element.");
            }

            single = element;
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Array contains no element matching the predicate.");
      } else {
         return (T)single;
      }
   }

   @JvmStatic
   public inline fun ByteArray.single(predicate: (Byte) -> Boolean): Byte {
      var single: java.lang.Byte = null;
      var found: Boolean = false;

      for (byte element : $this$single) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Array contains more than one matching element.");
            }

            single = element;
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Array contains no element matching the predicate.");
      } else {
         return single;
      }
   }

   @JvmStatic
   public inline fun ShortArray.single(predicate: (Short) -> Boolean): Short {
      var single: java.lang.Short = null;
      var found: Boolean = false;

      for (short element : $this$single) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Array contains more than one matching element.");
            }

            single = element;
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Array contains no element matching the predicate.");
      } else {
         return single;
      }
   }

   @JvmStatic
   public inline fun IntArray.single(predicate: (Int) -> Boolean): Int {
      var single: Int = null;
      var found: Boolean = false;

      for (int element : $this$single) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Array contains more than one matching element.");
            }

            single = element;
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Array contains no element matching the predicate.");
      } else {
         return single;
      }
   }

   @JvmStatic
   public inline fun LongArray.single(predicate: (Long) -> Boolean): Long {
      var single: java.lang.Long = null;
      var found: Boolean = false;

      for (long element : $this$single) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Array contains more than one matching element.");
            }

            single = element;
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Array contains no element matching the predicate.");
      } else {
         return single;
      }
   }

   @JvmStatic
   public inline fun FloatArray.single(predicate: (Float) -> Boolean): Float {
      var single: java.lang.Float = null;
      var found: Boolean = false;

      for (float element : $this$single) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Array contains more than one matching element.");
            }

            single = element;
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Array contains no element matching the predicate.");
      } else {
         return single;
      }
   }

   @JvmStatic
   public inline fun DoubleArray.single(predicate: (Double) -> Boolean): Double {
      var single: java.lang.Double = null;
      var found: Boolean = false;

      for (double element : $this$single) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Array contains more than one matching element.");
            }

            single = element;
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Array contains no element matching the predicate.");
      } else {
         return single;
      }
   }

   @JvmStatic
   public inline fun BooleanArray.single(predicate: (Boolean) -> Boolean): Boolean {
      var single: java.lang.Boolean = null;
      var found: Boolean = false;

      for (boolean element : $this$single) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Array contains more than one matching element.");
            }

            single = element;
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Array contains no element matching the predicate.");
      } else {
         return single;
      }
   }

   @JvmStatic
   public inline fun CharArray.single(predicate: (Char) -> Boolean): Char {
      var single: Character = null;
      var found: Boolean = false;

      for (char element : $this$single) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               throw new IllegalArgumentException("Array contains more than one matching element.");
            }

            single = element;
            found = true;
         }
      }

      if (!found) {
         throw new NoSuchElementException("Array contains no element matching the predicate.");
      } else {
         return single;
      }
   }

   @JvmStatic
   public fun <T> Array<out T>.singleOrNull(): T? {
      return (T)(if (`$this$singleOrNull`.length == 1) `$this$singleOrNull`[0] else null);
   }

   @JvmStatic
   public fun ByteArray.singleOrNull(): Byte? {
      return if (`$this$singleOrNull`.length == 1) `$this$singleOrNull`[0] else null;
   }

   @JvmStatic
   public fun ShortArray.singleOrNull(): Short? {
      return if (`$this$singleOrNull`.length == 1) `$this$singleOrNull`[0] else null;
   }

   @JvmStatic
   public fun IntArray.singleOrNull(): Int? {
      return if (`$this$singleOrNull`.length == 1) `$this$singleOrNull`[0] else null;
   }

   @JvmStatic
   public fun LongArray.singleOrNull(): Long? {
      return if (`$this$singleOrNull`.length == 1) `$this$singleOrNull`[0] else null;
   }

   @JvmStatic
   public fun FloatArray.singleOrNull(): Float? {
      return if (`$this$singleOrNull`.length == 1) `$this$singleOrNull`[0] else null;
   }

   @JvmStatic
   public fun DoubleArray.singleOrNull(): Double? {
      return if (`$this$singleOrNull`.length == 1) `$this$singleOrNull`[0] else null;
   }

   @JvmStatic
   public fun BooleanArray.singleOrNull(): Boolean? {
      return if (`$this$singleOrNull`.length == 1) `$this$singleOrNull`[0] else null;
   }

   @JvmStatic
   public fun CharArray.singleOrNull(): Char? {
      return if (`$this$singleOrNull`.length == 1) `$this$singleOrNull`[0] else null;
   }

   @JvmStatic
   public inline fun <T> Array<out T>.singleOrNull(predicate: (T) -> Boolean): T? {
      var single: Any = null;
      var found: Boolean = false;

      for (Object element : $this$singleOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = element;
            found = true;
         }
      }

      return (T)(if (!found) null else single);
   }

   @JvmStatic
   public inline fun ByteArray.singleOrNull(predicate: (Byte) -> Boolean): Byte? {
      var single: java.lang.Byte = null;
      var found: Boolean = false;

      for (byte element : $this$singleOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = element;
            found = true;
         }
      }

      return if (!found) null else single;
   }

   @JvmStatic
   public inline fun ShortArray.singleOrNull(predicate: (Short) -> Boolean): Short? {
      var single: java.lang.Short = null;
      var found: Boolean = false;

      for (short element : $this$singleOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = element;
            found = true;
         }
      }

      return if (!found) null else single;
   }

   @JvmStatic
   public inline fun IntArray.singleOrNull(predicate: (Int) -> Boolean): Int? {
      var single: Int = null;
      var found: Boolean = false;

      for (int element : $this$singleOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = element;
            found = true;
         }
      }

      return if (!found) null else single;
   }

   @JvmStatic
   public inline fun LongArray.singleOrNull(predicate: (Long) -> Boolean): Long? {
      var single: java.lang.Long = null;
      var found: Boolean = false;

      for (long element : $this$singleOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = element;
            found = true;
         }
      }

      return if (!found) null else single;
   }

   @JvmStatic
   public inline fun FloatArray.singleOrNull(predicate: (Float) -> Boolean): Float? {
      var single: java.lang.Float = null;
      var found: Boolean = false;

      for (float element : $this$singleOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = element;
            found = true;
         }
      }

      return if (!found) null else single;
   }

   @JvmStatic
   public inline fun DoubleArray.singleOrNull(predicate: (Double) -> Boolean): Double? {
      var single: java.lang.Double = null;
      var found: Boolean = false;

      for (double element : $this$singleOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = element;
            found = true;
         }
      }

      return if (!found) null else single;
   }

   @JvmStatic
   public inline fun BooleanArray.singleOrNull(predicate: (Boolean) -> Boolean): Boolean? {
      var single: java.lang.Boolean = null;
      var found: Boolean = false;

      for (boolean element : $this$singleOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = element;
            found = true;
         }
      }

      return if (!found) null else single;
   }

   @JvmStatic
   public inline fun CharArray.singleOrNull(predicate: (Char) -> Boolean): Char? {
      var single: Character = null;
      var found: Boolean = false;

      for (char element : $this$singleOrNull) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            if (found) {
               return null;
            }

            single = element;
            found = true;
         }
      }

      return if (!found) null else single;
   }

   @JvmStatic
   public fun <T> Array<out T>.drop(n: Int): List<T> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return (java.util.List<T>)ArraysKt.takeLast(`$this$drop`, RangesKt.coerceAtLeast(`$this$drop`.length - n, 0));
      }
   }

   @JvmStatic
   public fun ByteArray.drop(n: Int): List<Byte> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.takeLast(`$this$drop`, RangesKt.coerceAtLeast(`$this$drop`.length - n, 0));
      }
   }

   @JvmStatic
   public fun ShortArray.drop(n: Int): List<Short> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.takeLast(`$this$drop`, RangesKt.coerceAtLeast(`$this$drop`.length - n, 0));
      }
   }

   @JvmStatic
   public fun IntArray.drop(n: Int): List<Int> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.takeLast(`$this$drop`, RangesKt.coerceAtLeast(`$this$drop`.length - n, 0));
      }
   }

   @JvmStatic
   public fun LongArray.drop(n: Int): List<Long> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.takeLast(`$this$drop`, RangesKt.coerceAtLeast(`$this$drop`.length - n, 0));
      }
   }

   @JvmStatic
   public fun FloatArray.drop(n: Int): List<Float> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.takeLast(`$this$drop`, RangesKt.coerceAtLeast(`$this$drop`.length - n, 0));
      }
   }

   @JvmStatic
   public fun DoubleArray.drop(n: Int): List<Double> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.takeLast(`$this$drop`, RangesKt.coerceAtLeast(`$this$drop`.length - n, 0));
      }
   }

   @JvmStatic
   public fun BooleanArray.drop(n: Int): List<Boolean> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.takeLast(`$this$drop`, RangesKt.coerceAtLeast(`$this$drop`.length - n, 0));
      }
   }

   @JvmStatic
   public fun CharArray.drop(n: Int): List<Char> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.takeLast(`$this$drop`, RangesKt.coerceAtLeast(`$this$drop`.length - n, 0));
      }
   }

   @JvmStatic
   public fun <T> Array<out T>.dropLast(n: Int): List<T> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return (java.util.List<T>)ArraysKt.take(`$this$dropLast`, RangesKt.coerceAtLeast(`$this$dropLast`.length - n, 0));
      }
   }

   @JvmStatic
   public fun ByteArray.dropLast(n: Int): List<Byte> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.take(`$this$dropLast`, RangesKt.coerceAtLeast(`$this$dropLast`.length - n, 0));
      }
   }

   @JvmStatic
   public fun ShortArray.dropLast(n: Int): List<Short> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.take(`$this$dropLast`, RangesKt.coerceAtLeast(`$this$dropLast`.length - n, 0));
      }
   }

   @JvmStatic
   public fun IntArray.dropLast(n: Int): List<Int> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.take(`$this$dropLast`, RangesKt.coerceAtLeast(`$this$dropLast`.length - n, 0));
      }
   }

   @JvmStatic
   public fun LongArray.dropLast(n: Int): List<Long> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.take(`$this$dropLast`, RangesKt.coerceAtLeast(`$this$dropLast`.length - n, 0));
      }
   }

   @JvmStatic
   public fun FloatArray.dropLast(n: Int): List<Float> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.take(`$this$dropLast`, RangesKt.coerceAtLeast(`$this$dropLast`.length - n, 0));
      }
   }

   @JvmStatic
   public fun DoubleArray.dropLast(n: Int): List<Double> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.take(`$this$dropLast`, RangesKt.coerceAtLeast(`$this$dropLast`.length - n, 0));
      }
   }

   @JvmStatic
   public fun BooleanArray.dropLast(n: Int): List<Boolean> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.take(`$this$dropLast`, RangesKt.coerceAtLeast(`$this$dropLast`.length - n, 0));
      }
   }

   @JvmStatic
   public fun CharArray.dropLast(n: Int): List<Char> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else {
         return ArraysKt.take(`$this$dropLast`, RangesKt.coerceAtLeast(`$this$dropLast`.length - n, 0));
      }
   }

   @JvmStatic
   public inline fun <T> Array<out T>.dropLastWhile(predicate: (T) -> Boolean): List<T> {
      for (int index = ArraysKt.getLastIndex($this$dropLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$dropLastWhile`[index]) as java.lang.Boolean) {
            return (java.util.List<T>)ArraysKt.take(`$this$dropLastWhile`, index + 1);
         }
      }

      return CollectionsKt.emptyList();
   }

   @JvmStatic
   public inline fun ByteArray.dropLastWhile(predicate: (Byte) -> Boolean): List<Byte> {
      for (int index = ArraysKt.getLastIndex($this$dropLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$dropLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.take(`$this$dropLastWhile`, index + 1);
         }
      }

      return CollectionsKt.emptyList();
   }

   @JvmStatic
   public inline fun ShortArray.dropLastWhile(predicate: (Short) -> Boolean): List<Short> {
      for (int index = ArraysKt.getLastIndex($this$dropLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$dropLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.take(`$this$dropLastWhile`, index + 1);
         }
      }

      return CollectionsKt.emptyList();
   }

   @JvmStatic
   public inline fun IntArray.dropLastWhile(predicate: (Int) -> Boolean): List<Int> {
      for (int index = ArraysKt.getLastIndex($this$dropLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$dropLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.take(`$this$dropLastWhile`, index + 1);
         }
      }

      return CollectionsKt.emptyList();
   }

   @JvmStatic
   public inline fun LongArray.dropLastWhile(predicate: (Long) -> Boolean): List<Long> {
      for (int index = ArraysKt.getLastIndex($this$dropLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$dropLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.take(`$this$dropLastWhile`, index + 1);
         }
      }

      return CollectionsKt.emptyList();
   }

   @JvmStatic
   public inline fun FloatArray.dropLastWhile(predicate: (Float) -> Boolean): List<Float> {
      for (int index = ArraysKt.getLastIndex($this$dropLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$dropLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.take(`$this$dropLastWhile`, index + 1);
         }
      }

      return CollectionsKt.emptyList();
   }

   @JvmStatic
   public inline fun DoubleArray.dropLastWhile(predicate: (Double) -> Boolean): List<Double> {
      for (int index = ArraysKt.getLastIndex($this$dropLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$dropLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.take(`$this$dropLastWhile`, index + 1);
         }
      }

      return CollectionsKt.emptyList();
   }

   @JvmStatic
   public inline fun BooleanArray.dropLastWhile(predicate: (Boolean) -> Boolean): List<Boolean> {
      for (int index = ArraysKt.getLastIndex($this$dropLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$dropLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.take(`$this$dropLastWhile`, index + 1);
         }
      }

      return CollectionsKt.emptyList();
   }

   @JvmStatic
   public inline fun CharArray.dropLastWhile(predicate: (Char) -> Boolean): List<Char> {
      for (int index = ArraysKt.getLastIndex($this$dropLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$dropLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.take(`$this$dropLastWhile`, index + 1);
         }
      }

      return CollectionsKt.emptyList();
   }

   @JvmStatic
   public inline fun <T> Array<out T>.dropWhile(predicate: (T) -> Boolean): List<T> {
      var yielding: Boolean = false;
      val list: ArrayList = new ArrayList();

      for (Object item : $this$dropWhile) {
         if (yielding) {
            list.add(item);
         } else if (!predicate.invoke(item) as java.lang.Boolean) {
            list.add(item);
            yielding = true;
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun ByteArray.dropWhile(predicate: (Byte) -> Boolean): List<Byte> {
      var yielding: Boolean = false;
      val list: ArrayList = new ArrayList();

      for (byte item : $this$dropWhile) {
         if (yielding) {
            list.add(item);
         } else if (!predicate.invoke(item) as java.lang.Boolean) {
            list.add(item);
            yielding = true;
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun ShortArray.dropWhile(predicate: (Short) -> Boolean): List<Short> {
      var yielding: Boolean = false;
      val list: ArrayList = new ArrayList();

      for (short item : $this$dropWhile) {
         if (yielding) {
            list.add(item);
         } else if (!predicate.invoke(item) as java.lang.Boolean) {
            list.add(item);
            yielding = true;
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun IntArray.dropWhile(predicate: (Int) -> Boolean): List<Int> {
      var yielding: Boolean = false;
      val list: ArrayList = new ArrayList();

      for (int item : $this$dropWhile) {
         if (yielding) {
            list.add(item);
         } else if (!predicate.invoke(item) as java.lang.Boolean) {
            list.add(item);
            yielding = true;
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun LongArray.dropWhile(predicate: (Long) -> Boolean): List<Long> {
      var yielding: Boolean = false;
      val list: ArrayList = new ArrayList();

      for (long item : $this$dropWhile) {
         if (yielding) {
            list.add(item);
         } else if (!predicate.invoke(item) as java.lang.Boolean) {
            list.add(item);
            yielding = true;
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun FloatArray.dropWhile(predicate: (Float) -> Boolean): List<Float> {
      var yielding: Boolean = false;
      val list: ArrayList = new ArrayList();

      for (float item : $this$dropWhile) {
         if (yielding) {
            list.add(item);
         } else if (!predicate.invoke(item) as java.lang.Boolean) {
            list.add(item);
            yielding = true;
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun DoubleArray.dropWhile(predicate: (Double) -> Boolean): List<Double> {
      var yielding: Boolean = false;
      val list: ArrayList = new ArrayList();

      for (double item : $this$dropWhile) {
         if (yielding) {
            list.add(item);
         } else if (!predicate.invoke(item) as java.lang.Boolean) {
            list.add(item);
            yielding = true;
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun BooleanArray.dropWhile(predicate: (Boolean) -> Boolean): List<Boolean> {
      var yielding: Boolean = false;
      val list: ArrayList = new ArrayList();

      for (boolean item : $this$dropWhile) {
         if (yielding) {
            list.add(item);
         } else if (!predicate.invoke(item) as java.lang.Boolean) {
            list.add(item);
            yielding = true;
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun CharArray.dropWhile(predicate: (Char) -> Boolean): List<Char> {
      var yielding: Boolean = false;
      val list: ArrayList = new ArrayList();

      for (char item : $this$dropWhile) {
         if (yielding) {
            list.add(item);
         } else if (!predicate.invoke(item) as java.lang.Boolean) {
            list.add(item);
            yielding = true;
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun <T> Array<out T>.filter(predicate: (T) -> Boolean): List<T> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv : $this$filter) {
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<T>;
   }

   @JvmStatic
   public inline fun ByteArray.filter(predicate: (Byte) -> Boolean): List<Byte> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (byte element$iv : $this$filter) {
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Byte>;
   }

   @JvmStatic
   public inline fun ShortArray.filter(predicate: (Short) -> Boolean): List<Short> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (short element$iv : $this$filter) {
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Short>;
   }

   @JvmStatic
   public inline fun IntArray.filter(predicate: (Int) -> Boolean): List<Int> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (int element$iv : $this$filter) {
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<Int>;
   }

   @JvmStatic
   public inline fun LongArray.filter(predicate: (Long) -> Boolean): List<Long> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (long element$iv : $this$filter) {
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Long>;
   }

   @JvmStatic
   public inline fun FloatArray.filter(predicate: (Float) -> Boolean): List<Float> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (float element$iv : $this$filter) {
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Float>;
   }

   @JvmStatic
   public inline fun DoubleArray.filter(predicate: (Double) -> Boolean): List<Double> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (double element$iv : $this$filter) {
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Double>;
   }

   @JvmStatic
   public inline fun BooleanArray.filter(predicate: (Boolean) -> Boolean): List<Boolean> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (boolean element$iv : $this$filter) {
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Boolean>;
   }

   @JvmStatic
   public inline fun CharArray.filter(predicate: (Char) -> Boolean): List<Char> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (char element$iv : $this$filter) {
         if (predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<Character>;
   }

   @JvmStatic
   public inline fun <T> Array<out T>.filterIndexed(predicate: (Int, T) -> Boolean): List<T> {
      val `destination$iv`: java.util.Collection = new ArrayList();
      val `index$iv$iv`: Int = 0;

      for (Object item$iv$iv : $this$filterIndexed) {
         if (predicate.invoke(`index$iv$iv`++, `item$iv$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`item$iv$iv`);
         }
      }

      return `destination$iv` as MutableList<T>;
   }

   @JvmStatic
   public inline fun ByteArray.filterIndexed(predicate: (Int, Byte) -> Boolean): List<Byte> {
      val `destination$iv`: java.util.Collection = new ArrayList();
      val `index$iv$iv`: Int = 0;

      for (byte item$iv$iv : $this$filterIndexed) {
         if (predicate.invoke(`index$iv$iv`++, `item$iv$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`item$iv$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Byte>;
   }

   @JvmStatic
   public inline fun ShortArray.filterIndexed(predicate: (Int, Short) -> Boolean): List<Short> {
      val `destination$iv`: java.util.Collection = new ArrayList();
      val `index$iv$iv`: Int = 0;

      for (short item$iv$iv : $this$filterIndexed) {
         if (predicate.invoke(`index$iv$iv`++, `item$iv$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`item$iv$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Short>;
   }

   @JvmStatic
   public inline fun IntArray.filterIndexed(predicate: (Int, Int) -> Boolean): List<Int> {
      val `destination$iv`: java.util.Collection = new ArrayList();
      val `index$iv$iv`: Int = 0;

      for (int item$iv$iv : $this$filterIndexed) {
         if (predicate.invoke(`index$iv$iv`++, `item$iv$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`item$iv$iv`);
         }
      }

      return `destination$iv` as MutableList<Int>;
   }

   @JvmStatic
   public inline fun LongArray.filterIndexed(predicate: (Int, Long) -> Boolean): List<Long> {
      val `destination$iv`: java.util.Collection = new ArrayList();
      val `index$iv$iv`: Int = 0;

      for (long item$iv$iv : $this$filterIndexed) {
         if (predicate.invoke(`index$iv$iv`++, `item$iv$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`item$iv$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Long>;
   }

   @JvmStatic
   public inline fun FloatArray.filterIndexed(predicate: (Int, Float) -> Boolean): List<Float> {
      val `destination$iv`: java.util.Collection = new ArrayList();
      val `index$iv$iv`: Int = 0;

      for (float item$iv$iv : $this$filterIndexed) {
         if (predicate.invoke(`index$iv$iv`++, `item$iv$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`item$iv$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Float>;
   }

   @JvmStatic
   public inline fun DoubleArray.filterIndexed(predicate: (Int, Double) -> Boolean): List<Double> {
      val `destination$iv`: java.util.Collection = new ArrayList();
      val `index$iv$iv`: Int = 0;

      for (double item$iv$iv : $this$filterIndexed) {
         if (predicate.invoke(`index$iv$iv`++, `item$iv$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`item$iv$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Double>;
   }

   @JvmStatic
   public inline fun BooleanArray.filterIndexed(predicate: (Int, Boolean) -> Boolean): List<Boolean> {
      val `destination$iv`: java.util.Collection = new ArrayList();
      val `index$iv$iv`: Int = 0;

      for (boolean item$iv$iv : $this$filterIndexed) {
         if (predicate.invoke(`index$iv$iv`++, `item$iv$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`item$iv$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Boolean>;
   }

   @JvmStatic
   public inline fun CharArray.filterIndexed(predicate: (Int, Char) -> Boolean): List<Char> {
      val `destination$iv`: java.util.Collection = new ArrayList();
      val `index$iv$iv`: Int = 0;

      for (char item$iv$iv : $this$filterIndexed) {
         if (predicate.invoke(`index$iv$iv`++, `item$iv$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`item$iv$iv`);
         }
      }

      return `destination$iv` as MutableList<Character>;
   }

   @JvmStatic
   public inline fun <T, C : MutableCollection<in T>> Array<out T>.filterIndexedTo(destination: C, predicate: (Int, T) -> Boolean): C {
      val `index$iv`: Int = 0;

      for (Object item$iv : $this$filterIndexedTo) {
         if (predicate.invoke(`index$iv`++, `item$iv`) as java.lang.Boolean) {
            destination.add(`item$iv`);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Byte>> ByteArray.filterIndexedTo(destination: C, predicate: (Int, Byte) -> Boolean): C {
      val `index$iv`: Int = 0;

      for (byte item$iv : $this$filterIndexedTo) {
         if (predicate.invoke(`index$iv`++, `item$iv`) as java.lang.Boolean) {
            destination.add(`item$iv`);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Short>> ShortArray.filterIndexedTo(destination: C, predicate: (Int, Short) -> Boolean): C {
      val `index$iv`: Int = 0;

      for (short item$iv : $this$filterIndexedTo) {
         if (predicate.invoke(`index$iv`++, `item$iv`) as java.lang.Boolean) {
            destination.add(`item$iv`);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Int>> IntArray.filterIndexedTo(destination: C, predicate: (Int, Int) -> Boolean): C {
      val `index$iv`: Int = 0;

      for (int item$iv : $this$filterIndexedTo) {
         if (predicate.invoke(`index$iv`++, `item$iv`) as java.lang.Boolean) {
            destination.add(`item$iv`);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Long>> LongArray.filterIndexedTo(destination: C, predicate: (Int, Long) -> Boolean): C {
      val `index$iv`: Int = 0;

      for (long item$iv : $this$filterIndexedTo) {
         if (predicate.invoke(`index$iv`++, `item$iv`) as java.lang.Boolean) {
            destination.add(`item$iv`);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Float>> FloatArray.filterIndexedTo(destination: C, predicate: (Int, Float) -> Boolean): C {
      val `index$iv`: Int = 0;

      for (float item$iv : $this$filterIndexedTo) {
         if (predicate.invoke(`index$iv`++, `item$iv`) as java.lang.Boolean) {
            destination.add(`item$iv`);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Double>> DoubleArray.filterIndexedTo(destination: C, predicate: (Int, Double) -> Boolean): C {
      val `index$iv`: Int = 0;

      for (double item$iv : $this$filterIndexedTo) {
         if (predicate.invoke(`index$iv`++, `item$iv`) as java.lang.Boolean) {
            destination.add(`item$iv`);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Boolean>> BooleanArray.filterIndexedTo(destination: C, predicate: (Int, Boolean) -> Boolean): C {
      val `index$iv`: Int = 0;

      for (boolean item$iv : $this$filterIndexedTo) {
         if (predicate.invoke(`index$iv`++, `item$iv`) as java.lang.Boolean) {
            destination.add(`item$iv`);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Char>> CharArray.filterIndexedTo(destination: C, predicate: (Int, Char) -> Boolean): C {
      val `index$iv`: Int = 0;

      for (char item$iv : $this$filterIndexedTo) {
         if (predicate.invoke(`index$iv`++, `item$iv`) as java.lang.Boolean) {
            destination.add(`item$iv`);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T> Array<out T>.filterNot(predicate: (T) -> Boolean): List<T> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv : $this$filterNot) {
         if (!predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<T>;
   }

   @JvmStatic
   public inline fun ByteArray.filterNot(predicate: (Byte) -> Boolean): List<Byte> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (byte element$iv : $this$filterNot) {
         if (!predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Byte>;
   }

   @JvmStatic
   public inline fun ShortArray.filterNot(predicate: (Short) -> Boolean): List<Short> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (short element$iv : $this$filterNot) {
         if (!predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Short>;
   }

   @JvmStatic
   public inline fun IntArray.filterNot(predicate: (Int) -> Boolean): List<Int> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (int element$iv : $this$filterNot) {
         if (!predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<Int>;
   }

   @JvmStatic
   public inline fun LongArray.filterNot(predicate: (Long) -> Boolean): List<Long> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (long element$iv : $this$filterNot) {
         if (!predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Long>;
   }

   @JvmStatic
   public inline fun FloatArray.filterNot(predicate: (Float) -> Boolean): List<Float> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (float element$iv : $this$filterNot) {
         if (!predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Float>;
   }

   @JvmStatic
   public inline fun DoubleArray.filterNot(predicate: (Double) -> Boolean): List<Double> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (double element$iv : $this$filterNot) {
         if (!predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Double>;
   }

   @JvmStatic
   public inline fun BooleanArray.filterNot(predicate: (Boolean) -> Boolean): List<Boolean> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (boolean element$iv : $this$filterNot) {
         if (!predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<java.lang.Boolean>;
   }

   @JvmStatic
   public inline fun CharArray.filterNot(predicate: (Char) -> Boolean): List<Char> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (char element$iv : $this$filterNot) {
         if (!predicate.invoke(`element$iv`) as java.lang.Boolean) {
            `destination$iv`.add(`element$iv`);
         }
      }

      return `destination$iv` as MutableList<Character>;
   }

   @JvmStatic
   public fun <T : Any> Array<out T?>.filterNotNull(): List<T> {
      return ArraysKt.filterNotNullTo(`$this$filterNotNull`, new ArrayList()) as MutableList<T>;
   }

   @JvmStatic
   public fun <C : MutableCollection<in T>, T : Any> Array<out T?>.filterNotNullTo(destination: C): C {
      for (Object element : $this$filterNotNullTo) {
         if (element != null) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, C : MutableCollection<in T>> Array<out T>.filterNotTo(destination: C, predicate: (T) -> Boolean): C {
      for (Object element : $this$filterNotTo) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Byte>> ByteArray.filterNotTo(destination: C, predicate: (Byte) -> Boolean): C {
      for (byte element : $this$filterNotTo) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Short>> ShortArray.filterNotTo(destination: C, predicate: (Short) -> Boolean): C {
      for (short element : $this$filterNotTo) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Int>> IntArray.filterNotTo(destination: C, predicate: (Int) -> Boolean): C {
      for (int element : $this$filterNotTo) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Long>> LongArray.filterNotTo(destination: C, predicate: (Long) -> Boolean): C {
      for (long element : $this$filterNotTo) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Float>> FloatArray.filterNotTo(destination: C, predicate: (Float) -> Boolean): C {
      for (float element : $this$filterNotTo) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Double>> DoubleArray.filterNotTo(destination: C, predicate: (Double) -> Boolean): C {
      for (double element : $this$filterNotTo) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Boolean>> BooleanArray.filterNotTo(destination: C, predicate: (Boolean) -> Boolean): C {
      for (boolean element : $this$filterNotTo) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Char>> CharArray.filterNotTo(destination: C, predicate: (Char) -> Boolean): C {
      for (char element : $this$filterNotTo) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, C : MutableCollection<in T>> Array<out T>.filterTo(destination: C, predicate: (T) -> Boolean): C {
      for (Object element : $this$filterTo) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Byte>> ByteArray.filterTo(destination: C, predicate: (Byte) -> Boolean): C {
      for (byte element : $this$filterTo) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Short>> ShortArray.filterTo(destination: C, predicate: (Short) -> Boolean): C {
      for (short element : $this$filterTo) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Int>> IntArray.filterTo(destination: C, predicate: (Int) -> Boolean): C {
      for (int element : $this$filterTo) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Long>> LongArray.filterTo(destination: C, predicate: (Long) -> Boolean): C {
      for (long element : $this$filterTo) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Float>> FloatArray.filterTo(destination: C, predicate: (Float) -> Boolean): C {
      for (float element : $this$filterTo) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Double>> DoubleArray.filterTo(destination: C, predicate: (Double) -> Boolean): C {
      for (double element : $this$filterTo) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Boolean>> BooleanArray.filterTo(destination: C, predicate: (Boolean) -> Boolean): C {
      for (boolean element : $this$filterTo) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <C : MutableCollection<in Char>> CharArray.filterTo(destination: C, predicate: (Char) -> Boolean): C {
      for (char element : $this$filterTo) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            destination.add(element);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <T> Array<out T>.slice(indices: IntRange): List<T> {
      return if (indices.isEmpty())
         CollectionsKt.emptyList()
         else
         ArraysKt.asList(ArraysKt.copyOfRange(`$this$slice`, indices.getStart(), indices.getEndInclusive() + 1));
   }

   @JvmStatic
   public fun ByteArray.slice(indices: IntRange): List<Byte> {
      return if (indices.isEmpty())
         CollectionsKt.emptyList()
         else
         ArraysKt.asList(ArraysKt.copyOfRange(`$this$slice`, indices.getStart(), indices.getEndInclusive() + 1));
   }

   @JvmStatic
   public fun ShortArray.slice(indices: IntRange): List<Short> {
      return if (indices.isEmpty())
         CollectionsKt.emptyList()
         else
         ArraysKt.asList(ArraysKt.copyOfRange(`$this$slice`, indices.getStart(), indices.getEndInclusive() + 1));
   }

   @JvmStatic
   public fun IntArray.slice(indices: IntRange): List<Int> {
      return if (indices.isEmpty())
         CollectionsKt.emptyList()
         else
         ArraysKt.asList(ArraysKt.copyOfRange(`$this$slice`, indices.getStart(), indices.getEndInclusive() + 1));
   }

   @JvmStatic
   public fun LongArray.slice(indices: IntRange): List<Long> {
      return if (indices.isEmpty())
         CollectionsKt.emptyList()
         else
         ArraysKt.asList(ArraysKt.copyOfRange(`$this$slice`, indices.getStart(), indices.getEndInclusive() + 1));
   }

   @JvmStatic
   public fun FloatArray.slice(indices: IntRange): List<Float> {
      return if (indices.isEmpty())
         CollectionsKt.emptyList()
         else
         ArraysKt.asList(ArraysKt.copyOfRange(`$this$slice`, indices.getStart(), indices.getEndInclusive() + 1));
   }

   @JvmStatic
   public fun DoubleArray.slice(indices: IntRange): List<Double> {
      return if (indices.isEmpty())
         CollectionsKt.emptyList()
         else
         ArraysKt.asList(ArraysKt.copyOfRange(`$this$slice`, indices.getStart(), indices.getEndInclusive() + 1));
   }

   @JvmStatic
   public fun BooleanArray.slice(indices: IntRange): List<Boolean> {
      return if (indices.isEmpty())
         CollectionsKt.emptyList()
         else
         ArraysKt.asList(ArraysKt.copyOfRange(`$this$slice`, indices.getStart(), indices.getEndInclusive() + 1));
   }

   @JvmStatic
   public fun CharArray.slice(indices: IntRange): List<Char> {
      return if (indices.isEmpty())
         CollectionsKt.emptyList()
         else
         ArraysKt.asList(ArraysKt.copyOfRange(`$this$slice`, indices.getStart(), indices.getEndInclusive() + 1));
   }

   @JvmStatic
   public fun <T> Array<out T>.slice(indices: Iterable<Int>): List<T> {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: ArrayList = new ArrayList(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            list.add(`$this$slice`[(var4.next() as java.lang.Number).intValue()]);
         }

         return list;
      }
   }

   @JvmStatic
   public fun ByteArray.slice(indices: Iterable<Int>): List<Byte> {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: ArrayList = new ArrayList(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            list.add(`$this$slice`[(var4.next() as java.lang.Number).intValue()]);
         }

         return list;
      }
   }

   @JvmStatic
   public fun ShortArray.slice(indices: Iterable<Int>): List<Short> {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: ArrayList = new ArrayList(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            list.add(`$this$slice`[(var4.next() as java.lang.Number).intValue()]);
         }

         return list;
      }
   }

   @JvmStatic
   public fun IntArray.slice(indices: Iterable<Int>): List<Int> {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: ArrayList = new ArrayList(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            list.add(`$this$slice`[(var4.next() as java.lang.Number).intValue()]);
         }

         return list;
      }
   }

   @JvmStatic
   public fun LongArray.slice(indices: Iterable<Int>): List<Long> {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: ArrayList = new ArrayList(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            list.add(`$this$slice`[(var4.next() as java.lang.Number).intValue()]);
         }

         return list;
      }
   }

   @JvmStatic
   public fun FloatArray.slice(indices: Iterable<Int>): List<Float> {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: ArrayList = new ArrayList(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            list.add(`$this$slice`[(var4.next() as java.lang.Number).intValue()]);
         }

         return list;
      }
   }

   @JvmStatic
   public fun DoubleArray.slice(indices: Iterable<Int>): List<Double> {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: ArrayList = new ArrayList(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            list.add(`$this$slice`[(var4.next() as java.lang.Number).intValue()]);
         }

         return list;
      }
   }

   @JvmStatic
   public fun BooleanArray.slice(indices: Iterable<Int>): List<Boolean> {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: ArrayList = new ArrayList(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            list.add(`$this$slice`[(var4.next() as java.lang.Number).intValue()]);
         }

         return list;
      }
   }

   @JvmStatic
   public fun CharArray.slice(indices: Iterable<Int>): List<Char> {
      val size: Int = CollectionsKt.collectionSizeOrDefault(indices, 10);
      if (size == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: ArrayList = new ArrayList(size);
         val var4: java.util.Iterator = indices.iterator();

         while (var4.hasNext()) {
            list.add(`$this$slice`[(var4.next() as java.lang.Number).intValue()]);
         }

         return list;
      }
   }

   @JvmStatic
   public fun <T> Array<T>.sliceArray(indices: Collection<Int>): Array<T> {
      val result: Array<Any> = ArraysKt.arrayOfNulls(`$this$sliceArray`, indices.size());
      var targetIndex: Int = 0;
      val var4: java.util.Iterator = indices.iterator();

      while (var4.hasNext()) {
         result[targetIndex++] = `$this$sliceArray`[(var4.next() as java.lang.Number).intValue()];
      }

      return (T[])result;
   }

   @JvmStatic
   public fun ByteArray.sliceArray(indices: Collection<Int>): ByteArray {
      val result: ByteArray = new byte[indices.size()];
      var targetIndex: Int = 0;
      val var4: java.util.Iterator = indices.iterator();

      while (var4.hasNext()) {
         result[targetIndex++] = `$this$sliceArray`[(var4.next() as java.lang.Number).intValue()];
      }

      return result;
   }

   @JvmStatic
   public fun ShortArray.sliceArray(indices: Collection<Int>): ShortArray {
      val result: ShortArray = new short[indices.size()];
      var targetIndex: Int = 0;
      val var4: java.util.Iterator = indices.iterator();

      while (var4.hasNext()) {
         result[targetIndex++] = `$this$sliceArray`[(var4.next() as java.lang.Number).intValue()];
      }

      return result;
   }

   @JvmStatic
   public fun IntArray.sliceArray(indices: Collection<Int>): IntArray {
      val result: IntArray = new int[indices.size()];
      var targetIndex: Int = 0;
      val var4: java.util.Iterator = indices.iterator();

      while (var4.hasNext()) {
         result[targetIndex++] = `$this$sliceArray`[(var4.next() as java.lang.Number).intValue()];
      }

      return result;
   }

   @JvmStatic
   public fun LongArray.sliceArray(indices: Collection<Int>): LongArray {
      val result: LongArray = new long[indices.size()];
      var targetIndex: Int = 0;
      val var4: java.util.Iterator = indices.iterator();

      while (var4.hasNext()) {
         result[targetIndex++] = `$this$sliceArray`[(var4.next() as java.lang.Number).intValue()];
      }

      return result;
   }

   @JvmStatic
   public fun FloatArray.sliceArray(indices: Collection<Int>): FloatArray {
      val result: FloatArray = new float[indices.size()];
      var targetIndex: Int = 0;
      val var4: java.util.Iterator = indices.iterator();

      while (var4.hasNext()) {
         result[targetIndex++] = `$this$sliceArray`[(var4.next() as java.lang.Number).intValue()];
      }

      return result;
   }

   @JvmStatic
   public fun DoubleArray.sliceArray(indices: Collection<Int>): DoubleArray {
      val result: DoubleArray = new double[indices.size()];
      var targetIndex: Int = 0;
      val var4: java.util.Iterator = indices.iterator();

      while (var4.hasNext()) {
         result[targetIndex++] = `$this$sliceArray`[(var4.next() as java.lang.Number).intValue()];
      }

      return result;
   }

   @JvmStatic
   public fun BooleanArray.sliceArray(indices: Collection<Int>): BooleanArray {
      val result: BooleanArray = new boolean[indices.size()];
      var targetIndex: Int = 0;
      val var4: java.util.Iterator = indices.iterator();

      while (var4.hasNext()) {
         result[targetIndex++] = `$this$sliceArray`[(var4.next() as java.lang.Number).intValue()];
      }

      return result;
   }

   @JvmStatic
   public fun CharArray.sliceArray(indices: Collection<Int>): CharArray {
      val result: CharArray = new char[indices.size()];
      var targetIndex: Int = 0;
      val var4: java.util.Iterator = indices.iterator();

      while (var4.hasNext()) {
         result[targetIndex++] = `$this$sliceArray`[(var4.next() as java.lang.Number).intValue()];
      }

      return result;
   }

   @JvmStatic
   public fun <T> Array<T>.sliceArray(indices: IntRange): Array<T> {
      return (T[])(if (indices.isEmpty())
         ArraysKt.copyOfRange(`$this$sliceArray`, 0, 0)
         else
         ArraysKt.copyOfRange(`$this$sliceArray`, indices.getStart(), indices.getEndInclusive() + 1));
   }

   @JvmStatic
   public fun ByteArray.sliceArray(indices: IntRange): ByteArray {
      return if (indices.isEmpty()) new byte[0] else ArraysKt.copyOfRange(`$this$sliceArray`, indices.getStart(), indices.getEndInclusive() + 1);
   }

   @JvmStatic
   public fun ShortArray.sliceArray(indices: IntRange): ShortArray {
      return if (indices.isEmpty()) new short[0] else ArraysKt.copyOfRange(`$this$sliceArray`, indices.getStart(), indices.getEndInclusive() + 1);
   }

   @JvmStatic
   public fun IntArray.sliceArray(indices: IntRange): IntArray {
      return if (indices.isEmpty()) new int[0] else ArraysKt.copyOfRange(`$this$sliceArray`, indices.getStart(), indices.getEndInclusive() + 1);
   }

   @JvmStatic
   public fun LongArray.sliceArray(indices: IntRange): LongArray {
      return if (indices.isEmpty()) new long[0] else ArraysKt.copyOfRange(`$this$sliceArray`, indices.getStart(), indices.getEndInclusive() + 1);
   }

   @JvmStatic
   public fun FloatArray.sliceArray(indices: IntRange): FloatArray {
      return if (indices.isEmpty()) new float[0] else ArraysKt.copyOfRange(`$this$sliceArray`, indices.getStart(), indices.getEndInclusive() + 1);
   }

   @JvmStatic
   public fun DoubleArray.sliceArray(indices: IntRange): DoubleArray {
      return if (indices.isEmpty()) new double[0] else ArraysKt.copyOfRange(`$this$sliceArray`, indices.getStart(), indices.getEndInclusive() + 1);
   }

   @JvmStatic
   public fun BooleanArray.sliceArray(indices: IntRange): BooleanArray {
      return if (indices.isEmpty()) new boolean[0] else ArraysKt.copyOfRange(`$this$sliceArray`, indices.getStart(), indices.getEndInclusive() + 1);
   }

   @JvmStatic
   public fun CharArray.sliceArray(indices: IntRange): CharArray {
      return if (indices.isEmpty()) new char[0] else ArraysKt.copyOfRange(`$this$sliceArray`, indices.getStart(), indices.getEndInclusive() + 1);
   }

   @JvmStatic
   public fun <T> Array<out T>.take(n: Int): List<T> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else if (n >= `$this$take`.length) {
         return (java.util.List<T>)ArraysKt.toList(`$this$take`);
      } else if (n == 1) {
         return (java.util.List<T>)CollectionsKt.listOf(`$this$take`[0]);
      } else {
         val count: Int = 0;
         val list: ArrayList = new ArrayList(n);

         for (Object item : $this$take) {
            list.add(item);
            if (++count == n) {
               break;
            }
         }

         return list;
      }
   }

   @JvmStatic
   public fun ByteArray.take(n: Int): List<Byte> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else if (n >= `$this$take`.length) {
         return ArraysKt.toList(`$this$take`);
      } else if (n == 1) {
         return CollectionsKt.listOf(`$this$take`[0]);
      } else {
         val count: Int = 0;
         val list: ArrayList = new ArrayList(n);

         for (byte item : $this$take) {
            list.add(item);
            if (++count == n) {
               break;
            }
         }

         return list;
      }
   }

   @JvmStatic
   public fun ShortArray.take(n: Int): List<Short> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else if (n >= `$this$take`.length) {
         return ArraysKt.toList(`$this$take`);
      } else if (n == 1) {
         return CollectionsKt.listOf(`$this$take`[0]);
      } else {
         val count: Int = 0;
         val list: ArrayList = new ArrayList(n);

         for (short item : $this$take) {
            list.add(item);
            if (++count == n) {
               break;
            }
         }

         return list;
      }
   }

   @JvmStatic
   public fun IntArray.take(n: Int): List<Int> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else if (n >= `$this$take`.length) {
         return ArraysKt.toList(`$this$take`);
      } else if (n == 1) {
         return CollectionsKt.listOf(`$this$take`[0]);
      } else {
         val count: Int = 0;
         val list: ArrayList = new ArrayList(n);

         for (int item : $this$take) {
            list.add(item);
            if (++count == n) {
               break;
            }
         }

         return list;
      }
   }

   @JvmStatic
   public fun LongArray.take(n: Int): List<Long> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else if (n >= `$this$take`.length) {
         return ArraysKt.toList(`$this$take`);
      } else if (n == 1) {
         return CollectionsKt.listOf(`$this$take`[0]);
      } else {
         val count: Int = 0;
         val list: ArrayList = new ArrayList(n);

         for (long item : $this$take) {
            list.add(item);
            if (++count == n) {
               break;
            }
         }

         return list;
      }
   }

   @JvmStatic
   public fun FloatArray.take(n: Int): List<Float> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else if (n >= `$this$take`.length) {
         return ArraysKt.toList(`$this$take`);
      } else if (n == 1) {
         return CollectionsKt.listOf(`$this$take`[0]);
      } else {
         val count: Int = 0;
         val list: ArrayList = new ArrayList(n);

         for (float item : $this$take) {
            list.add(item);
            if (++count == n) {
               break;
            }
         }

         return list;
      }
   }

   @JvmStatic
   public fun DoubleArray.take(n: Int): List<Double> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else if (n >= `$this$take`.length) {
         return ArraysKt.toList(`$this$take`);
      } else if (n == 1) {
         return CollectionsKt.listOf(`$this$take`[0]);
      } else {
         val count: Int = 0;
         val list: ArrayList = new ArrayList(n);

         for (double item : $this$take) {
            list.add(item);
            if (++count == n) {
               break;
            }
         }

         return list;
      }
   }

   @JvmStatic
   public fun BooleanArray.take(n: Int): List<Boolean> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else if (n >= `$this$take`.length) {
         return ArraysKt.toList(`$this$take`);
      } else if (n == 1) {
         return CollectionsKt.listOf(`$this$take`[0]);
      } else {
         val count: Int = 0;
         val list: ArrayList = new ArrayList(n);

         for (boolean item : $this$take) {
            list.add(item);
            if (++count == n) {
               break;
            }
         }

         return list;
      }
   }

   @JvmStatic
   public fun CharArray.take(n: Int): List<Char> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else if (n >= `$this$take`.length) {
         return ArraysKt.toList(`$this$take`);
      } else if (n == 1) {
         return CollectionsKt.listOf(`$this$take`[0]);
      } else {
         val count: Int = 0;
         val list: ArrayList = new ArrayList(n);

         for (char item : $this$take) {
            list.add(item);
            if (++count == n) {
               break;
            }
         }

         return list;
      }
   }

   @JvmStatic
   public fun <T> Array<out T>.takeLast(n: Int): List<T> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         val size: Int = `$this$takeLast`.length;
         if (n >= `$this$takeLast`.length) {
            return (java.util.List<T>)ArraysKt.toList(`$this$takeLast`);
         } else if (n == 1) {
            return (java.util.List<T>)CollectionsKt.listOf(`$this$takeLast`[size - 1]);
         } else {
            val list: ArrayList = new ArrayList(n);

            for (int index = size - n; index < size; index++) {
               list.add(`$this$takeLast`[index]);
            }

            return list;
         }
      }
   }

   @JvmStatic
   public fun ByteArray.takeLast(n: Int): List<Byte> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         val size: Int = `$this$takeLast`.length;
         if (n >= `$this$takeLast`.length) {
            return ArraysKt.toList(`$this$takeLast`);
         } else if (n == 1) {
            return CollectionsKt.listOf(`$this$takeLast`[size - 1]);
         } else {
            val list: ArrayList = new ArrayList(n);

            for (int index = size - n; index < size; index++) {
               list.add(`$this$takeLast`[index]);
            }

            return list;
         }
      }
   }

   @JvmStatic
   public fun ShortArray.takeLast(n: Int): List<Short> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         val size: Int = `$this$takeLast`.length;
         if (n >= `$this$takeLast`.length) {
            return ArraysKt.toList(`$this$takeLast`);
         } else if (n == 1) {
            return CollectionsKt.listOf(`$this$takeLast`[size - 1]);
         } else {
            val list: ArrayList = new ArrayList(n);

            for (int index = size - n; index < size; index++) {
               list.add(`$this$takeLast`[index]);
            }

            return list;
         }
      }
   }

   @JvmStatic
   public fun IntArray.takeLast(n: Int): List<Int> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         val size: Int = `$this$takeLast`.length;
         if (n >= `$this$takeLast`.length) {
            return ArraysKt.toList(`$this$takeLast`);
         } else if (n == 1) {
            return CollectionsKt.listOf(`$this$takeLast`[size - 1]);
         } else {
            val list: ArrayList = new ArrayList(n);

            for (int index = size - n; index < size; index++) {
               list.add(`$this$takeLast`[index]);
            }

            return list;
         }
      }
   }

   @JvmStatic
   public fun LongArray.takeLast(n: Int): List<Long> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         val size: Int = `$this$takeLast`.length;
         if (n >= `$this$takeLast`.length) {
            return ArraysKt.toList(`$this$takeLast`);
         } else if (n == 1) {
            return CollectionsKt.listOf(`$this$takeLast`[size - 1]);
         } else {
            val list: ArrayList = new ArrayList(n);

            for (int index = size - n; index < size; index++) {
               list.add(`$this$takeLast`[index]);
            }

            return list;
         }
      }
   }

   @JvmStatic
   public fun FloatArray.takeLast(n: Int): List<Float> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         val size: Int = `$this$takeLast`.length;
         if (n >= `$this$takeLast`.length) {
            return ArraysKt.toList(`$this$takeLast`);
         } else if (n == 1) {
            return CollectionsKt.listOf(`$this$takeLast`[size - 1]);
         } else {
            val list: ArrayList = new ArrayList(n);

            for (int index = size - n; index < size; index++) {
               list.add(`$this$takeLast`[index]);
            }

            return list;
         }
      }
   }

   @JvmStatic
   public fun DoubleArray.takeLast(n: Int): List<Double> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         val size: Int = `$this$takeLast`.length;
         if (n >= `$this$takeLast`.length) {
            return ArraysKt.toList(`$this$takeLast`);
         } else if (n == 1) {
            return CollectionsKt.listOf(`$this$takeLast`[size - 1]);
         } else {
            val list: ArrayList = new ArrayList(n);

            for (int index = size - n; index < size; index++) {
               list.add(`$this$takeLast`[index]);
            }

            return list;
         }
      }
   }

   @JvmStatic
   public fun BooleanArray.takeLast(n: Int): List<Boolean> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         val size: Int = `$this$takeLast`.length;
         if (n >= `$this$takeLast`.length) {
            return ArraysKt.toList(`$this$takeLast`);
         } else if (n == 1) {
            return CollectionsKt.listOf(`$this$takeLast`[size - 1]);
         } else {
            val list: ArrayList = new ArrayList(n);

            for (int index = size - n; index < size; index++) {
               list.add(`$this$takeLast`[index]);
            }

            return list;
         }
      }
   }

   @JvmStatic
   public fun CharArray.takeLast(n: Int): List<Char> {
      if (n < 0) {
         throw new IllegalArgumentException(("Requested element count $n is less than zero.").toString());
      } else if (n == 0) {
         return CollectionsKt.emptyList();
      } else {
         val size: Int = `$this$takeLast`.length;
         if (n >= `$this$takeLast`.length) {
            return ArraysKt.toList(`$this$takeLast`);
         } else if (n == 1) {
            return CollectionsKt.listOf(`$this$takeLast`[size - 1]);
         } else {
            val list: ArrayList = new ArrayList(n);

            for (int index = size - n; index < size; index++) {
               list.add(`$this$takeLast`[index]);
            }

            return list;
         }
      }
   }

   @JvmStatic
   public inline fun <T> Array<out T>.takeLastWhile(predicate: (T) -> Boolean): List<T> {
      for (int index = ArraysKt.getLastIndex($this$takeLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$takeLastWhile`[index]) as java.lang.Boolean) {
            return (java.util.List<T>)ArraysKt.drop(`$this$takeLastWhile`, index + 1);
         }
      }

      return (java.util.List<T>)ArraysKt.toList(`$this$takeLastWhile`);
   }

   @JvmStatic
   public inline fun ByteArray.takeLastWhile(predicate: (Byte) -> Boolean): List<Byte> {
      for (int index = ArraysKt.getLastIndex($this$takeLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$takeLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.drop(`$this$takeLastWhile`, index + 1);
         }
      }

      return ArraysKt.toList(`$this$takeLastWhile`);
   }

   @JvmStatic
   public inline fun ShortArray.takeLastWhile(predicate: (Short) -> Boolean): List<Short> {
      for (int index = ArraysKt.getLastIndex($this$takeLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$takeLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.drop(`$this$takeLastWhile`, index + 1);
         }
      }

      return ArraysKt.toList(`$this$takeLastWhile`);
   }

   @JvmStatic
   public inline fun IntArray.takeLastWhile(predicate: (Int) -> Boolean): List<Int> {
      for (int index = ArraysKt.getLastIndex($this$takeLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$takeLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.drop(`$this$takeLastWhile`, index + 1);
         }
      }

      return ArraysKt.toList(`$this$takeLastWhile`);
   }

   @JvmStatic
   public inline fun LongArray.takeLastWhile(predicate: (Long) -> Boolean): List<Long> {
      for (int index = ArraysKt.getLastIndex($this$takeLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$takeLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.drop(`$this$takeLastWhile`, index + 1);
         }
      }

      return ArraysKt.toList(`$this$takeLastWhile`);
   }

   @JvmStatic
   public inline fun FloatArray.takeLastWhile(predicate: (Float) -> Boolean): List<Float> {
      for (int index = ArraysKt.getLastIndex($this$takeLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$takeLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.drop(`$this$takeLastWhile`, index + 1);
         }
      }

      return ArraysKt.toList(`$this$takeLastWhile`);
   }

   @JvmStatic
   public inline fun DoubleArray.takeLastWhile(predicate: (Double) -> Boolean): List<Double> {
      for (int index = ArraysKt.getLastIndex($this$takeLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$takeLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.drop(`$this$takeLastWhile`, index + 1);
         }
      }

      return ArraysKt.toList(`$this$takeLastWhile`);
   }

   @JvmStatic
   public inline fun BooleanArray.takeLastWhile(predicate: (Boolean) -> Boolean): List<Boolean> {
      for (int index = ArraysKt.getLastIndex($this$takeLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$takeLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.drop(`$this$takeLastWhile`, index + 1);
         }
      }

      return ArraysKt.toList(`$this$takeLastWhile`);
   }

   @JvmStatic
   public inline fun CharArray.takeLastWhile(predicate: (Char) -> Boolean): List<Char> {
      for (int index = ArraysKt.getLastIndex($this$takeLastWhile); -1 < index; index--) {
         if (!predicate.invoke(`$this$takeLastWhile`[index]) as java.lang.Boolean) {
            return ArraysKt.drop(`$this$takeLastWhile`, index + 1);
         }
      }

      return ArraysKt.toList(`$this$takeLastWhile`);
   }

   @JvmStatic
   public inline fun <T> Array<out T>.takeWhile(predicate: (T) -> Boolean): List<T> {
      val list: ArrayList = new ArrayList();

      for (Object item : $this$takeWhile) {
         if (!predicate.invoke(item) as java.lang.Boolean) {
            break;
         }

         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public inline fun ByteArray.takeWhile(predicate: (Byte) -> Boolean): List<Byte> {
      val list: ArrayList = new ArrayList();

      for (byte item : $this$takeWhile) {
         if (!predicate.invoke(item) as java.lang.Boolean) {
            break;
         }

         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public inline fun ShortArray.takeWhile(predicate: (Short) -> Boolean): List<Short> {
      val list: ArrayList = new ArrayList();

      for (short item : $this$takeWhile) {
         if (!predicate.invoke(item) as java.lang.Boolean) {
            break;
         }

         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public inline fun IntArray.takeWhile(predicate: (Int) -> Boolean): List<Int> {
      val list: ArrayList = new ArrayList();

      for (int item : $this$takeWhile) {
         if (!predicate.invoke(item) as java.lang.Boolean) {
            break;
         }

         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public inline fun LongArray.takeWhile(predicate: (Long) -> Boolean): List<Long> {
      val list: ArrayList = new ArrayList();

      for (long item : $this$takeWhile) {
         if (!predicate.invoke(item) as java.lang.Boolean) {
            break;
         }

         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public inline fun FloatArray.takeWhile(predicate: (Float) -> Boolean): List<Float> {
      val list: ArrayList = new ArrayList();

      for (float item : $this$takeWhile) {
         if (!predicate.invoke(item) as java.lang.Boolean) {
            break;
         }

         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public inline fun DoubleArray.takeWhile(predicate: (Double) -> Boolean): List<Double> {
      val list: ArrayList = new ArrayList();

      for (double item : $this$takeWhile) {
         if (!predicate.invoke(item) as java.lang.Boolean) {
            break;
         }

         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public inline fun BooleanArray.takeWhile(predicate: (Boolean) -> Boolean): List<Boolean> {
      val list: ArrayList = new ArrayList();

      for (boolean item : $this$takeWhile) {
         if (!predicate.invoke(item) as java.lang.Boolean) {
            break;
         }

         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public inline fun CharArray.takeWhile(predicate: (Char) -> Boolean): List<Char> {
      val list: ArrayList = new ArrayList();

      for (char item : $this$takeWhile) {
         if (!predicate.invoke(item) as java.lang.Boolean) {
            break;
         }

         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public fun <T> Array<T>.reverse() {
      val midPoint: Int = `$this$reverse`.length / 2 - 1;
      if (`$this$reverse`.length / 2 - 1 >= 0) {
         var reverseIndex: Int = ArraysKt.getLastIndex(`$this$reverse`);
         var index: Int = 0;
         if (0 <= midPoint) {
            while (true) {
               val tmp: Any = `$this$reverse`[index];
               `$this$reverse`[index] = `$this$reverse`[reverseIndex];
               `$this$reverse`[reverseIndex] = tmp;
               reverseIndex--;
               if (index == midPoint) {
                  break;
               }

               index++;
            }
         }
      }
   }

   @JvmStatic
   public fun ByteArray.reverse() {
      val midPoint: Int = `$this$reverse`.length / 2 - 1;
      if (`$this$reverse`.length / 2 - 1 >= 0) {
         var reverseIndex: Int = ArraysKt.getLastIndex(`$this$reverse`);
         var index: Int = 0;
         if (0 <= midPoint) {
            while (true) {
               val tmp: Byte = `$this$reverse`[index];
               `$this$reverse`[index] = `$this$reverse`[reverseIndex];
               `$this$reverse`[reverseIndex] = tmp;
               reverseIndex--;
               if (index == midPoint) {
                  break;
               }

               index++;
            }
         }
      }
   }

   @JvmStatic
   public fun ShortArray.reverse() {
      val midPoint: Int = `$this$reverse`.length / 2 - 1;
      if (`$this$reverse`.length / 2 - 1 >= 0) {
         var reverseIndex: Int = ArraysKt.getLastIndex(`$this$reverse`);
         var index: Int = 0;
         if (0 <= midPoint) {
            while (true) {
               val tmp: Short = `$this$reverse`[index];
               `$this$reverse`[index] = `$this$reverse`[reverseIndex];
               `$this$reverse`[reverseIndex] = tmp;
               reverseIndex--;
               if (index == midPoint) {
                  break;
               }

               index++;
            }
         }
      }
   }

   @JvmStatic
   public fun IntArray.reverse() {
      val midPoint: Int = `$this$reverse`.length / 2 - 1;
      if (`$this$reverse`.length / 2 - 1 >= 0) {
         var reverseIndex: Int = ArraysKt.getLastIndex(`$this$reverse`);
         var index: Int = 0;
         if (0 <= midPoint) {
            while (true) {
               val tmp: Int = `$this$reverse`[index];
               `$this$reverse`[index] = `$this$reverse`[reverseIndex];
               `$this$reverse`[reverseIndex] = tmp;
               reverseIndex--;
               if (index == midPoint) {
                  break;
               }

               index++;
            }
         }
      }
   }

   @JvmStatic
   public fun LongArray.reverse() {
      val midPoint: Int = `$this$reverse`.length / 2 - 1;
      if (`$this$reverse`.length / 2 - 1 >= 0) {
         var reverseIndex: Int = ArraysKt.getLastIndex(`$this$reverse`);
         var index: Int = 0;
         if (0 <= midPoint) {
            while (true) {
               val tmp: Long = `$this$reverse`[index];
               `$this$reverse`[index] = `$this$reverse`[reverseIndex];
               `$this$reverse`[reverseIndex] = tmp;
               reverseIndex--;
               if (index == midPoint) {
                  break;
               }

               index++;
            }
         }
      }
   }

   @JvmStatic
   public fun FloatArray.reverse() {
      val midPoint: Int = `$this$reverse`.length / 2 - 1;
      if (`$this$reverse`.length / 2 - 1 >= 0) {
         var reverseIndex: Int = ArraysKt.getLastIndex(`$this$reverse`);
         var index: Int = 0;
         if (0 <= midPoint) {
            while (true) {
               val tmp: Float = `$this$reverse`[index];
               `$this$reverse`[index] = `$this$reverse`[reverseIndex];
               `$this$reverse`[reverseIndex] = tmp;
               reverseIndex--;
               if (index == midPoint) {
                  break;
               }

               index++;
            }
         }
      }
   }

   @JvmStatic
   public fun DoubleArray.reverse() {
      val midPoint: Int = `$this$reverse`.length / 2 - 1;
      if (`$this$reverse`.length / 2 - 1 >= 0) {
         var reverseIndex: Int = ArraysKt.getLastIndex(`$this$reverse`);
         var index: Int = 0;
         if (0 <= midPoint) {
            while (true) {
               val tmp: Double = `$this$reverse`[index];
               `$this$reverse`[index] = `$this$reverse`[reverseIndex];
               `$this$reverse`[reverseIndex] = tmp;
               reverseIndex--;
               if (index == midPoint) {
                  break;
               }

               index++;
            }
         }
      }
   }

   @JvmStatic
   public fun BooleanArray.reverse() {
      val midPoint: Int = `$this$reverse`.length / 2 - 1;
      if (`$this$reverse`.length / 2 - 1 >= 0) {
         var reverseIndex: Int = ArraysKt.getLastIndex(`$this$reverse`);
         var index: Int = 0;
         if (0 <= midPoint) {
            while (true) {
               val tmp: Boolean = `$this$reverse`[index];
               `$this$reverse`[index] = `$this$reverse`[reverseIndex];
               `$this$reverse`[reverseIndex] = tmp;
               reverseIndex--;
               if (index == midPoint) {
                  break;
               }

               index++;
            }
         }
      }
   }

   @JvmStatic
   public fun CharArray.reverse() {
      val midPoint: Int = `$this$reverse`.length / 2 - 1;
      if (`$this$reverse`.length / 2 - 1 >= 0) {
         var reverseIndex: Int = ArraysKt.getLastIndex(`$this$reverse`);
         var index: Int = 0;
         if (0 <= midPoint) {
            while (true) {
               val tmp: Char = `$this$reverse`[index];
               `$this$reverse`[index] = `$this$reverse`[reverseIndex];
               `$this$reverse`[reverseIndex] = tmp;
               reverseIndex--;
               if (index == midPoint) {
                  break;
               }

               index++;
            }
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T> Array<T>.reverse(fromIndex: Int, toIndex: Int) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, `$this$reverse`.length);
      val midPoint: Int = (fromIndex + toIndex) / 2;
      if (fromIndex != (fromIndex + toIndex) / 2) {
         var reverseIndex: Int = toIndex - 1;

         for (int index = fromIndex; index < midPoint; index++) {
            val tmp: Any = `$this$reverse`[index];
            `$this$reverse`[index] = `$this$reverse`[reverseIndex];
            `$this$reverse`[reverseIndex] = tmp;
            reverseIndex--;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ByteArray.reverse(fromIndex: Int, toIndex: Int) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, `$this$reverse`.length);
      val midPoint: Int = (fromIndex + toIndex) / 2;
      if (fromIndex != (fromIndex + toIndex) / 2) {
         var reverseIndex: Int = toIndex - 1;

         for (int index = fromIndex; index < midPoint; index++) {
            val tmp: Byte = `$this$reverse`[index];
            `$this$reverse`[index] = `$this$reverse`[reverseIndex];
            `$this$reverse`[reverseIndex] = tmp;
            reverseIndex--;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ShortArray.reverse(fromIndex: Int, toIndex: Int) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, `$this$reverse`.length);
      val midPoint: Int = (fromIndex + toIndex) / 2;
      if (fromIndex != (fromIndex + toIndex) / 2) {
         var reverseIndex: Int = toIndex - 1;

         for (int index = fromIndex; index < midPoint; index++) {
            val tmp: Short = `$this$reverse`[index];
            `$this$reverse`[index] = `$this$reverse`[reverseIndex];
            `$this$reverse`[reverseIndex] = tmp;
            reverseIndex--;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun IntArray.reverse(fromIndex: Int, toIndex: Int) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, `$this$reverse`.length);
      val midPoint: Int = (fromIndex + toIndex) / 2;
      if (fromIndex != (fromIndex + toIndex) / 2) {
         var reverseIndex: Int = toIndex - 1;

         for (int index = fromIndex; index < midPoint; index++) {
            val tmp: Int = `$this$reverse`[index];
            `$this$reverse`[index] = `$this$reverse`[reverseIndex];
            `$this$reverse`[reverseIndex] = tmp;
            reverseIndex--;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun LongArray.reverse(fromIndex: Int, toIndex: Int) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, `$this$reverse`.length);
      val midPoint: Int = (fromIndex + toIndex) / 2;
      if (fromIndex != (fromIndex + toIndex) / 2) {
         var reverseIndex: Int = toIndex - 1;

         for (int index = fromIndex; index < midPoint; index++) {
            val tmp: Long = `$this$reverse`[index];
            `$this$reverse`[index] = `$this$reverse`[reverseIndex];
            `$this$reverse`[reverseIndex] = tmp;
            reverseIndex--;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun FloatArray.reverse(fromIndex: Int, toIndex: Int) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, `$this$reverse`.length);
      val midPoint: Int = (fromIndex + toIndex) / 2;
      if (fromIndex != (fromIndex + toIndex) / 2) {
         var reverseIndex: Int = toIndex - 1;

         for (int index = fromIndex; index < midPoint; index++) {
            val tmp: Float = `$this$reverse`[index];
            `$this$reverse`[index] = `$this$reverse`[reverseIndex];
            `$this$reverse`[reverseIndex] = tmp;
            reverseIndex--;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun DoubleArray.reverse(fromIndex: Int, toIndex: Int) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, `$this$reverse`.length);
      val midPoint: Int = (fromIndex + toIndex) / 2;
      if (fromIndex != (fromIndex + toIndex) / 2) {
         var reverseIndex: Int = toIndex - 1;

         for (int index = fromIndex; index < midPoint; index++) {
            val tmp: Double = `$this$reverse`[index];
            `$this$reverse`[index] = `$this$reverse`[reverseIndex];
            `$this$reverse`[reverseIndex] = tmp;
            reverseIndex--;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun BooleanArray.reverse(fromIndex: Int, toIndex: Int) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, `$this$reverse`.length);
      val midPoint: Int = (fromIndex + toIndex) / 2;
      if (fromIndex != (fromIndex + toIndex) / 2) {
         var reverseIndex: Int = toIndex - 1;

         for (int index = fromIndex; index < midPoint; index++) {
            val tmp: Boolean = `$this$reverse`[index];
            `$this$reverse`[index] = `$this$reverse`[reverseIndex];
            `$this$reverse`[reverseIndex] = tmp;
            reverseIndex--;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun CharArray.reverse(fromIndex: Int, toIndex: Int) {
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, toIndex, `$this$reverse`.length);
      val midPoint: Int = (fromIndex + toIndex) / 2;
      if (fromIndex != (fromIndex + toIndex) / 2) {
         var reverseIndex: Int = toIndex - 1;

         for (int index = fromIndex; index < midPoint; index++) {
            val tmp: Char = `$this$reverse`[index];
            `$this$reverse`[index] = `$this$reverse`[reverseIndex];
            `$this$reverse`[reverseIndex] = tmp;
            reverseIndex--;
         }
      }
   }

   @JvmStatic
   public fun <T> Array<out T>.reversed(): List<T> {
      if (`$this$reversed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: java.util.List = ArraysKt.toMutableList(`$this$reversed`);
         CollectionsKt.reverse(list);
         return list;
      }
   }

   @JvmStatic
   public fun ByteArray.reversed(): List<Byte> {
      if (`$this$reversed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: java.util.List = ArraysKt.toMutableList(`$this$reversed`);
         CollectionsKt.reverse(list);
         return list;
      }
   }

   @JvmStatic
   public fun ShortArray.reversed(): List<Short> {
      if (`$this$reversed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: java.util.List = ArraysKt.toMutableList(`$this$reversed`);
         CollectionsKt.reverse(list);
         return list;
      }
   }

   @JvmStatic
   public fun IntArray.reversed(): List<Int> {
      if (`$this$reversed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: java.util.List = ArraysKt.toMutableList(`$this$reversed`);
         CollectionsKt.reverse(list);
         return list;
      }
   }

   @JvmStatic
   public fun LongArray.reversed(): List<Long> {
      if (`$this$reversed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: java.util.List = ArraysKt.toMutableList(`$this$reversed`);
         CollectionsKt.reverse(list);
         return list;
      }
   }

   @JvmStatic
   public fun FloatArray.reversed(): List<Float> {
      if (`$this$reversed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: java.util.List = ArraysKt.toMutableList(`$this$reversed`);
         CollectionsKt.reverse(list);
         return list;
      }
   }

   @JvmStatic
   public fun DoubleArray.reversed(): List<Double> {
      if (`$this$reversed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: java.util.List = ArraysKt.toMutableList(`$this$reversed`);
         CollectionsKt.reverse(list);
         return list;
      }
   }

   @JvmStatic
   public fun BooleanArray.reversed(): List<Boolean> {
      if (`$this$reversed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: java.util.List = ArraysKt.toMutableList(`$this$reversed`);
         CollectionsKt.reverse(list);
         return list;
      }
   }

   @JvmStatic
   public fun CharArray.reversed(): List<Char> {
      if (`$this$reversed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         val list: java.util.List = ArraysKt.toMutableList(`$this$reversed`);
         CollectionsKt.reverse(list);
         return list;
      }
   }

   @JvmStatic
   public fun <T> Array<T>.reversedArray(): Array<T> {
      if (`$this$reversedArray`.length == 0) {
         return (T[])`$this$reversedArray`;
      } else {
         val result: Array<Any> = ArraysKt.arrayOfNulls(`$this$reversedArray`, `$this$reversedArray`.length);
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$reversedArray`);
         var i: Int = 0;
         if (0 <= lastIndex) {
            while (true) {
               result[lastIndex - i] = `$this$reversedArray`[i];
               if (i == lastIndex) {
                  break;
               }

               i++;
            }
         }

         return (T[])result;
      }
   }

   @JvmStatic
   public fun ByteArray.reversedArray(): ByteArray {
      if (`$this$reversedArray`.length == 0) {
         return `$this$reversedArray`;
      } else {
         val result: ByteArray = new byte[`$this$reversedArray`.length];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$reversedArray`);
         var i: Int = 0;
         if (0 <= lastIndex) {
            while (true) {
               result[lastIndex - i] = `$this$reversedArray`[i];
               if (i == lastIndex) {
                  break;
               }

               i++;
            }
         }

         return result;
      }
   }

   @JvmStatic
   public fun ShortArray.reversedArray(): ShortArray {
      if (`$this$reversedArray`.length == 0) {
         return `$this$reversedArray`;
      } else {
         val result: ShortArray = new short[`$this$reversedArray`.length];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$reversedArray`);
         var i: Int = 0;
         if (0 <= lastIndex) {
            while (true) {
               result[lastIndex - i] = `$this$reversedArray`[i];
               if (i == lastIndex) {
                  break;
               }

               i++;
            }
         }

         return result;
      }
   }

   @JvmStatic
   public fun IntArray.reversedArray(): IntArray {
      if (`$this$reversedArray`.length == 0) {
         return `$this$reversedArray`;
      } else {
         val result: IntArray = new int[`$this$reversedArray`.length];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$reversedArray`);
         var i: Int = 0;
         if (0 <= lastIndex) {
            while (true) {
               result[lastIndex - i] = `$this$reversedArray`[i];
               if (i == lastIndex) {
                  break;
               }

               i++;
            }
         }

         return result;
      }
   }

   @JvmStatic
   public fun LongArray.reversedArray(): LongArray {
      if (`$this$reversedArray`.length == 0) {
         return `$this$reversedArray`;
      } else {
         val result: LongArray = new long[`$this$reversedArray`.length];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$reversedArray`);
         var i: Int = 0;
         if (0 <= lastIndex) {
            while (true) {
               result[lastIndex - i] = `$this$reversedArray`[i];
               if (i == lastIndex) {
                  break;
               }

               i++;
            }
         }

         return result;
      }
   }

   @JvmStatic
   public fun FloatArray.reversedArray(): FloatArray {
      if (`$this$reversedArray`.length == 0) {
         return `$this$reversedArray`;
      } else {
         val result: FloatArray = new float[`$this$reversedArray`.length];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$reversedArray`);
         var i: Int = 0;
         if (0 <= lastIndex) {
            while (true) {
               result[lastIndex - i] = `$this$reversedArray`[i];
               if (i == lastIndex) {
                  break;
               }

               i++;
            }
         }

         return result;
      }
   }

   @JvmStatic
   public fun DoubleArray.reversedArray(): DoubleArray {
      if (`$this$reversedArray`.length == 0) {
         return `$this$reversedArray`;
      } else {
         val result: DoubleArray = new double[`$this$reversedArray`.length];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$reversedArray`);
         var i: Int = 0;
         if (0 <= lastIndex) {
            while (true) {
               result[lastIndex - i] = `$this$reversedArray`[i];
               if (i == lastIndex) {
                  break;
               }

               i++;
            }
         }

         return result;
      }
   }

   @JvmStatic
   public fun BooleanArray.reversedArray(): BooleanArray {
      if (`$this$reversedArray`.length == 0) {
         return `$this$reversedArray`;
      } else {
         val result: BooleanArray = new boolean[`$this$reversedArray`.length];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$reversedArray`);
         var i: Int = 0;
         if (0 <= lastIndex) {
            while (true) {
               result[lastIndex - i] = `$this$reversedArray`[i];
               if (i == lastIndex) {
                  break;
               }

               i++;
            }
         }

         return result;
      }
   }

   @JvmStatic
   public fun CharArray.reversedArray(): CharArray {
      if (`$this$reversedArray`.length == 0) {
         return `$this$reversedArray`;
      } else {
         val result: CharArray = new char[`$this$reversedArray`.length];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$reversedArray`);
         var i: Int = 0;
         if (0 <= lastIndex) {
            while (true) {
               result[lastIndex - i] = `$this$reversedArray`[i];
               if (i == lastIndex) {
                  break;
               }

               i++;
            }
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T> Array<T>.shuffle() {
      ArraysKt.shuffle(`$this$shuffle`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ByteArray.shuffle() {
      ArraysKt.shuffle(`$this$shuffle`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ShortArray.shuffle() {
      ArraysKt.shuffle(`$this$shuffle`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun IntArray.shuffle() {
      ArraysKt.shuffle(`$this$shuffle`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun LongArray.shuffle() {
      ArraysKt.shuffle(`$this$shuffle`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun FloatArray.shuffle() {
      ArraysKt.shuffle(`$this$shuffle`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun DoubleArray.shuffle() {
      ArraysKt.shuffle(`$this$shuffle`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun BooleanArray.shuffle() {
      ArraysKt.shuffle(`$this$shuffle`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun CharArray.shuffle() {
      ArraysKt.shuffle(`$this$shuffle`, Random.Default);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T> Array<T>.shuffle(random: Random) {
      for (int i = ArraysKt.getLastIndex($this$shuffle); 0 < i; i--) {
         val j: Int = random.nextInt(i + 1);
         val copy: Any = `$this$shuffle`[i];
         `$this$shuffle`[i] = `$this$shuffle`[j];
         `$this$shuffle`[j] = copy;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ByteArray.shuffle(random: Random) {
      for (int i = ArraysKt.getLastIndex($this$shuffle); 0 < i; i--) {
         val j: Int = random.nextInt(i + 1);
         val copy: Byte = `$this$shuffle`[i];
         `$this$shuffle`[i] = `$this$shuffle`[j];
         `$this$shuffle`[j] = copy;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ShortArray.shuffle(random: Random) {
      for (int i = ArraysKt.getLastIndex($this$shuffle); 0 < i; i--) {
         val j: Int = random.nextInt(i + 1);
         val copy: Short = `$this$shuffle`[i];
         `$this$shuffle`[i] = `$this$shuffle`[j];
         `$this$shuffle`[j] = copy;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun IntArray.shuffle(random: Random) {
      for (int i = ArraysKt.getLastIndex($this$shuffle); 0 < i; i--) {
         val j: Int = random.nextInt(i + 1);
         val copy: Int = `$this$shuffle`[i];
         `$this$shuffle`[i] = `$this$shuffle`[j];
         `$this$shuffle`[j] = copy;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun LongArray.shuffle(random: Random) {
      for (int i = ArraysKt.getLastIndex($this$shuffle); 0 < i; i--) {
         val j: Int = random.nextInt(i + 1);
         val copy: Long = `$this$shuffle`[i];
         `$this$shuffle`[i] = `$this$shuffle`[j];
         `$this$shuffle`[j] = copy;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun FloatArray.shuffle(random: Random) {
      for (int i = ArraysKt.getLastIndex($this$shuffle); 0 < i; i--) {
         val j: Int = random.nextInt(i + 1);
         val copy: Float = `$this$shuffle`[i];
         `$this$shuffle`[i] = `$this$shuffle`[j];
         `$this$shuffle`[j] = copy;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun DoubleArray.shuffle(random: Random) {
      for (int i = ArraysKt.getLastIndex($this$shuffle); 0 < i; i--) {
         val j: Int = random.nextInt(i + 1);
         val copy: Double = `$this$shuffle`[i];
         `$this$shuffle`[i] = `$this$shuffle`[j];
         `$this$shuffle`[j] = copy;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun BooleanArray.shuffle(random: Random) {
      for (int i = ArraysKt.getLastIndex($this$shuffle); 0 < i; i--) {
         val j: Int = random.nextInt(i + 1);
         val copy: Boolean = `$this$shuffle`[i];
         `$this$shuffle`[i] = `$this$shuffle`[j];
         `$this$shuffle`[j] = copy;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun CharArray.shuffle(random: Random) {
      for (int i = ArraysKt.getLastIndex($this$shuffle); 0 < i; i--) {
         val j: Int = random.nextInt(i + 1);
         val copy: Char = `$this$shuffle`[i];
         `$this$shuffle`[i] = `$this$shuffle`[j];
         `$this$shuffle`[j] = copy;
      }
   }

   @JvmStatic
   public inline fun <T, R : Comparable<R>> Array<out T>.sortBy(crossinline selector: (T) -> R?) {
      if (`$this$sortBy`.length > 1) {
         ArraysKt.sortWith(`$this$sortBy`, new 2<>(selector));
      }
   }

   @JvmStatic
   public inline fun <T, R : Comparable<R>> Array<out T>.sortByDescending(crossinline selector: (T) -> R?) {
      if (`$this$sortByDescending`.length > 1) {
         ArraysKt.sortWith(`$this$sortByDescending`, new 1<>(selector));
      }
   }

   @JvmStatic
   public fun <T : Comparable<T>> Array<out T>.sortDescending() {
      ArraysKt.sortWith(`$this$sortDescending`, ComparisonsKt.reverseOrder());
   }

   @JvmStatic
   public fun ByteArray.sortDescending() {
      if (`$this$sortDescending`.length > 1) {
         ArraysKt.sort(`$this$sortDescending`);
         ArraysKt.reverse(`$this$sortDescending`);
      }
   }

   @JvmStatic
   public fun ShortArray.sortDescending() {
      if (`$this$sortDescending`.length > 1) {
         ArraysKt.sort(`$this$sortDescending`);
         ArraysKt.reverse(`$this$sortDescending`);
      }
   }

   @JvmStatic
   public fun IntArray.sortDescending() {
      if (`$this$sortDescending`.length > 1) {
         ArraysKt.sort(`$this$sortDescending`);
         ArraysKt.reverse(`$this$sortDescending`);
      }
   }

   @JvmStatic
   public fun LongArray.sortDescending() {
      if (`$this$sortDescending`.length > 1) {
         ArraysKt.sort(`$this$sortDescending`);
         ArraysKt.reverse(`$this$sortDescending`);
      }
   }

   @JvmStatic
   public fun FloatArray.sortDescending() {
      if (`$this$sortDescending`.length > 1) {
         ArraysKt.sort(`$this$sortDescending`);
         ArraysKt.reverse(`$this$sortDescending`);
      }
   }

   @JvmStatic
   public fun DoubleArray.sortDescending() {
      if (`$this$sortDescending`.length > 1) {
         ArraysKt.sort(`$this$sortDescending`);
         ArraysKt.reverse(`$this$sortDescending`);
      }
   }

   @JvmStatic
   public fun CharArray.sortDescending() {
      if (`$this$sortDescending`.length > 1) {
         ArraysKt.sort(`$this$sortDescending`);
         ArraysKt.reverse(`$this$sortDescending`);
      }
   }

   @JvmStatic
   public fun <T : Comparable<T>> Array<out T>.sorted(): List<T> {
      return (java.util.List<T>)ArraysKt.asList(ArraysKt.sortedArray(`$this$sorted`));
   }

   @JvmStatic
   public fun ByteArray.sorted(): List<Byte> {
      val var1: Array<java.lang.Byte> = ArraysKt.toTypedArray(`$this$sorted`);
      ArraysKt.sort(var1);
      return ArraysKt.asList(var1);
   }

   @JvmStatic
   public fun ShortArray.sorted(): List<Short> {
      val var1: Array<java.lang.Short> = ArraysKt.toTypedArray(`$this$sorted`);
      ArraysKt.sort(var1);
      return ArraysKt.asList(var1);
   }

   @JvmStatic
   public fun IntArray.sorted(): List<Int> {
      val var1: Array<Int> = ArraysKt.toTypedArray(`$this$sorted`);
      ArraysKt.sort(var1);
      return ArraysKt.asList(var1);
   }

   @JvmStatic
   public fun LongArray.sorted(): List<Long> {
      val var1: Array<java.lang.Long> = ArraysKt.toTypedArray(`$this$sorted`);
      ArraysKt.sort(var1);
      return ArraysKt.asList(var1);
   }

   @JvmStatic
   public fun FloatArray.sorted(): List<Float> {
      val var1: Array<java.lang.Float> = ArraysKt.toTypedArray(`$this$sorted`);
      ArraysKt.sort(var1);
      return ArraysKt.asList(var1);
   }

   @JvmStatic
   public fun DoubleArray.sorted(): List<Double> {
      val var1: Array<java.lang.Double> = ArraysKt.toTypedArray(`$this$sorted`);
      ArraysKt.sort(var1);
      return ArraysKt.asList(var1);
   }

   @JvmStatic
   public fun CharArray.sorted(): List<Char> {
      val var1: Array<Character> = ArraysKt.toTypedArray(`$this$sorted`);
      ArraysKt.sort(var1);
      return ArraysKt.asList(var1);
   }

   @JvmStatic
   public fun <T : Comparable<T>> Array<T>.sortedArray(): Array<T> {
      if (`$this$sortedArray`.length == 0) {
         return (T[])`$this$sortedArray`;
      } else {
         val var10000: Array<Any> = Arrays.copyOf(`$this$sortedArray`, `$this$sortedArray`.length);
         ArraysKt.sort(var10000 as Array<java.lang.Comparable>);
         return (T[])(var10000 as Array<java.lang.Comparable>);
      }
   }

   @JvmStatic
   public fun ByteArray.sortedArray(): ByteArray {
      if (`$this$sortedArray`.length == 0) {
         return `$this$sortedArray`;
      } else {
         val var10000: ByteArray = Arrays.copyOf(`$this$sortedArray`, `$this$sortedArray`.length);
         ArraysKt.sort(var10000);
         return var10000;
      }
   }

   @JvmStatic
   public fun ShortArray.sortedArray(): ShortArray {
      if (`$this$sortedArray`.length == 0) {
         return `$this$sortedArray`;
      } else {
         val var10000: ShortArray = Arrays.copyOf(`$this$sortedArray`, `$this$sortedArray`.length);
         ArraysKt.sort(var10000);
         return var10000;
      }
   }

   @JvmStatic
   public fun IntArray.sortedArray(): IntArray {
      if (`$this$sortedArray`.length == 0) {
         return `$this$sortedArray`;
      } else {
         val var10000: IntArray = Arrays.copyOf(`$this$sortedArray`, `$this$sortedArray`.length);
         ArraysKt.sort(var10000);
         return var10000;
      }
   }

   @JvmStatic
   public fun LongArray.sortedArray(): LongArray {
      if (`$this$sortedArray`.length == 0) {
         return `$this$sortedArray`;
      } else {
         val var10000: LongArray = Arrays.copyOf(`$this$sortedArray`, `$this$sortedArray`.length);
         ArraysKt.sort(var10000);
         return var10000;
      }
   }

   @JvmStatic
   public fun FloatArray.sortedArray(): FloatArray {
      if (`$this$sortedArray`.length == 0) {
         return `$this$sortedArray`;
      } else {
         val var10000: FloatArray = Arrays.copyOf(`$this$sortedArray`, `$this$sortedArray`.length);
         ArraysKt.sort(var10000);
         return var10000;
      }
   }

   @JvmStatic
   public fun DoubleArray.sortedArray(): DoubleArray {
      if (`$this$sortedArray`.length == 0) {
         return `$this$sortedArray`;
      } else {
         val var10000: DoubleArray = Arrays.copyOf(`$this$sortedArray`, `$this$sortedArray`.length);
         ArraysKt.sort(var10000);
         return var10000;
      }
   }

   @JvmStatic
   public fun CharArray.sortedArray(): CharArray {
      if (`$this$sortedArray`.length == 0) {
         return `$this$sortedArray`;
      } else {
         val var10000: CharArray = Arrays.copyOf(`$this$sortedArray`, `$this$sortedArray`.length);
         ArraysKt.sort(var10000);
         return var10000;
      }
   }

   @JvmStatic
   public fun <T : Comparable<T>> Array<T>.sortedArrayDescending(): Array<T> {
      if (`$this$sortedArrayDescending`.length == 0) {
         return (T[])`$this$sortedArrayDescending`;
      } else {
         val var10000: Array<Any> = Arrays.copyOf(`$this$sortedArrayDescending`, `$this$sortedArrayDescending`.length);
         ArraysKt.sortWith(var10000 as Array<java.lang.Comparable>, ComparisonsKt.reverseOrder());
         return (T[])(var10000 as Array<java.lang.Comparable>);
      }
   }

   @JvmStatic
   public fun ByteArray.sortedArrayDescending(): ByteArray {
      if (`$this$sortedArrayDescending`.length == 0) {
         return `$this$sortedArrayDescending`;
      } else {
         val var10000: ByteArray = Arrays.copyOf(`$this$sortedArrayDescending`, `$this$sortedArrayDescending`.length);
         ArraysKt.sortDescending(var10000);
         return var10000;
      }
   }

   @JvmStatic
   public fun ShortArray.sortedArrayDescending(): ShortArray {
      if (`$this$sortedArrayDescending`.length == 0) {
         return `$this$sortedArrayDescending`;
      } else {
         val var10000: ShortArray = Arrays.copyOf(`$this$sortedArrayDescending`, `$this$sortedArrayDescending`.length);
         ArraysKt.sortDescending(var10000);
         return var10000;
      }
   }

   @JvmStatic
   public fun IntArray.sortedArrayDescending(): IntArray {
      if (`$this$sortedArrayDescending`.length == 0) {
         return `$this$sortedArrayDescending`;
      } else {
         val var10000: IntArray = Arrays.copyOf(`$this$sortedArrayDescending`, `$this$sortedArrayDescending`.length);
         ArraysKt.sortDescending(var10000);
         return var10000;
      }
   }

   @JvmStatic
   public fun LongArray.sortedArrayDescending(): LongArray {
      if (`$this$sortedArrayDescending`.length == 0) {
         return `$this$sortedArrayDescending`;
      } else {
         val var10000: LongArray = Arrays.copyOf(`$this$sortedArrayDescending`, `$this$sortedArrayDescending`.length);
         ArraysKt.sortDescending(var10000);
         return var10000;
      }
   }

   @JvmStatic
   public fun FloatArray.sortedArrayDescending(): FloatArray {
      if (`$this$sortedArrayDescending`.length == 0) {
         return `$this$sortedArrayDescending`;
      } else {
         val var10000: FloatArray = Arrays.copyOf(`$this$sortedArrayDescending`, `$this$sortedArrayDescending`.length);
         ArraysKt.sortDescending(var10000);
         return var10000;
      }
   }

   @JvmStatic
   public fun DoubleArray.sortedArrayDescending(): DoubleArray {
      if (`$this$sortedArrayDescending`.length == 0) {
         return `$this$sortedArrayDescending`;
      } else {
         val var10000: DoubleArray = Arrays.copyOf(`$this$sortedArrayDescending`, `$this$sortedArrayDescending`.length);
         ArraysKt.sortDescending(var10000);
         return var10000;
      }
   }

   @JvmStatic
   public fun CharArray.sortedArrayDescending(): CharArray {
      if (`$this$sortedArrayDescending`.length == 0) {
         return `$this$sortedArrayDescending`;
      } else {
         val var10000: CharArray = Arrays.copyOf(`$this$sortedArrayDescending`, `$this$sortedArrayDescending`.length);
         ArraysKt.sortDescending(var10000);
         return var10000;
      }
   }

   @JvmStatic
   public fun <T> Array<out T>.sortedArrayWith(comparator: Comparator<in T>): Array<out T> {
      if (`$this$sortedArrayWith`.length == 0) {
         return (T[])`$this$sortedArrayWith`;
      } else {
         val var10000: Array<Any> = Arrays.copyOf(`$this$sortedArrayWith`, `$this$sortedArrayWith`.length);
         ArraysKt.sortWith(var10000, comparator);
         return (T[])var10000;
      }
   }

   @JvmStatic
   public inline fun <T, R : Comparable<R>> Array<out T>.sortedBy(crossinline selector: (T) -> R?): List<T> {
      return (java.util.List<T>)ArraysKt.sortedWith(`$this$sortedBy`, new 2<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> ByteArray.sortedBy(crossinline selector: (Byte) -> R?): List<Byte> {
      return ArraysKt.sortedWith(`$this$sortedBy`, new 2<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> ShortArray.sortedBy(crossinline selector: (Short) -> R?): List<Short> {
      return ArraysKt.sortedWith(`$this$sortedBy`, new 2<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> IntArray.sortedBy(crossinline selector: (Int) -> R?): List<Int> {
      return ArraysKt.sortedWith(`$this$sortedBy`, new 2<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> LongArray.sortedBy(crossinline selector: (Long) -> R?): List<Long> {
      return ArraysKt.sortedWith(`$this$sortedBy`, new 2<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> FloatArray.sortedBy(crossinline selector: (Float) -> R?): List<Float> {
      return ArraysKt.sortedWith(`$this$sortedBy`, new 2<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> DoubleArray.sortedBy(crossinline selector: (Double) -> R?): List<Double> {
      return ArraysKt.sortedWith(`$this$sortedBy`, new 2<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> BooleanArray.sortedBy(crossinline selector: (Boolean) -> R?): List<Boolean> {
      return ArraysKt.sortedWith(`$this$sortedBy`, new 2<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> CharArray.sortedBy(crossinline selector: (Char) -> R?): List<Char> {
      return ArraysKt.sortedWith(`$this$sortedBy`, new 2<>(selector));
   }

   @JvmStatic
   public inline fun <T, R : Comparable<R>> Array<out T>.sortedByDescending(crossinline selector: (T) -> R?): List<T> {
      return (java.util.List<T>)ArraysKt.sortedWith(`$this$sortedByDescending`, new 1<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> ByteArray.sortedByDescending(crossinline selector: (Byte) -> R?): List<Byte> {
      return ArraysKt.sortedWith(`$this$sortedByDescending`, new 1<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> ShortArray.sortedByDescending(crossinline selector: (Short) -> R?): List<Short> {
      return ArraysKt.sortedWith(`$this$sortedByDescending`, new 1<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> IntArray.sortedByDescending(crossinline selector: (Int) -> R?): List<Int> {
      return ArraysKt.sortedWith(`$this$sortedByDescending`, new 1<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> LongArray.sortedByDescending(crossinline selector: (Long) -> R?): List<Long> {
      return ArraysKt.sortedWith(`$this$sortedByDescending`, new 1<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> FloatArray.sortedByDescending(crossinline selector: (Float) -> R?): List<Float> {
      return ArraysKt.sortedWith(`$this$sortedByDescending`, new 1<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> DoubleArray.sortedByDescending(crossinline selector: (Double) -> R?): List<Double> {
      return ArraysKt.sortedWith(`$this$sortedByDescending`, new 1<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> BooleanArray.sortedByDescending(crossinline selector: (Boolean) -> R?): List<Boolean> {
      return ArraysKt.sortedWith(`$this$sortedByDescending`, new 1<>(selector));
   }

   @JvmStatic
   public inline fun <R : Comparable<R>> CharArray.sortedByDescending(crossinline selector: (Char) -> R?): List<Char> {
      return ArraysKt.sortedWith(`$this$sortedByDescending`, new 1<>(selector));
   }

   @JvmStatic
   public fun <T : Comparable<T>> Array<out T>.sortedDescending(): List<T> {
      return (java.util.List<T>)ArraysKt.sortedWith(`$this$sortedDescending`, ComparisonsKt.reverseOrder());
   }

   @JvmStatic
   public fun ByteArray.sortedDescending(): List<Byte> {
      val var10000: ByteArray = Arrays.copyOf(`$this$sortedDescending`, `$this$sortedDescending`.length);
      ArraysKt.sort(var10000);
      return ArraysKt.reversed(var10000);
   }

   @JvmStatic
   public fun ShortArray.sortedDescending(): List<Short> {
      val var10000: ShortArray = Arrays.copyOf(`$this$sortedDescending`, `$this$sortedDescending`.length);
      ArraysKt.sort(var10000);
      return ArraysKt.reversed(var10000);
   }

   @JvmStatic
   public fun IntArray.sortedDescending(): List<Int> {
      val var10000: IntArray = Arrays.copyOf(`$this$sortedDescending`, `$this$sortedDescending`.length);
      ArraysKt.sort(var10000);
      return ArraysKt.reversed(var10000);
   }

   @JvmStatic
   public fun LongArray.sortedDescending(): List<Long> {
      val var10000: LongArray = Arrays.copyOf(`$this$sortedDescending`, `$this$sortedDescending`.length);
      ArraysKt.sort(var10000);
      return ArraysKt.reversed(var10000);
   }

   @JvmStatic
   public fun FloatArray.sortedDescending(): List<Float> {
      val var10000: FloatArray = Arrays.copyOf(`$this$sortedDescending`, `$this$sortedDescending`.length);
      ArraysKt.sort(var10000);
      return ArraysKt.reversed(var10000);
   }

   @JvmStatic
   public fun DoubleArray.sortedDescending(): List<Double> {
      val var10000: DoubleArray = Arrays.copyOf(`$this$sortedDescending`, `$this$sortedDescending`.length);
      ArraysKt.sort(var10000);
      return ArraysKt.reversed(var10000);
   }

   @JvmStatic
   public fun CharArray.sortedDescending(): List<Char> {
      val var10000: CharArray = Arrays.copyOf(`$this$sortedDescending`, `$this$sortedDescending`.length);
      ArraysKt.sort(var10000);
      return ArraysKt.reversed(var10000);
   }

   @JvmStatic
   public fun <T> Array<out T>.sortedWith(comparator: Comparator<in T>): List<T> {
      return (java.util.List<T>)ArraysKt.asList(ArraysKt.sortedArrayWith(`$this$sortedWith`, comparator));
   }

   @JvmStatic
   public fun ByteArray.sortedWith(comparator: Comparator<in Byte>): List<Byte> {
      val var2: Array<java.lang.Byte> = ArraysKt.toTypedArray(`$this$sortedWith`);
      ArraysKt.sortWith(var2, comparator);
      return ArraysKt.asList(var2);
   }

   @JvmStatic
   public fun ShortArray.sortedWith(comparator: Comparator<in Short>): List<Short> {
      val var2: Array<java.lang.Short> = ArraysKt.toTypedArray(`$this$sortedWith`);
      ArraysKt.sortWith(var2, comparator);
      return ArraysKt.asList(var2);
   }

   @JvmStatic
   public fun IntArray.sortedWith(comparator: Comparator<in Int>): List<Int> {
      val var2: Array<Int> = ArraysKt.toTypedArray(`$this$sortedWith`);
      ArraysKt.sortWith(var2, comparator);
      return ArraysKt.asList(var2);
   }

   @JvmStatic
   public fun LongArray.sortedWith(comparator: Comparator<in Long>): List<Long> {
      val var2: Array<java.lang.Long> = ArraysKt.toTypedArray(`$this$sortedWith`);
      ArraysKt.sortWith(var2, comparator);
      return ArraysKt.asList(var2);
   }

   @JvmStatic
   public fun FloatArray.sortedWith(comparator: Comparator<in Float>): List<Float> {
      val var2: Array<java.lang.Float> = ArraysKt.toTypedArray(`$this$sortedWith`);
      ArraysKt.sortWith(var2, comparator);
      return ArraysKt.asList(var2);
   }

   @JvmStatic
   public fun DoubleArray.sortedWith(comparator: Comparator<in Double>): List<Double> {
      val var2: Array<java.lang.Double> = ArraysKt.toTypedArray(`$this$sortedWith`);
      ArraysKt.sortWith(var2, comparator);
      return ArraysKt.asList(var2);
   }

   @JvmStatic
   public fun BooleanArray.sortedWith(comparator: Comparator<in Boolean>): List<Boolean> {
      val var2: Array<java.lang.Boolean> = ArraysKt.toTypedArray(`$this$sortedWith`);
      ArraysKt.sortWith(var2, comparator);
      return ArraysKt.asList(var2);
   }

   @JvmStatic
   public fun CharArray.sortedWith(comparator: Comparator<in Char>): List<Char> {
      val var2: Array<Character> = ArraysKt.toTypedArray(`$this$sortedWith`);
      ArraysKt.sortWith(var2, comparator);
      return ArraysKt.asList(var2);
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalStdlibApi
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<T>.copyOf(newSize: Int, init: (Int) -> T): Array<T> {
      if (newSize < 0) {
         throw new IllegalArgumentException(("Invalid new array size: $newSize.").toString());
      } else {
         val oldSize: Int = `$this$copyOf`.length;
         val var10000: Array<Any> = Arrays.copyOf(`$this$copyOf`, newSize);
         val copy: Array<Any> = var10000;

         for (int idx = oldSize; idx < newSize; idx++) {
            copy[idx] = init.invoke(idx);
         }

         return (T[])copy;
      }
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalStdlibApi
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.copyOf(newSize: Int, init: (Int) -> Byte): ByteArray {
      if (newSize < 0) {
         throw new IllegalArgumentException(("Invalid new array size: $newSize.").toString());
      } else {
         val oldSize: Int = `$this$copyOf`.length;
         val var10000: ByteArray = Arrays.copyOf(`$this$copyOf`, newSize);
         val copy: ByteArray = var10000;

         for (int idx = oldSize; idx < newSize; idx++) {
            copy[idx] = (init.invoke(idx) as java.lang.Number).byteValue();
         }

         return copy;
      }
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalStdlibApi
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.copyOf(newSize: Int, init: (Int) -> Short): ShortArray {
      if (newSize < 0) {
         throw new IllegalArgumentException(("Invalid new array size: $newSize.").toString());
      } else {
         val oldSize: Int = `$this$copyOf`.length;
         val var10000: ShortArray = Arrays.copyOf(`$this$copyOf`, newSize);
         val copy: ShortArray = var10000;

         for (int idx = oldSize; idx < newSize; idx++) {
            copy[idx] = (init.invoke(idx) as java.lang.Number).shortValue();
         }

         return copy;
      }
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalStdlibApi
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.copyOf(newSize: Int, init: (Int) -> Int): IntArray {
      if (newSize < 0) {
         throw new IllegalArgumentException(("Invalid new array size: $newSize.").toString());
      } else {
         val oldSize: Int = `$this$copyOf`.length;
         val var10000: IntArray = Arrays.copyOf(`$this$copyOf`, newSize);
         val copy: IntArray = var10000;

         for (int idx = oldSize; idx < newSize; idx++) {
            copy[idx] = (init.invoke(idx) as java.lang.Number).intValue();
         }

         return copy;
      }
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalStdlibApi
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.copyOf(newSize: Int, init: (Int) -> Long): LongArray {
      if (newSize < 0) {
         throw new IllegalArgumentException(("Invalid new array size: $newSize.").toString());
      } else {
         val oldSize: Int = `$this$copyOf`.length;
         val var10000: LongArray = Arrays.copyOf(`$this$copyOf`, newSize);
         val copy: LongArray = var10000;

         for (int idx = oldSize; idx < newSize; idx++) {
            copy[idx] = (init.invoke(idx) as java.lang.Number).longValue();
         }

         return copy;
      }
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalStdlibApi
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.copyOf(newSize: Int, init: (Int) -> Float): FloatArray {
      if (newSize < 0) {
         throw new IllegalArgumentException(("Invalid new array size: $newSize.").toString());
      } else {
         val oldSize: Int = `$this$copyOf`.length;
         val var10000: FloatArray = Arrays.copyOf(`$this$copyOf`, newSize);
         val copy: FloatArray = var10000;

         for (int idx = oldSize; idx < newSize; idx++) {
            copy[idx] = (init.invoke(idx) as java.lang.Number).floatValue();
         }

         return copy;
      }
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalStdlibApi
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.copyOf(newSize: Int, init: (Int) -> Double): DoubleArray {
      if (newSize < 0) {
         throw new IllegalArgumentException(("Invalid new array size: $newSize.").toString());
      } else {
         val oldSize: Int = `$this$copyOf`.length;
         val var10000: DoubleArray = Arrays.copyOf(`$this$copyOf`, newSize);
         val copy: DoubleArray = var10000;

         for (int idx = oldSize; idx < newSize; idx++) {
            copy[idx] = (init.invoke(idx) as java.lang.Number).doubleValue();
         }

         return copy;
      }
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalStdlibApi
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.copyOf(newSize: Int, init: (Int) -> Boolean): BooleanArray {
      if (newSize < 0) {
         throw new IllegalArgumentException(("Invalid new array size: $newSize.").toString());
      } else {
         val oldSize: Int = `$this$copyOf`.length;
         val var10000: BooleanArray = Arrays.copyOf(`$this$copyOf`, newSize);
         val copy: BooleanArray = var10000;

         for (int idx = oldSize; idx < newSize; idx++) {
            copy[idx] = init.invoke(idx) as java.lang.Boolean;
         }

         return copy;
      }
   }

   @SinceKotlin(version = "2.2")
   @ExperimentalStdlibApi
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.copyOf(newSize: Int, init: (Int) -> Char): CharArray {
      if (newSize < 0) {
         throw new IllegalArgumentException(("Invalid new array size: $newSize.").toString());
      } else {
         val oldSize: Int = `$this$copyOf`.length;
         val var10000: CharArray = Arrays.copyOf(`$this$copyOf`, newSize);
         val copy: CharArray = var10000;

         for (int idx = oldSize; idx < newSize; idx++) {
            copy[idx] = init.invoke(idx) as Character;
         }

         return copy;
      }
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.isEmpty(): Boolean {
      return `$this$isEmpty`.length == 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.isEmpty(): Boolean {
      return `$this$isEmpty`.length == 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.isEmpty(): Boolean {
      return `$this$isEmpty`.length == 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun IntArray.isEmpty(): Boolean {
      return `$this$isEmpty`.length == 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun LongArray.isEmpty(): Boolean {
      return `$this$isEmpty`.length == 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.isEmpty(): Boolean {
      return `$this$isEmpty`.length == 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.isEmpty(): Boolean {
      return `$this$isEmpty`.length == 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.isEmpty(): Boolean {
      return `$this$isEmpty`.length == 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharArray.isEmpty(): Boolean {
      return `$this$isEmpty`.length == 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.isNotEmpty(): Boolean {
      return `$this$isNotEmpty`.length != 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.isNotEmpty(): Boolean {
      return `$this$isNotEmpty`.length != 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.isNotEmpty(): Boolean {
      return `$this$isNotEmpty`.length != 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun IntArray.isNotEmpty(): Boolean {
      return `$this$isNotEmpty`.length != 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun LongArray.isNotEmpty(): Boolean {
      return `$this$isNotEmpty`.length != 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.isNotEmpty(): Boolean {
      return `$this$isNotEmpty`.length != 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.isNotEmpty(): Boolean {
      return `$this$isNotEmpty`.length != 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.isNotEmpty(): Boolean {
      return `$this$isNotEmpty`.length != 0;
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharArray.isNotEmpty(): Boolean {
      return `$this$isNotEmpty`.length != 0;
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T : Comparable<T>> Array<out T>.sortDescending(fromIndex: Int, toIndex: Int) {
      ArraysKt.sortWith(`$this$sortDescending`, ComparisonsKt.reverseOrder(), fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ByteArray.sortDescending(fromIndex: Int, toIndex: Int) {
      ArraysKt.sort(`$this$sortDescending`, fromIndex, toIndex);
      ArraysKt.reverse(`$this$sortDescending`, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ShortArray.sortDescending(fromIndex: Int, toIndex: Int) {
      ArraysKt.sort(`$this$sortDescending`, fromIndex, toIndex);
      ArraysKt.reverse(`$this$sortDescending`, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun IntArray.sortDescending(fromIndex: Int, toIndex: Int) {
      ArraysKt.sort(`$this$sortDescending`, fromIndex, toIndex);
      ArraysKt.reverse(`$this$sortDescending`, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun LongArray.sortDescending(fromIndex: Int, toIndex: Int) {
      ArraysKt.sort(`$this$sortDescending`, fromIndex, toIndex);
      ArraysKt.reverse(`$this$sortDescending`, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun FloatArray.sortDescending(fromIndex: Int, toIndex: Int) {
      ArraysKt.sort(`$this$sortDescending`, fromIndex, toIndex);
      ArraysKt.reverse(`$this$sortDescending`, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun DoubleArray.sortDescending(fromIndex: Int, toIndex: Int) {
      ArraysKt.sort(`$this$sortDescending`, fromIndex, toIndex);
      ArraysKt.reverse(`$this$sortDescending`, fromIndex, toIndex);
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun CharArray.sortDescending(fromIndex: Int, toIndex: Int) {
      ArraysKt.sort(`$this$sortDescending`, fromIndex, toIndex);
      ArraysKt.reverse(`$this$sortDescending`, fromIndex, toIndex);
   }

   @JvmStatic
   public fun Array<out Boolean>.toBooleanArray(): BooleanArray {
      var var1: Int = 0;
      val var2: Int = `$this$toBooleanArray`.length;

      val var3: BooleanArray;
      for (var3 = new boolean[$this$toBooleanArray.length]; var1 < var2; var1++) {
         var3[var1] = `$this$toBooleanArray`[var1];
      }

      return var3;
   }

   @JvmStatic
   public fun Array<out Byte>.toByteArray(): ByteArray {
      var var1: Int = 0;
      val var2: Int = `$this$toByteArray`.length;

      val var3: ByteArray;
      for (var3 = new byte[$this$toByteArray.length]; var1 < var2; var1++) {
         var3[var1] = `$this$toByteArray`[var1];
      }

      return var3;
   }

   @JvmStatic
   public fun Array<out Char>.toCharArray(): CharArray {
      var var1: Int = 0;
      val var2: Int = `$this$toCharArray`.length;

      val var3: CharArray;
      for (var3 = new char[$this$toCharArray.length]; var1 < var2; var1++) {
         var3[var1] = `$this$toCharArray`[var1];
      }

      return var3;
   }

   @JvmStatic
   public fun Array<out Double>.toDoubleArray(): DoubleArray {
      var var1: Int = 0;
      val var2: Int = `$this$toDoubleArray`.length;

      val var3: DoubleArray;
      for (var3 = new double[$this$toDoubleArray.length]; var1 < var2; var1++) {
         var3[var1] = `$this$toDoubleArray`[var1];
      }

      return var3;
   }

   @JvmStatic
   public fun Array<out Float>.toFloatArray(): FloatArray {
      var var1: Int = 0;
      val var2: Int = `$this$toFloatArray`.length;

      val var3: FloatArray;
      for (var3 = new float[$this$toFloatArray.length]; var1 < var2; var1++) {
         var3[var1] = `$this$toFloatArray`[var1];
      }

      return var3;
   }

   @JvmStatic
   public fun Array<out Int>.toIntArray(): IntArray {
      var var1: Int = 0;
      val var2: Int = `$this$toIntArray`.length;

      val var3: IntArray;
      for (var3 = new int[$this$toIntArray.length]; var1 < var2; var1++) {
         var3[var1] = `$this$toIntArray`[var1];
      }

      return var3;
   }

   @JvmStatic
   public fun Array<out Long>.toLongArray(): LongArray {
      var var1: Int = 0;
      val var2: Int = `$this$toLongArray`.length;

      val var3: LongArray;
      for (var3 = new long[$this$toLongArray.length]; var1 < var2; var1++) {
         var3[var1] = `$this$toLongArray`[var1];
      }

      return var3;
   }

   @JvmStatic
   public fun Array<out Short>.toShortArray(): ShortArray {
      var var1: Int = 0;
      val var2: Int = `$this$toShortArray`.length;

      val var3: ShortArray;
      for (var3 = new short[$this$toShortArray.length]; var1 < var2; var1++) {
         var3[var1] = `$this$toShortArray`[var1];
      }

      return var3;
   }

   @JvmStatic
   public inline fun <T, K, V> Array<out T>.associate(transform: (T) -> Pair<K, V>): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associate`.length), 16));

      for (Object element$iv : $this$associate) {
         val var11: Pair = transform.invoke(`element$iv`) as Pair;
         `destination$iv`.put(var11.getFirst(), var11.getSecond());
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> ByteArray.associate(transform: (Byte) -> Pair<K, V>): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associate`.length), 16));

      for (byte element$iv : $this$associate) {
         val var11: Pair = transform.invoke(`element$iv`) as Pair;
         `destination$iv`.put(var11.getFirst(), var11.getSecond());
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> ShortArray.associate(transform: (Short) -> Pair<K, V>): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associate`.length), 16));

      for (short element$iv : $this$associate) {
         val var11: Pair = transform.invoke(`element$iv`) as Pair;
         `destination$iv`.put(var11.getFirst(), var11.getSecond());
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> IntArray.associate(transform: (Int) -> Pair<K, V>): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associate`.length), 16));

      for (int element$iv : $this$associate) {
         val var11: Pair = transform.invoke(`element$iv`) as Pair;
         `destination$iv`.put(var11.getFirst(), var11.getSecond());
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> LongArray.associate(transform: (Long) -> Pair<K, V>): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associate`.length), 16));

      for (long element$iv : $this$associate) {
         val var12: Pair = transform.invoke(`element$iv`) as Pair;
         `destination$iv`.put(var12.getFirst(), var12.getSecond());
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> FloatArray.associate(transform: (Float) -> Pair<K, V>): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associate`.length), 16));

      for (float element$iv : $this$associate) {
         val var11: Pair = transform.invoke(`element$iv`) as Pair;
         `destination$iv`.put(var11.getFirst(), var11.getSecond());
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> DoubleArray.associate(transform: (Double) -> Pair<K, V>): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associate`.length), 16));

      for (double element$iv : $this$associate) {
         val var12: Pair = transform.invoke(`element$iv`) as Pair;
         `destination$iv`.put(var12.getFirst(), var12.getSecond());
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> BooleanArray.associate(transform: (Boolean) -> Pair<K, V>): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associate`.length), 16));

      for (boolean element$iv : $this$associate) {
         val var11: Pair = transform.invoke(`element$iv`) as Pair;
         `destination$iv`.put(var11.getFirst(), var11.getSecond());
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> CharArray.associate(transform: (Char) -> Pair<K, V>): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associate`.length), 16));

      for (char element$iv : $this$associate) {
         val var11: Pair = transform.invoke(`element$iv`) as Pair;
         `destination$iv`.put(var11.getFirst(), var11.getSecond());
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <T, K> Array<out T>.associateBy(keySelector: (T) -> K): Map<K, T> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (Object element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), `element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> ByteArray.associateBy(keySelector: (Byte) -> K): Map<K, Byte> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (byte element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), `element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> ShortArray.associateBy(keySelector: (Short) -> K): Map<K, Short> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (short element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), `element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> IntArray.associateBy(keySelector: (Int) -> K): Map<K, Int> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (int element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), `element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> LongArray.associateBy(keySelector: (Long) -> K): Map<K, Long> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (long element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), `element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> FloatArray.associateBy(keySelector: (Float) -> K): Map<K, Float> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (float element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), `element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> DoubleArray.associateBy(keySelector: (Double) -> K): Map<K, Double> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (double element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), `element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> BooleanArray.associateBy(keySelector: (Boolean) -> K): Map<K, Boolean> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (boolean element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), `element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> CharArray.associateBy(keySelector: (Char) -> K): Map<K, Char> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (char element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), `element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <T, K, V> Array<out T>.associateBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (Object element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> ByteArray.associateBy(keySelector: (Byte) -> K, valueTransform: (Byte) -> V): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (byte element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> ShortArray.associateBy(keySelector: (Short) -> K, valueTransform: (Short) -> V): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (short element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> IntArray.associateBy(keySelector: (Int) -> K, valueTransform: (Int) -> V): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (int element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> LongArray.associateBy(keySelector: (Long) -> K, valueTransform: (Long) -> V): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (long element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> FloatArray.associateBy(keySelector: (Float) -> K, valueTransform: (Float) -> V): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (float element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> DoubleArray.associateBy(keySelector: (Double) -> K, valueTransform: (Double) -> V): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (double element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> BooleanArray.associateBy(keySelector: (Boolean) -> K, valueTransform: (Boolean) -> V): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (boolean element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> CharArray.associateBy(keySelector: (Char) -> K, valueTransform: (Char) -> V): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateBy`.length), 16));

      for (char element$iv : $this$associateBy) {
         `destination$iv`.put(keySelector.invoke(`element$iv`), valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <T, K, M : MutableMap<in K, in T>> Array<out T>.associateByTo(destination: M, keySelector: (T) -> K): M {
      for (Object element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, in Byte>> ByteArray.associateByTo(destination: M, keySelector: (Byte) -> K): M {
      for (byte element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, in Short>> ShortArray.associateByTo(destination: M, keySelector: (Short) -> K): M {
      for (short element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, in Int>> IntArray.associateByTo(destination: M, keySelector: (Int) -> K): M {
      for (int element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, in Long>> LongArray.associateByTo(destination: M, keySelector: (Long) -> K): M {
      for (long element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, in Float>> FloatArray.associateByTo(destination: M, keySelector: (Float) -> K): M {
      for (float element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, in Double>> DoubleArray.associateByTo(destination: M, keySelector: (Double) -> K): M {
      for (double element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, in Boolean>> BooleanArray.associateByTo(destination: M, keySelector: (Boolean) -> K): M {
      for (boolean element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, in Char>> CharArray.associateByTo(destination: M, keySelector: (Char) -> K): M {
      for (char element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <T, K, V, M : MutableMap<in K, in V>> Array<out T>.associateByTo(destination: M, keySelector: (T) -> K, valueTransform: (T) -> V): M {
      for (Object element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> ByteArray.associateByTo(destination: M, keySelector: (Byte) -> K, valueTransform: (Byte) -> V): M {
      for (byte element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> ShortArray.associateByTo(destination: M, keySelector: (Short) -> K, valueTransform: (Short) -> V): M {
      for (short element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> IntArray.associateByTo(destination: M, keySelector: (Int) -> K, valueTransform: (Int) -> V): M {
      for (int element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> LongArray.associateByTo(destination: M, keySelector: (Long) -> K, valueTransform: (Long) -> V): M {
      for (long element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> FloatArray.associateByTo(destination: M, keySelector: (Float) -> K, valueTransform: (Float) -> V): M {
      for (float element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> DoubleArray.associateByTo(destination: M, keySelector: (Double) -> K, valueTransform: (Double) -> V): M {
      for (double element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> BooleanArray.associateByTo(destination: M, keySelector: (Boolean) -> K, valueTransform: (Boolean) -> V): M {
      for (boolean element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> CharArray.associateByTo(destination: M, keySelector: (Char) -> K, valueTransform: (Char) -> V): M {
      for (char element : $this$associateByTo) {
         destination.put(keySelector.invoke(element), valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <T, K, V, M : MutableMap<in K, in V>> Array<out T>.associateTo(destination: M, transform: (T) -> Pair<K, V>): M {
      for (Object element : $this$associateTo) {
         val var8: Pair = transform.invoke(element) as Pair;
         destination.put(var8.getFirst(), var8.getSecond());
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> ByteArray.associateTo(destination: M, transform: (Byte) -> Pair<K, V>): M {
      for (byte element : $this$associateTo) {
         val var8: Pair = transform.invoke(element) as Pair;
         destination.put(var8.getFirst(), var8.getSecond());
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> ShortArray.associateTo(destination: M, transform: (Short) -> Pair<K, V>): M {
      for (short element : $this$associateTo) {
         val var8: Pair = transform.invoke(element) as Pair;
         destination.put(var8.getFirst(), var8.getSecond());
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> IntArray.associateTo(destination: M, transform: (Int) -> Pair<K, V>): M {
      for (int element : $this$associateTo) {
         val var8: Pair = transform.invoke(element) as Pair;
         destination.put(var8.getFirst(), var8.getSecond());
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> LongArray.associateTo(destination: M, transform: (Long) -> Pair<K, V>): M {
      for (long element : $this$associateTo) {
         val var9: Pair = transform.invoke(element) as Pair;
         destination.put(var9.getFirst(), var9.getSecond());
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> FloatArray.associateTo(destination: M, transform: (Float) -> Pair<K, V>): M {
      for (float element : $this$associateTo) {
         val var8: Pair = transform.invoke(element) as Pair;
         destination.put(var8.getFirst(), var8.getSecond());
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> DoubleArray.associateTo(destination: M, transform: (Double) -> Pair<K, V>): M {
      for (double element : $this$associateTo) {
         val var9: Pair = transform.invoke(element) as Pair;
         destination.put(var9.getFirst(), var9.getSecond());
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> BooleanArray.associateTo(destination: M, transform: (Boolean) -> Pair<K, V>): M {
      for (boolean element : $this$associateTo) {
         val var8: Pair = transform.invoke(element) as Pair;
         destination.put(var8.getFirst(), var8.getSecond());
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> CharArray.associateTo(destination: M, transform: (Char) -> Pair<K, V>): M {
      for (char element : $this$associateTo) {
         val var8: Pair = transform.invoke(element) as Pair;
         destination.put(var8.getFirst(), var8.getSecond());
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <K, V> Array<out K>.associateWith(valueSelector: (K) -> V): Map<K, V> {
      val `destination$iv`: java.util.Map = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateWith`.length), 16));

      for (Object element$iv : $this$associateWith) {
         `destination$iv`.put(`element$iv`, valueSelector.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V> ByteArray.associateWith(valueSelector: (Byte) -> V): Map<Byte, V> {
      val result: LinkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateWith`.length), 16));

      for (byte var6 : $this$associateWith) {
         result.put(var6, valueSelector.invoke(var6));
      }

      return result;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V> ShortArray.associateWith(valueSelector: (Short) -> V): Map<Short, V> {
      val result: LinkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateWith`.length), 16));

      for (short var6 : $this$associateWith) {
         result.put(var6, valueSelector.invoke(var6));
      }

      return result;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V> IntArray.associateWith(valueSelector: (Int) -> V): Map<Int, V> {
      val result: LinkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateWith`.length), 16));

      for (int var6 : $this$associateWith) {
         result.put(var6, valueSelector.invoke(var6));
      }

      return result;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V> LongArray.associateWith(valueSelector: (Long) -> V): Map<Long, V> {
      val result: LinkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateWith`.length), 16));

      for (long var6 : $this$associateWith) {
         result.put(var6, valueSelector.invoke(var6));
      }

      return result;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V> FloatArray.associateWith(valueSelector: (Float) -> V): Map<Float, V> {
      val result: LinkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateWith`.length), 16));

      for (float var6 : $this$associateWith) {
         result.put(var6, valueSelector.invoke(var6));
      }

      return result;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V> DoubleArray.associateWith(valueSelector: (Double) -> V): Map<Double, V> {
      val result: LinkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateWith`.length), 16));

      for (double var6 : $this$associateWith) {
         result.put(var6, valueSelector.invoke(var6));
      }

      return result;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V> BooleanArray.associateWith(valueSelector: (Boolean) -> V): Map<Boolean, V> {
      val result: LinkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(`$this$associateWith`.length), 16));

      for (boolean var6 : $this$associateWith) {
         result.put(var6, valueSelector.invoke(var6));
      }

      return result;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V> CharArray.associateWith(valueSelector: (Char) -> V): Map<Char, V> {
      val result: LinkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(RangesKt.coerceAtMost(`$this$associateWith`.length, 128)), 16));

      for (char var6 : $this$associateWith) {
         result.put(var6, valueSelector.invoke(var6));
      }

      return result;
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, in V>> Array<out K>.associateWithTo(destination: M, valueSelector: (K) -> V): M {
      for (Object element : $this$associateWithTo) {
         destination.put(element, valueSelector.invoke(element));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V, M : MutableMap<in Byte, in V>> ByteArray.associateWithTo(destination: M, valueSelector: (Byte) -> V): M {
      for (byte element : $this$associateWithTo) {
         destination.put(element, valueSelector.invoke(element));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V, M : MutableMap<in Short, in V>> ShortArray.associateWithTo(destination: M, valueSelector: (Short) -> V): M {
      for (short element : $this$associateWithTo) {
         destination.put(element, valueSelector.invoke(element));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V, M : MutableMap<in Int, in V>> IntArray.associateWithTo(destination: M, valueSelector: (Int) -> V): M {
      for (int element : $this$associateWithTo) {
         destination.put(element, valueSelector.invoke(element));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V, M : MutableMap<in Long, in V>> LongArray.associateWithTo(destination: M, valueSelector: (Long) -> V): M {
      for (long element : $this$associateWithTo) {
         destination.put(element, valueSelector.invoke(element));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V, M : MutableMap<in Float, in V>> FloatArray.associateWithTo(destination: M, valueSelector: (Float) -> V): M {
      for (float element : $this$associateWithTo) {
         destination.put(element, valueSelector.invoke(element));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V, M : MutableMap<in Double, in V>> DoubleArray.associateWithTo(destination: M, valueSelector: (Double) -> V): M {
      for (double element : $this$associateWithTo) {
         destination.put(element, valueSelector.invoke(element));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V, M : MutableMap<in Boolean, in V>> BooleanArray.associateWithTo(destination: M, valueSelector: (Boolean) -> V): M {
      for (boolean element : $this$associateWithTo) {
         destination.put(element, valueSelector.invoke(element));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <V, M : MutableMap<in Char, in V>> CharArray.associateWithTo(destination: M, valueSelector: (Char) -> V): M {
      for (char element : $this$associateWithTo) {
         destination.put(element, valueSelector.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public fun <T, C : MutableCollection<in T>> Array<out T>.toCollection(destination: C): C {
      for (Object item : $this$toCollection) {
         destination.add(item);
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <C : MutableCollection<in Byte>> ByteArray.toCollection(destination: C): C {
      for (byte item : $this$toCollection) {
         destination.add(item);
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <C : MutableCollection<in Short>> ShortArray.toCollection(destination: C): C {
      for (short item : $this$toCollection) {
         destination.add(item);
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <C : MutableCollection<in Int>> IntArray.toCollection(destination: C): C {
      for (int item : $this$toCollection) {
         destination.add(item);
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <C : MutableCollection<in Long>> LongArray.toCollection(destination: C): C {
      for (long item : $this$toCollection) {
         destination.add(item);
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <C : MutableCollection<in Float>> FloatArray.toCollection(destination: C): C {
      for (float item : $this$toCollection) {
         destination.add(item);
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <C : MutableCollection<in Double>> DoubleArray.toCollection(destination: C): C {
      for (double item : $this$toCollection) {
         destination.add(item);
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <C : MutableCollection<in Boolean>> BooleanArray.toCollection(destination: C): C {
      for (boolean item : $this$toCollection) {
         destination.add(item);
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <C : MutableCollection<in Char>> CharArray.toCollection(destination: C): C {
      for (char item : $this$toCollection) {
         destination.add(item);
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <T> Array<out T>.toHashSet(): HashSet<T> {
      return ArraysKt.toCollection(`$this$toHashSet`, new HashSet(MapsKt.mapCapacity(`$this$toHashSet`.length))) as HashSet<T>;
   }

   @JvmStatic
   public fun ByteArray.toHashSet(): HashSet<Byte> {
      return ArraysKt.toCollection(`$this$toHashSet`, new HashSet<>(MapsKt.mapCapacity(`$this$toHashSet`.length)));
   }

   @JvmStatic
   public fun ShortArray.toHashSet(): HashSet<Short> {
      return ArraysKt.toCollection(`$this$toHashSet`, new HashSet<>(MapsKt.mapCapacity(`$this$toHashSet`.length)));
   }

   @JvmStatic
   public fun IntArray.toHashSet(): HashSet<Int> {
      return ArraysKt.toCollection(`$this$toHashSet`, new HashSet<>(MapsKt.mapCapacity(`$this$toHashSet`.length)));
   }

   @JvmStatic
   public fun LongArray.toHashSet(): HashSet<Long> {
      return ArraysKt.toCollection(`$this$toHashSet`, new HashSet<>(MapsKt.mapCapacity(`$this$toHashSet`.length)));
   }

   @JvmStatic
   public fun FloatArray.toHashSet(): HashSet<Float> {
      return ArraysKt.toCollection(`$this$toHashSet`, new HashSet<>(MapsKt.mapCapacity(`$this$toHashSet`.length)));
   }

   @JvmStatic
   public fun DoubleArray.toHashSet(): HashSet<Double> {
      return ArraysKt.toCollection(`$this$toHashSet`, new HashSet<>(MapsKt.mapCapacity(`$this$toHashSet`.length)));
   }

   @JvmStatic
   public fun BooleanArray.toHashSet(): HashSet<Boolean> {
      return ArraysKt.toCollection(`$this$toHashSet`, new HashSet<>(MapsKt.mapCapacity(`$this$toHashSet`.length)));
   }

   @JvmStatic
   public fun CharArray.toHashSet(): HashSet<Char> {
      return ArraysKt.toCollection(`$this$toHashSet`, new HashSet<>(MapsKt.mapCapacity(RangesKt.coerceAtMost(`$this$toHashSet`.length, 128))));
   }

   @JvmStatic
   public fun <T> Array<out T>.toList(): List<T> {
      var var10000: java.util.List;
      switch ($this$toList.length) {
         case 0:
            var10000 = CollectionsKt.emptyList();
            break;
         case 1:
            var10000 = CollectionsKt.listOf(`$this$toList`[0]);
            break;
         default:
            var10000 = ArraysKt.toMutableList(`$this$toList`);
      }

      return var10000;
   }

   @JvmStatic
   public fun ByteArray.toList(): List<Byte> {
      var var10000: java.util.List;
      switch ($this$toList.length) {
         case 0:
            var10000 = CollectionsKt.emptyList();
            break;
         case 1:
            var10000 = CollectionsKt.listOf(`$this$toList`[0]);
            break;
         default:
            var10000 = ArraysKt.toMutableList(`$this$toList`);
      }

      return var10000;
   }

   @JvmStatic
   public fun ShortArray.toList(): List<Short> {
      var var10000: java.util.List;
      switch ($this$toList.length) {
         case 0:
            var10000 = CollectionsKt.emptyList();
            break;
         case 1:
            var10000 = CollectionsKt.listOf(`$this$toList`[0]);
            break;
         default:
            var10000 = ArraysKt.toMutableList(`$this$toList`);
      }

      return var10000;
   }

   @JvmStatic
   public fun IntArray.toList(): List<Int> {
      var var10000: java.util.List;
      switch ($this$toList.length) {
         case 0:
            var10000 = CollectionsKt.emptyList();
            break;
         case 1:
            var10000 = CollectionsKt.listOf(`$this$toList`[0]);
            break;
         default:
            var10000 = ArraysKt.toMutableList(`$this$toList`);
      }

      return var10000;
   }

   @JvmStatic
   public fun LongArray.toList(): List<Long> {
      var var10000: java.util.List;
      switch ($this$toList.length) {
         case 0:
            var10000 = CollectionsKt.emptyList();
            break;
         case 1:
            var10000 = CollectionsKt.listOf(`$this$toList`[0]);
            break;
         default:
            var10000 = ArraysKt.toMutableList(`$this$toList`);
      }

      return var10000;
   }

   @JvmStatic
   public fun FloatArray.toList(): List<Float> {
      var var10000: java.util.List;
      switch ($this$toList.length) {
         case 0:
            var10000 = CollectionsKt.emptyList();
            break;
         case 1:
            var10000 = CollectionsKt.listOf(`$this$toList`[0]);
            break;
         default:
            var10000 = ArraysKt.toMutableList(`$this$toList`);
      }

      return var10000;
   }

   @JvmStatic
   public fun DoubleArray.toList(): List<Double> {
      var var10000: java.util.List;
      switch ($this$toList.length) {
         case 0:
            var10000 = CollectionsKt.emptyList();
            break;
         case 1:
            var10000 = CollectionsKt.listOf(`$this$toList`[0]);
            break;
         default:
            var10000 = ArraysKt.toMutableList(`$this$toList`);
      }

      return var10000;
   }

   @JvmStatic
   public fun BooleanArray.toList(): List<Boolean> {
      var var10000: java.util.List;
      switch ($this$toList.length) {
         case 0:
            var10000 = CollectionsKt.emptyList();
            break;
         case 1:
            var10000 = CollectionsKt.listOf(`$this$toList`[0]);
            break;
         default:
            var10000 = ArraysKt.toMutableList(`$this$toList`);
      }

      return var10000;
   }

   @JvmStatic
   public fun CharArray.toList(): List<Char> {
      var var10000: java.util.List;
      switch ($this$toList.length) {
         case 0:
            var10000 = CollectionsKt.emptyList();
            break;
         case 1:
            var10000 = CollectionsKt.listOf(`$this$toList`[0]);
            break;
         default:
            var10000 = ArraysKt.toMutableList(`$this$toList`);
      }

      return var10000;
   }

   @JvmStatic
   public fun <T> Array<out T>.toMutableList(): MutableList<T> {
      return new ArrayList(CollectionsKt.asCollection$default(`$this$toMutableList`, false, 1, null));
   }

   @JvmStatic
   public fun ByteArray.toMutableList(): MutableList<Byte> {
      val list: ArrayList = new ArrayList(`$this$toMutableList`.length);

      for (byte item : $this$toMutableList) {
         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public fun ShortArray.toMutableList(): MutableList<Short> {
      val list: ArrayList = new ArrayList(`$this$toMutableList`.length);

      for (short item : $this$toMutableList) {
         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public fun IntArray.toMutableList(): MutableList<Int> {
      val list: ArrayList = new ArrayList(`$this$toMutableList`.length);

      for (int item : $this$toMutableList) {
         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public fun LongArray.toMutableList(): MutableList<Long> {
      val list: ArrayList = new ArrayList(`$this$toMutableList`.length);

      for (long item : $this$toMutableList) {
         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public fun FloatArray.toMutableList(): MutableList<Float> {
      val list: ArrayList = new ArrayList(`$this$toMutableList`.length);

      for (float item : $this$toMutableList) {
         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public fun DoubleArray.toMutableList(): MutableList<Double> {
      val list: ArrayList = new ArrayList(`$this$toMutableList`.length);

      for (double item : $this$toMutableList) {
         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public fun BooleanArray.toMutableList(): MutableList<Boolean> {
      val list: ArrayList = new ArrayList(`$this$toMutableList`.length);

      for (boolean item : $this$toMutableList) {
         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public fun CharArray.toMutableList(): MutableList<Char> {
      val list: ArrayList = new ArrayList(`$this$toMutableList`.length);

      for (char item : $this$toMutableList) {
         list.add(item);
      }

      return list;
   }

   @JvmStatic
   public fun <T> Array<out T>.toSet(): Set<T> {
      var var10000: java.util.Set;
      switch ($this$toSet.length) {
         case 0:
            var10000 = SetsKt.emptySet();
            break;
         case 1:
            var10000 = SetsKt.setOf(`$this$toSet`[0]);
            break;
         default:
            var10000 = ArraysKt.toCollection(`$this$toSet`, new LinkedHashSet(MapsKt.mapCapacity(`$this$toSet`.length)));
      }

      return var10000;
   }

   @JvmStatic
   public fun ByteArray.toSet(): Set<Byte> {
      var var10000: java.util.Set;
      switch ($this$toSet.length) {
         case 0:
            var10000 = SetsKt.emptySet();
            break;
         case 1:
            var10000 = SetsKt.setOf(`$this$toSet`[0]);
            break;
         default:
            var10000 = ArraysKt.toCollection(`$this$toSet`, new LinkedHashSet(MapsKt.mapCapacity(`$this$toSet`.length)));
      }

      return var10000;
   }

   @JvmStatic
   public fun ShortArray.toSet(): Set<Short> {
      var var10000: java.util.Set;
      switch ($this$toSet.length) {
         case 0:
            var10000 = SetsKt.emptySet();
            break;
         case 1:
            var10000 = SetsKt.setOf(`$this$toSet`[0]);
            break;
         default:
            var10000 = ArraysKt.toCollection(`$this$toSet`, new LinkedHashSet(MapsKt.mapCapacity(`$this$toSet`.length)));
      }

      return var10000;
   }

   @JvmStatic
   public fun IntArray.toSet(): Set<Int> {
      var var10000: java.util.Set;
      switch ($this$toSet.length) {
         case 0:
            var10000 = SetsKt.emptySet();
            break;
         case 1:
            var10000 = SetsKt.setOf(`$this$toSet`[0]);
            break;
         default:
            var10000 = ArraysKt.toCollection(`$this$toSet`, new LinkedHashSet(MapsKt.mapCapacity(`$this$toSet`.length)));
      }

      return var10000;
   }

   @JvmStatic
   public fun LongArray.toSet(): Set<Long> {
      var var10000: java.util.Set;
      switch ($this$toSet.length) {
         case 0:
            var10000 = SetsKt.emptySet();
            break;
         case 1:
            var10000 = SetsKt.setOf(`$this$toSet`[0]);
            break;
         default:
            var10000 = ArraysKt.toCollection(`$this$toSet`, new LinkedHashSet(MapsKt.mapCapacity(`$this$toSet`.length)));
      }

      return var10000;
   }

   @JvmStatic
   public fun FloatArray.toSet(): Set<Float> {
      var var10000: java.util.Set;
      switch ($this$toSet.length) {
         case 0:
            var10000 = SetsKt.emptySet();
            break;
         case 1:
            var10000 = SetsKt.setOf(`$this$toSet`[0]);
            break;
         default:
            var10000 = ArraysKt.toCollection(`$this$toSet`, new LinkedHashSet(MapsKt.mapCapacity(`$this$toSet`.length)));
      }

      return var10000;
   }

   @JvmStatic
   public fun DoubleArray.toSet(): Set<Double> {
      var var10000: java.util.Set;
      switch ($this$toSet.length) {
         case 0:
            var10000 = SetsKt.emptySet();
            break;
         case 1:
            var10000 = SetsKt.setOf(`$this$toSet`[0]);
            break;
         default:
            var10000 = ArraysKt.toCollection(`$this$toSet`, new LinkedHashSet(MapsKt.mapCapacity(`$this$toSet`.length)));
      }

      return var10000;
   }

   @JvmStatic
   public fun BooleanArray.toSet(): Set<Boolean> {
      var var10000: java.util.Set;
      switch ($this$toSet.length) {
         case 0:
            var10000 = SetsKt.emptySet();
            break;
         case 1:
            var10000 = SetsKt.setOf(`$this$toSet`[0]);
            break;
         default:
            var10000 = ArraysKt.toCollection(`$this$toSet`, new LinkedHashSet(MapsKt.mapCapacity(`$this$toSet`.length)));
      }

      return var10000;
   }

   @JvmStatic
   public fun CharArray.toSet(): Set<Char> {
      var var10000: java.util.Set;
      switch ($this$toSet.length) {
         case 0:
            var10000 = SetsKt.emptySet();
            break;
         case 1:
            var10000 = SetsKt.setOf(`$this$toSet`[0]);
            break;
         default:
            var10000 = ArraysKt.toCollection(`$this$toSet`, new LinkedHashSet(MapsKt.mapCapacity(RangesKt.coerceAtMost(`$this$toSet`.length, 128))));
      }

      return var10000;
   }

   @JvmStatic
   public inline fun <T, R> Array<out T>.flatMap(transform: (T) -> Iterable<R>): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv : $this$flatMap) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`element$iv`) as java.lang.Iterable);
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> ByteArray.flatMap(transform: (Byte) -> Iterable<R>): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (byte element$iv : $this$flatMap) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`element$iv`) as java.lang.Iterable);
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> ShortArray.flatMap(transform: (Short) -> Iterable<R>): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (short element$iv : $this$flatMap) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`element$iv`) as java.lang.Iterable);
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> IntArray.flatMap(transform: (Int) -> Iterable<R>): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (int element$iv : $this$flatMap) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`element$iv`) as java.lang.Iterable);
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> LongArray.flatMap(transform: (Long) -> Iterable<R>): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (long element$iv : $this$flatMap) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`element$iv`) as java.lang.Iterable);
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> FloatArray.flatMap(transform: (Float) -> Iterable<R>): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (float element$iv : $this$flatMap) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`element$iv`) as java.lang.Iterable);
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> DoubleArray.flatMap(transform: (Double) -> Iterable<R>): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (double element$iv : $this$flatMap) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`element$iv`) as java.lang.Iterable);
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> BooleanArray.flatMap(transform: (Boolean) -> Iterable<R>): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (boolean element$iv : $this$flatMap) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`element$iv`) as java.lang.Iterable);
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> CharArray.flatMap(transform: (Char) -> Iterable<R>): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (char element$iv : $this$flatMap) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`element$iv`) as java.lang.Iterable);
      }

      return `destination$iv` as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapSequence")
   @JvmStatic
   public inline fun <T, R> Array<out T>.flatMap(transform: (T) -> Sequence<R>): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv : $this$flatMap) {
         CollectionsKt.addAll(`destination$iv`, transform.invoke(`element$iv`) as Sequence);
      }

      return `destination$iv` as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterable")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R> Array<out T>.flatMapIndexed(transform: (Int, T) -> Iterable<R>): List<R> {
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (Object var7 : $this$flatMapIndexed) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, var7) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterable")
   @InlineOnly
   @JvmStatic
   public inline fun <R> ByteArray.flatMapIndexed(transform: (Int, Byte) -> Iterable<R>): List<R> {
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (byte var7 : $this$flatMapIndexed) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, var7) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterable")
   @InlineOnly
   @JvmStatic
   public inline fun <R> ShortArray.flatMapIndexed(transform: (Int, Short) -> Iterable<R>): List<R> {
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (short var7 : $this$flatMapIndexed) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, var7) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterable")
   @InlineOnly
   @JvmStatic
   public inline fun <R> IntArray.flatMapIndexed(transform: (Int, Int) -> Iterable<R>): List<R> {
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (int var7 : $this$flatMapIndexed) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, var7) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterable")
   @InlineOnly
   @JvmStatic
   public inline fun <R> LongArray.flatMapIndexed(transform: (Int, Long) -> Iterable<R>): List<R> {
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (long var7 : $this$flatMapIndexed) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, var7) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterable")
   @InlineOnly
   @JvmStatic
   public inline fun <R> FloatArray.flatMapIndexed(transform: (Int, Float) -> Iterable<R>): List<R> {
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (float var7 : $this$flatMapIndexed) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, var7) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterable")
   @InlineOnly
   @JvmStatic
   public inline fun <R> DoubleArray.flatMapIndexed(transform: (Int, Double) -> Iterable<R>): List<R> {
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (double var7 : $this$flatMapIndexed) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, var7) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterable")
   @InlineOnly
   @JvmStatic
   public inline fun <R> BooleanArray.flatMapIndexed(transform: (Int, Boolean) -> Iterable<R>): List<R> {
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (boolean var7 : $this$flatMapIndexed) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, var7) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterable")
   @InlineOnly
   @JvmStatic
   public inline fun <R> CharArray.flatMapIndexed(transform: (Int, Char) -> Iterable<R>): List<R> {
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (char var7 : $this$flatMapIndexed) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, var7) as java.lang.Iterable);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedSequence")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R> Array<out T>.flatMapIndexed(transform: (Int, T) -> Sequence<R>): List<R> {
      val var3: java.util.Collection = new ArrayList();
      var var4: Int = 0;

      for (Object var7 : $this$flatMapIndexed) {
         CollectionsKt.addAll(var3, transform.invoke(var4++, var7) as Sequence);
      }

      return var3 as MutableList<R>;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterableTo")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Array<out T>.flatMapIndexedTo(destination: C, transform: (Int, T) -> Iterable<R>): C {
      var index: Int = 0;

      for (Object element : $this$flatMapIndexedTo) {
         CollectionsKt.addAll(destination, transform.invoke(index++, element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterableTo")
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> ByteArray.flatMapIndexedTo(destination: C, transform: (Int, Byte) -> Iterable<R>): C {
      var index: Int = 0;

      for (byte element : $this$flatMapIndexedTo) {
         CollectionsKt.addAll(destination, transform.invoke(index++, element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterableTo")
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> ShortArray.flatMapIndexedTo(destination: C, transform: (Int, Short) -> Iterable<R>): C {
      var index: Int = 0;

      for (short element : $this$flatMapIndexedTo) {
         CollectionsKt.addAll(destination, transform.invoke(index++, element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterableTo")
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> IntArray.flatMapIndexedTo(destination: C, transform: (Int, Int) -> Iterable<R>): C {
      var index: Int = 0;

      for (int element : $this$flatMapIndexedTo) {
         CollectionsKt.addAll(destination, transform.invoke(index++, element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterableTo")
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> LongArray.flatMapIndexedTo(destination: C, transform: (Int, Long) -> Iterable<R>): C {
      var index: Int = 0;

      for (long element : $this$flatMapIndexedTo) {
         CollectionsKt.addAll(destination, transform.invoke(index++, element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterableTo")
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> FloatArray.flatMapIndexedTo(destination: C, transform: (Int, Float) -> Iterable<R>): C {
      var index: Int = 0;

      for (float element : $this$flatMapIndexedTo) {
         CollectionsKt.addAll(destination, transform.invoke(index++, element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterableTo")
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> DoubleArray.flatMapIndexedTo(destination: C, transform: (Int, Double) -> Iterable<R>): C {
      var index: Int = 0;

      for (double element : $this$flatMapIndexedTo) {
         CollectionsKt.addAll(destination, transform.invoke(index++, element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterableTo")
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> BooleanArray.flatMapIndexedTo(destination: C, transform: (Int, Boolean) -> Iterable<R>): C {
      var index: Int = 0;

      for (boolean element : $this$flatMapIndexedTo) {
         CollectionsKt.addAll(destination, transform.invoke(index++, element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedIterableTo")
   @InlineOnly
   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> CharArray.flatMapIndexedTo(destination: C, transform: (Int, Char) -> Iterable<R>): C {
      var index: Int = 0;

      for (char element : $this$flatMapIndexedTo) {
         CollectionsKt.addAll(destination, transform.invoke(index++, element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapIndexedSequenceTo")
   @InlineOnly
   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Array<out T>.flatMapIndexedTo(destination: C, transform: (Int, T) -> Sequence<R>): C {
      var index: Int = 0;

      for (Object element : $this$flatMapIndexedTo) {
         CollectionsKt.addAll(destination, transform.invoke(index++, element) as Sequence);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Array<out T>.flatMapTo(destination: C, transform: (T) -> Iterable<R>): C {
      for (Object element : $this$flatMapTo) {
         CollectionsKt.addAll(destination, transform.invoke(element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> ByteArray.flatMapTo(destination: C, transform: (Byte) -> Iterable<R>): C {
      for (byte element : $this$flatMapTo) {
         CollectionsKt.addAll(destination, transform.invoke(element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> ShortArray.flatMapTo(destination: C, transform: (Short) -> Iterable<R>): C {
      for (short element : $this$flatMapTo) {
         CollectionsKt.addAll(destination, transform.invoke(element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> IntArray.flatMapTo(destination: C, transform: (Int) -> Iterable<R>): C {
      for (int element : $this$flatMapTo) {
         CollectionsKt.addAll(destination, transform.invoke(element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> LongArray.flatMapTo(destination: C, transform: (Long) -> Iterable<R>): C {
      for (long element : $this$flatMapTo) {
         CollectionsKt.addAll(destination, transform.invoke(element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> FloatArray.flatMapTo(destination: C, transform: (Float) -> Iterable<R>): C {
      for (float element : $this$flatMapTo) {
         CollectionsKt.addAll(destination, transform.invoke(element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> DoubleArray.flatMapTo(destination: C, transform: (Double) -> Iterable<R>): C {
      for (double element : $this$flatMapTo) {
         CollectionsKt.addAll(destination, transform.invoke(element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> BooleanArray.flatMapTo(destination: C, transform: (Boolean) -> Iterable<R>): C {
      for (boolean element : $this$flatMapTo) {
         CollectionsKt.addAll(destination, transform.invoke(element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> CharArray.flatMapTo(destination: C, transform: (Char) -> Iterable<R>): C {
      for (char element : $this$flatMapTo) {
         CollectionsKt.addAll(destination, transform.invoke(element) as java.lang.Iterable);
      }

      return (C)destination;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "flatMapSequenceTo")
   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Array<out T>.flatMapTo(destination: C, transform: (T) -> Sequence<R>): C {
      for (Object element : $this$flatMapTo) {
         CollectionsKt.addAll(destination, transform.invoke(element) as Sequence);
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, K> Array<out T>.groupBy(keySelector: (T) -> K): Map<K, List<T>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (Object element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var16: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var16);
            var10000 = var16;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(`element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> ByteArray.groupBy(keySelector: (Byte) -> K): Map<K, List<Byte>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (byte element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var16: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var16);
            var10000 = var16;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(`element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> ShortArray.groupBy(keySelector: (Short) -> K): Map<K, List<Short>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (short element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var16: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var16);
            var10000 = var16;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(`element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> IntArray.groupBy(keySelector: (Int) -> K): Map<K, List<Int>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (int element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var16: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var16);
            var10000 = var16;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(`element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> LongArray.groupBy(keySelector: (Long) -> K): Map<K, List<Long>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (long element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var17: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var17);
            var10000 = var17;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(`element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> FloatArray.groupBy(keySelector: (Float) -> K): Map<K, List<Float>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (float element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var16: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var16);
            var10000 = var16;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(`element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> DoubleArray.groupBy(keySelector: (Double) -> K): Map<K, List<Double>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (double element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var17: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var17);
            var10000 = var17;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(`element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> BooleanArray.groupBy(keySelector: (Boolean) -> K): Map<K, List<Boolean>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (boolean element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var16: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var16);
            var10000 = var16;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(`element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K> CharArray.groupBy(keySelector: (Char) -> K): Map<K, List<Char>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (char element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var16: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var16);
            var10000 = var16;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(`element$iv`);
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <T, K, V> Array<out T>.groupBy(keySelector: (T) -> K, valueTransform: (T) -> V): Map<K, List<V>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (Object element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var17: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var17);
            var10000 = var17;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> ByteArray.groupBy(keySelector: (Byte) -> K, valueTransform: (Byte) -> V): Map<K, List<V>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (byte element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var17: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var17);
            var10000 = var17;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> ShortArray.groupBy(keySelector: (Short) -> K, valueTransform: (Short) -> V): Map<K, List<V>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (short element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var17: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var17);
            var10000 = var17;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> IntArray.groupBy(keySelector: (Int) -> K, valueTransform: (Int) -> V): Map<K, List<V>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (int element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var17: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var17);
            var10000 = var17;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> LongArray.groupBy(keySelector: (Long) -> K, valueTransform: (Long) -> V): Map<K, List<V>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (long element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var18: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var18);
            var10000 = var18;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> FloatArray.groupBy(keySelector: (Float) -> K, valueTransform: (Float) -> V): Map<K, List<V>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (float element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var17: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var17);
            var10000 = var17;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> DoubleArray.groupBy(keySelector: (Double) -> K, valueTransform: (Double) -> V): Map<K, List<V>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (double element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var18: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var18);
            var10000 = var18;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> BooleanArray.groupBy(keySelector: (Boolean) -> K, valueTransform: (Boolean) -> V): Map<K, List<V>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (boolean element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var17: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var17);
            var10000 = var17;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <K, V> CharArray.groupBy(keySelector: (Char) -> K, valueTransform: (Char) -> V): Map<K, List<V>> {
      val `destination$iv`: java.util.Map = new LinkedHashMap();

      for (char element$iv : $this$groupBy) {
         val `key$iv`: Any = keySelector.invoke(`element$iv`);
         val `value$iv$iv`: Any = `destination$iv`.get(`key$iv`);
         val var10000: Any;
         if (`value$iv$iv` == null) {
            val var17: Any = new ArrayList();
            `destination$iv`.put(`key$iv`, var17);
            var10000 = var17;
         } else {
            var10000 = `value$iv$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(`element$iv`));
      }

      return `destination$iv`;
   }

   @JvmStatic
   public inline fun <T, K, M : MutableMap<in K, MutableList<T>>> Array<out T>.groupByTo(destination: M, keySelector: (T) -> K): M {
      for (Object element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var14: Any = new ArrayList();
            destination.put(key, var14);
            var10000 = var14;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, MutableList<Byte>>> ByteArray.groupByTo(destination: M, keySelector: (Byte) -> K): M {
      for (byte element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var14: Any = new ArrayList();
            destination.put(key, var14);
            var10000 = var14;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, MutableList<Short>>> ShortArray.groupByTo(destination: M, keySelector: (Short) -> K): M {
      for (short element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var14: Any = new ArrayList();
            destination.put(key, var14);
            var10000 = var14;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, MutableList<Int>>> IntArray.groupByTo(destination: M, keySelector: (Int) -> K): M {
      for (int element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var14: Any = new ArrayList();
            destination.put(key, var14);
            var10000 = var14;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, MutableList<Long>>> LongArray.groupByTo(destination: M, keySelector: (Long) -> K): M {
      for (long element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var15: Any = new ArrayList();
            destination.put(key, var15);
            var10000 = var15;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, MutableList<Float>>> FloatArray.groupByTo(destination: M, keySelector: (Float) -> K): M {
      for (float element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var14: Any = new ArrayList();
            destination.put(key, var14);
            var10000 = var14;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, MutableList<Double>>> DoubleArray.groupByTo(destination: M, keySelector: (Double) -> K): M {
      for (double element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var15: Any = new ArrayList();
            destination.put(key, var15);
            var10000 = var15;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, MutableList<Boolean>>> BooleanArray.groupByTo(destination: M, keySelector: (Boolean) -> K): M {
      for (boolean element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var14: Any = new ArrayList();
            destination.put(key, var14);
            var10000 = var14;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, M : MutableMap<in K, MutableList<Char>>> CharArray.groupByTo(destination: M, keySelector: (Char) -> K): M {
      for (char element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var14: Any = new ArrayList();
            destination.put(key, var14);
            var10000 = var14;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(element);
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <T, K, V, M : MutableMap<in K, MutableList<V>>> Array<out T>.groupByTo(destination: M, keySelector: (T) -> K, valueTransform: (T) -> V): M {
      for (Object element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var15: Any = new ArrayList();
            destination.put(key, var15);
            var10000 = var15;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, MutableList<V>>> ByteArray.groupByTo(destination: M, keySelector: (Byte) -> K, valueTransform: (Byte) -> V): M {
      for (byte element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var15: Any = new ArrayList();
            destination.put(key, var15);
            var10000 = var15;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, MutableList<V>>> ShortArray.groupByTo(destination: M, keySelector: (Short) -> K, valueTransform: (Short) -> V): M {
      for (short element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var15: Any = new ArrayList();
            destination.put(key, var15);
            var10000 = var15;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, MutableList<V>>> IntArray.groupByTo(destination: M, keySelector: (Int) -> K, valueTransform: (Int) -> V): M {
      for (int element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var15: Any = new ArrayList();
            destination.put(key, var15);
            var10000 = var15;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, MutableList<V>>> LongArray.groupByTo(destination: M, keySelector: (Long) -> K, valueTransform: (Long) -> V): M {
      for (long element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var16: Any = new ArrayList();
            destination.put(key, var16);
            var10000 = var16;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, MutableList<V>>> FloatArray.groupByTo(destination: M, keySelector: (Float) -> K, valueTransform: (Float) -> V): M {
      for (float element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var15: Any = new ArrayList();
            destination.put(key, var15);
            var10000 = var15;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, MutableList<V>>> DoubleArray.groupByTo(
      destination: M,
      keySelector: (Double) -> K,
      valueTransform: (Double) -> V
   ): M {
      for (double element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var16: Any = new ArrayList();
            destination.put(key, var16);
            var10000 = var16;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, MutableList<V>>> BooleanArray.groupByTo(
      destination: M,
      keySelector: (Boolean) -> K,
      valueTransform: (Boolean) -> V
   ): M {
      for (boolean element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var15: Any = new ArrayList();
            destination.put(key, var15);
            var10000 = var15;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @JvmStatic
   public inline fun <K, V, M : MutableMap<in K, MutableList<V>>> CharArray.groupByTo(destination: M, keySelector: (Char) -> K, valueTransform: (Char) -> V): M {
      for (char element : $this$groupByTo) {
         val key: Any = keySelector.invoke(element);
         val `value$iv`: Any = destination.get(key);
         val var10000: Any;
         if (`value$iv` == null) {
            val var15: Any = new ArrayList();
            destination.put(key, var15);
            var10000 = var15;
         } else {
            var10000 = `value$iv`;
         }

         (var10000 as java.util.List).add(valueTransform.invoke(element));
      }

      return (M)destination;
   }

   @SinceKotlin(version = "1.1")
   @JvmStatic
   public inline fun <T, K> Array<out T>.groupingBy(crossinline keySelector: (T) -> K): Grouping<T, K> {
      return new kotlin.collections.ArraysKt___ArraysKt.groupingBy.1(`$this$groupingBy`, keySelector);
   }

   @JvmStatic
   public inline fun <T, R> Array<out T>.map(transform: (T) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$map`.length);

      for (Object item$iv : $this$map) {
         `destination$iv`.add(transform.invoke(`item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> ByteArray.map(transform: (Byte) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$map`.length);

      for (byte item$iv : $this$map) {
         `destination$iv`.add(transform.invoke(`item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> ShortArray.map(transform: (Short) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$map`.length);

      for (short item$iv : $this$map) {
         `destination$iv`.add(transform.invoke(`item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> IntArray.map(transform: (Int) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$map`.length);

      for (int item$iv : $this$map) {
         `destination$iv`.add(transform.invoke(`item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> LongArray.map(transform: (Long) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$map`.length);

      for (long item$iv : $this$map) {
         `destination$iv`.add(transform.invoke(`item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> FloatArray.map(transform: (Float) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$map`.length);

      for (float item$iv : $this$map) {
         `destination$iv`.add(transform.invoke(`item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> DoubleArray.map(transform: (Double) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$map`.length);

      for (double item$iv : $this$map) {
         `destination$iv`.add(transform.invoke(`item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> BooleanArray.map(transform: (Boolean) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$map`.length);

      for (boolean item$iv : $this$map) {
         `destination$iv`.add(transform.invoke(`item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> CharArray.map(transform: (Char) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$map`.length);

      for (char item$iv : $this$map) {
         `destination$iv`.add(transform.invoke(`item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <T, R> Array<out T>.mapIndexed(transform: (Int, T) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$mapIndexed`.length);
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$mapIndexed) {
         `destination$iv`.add(transform.invoke(`index$iv`++, `item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> ByteArray.mapIndexed(transform: (Int, Byte) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$mapIndexed`.length);
      var `index$iv`: Int = 0;

      for (byte item$iv : $this$mapIndexed) {
         `destination$iv`.add(transform.invoke(`index$iv`++, `item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> ShortArray.mapIndexed(transform: (Int, Short) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$mapIndexed`.length);
      var `index$iv`: Int = 0;

      for (short item$iv : $this$mapIndexed) {
         `destination$iv`.add(transform.invoke(`index$iv`++, `item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> IntArray.mapIndexed(transform: (Int, Int) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$mapIndexed`.length);
      var `index$iv`: Int = 0;

      for (int item$iv : $this$mapIndexed) {
         `destination$iv`.add(transform.invoke(`index$iv`++, `item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> LongArray.mapIndexed(transform: (Int, Long) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$mapIndexed`.length);
      var `index$iv`: Int = 0;

      for (long item$iv : $this$mapIndexed) {
         `destination$iv`.add(transform.invoke(`index$iv`++, `item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> FloatArray.mapIndexed(transform: (Int, Float) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$mapIndexed`.length);
      var `index$iv`: Int = 0;

      for (float item$iv : $this$mapIndexed) {
         `destination$iv`.add(transform.invoke(`index$iv`++, `item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> DoubleArray.mapIndexed(transform: (Int, Double) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$mapIndexed`.length);
      var `index$iv`: Int = 0;

      for (double item$iv : $this$mapIndexed) {
         `destination$iv`.add(transform.invoke(`index$iv`++, `item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> BooleanArray.mapIndexed(transform: (Int, Boolean) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$mapIndexed`.length);
      var `index$iv`: Int = 0;

      for (boolean item$iv : $this$mapIndexed) {
         `destination$iv`.add(transform.invoke(`index$iv`++, `item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <R> CharArray.mapIndexed(transform: (Int, Char) -> R): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList(`$this$mapIndexed`.length);
      var `index$iv`: Int = 0;

      for (char item$iv : $this$mapIndexed) {
         `destination$iv`.add(transform.invoke(`index$iv`++, `item$iv`));
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <T, R : Any> Array<out T>.mapIndexedNotNull(transform: (Int, T) -> R?): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();
      var `index$iv$iv`: Int = 0;

      for (Object item$iv$iv : $this$mapIndexedNotNull) {
         val var17: Any = transform.invoke(`index$iv$iv`++, `item$iv$iv`);
         if (var17 != null) {
            `destination$iv`.add(var17);
         }
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <T, R : Any, C : MutableCollection<in R>> Array<out T>.mapIndexedNotNullTo(destination: C, transform: (Int, T) -> R?): C {
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$mapIndexedNotNullTo) {
         val var15: Any = transform.invoke(`index$iv`++, `item$iv`);
         if (var15 != null) {
            destination.add(var15);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Array<out T>.mapIndexedTo(destination: C, transform: (Int, T) -> R): C {
      var index: Int = 0;

      for (Object item : $this$mapIndexedTo) {
         destination.add(transform.invoke(index++, item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> ByteArray.mapIndexedTo(destination: C, transform: (Int, Byte) -> R): C {
      var index: Int = 0;

      for (byte item : $this$mapIndexedTo) {
         destination.add(transform.invoke(index++, item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> ShortArray.mapIndexedTo(destination: C, transform: (Int, Short) -> R): C {
      var index: Int = 0;

      for (short item : $this$mapIndexedTo) {
         destination.add(transform.invoke(index++, item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> IntArray.mapIndexedTo(destination: C, transform: (Int, Int) -> R): C {
      var index: Int = 0;

      for (int item : $this$mapIndexedTo) {
         destination.add(transform.invoke(index++, item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> LongArray.mapIndexedTo(destination: C, transform: (Int, Long) -> R): C {
      var index: Int = 0;

      for (long item : $this$mapIndexedTo) {
         destination.add(transform.invoke(index++, item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> FloatArray.mapIndexedTo(destination: C, transform: (Int, Float) -> R): C {
      var index: Int = 0;

      for (float item : $this$mapIndexedTo) {
         destination.add(transform.invoke(index++, item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> DoubleArray.mapIndexedTo(destination: C, transform: (Int, Double) -> R): C {
      var index: Int = 0;

      for (double item : $this$mapIndexedTo) {
         destination.add(transform.invoke(index++, item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> BooleanArray.mapIndexedTo(destination: C, transform: (Int, Boolean) -> R): C {
      var index: Int = 0;

      for (boolean item : $this$mapIndexedTo) {
         destination.add(transform.invoke(index++, item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> CharArray.mapIndexedTo(destination: C, transform: (Int, Char) -> R): C {
      var index: Int = 0;

      for (char item : $this$mapIndexedTo) {
         destination.add(transform.invoke(index++, item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, R : Any> Array<out T>.mapNotNull(transform: (T) -> R?): List<R> {
      val `destination$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$mapNotNull) {
         val var10000: Any = transform.invoke(`element$iv$iv`);
         if (var10000 != null) {
            `destination$iv`.add(var10000);
         }
      }

      return `destination$iv` as MutableList<R>;
   }

   @JvmStatic
   public inline fun <T, R : Any, C : MutableCollection<in R>> Array<out T>.mapNotNullTo(destination: C, transform: (T) -> R?): C {
      for (Object element$iv : $this$mapNotNullTo) {
         val var10000: Any = transform.invoke(`element$iv`);
         if (var10000 != null) {
            destination.add(var10000);
         }
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <T, R, C : MutableCollection<in R>> Array<out T>.mapTo(destination: C, transform: (T) -> R): C {
      for (Object item : $this$mapTo) {
         destination.add(transform.invoke(item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> ByteArray.mapTo(destination: C, transform: (Byte) -> R): C {
      for (byte item : $this$mapTo) {
         destination.add(transform.invoke(item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> ShortArray.mapTo(destination: C, transform: (Short) -> R): C {
      for (short item : $this$mapTo) {
         destination.add(transform.invoke(item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> IntArray.mapTo(destination: C, transform: (Int) -> R): C {
      for (int item : $this$mapTo) {
         destination.add(transform.invoke(item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> LongArray.mapTo(destination: C, transform: (Long) -> R): C {
      for (long item : $this$mapTo) {
         destination.add(transform.invoke(item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> FloatArray.mapTo(destination: C, transform: (Float) -> R): C {
      for (float item : $this$mapTo) {
         destination.add(transform.invoke(item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> DoubleArray.mapTo(destination: C, transform: (Double) -> R): C {
      for (double item : $this$mapTo) {
         destination.add(transform.invoke(item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> BooleanArray.mapTo(destination: C, transform: (Boolean) -> R): C {
      for (boolean item : $this$mapTo) {
         destination.add(transform.invoke(item));
      }

      return (C)destination;
   }

   @JvmStatic
   public inline fun <R, C : MutableCollection<in R>> CharArray.mapTo(destination: C, transform: (Char) -> R): C {
      for (char item : $this$mapTo) {
         destination.add(transform.invoke(item));
      }

      return (C)destination;
   }

   @JvmStatic
   public fun <T> Array<out T>.withIndex(): Iterable<IndexedValue<T>> {
      return new IndexingIterable(ArraysKt___ArraysKt::withIndex$lambda$0$ArraysKt___ArraysKt);
   }

   @JvmStatic
   public fun ByteArray.withIndex(): Iterable<IndexedValue<Byte>> {
      return new IndexingIterable<>(ArraysKt___ArraysKt::withIndex$lambda$1$ArraysKt___ArraysKt);
   }

   @JvmStatic
   public fun ShortArray.withIndex(): Iterable<IndexedValue<Short>> {
      return new IndexingIterable<>(ArraysKt___ArraysKt::withIndex$lambda$2$ArraysKt___ArraysKt);
   }

   @JvmStatic
   public fun IntArray.withIndex(): Iterable<IndexedValue<Int>> {
      return new IndexingIterable<>(ArraysKt___ArraysKt::withIndex$lambda$3$ArraysKt___ArraysKt);
   }

   @JvmStatic
   public fun LongArray.withIndex(): Iterable<IndexedValue<Long>> {
      return new IndexingIterable<>(ArraysKt___ArraysKt::withIndex$lambda$4$ArraysKt___ArraysKt);
   }

   @JvmStatic
   public fun FloatArray.withIndex(): Iterable<IndexedValue<Float>> {
      return new IndexingIterable<>(ArraysKt___ArraysKt::withIndex$lambda$5$ArraysKt___ArraysKt);
   }

   @JvmStatic
   public fun DoubleArray.withIndex(): Iterable<IndexedValue<Double>> {
      return new IndexingIterable<>(ArraysKt___ArraysKt::withIndex$lambda$6$ArraysKt___ArraysKt);
   }

   @JvmStatic
   public fun BooleanArray.withIndex(): Iterable<IndexedValue<Boolean>> {
      return new IndexingIterable<>(ArraysKt___ArraysKt::withIndex$lambda$7$ArraysKt___ArraysKt);
   }

   @JvmStatic
   public fun CharArray.withIndex(): Iterable<IndexedValue<Char>> {
      return new IndexingIterable<>(ArraysKt___ArraysKt::withIndex$lambda$8$ArraysKt___ArraysKt);
   }

   @JvmStatic
   public fun <T> Array<out T>.distinct(): List<T> {
      return (java.util.List<T>)CollectionsKt.toList(ArraysKt.toMutableSet(`$this$distinct`));
   }

   @JvmStatic
   public fun ByteArray.distinct(): List<Byte> {
      return CollectionsKt.toList(ArraysKt.toMutableSet(`$this$distinct`));
   }

   @JvmStatic
   public fun ShortArray.distinct(): List<Short> {
      return CollectionsKt.toList(ArraysKt.toMutableSet(`$this$distinct`));
   }

   @JvmStatic
   public fun IntArray.distinct(): List<Int> {
      return CollectionsKt.toList(ArraysKt.toMutableSet(`$this$distinct`));
   }

   @JvmStatic
   public fun LongArray.distinct(): List<Long> {
      return CollectionsKt.toList(ArraysKt.toMutableSet(`$this$distinct`));
   }

   @JvmStatic
   public fun FloatArray.distinct(): List<Float> {
      return CollectionsKt.toList(ArraysKt.toMutableSet(`$this$distinct`));
   }

   @JvmStatic
   public fun DoubleArray.distinct(): List<Double> {
      return CollectionsKt.toList(ArraysKt.toMutableSet(`$this$distinct`));
   }

   @JvmStatic
   public fun BooleanArray.distinct(): List<Boolean> {
      return CollectionsKt.toList(ArraysKt.toMutableSet(`$this$distinct`));
   }

   @JvmStatic
   public fun CharArray.distinct(): List<Char> {
      return CollectionsKt.toList(ArraysKt.toMutableSet(`$this$distinct`));
   }

   @JvmStatic
   public inline fun <T, K> Array<out T>.distinctBy(selector: (T) -> K): List<T> {
      val set: HashSet = new HashSet();
      val list: ArrayList = new ArrayList();

      for (Object e : $this$distinctBy) {
         if (set.add(selector.invoke(e))) {
            list.add(e);
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun <K> ByteArray.distinctBy(selector: (Byte) -> K): List<Byte> {
      val set: HashSet = new HashSet();
      val list: ArrayList = new ArrayList();

      for (byte e : $this$distinctBy) {
         if (set.add(selector.invoke(e))) {
            list.add(e);
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun <K> ShortArray.distinctBy(selector: (Short) -> K): List<Short> {
      val set: HashSet = new HashSet();
      val list: ArrayList = new ArrayList();

      for (short e : $this$distinctBy) {
         if (set.add(selector.invoke(e))) {
            list.add(e);
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun <K> IntArray.distinctBy(selector: (Int) -> K): List<Int> {
      val set: HashSet = new HashSet();
      val list: ArrayList = new ArrayList();

      for (int e : $this$distinctBy) {
         if (set.add(selector.invoke(e))) {
            list.add(e);
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun <K> LongArray.distinctBy(selector: (Long) -> K): List<Long> {
      val set: HashSet = new HashSet();
      val list: ArrayList = new ArrayList();

      for (long e : $this$distinctBy) {
         if (set.add(selector.invoke(e))) {
            list.add(e);
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun <K> FloatArray.distinctBy(selector: (Float) -> K): List<Float> {
      val set: HashSet = new HashSet();
      val list: ArrayList = new ArrayList();

      for (float e : $this$distinctBy) {
         if (set.add(selector.invoke(e))) {
            list.add(e);
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun <K> DoubleArray.distinctBy(selector: (Double) -> K): List<Double> {
      val set: HashSet = new HashSet();
      val list: ArrayList = new ArrayList();

      for (double e : $this$distinctBy) {
         if (set.add(selector.invoke(e))) {
            list.add(e);
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun <K> BooleanArray.distinctBy(selector: (Boolean) -> K): List<Boolean> {
      val set: HashSet = new HashSet();
      val list: ArrayList = new ArrayList();

      for (boolean e : $this$distinctBy) {
         if (set.add(selector.invoke(e))) {
            list.add(e);
         }
      }

      return list;
   }

   @JvmStatic
   public inline fun <K> CharArray.distinctBy(selector: (Char) -> K): List<Char> {
      val set: HashSet = new HashSet();
      val list: ArrayList = new ArrayList();

      for (char e : $this$distinctBy) {
         if (set.add(selector.invoke(e))) {
            list.add(e);
         }
      }

      return list;
   }

   @JvmStatic
   public infix fun <T> Array<out T>.intersect(other: Iterable<T>): Set<T> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$intersect`);
      CollectionsKt.retainAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun ByteArray.intersect(other: Iterable<Byte>): Set<Byte> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$intersect`);
      CollectionsKt.retainAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun ShortArray.intersect(other: Iterable<Short>): Set<Short> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$intersect`);
      CollectionsKt.retainAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun IntArray.intersect(other: Iterable<Int>): Set<Int> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$intersect`);
      CollectionsKt.retainAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun LongArray.intersect(other: Iterable<Long>): Set<Long> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$intersect`);
      CollectionsKt.retainAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun FloatArray.intersect(other: Iterable<Float>): Set<Float> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$intersect`);
      CollectionsKt.retainAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun DoubleArray.intersect(other: Iterable<Double>): Set<Double> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$intersect`);
      CollectionsKt.retainAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun BooleanArray.intersect(other: Iterable<Boolean>): Set<Boolean> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$intersect`);
      CollectionsKt.retainAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun CharArray.intersect(other: Iterable<Char>): Set<Char> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$intersect`);
      CollectionsKt.retainAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun <T> Array<out T>.subtract(other: Iterable<T>): Set<T> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$subtract`);
      CollectionsKt.removeAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun ByteArray.subtract(other: Iterable<Byte>): Set<Byte> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$subtract`);
      CollectionsKt.removeAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun ShortArray.subtract(other: Iterable<Short>): Set<Short> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$subtract`);
      CollectionsKt.removeAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun IntArray.subtract(other: Iterable<Int>): Set<Int> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$subtract`);
      CollectionsKt.removeAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun LongArray.subtract(other: Iterable<Long>): Set<Long> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$subtract`);
      CollectionsKt.removeAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun FloatArray.subtract(other: Iterable<Float>): Set<Float> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$subtract`);
      CollectionsKt.removeAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun DoubleArray.subtract(other: Iterable<Double>): Set<Double> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$subtract`);
      CollectionsKt.removeAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun BooleanArray.subtract(other: Iterable<Boolean>): Set<Boolean> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$subtract`);
      CollectionsKt.removeAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun CharArray.subtract(other: Iterable<Char>): Set<Char> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$subtract`);
      CollectionsKt.removeAll(set, other);
      return set;
   }

   @JvmStatic
   public fun <T> Array<out T>.toMutableSet(): MutableSet<T> {
      return ArraysKt.toCollection(`$this$toMutableSet`, new LinkedHashSet(MapsKt.mapCapacity(`$this$toMutableSet`.length))) as MutableSet<T>;
   }

   @JvmStatic
   public fun ByteArray.toMutableSet(): MutableSet<Byte> {
      return ArraysKt.toCollection(`$this$toMutableSet`, new LinkedHashSet<>(MapsKt.mapCapacity(`$this$toMutableSet`.length)));
   }

   @JvmStatic
   public fun ShortArray.toMutableSet(): MutableSet<Short> {
      return ArraysKt.toCollection(`$this$toMutableSet`, new LinkedHashSet<>(MapsKt.mapCapacity(`$this$toMutableSet`.length)));
   }

   @JvmStatic
   public fun IntArray.toMutableSet(): MutableSet<Int> {
      return ArraysKt.toCollection(`$this$toMutableSet`, new LinkedHashSet<>(MapsKt.mapCapacity(`$this$toMutableSet`.length)));
   }

   @JvmStatic
   public fun LongArray.toMutableSet(): MutableSet<Long> {
      return ArraysKt.toCollection(`$this$toMutableSet`, new LinkedHashSet<>(MapsKt.mapCapacity(`$this$toMutableSet`.length)));
   }

   @JvmStatic
   public fun FloatArray.toMutableSet(): MutableSet<Float> {
      return ArraysKt.toCollection(`$this$toMutableSet`, new LinkedHashSet<>(MapsKt.mapCapacity(`$this$toMutableSet`.length)));
   }

   @JvmStatic
   public fun DoubleArray.toMutableSet(): MutableSet<Double> {
      return ArraysKt.toCollection(`$this$toMutableSet`, new LinkedHashSet<>(MapsKt.mapCapacity(`$this$toMutableSet`.length)));
   }

   @JvmStatic
   public fun BooleanArray.toMutableSet(): MutableSet<Boolean> {
      return ArraysKt.toCollection(`$this$toMutableSet`, new LinkedHashSet<>(MapsKt.mapCapacity(`$this$toMutableSet`.length)));
   }

   @JvmStatic
   public fun CharArray.toMutableSet(): MutableSet<Char> {
      return ArraysKt.toCollection(`$this$toMutableSet`, new LinkedHashSet<>(MapsKt.mapCapacity(RangesKt.coerceAtMost(`$this$toMutableSet`.length, 128))));
   }

   @JvmStatic
   public infix fun <T> Array<out T>.union(other: Iterable<T>): Set<T> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$union`);
      CollectionsKt.addAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun ByteArray.union(other: Iterable<Byte>): Set<Byte> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$union`);
      CollectionsKt.addAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun ShortArray.union(other: Iterable<Short>): Set<Short> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$union`);
      CollectionsKt.addAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun IntArray.union(other: Iterable<Int>): Set<Int> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$union`);
      CollectionsKt.addAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun LongArray.union(other: Iterable<Long>): Set<Long> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$union`);
      CollectionsKt.addAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun FloatArray.union(other: Iterable<Float>): Set<Float> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$union`);
      CollectionsKt.addAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun DoubleArray.union(other: Iterable<Double>): Set<Double> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$union`);
      CollectionsKt.addAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun BooleanArray.union(other: Iterable<Boolean>): Set<Boolean> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$union`);
      CollectionsKt.addAll(set, other);
      return set;
   }

   @JvmStatic
   public infix fun CharArray.union(other: Iterable<Char>): Set<Char> {
      val set: java.util.Set = ArraysKt.toMutableSet(`$this$union`);
      CollectionsKt.addAll(set, other);
      return set;
   }

   @JvmStatic
   public inline fun <T> Array<out T>.all(predicate: (T) -> Boolean): Boolean {
      for (Object element : $this$all) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun ByteArray.all(predicate: (Byte) -> Boolean): Boolean {
      for (byte element : $this$all) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun ShortArray.all(predicate: (Short) -> Boolean): Boolean {
      for (short element : $this$all) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun IntArray.all(predicate: (Int) -> Boolean): Boolean {
      for (int element : $this$all) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun LongArray.all(predicate: (Long) -> Boolean): Boolean {
      for (long element : $this$all) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun FloatArray.all(predicate: (Float) -> Boolean): Boolean {
      for (float element : $this$all) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun DoubleArray.all(predicate: (Double) -> Boolean): Boolean {
      for (double element : $this$all) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun BooleanArray.all(predicate: (Boolean) -> Boolean): Boolean {
      for (boolean element : $this$all) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun CharArray.all(predicate: (Char) -> Boolean): Boolean {
      for (char element : $this$all) {
         if (!predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public fun <T> Array<out T>.any(): Boolean {
      return `$this$any`.length != 0;
   }

   @JvmStatic
   public fun ByteArray.any(): Boolean {
      return `$this$any`.length != 0;
   }

   @JvmStatic
   public fun ShortArray.any(): Boolean {
      return `$this$any`.length != 0;
   }

   @JvmStatic
   public fun IntArray.any(): Boolean {
      return `$this$any`.length != 0;
   }

   @JvmStatic
   public fun LongArray.any(): Boolean {
      return `$this$any`.length != 0;
   }

   @JvmStatic
   public fun FloatArray.any(): Boolean {
      return `$this$any`.length != 0;
   }

   @JvmStatic
   public fun DoubleArray.any(): Boolean {
      return `$this$any`.length != 0;
   }

   @JvmStatic
   public fun BooleanArray.any(): Boolean {
      return `$this$any`.length != 0;
   }

   @JvmStatic
   public fun CharArray.any(): Boolean {
      return `$this$any`.length != 0;
   }

   @JvmStatic
   public inline fun <T> Array<out T>.any(predicate: (T) -> Boolean): Boolean {
      for (Object element : $this$any) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @JvmStatic
   public inline fun ByteArray.any(predicate: (Byte) -> Boolean): Boolean {
      for (byte element : $this$any) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @JvmStatic
   public inline fun ShortArray.any(predicate: (Short) -> Boolean): Boolean {
      for (short element : $this$any) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @JvmStatic
   public inline fun IntArray.any(predicate: (Int) -> Boolean): Boolean {
      for (int element : $this$any) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @JvmStatic
   public inline fun LongArray.any(predicate: (Long) -> Boolean): Boolean {
      for (long element : $this$any) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @JvmStatic
   public inline fun FloatArray.any(predicate: (Float) -> Boolean): Boolean {
      for (float element : $this$any) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @JvmStatic
   public inline fun DoubleArray.any(predicate: (Double) -> Boolean): Boolean {
      for (double element : $this$any) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @JvmStatic
   public inline fun BooleanArray.any(predicate: (Boolean) -> Boolean): Boolean {
      for (boolean element : $this$any) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @JvmStatic
   public inline fun CharArray.any(predicate: (Char) -> Boolean): Boolean {
      for (char element : $this$any) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.count(): Int {
      return `$this$count`.length;
   }

   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.count(): Int {
      return `$this$count`.length;
   }

   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.count(): Int {
      return `$this$count`.length;
   }

   @InlineOnly
   @JvmStatic
   public inline fun IntArray.count(): Int {
      return `$this$count`.length;
   }

   @InlineOnly
   @JvmStatic
   public inline fun LongArray.count(): Int {
      return `$this$count`.length;
   }

   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.count(): Int {
      return `$this$count`.length;
   }

   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.count(): Int {
      return `$this$count`.length;
   }

   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.count(): Int {
      return `$this$count`.length;
   }

   @InlineOnly
   @JvmStatic
   public inline fun CharArray.count(): Int {
      return `$this$count`.length;
   }

   @JvmStatic
   public inline fun <T> Array<out T>.count(predicate: (T) -> Boolean): Int {
      var count: Int = 0;

      for (Object element : $this$count) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            count++;
         }
      }

      return count;
   }

   @JvmStatic
   public inline fun ByteArray.count(predicate: (Byte) -> Boolean): Int {
      var count: Int = 0;

      for (byte element : $this$count) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            count++;
         }
      }

      return count;
   }

   @JvmStatic
   public inline fun ShortArray.count(predicate: (Short) -> Boolean): Int {
      var count: Int = 0;

      for (short element : $this$count) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            count++;
         }
      }

      return count;
   }

   @JvmStatic
   public inline fun IntArray.count(predicate: (Int) -> Boolean): Int {
      var count: Int = 0;

      for (int element : $this$count) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            count++;
         }
      }

      return count;
   }

   @JvmStatic
   public inline fun LongArray.count(predicate: (Long) -> Boolean): Int {
      var count: Int = 0;

      for (long element : $this$count) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            count++;
         }
      }

      return count;
   }

   @JvmStatic
   public inline fun FloatArray.count(predicate: (Float) -> Boolean): Int {
      var count: Int = 0;

      for (float element : $this$count) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            count++;
         }
      }

      return count;
   }

   @JvmStatic
   public inline fun DoubleArray.count(predicate: (Double) -> Boolean): Int {
      var count: Int = 0;

      for (double element : $this$count) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            count++;
         }
      }

      return count;
   }

   @JvmStatic
   public inline fun BooleanArray.count(predicate: (Boolean) -> Boolean): Int {
      var count: Int = 0;

      for (boolean element : $this$count) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            count++;
         }
      }

      return count;
   }

   @JvmStatic
   public inline fun CharArray.count(predicate: (Char) -> Boolean): Int {
      var count: Int = 0;

      for (char element : $this$count) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            count++;
         }
      }

      return count;
   }

   @JvmStatic
   public inline fun <T, R> Array<out T>.fold(initial: R, operation: (R, T) -> R): R {
      var accumulator: Any = initial;

      for (Object element : $this$fold) {
         accumulator = operation.invoke(accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> ByteArray.fold(initial: R, operation: (R, Byte) -> R): R {
      var accumulator: Any = initial;

      for (byte element : $this$fold) {
         accumulator = operation.invoke(accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> ShortArray.fold(initial: R, operation: (R, Short) -> R): R {
      var accumulator: Any = initial;

      for (short element : $this$fold) {
         accumulator = operation.invoke(accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> IntArray.fold(initial: R, operation: (R, Int) -> R): R {
      var accumulator: Any = initial;

      for (int element : $this$fold) {
         accumulator = operation.invoke(accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> LongArray.fold(initial: R, operation: (R, Long) -> R): R {
      var accumulator: Any = initial;

      for (long element : $this$fold) {
         accumulator = operation.invoke(accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> FloatArray.fold(initial: R, operation: (R, Float) -> R): R {
      var accumulator: Any = initial;

      for (float element : $this$fold) {
         accumulator = operation.invoke(accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> DoubleArray.fold(initial: R, operation: (R, Double) -> R): R {
      var accumulator: Any = initial;

      for (double element : $this$fold) {
         accumulator = operation.invoke(accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> BooleanArray.fold(initial: R, operation: (R, Boolean) -> R): R {
      var accumulator: Any = initial;

      for (boolean element : $this$fold) {
         accumulator = operation.invoke(accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> CharArray.fold(initial: R, operation: (R, Char) -> R): R {
      var accumulator: Any = initial;

      for (char element : $this$fold) {
         accumulator = operation.invoke(accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <T, R> Array<out T>.foldIndexed(initial: R, operation: (Int, R, T) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;

      for (Object element : $this$foldIndexed) {
         accumulator = operation.invoke(index++, accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> ByteArray.foldIndexed(initial: R, operation: (Int, R, Byte) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;

      for (byte element : $this$foldIndexed) {
         accumulator = operation.invoke(index++, accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> ShortArray.foldIndexed(initial: R, operation: (Int, R, Short) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;

      for (short element : $this$foldIndexed) {
         accumulator = operation.invoke(index++, accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> IntArray.foldIndexed(initial: R, operation: (Int, R, Int) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;

      for (int element : $this$foldIndexed) {
         accumulator = operation.invoke(index++, accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> LongArray.foldIndexed(initial: R, operation: (Int, R, Long) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;

      for (long element : $this$foldIndexed) {
         accumulator = operation.invoke(index++, accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> FloatArray.foldIndexed(initial: R, operation: (Int, R, Float) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;

      for (float element : $this$foldIndexed) {
         accumulator = operation.invoke(index++, accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> DoubleArray.foldIndexed(initial: R, operation: (Int, R, Double) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;

      for (double element : $this$foldIndexed) {
         accumulator = operation.invoke(index++, accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> BooleanArray.foldIndexed(initial: R, operation: (Int, R, Boolean) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;

      for (boolean element : $this$foldIndexed) {
         accumulator = operation.invoke(index++, accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> CharArray.foldIndexed(initial: R, operation: (Int, R, Char) -> R): R {
      var index: Int = 0;
      var accumulator: Any = initial;

      for (char element : $this$foldIndexed) {
         accumulator = operation.invoke(index++, accumulator, element);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <T, R> Array<out T>.foldRight(initial: R, operation: (T, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRight`);
      var accumulator: Any = initial;

      while (index >= 0) {
         accumulator = operation.invoke(`$this$foldRight`[index--], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> ByteArray.foldRight(initial: R, operation: (Byte, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRight`);
      var accumulator: Any = initial;

      while (index >= 0) {
         accumulator = operation.invoke(`$this$foldRight`[index--], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> ShortArray.foldRight(initial: R, operation: (Short, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRight`);
      var accumulator: Any = initial;

      while (index >= 0) {
         accumulator = operation.invoke(`$this$foldRight`[index--], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> IntArray.foldRight(initial: R, operation: (Int, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRight`);
      var accumulator: Any = initial;

      while (index >= 0) {
         accumulator = operation.invoke(`$this$foldRight`[index--], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> LongArray.foldRight(initial: R, operation: (Long, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRight`);
      var accumulator: Any = initial;

      while (index >= 0) {
         accumulator = operation.invoke(`$this$foldRight`[index--], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> FloatArray.foldRight(initial: R, operation: (Float, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRight`);
      var accumulator: Any = initial;

      while (index >= 0) {
         accumulator = operation.invoke(`$this$foldRight`[index--], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> DoubleArray.foldRight(initial: R, operation: (Double, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRight`);
      var accumulator: Any = initial;

      while (index >= 0) {
         accumulator = operation.invoke(`$this$foldRight`[index--], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> BooleanArray.foldRight(initial: R, operation: (Boolean, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRight`);
      var accumulator: Any = initial;

      while (index >= 0) {
         accumulator = operation.invoke(`$this$foldRight`[index--], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> CharArray.foldRight(initial: R, operation: (Char, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRight`);
      var accumulator: Any = initial;

      while (index >= 0) {
         accumulator = operation.invoke(`$this$foldRight`[index--], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <T, R> Array<out T>.foldRightIndexed(initial: R, operation: (Int, T, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRightIndexed`);

      var accumulator: Any;
      for (accumulator = initial; index >= 0; index--) {
         accumulator = operation.invoke(index, `$this$foldRightIndexed`[index], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> ByteArray.foldRightIndexed(initial: R, operation: (Int, Byte, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRightIndexed`);

      var accumulator: Any;
      for (accumulator = initial; index >= 0; index--) {
         accumulator = operation.invoke(index, `$this$foldRightIndexed`[index], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> ShortArray.foldRightIndexed(initial: R, operation: (Int, Short, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRightIndexed`);

      var accumulator: Any;
      for (accumulator = initial; index >= 0; index--) {
         accumulator = operation.invoke(index, `$this$foldRightIndexed`[index], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> IntArray.foldRightIndexed(initial: R, operation: (Int, Int, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRightIndexed`);

      var accumulator: Any;
      for (accumulator = initial; index >= 0; index--) {
         accumulator = operation.invoke(index, `$this$foldRightIndexed`[index], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> LongArray.foldRightIndexed(initial: R, operation: (Int, Long, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRightIndexed`);

      var accumulator: Any;
      for (accumulator = initial; index >= 0; index--) {
         accumulator = operation.invoke(index, `$this$foldRightIndexed`[index], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> FloatArray.foldRightIndexed(initial: R, operation: (Int, Float, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRightIndexed`);

      var accumulator: Any;
      for (accumulator = initial; index >= 0; index--) {
         accumulator = operation.invoke(index, `$this$foldRightIndexed`[index], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> DoubleArray.foldRightIndexed(initial: R, operation: (Int, Double, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRightIndexed`);

      var accumulator: Any;
      for (accumulator = initial; index >= 0; index--) {
         accumulator = operation.invoke(index, `$this$foldRightIndexed`[index], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> BooleanArray.foldRightIndexed(initial: R, operation: (Int, Boolean, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRightIndexed`);

      var accumulator: Any;
      for (accumulator = initial; index >= 0; index--) {
         accumulator = operation.invoke(index, `$this$foldRightIndexed`[index], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <R> CharArray.foldRightIndexed(initial: R, operation: (Int, Char, R) -> R): R {
      var index: Int = ArraysKt.getLastIndex(`$this$foldRightIndexed`);

      var accumulator: Any;
      for (accumulator = initial; index >= 0; index--) {
         accumulator = operation.invoke(index, `$this$foldRightIndexed`[index], accumulator);
      }

      return (R)accumulator;
   }

   @JvmStatic
   public inline fun <T> Array<out T>.forEach(action: (T) -> Unit) {
      for (Object element : $this$forEach) {
         action.invoke(element);
      }
   }

   @JvmStatic
   public inline fun ByteArray.forEach(action: (Byte) -> Unit) {
      for (byte element : $this$forEach) {
         action.invoke(element);
      }
   }

   @JvmStatic
   public inline fun ShortArray.forEach(action: (Short) -> Unit) {
      for (short element : $this$forEach) {
         action.invoke(element);
      }
   }

   @JvmStatic
   public inline fun IntArray.forEach(action: (Int) -> Unit) {
      for (int element : $this$forEach) {
         action.invoke(element);
      }
   }

   @JvmStatic
   public inline fun LongArray.forEach(action: (Long) -> Unit) {
      for (long element : $this$forEach) {
         action.invoke(element);
      }
   }

   @JvmStatic
   public inline fun FloatArray.forEach(action: (Float) -> Unit) {
      for (float element : $this$forEach) {
         action.invoke(element);
      }
   }

   @JvmStatic
   public inline fun DoubleArray.forEach(action: (Double) -> Unit) {
      for (double element : $this$forEach) {
         action.invoke(element);
      }
   }

   @JvmStatic
   public inline fun BooleanArray.forEach(action: (Boolean) -> Unit) {
      for (boolean element : $this$forEach) {
         action.invoke(element);
      }
   }

   @JvmStatic
   public inline fun CharArray.forEach(action: (Char) -> Unit) {
      for (char element : $this$forEach) {
         action.invoke(element);
      }
   }

   @JvmStatic
   public inline fun <T> Array<out T>.forEachIndexed(action: (Int, T) -> Unit) {
      var index: Int = 0;

      for (Object item : $this$forEachIndexed) {
         action.invoke(index++, item);
      }
   }

   @JvmStatic
   public inline fun ByteArray.forEachIndexed(action: (Int, Byte) -> Unit) {
      var index: Int = 0;

      for (byte item : $this$forEachIndexed) {
         action.invoke(index++, item);
      }
   }

   @JvmStatic
   public inline fun ShortArray.forEachIndexed(action: (Int, Short) -> Unit) {
      var index: Int = 0;

      for (short item : $this$forEachIndexed) {
         action.invoke(index++, item);
      }
   }

   @JvmStatic
   public inline fun IntArray.forEachIndexed(action: (Int, Int) -> Unit) {
      var index: Int = 0;

      for (int item : $this$forEachIndexed) {
         action.invoke(index++, item);
      }
   }

   @JvmStatic
   public inline fun LongArray.forEachIndexed(action: (Int, Long) -> Unit) {
      var index: Int = 0;

      for (long item : $this$forEachIndexed) {
         action.invoke(index++, item);
      }
   }

   @JvmStatic
   public inline fun FloatArray.forEachIndexed(action: (Int, Float) -> Unit) {
      var index: Int = 0;

      for (float item : $this$forEachIndexed) {
         action.invoke(index++, item);
      }
   }

   @JvmStatic
   public inline fun DoubleArray.forEachIndexed(action: (Int, Double) -> Unit) {
      var index: Int = 0;

      for (double item : $this$forEachIndexed) {
         action.invoke(index++, item);
      }
   }

   @JvmStatic
   public inline fun BooleanArray.forEachIndexed(action: (Int, Boolean) -> Unit) {
      var index: Int = 0;

      for (boolean item : $this$forEachIndexed) {
         action.invoke(index++, item);
      }
   }

   @JvmStatic
   public inline fun CharArray.forEachIndexed(action: (Int, Char) -> Unit) {
      var index: Int = 0;

      for (char item : $this$forEachIndexed) {
         action.invoke(index++, item);
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxOrThrow")
   @JvmStatic
   public fun Array<out Double>.max(): Double {
      if (`$this$max`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Double = `$this$max`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$max`);
         if (1 <= var4) {
            while (true) {
               max = Math.max(max, `$this$max`[i]);
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
   @JvmName(name = "maxOrThrow")
   @JvmStatic
   public fun Array<out Float>.max(): Float {
      if (`$this$max`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Float = `$this$max`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$max`);
         if (1 <= var3) {
            while (true) {
               max = Math.max(max, `$this$max`[i]);
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
   @JvmName(name = "maxOrThrow")
   @JvmStatic
   public fun <T : Comparable<T>> Array<out T>.max(): T {
      if (`$this$max`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: java.lang.Comparable = `$this$max`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$max`);
         if (1 <= var3) {
            while (true) {
               val e: java.lang.Comparable = `$this$max`[i];
               if (max.compareTo(`$this$max`[i]) < 0) {
                  max = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return (T)max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxOrThrow")
   @JvmStatic
   public fun ByteArray.max(): Byte {
      if (`$this$max`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Byte = `$this$max`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$max`);
         if (1 <= var3) {
            while (true) {
               val e: Byte = `$this$max`[i];
               if (max < `$this$max`[i]) {
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
   @JvmName(name = "maxOrThrow")
   @JvmStatic
   public fun ShortArray.max(): Short {
      if (`$this$max`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Short = `$this$max`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$max`);
         if (1 <= var3) {
            while (true) {
               val e: Short = `$this$max`[i];
               if (max < `$this$max`[i]) {
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
   @JvmName(name = "maxOrThrow")
   @JvmStatic
   public fun IntArray.max(): Int {
      if (`$this$max`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Int = `$this$max`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$max`);
         if (1 <= var3) {
            while (true) {
               val e: Int = `$this$max`[i];
               if (max < `$this$max`[i]) {
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
   @JvmName(name = "maxOrThrow")
   @JvmStatic
   public fun LongArray.max(): Long {
      if (`$this$max`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Long = `$this$max`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$max`);
         if (1 <= var4) {
            while (true) {
               val e: Long = `$this$max`[i];
               if (max < `$this$max`[i]) {
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
   @JvmName(name = "maxOrThrow")
   @JvmStatic
   public fun FloatArray.max(): Float {
      if (`$this$max`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Float = `$this$max`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$max`);
         if (1 <= var3) {
            while (true) {
               max = Math.max(max, `$this$max`[i]);
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
   @JvmName(name = "maxOrThrow")
   @JvmStatic
   public fun DoubleArray.max(): Double {
      if (`$this$max`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Double = `$this$max`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$max`);
         if (1 <= var4) {
            while (true) {
               max = Math.max(max, `$this$max`[i]);
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
   @JvmName(name = "maxOrThrow")
   @JvmStatic
   public fun CharArray.max(): Char {
      if (`$this$max`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Char = `$this$max`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$max`);
         if (1 <= var3) {
            while (true) {
               val e: Char = `$this$max`[i];
               if (Intrinsics.compare(max, `$this$max`[i]) < 0) {
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
   @JvmName(name = "maxByOrThrow")
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Array<out T>.maxBy(selector: (T) -> R): T {
      if (`$this$maxBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Any = `$this$maxBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxBy`);
         if (lastIndex == 0) {
            return (T)maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Any = `$this$maxBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxBy`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return (T)maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> ByteArray.maxBy(selector: (Byte) -> R): Byte {
      if (`$this$maxBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Byte = `$this$maxBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxBy`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Byte = `$this$maxBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxBy`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> ShortArray.maxBy(selector: (Short) -> R): Short {
      if (`$this$maxBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Short = `$this$maxBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxBy`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Short = `$this$maxBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxBy`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> IntArray.maxBy(selector: (Int) -> R): Int {
      if (`$this$maxBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Int = `$this$maxBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxBy`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Int = `$this$maxBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxBy`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> LongArray.maxBy(selector: (Long) -> R): Long {
      if (`$this$maxBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Long = `$this$maxBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxBy`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Long = `$this$maxBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxBy`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> FloatArray.maxBy(selector: (Float) -> R): Float {
      if (`$this$maxBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Float = `$this$maxBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxBy`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Float = `$this$maxBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxBy`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> DoubleArray.maxBy(selector: (Double) -> R): Double {
      if (`$this$maxBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Double = `$this$maxBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxBy`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Double = `$this$maxBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxBy`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> BooleanArray.maxBy(selector: (Boolean) -> R): Boolean {
      if (`$this$maxBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Boolean = `$this$maxBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxBy`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Boolean = `$this$maxBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxBy`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> CharArray.maxBy(selector: (Char) -> R): Char {
      if (`$this$maxBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxElem: Char = `$this$maxBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxBy`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Char = `$this$maxBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxBy`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Array<out T>.maxByOrNull(selector: (T) -> R): T? {
      if (`$this$maxByOrNull`.length == 0) {
         return null;
      } else {
         var maxElem: Any = `$this$maxByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxByOrNull`);
         if (lastIndex == 0) {
            return (T)maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Any = `$this$maxByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxByOrNull`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return (T)maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> ByteArray.maxByOrNull(selector: (Byte) -> R): Byte? {
      if (`$this$maxByOrNull`.length == 0) {
         return null;
      } else {
         var maxElem: Byte = `$this$maxByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxByOrNull`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Byte = `$this$maxByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxByOrNull`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> ShortArray.maxByOrNull(selector: (Short) -> R): Short? {
      if (`$this$maxByOrNull`.length == 0) {
         return null;
      } else {
         var maxElem: Short = `$this$maxByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxByOrNull`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Short = `$this$maxByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxByOrNull`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> IntArray.maxByOrNull(selector: (Int) -> R): Int? {
      if (`$this$maxByOrNull`.length == 0) {
         return null;
      } else {
         var maxElem: Int = `$this$maxByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxByOrNull`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Int = `$this$maxByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxByOrNull`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> LongArray.maxByOrNull(selector: (Long) -> R): Long? {
      if (`$this$maxByOrNull`.length == 0) {
         return null;
      } else {
         var maxElem: Long = `$this$maxByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxByOrNull`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Long = `$this$maxByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxByOrNull`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> FloatArray.maxByOrNull(selector: (Float) -> R): Float? {
      if (`$this$maxByOrNull`.length == 0) {
         return null;
      } else {
         var maxElem: Float = `$this$maxByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxByOrNull`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Float = `$this$maxByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxByOrNull`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> DoubleArray.maxByOrNull(selector: (Double) -> R): Double? {
      if (`$this$maxByOrNull`.length == 0) {
         return null;
      } else {
         var maxElem: Double = `$this$maxByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxByOrNull`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Double = `$this$maxByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxByOrNull`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> BooleanArray.maxByOrNull(selector: (Boolean) -> R): Boolean? {
      if (`$this$maxByOrNull`.length == 0) {
         return null;
      } else {
         var maxElem: Boolean = `$this$maxByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxByOrNull`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Boolean = `$this$maxByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxByOrNull`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> CharArray.maxByOrNull(selector: (Char) -> R): Char? {
      if (`$this$maxByOrNull`.length == 0) {
         return null;
      } else {
         var maxElem: Char = `$this$maxByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$maxByOrNull`);
         if (lastIndex == 0) {
            return maxElem;
         } else {
            var maxValue: java.lang.Comparable = selector.invoke(maxElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Char = `$this$maxByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$maxByOrNull`[i]) as java.lang.Comparable;
                  if (maxValue.compareTo(v) < 0) {
                     maxElem = e;
                     maxValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return maxElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.maxOf(selector: (T) -> Double): Double {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.maxOf(selector: (Byte) -> Double): Double {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.maxOf(selector: (Short) -> Double): Double {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.maxOf(selector: (Int) -> Double): Double {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.maxOf(selector: (Long) -> Double): Double {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.maxOf(selector: (Float) -> Double): Double {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.maxOf(selector: (Double) -> Double): Double {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.maxOf(selector: (Boolean) -> Double): Double {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.maxOf(selector: (Char) -> Double): Double {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.maxOf(selector: (T) -> Float): Float {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.maxOf(selector: (Byte) -> Float): Float {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.maxOf(selector: (Short) -> Float): Float {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.maxOf(selector: (Int) -> Float): Float {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.maxOf(selector: (Long) -> Float): Float {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.maxOf(selector: (Float) -> Float): Float {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.maxOf(selector: (Double) -> Float): Float {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.maxOf(selector: (Boolean) -> Float): Float {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.maxOf(selector: (Char) -> Float): Float {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Array<out T>.maxOf(selector: (T) -> R): R {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ByteArray.maxOf(selector: (Byte) -> R): R {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ShortArray.maxOf(selector: (Short) -> R): R {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> IntArray.maxOf(selector: (Int) -> R): R {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> LongArray.maxOf(selector: (Long) -> R): R {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> FloatArray.maxOf(selector: (Float) -> R): R {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> DoubleArray.maxOf(selector: (Double) -> R): R {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> BooleanArray.maxOf(selector: (Boolean) -> R): R {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> CharArray.maxOf(selector: (Char) -> R): R {
      if (`$this$maxOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.maxOfOrNull(selector: (T) -> Double): Double? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.maxOfOrNull(selector: (Byte) -> Double): Double? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.maxOfOrNull(selector: (Short) -> Double): Double? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.maxOfOrNull(selector: (Int) -> Double): Double? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.maxOfOrNull(selector: (Long) -> Double): Double? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.maxOfOrNull(selector: (Float) -> Double): Double? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.maxOfOrNull(selector: (Double) -> Double): Double? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.maxOfOrNull(selector: (Boolean) -> Double): Double? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.maxOfOrNull(selector: (Char) -> Double): Double? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Double = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var5) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.maxOfOrNull(selector: (T) -> Float): Float? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.maxOfOrNull(selector: (Byte) -> Float): Float? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.maxOfOrNull(selector: (Short) -> Float): Float? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.maxOfOrNull(selector: (Int) -> Float): Float? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.maxOfOrNull(selector: (Long) -> Float): Float? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.maxOfOrNull(selector: (Float) -> Float): Float? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.maxOfOrNull(selector: (Double) -> Float): Float? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.maxOfOrNull(selector: (Boolean) -> Float): Float? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.maxOfOrNull(selector: (Char) -> Float): Float? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Float = (selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               maxValue = Math.max(maxValue, (selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Array<out T>.maxOfOrNull(selector: (T) -> R): R? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ByteArray.maxOfOrNull(selector: (Byte) -> R): R? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ShortArray.maxOfOrNull(selector: (Short) -> R): R? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> IntArray.maxOfOrNull(selector: (Int) -> R): R? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> LongArray.maxOfOrNull(selector: (Long) -> R): R? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> FloatArray.maxOfOrNull(selector: (Float) -> R): R? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> DoubleArray.maxOfOrNull(selector: (Double) -> R): R? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> BooleanArray.maxOfOrNull(selector: (Boolean) -> R): R? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> CharArray.maxOfOrNull(selector: (Char) -> R): R? {
      if (`$this$maxOfOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$maxOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <T, R> Array<out T>.maxOfWith(comparator: Comparator<in R>, selector: (T) -> R): R {
      if (`$this$maxOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> ByteArray.maxOfWith(comparator: Comparator<in R>, selector: (Byte) -> R): R {
      if (`$this$maxOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> ShortArray.maxOfWith(comparator: Comparator<in R>, selector: (Short) -> R): R {
      if (`$this$maxOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> IntArray.maxOfWith(comparator: Comparator<in R>, selector: (Int) -> R): R {
      if (`$this$maxOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> LongArray.maxOfWith(comparator: Comparator<in R>, selector: (Long) -> R): R {
      if (`$this$maxOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> FloatArray.maxOfWith(comparator: Comparator<in R>, selector: (Float) -> R): R {
      if (`$this$maxOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> DoubleArray.maxOfWith(comparator: Comparator<in R>, selector: (Double) -> R): R {
      if (`$this$maxOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> BooleanArray.maxOfWith(comparator: Comparator<in R>, selector: (Boolean) -> R): R {
      if (`$this$maxOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> CharArray.maxOfWith(comparator: Comparator<in R>, selector: (Char) -> R): R {
      if (`$this$maxOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <T, R> Array<out T>.maxOfWithOrNull(comparator: Comparator<in R>, selector: (T) -> R): R? {
      if (`$this$maxOfWithOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> ByteArray.maxOfWithOrNull(comparator: Comparator<in R>, selector: (Byte) -> R): R? {
      if (`$this$maxOfWithOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> ShortArray.maxOfWithOrNull(comparator: Comparator<in R>, selector: (Short) -> R): R? {
      if (`$this$maxOfWithOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> IntArray.maxOfWithOrNull(comparator: Comparator<in R>, selector: (Int) -> R): R? {
      if (`$this$maxOfWithOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> LongArray.maxOfWithOrNull(comparator: Comparator<in R>, selector: (Long) -> R): R? {
      if (`$this$maxOfWithOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> FloatArray.maxOfWithOrNull(comparator: Comparator<in R>, selector: (Float) -> R): R? {
      if (`$this$maxOfWithOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> DoubleArray.maxOfWithOrNull(comparator: Comparator<in R>, selector: (Double) -> R): R? {
      if (`$this$maxOfWithOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> BooleanArray.maxOfWithOrNull(comparator: Comparator<in R>, selector: (Boolean) -> R): R? {
      if (`$this$maxOfWithOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> CharArray.maxOfWithOrNull(comparator: Comparator<in R>, selector: (Char) -> R): R? {
      if (`$this$maxOfWithOrNull`.length == 0) {
         return null;
      } else {
         var maxValue: Any = selector.invoke(`$this$maxOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$maxOfWithOrNull`[i]);
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
   @JvmStatic
   public fun Array<out Double>.maxOrNull(): Double? {
      if (`$this$maxOrNull`.length == 0) {
         return null;
      } else {
         var max: Double = `$this$maxOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOrNull`);
         if (1 <= var4) {
            while (true) {
               max = Math.max(max, `$this$maxOrNull`[i]);
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
   @JvmStatic
   public fun Array<out Float>.maxOrNull(): Float? {
      if (`$this$maxOrNull`.length == 0) {
         return null;
      } else {
         var max: Float = `$this$maxOrNull`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$maxOrNull`);
         if (1 <= var3) {
            while (true) {
               max = Math.max(max, `$this$maxOrNull`[i]);
               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T : Comparable<T>> Array<out T>.maxOrNull(): T? {
      if (`$this$maxOrNull`.length == 0) {
         return null;
      } else {
         var max: java.lang.Comparable = `$this$maxOrNull`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$maxOrNull`);
         if (1 <= var3) {
            while (true) {
               val e: java.lang.Comparable = `$this$maxOrNull`[i];
               if (max.compareTo(`$this$maxOrNull`[i]) < 0) {
                  max = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return (T)max;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ByteArray.maxOrNull(): Byte? {
      if (`$this$maxOrNull`.length == 0) {
         return null;
      } else {
         var max: Byte = `$this$maxOrNull`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$maxOrNull`);
         if (1 <= var3) {
            while (true) {
               val e: Byte = `$this$maxOrNull`[i];
               if (max < `$this$maxOrNull`[i]) {
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

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ShortArray.maxOrNull(): Short? {
      if (`$this$maxOrNull`.length == 0) {
         return null;
      } else {
         var max: Short = `$this$maxOrNull`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$maxOrNull`);
         if (1 <= var3) {
            while (true) {
               val e: Short = `$this$maxOrNull`[i];
               if (max < `$this$maxOrNull`[i]) {
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

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun IntArray.maxOrNull(): Int? {
      if (`$this$maxOrNull`.length == 0) {
         return null;
      } else {
         var max: Int = `$this$maxOrNull`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$maxOrNull`);
         if (1 <= var3) {
            while (true) {
               val e: Int = `$this$maxOrNull`[i];
               if (max < `$this$maxOrNull`[i]) {
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

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun LongArray.maxOrNull(): Long? {
      if (`$this$maxOrNull`.length == 0) {
         return null;
      } else {
         var max: Long = `$this$maxOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Long = `$this$maxOrNull`[i];
               if (max < `$this$maxOrNull`[i]) {
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
   @JvmStatic
   public fun FloatArray.maxOrNull(): Float? {
      if (`$this$maxOrNull`.length == 0) {
         return null;
      } else {
         var max: Float = `$this$maxOrNull`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$maxOrNull`);
         if (1 <= var3) {
            while (true) {
               max = Math.max(max, `$this$maxOrNull`[i]);
               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return max;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun DoubleArray.maxOrNull(): Double? {
      if (`$this$maxOrNull`.length == 0) {
         return null;
      } else {
         var max: Double = `$this$maxOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxOrNull`);
         if (1 <= var4) {
            while (true) {
               max = Math.max(max, `$this$maxOrNull`[i]);
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
   @JvmStatic
   public fun CharArray.maxOrNull(): Char? {
      if (`$this$maxOrNull`.length == 0) {
         return null;
      } else {
         var max: Char = `$this$maxOrNull`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$maxOrNull`);
         if (1 <= var3) {
            while (true) {
               val e: Char = `$this$maxOrNull`[i];
               if (Intrinsics.compare(max, `$this$maxOrNull`[i]) < 0) {
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
   @JvmName(name = "maxWithOrThrow")
   @JvmStatic
   public fun <T> Array<out T>.maxWith(comparator: Comparator<in T>): T {
      if (`$this$maxWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Any = `$this$maxWith`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxWith`);
         if (1 <= var4) {
            while (true) {
               val e: Any = `$this$maxWith`[i];
               if (comparator.compare(max, `$this$maxWith`[i]) < 0) {
                  max = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (T)max;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "maxWithOrThrow")
   @JvmStatic
   public fun ByteArray.maxWith(comparator: Comparator<in Byte>): Byte {
      if (`$this$maxWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Byte = `$this$maxWith`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxWith`);
         if (1 <= var4) {
            while (true) {
               val e: Byte = `$this$maxWith`[i];
               if (comparator.compare(max, `$this$maxWith`[i]) < 0) {
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
   @JvmName(name = "maxWithOrThrow")
   @JvmStatic
   public fun ShortArray.maxWith(comparator: Comparator<in Short>): Short {
      if (`$this$maxWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Short = `$this$maxWith`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxWith`);
         if (1 <= var4) {
            while (true) {
               val e: Short = `$this$maxWith`[i];
               if (comparator.compare(max, `$this$maxWith`[i]) < 0) {
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
   @JvmName(name = "maxWithOrThrow")
   @JvmStatic
   public fun IntArray.maxWith(comparator: Comparator<in Int>): Int {
      if (`$this$maxWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Int = `$this$maxWith`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxWith`);
         if (1 <= var4) {
            while (true) {
               val e: Int = `$this$maxWith`[i];
               if (comparator.compare(max, `$this$maxWith`[i]) < 0) {
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
   @JvmName(name = "maxWithOrThrow")
   @JvmStatic
   public fun LongArray.maxWith(comparator: Comparator<in Long>): Long {
      if (`$this$maxWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Long = `$this$maxWith`[0];
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxWith`);
         if (1 <= var5) {
            while (true) {
               val e: Long = `$this$maxWith`[i];
               if (comparator.compare(max, `$this$maxWith`[i]) < 0) {
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
   @JvmName(name = "maxWithOrThrow")
   @JvmStatic
   public fun FloatArray.maxWith(comparator: Comparator<in Float>): Float {
      if (`$this$maxWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Float = `$this$maxWith`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxWith`);
         if (1 <= var4) {
            while (true) {
               val e: Float = `$this$maxWith`[i];
               if (comparator.compare(max, `$this$maxWith`[i]) < 0) {
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
   @JvmName(name = "maxWithOrThrow")
   @JvmStatic
   public fun DoubleArray.maxWith(comparator: Comparator<in Double>): Double {
      if (`$this$maxWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Double = `$this$maxWith`[0];
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxWith`);
         if (1 <= var5) {
            while (true) {
               val e: Double = `$this$maxWith`[i];
               if (comparator.compare(max, `$this$maxWith`[i]) < 0) {
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
   @JvmName(name = "maxWithOrThrow")
   @JvmStatic
   public fun BooleanArray.maxWith(comparator: Comparator<in Boolean>): Boolean {
      if (`$this$maxWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Boolean = `$this$maxWith`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxWith`);
         if (1 <= var4) {
            while (true) {
               val e: Boolean = `$this$maxWith`[i];
               if (comparator.compare(max, `$this$maxWith`[i]) < 0) {
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
   @JvmName(name = "maxWithOrThrow")
   @JvmStatic
   public fun CharArray.maxWith(comparator: Comparator<in Char>): Char {
      if (`$this$maxWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var max: Char = `$this$maxWith`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxWith`);
         if (1 <= var4) {
            while (true) {
               val e: Char = `$this$maxWith`[i];
               if (comparator.compare(max, `$this$maxWith`[i]) < 0) {
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
   @JvmStatic
   public fun <T> Array<out T>.maxWithOrNull(comparator: Comparator<in T>): T? {
      if (`$this$maxWithOrNull`.length == 0) {
         return null;
      } else {
         var max: Any = `$this$maxWithOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Any = `$this$maxWithOrNull`[i];
               if (comparator.compare(max, `$this$maxWithOrNull`[i]) < 0) {
                  max = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (T)max;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ByteArray.maxWithOrNull(comparator: Comparator<in Byte>): Byte? {
      if (`$this$maxWithOrNull`.length == 0) {
         return null;
      } else {
         var max: Byte = `$this$maxWithOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Byte = `$this$maxWithOrNull`[i];
               if (comparator.compare(max, `$this$maxWithOrNull`[i]) < 0) {
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
   @JvmStatic
   public fun ShortArray.maxWithOrNull(comparator: Comparator<in Short>): Short? {
      if (`$this$maxWithOrNull`.length == 0) {
         return null;
      } else {
         var max: Short = `$this$maxWithOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Short = `$this$maxWithOrNull`[i];
               if (comparator.compare(max, `$this$maxWithOrNull`[i]) < 0) {
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
   @JvmStatic
   public fun IntArray.maxWithOrNull(comparator: Comparator<in Int>): Int? {
      if (`$this$maxWithOrNull`.length == 0) {
         return null;
      } else {
         var max: Int = `$this$maxWithOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Int = `$this$maxWithOrNull`[i];
               if (comparator.compare(max, `$this$maxWithOrNull`[i]) < 0) {
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
   @JvmStatic
   public fun LongArray.maxWithOrNull(comparator: Comparator<in Long>): Long? {
      if (`$this$maxWithOrNull`.length == 0) {
         return null;
      } else {
         var max: Long = `$this$maxWithOrNull`[0];
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val e: Long = `$this$maxWithOrNull`[i];
               if (comparator.compare(max, `$this$maxWithOrNull`[i]) < 0) {
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

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun FloatArray.maxWithOrNull(comparator: Comparator<in Float>): Float? {
      if (`$this$maxWithOrNull`.length == 0) {
         return null;
      } else {
         var max: Float = `$this$maxWithOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Float = `$this$maxWithOrNull`[i];
               if (comparator.compare(max, `$this$maxWithOrNull`[i]) < 0) {
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
   @JvmStatic
   public fun DoubleArray.maxWithOrNull(comparator: Comparator<in Double>): Double? {
      if (`$this$maxWithOrNull`.length == 0) {
         return null;
      } else {
         var max: Double = `$this$maxWithOrNull`[0];
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$maxWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val e: Double = `$this$maxWithOrNull`[i];
               if (comparator.compare(max, `$this$maxWithOrNull`[i]) < 0) {
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

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun BooleanArray.maxWithOrNull(comparator: Comparator<in Boolean>): Boolean? {
      if (`$this$maxWithOrNull`.length == 0) {
         return null;
      } else {
         var max: Boolean = `$this$maxWithOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Boolean = `$this$maxWithOrNull`[i];
               if (comparator.compare(max, `$this$maxWithOrNull`[i]) < 0) {
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
   @JvmStatic
   public fun CharArray.maxWithOrNull(comparator: Comparator<in Char>): Char? {
      if (`$this$maxWithOrNull`.length == 0) {
         return null;
      } else {
         var max: Char = `$this$maxWithOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$maxWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Char = `$this$maxWithOrNull`[i];
               if (comparator.compare(max, `$this$maxWithOrNull`[i]) < 0) {
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
   @JvmName(name = "minOrThrow")
   @JvmStatic
   public fun Array<out Double>.min(): Double {
      if (`$this$min`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Double = `$this$min`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$min`);
         if (1 <= var4) {
            while (true) {
               min = Math.min(min, `$this$min`[i]);
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
   @JvmName(name = "minOrThrow")
   @JvmStatic
   public fun Array<out Float>.min(): Float {
      if (`$this$min`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Float = `$this$min`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$min`);
         if (1 <= var3) {
            while (true) {
               min = Math.min(min, `$this$min`[i]);
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
   @JvmName(name = "minOrThrow")
   @JvmStatic
   public fun <T : Comparable<T>> Array<out T>.min(): T {
      if (`$this$min`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: java.lang.Comparable = `$this$min`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$min`);
         if (1 <= var3) {
            while (true) {
               val e: java.lang.Comparable = `$this$min`[i];
               if (min.compareTo(`$this$min`[i]) > 0) {
                  min = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return (T)min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minOrThrow")
   @JvmStatic
   public fun ByteArray.min(): Byte {
      if (`$this$min`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Byte = `$this$min`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$min`);
         if (1 <= var3) {
            while (true) {
               val e: Byte = `$this$min`[i];
               if (min > `$this$min`[i]) {
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
   @JvmName(name = "minOrThrow")
   @JvmStatic
   public fun ShortArray.min(): Short {
      if (`$this$min`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Short = `$this$min`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$min`);
         if (1 <= var3) {
            while (true) {
               val e: Short = `$this$min`[i];
               if (min > `$this$min`[i]) {
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
   @JvmName(name = "minOrThrow")
   @JvmStatic
   public fun IntArray.min(): Int {
      if (`$this$min`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Int = `$this$min`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$min`);
         if (1 <= var3) {
            while (true) {
               val e: Int = `$this$min`[i];
               if (min > `$this$min`[i]) {
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
   @JvmName(name = "minOrThrow")
   @JvmStatic
   public fun LongArray.min(): Long {
      if (`$this$min`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Long = `$this$min`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$min`);
         if (1 <= var4) {
            while (true) {
               val e: Long = `$this$min`[i];
               if (min > `$this$min`[i]) {
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
   @JvmName(name = "minOrThrow")
   @JvmStatic
   public fun FloatArray.min(): Float {
      if (`$this$min`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Float = `$this$min`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$min`);
         if (1 <= var3) {
            while (true) {
               min = Math.min(min, `$this$min`[i]);
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
   @JvmName(name = "minOrThrow")
   @JvmStatic
   public fun DoubleArray.min(): Double {
      if (`$this$min`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Double = `$this$min`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$min`);
         if (1 <= var4) {
            while (true) {
               min = Math.min(min, `$this$min`[i]);
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
   @JvmName(name = "minOrThrow")
   @JvmStatic
   public fun CharArray.min(): Char {
      if (`$this$min`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Char = `$this$min`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$min`);
         if (1 <= var3) {
            while (true) {
               val e: Char = `$this$min`[i];
               if (Intrinsics.compare(min, `$this$min`[i]) > 0) {
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
   @JvmName(name = "minByOrThrow")
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Array<out T>.minBy(selector: (T) -> R): T {
      if (`$this$minBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minElem: Any = `$this$minBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minBy`);
         if (lastIndex == 0) {
            return (T)minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Any = `$this$minBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minBy`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return (T)minElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> ByteArray.minBy(selector: (Byte) -> R): Byte {
      if (`$this$minBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minElem: Byte = `$this$minBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minBy`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Byte = `$this$minBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minBy`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> ShortArray.minBy(selector: (Short) -> R): Short {
      if (`$this$minBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minElem: Short = `$this$minBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minBy`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Short = `$this$minBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minBy`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> IntArray.minBy(selector: (Int) -> R): Int {
      if (`$this$minBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minElem: Int = `$this$minBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minBy`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Int = `$this$minBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minBy`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> LongArray.minBy(selector: (Long) -> R): Long {
      if (`$this$minBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minElem: Long = `$this$minBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minBy`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Long = `$this$minBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minBy`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> FloatArray.minBy(selector: (Float) -> R): Float {
      if (`$this$minBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minElem: Float = `$this$minBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minBy`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Float = `$this$minBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minBy`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> DoubleArray.minBy(selector: (Double) -> R): Double {
      if (`$this$minBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minElem: Double = `$this$minBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minBy`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Double = `$this$minBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minBy`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> BooleanArray.minBy(selector: (Boolean) -> R): Boolean {
      if (`$this$minBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minElem: Boolean = `$this$minBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minBy`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Boolean = `$this$minBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minBy`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minByOrThrow")
   @JvmStatic
   public inline fun <R : Comparable<R>> CharArray.minBy(selector: (Char) -> R): Char {
      if (`$this$minBy`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minElem: Char = `$this$minBy`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minBy`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Char = `$this$minBy`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minBy`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Array<out T>.minByOrNull(selector: (T) -> R): T? {
      if (`$this$minByOrNull`.length == 0) {
         return null;
      } else {
         var minElem: Any = `$this$minByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minByOrNull`);
         if (lastIndex == 0) {
            return (T)minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Any = `$this$minByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minByOrNull`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return (T)minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> ByteArray.minByOrNull(selector: (Byte) -> R): Byte? {
      if (`$this$minByOrNull`.length == 0) {
         return null;
      } else {
         var minElem: Byte = `$this$minByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minByOrNull`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Byte = `$this$minByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minByOrNull`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> ShortArray.minByOrNull(selector: (Short) -> R): Short? {
      if (`$this$minByOrNull`.length == 0) {
         return null;
      } else {
         var minElem: Short = `$this$minByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minByOrNull`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Short = `$this$minByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minByOrNull`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> IntArray.minByOrNull(selector: (Int) -> R): Int? {
      if (`$this$minByOrNull`.length == 0) {
         return null;
      } else {
         var minElem: Int = `$this$minByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minByOrNull`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Int = `$this$minByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minByOrNull`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> LongArray.minByOrNull(selector: (Long) -> R): Long? {
      if (`$this$minByOrNull`.length == 0) {
         return null;
      } else {
         var minElem: Long = `$this$minByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minByOrNull`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Long = `$this$minByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minByOrNull`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> FloatArray.minByOrNull(selector: (Float) -> R): Float? {
      if (`$this$minByOrNull`.length == 0) {
         return null;
      } else {
         var minElem: Float = `$this$minByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minByOrNull`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Float = `$this$minByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minByOrNull`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> DoubleArray.minByOrNull(selector: (Double) -> R): Double? {
      if (`$this$minByOrNull`.length == 0) {
         return null;
      } else {
         var minElem: Double = `$this$minByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minByOrNull`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Double = `$this$minByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minByOrNull`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> BooleanArray.minByOrNull(selector: (Boolean) -> R): Boolean? {
      if (`$this$minByOrNull`.length == 0) {
         return null;
      } else {
         var minElem: Boolean = `$this$minByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minByOrNull`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Boolean = `$this$minByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minByOrNull`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <R : Comparable<R>> CharArray.minByOrNull(selector: (Char) -> R): Char? {
      if (`$this$minByOrNull`.length == 0) {
         return null;
      } else {
         var minElem: Char = `$this$minByOrNull`[0];
         val lastIndex: Int = ArraysKt.getLastIndex(`$this$minByOrNull`);
         if (lastIndex == 0) {
            return minElem;
         } else {
            var minValue: java.lang.Comparable = selector.invoke(minElem) as java.lang.Comparable;
            var i: Int = 1;
            if (1 <= lastIndex) {
               while (true) {
                  val e: Char = `$this$minByOrNull`[i];
                  val v: java.lang.Comparable = selector.invoke(`$this$minByOrNull`[i]) as java.lang.Comparable;
                  if (minValue.compareTo(v) > 0) {
                     minElem = e;
                     minValue = v;
                  }

                  if (i == lastIndex) {
                     break;
                  }

                  i++;
               }
            }

            return minElem;
         }
      }
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.minOf(selector: (T) -> Double): Double {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.minOf(selector: (Byte) -> Double): Double {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.minOf(selector: (Short) -> Double): Double {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.minOf(selector: (Int) -> Double): Double {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.minOf(selector: (Long) -> Double): Double {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.minOf(selector: (Float) -> Double): Double {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.minOf(selector: (Double) -> Double): Double {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.minOf(selector: (Boolean) -> Double): Double {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.minOf(selector: (Char) -> Double): Double {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Double = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.minOf(selector: (T) -> Float): Float {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.minOf(selector: (Byte) -> Float): Float {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.minOf(selector: (Short) -> Float): Float {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.minOf(selector: (Int) -> Float): Float {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.minOf(selector: (Long) -> Float): Float {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.minOf(selector: (Float) -> Float): Float {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.minOf(selector: (Double) -> Float): Float {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.minOf(selector: (Boolean) -> Float): Float {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.minOf(selector: (Char) -> Float): Float {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Float = (selector.invoke(`$this$minOf`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOf`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Array<out T>.minOf(selector: (T) -> R): R {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ByteArray.minOf(selector: (Byte) -> R): R {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ShortArray.minOf(selector: (Short) -> R): R {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> IntArray.minOf(selector: (Int) -> R): R {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> LongArray.minOf(selector: (Long) -> R): R {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> FloatArray.minOf(selector: (Float) -> R): R {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> DoubleArray.minOf(selector: (Double) -> R): R {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> BooleanArray.minOf(selector: (Boolean) -> R): R {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> CharArray.minOf(selector: (Char) -> R): R {
      if (`$this$minOf`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOf`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOf`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOf`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.minOfOrNull(selector: (T) -> Double): Double? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.minOfOrNull(selector: (Byte) -> Double): Double? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.minOfOrNull(selector: (Short) -> Double): Double? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.minOfOrNull(selector: (Int) -> Double): Double? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.minOfOrNull(selector: (Long) -> Double): Double? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.minOfOrNull(selector: (Float) -> Double): Double? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.minOfOrNull(selector: (Double) -> Double): Double? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.minOfOrNull(selector: (Boolean) -> Double): Double? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.minOfOrNull(selector: (Char) -> Double): Double? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Double = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).doubleValue();
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var5) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).doubleValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.minOfOrNull(selector: (T) -> Float): Float? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.minOfOrNull(selector: (Byte) -> Float): Float? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.minOfOrNull(selector: (Short) -> Float): Float? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.minOfOrNull(selector: (Int) -> Float): Float? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.minOfOrNull(selector: (Long) -> Float): Float? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.minOfOrNull(selector: (Float) -> Float): Float? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.minOfOrNull(selector: (Double) -> Float): Float? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.minOfOrNull(selector: (Boolean) -> Float): Float? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.minOfOrNull(selector: (Char) -> Float): Float? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Float = (selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Number).floatValue();
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               minValue = Math.min(minValue, (selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Number).floatValue());
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
   @InlineOnly
   @JvmStatic
   public inline fun <T, R : Comparable<R>> Array<out T>.minOfOrNull(selector: (T) -> R): R? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ByteArray.minOfOrNull(selector: (Byte) -> R): R? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> ShortArray.minOfOrNull(selector: (Short) -> R): R? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> IntArray.minOfOrNull(selector: (Int) -> R): R? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> LongArray.minOfOrNull(selector: (Long) -> R): R? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> FloatArray.minOfOrNull(selector: (Float) -> R): R? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> DoubleArray.minOfOrNull(selector: (Double) -> R): R? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> BooleanArray.minOfOrNull(selector: (Boolean) -> R): R? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <R : Comparable<R>> CharArray.minOfOrNull(selector: (Char) -> R): R? {
      if (`$this$minOfOrNull`.length == 0) {
         return null;
      } else {
         var minValue: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[0]) as java.lang.Comparable;
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOfOrNull`);
         if (1 <= var4) {
            while (true) {
               val v: java.lang.Comparable = selector.invoke(`$this$minOfOrNull`[i]) as java.lang.Comparable;
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
   @InlineOnly
   @JvmStatic
   public inline fun <T, R> Array<out T>.minOfWith(comparator: Comparator<in R>, selector: (T) -> R): R {
      if (`$this$minOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> ByteArray.minOfWith(comparator: Comparator<in R>, selector: (Byte) -> R): R {
      if (`$this$minOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> ShortArray.minOfWith(comparator: Comparator<in R>, selector: (Short) -> R): R {
      if (`$this$minOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> IntArray.minOfWith(comparator: Comparator<in R>, selector: (Int) -> R): R {
      if (`$this$minOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> LongArray.minOfWith(comparator: Comparator<in R>, selector: (Long) -> R): R {
      if (`$this$minOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> FloatArray.minOfWith(comparator: Comparator<in R>, selector: (Float) -> R): R {
      if (`$this$minOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> DoubleArray.minOfWith(comparator: Comparator<in R>, selector: (Double) -> R): R {
      if (`$this$minOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> BooleanArray.minOfWith(comparator: Comparator<in R>, selector: (Boolean) -> R): R {
      if (`$this$minOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> CharArray.minOfWith(comparator: Comparator<in R>, selector: (Char) -> R): R {
      if (`$this$minOfWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWith`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWith`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWith`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <T, R> Array<out T>.minOfWithOrNull(comparator: Comparator<in R>, selector: (T) -> R): R? {
      if (`$this$minOfWithOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> ByteArray.minOfWithOrNull(comparator: Comparator<in R>, selector: (Byte) -> R): R? {
      if (`$this$minOfWithOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> ShortArray.minOfWithOrNull(comparator: Comparator<in R>, selector: (Short) -> R): R? {
      if (`$this$minOfWithOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> IntArray.minOfWithOrNull(comparator: Comparator<in R>, selector: (Int) -> R): R? {
      if (`$this$minOfWithOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> LongArray.minOfWithOrNull(comparator: Comparator<in R>, selector: (Long) -> R): R? {
      if (`$this$minOfWithOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> FloatArray.minOfWithOrNull(comparator: Comparator<in R>, selector: (Float) -> R): R? {
      if (`$this$minOfWithOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> DoubleArray.minOfWithOrNull(comparator: Comparator<in R>, selector: (Double) -> R): R? {
      if (`$this$minOfWithOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> BooleanArray.minOfWithOrNull(comparator: Comparator<in R>, selector: (Boolean) -> R): R? {
      if (`$this$minOfWithOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWithOrNull`[i]);
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
   @InlineOnly
   @JvmStatic
   public inline fun <R> CharArray.minOfWithOrNull(comparator: Comparator<in R>, selector: (Char) -> R): R? {
      if (`$this$minOfWithOrNull`.length == 0) {
         return null;
      } else {
         var minValue: Any = selector.invoke(`$this$minOfWithOrNull`[0]);
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minOfWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val v: Any = selector.invoke(`$this$minOfWithOrNull`[i]);
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
   @JvmStatic
   public fun Array<out Double>.minOrNull(): Double? {
      if (`$this$minOrNull`.length == 0) {
         return null;
      } else {
         var min: Double = `$this$minOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOrNull`);
         if (1 <= var4) {
            while (true) {
               min = Math.min(min, `$this$minOrNull`[i]);
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
   @JvmStatic
   public fun Array<out Float>.minOrNull(): Float? {
      if (`$this$minOrNull`.length == 0) {
         return null;
      } else {
         var min: Float = `$this$minOrNull`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$minOrNull`);
         if (1 <= var3) {
            while (true) {
               min = Math.min(min, `$this$minOrNull`[i]);
               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun <T : Comparable<T>> Array<out T>.minOrNull(): T? {
      if (`$this$minOrNull`.length == 0) {
         return null;
      } else {
         var min: java.lang.Comparable = `$this$minOrNull`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$minOrNull`);
         if (1 <= var3) {
            while (true) {
               val e: java.lang.Comparable = `$this$minOrNull`[i];
               if (min.compareTo(`$this$minOrNull`[i]) > 0) {
                  min = e;
               }

               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return (T)min;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ByteArray.minOrNull(): Byte? {
      if (`$this$minOrNull`.length == 0) {
         return null;
      } else {
         var min: Byte = `$this$minOrNull`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$minOrNull`);
         if (1 <= var3) {
            while (true) {
               val e: Byte = `$this$minOrNull`[i];
               if (min > `$this$minOrNull`[i]) {
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

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ShortArray.minOrNull(): Short? {
      if (`$this$minOrNull`.length == 0) {
         return null;
      } else {
         var min: Short = `$this$minOrNull`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$minOrNull`);
         if (1 <= var3) {
            while (true) {
               val e: Short = `$this$minOrNull`[i];
               if (min > `$this$minOrNull`[i]) {
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

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun IntArray.minOrNull(): Int? {
      if (`$this$minOrNull`.length == 0) {
         return null;
      } else {
         var min: Int = `$this$minOrNull`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$minOrNull`);
         if (1 <= var3) {
            while (true) {
               val e: Int = `$this$minOrNull`[i];
               if (min > `$this$minOrNull`[i]) {
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

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun LongArray.minOrNull(): Long? {
      if (`$this$minOrNull`.length == 0) {
         return null;
      } else {
         var min: Long = `$this$minOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Long = `$this$minOrNull`[i];
               if (min > `$this$minOrNull`[i]) {
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
   @JvmStatic
   public fun FloatArray.minOrNull(): Float? {
      if (`$this$minOrNull`.length == 0) {
         return null;
      } else {
         var min: Float = `$this$minOrNull`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$minOrNull`);
         if (1 <= var3) {
            while (true) {
               min = Math.min(min, `$this$minOrNull`[i]);
               if (i == var3) {
                  break;
               }

               i++;
            }
         }

         return min;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun DoubleArray.minOrNull(): Double? {
      if (`$this$minOrNull`.length == 0) {
         return null;
      } else {
         var min: Double = `$this$minOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minOrNull`);
         if (1 <= var4) {
            while (true) {
               min = Math.min(min, `$this$minOrNull`[i]);
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
   @JvmStatic
   public fun CharArray.minOrNull(): Char? {
      if (`$this$minOrNull`.length == 0) {
         return null;
      } else {
         var min: Char = `$this$minOrNull`[0];
         var i: Int = 1;
         val var3: Int = ArraysKt.getLastIndex(`$this$minOrNull`);
         if (1 <= var3) {
            while (true) {
               val e: Char = `$this$minOrNull`[i];
               if (Intrinsics.compare(min, `$this$minOrNull`[i]) > 0) {
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
   @JvmName(name = "minWithOrThrow")
   @JvmStatic
   public fun <T> Array<out T>.minWith(comparator: Comparator<in T>): T {
      if (`$this$minWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Any = `$this$minWith`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minWith`);
         if (1 <= var4) {
            while (true) {
               val e: Any = `$this$minWith`[i];
               if (comparator.compare(min, `$this$minWith`[i]) > 0) {
                  min = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (T)min;
      }
   }

   @SinceKotlin(version = "1.7")
   @JvmName(name = "minWithOrThrow")
   @JvmStatic
   public fun ByteArray.minWith(comparator: Comparator<in Byte>): Byte {
      if (`$this$minWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Byte = `$this$minWith`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minWith`);
         if (1 <= var4) {
            while (true) {
               val e: Byte = `$this$minWith`[i];
               if (comparator.compare(min, `$this$minWith`[i]) > 0) {
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
   @JvmName(name = "minWithOrThrow")
   @JvmStatic
   public fun ShortArray.minWith(comparator: Comparator<in Short>): Short {
      if (`$this$minWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Short = `$this$minWith`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minWith`);
         if (1 <= var4) {
            while (true) {
               val e: Short = `$this$minWith`[i];
               if (comparator.compare(min, `$this$minWith`[i]) > 0) {
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
   @JvmName(name = "minWithOrThrow")
   @JvmStatic
   public fun IntArray.minWith(comparator: Comparator<in Int>): Int {
      if (`$this$minWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Int = `$this$minWith`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minWith`);
         if (1 <= var4) {
            while (true) {
               val e: Int = `$this$minWith`[i];
               if (comparator.compare(min, `$this$minWith`[i]) > 0) {
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
   @JvmName(name = "minWithOrThrow")
   @JvmStatic
   public fun LongArray.minWith(comparator: Comparator<in Long>): Long {
      if (`$this$minWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Long = `$this$minWith`[0];
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minWith`);
         if (1 <= var5) {
            while (true) {
               val e: Long = `$this$minWith`[i];
               if (comparator.compare(min, `$this$minWith`[i]) > 0) {
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
   @JvmName(name = "minWithOrThrow")
   @JvmStatic
   public fun FloatArray.minWith(comparator: Comparator<in Float>): Float {
      if (`$this$minWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Float = `$this$minWith`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minWith`);
         if (1 <= var4) {
            while (true) {
               val e: Float = `$this$minWith`[i];
               if (comparator.compare(min, `$this$minWith`[i]) > 0) {
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
   @JvmName(name = "minWithOrThrow")
   @JvmStatic
   public fun DoubleArray.minWith(comparator: Comparator<in Double>): Double {
      if (`$this$minWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Double = `$this$minWith`[0];
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minWith`);
         if (1 <= var5) {
            while (true) {
               val e: Double = `$this$minWith`[i];
               if (comparator.compare(min, `$this$minWith`[i]) > 0) {
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
   @JvmName(name = "minWithOrThrow")
   @JvmStatic
   public fun BooleanArray.minWith(comparator: Comparator<in Boolean>): Boolean {
      if (`$this$minWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Boolean = `$this$minWith`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minWith`);
         if (1 <= var4) {
            while (true) {
               val e: Boolean = `$this$minWith`[i];
               if (comparator.compare(min, `$this$minWith`[i]) > 0) {
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
   @JvmName(name = "minWithOrThrow")
   @JvmStatic
   public fun CharArray.minWith(comparator: Comparator<in Char>): Char {
      if (`$this$minWith`.length == 0) {
         throw new NoSuchElementException();
      } else {
         var min: Char = `$this$minWith`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minWith`);
         if (1 <= var4) {
            while (true) {
               val e: Char = `$this$minWith`[i];
               if (comparator.compare(min, `$this$minWith`[i]) > 0) {
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
   @JvmStatic
   public fun <T> Array<out T>.minWithOrNull(comparator: Comparator<in T>): T? {
      if (`$this$minWithOrNull`.length == 0) {
         return null;
      } else {
         var min: Any = `$this$minWithOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Any = `$this$minWithOrNull`[i];
               if (comparator.compare(min, `$this$minWithOrNull`[i]) > 0) {
                  min = e;
               }

               if (i == var4) {
                  break;
               }

               i++;
            }
         }

         return (T)min;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun ByteArray.minWithOrNull(comparator: Comparator<in Byte>): Byte? {
      if (`$this$minWithOrNull`.length == 0) {
         return null;
      } else {
         var min: Byte = `$this$minWithOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Byte = `$this$minWithOrNull`[i];
               if (comparator.compare(min, `$this$minWithOrNull`[i]) > 0) {
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
   @JvmStatic
   public fun ShortArray.minWithOrNull(comparator: Comparator<in Short>): Short? {
      if (`$this$minWithOrNull`.length == 0) {
         return null;
      } else {
         var min: Short = `$this$minWithOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Short = `$this$minWithOrNull`[i];
               if (comparator.compare(min, `$this$minWithOrNull`[i]) > 0) {
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
   @JvmStatic
   public fun IntArray.minWithOrNull(comparator: Comparator<in Int>): Int? {
      if (`$this$minWithOrNull`.length == 0) {
         return null;
      } else {
         var min: Int = `$this$minWithOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Int = `$this$minWithOrNull`[i];
               if (comparator.compare(min, `$this$minWithOrNull`[i]) > 0) {
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
   @JvmStatic
   public fun LongArray.minWithOrNull(comparator: Comparator<in Long>): Long? {
      if (`$this$minWithOrNull`.length == 0) {
         return null;
      } else {
         var min: Long = `$this$minWithOrNull`[0];
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val e: Long = `$this$minWithOrNull`[i];
               if (comparator.compare(min, `$this$minWithOrNull`[i]) > 0) {
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

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun FloatArray.minWithOrNull(comparator: Comparator<in Float>): Float? {
      if (`$this$minWithOrNull`.length == 0) {
         return null;
      } else {
         var min: Float = `$this$minWithOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Float = `$this$minWithOrNull`[i];
               if (comparator.compare(min, `$this$minWithOrNull`[i]) > 0) {
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
   @JvmStatic
   public fun DoubleArray.minWithOrNull(comparator: Comparator<in Double>): Double? {
      if (`$this$minWithOrNull`.length == 0) {
         return null;
      } else {
         var min: Double = `$this$minWithOrNull`[0];
         var i: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$minWithOrNull`);
         if (1 <= var5) {
            while (true) {
               val e: Double = `$this$minWithOrNull`[i];
               if (comparator.compare(min, `$this$minWithOrNull`[i]) > 0) {
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

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public fun BooleanArray.minWithOrNull(comparator: Comparator<in Boolean>): Boolean? {
      if (`$this$minWithOrNull`.length == 0) {
         return null;
      } else {
         var min: Boolean = `$this$minWithOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Boolean = `$this$minWithOrNull`[i];
               if (comparator.compare(min, `$this$minWithOrNull`[i]) > 0) {
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
   @JvmStatic
   public fun CharArray.minWithOrNull(comparator: Comparator<in Char>): Char? {
      if (`$this$minWithOrNull`.length == 0) {
         return null;
      } else {
         var min: Char = `$this$minWithOrNull`[0];
         var i: Int = 1;
         val var4: Int = ArraysKt.getLastIndex(`$this$minWithOrNull`);
         if (1 <= var4) {
            while (true) {
               val e: Char = `$this$minWithOrNull`[i];
               if (comparator.compare(min, `$this$minWithOrNull`[i]) > 0) {
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

   @JvmStatic
   public fun <T> Array<out T>.none(): Boolean {
      return `$this$none`.length == 0;
   }

   @JvmStatic
   public fun ByteArray.none(): Boolean {
      return `$this$none`.length == 0;
   }

   @JvmStatic
   public fun ShortArray.none(): Boolean {
      return `$this$none`.length == 0;
   }

   @JvmStatic
   public fun IntArray.none(): Boolean {
      return `$this$none`.length == 0;
   }

   @JvmStatic
   public fun LongArray.none(): Boolean {
      return `$this$none`.length == 0;
   }

   @JvmStatic
   public fun FloatArray.none(): Boolean {
      return `$this$none`.length == 0;
   }

   @JvmStatic
   public fun DoubleArray.none(): Boolean {
      return `$this$none`.length == 0;
   }

   @JvmStatic
   public fun BooleanArray.none(): Boolean {
      return `$this$none`.length == 0;
   }

   @JvmStatic
   public fun CharArray.none(): Boolean {
      return `$this$none`.length == 0;
   }

   @JvmStatic
   public inline fun <T> Array<out T>.none(predicate: (T) -> Boolean): Boolean {
      for (Object element : $this$none) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun ByteArray.none(predicate: (Byte) -> Boolean): Boolean {
      for (byte element : $this$none) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun ShortArray.none(predicate: (Short) -> Boolean): Boolean {
      for (short element : $this$none) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun IntArray.none(predicate: (Int) -> Boolean): Boolean {
      for (int element : $this$none) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun LongArray.none(predicate: (Long) -> Boolean): Boolean {
      for (long element : $this$none) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun FloatArray.none(predicate: (Float) -> Boolean): Boolean {
      for (float element : $this$none) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun DoubleArray.none(predicate: (Double) -> Boolean): Boolean {
      for (double element : $this$none) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun BooleanArray.none(predicate: (Boolean) -> Boolean): Boolean {
      for (boolean element : $this$none) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @JvmStatic
   public inline fun CharArray.none(predicate: (Char) -> Boolean): Boolean {
      for (char element : $this$none) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            return false;
         }
      }

      return true;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.onEach(action: (T) -> Unit): Array<out T> {
      for (Object element : $this$onEach) {
         action.invoke(element);
      }

      return (T[])`$this$onEach`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.onEach(action: (Byte) -> Unit): ByteArray {
      for (byte element : $this$onEach) {
         action.invoke(element);
      }

      return `$this$onEach`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.onEach(action: (Short) -> Unit): ShortArray {
      for (short element : $this$onEach) {
         action.invoke(element);
      }

      return `$this$onEach`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.onEach(action: (Int) -> Unit): IntArray {
      for (int element : $this$onEach) {
         action.invoke(element);
      }

      return `$this$onEach`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.onEach(action: (Long) -> Unit): LongArray {
      for (long element : $this$onEach) {
         action.invoke(element);
      }

      return `$this$onEach`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.onEach(action: (Float) -> Unit): FloatArray {
      for (float element : $this$onEach) {
         action.invoke(element);
      }

      return `$this$onEach`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.onEach(action: (Double) -> Unit): DoubleArray {
      for (double element : $this$onEach) {
         action.invoke(element);
      }

      return `$this$onEach`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.onEach(action: (Boolean) -> Unit): BooleanArray {
      for (boolean element : $this$onEach) {
         action.invoke(element);
      }

      return `$this$onEach`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.onEach(action: (Char) -> Unit): CharArray {
      for (char element : $this$onEach) {
         action.invoke(element);
      }

      return `$this$onEach`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.onEachIndexed(action: (Int, T) -> Unit): Array<out T> {
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$onEachIndexed) {
         action.invoke(`index$iv`++, `item$iv`);
      }

      return (T[])`$this$onEachIndexed`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.onEachIndexed(action: (Int, Byte) -> Unit): ByteArray {
      var `index$iv`: Int = 0;

      for (byte item$iv : $this$onEachIndexed) {
         action.invoke(`index$iv`++, `item$iv`);
      }

      return `$this$onEachIndexed`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.onEachIndexed(action: (Int, Short) -> Unit): ShortArray {
      var `index$iv`: Int = 0;

      for (short item$iv : $this$onEachIndexed) {
         action.invoke(`index$iv`++, `item$iv`);
      }

      return `$this$onEachIndexed`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.onEachIndexed(action: (Int, Int) -> Unit): IntArray {
      var `index$iv`: Int = 0;

      for (int item$iv : $this$onEachIndexed) {
         action.invoke(`index$iv`++, `item$iv`);
      }

      return `$this$onEachIndexed`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.onEachIndexed(action: (Int, Long) -> Unit): LongArray {
      var `index$iv`: Int = 0;

      for (long item$iv : $this$onEachIndexed) {
         action.invoke(`index$iv`++, `item$iv`);
      }

      return `$this$onEachIndexed`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.onEachIndexed(action: (Int, Float) -> Unit): FloatArray {
      var `index$iv`: Int = 0;

      for (float item$iv : $this$onEachIndexed) {
         action.invoke(`index$iv`++, `item$iv`);
      }

      return `$this$onEachIndexed`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.onEachIndexed(action: (Int, Double) -> Unit): DoubleArray {
      var `index$iv`: Int = 0;

      for (double item$iv : $this$onEachIndexed) {
         action.invoke(`index$iv`++, `item$iv`);
      }

      return `$this$onEachIndexed`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.onEachIndexed(action: (Int, Boolean) -> Unit): BooleanArray {
      var `index$iv`: Int = 0;

      for (boolean item$iv : $this$onEachIndexed) {
         action.invoke(`index$iv`++, `item$iv`);
      }

      return `$this$onEachIndexed`;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.onEachIndexed(action: (Int, Char) -> Unit): CharArray {
      var `index$iv`: Int = 0;

      for (char item$iv : $this$onEachIndexed) {
         action.invoke(`index$iv`++, `item$iv`);
      }

      return `$this$onEachIndexed`;
   }

   @JvmStatic
   public inline fun <S, T : S> Array<out T>.reduce(operation: (S, T) -> S): S {
      if (`$this$reduce`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Any = `$this$reduce`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduce`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(accumulator, `$this$reduce`[index]);
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return (S)accumulator;
      }
   }

   @JvmStatic
   public inline fun ByteArray.reduce(operation: (Byte, Byte) -> Byte): Byte {
      if (`$this$reduce`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Byte = `$this$reduce`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduce`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(accumulator, `$this$reduce`[index]) as java.lang.Number).byteValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun ShortArray.reduce(operation: (Short, Short) -> Short): Short {
      if (`$this$reduce`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Short = `$this$reduce`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduce`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(accumulator, `$this$reduce`[index]) as java.lang.Number).shortValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun IntArray.reduce(operation: (Int, Int) -> Int): Int {
      if (`$this$reduce`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Int = `$this$reduce`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduce`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(accumulator, `$this$reduce`[index]) as java.lang.Number).intValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun LongArray.reduce(operation: (Long, Long) -> Long): Long {
      if (`$this$reduce`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Long = `$this$reduce`[0];
         var index: Int = 1;
         val var6: Int = ArraysKt.getLastIndex(`$this$reduce`);
         if (1 <= var6) {
            while (true) {
               accumulator = (operation.invoke(accumulator, `$this$reduce`[index]) as java.lang.Number).longValue();
               if (index == var6) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun FloatArray.reduce(operation: (Float, Float) -> Float): Float {
      if (`$this$reduce`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Float = `$this$reduce`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduce`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(accumulator, `$this$reduce`[index]) as java.lang.Number).floatValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun DoubleArray.reduce(operation: (Double, Double) -> Double): Double {
      if (`$this$reduce`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Double = `$this$reduce`[0];
         var index: Int = 1;
         val var6: Int = ArraysKt.getLastIndex(`$this$reduce`);
         if (1 <= var6) {
            while (true) {
               accumulator = (operation.invoke(accumulator, `$this$reduce`[index]) as java.lang.Number).doubleValue();
               if (index == var6) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun BooleanArray.reduce(operation: (Boolean, Boolean) -> Boolean): Boolean {
      if (`$this$reduce`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Boolean = `$this$reduce`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduce`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(accumulator, `$this$reduce`[index]) as java.lang.Boolean;
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun CharArray.reduce(operation: (Char, Char) -> Char): Char {
      if (`$this$reduce`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Char = `$this$reduce`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduce`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(accumulator, `$this$reduce`[index]) as Character;
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun <S, T : S> Array<out T>.reduceIndexed(operation: (Int, S, T) -> S): S {
      if (`$this$reduceIndexed`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Any = `$this$reduceIndexed`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceIndexed`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(index, accumulator, `$this$reduceIndexed`[index]);
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return (S)accumulator;
      }
   }

   @JvmStatic
   public inline fun ByteArray.reduceIndexed(operation: (Int, Byte, Byte) -> Byte): Byte {
      if (`$this$reduceIndexed`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Byte = `$this$reduceIndexed`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceIndexed`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(index, accumulator, `$this$reduceIndexed`[index]) as java.lang.Number).byteValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun ShortArray.reduceIndexed(operation: (Int, Short, Short) -> Short): Short {
      if (`$this$reduceIndexed`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Short = `$this$reduceIndexed`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceIndexed`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(index, accumulator, `$this$reduceIndexed`[index]) as java.lang.Number).shortValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun IntArray.reduceIndexed(operation: (Int, Int, Int) -> Int): Int {
      if (`$this$reduceIndexed`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Int = `$this$reduceIndexed`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceIndexed`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(index, accumulator, `$this$reduceIndexed`[index]) as java.lang.Number).intValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun LongArray.reduceIndexed(operation: (Int, Long, Long) -> Long): Long {
      if (`$this$reduceIndexed`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Long = `$this$reduceIndexed`[0];
         var index: Int = 1;
         val var6: Int = ArraysKt.getLastIndex(`$this$reduceIndexed`);
         if (1 <= var6) {
            while (true) {
               accumulator = (operation.invoke(index, accumulator, `$this$reduceIndexed`[index]) as java.lang.Number).longValue();
               if (index == var6) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun FloatArray.reduceIndexed(operation: (Int, Float, Float) -> Float): Float {
      if (`$this$reduceIndexed`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Float = `$this$reduceIndexed`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceIndexed`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(index, accumulator, `$this$reduceIndexed`[index]) as java.lang.Number).floatValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun DoubleArray.reduceIndexed(operation: (Int, Double, Double) -> Double): Double {
      if (`$this$reduceIndexed`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Double = `$this$reduceIndexed`[0];
         var index: Int = 1;
         val var6: Int = ArraysKt.getLastIndex(`$this$reduceIndexed`);
         if (1 <= var6) {
            while (true) {
               accumulator = (operation.invoke(index, accumulator, `$this$reduceIndexed`[index]) as java.lang.Number).doubleValue();
               if (index == var6) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun BooleanArray.reduceIndexed(operation: (Int, Boolean, Boolean) -> Boolean): Boolean {
      if (`$this$reduceIndexed`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Boolean = `$this$reduceIndexed`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceIndexed`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(index, accumulator, `$this$reduceIndexed`[index]) as java.lang.Boolean;
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun CharArray.reduceIndexed(operation: (Int, Char, Char) -> Char): Char {
      if (`$this$reduceIndexed`.length == 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Char = `$this$reduceIndexed`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceIndexed`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(index, accumulator, `$this$reduceIndexed`[index]) as Character;
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <S, T : S> Array<out T>.reduceIndexedOrNull(operation: (Int, S, T) -> S): S? {
      if (`$this$reduceIndexedOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Any = `$this$reduceIndexedOrNull`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceIndexedOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(index, accumulator, `$this$reduceIndexedOrNull`[index]);
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return (S)accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun ByteArray.reduceIndexedOrNull(operation: (Int, Byte, Byte) -> Byte): Byte? {
      if (`$this$reduceIndexedOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Byte = `$this$reduceIndexedOrNull`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceIndexedOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(index, accumulator, `$this$reduceIndexedOrNull`[index]) as java.lang.Number).byteValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun ShortArray.reduceIndexedOrNull(operation: (Int, Short, Short) -> Short): Short? {
      if (`$this$reduceIndexedOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Short = `$this$reduceIndexedOrNull`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceIndexedOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(index, accumulator, `$this$reduceIndexedOrNull`[index]) as java.lang.Number).shortValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun IntArray.reduceIndexedOrNull(operation: (Int, Int, Int) -> Int): Int? {
      if (`$this$reduceIndexedOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Int = `$this$reduceIndexedOrNull`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceIndexedOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(index, accumulator, `$this$reduceIndexedOrNull`[index]) as java.lang.Number).intValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun LongArray.reduceIndexedOrNull(operation: (Int, Long, Long) -> Long): Long? {
      if (`$this$reduceIndexedOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Long = `$this$reduceIndexedOrNull`[0];
         var index: Int = 1;
         val var6: Int = ArraysKt.getLastIndex(`$this$reduceIndexedOrNull`);
         if (1 <= var6) {
            while (true) {
               accumulator = (operation.invoke(index, accumulator, `$this$reduceIndexedOrNull`[index]) as java.lang.Number).longValue();
               if (index == var6) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun FloatArray.reduceIndexedOrNull(operation: (Int, Float, Float) -> Float): Float? {
      if (`$this$reduceIndexedOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Float = `$this$reduceIndexedOrNull`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceIndexedOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(index, accumulator, `$this$reduceIndexedOrNull`[index]) as java.lang.Number).floatValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun DoubleArray.reduceIndexedOrNull(operation: (Int, Double, Double) -> Double): Double? {
      if (`$this$reduceIndexedOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Double = `$this$reduceIndexedOrNull`[0];
         var index: Int = 1;
         val var6: Int = ArraysKt.getLastIndex(`$this$reduceIndexedOrNull`);
         if (1 <= var6) {
            while (true) {
               accumulator = (operation.invoke(index, accumulator, `$this$reduceIndexedOrNull`[index]) as java.lang.Number).doubleValue();
               if (index == var6) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun BooleanArray.reduceIndexedOrNull(operation: (Int, Boolean, Boolean) -> Boolean): Boolean? {
      if (`$this$reduceIndexedOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Boolean = `$this$reduceIndexedOrNull`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceIndexedOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(index, accumulator, `$this$reduceIndexedOrNull`[index]) as java.lang.Boolean;
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun CharArray.reduceIndexedOrNull(operation: (Int, Char, Char) -> Char): Char? {
      if (`$this$reduceIndexedOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Char = `$this$reduceIndexedOrNull`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceIndexedOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(index, accumulator, `$this$reduceIndexedOrNull`[index]) as Character;
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <S, T : S> Array<out T>.reduceOrNull(operation: (S, T) -> S): S? {
      if (`$this$reduceOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Any = `$this$reduceOrNull`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(accumulator, `$this$reduceOrNull`[index]);
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return (S)accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun ByteArray.reduceOrNull(operation: (Byte, Byte) -> Byte): Byte? {
      if (`$this$reduceOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Byte = `$this$reduceOrNull`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(accumulator, `$this$reduceOrNull`[index]) as java.lang.Number).byteValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun ShortArray.reduceOrNull(operation: (Short, Short) -> Short): Short? {
      if (`$this$reduceOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Short = `$this$reduceOrNull`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(accumulator, `$this$reduceOrNull`[index]) as java.lang.Number).shortValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun IntArray.reduceOrNull(operation: (Int, Int) -> Int): Int? {
      if (`$this$reduceOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Int = `$this$reduceOrNull`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(accumulator, `$this$reduceOrNull`[index]) as java.lang.Number).intValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun LongArray.reduceOrNull(operation: (Long, Long) -> Long): Long? {
      if (`$this$reduceOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Long = `$this$reduceOrNull`[0];
         var index: Int = 1;
         val var6: Int = ArraysKt.getLastIndex(`$this$reduceOrNull`);
         if (1 <= var6) {
            while (true) {
               accumulator = (operation.invoke(accumulator, `$this$reduceOrNull`[index]) as java.lang.Number).longValue();
               if (index == var6) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun FloatArray.reduceOrNull(operation: (Float, Float) -> Float): Float? {
      if (`$this$reduceOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Float = `$this$reduceOrNull`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = (operation.invoke(accumulator, `$this$reduceOrNull`[index]) as java.lang.Number).floatValue();
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun DoubleArray.reduceOrNull(operation: (Double, Double) -> Double): Double? {
      if (`$this$reduceOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Double = `$this$reduceOrNull`[0];
         var index: Int = 1;
         val var6: Int = ArraysKt.getLastIndex(`$this$reduceOrNull`);
         if (1 <= var6) {
            while (true) {
               accumulator = (operation.invoke(accumulator, `$this$reduceOrNull`[index]) as java.lang.Number).doubleValue();
               if (index == var6) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun BooleanArray.reduceOrNull(operation: (Boolean, Boolean) -> Boolean): Boolean? {
      if (`$this$reduceOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Boolean = `$this$reduceOrNull`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(accumulator, `$this$reduceOrNull`[index]) as java.lang.Boolean;
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun CharArray.reduceOrNull(operation: (Char, Char) -> Char): Char? {
      if (`$this$reduceOrNull`.length == 0) {
         return null;
      } else {
         var accumulator: Char = `$this$reduceOrNull`[0];
         var index: Int = 1;
         val var5: Int = ArraysKt.getLastIndex(`$this$reduceOrNull`);
         if (1 <= var5) {
            while (true) {
               accumulator = operation.invoke(accumulator, `$this$reduceOrNull`[index]) as Character;
               if (index == var5) {
                  break;
               }

               index++;
            }
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun <S, T : S> Array<out T>.reduceRight(operation: (T, S) -> S): S {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRight`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Any = `$this$reduceRight`[index--];

         while (index >= 0) {
            accumulator = operation.invoke(`$this$reduceRight`[index--], accumulator);
         }

         return (S)accumulator;
      }
   }

   @JvmStatic
   public inline fun ByteArray.reduceRight(operation: (Byte, Byte) -> Byte): Byte {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRight`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Byte = `$this$reduceRight`[index--];

         while (index >= 0) {
            accumulator = (operation.invoke(`$this$reduceRight`[index--], accumulator) as java.lang.Number).byteValue();
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun ShortArray.reduceRight(operation: (Short, Short) -> Short): Short {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRight`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Short = `$this$reduceRight`[index--];

         while (index >= 0) {
            accumulator = (operation.invoke(`$this$reduceRight`[index--], accumulator) as java.lang.Number).shortValue();
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun IntArray.reduceRight(operation: (Int, Int) -> Int): Int {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRight`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Int = `$this$reduceRight`[index--];

         while (index >= 0) {
            accumulator = (operation.invoke(`$this$reduceRight`[index--], accumulator) as java.lang.Number).intValue();
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun LongArray.reduceRight(operation: (Long, Long) -> Long): Long {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRight`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Long = `$this$reduceRight`[index--];

         while (index >= 0) {
            accumulator = (operation.invoke(`$this$reduceRight`[index--], accumulator) as java.lang.Number).longValue();
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun FloatArray.reduceRight(operation: (Float, Float) -> Float): Float {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRight`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Float = `$this$reduceRight`[index--];

         while (index >= 0) {
            accumulator = (operation.invoke(`$this$reduceRight`[index--], accumulator) as java.lang.Number).floatValue();
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun DoubleArray.reduceRight(operation: (Double, Double) -> Double): Double {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRight`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Double = `$this$reduceRight`[index--];

         while (index >= 0) {
            accumulator = (operation.invoke(`$this$reduceRight`[index--], accumulator) as java.lang.Number).doubleValue();
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun BooleanArray.reduceRight(operation: (Boolean, Boolean) -> Boolean): Boolean {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRight`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Boolean = `$this$reduceRight`[index--];

         while (index >= 0) {
            accumulator = operation.invoke(`$this$reduceRight`[index--], accumulator) as java.lang.Boolean;
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun CharArray.reduceRight(operation: (Char, Char) -> Char): Char {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRight`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Char = `$this$reduceRight`[index--];

         while (index >= 0) {
            accumulator = operation.invoke(`$this$reduceRight`[index--], accumulator) as Character;
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun <S, T : S> Array<out T>.reduceRightIndexed(operation: (Int, T, S) -> S): S {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexed`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Any;
         for (accumulator = $this$reduceRightIndexed[index--]; index >= 0; index--) {
            accumulator = operation.invoke(index, `$this$reduceRightIndexed`[index], accumulator);
         }

         return (S)accumulator;
      }
   }

   @JvmStatic
   public inline fun ByteArray.reduceRightIndexed(operation: (Int, Byte, Byte) -> Byte): Byte {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexed`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Byte;
         for (accumulator = $this$reduceRightIndexed[index--]; index >= 0; index--) {
            accumulator = (operation.invoke(index, `$this$reduceRightIndexed`[index], accumulator) as java.lang.Number).byteValue();
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun ShortArray.reduceRightIndexed(operation: (Int, Short, Short) -> Short): Short {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexed`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Short;
         for (accumulator = $this$reduceRightIndexed[index--]; index >= 0; index--) {
            accumulator = (operation.invoke(index, `$this$reduceRightIndexed`[index], accumulator) as java.lang.Number).shortValue();
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun IntArray.reduceRightIndexed(operation: (Int, Int, Int) -> Int): Int {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexed`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Int;
         for (accumulator = $this$reduceRightIndexed[index--]; index >= 0; index--) {
            accumulator = (operation.invoke(index, `$this$reduceRightIndexed`[index], accumulator) as java.lang.Number).intValue();
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun LongArray.reduceRightIndexed(operation: (Int, Long, Long) -> Long): Long {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexed`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Long;
         for (accumulator = $this$reduceRightIndexed[index--]; index >= 0; index--) {
            accumulator = (operation.invoke(index, `$this$reduceRightIndexed`[index], accumulator) as java.lang.Number).longValue();
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun FloatArray.reduceRightIndexed(operation: (Int, Float, Float) -> Float): Float {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexed`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Float;
         for (accumulator = $this$reduceRightIndexed[index--]; index >= 0; index--) {
            accumulator = (operation.invoke(index, `$this$reduceRightIndexed`[index], accumulator) as java.lang.Number).floatValue();
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun DoubleArray.reduceRightIndexed(operation: (Int, Double, Double) -> Double): Double {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexed`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Double;
         for (accumulator = $this$reduceRightIndexed[index--]; index >= 0; index--) {
            accumulator = (operation.invoke(index, `$this$reduceRightIndexed`[index], accumulator) as java.lang.Number).doubleValue();
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun BooleanArray.reduceRightIndexed(operation: (Int, Boolean, Boolean) -> Boolean): Boolean {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexed`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Boolean;
         for (accumulator = $this$reduceRightIndexed[index--]; index >= 0; index--) {
            accumulator = operation.invoke(index, `$this$reduceRightIndexed`[index], accumulator) as java.lang.Boolean;
         }

         return accumulator;
      }
   }

   @JvmStatic
   public inline fun CharArray.reduceRightIndexed(operation: (Int, Char, Char) -> Char): Char {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexed`);
      if (index < 0) {
         throw new UnsupportedOperationException("Empty array can't be reduced.");
      } else {
         var accumulator: Char;
         for (accumulator = $this$reduceRightIndexed[index--]; index >= 0; index--) {
            accumulator = operation.invoke(index, `$this$reduceRightIndexed`[index], accumulator) as Character;
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <S, T : S> Array<out T>.reduceRightIndexedOrNull(operation: (Int, T, S) -> S): S? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexedOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Any;
         for (accumulator = $this$reduceRightIndexedOrNull[index--]; index >= 0; index--) {
            accumulator = operation.invoke(index, `$this$reduceRightIndexedOrNull`[index], accumulator);
         }

         return (S)accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun ByteArray.reduceRightIndexedOrNull(operation: (Int, Byte, Byte) -> Byte): Byte? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexedOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Byte;
         for (accumulator = $this$reduceRightIndexedOrNull[index--]; index >= 0; index--) {
            accumulator = (operation.invoke(index, `$this$reduceRightIndexedOrNull`[index], accumulator) as java.lang.Number).byteValue();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun ShortArray.reduceRightIndexedOrNull(operation: (Int, Short, Short) -> Short): Short? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexedOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Short;
         for (accumulator = $this$reduceRightIndexedOrNull[index--]; index >= 0; index--) {
            accumulator = (operation.invoke(index, `$this$reduceRightIndexedOrNull`[index], accumulator) as java.lang.Number).shortValue();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun IntArray.reduceRightIndexedOrNull(operation: (Int, Int, Int) -> Int): Int? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexedOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Int;
         for (accumulator = $this$reduceRightIndexedOrNull[index--]; index >= 0; index--) {
            accumulator = (operation.invoke(index, `$this$reduceRightIndexedOrNull`[index], accumulator) as java.lang.Number).intValue();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun LongArray.reduceRightIndexedOrNull(operation: (Int, Long, Long) -> Long): Long? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexedOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Long;
         for (accumulator = $this$reduceRightIndexedOrNull[index--]; index >= 0; index--) {
            accumulator = (operation.invoke(index, `$this$reduceRightIndexedOrNull`[index], accumulator) as java.lang.Number).longValue();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun FloatArray.reduceRightIndexedOrNull(operation: (Int, Float, Float) -> Float): Float? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexedOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Float;
         for (accumulator = $this$reduceRightIndexedOrNull[index--]; index >= 0; index--) {
            accumulator = (operation.invoke(index, `$this$reduceRightIndexedOrNull`[index], accumulator) as java.lang.Number).floatValue();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun DoubleArray.reduceRightIndexedOrNull(operation: (Int, Double, Double) -> Double): Double? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexedOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Double;
         for (accumulator = $this$reduceRightIndexedOrNull[index--]; index >= 0; index--) {
            accumulator = (operation.invoke(index, `$this$reduceRightIndexedOrNull`[index], accumulator) as java.lang.Number).doubleValue();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun BooleanArray.reduceRightIndexedOrNull(operation: (Int, Boolean, Boolean) -> Boolean): Boolean? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexedOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Boolean;
         for (accumulator = $this$reduceRightIndexedOrNull[index--]; index >= 0; index--) {
            accumulator = operation.invoke(index, `$this$reduceRightIndexedOrNull`[index], accumulator) as java.lang.Boolean;
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun CharArray.reduceRightIndexedOrNull(operation: (Int, Char, Char) -> Char): Char? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightIndexedOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Char;
         for (accumulator = $this$reduceRightIndexedOrNull[index--]; index >= 0; index--) {
            accumulator = operation.invoke(index, `$this$reduceRightIndexedOrNull`[index], accumulator) as Character;
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <S, T : S> Array<out T>.reduceRightOrNull(operation: (T, S) -> S): S? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Any = `$this$reduceRightOrNull`[index--];

         while (index >= 0) {
            accumulator = operation.invoke(`$this$reduceRightOrNull`[index--], accumulator);
         }

         return (S)accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun ByteArray.reduceRightOrNull(operation: (Byte, Byte) -> Byte): Byte? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Byte = `$this$reduceRightOrNull`[index--];

         while (index >= 0) {
            accumulator = (operation.invoke(`$this$reduceRightOrNull`[index--], accumulator) as java.lang.Number).byteValue();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun ShortArray.reduceRightOrNull(operation: (Short, Short) -> Short): Short? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Short = `$this$reduceRightOrNull`[index--];

         while (index >= 0) {
            accumulator = (operation.invoke(`$this$reduceRightOrNull`[index--], accumulator) as java.lang.Number).shortValue();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun IntArray.reduceRightOrNull(operation: (Int, Int) -> Int): Int? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Int = `$this$reduceRightOrNull`[index--];

         while (index >= 0) {
            accumulator = (operation.invoke(`$this$reduceRightOrNull`[index--], accumulator) as java.lang.Number).intValue();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun LongArray.reduceRightOrNull(operation: (Long, Long) -> Long): Long? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Long = `$this$reduceRightOrNull`[index--];

         while (index >= 0) {
            accumulator = (operation.invoke(`$this$reduceRightOrNull`[index--], accumulator) as java.lang.Number).longValue();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun FloatArray.reduceRightOrNull(operation: (Float, Float) -> Float): Float? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Float = `$this$reduceRightOrNull`[index--];

         while (index >= 0) {
            accumulator = (operation.invoke(`$this$reduceRightOrNull`[index--], accumulator) as java.lang.Number).floatValue();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun DoubleArray.reduceRightOrNull(operation: (Double, Double) -> Double): Double? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Double = `$this$reduceRightOrNull`[index--];

         while (index >= 0) {
            accumulator = (operation.invoke(`$this$reduceRightOrNull`[index--], accumulator) as java.lang.Number).doubleValue();
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun BooleanArray.reduceRightOrNull(operation: (Boolean, Boolean) -> Boolean): Boolean? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Boolean = `$this$reduceRightOrNull`[index--];

         while (index >= 0) {
            accumulator = operation.invoke(`$this$reduceRightOrNull`[index--], accumulator) as java.lang.Boolean;
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun CharArray.reduceRightOrNull(operation: (Char, Char) -> Char): Char? {
      var index: Int = ArraysKt.getLastIndex(`$this$reduceRightOrNull`);
      if (index < 0) {
         return null;
      } else {
         var accumulator: Char = `$this$reduceRightOrNull`[index--];

         while (index >= 0) {
            accumulator = operation.invoke(`$this$reduceRightOrNull`[index--], accumulator) as Character;
         }

         return accumulator;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <T, R> Array<out T>.runningFold(initial: R, operation: (R, T) -> R): List<R> {
      if (`$this$runningFold`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFold`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var9: Any = initial;

         for (Object element : $this$runningFold) {
            var9 = operation.invoke(var9, element);
            result.add(var9);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> ByteArray.runningFold(initial: R, operation: (R, Byte) -> R): List<R> {
      if (`$this$runningFold`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFold`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var8: Any = initial;

         for (byte element : $this$runningFold) {
            var8 = operation.invoke(var8, element);
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> ShortArray.runningFold(initial: R, operation: (R, Short) -> R): List<R> {
      if (`$this$runningFold`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFold`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var8: Any = initial;

         for (short element : $this$runningFold) {
            var8 = operation.invoke(var8, element);
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> IntArray.runningFold(initial: R, operation: (R, Int) -> R): List<R> {
      if (`$this$runningFold`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFold`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var8: Any = initial;

         for (int element : $this$runningFold) {
            var8 = operation.invoke(var8, element);
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> LongArray.runningFold(initial: R, operation: (R, Long) -> R): List<R> {
      if (`$this$runningFold`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFold`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var9: Any = initial;

         for (long element : $this$runningFold) {
            var9 = operation.invoke(var9, element);
            result.add(var9);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> FloatArray.runningFold(initial: R, operation: (R, Float) -> R): List<R> {
      if (`$this$runningFold`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFold`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var8: Any = initial;

         for (float element : $this$runningFold) {
            var8 = operation.invoke(var8, element);
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> DoubleArray.runningFold(initial: R, operation: (R, Double) -> R): List<R> {
      if (`$this$runningFold`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFold`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var9: Any = initial;

         for (double element : $this$runningFold) {
            var9 = operation.invoke(var9, element);
            result.add(var9);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> BooleanArray.runningFold(initial: R, operation: (R, Boolean) -> R): List<R> {
      if (`$this$runningFold`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFold`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var8: Any = initial;

         for (boolean element : $this$runningFold) {
            var8 = operation.invoke(var8, element);
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> CharArray.runningFold(initial: R, operation: (R, Char) -> R): List<R> {
      if (`$this$runningFold`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFold`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var8: Any = initial;

         for (char element : $this$runningFold) {
            var8 = operation.invoke(var8, element);
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <T, R> Array<out T>.runningFoldIndexed(initial: R, operation: (Int, R, T) -> R): List<R> {
      if (`$this$runningFoldIndexed`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFoldIndexed`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var8: Any = initial;
         var index: Int = 0;

         for (int var9 = $this$runningFoldIndexed.length; index < var9; index++) {
            var8 = operation.invoke(index, var8, `$this$runningFoldIndexed`[index]);
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> ByteArray.runningFoldIndexed(initial: R, operation: (Int, R, Byte) -> R): List<R> {
      if (`$this$runningFoldIndexed`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFoldIndexed`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var7: Any = initial;
         var index: Int = 0;

         for (int var8 = $this$runningFoldIndexed.length; index < var8; index++) {
            var7 = operation.invoke(index, var7, `$this$runningFoldIndexed`[index]);
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> ShortArray.runningFoldIndexed(initial: R, operation: (Int, R, Short) -> R): List<R> {
      if (`$this$runningFoldIndexed`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFoldIndexed`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var7: Any = initial;
         var index: Int = 0;

         for (int var8 = $this$runningFoldIndexed.length; index < var8; index++) {
            var7 = operation.invoke(index, var7, `$this$runningFoldIndexed`[index]);
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> IntArray.runningFoldIndexed(initial: R, operation: (Int, R, Int) -> R): List<R> {
      if (`$this$runningFoldIndexed`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFoldIndexed`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var7: Any = initial;
         var index: Int = 0;

         for (int var8 = $this$runningFoldIndexed.length; index < var8; index++) {
            var7 = operation.invoke(index, var7, `$this$runningFoldIndexed`[index]);
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> LongArray.runningFoldIndexed(initial: R, operation: (Int, R, Long) -> R): List<R> {
      if (`$this$runningFoldIndexed`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFoldIndexed`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var7: Any = initial;
         var index: Int = 0;

         for (int var8 = $this$runningFoldIndexed.length; index < var8; index++) {
            var7 = operation.invoke(index, var7, `$this$runningFoldIndexed`[index]);
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> FloatArray.runningFoldIndexed(initial: R, operation: (Int, R, Float) -> R): List<R> {
      if (`$this$runningFoldIndexed`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFoldIndexed`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var7: Any = initial;
         var index: Int = 0;

         for (int var8 = $this$runningFoldIndexed.length; index < var8; index++) {
            var7 = operation.invoke(index, var7, `$this$runningFoldIndexed`[index]);
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> DoubleArray.runningFoldIndexed(initial: R, operation: (Int, R, Double) -> R): List<R> {
      if (`$this$runningFoldIndexed`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFoldIndexed`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var7: Any = initial;
         var index: Int = 0;

         for (int var8 = $this$runningFoldIndexed.length; index < var8; index++) {
            var7 = operation.invoke(index, var7, `$this$runningFoldIndexed`[index]);
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> BooleanArray.runningFoldIndexed(initial: R, operation: (Int, R, Boolean) -> R): List<R> {
      if (`$this$runningFoldIndexed`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFoldIndexed`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var7: Any = initial;
         var index: Int = 0;

         for (int var8 = $this$runningFoldIndexed.length; index < var8; index++) {
            var7 = operation.invoke(index, var7, `$this$runningFoldIndexed`[index]);
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> CharArray.runningFoldIndexed(initial: R, operation: (Int, R, Char) -> R): List<R> {
      if (`$this$runningFoldIndexed`.length == 0) {
         return (java.util.List<R>)CollectionsKt.listOf(initial);
      } else {
         val accumulator: ArrayList = new ArrayList(`$this$runningFoldIndexed`.length + 1);
         accumulator.add(initial);
         val result: ArrayList = accumulator;
         var var7: Any = initial;
         var index: Int = 0;

         for (int var8 = $this$runningFoldIndexed.length; index < var8; index++) {
            var7 = operation.invoke(index, var7, `$this$runningFoldIndexed`[index]);
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <S, T : S> Array<out T>.runningReduce(operation: (S, T) -> S): List<S> {
      if (`$this$runningReduce`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var8: Any = `$this$runningReduce`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduce`.length);
         index.add(var8);
         val result: ArrayList = index;
         var var9: Int = 1;

         for (int $this$runningReduce_u24lambda_u240 = $this$runningReduce.length; index < $this$runningReduce_u24lambda_u240; index++) {
            var8 = operation.invoke(var8, `$this$runningReduce`[var9]);
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.runningReduce(operation: (Byte, Byte) -> Byte): List<Byte> {
      if (`$this$runningReduce`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Byte = `$this$runningReduce`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduce`.length);
         index.add(var7);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduce_u24lambda_u241 = $this$runningReduce.length; index < $this$runningReduce_u24lambda_u241; index++) {
            var7 = (operation.invoke(var7, `$this$runningReduce`[var8]) as java.lang.Number).byteValue();
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.runningReduce(operation: (Short, Short) -> Short): List<Short> {
      if (`$this$runningReduce`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Short = `$this$runningReduce`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduce`.length);
         index.add(var7);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduce_u24lambda_u242 = $this$runningReduce.length; index < $this$runningReduce_u24lambda_u242; index++) {
            var7 = (operation.invoke(var7, `$this$runningReduce`[var8]) as java.lang.Number).shortValue();
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.runningReduce(operation: (Int, Int) -> Int): List<Int> {
      if (`$this$runningReduce`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Int = `$this$runningReduce`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduce`.length);
         index.add(var7);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduce_u24lambda_u243 = $this$runningReduce.length; index < $this$runningReduce_u24lambda_u243; index++) {
            var7 = (operation.invoke(var7, `$this$runningReduce`[var8]) as java.lang.Number).intValue();
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.runningReduce(operation: (Long, Long) -> Long): List<Long> {
      if (`$this$runningReduce`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var9: Long = `$this$runningReduce`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduce`.length);
         index.add(var9);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduce_u24lambda_u244 = $this$runningReduce.length; index < $this$runningReduce_u24lambda_u244; index++) {
            var9 = (operation.invoke(var9, `$this$runningReduce`[var8]) as java.lang.Number).longValue();
            result.add(var9);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.runningReduce(operation: (Float, Float) -> Float): List<Float> {
      if (`$this$runningReduce`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Float = `$this$runningReduce`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduce`.length);
         index.add(var7);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduce_u24lambda_u245 = $this$runningReduce.length; index < $this$runningReduce_u24lambda_u245; index++) {
            var7 = (operation.invoke(var7, `$this$runningReduce`[var8]) as java.lang.Number).floatValue();
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.runningReduce(operation: (Double, Double) -> Double): List<Double> {
      if (`$this$runningReduce`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var9: Double = `$this$runningReduce`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduce`.length);
         index.add(var9);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduce_u24lambda_u246 = $this$runningReduce.length; index < $this$runningReduce_u24lambda_u246; index++) {
            var9 = (operation.invoke(var9, `$this$runningReduce`[var8]) as java.lang.Number).doubleValue();
            result.add(var9);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.runningReduce(operation: (Boolean, Boolean) -> Boolean): List<Boolean> {
      if (`$this$runningReduce`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Boolean = `$this$runningReduce`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduce`.length);
         index.add(var7);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduce_u24lambda_u247 = $this$runningReduce.length; index < $this$runningReduce_u24lambda_u247; index++) {
            var7 = operation.invoke(var7, `$this$runningReduce`[var8]) as java.lang.Boolean;
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.runningReduce(operation: (Char, Char) -> Char): List<Char> {
      if (`$this$runningReduce`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Char = `$this$runningReduce`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduce`.length);
         index.add(var7);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduce_u24lambda_u248 = $this$runningReduce.length; index < $this$runningReduce_u24lambda_u248; index++) {
            var7 = operation.invoke(var7, `$this$runningReduce`[var8]) as Character;
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <S, T : S> Array<out T>.runningReduceIndexed(operation: (Int, S, T) -> S): List<S> {
      if (`$this$runningReduceIndexed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var8: Any = `$this$runningReduceIndexed`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduceIndexed`.length);
         index.add(var8);
         val result: ArrayList = index;
         var var9: Int = 1;

         for (int $this$runningReduceIndexed_u24lambda_u240 = $this$runningReduceIndexed.length; index < $this$runningReduceIndexed_u24lambda_u240; index++) {
            var8 = operation.invoke(var9, var8, `$this$runningReduceIndexed`[var9]);
            result.add(var8);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.runningReduceIndexed(operation: (Int, Byte, Byte) -> Byte): List<Byte> {
      if (`$this$runningReduceIndexed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Byte = `$this$runningReduceIndexed`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduceIndexed`.length);
         index.add(var7);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduceIndexed_u24lambda_u241 = $this$runningReduceIndexed.length; index < $this$runningReduceIndexed_u24lambda_u241; index++) {
            var7 = (operation.invoke(var8, var7, `$this$runningReduceIndexed`[var8]) as java.lang.Number).byteValue();
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.runningReduceIndexed(operation: (Int, Short, Short) -> Short): List<Short> {
      if (`$this$runningReduceIndexed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Short = `$this$runningReduceIndexed`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduceIndexed`.length);
         index.add(var7);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduceIndexed_u24lambda_u242 = $this$runningReduceIndexed.length; index < $this$runningReduceIndexed_u24lambda_u242; index++) {
            var7 = (operation.invoke(var8, var7, `$this$runningReduceIndexed`[var8]) as java.lang.Number).shortValue();
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.runningReduceIndexed(operation: (Int, Int, Int) -> Int): List<Int> {
      if (`$this$runningReduceIndexed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Int = `$this$runningReduceIndexed`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduceIndexed`.length);
         index.add(var7);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduceIndexed_u24lambda_u243 = $this$runningReduceIndexed.length; index < $this$runningReduceIndexed_u24lambda_u243; index++) {
            var7 = (operation.invoke(var8, var7, `$this$runningReduceIndexed`[var8]) as java.lang.Number).intValue();
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.runningReduceIndexed(operation: (Int, Long, Long) -> Long): List<Long> {
      if (`$this$runningReduceIndexed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var9: Long = `$this$runningReduceIndexed`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduceIndexed`.length);
         index.add(var9);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduceIndexed_u24lambda_u244 = $this$runningReduceIndexed.length; index < $this$runningReduceIndexed_u24lambda_u244; index++) {
            var9 = (operation.invoke(var8, var9, `$this$runningReduceIndexed`[var8]) as java.lang.Number).longValue();
            result.add(var9);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.runningReduceIndexed(operation: (Int, Float, Float) -> Float): List<Float> {
      if (`$this$runningReduceIndexed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Float = `$this$runningReduceIndexed`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduceIndexed`.length);
         index.add(var7);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduceIndexed_u24lambda_u245 = $this$runningReduceIndexed.length; index < $this$runningReduceIndexed_u24lambda_u245; index++) {
            var7 = (operation.invoke(var8, var7, `$this$runningReduceIndexed`[var8]) as java.lang.Number).floatValue();
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.runningReduceIndexed(operation: (Int, Double, Double) -> Double): List<Double> {
      if (`$this$runningReduceIndexed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var9: Double = `$this$runningReduceIndexed`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduceIndexed`.length);
         index.add(var9);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduceIndexed_u24lambda_u246 = $this$runningReduceIndexed.length; index < $this$runningReduceIndexed_u24lambda_u246; index++) {
            var9 = (operation.invoke(var8, var9, `$this$runningReduceIndexed`[var8]) as java.lang.Number).doubleValue();
            result.add(var9);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.runningReduceIndexed(operation: (Int, Boolean, Boolean) -> Boolean): List<Boolean> {
      if (`$this$runningReduceIndexed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Boolean = `$this$runningReduceIndexed`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduceIndexed`.length);
         index.add(var7);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduceIndexed_u24lambda_u247 = $this$runningReduceIndexed.length; index < $this$runningReduceIndexed_u24lambda_u247; index++) {
            var7 = operation.invoke(var8, var7, `$this$runningReduceIndexed`[var8]) as java.lang.Boolean;
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.runningReduceIndexed(operation: (Int, Char, Char) -> Char): List<Char> {
      if (`$this$runningReduceIndexed`.length == 0) {
         return CollectionsKt.emptyList();
      } else {
         var var7: Char = `$this$runningReduceIndexed`[0];
         val index: ArrayList = new ArrayList(`$this$runningReduceIndexed`.length);
         index.add(var7);
         val result: ArrayList = index;
         var var8: Int = 1;

         for (int $this$runningReduceIndexed_u24lambda_u248 = $this$runningReduceIndexed.length; index < $this$runningReduceIndexed_u24lambda_u248; index++) {
            var7 = operation.invoke(var8, var7, `$this$runningReduceIndexed`[var8]) as Character;
            result.add(var7);
         }

         return result;
      }
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <T, R> Array<out T>.scan(initial: R, operation: (R, T) -> R): List<R> {
      val var10000: java.util.List;
      if (`$this$scan`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val `accumulator$iv`: ArrayList = new ArrayList(`$this$scan`.length + 1);
         `accumulator$iv`.add(initial);
         val `result$iv`: ArrayList = `accumulator$iv`;
         var var12: Any = initial;

         for (Object element$iv : $this$scan) {
            var12 = operation.invoke(var12, `element$iv`);
            `result$iv`.add(var12);
         }

         var10000 = `result$iv`;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> ByteArray.scan(initial: R, operation: (R, Byte) -> R): List<R> {
      val var10000: java.util.List;
      if (`$this$scan`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scan`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var9: Any = initial;

         for (byte var8 : $this$scan) {
            var9 = operation.invoke(var9, var8);
            var6.add(var9);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> ShortArray.scan(initial: R, operation: (R, Short) -> R): List<R> {
      val var10000: java.util.List;
      if (`$this$scan`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scan`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var9: Any = initial;

         for (short var8 : $this$scan) {
            var9 = operation.invoke(var9, var8);
            var6.add(var9);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> IntArray.scan(initial: R, operation: (R, Int) -> R): List<R> {
      val var10000: java.util.List;
      if (`$this$scan`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scan`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var9: Any = initial;

         for (int var8 : $this$scan) {
            var9 = operation.invoke(var9, var8);
            var6.add(var9);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> LongArray.scan(initial: R, operation: (R, Long) -> R): List<R> {
      val var10000: java.util.List;
      if (`$this$scan`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scan`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var10: Any = initial;

         for (long var8 : $this$scan) {
            var10 = operation.invoke(var10, var8);
            var6.add(var10);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> FloatArray.scan(initial: R, operation: (R, Float) -> R): List<R> {
      val var10000: java.util.List;
      if (`$this$scan`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scan`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var9: Any = initial;

         for (float var8 : $this$scan) {
            var9 = operation.invoke(var9, var8);
            var6.add(var9);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> DoubleArray.scan(initial: R, operation: (R, Double) -> R): List<R> {
      val var10000: java.util.List;
      if (`$this$scan`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scan`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var10: Any = initial;

         for (double var8 : $this$scan) {
            var10 = operation.invoke(var10, var8);
            var6.add(var10);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> BooleanArray.scan(initial: R, operation: (R, Boolean) -> R): List<R> {
      val var10000: java.util.List;
      if (`$this$scan`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scan`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var9: Any = initial;

         for (boolean var8 : $this$scan) {
            var9 = operation.invoke(var9, var8);
            var6.add(var9);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> CharArray.scan(initial: R, operation: (R, Char) -> R): List<R> {
      val var10000: java.util.List;
      if (`$this$scan`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scan`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var9: Any = initial;

         for (char var8 : $this$scan) {
            var9 = operation.invoke(var9, var8);
            var6.add(var9);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @JvmStatic
   public inline fun <T, R> Array<out T>.scanIndexed(initial: R, operation: (Int, R, T) -> R): List<R> {
      val `$this$runningFoldIndexed$iv`: Array<Any> = `$this$scanIndexed`;
      val var10000: java.util.List;
      if (`$this$scanIndexed`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val `accumulator$iv`: ArrayList = new ArrayList(`$this$scanIndexed`.length + 1);
         `accumulator$iv`.add(initial);
         val `result$iv`: ArrayList = `accumulator$iv`;
         var var11: Any = initial;
         var `index$iv`: Int = 0;

         for (int var12 = $this$scanIndexed.length; index$iv < var12; index$iv++) {
            var11 = operation.invoke(`index$iv`, var11, `$this$runningFoldIndexed$iv`[`index$iv`]);
            `result$iv`.add(var11);
         }

         var10000 = `result$iv`;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> ByteArray.scanIndexed(initial: R, operation: (Int, R, Byte) -> R): List<R> {
      val var3: ByteArray = `$this$scanIndexed`;
      val var10000: java.util.List;
      if (`$this$scanIndexed`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scanIndexed`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var8: Any = initial;
         var var5: Int = 0;

         for (int var7 = $this$scanIndexed.length; var5 < var7; var5++) {
            var8 = operation.invoke(var5, var8, var3[var5]);
            var6.add(var8);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> ShortArray.scanIndexed(initial: R, operation: (Int, R, Short) -> R): List<R> {
      val var3: ShortArray = `$this$scanIndexed`;
      val var10000: java.util.List;
      if (`$this$scanIndexed`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scanIndexed`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var8: Any = initial;
         var var5: Int = 0;

         for (int var7 = $this$scanIndexed.length; var5 < var7; var5++) {
            var8 = operation.invoke(var5, var8, var3[var5]);
            var6.add(var8);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> IntArray.scanIndexed(initial: R, operation: (Int, R, Int) -> R): List<R> {
      val var3: IntArray = `$this$scanIndexed`;
      val var10000: java.util.List;
      if (`$this$scanIndexed`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scanIndexed`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var8: Any = initial;
         var var5: Int = 0;

         for (int var7 = $this$scanIndexed.length; var5 < var7; var5++) {
            var8 = operation.invoke(var5, var8, var3[var5]);
            var6.add(var8);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> LongArray.scanIndexed(initial: R, operation: (Int, R, Long) -> R): List<R> {
      val var3: LongArray = `$this$scanIndexed`;
      val var10000: java.util.List;
      if (`$this$scanIndexed`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scanIndexed`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var8: Any = initial;
         var var5: Int = 0;

         for (int var7 = $this$scanIndexed.length; var5 < var7; var5++) {
            var8 = operation.invoke(var5, var8, var3[var5]);
            var6.add(var8);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> FloatArray.scanIndexed(initial: R, operation: (Int, R, Float) -> R): List<R> {
      val var3: FloatArray = `$this$scanIndexed`;
      val var10000: java.util.List;
      if (`$this$scanIndexed`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scanIndexed`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var8: Any = initial;
         var var5: Int = 0;

         for (int var7 = $this$scanIndexed.length; var5 < var7; var5++) {
            var8 = operation.invoke(var5, var8, var3[var5]);
            var6.add(var8);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> DoubleArray.scanIndexed(initial: R, operation: (Int, R, Double) -> R): List<R> {
      val var3: DoubleArray = `$this$scanIndexed`;
      val var10000: java.util.List;
      if (`$this$scanIndexed`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scanIndexed`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var8: Any = initial;
         var var5: Int = 0;

         for (int var7 = $this$scanIndexed.length; var5 < var7; var5++) {
            var8 = operation.invoke(var5, var8, var3[var5]);
            var6.add(var8);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> BooleanArray.scanIndexed(initial: R, operation: (Int, R, Boolean) -> R): List<R> {
      val var3: BooleanArray = `$this$scanIndexed`;
      val var10000: java.util.List;
      if (`$this$scanIndexed`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scanIndexed`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var8: Any = initial;
         var var5: Int = 0;

         for (int var7 = $this$scanIndexed.length; var5 < var7; var5++) {
            var8 = operation.invoke(var5, var8, var3[var5]);
            var6.add(var8);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @SinceKotlin(version = "1.4")
   @InlineOnly
   @JvmStatic
   public inline fun <R> CharArray.scanIndexed(initial: R, operation: (Int, R, Char) -> R): List<R> {
      val var3: CharArray = `$this$scanIndexed`;
      val var10000: java.util.List;
      if (`$this$scanIndexed`.length == 0) {
         var10000 = CollectionsKt.listOf(initial);
      } else {
         val var4: ArrayList = new ArrayList(`$this$scanIndexed`.length + 1);
         var4.add(initial);
         val var6: ArrayList = var4;
         var var8: Any = initial;
         var var5: Int = 0;

         for (int var7 = $this$scanIndexed.length; var5 < var7; var5++) {
            var8 = operation.invoke(var5, var8, var3[var5]);
            var6.add(var8);
         }

         var10000 = var6;
      }

      return var10000;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun <T> Array<out T>.sumBy(selector: (T) -> Int): Int {
      var sum: Int = 0;

      for (Object element : $this$sumBy) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun ByteArray.sumBy(selector: (Byte) -> Int): Int {
      var sum: Int = 0;

      for (byte element : $this$sumBy) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun ShortArray.sumBy(selector: (Short) -> Int): Int {
      var sum: Int = 0;

      for (short element : $this$sumBy) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun IntArray.sumBy(selector: (Int) -> Int): Int {
      var sum: Int = 0;

      for (int element : $this$sumBy) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun LongArray.sumBy(selector: (Long) -> Int): Int {
      var sum: Int = 0;

      for (long element : $this$sumBy) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun FloatArray.sumBy(selector: (Float) -> Int): Int {
      var sum: Int = 0;

      for (float element : $this$sumBy) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun DoubleArray.sumBy(selector: (Double) -> Int): Int {
      var sum: Int = 0;

      for (double element : $this$sumBy) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun BooleanArray.sumBy(selector: (Boolean) -> Int): Int {
      var sum: Int = 0;

      for (boolean element : $this$sumBy) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun CharArray.sumBy(selector: (Char) -> Int): Int {
      var sum: Int = 0;

      for (char element : $this$sumBy) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun <T> Array<out T>.sumByDouble(selector: (T) -> Double): Double {
      var sum: Double = 0.0;

      for (Object element : $this$sumByDouble) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun ByteArray.sumByDouble(selector: (Byte) -> Double): Double {
      var sum: Double = 0.0;

      for (byte element : $this$sumByDouble) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun ShortArray.sumByDouble(selector: (Short) -> Double): Double {
      var sum: Double = 0.0;

      for (short element : $this$sumByDouble) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun IntArray.sumByDouble(selector: (Int) -> Double): Double {
      var sum: Double = 0.0;

      for (int element : $this$sumByDouble) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun LongArray.sumByDouble(selector: (Long) -> Double): Double {
      var sum: Double = 0.0;

      for (long element : $this$sumByDouble) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun FloatArray.sumByDouble(selector: (Float) -> Double): Double {
      var sum: Double = 0.0;

      for (float element : $this$sumByDouble) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun DoubleArray.sumByDouble(selector: (Double) -> Double): Double {
      var sum: Double = 0.0;

      for (double element : $this$sumByDouble) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun BooleanArray.sumByDouble(selector: (Boolean) -> Double): Double {
      var sum: Double = 0.0;

      for (boolean element : $this$sumByDouble) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @Deprecated(message = "Use sumOf instead.", replaceWith = @ReplaceWith(expression = "this.sumOf(selector)", imports = []))
   @DeprecatedSinceKotlin(warningSince = "1.5")
   @JvmStatic
   public inline fun CharArray.sumByDouble(selector: (Char) -> Double): Double {
      var sum: Double = 0.0;

      for (char element : $this$sumByDouble) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.sumOf(selector: (T) -> Double): Double {
      var sum: Double = 0.0;

      for (Object element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.sumOf(selector: (Byte) -> Double): Double {
      var sum: Double = 0.0;

      for (byte element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.sumOf(selector: (Short) -> Double): Double {
      var sum: Double = 0.0;

      for (short element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.sumOf(selector: (Int) -> Double): Double {
      var sum: Double = 0.0;

      for (int element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.sumOf(selector: (Long) -> Double): Double {
      var sum: Double = 0.0;

      for (long element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.sumOf(selector: (Float) -> Double): Double {
      var sum: Double = 0.0;

      for (float element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.sumOf(selector: (Double) -> Double): Double {
      var sum: Double = 0.0;

      for (double element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.sumOf(selector: (Boolean) -> Double): Double {
      var sum: Double = 0.0;

      for (boolean element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfDouble")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.sumOf(selector: (Char) -> Double): Double {
      var sum: Double = 0.0;

      for (char element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).doubleValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.sumOf(selector: (T) -> Int): Int {
      var sum: Int = 0;

      for (Object element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.sumOf(selector: (Byte) -> Int): Int {
      var sum: Int = 0;

      for (byte element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.sumOf(selector: (Short) -> Int): Int {
      var sum: Int = 0;

      for (short element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.sumOf(selector: (Int) -> Int): Int {
      var sum: Int = 0;

      for (int element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.sumOf(selector: (Long) -> Int): Int {
      var sum: Int = 0;

      for (long element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.sumOf(selector: (Float) -> Int): Int {
      var sum: Int = 0;

      for (float element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.sumOf(selector: (Double) -> Int): Int {
      var sum: Int = 0;

      for (double element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.sumOf(selector: (Boolean) -> Int): Int {
      var sum: Int = 0;

      for (boolean element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @JvmName(name = "sumOfInt")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.sumOf(selector: (Char) -> Int): Int {
      var sum: Int = 0;

      for (char element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).intValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.sumOf(selector: (T) -> Long): Long {
      var sum: Long = 0L;

      for (Object element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.sumOf(selector: (Byte) -> Long): Long {
      var sum: Long = 0L;

      for (byte element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.sumOf(selector: (Short) -> Long): Long {
      var sum: Long = 0L;

      for (short element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.sumOf(selector: (Int) -> Long): Long {
      var sum: Long = 0L;

      for (int element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.sumOf(selector: (Long) -> Long): Long {
      var sum: Long = 0L;

      for (long element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.sumOf(selector: (Float) -> Long): Long {
      var sum: Long = 0L;

      for (float element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.sumOf(selector: (Double) -> Long): Long {
      var sum: Long = 0L;

      for (double element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.sumOf(selector: (Boolean) -> Long): Long {
      var sum: Long = 0L;

      for (boolean element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.4")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfLong")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.sumOf(selector: (Char) -> Long): Long {
      var sum: Long = 0L;

      for (char element : $this$sumOf) {
         sum += (selector.invoke(element) as java.lang.Number).longValue();
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.sumOf(selector: (T) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);

      for (Object element : $this$sumOf) {
         sum = UInt.constructor-impl(sum + (selector.invoke(element) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.sumOf(selector: (Byte) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);

      for (byte element : $this$sumOf) {
         sum = UInt.constructor-impl(sum + (selector.invoke(element) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.sumOf(selector: (Short) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);

      for (short element : $this$sumOf) {
         sum = UInt.constructor-impl(sum + (selector.invoke(element) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.sumOf(selector: (Int) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);

      for (int element : $this$sumOf) {
         sum = UInt.constructor-impl(sum + (selector.invoke(element) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.sumOf(selector: (Long) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);

      for (long element : $this$sumOf) {
         sum = UInt.constructor-impl(sum + (selector.invoke(element) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.sumOf(selector: (Float) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);

      for (float element : $this$sumOf) {
         sum = UInt.constructor-impl(sum + (selector.invoke(element) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.sumOf(selector: (Double) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);

      for (double element : $this$sumOf) {
         sum = UInt.constructor-impl(sum + (selector.invoke(element) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.sumOf(selector: (Boolean) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);

      for (boolean element : $this$sumOf) {
         sum = UInt.constructor-impl(sum + (selector.invoke(element) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @JvmName(name = "sumOfUInt")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.sumOf(selector: (Char) -> UInt): UInt {
      var sum: Int = UInt.constructor-impl(0);

      for (char element : $this$sumOf) {
         sum = UInt.constructor-impl(sum + (selector.invoke(element) as UInt).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @InlineOnly
   @JvmStatic
   public inline fun <T> Array<out T>.sumOf(selector: (T) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);

      for (Object element : $this$sumOf) {
         sum = ULong.constructor-impl(sum + (selector.invoke(element) as ULong).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @InlineOnly
   @JvmStatic
   public inline fun ByteArray.sumOf(selector: (Byte) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);

      for (byte element : $this$sumOf) {
         sum = ULong.constructor-impl(sum + (selector.invoke(element) as ULong).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @InlineOnly
   @JvmStatic
   public inline fun ShortArray.sumOf(selector: (Short) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);

      for (short element : $this$sumOf) {
         sum = ULong.constructor-impl(sum + (selector.invoke(element) as ULong).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @InlineOnly
   @JvmStatic
   public inline fun IntArray.sumOf(selector: (Int) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);

      for (int element : $this$sumOf) {
         sum = ULong.constructor-impl(sum + (selector.invoke(element) as ULong).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @InlineOnly
   @JvmStatic
   public inline fun LongArray.sumOf(selector: (Long) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);

      for (long element : $this$sumOf) {
         sum = ULong.constructor-impl(sum + (selector.invoke(element) as ULong).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @InlineOnly
   @JvmStatic
   public inline fun FloatArray.sumOf(selector: (Float) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);

      for (float element : $this$sumOf) {
         sum = ULong.constructor-impl(sum + (selector.invoke(element) as ULong).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @InlineOnly
   @JvmStatic
   public inline fun DoubleArray.sumOf(selector: (Double) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);

      for (double element : $this$sumOf) {
         sum = ULong.constructor-impl(sum + (selector.invoke(element) as ULong).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @InlineOnly
   @JvmStatic
   public inline fun BooleanArray.sumOf(selector: (Boolean) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);

      for (boolean element : $this$sumOf) {
         sum = ULong.constructor-impl(sum + (selector.invoke(element) as ULong).unbox-impl());
      }

      return sum;
   }

   @SinceKotlin(version = "1.5")
   @OverloadResolutionByLambdaReturnType
   @JvmName(name = "sumOfULong")
   @InlineOnly
   @JvmStatic
   public inline fun CharArray.sumOf(selector: (Char) -> ULong): ULong {
      var sum: Long = ULong.constructor-impl(0L);

      for (char element : $this$sumOf) {
         sum = ULong.constructor-impl(sum + (selector.invoke(element) as ULong).unbox-impl());
      }

      return sum;
   }

   @JvmStatic
   public fun <T : Any> Array<T?>.requireNoNulls(): Array<T> {
      for (Object element : $this$requireNoNulls) {
         if (element == null) {
            throw new IllegalArgumentException("null element found in $`$this$requireNoNulls`.");
         }
      }

      return (T[])`$this$requireNoNulls`;
   }

   @JvmStatic
   public inline fun <T> Array<out T>.partition(predicate: (T) -> Boolean): Pair<List<T>, List<T>> {
      val first: ArrayList = new ArrayList();
      val second: ArrayList = new ArrayList();

      for (Object element : $this$partition) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            first.add(element);
         } else {
            second.add(element);
         }
      }

      return new Pair<>(first, second);
   }

   @JvmStatic
   public inline fun ByteArray.partition(predicate: (Byte) -> Boolean): Pair<List<Byte>, List<Byte>> {
      val first: ArrayList = new ArrayList();
      val second: ArrayList = new ArrayList();

      for (byte element : $this$partition) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            first.add(element);
         } else {
            second.add(element);
         }
      }

      return new Pair<>(first, second);
   }

   @JvmStatic
   public inline fun ShortArray.partition(predicate: (Short) -> Boolean): Pair<List<Short>, List<Short>> {
      val first: ArrayList = new ArrayList();
      val second: ArrayList = new ArrayList();

      for (short element : $this$partition) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            first.add(element);
         } else {
            second.add(element);
         }
      }

      return new Pair<>(first, second);
   }

   @JvmStatic
   public inline fun IntArray.partition(predicate: (Int) -> Boolean): Pair<List<Int>, List<Int>> {
      val first: ArrayList = new ArrayList();
      val second: ArrayList = new ArrayList();

      for (int element : $this$partition) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            first.add(element);
         } else {
            second.add(element);
         }
      }

      return new Pair<>(first, second);
   }

   @JvmStatic
   public inline fun LongArray.partition(predicate: (Long) -> Boolean): Pair<List<Long>, List<Long>> {
      val first: ArrayList = new ArrayList();
      val second: ArrayList = new ArrayList();

      for (long element : $this$partition) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            first.add(element);
         } else {
            second.add(element);
         }
      }

      return new Pair<>(first, second);
   }

   @JvmStatic
   public inline fun FloatArray.partition(predicate: (Float) -> Boolean): Pair<List<Float>, List<Float>> {
      val first: ArrayList = new ArrayList();
      val second: ArrayList = new ArrayList();

      for (float element : $this$partition) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            first.add(element);
         } else {
            second.add(element);
         }
      }

      return new Pair<>(first, second);
   }

   @JvmStatic
   public inline fun DoubleArray.partition(predicate: (Double) -> Boolean): Pair<List<Double>, List<Double>> {
      val first: ArrayList = new ArrayList();
      val second: ArrayList = new ArrayList();

      for (double element : $this$partition) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            first.add(element);
         } else {
            second.add(element);
         }
      }

      return new Pair<>(first, second);
   }

   @JvmStatic
   public inline fun BooleanArray.partition(predicate: (Boolean) -> Boolean): Pair<List<Boolean>, List<Boolean>> {
      val first: ArrayList = new ArrayList();
      val second: ArrayList = new ArrayList();

      for (boolean element : $this$partition) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            first.add(element);
         } else {
            second.add(element);
         }
      }

      return new Pair<>(first, second);
   }

   @JvmStatic
   public inline fun CharArray.partition(predicate: (Char) -> Boolean): Pair<List<Char>, List<Char>> {
      val first: ArrayList = new ArrayList();
      val second: ArrayList = new ArrayList();

      for (char element : $this$partition) {
         if (predicate.invoke(element) as java.lang.Boolean) {
            first.add(element);
         } else {
            second.add(element);
         }
      }

      return new Pair<>(first, second);
   }

   @JvmStatic
   public infix fun <T, R> Array<out T>.zip(other: Array<out R>): List<Pair<T, R>> {
      val `$this$zip$iv`: Array<Any> = `$this$zip`;
      val `other$iv`: Array<Any> = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> ByteArray.zip(other: Array<out R>): List<Pair<Byte, R>> {
      val `$this$zip$iv`: ByteArray = `$this$zip`;
      val `other$iv`: Array<Any> = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> ShortArray.zip(other: Array<out R>): List<Pair<Short, R>> {
      val `$this$zip$iv`: ShortArray = `$this$zip`;
      val `other$iv`: Array<Any> = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> IntArray.zip(other: Array<out R>): List<Pair<Int, R>> {
      val `$this$zip$iv`: IntArray = `$this$zip`;
      val `other$iv`: Array<Any> = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> LongArray.zip(other: Array<out R>): List<Pair<Long, R>> {
      val `$this$zip$iv`: LongArray = `$this$zip`;
      val `other$iv`: Array<Any> = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> FloatArray.zip(other: Array<out R>): List<Pair<Float, R>> {
      val `$this$zip$iv`: FloatArray = `$this$zip`;
      val `other$iv`: Array<Any> = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> DoubleArray.zip(other: Array<out R>): List<Pair<Double, R>> {
      val `$this$zip$iv`: DoubleArray = `$this$zip`;
      val `other$iv`: Array<Any> = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> BooleanArray.zip(other: Array<out R>): List<Pair<Boolean, R>> {
      val `$this$zip$iv`: BooleanArray = `$this$zip`;
      val `other$iv`: Array<Any> = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> CharArray.zip(other: Array<out R>): List<Pair<Char, R>> {
      val `$this$zip$iv`: CharArray = `$this$zip`;
      val `other$iv`: Array<Any> = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public inline fun <T, R, V> Array<out T>.zip(other: Array<out R>, transform: (T, R) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> ByteArray.zip(other: Array<out R>, transform: (Byte, R) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> ShortArray.zip(other: Array<out R>, transform: (Short, R) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> IntArray.zip(other: Array<out R>, transform: (Int, R) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> LongArray.zip(other: Array<out R>, transform: (Long, R) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> FloatArray.zip(other: Array<out R>, transform: (Float, R) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> DoubleArray.zip(other: Array<out R>, transform: (Double, R) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> BooleanArray.zip(other: Array<out R>, transform: (Boolean, R) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> CharArray.zip(other: Array<out R>, transform: (Char, R) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public infix fun <T, R> Array<out T>.zip(other: Iterable<R>): List<Pair<T, R>> {
      val `$this$zip$iv`: Array<Any> = `$this$zip`;
      val `arraySize$iv`: Int = `$this$zip`.length;
      val `list$iv`: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var `i$iv`: Int = 0;

      for (Object element$iv : other) {
         if (`i$iv` >= `arraySize$iv`) {
            break;
         }

         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`++], `element$iv`));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> ByteArray.zip(other: Iterable<R>): List<Pair<Byte, R>> {
      val `$this$zip$iv`: ByteArray = `$this$zip`;
      val `arraySize$iv`: Int = `$this$zip`.length;
      val `list$iv`: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var `i$iv`: Int = 0;

      for (Object element$iv : other) {
         if (`i$iv` >= `arraySize$iv`) {
            break;
         }

         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`++], `element$iv`));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> ShortArray.zip(other: Iterable<R>): List<Pair<Short, R>> {
      val `$this$zip$iv`: ShortArray = `$this$zip`;
      val `arraySize$iv`: Int = `$this$zip`.length;
      val `list$iv`: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var `i$iv`: Int = 0;

      for (Object element$iv : other) {
         if (`i$iv` >= `arraySize$iv`) {
            break;
         }

         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`++], `element$iv`));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> IntArray.zip(other: Iterable<R>): List<Pair<Int, R>> {
      val `$this$zip$iv`: IntArray = `$this$zip`;
      val `arraySize$iv`: Int = `$this$zip`.length;
      val `list$iv`: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var `i$iv`: Int = 0;

      for (Object element$iv : other) {
         if (`i$iv` >= `arraySize$iv`) {
            break;
         }

         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`++], `element$iv`));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> LongArray.zip(other: Iterable<R>): List<Pair<Long, R>> {
      val `$this$zip$iv`: LongArray = `$this$zip`;
      val `arraySize$iv`: Int = `$this$zip`.length;
      val `list$iv`: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var `i$iv`: Int = 0;

      for (Object element$iv : other) {
         if (`i$iv` >= `arraySize$iv`) {
            break;
         }

         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`++], `element$iv`));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> FloatArray.zip(other: Iterable<R>): List<Pair<Float, R>> {
      val `$this$zip$iv`: FloatArray = `$this$zip`;
      val `arraySize$iv`: Int = `$this$zip`.length;
      val `list$iv`: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var `i$iv`: Int = 0;

      for (Object element$iv : other) {
         if (`i$iv` >= `arraySize$iv`) {
            break;
         }

         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`++], `element$iv`));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> DoubleArray.zip(other: Iterable<R>): List<Pair<Double, R>> {
      val `$this$zip$iv`: DoubleArray = `$this$zip`;
      val `arraySize$iv`: Int = `$this$zip`.length;
      val `list$iv`: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var `i$iv`: Int = 0;

      for (Object element$iv : other) {
         if (`i$iv` >= `arraySize$iv`) {
            break;
         }

         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`++], `element$iv`));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> BooleanArray.zip(other: Iterable<R>): List<Pair<Boolean, R>> {
      val `$this$zip$iv`: BooleanArray = `$this$zip`;
      val `arraySize$iv`: Int = `$this$zip`.length;
      val `list$iv`: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var `i$iv`: Int = 0;

      for (Object element$iv : other) {
         if (`i$iv` >= `arraySize$iv`) {
            break;
         }

         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`++], `element$iv`));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun <R> CharArray.zip(other: Iterable<R>): List<Pair<Char, R>> {
      val `$this$zip$iv`: CharArray = `$this$zip`;
      val `arraySize$iv`: Int = `$this$zip`.length;
      val `list$iv`: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var `i$iv`: Int = 0;

      for (Object element$iv : other) {
         if (`i$iv` >= `arraySize$iv`) {
            break;
         }

         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`++], `element$iv`));
      }

      return `list$iv`;
   }

   @JvmStatic
   public inline fun <T, R, V> Array<out T>.zip(other: Iterable<R>, transform: (T, R) -> V): List<V> {
      val arraySize: Int = `$this$zip`.length;
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var i: Int = 0;

      for (Object element : other) {
         if (i >= arraySize) {
            break;
         }

         list.add(transform.invoke(`$this$zip`[i++], element));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> ByteArray.zip(other: Iterable<R>, transform: (Byte, R) -> V): List<V> {
      val arraySize: Int = `$this$zip`.length;
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var i: Int = 0;

      for (Object element : other) {
         if (i >= arraySize) {
            break;
         }

         list.add(transform.invoke(`$this$zip`[i++], element));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> ShortArray.zip(other: Iterable<R>, transform: (Short, R) -> V): List<V> {
      val arraySize: Int = `$this$zip`.length;
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var i: Int = 0;

      for (Object element : other) {
         if (i >= arraySize) {
            break;
         }

         list.add(transform.invoke(`$this$zip`[i++], element));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> IntArray.zip(other: Iterable<R>, transform: (Int, R) -> V): List<V> {
      val arraySize: Int = `$this$zip`.length;
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var i: Int = 0;

      for (Object element : other) {
         if (i >= arraySize) {
            break;
         }

         list.add(transform.invoke(`$this$zip`[i++], element));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> LongArray.zip(other: Iterable<R>, transform: (Long, R) -> V): List<V> {
      val arraySize: Int = `$this$zip`.length;
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var i: Int = 0;

      for (Object element : other) {
         if (i >= arraySize) {
            break;
         }

         list.add(transform.invoke(`$this$zip`[i++], element));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> FloatArray.zip(other: Iterable<R>, transform: (Float, R) -> V): List<V> {
      val arraySize: Int = `$this$zip`.length;
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var i: Int = 0;

      for (Object element : other) {
         if (i >= arraySize) {
            break;
         }

         list.add(transform.invoke(`$this$zip`[i++], element));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> DoubleArray.zip(other: Iterable<R>, transform: (Double, R) -> V): List<V> {
      val arraySize: Int = `$this$zip`.length;
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var i: Int = 0;

      for (Object element : other) {
         if (i >= arraySize) {
            break;
         }

         list.add(transform.invoke(`$this$zip`[i++], element));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> BooleanArray.zip(other: Iterable<R>, transform: (Boolean, R) -> V): List<V> {
      val arraySize: Int = `$this$zip`.length;
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var i: Int = 0;

      for (Object element : other) {
         if (i >= arraySize) {
            break;
         }

         list.add(transform.invoke(`$this$zip`[i++], element));
      }

      return list;
   }

   @JvmStatic
   public inline fun <R, V> CharArray.zip(other: Iterable<R>, transform: (Char, R) -> V): List<V> {
      val arraySize: Int = `$this$zip`.length;
      val list: ArrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(other, 10), `$this$zip`.length));
      var i: Int = 0;

      for (Object element : other) {
         if (i >= arraySize) {
            break;
         }

         list.add(transform.invoke(`$this$zip`[i++], element));
      }

      return list;
   }

   @JvmStatic
   public infix fun ByteArray.zip(other: ByteArray): List<Pair<Byte, Byte>> {
      val `$this$zip$iv`: ByteArray = `$this$zip`;
      val `other$iv`: ByteArray = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun ShortArray.zip(other: ShortArray): List<Pair<Short, Short>> {
      val `$this$zip$iv`: ShortArray = `$this$zip`;
      val `other$iv`: ShortArray = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun IntArray.zip(other: IntArray): List<Pair<Int, Int>> {
      val `$this$zip$iv`: IntArray = `$this$zip`;
      val `other$iv`: IntArray = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun LongArray.zip(other: LongArray): List<Pair<Long, Long>> {
      val `$this$zip$iv`: LongArray = `$this$zip`;
      val `other$iv`: LongArray = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun FloatArray.zip(other: FloatArray): List<Pair<Float, Float>> {
      val `$this$zip$iv`: FloatArray = `$this$zip`;
      val `other$iv`: FloatArray = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun DoubleArray.zip(other: DoubleArray): List<Pair<Double, Double>> {
      val `$this$zip$iv`: DoubleArray = `$this$zip`;
      val `other$iv`: DoubleArray = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun BooleanArray.zip(other: BooleanArray): List<Pair<Boolean, Boolean>> {
      val `$this$zip$iv`: BooleanArray = `$this$zip`;
      val `other$iv`: BooleanArray = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public infix fun CharArray.zip(other: CharArray): List<Pair<Char, Char>> {
      val `$this$zip$iv`: CharArray = `$this$zip`;
      val `other$iv`: CharArray = other;
      val `size$iv`: Int = Math.min(`$this$zip`.length, other.length);
      val `list$iv`: ArrayList = new ArrayList(`size$iv`);

      for (int i$iv = 0; i$iv < size$iv; i$iv++) {
         `list$iv`.add(TuplesKt.to(`$this$zip$iv`[`i$iv`], `other$iv`[`i$iv`]));
      }

      return `list$iv`;
   }

   @JvmStatic
   public inline fun <V> ByteArray.zip(other: ByteArray, transform: (Byte, Byte) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <V> ShortArray.zip(other: ShortArray, transform: (Short, Short) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <V> IntArray.zip(other: IntArray, transform: (Int, Int) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <V> LongArray.zip(other: LongArray, transform: (Long, Long) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <V> FloatArray.zip(other: FloatArray, transform: (Float, Float) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <V> DoubleArray.zip(other: DoubleArray, transform: (Double, Double) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <V> BooleanArray.zip(other: BooleanArray, transform: (Boolean, Boolean) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public inline fun <V> CharArray.zip(other: CharArray, transform: (Char, Char) -> V): List<V> {
      val size: Int = Math.min(`$this$zip`.length, other.length);
      val list: ArrayList = new ArrayList(size);

      for (int i = 0; i < size; i++) {
         list.add(transform.invoke(`$this$zip`[i], other[i]));
      }

      return list;
   }

   @JvmStatic
   public fun <T, A : Appendable> Array<out T>.joinTo(
      buffer: A,
      separator: CharSequence = ...,
      prefix: CharSequence = ...,
      postfix: CharSequence = ...,
      limit: Int = ...,
      truncated: CharSequence = ...,
      transform: ((T) -> CharSequence)? = ...
   ): A {
      buffer.append(prefix);
      val count: Int = 0;

      for (Object element : $this$joinTo) {
         if (++count > 1) {
            buffer.append(separator);
         }

         if (limit >= 0 && count > limit) {
            break;
         }

         StringsKt.appendElement(buffer, element, transform);
      }

      if (limit >= 0 && count > limit) {
         buffer.append(truncated);
      }

      buffer.append(postfix);
      return (A)buffer;
   }

   @JvmStatic
   public fun <A : Appendable> ByteArray.joinTo(
      buffer: A,
      separator: CharSequence = ...,
      prefix: CharSequence = ...,
      postfix: CharSequence = ...,
      limit: Int = ...,
      truncated: CharSequence = ...,
      transform: ((Byte) -> CharSequence)? = ...
   ): A {
      buffer.append(prefix);
      val count: Int = 0;

      for (byte element : $this$joinTo) {
         if (++count > 1) {
            buffer.append(separator);
         }

         if (limit >= 0 && count > limit) {
            break;
         }

         if (transform != null) {
            buffer.append(transform.invoke(element) as java.lang.CharSequence);
         } else {
            buffer.append(java.lang.String.valueOf((int)element));
         }
      }

      if (limit >= 0 && count > limit) {
         buffer.append(truncated);
      }

      buffer.append(postfix);
      return (A)buffer;
   }

   @JvmStatic
   public fun <A : Appendable> ShortArray.joinTo(
      buffer: A,
      separator: CharSequence = ...,
      prefix: CharSequence = ...,
      postfix: CharSequence = ...,
      limit: Int = ...,
      truncated: CharSequence = ...,
      transform: ((Short) -> CharSequence)? = ...
   ): A {
      buffer.append(prefix);
      val count: Int = 0;

      for (short element : $this$joinTo) {
         if (++count > 1) {
            buffer.append(separator);
         }

         if (limit >= 0 && count > limit) {
            break;
         }

         if (transform != null) {
            buffer.append(transform.invoke(element) as java.lang.CharSequence);
         } else {
            buffer.append(java.lang.String.valueOf((int)element));
         }
      }

      if (limit >= 0 && count > limit) {
         buffer.append(truncated);
      }

      buffer.append(postfix);
      return (A)buffer;
   }

   @JvmStatic
   public fun <A : Appendable> IntArray.joinTo(
      buffer: A,
      separator: CharSequence = ...,
      prefix: CharSequence = ...,
      postfix: CharSequence = ...,
      limit: Int = ...,
      truncated: CharSequence = ...,
      transform: ((Int) -> CharSequence)? = ...
   ): A {
      buffer.append(prefix);
      val count: Int = 0;

      for (int element : $this$joinTo) {
         if (++count > 1) {
            buffer.append(separator);
         }

         if (limit >= 0 && count > limit) {
            break;
         }

         if (transform != null) {
            buffer.append(transform.invoke(element) as java.lang.CharSequence);
         } else {
            buffer.append(java.lang.String.valueOf(element));
         }
      }

      if (limit >= 0 && count > limit) {
         buffer.append(truncated);
      }

      buffer.append(postfix);
      return (A)buffer;
   }

   @JvmStatic
   public fun <A : Appendable> LongArray.joinTo(
      buffer: A,
      separator: CharSequence = ...,
      prefix: CharSequence = ...,
      postfix: CharSequence = ...,
      limit: Int = ...,
      truncated: CharSequence = ...,
      transform: ((Long) -> CharSequence)? = ...
   ): A {
      buffer.append(prefix);
      val count: Int = 0;

      for (long element : $this$joinTo) {
         if (++count > 1) {
            buffer.append(separator);
         }

         if (limit >= 0 && count > limit) {
            break;
         }

         if (transform != null) {
            buffer.append(transform.invoke(element) as java.lang.CharSequence);
         } else {
            buffer.append(java.lang.String.valueOf(element));
         }
      }

      if (limit >= 0 && count > limit) {
         buffer.append(truncated);
      }

      buffer.append(postfix);
      return (A)buffer;
   }

   @JvmStatic
   public fun <A : Appendable> FloatArray.joinTo(
      buffer: A,
      separator: CharSequence = ...,
      prefix: CharSequence = ...,
      postfix: CharSequence = ...,
      limit: Int = ...,
      truncated: CharSequence = ...,
      transform: ((Float) -> CharSequence)? = ...
   ): A {
      buffer.append(prefix);
      val count: Int = 0;

      for (float element : $this$joinTo) {
         if (++count > 1) {
            buffer.append(separator);
         }

         if (limit >= 0 && count > limit) {
            break;
         }

         if (transform != null) {
            buffer.append(transform.invoke(element) as java.lang.CharSequence);
         } else {
            buffer.append(java.lang.String.valueOf(element));
         }
      }

      if (limit >= 0 && count > limit) {
         buffer.append(truncated);
      }

      buffer.append(postfix);
      return (A)buffer;
   }

   @JvmStatic
   public fun <A : Appendable> DoubleArray.joinTo(
      buffer: A,
      separator: CharSequence = ...,
      prefix: CharSequence = ...,
      postfix: CharSequence = ...,
      limit: Int = ...,
      truncated: CharSequence = ...,
      transform: ((Double) -> CharSequence)? = ...
   ): A {
      buffer.append(prefix);
      val count: Int = 0;

      for (double element : $this$joinTo) {
         if (++count > 1) {
            buffer.append(separator);
         }

         if (limit >= 0 && count > limit) {
            break;
         }

         if (transform != null) {
            buffer.append(transform.invoke(element) as java.lang.CharSequence);
         } else {
            buffer.append(java.lang.String.valueOf(element));
         }
      }

      if (limit >= 0 && count > limit) {
         buffer.append(truncated);
      }

      buffer.append(postfix);
      return (A)buffer;
   }

   @JvmStatic
   public fun <A : Appendable> BooleanArray.joinTo(
      buffer: A,
      separator: CharSequence = ...,
      prefix: CharSequence = ...,
      postfix: CharSequence = ...,
      limit: Int = ...,
      truncated: CharSequence = ...,
      transform: ((Boolean) -> CharSequence)? = ...
   ): A {
      buffer.append(prefix);
      val count: Int = 0;

      for (boolean element : $this$joinTo) {
         if (++count > 1) {
            buffer.append(separator);
         }

         if (limit >= 0 && count > limit) {
            break;
         }

         if (transform != null) {
            buffer.append(transform.invoke(element) as java.lang.CharSequence);
         } else {
            buffer.append(java.lang.String.valueOf(element));
         }
      }

      if (limit >= 0 && count > limit) {
         buffer.append(truncated);
      }

      buffer.append(postfix);
      return (A)buffer;
   }

   @JvmStatic
   public fun <A : Appendable> CharArray.joinTo(
      buffer: A,
      separator: CharSequence = ...,
      prefix: CharSequence = ...,
      postfix: CharSequence = ...,
      limit: Int = ...,
      truncated: CharSequence = ...,
      transform: ((Char) -> CharSequence)? = ...
   ): A {
      buffer.append(prefix);
      val count: Int = 0;

      for (char element : $this$joinTo) {
         if (++count > 1) {
            buffer.append(separator);
         }

         if (limit >= 0 && count > limit) {
            break;
         }

         if (transform != null) {
            buffer.append(transform.invoke(element) as java.lang.CharSequence);
         } else {
            buffer.append(element);
         }
      }

      if (limit >= 0 && count > limit) {
         buffer.append(truncated);
      }

      buffer.append(postfix);
      return (A)buffer;
   }

   @JvmStatic
   public fun <T> Array<out T>.joinToString(
      separator: CharSequence = ", " as java.lang.CharSequence,
      prefix: CharSequence = "" as java.lang.CharSequence,
      postfix: CharSequence = "" as java.lang.CharSequence,
      limit: Int = -1,
      truncated: CharSequence = "..." as java.lang.CharSequence,
      transform: ((T) -> CharSequence)? = null
   ): String {
      return ArraysKt.joinTo(`$this$joinToString`, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
   }

   @JvmStatic
   public fun ByteArray.joinToString(
      separator: CharSequence = ", " as java.lang.CharSequence,
      prefix: CharSequence = "" as java.lang.CharSequence,
      postfix: CharSequence = "" as java.lang.CharSequence,
      limit: Int = -1,
      truncated: CharSequence = "..." as java.lang.CharSequence,
      transform: ((Byte) -> CharSequence)? = null
   ): String {
      return ArraysKt.joinTo(`$this$joinToString`, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
   }

   @JvmStatic
   public fun ShortArray.joinToString(
      separator: CharSequence = ", " as java.lang.CharSequence,
      prefix: CharSequence = "" as java.lang.CharSequence,
      postfix: CharSequence = "" as java.lang.CharSequence,
      limit: Int = -1,
      truncated: CharSequence = "..." as java.lang.CharSequence,
      transform: ((Short) -> CharSequence)? = null
   ): String {
      return ArraysKt.joinTo(`$this$joinToString`, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
   }

   @JvmStatic
   public fun IntArray.joinToString(
      separator: CharSequence = ", " as java.lang.CharSequence,
      prefix: CharSequence = "" as java.lang.CharSequence,
      postfix: CharSequence = "" as java.lang.CharSequence,
      limit: Int = -1,
      truncated: CharSequence = "..." as java.lang.CharSequence,
      transform: ((Int) -> CharSequence)? = null
   ): String {
      return ArraysKt.joinTo(`$this$joinToString`, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
   }

   @JvmStatic
   public fun LongArray.joinToString(
      separator: CharSequence = ", " as java.lang.CharSequence,
      prefix: CharSequence = "" as java.lang.CharSequence,
      postfix: CharSequence = "" as java.lang.CharSequence,
      limit: Int = -1,
      truncated: CharSequence = "..." as java.lang.CharSequence,
      transform: ((Long) -> CharSequence)? = null
   ): String {
      return ArraysKt.joinTo(`$this$joinToString`, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
   }

   @JvmStatic
   public fun FloatArray.joinToString(
      separator: CharSequence = ", " as java.lang.CharSequence,
      prefix: CharSequence = "" as java.lang.CharSequence,
      postfix: CharSequence = "" as java.lang.CharSequence,
      limit: Int = -1,
      truncated: CharSequence = "..." as java.lang.CharSequence,
      transform: ((Float) -> CharSequence)? = null
   ): String {
      return ArraysKt.joinTo(`$this$joinToString`, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
   }

   @JvmStatic
   public fun DoubleArray.joinToString(
      separator: CharSequence = ", " as java.lang.CharSequence,
      prefix: CharSequence = "" as java.lang.CharSequence,
      postfix: CharSequence = "" as java.lang.CharSequence,
      limit: Int = -1,
      truncated: CharSequence = "..." as java.lang.CharSequence,
      transform: ((Double) -> CharSequence)? = null
   ): String {
      return ArraysKt.joinTo(`$this$joinToString`, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
   }

   @JvmStatic
   public fun BooleanArray.joinToString(
      separator: CharSequence = ", " as java.lang.CharSequence,
      prefix: CharSequence = "" as java.lang.CharSequence,
      postfix: CharSequence = "" as java.lang.CharSequence,
      limit: Int = -1,
      truncated: CharSequence = "..." as java.lang.CharSequence,
      transform: ((Boolean) -> CharSequence)? = null
   ): String {
      return ArraysKt.joinTo(`$this$joinToString`, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
   }

   @JvmStatic
   public fun CharArray.joinToString(
      separator: CharSequence = ", " as java.lang.CharSequence,
      prefix: CharSequence = "" as java.lang.CharSequence,
      postfix: CharSequence = "" as java.lang.CharSequence,
      limit: Int = -1,
      truncated: CharSequence = "..." as java.lang.CharSequence,
      transform: ((Char) -> CharSequence)? = null
   ): String {
      return ArraysKt.joinTo(`$this$joinToString`, new StringBuilder(), separator, prefix, postfix, limit, truncated, transform).toString();
   }

   @JvmStatic
   public fun <T> Array<out T>.asIterable(): Iterable<T> {
      return (java.lang.Iterable<T>)(if (`$this$asIterable`.length == 0)
         CollectionsKt.emptyList()
         else
         new kotlin.collections.ArraysKt___ArraysKt.asIterable..inlined.Iterable.1(`$this$asIterable`));
   }

   @JvmStatic
   public fun ByteArray.asIterable(): Iterable<Byte> {
      return (java.lang.Iterable<java.lang.Byte>)(if (`$this$asIterable`.length == 0)
         CollectionsKt.emptyList()
         else
         new kotlin.collections.ArraysKt___ArraysKt.asIterable..inlined.Iterable.2(`$this$asIterable`));
   }

   @JvmStatic
   public fun ShortArray.asIterable(): Iterable<Short> {
      return (java.lang.Iterable<java.lang.Short>)(if (`$this$asIterable`.length == 0) CollectionsKt.emptyList() else new 3(`$this$asIterable`));
   }

   @JvmStatic
   public fun IntArray.asIterable(): Iterable<Int> {
      return (java.lang.Iterable<Integer>)(if (`$this$asIterable`.length == 0) CollectionsKt.emptyList() else new 4(`$this$asIterable`));
   }

   @JvmStatic
   public fun LongArray.asIterable(): Iterable<Long> {
      return (java.lang.Iterable<java.lang.Long>)(if (`$this$asIterable`.length == 0) CollectionsKt.emptyList() else new 5(`$this$asIterable`));
   }

   @JvmStatic
   public fun FloatArray.asIterable(): Iterable<Float> {
      return (java.lang.Iterable<java.lang.Float>)(if (`$this$asIterable`.length == 0) CollectionsKt.emptyList() else new 6(`$this$asIterable`));
   }

   @JvmStatic
   public fun DoubleArray.asIterable(): Iterable<Double> {
      return (java.lang.Iterable<java.lang.Double>)(if (`$this$asIterable`.length == 0) CollectionsKt.emptyList() else new 7(`$this$asIterable`));
   }

   @JvmStatic
   public fun BooleanArray.asIterable(): Iterable<Boolean> {
      return (java.lang.Iterable<java.lang.Boolean>)(if (`$this$asIterable`.length == 0) CollectionsKt.emptyList() else new 8(`$this$asIterable`));
   }

   @JvmStatic
   public fun CharArray.asIterable(): Iterable<Char> {
      return (java.lang.Iterable<Character>)(if (`$this$asIterable`.length == 0) CollectionsKt.emptyList() else new 9(`$this$asIterable`));
   }

   @JvmStatic
   public fun <T> Array<out T>.asSequence(): Sequence<T> {
      return (Sequence<T>)(if (`$this$asSequence`.length == 0)
         SequencesKt.emptySequence()
         else
         new kotlin.collections.ArraysKt___ArraysKt.asSequence..inlined.Sequence.1(`$this$asSequence`));
   }

   @JvmStatic
   public fun ByteArray.asSequence(): Sequence<Byte> {
      return (Sequence<java.lang.Byte>)(if (`$this$asSequence`.length == 0)
         SequencesKt.emptySequence()
         else
         new kotlin.collections.ArraysKt___ArraysKt.asSequence..inlined.Sequence.2(`$this$asSequence`));
   }

   @JvmStatic
   public fun ShortArray.asSequence(): Sequence<Short> {
      return (Sequence<java.lang.Short>)(if (`$this$asSequence`.length == 0)
         SequencesKt.emptySequence()
         else
         new kotlin.collections.ArraysKt___ArraysKt.asSequence..inlined.Sequence.3(`$this$asSequence`));
   }

   @JvmStatic
   public fun IntArray.asSequence(): Sequence<Int> {
      return (Sequence<Integer>)(if (`$this$asSequence`.length == 0)
         SequencesKt.emptySequence()
         else
         new kotlin.collections.ArraysKt___ArraysKt.asSequence..inlined.Sequence.4(`$this$asSequence`));
   }

   @JvmStatic
   public fun LongArray.asSequence(): Sequence<Long> {
      return (Sequence<java.lang.Long>)(if (`$this$asSequence`.length == 0)
         SequencesKt.emptySequence()
         else
         new kotlin.collections.ArraysKt___ArraysKt.asSequence..inlined.Sequence.5(`$this$asSequence`));
   }

   @JvmStatic
   public fun FloatArray.asSequence(): Sequence<Float> {
      return (Sequence<java.lang.Float>)(if (`$this$asSequence`.length == 0)
         SequencesKt.emptySequence()
         else
         new kotlin.collections.ArraysKt___ArraysKt.asSequence..inlined.Sequence.6(`$this$asSequence`));
   }

   @JvmStatic
   public fun DoubleArray.asSequence(): Sequence<Double> {
      return (Sequence<java.lang.Double>)(if (`$this$asSequence`.length == 0)
         SequencesKt.emptySequence()
         else
         new kotlin.collections.ArraysKt___ArraysKt.asSequence..inlined.Sequence.7(`$this$asSequence`));
   }

   @JvmStatic
   public fun BooleanArray.asSequence(): Sequence<Boolean> {
      return (Sequence<java.lang.Boolean>)(if (`$this$asSequence`.length == 0)
         SequencesKt.emptySequence()
         else
         new kotlin.collections.ArraysKt___ArraysKt.asSequence..inlined.Sequence.8(`$this$asSequence`));
   }

   @JvmStatic
   public fun CharArray.asSequence(): Sequence<Char> {
      return (Sequence<Character>)(if (`$this$asSequence`.length == 0)
         SequencesKt.emptySequence()
         else
         new kotlin.collections.ArraysKt___ArraysKt.asSequence..inlined.Sequence.9(`$this$asSequence`));
   }

   @JvmName(name = "averageOfByte")
   @JvmStatic
   public fun Array<out Byte>.average(): Double {
      var sum: Double = 0.0;
      var count: Int = 0;
      var var4: Int = 0;

      for (int var5 = $this$average.length; var4 < var5; var4++) {
         sum += `$this$average`[var4].byteValue();
         count++;
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmName(name = "averageOfShort")
   @JvmStatic
   public fun Array<out Short>.average(): Double {
      var sum: Double = 0.0;
      var count: Int = 0;
      var var4: Int = 0;

      for (int var5 = $this$average.length; var4 < var5; var4++) {
         sum += `$this$average`[var4].shortValue();
         count++;
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmName(name = "averageOfInt")
   @JvmStatic
   public fun Array<out Int>.average(): Double {
      var sum: Double = 0.0;
      var count: Int = 0;
      var var4: Int = 0;

      for (int var5 = $this$average.length; var4 < var5; var4++) {
         sum += `$this$average`[var4].intValue();
         count++;
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmName(name = "averageOfLong")
   @JvmStatic
   public fun Array<out Long>.average(): Double {
      var sum: Double = 0.0;
      var count: Int = 0;
      var var4: Int = 0;

      for (int var5 = $this$average.length; var4 < var5; var4++) {
         sum += `$this$average`[var4].longValue();
         count++;
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmName(name = "averageOfFloat")
   @JvmStatic
   public fun Array<out Float>.average(): Double {
      var sum: Double = 0.0;
      var count: Int = 0;
      var var4: Int = 0;

      for (int var5 = $this$average.length; var4 < var5; var4++) {
         sum += `$this$average`[var4].floatValue();
         count++;
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmName(name = "averageOfDouble")
   @JvmStatic
   public fun Array<out Double>.average(): Double {
      var sum: Double = 0.0;
      var count: Int = 0;
      var var4: Int = 0;

      for (int var5 = $this$average.length; var4 < var5; var4++) {
         sum += `$this$average`[var4];
         count++;
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmStatic
   public fun ByteArray.average(): Double {
      var sum: Double = 0.0;
      var count: Int = 0;

      for (byte element : $this$average) {
         sum += element;
         count++;
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmStatic
   public fun ShortArray.average(): Double {
      var sum: Double = 0.0;
      var count: Int = 0;

      for (short element : $this$average) {
         sum += element;
         count++;
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmStatic
   public fun IntArray.average(): Double {
      var sum: Double = 0.0;
      var count: Int = 0;

      for (int element : $this$average) {
         sum += element;
         count++;
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmStatic
   public fun LongArray.average(): Double {
      var sum: Double = 0.0;
      var count: Int = 0;

      for (long element : $this$average) {
         sum += element;
         count++;
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmStatic
   public fun FloatArray.average(): Double {
      var sum: Double = 0.0;
      var count: Int = 0;

      for (float element : $this$average) {
         sum += element;
         count++;
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmStatic
   public fun DoubleArray.average(): Double {
      var sum: Double = 0.0;
      var count: Int = 0;

      for (double element : $this$average) {
         sum += element;
         count++;
      }

      return if (count == 0) java.lang.Double.NaN else sum / count;
   }

   @JvmName(name = "sumOfByte")
   @JvmStatic
   public fun Array<out Byte>.sum(): Int {
      var sum: Int = 0;
      var var2: Int = 0;

      for (int var3 = $this$sum.length; var2 < var3; var2++) {
         sum += `$this$sum`[var2];
      }

      return sum;
   }

   @JvmName(name = "sumOfShort")
   @JvmStatic
   public fun Array<out Short>.sum(): Int {
      var sum: Int = 0;
      var var2: Int = 0;

      for (int var3 = $this$sum.length; var2 < var3; var2++) {
         sum += `$this$sum`[var2];
      }

      return sum;
   }

   @JvmName(name = "sumOfInt")
   @JvmStatic
   public fun Array<out Int>.sum(): Int {
      var sum: Int = 0;
      var var2: Int = 0;

      for (int var3 = $this$sum.length; var2 < var3; var2++) {
         sum += `$this$sum`[var2];
      }

      return sum;
   }

   @JvmName(name = "sumOfLong")
   @JvmStatic
   public fun Array<out Long>.sum(): Long {
      var sum: Long = 0L;
      var var3: Int = 0;

      for (int var4 = $this$sum.length; var3 < var4; var3++) {
         sum += `$this$sum`[var3];
      }

      return sum;
   }

   @JvmName(name = "sumOfFloat")
   @JvmStatic
   public fun Array<out Float>.sum(): Float {
      var sum: Float = 0.0F;
      var var2: Int = 0;

      for (int var3 = $this$sum.length; var2 < var3; var2++) {
         sum += `$this$sum`[var2];
      }

      return sum;
   }

   @JvmName(name = "sumOfDouble")
   @JvmStatic
   public fun Array<out Double>.sum(): Double {
      var sum: Double = 0.0;
      var var3: Int = 0;

      for (int var4 = $this$sum.length; var3 < var4; var3++) {
         sum += `$this$sum`[var3];
      }

      return sum;
   }

   @JvmStatic
   public fun ByteArray.sum(): Int {
      var sum: Int = 0;

      for (byte element : $this$sum) {
         sum += element;
      }

      return sum;
   }

   @JvmStatic
   public fun ShortArray.sum(): Int {
      var sum: Int = 0;

      for (short element : $this$sum) {
         sum += element;
      }

      return sum;
   }

   @JvmStatic
   public fun IntArray.sum(): Int {
      var sum: Int = 0;

      for (int element : $this$sum) {
         sum += element;
      }

      return sum;
   }

   @JvmStatic
   public fun LongArray.sum(): Long {
      var sum: Long = 0L;

      for (long element : $this$sum) {
         sum += element;
      }

      return sum;
   }

   @JvmStatic
   public fun FloatArray.sum(): Float {
      var sum: Float = 0.0F;

      for (float element : $this$sum) {
         sum += element;
      }

      return sum;
   }

   @JvmStatic
   public fun DoubleArray.sum(): Double {
      var sum: Double = 0.0;

      for (double element : $this$sum) {
         sum += element;
      }

      return sum;
   }

   @JvmStatic
   fun `withIndex$lambda$0$ArraysKt___ArraysKt`(`$this_withIndex`: Array<Any>): java.util.Iterator {
      return ArrayIteratorKt.iterator(`$this_withIndex`);
   }

   @JvmStatic
   fun `withIndex$lambda$1$ArraysKt___ArraysKt`(`$this_withIndex`: ByteArray): java.util.Iterator {
      return ArrayIteratorsKt.iterator(`$this_withIndex`);
   }

   @JvmStatic
   fun `withIndex$lambda$2$ArraysKt___ArraysKt`(`$this_withIndex`: ShortArray): java.util.Iterator {
      return ArrayIteratorsKt.iterator(`$this_withIndex`);
   }

   @JvmStatic
   fun `withIndex$lambda$3$ArraysKt___ArraysKt`(`$this_withIndex`: IntArray): java.util.Iterator {
      return ArrayIteratorsKt.iterator(`$this_withIndex`);
   }

   @JvmStatic
   fun `withIndex$lambda$4$ArraysKt___ArraysKt`(`$this_withIndex`: LongArray): java.util.Iterator {
      return ArrayIteratorsKt.iterator(`$this_withIndex`);
   }

   @JvmStatic
   fun `withIndex$lambda$5$ArraysKt___ArraysKt`(`$this_withIndex`: FloatArray): java.util.Iterator {
      return ArrayIteratorsKt.iterator(`$this_withIndex`);
   }

   @JvmStatic
   fun `withIndex$lambda$6$ArraysKt___ArraysKt`(`$this_withIndex`: DoubleArray): java.util.Iterator {
      return ArrayIteratorsKt.iterator(`$this_withIndex`);
   }

   @JvmStatic
   fun `withIndex$lambda$7$ArraysKt___ArraysKt`(`$this_withIndex`: BooleanArray): java.util.Iterator {
      return ArrayIteratorsKt.iterator(`$this_withIndex`);
   }

   @JvmStatic
   fun `withIndex$lambda$8$ArraysKt___ArraysKt`(`$this_withIndex`: CharArray): java.util.Iterator {
      return ArrayIteratorsKt.iterator(`$this_withIndex`);
   }

   open fun ArraysKt___ArraysKt() {
   }
}
