package com.rex50.notes.utils

import com.rex50.notes.interfaces.providers.TokenProvider

class TokenProviderImpl: TokenProvider {
    override fun getToken(): String {
        return "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwOjAuMC4wLjA6ODA4MC9oZWxsbyIsImlzcyI6Imh0dHA6MC4wLjAuMDo4MDgwLyIsImV4cCI6MTY0MjI1ODI1OSwidXNlcklkIjoxLCJ1c2VybmFtZSI6InJleDUwIn0.L8TzJBl8lZPAtl4F-7jT0r18Wus6DZ0-V6a8f4Syqwk"
    }
}