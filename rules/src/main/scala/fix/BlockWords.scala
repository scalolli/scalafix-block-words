package fix

import scalafix.v1._

import scala.meta._

case class BlockedWord(value: String, pos: Position) extends Diagnostic {
  override def position: Position = pos

  override def message: String =
    s"Blocked word! '$value'"
}

class BlockWords extends SyntacticRule("BlockWords") {

  override def fix(implicit doc: SyntacticDocument): Patch = {
    doc.tree.collect {
      //      case t@Term.Apply(Term.Name(name), List()) if containsBlockedWord(name) =>
      //        List(Patch.lint(BlockedWord(name, t.pos)))
      case Defn.Val(List(), pats, None, value) => {
        val patch1 = value match {
          case s@Lit.String(v) if containsBlockedWord(v) =>
            Patch.lint(BlockedWord(v, s.pos))
          case _ =>
            Patch.empty
        }
        val patch2 = pats.collect {
          case v@Pat.Var(name) if containsBlockedWord(name.value) =>
            Patch.lint(BlockedWord(name.value, v.pos))
        }

        patch1 :: patch2
      }
    }.flatten.asPatch
  }

  private def containsBlockedWord(word: String): Boolean = {
    List("master", "sanityCheck", "whitelist", "blacklist").map(_.toLowerCase()).exists(blockedWord => {
      val wordIgnoredCase = word.toLowerCase()
      wordIgnoredCase.contains(blockedWord) || wordIgnoredCase.startsWith(blockedWord)
    })
  }

}
