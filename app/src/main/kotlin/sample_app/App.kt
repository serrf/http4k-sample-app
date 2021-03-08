package sample_app

import org.http4k.core.Body
import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Request
import org.http4k.core.with
import org.http4k.core.Method.GET
import org.http4k.core.Status
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.Query
import org.http4k.lens.int

// Import the Routing
import org.http4k.routing.bind
import org.http4k.routing.routes

// Import the Server
import org.http4k.server.SunHttp
import org.http4k.server.asServer

// Import Serialization with Jackson
import org.http4k.format.Jackson
import org.http4k.format.Jackson.auto


data class SimpleResponse(val status: Status, val message: String)
val simpleResponseLens = Body.auto<SimpleResponse>().toLens()

val app: HttpHandler = routes(
    "v1" bind routes(
        "status" bind GET to {
            Response(OK).with(simpleResponseLens of SimpleResponse(OK, "Sample App"))
        }
    )
)

fun main(args: Array<String>) {
    app.asServer(SunHttp(80)).start().also {
        println("Now Serving at http://0.0.0.0:${it.port()}")
    }
}