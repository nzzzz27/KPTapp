package model

case class ViewValueCommon (
  title:    String      = "KPT",                                                       // タイトル
  ngJsSrc:  Seq[String] = Seq("main.js", "polyfills.js", "runtime.js", "vendor.js"),   // ng-elementsのJS
  ngCssSrc: Seq[String] = Seq("styles.css.map")                                        // ng-elementsのcss
)
