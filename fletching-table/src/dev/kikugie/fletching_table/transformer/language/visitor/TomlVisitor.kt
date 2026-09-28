package dev.kikugie.fletching_table.transformer.language.visitor

import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlLiteral
import net.peanuuutz.tomlkt.TomlNull
import net.peanuuutz.tomlkt.TomlTable

public interface TomlVisitor<T> {
   public abstract fun visitNull(it: TomlNull): Any {
   }

   public abstract fun visitLiteral(it: TomlLiteral): Any {
   }

   public abstract fun visitArray(it: TomlArray): Any {
   }

   public abstract fun visitTable(it: TomlTable): Any {
   }
}
