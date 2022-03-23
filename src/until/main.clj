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

(defn kick-user [duration client guild-id user]
  (bot/say (format "Will kick %s out of voicechat in %d minutes" (:username user) duration))
  (Thread/sleep (* duration 60 1000))
  (bot/say "Sleep is for the weak but I am very weak so I need sleep")
  (bot/say (format "Kicking %s out of voicechat" (pr-str (:username user))))
  (http/edit-member client guild-id (:id user) :channel_id nil))

(bot/defcommand :talk-for
                [client message]
                (let [user-mentions (:user-mentions message)
                      guild-id (get-in message [:channel :guild-id])
                      duration (get-duration message)
                      kick-fn (partial kick-user duration client guild-id)]
                  (if (> duration 0)
                    (doall
                      (pmap kick-fn user-mentions))
                    (bot/say "Invalid command, expected: !talk-for <duration-in-minutes> @User1 @User2 ... @UserN"))))

(defn -main
  [& args]
  (bot/start))
