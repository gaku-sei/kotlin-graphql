package poc

import graphql.ExecutionInput.Builder
import graphql.GraphQL.newGraphQL
import java.sql.Connection
import org.flywaydb.core.Flyway
import org.http4k.core.Method.POST
import org.http4k.graphql.*
import org.http4k.routing.bind
import org.http4k.routing.graphQL
import org.http4k.routing.routes
import org.http4k.server.ApacheServer
import org.http4k.server.asServer
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import poc.schema.Schema

object GlobalGraphQLHandler : GraphQLHandler {
  private val graphQL = newGraphQL(Schema()).build()

  override fun invoke(request: GraphQLRequest) =
      GraphQLResponse.from(
          graphQL.execute(
              Builder()
                  .query(request.query)
                  .variables(request.variables)
                  .operationName(request.operationName)))
}

object App {
  private val graphqlHandler = graphQL(GlobalGraphQLHandler)

  operator fun invoke() = routes("/graphql" bind POST to graphqlHandler)
}

fun main() {
  Database.connect("jdbc:sqlite:data.db", driver = "org.sqlite.JDBC")

  val flyway = Flyway.configure().dataSource("jdbc:sqlite:data.db", "", "").load()

  flyway.migrate()

  TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE

  App().asServer(ApacheServer(8000)).start()
}
