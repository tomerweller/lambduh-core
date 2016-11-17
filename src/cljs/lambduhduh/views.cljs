(ns lambduhduh.views
  (:require
    [re-frame.core :as re-frame]
    [reagent.core :as r]
    [lambduhduh.util :refer [log]]))

(def ace-editor (r/adapt-react-class (aget js/deps "react-ace" "default")))

(defn brick [id]
  (let [code (re-frame/subscribe [:brick id])]
    [:div#editor-container
     [ace-editor
      {:value @code
       :name (str id)
       :mode "clojure"
       :theme "twilight"
       :height "100px"
       :on-change #(log %)}]]))

(defn root []
  (let [name "Tomer"]
    (fn []
      [:div "Hello from " name
       (brick :a)
       (brick :b)
       [:button
        {:on-click #(log %)}
        "Add code"]])))


