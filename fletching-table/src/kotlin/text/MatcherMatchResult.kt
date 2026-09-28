package kotlin.text

import java.util.regex.Matcher
import kotlin.text.MatcherMatchResult.groupValues.1

private class MatcherMatchResult(matcher: Matcher, input: CharSequence) : MatchResult {
   private final val matcher: Matcher
   private final val input: CharSequence

   private final val matchResult: java.util.regex.MatchResult
      private final get() {
         return this.matcher;
      }


   public open val range: IntRange
      public open get() {
         return RegexKt.access$range(this.getMatchResult());
      }


   public open val value: String
      public open get() {
         val var10000: java.lang.String = this.getMatchResult().group();
         return var10000;
      }


   public open val groups: MatchGroupCollection
   private final var groupValues_: List<String>?

   public open val groupValues: List<String>
      public open get() {
         if (this.groupValues_ == null) {
            this.groupValues_ = new 1(this);
         }

         val var10000: java.util.List = this.groupValues_;
         return var10000;
      }


   init {
      this.matcher = matcher;
      this.input = input;
      this.groups = new kotlin.text.MatcherMatchResult.groups.1(this);
   }

   public override fun next(): MatchResult? {
      val nextIndex: Int = this.getMatchResult().end() + (if (this.getMatchResult().end() == this.getMatchResult().start()) 1 else 0);
      val var2: MatchResult;
      if (nextIndex <= this.input.length()) {
         val var10000: Matcher = this.matcher.pattern().matcher(this.input);
         var2 = RegexKt.access$findNext(var10000, nextIndex, this.input);
      } else {
         var2 = null;
      }

      return var2;
   }
}
