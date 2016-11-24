(ns lambduhduh.views
  (:require
    [re-frame.core :refer [subscribe dispatch]]
    [reagent.core :as r]
    [lambduhduh.util :refer [log]]))

(def ace-editor (r/adapt-react-class (aget js/deps "react-ace" "default")))

(defn brick [brick-id]
  (let [brick-code (subscribe [:brick-code brick-id])
        brick-ast (subscribe [:brick-ast brick-id])]
    (log "rendering brick" brick-id)
    ^{:key (str brick-id "-brick") } [:div#editor-container
     [ace-editor
      {:value @brick-code
       :name (str brick-id)
       :mode "clojure"
       :theme "twilight"
       :height "50px"
       :on-change #(dispatch [:brick-change-code brick-id %])}]
     [:p "ast (" brick-id ") " @brick-ast]]))

(defn result-container []
  (let [result (subscribe [:result])
        ast-list (subscribe [:ast-list])]
    (log "result is " @result)
    [:div#result-container
     [:p "result " @result]
     [:p "ast list " @ ast-list]]))

(defn root []
  (let [bricks-keys (subscribe [:bricks-keys])]
    (log "rendering root")
    [:div [:h1 "Lambduh"]
     (doall (map (fn [brick-id] ^{:key brick-id} [brick brick-id]) @bricks-keys))
     [:button
      {:on-click #(dispatch [:brick-add])}
      "Add code"]
     [result-container]]))
