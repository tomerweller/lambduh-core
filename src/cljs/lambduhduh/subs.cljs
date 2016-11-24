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
  :result
  (fn [db  _]
    (:result db)))

(reg-sub
  :brick-code
  :<- [:bricks-map]
  (fn [bricks-map [_ brick-id] _]
    (-> bricks-map brick-id :code)))

(reg-sub
  :brick-ast
  :<- [:bricks-map]
  (fn [bricks-map [_ brick-id] _]
    (-> bricks-map brick-id :ast)))

(reg-sub
  :bricks-keys
  :<- [:bricks-map]
  (fn [bricks-map _ _]
    (keys bricks-map)))

(reg-sub
  :ast-list
  :<- [:bricks-map]
  (fn [bricks-map _ _]
    (let [ast-list (map #(-> % second :ast) bricks-map)]
      (log "ast-list" ast-list)
      ast-list)))