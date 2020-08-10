package by.runets.carscrapper.configuration

import by.runets.carscrapper.configuration.properties.DatabaseProperties
import by.runets.carscrapper.utils.UUIDConverter
import io.r2dbc.pool.ConnectionPool
import io.r2dbc.pool.ConnectionPoolConfiguration
import io.r2dbc.pool.PoolingConnectionFactoryProvider
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.reactive.TransactionalOperator
import java.time.Duration


@Configuration
@EnableR2dbcRepositories("by.runets.carscrapper")
@EnableTransactionManagement
class R2dbcConfiguration (private val databaseProperties: DatabaseProperties) : AbstractR2dbcConfiguration() {
    private final val POSTGRES_DRIVER: String = "pool";
    private final val POSTGRES_PROTOCOL: String = "postgresql";

    private val connectionFactory: ConnectionFactory = ConnectionFactories.get(builder()
            .option(DRIVER, POSTGRES_DRIVER)
            .option(PROTOCOL, POSTGRES_PROTOCOL) // driver identifier, PROTOCOL is delegated as DRIVER by the pool.
            .option(HOST, databaseProperties.host)
            .option(PORT, databaseProperties.port)
            .option(USER, databaseProperties.username)
            .option(PASSWORD, databaseProperties.pass)
            .option(PoolingConnectionFactoryProvider.MAX_SIZE, databaseProperties.maxSize)
            .build())

    @Bean
    override fun connectionFactory(): ConnectionFactory {
        return ConnectionPool(ConnectionPoolConfiguration.builder(connectionFactory)
                .validationQuery(databaseProperties.validationQuery)
                .initialSize(databaseProperties.initializeSize)
                .maxIdleTime(Duration.ofMinutes(databaseProperties.maxIdleTime))
                .maxSize(databaseProperties.maxSize)
                .build())
    }

    @Bean
    fun transactionManager(): R2dbcTransactionManager {
        return R2dbcTransactionManager(connectionFactory)
    }

    @Bean
    override fun r2dbcCustomConversions(): R2dbcCustomConversions {
        return R2dbcCustomConversions(
                listOf(
                        UUIDConverter()
                )
        )
    }

    @Bean
    fun transactionOperator(transactionManager: R2dbcTransactionManager): TransactionalOperator {
        return TransactionalOperator.create(transactionManager)
    }

}