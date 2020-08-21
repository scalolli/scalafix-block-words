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


