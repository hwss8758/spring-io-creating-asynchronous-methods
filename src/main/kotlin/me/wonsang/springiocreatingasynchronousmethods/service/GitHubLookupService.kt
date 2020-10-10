package me.wonsang.springiocreatingasynchronousmethods.service

import me.wonsang.springiocreatingasynchronousmethods.domain.User
import org.slf4j.LoggerFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture

@Service
class GitHubLookupService {

    val logger = LoggerFactory.getLogger(GitHubLookupService::class.java)

    private lateinit var restTemplate: RestTemplate

    constructor(restTemplateBuilder: RestTemplateBuilder) {
        restTemplate = restTemplateBuilder.build()
    }

    @Async
    fun findUser(user: String): CompletableFuture<User>{
        logger.info("looking up " + user)
        val url = String.format("https://api.github.com/users/%s", user)
        val results = restTemplate.getForObject(url, User::class.java)
        Thread.sleep(1000L)
        return CompletableFuture.completedFuture(results)
    }
}