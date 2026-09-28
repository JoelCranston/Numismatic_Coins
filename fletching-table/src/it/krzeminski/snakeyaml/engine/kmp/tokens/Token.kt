package it.krzeminski.snakeyaml.engine.kmp.tokens

import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark
import kotlin.enums.EnumEntries

public sealed class Token protected constructor(startMark: Mark?, endMark: Mark?) {
   public final val startMark: Mark?
   public final val endMark: Mark?
   public abstract val tokenId: it.krzeminski.snakeyaml.engine.kmp.tokens.Token.ID

   init {
      this.startMark = startMark;
      this.endMark = endMark;
   }

   public override fun toString(): String {
      return this.getTokenId().toString();
   }

   public enum class ID(description: String) {
      Alias("<alias>"),
      Anchor("<anchor>"),
      BlockEnd("<block end>"),
      BlockEntry("-"),
      BlockMappingStart("<block mapping start>"),
      BlockSequenceStart("<block sequence start>"),
      Directive("<directive>"),
      DocumentEnd("<document end>"),
      DocumentStart("<document start>"),
      FlowEntry(","),
      FlowMappingEnd("}"),
      FlowMappingStart("{"),
      FlowSequenceEnd("]"),
      FlowSequenceStart("["),
      Key("?"),
      Scalar("<scalar>"),
      StreamEnd("<stream end>"),
      StreamStart("<stream start>"),
      Tag("<tag>"),
      Comment("#"),
      Value(":")
      private final val description: String

      init {
         this.description = description;
      }

      public override fun toString(): String {
         return this.description;
      }

      @JvmStatic
      fun getEntries(): EnumEntries<Token.ID> {
         return $ENTRIES;
      }
   }
}
