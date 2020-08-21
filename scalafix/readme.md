# Scalafix rule for blocking words from your repository

Scalafix plugin to block words in a repository from the list published by [twitter](https://twitter.com/TwitterEng/status/1278733305190342656)

### How to use

- Setup your project to use scala-fix plugin, just add this line to your plugins.sbt

```
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % "0.9.19")
```

- You can run the linting rules as below:

```
scalafix --rules=github:scalolli/scalafix-block-words
test:scalafix --rules=github:scalolli/scalafix-block-word
```

- You should then see this in your console:

```

###########> Lint       <###########


===========> Unexpected <===========

test/ScalafixBlockWords.scala:7:16: error: [BlockWords]:
Blocked word! 'master'
  val master = "master"
               ^^^^^^^^

test/ScalafixBlockWords.scala:8:7: error: [BlockWords]:
Blocked word! 'sanityCheck'
  val sanityCheck = true
      ^^^^^^^^^^^

---------------------------------------

test/ScalafixBlockWords.scala:9:7: error: [BlockWords]:
Blocked word! 'whiteList'
  val whiteList = Nil
      ^^^^^^^^^
```

### Currently supported rules

- Variable names
- String literals

### Pending

- Class names
- Alternatives in error messages 


