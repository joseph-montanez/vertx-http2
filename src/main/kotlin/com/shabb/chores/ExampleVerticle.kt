package com.shabb.chores

import io.vertx.core.AbstractVerticle
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.net.JksOptions

class ExampleVerticle : AbstractVerticle() {

    override fun start() {
        val jks= JksOptions()
        jks.path = "keystore2.jks"
        jks.password = "password"
        val server =  vertx.createHttpServer(HttpServerOptions()
                .setUseAlpn(true)
                .setSsl(true)
                .setKeyStoreOptions(jks)
        )

        server.requestHandler({ request ->
            request.response()
                    .putHeader("Content-Type", "text/plain")
                    .end("Greetings, Earthicans!\n")
        }).listen(8443)
    }
}