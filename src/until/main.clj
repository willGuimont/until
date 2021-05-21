(ns until.main
  (:require [discord.bot :as bot]
            [discord.http :as http])
  (:gen-class))


(bot/defcommand :quit
                [client message]
                (let
                  [user-mentions (:user-mentions message)
                   guild-id (get-in message [:channel :guild-id])]
                  (doseq [user user-mentions]
                    (bot/say (format "Kicking %s out of voicechat" (pr-str (:username user))))
                    (http/edit-member client guild-id (:id user) :channel_id nil)
                    )
                  ))

(defn -main
  [& args]
  (bot/start))
