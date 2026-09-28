package io.ktor.utils.io.core

@Deprecated(
   message = "Use exhausted() instead",
   replaceWith = @ReplaceWith(
      expression = "exhausted()",
      imports = {}
   )
)
public final val isEmpty: Boolean
   public final get() {
      return `$this$isEmpty`.exhausted();
   }


@Deprecated(
   message = "This makes no sense for streaming inputs. Some use-cases are covered by exhausted() method",
   replaceWith = @ReplaceWith(
      expression = "!exhausted()",
      imports = {}
   )
)
public final val isNotEmpty: Boolean
   public final get() {
      return !`$this$isNotEmpty`.exhausted();
   }

