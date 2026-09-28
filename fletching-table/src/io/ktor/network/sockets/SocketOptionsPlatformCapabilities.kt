package io.ktor.network.sockets

import java.io.IOException
import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.nio.channels.DatagramChannel
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.util.ArrayList
import java.util.LinkedHashMap
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nSocketOptionsPlatformCapabilities.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SocketOptionsPlatformCapabilities.kt\nio/ktor/network/sockets/SocketOptionsPlatformCapabilities\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,102:1\n3919#2:103\n4434#2,2:104\n1400#2:112\n1401#2:114\n1400#2,2:115\n1400#2,2:117\n1208#3,2:106\n1236#3,4:108\n1#4:113\n*S KotlinDebug\n*F\n+ 1 SocketOptionsPlatformCapabilities.kt\nio/ktor/network/sockets/SocketOptionsPlatformCapabilities\n*L\n23#1:103\n23#1:104,2\n38#1:112\n38#1:114\n54#1:115,2\n70#1:117,2\n28#1:106,2\n28#1:108,4\n*E\n"])
internal object SocketOptionsPlatformCapabilities {
   private final val standardSocketOptions: Map<String, Field>
   private final val channelSetOption: Method?
   private final val serverChannelSetOption: Method?
   private final val datagramSetOption: Method?

   public fun setReusePort(channel: SocketChannel) {
      val option: Any = this.socketOption("SO_REUSEPORT");
      val var10000: Method = channelSetOption;
      var10000.invoke(channel, option, true);
   }

   public fun setReusePort(channel: ServerSocketChannel) {
      val option: Any = this.socketOption("SO_REUSEPORT");
      val var10000: Method = serverChannelSetOption;
      var10000.invoke(channel, option, true);
   }

   public fun setReusePort(channel: DatagramChannel) {
      val option: Any = this.socketOption("SO_REUSEPORT");
      val var10000: Method = datagramSetOption;
      var10000.invoke(channel, option, true);
   }

   private fun socketOption(name: String): Any {
      val var10000: Field = standardSocketOptions.get(name);
      if (var10000 != null) {
         val var2: Any = var10000.get(null);
         if (var2 != null) {
            return var2;
         }
      }

      throw new IOException("Socket option $name is not supported");
   }

   @JvmStatic
   fun {
      var socketOptionType: java.util.Map;
      try {
         var var10000: java.util.Map;
         label199: {
            val var20: Class = Class.forName("java.net.StandardSocketOptions");
            if (var20 != null) {
               val var1: Array<Field> = var20.getFields();
               if (var1 != null) {
                  val `element$iv`: java.util.Collection = new ArrayList();

                  for (Object element$iv$iv : var1) {
                     val var13: Int = var10.getModifiers();
                     if (Modifier.isStatic(var13) && Modifier.isFinal(var13) && Modifier.isPublic(var13)) {
                        `element$iv`.add(var10);
                     }
                  }

                  val var34: java.lang.Iterable = `element$iv` as java.util.List;
                  val `destination$iv$ivx`: java.util.Map = new LinkedHashMap(
                     RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(`element$iv` as java.util.List, 10)), 16)
                  );

                  for (Object element$iv$ivx : var34) {
                     val var63: java.lang.String = (`element$iv$ivx` as Field).getName();
                     `destination$iv$ivx`.put(var63, `element$iv$ivx`);
                  }

                  var10000 = `destination$iv$ivx`;
                  break label199;
               }
            }

            var10000 = MapsKt.emptyMap();
         }

         socketOptionType = var10000;
      } catch (var19: java.lang.Throwable) {
         socketOptionType = MapsKt.emptyMap();
      }

      standardSocketOptions = socketOptionType;

      try {
         val var22: Class = Class.forName("java.net.SocketOption");
         val var27: Class = Class.forName("java.nio.channels.SocketChannel");
         var var64: Array<Method> = var27.getMethods();
         val `$this$firstOrNull$iv`: Array<Any> = var64;
         var var35: Int = 0;
         val var39: Int = `$this$firstOrNull$iv`.length;

         while (true) {
            if (var35 >= var39) {
               var64 = null;
               break;
            }

            val var43: Any = `$this$firstOrNull$iv`[var35];
            val var46: Method = `$this$firstOrNull$iv`[var35] as Method;
            val var54: Int = (`$this$firstOrNull$iv`[var35] as Method).getModifiers();
            if (Modifier.isPublic(var54)
               && !Modifier.isStatic(var54)
               && var46.getName() == "setOption"
               && var46.getParameterTypes().length == 2
               && var46.getReturnType() == var27
               && var46.getParameterTypes()[0] == var22
               && var46.getParameterTypes()[1] == Object::class.java) {
               var64 = (Method[])var43;
               break;
            }

            var35++;
         }

         var21 = var64 as Method;
      } catch (var18: java.lang.Throwable) {
         var21 = null;
      }

      channelSetOption = var21;

      try {
         val var24: Class = Class.forName("java.net.SocketOption");
         val var28: Class = Class.forName("java.nio.channels.ServerSocketChannel");
         var var66: Array<Method> = var28.getMethods();
         val var30: Array<Any> = var66;
         var var36: Int = 0;
         val var40: Int = var30.length;

         while (true) {
            if (var36 >= var40) {
               var66 = null;
               break;
            }

            val var44: Any = var30[var36];
            val var47: Method = var30[var36] as Method;
            val var55: Int = (var30[var36] as Method).getModifiers();
            if (Modifier.isPublic(var55)
               && !Modifier.isStatic(var55)
               && var47.getName() == "setOption"
               && var47.getParameterTypes().length == 2
               && var47.getReturnType() == var28
               && var47.getParameterTypes()[0] == var24
               && var47.getParameterTypes()[1] == Object::class.java) {
               var66 = (Method[])var44;
               break;
            }

            var36++;
         }

         var23 = var66 as Method;
      } catch (var17: java.lang.Throwable) {
         var23 = null;
      }

      serverChannelSetOption = var23;

      try {
         val var26: Class = Class.forName("java.net.SocketOption");
         val var29: Class = Class.forName("java.nio.channels.DatagramChannel");
         var var68: Array<Method> = var29.getMethods();
         val var31: Array<Any> = var68;
         var var37: Int = 0;
         val var41: Int = var31.length;

         while (true) {
            if (var37 >= var41) {
               var68 = null;
               break;
            }

            val var45: Any = var31[var37];
            val var48: Method = var31[var37] as Method;
            val var56: Int = (var31[var37] as Method).getModifiers();
            if (Modifier.isPublic(var56)
               && !Modifier.isStatic(var56)
               && var48.getName() == "setOption"
               && var48.getParameterTypes().length == 2
               && var48.getReturnType() == var29
               && var48.getParameterTypes()[0] == var26
               && var48.getParameterTypes()[1] == Object::class.java) {
               var68 = (Method[])var45;
               break;
            }

            var37++;
         }

         var25 = var68 as Method;
      } catch (var16: java.lang.Throwable) {
         var25 = null;
      }

      datagramSetOption = var25;
   }
}
