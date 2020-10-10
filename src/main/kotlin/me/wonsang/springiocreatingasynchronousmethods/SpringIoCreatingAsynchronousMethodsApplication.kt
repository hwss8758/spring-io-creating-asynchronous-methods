package me.wonsang.springiocreatingasynchronousmethods

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor
import java.util.concurrent.ThreadPoolExecutor

@SpringBootApplication
@EnableAsync
class SpringIoCreatingAsynchronousMethodsApplication

fun main(args: Array<String>) {
    runApplication<SpringIoCreatingAsynchronousMethodsApplication>(*args)
}

@Bean
fun taskExecutor(): Executor {
    val executor = ThreadPoolTaskExecutor()
    executor.corePoolSize = 2
    executor.maxPoolSize = 2
    executor.setQueueCapacity(500)
    executor.setThreadNamePrefix("GithubLookup-")
    executor.initialize()
    return executor
}