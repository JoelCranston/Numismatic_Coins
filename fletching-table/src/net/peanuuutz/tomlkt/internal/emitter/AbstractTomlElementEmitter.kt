package net.peanuuutz.tomlkt.internal.emitter

import kotlin.jvm.internal.SourceDebugExtension
import net.peanuuutz.tomlkt.Toml
import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlBlockArray
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlElementKt
import net.peanuuutz.tomlkt.TomlInteger
import net.peanuuutz.tomlkt.TomlLiteral
import net.peanuuutz.tomlkt.TomlNull
import net.peanuuutz.tomlkt.TomlTable
import net.peanuuutz.tomlkt.TomlWriter
import net.peanuuutz.tomlkt.internal.StringUtilsKt

@SourceDebugExtension(["SMAP\nTomlElementEmitter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TomlElementEmitter.kt\nnet/peanuuutz/tomlkt/internal/emitter/AbstractTomlElementEmitter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,690:1\n1#2:691\n*E\n"])
internal abstract class AbstractTomlElementEmitter {
   public final val toml: Toml
   public final val writer: TomlWriter

   public final var isInline: Boolean
      internal set

   public final var blockArray: TomlBlockArray?
      internal set

   public final var isStringMultiline: Boolean
      internal set

   public final var isStringLiteral: Boolean
      internal set

   public final var integerRepresentation: TomlInteger?
      internal set

   open fun AbstractTomlElementEmitter(toml: Toml, writer: TomlWriter) {
      this.toml = toml;
      this.writer = writer;
   }

   public fun emitElement(element: TomlElement) {
      if (element is TomlNull) {
         this.emitNull();
      } else if (element is TomlLiteral) {
         this.emitLiteral(element as TomlLiteral);
      } else if (element is TomlArray) {
         this.createArrayEmitter(element as TomlArray).emitArray(element as TomlArray);
      } else {
         if (element !is TomlTable) {
            throw new NoWhenBranchMatchedException();
         }

         this.createTableEmitter(element as TomlTable).emitTable(element as TomlTable);
      }
   }

   public open fun emitNull() {
      this.writer.writeNullValue();
   }

   public fun emitLiteral(literal: TomlLiteral) {
      switch (AbstractTomlElementEmitter.WhenMappings.$EnumSwitchMapping$0[literal.getType().ordinal()]) {
         case 1:
            this.emitBoolean(TomlElementKt.toBoolean(literal));
            break;
         case 2:
            val var2: java.lang.Long = TomlElementKt.toLongOrNull(literal);
            if (var2 == null) {
               this.emitULong(literal.getContent());
               return;
            }

            this.emitInteger(var2);
            break;
         case 3:
            this.emitFloat(TomlElementKt.toDouble(literal));
            break;
         case 4:
            this.emitString(literal.toString());
            break;
         case 5:
         case 6:
         case 7:
         case 8:
            this.emitDateTime(literal.getContent());
            break;
         default:
            throw new NoWhenBranchMatchedException();
      }
   }

   public open fun emitBoolean(boolean: Boolean) {
      this.writer.writeBooleanValue(var1);
   }

   public open fun emitInteger(integer: Long) {
      val representation: TomlInteger = this.integerRepresentation;
      if (this.integerRepresentation == null) {
         TomlWriter.writeIntegerValue$default(this.writer, integer, null, 0, false, 14, null);
      } else {
         this.writer.writeIntegerValue(integer, this.integerRepresentation.base(), representation.group(), this.toml.getConfig().getUppercaseInteger());
      }
   }

   public open fun emitULong(content: String) {
      val representation: TomlInteger = this.integerRepresentation;
      if (this.integerRepresentation == null) {
         this.writer.writeString(content);
      } else if (this.integerRepresentation.group() < 0) {
         throw new IllegalArgumentException("Group size cannot be negative".toString());
      } else {
         this.writer
            .writeString(
               StringUtilsKt.processIntegerString(content, representation.base(), representation.group(), this.toml.getConfig().getUppercaseInteger())
            );
      }
   }

   public open fun emitFloat(float: Double) {
      this.writer.writeFloatValue(var1);
   }

   public open fun emitString(string: String) {
      this.writer.writeStringValue(string, this.isStringMultiline, this.isStringLiteral);
   }

   public open fun emitDateTime(content: String) {
      this.writer.writeString(content);
   }

   public open fun createArrayEmitter(array: TomlArray): AbstractTomlElementEmitter {
      return new TomlInlineArrayEmitter(this);
   }

   public open fun emitArray(array: TomlArray) {
      this.createArrayEmitter(array).emitArray(array);
   }

   public open fun createTableEmitter(table: TomlTable): AbstractTomlElementEmitter {
      return new TomlInlineTableEmitter(this);
   }

   public open fun emitTable(table: TomlTable) {
      this.createTableEmitter(table).emitTable(table);
   }
}
