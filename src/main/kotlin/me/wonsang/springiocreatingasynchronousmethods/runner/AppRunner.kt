package me.wonsang.springiocreatingasynchronousmethods.runner

import me.wonsang.springiocreatingasynchronousmethods.service.GitHubLookupService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class AppRunner : CommandLineRunner {

    private val logger =  LoggerFactory.getLogger(AppRunner::class.java)

    @Autowired
    lateinit var gitHubLookupService: GitHubLookupService

    override fun run(vararg args: String?) {
        // start the clock
        val start: Long = System.currentTimeMillis()

        //kick of multiple, asynchronous lookups
        val page1 = gitHubLookupService.findUser("PivotalSoftware")
        val page2 = gitHubLookupService.findUser("CloudFoundry")
        val page3 = gitHubLookupService.findUser("Spring-Projects")

        // wait until they are all done
        CompletableFuture.allOf(page1, page2, page3).join()

        // print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start))
        logger.info("--> " + page1.get())
        logger.info("--> " + page2.get())
        logger.info("--> " + page3.get())
    }
}