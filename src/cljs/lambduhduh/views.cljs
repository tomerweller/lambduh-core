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
       :on-change #(log %)}]]))

(defn root []
  (let [bricks-map (subscribe [:bricks-map])]
    [:div [:h1 "Lambduh"]
     (doall (map (fn [brick-id] (brick brick-id)) (keys @bricks-map)))
     [:button
      {:on-click #(dispatch [:brick-add])}
      "Add code"]]))