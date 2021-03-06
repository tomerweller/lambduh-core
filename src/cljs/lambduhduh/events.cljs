(ns lambduhduh.events
  (:require [re-frame.core :refer [reg-event-db]]
            [lambduhduh.db :as db]
            [lambduhduh.util :refer [log]]))

;TODO: ADD INTERCEPTORS

(reg-event-db
  :initialize-db
  (fn  [_ _]
    db/default-db))

(defn next-id []
  (keyword (str (rand-int 9999))))

(reg-event-db
  :brick-add
  (fn [db _]
    (let [new-brick-id (next-id)
          new-brick {:code "(+ 1 2)"}
          new-db (assoc-in db [:bricks-map new-brick-id] new-brick)]
      new-db)))

(defn code->ast
  [code]
  (cljs.reader/read-string code))

(reg-event-db
  :brick-change-code
  (fn [db q]
    (let [[_ brick-id code] q
          ast (code->ast code)
          db-with-code (assoc-in db [:bricks-map brick-id :code] code)
          db-with-ast (assoc-in db-with-code [:bricks-map brick-id :ast] ast)]
      db-with-ast)))

(reg-event-db
  :new-result
  (fn [db [_ result]]
    (let [new-db (assoc-in db [:result] result)]
      (log ":new-result " result)
      (log "new-db " new-db)
      new-db)))