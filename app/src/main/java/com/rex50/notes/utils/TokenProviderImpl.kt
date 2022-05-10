package com.rex50.notes.utils

import com.rex50.notes.interfaces.providers.TokenProvider

class TokenProviderImpl: TokenProvider {
    private val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwOjAuMC4wLjA6ODA4MC9oZWxsbyIsImlzcyI6Imh0dHA6MC4wLjAuMDo4MDgwLyIsImV4cCI6MTY1NDgwNTA0OCwidXNlcklkIjoxLCJ1c2VybmFtZSI6InJleDUwIn0.VFocVmvbcvm2W4UbmLMw-JpC_7UHQ2YW0Zbz8Xam8s0"
    override fun getToken(): String {
        return "Bearer $token"
    }
}