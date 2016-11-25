(ns lambduhduh.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require
    [lambduhduh.util :refer [log]]
    [reagent.ratom :refer [make-reaction]]
    [re-frame.core :refer [reg-sub reg-sub-raw subscribe dispatch]]
    [cljs.js :refer [empty-state eval js-eval]]))

(reg-sub
  :result
  (fn [db  _]
    (:result db)))

(reg-sub
  :bricks-map
  (fn [db  _]
    (:bricks-map db)))

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

(defn ast->eval
  [ast-list]
  (let [exps (into ast-list `(do))]
    (log "ast->eval" exps)
    (time
      (try
        (eval
          (empty-state)
          exps
          {:eval js-eval}
          (fn [result]
            (dispatch [:new-result (:value result)])))
        (catch
          js/Error e
          (js/console.error "Eval failed:" e))))))

(reg-sub
  :ast-list
  :<- [:bricks-map]
  (fn [bricks-map _ _]
    (let [ast-list (map #(-> % second :ast) bricks-map)]
      (log "ast-list" ast-list)
      (ast->eval ast-list)                                  ;evil hack? maybe.
      ast-list)))