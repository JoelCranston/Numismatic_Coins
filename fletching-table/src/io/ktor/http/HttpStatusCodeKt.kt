package io.ktor.http

internal fun allStatusCodes(): List<HttpStatusCode> {
   return CollectionsKt.listOf(
      new HttpStatusCode[]{
         HttpStatusCode.Companion.getContinue(),
         HttpStatusCode.Companion.getSwitchingProtocols(),
         HttpStatusCode.Companion.getProcessing(),
         HttpStatusCode.Companion.getOK(),
         HttpStatusCode.Companion.getCreated(),
         HttpStatusCode.Companion.getAccepted(),
         HttpStatusCode.Companion.getNonAuthoritativeInformation(),
         HttpStatusCode.Companion.getNoContent(),
         HttpStatusCode.Companion.getResetContent(),
         HttpStatusCode.Companion.getPartialContent(),
         HttpStatusCode.Companion.getMultiStatus(),
         HttpStatusCode.Companion.getMultipleChoices(),
         HttpStatusCode.Companion.getMovedPermanently(),
         HttpStatusCode.Companion.getFound(),
         HttpStatusCode.Companion.getSeeOther(),
         HttpStatusCode.Companion.getNotModified(),
         HttpStatusCode.Companion.getUseProxy(),
         HttpStatusCode.Companion.getSwitchProxy(),
         HttpStatusCode.Companion.getTemporaryRedirect(),
         HttpStatusCode.Companion.getPermanentRedirect(),
         HttpStatusCode.Companion.getBadRequest(),
         HttpStatusCode.Companion.getUnauthorized(),
         HttpStatusCode.Companion.getPaymentRequired(),
         HttpStatusCode.Companion.getForbidden(),
         HttpStatusCode.Companion.getNotFound(),
         HttpStatusCode.Companion.getMethodNotAllowed(),
         HttpStatusCode.Companion.getNotAcceptable(),
         HttpStatusCode.Companion.getProxyAuthenticationRequired(),
         HttpStatusCode.Companion.getRequestTimeout(),
         HttpStatusCode.Companion.getConflict(),
         HttpStatusCode.Companion.getGone(),
         HttpStatusCode.Companion.getLengthRequired(),
         HttpStatusCode.Companion.getPreconditionFailed(),
         HttpStatusCode.Companion.getPayloadTooLarge(),
         HttpStatusCode.Companion.getRequestURITooLong(),
         HttpStatusCode.Companion.getUnsupportedMediaType(),
         HttpStatusCode.Companion.getRequestedRangeNotSatisfiable(),
         HttpStatusCode.Companion.getExpectationFailed(),
         HttpStatusCode.Companion.getUnprocessableEntity(),
         HttpStatusCode.Companion.getLocked(),
         HttpStatusCode.Companion.getFailedDependency(),
         HttpStatusCode.Companion.getTooEarly(),
         HttpStatusCode.Companion.getUpgradeRequired(),
         HttpStatusCode.Companion.getTooManyRequests(),
         HttpStatusCode.Companion.getRequestHeaderFieldTooLarge(),
         HttpStatusCode.Companion.getInternalServerError(),
         HttpStatusCode.Companion.getNotImplemented(),
         HttpStatusCode.Companion.getBadGateway(),
         HttpStatusCode.Companion.getServiceUnavailable(),
         HttpStatusCode.Companion.getGatewayTimeout(),
         HttpStatusCode.Companion.getVersionNotSupported(),
         HttpStatusCode.Companion.getVariantAlsoNegotiates(),
         HttpStatusCode.Companion.getInsufficientStorage()
      }
   );
}

public fun HttpStatusCode.isSuccess(): Boolean {
   val var1: Int = `$this$isSuccess`.getValue();
   return 200 <= var1 && var1 < 300;
}
