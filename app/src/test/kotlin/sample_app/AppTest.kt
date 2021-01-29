package sample_app

import kotlin.test.Test

import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Method.GET
import org.http4k.core.Status.Companion.OK
import org.http4k.core.Status.Companion.NOT_FOUND

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.hamkrest.hasBody

class AppTest {
    @Test fun `GET status returns OK string`() {
        val actualResponse = simpleResponseLens(app(Request(GET, "/v1/status")))
        val expectedResponse = equalTo(SimpleResponse(OK, "Sample App"))
        assertThat(actualResponse, expectedResponse)
    }
}