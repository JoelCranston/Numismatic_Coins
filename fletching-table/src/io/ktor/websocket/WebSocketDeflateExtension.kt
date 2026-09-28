package io.ktor.websocket

import io.ktor.util.AttributeKey
import io.ktor.util.reflect.TypeInfo
import io.ktor.websocket.internals.DeflaterUtilsKt
import java.util.ArrayList
import java.util.Locale
import java.util.zip.Deflater
import java.util.zip.Inflater
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.Reflection
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType

@SourceDebugExtension(["SMAP\nWebSocketDeflateExtension.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebSocketDeflateExtension.kt\nio/ktor/websocket/WebSocketDeflateExtension\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Attributes.kt\nio/ktor/util/AttributesKt\n+ 4 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,256:1\n1#2:257\n21#3:258\n69#4:259\n84#4,8:260\n*S KotlinDebug\n*F\n+ 1 WebSocketDeflateExtension.kt\nio/ktor/websocket/WebSocketDeflateExtension\n*L\n245#1:258\n245#1:259\n245#1:260,8\n*E\n"])
public class WebSocketDeflateExtension internal constructor(config: io.ktor.websocket.WebSocketDeflateExtension.Config) :
   WebSocketExtension<WebSocketDeflateExtension.Config> {
   private final val config: io.ktor.websocket.WebSocketDeflateExtension.Config
   public open val factory: WebSocketExtensionFactory<
      io.ktor.websocket.WebSocketDeflateExtension.Config,
      out WebSocketExtension<io.ktor.websocket.WebSocketDeflateExtension.Config>
   >
   public open val protocols: List<WebSocketExtensionHeader>
   private final val inflater: Inflater
   private final val deflater: Deflater
   internal final var outgoingNoContextTakeover: Boolean
   internal final var incomingNoContextTakeover: Boolean
   private final var decompressIncoming: Boolean

   init {
      this.config = config;
      this.factory = Companion;
      this.protocols = this.config.build$ktor_websockets();
      this.inflater = new Inflater(true);
      this.deflater = new Deflater(this.config.getCompressionLevel(), true);
   }

   public override fun clientNegotiation(negotiatedProtocols: List<WebSocketExtensionHeader>): Boolean {
      val value: java.util.Iterator = negotiatedProtocols.iterator();

      var var10000: Any;
      while (true) {
         if (value.hasNext()) {
            val var7: Any = value.next();
            if (!((var7 as WebSocketExtensionHeader).getName() == "permessage-deflate")) {
               continue;
            }

            var10000 = var7;
            break;
         }

         var10000 = null;
         break;
      }

      var10000 = var10000 as WebSocketExtensionHeader;
      if (var10000 as WebSocketExtensionHeader == null) {
         return false;
      } else {
         this.incomingNoContextTakeover = this.config.getServerNoContextTakeOver();
         this.outgoingNoContextTakeover = this.config.getClientNoContextTakeOver();

         for (Pair var4 : ((WebSocketExtensionHeader)var10000).parseParameters()) {
            val var10: java.lang.String = var4.component1() as java.lang.String;
            val var11: java.lang.String = var4.component2() as java.lang.String;
            switch (key.hashCode()) {
               case -708713803:
                  if (var10.equals("client_no_context_takeover")) {
                     if (!StringsKt.isBlank(var11)) {
                        throw new IllegalStateException(
                           ("WebSocket permessage-deflate extension parameter client_no_context_takeover shouldn't have a value. Current: $var11").toString()
                        );
                     }

                     this.outgoingNoContextTakeover = true;
                  }
                  break;
               case 646404390:
                  if (var10.equals("client_max_window_bits") && !StringsKt.isBlank(var11) && Integer.parseInt(var11) != 15) {
                     throw new IllegalStateException("Only 15 window size is supported.".toString());
                  }
                  break;
               case 1266201133:
                  if (var10.equals("server_no_context_takeover")) {
                     if (!StringsKt.isBlank(var11)) {
                        throw new IllegalStateException(
                           ("WebSocket permessage-deflate extension parameter server_no_context_takeover shouldn't have a value. Current: $var11").toString()
                        );
                     }

                     this.incomingNoContextTakeover = true;
                  }
                  break;
               case 2034279582:
                  if (!var10.equals("server_max_window_bits")) {
                  }
               default:
            }
         }

         return true;
      }
   }

   public override fun serverNegotiation(requestedProtocols: List<WebSocketExtensionHeader>): List<WebSocketExtensionHeader> {
      val key: java.util.Iterator = requestedProtocols.iterator();

      var var10000: Any;
      while (true) {
         if (key.hasNext()) {
            val value: Any = key.next();
            if (!((value as WebSocketExtensionHeader).getName() == "permessage-deflate")) {
               continue;
            }

            var10000 = value;
            break;
         }

         var10000 = null;
         break;
      }

      var10000 = var10000 as WebSocketExtensionHeader;
      if (var10000 as WebSocketExtensionHeader == null) {
         return CollectionsKt.emptyList();
      } else {
         val parameters: java.util.List = new ArrayList();

         for (Pair var11 : ((WebSocketExtensionHeader)var10000).parseParameters()) {
            val var12: java.lang.String = var11.component1() as java.lang.String;
            val var13: java.lang.String = var11.component2() as java.lang.String;
            var10000 = Locale.getDefault();
            var10000 = var12.toLowerCase((Locale)var10000);
            switch (var10000.hashCode()) {
               case -708713803:
                  if (!var10000.equals("client_no_context_takeover")) {
                     throw new IllegalStateException(("Unsupported extension parameter: ($var12, $var13)").toString());
                  }

                  if (!StringsKt.isBlank(var13)) {
                     throw new IllegalStateException("Check failed.");
                  }

                  this.incomingNoContextTakeover = true;
                  parameters.add("client_no_context_takeover");
                  break;
               case 646404390:
                  if (!var10000.equals("client_max_window_bits")) {
                     throw new IllegalStateException(("Unsupported extension parameter: ($var12, $var13)").toString());
                  }
                  break;
               case 1266201133:
                  if (!var10000.equals("server_no_context_takeover")) {
                     throw new IllegalStateException(("Unsupported extension parameter: ($var12, $var13)").toString());
                  }

                  if (!StringsKt.isBlank(var13)) {
                     throw new IllegalStateException("Check failed.");
                  }

                  this.outgoingNoContextTakeover = true;
                  parameters.add("server_no_context_takeover");
                  break;
               case 2034279582:
                  if (var10000.equals("server_max_window_bits")) {
                     if (Integer.parseInt(var13) != 15) {
                        throw new IllegalStateException("Only 15 window size is supported".toString());
                     }
                     break;
                  }

                  throw new IllegalStateException(("Unsupported extension parameter: ($var12, $var13)").toString());
               default:
                  throw new IllegalStateException(("Unsupported extension parameter: ($var12, $var13)").toString());
            }
         }

         return CollectionsKt.listOf(new WebSocketExtensionHeader("permessage-deflate", parameters));
      }
   }

   public override fun processOutgoingFrame(frame: Frame): Frame {
      if (frame !is Frame.Text && frame !is Frame.Binary) {
         return frame;
      } else if (!this.config.getCompressCondition$ktor_websockets().invoke(frame)) {
         return frame;
      } else {
         val deflated: ByteArray = DeflaterUtilsKt.deflateFully(this.deflater, frame.getData());
         if (this.outgoingNoContextTakeover) {
            this.deflater.reset();
         }

         return Frame.Companion.byType(frame.getFin(), frame.getFrameType(), deflated, rsv1, frame.getRsv2(), frame.getRsv3());
      }
   }

   public override fun processIncomingFrame(frame: Frame): Frame {
      if (!WebSocketDeflateExtensionKt.access$isCompressed(frame) && !this.decompressIncoming) {
         return frame;
      } else {
         this.decompressIncoming = true;
         val inflated: ByteArray = DeflaterUtilsKt.inflateFully(this.inflater, frame.getData());
         if (this.incomingNoContextTakeover) {
            this.inflater.reset();
         }

         if (frame.getFin()) {
            this.decompressIncoming = false;
         }

         return Frame.Companion.byType(frame.getFin(), frame.getFrameType(), inflated, !rsv1, frame.getRsv2(), frame.getRsv3());
      }
   }

   @JvmStatic
   fun {
      var var6: KType;
      try {
         var6 = Reflection.typeOf(WebSocketDeflateExtension.class);
      } catch (var12: java.lang.Throwable) {
         var6 = null;
      }

      key = new AttributeKey<>("WebsocketDeflateExtension", new TypeInfo(WebSocketDeflateExtension::class, var6));
      rsv1 = true;
   }

   public companion object : WebSocketExtensionFactory<WebSocketDeflateExtension.Config, WebSocketDeflateExtension> {
      public open val key: AttributeKey<WebSocketDeflateExtension>
      public open val rsv1: Boolean
      public open val rsv2: Boolean
      public open val rsv3: Boolean

      public open fun install(config: (io.ktor.websocket.WebSocketDeflateExtension.Config) -> Unit): WebSocketDeflateExtension {
         val var2: WebSocketDeflateExtension.Config = new WebSocketDeflateExtension.Config();
         config.invoke(var2);
         return new WebSocketDeflateExtension(var2);
      }
   }

   public class Config {
      public final var clientNoContextTakeOver: Boolean
      public final var serverNoContextTakeOver: Boolean
      public final var compressionLevel: Int = -1
      internal final var manualConfig: (MutableList<WebSocketExtensionHeader>) -> Unit = WebSocketDeflateExtension.Config::manualConfig$lambda$0
      internal final var compressCondition: (Frame) -> Boolean = WebSocketDeflateExtension.Config::compressCondition$lambda$0

      public fun configureProtocols(block: (MutableList<WebSocketExtensionHeader>) -> Unit) {
         this.manualConfig = WebSocketDeflateExtension.Config::configureProtocols$lambda$0;
      }

      public fun compressIf(block: (Frame) -> Boolean) {
         this.compressCondition = WebSocketDeflateExtension.Config::compressIf$lambda$0;
      }

      public fun compressIfBiggerThan(bytes: Int) {
         this.compressIf(WebSocketDeflateExtension.Config::compressIfBiggerThan$lambda$0);
      }

      internal fun build(): List<WebSocketExtensionHeader> {
         val result: java.util.List = new ArrayList();
         val parameters: java.util.List = new ArrayList();
         if (this.clientNoContextTakeOver) {
            parameters.add("client_no_context_takeover");
         }

         if (this.serverNoContextTakeOver) {
            parameters.add("server_no_context_takeover");
         }

         result.add(new WebSocketExtensionHeader("permessage-deflate", parameters));
         this.manualConfig.invoke(result);
         return result;
      }

      @JvmStatic
      fun `manualConfig$lambda$0`(it: java.util.List): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `compressCondition$lambda$0`(it: Frame): Boolean {
         return true;
      }

      @JvmStatic
      fun `configureProtocols$lambda$0`(`$old`: Function1, `$block`: Function1, it: java.util.List): Unit {
         `$old`.invoke(it);
         `$block`.invoke(it);
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `compressIf$lambda$0`(`$block`: Function1, `$old`: Function1, it: Frame): Boolean {
         return `$block`.invoke(it) as java.lang.Boolean && `$old`.invoke(it) as java.lang.Boolean;
      }

      @JvmStatic
      fun `compressIfBiggerThan$lambda$0`(`$bytes`: Int, frame: Frame): Boolean {
         return frame.getData().length > `$bytes`;
      }
   }
}
