package it.krzeminski.snakeyaml.engine.kmp.exceptions

public open class MarkedYamlEngineException protected constructor(context: String?,
   contextMark: Mark?,
   problem: String,
   problemMark: Mark?,
   cause: Throwable? = null
) : YamlEngineException(MarkedYamlEngineException.Companion.access$buildReadableError(Companion, context, contextMark, problem, problemMark), cause) {
   public final val context: String?
   public final val contextMark: Mark?
   public final val problem: String
   public final val problemMark: Mark?

   init {
      this.context = context;
      this.contextMark = contextMark;
      this.problem = problem;
      this.problemMark = problemMark;
   }

   public companion object {
      private fun buildReadableError(context: String?, contextMark: Mark?, problem: String?, problemMark: Mark?): String {
         val var5: StringBuilder = new StringBuilder();
         if (context != null) {
            var5.append(context).append('\n');
         }

         if (contextMark != null
            && (
               problem == null
                  || problemMark == null
                  || contextMark.getName() == (if (problemMark != null) problemMark.getName() else null)
                  || problemMark == null
                  || contextMark.getLine() != problemMark.getLine()
                  || contextMark.getColumn() != problemMark.getColumn()
            )) {
            var5.append(contextMark).append('\n');
         }

         if (problem != null) {
            var5.append(problem).append('\n');
         }

         if (problemMark != null) {
            var5.append(problemMark).append('\n');
         }

         return var5.toString();
      }
   }
}
