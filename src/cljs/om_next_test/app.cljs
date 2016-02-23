(ns om-next-test.app
  (:require [om.next :as om :refer-macros [defui]]
            ;;
            [om-next-test.split :as split]))

(defui Main
  static om/IQuery
  (query [this]
         [{:app/main (om/get-query split/Counter)}])
  Object
  (render [this]
          (let [{:keys [app/main]} (om/props this)]
            (split/split-ui main))))
