(ns lambduhduh.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require
    [lambduhduh.util :refer [log]]
    [re-frame.core :refer [reg-sub subscribe]]))

(reg-sub
  :bricks-map
  (fn [db  _]
    (:bricks-map db)))

(reg-sub
  :brick
  :<- [:bricks-map]
  (fn [bricks-map q _]
    (let [brick-id (get q 1)]
      (log "brick-id" brick-id)
      (-> bricks-map brick-id :code))))