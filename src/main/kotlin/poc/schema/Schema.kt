package poc.schema

import com.expediagroup.graphql.SchemaGeneratorConfig
import com.expediagroup.graphql.TopLevelObject
import com.expediagroup.graphql.toSchema

object Schema {
  private val config = SchemaGeneratorConfig(supportedPackages = listOf("poc"))

  private val queries = listOf(TopLevelObject(PostQuery()))

  private val mutations = listOf(TopLevelObject(PostMutation()))

  operator fun invoke() = toSchema(config = config, queries = queries, mutations = mutations)
}
