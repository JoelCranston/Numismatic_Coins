package dev.kikugie.fletching_table.transformer.language.visitor

import com.charleskorn.kaml.YamlList
import com.charleskorn.kaml.YamlMap
import com.charleskorn.kaml.YamlNull
import com.charleskorn.kaml.YamlScalar
import com.charleskorn.kaml.YamlTaggedNode

internal interface YamlVisitor<T> {
   public abstract fun visitNull(it: YamlNull): Any {
   }

   public abstract fun visitScalar(it: YamlScalar): Any {
   }

   public abstract fun visitList(it: YamlList): Any {
   }

   public abstract fun visitMap(it: YamlMap): Any {
   }

   public abstract fun visitTagged(it: YamlTaggedNode): Any {
   }
}
