(ns until.main
  (:require [clojure.string :as str]
            [discord.bot :as bot]
            [discord.http :as http])
  (:gen-class))

(defn parse-int [s]
  (Integer/parseInt (re-find #"\A-?\d+" s)))

(defn get-duration [message] (try (-> (:content message)
                                      (str/split #" ")
                                      first
                                      parse-int)
                                  (catch Exception e 0)))

(defmacro dopar [seq-expr & body]
  (assert (= 2 (count seq-expr)) "single pair of forms in sequence expression")
  (let [[k v] seq-expr]
    `(apply await
            (for [k# ~v]
              (let [a# (agent k#)]
                (send a# (fn [~k] ~@body))
                a#)))))

(bot/defcommand :talk-for
                [client message]
                (let [user-mentions (:user-mentions message)
                      guild-id (get-in message [:channel :guild-id])
                      duration (get-duration message)]
                  (if (> duration 0)
                    (dopar [user user-mentions]
                           (bot/say (format "Will kick %s out of voicechat in %d minutes" (:username user) duration))
                           (Thread/sleep (* duration 60 1000))
                           (bot/say "Sleep is for the weak but I am very weak so I need sleep")
                           (bot/say (format "Kicking %s out of voicechat" (pr-str (:username user))))
                           (http/edit-member client guild-id (:id user) :channel_id nil))
                    (bot/say "Invalid command, expected: !talk-for <duration-in-minutes> @User1 @User2 ... @UserN"))))

(defn -main
  [& args]
  (bot/start))
