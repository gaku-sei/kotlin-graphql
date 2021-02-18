package poc.schema

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema

object Schema {
  private val config = SchemaGeneratorConfig(supportedPackages = listOf("poc"))

  private val queries = listOf(TopLevelObject(PostQuery()))

  private val mutations =
      listOf(TopLevelObject(PostMutation()), TopLevelObject(AssessmentMutation()))

  operator fun invoke() = toSchema(config = config, queries = queries, mutations = mutations)
}
