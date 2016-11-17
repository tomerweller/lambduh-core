(ns lambduhduh.events
  (:require [re-frame.core :refer [reg-event-db]]
            [lambduhduh.db :as db]
            [lambduhduh.util :refer [log]]))

(reg-event-db
  :initialize-db
  (fn  [_ _]
    db/default-db))

;UNTESTED
(reg-event-db
  :modify-brick
  (fn [db _]
    (log "on event with db" db)
    db))

;(reg-event-db
;  :add-todo
;  []                                                        ;interceptors
;  (fn [todos [text]]              ;; the "path" interceptor in `todo-interceptors` means 1st parameter is :todos
;    (let [id (allocate-next-id todos)]
;      (assoc todos id {:id id :title text :done false}))))