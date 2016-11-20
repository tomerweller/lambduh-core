(ns lambduhduh.views
  (:require
    [re-frame.core :refer [subscribe dispatch]]
    [reagent.core :as r]
    [lambduhduh.util :refer [log]]))

(def ace-editor (r/adapt-react-class (aget js/deps "react-ace" "default")))

(defn brick [brick-id]
  (let [brick-code (subscribe [:brick-code brick-id])]
    [:div#editor-container {:key brick-id}
     [ace-editor
      {:value @brick-code
       :name (str brick-id)
       :mode "clojure"
       :theme "twilight"
       :height "50px"
       :on-change #(dispatch [:brick-change-code brick-id %])}]]))

(defn root []
  (fn []
    (let [bricks-keys (subscribe [:bricks-keys])]
      (log "rendering root")
      (log "bricks-keys" @bricks-keys)
      [:div [:h1 "Lambduh"]
       (doall (map (fn [brick-id] (brick brick-id)) @bricks-keys))
       [:button
        {:on-click #(dispatch [:brick-add])}
        "Add code"]])))