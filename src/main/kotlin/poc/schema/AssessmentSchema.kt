package poc.schema

import com.expediagroup.graphql.generator.annotations.GraphQLDescription
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import poc.entities.Lang
import poc.repositories.assessmentRuns.AssessmentRuns

class AssessmentQuery {}

class AssessmentMutation {
  @GraphQLDescription(
      """
        Returns all the questions available for the candidate in the specified language.
        The mutation will automatically creates a run the first time it's requested.
        Returns null if the assessment run for the candidate has already been submitted.
      """)
  fun assessmentQuestions(candidateId: Int, lang: Lang) =
      AssessmentRuns.getByCandidateIdOrCreate(candidateId, lang)

  @GraphQLDescription(
      """
        Submits one answer to a question for a candidate.
        Returns false if the assessment run for the candidate has already been submitted.
      """)
  fun submitAnswer(candidateId: Int, questionId: Int, answerId: Int) =
      AssessmentRuns.saveAnswer(
          candidateId = candidateId, questionId = questionId, answerId = answerId)

  @GraphQLDescription(
      """
        Submits the whole assessment, will close the assessment.
        After closing an assessment there is no way to submit any more answers.
        Returns false if the assessment run for the candidate has already been submitted
        or if not all the questions have been answered.
      """)
  fun submitAssessment(candidateId: Int) = AssessmentRuns.close(candidateId)
}
