package com.postit.api
import com.amazonaws.services.lambda.runtime.*
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import io.micronaut.function.aws.proxy.payload1.ApiGatewayProxyRequestEventFunction
import io.micronaut.http.HttpMethod
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class HomeControllerTest : StringSpec({

    "test book controller" {
        val handler = ApiGatewayProxyRequestEventFunction()
        val lambdaContext = object : Context {
            override fun getAwsRequestId(): String {
                return ""
            }

            override fun getLogGroupName(): String {
                return ""
            }

            override fun getLogStreamName(): String {
                return ""
            }

            override fun getFunctionName(): String {
                return ""
            }

            override fun getFunctionVersion(): String {
                return ""
            }

            override fun getInvokedFunctionArn(): String {
                return ""
            }

            override fun getIdentity(): CognitoIdentity {
                return object : CognitoIdentity {
                    override fun getIdentityId(): String {
                        return ""
                    }

                    override fun getIdentityPoolId(): String {
                        return ""
                    }
                }
            }

            override fun getClientContext(): ClientContext {
                return object : ClientContext {
                    override fun getClient(): Client {
                        TODO("Not yet implemented")
                    }

                    override fun getCustom(): MutableMap<String, String> {
                        return mutableMapOf()
                    }

                    override fun getEnvironment(): MutableMap<String, String> {
                        return mutableMapOf()
                    }
                }
            }

            override fun getRemainingTimeInMillis(): Int {
                return 0
            }

            override fun getMemoryLimitInMB(): Int {
                return 0
            }

            override fun getLogger(): LambdaLogger {
                return object : LambdaLogger {
                    override fun log(message: String?) {
                    }
                    override fun log(message: ByteArray?) {
                    }
                }
            }
        }
        val request = APIGatewayProxyRequestEvent()
        request.httpMethod = "GET"
        request.path = "/"
        val response = handler.handleRequest(request, lambdaContext)
        response.statusCode.shouldBe(200)
        response.body.shouldBe("{\"message\":\"Hello World\"}")
        handler.applicationContext.close()
    }
})
