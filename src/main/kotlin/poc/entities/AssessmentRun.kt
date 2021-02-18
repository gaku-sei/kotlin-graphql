package poc.entities

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import org.valiktor.functions.*
import org.valiktor.validate

data class AssessmentRun(
    val id: Int? = null,
    val candidate: Candidate,
    val questions: List<AssessmentQuestion>,
    @GraphQLIgnore val answers: MutableList<Pair<Int, Int>>,
    @GraphQLIgnore var inProgress: Boolean,
) {
  init {
    validate(this) {
      validate(AssessmentRun::id).isPositive()
      validate(AssessmentRun::questions).isNotEmpty()
    }
  }
}
