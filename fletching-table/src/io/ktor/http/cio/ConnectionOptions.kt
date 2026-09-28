package io.ktor.http.cio

import io.ktor.http.cio.internals.AsciiCharTree
import java.util.ArrayList

public class ConnectionOptions(close: Boolean = false,
   keepAlive: Boolean = false,
   upgrade: Boolean = false,
   extraOptions: List<String> = CollectionsKt.emptyList()
) {
   public final val close: Boolean
   public final val keepAlive: Boolean
   public final val upgrade: Boolean
   public final val extraOptions: List<String>

   init {
      this.close = close;
      this.keepAlive = keepAlive;
      this.upgrade = upgrade;
      this.extraOptions = extraOptions;
   }

   public override fun toString(): String {
      return if (this.extraOptions.isEmpty())
         (
            if (this.close && !this.keepAlive && !this.upgrade)
               "close"
               else
               (
                  if (!this.close && this.keepAlive && !this.upgrade)
                     "keep-alive"
                     else
                     (if (!this.close && this.keepAlive && this.upgrade) "keep-alive, Upgrade" else this.buildToString())
               )
         )
         else
         this.buildToString();
   }

   private fun buildToString(): String {
      val var1: StringBuilder = new StringBuilder();
      val items: ArrayList = new ArrayList(this.extraOptions.size() + 3);
      if (this.close) {
         items.add("close");
      }

      if (this.keepAlive) {
         items.add("keep-alive");
      }

      if (this.upgrade) {
         items.add("Upgrade");
      }

      if (!this.extraOptions.isEmpty()) {
         items.addAll(this.extraOptions);
      }

      CollectionsKt.joinTo$default(items, var1, null, null, null, 0, null, null, 126, null);
      return var1.toString();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other == null || this.getClass() != other.getClass()) {
         return false;
      } else if (this.close != (other as ConnectionOptions).close) {
         return false;
      } else if (this.keepAlive != (other as ConnectionOptions).keepAlive) {
         return false;
      } else if (this.upgrade != (other as ConnectionOptions).upgrade) {
         return false;
      } else {
         return this.extraOptions == (other as ConnectionOptions).extraOptions;
      }
   }

   public override fun hashCode(): Int {
      return 31 * (31 * (31 * java.lang.Boolean.hashCode(this.close) + java.lang.Boolean.hashCode(this.keepAlive)) + java.lang.Boolean.hashCode(this.upgrade))
         + this.extraOptions.hashCode();
   }

   @JvmStatic
   fun `knownTypes$lambda$0`(it: Pair): Int {
      return (it.getFirst() as java.lang.String).length();
   }

   @JvmStatic
   fun `knownTypes$lambda$1`(t: Pair, idx: Int): Char {
      return (t.getFirst() as java.lang.String).charAt(idx);
   }

   fun ConnectionOptions() {
      this(false, false, false, null, 15, null);
   }

   public companion object {
      public final val Close: ConnectionOptions
      public final val KeepAlive: ConnectionOptions
      public final val Upgrade: ConnectionOptions
      private final val knownTypes: AsciiCharTree<Pair<String, ConnectionOptions>>

      public fun parse(connection: CharSequence?): ConnectionOptions? {
         if (connection == null) {
            return null;
         } else {
            val known: java.util.List = AsciiCharTree.search$default(
               ConnectionOptions.access$getKnownTypes$cp(), connection, 0, 0, true, ConnectionOptions.Companion::parse$lambda$0, 6, null
            );
            return if (known.size() == 1) (known.get(0) as Pair).getSecond() as ConnectionOptions else this.parseSlow(connection);
         }
      }

      private fun parseSlow(connection: CharSequence): ConnectionOptions {
         var idx: Int = 0;
         var start: Int = 0;
         val length: Int = connection.length();
         var connectionOptions: ConnectionOptions = null;
         var hopHeadersList: ArrayList = null;

         while (idx < length) {
            do {
               val detected: Char = connection.charAt(idx);
               if (detected != ' ' && detected != ',') {
                  start = idx;
                  break;
               }
            } while (++idx < length);

            label58:
            while (idx < length) {
               switch (connection.charAt(idx)) {
                  case ' ':
                  case ',':
                     break label58;
                  default:
                     idx++;
               }
            }

            val var9: Pair = CollectionsKt.singleOrNull(
               ConnectionOptions.access$getKnownTypes$cp().search(connection, start, idx, true, ConnectionOptions.Companion::parseSlow$lambda$0)
            );
            if (var9 == null) {
               if (hopHeadersList == null) {
                  hopHeadersList = new ArrayList();
               }

               hopHeadersList.add(connection.subSequence(start, idx).toString());
            } else if (connectionOptions == null) {
               connectionOptions = var9.getSecond() as ConnectionOptions;
            } else {
               connectionOptions = new ConnectionOptions(
                  connectionOptions.getClose() || (var9.getSecond() as ConnectionOptions).getClose(),
                  connectionOptions.getKeepAlive() || (var9.getSecond() as ConnectionOptions).getKeepAlive(),
                  connectionOptions.getUpgrade() || (var9.getSecond() as ConnectionOptions).getUpgrade(),
                  CollectionsKt.emptyList()
               );
            }
         }

         if (connectionOptions == null) {
            connectionOptions = this.getKeepAlive();
         }

         return if (hopHeadersList == null)
            connectionOptions
            else
            new ConnectionOptions(connectionOptions.getClose(), connectionOptions.getKeepAlive(), connectionOptions.getUpgrade(), hopHeadersList);
      }

      @JvmStatic
      fun `parse$lambda$0`(var0: Char, var1: Int): Boolean {
         return false;
      }

      @JvmStatic
      fun `parseSlow$lambda$0`(var0: Char, var1: Int): Boolean {
         return false;
      }
   }
}
