package io.ktor.util

import kotlin.enums.EnumEntries

public sealed class Platform protected constructor() {
   public data class Js(jsPlatform: io.ktor.util.Platform.JsPlatform) : Platform() {
      public final val jsPlatform: io.ktor.util.Platform.JsPlatform

      init {
         this.jsPlatform = jsPlatform;
      }

      public operator fun component1(): io.ktor.util.Platform.JsPlatform {
         return this.jsPlatform;
      }

      public fun copy(jsPlatform: io.ktor.util.Platform.JsPlatform = this.jsPlatform): io.ktor.util.Platform.Js {
         return new Platform.Js(jsPlatform);
      }

      public override fun toString(): String {
         return "Js(jsPlatform=${this.jsPlatform})";
      }

      public override fun hashCode(): Int {
         return this.jsPlatform.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is Platform.Js) {
            return false;
         } else {
            return this.jsPlatform === (other as Platform.Js).jsPlatform;
         }
      }
   }

   public enum class JsPlatform {
      Browser,
      Node
      @JvmStatic
      fun getEntries(): EnumEntries<Platform.JsPlatform> {
         return $ENTRIES;
      }
   }

   public data object Jvm : Platform() {
      public override fun toString(): String {
         return "Jvm";
      }

      public override fun hashCode(): Int {
         return 1051825272;
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else {
            return other is Platform.Jvm;
         }
      }
   }

   public data object Native : Platform() {
      public override fun toString(): String {
         return "Native";
      }

      public override fun hashCode(): Int {
         return -1059277600;
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else {
            return other is Platform.Native;
         }
      }
   }

   public data class WasmJs(jsPlatform: io.ktor.util.Platform.JsPlatform) : Platform() {
      public final val jsPlatform: io.ktor.util.Platform.JsPlatform

      init {
         this.jsPlatform = jsPlatform;
      }

      public operator fun component1(): io.ktor.util.Platform.JsPlatform {
         return this.jsPlatform;
      }

      public fun copy(jsPlatform: io.ktor.util.Platform.JsPlatform = this.jsPlatform): io.ktor.util.Platform.WasmJs {
         return new Platform.WasmJs(jsPlatform);
      }

      public override fun toString(): String {
         return "WasmJs(jsPlatform=${this.jsPlatform})";
      }

      public override fun hashCode(): Int {
         return this.jsPlatform.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is Platform.WasmJs) {
            return false;
         } else {
            return this.jsPlatform === (other as Platform.WasmJs).jsPlatform;
         }
      }
   }
}
