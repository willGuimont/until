(defproject until "0.1.0-SNAPSHOT"
  :description "A dicord bot that kicks you out of voicechat after x minutes"
  :url "https://github.com/willGuimont/until"
  :license {:name "MIT"
            :url  "https://choosealicense.com/licenses/mit/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/core.async "0.3.443"]
                 [clj-http "3.6.1"]
                 [clj-time "0.14.4"]
                 [com.taoensso/timbre "4.10.0"]
                 [org.clojure/data.json "0.2.6"]
                 [overtone/at-at "1.2.0"]
                 [stylefruits/gniazdo "1.2.0"]]
  :main until.main)
