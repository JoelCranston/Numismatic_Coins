package kotlinx.serialization

@ExperimentalSerializationApi
public class MissingFieldException(missingFields: List<String>, message: String?, cause: Throwable?) : SerializationException(message, cause) {
   public final val missingFields: List<String>

   init {
      this.missingFields = missingFields;
   }

   public constructor(missingFields: List<String>, serialName: String) : this(
         missingFields,
         if (missingFields.size() == 1)
            "Field '${missingFields.get(0) as java.lang.String}' is required for type with serial name '$serialName', but it was missing"
            else
            "Fields $missingFields are required for type with serial name '$serialName', but they were missing",
         null
      )
   public constructor(missingField: String, serialName: String) : this(
         CollectionsKt.listOf(missingField), "Field '$missingField' is required for type with serial name '$serialName', but it was missing", null
      )
   @PublishedApi
   internal constructor(missingField: String) : this(CollectionsKt.listOf(missingField), "Field '$missingField' is required, but it was missing", null)}
