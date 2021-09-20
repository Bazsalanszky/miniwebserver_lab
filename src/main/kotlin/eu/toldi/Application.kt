package eu.toldi

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import eu.toldi.plugins.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.security.InvalidParameterException

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        routing {
            get("/") {
                call.respondText("Hello, world!")
            }
            get("hello"){
                val hello = if(call.request.queryParameters["name"] != null) call.request.queryParameters["name"] else "stranger"
                call.respondText("Hello $hello")
            }
            get("sum"){
                val a : Int = call.request.queryParameters["a"]?.toInt() ?: 0
                val b : Int = call.request.queryParameters["b"]?.toInt() ?: 0
                call.respondText("Sum: ${a+b}")
            }
        }
    }.start(wait = true)
}
