(ns lambduhduh.core
  (:require
    [reagent.core :as reagent]
    [re-frame.core :as re-frame]
    [lambduhduh.events]
    [lambduhduh.subs]
    [lambduhduh.views :as views]
    [lambduhduh.config :as config]
    [lambduhduh.util :refer [log]]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (log "init. dev mode")))

(defn mount-root []
  (reagent/render
    [views/root]
    (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
