package poc.repositories.assessmentRuns

import poc.entities.AssessmentAnswer
import poc.entities.AssessmentQuestion
import poc.entities.AssessmentRun
import poc.entities.Candidate
import poc.entities.Lang

// Should all be in DB

object AssessmentRuns {
  private val assessments: MutableList<AssessmentRun> = mutableListOf()

  private fun addAssessment(candidateId: Int, lang: Lang): AssessmentRun {
    val assessment =
        AssessmentRun(
            id = this.assessments.size + 1,
            candidate = Candidate.random(candidateId),
            questions =
                listOf(
                    AssessmentQuestion(
                        id = 21,
                        label = "Do you like chocolate?",
                        lang = lang,
                        answers =
                            listOf(
                                AssessmentAnswer(id = 4, label = "Nope"),
                                AssessmentAnswer(id = 6, label = "A little"),
                                AssessmentAnswer(id = 9, label = "A lot"))),
                    AssessmentQuestion(
                        id = 22,
                        label = "What about Kotlin?",
                        lang = lang,
                        answers =
                            listOf(
                                AssessmentAnswer(id = 4, label = "Nope"),
                                AssessmentAnswer(id = 6, label = "A little"),
                                AssessmentAnswer(id = 9, label = "A lot"))),
                    AssessmentQuestion(
                        id = 23,
                        label = "Elixir is overrated?",
                        lang = lang,
                        answers =
                            listOf(
                                AssessmentAnswer(id = 10, label = "Yes"),
                                AssessmentAnswer(id = 11, label = "Absolutely"),
                                AssessmentAnswer(id = 13, label = "Fuck yes")))),
            answers = mutableListOf(),
            inProgress = true)

    this.assessments.add(assessment)

    return assessment
  }

  private infix fun get(candidateId: Int) = this.assessments.find { it.candidate.id == candidateId }

  private infix fun getOrThrow(candidateId: Int): AssessmentRun {
    val assessment = this get candidateId

    if (assessment == null) {
      throw Exception("No assessment running for candidate with id $candidateId")
    }

    return assessment
  }

  fun getByCandidateIdOrCreate(candidateId: Int, lang: Lang): AssessmentRun? {
    val assessment = this get candidateId

    return when {
      assessment == null -> this.addAssessment(candidateId, lang)
      assessment.inProgress -> assessment
      else -> null
    }
  }

  fun saveAnswer(candidateId: Int, questionId: Int, answerId: Int): Boolean {
    val assessment = this getOrThrow candidateId

    if (!assessment.inProgress) {
      return false
    }

    // We first remove the current answer for the question so that we don't have duplicates
    // If the answer for the question is submitted for the first time, nothing happens
    assessment.answers.removeAll { it.first == questionId }

    // We can now set the question/answer pair
    assessment.answers.add(Pair(questionId, answerId))

    return true
  }

  fun close(candidateId: Int): Boolean {
    val assessment = this getOrThrow candidateId

    // If not all the questions are answered we don't do anything
    if (!assessment.inProgress || assessment.answers.size != assessment.questions.size) {
      return false
    }

    assessment.inProgress = false

    return true
  }
}
