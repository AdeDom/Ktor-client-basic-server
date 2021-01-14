package com.adedom.thepharak.acc

import com.adedom.thepharak.acc.model.Staff
import com.adedom.thepharak.acc.model.StaffResponse
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import org.jsoup.Jsoup

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {

    // start project
    install(DefaultHeaders)
    install(CallLogging)

    // gson convertor json
    install(ContentNegotiation) {
        json()
    }

    // route
    install(Routing) {

        get("/api/thepharak-acc/staff") {
            val response = StaffResponse()

            val baseUrl = "http://www.thepharak-acc.com/"
            val url = baseUrl + "contact"
            val doc = Jsoup.connect(url).get()

            val data = doc.select("div.staff")

            val logo = baseUrl + doc.select("div.container-fluid")
                .select("img")
                .attr("src")

            val staffList = mutableListOf<Staff>()
            for (i in 0 until data.size) {
                val name = data[i]
                    .select("b")
                    .text()

                val fullName = name.substringBefore("(").trim()

                val nickName = name.substringAfter("(")
                    .substringBefore(")")
                    .trim()

                val position = data[i]
                    .select("span.position.mb-4")
                    .text()

                val imageOriginal = data[i]
                    .select("div.img.align-self-stretch")
                    .attr("style")

                val imgUrl = baseUrl + imageOriginal
                    .substringAfter("background-image: url(")
                    .substringBefore(");")

                val staff = Staff(
                    fullName = fullName,
                    nickName = if (nickName == fullName) "" else nickName,
                    position = position,
                    imgUrl = imgUrl,
                )
                staffList.add(staff)
            }

            response.success = true
            response.message = "Fetch staff success"
            response.logo = logo
            response.staffList = staffList

            call.respond(response)
        }

    }

}
