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
  :brick-code
  :<- [:bricks-map]
  (fn [bricks-map q _]
    (let [brick-id (get q 1)]
      (-> bricks-map brick-id :code))))

(reg-sub
  :brick-ast
  :<- [:bricks-map]
  (fn [bricks-map q _]
    (let [brick-id (get q 1)]
      (-> bricks-map brick-id :ast))))

(reg-sub
  :bricks-keys
  :<- [:bricks-map]
  (fn [bricks-map _ _]
    (keys bricks-map)))