(ns lambduhduh.views
  (:require
    [re-frame.core :as re-frame]
    [reagent.core :as r]
    [lambduhduh.util :refer [log]]))

(def ace-editor (r/adapt-react-class (aget js/deps "react-ace" "default")))

(defn brick [id]
  (let [code (re-frame/subscribe [:name])]
    (print code)
    [:div#editor-container
     [ace-editor
      {:value @code
       :name (str id)
       :mode "clojure"
       :theme "twilight"
       :height "150px"
       :on-change #(log %)}]]))

(defn main-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div "Hello from " @name
       (brick "5")])))