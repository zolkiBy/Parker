package com.zolki.parker.data.networking.result

interface HttpResponse {
    val statusCode: Int
    val statusMessage: String?
    val url: String?
}